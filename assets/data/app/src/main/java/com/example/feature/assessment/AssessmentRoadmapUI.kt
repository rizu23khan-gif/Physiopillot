package com.example.feature.assessment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.*
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun RoadmapsTabContent(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredGeneral = RoadmapRepo.generalAssessment.filter {
        it.title.contains(searchQuery, ignoreCase = true) || it.purpose.contains(searchQuery, ignoreCase = true)
    }
    val filteredRoadmaps = RoadmapRepo.roadmaps.filter {
        it.diagnosisName.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search Roadmaps") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = MaterialTheme.shapes.medium
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Level 1: General Assessment",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Standard BPT subjective and objective examination flow.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            
            items(filteredGeneral) { step ->
                GeneralAssessmentCard(step = step)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Level 2: Diagnosis-Specific Roadmaps",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(filteredRoadmaps) { roadmap ->
                DiagnosisRoadmapCard(roadmap = roadmap)
            }
        }
    }
}

@Composable
fun GeneralAssessmentCard(step: GeneralAssessmentStep) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = step.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(8.dp))

                SectionField("Purpose", step.purpose)
                SectionField("What To Assess", step.whatToAssess)
                SectionField("Why It Is Important", step.whyItIsImportant)
                SectionField("Common Findings", step.commonFindings)
            }
        }
    }
}

@Composable
fun DiagnosisRoadmapCard(roadmap: AssessmentRoadmap) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = roadmap.diagnosisName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                
                roadmap.steps.forEach { step ->
                    AssessmentStepCard(step)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                if (roadmap.specialTests.isNotEmpty()) {
                    ExpandableSectionRoadmap("Special Tests") {
                        roadmap.specialTests.forEach { test ->
                            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                                Text(text = test.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Purpose: ${test.purpose}", style = MaterialTheme.typography.bodySmall)
                                Text(text = "Positive: ${test.positiveFinding}", style = MaterialTheme.typography.bodySmall)
                                Text(text = "Interpretation: ${test.interpretation}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }

                if (roadmap.outcomeMeasures.isNotEmpty()) {
                    ExpandableSectionRoadmap("Outcome Measures") {
                        roadmap.outcomeMeasures.forEach { om ->
                            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                                Text(text = om.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Purpose: ${om.purpose}", style = MaterialTheme.typography.bodySmall)
                                Text(text = "Interpretation: ${om.interpretation}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }

                ExpandableSectionRoadmap("Problem List") {
                    Text("Impairments:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                    BulletedListRoadmap(roadmap.impairments)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Activity Limitations:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                    BulletedListRoadmap(roadmap.activityLimitations)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Participation Restrictions:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                    BulletedListRoadmap(roadmap.participationRestrictions)
                }

                ExpandableSectionRoadmap("Goal Setting") {
                    Text("Short-Term Goals:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                    BulletedListRoadmap(roadmap.shortTermGoals)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Long-Term Goals:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
                    BulletedListRoadmap(roadmap.longTermGoals)
                }

                ExpandableSectionRoadmap("Treatment Planning Link") {
                    Text(text = roadmap.treatmentLink, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun AssessmentStepCard(step: AssessmentStep) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = step.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            SectionField("What to Assess", step.whatToAssess)
            SectionField("Why to Assess", step.whyToAssess)
            SectionField("How to Assess", step.howToAssess)
            SectionField("Expected Findings", step.expectedFindings, MaterialTheme.colorScheme.primary)
            SectionField("Clinical Significance", step.clinicalSignificance, MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun SectionField(label: String, value: String, color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label + ":", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodySmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, color = color)
    }
}

@Composable
fun ExpandableSectionRoadmap(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded }.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        if (expanded) {
            content()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun BulletedListRoadmap(items: List<String>) {
    Column {
        items.forEach { item ->
            Row(modifier = Modifier.padding(bottom = 2.dp, start = 8.dp)) {
                Text("• ", style = MaterialTheme.typography.bodyMedium)
                Text(text = item, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
