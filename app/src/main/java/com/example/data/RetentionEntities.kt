package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "review_cards")
@Serializable
data class ReviewCard(
    @PrimaryKey val cardId: String,
    val subject: String,         // e.g., "Anatomy", "Physiology", "Electrotherapy", "Exercise Therapy", "Pharmacology", "Biomechanics"
    val category: String,        // e.g., "Definition", "Muscle Spec", "MCQ", "Viva Prep", "Clinical Pearl", "Drug Rationale"
    val frontText: String,
    val backText: String,
    val state: String = "NEW",   // "NEW", "LEARNING", "REVIEWING", "MASTERED"
    val lastReviewed: Long = 0,
    val nextReviewDue: Long = 0,
    val intervalDays: Int = 0,
    val easeFactor: Float = 2.5f,
    val timesReviewed: Int = 0
)

@Entity(tableName = "review_sessions")
@Serializable
data class ReviewSession(
    @PrimaryKey val sessionId: String,
    val timestamp: Long = System.currentTimeMillis(),
    val cardsReviewed: Int,
    val successfulReviews: Int,
    val durationMs: Long
)
