package com.example.feature.curriculum

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CurriculumScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val coroutineScope = rememberCoroutineScope()
    
    // Database states
    val curriculumProgress by database.physioDao().getAllCurriculumProgress().collectAsState(initial = emptyList())
    val recentActivities by database.physioDao().getRecentStudyActivities().collectAsState(initial = emptyList())
    val streakObj by database.physioDao().getStreakFlow().collectAsState(initial = null)

    // Master lists from data
    val anatomiesDb by database.physioDao().getAllAnatomies().collectAsState(initial = emptyList())
    val currentAnatomies = if (anatomiesDb.isNotEmpty()) anatomiesDb else ContentRepo.initialAnatomies

    val assessmentsDb by database.physioDao().getAllAssessments().collectAsState(initial = emptyList())
    val currentAssessments = if (assessmentsDb.isNotEmpty()) assessmentsDb else DataLoader.rawDiagnoses

    val topicsDb by database.physioDao().getAllVivaTopics().collectAsState(initial = emptyList())
    val currentVivaTopics = if (topicsDb.isNotEmpty()) topicsDb else VivaPrepRepo.initialTopics

    val postingsDb by database.physioDao().getAllPostings().collectAsState(initial = emptyList())
    val currentPostings = if (postingsDb.isNotEmpty()) postingsDb else ClinicalPostingSurvivalRepo.initialModules

    // Curriculum classification logic
    // We dynamically tally and compute statistics
    val completedSet = remember(curriculumProgress) {
        curriculumProgress.filter { it.isCompleted }.map { it.topicId }.toSet()
    }

    // Calculate dynamic counts and subjects from the authoritative CurriculumRepository
    val subjectItems = remember(
        currentAnatomies,
        currentVivaTopics,
        currentAssessments,
        currentPostings,
        completedSet
    ) {
        CurriculumRepository.allSubjects.map { subMeta ->
            val prog = CurriculumRepository.calculateSubjectProgress(
                subMeta.name,
                currentAnatomies,
                currentVivaTopics,
                currentAssessments,
                currentPostings,
                completedSet
            )
            SubjectBlueprintItem(
                title = subMeta.name,
                summary = subMeta.description,
                yearTag = subMeta.yearGroup,
                completedCount = prog.first,
                totalCount = prog.second,
                icon = subMeta.icon,
                color = subMeta.accentColor,
                routeString = subMeta.routeId
            )
        }
    }

    // Filters for Year Tab Selection
    var selectedYearTab by remember { mutableStateOf("All Years") }
    val yearTabsList = listOf("All Years", "1st Year", "2nd Year", "3rd Year", "4th Year", "Internship")

    // Subject progress list based on selection
    val filteredSubjects = remember(selectedYearTab, subjectItems) {
        if (selectedYearTab == "All Years") {
            subjectItems
        } else {
            subjectItems.filter { it.yearTag.equals(selectedYearTab, ignoreCase = true) }
        }
    }

    // Dynamic overall accomplishments
    val totalOverall = remember(subjectItems) { subjectItems.sumOf { it.totalCount } }
    val completedOverall = remember(subjectItems) { subjectItems.sumOf { it.completedCount } }
    val progressOverall = if (totalOverall > 0) completedOverall.toFloat() / totalOverall.toFloat() else 0f

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Curriculum Progress", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                        Text("Course Blueprint & Achievements", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
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
                ),
                actions = {
                    IconButton(onClick = {
                        // Resets database-driven curriculum checkpoints for diagnostic testing
                        coroutineScope.launch {
                            com.example.data.AppDatabase.getDatabase(context).clearAllTables()
                        }
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reset Progress", tint = MaterialTheme.colorScheme.error.copy(alpha = 0.6f))
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            
            // 1. Overall Progress Header Card
            item {
                CurriculumProgressHeaderCard(
                    completed = completedOverall,
                    total = totalOverall,
                    percent = progressOverall
                )
            }

            // 2. Daily Study Streak Card
            item {
                StreakDisplayCard(streakInputObj = streakObj)
            }

            // 3. Recently Studied Shelf
            item {
                Column {
                    Text(
                        text = "RECENTLY STUDIED TOPICS",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    if (recentActivities.isEmpty()) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp, horizontal = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = "No studies logged",
                                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Your studied subjects shelf is empty.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(recentActivities) { activity ->
                                RecentActivityChip(
                                    activity = activity,
                                    onClick = {
                                        when (activity.type) {
                                            "ANATOMY" -> navController.navigate("anatomy")
                                            "ASSESSMENT" -> navController.navigate("assessment")
                                            "VIVA" -> navController.navigate("viva")
                                            "POSTING" -> navController.navigate("posting")
                                            else -> {}
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // Academic Year Tab Selector
            item {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "UNIVERSITY COURSE SUBJECTS",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    ScrollableTabRow(
                        selectedTabIndex = yearTabsList.indexOf(selectedYearTab),
                        edgePadding = 0.dp,
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.primary,
                        indicator = { tabPositions ->
                            val currentTabIdx = yearTabsList.indexOf(selectedYearTab)
                            if (currentTabIdx >= 0 && currentTabIdx < tabPositions.size) {
                                TabRowDefaults.SecondaryIndicator(
                                    modifier = Modifier.tabIndicatorOffset(tabPositions[currentTabIdx]),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        yearTabsList.forEach { tab ->
                            val isSelected = selectedYearTab == tab
                            Tab(
                                selected = isSelected,
                                onClick = { selectedYearTab = tab },
                                text = {
                                    Text(
                                        text = tab,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            )
                        }
                    }
                }
            }

            items(filteredSubjects) { subject ->
                SubjectBlueprintCard(
                    item = subject,
                    onClick = {
                        val subId = SubjectChapterRepository.getSubjectIdFromTitle(subject.title)
                        navController.navigate("subject_detail/$subId")
                    }
                )
            }
        }
    }
}

data class SubjectBlueprintItem(
    val title: String,
    val summary: String,
    val yearTag: String,
    val completedCount: Int,
    val totalCount: Int,
    val icon: ImageVector,
    val color: Color,
    val routeString: String
)

@Composable
fun CurriculumProgressHeaderCard(completed: Int, total: Int, percent: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "OVERALL BLUEPRINT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Curriculum Mastered",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { percent },
                        modifier = Modifier.size(54.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4.5.dp,
                        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    )
                    Text(
                        text = "${(percent * 100).toInt()}%",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            LinearProgressIndicator(
                progress = { percent },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$completed of $total syllabus targets completed",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium
                )
                
                if (percent == 1f) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Verified, contentDescription = "Syllabus Finished", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Graduate Level Ready", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Composable
fun StreakDisplayCard(streakInputObj: UserStreak?) {
    val streakCount = streakInputObj?.currentStreak ?: 0
    val bestStreak = streakInputObj?.bestStreak ?: 0
    val activeToday = streakInputObj != null && streakCount > 0

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(
                        if (activeToday) Color(0xFFFFECE5) else MaterialTheme.colorScheme.surfaceVariant,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = "Streak Fire",
                    tint = if (activeToday) Color(0xFFFA541C) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.45f),
                    modifier = Modifier.size(34.dp)
                )
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${streakCount}-DAY STUDY STREAK",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (activeToday) Color(0xFFE0420F) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (activeToday) "Excellent work! Keep checking off clinical checklists daily!" else "Check off any subject topic or checklist to start your streak!",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Personal Best: $bestStreak days",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun RecentActivityChip(activity: StudyActivity, onClick: () -> Unit) {
    val cardColor = when (activity.type) {
        "ANATOMY" -> Color(0xFFEEF2FF)
        "ASSESSMENT" -> Color(0xFFEFF6FF)
        "VIVA" -> Color(0xFFFFFBEB)
        "POSTING" -> Color(0xFFF0FDFA)
        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
    }
    
    val badgeColor = when (activity.type) {
        "ANATOMY" -> Color(0xFF4338CA)
        "ASSESSMENT" -> Color(0xFF1D4ED8)
        "VIVA" -> Color(0xFFB45309)
        "POSTING" -> Color(0xFF0F766E)
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() }
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(badgeColor.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = activity.type,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = badgeColor
                )
            }
            Text(
                text = activity.title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = activity.subject,
                fontSize = 9.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun SubjectBlueprintCard(item: SubjectBlueprintItem, onClick: () -> Unit) {
    val progressPercent = if (item.totalCount > 0) item.completedCount.toFloat() / item.totalCount.toFloat() else 0f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.14f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(item.color.copy(alpha = 0.12f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "Subject Icon",
                        tint = item.color,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier
                                .background(item.color.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 7.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = item.yearTag.uppercase(),
                                fontSize = 8.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = item.color
                            )
                        }
                    }
                    Text(
                        text = item.summary,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Completion Progress",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
                Text(
                    text = "${item.completedCount} / ${item.totalCount} items (${(progressPercent * 100).toInt()}%)",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = item.color
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { progressPercent },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = item.color,
                trackColor = item.color.copy(alpha = 0.1f)
            )
        }
    }
}
