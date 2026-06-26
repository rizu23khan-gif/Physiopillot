const fs = require('fs');
const path = require('path');

function findZip(dir) {
    const files = fs.readdirSync(dir);
    for (const file of files) {
        const fullPath = path.join(dir, file);
        try {
            if (fs.statSync(fullPath).isDirectory()) {
                if (fullPath.includes('node_modules') || fullPath.includes('.git')) continue;
                findZip(fullPath);
            } else if (fullPath.endsWith('.zip')) {
                console.log(fullPath);
            }
        } catch (e) {}
    }
}

findZip('/app');
