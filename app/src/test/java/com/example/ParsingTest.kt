package com.example

import com.example.data.PucsChapter
import kotlinx.serialization.json.Json
import org.junit.Test
import java.io.File

class ParsingTest {
    @Test
    fun testPucsParsing() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val file = File("src/main/assets/content/exercise_therapy_1/ex_ex1_ch2.json")
        val jsonString = file.readText()

        try {
            val pucs = jsonParams.decodeFromString<PucsChapter>(jsonString)
            val interactive = pucs.toInteractiveChapterContent()
            println("Success! Definitions count: ${interactive.definition.size}")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}
