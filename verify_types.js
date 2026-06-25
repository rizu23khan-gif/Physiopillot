const fs = require('fs');
const path = require('path');

const contentDir = 'app/src/main/assets/content';
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

allJsonFiles.forEach(file => {
    try {
        let raw = fs.readFileSync(file, 'utf8');
        let data = JSON.parse(raw);
        
        let typeErrors = [];
        
        // primitive types
        if (typeof data.subject !== 'string') typeErrors.push('subject is not a string');
        if (typeof data.chapterId !== 'string') typeErrors.push('chapterId is not a string');
        if (typeof data.chapterName !== 'string') typeErrors.push('chapterName is not a string');
        
        // arrays
        const arrays = [
            'definition', 'principle', 'equipment', 'physiologicalEffects', 'indications',
            'precautions', 'clinicalPearls', 'clinicalProtocols', 'vivaQuestions', 'mcqs', 'reference'
        ];
        arrays.forEach(arr => {
            if (data[arr] !== undefined && !Array.isArray(data[arr])) {
                typeErrors.push(`${arr} is not an array`);
            }
        });
        
        // contraindications
        if (data.contraindications) {
            if (typeof data.contraindications !== 'object') typeErrors.push('contraindications is not an object');
            else {
                if (data.contraindications.general && !Array.isArray(data.contraindications.general)) typeErrors.push('contraindications.general is not an array');
                if (data.contraindications.local && !Array.isArray(data.contraindications.local)) typeErrors.push('contraindications.local is not an array');
            }
        }
        
        // technique
        if (data.technique) {
            if (typeof data.technique !== 'object') typeErrors.push('technique is not an object');
            else {
                const techArrays = ['patientPreparation', 'apparatusPreparation', 'skinPreparation', 'electrodePlacement', 'treatmentProcedure'];
                techArrays.forEach(arr => {
                    if (data.technique[arr] && !Array.isArray(data.technique[arr])) {
                        typeErrors.push(`technique.${arr} is not an array`);
                    }
                });
            }
        }
        
        // mcqs
        if (data.mcqs && Array.isArray(data.mcqs)) {
            data.mcqs.forEach((mcq, idx) => {
                if (typeof mcq.question !== 'string') typeErrors.push(`mcqs[${idx}].question is not a string`);
                if (!Array.isArray(mcq.options)) typeErrors.push(`mcqs[${idx}].options is not an array`);
                if (typeof mcq.answer !== 'string') typeErrors.push(`mcqs[${idx}].answer is not a string`);
            });
        }
        
        if (typeErrors.length > 0) {
            console.log(`Type errors in ${file}:`);
            typeErrors.forEach(e => console.log(`  - ${e}`));
        }
        
    } catch(e) {
        // syntax errors already caught
    }
});
