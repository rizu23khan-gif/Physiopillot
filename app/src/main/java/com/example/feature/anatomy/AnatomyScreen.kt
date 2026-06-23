package com.example.feature.anatomy

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.ContentRepo
import com.example.data.DetailedAnatomy
import com.example.data.TopicImageRepository
import com.example.ui.components.EvidenceReferencesCard
import com.example.ui.components.ImageGallery
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnatomyScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedStructureType by remember { mutableStateOf("All Dynamics") }
    
    val context = androidx.compose.ui.platform.LocalContext.current
    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val coroutineScope = rememberCoroutineScope()

    val curriculumProgress by database.physioDao().getAllCurriculumProgress().collectAsState(initial = emptyList())
    val revisedTopics = remember(curriculumProgress) {
        curriculumProgress.filter { it.type == "ANATOMY" && it.isCompleted }.map { it.topicId }.toSet()
    }

    val anatomiesDb by database.physioDao().getAllAnatomies().collectAsState(initial = emptyList())
    val currentAnatomies = if (anatomiesDb.isNotEmpty()) anatomiesDb else ContentRepo.initialAnatomies

    // Derive regions dynamically based on keywords and terms
    val categorisedAnatomies = remember(currentAnatomies) {
        currentAnatomies.map { anatomy ->
            val region = classifyAnatomy(anatomy)
            anatomy to region
        }
    }

    // Filtered lists
    val filteredAnatomies = remember(searchQuery, selectedCategory, selectedStructureType, categorisedAnatomies) {
        categorisedAnatomies.filter { (anatomy, region) ->
            val matchesCategory = (selectedCategory == "All") || (region == selectedCategory)
            
            val matchesStructureType = when (selectedStructureType) {
                "All Dynamics" -> true
                "Muscles" -> anatomy.anatomyType.equals("Muscle", ignoreCase = true)
                "Bones" -> anatomy.anatomyType.equals("Bone", ignoreCase = true)
                "Joints" -> anatomy.anatomyType.equals("Joint", ignoreCase = true)
                "Nerves" -> anatomy.anatomyType.equals("Nerve", ignoreCase = true)
                "Arteries" -> anatomy.anatomyType.equals("Artery", ignoreCase = true)
                "Ligaments" -> anatomy.anatomyType.equals("Ligament", ignoreCase = true)
                else -> true
            }

            val matchesQuery = searchQuery.isBlank() || 
                    anatomy.name.contains(searchQuery, ignoreCase = true) ||
                    anatomy.searchTerms.any { it.contains(searchQuery, ignoreCase = true) } ||
                    anatomy.definition.contains(searchQuery, ignoreCase = true) ||
                    anatomy.origin.contains(searchQuery, ignoreCase = true) ||
                    anatomy.insertion.contains(searchQuery, ignoreCase = true) ||
                    anatomy.nerveSupply.contains(searchQuery, ignoreCase = true) ||
                    anatomy.action.contains(searchQuery, ignoreCase = true) ||
                    anatomy.clinicalImportance.contains(searchQuery, ignoreCase = true) ||
                    anatomy.surfaceAnatomy.contains(searchQuery, ignoreCase = true) ||
                    anatomy.palpationLandmarks.contains(searchQuery, ignoreCase = true) ||
                    anatomy.mnemonic.contains(searchQuery, ignoreCase = true)
            matchesCategory && matchesStructureType && matchesQuery
        }.map { it.first }
    }

    // Interactive stats
    val totalTopics = currentAnatomies.size
    val revisedCount = revisedTopics.size
    val progressPercent = if (totalTopics > 0) revisedCount.toFloat() / totalTopics.toFloat() else 0f

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Anatomy Revision",
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
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            
            // Interactive Progress Tracker at the top of the Screen
            LearningProgressHeader(
                revisedCount = revisedCount,
                totalCount = totalTopics,
                progress = progressPercent
            )

            // Search Bar Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                placeholder = { 
                    Text(
                        "Search anatomy (e.g. 'Deltoid', 'Femur', 'Sciatic')",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    ) 
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear search")
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            // Category scrolling horizontal row
            val categories = listOf("All", "Upper Limb", "Lower Limb", "Trunk & Spine", "Neurovascular")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { cat ->
                    val isSelected = selectedCategory == cat
                    Card(
                        modifier = Modifier
                            .clickable { selectedCategory = cat }
                            .clip(RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                        ),
                        border = if (isSelected) null else BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = cat,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            // Dynamics / Structure Types filtering row
            val structureTypes = listOf("All Dynamics", "Muscles", "Bones", "Joints", "Nerves", "Arteries", "Ligaments")
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                structureTypes.forEach { type ->
                    val isSelected = selectedStructureType == type
                    val colorScheme = MaterialTheme.colorScheme
                    val chipColor = when (type) {
                        "Muscles" -> Color(0xFFEF4444)
                        "Bones" -> Color(0xFF6B7280)
                        "Joints" -> Color(0xFFF59E0B)
                        "Nerves" -> Color(0xFF6366F1)
                        "Arteries" -> Color(0xFFDC2626)
                        "Ligaments" -> Color(0xFF10B981)
                        else -> colorScheme.primary
                    }
                    
                    Card(
                        modifier = Modifier
                            .clickable { selectedStructureType = type }
                            .clip(RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) chipColor else colorScheme.surfaceVariant.copy(alpha = 0.35f)
                        ),
                        border = if (isSelected) null else BorderStroke(0.5.dp, colorScheme.outline.copy(alpha = 0.2f))
                    ) {
                        Text(
                            text = type,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Main anatomy card listing
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (filteredAnatomies.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 48.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                Icons.Default.Info,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "No anatomy modules match your filter.",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    items(filteredAnatomies) { item ->
                        val isRevised = revisedTopics.contains(item.id)
                        val regionTag = classifyAnatomy(item)

                        RedesignedAnatomyCard(
                            item = item,
                            regionTag = regionTag,
                            isRevised = isRevised,
                            onRevisedToggle = {
                                coroutineScope.launch {
                                    com.example.data.ProgressManager.toggleTopicCompletion(
                                        context = context,
                                        topicId = item.id,
                                        isCompleted = !isRevised,
                                        type = "ANATOMY",
                                        title = item.name,
                                        subject = "Anatomy",
                                        year = "1st Year"
                                    )
                                    if (!isRevised) {
                                        com.example.data.ContentRepo.lastViewedAnatomy = item.name
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LearningProgressHeader(revisedCount: Int, totalCount: Int, progress: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "REVISION PROGRESS",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Gamified Anatomy Goals",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "$revisedCount / $totalCount TOPICS",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            
            // Progress Bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = if (progress >= 1.0f) "Outstanding! You mastered the entire syllabus!" 
                       else if (progress > 0.5f) "Excellent! Over half-way through. Keep revising!"
                       else "Tap the checkmark on any study card once you've learned its specs.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RedesignedAnatomyCard(
    item: DetailedAnatomy,
    regionTag: String,
    isRevised: Boolean,
    onRevisedToggle: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedInnerTab by remember { mutableStateOf(0) } // 0: Core Specs, 1: Clinical & Rehab, 2: Surface & Palpation, 3: Board Prep

    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { 
                expanded = !expanded 
                if (expanded) {
                    com.example.data.ContentRepo.lastViewedAnatomy = item.name
                    coroutineScope.launch {
                        com.example.data.ProgressManager.logActivityAndCheckStreak(
                            context = context,
                            topicId = item.id,
                            title = item.name,
                            type = "ANATOMY",
                            subject = "Anatomy",
                            year = "1st Year"
                        )
                    }
                }
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isRevised) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) 
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            ),
            width = if (isRevised) 1.5.dp else 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            
            // Top Header: Name, Category tag, Master Status Checkbox
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    FlowRow(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        
                        // Region tag badge
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = when (regionTag) {
                                    "Upper Limb" -> MaterialTheme.colorScheme.secondaryContainer
                                    "Lower Limb" -> MaterialTheme.colorScheme.tertiaryContainer
                                    "Trunk & Spine" -> MaterialTheme.colorScheme.surfaceVariant
                                    else -> MaterialTheme.colorScheme.primaryContainer
                                }
                            ),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = regionTag.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        // Structure type badge (dynamic color coding)
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = when (item.anatomyType) {
                                    "Muscle" -> Color(0xFFEF4444).copy(alpha = 0.12f)
                                    "Bone" -> Color(0xFF6B7280).copy(alpha = 0.12f)
                                    "Joint" -> Color(0xFFF59E0B).copy(alpha = 0.12f)
                                    "Nerve" -> Color(0xFF6366F1).copy(alpha = 0.12f)
                                    "Artery" -> Color(0xFFDC2626).copy(alpha = 0.12f)
                                    "Ligament" -> Color(0xFF10B981).copy(alpha = 0.12f)
                                    else -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                                }
                            ),
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(0.6.dp, when (item.anatomyType) {
                                "Muscle" -> Color(0xFFEF4444)
                                "Bone" -> Color(0xFF6B7280)
                                "Joint" -> Color(0xFFF59E0B)
                                "Nerve" -> Color(0xFF6366F1)
                                "Artery" -> Color(0xFFDC2626)
                                "Ligament" -> Color(0xFF10B981)
                                else -> MaterialTheme.colorScheme.primary
                            }.copy(alpha = 0.6f))
                        ) {
                            Text(
                                text = item.anatomyType.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = when (item.anatomyType) {
                                    "Muscle" -> Color(0xFFDC2626)
                                    "Bone" -> Color(0xFF374151)
                                    "Joint" -> Color(0xFFB45309)
                                    "Nerve" -> Color(0xFF4338CA)
                                    "Artery" -> Color(0xFF991B1B)
                                    "Ligament" -> Color(0xFF065F46)
                                    else -> MaterialTheme.colorScheme.primary
                                },
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    Text(
                        text = item.definition,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.62f),
                        maxLines = if (expanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                
                // studied check circle (interactive study tracker)
                IconButton(
                    onClick = { onRevisedToggle() },
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        imageVector = if (isRevised) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = "Mark as studied",
                        tint = if (isRevised) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            
            // Animated visibility of expanded contents
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        modifier = Modifier.padding(vertical = 12.dp)
                    )

                    // Card Segmented Inner Web Tabs (4 high-yield categories)
                    TabRow(
                        selectedTabIndex = selectedInnerTab,
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.primary,
                        divider = {},
                        indicator = { tabPositions ->
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedInnerTab]),
                                color = MaterialTheme.colorScheme.primary
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Tab(
                            selected = selectedInnerTab == 0,
                            onClick = { selectedInnerTab = 0 },
                            text = { Text("Core Specs", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium) }
                        )
                        Tab(
                            selected = selectedInnerTab == 1,
                            onClick = { selectedInnerTab = 1 },
                            text = { Text("Clinical Rehab", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium) }
                        )
                        Tab(
                            selected = selectedInnerTab == 2,
                            onClick = { selectedInnerTab = 2 },
                            text = { Text("Surface", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium) }
                        )
                        Tab(
                            selected = selectedInnerTab == 3,
                            onClick = { selectedInnerTab = 3 },
                            text = { Text("Board Viva", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelMedium) }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    when (selectedInnerTab) {
                        0 -> CoreSpecsTab(item)
                        1 -> ClinicalRehabTab(item)
                        2 -> SurfacePalpationTab(item)
                        3 -> BoardVivaPrepTab(item)
                    }

                    // Visual Learning integration for mapped anatomy topics
                    val imageGallery = remember(item.id) {
                        TopicImageRepository.getGalleryForTopic(context, item.id)
                    }
                    if (imageGallery.images.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        ImageGallery(gallery = imageGallery)
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    
                    // Evidence Card integration
                    EvidenceReferencesCard(
                        references = item.references,
                        evidenceLevel = "Premium Academic Reference Atlas"
                    )
                }
            }
            
            // Expand indicator footer
            if (!expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ExpandMore,
                        contentDescription = "Expand",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

// Subcomponent: Segment 1 (Core Structural Specifications Layout - Adaptive for all anatomy types)
@Composable
fun CoreSpecsTab(item: DetailedAnatomy) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val type = item.anatomyType.lowercase()
        
        val originLabel = when {
            type.contains("bone") -> "SKELETAL BASEPOINT"
            type.contains("joint") -> "SOCKET / RECEPTACLE"
            type.contains("nerve") -> "SACRAL / SPINAL ROOTS"
            type.contains("artery") -> "VASCULAR ORIGIN"
            type.contains("ligament") -> "PROXIMAL ANCHOR"
            else -> "ORIGIN ATTACHMENT"
        }
        
        val insertionLabel = when {
            type.contains("bone") -> "BONY ARTICULATIONS"
            type.contains("joint") -> "BALL / HUMERAL CONGRUENCE"
            type.contains("nerve") -> "TERMINAL BRANCHINGS"
            type.contains("artery") -> "SUPPLY TERMINUS"
            type.contains("ligament") -> "DISTAL ANCHOR"
            else -> "INSERTION TARGET"
        }

        val innervationLabel = when {
            type.contains("nerve") -> "NERVE TRANSMISSION TYPE"
            type.contains("artery") -> "PERIVASCULAR SYMPATHETICS"
            else -> "NERVE INNERVATION"
        }

        val actionLabel = when {
            type.contains("bone") -> "LOCOMOTIVE LEVER FUNCTIONS"
            type.contains("joint") -> "KINEMATIC RANGE & VEHICULATIONS"
            type.contains("nerve") -> "MOTOR & SENSORY SENSITIVITIES"
            type.contains("artery") -> "VASCULAR COMMISSIONS"
            type.contains("ligament") -> "MECHANICAL RESTRAINTS"
            else -> "FUNCTIONAL MOTOR ACTION"
        }

        if (item.origin != "N/A" && item.origin.isNotBlank()) {
            SpecsDetailItem(
                label = originLabel,
                value = item.origin,
                icon = Icons.Default.PushPin,
                iconColor = MaterialTheme.colorScheme.primary
            )
        }
        
        if (item.insertion != "N/A" && item.insertion.isNotBlank()) {
            SpecsDetailItem(
                label = insertionLabel,
                value = item.insertion,
                icon = Icons.Default.Flag,
                iconColor = MaterialTheme.colorScheme.secondary
            )
        }
        
        SpecsDetailItem(
            label = innervationLabel,
            value = item.nerveSupply,
            icon = Icons.Default.Psychology,
            iconColor = Color(0xFF6366F1) // Indigo/Neuro color
        )
        
        if (item.bloodSupply != "N/A" && item.bloodSupply.isNotBlank()) {
            SpecsDetailItem(
                label = "VASCULAR BLOOD SUPPLY",
                value = item.bloodSupply,
                icon = Icons.Default.Favorite,
                iconColor = Color(0xFFEF4444) // Vascular Red
            )
        }
        
        SpecsDetailItem(
            label = actionLabel,
            value = item.action,
            icon = Icons.Default.FitnessCenter,
            iconColor = Color(0xFF10B981) // Green / Movement
        )
    }
}

@Composable
fun SpecsDetailItem(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(iconColor.copy(alpha = 0.12f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                letterSpacing = 0.8.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// Subcomponent: Segment 2 (Clinical Relevance, Biomechanics and Injuries)
@Composable
fun ClinicalRehabTab(item: DetailedAnatomy) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Clinical Importance Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25f)
            ),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.MedicalServices,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Clinical Physiotherapy Resonance",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.clinicalImportance,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Biomechanical Importance Card
        val bioValue = item.biomechanicalImportance.ifBlank {
            "Acts as a principal motor lever or biomechanical stabilizer. Regulates joint distribution of force coordinates, maintaining spatial posture alignment during kinetic chains."
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)
            ),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.08f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Architecture,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Biomechanical Force & Leverage",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = bioValue,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Common Pathology Checklists
        if (item.commonConditions.isNotEmpty()) {
            Text(
                text = "ASSOCIATED CONDITIONS & PATHOLOGY",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                letterSpacing = 1.sp,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
            
            item.commonConditions.forEach { cond ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.12f))
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Clinical Warning",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = cond,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

// Subcomponent: Segment 3 (Surface Anatomy, Palpation & Mnemonics)
@Composable
fun SurfacePalpationTab(item: DetailedAnatomy) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        val type = item.anatomyType.lowercase()
        
        // Surface projection
        val surfaceValue = item.surfaceAnatomy.ifBlank {
            "Located proximal to standard joint line markers or regional borders. Identified on healthy targets during isometric contractions or active ranges."
        }
        SpecsDetailItem(
            label = "SURFACE ANATOMY & PROJECTION",
            value = surfaceValue,
            icon = Icons.Default.Visibility,
            iconColor = Color(0xFF0EA5E9)
        )

        // Palpation landmarks
        val palpationValue = item.palpationLandmarks.ifBlank {
            "Palpated through flat bimanual pressure crossing the belly fibers perpendicularly, or along the adjacent joint cleft line."
        }
        SpecsDetailItem(
            label = "CLINICAL PALPATION LANDMARKS",
            value = palpationValue,
            icon = Icons.Default.TouchApp,
            iconColor = Color(0xFFF43F5E)
        )

        // Mnemonic (if any)
        if (item.mnemonic.isNotBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFEF3C7) // Amber light background
                ),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFFBBF24).copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = Color(0xFFD97706),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Learning Mnemonic Hook",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD97706)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = item.mnemonic,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF78350F)
                    )
                }
            }
        }
    }
}

// Subcomponent: Segment 4 (Board Viva Prep & High Yield Revision Box)
@Composable
fun BoardVivaPrepTab(item: DetailedAnatomy) {
    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        
        // High-Yield Quick Revision Summary Box
        val revText = item.quickRevision.ifBlank {
            "Clinical Highpoints: Primary trigger of regional movement. Innervated by the ${item.nerveSupply}. At risk under chronic compression or postural overuse."
        }
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFECFDF5) // Emerald mint tint
            ),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(2.dp, Color(0xFF10B981).copy(alpha = 0.3f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Bolt,
                        contentDescription = null,
                        tint = Color(0xFF059669),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "HIGH-YIELD REVISION SUMMARY BOX",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF047857),
                        letterSpacing = 1.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = revText,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF065F46),
                    lineHeight = 16.sp
                )
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

        // High-Yield Viva Questions
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Quiz,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Direct Viva Examiner Questions",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            
            if (item.vivaQuestions.isEmpty()) {
                Text(
                    text = "• Explain the core anatomical attachments and supply divisions.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "• Distinguish normal ranges and biomechanical compensations on active testing.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            } else {
                item.vivaQuestions.forEach { question ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Help,
                                contentDescription = "?",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = question,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

// Global classifier helper to classify anatomy topics segmentally
fun classifyAnatomy(anatomy: DetailedAnatomy): String {
    val nameLower = anatomy.name.lowercase()
    val searchLower = anatomy.searchTerms.map { it.lowercase() }
    val isNerveOrArtery = nameLower.contains("nerve") || nameLower.contains("artery") || nameLower.contains("plexus") || nameLower.contains("mca")
    if (isNerveOrArtery) return "Neurovascular"
    
    val upperKeywords = listOf("deltoid", "biceps", "triceps", "brachialis", "brachioradialis", "pronator", "supinator", "hand", "wrist", "finger", "shoulder", "scapula", "clavicle", "humerus", "radial", "ulnar", "median", "axillary", "pectoralis", "teres", "infraspinatus", "supraspinatus", "subscapularis", "latissimus")
    val lowerKeywords = listOf("quadriceps", "hamstring", "gluteus", "gastrocnemius", "tibialis", "soleus", "femur", "tibia", "fibula", "foot", "toe", "sciatic", "patella", "hip", "knee", "ankle", "quad", "sartorius", "gracilis", "biceps femoris", "semitendinosus", "semimembranosus", "iliopsoas", "adductor", "peroneus")
    
    if (upperKeywords.any { keyword -> nameLower.contains(keyword) || searchLower.any { s -> s.contains(keyword) } }) {
        return "Upper Limb"
    }
    if (lowerKeywords.any { keyword -> nameLower.contains(keyword) || searchLower.any { s -> s.contains(keyword) } }) {
        return "Lower Limb"
    }
    return "Trunk & Spine"
}
