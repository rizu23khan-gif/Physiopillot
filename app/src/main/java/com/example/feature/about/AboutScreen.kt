package com.example.feature.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.AppTheme
import com.example.data.ThemePreferences
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    val context = LocalContext.current
    val themePreferences = remember { ThemePreferences(context) }
    val currentTheme by themePreferences.themeModeFlow.collectAsState(initial = AppTheme.SYSTEM)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings & About") },
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

            // Theme Settings Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)),
                    width = 1.dp
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Theme Settings",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    
                    ThemeOptionRow(
                        title = "Light Mode",
                        icon = Icons.Default.LightMode,
                        selected = currentTheme == AppTheme.LIGHT,
                        onClick = {
                            coroutineScope.launch {
                                themePreferences.saveThemeMode(AppTheme.LIGHT)
                            }
                        }
                    )
                    
                    ThemeOptionRow(
                        title = "Dark Mode",
                        icon = Icons.Default.DarkMode,
                        selected = currentTheme == AppTheme.DARK,
                        onClick = {
                            coroutineScope.launch {
                                themePreferences.saveThemeMode(AppTheme.DARK)
                            }
                        }
                    )
                    
                    ThemeOptionRow(
                        title = "Follow System",
                        icon = Icons.Default.Settings,
                        selected = currentTheme == AppTheme.SYSTEM,
                        onClick = {
                            coroutineScope.launch {
                                themePreferences.saveThemeMode(AppTheme.SYSTEM)
                            }
                        }
                    )
                }
            }

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
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f)),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.error.copy(alpha = 0.4f)),
                    width = 1.dp
                )
            ) {
                Text(
                    text = "PhysioPilot is an educational learning and assessment tool designed for physiotherapy students. Clinical decisions should always be based on professional supervision, institutional protocols, and current evidence-based practice.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
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

@Composable
fun ThemeOptionRow(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        )
    }
}
