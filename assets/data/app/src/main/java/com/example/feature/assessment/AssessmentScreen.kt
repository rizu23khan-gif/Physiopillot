package com.example.feature.assessment

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.FactCheck
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
fun AssessmentScreen(navController: NavController, context: Context) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredDiagnoses = ContentRepo.diagnoses.filter { diagnosis ->
        diagnosis.name.contains(searchQuery, ignoreCase = true) || 
        diagnosis.relevantAnatomy.any { it.contains(searchQuery, ignoreCase = true) }
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
            var selectedTabIndex by remember { mutableIntStateOf(0) }
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
                            .padding(16.dp),
                        placeholder = { Text("Search Diagnosis") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium
                    )
                    
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(filteredDiagnoses) { diagnosis ->
                            DiagnosisCard(diagnosis = diagnosis, context = context)
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
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.MenuBook,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Practical OSCE Guides",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Learn precise step-by-step subjective and objective clinical examinations according to BPT syllabus guidelines.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                        elevation = CardDefaults.cardElevation(defaultElevation = if (isOaKnee) 2.dp else 0.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isOaKnee) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        ),
                        border = if (isOaKnee) CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))) else null,
                        shape = RoundedCornerShape(16.dp)
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
                                        color = Color.White,
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
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${template.steps.size} clinical checklist steps",
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
        }
    }
}

@Composable
fun DiagnosisCard(diagnosis: DetailedDiagnosis, context: Context) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val dao = remember { AppDatabase.getDatabase(context).physioDao() }
    val progressList by dao.getAllProgress().collectAsState(initial = emptyList())
    val progress = progressList.find { it.assessmentId == diagnosis.id }
    val isCompleted = progress?.isCompleted ?: false

    Card(
        modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded },
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = diagnosis.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { checked ->
                        coroutineScope.launch {
                            dao.updateProgress(ChecklistProgress(diagnosis.id, checked))
                        }
                    }
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }
            
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                
                ExpandableSection("Relevant Anatomy & Physiology") {
                    Text(text = "Anatomy:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.relevantAnatomy)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Physiology:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.relevantPhysiology)
                }
                
                ExpandableSection("Clinical Correlation") {
                    BulletedList(items = diagnosis.clinicalCorrelation)
                }

                ExpandableSection("Pathology") {
                    BulletedList(items = diagnosis.pathology)
                }

                ExpandableSection("Common Drugs Seen") {
                    BulletedList(items = diagnosis.pharmacology)
                }

                if (diagnosis.microbiology.isNotEmpty()) {
                    ExpandableSection("Microbiology") {
                        BulletedList(items = diagnosis.microbiology)
                    }
                }
                
                ExpandableSection("History Taking") {
                    BulletedList(items = diagnosis.historyTaking)
                }
                
                ExpandableSection("Observation & Palpation") {
                    Text(text = "Observation:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.observation)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Palpation:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.palpation)
                }
                
                ExpandableSection("Examination (ROM, MMT, Special Tests)") {
                    Text(text = "ROM:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.rom)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "MMT:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.mmt)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Special Tests:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.specialTests)
                }

                if (diagnosis.neurologicalAssessment.isNotEmpty()) {
                    ExpandableSection("Neurological Assessment") {
                        BulletedList(items = diagnosis.neurologicalAssessment)
                    }
                }

                if (diagnosis.functionalAssessment.isNotEmpty()) {
                    ExpandableSection("Functional Assessment") {
                        BulletedList(items = diagnosis.functionalAssessment)
                    }
                }

                if (diagnosis.clinicalReasoning.isNotBlank()) {
                    ExpandableSection("Clinical Reasoning") {
                        Text(text = diagnosis.clinicalReasoning, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                    }
                }
                
                ExpandableSection("Problem List & Goals") {
                    Text(text = "Problem List:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.problemList)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Short-Term Goals (STG):", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.stg)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Long-Term Goals (LTG):", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.ltg)
                }
                
                ExpandableSection("Physiotherapy Management & Outcome Measures") {
                    Text(text = "PT Management:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.ptManagement)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Outcome Measures:", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    BulletedList(items = diagnosis.outcomeMeasures)
                }

                if (diagnosis.clinicalPostingQuestions.isNotEmpty()) {
                    ExpandableSection("Clinical Posting Questions") {
                        BulletedList(items = diagnosis.clinicalPostingQuestions)
                    }
                }

                if (diagnosis.vivaQuestions.isNotEmpty()) {
                    ExpandableSection("Viva Questions") {
                        BulletedList(items = diagnosis.vivaQuestions)
                    }
                }
                
                com.example.ui.components.ReferencesAccordion(references = diagnosis.references)
            }
        }
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
fun BulletedList(items: List<String>) {
    Column {
        items.forEach { item ->
            Row(modifier = Modifier.padding(bottom = 2.dp, start = 8.dp)) {
                Text("• ", style = MaterialTheme.typography.bodyMedium)
                Text(text = item, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
