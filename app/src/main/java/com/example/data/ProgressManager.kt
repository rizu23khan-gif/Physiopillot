package com.example.data

import android.content.Context
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object ProgressManager {

    suspend fun logActivityAndCheckStreak(
        context: Context,
        topicId: String,
        title: String,
        type: String,
        subject: String,
        year: String
    ) {
        val database = AppDatabase.getDatabase(context)
        val dao = database.physioDao()

        // 1. Log the study activity
        val activity = StudyActivity(
            topicId = topicId,
            title = title,
            type = type,
            subject = subject,
            year = year,
            timestamp = System.currentTimeMillis()
        )
        dao.insertStudyActivity(activity)

        // 2. Audit & update streak
        try {
            val sdfReal = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val currentDateStr = sdfReal.format(Date())

            val currentStreakObj = dao.getStreakFlow().first()
            if (currentStreakObj == null) {
                // First streak record
                val newStreak = UserStreak(
                    id = 1,
                    currentStreak = 1,
                    lastActiveDate = currentDateStr,
                    bestStreak = 1
                )
                dao.insertStreak(newStreak)
            } else {
                val lastActiveStr = currentStreakObj.lastActiveDate
                if (lastActiveStr == currentDateStr) {
                    // Already active today, streak remains unchanged
                } else {
                    // Check if yesterday
                    val cal = Calendar.getInstance()
                    cal.add(Calendar.DAY_OF_YEAR, -1)
                    val yesterdayDateStr = sdfReal.format(cal.time)

                    val newStreakVal = if (lastActiveStr == yesterdayDateStr) {
                        currentStreakObj.currentStreak + 1
                    } else {
                        1
                    }

                    val bestStreakVal = maxOf(currentStreakObj.bestStreak, newStreakVal)
                    val updatedStreak = UserStreak(
                        id = 1,
                        currentStreak = newStreakVal,
                        lastActiveDate = currentDateStr,
                        bestStreak = bestStreakVal
                    )
                    dao.insertStreak(updatedStreak)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun toggleTopicCompletion(
        context: Context,
        topicId: String,
        isCompleted: Boolean,
        type: String,
        title: String,
        subject: String,
        year: String
    ) {
        val database = AppDatabase.getDatabase(context)
        val dao = database.physioDao()

        val progress = CurriculumProgress(
            topicId = topicId,
            isCompleted = isCompleted,
            type = type,
            title = title,
            subject = subject,
            year = year,
            timestamp = System.currentTimeMillis()
        )
        dao.updateCurriculumProgress(progress)

        // Also log activity to trigger streak updates
        if (isCompleted) {
            logActivityAndCheckStreak(context, topicId, title, type, subject, year)
        }
    }
}
