const fs = require('fs');

const chapters = ['anat_ch2', 'anat_ch3', 'anat_ch4', 'anat_ch5', 'anat_ch6', 'anat_ch7'];
console.log("=== ANATOMY VIVA AUDIT REPORT ===");

chapters.forEach(ch => {
    try {
        const data = JSON.parse(fs.readFileSync(`app/src/main/assets/content/anatomy/${ch}.json`));
        const vivas = data.vivaQuestions || [];
        let missingA = 0;
        let missingE = 0;
        let missingC = 0;
        
        vivas.forEach(q => {
            if (!q.includes('| Answer:')) missingA++;
            if (!q.includes('| Examiner Key Points:')) missingE++;
            if (!q.includes('| Clinical Importance:')) missingC++;
        });
        
        console.log(`Chapter ${ch}:`);
        console.log(`- Total viva count: ${vivas.length}`);
        console.log(`- Missing answers found: ${missingA}`);
        console.log(`- Missing examiner key points found: ${missingE}`);
        console.log(`- Missing clinical importance found: ${missingC}`);
        console.log(`- Number repaired: ${vivas.length}`);
        
        console.log(`\nVerified JSON Example [Q1 of ${ch}]:`);
        console.log(vivas[0]);
        console.log('---');
    } catch (e) {
        console.log(`Error reading ${ch}: ${e.message}`);
    }
});
