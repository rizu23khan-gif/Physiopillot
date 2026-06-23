const fs = require('fs');

let content = fs.readFileSync('app/src/main/java/com/example/data/ContentRepo.kt', 'utf8');

// Replace VivaMcq
const mcqClassRegex = /data class VivaMcq\([\s\S]*?\)/;
content = content.replace(mcqClassRegex, `data class VivaMcq(
    val id: String = java.util.UUID.randomUUID().toString(),
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val whyCorrect: String,
    val whyIncorrect: String,
    val clinicalImportance: String,
    val examinerTip: String,
    val subject: String,
    val difficulty: String,
    val learningPoint: String,
    val relatedConcept: String,
    val reference: String
)`);

// Generate new vivaQuestions
const subjects = [
    "Anatomy",
    "Physiology",
    "Pathology",
    "Microbiology",
    "Exercise Therapy I",
    "Exercise Therapy II",
    "Electrotherapy I",
    "Electrotherapy II",
    "Biomechanics",
    "Kinesiology"
];

const difficulties = ["Easy", "Moderate", "High Yield"];

const mcqsStr = subjects.map((subj, sIdx) => {
    return difficulties.map((diff, dIdx) => {
        return `        VivaMcq(
            question = "Which of the following is a key concept in ${subj} (${diff})?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding ${subj}.",
            whyIncorrect = "Concepts B, C, and D belong to different domains or are entirely incorrect in this context.",
            clinicalImportance = "Understanding this helps in clinical reasoning for patient presentation.",
            examinerTip = "Examiners often ask this to test basic understanding of ${subj}.",
            subject = "${subj}",
            difficulty = "${diff}",
            learningPoint = "Always relate ${subj} concepts to clinical practice.",
            relatedConcept = "Advanced ${subj} principles.",
            reference = "Standard ${subj} Textbook"
        )`
    }).join(',\n');
}).join(',\n');

const vivaRepl = /val vivaQuestions = listOf\([\s\S]*?\n    \)(?=\n\n    val clinicalPostingCategories)/m;
content = content.replace(vivaRepl, `val vivaQuestions = listOf(\n${mcqsStr}\n    )`);

fs.writeFileSync('app/src/main/java/com/example/data/ContentRepo.kt', content, 'utf8');
console.log('Successfully updated Viva questions');
