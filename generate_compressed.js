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

let md = "# PHYSIOPILOT CONTENT QUALITY INVENTORY\n\n";

// Task 1: Inventory
md += "## TASK 1: CHAPTER INVENTORY\n";
for (const sub of Object.keys(subjects)) {
    md += `\n**${sub}**\n| ID | Name | Comp% | Align | Acc | PT | Read | Exam | Score |\n|---|---|---|---|---|---|---|---|---|\n`;
    subjects[sub].forEach(ch => {
        md += `| ${ch.id} | ${ch.name} | ${ch.completeness}% | ${ch.alignment} | ${ch.accuracy} | ${ch.ptRelevance} | ${ch.readability} | ${ch.examReadiness} | **${ch.score}** |\n`;
    });
}

// Task 2: Classifications
md += "\n## TASK 2: CLASSIFICATIONS\n";
data.forEach(ch => {
    md += `- **${ch.id}**: ${ch.classification} - ${ch.justification}\n`;
});

// Task 3: Missing
md += "\n## TASK 3: MISSING CONTENT\n";
data.forEach(ch => {
    md += `- **${ch.id}**: ${ch.missing.length ? ch.missing.join(', ') : 'None'}\n`;
});

// Task 4: Summary Table
md += "\n## TASK 4: SUBJECT SUMMARY\n| Subject | Total | Prod Ready | Minor Rev | Major Rev | Rewrite |\n|---|---|---|---|---|---|\n";
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

// Task 5: Queue
md += "\n## TASK 5: EXECUTION QUEUE\n| Priority | ID | Score | Classification |\n|---|---|---|---|\n";
const sorted = [...data].sort((a, b) => a.score - b.score);
sorted.forEach((ch, i) => {
    md += `| ${i+1} | ${ch.id} | ${ch.score} | ${ch.classification} |\n`;
});

fs.writeFileSync('compressed_audit.md', md);
