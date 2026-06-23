package com.example.feature.anatomy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class BodyHotspot(val region: String, val xPercent: Float, val yPercent: Float)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnatomyNavigatorScreen(navController: NavHostController) {
    // Relative coordinates for the regions (placeholder layout)
    val hotspots = listOf(
        BodyHotspot("Head", 0.5f, 0.15f),
        BodyHotspot("Neck", 0.5f, 0.22f),
        BodyHotspot("Shoulder", 0.7f, 0.28f),
        BodyHotspot("Thorax", 0.5f, 0.35f),
        BodyHotspot("Spine", 0.55f, 0.4f),
        BodyHotspot("Arm", 0.8f, 0.48f),
        BodyHotspot("Elbow", 0.75f, 0.55f),
        BodyHotspot("Hand", 0.85f, 0.65f),
        BodyHotspot("Pelvis", 0.5f, 0.48f),
        BodyHotspot("Hip", 0.6f, 0.5f),
        BodyHotspot("Knee", 0.5f, 0.73f),
        BodyHotspot("Ankle", 0.52f, 0.9f),
        BodyHotspot("Foot", 0.55f, 0.97f)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Anatomy Navigator") }) }
    ) { padding ->
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            // Placeholder: Placeholder shape layout
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Body Layout Placeholder",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Overlay hotspots
            hotspots.forEach { hotspot ->
                Box(
                    modifier = Modifier
                        .offset(
                            x = (maxWidth * hotspot.xPercent) - 20.dp,
                            y = (maxHeight * hotspot.yPercent) - 20.dp
                        )
                        .size(40.dp)
                        .clickable { navController.navigate("region_dashboard/${hotspot.region}") }
                ) {
                    // Visual indicator for hotspot
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        shape = androidx.compose.foundation.shape.CircleShape,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                        border = androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.onPrimary)
                    ) {}
                }
            }
        }
    }
}
