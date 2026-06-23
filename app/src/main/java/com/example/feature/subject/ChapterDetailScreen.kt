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

    val chapterContent = remember(chapterId) {
        ChapterContentRepository.loadChapterContent(context, chapterId)
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
        }
    ) { paddingValues ->
        if (chapterContent != null) {
            RichChapterContent(
                paddingValues = paddingValues,
                content = chapterContent,
                themeColor = themeColor,
                navController = navController,
                subject = subject
            )
        } else {
            // Standard original placeholder
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 1. Chapter Title Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(2.dp, shape = RoundedCornerShape(18.dp)),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, themeColor.copy(alpha = 0.15f)),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(themeColor.copy(alpha = 0.15f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = chapter.index.toString(),
                                        color = themeColor,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "CHAPTER DETAILS",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = themeColor
                                )
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = chapter.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            if (chapter.description.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = chapter.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }

                // Placeholder warning
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.08f)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                                    .padding(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = "Textbook Reading",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Syllabus Integration Underway",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Detailed learning modules, diagnostic simulations, and illustrated study sheets for '${chapter.title}' are currently being prepared as part of the next master curriculum update.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                lineHeight = 18.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.06f))
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "RECOMMENDED READING FOR THIS TOPIC",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = themeColor
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "📚 Primary: ${subject.primaryTextbook}\n📖 Secondary: ${subject.secondaryTextbook}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 18.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // Practice links
                item {
                    Column {
                        Text(
                            text = "EXAM & PRACTICE SUITE",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(140.dp),
                                onClick = { navController.navigate("viva_generator") },
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(14.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFFFEF3C7), CircleShape)
                                            .padding(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.RecordVoiceOver,
                                            contentDescription = "Viva",
                                            tint = Color(0xFFD97706),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Viva Practice",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(140.dp),
                                onClick = { navController.navigate("viva") },
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(14.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(Color(0xFFEFF6FF), CircleShape)
                                            .padding(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "MCQ",
                                            tint = Color(0xFF2563EB),
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "MCQ Diagnostic",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
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
fun RichChapterContent(
    paddingValues: PaddingValues,
    content: InteractiveChapterContent,
    themeColor: Color,
    navController: NavController,
    subject: SubjectModel
) {
    val isAnatomy = subject.subjectId == "anatomy" || content.subject.equals("Anatomy", ignoreCase = true)
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = if (isAnatomy) listOf("Theory", "Clinical App", "Exam Prep") else listOf("Theory", "Physiology", "Clinical App", "Exam Prep")

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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        val currentTab = tabTitles.getOrNull(tabIndex)
        when (currentTab) {
            "Theory" -> TheoryTabContent(content = content, themeColor = themeColor, isAnatomy = isAnatomy)
            "Physiology" -> PhysiologyTabContent(content = content, themeColor = themeColor)
            "Clinical App" -> ClinicalTabContent(content = content, themeColor = themeColor, isAnatomy = isAnatomy)
            "Exam Prep" -> ExamPrepTabContent(content = content, themeColor = themeColor, navController = navController)
        }
    }
}

// ==================== TAB 0: THEORY ====================
@Composable
fun TheoryTabContent(content: InteractiveChapterContent, themeColor: Color, isAnatomy: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // 1. Definition Card
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
                        text = if (isAnatomy) "DEFINITION & OVERVIEW" else "THERAPEUTIC DEFINITION",
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

        // 2. Working Principle Card
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
                        text = if (isAnatomy) "IMPORTANT STRUCTURES" else "BIOPHYSICS & PRINCIPLE",
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

        // 3. Required Equipment Checklist
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
                        text = if (isAnatomy) "SYSTEMS & COMPONENTS" else "APPARATUS & CLINICAL GEAR",
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

// ==================== TAB 1: PHYSIOLOGY & PARAMETERS ====================
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PhysiologyTabContent(content: InteractiveChapterContent, themeColor: Color) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // 1. Technical Parameters Table
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
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Parameters",
                            tint = themeColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "THERAPEUTIC PARAMETERS",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = themeColor
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                
                content.parameters.forEach { (key, value) ->
                    Column(modifier = Modifier.padding(bottom = 12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = key.replaceFirstChar { it.uppercase() }
                                    .replace(Regex("(?<=[a-z])(?=[A-Z])"), " "),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Box(
                                modifier = Modifier
                                    .background(themeColor.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (value.length > 25) "Details Below" else value,
                                    fontSize = 12.sp,
                                    color = themeColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        if (value.length > 25) {
                            Text(
                                text = value,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.05f))
                    }
                }
            }
        }

        // 2. Biological Physiological Effects Card
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
                            .background(Color(0xFFECFDF5), CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Physiological Effects",
                            tint = Color(0xFF059669),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "PHYSIOLOGICAL EFFECTS",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF059669)
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                
                content.physiologicalEffects.forEach { effectModel ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFF059669).copy(alpha = 0.12f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = effectModel.effect,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Badge(containerColor = Color(0xFF059669)) {
                                    Text(
                                        text = effectModel.frequency,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            effectModel.mechanism.forEach { mech ->
                                Row(
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowRight,
                                        contentDescription = "Mechanism",
                                        tint = Color(0xFF059669),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = mech,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // 3. Clinical Indications Checklist
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, Color(0xFF2563EB).copy(alpha = 0.15f)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFEFF6FF), CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Indications",
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "CLINICAL INDICATIONS",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2563EB)
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                content.indications.forEach { ind ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFFEFF6FF), RoundedCornerShape(8.dp))
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Indicated Condition",
                            tint = Color(0xFF10B981),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = ind,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // 4. Warning & Contraindications Card (High Contrast Caution)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
            border = BorderStroke(1.dp, Color(0xFFFCA5A5)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFEE2E2), CircleShape)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.HealthAndSafety,
                            contentDescription = "Contraindications",
                            tint = Color(0xFFDC2626),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "CONTRAINDICATIONS & SAFETY",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB91C1C)
                    )
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // General Warnings
                Text(
                    text = "GENERAL CONTRAINDICATIONS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF991B1B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, bottom = 6.dp)
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    content.contraindications.general.forEach { item ->
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(6.dp))
                                .border(1.dp, Color(0xFFFEE2E2), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = item,
                                fontSize = 11.sp,
                                color = Color(0xFF991B1B),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Local Warnings
                Text(
                    text = "LOCAL CONTRAINDICATIONS",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF991B1B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp, bottom = 6.dp)
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    content.contraindications.local.forEach { item ->
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFEF2F2), RoundedCornerShape(6.dp))
                                .border(1.dp, Color(0xFFFCA5A5), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = item,
                                fontSize = 11.sp,
                                color = Color(0xFFB91C1C),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

// ==================== TAB 2: CLINICAL APPLICATION ====================
@Composable
fun ClinicalTabContent(content: InteractiveChapterContent, themeColor: Color, isAnatomy: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        
        if (!isAnatomy) {
            // Expandable application technique
            var expandedPrepStep by remember { mutableStateOf(0) } // 0-indexed
            
            Text(
                text = "GUIDED TREATMENT STEPS",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.padding(start = 4.dp)
        )

        val techniqueCategories = listOf(
            "Patient Preparation" to content.technique.patientPreparation,
            "Apparatus Preparation" to content.technique.apparatusPreparation,
            "Skin Preparation" to content.technique.skinPreparation,
            "Electrode Placement" to content.technique.electrodePlacement,
            "Treatment Procedure" to content.technique.treatmentProcedure
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
        } // Close if (!isAnatomy)

        // Clinical Pearls (Amber Highlight)
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
    navController: NavController
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        
        // 1. Interactive MCQ Segment
        InteractiveMCQCard(questions = content.mcqs, themeColor = themeColor)

        // 2. Oral Viva Q&A Guide
        OralVivaCard(vivaQuestions = content.vivaQuestions, themeColor = themeColor)

        // 3. Recommended Readings References block
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
