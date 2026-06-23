package com.example.data

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.coroutines.flow.first

@Serializable
data class HistoryItem(
    val what: String,
    val why: String,
    val expected: String,
    val sig: String
)

@Serializable
data class ObservationItem(
    val what: String,
    val why: String,
    val expected: String,
    val sig: String
)

@Serializable
data class PalpationItem(
    val what: String,
    val why: String,
    val expected: String,
    val sig: String
)

@Serializable
data class RomAssessment(
    val joints: String,
    val arom: String,
    val prom: String,
    val limitations: String,
    val significance: String
)

@Serializable
data class MmtAssessment(
    val muscles: String,
    val pattern: String,
    val interpretation: String
)

@Serializable
data class NeuroItem(
    val tone: String,
    val reflexes: String,
    val sensation: String,
    val coordination: String,
    val cranial: String,
    val balance: String,
    val findings: String
)

@Serializable
data class SpecialTestItem(
    val name: String,
    val purpose: String,
    val positiveFinding: String,
    val interpretation: String
)

@Serializable
data class OutcomeMeasureItem(
    val name: String,
    val purpose: String,
    val interpretation: String
)

@Serializable
data class GoalItem(
    val shortTerm: List<String>,
    val longTerm: List<String>
)

@Entity(tableName = "clinical_assessment")
@Serializable
data class DiagnosisData(
    @PrimaryKey val id: String,
    val diagnosisName: String,
    val relevantAnatomy: List<String>,
    val relevantPhysiology: List<String>,
    val clinicalCorrelation: List<String>,
    val pathology: List<String>,
    val pharmacology: List<String>,
    val microbiology: List<String>,
    val ptManagement: List<String>,
    val clinicalPostingQuestions: List<String>,
    val vivaQuestions: List<String>,
    
    val historyTaking: List<HistoryItem>,
    val observation: List<ObservationItem>,
    val palpation: List<PalpationItem>,
    val romAssessment: RomAssessment,
    val mmtAssessment: MmtAssessment,
    val neurologicalAssessment: List<NeuroItem>,
    val specialTests: List<SpecialTestItem>,
    val functionalAssessment: List<String>,
    val outcomeMeasures: List<OutcomeMeasureItem>,
    val problemList: List<String>,
    val goals: GoalItem,
    val clinicalReasoning: String
)

object DataLoader {
    @Volatile
    var rawDiagnoses: List<DiagnosisData> = emptyList()

    private val json = Json { ignoreUnknownKeys = true }

    fun load(context: Context) {
        if (rawDiagnoses.isNotEmpty()) return
        synchronized(this) {
            if (rawDiagnoses.isNotEmpty()) return
            try {
                val text = context.assets.open("diagnoses.json").bufferedReader().use { it.readText() }
                rawDiagnoses = json.decodeFromString<List<DiagnosisData>>(text)
            } catch (e: Exception) {
                e.printStackTrace()
                rawDiagnoses = emptyList()
            }
        }
    }

    suspend fun loadIntoDb(context: Context, database: AppDatabase) = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        // Eagerly run assets read/parse on background IO thread
        load(context)

        val dao = database.physioDao()

        // 1. Diagnoses / Clinical Assessments
        val existingAssessments = try {
            dao.getAllAssessments().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingAssessments.size < rawDiagnoses.size && rawDiagnoses.isNotEmpty()) {
            dao.insertAllAssessments(rawDiagnoses)
        } else if (existingAssessments.isNotEmpty()) {
            rawDiagnoses = existingAssessments
        }

        // 2. Anatomies
        val existingAnatomies = try {
            dao.getAllAnatomies().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingAnatomies.isEmpty()) {
            dao.insertAllAnatomies(ContentRepo.initialAnatomies)
            ContentRepo.anatomies = ContentRepo.initialAnatomies
        } else {
            val existingIds = existingAnatomies.map { it.id }.toSet()
            val missingAnatomies = ContentRepo.initialAnatomies.filter { !existingIds.contains(it.id) }
            if (missingAnatomies.isNotEmpty()) {
                dao.insertAllAnatomies(missingAnatomies)
                ContentRepo.anatomies = existingAnatomies + missingAnatomies
            } else {
                ContentRepo.anatomies = existingAnatomies
            }
        }

        // 3. Viva Topics
        val existingVivaTopics = try {
            dao.getAllVivaTopics().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingVivaTopics.size < VivaPrepRepo.initialTopics.size && VivaPrepRepo.initialTopics.isNotEmpty()) {
            dao.insertAllVivaTopics(VivaPrepRepo.initialTopics)
            VivaPrepRepo.topics = VivaPrepRepo.initialTopics
        } else if (existingVivaTopics.isNotEmpty()) {
            VivaPrepRepo.topics = existingVivaTopics
        }

        // 4. Viva Mcqs
        val existingVivaMcqs = try {
            dao.getAllVivaMcqs().first()
        } catch (e: Exception) {
            emptyList()
        }
        val hasPathologyPlaceholders = existingVivaMcqs.any { it.subject == "Pathology" && (it.options.contains("Concept A") || it.question.contains("Pathology (Easy)")) }
        val hasMicrobiologyPlaceholders = existingVivaMcqs.any { it.subject == "Microbiology" && (it.options.contains("Concept A") || it.question.contains("Microbiology (Easy)")) }
        val hasExerciseTherapyIPlaceholders = existingVivaMcqs.any { it.subject == "Exercise Therapy I" && (it.options.contains("Concept A") || it.question.contains("Exercise Therapy I (Easy)")) }
        val hasExerciseTherapyIIPlaceholders = existingVivaMcqs.any { it.subject == "Exercise Therapy II" && (it.options.contains("Concept A") || it.question.contains("Exercise Therapy II (Easy)")) }
        val hasElectrotherapyIPlaceholders = existingVivaMcqs.any { it.subject == "Electrotherapy I" && (it.options.contains("Concept A") || it.question.contains("Electrotherapy I (Easy)")) }
        val hasElectrotherapyIIPlaceholders = existingVivaMcqs.any { it.subject == "Electrotherapy II" && (it.options.contains("Concept A") || it.question.contains("Electrotherapy II (Easy)")) }
        val hasBiomechanicsPlaceholders = existingVivaMcqs.any { it.subject == "Biomechanics" && (it.options.contains("Concept A") || it.question.contains("Biomechanics (Easy)")) }
        val hasKinesiologyPlaceholders = existingVivaMcqs.any { it.subject == "Kinesiology" && (it.options.contains("Concept A") || it.question.contains("Kinesiology (Easy)")) }
        if (existingVivaMcqs.isEmpty() || hasPathologyPlaceholders || hasMicrobiologyPlaceholders || hasExerciseTherapyIPlaceholders || hasExerciseTherapyIIPlaceholders || hasElectrotherapyIPlaceholders || hasElectrotherapyIIPlaceholders || hasBiomechanicsPlaceholders || hasKinesiologyPlaceholders) {
            if (hasPathologyPlaceholders || hasMicrobiologyPlaceholders || hasExerciseTherapyIPlaceholders || hasExerciseTherapyIIPlaceholders || hasElectrotherapyIPlaceholders || hasElectrotherapyIIPlaceholders || hasBiomechanicsPlaceholders || hasKinesiologyPlaceholders) {
                dao.deleteAllVivaMcqs()
            }
            dao.insertAllVivaMcqs(ContentRepo.initialVivaQuestions)
            ContentRepo.vivaQuestions = ContentRepo.initialVivaQuestions
        } else {
            ContentRepo.vivaQuestions = existingVivaMcqs
        }

        // 5. Clinical Posting modules
        val existingPostings = try {
            dao.getAllPostings().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingPostings.isEmpty()) {
            dao.insertAllPostings(ClinicalPostingSurvivalRepo.initialModules)
        } else {
            ClinicalPostingSurvivalRepo.modules = existingPostings
        }

        // 6. Library references
        val existingReferences = try {
            dao.getAllLibraryReferences().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingReferences.isEmpty()) {
            dao.insertAllLibraryReferences(ContentRepo.initialLibraryReferences)
        } else {
            ContentRepo.libraryReferences = existingReferences
        }

        // 7. Clinical Posting Categories
        val existingPostingCats = try {
            dao.getAllPostingCategories().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingPostingCats.isEmpty()) {
            dao.insertAllPostingCategories(ContentRepo.initialClinicalPostingCategories)
        } else {
            ContentRepo.clinicalPostingCategories = existingPostingCats
        }

        // 8. Clinical Cases
        val casesText = try {
            context.assets.open("clinical_cases.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            ""
        }
        val parsedCases = if (casesText.isNotEmpty()) {
            try {
                json.decodeFromString<List<ClinicalCase>>(casesText)
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        } else {
            emptyList()
        }

        val existingCases = try {
            dao.getAllClinicalCases().first()
        } catch (e: Exception) {
            emptyList()
        }

        val casesToUse = if (existingCases.size < parsedCases.size && parsedCases.isNotEmpty()) {
            dao.insertAllClinicalCases(parsedCases)
            parsedCases
        } else {
            existingCases.ifEmpty { parsedCases }
        }

        // 9. Topic Reference Metadata Preloading
        val existingRefs = try {
            dao.getAllTopicReferences().first()
        } catch (e: Exception) {
            emptyList()
        }
        if (existingRefs.isEmpty()) {
            val loadedSpecialTests = try {
                val stText = context.assets.open("special_tests.json").bufferedReader().use { it.readText() }
                json.decodeFromString<List<LocalSpecialTest>>(stText)
            } catch (e: Exception) {
                emptyList()
            }
            val mappedSpecialTests = loadedSpecialTests.map {
                com.example.feature.assessment.SpecialTestModel(
                    test_id = it.test_id,
                    test_name = it.test_name,
                    body_region = it.body_region,
                    purpose = "",
                    patient_position = "",
                    therapist_position = "",
                    procedure = emptyList(),
                    positive_sign = "",
                    clinical_interpretation = ""
                )
            }

            val allRefs = TextbookReferenceManager.mapAllRepositoryTopics(
                anatomies = ContentRepo.initialAnatomies,
                vivaTopics = VivaPrepRepo.initialTopics,
                mcqs = ContentRepo.initialVivaQuestions,
                specialTests = mappedSpecialTests,
                clinicalCases = casesToUse,
                postings = ClinicalPostingSurvivalRepo.initialModules,
                diagnoses = rawDiagnoses
            )
            dao.insertAllTopicReferences(allRefs)
        }
    }
}

@Serializable
data class LocalSpecialTest(
    val test_id: String,
    val test_name: String,
    val body_region: String
)

