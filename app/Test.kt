fun main() {
    val questions = listOf(
        "Question: Define the precise boundaries... | Answer: It is bounded... | Examiner Key Points: Anterior... | Clinical Importance: Tenderness...",
        "Just a normal question without separators"
    )

    for (question in questions) {
        val parts = question.split(" | ")
        var parsedQuestion = question
        var parsedAnswer = ""
        var parsedExaminer = ""
        var parsedClinical = ""
        
        if (parts.size == 1 && !question.startsWith("Question:")) {
            parsedQuestion = question
        } else {
            parts.forEach { part ->
                val item = part.trim()
                when {
                    item.startsWith("Question:") -> parsedQuestion = item.removePrefix("Question:").trim()
                    item.startsWith("Answer:") -> parsedAnswer = item.removePrefix("Answer:").trim()
                    item.startsWith("Examiner Key Points:") -> parsedExaminer = item.removePrefix("Examiner Key Points:").trim()
                    item.startsWith("Clinical Importance:") -> parsedClinical = item.removePrefix("Clinical Importance:").trim()
                }
            }
        }
        println("Q: \$parsedQuestion")
        println("A: \$parsedAnswer")
        println("E: \$parsedExaminer")
        println("C: \$parsedClinical")
        println("---")
    }
}
