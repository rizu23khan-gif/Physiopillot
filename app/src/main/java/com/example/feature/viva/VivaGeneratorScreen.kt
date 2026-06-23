package com.example.feature.viva

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Question wrapper for unified presentation
data class GeneratedQuestion(
    val id: String,
    val question: String,
    val answer: String,
    val year: String,
    val subject: String,
    val module: String,
    val source: String, // "Syllabus Q&A", "Anatomy Review", "Clinical Cases", "Ward Postings"
    val tips: List<String> = emptyList(),
    val clinicalNotes: String = "",
    val references: List<String> = emptyList()
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VivaGeneratorScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val dao = database.physioDao()
    val coroutineScope = rememberCoroutineScope()

    // 1st Level Db Flows
    val dbVivaTopics by dao.getAllVivaTopics().collectAsState(initial = emptyList())
    val dbAnatomies by dao.getAllAnatomies().collectAsState(initial = emptyList())
    val dbAssessments by dao.getAllAssessments().collectAsState(initial = emptyList())
    val dbPostings by dao.getAllPostings().collectAsState(initial = emptyList())
    val dbFavorites by dao.getFavorites().collectAsState(initial = emptyList())

    // UI Configuration States
    var selectedYear by remember { mutableStateOf("All Years") }
    var selectedSubject by remember { mutableStateOf("All Subjects") }
    var selectedModule by remember { mutableStateOf("All Modules") }
    var randomizeDeck by remember { mutableStateOf(true) }

    // Active Study State
    var isDeckActive by remember { mutableStateOf(false) }
    var generatedDeck by remember { mutableStateOf<List<GeneratedQuestion>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }
    var revealAnswer by remember { mutableStateOf(false) }

    // Helper to Reset filter states when Year changes
    fun resetOnYearChange() {
        selectedSubject = "All Subjects"
        selectedModule = "All Modules"
    }

    // Helper to Reset module when Subject changes
    fun resetOnSubjectChange() {
        selectedModule = "All Modules"
    }

    // 1. Construct standard pool of all questions dynamically
    val masterPool = remember(dbVivaTopics, dbAnatomies, dbAssessments, dbPostings) {
        val pool = mutableListOf<GeneratedQuestion>()

        // A. Base Q&A Topics (VivaTopic)
        dbVivaTopics.forEach { topic ->
            val mapping = mapCategoryToYearSubject(topic.category)
            val yr = mapping.first
            val subj = mapping.second

            // Primary Question
            pool.add(
                GeneratedQuestion(
                    id = "viva_primary_${topic.id}",
                    question = topic.vivaQuestion,
                    answer = topic.detailedAnswer,
                    year = yr,
                    subject = subj,
                    module = topic.title,
                    source = "Syllabus Q&A",
                    tips = topic.rapidRevision,
                    clinicalNotes = topic.clinicalCorrelation,
                    references = listOf("Examiner favorites: " + topic.examinerFavorites.joinToString(", "))
                )
            )

            // Examiner Favorites
            topic.examinerFavorites.forEachIndexed { idx, q ->
                pool.add(
                    GeneratedQuestion(
                        id = "viva_fav_${topic.id}_$idx",
                        question = q,
                        answer = "Core summary:\n${topic.oneLineAnswer}\n\nDetailed breakdown:\n${topic.detailedAnswer}",
                        year = yr,
                        subject = subj,
                        module = topic.title,
                        source = "Syllabus Q&A",
                        tips = topic.rapidRevision + topic.commonMistakes,
                        clinicalNotes = topic.clinicalCorrelation,
                        references = listOf("Memory Aid: " + topic.memoryTricks)
                    )
                )
            }
        }

        // B. Muscle Anatomy Revision (DetailedAnatomy)
        dbAnatomies.forEach { anatomy ->
            // Core anatomy questions
            pool.add(
                GeneratedQuestion(
                    id = "anatomy_core_${anatomy.id}",
                    question = "Outline the origin, insertion, action, nerve supply, and clinical vulnerability of the ${anatomy.name} muscle.",
                    answer = "**Origin:** ${anatomy.origin}\n\n**Insertion:** ${anatomy.insertion}\n\n**Motor Action:** ${anatomy.action}\n\n**Nerve Supply:** ${anatomy.nerveSupply}\n\n**Blood Supply:** ${anatomy.bloodSupply}",
                    year = "1st Year",
                    subject = "Human Anatomy & Histology",
                    module = anatomy.name,
                    source = "Anatomy Review",
                    tips = if (anatomy.mnemonic.isNotEmpty()) listOf("Mnemonic: " + anatomy.mnemonic) else emptyList(),
                    clinicalNotes = anatomy.clinicalImportance,
                    references = anatomy.references
                )
            )

            // Specific Anatomy sub-viva questions
            anatomy.vivaQuestions.forEachIndexed { idx, q ->
                pool.add(
                    GeneratedQuestion(
                        id = "anatomy_sub_${anatomy.id}_$idx",
                        question = "$q (Muscle: ${anatomy.name})",
                        answer = "This question pertains to the **${anatomy.name}** muscle.\n\n**Nerve Supply:** ${anatomy.nerveSupply}\n\n**Action:** ${anatomy.action}\n\n**Origin & Insertion:** ${anatomy.origin} -> ${anatomy.insertion}\n\n**Clinical Relevance:** ${anatomy.clinicalImportance}",
                        year = "1st Year",
                        subject = "Human Anatomy & Histology",
                        module = anatomy.name,
                        source = "Anatomy Review",
                        tips = anatomy.commonConditions,
                        clinicalNotes = anatomy.clinicalImportance,
                        references = anatomy.references
                    )
                )
            }
        }

        // C. Clinical Assessments (DiagnosisData)
        dbAssessments.forEach { diag ->
            val mapping = mapCaseToYearSubject(diag.diagnosisName)
            val yr = mapping.first
            val subj = mapping.second

            // Core Case Q
            pool.add(
                GeneratedQuestion(
                    id = "case_core_${diag.id}",
                    question = "How do you evaluate and structure the rehabilitation protocol for a patient diagnosed with ${diag.diagnosisName}?",
                    answer = "**Clinical Reasoning Basis:**\n${diag.clinicalReasoning}\n\n**Physiotherapy Management:**\n${diag.ptManagement.joinToString("\n")}",
                    year = yr,
                    subject = subj,
                    module = diag.diagnosisName,
                    source = "Clinical Cases",
                    tips = diag.problemList,
                    clinicalNotes = "Anatomy associated: " + diag.relevantAnatomy.joinToString(", "),
                    references = listOf("Standard Clinical Guidelines")
                )
            )

            // Dynamic case sub questions
            diag.vivaQuestions.forEachIndexed { idx, q ->
                pool.add(
                    GeneratedQuestion(
                        id = "case_sub_${diag.id}_$idx",
                        question = "$q (Case Study: ${diag.diagnosisName})",
                        answer = "**Detailed Case Reasoning:**\n${diag.clinicalReasoning}\n\n**Protocol Directives:**\n${diag.ptManagement.joinToString("\n")}",
                        year = yr,
                        subject = subj,
                        module = diag.diagnosisName,
                        source = "Clinical Cases",
                        tips = diag.problemList,
                        clinicalNotes = "Physiological changes: " + diag.relevantPhysiology.joinToString(", "),
                        references = listOf("Magee's Orthopedic Assessment Protocols")
                    )
                )
            }
        }

        // D. Ward Postings Viva (SurvivalModule)
        dbPostings.forEach { posting ->
            posting.clinicalViva.forEachIndexed { idx, q ->
                pool.add(
                    GeneratedQuestion(
                        id = "posting_viva_${posting.id}_$idx",
                        question = "${q.question} (${posting.title})",
                        answer = q.answer,
                        year = "4th Year",
                        subject = "Clinical Practice & Ward Postings",
                        module = posting.title,
                        source = "Ward Postings",
                        tips = posting.commonMistakes,
                        clinicalNotes = "Observation Tips: " + posting.whatToObserve.take(3).joinToString("; "),
                        references = listOf("Standard Logbook Requirements")
                    )
                )
            }
        }

        pool
    }

    // 2. Extract Years we support
    val yearsList = listOf("All Years", "1st Year", "2nd Year", "3rd Year", "4th Year")

    // 3. Extract Subjects available based on current selection
    val subjectsList = remember(selectedYear, masterPool) {
        val list = mutableListOf<String>()
        list.add("All Subjects")
        masterPool.filter { selectedYear == "All Years" || it.year == selectedYear }
            .map { it.subject }
            .distinct()
            .sorted()
            .forEach { list.add(it) }
        list
    }

    // 4. Extract Modules available based on selected Year & Subject
    val modulesList = remember(selectedYear, selectedSubject, masterPool) {
        val list = mutableListOf<String>()
        list.add("All Modules")
        masterPool.filter {
            (selectedYear == "All Years" || it.year == selectedYear) &&
            (selectedSubject == "All Subjects" || it.subject == selectedSubject)
        }
            .map { it.module }
            .distinct()
            .sorted()
            .forEach { list.add(it) }
        list
    }

    // 5. Compute matching pool counts
    val currentFilteredCount = remember(selectedYear, selectedSubject, selectedModule, masterPool) {
        masterPool.filter {
            (selectedYear == "All Years" || it.year == selectedYear) &&
            (selectedSubject == "All Subjects" || it.subject == selectedSubject) &&
            (selectedModule == "All Modules" || it.module == selectedModule)
        }.size
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isDeckActive) "Active Q&A Session" else "Viva Q&A Generator",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (isDeckActive) {
                            isDeckActive = false
                            revealAnswer = false
                        } else {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        )
                    )
                )
        ) {
            if (!isDeckActive) {
                // FILTER AND CONFIGURATION VIEW
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Header Visual Representation
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = "Power Viva Icon",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "Curriculum Viva Simulator",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "Generate targeted viva examinations instantly from our syllabus registry. Local storage only.",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }

                    // 1. SELECT YEAR
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "1. Choose Academic Year",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    items(yearsList) { yr ->
                                        val selected = (selectedYear == yr)
                                        FilterChip(
                                            selected = selected,
                                            onClick = {
                                                selectedYear = yr
                                                resetOnYearChange()
                                            },
                                            label = { Text(yr) }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 2. SELECT SUBJECT (Exposed Dropdown Box)
                    var subjectDropdownExpanded by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "2. Filter Subject Blueprint",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            ExposedDropdownMenuBox(
                                expanded = subjectDropdownExpanded,
                                onExpandedChange = { subjectDropdownExpanded = !subjectDropdownExpanded }
                            ) {
                                OutlinedTextField(
                                    value = selectedSubject,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = subjectDropdownExpanded) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                ExposedDropdownMenu(
                                    expanded = subjectDropdownExpanded,
                                    onDismissRequest = { subjectDropdownExpanded = false }
                                ) {
                                    subjectsList.forEach { subj ->
                                        DropdownMenuItem(
                                            text = { Text(subj, fontWeight = if (subj == selectedSubject) FontWeight.Bold else FontWeight.Normal) },
                                            onClick = {
                                                selectedSubject = subj
                                                resetOnSubjectChange()
                                                subjectDropdownExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 3. SELECT MODULE
                    var moduleDropdownExpanded by remember { mutableStateOf(false) }
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "3. Select Specific Module / Case",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            ExposedDropdownMenuBox(
                                expanded = moduleDropdownExpanded,
                                onExpandedChange = { moduleDropdownExpanded = !moduleDropdownExpanded }
                            ) {
                                OutlinedTextField(
                                    value = selectedModule,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = moduleDropdownExpanded) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                ExposedDropdownMenu(
                                    expanded = moduleDropdownExpanded,
                                    onDismissRequest = { moduleDropdownExpanded = false },
                                    modifier = Modifier.heightIn(max = 280.dp) // Maintain safety limits on UI height
                                ) {
                                    modulesList.forEach { mod ->
                                        DropdownMenuItem(
                                            text = { Text(mod, maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = if (mod == selectedModule) FontWeight.Bold else FontWeight.Normal) },
                                            onClick = {
                                                selectedModule = mod
                                                moduleDropdownExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 4. RANDOMIZE SWITCH
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Randomize Question Sequence",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Shuffles generated questions in random order for realistic examiner simulation.",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            }
                            Switch(
                                checked = randomizeDeck,
                                onCheckedChange = { randomizeDeck = it }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // 5. DECK COMPILATION SUMMARY & LAUNCH BUTTON
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Selected Filters Match: $currentFilteredCount Questions",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )

                            Button(
                                onClick = {
                                    if (currentFilteredCount == 0) {
                                        Toast.makeText(context, "No matching questions. Relax filters to compile deck.", Toast.LENGTH_SHORT).show()
                                        return@Button
                                    }
                                    // Filter master pool
                                    val compiled = masterPool.filter {
                                        (selectedYear == "All Years" || it.year == selectedYear) &&
                                        (selectedSubject == "All Subjects" || it.subject == selectedSubject) &&
                                        (selectedModule == "All Modules" || it.module == selectedModule)
                                    }
                                    
                                    // Apply randomizing
                                    generatedDeck = if (randomizeDeck) compiled.shuffled() else compiled
                                    currentIndex = 0
                                    revealAnswer = false
                                    isDeckActive = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play icon")
                                    Text("Compile & Launch Viva", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }
                            }
                        }
                    }
                }
            } else {
                // ACTIVE STUDY SESSION VIEW
                if (generatedDeck.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No active session cards found.")
                    }
                } else {
                    val activeQuestion = generatedDeck[currentIndex]
                    val isFavorite = dbFavorites.any { it.questionId == activeQuestion.id }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top Navigation Progress & Saving Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    contentColor = MaterialTheme.colorScheme.onTertiary,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                ) {
                                    Text("${currentIndex + 1}/${generatedDeck.size}")
                                }
                                Text(
                                    text = activeQuestion.source,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            // Favorite Toggle Button
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    if (isFavorite) {
                                        dao.removeFavorite(activeQuestion.id)
                                        Toast.makeText(context, "Removed from syllabus bookmarks", Toast.LENGTH_SHORT).show()
                                    } else {
                                        dao.toggleFavorite(VivaFavorite(activeQuestion.id))
                                        Toast.makeText(context, "Saved to syllabus bookmarks!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                    contentDescription = "Syllabus Bookmark Toggle",
                                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        // Progress Indicator
                        LinearProgressIndicator(
                            progress = { (currentIndex + 1).toFloat() / generatedDeck.size.toFloat() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp)),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Unified Question Container Card (Flexible Scroll internally depending on display depth)
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primaryContainer),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp)
                                    .verticalScroll(rememberScrollState()),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // Subject Breadcrumb tags
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(MaterialTheme.colorScheme.outlineVariant)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(activeQuestion.year, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(MaterialTheme.colorScheme.primaryContainer)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(activeQuestion.subject, fontSize = 10.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    }
                                }

                                // Module Heading
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.Layers, contentDescription = null, modifier = Modifier.size(16.dp), tint = MaterialTheme.colorScheme.primary)
                                    Text(
                                        text = "Module: " + activeQuestion.module,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 13.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                    )
                                }

                                // Question block
                                Text(
                                    text = activeQuestion.question,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 24.sp,
                                    fontFamily = FontFamily.SansSerif
                                )

                                Divider(color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))

                                // ANSWER PANEL with fade and slide animation
                                AnimatedVisibility(
                                    visible = revealAnswer,
                                    enter = fadeIn() + slideInVertically(initialOffsetY = { 30 }),
                                    exit = fadeOut()
                                ) {
                                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                        // A. The Primary Answer
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(MaterialTheme.colorScheme.surface)
                                                .padding(16.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                modifier = Modifier.padding(bottom = 8.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.AssignmentTurnedIn,
                                                    contentDescription = null,
                                                    tint = Color(0xFF10B981),
                                                    modifier = Modifier.size(18.dp)
                                                )
                                                Text(
                                                    text = "EXAMINER ANSWER MODEL",
                                                    fontWeight = FontWeight.ExtraBold,
                                                    fontSize = 12.sp,
                                                    color = Color(0xFF10B981)
                                                )
                                            }
                                            Text(
                                                text = activeQuestion.answer,
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                lineHeight = 20.sp
                                            )
                                        }

                                        // B. Clinical Application/Notes if they exist
                                        if (activeQuestion.clinicalNotes.isNotEmpty()) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f))
                                                    .padding(16.dp)
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                    modifier = Modifier.padding(bottom = 6.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.MedicalServices,
                                                        contentDescription = null,
                                                        tint = MaterialTheme.colorScheme.tertiary,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                    Text(
                                                        text = "CLINICAL RELEVANCE CORRELATION",
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 11.sp,
                                                        color = MaterialTheme.colorScheme.tertiary
                                                    )
                                                }
                                                Text(
                                                    text = activeQuestion.clinicalNotes,
                                                    fontSize = 12.sp,
                                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                                                    lineHeight = 18.sp
                                                )
                                            }
                                        }

                                        // C. Rapid Revision Tips/Syllabus Pearls
                                        if (activeQuestion.tips.isNotEmpty()) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                                                    .padding(16.dp)
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                    modifier = Modifier.padding(bottom = 6.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Lightbulb,
                                                        contentDescription = null,
                                                        tint = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                    Text(
                                                        text = "HIGH-YIELD REVISION TIPS",
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 11.sp,
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                                activeQuestion.tips.forEach { tip ->
                                                    Row(
                                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                                        modifier = Modifier.padding(vertical = 3.dp)
                                                    ) {
                                                        Text("•", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                                        Text(
                                                            text = tip,
                                                            fontSize = 12.sp,
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            lineHeight = 16.sp
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        // D. Scientific References
                                        if (activeQuestion.references.isNotEmpty()) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 4.dp),
                                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.MenuBook,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                                    modifier = Modifier.size(14.dp)
                                                )
                                                Text(
                                                    text = "Source: " + activeQuestion.references.joinToString(", "),
                                                    fontSize = 11.sp,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Active navigation and verification toolbar
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if (!revealAnswer) {
                                Button(
                                    onClick = { revealAnswer = true },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(imageVector = Icons.Default.Visibility, contentDescription = null)
                                        Text("Reveal Answer", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                                    }
                                }
                            } else {
                                Button(
                                    onClick = { revealAnswer = false },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(52.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f), contentColor = MaterialTheme.colorScheme.primary)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(imageVector = Icons.Default.VisibilityOff, contentDescription = null)
                                        Text("Hide Answer Model", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                                    }
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        if (currentIndex > 0) {
                                            currentIndex--
                                            revealAnswer = false
                                        }
                                    },
                                    enabled = (currentIndex > 0),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(48.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous")
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Previous", fontWeight = FontWeight.Bold)
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                if (currentIndex == generatedDeck.size - 1) {
                                    Button(
                                        onClick = {
                                            // Finish session, log to Local Storage via Activity & Streaks
                                            coroutineScope.launch {
                                                ProgressManager.logActivityAndCheckStreak(
                                                    context = context,
                                                    topicId = "viva_generator_session_${selectedSubject.hashCode()}",
                                                    title = "Completed ${generatedDeck.size} Generated Vivas",
                                                    type = "VIVA",
                                                    subject = if (selectedSubject == "All Subjects") "Unified Syllabus Mix" else selectedSubject,
                                                    year = selectedYear
                                                )
                                                Toast.makeText(context, "Logged study session successfully! Streak Updated!", Toast.LENGTH_LONG).show()
                                                isDeckActive = false
                                                revealAnswer = false
                                            }
                                        },
                                        shape = RoundedCornerShape(10.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp)
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Done icon")
                                            Text("Finish Session", fontWeight = FontWeight.Bold)
                                        }
                                    }
                                } else {
                                    Button(
                                        onClick = {
                                            currentIndex++
                                            revealAnswer = false
                                        },
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(48.dp)
                                    ) {
                                        Text("Next", fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Map database categories to structured Year / Academic Subject
private fun mapCategoryToYearSubject(category: String): Pair<String, String> {
    return when (category) {
        "Anatomy" -> Pair("1st Year", "Human Anatomy & Histology")
        "Physiology" -> Pair("1st Year", "Human Physiology & Biochemistry")
        "Electrotherapy" -> Pair("2nd Year", "Electrophysical Agents")
        "Biomechanics", "Exercise Therapy" -> Pair("2nd Year", "Exercise Gymnastics & Biomechanics")
        "Pathology" -> Pair("3rd Year", "Orthopedic Physiotherapy & Sports") // Or Pathology
        "Pharmacology" -> Pair("3rd Year", "Orthopedic Physiotherapy & Sports") // Or Pharmacology
        "Orthopedics" -> Pair("3rd Year", "Orthopedic Physiotherapy & Sports")
        "Neurology" -> Pair("4th Year", "Neurological PT & Pediatrics")
        "Cardiopulmonary" -> Pair("4th Year", "Cardiopulmonary & Geriatric Care")
        "Women's Health" -> Pair("4th Year", "Community Rehabilitation & Women's Health")
        else -> Pair("3rd Year", "Orthopedic Physiotherapy & Sports")
    }
}

// Map diagnostic clinical cases based on keywords
private fun mapCaseToYearSubject(caseName: String): Pair<String, String> {
    val name = caseName.lowercase()
    return when {
        name.contains("diastasis") || name.contains("pelvic") || name.contains("antenatal") || name.contains("postnatal") || name.contains("girdle") || name.contains("incontinence") || name.contains("cesarean") || name.contains("obgyn") || name.contains("women") -> {
            Pair("4th Year", "Community Rehabilitation & Women's Health")
        }
        name.contains("stroke") || name.contains("hemiplegia") || name.contains("brain") || name.contains("spinal") || name.contains("palsy") || name.contains("neurology") || name.contains("injury") || name.contains("neuropath") || name.contains("child") || name.contains("cerebral") -> {
            Pair("4th Year", "Neurological PT & Pediatrics")
        }
        name.contains("copd") || name.contains("asthma") || name.contains("cardio") || name.contains("pulmonary") || name.contains("post-op") || name.contains("artery") || name.contains("heart") || name.contains("valve") -> {
            Pair("4th Year", "Cardiopulmonary & Geriatric Care")
        }
        else -> {
            Pair("3rd Year", "Orthopedic Physiotherapy & Sports")
        }
    }
}
