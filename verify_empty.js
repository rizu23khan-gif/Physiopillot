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

let emptyCount = 0;
allJsonFiles.forEach(file => {
    try {
        let raw = fs.readFileSync(file, 'utf8');
        let data = JSON.parse(raw);
        
        let hasContent = false;
        
        if (data.definition && data.definition.length > 0) hasContent = true;
        if (data.mcqs && data.mcqs.length > 0) hasContent = true;
        if (data.vivaQuestions && data.vivaQuestions.length > 0) hasContent = true;
        
        if (!hasContent) {
            console.log(`Empty/placeholder: ${file}`);
            emptyCount++;
        }
    } catch(e) { }
});

if (emptyCount === 0) console.log("No empty files found.");
