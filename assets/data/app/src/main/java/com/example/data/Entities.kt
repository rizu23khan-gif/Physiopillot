package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

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
