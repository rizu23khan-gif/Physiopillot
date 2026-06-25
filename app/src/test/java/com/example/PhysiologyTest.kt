package com.example

import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class PhysiologyTest {
    @Test
    fun testPhysiologySerialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val chapters = listOf(
            "phys_ch1",
            "phys_ch2",
            "phys_ch3",
            "phys_ch4",
            "phys_ch5",
            "phys_ch6",
            "phys_ch7",
            "phys_ch8",
            "phys_ch9"
        )
        
        for (ch in chapters) {
            val file = File("src/main/assets/content/physiology/$ch.json")
            assertTrue("File $ch should exist", file.exists())
            
            val jsonString = file.readText()
            try {
                val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
                assertNotNull(content)
                assertEquals(ch, content.chapterId)
                assertEquals("Physiology", content.subject)
                assertTrue("Definitions should not be empty in $ch", content.definition.isNotEmpty())
                assertTrue("Viva Questions should not be empty in $ch", content.vivaQuestions.isNotEmpty())
                assertTrue("MCQs should not be empty in $ch", content.mcqs.isNotEmpty())
                assertTrue("Reference should not be empty in $ch", content.reference.isNotEmpty())
                
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
