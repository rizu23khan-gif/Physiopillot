const fs = require('fs');
const path = require('path');

const dir = './app/src/main/assets/content/anatomy';
const files = fs.readdirSync(dir).filter(f => f.endsWith('.json'));

let totalImprovements = 0;

files.forEach(file => {
    const filePath = path.join(dir, file);
    let data = JSON.parse(fs.readFileSync(filePath, 'utf8'));

    let improved = false;

    // 1. Move equipment to coreConcepts
    if (data.theory && data.theory.equipment && data.theory.equipment.length > 0) {
        data.theory.coreConcepts = (data.theory.coreConcepts || []).concat(data.theory.equipment);
        data.theory.equipment = [];
        improved = true;
    }

    // 2. Remove AI filler from strings recursively
    const removeAIFiller = (obj) => {
        if (typeof obj === 'string') {
            let original = obj;
            obj = obj.replace(/It is important to note that /gi, '');
            obj = obj.replace(/It is crucial to understand that /gi, '');
            obj = obj.replace(/In summary, /gi, '');
            obj = obj.replace(/Overall, /gi, '');
            if (original !== obj) improved = true;
            return obj;
        } else if (Array.isArray(obj)) {
            return obj.map(removeAIFiller);
        } else if (typeof obj === 'object' && obj !== null) {
            for (let key in obj) {
                obj[key] = removeAIFiller(obj[key]);
            }
        }
        return obj;
    };

    data = removeAIFiller(data);

    // 3. Fix "physiologicalEffects" string array into objects if they are strings. Wait, no. The schema change allowed strings.
    // Actually, physiological effects shouldn't be in Anatomy anyway, but we'll leave it as is.
    
    // 4. Standardize Terminology
    const standardizeTerms = (obj) => {
        if (typeof obj === 'string') {
            let original = obj;
            obj = obj.replace(/shoulder blade/gi, 'scapula');
            obj = obj.replace(/collar bone/gi, 'clavicle');
            obj = obj.replace(/kneecap/gi, 'patella');
            if (original !== obj) improved = true;
            return obj;
        } else if (Array.isArray(obj)) {
            return obj.map(standardizeTerms);
        } else if (typeof obj === 'object' && obj !== null) {
            for (let key in obj) {
                obj[key] = standardizeTerms(obj[key]);
            }
        }
        return obj;
    };
    
    data = standardizeTerms(data);

    if (improved) {
        fs.writeFileSync(filePath, JSON.stringify(data, null, 2));
        totalImprovements++;
    }
});

console.log(`Processed ${files.length} chapters. Made improvements to ${totalImprovements} chapters.`);
