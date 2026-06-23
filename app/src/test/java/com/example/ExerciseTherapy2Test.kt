package com.example

import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class ExerciseTherapy2Test {
    @Test
    fun testExerciseTherapy2Serialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val chapters = listOf(
            "ex_ex2_ch1",
            "ex_ex2_ch2",
            "ex_ex2_ch3",
            "ex_ex2_ch4",
            "ex_ex2_ch5",
            "ex_ex2_ch6",
            "ex_ex2_ch7",
            "ex_ex2_ch8",
            "ex_ex2_ch9",
            "ex_ex2_ch10",
            "ex_ex2_ch11",
            "ex_ex2_ch12"
        )
        
        for (ch in chapters) {
            val file = File("src/main/assets/content/exercise_therapy_ii/$ch.json")
            assertTrue("File $ch.json should exist", file.exists())
            
            val jsonString = file.readText()
            try {
                val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
                assertNotNull(content)
                assertEquals("ex2_ch" + ch.substringAfter("ex_ex2_ch"), content.chapterId)
                assertEquals("Exercise Therapy II", content.subject)
                assertTrue("clinicalPearls should not be empty", content.clinicalPearls.isNotEmpty())
                assertTrue("vivaQuestions should not be empty", content.vivaQuestions.isNotEmpty())
                assertTrue("mcqs should not be empty", content.mcqs.isNotEmpty())
                assertTrue("reference should not be empty", content.reference.isNotEmpty())
                println("Successfully parsed and validated $ch")
            } catch (e: Exception) {
                e.printStackTrace()
                fail("Parsing failed for $ch with exception: ${e.message}")
            }
        }
    }
}
