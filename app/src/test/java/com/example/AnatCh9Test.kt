package com.example

import com.example.data.ChapterContentRepository
import com.example.data.InteractiveChapterContent
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*
import java.io.File

class AnatChaptersTest {
    // force rebuild
    @Test
    fun testSerialization() {
        val jsonParams = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val chapters = listOf(
            "anat_ch9",
            "anat_ch10",
            "anat_ch11",
            "anat_ch12",
            "anat_ch13",
            "anat_ch14"
        )
        
        for (ch in chapters) {
            val file = File("src/main/assets/content/anatomy/$ch.json")
            assertTrue("File $ch should exist", file.exists())
            
            val jsonString = file.readText()
            try {
                val content = jsonParams.decodeFromString<InteractiveChapterContent>(jsonString)
                assertNotNull(content)
                println("Successfully parsed $ch")
            } catch (e: Exception) {
                println("EXCEPTION_MESSAGE for $ch: ${e.message}")
                fail("Parsing failed for $ch with exception: ${e.message}")
            }
        }
    }
}
