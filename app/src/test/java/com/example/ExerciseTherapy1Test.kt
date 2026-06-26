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
        
            val file = File("src/main/assets/content/exercise_therapy_1/ex_ex1_ch2.json")
            val jsonString = file.readText()
            try {
                val pucs = jsonParams.decodeFromString<com.example.data.PucsChapter>(jsonString)
                val content = pucs.toInteractiveChapterContent()
                println("SUCCESS! Definitions count: ${content.definition.size}")
                java.io.File("/app/test_success.txt").writeText("Success: ${content.definition.size}")
            } catch (e: Exception) {
                java.io.File("/app/test_error_ex_ex1_ch2.txt").writeText("EXCEPTION:\n${e.stackTraceToString()}")
                fail("Parsing failed for ex_ex1_ch2")
            }
        // removing loop
    }
}
