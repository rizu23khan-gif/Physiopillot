package com.example.feature.viva

import kotlinx.coroutines.launch
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.ContentRepo
import com.example.data.VivaMcq
import com.example.data.VivaPrepRepo
import com.example.data.VivaTopic

class VivaAnalyticsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("viva_analytics_v2", Context.MODE_PRIVATE)

    var totalAttempted: Int
        get() = prefs.getInt("totalAttempted", 0)
        set(value) { prefs.edit().putInt("totalAttempted", value).apply() }

    var totalCorrect: Int
        get() = prefs.getInt("totalCorrect", 0)
        set(value) { prefs.edit().putInt("totalCorrect", value).apply() }

    var totalIncorrect: Int
        get() = prefs.getInt("totalIncorrect", 0)
        set(value) { prefs.edit().putInt("totalIncorrect", value).apply() }
        
    fun getSubjectAttempted(subject: String): Int = prefs.getInt("attempted_$subject", 0)
    fun setSubjectAttempted(subject: String, value: Int) { prefs.edit().putInt("attempted_$subject", value).apply() }

    fun getSubjectCorrect(subject: String): Int = prefs.getInt("correct_$subject", 0)
    fun setSubjectCorrect(subject: String, value: Int) { prefs.edit().putInt("correct_$subject", value).apply() }

    fun getSubjectIncorrect(subject: String): Int = prefs.getInt("incorrect_$subject", 0)
    fun setSubjectIncorrect(subject: String, value: Int) { prefs.edit().putInt("incorrect_$subject", value).apply() }
    
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
    var activeTab by remember { mutableStateOf(0) } // 0: Oral Q&A Prep (WBUHS), 1: MCQ Practice

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
                title = {
                    Column {
                        Text("Viva Preparation", fontWeight = FontWeight.Bold)
                        Text(
                            text = if (activeTab == 0) "WBUHS Oral Cards Guide" else "Interactive MCQ Diagnostics",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Mode Tab Selection
            TabRow(
                selectedTabIndex = activeTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = activeTab == 0,
                    onClick = { activeTab = 0 },
                    text = { Text("Oral Board Q&A", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium) }
                )
                Tab(
                    selected = activeTab == 1,
                    onClick = { activeTab = 1 },
                    text = { Text("MCQ Practice", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium) }
                )
            }

            if (activeTab == 0) {
                OralVivaGuideContent(navController = navController)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Overall Stats
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = CardDefaults.outlinedCardBorder().copy(
                                brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                                width = 1.5.dp
                            )
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "PERFORMANCE OVERVIEW",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Column {
                                        Text("Attempted: $totalAtt", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                                        Text("Correct: $totalCorr", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                                        Text("Incorrect: $totalInc", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text("Accuracy", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                        Text("$overallAcc%", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                                Spacer(modifier = Modifier.height(12.dp))
                                Text("Strongest: $strongestSubj", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
                                Text("Weakest: $weakestSubj", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
                                Text("Most Practiced: $mostPracSubj", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
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
                            Text("High-Yield Revision Mode", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
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
                        Text("Subjects", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    }

                    items(subjects) { subj ->
                        val att = analytics.getSubjectAttempted(subj)
                        val corr = analytics.getSubjectCorrect(subj)
                        val acc = if (att > 0) (corr.toFloat() / att * 100).toInt() else 0

                        Card(
                            modifier = Modifier.fillMaxWidth().clickable {
                                com.example.data.ContentRepo.lastRevisedViva = subj
                                onStartSubject(subj)
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            border = CardDefaults.outlinedCardBorder().copy(
                                brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                                width = 1.dp
                            )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(subj, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                                    Text("Attempted: $att | Accuracy: $acc%", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                }
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = "Go",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OralVivaGuideContent(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All Categories") }

    val categories = listOf(
        "All Categories",
        "Anatomy",
        "Biomechanics",
        "Exercise Therapy",
        "Electrotherapy",
        "Pathology",
        "Pharmacology",
        "Neurology",
        "Orthopedics",
        "Pediatrics",
        "Women's Health"
    )

    val categoryThemeColor = mapOf(
        "Anatomy" to Color(0xFFEF4444),
        "Biomechanics" to Color(0xFF6366F1),
        "Exercise Therapy" to Color(0xFF14B8A6),
        "Electrotherapy" to Color(0xFF06B6D4),
        "Pathology" to Color(0xFFF59E0B),
        "Pharmacology" to Color(0xFFEC4899),
        "Neurology" to Color(0xFF8B5CF6),
        "Orthopedics" to Color(0xFFF43F5E),
        "Pediatrics" to Color(0xFF10B981),
        "Women's Health" to Color(0xFFEC4899)
    )

    val context = LocalContext.current
    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val topicsDb by database.physioDao().getAllVivaTopics().collectAsState(initial = emptyList())
    val currentTopics = if (topicsDb.isNotEmpty()) topicsDb else VivaPrepRepo.initialTopics

    val filteredTopics = remember(searchQuery, selectedCategory, currentTopics) {
        currentTopics.filter { topic ->
            val matchesCategory = if (selectedCategory == "All Categories") true else topic.category == selectedCategory
            val matchesSearch = topic.title.contains(searchQuery, ignoreCase = true) ||
                    topic.vivaQuestion.contains(searchQuery, ignoreCase = true) ||
                    topic.oneLineAnswer.contains(searchQuery, ignoreCase = true) ||
                    topic.detailedAnswer.contains(searchQuery, ignoreCase = true) ||
                    topic.clinicalCorrelation.contains(searchQuery, ignoreCase = true) ||
                    topic.memoryTricks.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesSearch
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            placeholder = { Text("Search topics, questions, mnemonics...", fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Horizontal Category Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = selectedCategory == category
                val outlineColor = categoryThemeColor[category] ?: MaterialTheme.colorScheme.primary
                val containerColor = if (isSelected) outlineColor else outlineColor.copy(alpha = 0.08f)
                val contentColor = if (isSelected) Color.White else outlineColor

                Card(
                    onClick = { selectedCategory = category },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = containerColor
                    ),
                    border = if (isSelected) null else BorderStroke(1.dp, outlineColor.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = category.uppercase(),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = contentColor,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Topic scroll list
        if (filteredTopics.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "No Results",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(54.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No high-yield topics located.",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Try adjusting your search keywords, or selecting another WBUHS syllabus category above.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header summarizing the count
                item {
                    Text(
                        text = "${filteredTopics.size} HIGH-YIELD TOPICS AVAILABLE",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        letterSpacing = 0.8.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                item {
                    Card(
                        onClick = { navController.navigate("viva_generator") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "LAUNCH VIVA GENERATOR",
                                    fontWeight = FontWeight.Black,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = "Generate randomized oral review cards filtered by Year, Subject, and specific Modules.",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    lineHeight = 15.sp
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Launch",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                items(filteredTopics, key = { it.id }) { topic ->
                    OralVivaTopicCard(topic, categoryThemeColor[topic.category] ?: MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
fun OralVivaTopicCard(topic: VivaTopic, brandColor: Color) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var isAnswerRevealed by rememberSaveable(isExpanded) { mutableStateOf(false) }

    val context = LocalContext.current
    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val coroutineScope = rememberCoroutineScope()

    val curriculumProgress by database.physioDao().getAllCurriculumProgress().collectAsState(initial = emptyList())
    val isCompleted = remember(curriculumProgress, topic.id) {
        curriculumProgress.any { it.topicId == topic.id && it.isCompleted }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = if (isExpanded) 1.5.dp else 1.dp,
            color = if (isExpanded) brandColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    // Badge Category
                    Box(
                        modifier = Modifier
                            .background(brandColor.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = topic.category.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = brandColor,
                            fontSize = 8.5.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = topic.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isCompleted,
                        onCheckedChange = { checked ->
                            coroutineScope.launch {
                                 val sub = when (topic.category) {
                                    "Anatomy" -> "Anatomy"
                                    "Physiology" -> "Physiology"
                                    "Electrotherapy" -> "Electrotherapy"
                                    "Orthopedics" -> "Orthopedic PT & Sports"
                                    "Neurology" -> "Neurological PT & Pediatrics"
                                    "Cardiopulmonary" -> "Cardiopulmonary PT & Geriatrics"
                                    "Women's Health" -> "Community Rehabilitation & Women's Health"
                                    else -> "Orthopedic PT & Sports"
                                }
                                val yr = when (topic.category) {
                                    "Anatomy", "Physiology" -> "1st Year"
                                    "Electrotherapy" -> "2nd Year"
                                    "Orthopedics" -> "3rd Year"
                                    "Neurology", "Cardiopulmonary" -> "4th Year"
                                    "Women's Health" -> "4th Year"
                                    else -> "3rd Year"
                                }
                                com.example.data.ProgressManager.toggleTopicCompletion(
                                    context = context,
                                    topicId = topic.id,
                                    isCompleted = checked,
                                    type = "VIVA",
                                    title = topic.title,
                                    subject = sub,
                                    year = yr
                                )
                            }
                        },
                        colors = CheckboxDefaults.colors(checkedColor = brandColor),
                        modifier = Modifier
                            .size(48.dp)
                            .padding(end = 4.dp)
                    )
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = brandColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // FAQ Question Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brandColor.copy(alpha = 0.05f), RoundedCornerShape(10.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.HelpCenter,
                    contentDescription = "FAQ",
                    tint = brandColor,
                    modifier = Modifier.size(20.dp).padding(top = 1.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "FREQUENT VIVA QUESTION",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = brandColor,
                        fontSize = 8.sp,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = topic.vivaQuestion,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            if (!isExpanded) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Reveal Board Prep Details",
                        fontSize = 11.sp,
                        style = MaterialTheme.typography.labelSmall,
                        color = brandColor,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = brandColor,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(16.dp))

                // One-line response block
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF10B981).copy(alpha = 0.06f), RoundedCornerShape(10.dp))
                        .border(BorderStroke(1.dp, Color(0xFF10B981).copy(alpha = 0.25f)), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "One-line Answer",
                            tint = Color(0xFF10B981),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ONE-LINE OFFICIAL ANSWER (VERDICT)",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF10B981),
                            fontSize = 8.5.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = topic.oneLineAnswer,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Detailed answer block under a "Self-Test Shield"
                Card(
                     modifier = Modifier.fillMaxWidth(),
                     shape = RoundedCornerShape(12.dp),
                     colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)),
                     border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "DETAILED SYLLABUS ANSWER",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 8.5.sp,
                                letterSpacing = 0.5.sp
                            )

                            if (isAnswerRevealed) {
                                Text(
                                    text = "Hide Info",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = brandColor,
                                    modifier = Modifier.clickable { isAnswerRevealed = false }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        if (!isAnswerRevealed) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "💡 Self-Test Active: Pause & think of your core answer first!",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = { isAnswerRevealed = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = brandColor),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("REVEAL FULL ANSWER", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        } else {
                            Text(
                                text = topic.detailedAnswer,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Examiner Favorites
                AcademicBulletSection(
                    title = "EXAMINER FAVORITE INQUIRIES",
                    items = topic.examinerFavorites,
                    icon = Icons.Default.Star,
                    color = Color(0xFFF59E0B),
                    contentColor = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Rapid Revision Points
                AcademicBulletSection(
                    title = "RAPID REVISION POINTS",
                    items = topic.rapidRevision,
                    icon = Icons.Default.ListAlt,
                    color = Color(0xFF14B8A6),
                    contentColor = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Common Mistakes Warning Card
                AcademicBulletSection(
                    title = "COMMON MISTAKES TO AVOID",
                    items = topic.commonMistakes,
                    icon = Icons.Default.Warning,
                    color = Color(0xFFEF4444),
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    isWarning = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Clinical Correlation Case Box
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2563EB).copy(alpha = 0.04f), RoundedCornerShape(10.dp))
                        .border(BorderStroke(0.5.dp, Color(0xFF2563EB).copy(alpha = 0.15f)), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.MedicalServices,
                            contentDescription = "Clinical",
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "CLINICAL MEDICINE CORRELATION",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2563EB),
                            fontSize = 8.5.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = topic.clinicalCorrelation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Mnemonics & Memory Hacks
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFF9800).copy(alpha = 0.05f), RoundedCornerShape(10.dp))
                        .border(BorderStroke(0.5.dp, Color(0xFFFF9800).copy(alpha = 0.2f)), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "Mnemonic",
                            tint = Color(0xFFF59E0B),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "MEMORY TRICK / MNEMONIC",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF9800),
                            fontSize = 8.5.sp,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = topic.memoryTricks,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AcademicBulletSection(
    title: String,
    items: List<String>,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    contentColor: Color,
    isWarning: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isWarning) color.copy(alpha = 0.05f) else color.copy(alpha = 0.03f),
                RoundedCornerShape(10.dp)
            )
            .border(
                BorderStroke(0.5.dp, if (isWarning) color.copy(alpha = 0.25f) else color.copy(alpha = 0.15f)),
                RoundedCornerShape(10.dp)
            )
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = 8.5.sp,
                letterSpacing = 0.5.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        items.forEach { item ->
            Row(
                modifier = Modifier.padding(start = 12.dp, bottom = 4.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "•",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = color,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyMedium,
                    color = contentColor
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VivaQuizScreen(mode: QuizMode, analytics: VivaAnalyticsManager, onBack: () -> Unit) {
    val context = LocalContext.current
    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val mcqsDb by database.physioDao().getAllVivaMcqs().collectAsState(initial = emptyList())
    val currentVivaQuestions = if (mcqsDb.isNotEmpty()) mcqsDb else ContentRepo.initialVivaQuestions

    val questions = remember(mode, currentVivaQuestions) {
        when (mode) {
            is QuizMode.Subject -> currentVivaQuestions.filter { 
                it.subject == mode.subject && (!mode.highYieldOnly || it.difficulty == "High Yield") 
            }
            is QuizMode.MistakeReview -> {
                val mistakes = analytics.getMistakes()
                currentVivaQuestions.filter { mistakes.contains(it.id) }
            }
            is QuizMode.Bookmarks -> {
                val bookmarks = analytics.getBookmarks()
                currentVivaQuestions.filter { bookmarks.contains(it.id) }
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
                        !isAnswered -> MaterialTheme.colorScheme.surface
                        isOptCorrect -> Color(0xFF10B981).copy(alpha = 0.12f)
                        isOptSelected && !isOptCorrect -> Color(0xFFEF4444).copy(alpha = 0.12f)
                        else -> MaterialTheme.colorScheme.surface
                    }
                    
                    val bColor = when {
                        !isAnswered -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                        isOptCorrect -> Color(0xFF10B981)
                        isOptSelected && !isOptCorrect -> Color(0xFFEF4444)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(enabled = !isAnswered) {
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
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, bColor)
                    ) {
                        Text(
                            text = option,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
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
