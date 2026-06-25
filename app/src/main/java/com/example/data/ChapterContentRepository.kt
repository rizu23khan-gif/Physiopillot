package com.example.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.jsonArray
import java.io.InputStream

@Serializable
data class PhysiologicalEffect(
    val effect: String,
    val frequency: String,
    val mechanism: List<String>
)

@Serializable
data class Contraindications(
    val general: List<String> = emptyList(),
    val local: List<String> = emptyList()
)

@Serializable
data class Technique(
    val patientPreparation: List<String> = emptyList(),
    val apparatusPreparation: List<String> = emptyList(),
    val skinPreparation: List<String> = emptyList(),
    val electrodePlacement: List<String> = emptyList(),
    val treatmentProcedure: List<String> = emptyList()
)

@Serializable
data class ClinicalProtocol(
    val condition: String,
    val position: String = "",
    val electrodePlacement: String = "",
    val frequency: String,
    val purpose: String
)

@Serializable
data class ChapterMCQ(
    val question: String,
    val options: List<String>,
    val answer: String,
    val explanation: String = ""
)

@Serializable
data class PucsMetadata(
    val chapterId: String,
    val title: String,
    val subject: String,
    val keywords: List<String> = emptyList(),
    val tags: List<String> = emptyList()
)

@Serializable
data class PucsTheory(
    val definitions: List<String> = emptyList(),
    val coreConcepts: List<String> = emptyList(),
    val equipment: List<String> = emptyList()
)

@Serializable
data class PucsClinicalCorrelations(
    val pearls: List<String> = emptyList(),
    val precautions: List<String> = emptyList(),
    val indications: List<String> = emptyList(),
    val contraindications: Contraindications = Contraindications(),
    val technique: Technique = Technique(),
    val protocols: List<ClinicalProtocol> = emptyList(),
    val physiologicalEffects: List<JsonElement> = emptyList()
)

@Serializable
data class PucsVivaQuestion(
    val id: String = "",
    val question: String,
    val answer: String,
    val keyPoints: String = "",
    val clinicalImportance: String = ""
)

@Serializable
data class PucsAnatomyExtension(
    val parameters: Map<String, String> = emptyMap()
)

@Serializable
data class PucsSubjectExtensions(
    val anatomy: PucsAnatomyExtension? = null
)

@Serializable
data class PucsChapter(
    val metadata: PucsMetadata,
    val theory: PucsTheory = PucsTheory(),
    val clinicalCorrelations: PucsClinicalCorrelations = PucsClinicalCorrelations(),
    val mcqs: List<ChapterMCQ> = emptyList(),
    val viva: List<PucsVivaQuestion> = emptyList(),
    val references: List<String> = emptyList(),
    val subjectExtensions: PucsSubjectExtensions? = null
) {
    fun toInteractiveChapterContent(): InteractiveChapterContent {
        val vivaFormatted = viva.map { 
            "Question: ${it.question} | Answer: ${it.answer} | Examiner Key Points: ${it.keyPoints} | Clinical Importance: ${it.clinicalImportance}"
        }
        return InteractiveChapterContent(
            subject = metadata.subject,
            chapterId = metadata.chapterId,
            chapterName = metadata.title,
            definition = theory.definitions,
            principle = theory.coreConcepts,
            equipment = theory.equipment,
            parameters = subjectExtensions?.anatomy?.parameters ?: emptyMap(),
            physiologicalEffects = clinicalCorrelations.physiologicalEffects.mapNotNull { element ->
                try {
                    if (element is kotlinx.serialization.json.JsonObject) {
                        PhysiologicalEffect(
                            effect = element["effect"]?.jsonPrimitive?.content ?: "",
                            frequency = element["frequency"]?.jsonPrimitive?.content ?: "",
                            mechanism = element["mechanism"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                        )
                    } else {
                        PhysiologicalEffect(
                            effect = element.jsonPrimitive.content,
                            frequency = "",
                            mechanism = emptyList()
                        )
                    }
                } catch (e: Exception) {
                    null
                }
            },
            indications = clinicalCorrelations.indications,
            contraindications = clinicalCorrelations.contraindications,
            technique = clinicalCorrelations.technique,
            precautions = clinicalCorrelations.precautions,
            clinicalPearls = clinicalCorrelations.pearls,
            clinicalProtocols = clinicalCorrelations.protocols,
            vivaQuestions = vivaFormatted,
            mcqs = mcqs,
            reference = references
        )
    }
}

@Serializable
data class InteractiveChapterContent(
    val subject: String,
    val chapterId: String,
    val chapterName: String,
    val definition: List<String> = emptyList(),
    val principle: List<String> = emptyList(),
    val equipment: List<String> = emptyList(),
    val parameters: Map<String, String> = emptyMap(),
    val physiologicalEffects: List<PhysiologicalEffect> = emptyList(),
    val indications: List<String> = emptyList(),
    val contraindications: Contraindications = Contraindications(),
    val technique: Technique = Technique(),
    val precautions: List<String> = emptyList(),
    val clinicalPearls: List<String> = emptyList(),
    val clinicalProtocols: List<ClinicalProtocol> = emptyList(),
    val vivaQuestions: List<String> = emptyList(),
    val mcqs: List<ChapterMCQ> = emptyList(),
    val reference: List<String> = emptyList()
)

object ChapterContentRepository {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val registry = mapOf(
        "et2_ift" to "content/electrotherapy_ii/et2_ift.json",
        "el2_ch2" to "content/electrotherapy_ii/et_el2_ch2.json",
        "el2_ch3" to "content/electrotherapy_ii/et_el2_ch3.json",
        "el2_ch4" to "content/electrotherapy_ii/et_el2_ch4.json",
        "el2_ch5" to "content/electrotherapy_ii/et_el2_ch5.json",
        "el2_ch6" to "content/electrotherapy_ii/et_el2_ch6.json",
        "el2_ch7" to "content/electrotherapy_ii/et_el2_ch7.json",
        "el2_ch8" to "content/electrotherapy_ii/et_el2_ch8.json",
        "el2_ch9" to "content/electrotherapy_ii/et_el2_ch9.json",
        "el2_ch10" to "content/electrotherapy_ii/et_el2_ch10.json",
        "el2_ch11" to "content/electrotherapy_ii/et_el2_ch11.json",
        "anat_ch1" to "content/anatomy/anat_ch1.json",
        "anat_ch2" to "content/anatomy/anat_ch2.json",
        "anat_ch3" to "content/anatomy/anat_ch3.json",
        "anat_ch4" to "content/anatomy/anat_ch4.json",
        "anat_ch5" to "content/anatomy/anat_ch5.json",
        "anat_ch6" to "content/anatomy/anat_ch6.json",
        "anat_ch7" to "content/anatomy/anat_ch7.json",
        "anat_ch8" to "content/anatomy/anat_ch8.json",
        "anat_ch9" to "content/anatomy/anat_ch9.json",
        "anat_ch10" to "content/anatomy/anat_ch10.json",
        "anat_ch11" to "content/anatomy/anat_ch11.json",
        "anat_ch12" to "content/anatomy/anat_ch12.json",
        "anat_ch13" to "content/anatomy/anat_ch13.json",
        "anat_ch14" to "content/anatomy/anat_ch14.json",
        "anat_ch15" to "content/anatomy/anat_ch15.json",
        "anat_ch16" to "content/anatomy/anat_ch16.json",
        "anat_ch17" to "content/anatomy/anat_ch17.json",
        "anat_ch18" to "content/anatomy/anat_ch18.json",
        "anat_ch19" to "content/anatomy/anat_ch19.json",
        "anat_ch20" to "content/anatomy/anat_ch20.json",
        "anat_ch21" to "content/anatomy/anat_ch21.json",
        "anat_ch22" to "content/anatomy/anat_ch22.json",
        "anat_ch23" to "content/anatomy/anat_ch23.json",
        "anat_ch24" to "content/anatomy/anat_ch24.json",
        "anat_ch25" to "content/anatomy/anat_ch25.json",
        "anat_ch26" to "content/anatomy/anat_ch26.json",
        "anat_ch27" to "content/anatomy/anat_ch27.json",
        "anat_ch28" to "content/anatomy/anat_ch28.json",
        "anat_ch29" to "content/anatomy/anat_ch29.json",
        "anat_ch30" to "content/anatomy/anat_ch30.json",
        "anat_ch31" to "content/anatomy/anat_ch31.json",
        "anat_ch32" to "content/anatomy/anat_ch32.json",
        "anat_ch33" to "content/anatomy/anat_ch33.json",
        "anat_ch34" to "content/anatomy/anat_ch34.json",
        "phys_ch1" to "content/physiology/phys_ch1.json",
        "phys_ch2" to "content/physiology/phys_ch2.json",
        "phys_ch3" to "content/physiology/phys_ch3.json",
        "phys_ch4" to "content/physiology/phys_ch4.json",
        "phys_ch5" to "content/physiology/phys_ch5.json",
        "phys_ch6" to "content/physiology/phys_ch6.json",
        "phys_ch7" to "content/physiology/phys_ch7.json",
        "phys_ch8" to "content/physiology/phys_ch8.json",
        "phys_ch9" to "content/physiology/phys_ch9.json",
        "ex1_ch1" to "content/exercise_therapy_1/ex_ex1_ch1.json",
        "ex1_ch2" to "content/exercise_therapy_1/ex_ex1_ch2.json",
        "ex1_ch3" to "content/exercise_therapy_1/ex_ex1_ch3.json",
        "ex1_ch4" to "content/exercise_therapy_1/ex_ex1_ch4.json",
        "ex1_ch5" to "content/exercise_therapy_1/ex_ex1_ch5.json",
        "ex1_ch6" to "content/exercise_therapy_1/ex_ex1_ch6.json",
        "el1_ch1" to "content/electrotherapy_1/et_el1_ch1.json",
        "el1_ch2" to "content/electrotherapy_1/et_el1_ch2.json",
        "el1_ch3" to "content/electrotherapy_1/et_el1_ch3.json",
        "el1_ch4" to "content/electrotherapy_1/et_el1_ch4.json",
        "bio_ch1" to "content/biomechanics/bio_ch1.json",
        "bio_ch2" to "content/biomechanics/bio_ch2.json",
        "bio_ch3" to "content/biomechanics/bio_ch3.json",
        "bio_ch4" to "content/biomechanics/bio_ch4.json",
        "bio_ch5" to "content/biomechanics/bio_ch5.json",
        "bio_ch6" to "content/biomechanics/bio_ch6.json",
        "bio_ch7" to "content/biomechanics/bio_ch7.json",
        "bio_ch8" to "content/biomechanics/bio_ch8.json",
        "bio_ch9" to "content/biomechanics/bio_ch9.json",
        "bio_ch10" to "content/biomechanics/bio_ch10.json",
        "bio_ch11" to "content/biomechanics/bio_ch11.json",
        "bio_ch12" to "content/biomechanics/bio_ch12.json",
        "bio_ch13" to "content/biomechanics/bio_ch13.json",
        "ex2_ch1" to "content/exercise_therapy_ii/ex_ex2_ch1.json",
        "ex2_ch2" to "content/exercise_therapy_ii/ex_ex2_ch2.json",
        "ex2_ch3" to "content/exercise_therapy_ii/ex_ex2_ch3.json",
        "ex2_ch4" to "content/exercise_therapy_ii/ex_ex2_ch4.json",
        "ex2_ch5" to "content/exercise_therapy_ii/ex_ex2_ch5.json",
        "ex2_ch6" to "content/exercise_therapy_ii/ex_ex2_ch6.json",
        "ex2_ch7" to "content/exercise_therapy_ii/ex_ex2_ch7.json",
        "ex2_ch8" to "content/exercise_therapy_ii/ex_ex2_ch8.json",
        "ex2_ch9" to "content/exercise_therapy_ii/ex_ex2_ch9.json",
        "ex2_ch10" to "content/exercise_therapy_ii/ex_ex2_ch10.json",
        "ex2_ch11" to "content/exercise_therapy_ii/ex_ex2_ch11.json",
        "ex2_ch12" to "content/exercise_therapy_ii/ex_ex2_ch12.json",
        
        // Pathology
        "path_ch1" to "content/pathology/path_ch1.json",
        "path_ch2" to "content/pathology/path_ch2.json",
        "path_ch3" to "content/pathology/path_ch3.json",
        "path_ch4" to "content/pathology/path_ch4.json",
        "path_ch5" to "content/pathology/path_ch5.json",
        "path_ch6" to "content/pathology/path_ch6.json",
        "path_ch7" to "content/pathology/path_ch7.json",
        "path_ch8" to "content/pathology/path_ch8.json",
        "path_ch9" to "content/pathology/path_ch9.json",
        "path_ch10" to "content/pathology/path_ch10.json",
        "path_ch11" to "content/pathology/path_ch11.json",
        "path_ch12" to "content/pathology/path_ch12.json",
        "path_ch13" to "content/pathology/path_ch13.json",
        "path_ch14" to "content/pathology/path_ch14.json",
        "path_ch15" to "content/pathology/path_ch15.json",
        
        // Microbiology
        "micro_ch1" to "content/microbiology/micro_ch1.json",
        "micro_ch2" to "content/microbiology/micro_ch2.json",
        "micro_ch3" to "content/microbiology/micro_ch3.json",
        "micro_ch4" to "content/microbiology/micro_ch4.json",
        "micro_ch5" to "content/microbiology/micro_ch5.json",
        "micro_ch6" to "content/microbiology/micro_ch6.json",
        "micro_ch7" to "content/microbiology/micro_ch7.json",
        "micro_ch8" to "content/microbiology/micro_ch8.json",
        
        // Pharmacology
        "pharm_ch1" to "content/pharmacology/pharm_ch1.json",
        "pharm_ch2" to "content/pharmacology/pharm_ch2.json",
        "pharm_ch3" to "content/pharmacology/pharm_ch3.json",
        "pharm_ch4" to "content/pharmacology/pharm_ch4.json",
        "pharm_ch5" to "content/pharmacology/pharm_ch5.json",
        "pharm_ch6" to "content/pharmacology/pharm_ch6.json",
        "pharm_ch7" to "content/pharmacology/pharm_ch7.json",
        "pharm_ch8" to "content/pharmacology/pharm_ch8.json",
        "pharm_ch9" to "content/pharmacology/pharm_ch9.json",
        "pharm_ch10" to "content/pharmacology/pharm_ch10.json",
        "pharm_ch11" to "content/pharmacology/pharm_ch11.json",
        "pharm_ch12" to "content/pharmacology/pharm_ch12.json"
    )

    private val cache = android.util.LruCache<String, InteractiveChapterContent>(30)

    fun loadChapterContent(context: Context, chapterId: String): InteractiveChapterContent? {
        synchronized(cache) {
            val cached = cache.get(chapterId)
            if (cached != null) return cached
        }
        val assetPath = registry[chapterId] ?: return null
        val content = try {
            val jsonString = context.assets.open(assetPath).bufferedReader().use { it.readText() }
            try {
                json.decodeFromString<InteractiveChapterContent>(jsonString)
            } catch (e: Exception) {
                try {
                    json.decodeFromString<PucsChapter>(jsonString).toInteractiveChapterContent()
                } catch (e2: Exception) {
                    e.printStackTrace()
                    e2.printStackTrace()
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (content != null) {
            synchronized(cache) {
                cache.put(chapterId, content)
            }
        }
        return content
    }

    suspend fun loadChapterContentSuspended(context: Context, chapterId: String): InteractiveChapterContent? = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        synchronized(cache) {
            val cached = cache.get(chapterId)
            if (cached != null) return@withContext cached
        }
        val assetPath = registry[chapterId] ?: return@withContext null
        val content = try {
            val jsonString = context.assets.open(assetPath).bufferedReader().use { it.readText() }
            try {
                json.decodeFromString<InteractiveChapterContent>(jsonString)
            } catch (e: Exception) {
                try {
                    json.decodeFromString<PucsChapter>(jsonString).toInteractiveChapterContent()
                } catch (e2: Exception) {
                    e.printStackTrace()
                    e2.printStackTrace()
                    null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (content != null) {
            synchronized(cache) {
                cache.put(chapterId, content)
            }
        }
        content
    }

    fun getChapterContentFlow(context: Context, chapterId: String): kotlinx.coroutines.flow.Flow<InteractiveChapterContent?> = kotlinx.coroutines.flow.flow {
        emit(loadChapterContentSuspended(context, chapterId))
    }
}
