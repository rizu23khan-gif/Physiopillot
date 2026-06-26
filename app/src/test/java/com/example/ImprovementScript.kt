package com.example

import org.junit.Test
import java.io.File

class ImprovementScript {

    @Test
    fun runImprovements() {
        val dir = File("/app/src/main/assets/content/anatomy")
        val files = dir.listFiles { file -> file.name.endsWith(".json") }
        
        var improvedCount = 0
        
        files?.forEach { file ->
            var content = file.readText()
            var improved = false
            
            val fillers = listOf(
                "It is important to note that ",
                "It is crucial to understand that ",
                "In summary, ",
                "Overall, "
            )
            for (filler in fillers) {
                if (content.contains(filler)) {
                    content = content.replace(filler, "")
                    improved = true
                }
            }
            
            val terms = mapOf(
                "shoulder blade" to "scapula",
                "collar bone" to "clavicle",
                "kneecap" to "patella",
                "Shoulder blade" to "Scapula",
                "Collar bone" to "Clavicle",
                "Kneecap" to "Patella"
            )
            for ((old, new) in terms) {
                if (content.contains(old)) {
                    content = content.replace(old, new)
                    improved = true
                }
            }
            
            if (improved) {
                file.writeText(content)
                improvedCount++
            }
        }
        File("/app/improve_result.txt").writeText("IMPROVED_COUNT: " + improvedCount)
    }
}
