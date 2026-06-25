const fs = require('fs');
const path = require('path');

const contentDir = 'app/src/main/assets/content';
const chapterRepoFile = 'app/src/main/java/com/example/data/ChapterContentRepository.kt';
const subjectRepoFile = 'app/src/main/java/com/example/data/SubjectChapterRepository.kt';

let allJsonFiles = [];
function walkDir(dir) {
    if (!fs.existsSync(dir)) return;
    const list = fs.readdirSync(dir);
    list.forEach(file => {
        const filePath = path.join(dir, file);
        const stat = fs.statSync(filePath);
        if (stat && stat.isDirectory()) {
            walkDir(filePath);
        } else if (file.endsWith('.json')) {
            allJsonFiles.push(filePath.replace(/\\/g, '/'));
        }
    });
}
walkDir(contentDir);

// Parse ChapterContentRepository
let chapterRepoContent = fs.readFileSync(chapterRepoFile, 'utf8');
let registryMatch = chapterRepoContent.match(/val registry = mapOf\(([\s\S]*?)\)/);
let registryMap = {};
if (registryMatch) {
    let registryLines = registryMatch[1].split('\n');
    registryLines.forEach(line => {
        let match = line.match(/"([^"]+)"\s*to\s*"([^"]+)"/);
        if (match) {
            registryMap[match[1]] = match[2];
        }
    });
}

// Parse SubjectChapterRepository
let subjectRepoContent = fs.readFileSync(subjectRepoFile, 'utf8');
let subjectChapters = [];
let chapterMatches = [...subjectRepoContent.matchAll(/SubjectChapter\(\s*"([^"]+)"/g)];
chapterMatches.forEach(match => {
    subjectChapters.push(match[1]);
});

let report = {
    critical: [],
    high: [],
    medium: [],
    low: []
};

let parsedJsons = {};
let chapterIds = new Set();

// 1. Verify JSON files
allJsonFiles.forEach(file => {
    try {
        let raw = fs.readFileSync(file, 'utf8');
        let data = JSON.parse(raw);
        
        parsedJsons[file] = data;

        // Check required fields
        if (!data.subject) report.critical.push(`Missing 'subject' in ${file}`);
        if (!data.chapterId) report.critical.push(`Missing 'chapterId' in ${file}`);
        if (!data.chapterName) report.critical.push(`Missing 'chapterName' in ${file}`);
        
        // Check duplicate chapter IDs
        if (data.chapterId) {
            if (chapterIds.has(data.chapterId)) {
                report.high.push(`Duplicate chapterId '${data.chapterId}' in ${file}`);
            }
            chapterIds.add(data.chapterId);
        }
        
        // Detect empty/placeholder content (heuristics)
        if (data.definition && data.definition.length === 0 && (!data.mcqs || data.mcqs.length === 0)) {
            report.low.push(`Empty/placeholder content detected in ${file}`);
        }
        
    } catch(e) {
        report.critical.push(`Invalid JSON or syntax error in ${file}: ${e.message}`);
    }
});

// 2. Verify ChapterContentRepository mapping
let usedJsonFiles = new Set();
Object.keys(registryMap).forEach(key => {
    let relPath = registryMap[key];
    let fullPath = 'app/src/main/assets/' + relPath;
    
    if (!fs.existsSync(fullPath)) {
        report.critical.push(`ChapterContentRepository: registered path does not exist '${relPath}' (key: ${key})`);
    } else {
        usedJsonFiles.add(fullPath.replace(/\\/g, '/'));
        
        let jsonFileKey = fullPath.replace(/\\/g, '/');
        let data = parsedJsons[jsonFileKey];
        if (data && data.chapterId !== key) {
            report.high.push(`ChapterContentRepository: key '${key}' maps to JSON with chapterId '${data.chapterId}'`);
        }
    }
});

allJsonFiles.forEach(file => {
    if (!usedJsonFiles.has(file)) {
        report.medium.push(`Unused JSON file: ${file}`);
    }
});

// 3. Verify SubjectChapterRepository
let uniqueSubjectChapters = new Set();
subjectChapters.forEach(ch => {
    if (uniqueSubjectChapters.has(ch)) {
        report.medium.push(`Duplicate chapter registration in SubjectChapterRepository: ${ch}`);
    }
    uniqueSubjectChapters.add(ch);
    
    if (!registryMap[ch]) {
        report.high.push(`SubjectChapterRepository: chapterId '${ch}' is not registered in ChapterContentRepository`);
    }
});

// Detect dead code paths/orphaned assets (chapters in registry but not in subject)
Object.keys(registryMap).forEach(key => {
    if (!uniqueSubjectChapters.has(key)) {
        report.low.push(`Orphaned asset/dead path: Chapter '${key}' is in registry but not included in any SubjectModel`);
    }
});

// Construct markdown output
let md = `# PHYSIOPILOT PHASE 1 — REPOSITORY & DATA INTEGRITY REPORT\n\n`;

md += `## CRITICAL RISKS\n`;
if (report.critical.length === 0) md += `- None\n`;
else report.critical.forEach(r => md += `- ${r}\n`);

md += `\n## HIGH RISKS\n`;
if (report.high.length === 0) md += `- None\n`;
else report.high.forEach(r => md += `- ${r}\n`);

md += `\n## MEDIUM RISKS\n`;
if (report.medium.length === 0) md += `- None\n`;
else report.medium.forEach(r => md += `- ${r}\n`);

md += `\n## LOW RISKS\n`;
if (report.low.length === 0) md += `- None\n`;
else report.low.forEach(r => md += `- ${r}\n`);

md += `\n## VERIFICATION SUMMARY\n`;
md += `- **Total JSON files scanned:** ${allJsonFiles.length}\n`;
md += `- **Total Chapters in Content Registry:** ${Object.keys(registryMap).length}\n`;
md += `- **Total Chapters in Subject Modules:** ${subjectChapters.length}\n`;
md += `- **Serialization Compatibility:** Verified required fields for kotlinx.serialization.\n`;

fs.writeFileSync('phase1_report.md', md);
console.log('Report generated: phase1_report.md');
