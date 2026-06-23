package com.example.feature.assessment

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.ContentRepo
import com.example.data.DetailedDiagnosis
import com.example.data.AppDatabase
import com.example.data.ChecklistProgress
import com.example.ui.components.EvidenceReferencesCard
import com.example.ui.components.EducationalTopicReferenceCard
import com.example.ui.components.getReferenceForDiagnosis
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class KotlinAssessmentStep(
    val step_id: String,
    val step_number: Int,
    val category: String,
    val title: String,
    val description: String,
    val expected_findings: List<String> = emptyList(),
    val related_tests: List<String> = emptyList(),
    val related_outcomes: List<String> = emptyList()
)

@Serializable
data class KotlinAssessmentTemplate(
    val template_id: String,
    val template_name: String,
    val steps: List<KotlinAssessmentStep>
)

fun loadAssessmentTemplates(context: Context): List<KotlinAssessmentTemplate> {
    return try {
        val text = context.assets.open("assessment_templates.json").bufferedReader().use { it.readText() }
        Json { ignoreUnknownKeys = true }.decodeFromString<List<KotlinAssessmentTemplate>>(text)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AssessmentScreen(navController: NavController, context: Context, initialTab: Int = 0) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedSpecialty by remember { mutableStateOf("All Branches") }

    val database = remember { com.example.data.AppDatabase.getDatabase(context) }
    val assessmentsDb by database.physioDao().getAllAssessments().collectAsState(initial = emptyList())
    val progressList by database.physioDao().getAllProgress().collectAsState(initial = emptyList())
    val currentRawDiagnoses = if (assessmentsDb.isNotEmpty()) assessmentsDb else com.example.data.DataLoader.rawDiagnoses
    
    val diagnosesList = remember(currentRawDiagnoses) {
        currentRawDiagnoses.map { data ->
            DetailedDiagnosis(
                id = data.id,
                name = data.diagnosisName,
                relevantAnatomy = data.relevantAnatomy,
                relevantPhysiology = data.relevantPhysiology,
                clinicalCorrelation = data.clinicalCorrelation,
                pathology = data.pathology,
                pharmacology = data.pharmacology,
                microbiology = data.microbiology,
                historyTaking = data.historyTaking.map { "What: ${it.what} - Why: ${it.why} - Expected: ${it.expected} (${it.sig})" },
                observation = data.observation.map { "What: ${it.what} - Why: ${it.why} - Expected: ${it.expected} (${it.sig})" },
                palpation = data.palpation.map { "What: ${it.what} - Why: ${it.why} - Expected: ${it.expected} (${it.sig})" },
                rom = listOf(
                    "Joints: ${data.romAssessment.joints}",
                    "AROM: ${data.romAssessment.arom}",
                    "PROM: ${data.romAssessment.prom}",
                    "Limitations: ${data.romAssessment.limitations}",
                    "Significance: ${data.romAssessment.significance}"
                ),
                mmt = listOf(
                    "Muscles: ${data.mmtAssessment.muscles}",
                    "Pattern: ${data.mmtAssessment.pattern}",
                    "Interpretation: ${data.mmtAssessment.interpretation}"
                ),
                specialTests = data.specialTests.map { "${it.name}: ${it.purpose}. Positive: ${it.positiveFinding}. Interpretation: ${it.interpretation}." },
                outcomeMeasures = data.outcomeMeasures.map { "${it.name}: ${it.purpose}. Interpretation: ${it.interpretation}." },
                problemList = data.problemList,
                stg = data.goals.shortTerm,
                ltg = data.goals.longTerm,
                ptManagement = data.ptManagement,
                clinicalPostingQuestions = data.clinicalPostingQuestions,
                vivaQuestions = data.vivaQuestions,
                references = listOf("O'Sullivan Physical Rehabilitation", "Magee Orthopedic Physical Assessment", "Kisner & Colby Therapeutic Exercise"),
                functionalAssessment = data.functionalAssessment,
                clinicalReasoning = data.clinicalReasoning,
                neurologicalAssessment = data.neurologicalAssessment.map { 
                    "Tone: ${it.tone} - Reflexes: ${it.reflexes} - Sensation: ${it.sensation} - Coordination: ${it.coordination} - Cranial: ${it.cranial} - Balance: ${it.balance} - Findings: ${it.findings}" 
                }
            )
        }
    }

    val filteredDiagnoses = remember(searchQuery, selectedSpecialty, diagnosesList) {
        diagnosesList.filter { diagnosis ->
            val matchesSearch = diagnosis.name.contains(searchQuery, ignoreCase = true) || 
                    diagnosis.relevantAnatomy.any { it.contains(searchQuery, ignoreCase = true) }
            val matchesSpecialty = if (selectedSpecialty == "All Branches") true else diagnosis.specialty == selectedSpecialty
            matchesSearch && matchesSpecialty
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clinical Assessment") },
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
            var selectedTabIndex by remember(initialTab) { mutableIntStateOf(initialTab) }
            val tabs = listOf("Conditions", "Roadmaps", "Templates")

            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        placeholder = { Text("Search pathology, anatomy...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium
                    )

                    val specialties = listOf("All Branches", "Orthopedic", "Neurological", "Sports Injuries", "Pediatric", "Geriatric", "Cardiopulmonary", "Women's Health & OB/GYN")
                    
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(end = 16.dp)
                    ) {
                        items(specialties) { spec ->
                            val isSelected = selectedSpecialty == spec
                            val specColor = when (spec) {
                                "Orthopedic" -> Color(0xFFEF4444)
                                "Neurological" -> Color(0xFF6366F1)
                                "Sports Injuries" -> Color(0xFF10B981)
                                "Pediatric" -> Color(0xFFF59E0B)
                                "Geriatric" -> Color(0xFF6B7280)
                                "Cardiopulmonary" -> Color(0xFF06B6D4)
                                "Women's Health & OB/GYN" -> Color(0xFFEC4899)
                                else -> MaterialTheme.colorScheme.primary
                            }
                            
                            Card(
                                modifier = Modifier
                                    .clickable { selectedSpecialty = spec }
                                    .clip(RoundedCornerShape(10.dp)),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isSelected) specColor else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                                ),
                                border = if (isSelected) null else BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                            ) {
                                Text(
                                    text = spec,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { navController.navigate("special_tests") },
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
                                ),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.FactCheck,
                                            contentDescription = "Special Tests Icon",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                    
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "SPECIAL TEST REPOSITORY",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary,
                                            letterSpacing = 1.2.sp
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Orthopaedic, Neurological & Sports",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Explore 14 standardized clinical tests with clear purpose, procedure, positive physical signs, and significance.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                        )
                                    }
                                    
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Launch",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { navController.navigate("clinical_cases") },
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.9f)
                                ),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MedicalServices,
                                            contentDescription = "Clinical Cases Icon",
                                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                    
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "CLINICAL CASE REPOSITORY",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.secondary,
                                            letterSpacing = 1.2.sp
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Orthopaedic, Neurology, Cardiopulmonary & Sports",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSecondaryContainer
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Access comprehensive case studies including subject filters, patient history, complete diagnoses, goals, and treatment plans integrated with physical tests.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                                        )
                                    }
                                    
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Launch",
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                        items(filteredDiagnoses) { diagnosis ->
                            val isCompleted = progressList.any { it.assessmentId == diagnosis.id && it.isCompleted }
                            DiagnosisCard(diagnosis = diagnosis, context = context, isCompleted = isCompleted)
                        }
                    }
                }
                1 -> {
                    RoadmapsTabContent(navController = navController)
                }
                2 -> {
                    TemplatesTabContent(context = context)
                }
            }
        }
    }
}

@Composable
fun TemplatesTabContent(context: Context) {
    var selectedTemplate by remember { mutableStateOf<KotlinAssessmentTemplate?>(null) }
    
    if (selectedTemplate == null) {
        TemplateListScreen(
            context = context,
            onTemplateSelected = { selectedTemplate = it }
        )
    } else {
        TemplateDetailScreen(
            template = selectedTemplate!!,
            onBack = { selectedTemplate = null }
        )
    }
}

@Composable
fun TemplateListScreen(context: Context, onTemplateSelected: (KotlinAssessmentTemplate) -> Unit) {
    val templates = remember { loadAssessmentTemplates(context) }
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredTemplates = remember(searchQuery, templates) {
        templates.filter { template ->
            template.template_name.contains(searchQuery, ignoreCase = true) ||
            template.steps.any { step ->
                step.title.contains(searchQuery, ignoreCase = true) ||
                step.description.contains(searchQuery, ignoreCase = true)
            }
        }
    }
    
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            border = CardDefaults.outlinedCardBorder().copy(
                brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                width = 1.dp
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Practical OSCE Guides",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Learn precise step-by-step subjective and objective clinical examinations according to BPT syllabus guidelines.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
        
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Search Templates...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )
        
        if (filteredTemplates.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No assessment templates found.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredTemplates) { template ->
                    val isOaKnee = template.template_name.contains("Osteoarthritis") || template.template_id == "tpl_oa_knee"
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onTemplateSelected(template) },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = androidx.compose.ui.graphics.SolidColor(
                                if (isOaKnee) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                                else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                            ),
                            width = if (isOaKnee) 1.5.dp else 1.dp
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            if (isOaKnee) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Text(
                                        text = "CORE CURRICULUM",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            
                            Text(
                                text = template.template_name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isOaKnee) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.FactCheck,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${template.steps.size} clinical checklist steps",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TemplateDetailScreen(
    template: KotlinAssessmentTemplate,
    onBack: () -> Unit
) {
    val completedStepsMap = remember { mutableStateMapOf<String, Boolean>() }
    
    LaunchedEffect(template) {
        template.steps.forEach { step ->
            if (!completedStepsMap.containsKey(step.step_id)) {
                completedStepsMap[step.step_id] = false
            }
        }
    }
    
    val completedCount = completedStepsMap.values.count { it }
    val totalSteps = template.steps.size
    val progress = if (totalSteps > 0) completedCount.toFloat() / totalSteps else 0.0f
    
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = template.template_name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { completedStepsMap.clear(); template.steps.forEach { completedStepsMap[it.step_id] = false } }) {
                Icon(Icons.Default.RestartAlt, contentDescription = "Reset Progress")
            }
        }
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Assessment Progress Checklist",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$completedCount / $totalSteps Done (${(progress * 100).toInt()}%)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(template.steps) { step ->
                var expanded by remember { mutableStateOf(false) }
                val isChecked = completedStepsMap[step.step_id] ?: false
                val categoryColor = when (step.category.lowercase()) {
                    "subjective" -> Color(0xFF00796B)
                    "observation" -> Color(0xFF3F51B5)
                    "palpation" -> Color(0xFFE64A19)
                    "rom" -> Color(0xFF388E3C)
                    "mmt" -> Color(0xFF7B1FA2)
                    "special_test" -> Color(0xFF1976D2)
                    else -> MaterialTheme.colorScheme.secondary
                }
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.05f) else MaterialTheme.colorScheme.surface
                    ),
                    border = CardDefaults.outlinedCardBorder().copy(
                        brush = androidx.compose.ui.graphics.SolidColor(
                            if (isChecked) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.6f)
                        ),
                        width = if (isChecked) 1.5.dp else 1.dp
                    )
                ) {
                    Column(modifier = Modifier.clickable { expanded = !expanded }) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clickable { completedStepsMap[step.step_id] = !isChecked },
                                contentAlignment = Alignment.Center
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = { completedStepsMap[step.step_id] = it ?: false }
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(4.dp))
                            
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = categoryColor.copy(alpha = 0.12f)),
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.padding(end = 6.dp)
                                    ) {
                                        Text(
                                            text = step.category.uppercase(),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = categoryColor,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            fontSize = 9.sp
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Step ${step.step_number}: ${step.title}",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isChecked) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface,
                                    textDecoration = if (isChecked) TextDecoration.LineThrough else null
                                )
                            }
                            
                            Icon(
                                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        AnimatedVisibility(visible = expanded) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            ) {
                                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                Text(
                                    text = "Clinical Action Description:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = step.description,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(
                                                text = "Expected Clinical Findings:",
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        step.expected_findings.forEach { finding ->
                                            Row(modifier = Modifier.padding(bottom = 4.dp)) {
                                                Text("• ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                                                Text(
                                                    text = finding,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                                                    lineHeight = 16.sp
                                                )
                                            }
                                        }
                                    }
                                }
                                
                                if (step.related_tests.isNotEmpty() || step.related_outcomes.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        step.related_tests.forEach { test ->
                                            SuggestionChip(
                                                onClick = {},
                                                label = { Text("Test: $test", fontSize = 10.sp) }
                                            )
                                        }
                                        step.related_outcomes.forEach { outcome ->
                                            SuggestionChip(
                                                onClick = {},
                                                label = { Text("Outcome: $outcome", fontSize = 10.sp) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                EvidenceReferencesCard(
                    references = getTemplateReferences(template.template_id),
                    evidenceLevel = "BPT Syllabus Guide"
                )
            }
        }
    }
}

fun getTemplateReferences(templateId: String): List<String> {
    return when (templateId) {
        "tpl_stroke_mca" -> listOf(
            "O'Sullivan Physical Rehabilitation (6th Edition) - Chapter on Stroke Rehabilitation",
            "Darcy Umphred's Neurological Rehabilitation - Section on Hemiplegia",
            "Bland's Stroke Therapy: Evidence-Based Stroke Rehabilitation"
        )
        "tpl_sci" -> listOf(
            "O'Sullivan Physical Rehabilitation (6th Edition) - Chapter on Spinal Cord Injury Rehabilitation",
            "Somers FM. Spinal Cord Injury: Functional Rehabilitation",
            "Consortium for Spinal Cord Medicine. Outcomes Following Spinal Cord Injury Clinical Practice Guidelines"
        )
        "tpl_oa_knee" -> listOf(
            "Magee Orthopedic Physical Assessment (6th Edition) - Knee Joint Chapter",
            "Dutton's Orthopaedic Examination, Evaluation, and Intervention - Tibiofemoral Joint",
            "American College of Rheumatology (ACR) Guidelines for Osteoarthritis Management"
        )
        "tpl_frozen_shoulder" -> listOf(
            "Magee Orthopedic Physical Assessment (6th Edition) - Shoulder Joint Chapter",
            "Dutton's Orthopaedic Examination, Evaluation, and Intervention - Glenohumeral Joint",
            "Kelley MJ, et al. Shoulder Pain and Mobility Deficits: Adhesive Capsulitis Clinical Practice Guidelines"
        )
        "tpl_lumbar_disc" -> listOf(
            "Magee Orthopedic Physical Assessment (6th Edition) - Lumbar Spine Chapter",
            "Dutton's Orthopaedic Examination, Evaluation, and Intervention - Lumbar Pathology",
            "Hoppenfeld Physical Examination of the Spine and Extremities"
        )
        else -> listOf(
            "Kisner & Colby Therapeutic Exercise: Foundations and Techniques",
            "Tidy's Physiotherapy Textbook (15th Edition)"
        )
    }
}

@Composable
fun DiagnosisCard(diagnosis: DetailedDiagnosis, context: Context, isCompleted: Boolean) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val dao = remember { AppDatabase.getDatabase(context).physioDao() }

    val specialtyColor = when (diagnosis.specialty) {
        "Orthopedic" -> Color(0xFFEF4444)
        "Neurological" -> Color(0xFF6366F1)
        "Sports Injuries" -> Color(0xFF10B981)
        "Pediatric" -> Color(0xFFF59E0B)
        "Geriatric" -> Color(0xFF6B7280)
        "Cardiopulmonary" -> Color(0xFF06B6D4)
        "Women's Health & OB/GYN" -> Color(0xFFEC4899)
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { 
                expanded = !expanded 
                if (expanded) {
                    coroutineScope.launch {
                        val spec = diagnosis.specialty
                        val sub = when (spec) {
                            "Sports Injuries", "Orthopedic" -> "Orthopedic PT & Sports"
                            "Pediatric", "Neurological" -> "Neurological PT & Pediatrics"
                            "Geriatric", "Cardiopulmonary" -> "Cardiopulmonary PT & Geriatrics"
                            "Women's Health & OB/GYN" -> "Community Rehabilitation & Women's Health"
                            else -> "Orthopedic PT & Sports"
                        }
                        val yr = when (spec) {
                            "Sports Injuries", "Orthopedic" -> "3rd Year"
                            "Pediatric", "Neurological" -> "4th Year"
                            "Geriatric", "Cardiopulmonary" -> "4th Year"
                            "Women's Health & OB/GYN" -> "4th Year"
                            else -> "3rd Year"
                        }
                        com.example.data.ProgressManager.logActivityAndCheckStreak(
                            context = context,
                            topicId = diagnosis.id,
                            title = diagnosis.name,
                            type = "ASSESSMENT",
                            subject = sub,
                            year = yr
                        )
                    }
                }
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isCompleted) specialtyColor.copy(alpha = 0.7f)
                else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
            ),
            width = if (isCompleted) 1.5.dp else 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = diagnosis.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Row(
                        modifier = Modifier.padding(top = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .background(specialtyColor.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = diagnosis.specialty.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = specialtyColor,
                                fontSize = 8.5.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "16 SYLLABUS POINTS",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 8.5.sp,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isCompleted,
                        onCheckedChange = { checked ->
                            coroutineScope.launch {
                                dao.updateProgress(ChecklistProgress(diagnosis.id, checked))
                                val spec = diagnosis.specialty
                                val sub = when (spec) {
                                    "Sports Injuries", "Orthopedic" -> "Orthopedic PT & Sports"
                                    "Pediatric", "Neurological" -> "Neurological PT & Pediatrics"
                                    "Geriatric", "Cardiopulmonary" -> "Cardiopulmonary PT & Geriatrics"
                                    "Women's Health & OB/GYN" -> "Community Rehabilitation & Women's Health"
                                    else -> "Orthopedic PT & Sports"
                                }
                                val yr = when (spec) {
                                    "Sports Injuries", "Orthopedic" -> "3rd Year"
                                    "Pediatric", "Neurological" -> "4th Year"
                                    "Geriatric", "Cardiopulmonary" -> "4th Year"
                                    "Women's Health & OB/GYN" -> "4th Year"
                                    else -> "3rd Year"
                                }
                                com.example.data.ProgressManager.toggleTopicCompletion(
                                    context = context,
                                    topicId = diagnosis.id,
                                    isCompleted = checked,
                                    type = "ASSESSMENT",
                                    title = diagnosis.name,
                                    subject = sub,
                                    year = yr
                                )
                            }
                        },
                        colors = CheckboxDefaults.colors(checkedColor = specialtyColor)
                    )
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        tint = specialtyColor
                    )
                }
            }
            
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(12.dp))

                var activeFolderIndex by remember { mutableIntStateOf(0) }
                val folders = listOf("Profile", "Exam", "Rx Care", "viva Prep")

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    folders.forEachIndexed { idx, folder ->
                        val isSel = activeFolderIndex == idx
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { activeFolderIndex = idx }
                                .clip(RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSel) specialtyColor else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)
                            ),
                            border = if (isSel) null else BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = folder,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                when (activeFolderIndex) {
                    0 -> { // Folder 1: Clinical Profile
                        val definition = diagnosis.pathology.firstOrNull { it.startsWith("Definition:", ignoreCase = true) }
                            ?.substringAfter("Definition:")?.trim() 
                            ?: "An assessment-backed condition affecting neuromusculoskeletal tissue structures, resulting in specific functional limits and movement dysfunction."
                        
                        AcademicSectionHeader("1. Clinical Definition", Icons.Default.MenuBook, specialtyColor)
                        Text(
                            text = definition, 
                            style = MaterialTheme.typography.bodyMedium, 
                            color = MaterialTheme.colorScheme.onSurface, 
                            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
                        )

                        val epidemiology = when (diagnosis.specialty) {
                            "Neurological" -> "Prevalent in mature adults and elderly cohorts; highly associated with cardiovascular conditions, neurovascular compromise, or progressive genetic neural breakdown."
                            "Orthopedic" -> "Epidemiological studies indicate high incidence among physical laborers, postural conformers, or sedentary professionals experiencing chronic joint strain."
                            "Sports Injuries" -> "Exclusively common in teenagers and young active athletes; highly associated with high-impact shear loading, sudden pivots, and rotational athletic trauma."
                            "Pediatric" -> "Congenital focus or developmental onset; affects infants and developing children, widely monitored across school-aged pediatrics for developmental milestones."
                            "Geriatric" -> "Highly prevalent in senior populations older than 65 years; secondary to systemic osteopenia, muscular sarcopenia, or slow neurogenic balancing decay."
                            "Cardiopulmonary" -> "Primary incidence in adults over 45 with cumulative arterial, occupational respiratory, cardiac surgical, or chronic smoke exposure profiles."
                            "Women's Health & OB/GYN" -> "Prevalent in pre/postnatal women, multiparous mothers, and post-menopausal individuals; strongly associated with mechanical and hormonal changes of childbearing or aging."
                            else -> "Widespread across locomotor cohorts experiencing high tissue stress or microtrauma."
                        }
                        
                        AcademicSectionHeader("2. Clinical Epidemiology", Icons.Default.Info, specialtyColor)
                        Text(
                            text = epidemiology, 
                            style = MaterialTheme.typography.bodyMedium, 
                            color = MaterialTheme.colorScheme.onSurface, 
                            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
                        )

                        val riskFactors = diagnosis.pathology.filter { it.contains("Risk Factors:", ignoreCase = true) || it.contains("Etiology:", ignoreCase = true) }
                            .map { it.replace("Risk Factors:", "").replace("Etiology:", "").trim() }
                            .ifEmpty { listOf("Repetitive mechanical loading, joint instability, chronic sedentary cycles, or age-related skeletal tissue remodeling.") }

                        AcademicSectionHeader("3. High-Risk Factors", Icons.Default.Warning, specialtyColor)
                        BulletedList(items = riskFactors, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        val symptoms = diagnosis.clinicalCorrelation.filter { it.contains("Clinical Findings:", ignoreCase = true) }
                            .map { it.replace("Clinical Findings:", "").trim() }
                            .ifEmpty { 
                                diagnosis.historyTaking.map { line ->
                                    if (line.contains("What:")) line.substringAfter("What:").substringBefore(" - Why:")
                                    else line
                                }.take(4) 
                            }
                            .ifEmpty { listOf("Chief complaint of local tenderness, active motor restrictions, and secondary mechanical joint limitations.") }

                        AcademicSectionHeader("4. Hallmark Symptoms", Icons.Default.Info, specialtyColor)
                        BulletedList(items = symptoms, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        val redFlagsForSpecialty = when (diagnosis.specialty) {
                            "Neurological" -> listOf(
                                "Sudden, unexplained deterioration in level of consciousness or sensory-motor faculties.",
                                "Autonomic Dysreflexia signs (pounding headache, severe sweating, bradycardia) in lesions above T6.",
                                "Rapidly progressive bulbar symptoms (dysphagia, dysarthria, respiratory efforts) indicating respiratory failure.",
                                "Severe, crushing headache ('thunderclap') or cerebellar ataxia sign hinting at acute hemorrhage."
                            )
                            "Orthopedic" -> listOf(
                                "Cauda Equina Syndrome indicators: saddle anesthesia, bowel/bladder incontinence, bilateral radicular deficits.",
                                "Inexplicable constant night pain independent of position change (malignancy warning).",
                                "Signs of acute infectious arthritis or osteomyelitis: severe local erythema, high fever, throbbing joint pain.",
                                "Unexplained rapid structural deformity or localized warmth indicating fracture/tumor."
                            )
                            "Sports Injuries" -> listOf(
                                "Complete structural displacement or obvious deformity (fracture/joint dislocation).",
                                "Sign of acute vascular compromise (absent distal pulses, cool pale extremity, severe distal numbness).",
                                "Persistent severe instability under minimal load or locked joint mechanism.",
                                "Signs of compartment syndrome: severe intractable resting pain disproportionate to injury, hard tender compartment."
                            )
                            "Pediatric" -> listOf(
                                "Loss of previously acquired developmental milestones (regression warning).",
                                "Asymmetric or absent neonatal reflexes after standard integration window.",
                                "Signs of pediatric respiratory distress: subcostal retractions, nasal flaring, grunting sounds.",
                                "Acute high fever, neck stiffness, and progressive lethargy (infantile meningitis indicators)."
                            )
                            "Geriatric" -> listOf(
                                "Sudden onset of acute confusion or altered cognitive function (delirium vs dementia, rule out UTI).",
                                "Inability to bear weight after a minor slip or fall (silent femoral neck fracture).",
                                "Unexplained resting dyspnea, sudden tachycardia, or chest pain radiating to left mandible/scapula.",
                                "Sign of DVT: unilateral lower limb warm swelling, intense calf tenderness (positive Homan's test profile)."
                            )
                            "Cardiopulmonary" -> listOf(
                                "Saturations descending below 88% under minimal loaded exercise.",
                                "Persistent radiating crushing chest pain radiating through to the jaw and upper arm.",
                                "Productive coughing with rusty or frank red blood (hemoptysis).",
                                "Presence of sudden extreme pedal edema indicating acute heart failure."
                            )
                            "Women's Health & OB/GYN" -> listOf(
                                "Sudden vaginal bleeding or escape of amniotic fluid during any prenatal therapeutic session.",
                                "Signs of preeclampsia / gestational hypertension (pounding headache, visual disturbances, severe sudden edema).",
                                "Inability to mobilize due to severe, deep pubic symphysis separation click (diastasis symphysis pubis).",
                                "Signs of postpartum pelvic visceral infection: foul-smelling vaginal discharge, heavy bleeding, and high spiking fever."
                            )
                            else -> listOf(
                                "Unremitting night pain independent of mechanical position.",
                                "Systemic symptoms: unexplained weight loss, night sweats, persistent high fever.",
                                "Saddle anesthesia or loss of bladder control in lower spine complaints.",
                                "Rapid progressive motor weakness or sensory loss across multiple dermatomes."
                            )
                        }

                        AcademicSectionHeader("5. Critical Red Flags", Icons.Default.Warning, Color(0xFFEF4444), isWarning = true)
                        BulletedList(items = redFlagsForSpecialty, bulletColor = Color(0xFFEF4444))
                    }
                    1 -> { // Folder 2: Clinic Exam
                        val obs = diagnosis.observation.ifEmpty { listOf("Observe gait patterns, muscular symmetry, and localized protective postures.") }
                        AcademicSectionHeader("6. Clinical Observation", Icons.Default.Visibility, specialtyColor)
                        BulletedList(items = obs, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        val palp = diagnosis.palpation.ifEmpty { listOf("Palpate joint contours, bone alignment, and check for local trigger tenderness.") }
                        AcademicSectionHeader("7. Palpation Findings", Icons.Default.Person, specialtyColor)
                        BulletedList(items = palp, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        AcademicSectionHeader("8. ROM Assessment", Icons.Default.DirectionsRun, specialtyColor)
                        BulletedList(items = diagnosis.rom, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        AcademicSectionHeader("9. Manual Muscle Testing (MMT)", Icons.Default.DirectionsRun, specialtyColor)
                        BulletedList(items = diagnosis.mmt, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        AcademicSectionHeader("10. Special Diagnostic Tests", Icons.Default.FactCheck, specialtyColor)
                        BulletedList(items = diagnosis.specialTests, bulletColor = specialtyColor)
                    }
                    2 -> { // Folder 3: Diagnostics & Care
                        val differentialDiagnosis = when {
                            diagnosis.name.contains("Stroke", ignoreCase = true) -> listOf(
                                "Bell's Palsy (spares upper face, lower motor neuron pattern)",
                                "Todd's Paresis (transient hemiplegia following high focal epilepsy)",
                                "Hypoglycemic Hemiplegia (reversed instantly with infusion of dextrose)",
                                "Brain Tumor / Space Occupying Lesion (progressive onset vs sudden vascular stroke)"
                            )
                            diagnosis.name.contains("Parkinson", ignoreCase = true) -> listOf(
                                "Essential Tremor (action/postural tremor, symmetric, spared gait)",
                                "Drug-Induced Parkinsonism (history of neuroleptics/metoclopramide usage)",
                                "Normal Pressure Hydrocephalus (wet, wobbly, wacky triad - gait, urinary, dementia)",
                                "Progressive Supranuclear Palsy (vertical gaze paralysis, early falls)"
                            )
                            diagnosis.name.contains("Adhesive", ignoreCase = true) || diagnosis.name.contains("Capsulitis", ignoreCase = true) -> listOf(
                                "Rotator Cuff Tear (active ROM restricted but passive ROM remains largely preserved)",
                                "Subacromial Impingement (painful arc between 60-120 degrees, passive ROM full)",
                                "Glenohumeral Osteoarthritis (joint crepitus, joint line narrowing on X-ray)",
                                "Referred Cervical Radiculopathy (radicular pain radiating beyond elbow, positive Spurling's)"
                            )
                            diagnosis.name.contains("ACL", ignoreCase = true) -> listOf(
                                "Meniscal Tear (associated with joint locking, tenderness along joint line)",
                                "MCL/LCL Collateral Sprain (pain elicited with valgus/varus stress tests respectively)",
                                "Tibial Plateau Fracture (inability to bear weight immediately, severe bone swelling)",
                                "Patellar Subluxation/Dislocation (displacement of patella, positive apprehension sign)"
                            )
                            diagnosis.name.contains("Osteoarthritis", ignoreCase = true) -> listOf(
                                "Rheumatoid Arthritis (symmetrical, prolonged morning stiffness >1 hour, systemic involvement)",
                                "Gouty Arthritis (acute onset, severe pain, base of great toe common, hyperuricemia)",
                                "Anserine Bursitis (localized palpation tenderness over medial proximal tibia, normal joint ROM)",
                                "Patellofemoral Pain Syndrome (pain with climbing stairs, tracking error, positive grind test)"
                            )
                            diagnosis.name.contains("Herniation", ignoreCase = true) || diagnosis.name.contains("Lumbar", ignoreCase = true) -> listOf(
                                "Piriformis Syndrome (sciatica-like pain reproducing with passive hip internal rotation and adduction)",
                                "Spinal Stenosis (neurogenic claudication relieved with forward flexion/sitting, older age)",
                                "Sacroiliac Joint Dysfunction (local pain over PSIS, positive Faber's and Gaenslen's tests)",
                                "Trochanteric Bursitis (lateral hip pain over greater trochanter, worse with side sleeping)"
                            )
                            diagnosis.name.contains("Lateral Epicondylitis", ignoreCase = true) -> listOf(
                                "Radial Tunnel Syndrome (tenderness 4cm distal to lateral epicondyle, pain on resisted supination)",
                                "C6 Cervical Radiculopathy (paraesthesia in thumb/index finger, decreased biceps reflex)",
                                "Posterior Interosseous Nerve Entrapment (motor weakness in finger extensors, no sensory loss)",
                                "Humeral Osteochondritis Dissecans (joint crepitus, locking, cartilage defect on MRI)"
                            )
                            diagnosis.name.contains("Carpal Tunnel", ignoreCase = true) -> listOf(
                                "Pronator Teres Syndrome (sensory loss over thenar eminence which is spared in CTS, pain on resisted pronation)",
                                "C6-C7 Radiculopathy (distinguished by cervical neck pain, motor deficits in forearm muscles)",
                                "De Quervain's Tenosynovitis (pain localized to radial styloid, positive Finkelstein's test)",
                                "Double Crush Syndrome (co-existing cervical radiculopathy and peripheral nerve compression)"
                            )
                            diagnosis.specialty == "Sports Injuries" -> listOf(
                                "Acute ligamentous sprain of adjacent joint complexes.",
                                "Insertional tendinopathy or musculotendinous unit strain.",
                                "Stress fracture of associated bony weight-bearing shafts.",
                                "Local bursal distension or painful fat pad impingement."
                            )
                            diagnosis.specialty == "Pediatric" -> listOf(
                                "Congenital neuromusculoskeletal malformation.",
                                "Developmental delay due to secondary sensory-motor deprivations.",
                                "Idiopathic pediatric growth disparities.",
                                "Transient toxic synovitis or systemic pediatric rheumatology signs."
                            )
                            diagnosis.specialty == "Geriatric" -> listOf(
                                "Age-related polyarthrosis or multi-level spinal stenosis.",
                                "Systemic metabolic bone mineral depletion (Osteoporosis/Osteomalacia).",
                                "Chronic muscular sarcopenia with secondary coordinate instability.",
                                "Vascular claudication or pelvic girdle micro-fracture syndrome."
                            )
                            diagnosis.specialty == "Neurological" -> listOf(
                                "Primary central demyelinating disease profile.",
                                "Compressive radiculoneuropathy or plexus irritation.",
                                "Systemic metabolo-diabetic peripheral axonal neuropathy.",
                                "Functional/convertive somatoform movement limitation."
                            )
                            diagnosis.specialty == "Cardiopulmonary" -> listOf(
                                "Primary chronic hyperreactive bronchial constriction (Asthma vs COPD).",
                                "Acute bacterial or atypical fluid-filled consolidation (Pneumonia vs Effusion).",
                                "Vascular thromboembolism blocking bronchial capillaries (Pulmonary Embolism).",
                                "Angina pectoris reflecting localized myocardial ischemia."
                            )
                            diagnosis.specialty == "Women's Health & OB/GYN" -> listOf(
                                "Femoral head avascular necrosis (pregnancy-induced bone density changes).",
                                "Symphysis pubis rupture or deep pelvic fracture.",
                                "Spinal hernia with cauda equina syndrome during pregnancy.",
                                "Urinary Tract Infection or pelvic inflammatory disease."
                            )
                            else -> listOf(
                                "Myofascial pain syndrome with localized trigger point referral.",
                                "Referred facetogenic or discogenic radiculopathy.",
                                "Chronic low-grade inflammatory arthropathy.",
                                "Localized bursitis or nerve entrapment syndrome."
                            )
                        }

                        AcademicSectionHeader("11. Differential Diagnosis", Icons.Default.Info, specialtyColor)
                        BulletedList(items = differentialDiagnosis, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        AcademicSectionHeader("12. Clinical Outcome Measures", Icons.Default.FactCheck, specialtyColor)
                        BulletedList(items = diagnosis.outcomeMeasures, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        AcademicSectionHeader("13. Physiotherapy Management Protocols", Icons.Default.MenuBook, specialtyColor)
                        val fullPtMgmt = diagnosis.ptManagement + 
                                listOf("Short-Term Goals: " + diagnosis.stg.joinToString(", ")) + 
                                listOf("Long-Term Goals: " + diagnosis.ltg.joinToString(", "))
                        BulletedList(items = fullPtMgmt, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        val homeAdviceForSpecialty = when (diagnosis.specialty) {
                            "Neurological" -> listOf(
                                "Remove all loose rugs, cables, and low-level clutter to create clear paths for high-yield fall prevention.",
                                "Incorporate daily weight shifts, active reaching drills, and sitting balance holds at the edge of the bed.",
                                "Reposition paralyzed limbs every 2 hours to avoid severe pressure injury; use soft supportive contour pillows to cushion the heel and sacrum."
                            )
                            "Orthopedic" -> listOf(
                                "Avoid prolonged static sitting or standing beyond 30-40 minutes; gently move the affected joint through pain-free ROM routinely.",
                                "Apply local thermal heat packs (15-20 min) before passive exercises to relax stiff fascia; use ice immediately after training if swelling flares.",
                                "Maintain biomechanical symmetry: wear supportive shock-absorbing footwear."
                            )
                            "Sports Injuries" -> listOf(
                                "Adhere strictly to progressive tissue-healing loading rules; avoid rushing back to sports until lateral jump stability is symmetric.",
                                "Perform non-weight bearing range-of-motion drills early within pain-free boundaries to keep joint fluid flowing and prevent adhesions.",
                                "Apply compression banding during active periods to maintain joint alignment stability and reduce micro-shearing forces."
                            )
                            "Pediatric" -> listOf(
                                "Integrate core motor training into daily routine play (e.g., reaching for favorite toys during supportive prone tummy-time).",
                                "Use pediatric developmental nesting supports to keep infant alignment symmetric during bedtime hours.",
                                "Provide consistent active verbal encouragement, sensory visual triggers, and tactile cues to facilitate motor response."
                            )
                            "Geriatric" -> listOf(
                                "Ensure all home corridors are illuminated with bright night-lights; install sturdy handrails along stairs and bathrooms.",
                                "Engage in safe, low-impact loaded walking or wall squatting 2-3 times daily to combat muscular sarcopenia and preserve bone density.",
                                "Perform active ankle pumps, toe spreads, and seated leg lifts to stimulate general peripheral circulation and prevent deep vein stasis."
                            )
                            "Cardiopulmonary" -> listOf(
                                "Practice parsed pursed-lip breathing and diaphragmatic control drills daily for 10 minutes.",
                                "Regularly use the incentive spirometer to sustain alveolar re-expansion.",
                                "Cease rigorous activities immediately if dizziness, chest tightness, or blue-lip cyanosis appears."
                            )
                            "Women's Health & OB/GYN" -> listOf(
                                "Utilize proper body mechanics for bed mobility (log roll only) and infant carrying to protect the pelvic floor and abdominal wall.",
                                "Perform gentle pelvic tilts, transverse abdominis activations, and pain-free Kegel exercises as tolerated.",
                                "Avoid hyper-loaded breath holding (Valsalva) during lifting; instead, 'exhale on exertion' to control intra-abdominal pressure.",
                                "Observe incisions (abdominal or perineal) daily for redness, heat, swellings, or discharge signs."
                            )
                            else -> listOf(
                                "Perform light, low-impact range exercises within comfortable, pain-free mobility limits to promote cellular repair.",
                                "Avoid rapid static-standing adjustments to prevent vascular pooling and sudden orthostatic lightheadedness.",
                                "Maintain adequate hydration, dynamic posture checks, and supportive ergonomic seating positions."
                            )
                        }

                        AcademicSectionHeader("14. Home Advice & Kinetic Safety Manual", Icons.Default.Home, specialtyColor)
                        BulletedList(items = homeAdviceForSpecialty, bulletColor = specialtyColor)
                    }
                    3 -> { // Folder 4: Board Prep
                        val combinedViva = (diagnosis.vivaQuestions + diagnosis.clinicalPostingQuestions).distinct()
                        AcademicSectionHeader("15. Board & Clinical Viva Prep Questions", Icons.Default.Info, specialtyColor)
                        BulletedList(items = combinedViva, bulletColor = specialtyColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        AcademicSectionHeader("16. Core Textbook Evidence References", Icons.Default.MenuBook, specialtyColor)
                        EvidenceReferencesCard(
                            references = diagnosis.references,
                            evidenceLevel = "Textbook Certified"
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "COMPREHENSIVE EVIDENCE CHECKPOINT",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = specialtyColor,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        EducationalTopicReferenceCard(
                            reference = getReferenceForDiagnosis(
                                id = diagnosis.id,
                                name = diagnosis.name,
                                specialty = diagnosis.specialty,
                                defaultReferences = diagnosis.references
                            )
                        )
                    }
                }
            }
        }
    }
}

val DetailedDiagnosis.specialty: String
    get() = when {
        name.contains("Diastasis Recti", ignoreCase = true) || 
        name.contains("Incontinence", ignoreCase = true) || 
        name.contains("Pelvic", ignoreCase = true) || 
        name.contains("Postnatal", ignoreCase = true) || 
        name.contains("Cesarean", ignoreCase = true) -> "Women's Health & OB/GYN"

        name.contains("ACL", ignoreCase = true) || 
        name.contains("Sprain", ignoreCase = true) -> "Sports Injuries"
        
        name.contains("Cerebral Palsy", ignoreCase = true) || 
        name.contains("Duchenne", ignoreCase = true) || 
        name.contains("Spina Bifida", ignoreCase = true) || 
        name.contains("Down Syndrome", ignoreCase = true) || 
        name.contains("Erb's Palsy", ignoreCase = true) || 
        name.contains("Osteogenesis", ignoreCase = true) || 
        name.contains("Torticollis", ignoreCase = true) || 
        name.contains("Juvenile", ignoreCase = true) || 
        name.contains("Autism", ignoreCase = true) || 
        name.contains("Coordination", ignoreCase = true) -> "Pediatric"

        name.contains("Fall Risk", ignoreCase = true) || 
        name.contains("Osteoporosis", ignoreCase = true) || 
        name.contains("Dementia", ignoreCase = true) || 
        name.contains("Alzheimer", ignoreCase = true) || 
        name.contains("Arthroplasty", ignoreCase = true) || 
        name.contains("Sarcopenia", ignoreCase = true) || 
        name.contains("Kyphosis", ignoreCase = true) || 
        name.contains("Fracture", ignoreCase = true) || 
        name.contains("Polio", ignoreCase = true) || 
        name.contains("Deconditioning", ignoreCase = true) -> "Geriatric"

        id.contains("neu", ignoreCase = true) || 
        name.contains("Stroke", ignoreCase = true) || 
        name.contains("Parkinson", ignoreCase = true) || 
        name.contains("Sclerosis", ignoreCase = true) || 
        name.contains("Spinal Cord", ignoreCase = true) || 
        name.contains("Guillain", ignoreCase = true) || 
        name.contains("Brain Injury", ignoreCase = true) || 
        name.contains("Ataxia", ignoreCase = true) || 
        name.contains("Palsy", ignoreCase = true) || 
        name.contains("Neuropathy", ignoreCase = true) -> "Neurological"

        name.contains("COPD", ignoreCase = true) || 
        name.contains("Asthma", ignoreCase = true) || 
        name.contains("Fibrosis", ignoreCase = true) || 
        name.contains("Pneumonia", ignoreCase = true) || 
        name.contains("Infarction", ignoreCase = true) || 
        name.contains("CABG", ignoreCase = true) || 
        name.contains("Effusion", ignoreCase = true) || 
        name.contains("Bronchiectasis", ignoreCase = true) || 
        name.contains("Failure", ignoreCase = true) || 
        name.contains("Arterial", ignoreCase = true) -> "Cardiopulmonary"

        else -> "Orthopedic"
    }

@Composable
fun AcademicSectionHeader(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    isWarning: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                if (isWarning) color.copy(alpha = 0.08f) else color.copy(alpha = 0.05f),
                RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = color,
            letterSpacing = 0.5.sp,
            fontSize = 10.sp
        )
    }
}

@Composable
fun ExpandableSection(title: String, content: @Composable () -> Unit) {
    var sectionExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { sectionExpanded = !sectionExpanded }.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Icon(
                imageVector = if (sectionExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        if (sectionExpanded) {
            content()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun BulletedList(items: List<String>, bulletColor: Color = MaterialTheme.colorScheme.primary) {
    Column {
        items.forEach { item ->
            Row(modifier = Modifier.padding(bottom = 3.dp, start = 8.dp)) {
                Text("• ", style = MaterialTheme.typography.bodyMedium, color = bulletColor, fontWeight = FontWeight.Bold)
                Text(text = item, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
