const fs = require('fs');

const raw = fs.readFileSync('app/src/main/assets/diagnoses.json', 'utf8');
const diagnoses = JSON.parse(raw);

let a = 0, b = 0, c = 0, d = 0, f = 0;

console.log("Diagnosis | Grade | Reason");
console.log("---|---|---");

diagnoses.forEach(diag => {
    let grade = 'C';
    let reason = '';
    
    const isTemplateString = (str) => {
        return str && typeof str === 'string' && (str.includes(diag.diagnosisName) || str.includes('Specific symptom triggers for') || str.includes('Condition-specific irritability') || str.match(/Findings guide selective loading/));
    };

    const hasTemplateElements = (arr) => {
        if (!arr || arr.length === 0) return true;
        for (let item of arr) {
            if (typeof item === 'string' && isTemplateString(item)) return true;
            if (item && item.what && isTemplateString(item.what)) return true;
        }
        return false;
    };

    let templateFields = 0;
    
    if (hasTemplateElements(diag.relevantAnatomy)) templateFields++;
    if (hasTemplateElements(diag.historyTaking)) templateFields++;
    if (hasTemplateElements(diag.observation)) templateFields++;
    if (hasTemplateElements(diag.palpation)) templateFields++;
    if (diag.neurologicalAssessment && diag.neurologicalAssessment.length === 0) templateFields++; 
    if (diag.microbiology && diag.microbiology.length === 0) templateFields++; 
    if (isTemplateString(diag.clinicalReasoning)) templateFields++;
    if (diag.problemList && diag.problemList.length > 0 && isTemplateString(diag.problemList[0])) templateFields++;
    if (diag.outcomeMeasures && diag.outcomeMeasures.length > 0 && isTemplateString(diag.outcomeMeasures[0].purpose)) templateFields++;

    if (templateFields === 0) {
        grade = 'A';
        reason = 'Comprehensive and clinically detailed';
        a++;
    } else if (templateFields < 3) {
        grade = 'B';
        reason = 'Good but contains minor template phrasing';
        b++;
    } else if (templateFields < 6) {
        grade = 'C';
        reason = 'Significant missing content or generic responses';
        c++;
    } else {
        grade = 'D';
        reason = 'Mostly template content with placeholders';
        d++;
    }
    
    console.log(`${diag.diagnosisName} | ${grade} | ${reason}`);
});

console.log("\nSummary:");
console.log(`1. Total diagnoses: ${diagnoses.length}`);
console.log(`2. Number of A grades: ${a}`);
console.log(`3. Number of B grades: ${b}`);
console.log(`4. Number of C grades: ${c}`);
console.log(`5. Number of D grades: ${d}`);
console.log(`6. Number of F grades: ${f}`);
