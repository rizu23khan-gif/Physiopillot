const fs = require('fs');
const path = require('path');

const fileNames = [
  "ex_ex1_ch2.json",
  "ex_ex1_ch3.json",
  "ex_ex1_ch4.json",
  "ex_ex1_ch5.json",
  "ex_ex1_ch6.json"
];

function convertToPucs(backup) {
  const pucs = {
    metadata: {
      chapterId: backup.chapterId || "",
      title: backup.chapterName || "",
      subject: backup.subject || "",
      keywords: [],
      tags: []
    },
    theory: {
      definitions: backup.definition || [],
      coreConcepts: backup.principle || [],
      equipment: backup.equipment || []
    },
    clinicalCorrelations: {
      pearls: backup.clinicalPearls || [],
      precautions: backup.precautions || [],
      indications: backup.indications || [],
      contraindications: {
        general: (backup.contraindications && backup.contraindications.general) ? backup.contraindications.general : [],
        local: (backup.contraindications && backup.contraindications.local) ? backup.contraindications.local : []
      },
      technique: {
        patientPreparation: (backup.technique && backup.technique.patientPreparation) ? backup.technique.patientPreparation : [],
        apparatusPreparation: (backup.technique && backup.technique.apparatusPreparation) ? backup.technique.apparatusPreparation : [],
        skinPreparation: (backup.technique && backup.technique.skinPreparation) ? backup.technique.skinPreparation : [],
        electrodePlacement: (backup.technique && backup.technique.electrodePlacement) ? backup.technique.electrodePlacement : [],
        treatmentProcedure: (backup.technique && backup.technique.treatmentProcedure) ? backup.technique.treatmentProcedure : []
      },
      protocols: backup.clinicalProtocols || [],
      physiologicalEffects: backup.physiologicalEffects || []
    },
    subjectExtensions: {
      anatomy: {
        parameters: backup.parameters || {}
      }
    },
    clinicalCases: [],
    mcqs: backup.mcqs || [],
    viva: [],
    flashcards: [],
    references: backup.reference || []
  };

  // Merge vivaQuestions and vivaQuestions_upgraded into viva
  const rawViva = [];
  if (backup.vivaQuestions) {
    rawViva.push(...backup.vivaQuestions);
  }
  if (backup.vivaQuestions_upgraded) {
    rawViva.push(...backup.vivaQuestions_upgraded);
  }

  pucs.viva = rawViva.map((qStr, index) => {
    const parts = qStr.split('|').map(s => s.trim());
    let question = "";
    let answer = "";
    let keyPoints = "";
    let clinicalImportance = "";

    parts.forEach(part => {
      if (part.startsWith('Question:')) {
        question = part.replace('Question:', '').trim();
      } else if (part.startsWith('Answer:')) {
        answer = part.replace('Answer:', '').trim();
      } else if (part.startsWith('Examiner Key Points:')) {
        keyPoints = part.replace('Examiner Key Points:', '').trim();
      } else if (part.startsWith('Clinical Importance:')) {
        clinicalImportance = part.replace('Clinical Importance:', '').trim();
      }
    });

    return {
      id: `${backup.chapterId}_v${index + 1}`,
      question: question,
      answer: answer,
      keyPoints: keyPoints,
      clinicalImportance: clinicalImportance
    };
  });

  return pucs;
}

const inputDir = '/app/backups'; // Assuming I saved them here
const outputDir = '/app/src/main/assets/content/exercise_therapy_1';

if (!fs.existsSync(inputDir)) {
  console.log("Input dir doesn't exist");
} else {
  for (const file of fileNames) {
    const backupPath = path.join(inputDir, file);
    if (fs.existsSync(backupPath)) {
      const backupJson = JSON.parse(fs.readFileSync(backupPath, 'utf8'));
      const pucsJson = convertToPucs(backupJson);
      fs.writeFileSync(path.join(outputDir, file), JSON.stringify(pucsJson, null, 4));
      console.log(`Converted ${file}`);
    } else {
      console.log(`File not found: ${backupPath}`);
    }
  }
}
