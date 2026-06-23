package com.example.feature

import com.example.data.ChapterContentRepository
import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import java.io.File

class VivaParsingTest {
    @Test
    fun testVivaParsing() {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        
        val jsonString = File("src/main/assets/content/anatomy/anat_ch2.json").readText()
        val content = json.decodeFromString<InteractiveChapterContent>(jsonString)
        
        val output = StringBuilder()
        output.appendLine("=== VIVA PARSING AUDIT LOG ===")
        val question = content.vivaQuestions[0]
        output.appendLine("RAW VIVA: $question")
                
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
        
        output.appendLine("PARSED Q: $parsedQuestion")
        output.appendLine("PARSED A: $parsedAnswer")
        output.appendLine("PARSED E: $parsedExaminer")
        output.appendLine("PARSED C: $parsedClinical")
        output.appendLine("=== END VIVA PARSING AUDIT LOG ===")
        
        File("build_output_log.txt").writeText(output.toString())
    }
}
