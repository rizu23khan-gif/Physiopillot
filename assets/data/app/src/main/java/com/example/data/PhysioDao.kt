package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhysioDao {
    @Query("SELECT * FROM checklist_progress")
    fun getAllProgress(): Flow<List<ChecklistProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProgress(progress: ChecklistProgress)

    @Query("SELECT * FROM viva_favorites")
    fun getFavorites(): Flow<List<VivaFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun toggleFavorite(favorite: VivaFavorite)
    
    @Query("DELETE FROM viva_favorites WHERE questionId = :questionId")
    suspend fun removeFavorite(questionId: String)
}
