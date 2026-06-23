package com.example

import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class BiomechanicsTest {
    @Test
    fun testBiomechanicsSerialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val chapters = listOf(
            "bio_ch1",
            "bio_ch2",
            "bio_ch3",
            "bio_ch4"
        )
        
        for (ch in chapters) {
            val file = File("src/main/assets/content/biomechanics/$ch.json")
            assertTrue("File $ch should exist", file.exists())
            
            val jsonString = file.readText()
            try {
                val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
                assertNotNull(content)
                assertEquals(ch, content.chapterId)
                assertEquals("Biomechanics & Kinesiology", content.subject)
                assertTrue(content.clinicalPearls.isNotEmpty())
                assertTrue(content.vivaQuestions.isNotEmpty())
                assertTrue(content.mcqs.isNotEmpty())
                assertTrue(content.reference.isNotEmpty())
                
                // Assert Viva parsing standard delimiter exists
                for (viva in content.vivaQuestions) {
                    val splitParts = viva.split("|")
                    assertTrue("Viva Question format error in $ch: must contain at least 2 parts divided by '|'", splitParts.size >= 2)
                    assertTrue("Viva Question should start with 'Question:'", splitParts[0].trim().startsWith("Question:"))
                }
                
                println("Successfully parsed and validated $ch")
            } catch (e: Exception) {
                fail("Parsing failed for $ch with exception: ${e.message}")
            }
        }
    }
}
