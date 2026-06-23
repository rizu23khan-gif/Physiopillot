package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "checklist_progress")
data class ChecklistProgress(
    @PrimaryKey val assessmentId: String,
    val isCompleted: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "viva_favorites")
data class VivaFavorite(
    @PrimaryKey val questionId: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "curriculum_progress")
data class CurriculumProgress(
    @PrimaryKey val topicId: String,
    val isCompleted: Boolean,
    val type: String, // "ANATOMY", "ASSESSMENT", "VIVA", "POSTING", "REFERENCE"
    val title: String,
    val subject: String,
    val year: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "study_activity")
data class StudyActivity(
    @PrimaryKey val topicId: String,
    val title: String,
    val type: String, // "ANATOMY", "ASSESSMENT", "VIVA", "POSTING", "REFERENCE"
    val subject: String,
    val year: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_streak")
data class UserStreak(
    @PrimaryKey val id: Int = 1,
    val currentStreak: Int,
    val lastActiveDate: String, // YYYY-MM-DD
    val bestStreak: Int
)

@Entity(tableName = "clinical_cases")
@Serializable
data class ClinicalCase(
    @PrimaryKey val id: String,
    val category: String, // "Orthopaedics", "Neurology", "Cardiopulmonary", "Sports Physiotherapy"
    val condition: String,
    val year: String,
    val subject: String,
    val patientProfile: String,
    val chiefComplaint: String,
    val history: List<String>,
    val observation: List<String>,
    val examination: List<String>,
    val specialTests: List<String>,
    val linkedSpecialTests: List<String> = emptyList(), // matching IDs like "st_lachman"
    val assessmentFindings: List<String>,
    val problemList: List<String>,
    val physiotherapyDiagnosis: String,
    val shortTermGoals: List<String>,
    val longTermGoals: List<String>,
    val treatmentPlan: List<String>,
    val outcomeMeasures: List<String>,
    val redFlags: List<String>,
    val isBookmarked: Boolean = false
)


@Entity(tableName = "topic_reference_metadata")
@Serializable
data class TopicReferenceMetadata(
    @PrimaryKey val topicId: String,
    val repository: String, // "Anatomy", "Physiology", "Viva", "MCQ", "Special Tests", "Clinical Cases", "Clinical Posting", "Diagnoses"
    val topicName: String,
    val primaryReference: String,
    val secondaryReference: String,
    val referenceCategory: String
)


