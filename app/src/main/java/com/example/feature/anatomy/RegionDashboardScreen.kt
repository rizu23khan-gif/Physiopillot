package com.example.feature.anatomy

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.data.*
import com.example.feature.assessment.SpecialTestModel
import com.example.feature.assessment.loadSpecialTests
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// Maps a region name to its curriculum moduleId in SubjectChapterRepository
fun getSubjectModuleIdForRegion(region: String): String {
    return when (region.lowercase()) {
        "thorax", "thoracic spine" -> "anat_thorax"
        "shoulder", "arm", "elbow", "wrist", "hand" -> "anat_upper_limb"
        "pelvis", "hip", "knee", "ankle", "foot", "lumber spine", "lumbar spine" -> "anat_lower_limb"
        "head", "neck", "cervical spine" -> "anat_head_neck"
        else -> "anat_gen"
    }
}

// Maps a region to typical anatomy search matches
fun isAnatomyInRegion(anatomy: DetailedAnatomy, region: String): Boolean {
    val r = region.lowercase()
    val name = anatomy.name.lowercase()
    val terms = anatomy.searchTerms.map { it.lowercase() }
    
    // Explicit list for Thorax
    if (r == "thorax" || r == "thoracic spine") {
        if (anatomy.id.startsWith("a_t")) return true
        val thoraxTerms = listOf("thorax", "thoracic", "diaphragm", "respiratory", "lungs", "heart", "sternum", "ribs", "esophagus", "azygos", "intercostal", "spine", "vertebra")
        return thoraxTerms.any { name.contains(it) || terms.any { t -> t.contains(it) } }
    }
    
    // Explicit list for Head/Neck
    if (r == "head" || r == "neck" || r == "cervical spine") {
        val hnTerms = listOf("head", "neck", "skull", "cervical", "spinal cord", "brain", "trapezius", "sternocleidomastoid", "platysma", "spine", "vertebra")
        return hnTerms.any { name.contains(it) || terms.any { t -> t.contains(it) } }
    }
    
    // Upper Limb match
    if (r == "shoulder" || r == "arm" || r == "elbow" || r == "hand" || r == "wrist") {
        val ulTerms = listOf("deltoid", "biceps", "triceps", "brachioradialis", "pronator", "supinator", "hand", "wrist", "finger", "shoulder", "scapula", "clavicle", "humerus", "radial", "ulnar", "median", "axillary", "pectoralis", "carpal")
        return ulTerms.any { name.contains(it) || terms.any { t -> t.contains(it) } }
    }

    // Lower Limb match
    if (r == "pelvis" || r == "hip" || r == "knee" || r == "ankle" || r == "foot" || r == "lumber spine" || r == "lumbar spine") {
        val llTerms = listOf("quadriceps", "hamstring", "gluteus", "gastrocnemius", "tibialis", "soleus", "femur", "tibia", "fibula", "foot", "toe", "sciatic", "patella", "hip", "knee", "ankle", "quad", "sartorius", "gracilis", "iliopsoas", "adductor", "lumbar", "spine", "vertebra")
        return llTerms.any { name.contains(it) || terms.any { t -> t.contains(it) } }
    }

    return name.contains(r) || terms.any { it.contains(r) }
}

// Maps a region to typical clinical cases
fun isCaseInRegion(cCase: ClinicalCase, region: String): Boolean {
    val r = region.lowercase()
    val cond = cCase.condition.lowercase()
    val diag = cCase.physiotherapyDiagnosis.lowercase()
    val complaint = cCase.chiefComplaint.lowercase()
    
    if (r == "thorax") {
        if (cCase.category.lowercase() == "cardiopulmonary") return true
        val words = listOf("chest", "rib", "thorax", "thoracic", "pneumonia", "cardiac", "heart", "breath", "lungs", "copd", "asthma")
        return words.any { cond.contains(it) || diag.contains(it) || complaint.contains(it) }
    }
    
    if (r == "knee") {
        val words = listOf("knee", "acl", "mcl", "patella", "meniscus", "ligament", "lachman")
        return words.any { cond.contains(it) || diag.contains(it) || complaint.contains(it) }
    }
    
    if (r == "shoulder") {
        val words = listOf("shoulder", "rotator", "supraspinatus", "adhesive", "capsulitis", "glenohumeral")
        return words.any { cond.contains(it) || diag.contains(it) || complaint.contains(it) }
    }

    if (r == "hand" || r == "wrist" || r == "elbow") {
        val words = listOf("carpal", "tunnel", "wrist", "hand", "elbow", "tennis", "epicondylitis", "phalen")
        return words.any { cond.contains(it) || diag.contains(it) || complaint.contains(it) }
    }

    if (r == "spine" || r == "neck" || r == "head") {
        val words = listOf("cervical", "spine", "lumber", "back", "disc", "herniation", "headache", "neck")
        return words.any { cond.contains(it) || diag.contains(it) || complaint.contains(it) }
    }

    return cond.contains(r) || diag.contains(r) || complaint.contains(r)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionDashboardScreen(navController: NavHostController, region: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    
    // Collect database items
    val database = remember { AppDatabase.getDatabase(context) }
    var loadedAnatomies by remember { mutableStateOf<List<DetailedAnatomy>>(emptyList()) }
    var clinicalCases by remember { mutableStateOf<List<ClinicalCase>>(emptyList()) }
    
    LaunchedEffect(Unit) {
        database.physioDao().getAllAnatomies().collect { list ->
            loadedAnatomies = list
        }
    }
    
    LaunchedEffect(Unit) {
        database.physioDao().getAllClinicalCases().collect { list ->
            clinicalCases = list
        }
    }

    // Load special tests
    val specialTests by androidx.compose.runtime.produceState(initialValue = emptyList<com.example.feature.assessment.SpecialTestModel>()) {
        value = loadSpecialTests(context)
    }

    // Navigation chapters lookup
    val moduleId = getSubjectModuleIdForRegion(region)
    val moduleTitle = when (moduleId) {
        "anat_thorax" -> "Thorax"
        "anat_upper_limb" -> "Upper Limb"
        "anat_lower_limb" -> "Lower Limb"
        "anat_head_neck" -> "Head & Neck"
        else -> "General Anatomy"
    }
    
    val moduleChapters = remember(moduleId) {
        val anatomySubject = SubjectChapterRepository.subjectsMap["anatomy"]
        anatomySubject?.modules?.find { it.moduleId == moduleId }?.chapters ?: emptyList()
    }

    // Filtered lists
    val filteredAnatomies = remember(region, loadedAnatomies) {
        loadedAnatomies.filter { isAnatomyInRegion(it, region) }
    }
    
    val filteredTests = remember(region, specialTests) {
        specialTests.filter { test ->
            test.body_region.contains(region, ignoreCase = true) ||
            (region.lowercase() == "thorax" && listOf("thoracic", "chest", "rib", "diaphragm").any { test.body_region.contains(it, ignoreCase = true) })
        }
    }
    
    val filteredCases = remember(region, clinicalCases) {
        clinicalCases.filter { isCaseInRegion(it, region) }
    }

    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Syllabus Chapters", "Anatomy Core", "Special Tests", "Clinical Cases")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            text = "$region Dashboard",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Physical Medicine & Rehabilitation Hub",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Region Header Banner Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
                            )
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = when (region.lowercase()) {
                                        "thorax" -> Icons.Default.Favorite
                                        "shoulder", "arm", "hand" -> Icons.Default.Create
                                        "knee", "hip", "ankle", "foot" -> Icons.Default.Place
                                        else -> Icons.Default.Info
                                    },
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = "Curriculum: $moduleTitle Module",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Structured study blocks for board exam preparation.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(14.dp))
                    
                    // Quick Stats row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StatBadge(icon = Icons.Default.List, value = "${moduleChapters.size} Chapters")
                        StatBadge(icon = Icons.Default.Star, value = "${filteredAnatomies.size} Structures")
                        StatBadge(icon = Icons.Default.PlayArrow, value = "${filteredTests.size} Tests")
                        StatBadge(icon = Icons.Default.Email, value = "${filteredCases.size} Cases")
                    }
                }
            }

            // Tabs
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 16.dp,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.primary,
                divider = { HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)) }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                                )
                            )
                        },
                        modifier = Modifier.testTag("tab_button_$index")
                    )
                }
            }

            // Tab Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AnimatedContent(
                    targetState = selectedTab,
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut()
                    },
                    label = "tab_content_anim"
                ) { targetTab ->
                    when (targetTab) {
                        0 -> SyllabusChaptersTab(
                            chapters = moduleChapters,
                            onChapterClick = { chapterId ->
                                navController.navigate("chapter_detail/anatomy/$chapterId")
                            }
                        )
                        1 -> AnatomySpecTab(
                            anatomies = filteredAnatomies,
                            region = region
                        )
                        2 -> SpecialTestsTab(
                            tests = filteredTests,
                            region = region
                        )
                        3 -> ClinicalCasesTab(
                            cases = filteredCases,
                            region = region
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatBadge(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        modifier = Modifier.padding(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SyllabusChaptersTab(
    chapters: List<SubjectChapter>,
    onChapterClick: (String) -> Unit
) {
    if (chapters.isEmpty()) {
        EmptyStateView(
            icon = Icons.Default.List,
            message = "Syllabus Integration Underway",
            tip = "The academic curriculum for this region is currently being drafted by clinical instructors."
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "Core Curriculum Guide",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Compulsory academic chapters with corresponding study blocks, viva voice logs and diagnostic checklists.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(chapters) { ch ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onChapterClick(ch.chapterId) }
                        .testTag("chapter_card_${ch.chapterId}"),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Numeric index marker
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "${ch.index + 1}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Black,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = ch.title,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            if (ch.description.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = ch.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Open Chapter",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnatomySpecTab(
    anatomies: List<DetailedAnatomy>,
    region: String
) {
    if (anatomies.isEmpty()) {
        EmptyStateView(
            icon = Icons.Default.Search,
            message = "Anatomy Archives Under Prep",
            tip = "Core specifications for muscle origin, insertions and neurovascular systems for $region are being updated."
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "Aesthetic Dissection Spec Sheets",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(anatomies) { item ->
                var expanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded }
                        .testTag("anatomy_card_${item.id}"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier.padding(2.dp)
                            ) {
                                Text(
                                    text = item.anatomyType,
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f)
                            )
                            
                            Icon(
                                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "Expand details",
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        
                        Text(
                            text = item.definition,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        AnimatedVisibility(visible = expanded) {
                            Column(modifier = Modifier.padding(top = 16.dp)) {
                                HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                SpecRow(label = "Origin", value = item.origin)
                                SpecRow(label = "Insertion", value = item.insertion)
                                SpecRow(label = "Nerve Supply", value = item.nerveSupply)
                                SpecRow(label = "Blood Supply", value = item.bloodSupply)
                                SpecRow(label = "Action / Function", value = item.action)
                                SpecRow(label = "Clinical Significance", value = item.clinicalImportance)
                                
                                if (item.mnemonic.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Info,
                                                contentDescription = "Mnemonic",
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Text(
                                                text = "Mnemonic: ${item.mnemonic}",
                                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                                color = MaterialTheme.colorScheme.onPrimaryContainer
                                              )
                                        }
                                    }
                                }

                                if (item.vivaQuestions.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "High-Yield Viva Triggers:",
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    item.vivaQuestions.forEach { q ->
                                        // Clean up parsing if containing questions & answers
                                        val displayQ = if (q.contains("|")) q.split("|")[0].replace("Question: ", "").trim() else q
                                        Text(
                                            text = "• $displayQ",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
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
}

@Composable
fun SpecRow(label: String, value: String) {
    if (value.isNotEmpty() && value != "N/A") {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.width(110.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun SpecialTestsTab(
    tests: List<SpecialTestModel>,
    region: String
) {
    if (tests.isEmpty()) {
        EmptyStateView(
            icon = Icons.Default.PlayArrow,
            message = "Physiotherapy Tests Pending",
            tip = "No verified manual/special orthopaedic evaluation diagnostic tests are registered for $region."
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "Manual Joint & Tissue Assessments",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(tests) { test ->
                var expanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded }
                        .testTag("special_test_card_${test.test_id}"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = test.test_name,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Aims: ${test.purpose}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        AnimatedVisibility(visible = expanded) {
                            Column(modifier = Modifier.padding(top = 16.dp)) {
                                HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                SpecRow(label = "Patient Position", value = test.patient_position)
                                SpecRow(label = "Therapist Position", value = test.therapist_position)
                                
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Step Procedure:",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                test.procedure.forEachIndexed { i, step ->
                                    Text(
                                        text = "${i + 1}. $step",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                SpecRow(label = "Positive Sign", value = test.positive_sign)
                                SpecRow(label = "Clinical Reading", value = test.clinical_interpretation)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClinicalCasesTab(
    cases: List<ClinicalCase>,
    region: String
) {
    if (cases.isEmpty()) {
        EmptyStateView(
            icon = Icons.Default.Star,
            message = "Case Studies Being Curated",
            tip = "Clinical posting student logs for $region disorders are currently being processed by the medical review team."
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = "Regional Board Examination Case Files",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            items(cases) { cCase ->
                var expanded by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded }
                        .testTag("clinical_case_card_${cCase.id}"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.padding(2.dp)
                            ) {
                                Text(
                                    text = cCase.category,
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            
                            Text(
                                text = cCase.condition,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.weight(1f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            
                            Icon(
                                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Complaint: ${cCase.chiefComplaint}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        AnimatedVisibility(visible = expanded) {
                            Column(modifier = Modifier.padding(top = 16.dp)) {
                                HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                SpecRow(label = "Patient Profiles", value = cCase.patientProfile)
                                SpecRow(label = "Physical Dx", value = cCase.physiotherapyDiagnosis)
                                
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Intervention Protocol:",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                cCase.treatmentPlan.forEachIndexed { i, plan ->
                                    Text(
                                        text = "• $plan",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                                    )
                                }
                                
                                if (cCase.redFlags.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.2f))
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Warning,
                                                    contentDescription = "Contraindications",
                                                    tint = MaterialTheme.colorScheme.error,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Text(
                                                    text = "Contraindications / Red Flags:",
                                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                                    color = MaterialTheme.colorScheme.onErrorContainer
                                                )
                                            }
                                            cCase.redFlags.forEach { flag ->
                                                Text(
                                                    text = "• $flag",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onErrorContainer,
                                                    modifier = Modifier.padding(top = 2.dp, start = 4.dp)
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
        }
    }
}

@Composable
fun EmptyStateView(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    message: String,
    tip: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(64.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = tip,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
