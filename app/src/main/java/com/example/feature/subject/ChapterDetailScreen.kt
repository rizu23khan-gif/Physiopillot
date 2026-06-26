package com.example.feature.subject

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.example.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterDetailScreen(
    navController: NavController,
    subjectId: String,
    chapterId: String
) {
    val context = LocalContext.current
    val subject = remember(subjectId) { SubjectChapterRepository.getSubjectById(subjectId) }
    val chapter = remember(subject, chapterId) {
        subject?.chapters?.find { it.chapterId == chapterId } 
            ?: subject?.modules?.flatMap { it.chapters }?.find { it.chapterId == chapterId }
    }

    var isLoading by remember { mutableStateOf(true) }
    var chapterContent by remember { mutableStateOf<InteractiveChapterContent?>(null) }

    LaunchedEffect(chapterId) {
        isLoading = true
        chapterContent = ChapterContentRepository.loadChapterContentSuspended(context, chapterId)
        isLoading = false
    }

    if (subject == null || chapter == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Not Found",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Chapter or Subject not found.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
        return
    }

    val themeColor = when (subject.year) {
        "1st Year" -> Color(0xFF6366F1)
        "2nd Year" -> Color(0xFFD97706)
        "3rd Year" -> Color(0xFFDC2626)
        "4th Year" -> Color(0xFF9333EA)
        else -> Color(0xFF14B8A6)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = chapter.title,
                            fontWeight = FontWeight.ExtraBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "${subject.title} • Chapter ${chapter.index}",
                            style = MaterialTheme.typography.labelSmall,
                            color = themeColor,
                            fontWeight = FontWeight.Bold
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
        },
        bottomBar = {
            if (!isLoading && chapterContent != null) {
                ChapterBottomNavigation(
                    subject = subject,
                    currentChapterId = chapterId,
                    themeColor = themeColor,
                    navController = navController
                )
            }
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = themeColor)
            }
        } else if (chapterContent != null) {
            RichChapterContent(
                paddingValues = paddingValues,
                content = chapterContent!!,
                themeColor = themeColor,
                navController = navController,
                subject = subject
            )
        } else {
            // Graceful Error for Missing / Malformed JSON
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = "Content Missing",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Content Unavailable",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "The educational content for this chapter is missing or being updated.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RichChapterContent(
    paddingValues: PaddingValues,
    content: InteractiveChapterContent,
    themeColor: Color,
    navController: NavController,
    subject: SubjectModel
) {
    val isAnatomy = subject.subjectId == "anatomy" || content.subject.equals("Anatomy", ignoreCase = true)
    val isModality = subject.subjectId in listOf("electrotherapy_i", "electrotherapy_ii", "exercise_therapy_i", "exercise_therapy_ii")
    val physiologyTitle = if (isModality) "Physiology" else "Concepts"
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = if (isAnatomy) listOf("Theory", "Concepts", "Clinical App", "Exam Prep") else listOf("Theory", physiologyTitle, "Clinical App", "Exam Prep")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.background)
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = themeColor,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                    color = themeColor
                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        IndexedChapterPane(
            tabIndex = selectedTab,
            tabTitles = tabTitles,
            content = content,
            themeColor = themeColor,
            navController = navController,
            subject = subject,
            isAnatomy = isAnatomy
        )
    }
}

@Composable
fun IndexedChapterPane(
    tabIndex: Int,
    tabTitles: List<String>,
    content: InteractiveChapterContent,
    themeColor: Color,
    navController: NavController,
    subject: SubjectModel,
    isAnatomy: Boolean
) {
    val isExerciseTherapy = subject.subjectId.contains("exercise_therapy", ignoreCase = true)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val currentTab = tabTitles.getOrNull(tabIndex)
        when (currentTab) {
            "Theory" -> TheoryTabContent(content = content, themeColor = themeColor, isAnatomy = isAnatomy, isExerciseTherapy = isExerciseTherapy)
            "Physiology", "Concepts" -> PhysiologyTabContent(content = content, themeColor = themeColor, subjectId = subject.subjectId, isExerciseTherapy = isExerciseTherapy)
            "Clinical App" -> ClinicalTabContent(content = content, themeColor = themeColor, isAnatomy = isAnatomy, isExerciseTherapy = isExerciseTherapy)
            "Exam Prep" -> ExamPrepTabContent(content = content, themeColor = themeColor, navController = navController, isExerciseTherapy = isExerciseTherapy)
        }
    }
}

// ==================== DEVELOPMENT MESSAGE ====================
@Composable
fun DevelopmentMessageCard(message: String, themeColor: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, themeColor.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "Under Development",
                tint = themeColor
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// ==================== TAB 0: THEORY ====================
@Composable
fun TheoryTabContent(content: InteractiveChapterContent, themeColor: Color, isAnatomy: Boolean = false, isExerciseTherapy: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        if (isExerciseTherapy && content.definition.isEmpty()) {
            DevelopmentMessageCard("Content under development", themeColor)
            return@Column
        }

        // 1. Definition Card
        if (content.definition.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, themeColor.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(themeColor.copy(alpha = 0.12f), CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = "Definition",
                                tint = themeColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = if (isAnatomy) "DEFINITION & OVERVIEW" else "DEFINITION",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = themeColor
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    content.definition.forEach { paragraph ->
                        Row(
                            modifier = Modifier.padding(bottom = 10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "✦",
                                color = themeColor,
                                modifier = Modifier.padding(end = 8.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = paragraph,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // 2. Working Principle Card
        if (content.principle.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ElectricBolt,
                                contentDescription = "Biophysics Principle",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = if (isAnatomy) "IMPORTANT STRUCTURES" else "CORE CONCEPTS",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    content.principle.forEachIndexed { idx, step ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.05f))
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(themeColor.copy(alpha = 0.1f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = (idx + 1).toString(),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = themeColor
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = step,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. Required Equipment Checklist
        if (content.equipment.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFE2E8F0), CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Handyman,
                                contentDescription = "Equipment",
                                tint = Color(0xFF475569),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = if (isAnatomy) "SYSTEMS & COMPONENTS" else "EQUIPMENT & TOOLS",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF475569)
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    
                    // Represent as stylish checklist grid
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        content.equipment.chunked(2).forEach { rowItems ->
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                rowItems.forEach { item ->
                                    Row(
                                        modifier = Modifier
                                            .weight(1f)
                                            .background(
                                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .border(
                                                1.dp,
                                                MaterialTheme.colorScheme.outline.copy(alpha = 0.05f),
                                                RoundedCornerShape(8.dp)
                                            )
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Ready",
                                            tint = themeColor,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = item,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ==================== TAB 1: PHYSIOLOGY & PARAMETERS ====================
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun getSectionTitles(subjectId: String): Map<String, String> {
    return when (subjectId) {
        "pathology" -> mapOf(
            "parameters" to "KEY CONCEPTS",
            "effects" to "DISEASE MECHANISM",
            "indications" to "CLINICAL MANIFESTATIONS",
            "contraindications" to "RED FLAGS & SAFETY"
        )
        "pharmacology" -> mapOf(
            "parameters" to "KEY DRUG CONCEPTS",
            "effects" to "HOW THE DRUG WORKS",
            "indications" to "WHEN IT IS USED",
            "contraindications" to "WHEN TO AVOID"
        )
        "microbiology" -> mapOf(
            "parameters" to "KEY CONCEPTS",
            "effects" to "INFECTION MECHANISM",
            "indications" to "CLINICAL RELEVANCE",
            "contraindications" to "SAFETY PRECAUTIONS"
        )
        "psychology" -> mapOf(
            "parameters" to "CORE CONCEPTS",
            "effects" to "BEHAVIOUR MECHANISM",
            "indications" to "CLINICAL IMPORTANCE",
            "contraindications" to "COMMUNICATION PITFALLS"
        )
        else -> mapOf(
            "parameters" to "THERAPEUTIC PARAMETERS",
            "effects" to "PHYSIOLOGICAL EFFECTS",
            "indications" to "CLINICAL INDICATIONS",
            "contraindications" to "CONTRAINDICATIONS & SAFETY"
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PhysiologyTabContent(content: InteractiveChapterContent, themeColor: Color, subjectId: String, isExerciseTherapy: Boolean = false) {
    var selectedLevel by remember { mutableStateOf(1) } // 1: Exam Def, 2: Simple Analogy, 3: Clinical Reasoning
    val (level1, level2, level3) = remember(content.chapterId) {
        ClinicalEducationEngine.getThreeLevelExplanation(content.chapterId, content.chapterName, content.subject, content.definition)
    }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        if (isExerciseTherapy) {
            DevelopmentMessageCard("Content under development", themeColor)
            return@Column
        }

        // 1. 3-Level Concept Explainer
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, themeColor.copy(alpha = 0.25f)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(themeColor.copy(alpha = 0.12f), CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = "Three Level Learning",
                            tint = themeColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "3-LEVEL CONCEPT EXPLAINER",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = themeColor
                        )
                        Text(
                            text = "Standardized BPT Curriculum Mastery",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Tabs / Chips Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf(
                        1 to "Level 1: Exam Def",
                        2 to "Level 2: Simple Analogy",
                        3 to "Level 3: Clinical Practice"
                    ).forEach { (level, title) ->
                        val isSelected = selectedLevel == level
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    if (isSelected) themeColor else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    RoundedCornerShape(8.dp)
                                )
                                .border(
                                    1.dp,
                                    if (isSelected) themeColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.12f),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { selectedLevel = level }
                                .padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Explainer content box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .background(
                            themeColor.copy(alpha = 0.04f),
                            RoundedCornerShape(12.dp)
                        )
                        .border(
                            1.dp,
                            themeColor.copy(alpha = 0.08f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(14.dp)
                ) {
                    val explanationText = when (selectedLevel) {
                        1 -> level1
                        2 -> level2
                        else -> level3
                    }
                    Text(
                        text = explanationText,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // 2. Subject-Specific Concept Cards
        val cards = remember(content.chapterId, subjectId) {
            ClinicalEducationEngine.getSubjectLayout(content.chapterId, subjectId, content)
        }
        
        cards.forEach { card ->
            if (card.content.isNotEmpty()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .background(themeColor.copy(alpha = 0.1f), CircleShape)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = card.icon,
                                    contentDescription = card.title,
                                    tint = themeColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = card.title.uppercase(),
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        card.content.forEach { line ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "•",
                                    color = themeColor,
                                    modifier = Modifier.padding(end = 8.dp),
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = line,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. Memory Zone & Exam Pearls Card
        val mz = remember(content.chapterId) {
            ClinicalEducationEngine.getMemoryZone(content.chapterId, content.chapterName)
        }
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFAF5FF)), // Soft purple theme for Memory Zone
            border = BorderStroke(1.dp, Color(0xFFE9D5FF)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFF3E8FF), CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = "Memory Zone",
                            tint = Color(0xFF9333EA),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "MEMORY ZONE & HIGH-YIELD PEARLS",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF7E22CE)
                        )
                        Text(
                            text = "Clinical Mnemonics & Exam Revision",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF9333EA)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Mnemonic Box
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .border(1.dp, Color(0xFFF3E8FF), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.VpnKey,
                            contentDescription = "Mnemonic",
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ACTIVE MNEMONIC",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD97706)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = mz.mnemonic,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Frequently Confused Concepts
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFEF2F2), RoundedCornerShape(10.dp))
                        .border(1.dp, Color(0xFFFEE2E2), RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Common Confusion",
                            tint = Color(0xFFDC2626),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "STUDENT CONFUSION CLEARER",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFDC2626)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = mz.frequentlyConfused.first.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFB91C1C)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = mz.frequentlyConfused.second,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                // High-Yield Viva Points
                Text(
                    text = "HIGH-YIELD VIVA CHEAT SHEET",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF7E22CE),
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                mz.vivaPoints.forEach { point ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.QuestionAnswer,
                            contentDescription = "Viva Question",
                            tint = Color(0xFF9333EA),
                            modifier = Modifier.size(14.dp).padding(top = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = point,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Exam Pearls
                Text(
                    text = "EXAM GOLDEN PEARLS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF7E22CE),
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                mz.examPearls.forEach { pearl ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Exam Pearl",
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = pearl,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

// ==================== TAB 2: CLINICAL APPLICATION ====================
@Composable
fun ClinicalTabContent(content: InteractiveChapterContent, themeColor: Color, isAnatomy: Boolean = false, isExerciseTherapy: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        
        val hasTechniqueData = content.technique.patientPreparation.isNotEmpty() ||
                content.technique.apparatusPreparation.isNotEmpty() ||
                content.technique.skinPreparation.isNotEmpty() ||
                content.technique.electrodePlacement.isNotEmpty() ||
                content.technique.treatmentProcedure.isNotEmpty()

        val hasClinical = hasTechniqueData || content.clinicalPearls.isNotEmpty() || content.precautions.isNotEmpty() || content.indications.isNotEmpty() || content.contraindications.general.isNotEmpty() || content.contraindications.local.isNotEmpty() || content.clinicalProtocols.isNotEmpty()
        
        if (isExerciseTherapy && !hasClinical) {
            DevelopmentMessageCard("Clinical content under development.", themeColor)
            return@Column
        }

        if (!isAnatomy && hasTechniqueData) {
            // Expandable application technique
            var expandedPrepStep by remember { mutableStateOf(0) } // 0-indexed
            
            val lowerSubject = content.subject.lowercase()
            
            val sectionTitle = when {
                lowerSubject.contains("electrotherapy") || lowerSubject.contains("physical agents") || lowerSubject.contains("device") -> "GUIDED TREATMENT STEPS"
                lowerSubject.contains("exercise") || lowerSubject.contains("biomechanics") || lowerSubject.contains("kinesiology") || lowerSubject.contains("rehabilitation") -> "GUIDED CLINICAL TECHNIQUES"
                lowerSubject.contains("microbiology") || lowerSubject.contains("pathology") || lowerSubject.contains("infection") || lowerSubject.contains("pharmacology") -> "PROCEDURE & SANITATION PROTOCOLS"
                lowerSubject.contains("anatomy") || lowerSubject.contains("physiology") -> "LANDMARKING & ASSESSMENT STEPS"
                else -> "GUIDED CLINICAL STEPS"
            }

            val labels = when {
                lowerSubject.contains("electrotherapy") || lowerSubject.contains("physical agents") || lowerSubject.contains("device") -> {
                    listOf(
                        "Patient Preparation",
                        "Apparatus Preparation",
                        "Skin Preparation",
                        "Electrode Placement",
                        "Treatment Procedure"
                    )
                }
                lowerSubject.contains("exercise") || lowerSubject.contains("biomechanics") || lowerSubject.contains("kinesiology") || lowerSubject.contains("rehabilitation") -> {
                    listOf(
                        "Patient Positioning & Safety",
                        "Equipment & Area Setup",
                        "Pre-Assessment & Warm-up",
                        "Stance & Stabilization Setup",
                        "Exercise Execution & Monitoring"
                    )
                }
                lowerSubject.contains("microbiology") || lowerSubject.contains("pathology") || lowerSubject.contains("infection") || lowerSubject.contains("pharmacology") -> {
                    listOf(
                        "Patient & Area Screening",
                        "Sterilization & Material Prep",
                        "Barrier / Hand Hygiene Protocol",
                        "Specimen / Modality Placement",
                        "Procedure & Processing Steps"
                    )
                }
                lowerSubject.contains("anatomy") || lowerSubject.contains("physiology") -> {
                    listOf(
                        "Subject Orientation",
                        "Measurement Tools Prep",
                        "Anatomical Landmark Prep",
                        "Palpation / Sensor Placement",
                        "Assessment / Testing Protocol"
                    )
                }
                else -> {
                    listOf(
                        "Patient / Client Setup",
                        "Equipment / Utility Prep",
                        "Pre-Treatment Prep",
                        "Interface / Contact Setup",
                        "Standard Procedure Steps"
                    )
                }
            }

            val techniqueCategories = listOf(
                labels.getOrElse(0) { "Patient Preparation" } to content.technique.patientPreparation,
                labels.getOrElse(1) { "Apparatus Preparation" } to content.technique.apparatusPreparation,
                labels.getOrElse(2) { "Skin Preparation" } to content.technique.skinPreparation,
                labels.getOrElse(3) { "Electrode Placement" } to content.technique.electrodePlacement,
                labels.getOrElse(4) { "Treatment Procedure" } to content.technique.treatmentProcedure
            ).filter { it.second.isNotEmpty() }

            if (techniqueCategories.isNotEmpty()) {
                Text(
                    text = sectionTitle,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 4.dp)
                )

                techniqueCategories.forEachIndexed { index, (title, steps) ->
                    val isExpanded = expandedPrepStep == index
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expandedPrepStep = index },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isExpanded) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        ),
                        border = BorderStroke(1.dp, if (isExpanded) themeColor.copy(alpha = 0.3f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.05f)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(26.dp)
                                            .background(if (isExpanded) themeColor else themeColor.copy(alpha = 0.15f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = (index + 1).toString(),
                                            color = if (isExpanded) Color.White else themeColor,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = "Expand",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            AnimatedVisibility(
                                visible = isExpanded,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically()
                            ) {
                                Column(modifier = Modifier.padding(top = 12.dp, start = 38.dp)) {
                                    steps.forEach { step ->
                                        Row(
                                            modifier = Modifier.padding(bottom = 6.dp),
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Text("•", color = themeColor, modifier = Modifier.padding(end = 8.dp))
                                            Text(
                                                text = step,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                lineHeight = 18.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } // Close if (!isAnatomy)

        // Clinical Pearls (Amber Highlight)
        if (content.clinicalPearls.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFBEB)),
                border = BorderStroke(1.dp, Color(0xFFFDE68A)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFEF3C7), CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "Clinical Pearls",
                                tint = Color(0xFFD97706),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "CLINICAL PEARLS & TIPS",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFB45309)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    content.clinicalPearls.forEach { pearl ->
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Pearl",
                                tint = Color(0xFFF59E0B),
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = pearl,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF78350F),
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }

        if (content.precautions.isNotEmpty()) {
            // Precautions Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.errorContainer, CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = "Precautions",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "PRECAUTIONS DURING TREATMENT",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    content.precautions.forEach { precaution ->
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "⚠️",
                                fontSize = 13.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = precaution,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }

        if (content.clinicalProtocols.isNotEmpty()) {
            // Clinical Protocols (Actual condition parameters mapping)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, themeColor.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(themeColor.copy(alpha = 0.12f), CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MedicalServices,
                                contentDescription = "Clinical Protocols",
                                tint = themeColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "CLINICAL SETUP PROTOCOLS",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = themeColor
                        )
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    content.clinicalProtocols.forEachIndexed { idx, proto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "CONDITION: ${proto.condition.uppercase()}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                if (proto.position.isNotEmpty()) {
                                    Text(
                                        text = "⚡ Position: ${proto.position}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                if (proto.electrodePlacement.isNotEmpty()) {
                                    Text(
                                        text = "🎯 Electrodes: ${proto.electrodePlacement}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Text(
                                    text = "📈 Beat Frequency: ${proto.frequency}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = themeColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "🎯 Purpose: ${proto.purpose}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ==================== TAB 3: EXAM PREP ====================
@Composable
fun ExamPrepTabContent(
    content: InteractiveChapterContent,
    themeColor: Color,
    navController: NavController,
    isExerciseTherapy: Boolean = false
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        
        // 1. Interactive MCQ Segment
        InteractiveMCQCard(questions = content.mcqs, themeColor = themeColor)

        // 2. Oral Viva Q&A Guide
        if (content.vivaQuestions.isEmpty() && isExerciseTherapy) {
            DevelopmentMessageCard("Viva content under development.", themeColor)
        } else {
            OralVivaCard(vivaQuestions = content.vivaQuestions, themeColor = themeColor)
        }

        // 3. Recommended Readings References block
        if (content.reference.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "ACADEMIC BIBLIOGRAPHY",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    content.reference.forEach { ref ->
                        Text(
                            text = "• $ref",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        } else if (isExerciseTherapy) {
            DevelopmentMessageCard("References will be added in a future update.", themeColor)
        }
    }
}

@Composable
fun InteractiveMCQCard(
    questions: List<ChapterMCQ>,
    themeColor: Color
) {
    if (questions.isEmpty()) return

    var currentIdx by remember { mutableStateOf(0) }
    var selectedOpt by remember { mutableStateOf(-1) }
    var answerChecked by remember { mutableStateOf(false) }
    var correctCount by remember { mutableStateOf(0) }
    var quizCompleted by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFF3B82F6).copy(alpha = 0.2f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFEFF6FF), CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Quiz,
                            contentDescription = "Quiz",
                            tint = Color(0xFF3B82F6),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "MCQ DIAGNOSTIC",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D4ED8)
                    )
                }
                
                if (!quizCompleted) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFEFF6FF), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${currentIdx + 1}/${questions.size}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1D4ED8)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (quizCompleted) {
                // Congratulations block
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🎉 Quiz Completed!",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF047857),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Your Diagnostic Score: $correctCount / ${questions.size}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { correctCount.toFloat() / questions.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = Color(0xFF10B981),
                        trackColor = Color(0xFFE2E8F0)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            currentIdx = 0
                            selectedOpt = -1
                            answerChecked = false
                            correctCount = 0
                            quizCompleted = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B82F6))
                    ) {
                        Text("Replay Diagnostics", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            } else {
                val currentQ = questions[currentIdx]
                
                Text(
                    text = currentQ.question,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Options
                currentQ.options.forEachIndexed { optIdx, opt ->
                    val isSelected = selectedOpt == optIdx
                    val isCorrectText = opt.trim() == currentQ.answer.trim()
                    
                    val bgCol = when {
                        answerChecked && isCorrectText -> Color(0xFFD1FAE5) // Green for correct
                        answerChecked && isSelected -> Color(0xFFFEE2E2)  // Red for ticked wrong
                        isSelected -> Color(0xFFEFF6FF)                    // Selected pending check
                        else -> MaterialTheme.colorScheme.surface
                    }

                    val borderCol = when {
                        answerChecked && isCorrectText -> Color(0xFF10B981)
                        answerChecked && isSelected -> Color(0xFFEF4444)
                        isSelected -> Color(0xFF3B82F6)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable(enabled = !answerChecked) {
                                selectedOpt = optIdx
                            },
                        colors = CardDefaults.cardColors(containerColor = bgCol),
                        border = BorderStroke(1.dp, borderCol),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        if (isSelected) Color(0xFF3B82F6) else Color(0xFFF1F5F9),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ('A'.code + optIdx).toChar().toString(),
                                    color = if (isSelected) Color.White else Color(0xFF475569),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = opt,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Actions row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (!answerChecked) {
                        Button(
                            onClick = {
                                if (selectedOpt != -1) {
                                    val pickedText = currentQ.options[selectedOpt]
                                    if (pickedText.trim() == currentQ.answer.trim()) {
                                        correctCount++
                                    }
                                    answerChecked = true
                                }
                            },
                            enabled = selectedOpt != -1,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))
                        ) {
                            Text("Check Answer", color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = {
                                if (currentIdx < questions.size - 1) {
                                    currentIdx++
                                    selectedOpt = -1
                                    answerChecked = false
                                } else {
                                    quizCompleted = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D4ED8))
                        ) {
                            Text(
                                text = if (currentIdx < questions.size - 1) "Next Question" else "Show Diagnostic Card",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OralVivaCard(
    vivaQuestions: List<String>,
    themeColor: Color
) {
    if (vivaQuestions.isEmpty()) return

    var activeQuestions = remember { mutableStateListOf<Int>() }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFD97706).copy(alpha = 0.2f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFEF3C7), CircleShape)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.RecordVoiceOver,
                        contentDescription = "Viva Exam Questions",
                        tint = Color(0xFFD97706),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "WBUHS VIVA PREPARATION GUIDE",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB45309)
                )
            }
            
            Spacer(modifier = Modifier.height(14.dp))
            
            Text(
                text = "Flip to check common viva and board queries with syllabus tips.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 2.dp, bottom = 12.dp)
            )

            vivaQuestions.forEachIndexed { index, question ->
                val isRevealed = activeQuestions.contains(index)
                
                val parts = question.split(" | ")
                var parsedQuestion = question
                var parsedAnswer = ""
                var parsedExaminer = ""
                var parsedClinical = ""
                
                if (parts.size == 1 && !question.startsWith("Question:")) {
                    parsedQuestion = question
                } else {
                    parts.forEach { part ->
                        val item = part.trim()
                        when {
                            item.startsWith("Question:") -> parsedQuestion = item.removePrefix("Question:").trim()
                            item.startsWith("Answer:") -> parsedAnswer = item.removePrefix("Answer:").trim()
                            item.startsWith("Examiner Key Points:") -> parsedExaminer = item.removePrefix("Examiner Key Points:").trim()
                            item.startsWith("Clinical Importance:") -> parsedClinical = item.removePrefix("Clinical Importance:").trim()
                        }
                    }
                }
                
                if (index == 0) {
                    android.util.Log.d("VIVA_TEST", "RAW VIVA: $question")
                    android.util.Log.d("VIVA_TEST", "PARSED Q: $parsedQuestion")
                    android.util.Log.d("VIVA_TEST", "PARSED A: $parsedAnswer")
                    android.util.Log.d("VIVA_TEST", "PARSED E: $parsedExaminer")
                    android.util.Log.d("VIVA_TEST", "PARSED C: $parsedClinical")
                }
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .clickable {
                            if (isRevealed) {
                                activeQuestions.remove(index)
                            } else {
                                activeQuestions.add(index)
                            }
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = if (isRevealed) Color(0xFFFFFBEB) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.22f)
                    ),
                    border = BorderStroke(1.dp, if (isRevealed) Color(0xFFFDE68A) else MaterialTheme.colorScheme.outline.copy(alpha = 0.05f)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Top) {
                                Text(
                                    text = "Q${index + 1}: ",
                                    color = Color(0xFFB45309),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = parsedQuestion,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isRevealed) Color(0xFF451A03) else MaterialTheme.colorScheme.onSurface,
                                    lineHeight = 20.sp
                                )
                            }
                            Icon(
                                imageVector = if (isRevealed) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = "Reveal answer",
                                tint = Color(0xFFD97706),
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 2.dp)
                            )
                        }

                        if (isRevealed) {
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(color = Color(0xFFFDE68A))
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            if (parsedAnswer.isNotEmpty()) {
                                Text(
                                    text = "Answer:",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFD97706),
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                                Text(
                                    text = parsedAnswer,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF78350F),
                                    lineHeight = 18.sp,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                                
                                if (parsedExaminer.isNotEmpty()) {
                                    Text(
                                        text = "Examiner Key Points:",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFD97706),
                                        modifier = Modifier.padding(bottom = 2.dp)
                                    )
                                    Text(
                                        text = parsedExaminer,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF78350F),
                                        lineHeight = 18.sp,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }
                                
                                if (parsedClinical.isNotEmpty()) {
                                    Text(
                                        text = "Clinical Importance:",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFD97706),
                                        modifier = Modifier.padding(bottom = 2.dp)
                                    )
                                    Text(
                                        text = parsedClinical,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color(0xFF78350F),
                                        lineHeight = 18.sp
                                    )
                                }
                            } else {
                                Text(
                                    text = "Answer not available.",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    color = Color(0xFF78350F),
                                    lineHeight = 18.sp,
                                    modifier = Modifier.padding(top = 3.dp, start = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChapterBottomNavigation(
    subject: SubjectModel,
    currentChapterId: String,
    themeColor: Color,
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isMarkedComplete by remember { mutableStateOf(false) }

    val allChapters = remember(subject) {
        if (subject.chapters.isNotEmpty()) subject.chapters else subject.modules.flatMap { it.chapters }
    }
    
    val currentIndex = remember(allChapters, currentChapterId) {
        allChapters.indexOfFirst { it.chapterId == currentChapterId }
    }
    
    val previousChapter = if (currentIndex > 0) allChapters[currentIndex - 1] else null
    val nextChapter = if (currentIndex in 0 until allChapters.lastIndex) allChapters[currentIndex + 1] else null

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Previous Chapter
            IconButton(
                onClick = {
                    previousChapter?.let {
                        navController.navigate("chapter/${subject.subjectId}/${it.chapterId}") {
                            popUpTo("chapter/${subject.subjectId}/$currentChapterId") { inclusive = true }
                        }
                    }
                },
                enabled = previousChapter != null
            ) {
                Icon(
                    imageVector = Icons.Default.NavigateBefore,
                    contentDescription = "Previous Chapter",
                    tint = if (previousChapter != null) themeColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            }

            // Mark Complete
            Button(
                onClick = {
                    isMarkedComplete = !isMarkedComplete
                    scope.launch(kotlinx.coroutines.Dispatchers.IO) {
                        com.example.data.ProgressManager.toggleTopicCompletion(
                            context = context,
                            topicId = currentChapterId,
                            isCompleted = isMarkedComplete,
                            type = "chapter",
                            title = allChapters.getOrNull(currentIndex)?.title ?: "Chapter",
                            subject = subject.title,
                            year = subject.year
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isMarkedComplete) Color(0xFF10B981) else themeColor
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = if (isMarkedComplete) Icons.Default.Check else Icons.Default.TaskAlt,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isMarkedComplete) "Completed" else "Mark Complete",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            // Next Chapter
            IconButton(
                onClick = {
                    nextChapter?.let {
                        navController.navigate("chapter/${subject.subjectId}/${it.chapterId}") {
                            popUpTo("chapter/${subject.subjectId}/$currentChapterId") { inclusive = true }
                        }
                    }
                },
                enabled = nextChapter != null
            ) {
                Icon(
                    imageVector = Icons.Default.NavigateNext,
                    contentDescription = "Next Chapter",
                    tint = if (nextChapter != null) themeColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            }
        }
    }
}
