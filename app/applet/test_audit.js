const fs = require('fs');
const path = require('path');
const dir = './app/src/main/java/com/example/data/';
if (!fs.existsSync(dir)) {
    console.log('Directory not found:', dir);
    process.exit(1);
}
const files = fs.readdirSync(dir).filter(f => f.endsWith('.kt'));

console.log('--- START MCQ CORRECTNESS AUDIT ---');

files.forEach(file => {
    const filePath = path.join(dir, file);
    const content = fs.readFileSync(filePath, 'utf8');
    
    // We split by "VivaMcq(" to inspect each MCQ instance
    const parts = content.split('VivaMcq(');
    if (parts.length <= 1) return;
    
    parts.slice(1).forEach((part, index) => {
        // We find the question and the options
        const qMatch = part.match(/question\s*=\s*"([^"]*)"/);
        const ansMatch = part.match(/correctAnswer\s*=\s*"([^"]*)"/);
        
        let question = qMatch ? qMatch[1] : 'Unknown Question';
        let ans = ansMatch ? ansMatch[1] : null;
        
        // Find options block: options = listOf(...)
        const optStartIndex = part.indexOf('options = listOf(');
        if (optStartIndex === -1) {
            console.log(`Error: options not found in ${file} at index ${index+1}`);
            return;
        }
        
        // Match the text within listOf(...)
        let optText = '';
        let braceCount = 1;
        let i = optStartIndex + 'options = listOf('.length;
        while (i < part.length && braceCount > 0) {
            let char = part[i];
            if (char === '(') braceCount++;
            else if (char === ')') braceCount--;
            if (braceCount > 0) optText += char;
            i++;
        }
        
        // Parse options strings within optText
        const opts = [];
        const optMatches = optText.matchAll(/"([^"]*)"/g);
        for (const m of optMatches) {
            opts.push(m[1].trim());
        }
        
        if (ans) {
            ans = ans.trim();
        }
        
        if (!ans) {
            console.log(`[SEVERITY: CRITICAL] [File: ${file}] [MCQ ${index+1}] Missing correctAnswer field! Q: "${question.substring(0, 60)}..."`);
        } else if (opts.length === 0) {
            console.log(`[SEVERITY: CRITICAL] [File: ${file}] [MCQ ${index+1}] No options parsed! Q: "${question.substring(0, 60)}..."`);
        } else {
            // Check if ans is in opts
            const exists = opts.some(opt => opt === ans);
            if (!exists) {
                console.log(`[SEVERITY: HIGH] [File: ${file}] [MCQ ${index+1}] correctAnswer NOT in options!`);
                console.log(`  Q: "${question.substring(0, 80)}..."`);
                console.log(`  Expected Answer: "${ans}"`);
                console.log(`  Parsed Options:`, opts);
            }
        }
    });
});

console.log('--- END MCQ CORRECTNESS AUDIT ---');
