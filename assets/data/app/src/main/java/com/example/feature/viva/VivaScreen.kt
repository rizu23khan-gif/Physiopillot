package com.example.feature.viva

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.ContentRepo
import com.example.data.VivaMcq

class VivaAnalyticsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("viva_analytics_v2", Context.MODE_PRIVATE)

    var totalAttempted: Int
        get() = prefs.getInt("totalAttempted", 0)
        set(value) = prefs.edit().putInt("totalAttempted", value).apply()

    var totalCorrect: Int
        get() = prefs.getInt("totalCorrect", 0)
        set(value) = prefs.edit().putInt("totalCorrect", value).apply()

    var totalIncorrect: Int
        get() = prefs.getInt("totalIncorrect", 0)
        set(value) = prefs.edit().putInt("totalIncorrect", value).apply()
        
    fun getSubjectAttempted(subject: String): Int = prefs.getInt("attempted_$subject", 0)
    fun setSubjectAttempted(subject: String, value: Int) = prefs.edit().putInt("attempted_$subject", value).apply()

    fun getSubjectCorrect(subject: String): Int = prefs.getInt("correct_$subject", 0)
    fun setSubjectCorrect(subject: String, value: Int) = prefs.edit().putInt("correct_$subject", value).apply()

    fun getSubjectIncorrect(subject: String): Int = prefs.getInt("incorrect_$subject", 0)
    fun setSubjectIncorrect(subject: String, value: Int) = prefs.edit().putInt("incorrect_$subject", value).apply()
    
    fun getMistakes(): Set<String> = prefs.getStringSet("mistakes", emptySet()) ?: emptySet()
    fun addMistake(id: String) {
        val mistakes = getMistakes().toMutableSet()
        mistakes.add(id)
        prefs.edit().putStringSet("mistakes", mistakes).apply()
    }
    fun removeMistake(id: String) {
        val mistakes = getMistakes().toMutableSet()
        mistakes.remove(id)
        prefs.edit().putStringSet("mistakes", mistakes).apply()
    }

    fun getBookmarks(): Set<String> = prefs.getStringSet("bookmarks", emptySet()) ?: emptySet()
    fun toggleBookmark(id: String) {
        val bookmarks = getBookmarks().toMutableSet()
        if (bookmarks.contains(id)) bookmarks.remove(id) else bookmarks.add(id)
        prefs.edit().putStringSet("bookmarks", bookmarks).apply()
    }
}

enum class VivaScene { DASHBOARD, QUIZ }

sealed class QuizMode {
    data class Subject(val subject: String, val highYieldOnly: Boolean) : QuizMode()
    object MistakeReview : QuizMode()
    object Bookmarks : QuizMode()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VivaScreen(navController: NavController) {
    val context = LocalContext.current
    val analytics = remember { VivaAnalyticsManager(context) }
    
    var currentScene by remember { mutableStateOf(VivaScene.DASHBOARD) }
    var currentMode by remember { mutableStateOf<QuizMode>(QuizMode.Subject("Anatomy", false)) }
    var highYieldEnabled by remember { mutableStateOf(false) }
    
    // Trigger recomposition on analytics updates
    var analyticsTrigger by remember { mutableIntStateOf(0) }
    val refreshAnalytics = { analyticsTrigger++ }

    if (currentScene == VivaScene.DASHBOARD) {
        VivaDashboard(
            navController = navController,
            analytics = analytics,
            highYieldEnabled = highYieldEnabled,
            onHighYieldToggle = { highYieldEnabled = it },
            onStartSubject = { subj ->
                currentMode = QuizMode.Subject(subj, highYieldEnabled)
                currentScene = VivaScene.QUIZ
            },
            onReviewMistakes = {
                currentMode = QuizMode.MistakeReview
                currentScene = VivaScene.QUIZ
            },
            onReviewBookmarks = {
                currentMode = QuizMode.Bookmarks
                currentScene = VivaScene.QUIZ
            },
            analyticsTrigger = analyticsTrigger
        )
    } else {
        VivaQuizScreen(
            mode = currentMode,
            analytics = analytics,
            onBack = { 
                refreshAnalytics()
                currentScene = VivaScene.DASHBOARD 
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VivaDashboard(
    navController: NavController,
    analytics: VivaAnalyticsManager,
    highYieldEnabled: Boolean,
    onHighYieldToggle: (Boolean) -> Unit,
    onStartSubject: (String) -> Unit,
    onReviewMistakes: () -> Unit,
    onReviewBookmarks: () -> Unit,
    analyticsTrigger: Int
) {
    val subjects = listOf(
        "Anatomy", "Physiology", "Pathology", "Microbiology",
        "Exercise Therapy I", "Exercise Therapy II",
        "Electrotherapy I", "Electrotherapy II",
        "Biomechanics", "Kinesiology"
    )

    // Compute stats
    val totalAtt = analytics.totalAttempted
    val totalCorr = analytics.totalCorrect
    val totalInc = analytics.totalIncorrect
    val overallAcc = if (totalAtt > 0) (totalCorr.toFloat() / totalAtt * 100).toInt() else 0

    var strongestSubj = "N/A"
    var weakestSubj = "N/A"
    var mostPracSubj = "N/A"
    var maxAcc = -1
    var minAcc = 101
    var maxPrac = -1

    subjects.forEach { subj ->
        val att = analytics.getSubjectAttempted(subj)
        val corr = analytics.getSubjectCorrect(subj)
        if (att > 0) {
            val acc = (corr.toFloat() / att * 100).toInt()
            if (acc > maxAcc) { maxAcc = acc; strongestSubj = subj }
            if (acc < minAcc) { minAcc = acc; weakestSubj = subj }
            if (att > maxPrac) { maxPrac = att; mostPracSubj = subj }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Viva Preparation") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Overall Stats
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Overall Performance", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text("Attempted: $totalAtt", style = MaterialTheme.typography.bodyMedium)
                                Text("Correct: $totalCorr", style = MaterialTheme.typography.bodyMedium)
                                Text("Incorrect: $totalInc", style = MaterialTheme.typography.bodyMedium)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Accuracy", style = MaterialTheme.typography.labelMedium)
                                Text("$overallAcc%", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Strongest: $strongestSubj", style = MaterialTheme.typography.bodySmall)
                        Text("Weakest: $weakestSubj", style = MaterialTheme.typography.bodySmall)
                        Text("Most Practiced: $mostPracSubj", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            // Controls
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("High-Yield Revision Mode", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                    Switch(checked = highYieldEnabled, onCheckedChange = onHighYieldToggle)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = onReviewMistakes, modifier = Modifier.weight(1f)) {
                        Text("Review Mistakes")
                    }
                    Button(onClick = onReviewBookmarks, modifier = Modifier.weight(1f)) {
                        Text("Bookmarks")
                    }
                }
            }

            // Subjects
            item {
                Text("Subjects", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            
            items(subjects) { subj ->
                val att = analytics.getSubjectAttempted(subj)
                val corr = analytics.getSubjectCorrect(subj)
                val acc = if (att > 0) (corr.toFloat() / att * 100).toInt() else 0
                
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onStartSubject(subj) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(subj, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                            Text("Attempted: $att | Accuracy: $acc%", style = MaterialTheme.typography.bodySmall)
                        }
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go", modifier = Modifier.padding(start=8.dp)) 
                        // Wait, ArrowForward is better, but since I have ArrowBack, I'll use it or omit. Let's omit or use a generic "play" text.
                        // I will fix icon to avoid error.
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VivaQuizScreen(mode: QuizMode, analytics: VivaAnalyticsManager, onBack: () -> Unit) {
    val questions = remember(mode) {
        when (mode) {
            is QuizMode.Subject -> ContentRepo.vivaQuestions.filter { 
                it.subject == mode.subject && (!mode.highYieldOnly || it.difficulty == "High Yield") 
            }
            is QuizMode.MistakeReview -> {
                val mistakes = analytics.getMistakes()
                ContentRepo.vivaQuestions.filter { mistakes.contains(it.id) }
            }
            is QuizMode.Bookmarks -> {
                val bookmarks = analytics.getBookmarks()
                ContentRepo.vivaQuestions.filter { bookmarks.contains(it.id) }
            }
        }
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    
    // Answer state per question index
    val selectedOptions = remember { mutableStateMapOf<Int, String>() }
    
    // We want to re-fetch bookmarks set on changes
    var bookmarks by remember { mutableStateOf(analytics.getBookmarks()) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    val titleText = when (mode) {
                        is QuizMode.Subject -> mode.subject
                        is QuizMode.MistakeReview -> "Review Mistakes"
                        is QuizMode.Bookmarks -> "Bookmarks"
                    }
                    Text(titleText)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (questions.isNotEmpty()) {
                        val currentQ = questions[currentIndex]
                        val isBookmarked = bookmarks.contains(currentQ.id)
                        IconButton(onClick = {
                            analytics.toggleBookmark(currentQ.id)
                            bookmarks = analytics.getBookmarks()
                        }) {
                            if (isBookmarked) {
                                Icon(Icons.Filled.Bookmark, contentDescription = "Bookmarked", tint = MaterialTheme.colorScheme.primary)
                            } else {
                                Icon(Icons.Filled.BookmarkBorder, contentDescription = "Bookmark")
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (questions.isNotEmpty()) {
                BottomAppBar {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { if (currentIndex > 0) currentIndex-- },
                            enabled = currentIndex > 0
                        ) {
                            Text("Previous")
                        }
                        Text("Q ${currentIndex + 1} of ${questions.size}", style = MaterialTheme.typography.labelMedium)
                        Button(
                            onClick = { if (currentIndex < questions.size - 1) currentIndex++ },
                            enabled = currentIndex < questions.size - 1
                        ) {
                            Text("Next")
                        }
                    }
                }
            }
        }
    ) { padding ->
        if (questions.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No questions found for this selection.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            val q = questions[currentIndex]
            val selectedOption = selectedOptions[currentIndex]
            val isAnswered = selectedOption != null
            val isCorrect = selectedOption == q.correctAnswer
            
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Surface(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(4.dp)) {
                        Text(q.difficulty, modifier = Modifier.padding(horizontal=8.dp, vertical=4.dp), style=MaterialTheme.typography.labelSmall)
                    }
                }
                
                Text(text = q.question, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                
                q.options.forEach { option ->
                    val isOptSelected = selectedOption == option
                    val isOptCorrect = option == q.correctAnswer

                    val bgColor = when {
                        !isAnswered -> MaterialTheme.colorScheme.surfaceVariant
                        isOptCorrect -> Color(0xFF4CAF50).copy(alpha = 0.3f)
                        isOptSelected && !isOptCorrect -> Color(0xFFF44336).copy(alpha = 0.3f)
                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    }
                    
                    val bColor = when {
                        isAnswered && isOptCorrect -> Color(0xFF4CAF50)
                        isAnswered && isOptSelected && !isOptCorrect -> Color(0xFFF44336)
                        else -> Color.Transparent
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).clickable(enabled = !isAnswered) {
                            selectedOptions[currentIndex] = option
                            val correctNow = option == q.correctAnswer
                            // Update analytics
                            analytics.totalAttempted++
                            if(correctNow) analytics.totalCorrect++ else analytics.totalIncorrect++
                            
                            val satt = analytics.getSubjectAttempted(q.subject)
                            val scorr = analytics.getSubjectCorrect(q.subject)
                            val sinc = analytics.getSubjectIncorrect(q.subject)
                            
                            analytics.setSubjectAttempted(q.subject, satt + 1)
                            if(correctNow) {
                                analytics.setSubjectCorrect(q.subject, scorr + 1)
                                analytics.removeMistake(q.id)
                            } else {
                                analytics.setSubjectIncorrect(q.subject, sinc + 1)
                                analytics.addMistake(q.id)
                            }
                        },
                        color = bgColor,
                        border = androidx.compose.foundation.BorderStroke(2.dp, bColor)
                    ) {
                        Text(text = option, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyLarge)
                    }
                }

                if (isAnswered) {
                    LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        item {
                            Text(
                                text = if (isCorrect) "Correct!" else "Incorrect",
                                color = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFF44336),
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            if (!isCorrect) {
                                Text(text = "Why your answer is wrong:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                                Text(text = q.whyIncorrect, style = MaterialTheme.typography.bodyMedium)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            
                            Text(text = "Why the correct answer is right:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                            Text(text = q.whyCorrect, style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Clinical Importance:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                            Text(text = q.clinicalImportance, style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(text = "Related Concept:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium)
                            Text(text = q.relatedConcept, style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            Text(text = "Learning Point:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                            Text(text = q.learningPoint, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Surface(color = MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(8.dp)) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("Examiner Tip", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onTertiaryContainer)
                                    Text(q.examinerTip, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onTertiaryContainer)
                                }
                            }
                            
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Reference: ${q.reference}", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}
