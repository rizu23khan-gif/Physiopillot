package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ui.components.TopicEvidenceReference

@Database(
    entities = [
        ChecklistProgress::class, 
        VivaFavorite::class,
        DetailedAnatomy::class,
        DiagnosisData::class,
        VivaTopic::class,
        VivaMcq::class,
        SurvivalModule::class,
        TopicEvidenceReference::class,
        ClinicalPostingCategory::class,
        CurriculumProgress::class,
        StudyActivity::class,
        UserStreak::class,
        ClinicalCase::class,
        TopicReferenceMetadata::class,
        ReviewCard::class,
        ReviewSession::class
    ], 
    version = 6, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun physioDao(): PhysioDao
    abstract fun retentionDao(): RetentionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `topic_reference_metadata` (`topicId` TEXT NOT NULL, `repository` TEXT NOT NULL, `topicName` TEXT NOT NULL, `primaryReference` TEXT NOT NULL, `secondaryReference` TEXT NOT NULL, `referenceCategory` TEXT NOT NULL, PRIMARY KEY(`topicId`))")
            }
        }

        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `review_cards` (`cardId` TEXT NOT NULL, `subject` TEXT NOT NULL, `category` TEXT NOT NULL, `frontText` TEXT NOT NULL, `backText` TEXT NOT NULL, `state` TEXT NOT NULL, `lastReviewed` INTEGER NOT NULL, `nextReviewDue` INTEGER NOT NULL, `intervalDays` INTEGER NOT NULL, `easeFactor` REAL NOT NULL, `timesReviewed` INTEGER NOT NULL, PRIMARY KEY(`cardId`))")
                db.execSQL("CREATE TABLE IF NOT EXISTS `review_sessions` (`sessionId` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `cardsReviewed` INTEGER NOT NULL, `successfulReviews` INTEGER NOT NULL, `durationMs` INTEGER NOT NULL, PRIMARY KEY(`sessionId`))")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "physiopilot_database"
                )
                .addMigrations(MIGRATION_4_5, MIGRATION_5_6)
                .fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationOnDowngrade()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
