package com.example.feature.retention

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

// Supporting analytical data structures
data class SubjectMastery(
    val subject: String,
    val totalCount: Int,
    val masteredCount: Int,
    val reviewingCount: Int,
    val masteryPercentage: Int
)

data class WeakTopic(
    val topicName: String,
    val subject: String,
    val forgotRate: Int,
    val learningCount: Int,
    val totalCount: Int
)

data class CardBreakdown(
    val newCount: Int,
    val learningCount: Int,
    val reviewingCount: Int,
    val masteredCount: Int,
    val totalCount: Int
)

data class TrendDataPoint(
    val dateLabel: String,
    val timestamp: Long,
    val count: Int
)

enum class RecommendationSeverity { HIGH, MEDIUM, LOW }

data class RevisionRecommendation(
    val title: String,
    val subject: String,
    val reason: String,
    val actionLabel: String,
    val severity: RecommendationSeverity,
    val dueCount: Int
)

class RetentionViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val repository = RetentionRepository(application, db.retentionDao(), db.physioDao())

    val allCards: StateFlow<List<ReviewCard>> = repository.allCards
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val dueCards: StateFlow<List<ReviewCard>> = repository.dueCards
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val streakObj: StateFlow<UserStreak?> = db.physioDao().getStreakFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val allSessions: StateFlow<List<ReviewSession>> = repository.allSessions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val dueCardsCount: StateFlow<Int> = repository.getDueCardsCountFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    init {
        viewModelScope.launch {
            repository.seedFlashcardsIfNeeded()
        }
    }

    // 1. Overall Retention Score Formula: (Mastered + Reviewing) / Total Cards * 100
    val overallRetentionScore: StateFlow<Int> = allCards.map { cards ->
        if (cards.isEmpty()) return@map 0
        val total = cards.size.toFloat()
        val mastered = cards.count { it.state == "MASTERED" }
        val reviewing = cards.count { it.state == "REVIEWING" }
        (((mastered + reviewing) / total) * 100f).roundToInt().coerceIn(0, 100)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Maintaining backward compatibility for old UI queries
    val retentionScore: StateFlow<Int> = overallRetentionScore

    // 2. Subject Mastery %
    val subjectMasteryList: StateFlow<List<SubjectMastery>> = allCards.map { cards ->
        val targetSubjects = listOf("Anatomy", "Physiology", "Biomechanics", "Exercise Therapy", "Electrotherapy", "Pharmacology", "Clinical Cases")
        val grouped = cards.groupBy { card ->
            when (card.subject) {
                "Nerve Lesions", "Gait Analysis", "Clinical Cases" -> "Clinical Cases"
                else -> card.subject
            }
        }
        targetSubjects.map { subj ->
            val subjCards = grouped[subj] ?: emptyList()
            val total = subjCards.size
            val mastered = subjCards.count { it.state == "MASTERED" }
            val reviewing = subjCards.count { it.state == "REVIEWING" }
            val pct = if (total > 0) (((mastered + reviewing).toFloat() / total.toFloat()) * 100f).roundToInt() else 0
            SubjectMastery(subj, total, mastered, reviewing, pct)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 3. Weak Topic Detection (Forgot Rate > 40%)
    val weakTopics: StateFlow<List<WeakTopic>> = allCards.map { cards ->
        val grouped = cards.groupBy { "${it.subject} - ${it.category}" }
        grouped.mapNotNull { (topicKey, topicCards) ->
            val totalReviewed = topicCards.count { it.timesReviewed > 0 }
            if (totalReviewed == 0) return@mapNotNull null

            val hardCount = topicCards.count { it.state == "LEARNING" || (it.timesReviewed > 0 && it.easeFactor < 2.2f) }
            val rate = ((hardCount.toFloat() / totalReviewed.toFloat()) * 100f).roundToInt()

            val parts = topicKey.split(" - ")
            val subj = parts.getOrElse(0) { "" }
            val cat = parts.getOrElse(1) { "" }

            if (rate > 40) {
                WeakTopic(
                    topicName = cat,
                    subject = when (subj) {
                        "Nerve Lesions", "Gait Analysis", "Clinical Cases" -> "Clinical Cases"
                        else -> subj
                    },
                    forgotRate = rate,
                    learningCount = topicCards.count { it.state == "LEARNING" },
                    totalCount = topicCards.size
                )
            } else {
                null
            }
        }.sortedByDescending { it.forgotRate }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 4. Due Card Breakdown
    val cardBreakdown: StateFlow<CardBreakdown> = allCards.map { cards ->
        CardBreakdown(
            newCount = cards.count { it.state == "NEW" },
            learningCount = cards.count { it.state == "LEARNING" },
            reviewingCount = cards.count { it.state == "REVIEWING" },
            masteredCount = cards.count { it.state == "MASTERED" },
            totalCount = cards.size
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CardBreakdown(0, 0, 0, 0, 0))

    // 5. Daily Revision Trend
    val trendLast7Days: StateFlow<List<TrendDataPoint>> = allSessions.map { sessions ->
        getPastDaysTrend(sessions, 7)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val trendLast30Days: StateFlow<List<TrendDataPoint>> = allSessions.map { sessions ->
        getPastDaysTrend(sessions, 30)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 6. Study Streak Analytics: Active Days This Month
    val activeDaysThisMonth: StateFlow<List<String>> = combine(allSessions, db.physioDao().getRecentStudyActivities()) { sessions, activities ->
        val sdfDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val currentMonthFormat = SimpleDateFormat("yyyy-MM", Locale.US)
        val currentMonthPrefix = currentMonthFormat.format(Date())
        val activeDates = mutableSetOf<String>()

        sessions.forEach {
            val dateStr = sdfDate.format(Date(it.timestamp))
            if (dateStr.startsWith(currentMonthPrefix)) {
                activeDates.add(dateStr)
            }
        }

        activities.forEach {
            val dateStr = sdfDate.format(Date(it.timestamp))
            if (dateStr.startsWith(currentMonthPrefix)) {
                activeDates.add(dateStr)
            }
        }

        activeDates.toList().sorted()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 7. Exam Readiness Score
    val examReadinessScore: StateFlow<Int> = combine(
        allCards,
        overallRetentionScore,
        activeDaysThisMonth,
        streakObj
    ) { cards, retScore, activeDays, streak ->
        if (cards.isEmpty()) return@combine 0
        val total = cards.size.toFloat()

        // R: Retention Score (weight: 0.4)
        val r = retScore.toFloat()

        // C: Subject Coverage (weight: 0.2)
        val reviewedCount = cards.count { it.timesReviewed > 0 }
        val c = (reviewedCount / total) * 100f

        // Consistency (weight: 0.2)
        val currentStreak = streak?.currentStreak ?: 0
        val calendarDaysCount = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val streakPart = (currentStreak * 10f).coerceAtMost(50f)
        val activeDaysPart = if (calendarDaysCount > 0) {
            ((activeDays.size.toFloat() / calendarDaysCount.toFloat()) * 50f).coerceAtMost(50f)
        } else {
            0f
        }
        val consistency = streakPart + activeDaysPart

        // M: Mastered % (weight: 0.2)
        val mastered = cards.count { it.state == "MASTERED" }
        val m = (mastered / total) * 100f

        val weighted = (r * 0.4f) + (c * 0.2f) + (consistency * 0.2f) + (m * 0.2f)
        weighted.roundToInt().coerceIn(0, 100)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // 8. Revision Recommendation Engine: Today's Focus Topics
    val recommendations: StateFlow<List<RevisionRecommendation>> = combine(
        allCards,
        dueCards,
        weakTopics,
        subjectMasteryList
    ) { cards, dues, weaks, masteryList ->
        val recs = mutableListOf<RevisionRecommendation>()

        // Type 1: High Priority - Weak Topics with Forgot Rate > 40%
        weaks.take(2).forEach { weak ->
            val subjDueCount = dues.count {
                val cardSubj = when (it.subject) {
                    "Nerve Lesions", "Gait Analysis", "Clinical Cases" -> "Clinical Cases"
                    else -> it.subject
                }
                cardSubj == weak.subject && it.category == weak.topicName
            }
            recs.add(
                RevisionRecommendation(
                    title = "Critical Recall Gap: ${weak.topicName}",
                    subject = weak.subject,
                    reason = "Retention for ${weak.topicName} is low (Forgot Rate: ${weak.forgotRate}%). Prompt revision is crucial.",
                    actionLabel = "Start targeted revision on ${weak.topicName}",
                    severity = RecommendationSeverity.HIGH,
                    dueCount = subjDueCount
                )
            )
        }

        // Type 2: Medium Priority - Subjects with due review cards
        val dueBySubject = dues.groupBy {
            when (it.subject) {
                "Nerve Lesions", "Gait Analysis", "Clinical Cases" -> "Clinical Cases"
                else -> it.subject
            }
        }

        dueBySubject.entries.sortedByDescending { it.value.size }.take(2).forEach { (subj, list) ->
            if (recs.none { it.subject == subj && it.severity == RecommendationSeverity.HIGH }) {
                recs.add(
                    RevisionRecommendation(
                        title = "Spaced Repetition Due: $subj",
                        subject = subj,
                        reason = "You have ${list.size} due flashcards ready for physical therapy review in $subj.",
                        actionLabel = "Filter and review $subj cards",
                        severity = RecommendationSeverity.MEDIUM,
                        dueCount = list.size
                    )
                )
            }
        }

        // Type 3: Low Premium Priority - Low mastery subjects
        masteryList.filter { it.totalCount > 0 && it.masteryPercentage < 40 }.take(1).forEach { mastery ->
            if (recs.none { it.subject == mastery.subject }) {
                recs.add(
                    RevisionRecommendation(
                        title = "Unlock Mastery in ${mastery.subject}",
                        subject = mastery.subject,
                        reason = "Current mastery of this subject is only ${mastery.masteryPercentage}%. Engage in more repetitions to lock into long-term memory.",
                        actionLabel = "Explore ${mastery.subject}",
                        severity = RecommendationSeverity.LOW,
                        dueCount = dues.count {
                            val cardSubj = when (it.subject) {
                                "Nerve Lesions", "Gait Analysis", "Clinical Cases" -> "Clinical Cases"
                                else -> it.subject
                            }
                            cardSubj == mastery.subject
                        }
                    )
                )
            }
        }

        if (recs.isEmpty()) {
            recs.add(
                RevisionRecommendation(
                    title = "Anatomy Landmark Prep",
                    subject = "Anatomy",
                    reason = "Anatomy recall is best maintained by steady repetitions. Keep up your study consistency!",
                    actionLabel = "Start Anatomy repetition",
                    severity = RecommendationSeverity.LOW,
                    dueCount = 0
                )
            )
        }

        recs
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // 2. Subject Distribution for Due Cards
    val subjectDistribution: StateFlow<Map<String, Int>> = dueCards.map { cards ->
        cards.groupBy { it.subject }.mapValues { it.value.size }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    // 3. Estimated Time in Minutes (e.g. 15 seconds per card)
    val estimatedTimeMinutes: StateFlow<Int> = dueCards.map { cards ->
        (cards.size * 15f / 60f).roundToInt().coerceAtLeast(if (cards.isNotEmpty()) 1 else 0)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun submitReview(cardId: String, quality: String) {
        viewModelScope.launch {
            repository.recordReview(cardId, quality)
        }
    }

    fun logSession(count: Int, correct: Int, durationMs: Long) {
        viewModelScope.launch {
            repository.saveSession(count, correct, durationMs)
        }
    }

    fun resetAndSeed() {
        viewModelScope.launch {
            repository.clearAndResetDeck()
        }
    }

    private fun getPastDaysTrend(sessions: List<ReviewSession>, daysCount: Int): List<TrendDataPoint> {
        val sdfDay = SimpleDateFormat("MMM dd", Locale.US)
        val sdfFull = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val result = mutableListOf<TrendDataPoint>()
        val cal = Calendar.getInstance()

        // Start daysCount days ago
        cal.add(Calendar.DAY_OF_YEAR, -(daysCount - 1))

        // Group sessions by day prefix
        val sessionMap = sessions.groupBy {
            val calSession = Calendar.getInstance().apply { timeInMillis = it.timestamp }
            sdfFull.format(calSession.time)
        }

        for (i in 0 until daysCount) {
            val dateKey = sdfFull.format(cal.time)
            val dayLabel = sdfDay.format(cal.time)
            val daySessions = sessionMap[dateKey] ?: emptyList()
            val totalReviewed = daySessions.sumOf { it.cardsReviewed }

            result.add(TrendDataPoint(dayLabel, cal.timeInMillis, totalReviewed))
            cal.add(Calendar.DAY_OF_YEAR, 1)
        }
        return result
    }
}

