package com.example.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
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
        "phys_ch1" to "content/physiology/phys_ch1.json",
        "phys_ch2" to "content/physiology/phys_ch2.json",
        "phys_ch3" to "content/physiology/phys_ch3.json",
        "phys_ch4" to "content/physiology/phys_ch4.json",
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
        "ex2_ch12" to "content/exercise_therapy_ii/ex_ex2_ch12.json"
    )

    fun loadChapterContent(context: Context, chapterId: String): InteractiveChapterContent? {
        val assetPath = registry[chapterId] ?: return null
        return try {
            val jsonString = context.assets.open(assetPath).bufferedReader().use { it.readText() }
            json.decodeFromString<InteractiveChapterContent>(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
