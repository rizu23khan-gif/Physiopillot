const fs = require('fs');

const data = JSON.parse(fs.readFileSync('audit_results.json', 'utf8'));

// Add the broken files manually
const broken = [
  { id: 'anat_ch22', name: 'Anatomy Chapter 22', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File is critically corrupted with JSON syntax errors preventing parsing.', missing: ['Missing all content due to JSON syntax corruption'] },
  { id: 'anat_ch23', name: 'Anatomy Chapter 23', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File is critically corrupted with JSON syntax errors preventing parsing.', missing: ['Missing all content due to JSON syntax corruption'] },
  { id: 'anat_ch33', name: 'Anatomy Chapter 33', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File is critically corrupted with JSON syntax errors preventing parsing.', missing: ['Missing all content due to JSON syntax corruption'] },
  { id: 'anat_ch34', name: 'Anatomy Chapter 34', subject: 'Anatomy', score: 0, completeness: 0, alignment: 'Not verified', accuracy: 'Not verified', ptRelevance: 'Not verified', readability: 'Not verified', examReadiness: 'Not verified', classification: 'Complete Rewrite Required', justification: 'File is critically corrupted with JSON syntax errors preventing parsing.', missing: ['Missing all content due to JSON syntax corruption'] }
];

broken.forEach(b => data.push(b));

// Group by Subject
const subjects = {};
data.forEach(ch => {
    if (!subjects[ch.subject]) subjects[ch.subject] = [];
    subjects[ch.subject].push(ch);
});

let md = "# PHYSIOPILOT CONTENT QUALITY INVENTORY\n\n";

// Task 1: Inventory per subject
md += "## TASK 1: CHAPTER INVENTORY\n\n";
for (const sub of Object.keys(subjects)) {
    md += `### Subject: ${sub}\n\n`;
    md += `| ID | Chapter Name | Complete % | WBUHS Align | Clin Acc | PT Rel | Readability | Exam Ready | Score |\n`;
    md += `|---|---|---|---|---|---|---|---|---|\n`;
    subjects[sub].forEach(ch => {
        md += `| ${ch.id} | ${ch.name} | ${ch.completeness}% | ${ch.alignment} | ${ch.accuracy} | ${ch.ptRelevance} | ${ch.readability} | ${ch.examReadiness} | **${ch.score}** |\n`;
    });
    md += `\n`;
}

// Task 2 specific (Justifications)
md += "## TASK 2: CHAPTER CLASSIFICATIONS\n\n";
data.forEach(ch => {
    md += `- **${ch.id} (${ch.name})**: ${ch.classification}. *${ch.justification}*\n`;
});
md += `\n`;

// Task 3: Missing content
md += "## TASK 3: MISSING CONTENT ANALYSIS\n\n";
data.forEach(ch => {
    md += `### ${ch.id}: ${ch.name}\n`;
    if (ch.missing.length === 0) {
        md += `- No major missing components.\n`;
    } else {
        ch.missing.forEach(m => {
            md += `- ${m}\n`;
        });
    }
    md += `\n`;
});

// Task 4: Subject summary table
md += "## TASK 4: SUBJECT SUMMARY TABLE\n\n";
md += `| Subject | Total Chapters | Production Ready | Minor Revision | Major Revision | Rewrite Required |\n`;
md += `|---|---|---|---|---|---|\n`;
for (const sub of Object.keys(subjects)) {
    let pr = 0, min = 0, maj = 0, rew = 0;
    subjects[sub].forEach(ch => {
        if (ch.classification === "Production Ready") pr++;
        else if (ch.classification === "Minor Revision Needed") min++;
        else if (ch.classification === "Major Revision Needed") maj++;
        else rew++;
    });
    md += `| ${sub} | ${subjects[sub].length} | ${pr} | ${min} | ${maj} | ${rew} |\n`;
}
md += `\n`;

// Task 5: Execution queue (highest impact = lowest score)
md += "## TASK 5: DEFINITIVE EXECUTION QUEUE\n\n";
md += "Ordered from lowest score (highest educational impact needed) to highest score.\n\n";
md += `| Priority | Chapter ID | Chapter Name | Score | Classification |\n`;
md += `|---|---|---|---|---|\n`;
const sorted = [...data].sort((a, b) => a.score - b.score);
sorted.forEach((ch, i) => {
    md += `| ${i+1} | ${ch.id} | ${ch.name} | ${ch.score} | ${ch.classification} |\n`;
});

fs.writeFileSync('audit_report_final.md', md);
console.log("Final Markdown generated.");
