const fs = require('fs');
const path = require('path');

const anatomyDir = './app/src/main/assets/content/anatomy';
const files = fs.readdirSync(anatomyDir).filter(f => f.endsWith('.json'));

let successCount = 0;
let errorCount = 0;

files.forEach(file => {
    const filePath = path.join(anatomyDir, file);
    let data;
    try {
        const raw = fs.readFileSync(filePath, 'utf8');
        data = JSON.parse(raw);
    } catch (e) {
        console.error(`Error parsing ${file}: ${e.message}`);
        errorCount++;
        return;
    }

    // PUCS v1.0 Mapping
    const pucs = {
        metadata: {
            chapterId: data.chapterId || file.replace('.json', ''),
            title: data.chapterName || '',
            subject: data.subject || 'Anatomy',
            subjectId: (data.subject || 'Anatomy').toLowerCase(),
            version: "1.0",
            lastUpdated: new Date().toISOString().split('T')[0],
            difficulty: "Medium",
            estimatedStudyTime: 60,
            keywords: [],
            tags: [],
            status: "published"
        },
        learningObjectives: [],
        theory: {
            definitions: data.definition || [],
            coreConcepts: data.principle || [],
            equipment: data.equipment || []
        },
        clinicalCorrelations: {
            pearls: data.clinicalPearls || [],
            precautions: data.precautions || [],
            indications: data.indications || [],
            contraindications: data.contraindications || { general: [], local: [] },
            technique: data.technique || {},
            protocols: data.clinicalProtocols || [],
            physiologicalEffects: data.physiologicalEffects || []
        },
        clinicalCases: [],
        mcqs: (data.mcqs || []).map((m, i) => ({
            id: `mcq_${data.chapterId}_${String(i+1).padStart(3, '0')}`,
            question: m.question,
            options: m.options,
            answer: m.answer,
            explanation: m.explanation
        })),
        flashcards: [],
        viva: (data.vivaQuestions || []).map((v, i) => {
            if (typeof v === 'string') {
                let q = "", a = "", kp = "", ci = "";
                const parts = v.split(" | ");
                parts.forEach(p => {
                    if (p.startsWith("Question: ")) q = p.replace("Question: ", "");
                    else if (p.startsWith("Answer: ")) a = p.replace("Answer: ", "");
                    else if (p.startsWith("Examiner Key Points: ")) kp = p.replace("Examiner Key Points: ", "");
                    else if (p.startsWith("Clinical Importance: ")) ci = p.replace("Clinical Importance: ", "");
                    else q += (q ? " " : "") + p;
                });
                return {
                    id: `viva_${data.chapterId}_${String(i+1).padStart(3, '0')}`,
                    question: q || v,
                    answer: a,
                    keyPoints: kp,
                    clinicalImportance: ci
                };
            }
            // If already object
            return {
                id: v.id || `viva_${data.chapterId}_${String(i+1).padStart(3, '0')}`,
                question: v.question || '',
                answer: v.answer || '',
                keyPoints: v.keyPoints || '',
                clinicalImportance: v.clinicalImportance || ''
            };
        }),
        pyqs: [],
        references: data.reference || [],
        subjectExtensions: {
            anatomy: {
                parameters: data.parameters || {}
            }
        }
    };

    try {
        fs.writeFileSync(filePath, JSON.stringify(pucs, null, 2));
        successCount++;
    } catch(e) {
        console.error(`Error writing ${file}: ${e.message}`);
        errorCount++;
    }
});

console.log(`Migration complete. Success: ${successCount}, Errors: ${errorCount}`);
