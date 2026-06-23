import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class InteractiveChapterContent(
    val subject: String,
    val chapterId: String,
    val chapterName: String,
    val vivaQuestions: List<String> = emptyList()
)

fun main() {
    val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    
    val jsonString = File("app/src/main/assets/content/anatomy/anat_ch2.json").readText()
    val content = json.decodeFromString<InteractiveChapterContent>(jsonString)
    
    println("Loaded \${content.vivaQuestions.size} viva questions")
    
    content.vivaQuestions.forEachIndexed { index, question ->
        if (index > 0) return@forEachIndexed
        
        println("=== RAW JSON QUESTION ===")
        println(question)
        
        val parts = question.split(" | ")
        var parsedQuestion = question
        var parsedAnswer = ""
        var parsedExaminer = ""
        var parsedClinical = ""
        
        println("Parts size: \${parts.size}")
        
        if (parts.size == 1 && !question.startsWith("Question:")) {
            parsedQuestion = question
        } else {
            parts.forEach { part ->
                val item = part.trim()
                println("Evaluating part: '\$item'")
                when {
                    item.startsWith("Question:") -> {
                        parsedQuestion = item.removePrefix("Question:").trim()
                        println("Matched Question: \$parsedQuestion")
                    }
                    item.startsWith("Answer:") -> {
                        parsedAnswer = item.removePrefix("Answer:").trim()
                        println("Matched Answer: \$parsedAnswer")
                    }
                    item.startsWith("Examiner Key Points:") -> {
                        parsedExaminer = item.removePrefix("Examiner Key Points:").trim()
                        println("Matched Examiner: \$parsedExaminer")
                    }
                    item.startsWith("Clinical Importance:") -> {
                        parsedClinical = item.removePrefix("Clinical Importance:").trim()
                        println("Matched Clinical: \$parsedClinical")
                    }
                    else -> {
                        println("Did not match any prefix!")
                    }
                }
            }
        }
        
        println("=== FINAL PARSED ===")
        println("Q: '\$parsedQuestion'")
        println("A: '\$parsedAnswer'")
        println("E: '\$parsedExaminer'")
        println("C: '\$parsedClinical'")
    }
}
