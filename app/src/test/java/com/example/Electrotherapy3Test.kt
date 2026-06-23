package com.example

import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class Electrotherapy3Test {
    @Test
    fun testElectrotherapy3Serialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        // Validate the newly created production Russian Current file
        val file = File("src/main/assets/content/electrotherapy_ii/et_el2_ch3.json")
        assertTrue("Russian Current content file should exist", file.exists())
        
        val jsonString = file.readText()
        try {
            val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
            assertNotNull(content)
            assertEquals("el2_ch3", content.chapterId)
            assertEquals("Russian Current", content.chapterName)
            assertEquals("Electrotherapy II", content.subject)
            assertTrue(content.clinicalPearls.isNotEmpty())
            assertTrue(content.vivaQuestions.isNotEmpty())
            assertTrue(content.mcqs.isNotEmpty())
            assertTrue(content.reference.isNotEmpty())
            
            // Check count requirements: 10 viva questions and 30 MCQs for Russian Current
            assertEquals(10, content.vivaQuestions.size)
            assertEquals(30, content.mcqs.size)
            
            // Core safety checks (duplicate checking)
            val uniqueMcqs = content.mcqs.map { it.question }.toSet()
            assertEquals("There should be no duplicate MCQ questions", content.mcqs.size, uniqueMcqs.size)
            
            val uniqueViva = content.vivaQuestions.toSet()
            assertEquals("There should be no duplicate viva questions", content.vivaQuestions.size, uniqueViva.size)
            
            println("Successfully parsed and validated Russian Current production content with 30 MCQs and 10 Viva questions.")
        } catch (e: Exception) {
            fail("Serialization/Parsing failed: ${e.message}")
        }
    }
}
