const fs = require('fs');
const data = JSON.parse(fs.readFileSync('audit_results.json', 'utf8'));

// Add broken files
const broken = [
  { id: 'anat_ch22', name: 'Anatomy Chapter 22', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File corrupted (JSON SyntaxError).', missing: ['All content (syntax error)'] },
  { id: 'anat_ch23', name: 'Anatomy Chapter 23', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File corrupted (JSON SyntaxError).', missing: ['All content (syntax error)'] },
  { id: 'anat_ch33', name: 'Anatomy Chapter 33', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File corrupted (JSON SyntaxError).', missing: ['All content (syntax error)'] },
  { id: 'anat_ch34', name: 'Anatomy Chapter 34', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File corrupted (JSON SyntaxError).', missing: ['All content (syntax error)'] }
];
broken.forEach(b => data.push(b));

const subjects = {};
data.forEach(ch => {
    if (!subjects[ch.subject]) subjects[ch.subject] = [];
    subjects[ch.subject].push(ch);
});

let out = "# PHYSIOPILOT CONTENT QUALITY INVENTORY\n\n";

// TASK 1 & 2 & 3 Grouped
out += "## TASK 1, 2 & 3: CHAPTER INVENTORY, CLASSIFICATION & MISSING CONTENT\n\n";
for (const sub of Object.keys(subjects)) {
    out += `### Subject: ${sub}\n`;
    
    // Group by score/classification to avoid repetitive lines
    const groups = {};
    subjects[sub].forEach(ch => {
        const key = `${ch.score}_${ch.classification}`;
        if (!groups[key]) groups[key] = {
            score: ch.score, classification: ch.classification, justification: ch.justification,
            alignment: ch.alignment, accuracy: ch.accuracy, pt: ch.ptRelevance, read: ch.readability, exam: ch.examReadiness,
            chapters: []
        };
        groups[key].chapters.push(ch);
    });

    for (const key of Object.keys(groups)) {
        const g = groups[key];
        out += `\n**Score: ${g.score} | ${g.classification}**\n`;
        out += `*Metrics: Completeness: ${g.score}%, WBUHS: ${g.alignment}, Clin. Acc: ${g.accuracy}, PT Rel: ${g.pt}, Readability: ${g.read}, Exam Ready: ${g.exam}*\n`;
        out += `*Justification: ${g.justification}*\n\n`;
        
        g.chapters.forEach(ch => {
            const missingStr = ch.missing.length ? ch.missing.join(', ') : 'None';
            out += `- **${ch.id}** (${ch.name}): Missing: ${missingStr}\n`;
        });
    }
    out += "\n";
}

// TASK 4
out += "## TASK 4: SUBJECT SUMMARY TABLE\n\n";
out += "| Subject | Total | Production Ready | Minor Revision | Major Revision | Rewrite Required |\n";
out += "|---|---|---|---|---|---|\n";
for (const sub of Object.keys(subjects)) {
    let pr = 0, min = 0, maj = 0, rew = 0;
    subjects[sub].forEach(ch => {
        if (ch.classification === "Production Ready") pr++;
        else if (ch.classification === "Minor Revision Needed") min++;
        else if (ch.classification === "Major Revision Needed") maj++;
        else rew++;
    });
    out += `| ${sub} | ${subjects[sub].length} | ${pr} | ${min} | ${maj} | ${rew} |\n`;
}

// TASK 5
out += "\n## TASK 5: DEFINITIVE EXECUTION QUEUE\n\n";
out += "Ordered by highest educational impact needed (lowest score first).\n\n";

const sorted = [...data].sort((a, b) => a.score - b.score);
const queueGroups = {};
sorted.forEach(ch => {
    if (!queueGroups[ch.score]) queueGroups[ch.score] = [];
    queueGroups[ch.score].push(`${ch.id} (${ch.classification})`);
});

Object.keys(queueGroups).sort((a, b) => a - b).forEach(score => {
    out += `**Priority Score ${score}:**\n`;
    out += queueGroups[score].join(', ') + '\n\n';
});

fs.writeFileSync('final_report_condensed.md', out);
