package com.example.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

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

@Serializable
data class DiagnosisData(
    val id: String,
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
    var rawDiagnoses: List<DiagnosisData> = emptyList()

    private val json = Json { ignoreUnknownKeys = true }

    fun load(context: Context) {
        if (rawDiagnoses.isNotEmpty()) return
        val text = context.assets.open("diagnoses.json").bufferedReader().use { it.readText() }
        rawDiagnoses = json.decodeFromString<List<DiagnosisData>>(text)
    }
}
