package com.example.feature.search

import android.content.Context
import androidx.compose.animation.*
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
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.data.*
import com.example.feature.assessment.SpecialTestModel
import com.example.feature.assessment.loadSpecialTests

enum class UniversalSearchCategory(val displayName: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    ALL("All", Icons.Default.Category),
    ANATOMY("Anatomy", Icons.Default.Accessibility),
    MCQ_BANK("MCQ Bank", Icons.Default.School),
    VIVA_REPOSITORY("Viva Prep", Icons.Default.MenuBook),
    SPECIAL_TESTS("Special Tests", Icons.Default.Healing),
    CLINICAL_CASES("Clinical Cases", Icons.Default.Assignment)
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    var query by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(UniversalSearchCategory.ALL) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    // Initialize Persistent SharedPreferences storage for searches
    val sharedPrefs = remember {
        context.getSharedPreferences("physiopilot_universal_search_prefs", Context.MODE_PRIVATE)
    }

    // Recent searches loaded/saved in ordered string sequence
    val recentSearches = remember {
        val savedString = sharedPrefs.getString("recent_searches_universal", "") ?: ""
        val list = if (savedString.isBlank()) {
            listOf("Stroke", "Median Nerve", "Lachman", "Erb's Palsy", "Gait Cycle")
        } else {
            savedString.split("|||").filter { it.isNotBlank() }
        }
        mutableStateListOf<String>().apply { addAll(list) }
    }

    fun addRecentSearch(item: String) {
        val trimmed = item.trim()
        if (trimmed.isBlank()) return
        recentSearches.remove(trimmed)
        recentSearches.add(0, trimmed)
        if (recentSearches.size > 10) {
            recentSearches.removeAt(recentSearches.size - 1)
        }
        sharedPrefs.edit().putString("recent_searches_universal", recentSearches.joinToString("|||")).apply()
    }

    fun removeRecentSearch(item: String) {
        recentSearches.remove(item)
        sharedPrefs.edit().putString("recent_searches_universal", recentSearches.joinToString("|||")).apply()
    }

    fun clearRecentSearches() {
        recentSearches.clear()
        sharedPrefs.edit().putString("recent_searches_universal", "").apply()
    }

    // Popular Searches pre-populated high-yield physiotherapy hot concepts
    val popularSearches = listOf(
        "ACL Rehabilitation",
        "Carpal Tunnel Syndrome",
        "Frozen Shoulder",
        "Trendelenburg Gait",
        "Ulnar Nerve",
        "Strength-Duration Curve"
    )

    // Curated Search Suggestions to assist typing
    val searchSuggestionsList = listOf(
        "Quadriceps",
        "Phalen Test",
        "Sciatica",
        "Bobath",
        "Active Insufficiency",
        "CIMT Therapy",
        "Hemiplegia"
    )

    // Database access
    val database = remember { AppDatabase.getDatabase(context) }
    val dao = remember { database.physioDao() }

    // Repositories Data State
    val anatomiesState = dao.getAllAnatomies().collectAsState(initial = emptyList())
    val mcqsState = dao.getAllVivaMcqs().collectAsState(initial = emptyList())
    val vivaTopicsState = dao.getAllVivaTopics().collectAsState(initial = emptyList())
    val clinicalCasesState = dao.getAllClinicalCases().collectAsState(initial = emptyList())
    val specialTests by androidx.compose.runtime.produceState(initialValue = emptyList<SpecialTestModel>()) {
        value = loadSpecialTests(context)
    }

    val anatomies = if (anatomiesState.value.isNotEmpty()) anatomiesState.value else ContentRepo.initialAnatomies
    val mcqs = if (mcqsState.value.isNotEmpty()) mcqsState.value else ContentRepo.initialVivaQuestions
    val topics = if (vivaTopicsState.value.isNotEmpty()) vivaTopicsState.value else VivaPrepRepo.initialTopics
    val cases = clinicalCasesState.value

    // Auto-Complete dynamic matching suggestions (typed suggestions block)
    val typedSuggestions = remember(query, anatomies, mcqs, topics, specialTests, cases) {
        if (query.isBlank()) emptyList() else {
            val list = mutableListOf<String>()
            anatomies.filter { it.name.contains(query, ignoreCase = true) }.take(2).forEach { list.add(it.name) }
            specialTests.filter { it.test_name.contains(query, ignoreCase = true) }.take(2).forEach { list.add(it.test_name) }
            cases.filter { it.condition.contains(query, ignoreCase = true) }.take(2).forEach { list.add(it.condition) }
            topics.filter { it.title.contains(query, ignoreCase = true) }.take(2).forEach { list.add(it.title) }
            list.distinct().take(5)
        }
    }

    // -- DYNAMIC SEARCH REPOSITORY FILTER ENGINES --
    val filteredAnatomy = remember(query, anatomies) {
        if (query.isBlank()) emptyList() else {
            anatomies.filter { item ->
                item.name.contains(query, ignoreCase = true) ||
                        item.definition.contains(query, ignoreCase = true) ||
                        item.action.contains(query, ignoreCase = true) ||
                        item.origin.contains(query, ignoreCase = true) ||
                        item.insertion.contains(query, ignoreCase = true) ||
                        item.nerveSupply.contains(query, ignoreCase = true) ||
                        item.clinicalImportance.contains(query, ignoreCase = true)
            }
        }
    }

    val filteredMcqs = remember(query, mcqs) {
        if (query.isBlank()) emptyList() else {
            mcqs.filter { item ->
                item.question.contains(query, ignoreCase = true) ||
                        item.correctAnswer.contains(query, ignoreCase = true) ||
                        item.whyCorrect.contains(query, ignoreCase = true) ||
                        item.subject.contains(query, ignoreCase = true) ||
                        item.learningPoint.contains(query, ignoreCase = true) ||
                        item.relatedConcept.contains(query, ignoreCase = true)
            }
        }
    }

    val filteredVivaTopics = remember(query, topics) {
        if (query.isBlank()) emptyList() else {
            topics.filter { item ->
                item.title.contains(query, ignoreCase = true) ||
                        item.category.contains(query, ignoreCase = true) ||
                        item.vivaQuestion.contains(query, ignoreCase = true) ||
                        item.oneLineAnswer.contains(query, ignoreCase = true) ||
                        item.detailedAnswer.contains(query, ignoreCase = true) ||
                        item.clinicalCorrelation.contains(query, ignoreCase = true)
            }
        }
    }

    val filteredSpecialTests = remember(query, specialTests) {
        if (query.isBlank()) emptyList() else {
            specialTests.filter { item ->
                item.test_name.contains(query, ignoreCase = true) ||
                        item.body_region.contains(query, ignoreCase = true) ||
                        item.purpose.contains(query, ignoreCase = true) ||
                        item.clinical_interpretation.contains(query, ignoreCase = true) ||
                        item.positive_sign.contains(query, ignoreCase = true)
            }
        }
    }

    val filteredCases = remember(query, cases) {
        if (query.isBlank()) emptyList() else {
            cases.filter { item ->
                item.condition.contains(query, ignoreCase = true) ||
                        item.chiefComplaint.contains(query, ignoreCase = true) ||
                        item.patientProfile.contains(query, ignoreCase = true) ||
                        item.category.contains(query, ignoreCase = true) ||
                        item.physiotherapyDiagnosis.contains(query, ignoreCase = true)
            }
        }
    }

    val totalMatchesCount = filteredAnatomy.size + filteredMcqs.size + filteredVivaTopics.size + filteredSpecialTests.size + filteredCases.size

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Universal Search",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Search Input Container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { 
                        query = it 
                        if (query.isNotBlank()) {
                            addRecentSearch(query)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("universal_search_input"),
                    placeholder = {
                        Text(
                            text = "Search Anatomy, MCQs, Cases, Tests...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { query = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear Search",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    )
                )

                // Render dynamic typing autocomplete suggestions inline
                if (typedSuggestions.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f))
                    ) {
                        Column {
                            typedSuggestions.forEach { suggestion ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            query = suggestion
                                            addRecentSearch(suggestion)
                                            keyboardController?.hide()
                                        }
                                        .padding(horizontal = 16.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowOutward,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = suggestion,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Category Horizontal Tabs
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(end = 16.dp)
            ) {
                items(UniversalSearchCategory.values()) { category ->
                    val isSelected = selectedCategory == category
                    val matchCount = when (category) {
                        UniversalSearchCategory.ALL -> totalMatchesCount
                        UniversalSearchCategory.ANATOMY -> filteredAnatomy.size
                        UniversalSearchCategory.MCQ_BANK -> filteredMcqs.size
                        UniversalSearchCategory.VIVA_REPOSITORY -> filteredVivaTopics.size
                        UniversalSearchCategory.SPECIAL_TESTS -> filteredSpecialTests.size
                        UniversalSearchCategory.CLINICAL_CASES -> filteredCases.size
                    }

                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedCategory = category },
                        label = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = category.icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (query.isBlank()) category.displayName else "${category.displayName} ($matchCount)",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.testTag("unified_chip_${category.name.lowercase()}")
                    )
                }
            }

            // Main screen content lists
            if (query.isBlank()) {
                // Empty query view: Search suggestions, Recent searches, and Popular searches
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    // 1. Curated Search Suggestions
                    item {
                        Text(
                            text = "SEARCH SUGGESTIONS",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            searchSuggestionsList.forEach { suggestion ->
                                SuggestionChip(
                                    onClick = {
                                        query = suggestion
                                        addRecentSearch(suggestion)
                                        keyboardController?.hide()
                                    },
                                    label = { Text(suggestion) },
                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                                        labelColor = MaterialTheme.colorScheme.primary
                                    ),
                                    border = null
                                )
                            }
                        }
                    }

                    // 2. Recent Searches list
                    if (recentSearches.isNotEmpty()) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "RECENT SEARCHES",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                                    letterSpacing = 1.1.sp
                                )
                                TextButton(
                                    onClick = { clearRecentSearches() },
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = "Clear All",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                        }

                        items(recentSearches) { item ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        query = item
                                        addRecentSearch(item)
                                        keyboardController?.hide()
                                    }
                                    .padding(vertical = 10.dp, horizontal = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                    Icon(
                                        imageVector = Icons.Default.History,
                                        contentDescription = "History Item",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                IconButton(
                                    onClick = { removeRecentSearch(item) },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Delete",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                        }
                    }

                    // 3. Curated Popular Searches section
                    item {
                        Text(
                            text = "POPULAR CLINICAL TOPICS",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.secondary,
                            letterSpacing = 1.1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            popularSearches.forEach { topic ->
                                SuggestionChip(
                                    onClick = {
                                        query = topic
                                        addRecentSearch(topic)
                                        keyboardController?.hide()
                                    },
                                    label = { Text(topic) },
                                    colors = SuggestionChipDefaults.suggestionChipColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f),
                                        labelColor = MaterialTheme.colorScheme.secondary
                                    ),
                                    border = null
                                )
                            }
                        }
                    }
                }
            } else {
                // Non-empty query view: filtered categorical result cards
                val displayAnatomy = selectedCategory == UniversalSearchCategory.ALL || selectedCategory == UniversalSearchCategory.ANATOMY
                val displayMcqs = selectedCategory == UniversalSearchCategory.ALL || selectedCategory == UniversalSearchCategory.MCQ_BANK
                val displayVivaTopics = selectedCategory == UniversalSearchCategory.ALL || selectedCategory == UniversalSearchCategory.VIVA_REPOSITORY
                val displaySpecialTests = selectedCategory == UniversalSearchCategory.ALL || selectedCategory == UniversalSearchCategory.SPECIAL_TESTS
                val displayCases = selectedCategory == UniversalSearchCategory.ALL || selectedCategory == UniversalSearchCategory.CLINICAL_CASES

                val itemsExist = (displayAnatomy && filteredAnatomy.isNotEmpty()) ||
                        (displayMcqs && filteredMcqs.isNotEmpty()) ||
                        (displayVivaTopics && filteredVivaTopics.isNotEmpty()) ||
                        (displaySpecialTests && filteredSpecialTests.isNotEmpty()) ||
                        (displayCases && filteredCases.isNotEmpty())

                if (!itemsExist) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SearchOff,
                                contentDescription = "No results found",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(54.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No results match your search",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Try typing standard syllabus terms such as 'Nerve', 'Gait', 'Stroke', 'Lachman', or 'Sciatica'",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 16.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Total count subtitle
                        item {
                            Text(
                                text = "FOUND $totalMatchesCount MATCHES ACROSS UNIVERSITY REPOSITORIES",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 1.1.sp
                            )
                        }

                        // 1. Anatomy results
                        if (displayAnatomy && filteredAnatomy.isNotEmpty()) {
                            item { ResultHeaderLabel("Anatomy Revision", filteredAnatomy.size) }
                            items(filteredAnatomy) { item ->
                                AnatomyUnifiedCard(item = item, navController = navController)
                            }
                        }

                        // 2. MCQ Bank results
                        if (displayMcqs && filteredMcqs.isNotEmpty()) {
                            item { ResultHeaderLabel("MCQ Bank Practice", filteredMcqs.size) }
                            items(filteredMcqs) { item ->
                                McqUnifiedCard(item = item, navController = navController)
                            }
                        }

                        // 3. Viva Repository results
                        if (displayVivaTopics && filteredVivaTopics.isNotEmpty()) {
                            item { ResultHeaderLabel("Viva Oral Prep", filteredVivaTopics.size) }
                            items(filteredVivaTopics) { item ->
                                VivaTopicUnifiedCard(item = item, navController = navController)
                            }
                        }

                        // 4. Special Test Repository results
                        if (displaySpecialTests && filteredSpecialTests.isNotEmpty()) {
                            item { ResultHeaderLabel("Special Test Repository", filteredSpecialTests.size) }
                            items(filteredSpecialTests) { item ->
                                SpecialTestUnifiedCard(item = item, navController = navController)
                            }
                        }

                        // 5. Clinical Case Repository results
                        if (displayCases && filteredCases.isNotEmpty()) {
                            item { ResultHeaderLabel("Clinical Case Repository", filteredCases.size) }
                            items(filteredCases) { item ->
                                ClinicalCaseUnifiedCard(item = item, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResultHeaderLabel(text: String, count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
            letterSpacing = 1.1.sp
        )
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f))
        ) {
            Text(
                text = "$count MATCHES",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }
    }
}

@Composable
fun AnatomyUnifiedCard(item: DetailedAnatomy, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("anatomy_unified_${item.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
            width = 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Accessibility,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Innervated by: ${item.nerveSupply}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand description",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Definition", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.definition, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Origin", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            Text(text = item.origin, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Insertion", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            Text(text = item.insertion, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Action / Motor Function", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.action, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Clinical Importance", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.clinicalImportance, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { 
                            navController.navigate("topic_hub?query=${item.name}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Open Anatomy Deck in Topic Hub", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun McqUnifiedCard(item: VivaMcq, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("mcq_unified_${item.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
            width = 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFE0F2FE), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.School,
                        contentDescription = null,
                        tint = Color(0xFF0369A1),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.question,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Subject: ${item.subject} • ${item.difficulty}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand description",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = item.question, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(10.dp))

                    item.options.forEach { opt ->
                        val isCorrect = opt == item.correctAnswer
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                                contentDescription = null,
                                tint = if (isCorrect) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = opt,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isCorrect) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurface,
                                fontWeight = if (isCorrect) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Explanation / Rationale", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.whyCorrect, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = item.whyIncorrect, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Examiner Tip", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                    Text(text = item.examinerTip, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { 
                            navController.navigate("topic_hub?query=${item.relatedConcept.ifEmpty { item.question }}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Open MCQ Topic in Topic Hub", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun VivaTopicUnifiedCard(item: VivaTopic, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("viva_topic_unified_${item.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
            width = 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFF3E8FF), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = Color(0xFF9333EA),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Subject category: ${item.category}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand description",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "High-Yield Viva Question", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.vivaQuestion, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "One Line Answer", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.oneLineAnswer, style = MaterialTheme.typography.bodyMedium, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Detailed Academic Explanation", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.detailedAnswer, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Clinical Correlation", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                    Text(text = item.clinicalCorrelation, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { 
                            navController.navigate("topic_hub?query=${item.title}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Open Viva Topic in Topic Hub", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SpecialTestUnifiedCard(item: SpecialTestModel, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("special_test_unified_${item.test_id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
            width = 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFECFDF5), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Healing,
                        contentDescription = null,
                        tint = Color(0xFF059669),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.test_name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Body Region: ${item.body_region}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand description",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Purpose", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.purpose, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Patient Position", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.patient_position, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Therapist Position", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.therapist_position, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Positive Diagnostic Sign", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                    Text(text = item.positive_sign, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Clinical Interpretation", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.clinical_interpretation, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { 
                            navController.navigate("topic_hub?query=${item.test_name}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Open Special Test on Topic Hub", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ClinicalCaseUnifiedCard(item: ClinicalCase, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("clinical_case_unified_${item.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
            width = 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color(0xFFFEF3C7), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = null,
                        tint = Color(0xFFD97706),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.condition,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Category: ${item.category} • Year: ${item.year}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand description",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Patient Profile", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.patientProfile, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Chief Complaint", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(text = item.chiefComplaint, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Physiotherapy Diagnosis", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                    Text(text = item.physiotherapyDiagnosis, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(8.dp))

                    if (item.treatmentPlan.isNotEmpty()) {
                        Text(text = "Treatment Plan Highlights", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                        Text(text = item.treatmentPlan.firstOrNull() ?: "", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(
                        onClick = { 
                            navController.navigate("topic_hub?query=${item.condition}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer, contentColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Open Clinical Case on Topic Hub", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}
