package com.example.feature.reference

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui.components.*
import com.example.data.ContentRepo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReferenceHubScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTextbookFilter by remember { mutableStateOf("All Sources") }
    var selectedEvidenceFilter by remember { mutableStateOf("All Levels") }
    
    val context = androidx.compose.ui.platform.LocalContext.current
    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val referenceTopicsDb by database.physioDao().getAllLibraryReferences().collectAsState(initial = emptyList())
    val referenceTopicsList = if (referenceTopicsDb.isNotEmpty()) referenceTopicsDb else ContentRepo.initialLibraryReferences

    // List of standard reference categories for filter navigation
    val textbookFilters = listOf("All Sources", "Magee", "Hoppenfeld", "O'Sullivan", "Kisner & Colby", "Norkin & Levangie", "Brunnstrom", "WBUHS")
    val evidenceFilters = listOf("All Levels", "Level I", "Level II", "Level III", "Level IV", "Level V")

    val filteredTopics = remember(searchQuery, selectedTextbookFilter, selectedEvidenceFilter, referenceTopicsList) {
        referenceTopicsList.filter { topic ->
            val matchesSearch = topic.title.contains(searchQuery, ignoreCase = true) ||
                    topic.category.contains(searchQuery, ignoreCase = true) ||
                    topic.quickEvidenceSummary.contains(searchQuery, ignoreCase = true) ||
                    topic.referenceTextbooks.any { it.contains(searchQuery, ignoreCase = true) }
            
            val matchesTextbook = if (selectedTextbookFilter == "All Sources") {
                true
            } else {
                topic.referenceTextbooks.any { it.contains(selectedTextbookFilter, ignoreCase = true) }
            }
            
            val matchesEvidence = when (selectedEvidenceFilter) {
                "Level I" -> topic.evidenceLevel == EvidenceLevel.LEVEL_I
                "Level II" -> topic.evidenceLevel == EvidenceLevel.LEVEL_II
                "Level III" -> topic.evidenceLevel == EvidenceLevel.LEVEL_III
                "Level IV" -> topic.evidenceLevel == EvidenceLevel.LEVEL_IV
                "Level V" -> topic.evidenceLevel == EvidenceLevel.LEVEL_V
                else -> true
            }
            
            matchesSearch && matchesTextbook && matchesEvidence
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Evidence & References Hub", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Reference-grade EBM & Curriculum Standards",
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
                ),
                actions = {
                    IconButton(onClick = { 
                        searchQuery = ""
                        selectedTextbookFilter = "All Sources"
                        selectedEvidenceFilter = "All Levels"
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reset Filters")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // High-Yield Reference Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoStories,
                            contentDescription = "Textbook Icon",
                            tint = Color.White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "HEALTHCARE KNOWLEDGE ARCHITECTURE",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 0.8.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Verify clinical protocols against certified syllabus books and Oxford EBM levels of clinical proof.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            // Search Filter Panel
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Search academic references & topics...", fontSize = 13.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search icon", tint = MaterialTheme.colorScheme.primary) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear search")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            // Horizontal Filter Scroll 1 - Syllabus Textbook Publishers
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(textbookFilters) { filter ->
                    val isSelected = selectedTextbookFilter == filter
                    val brandColor = if (filter == "All Sources") {
                        MaterialTheme.colorScheme.primary
                    } else {
                        getTextbookMetadata(filter).color
                    }
                    
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedTextbookFilter = filter },
                        label = { Text(filter, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = brandColor,
                            selectedLabelColor = Color.White
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            selected = isSelected,
                            enabled = true,
                            borderColor = brandColor.copy(alpha = 0.3f),
                            selectedBorderColor = brandColor
                        )
                    )
                }
            }

            // Horizontal Filter Scroll 2 - Oxford Evidence Levels
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(evidenceFilters) { levelFilter ->
                    val isSelected = selectedEvidenceFilter == levelFilter
                    val chipColor = when (levelFilter) {
                        "Level I" -> EvidenceLevel.LEVEL_I.badgeColor
                        "Level II" -> EvidenceLevel.LEVEL_II.badgeColor
                        "Level III" -> EvidenceLevel.LEVEL_III.badgeColor
                        "Level IV" -> EvidenceLevel.LEVEL_IV.badgeColor
                        "Level V" -> EvidenceLevel.LEVEL_V.badgeColor
                        else -> MaterialTheme.colorScheme.secondary
                    }
                    
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedEvidenceFilter = levelFilter },
                        label = { Text(levelFilter, fontSize = 12.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = chipColor,
                            selectedLabelColor = Color.White
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            selected = isSelected,
                            enabled = true,
                            borderColor = chipColor.copy(alpha = 0.3f),
                            selectedBorderColor = chipColor
                        )
                    )
                }
            }

            // Lazy List of detailed referencing cards
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 24.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (filteredTopics.isEmpty()) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 44.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.FindInPage,
                                contentDescription = "Not found",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                                modifier = Modifier.size(56.dp)
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "No References Match Your Criteria",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Try modifying your search query or clearing active textbook filters above to review original clinical standards.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                        }
                    }
                } else {
                    items(filteredTopics) { refItem ->
                        EducationalTopicReferenceCard(reference = refItem)
                    }
                }
                
                // Oxford Evidentiary Hierarchy Mini Guide
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f), RoundedCornerShape(16.dp))
                            .border(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoStories,
                                contentDescription = "Oxford EBM Guide",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "OXFORD SYSTEM OF EVIDENCE LEVELS",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 0.5.sp
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Standard clinical decisions inside physio guidelines follow sequential tiers of proof. Our evidence quality badges represent standard systematic protocols:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 16.sp
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        
                        EbmGuidelineRow(title = "Level I: Meta-Analysis", text = "Cochrane-grade database pooling that offers the highest statistical power for therapeutics.", color = EvidenceLevel.LEVEL_I.badgeColor)
                        EbmGuidelineRow(title = "Level II: Randomized Controlled Trial", text = "Blinded research trials removing clinical preconceptions to measure discrete results.", color = EvidenceLevel.LEVEL_II.badgeColor)
                        EbmGuidelineRow(title = "Level III: Cohort / Case-Control", text = "Observational tracking assessing physical interventions symmetrically against cohorts.", color = EvidenceLevel.LEVEL_III.badgeColor)
                        EbmGuidelineRow(title = "Level IV: Case Series", text = "Syllabus reports showing early implementation details in clinics.", color = EvidenceLevel.LEVEL_IV.badgeColor)
                        EbmGuidelineRow(title = "Level V: Textbook Consensus / Syllabus", text = "Expert practitioner guidelines and core WBUHS academic syllabi criteria.", color = EvidenceLevel.LEVEL_V.badgeColor)
                    }
                }
            }
        }
    }
}

@Composable
fun EbmGuidelineRow(
    title: String,
    text: String,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        }
    }
}
