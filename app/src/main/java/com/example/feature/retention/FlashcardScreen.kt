package com.example.feature.retention

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.ReviewCard
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardScreen(
    onBack: () -> Unit,
    viewModel: RetentionViewModel = viewModel()
) {
    val allCards by viewModel.allCards.collectAsState()
    val dueCards by viewModel.dueCards.collectAsState()
    val score by viewModel.retentionScore.collectAsState()
    val streakObj by viewModel.streakObj.collectAsState()

    var activeSubjectFilter by remember { mutableStateOf<String?>(null) }
    var reviewQueue by remember { mutableStateOf<List<ReviewCard>>(emptyList()) }
    var inSession by remember { mutableStateOf(false) }
    var activeTabIndex by remember { mutableStateOf(0) } // 0: Practice Decks, 1: Retention Analytics

    // Navigation and header setup
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "RETENTION SYSTEM V1",
                            style = MaterialTheme.typography.labelSmall,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = if (inSession) "Spaced Revision Arena" else if (activeTabIndex == 1) "Retention Analytics" else "PhysioPilot Decks",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (inSession) {
                                inSession = false
                            } else {
                                onBack()
                            }
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (inSession) {
                ReviewSessionContent(
                    queue = reviewQueue,
                    onSessionEnd = { correctCount, totalCount, elapsedMs ->
                        viewModel.logSession(totalCount, correctCount, elapsedMs)
                        inSession = false
                    },
                    onSubmitQuality = { cardId, quality ->
                        viewModel.submitReview(cardId, quality)
                    }
                )
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Segmented M3 Tab Selector
                    TabRow(
                        selectedTabIndex = activeTabIndex,
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.primary
                    ) {
                        Tab(
                            selected = activeTabIndex == 0,
                            onClick = { activeTabIndex = 0 },
                            text = { Text("Practice Decks", fontWeight = FontWeight.Bold) },
                            icon = { Icon(Icons.Default.Style, contentDescription = null) },
                            modifier = Modifier.testTag("practice_decks_tab")
                        )
                        Tab(
                            selected = activeTabIndex == 1,
                            onClick = { activeTabIndex = 1 },
                            text = { Text("Readiness Analytics", fontWeight = FontWeight.Bold) },
                            icon = { Icon(Icons.Default.Analytics, contentDescription = null) },
                            modifier = Modifier.testTag("retention_analytics_tab")
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        if (activeTabIndex == 0) {
                            DashboardContent(
                                allCards = allCards,
                                dueCards = dueCards,
                                retentionScore = score,
                                streakDays = streakObj?.currentStreak ?: 0,
                                activeFilter = activeSubjectFilter,
                                onFilterSelect = { activeSubjectFilter = it },
                                onStartSession = { selectedCards ->
                                    reviewQueue = selectedCards.shuffled()
                                    inSession = true
                                },
                                onResetData = { viewModel.resetAndSeed() }
                            )
                        } else {
                            RetentionAnalyticsDashboard(
                                viewModel = viewModel,
                                onNavigateToSubject = { subj ->
                                    activeSubjectFilter = subj
                                    activeTabIndex = 0
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardContent(
    allCards: List<ReviewCard>,
    dueCards: List<ReviewCard>,
    retentionScore: Int,
    streakDays: Int,
    activeFilter: String?,
    onFilterSelect: (String?) -> Unit,
    onStartSession: (List<ReviewCard>) -> Unit,
    onResetData: () -> Unit
) {
    val subjects = listOf("Anatomy", "Physiology", "Electrotherapy", "Exercise Therapy", "Pharmacology", "Biomechanics", "Clinical Cases", "Nerve Lesions", "Gait Analysis")
    
    // Filtering cards
    val filteredCards = if (activeFilter == null) allCards else allCards.filter { it.subject.equals(activeFilter, true) }
    val filteredDue = if (activeFilter == null) dueCards else dueCards.filter { it.subject.equals(activeFilter, true) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Stats Banner
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "LONG-TERM MEMORY RETENTION",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Daily Mind Audit",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                        // Streak Display badge
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xFFFFF0EA), RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Streak",
                                tint = Color(0xFFFA541C),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$streakDays Days",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFD4380D)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Retention Score Arc/Text
                        Column(
                            modifier = Modifier
                                .weight(1.2f)
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$retentionScore%",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.testTag("retention_score_value")
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Retention Score",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Due cards summary
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${dueCards.size}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (dueCards.isNotEmpty()) Color(0xFFFF4D4F) else MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Due for Review",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Spaced interval ratio progress bar
                    val totalSize = allCards.size.toFloat().coerceAtLeast(1f)
                    val masteredRatio = allCards.count { it.state == "MASTERED" } / totalSize
                    val reviewingRatio = allCards.count { it.state == "REVIEWING" } / totalSize
                    val learningRatio = allCards.count { it.state == "LEARNING" } / totalSize
                    val newRatio = allCards.count { it.state == "NEW" } / totalSize

                    Column {
                        Text(
                            text = "Deck Memory Progression Map",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        // Multi-color stacked progression bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                        ) {
                            if (allCards.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                )
                            } else {
                                if (masteredRatio > 0) Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(masteredRatio)
                                        .background(Color(0xFF52C41A))
                                )
                                if (reviewingRatio > 0) Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(reviewingRatio)
                                        .background(Color(0xFF1890FF))
                                )
                                if (learningRatio > 0) Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(learningRatio)
                                        .background(Color(0xFFFAAD14))
                                )
                                if (newRatio > 0) Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(newRatio)
                                        .background(Color(0xFFD9D9D9))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            LegendItem(label = "Mastered", color = Color(0xFF52C41A), count = allCards.count { it.state == "MASTERED" })
                            LegendItem(label = "Review", color = Color(0xFF1890FF), count = allCards.count { it.state == "REVIEWING" })
                            LegendItem(label = "Learning", color = Color(0xFFFAAD14), count = allCards.count { it.state == "LEARNING" })
                            LegendItem(label = "New", color = Color(0xFFD9D9D9), count = allCards.count { it.state == "NEW" })
                        }
                    }
                }
            }
        }

        // Action block: Study Now button
        item {
            Button(
                onClick = {
                    onStartSession(if (activeFilter == null) dueCards else filteredDue)
                },
                enabled = filteredDue.isNotEmpty(),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("start_quick_session_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                if (filteredDue.isNotEmpty()) {
                    Text(
                        text = "Review Due Cards Now (${filteredDue.size})",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                } else {
                    Text(
                        text = "Everything Retained! (0 Cards Due)",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Subject selector filter decks
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "CURRICULUM DECK VOLUMES",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                    if (activeFilter != null) {
                        Text(
                            text = "Reset Filter",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { onFilterSelect(null) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Subject card list
                subjects.forEach { subject ->
                    val totalSub = allCards.count { it.subject.equals(subject, true) }
                    val dueSub = dueCards.count { it.subject.equals(subject, true) }
                    val isSelected = activeFilter.equals(subject, true)

                    SubjectDeckItem(
                        subject = subject,
                        totalCount = totalSub,
                        dueCount = dueSub,
                        isSelected = isSelected,
                        onItemClick = {
                            onFilterSelect(if (isSelected) null else subject)
                        },
                        onPracticeDeck = {
                            val subjectCards = allCards.filter { it.subject.equals(subject, true) }
                            if (subjectCards.isNotEmpty()) {
                                onStartSession(subjectCards)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // Reset option
        item {
            OutlinedButton(
                onClick = onResetData,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.4f)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Re-generate Flashcards & Dynamic Seeds")
            }
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color, count: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$label ($count)",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp
        )
    }
}

@Composable
fun SubjectDeckItem(
    subject: String,
    totalCount: Int,
    dueCount: Int,
    isSelected: Boolean,
    onItemClick: () -> Unit,
    onPracticeDeck: () -> Unit
) {
    val containerColor = when (subject) {
        "Anatomy" -> Color(0xFFF9F0FF)
        "Physiology" -> Color(0xFFFFF0F6)
        "Electrotherapy" -> Color(0xFFE6FFFB)
        "Exercise Therapy" -> Color(0xFFFFF7E6)
        "Pharmacology" -> Color(0xFFF6FFED)
        "Clinical Cases" -> Color(0xFFFFF2E8)
        "Nerve Lesions" -> Color(0xFFF9F0FF)
        "Gait Analysis" -> Color(0xFFFCFFE6)
        else -> Color(0xFFE6F7FF)
    }

    val themeColor = when (subject) {
        "Anatomy" -> Color(0xFF9254DE)
        "Physiology" -> Color(0xFFF47590)
        "Electrotherapy" -> Color(0xFF13C2C2)
        "Exercise Therapy" -> Color(0xFFFA8C16)
        "Pharmacology" -> Color(0xFF52C41A)
        "Clinical Cases" -> Color(0xFFFA541C)
        "Nerve Lesions" -> Color(0xFF722ED1)
        "Gait Analysis" -> Color(0xFFA0D911)
        else -> Color(0xFF1890FF)
    }

    val icon = when (subject) {
        "Anatomy" -> Icons.Default.AccessibilityNew
        "Physiology" -> Icons.Default.Favorite
        "Electrotherapy" -> Icons.Default.FlashOn
        "Exercise Therapy" -> Icons.Default.DirectionsRun
        "Pharmacology" -> Icons.Default.LocalPharmacy
        "Clinical Cases" -> Icons.Default.Healing
        "Nerve Lesions" -> Icons.Default.Bolt
        "Gait Analysis" -> Icons.Default.DirectionsWalk
        else -> Icons.Default.BarChart
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) containerColor else MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) themeColor else MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(containerColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = themeColor)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = subject.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = themeColor
                    )
                    Text(
                        text = "$totalCount Flashcards Available",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (dueCount > 0) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFECEF), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "$dueCount Due",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF5222D)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }

                if (totalCount > 0) {
                    IconButton(
                        onClick = onPracticeDeck,
                        modifier = Modifier
                            .size(36.dp)
                            .background(themeColor.copy(alpha = 0.1f), CircleShape)
                    ) {
                        Icon(
                            Icons.Default.MenuBook,
                            contentDescription = "Test All",
                            tint = themeColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewSessionContent(
    queue: List<ReviewCard>,
    onSessionEnd: (correct: Int, total: Int, durationMs: Long) -> Unit,
    onSubmitQuality: (cardId: String, quality: String) -> Unit
) {
    if (queue.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Icon(Icons.Default.DoneAll, contentDescription = null, tint = Color(0xFF52C41A), modifier = Modifier.size(64.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text("Amazing Catch Up!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("No due flashcards for revision in this selection. Run a Practice Run by pressing Chapter Icons!", textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { onSessionEnd(0, 0, 0) }) {
                    Text("Go Back")
                }
            }
        }
        return
    }

    val startTime = remember { System.currentTimeMillis() }
    var currentIndex by remember { mutableIntStateOf(0) }
    var correctCount by remember { mutableIntStateOf(0) }
    var totalReviewedCount by remember { mutableIntStateOf(0) }

    // Card Flipping State
    var cardFlipped by remember { mutableStateOf(false) }

    val currentCard = queue.getOrNull(currentIndex)

    if (currentCard == null) {
        // Complete state
        val totalMs = System.currentTimeMillis() - startTime
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        ImageVector.vectorResource(id = android.R.drawable.btn_star_big_on), // fallback star
                        contentDescription = null,
                        tint = Color(0xFFFFC069),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Session Complete!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Your retention statistics have been saved to secure offline storage.",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ScoreDetail(label = "Reviewed", value = "$totalReviewedCount")
                        ScoreDetail(label = "Acquisition Rate", value = "${if (totalReviewedCount > 0) (correctCount * 100 / totalReviewedCount) else 0}%")
                        ScoreDetail(label = "Time Spent", value = "${(totalMs / 1000) / 60}m ${(totalMs / 1000) % 60}s")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { onSessionEnd(correctCount, totalReviewedCount, totalMs) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Finish Audit & Return")
                    }
                }
            }
        }
        return
    }

    val themeColor = when (currentCard.subject) {
        "Anatomy" -> Color(0xFF9254DE)
        "Physiology" -> Color(0xFFF47590)
        "Electrotherapy" -> Color(0xFF13C2C2)
        "Exercise Therapy" -> Color(0xFFFA8C16)
        "Pharmacology" -> Color(0xFF52C41A)
        "Clinical Cases" -> Color(0xFFFA541C)
        "Nerve Lesions" -> Color(0xFF722ED1)
        "Gait Analysis" -> Color(0xFFA0D911)
        else -> Color(0xFF1890FF)
    }

    val rotationY by animateFloatAsState(
        targetValue = if (cardFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Queue Progress Indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Card ${currentIndex + 1} of ${queue.size}",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Text(
                text = currentCard.subject.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = themeColor
            )
        }

        LinearProgressIndicator(
            progress = { (currentIndex.toFloat() / queue.size.toFloat()) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            color = themeColor,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Flip Card Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .graphicsLayer {
                    this.rotationY = rotationY
                    cameraDistance = 12f * density
                }
                .clickable { cardFlipped = !cardFlipped }
        ) {
            // Check orientation to render content
            if (rotationY <= 90f) {
                // FRONT SIDE OF CARD
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(2.dp, themeColor.copy(alpha = 0.3f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                            .verticalScroll(androidx.compose.foundation.rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = themeColor.copy(alpha = 0.1f),
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text(
                                text = currentCard.category.uppercase(),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = themeColor,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = currentCard.frontText,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(Icons.Default.Cached, contentDescription = null, tint = themeColor, modifier = Modifier.size(16.dp))
                            Text(
                                text = "Tap Card to Flip & Reveal",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                // BACK SIDE OF CARD (Rotated)
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { this.rotationY = 180f }, // negate rotation so elements render straight
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(2.dp, themeColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = themeColor.copy(alpha = 0.1f),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "EXAMINATION MEMORY TRUTH",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = themeColor,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                item {
                                    Text(
                                        text = currentCard.backText,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 15.sp,
                                        lineHeight = 22.sp,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Divider(color = MaterialTheme.colorScheme.outlineVariant)
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Tap to view Front question again",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Action Buttons Row (Animated visibility based on Flip)
        AnimatedVisibility(
            visible = cardFlipped,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Hard Button
                Button(
                    onClick = {
                        onSubmitQuality(currentCard.cardId, "HARD")
                        cardFlipped = false
                        totalReviewedCount++
                        currentIndex++
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("quality_hard_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF1F0)),
                    border = BorderStroke(1.dp, Color(0xFFFFA39E)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Forgot", color = Color(0xFFCF1322), fontWeight = FontWeight.Bold)
                }

                // Good Button
                Button(
                    onClick = {
                        onSubmitQuality(currentCard.cardId, "GOOD")
                        cardFlipped = false
                        correctCount++
                        totalReviewedCount++
                        currentIndex++
                    },
                    modifier = Modifier
                        .weight(1.2f)
                        .height(52.dp)
                        .testTag("quality_good_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Retained", color = Color.White, fontWeight = FontWeight.Bold)
                }

                // Easy Button
                Button(
                    onClick = {
                        onSubmitQuality(currentCard.cardId, "EASY")
                        cardFlipped = false
                        correctCount++
                        totalReviewedCount++
                        currentIndex++
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("quality_easy_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6FFED)),
                    border = BorderStroke(1.dp, Color(0xFFB7EB8F)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Mastered", color = Color(0xFF389E0D), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ScoreDetail(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
    }
}

// Custom vector resource loader helper if needed - fallback used above
@Composable
fun ImageVector.Companion.vectorResource(id: Int): ImageVector {
    return Icons.Default.Star
}

@Composable
fun RetentionAnalyticsDashboard(
    viewModel: RetentionViewModel,
    onNavigateToSubject: (String) -> Unit
) {
    val overallRetention by viewModel.overallRetentionScore.collectAsState()
    val subjectMasteryList by viewModel.subjectMasteryList.collectAsState()
    val weakTopics by viewModel.weakTopics.collectAsState()
    val cardBreakdown by viewModel.cardBreakdown.collectAsState()
    val trendLast7Days by viewModel.trendLast7Days.collectAsState()
    val trendLast30Days by viewModel.trendLast30Days.collectAsState()
    val activeDaysThisMonth by viewModel.activeDaysThisMonth.collectAsState()
    val examReadiness by viewModel.examReadinessScore.collectAsState()
    val recommendations by viewModel.recommendations.collectAsState()
    val streakObj by viewModel.streakObj.collectAsState()

    var trendDaysRange by remember { mutableStateOf(7) } // 7 or 30

    val currentTrendData = if (trendDaysRange == 7) trendLast7Days else trendLast30Days

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("retention_analytics_dashboard_scrollable"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Hero Section & Overall Retention Dial
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "KNOWLEDGE RETENTION METRICS",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        // Circular Dial Canvas
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(120.dp)
                        ) {
                            val primaryColor = MaterialTheme.colorScheme.primary
                            val surfaceColor = MaterialTheme.colorScheme.surfaceVariant
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                drawArc(
                                    color = surfaceColor,
                                    startAngle = 135f,
                                    sweepAngle = 270f,
                                    useCenter = false,
                                    style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                                )
                                drawArc(
                                    color = primaryColor,
                                    startAngle = 135f,
                                    sweepAngle = 270f * (overallRetention / 100f),
                                    useCenter = false,
                                    style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$overallRetention%",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.testTag("overall_retention_score_text")
                                )
                                Text(
                                    text = "Retention",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Score explanation formula
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.weight(1f).padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Retention Formula",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "(Mastered + Reviewing) / Total Cards",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f), modifier = Modifier.padding(vertical = 4.dp))
                            Text(
                                text = "Evaluates your active medical long-term recall. Mastered and Review states represent solid sensory pathways.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Exam Readiness Speedometer & Contributing Factors
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "EXAM READINESS FACTOR",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFA541C),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1.2f)) {
                            Text(
                                text = "University Readiness",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Scientific estimation of performance based on recall pathways, deck coverage, and active study consistency.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(96.dp).padding(start = 8.dp)
                        ) {
                            val activeColor = when {
                                examReadiness >= 75 -> Color(0xFF52C41A)
                                examReadiness >= 50 -> Color(0xFF1890FF)
                                else -> Color(0xFFFA8C16)
                            }
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                drawArc(
                                    color = Color(0xFFE8E8E8),
                                    startAngle = 135f,
                                    sweepAngle = 270f,
                                    useCenter = false,
                                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                                )
                                drawArc(
                                    color = activeColor,
                                    startAngle = 135f,
                                    sweepAngle = 270f * (examReadiness / 100f),
                                    useCenter = false,
                                    style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                                )
                            }
                            Text(
                                text = "$examReadiness%",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = activeColor,
                                modifier = Modifier.testTag("exam_readiness_score_text")
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Readiness Analytics Checklist",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Readiness Factors List
                    val totalCards = cardBreakdown.totalCount.toFloat().coerceAtLeast(1f)
                    val coveragePercent = ((cardBreakdown.totalCount - cardBreakdown.newCount) / totalCards * 100).toInt()
                    val masteredPercent = (cardBreakdown.masteredCount / totalCards * 100).toInt()

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ReadinessFactorIndicator("Recall (40%)", "$overallRetention%", overallRetention >= 65, modifier = Modifier.weight(1f))
                        ReadinessFactorIndicator("Coverage (20%)", "$coveragePercent%", coveragePercent >= 40, modifier = Modifier.weight(1f))
                        ReadinessFactorIndicator("Mastery (20%)", "$masteredPercent%", masteredPercent >= 30, modifier = Modifier.weight(1f))
                        ReadinessFactorIndicator("Streak (20%)", "${streakObj?.currentStreak ?: 0}d", (streakObj?.currentStreak ?: 0) >= 3, modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        // Today's Focus Action Recommendation Column (Dynamic Recommendation Engine)
        item {
            Column {
                Text(
                    text = "REVISION RECOMMENDATION ENGINE",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                recommendations.take(3).forEach { rec ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = when (rec.severity) {
                                RecommendationSeverity.HIGH -> Color(0xFFFFF1F0)
                                RecommendationSeverity.MEDIUM -> Color(0xFFE6F7FF)
                                RecommendationSeverity.LOW -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                            }
                        ),
                        border = BorderStroke(
                            1.dp,
                            when (rec.severity) {
                                RecommendationSeverity.HIGH -> Color(0xFFFFA39E)
                                RecommendationSeverity.MEDIUM -> Color(0xFF91D5FF)
                                RecommendationSeverity.LOW -> MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                            }
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val tintColor = when (rec.severity) {
                                    RecommendationSeverity.HIGH -> Color(0xFFF5222D)
                                    RecommendationSeverity.MEDIUM -> Color(0xFF1890FF)
                                    RecommendationSeverity.LOW -> MaterialTheme.colorScheme.primary
                                }
                                val icon = when (rec.severity) {
                                    RecommendationSeverity.HIGH -> Icons.Default.PriorityHigh
                                    RecommendationSeverity.MEDIUM -> Icons.Default.Campaign
                                    RecommendationSeverity.LOW -> Icons.Default.Lightbulb
                                }
                                Icon(icon, contentDescription = null, tint = tintColor)

                                Text(
                                    text = rec.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = tintColor,
                                    modifier = Modifier.weight(1f)
                                )

                                if (rec.dueCount > 0) {
                                    Badge(
                                        containerColor = tintColor,
                                        contentColor = Color.White
                                    ) {
                                        Text("${rec.dueCount} Due", modifier = Modifier.padding(horizontal = 4.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = rec.reason,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { onNavigateToSubject(rec.subject) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = when (rec.severity) {
                                        RecommendationSeverity.HIGH -> Color(0xFFF5222D)
                                        RecommendationSeverity.MEDIUM -> Color(0xFF1890FF)
                                        RecommendationSeverity.LOW -> MaterialTheme.colorScheme.primary
                                    },
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(38.dp)
                            ) {
                                Text(
                                    text = rec.actionLabel,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Daily Revision Trend Custom Chart
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "SPACED REVISION TRENDS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "Repetition Volumes",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Toggle Buttons for Last 7 or 30 Days trend
                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(2.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(7, 30).forEach { days ->
                                val active = trendDaysRange == days
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (active) MaterialTheme.colorScheme.primary else Color.Transparent)
                                        .clickable { trendDaysRange = days }
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "${days}D",
                                        color = if (active) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    if (currentTrendData.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No daily revision history found. Keep studying!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        val barColor = MaterialTheme.colorScheme.primary
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .testTag("revenue_trend_canvas")
                        ) {
                            val totalWidth = size.width
                            val totalHeight = size.height
                            val paddingX = 20.dp.toPx()
                            val paddingY = 24.dp.toPx()
                            val chartWidth = totalWidth - (paddingX * 2)
                            val chartHeight = totalHeight - (paddingY * 2)

                            val maxCount = currentTrendData.maxOf { it.count }.toFloat().coerceAtLeast(10f)
                            val pointsCount = currentTrendData.size
                            val barWidth = (chartWidth / pointsCount) * 0.55f
                            val gap = (chartWidth / pointsCount) * 0.45f

                            currentTrendData.forEachIndexed { index, dp ->
                                val x = paddingX + (index * (barWidth + gap)) + (gap / 2)
                                val barHeight = (dp.count / maxCount) * chartHeight
                                val y = totalHeight - paddingY - barHeight

                                // Draw subtle background container shadow for the bar
                                drawRoundRect(
                                    color = barColor.copy(alpha = 0.05f),
                                    topLeft = Offset(x, paddingY),
                                    size = Size(barWidth, chartHeight),
                                    cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
                                )

                                // Draw active bar height
                                if (dp.count > 0) {
                                    drawRoundRect(
                                        color = barColor,
                                        topLeft = Offset(x, y),
                                        size = Size(barWidth, barHeight),
                                        cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
                                    )
                                }
                            }
                        }

                        // Drawing X-Axis Labels row underneath
                        val labelFreq = if (trendDaysRange == 30) 6 else 1
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, start = 8.dp, end = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            currentTrendData.forEachIndexed { idx, dp ->
                                if (idx % labelFreq == 0) {
                                    Text(
                                        text = dp.dateLabel,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Card Stage Due Breakdown Summary
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "DECK PROGRESS STAGING",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BreakdownSegmentCard("NEW PILLS", "${cardBreakdown.newCount}", "D9D9D9", "Unexplored cards", modifier = Modifier.weight(1f))
                        BreakdownSegmentCard("LEARNING", "${cardBreakdown.learningCount}", "FAAD14", "Struggled cards", modifier = Modifier.weight(1f))
                        BreakdownSegmentCard("REVIEWING", "${cardBreakdown.reviewingCount}", "1890FF", "Spaced intervals", modifier = Modifier.weight(1f))
                        BreakdownSegmentCard("MASTERED", "${cardBreakdown.masteredCount}", "52C41A", "Target locked", modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        // Subject Mastery Linear Charts
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "SUBJECT LONG TERM MASTERY %",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        subjectMasteryList.forEach { m ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onNavigateToSubject(m.subject) }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = m.subject,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${m.masteryPercentage}% (${m.masteredCount + m.reviewingCount}/${m.totalCount} cards)",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                LinearProgressIndicator(
                                    progress = (m.masteryPercentage / 100f).coerceIn(0f, 1f),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp)),
                                    color = when {
                                        m.masteryPercentage >= 75 -> Color(0xFF52C41A)
                                        m.masteryPercentage >= 45 -> Color(0xFF1890FF)
                                        else -> Color(0xFFFAAD14)
                                    },
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Study Streak metrics
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "STUDY STREAK ANALYTICS",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.LocalFireDepartment, contentDescription = null, tint = Color(0xFFD4380D), modifier = Modifier.size(36.dp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${streakObj?.currentStreak ?: 0} Days",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFFD4380D)
                            )
                            Text(
                                text = "Current Streak",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        VerticalDivider(modifier = Modifier.height(40.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color(0xFFFFD700), modifier = Modifier.size(36.dp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${streakObj?.bestStreak ?: 0} Days",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF0050B3)
                            )
                            Text(
                                text = "All-time Best",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        VerticalDivider(modifier = Modifier.height(40.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                            Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(36.dp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${activeDaysThisMonth.size}",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = "Active Days (Month)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (activeDaysThisMonth.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "My Active History Calendar Dates",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(activeDaysThisMonth) { dateStr ->
                                val label = try {
                                    val fullFmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                                    val dateObj = fullFmt.parse(dateStr)
                                    val shortFmt = SimpleDateFormat("MMM dd", Locale.US)
                                    if (dateObj != null) shortFmt.format(dateObj) else dateStr
                                } catch (e: Exception) {
                                    dateStr
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFF6FFED), RoundedCornerShape(8.dp))
                                        .border(1.dp, Color(0xFFB7EB8F), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFF389E0D),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Weak Topic Detection Grid / Cards List
        item {
            Column {
                Text(
                    text = "WEAK CLINICAL AREAS DETECTED",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (weakTopics.isEmpty()) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6FFED)),
                        border = BorderStroke(1.dp, Color(0xFFB7EB8F))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF389E0D), modifier = Modifier.size(24.dp))
                            Column {
                                Text(
                                    text = "Solid Knowledge Retention!",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF135200)
                                )
                                Text(
                                    text = "No clinical topics have a Forgot Rate over 40%. All pathway retention spans are high. Continue standard practice!",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF135200)
                                )
                            }
                        }
                    }
                } else {
                    weakTopics.forEach { weak ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBE6)),
                            border = BorderStroke(1.dp, Color(0xFFFFE58F))
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(Color(0xFFFFF2E8), CircleShape)
                                ) {
                                    Icon(Icons.Default.Dangerous, contentDescription = null, tint = Color(0xFFFA541C))
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${weak.subject}: ${weak.topicName}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF873800)
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "${weak.learningCount} cards currently in active learning phase of this category.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF873800)
                                    )
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "${weak.forgotRate}%",
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color(0xFFF5222D)
                                    )
                                    Text(
                                        text = "Forgot Rate",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFF873800)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReadinessFactorIndicator(
    label: String,
    value: String,
    isGood: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isGood) Color(0xFFF6FFED) else Color(0xFFFFFBE6)
        ),
        border = BorderStroke(1.dp, if (isGood) Color(0xFFB7EB8F) else Color(0xFFFFE58F)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = if (isGood) Color(0xFF135200) else Color(0xFF873800),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = if (isGood) Color(0xFF389E0D) else Color(0xFFD46B08)
            )
        }
    }
}

@Composable
fun BreakdownSegmentCard(
    label: String,
    value: String,
    colorHex: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    val containerBg = Color(android.graphics.Color.parseColor("#$colorHex")).copy(alpha = 0.12f)
    val borderStrokeColor = Color(android.graphics.Color.parseColor("#$colorHex"))
    
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerBg),
        border = BorderStroke(1.dp, borderStrokeColor.copy(alpha = 0.4f)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = borderStrokeColor,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = borderStrokeColor
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                fontSize = 8.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: androidx.compose.ui.unit.Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
) {
    Box(
        modifier
            .fillMaxHeight()
            .width(thickness)
            .background(color = color)
    )
}

@Composable
fun RowScope.Divider(
    modifier: Modifier = Modifier,
    thickness: androidx.compose.ui.unit.Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(thickness)
            .background(color = color)
    )
}
