package com.example.feature

import com.example.data.ChapterContentRepository
import com.example.data.InteractiveChapterContent
import com.example.data.SubjectChapterRepository
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Test
import java.io.File

@Serializable
data class RegistryChapter(
    val year: String,
    val subject: String,
    val unit: String = "",
    val chapterId: String,
    val chapterName: String
)

class VivaParsingTest {
    @Test
    fun testVivaParsing() {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        
        val jsonString = File("src/main/assets/content/anatomy/anat_ch2.json").readText()
        val content = json.decodeFromString<InteractiveChapterContent>(jsonString)
        
        val output = StringBuilder()
        output.appendLine("=== VIVA PARSING AUDIT LOG ===")
        val question = content.vivaQuestions[0]
        output.appendLine("RAW VIVA: $question")
                
        val parts = question.split(" | ")
        var parsedQuestion = question
        var parsedAnswer = ""
        var parsedExaminer = ""
        var parsedClinical = ""
        
        if (parts.size == 1 && !question.startsWith("Question:")) {
            parsedQuestion = question
        } else {
            parts.forEach { part ->
                val item = part.trim()
                when {
                    item.startsWith("Question:") -> parsedQuestion = item.removePrefix("Question:").trim()
                    item.startsWith("Answer:") -> parsedAnswer = item.removePrefix("Answer:").trim()
                    item.startsWith("Examiner Key Points:") -> parsedExaminer = item.removePrefix("Examiner Key Points:").trim()
                    item.startsWith("Clinical Importance:") -> parsedClinical = item.removePrefix("Clinical Importance:").trim()
                }
            }
        }
        
        output.appendLine("PARSED Q: $parsedQuestion")
        output.appendLine("PARSED A: $parsedAnswer")
        output.appendLine("PARSED E: $parsedExaminer")
        output.appendLine("PARSED C: $parsedClinical")
        output.appendLine("=== END VIVA PARSING AUDIT LOG ===")
        
        File("build_output_log.txt").writeText(output.toString())
    }

    @Test
    fun testRegistryConsistency() {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val output = StringBuilder()
        output.appendLine("=== REGISTRY CONSISTENCY AUDIT LOG ===")

        // 1. Get SubjectChapterRepository IDs
        val subjectsMap = SubjectChapterRepository.subjectsMap
        val subjectChapterIds = mutableSetOf<String>()
        val subjectChapterDetails = mutableMapOf<String, String>()
        for ((subjId, subjModel) in subjectsMap) {
            val chapters = subjModel.chapters.toMutableList()
            chapters.addAll(subjModel.modules.flatMap { it.chapters })
            for (ch in chapters) {
                subjectChapterIds.add(ch.chapterId)
                subjectChapterDetails[ch.chapterId] = "${subjModel.year} - ${subjModel.title} - ${ch.title}"
            }
        }
        output.appendLine("SubjectChapterRepository total chapters: ${subjectChapterIds.size}")

        // 2. Parse ChapterContentRepository registry map from file
        val contentRepoLines = File("src/main/java/com/example/data/ChapterContentRepository.kt").readLines()
        val contentRepoMap = contentRepoLines.filter { it.contains("to \"content/") }
            .associate { line ->
                val parts = line.split(" to ")
                val key = parts[0].trim().removeSurrounding("\"")
                val path = parts[1].trim().removeSuffix(",").removeSurrounding("\"")
                key to path
            }
        output.appendLine("ChapterContentRepository.registry total chapters: ${contentRepoMap.size}")

        // 3. Parse chapter_registry.json
        val registryJsonStr = File("src/main/assets/chapter_registry.json").readText()
        val registryChapters = json.decodeFromString<List<RegistryChapter>>(registryJsonStr)
        val registryChapterIds = registryChapters.map { it.chapterId }.toSet()
        val registryChapterDupes = registryChapters.groupBy { it.chapterId }.filter { it.value.size > 1 }.keys
        output.appendLine("chapter_registry.json total chapters: ${registryChapters.size}")
        if (registryChapterDupes.isNotEmpty()) {
            output.appendLine("chapter_registry.json duplicates found: $registryChapterDupes")
        } else {
            output.appendLine("chapter_registry.json duplicates found: None")
        }

        // 4. Parse RetentionRepository.kt chapter keys
        val retentionRepoText = File("src/main/java/com/example/data/RetentionRepository.kt").readText()
        val matchResult = "listOf\\(([^)]+)\\)".toRegex().find(retentionRepoText)
        val retentionKeys = matchResult?.groupValues?.get(1)
            ?.split(",")
            ?.map { it.trim().removeSurrounding("\"") }
            ?.filter { it.isNotEmpty() }
            ?.toSet() ?: emptySet()
        output.appendLine("RetentionRepository.kt total keys: ${retentionKeys.size}")

        // 5. Check duplicate IDs in SubjectChapterRepository
        val allChaptersList = subjectsMap.values.flatMap { it.chapters + it.modules.flatMap { m -> m.chapters } }
        val subjectChapterDupes = allChaptersList.groupBy { it.chapterId }.filter { it.value.size > 1 }.keys
        if (subjectChapterDupes.isNotEmpty()) {
            output.appendLine("SubjectChapterRepository duplicates: $subjectChapterDupes")
        } else {
            output.appendLine("SubjectChapterRepository duplicates: None")
        }

        // 6. Check orphan IDs
        // - IDs in SubjectChapterRepository but not in ChapterContentRepository (no asset mapping)
        val missingAssetMapping = subjectChapterIds.filter { !contentRepoMap.containsKey(it) }
        output.appendLine("SubjectChapterRepository IDs missing asset mapping: $missingAssetMapping")

        // - IDs in ChapterContentRepository but not in SubjectChapterRepository (unreachable via UI syllabus tree)
        val unreachableFromUI = contentRepoMap.keys.filter { !subjectChapterIds.contains(it) }
        output.appendLine("ChapterContentRepository IDs unreachable from UI syllabus: $unreachableFromUI")

        // - IDs in SubjectChapterRepository but not in chapter_registry.json
        val missingFromRegistryJson = subjectChapterIds.filter { !registryChapterIds.contains(it) }
        output.appendLine("SubjectChapterRepository IDs missing from chapter_registry.json: $missingFromRegistryJson")

        // - IDs in chapter_registry.json but not in SubjectChapterRepository
        val missingFromSubjectRepo = registryChapterIds.filter { !subjectChapterIds.contains(it) }
        output.appendLine("chapter_registry.json IDs missing from SubjectChapterRepository: $missingFromSubjectRepo")

        // 7. Verify asset path physical existence
        val invalidAssetPaths = mutableListOf<String>()
        for ((key, path) in contentRepoMap) {
            val file = File("src/main/assets/$path")
            if (!file.exists()) {
                invalidAssetPaths.add("$key ($path)")
            }
        }
        if (invalidAssetPaths.isNotEmpty()) {
            output.appendLine("Invalid asset paths: $invalidAssetPaths")
        } else {
            output.appendLine("Invalid asset paths: None")
        }

        output.appendLine("=== END REGISTRY CONSISTENCY AUDIT LOG ===")
        File("registry_audit_output.txt").writeText(output.toString())
        println(output.toString())
    }

    @Test
    fun rebuildChapterRegistryJson() {
        val json = Json {
            prettyPrint = true
        }
        val list = mutableListOf<RegistryChapter>()
        for ((_, subjModel) in SubjectChapterRepository.subjectsMap) {
            val hasModules = subjModel.modules.isNotEmpty()
            if (hasModules) {
                for (module in subjModel.modules) {
                    for (ch in module.chapters) {
                        list.add(
                            RegistryChapter(
                                year = subjModel.year,
                                subject = subjModel.title,
                                unit = module.title,
                                chapterId = ch.chapterId,
                                chapterName = ch.title
                            )
                        )
                    }
                }
            } else {
                for (ch in subjModel.chapters) {
                    list.add(
                        RegistryChapter(
                            year = subjModel.year,
                            subject = subjModel.title,
                            unit = subjModel.title,
                            chapterId = ch.chapterId,
                            chapterName = ch.title
                        )
                    )
                }
            }
        }
        val outputJson = json.encodeToString(kotlinx.serialization.serializer<List<RegistryChapter>>(), list)
        File("src/main/assets/chapter_registry.json").writeText(outputJson)
    }
}
