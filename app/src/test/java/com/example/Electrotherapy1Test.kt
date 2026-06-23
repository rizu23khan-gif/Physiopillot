package com.example

import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class Electrotherapy1Test {
    @Test
    fun testElectrotherapy1Serialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val chapters = listOf(
            "et_el1_ch1",
            "et_el1_ch2",
            "et_el1_ch3",
            "et_el1_ch4"
        )
        
        for (ch in chapters) {
            val file = File("src/main/assets/content/electrotherapy_1/$ch.json")
            assertTrue("File $ch should exist", file.exists())
            
            val jsonString = file.readText()
            try {
                val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
                assertNotNull(content)
                assertEquals("el1_ch" + ch.takeLast(1), content.chapterId)
                assertEquals("Electrotherapy I", content.subject)
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
