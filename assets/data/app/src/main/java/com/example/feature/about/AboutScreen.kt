package com.example.feature.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About This Content") },
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
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "PHYSIOPILOT",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = "Version 1.0.0\nLast updated: Oct 2023",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            SectionContent(
                title = "Purpose",
                content = "PhysioPilot is an educational learning and assessment tool designed for BPT (Bachelor of Physiotherapy) students. It aims to support academic study, clinical posting preparation, and viva revision."
            )
            
            SectionContent(
                title = "Intended Audience",
                content = "BPT Students"
            )
            
            SectionContent(
                title = "Content Framework",
                content = "The clinical assessments and learning modules in this application are based on standard physiotherapy assessment principles."
            )
            
            SectionContent(
                title = "Textbooks Used",
                content = "• Susan B. O’Sullivan – Physical Rehabilitation\n" +
                          "• Umphred’s Neurological Rehabilitation\n" +
                          "• Magee – Orthopedic Physical Assessment\n" +
                          "• Kisner & Colby – Therapeutic Exercise\n" +
                          "• Norkin & Levangie – Joint Structure and Function\n" +
                          "• B.D. Chaurasia Human Anatomy\n" +
                          "• Gray’s Anatomy for Students\n" +
                          "• Moore Clinically Oriented Anatomy\n" +
                          "• Dena Gardiner – Principles of Exercise Therapy\n" +
                          "• Clayton’s Electrotherapy\n" +
                          "• Low & Reed’s Electrotherapy"
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(
                    text = "PhysioPilot is an educational learning and assessment tool designed for physiotherapy students. Clinical decisions should always be based on professional supervision, institutional protocols, and current evidence-based practice.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SectionContent(title: String, content: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
    }
}
