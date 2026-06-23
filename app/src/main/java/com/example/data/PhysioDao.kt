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

    // Anatomy Revision
    @Query("SELECT * FROM anatomy_revision")
    fun getAllAnatomies(): Flow<List<DetailedAnatomy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnatomies(anatomies: List<DetailedAnatomy>)

    // Clinical Assessment
    @Query("SELECT * FROM clinical_assessment")
    fun getAllAssessments(): Flow<List<DiagnosisData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAssessments(assessments: List<DiagnosisData>)

    // Viva Prep Oral
    @Query("SELECT * FROM viva_prep_topics")
    fun getAllVivaTopics(): Flow<List<VivaTopic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVivaTopics(topics: List<VivaTopic>)

    // Viva Prep MCQs
    @Query("SELECT * FROM viva_mcqs")
    fun getAllVivaMcqs(): Flow<List<VivaMcq>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVivaMcqs(mcqs: List<VivaMcq>)

    @Query("DELETE FROM viva_mcqs")
    suspend fun deleteAllVivaMcqs()

    // Clinical Posting
    @Query("SELECT * FROM clinical_postings")
    fun getAllPostings(): Flow<List<SurvivalModule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostings(postings: List<SurvivalModule>)

    // Library References
    @Query("SELECT * FROM library_references")
    fun getAllLibraryReferences(): Flow<List<com.example.ui.components.TopicEvidenceReference>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLibraryReferences(references: List<com.example.ui.components.TopicEvidenceReference>)

    // Clinical Posting Categories
    @Query("SELECT * FROM clinical_posting_categories")
    fun getAllPostingCategories(): Flow<List<ClinicalPostingCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPostingCategories(categories: List<ClinicalPostingCategory>)

    // Curriculum Progress Tracking
    @Query("SELECT * FROM curriculum_progress")
    fun getAllCurriculumProgress(): Flow<List<CurriculumProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurriculumProgress(progress: CurriculumProgress)

    @Query("SELECT * FROM study_activity ORDER BY timestamp DESC LIMIT 10")
    fun getRecentStudyActivities(): Flow<List<StudyActivity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudyActivity(activity: StudyActivity)

    @Query("SELECT * FROM user_streak WHERE id = 1")
    fun getStreakFlow(): Flow<UserStreak?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreak(streak: UserStreak)

    // Clinical Cases queries
    @Query("SELECT * FROM clinical_cases")
    fun getAllClinicalCases(): Flow<List<ClinicalCase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllClinicalCases(cases: List<ClinicalCase>)

    @Query("UPDATE clinical_cases SET isBookmarked = :isBookmarked WHERE id = :caseId")
    suspend fun updateBookmarkStatus(caseId: String, isBookmarked: Boolean)

    // Topic Reference Metadata
    @Query("SELECT * FROM topic_reference_metadata")
    fun getAllTopicReferences(): Flow<List<TopicReferenceMetadata>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopicReferences(references: List<TopicReferenceMetadata>)

    @Query("DELETE FROM clinical_assessment")
    suspend fun deleteAllAssessments()

    @Query("DELETE FROM viva_prep_topics")
    suspend fun deleteAllVivaTopics()

    @Query("DELETE FROM clinical_cases")
    suspend fun deleteAllClinicalCases()

    @Query("DELETE FROM topic_reference_metadata")
    suspend fun deleteAllTopicReferences()
}
