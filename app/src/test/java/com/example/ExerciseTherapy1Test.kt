package com.example

import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class ExerciseTherapy1Test {
    @Test
    fun testExerciseTherapySerialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val chapters = listOf(
            "ex_ex1_ch1",
            "ex_ex1_ch2",
            "ex_ex1_ch3",
            "ex_ex1_ch4",
            "ex_ex1_ch5",
            "ex_ex1_ch6"
        )
        
        for (ch in chapters) {
            val file = File("src/main/assets/content/exercise_therapy_1/$ch.json")
            assertTrue("File $ch should exist", file.exists())
            
            val jsonString = file.readText()
            try {
                val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
                assertNotNull(content)
                assertEquals("ex1_ch" + ch.takeLast(1), content.chapterId)
                assertEquals("Exercise Therapy I", content.subject)
                assertTrue(content.clinicalPearls.isNotEmpty())
                assertTrue(content.vivaQuestions.isNotEmpty())
                assertTrue(content.mcqs.isNotEmpty())
                assertTrue(content.reference.isNotEmpty())
                println("Successfully parsed and validated $ch")
            } catch (e: Exception) {
                fail("Parsing failed for $ch with exception: ${e.message}")
            }
        }
    }
}
