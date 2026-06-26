const fs = require('fs');
const path = require('path');

function findZip(dir, depth = 0) {
    if (depth > 6) return;
    try {
        const files = fs.readdirSync(dir);
        for (const file of files) {
            const fullPath = path.join(dir, file);
            if (fullPath.includes('node_modules') || fullPath.includes('.git') || fullPath.startsWith('/proc') || fullPath.startsWith('/sys')) continue;
            try {
                const stat = fs.statSync(fullPath);
                if (stat.isDirectory()) {
                    findZip(fullPath, depth + 1);
                } else if (fullPath.endsWith('.zip')) {
                    console.log('FOUND ZIP:', fullPath);
                }
            } catch (e) {}
        }
    } catch (e) {}
}

findZip('/');
