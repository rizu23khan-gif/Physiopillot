package com.example.feature.assessment

import android.content.Context
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.AppDatabase
import com.example.data.ClinicalCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ClinicalCaseRepositoryScreen(navController: NavController) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val dao = remember { database.physioDao() }
    val coroutineScope = rememberCoroutineScope()
    
    // Live database state for cases
    val caseListState = dao.getAllClinicalCases().collectAsState(initial = emptyList())
    val allCases = caseListState.value
    
    // Load special tests for connection
    val specialTestsList = remember { loadSpecialTests(context) }
    
    // Search and Filters
    var searchQuery by remember { mutableStateOf(com.example.data.ContentRepo.lastViewedClinicalCaseQuery ?: "") }
    var selectedCategory by remember { mutableStateOf("All Categories") }
    var selectedYear by remember { mutableStateOf("All Years") }
    var selectedSubject by remember { mutableStateOf("All Subjects") }
    var selectedCondition by remember { mutableStateOf("All Conditions") }
    var onlyBookmarked by remember { mutableStateOf(false) }
    
    // Dialog state for clicked Special Test
    var activeSpecialTestForDialog by remember { mutableStateOf<SpecialTestModel?>(null) }
    
    // Accordion expanded states
    val expandedStates = remember { mutableStateMapOf<String, Boolean>() }
    
    // Dynamic Filter lists derived from data
    val categories = listOf("All Categories", "Orthopaedics", "Neurology", "Cardiopulmonary", "Sports Physiotherapy", "Pediatrics", "Women's Health")
    val years = remember(allCases) {
        listOf("All Years") + allCases.map { it.year }.distinct().sorted()
    }
    val subjects = remember(allCases) {
        listOf("All Subjects") + allCases.map { it.subject }.distinct().sorted()
    }
    val conditions = remember(allCases) {
        listOf("All Conditions") + allCases.map { it.condition }.distinct().sorted()
    }
    
    // Filtering logic
    val filteredCases = remember(allCases, searchQuery, selectedCategory, selectedYear, selectedSubject, selectedCondition, onlyBookmarked) {
        allCases.filter { c ->
            val matchCategory = selectedCategory == "All Categories" || c.category.equals(selectedCategory, ignoreCase = true)
            val matchYear = selectedYear == "All Years" || c.year.equals(selectedYear, ignoreCase = true)
            val matchSubject = selectedSubject == "All Subjects" || c.subject.equals(selectedSubject, ignoreCase = true)
            val matchCondition = selectedCondition == "All Conditions" || c.condition.equals(selectedCondition, ignoreCase = true)
            val matchBookmark = !onlyBookmarked || c.isBookmarked
            
            val matchSearch = searchQuery.isEmpty() ||
                    c.condition.contains(searchQuery, ignoreCase = true) ||
                    c.patientProfile.contains(searchQuery, ignoreCase = true) ||
                    c.chiefComplaint.contains(searchQuery, ignoreCase = true) ||
                    c.physiotherapyDiagnosis.contains(searchQuery, ignoreCase = true) ||
                    c.category.contains(searchQuery, ignoreCase = true)
            
            matchCategory && matchYear && matchSubject && matchCondition && matchBookmark && matchSearch
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Clinical Case Repository",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Integrated Physiotherapy Cases",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onlyBookmarked = !onlyBookmarked }) {
                        Icon(
                            imageVector = if (onlyBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Toggle bookmarks filter",
                            tint = if (onlyBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
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
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                // Search bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search by diagnosis, history or profile...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear search")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    )
                )

                // Quick horizontal tabs for Main Categories
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(end = 16.dp)
                ) {
                    items(categories) { category ->
                        val selected = selectedCategory == category
                        FilterChip(
                            selected = selected,
                            onClick = { selectedCategory = category },
                            label = { Text(category) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }

                // Advanced filter chips row for Year / Subject / Condition
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Filter by Year Menu
                    var showYearMenu by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { showYearMenu = true },
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (selectedYear == "All Years") "Year" else selectedYear,
                                fontSize = 11.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                        DropdownMenu(expanded = showYearMenu, onDismissRequest = { showYearMenu = false }) {
                            years.forEach { yr ->
                                DropdownMenuItem(
                                    text = { Text(yr, fontSize = 13.sp) },
                                    onClick = {
                                        selectedYear = yr
                                        showYearMenu = false
                                    }
                                )
                            }
                        }
                    }

                    // Filter by Subject Menu
                    var showSubjectMenu by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { showSubjectMenu = true },
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (selectedSubject == "All Subjects") "Subject" else {
                                    if (selectedSubject.contains(" ")) selectedSubject.substringBefore(" ") else selectedSubject
                                },
                                fontSize = 11.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                        DropdownMenu(expanded = showSubjectMenu, onDismissRequest = { showSubjectMenu = false }) {
                            subjects.forEach { subj ->
                                DropdownMenuItem(
                                    text = { Text(subj, fontSize = 13.sp) },
                                    onClick = {
                                        selectedSubject = subj
                                        showSubjectMenu = false
                                    }
                                )
                            }
                        }
                    }

                    // Filter by Condition Menu
                    var showConditionMenu by remember { mutableStateOf(false) }
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { showConditionMenu = true },
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = if (selectedCondition == "All Conditions") "Condition" else {
                                    if (selectedCondition.length > 10) selectedCondition.take(8) + ".." else selectedCondition
                                },
                                fontSize = 11.sp,
                                maxLines = 1,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                        DropdownMenu(expanded = showConditionMenu, onDismissRequest = { showConditionMenu = false }) {
                            conditions.forEach { cond ->
                                DropdownMenuItem(
                                    text = { Text(cond, fontSize = 13.sp) },
                                    onClick = {
                                        selectedCondition = cond
                                        showConditionMenu = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Bookmark filter label
                if (onlyBookmarked || selectedYear != "All Years" || selectedSubject != "All Subjects" || selectedCondition != "All Conditions") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SuggestionChip(
                            onClick = {
                                onlyBookmarked = false
                                selectedYear = "All Years"
                                selectedSubject = "All Subjects"
                                selectedCondition = "All Conditions"
                            },
                            label = { Text("Clear sub-filters ✕", fontSize = 11.sp) },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                labelColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }

                // Count layout indicator
                Text(
                    text = "Showing ${filteredCases.size} verified cases",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )

                if (filteredCases.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SearchOff,
                                contentDescription = "No results",
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                            )
                            Text(
                                text = "No clinical cases found.",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                            Text(
                                text = "Adjust filters or try typing a different search query.",
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredCases, key = { it.id }) { clinicalCase ->
                            val isExpanded = expandedStates[clinicalCase.id] == true
                            ClinicalCaseCard(
                                clinicalCase = clinicalCase,
                                isExpanded = isExpanded,
                                specialTestsList = specialTestsList,
                                onToggleExpand = {
                                    expandedStates[clinicalCase.id] = !isExpanded
                                },
                                onToggleBookmark = {
                                    coroutineScope.launch {
                                        dao.updateBookmarkStatus(clinicalCase.id, !clinicalCase.isBookmarked)
                                    }
                                },
                                onSpecialTestClick = { matchedTest ->
                                    activeSpecialTestForDialog = matchedTest
                                }
                            )
                        }
                    }
                }
            }

            // High-fidelity Special Test Dialog
            if (activeSpecialTestForDialog != null) {
                val test = activeSpecialTestForDialog!!
                AlertDialog(
                    onDismissRequest = { activeSpecialTestForDialog = null },
                    icon = { Icon(Icons.Default.School, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    title = {
                        Text(
                            text = test.test_name,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    },
                    text = {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 400.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Region
                            item {
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(MaterialTheme.colorScheme.secondaryContainer)
                                            .padding(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text(test.body_region, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSecondaryContainer)
                                    }
                                }
                            }
                            
                            // Purpose
                            item {
                                Column {
                                    Text("Purpose", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                                    Text(test.purpose, fontSize = 13.sp)
                                }
                            }

                            // Positions
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text("Patient: ${test.patient_position}", fontSize = 12.sp, fontStyle = FontStyle.Italic)
                                    Text("Therapist: ${test.therapist_position}", fontSize = 12.sp, fontStyle = FontStyle.Italic)
                                }
                            }

                            // Procedure steps
                            item {
                                Column {
                                    Text("Procedure", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                                    test.procedure.forEachIndexed { i, step ->
                                        Row(modifier = Modifier.padding(vertical = 2.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                            Text("${i+1}.", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                            Text(step, fontSize = 13.sp)
                                        }
                                    }
                                }
                            }

                            // Positive Sign
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFFFF1F0))
                                        .border(1.dp, Color(0xFFFFA39E), RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                ) {
                                    Text("POSITIVE PHYSICAL SIGN", fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color(0xFFCF1322))
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(test.positive_sign, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF722ED1))
                                }
                            }

                            // Clinical Significance
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color(0xFFF6FFED))
                                        .border(1.dp, Color(0xFFB7EB8F), RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                ) {
                                    Text("CLINICAL SIGNIFICANCE", fontWeight = FontWeight.Bold, fontSize = 10.sp, color = Color(0xFF389E0D))
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(test.clinical_interpretation, fontSize = 12.sp, color = Color(0xFF1F2937))
                                }
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(onClick = { activeSpecialTestForDialog = null }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ClinicalCaseCard(
    clinicalCase: ClinicalCase,
    isExpanded: Boolean,
    specialTestsList: List<SpecialTestModel>,
    onToggleExpand: () -> Unit,
    onToggleBookmark: () -> Unit,
    onSpecialTestClick: (SpecialTestModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleExpand() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpanded) {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.8f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Header row: Badges & Bookmark Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Category Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                when (clinicalCase.category) {
                                    "Orthopaedics" -> Color(0xFFE8F5E9)
                                    "Neurology" -> Color(0xFFE3F2FD)
                                    "Cardiopulmonary" -> Color(0xFFFFF3E0)
                                    "Pediatrics" -> Color(0xFFE0F7FA)
                                    "Women's Health" -> Color(0xFFFCE4EC)
                                    else -> Color(0xFFF3E5F5)
                                }
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = clinicalCase.category,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = when (clinicalCase.category) {
                                "Orthopaedics" -> Color(0xFF2E7D32)
                                "Neurology" -> Color(0xFF1565C0)
                                "Cardiopulmonary" -> Color(0xFFEF6C00)
                                "Pediatrics" -> Color(0xFF00838F)
                                "Women's Health" -> Color(0xFFC2185B)
                                else -> Color(0xFF6A1B9A)
                            }
                        )
                    }

                    // Combined Year Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = clinicalCase.year,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Bookmark icon
                IconButton(
                    onClick = { onToggleBookmark() },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = if (clinicalCase.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (clinicalCase.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )
                }
            }

            // Condition & Subject
            Column {
                Text(
                    text = clinicalCase.condition,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Subject: ${clinicalCase.subject}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            }

            // Brief summary (Patient profile + Chief Complaint)
            Text(
                text = clinicalCase.patientProfile,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Dynamic detail expander
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Divider(color = MaterialTheme.colorScheme.outlineVariant)

                    // Chief Complaint Section
                    CaseSectionLabel(title = "CHIEF COMPLAINT")
                    Text(
                        text = clinicalCase.chiefComplaint,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    // History Section
                    CaseSectionLabel(title = "HISTORY")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.history.forEach { hist ->
                            BulletPoint(text = hist)
                        }
                    }

                    // Observation Section
                    CaseSectionLabel(title = "OBSERVATION")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.observation.forEach { obs ->
                            BulletPoint(text = obs)
                        }
                    }

                    // Examination Section
                    CaseSectionLabel(title = "PHYSICAL EXAMINATION")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.examination.forEach { exam ->
                            BulletPoint(text = exam)
                        }
                    }

                    // Special Tests with connection to Special Test Repository
                    CaseSectionLabel(title = "SPECIAL TESTS")
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        clinicalCase.specialTests.forEach { testName ->
                            BulletPoint(text = testName)
                        }

                        // Connected badge chips (only if linkable special tests exist)
                        if (clinicalCase.linkedSpecialTests.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "💡 Tap interactive special tests below to view examination procedure:",
                                fontSize = 11.sp,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.primary
                            )
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                clinicalCase.linkedSpecialTests.forEach { linkedId ->
                                    val matchedTest = specialTestsList.find { it.test_id == linkedId }
                                    if (matchedTest != null) {
                                        AssistChip(
                                            onClick = { onSpecialTestClick(matchedTest) },
                                            leadingIcon = { Icon(Icons.Default.School, contentDescription = null, modifier = Modifier.size(12.dp)) },
                                            label = { Text("Procedure: ${matchedTest.test_name}", fontSize = 11.sp) },
                                            colors = AssistChipDefaults.assistChipColors(
                                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                                                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                            ),
                                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Clinical Assessment Findings Section
                    CaseSectionLabel(title = "CLINICAL ASSESSMENT FINDINGS")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.assessmentFindings.forEach { finding ->
                            BulletPoint(text = finding)
                        }
                    }

                    // Problem List Section
                    CaseSectionLabel(title = "PHYSIOTHERAPY PROBLEM LIST")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.problemList.forEach { prob ->
                            BulletPoint(text = prob)
                        }
                    }

                    // Physiotherapy Diagnosis
                    CaseSectionLabel(title = "PHYSIOTHERAPY DIAGNOSIS (ICF FRAMEWORK)")
                    Text(
                        text = clinicalCase.physiotherapyDiagnosis,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 18.sp
                    )

                    // Short-Term Goals
                    CaseSectionLabel(title = "SHORT-TERM GOALS")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.shortTermGoals.forEach { goal ->
                            BulletPoint(text = goal)
                        }
                    }

                    // Long-Term Goals
                    CaseSectionLabel(title = "LONG-TERM GOALS")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.longTermGoals.forEach { goal ->
                            BulletPoint(text = goal)
                        }
                    }

                    // Treatment Plan
                    CaseSectionLabel(title = "PHYSIOTHERAPY TREATMENT PLAN")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.treatmentPlan.forEach { plan ->
                            BulletPoint(text = plan)
                        }
                    }

                    // Outcome Measures Section
                    CaseSectionLabel(title = "OUTCOME MEASURES")
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        clinicalCase.outcomeMeasures.forEach { measure ->
                            BulletPoint(text = measure)
                        }
                    }

                    // Red Flags Section
                    CaseSectionLabel(title = "RED FLAGS & CONTRAINDICATIONS")
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFF1F0))
                            .border(1.dp, Color(0xFFFFA39E), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        clinicalCase.redFlags.forEach { flag ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = "Warning",
                                    tint = Color(0xFFCF1322),
                                    modifier = Modifier
                                        .size(16.dp)
                                        .padding(top = 1.dp)
                                )
                                Text(
                                    text = flag,
                                    fontSize = 12.sp,
                                    color = Color(0xFF722ED1),
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
            }

            // Accordion toggle button at bottom center of card
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isExpanded) "Show Less" else "Tap for patient history, exam & treatment",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun CaseSectionLabel(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Black,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.primary,
        letterSpacing = 0.8.sp
    )
}

@Composable
fun BulletPoint(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp
        )
        Text(
            text = text,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 18.sp
        )
    }
}
