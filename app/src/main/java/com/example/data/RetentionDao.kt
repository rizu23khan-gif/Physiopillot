package com.example.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RetentionDao {
    @Query("SELECT * FROM review_cards")
    fun getAllCards(): Flow<List<ReviewCard>>

    @Query("SELECT * FROM review_cards WHERE nextReviewDue <= :now")
    fun getDueCards(now: Long): Flow<List<ReviewCard>>

    @Query("SELECT COUNT(*) FROM review_cards WHERE nextReviewDue <= :now")
    fun getDueCardsCount(now: Long): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: ReviewCard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCards(cards: List<ReviewCard>)

    @Query("UPDATE review_cards SET state = :state, lastReviewed = :lastReviewed, nextReviewDue = :nextReviewDue, intervalDays = :intervalDays, easeFactor = :easeFactor, timesReviewed = :timesReviewed WHERE cardId = :cardId")
    suspend fun updateCardProgress(
        cardId: String,
        state: String,
        lastReviewed: Long,
        nextReviewDue: Long,
        intervalDays: Int,
        easeFactor: Float,
        timesReviewed: Int
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: ReviewSession)

    @Query("SELECT * FROM review_sessions ORDER BY timestamp DESC")
    fun getAllSessions(): Flow<List<ReviewSession>>

    @Query("DELETE FROM review_cards")
    suspend fun clearAllCards()
}
