package com.example.feature.posting

import kotlinx.coroutines.launch
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.ClinicalPostingSurvivalRepo
import com.example.data.SurvivalModule
import com.example.data.SurvivalSoapNote
import com.example.data.SurvivalVivaQuestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicalPostingScreen(navController: NavController) {
    var selectedCategoryIndex by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    val context = LocalContext.current
    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val modulesDb by database.physioDao().getAllPostings().collectAsState(initial = emptyList())
    val modules = if (modulesDb.isNotEmpty()) modulesDb else ClinicalPostingSurvivalRepo.initialModules

    // Map module category styles
    val categoryTheme = mapOf(
        "opd_posting" to Color(0xFFF59E0B),        // Warm Amber
        "ward_posting" to Color(0xFF10B981),       // Emerald Green
        "icu_posting" to Color(0xFF06B6D4),        // Cyan
        "neuro_posting" to Color(0xFF8B5CF6),      // Deep Violet
        "orthopedic_posting" to Color(0xFF3B82F6), // Blue
        "pediatric_posting" to Color(0xFFEC4899),  // Pink
        "community_posting" to Color(0xFF14B8A6)   // Teal
    )

    val currentModule = modules.getOrElse(selectedCategoryIndex) { modules.firstOrNull() ?: ClinicalPostingSurvivalRepo.initialModules.first() }
    val brandColor = categoryTheme[currentModule.id] ?: MaterialTheme.colorScheme.primary

    val coroutineScope = rememberCoroutineScope()
    val curriculumProgress by database.physioDao().getAllCurriculumProgress().collectAsState(initial = emptyList())

    // Track check status for records and professional conduct per module so students can interact!
    val checkedRecordsState = remember { mutableStateMapOf<String, Boolean>() }
    val checkedConductState = remember { mutableStateMapOf<String, Boolean>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Clinical Posting", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Senior Clinical Instructor Survival Guide",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
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
            // Horizontal Scrollable Category Bar for Postings
            ScrollableTabRow(
                selectedTabIndex = selectedCategoryIndex,
                edgePadding = 16.dp,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = brandColor,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedCategoryIndex]),
                        color = brandColor
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                modules.forEachIndexed { index, module ->
                    val isSelected = selectedCategoryIndex == index
                    val activeColor = categoryTheme[module.id] ?: MaterialTheme.colorScheme.primary
                    Tab(
                        selected = isSelected,
                        onClick = { selectedCategoryIndex = index },
                        text = {
                            Text(
                                text = module.title.substringBefore(" ("),
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) activeColor else MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    )
                }
            }

            // Search Filter for Guide
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                placeholder = { Text("Search survival guide (e.g., GMFCS, DVT, SOAP)...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = brandColor) },
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

            // Content Area for current Clinical Module
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 24.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Instructural Welcome Bannercard
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = brandColor.copy(alpha = 0.08f)),
                        border = BorderStroke(1.dp, brandColor.copy(alpha = 0.25f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Instructor Note",
                                    tint = brandColor,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "CLINICAL OFFICER LOGBOOK",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = brandColor,
                                    letterSpacing = 0.5.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = currentModule.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentModule.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 19.sp
                            )
                            
                            val isModuleCompleted = remember(curriculumProgress, currentModule.id) {
                                curriculumProgress.any { it.topicId == currentModule.id && it.isCompleted }
                             }
                             
                             Spacer(modifier = Modifier.height(12.dp))
                             HorizontalDivider(color = brandColor.copy(alpha = 0.15f))
                             Spacer(modifier = Modifier.height(10.dp))
                             Row(
                                 modifier = Modifier.fillMaxWidth(),
                                 horizontalArrangement = Arrangement.SpaceBetween,
                                 verticalAlignment = Alignment.CenterVertically
                             ) {
                                 Text(
                                     text = "Mark shift & posting as mastered",
                                     style = MaterialTheme.typography.bodyMedium,
                                     fontWeight = FontWeight.SemiBold,
                                     color = brandColor
                                 )
                                 Switch(
                                     checked = isModuleCompleted,
                                     onCheckedChange = { checked ->
                                         coroutineScope.launch {
                                             com.example.data.ProgressManager.toggleTopicCompletion(
                                                 context = context,
                                                 topicId = currentModule.id,
                                                 isCompleted = checked,
                                                 type = "POSTING",
                                                 title = currentModule.title.substringBefore(" ("),
                                                 subject = "Clinical Practice & Postings",
                                                 year = "4th Year"
                                             )
                                         }
                                     },
                                     colors = SwitchDefaults.colors(
                                         checkedThumbColor = brandColor,
                                         checkedTrackColor = brandColor.copy(alpha = 0.4f)
                                     )
                                 )
                             }
                        }
                    }
                }

                // 1. What to Observe Section
                val filteredObserve = currentModule.whatToObserve.filter { it.contains(searchQuery, ignoreCase = true) }
                if (filteredObserve.isNotEmpty() || searchQuery.isEmpty()) {
                    item {
                        SurvivalCollapsibleCard(
                            title = "WHAT TO OBSERVE",
                            subtitle = "Visual clinical indicators and ward environment",
                            icon = Icons.Default.Visibility,
                            brandColor = brandColor
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                (if (searchQuery.isEmpty()) currentModule.whatToObserve else filteredObserve).forEach { item ->
                                    ObserveBulletItem(text = item, color = brandColor)
                                }
                            }
                        }
                    }
                }

                // 2. What to Ask Section
                val filteredAsk = currentModule.whatToAsk.filter { it.contains(searchQuery, ignoreCase = true) }
                if (filteredAsk.isNotEmpty() || searchQuery.isEmpty()) {
                    item {
                        SurvivalCollapsibleCard(
                            title = "WHAT TO ASK THE PATIENT",
                            subtitle = "History taking and symptom screening queries",
                            icon = Icons.Default.QuestionAnswer,
                            brandColor = brandColor
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                (if (searchQuery.isEmpty()) currentModule.whatToAsk else filteredAsk).forEach { q ->
                                    AskQuestionItem(text = q, color = brandColor)
                                }
                            }
                        }
                    }
                }

                // 3. What to Record Section
                val filteredRecord = currentModule.whatToRecord.filter { it.contains(searchQuery, ignoreCase = true) }
                if (filteredRecord.isNotEmpty() || searchQuery.isEmpty()) {
                    item {
                        SurvivalCollapsibleCard(
                            title = "WHAT TO RECORD (CHECKLIST)",
                            subtitle = "Mandatory physical findings & parameters",
                            icon = Icons.Default.Edit,
                            brandColor = brandColor
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "Check off as you complete recording of these parameters on your shift today:",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                                (if (searchQuery.isEmpty()) currentModule.whatToRecord else filteredRecord).forEach { item ->
                                    val checkedKey = "${currentModule.id}_record_${item.take(20)}"
                                    val isChecked = checkedRecordsState[checkedKey] ?: false
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable { checkedRecordsState[checkedKey] = !isChecked }
                                            .padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = isChecked,
                                            onCheckedChange = { checkedRecordsState[checkedKey] = it },
                                            colors = CheckboxDefaults.colors(checkedColor = brandColor)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = item,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 4. Assessment Sequence Timeline Section
                item {
                    SurvivalCollapsibleCard(
                        title = "ASSESSMENT SEQUENCE",
                        subtitle = "Logical bedside clinical workflow order",
                        icon = Icons.Default.FormatListNumbered,
                        brandColor = brandColor
                    ) {
                        Column {
                            currentModule.assessmentSequence.forEachIndexed { idx, seq ->
                                TimelineSequenceRow(
                                    stepNumber = idx + 1,
                                    text = seq,
                                    isLast = idx == currentModule.assessmentSequence.lastIndex,
                                    color = brandColor
                                )
                            }
                        }
                    }
                }

                // 5. Case Presentation Format Section
                item {
                    SurvivalCollapsibleCard(
                        title = "CASE PRESENTATION FORMAT",
                        subtitle = "Syllabus standard presentation structure",
                        icon = Icons.Default.AssignmentInd,
                        brandColor = brandColor
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        ) {
                            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    text = "📇 Case Sheet Outline:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = brandColor
                                )
                                currentModule.casePresentationFormat.forEach { line ->
                                    Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(bottom = 4.dp)) {
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowRight,
                                            contentDescription = null,
                                            tint = brandColor,
                                            modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = line,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 6. SOAP Note Template Section
                item {
                    SurvivalCollapsibleCard(
                        title = "SOAP NOTE TEMPLATE",
                        subtitle = "Fitted progress chart sample template",
                        icon = Icons.Default.Notes,
                        brandColor = brandColor
                    ) {
                        SoapTemplateDisplay(soapNote = currentModule.soapNoteTemplate, brandColor = brandColor)
                    }
                }

                // 7. Professional Conduct Checklist Section
                item {
                    SurvivalCollapsibleCard(
                        title = "PROFESSIONAL CONDUCT CHECKLIST",
                        subtitle = "Ethics, patient draping and bedside manners",
                        icon = Icons.Default.Badge,
                        brandColor = brandColor
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "Tick off these active ethical conducts and safety rules:",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                            currentModule.professionalConduct.forEach { conduct ->
                                val conductKey = "${currentModule.id}_conduct_${conduct.take(20)}"
                                val isChecked = checkedConductState[conductKey] ?: false
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { checkedConductState[conductKey] = !isChecked }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { checkedConductState[conductKey] = it },
                                        colors = CheckboxDefaults.colors(checkedColor = brandColor)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = conduct,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }

                // 8. Clinical Viva Questions Section
                val filteredViva = currentModule.clinicalViva.filter {
                    it.question.contains(searchQuery, ignoreCase = true) ||
                            it.answer.contains(searchQuery, ignoreCase = true)
                }
                if (filteredViva.isNotEmpty() || searchQuery.isEmpty()) {
                    item {
                        SurvivalCollapsibleCard(
                            title = "CLINICAL VIVA SURVIVAL",
                            subtitle = "Tough questions frequently asked by examiners",
                            icon = Icons.Default.School,
                            brandColor = brandColor
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                (if (searchQuery.isEmpty()) currentModule.clinicalViva else filteredViva).forEach { viva ->
                                    VivaInteractiveRow(viva = viva, brandColor = brandColor)
                                }
                            }
                        }
                    }
                }

                // 9. Common Mistakes Section
                item {
                    SurvivalCollapsibleCard(
                        title = "COMMON MISTAKES TO AVOID",
                        subtitle = "Critical critical red-flags and slip-ups",
                        icon = Icons.Default.Warning,
                        brandColor = brandColor
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            currentModule.commonMistakes.forEach { mistake ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFEF4444).copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                                        .border(BorderStroke(0.5.dp, Color(0xFFEF4444).copy(alpha = 0.2f)), RoundedCornerShape(8.dp))
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = "Avoid",
                                        tint = Color(0xFFEF4444),
                                        modifier = Modifier.size(16.dp).padding(top = 1.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = mistake,
                                        style = MaterialTheme.typography.bodyMedium,
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
fun SurvivalCollapsibleCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    brandColor: Color,
    content: @Composable () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = if (expanded) 1.5.dp else 1.dp,
            color = if (expanded) brandColor else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .background(brandColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = brandColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 11.sp
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = brandColor,
                    modifier = Modifier.rotate(rotateState)
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    Box(modifier = Modifier.padding(16.dp)) {
                        content()
                    }
                }
            }
        }
    }
}

@Composable
fun ObserveBulletItem(text: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.bodyLarge,
            color = color,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 6.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun AskQuestionItem(text: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ChatBubbleOutline,
            contentDescription = "Question cue",
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun TimelineSequenceRow(
    stepNumber: Int,
    text: String,
    isLast: Boolean,
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(28.dp)
                        .background(color.copy(alpha = 0.3f))
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 2.dp, bottom = if (isLast) 0.dp else 12.dp)
        )
    }
}

@Composable
fun SoapTemplateDisplay(soapNote: SurvivalSoapNote, brandColor: Color) {
    val states = listOf(
        "S" to Triple("Subjective", soapNote.subjective, Color(0xFF3B82F6)),
        "O" to Triple("Objective", soapNote.objective, Color(0xFF10B981)),
        "A" to Triple("Assessment", soapNote.assessment, Color(0xFFF59E0B)),
        "P" to Triple("Plan", soapNote.plan, Color(0xFF8B5CF6))
    )

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        states.forEach { (char, info) ->
            val (label, text, col) = info
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = col.copy(alpha = 0.05f)),
                border = BorderStroke(0.5.dp, col.copy(alpha = 0.25f))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(col, RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = label.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = col,
                            fontSize = 10.sp,
                            letterSpacing = 0.5.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VivaInteractiveRow(viva: SurvivalVivaQuestion, brandColor: Color) {
    var revealed by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Default.HelpCenter,
                    contentDescription = "Examiner Question",
                    tint = brandColor,
                    modifier = Modifier.size(18.dp).padding(top = 1.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = viva.question,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (!revealed) {
                Button(
                    onClick = { revealed = true },
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = brandColor),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("REVEAL EXAMINER VERDICT", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .background(Color(0xFF10B981).copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                        .border(BorderStroke(0.5.dp, Color(0xFF10B981).copy(alpha = 0.25f)), RoundedCornerShape(6.dp))
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "💡 SEVERAL EXAMINER EXPECTATIONS:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF10B981),
                            fontSize = 8.5.sp
                        )
                        Text(
                            text = "Hide",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = brandColor,
                            modifier = Modifier.clickable { revealed = false }
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = viva.answer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
