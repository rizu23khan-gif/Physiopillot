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
        TopicReferenceMetadata::class
    ], 
    version = 5, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun physioDao(): PhysioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `topic_reference_metadata` (`topicId` TEXT NOT NULL, `repository` TEXT NOT NULL, `topicName` TEXT NOT NULL, `primaryReference` TEXT NOT NULL, `secondaryReference` TEXT NOT NULL, `referenceCategory` TEXT NOT NULL, PRIMARY KEY(`topicId`))")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "physiopilot_database"
                )
                .addMigrations(MIGRATION_4_5)
                .fallbackToDestructiveMigration(true)
                .fallbackToDestructiveMigrationOnDowngrade()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
