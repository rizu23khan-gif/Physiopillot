package com.example.feature.anatomy

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

// High-Yield metadata for clinical physiotherapy students
data class BodyHotspot(
    val id: Int,
    val title: String,
    val region: String,
    val xPercent: Float,
    val yPercent: Float,
    val description: String,
    val keyBones: String,
    val keyMuscles: String,
    val clinicalApplications: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnatomyNavigatorScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    // The 13 critical anatomical regions identified in physical therapy curriculum
    val hotspots = remember {
        listOf(
            BodyHotspot(
                id = 1,
                title = "Head & Cranium",
                region = "Head",
                xPercent = 0.50f,
                yPercent = 0.08f,
                description = "Skull, cranial fossae segments, facial nerve pathways (CN VII), and temporomandibular kinesis.",
                keyBones = "Frontal, Parietal, Temporal, Occipital, Sphenoid, Mandible, Maxilla",
                keyMuscles = "Temporalis, Masseter, Lateral/Medial Pterygoids, Buccinator, Orbicularis Oris/Oculi",
                clinicalApplications = "Bell's Palsy, TMJ Dysfunction, Cranial nerve testing, Basilar skull fractures"
            ),
            BodyHotspot(
                id = 2,
                title = "Cervical Spine",
                region = "Cervical Spine",
                xPercent = 0.49f,
                yPercent = 0.15f,
                description = "C1-C7 lordotic spinal column guiding critical vertebral arteries and cervical spinal segments.",
                keyBones = "Atlas (C1), Axis (C2), C3-C7 typical/atypical cervical vertebrae",
                keyMuscles = "Sternocleidomastoid, Scalenes, Splenius Capitis, Longus Colli, Upper Trapezius",
                clinicalApplications = "Cervical Radiculopathy, Whiplash hyperextension, Vertebrobasilar Insufficiency (VBI)"
            ),
            BodyHotspot(
                id = 3,
                title = "Shoulder Complex",
                region = "Shoulder",
                xPercent = 0.65f,
                yPercent = 0.20f,
                description = "Multi-joint pectoral girdle (GH, AC, SC, ST joints) with maximum freedom of motion.",
                keyBones = "Clavicle, Scapula (glenoid, acromion), Humerus proximal head",
                keyMuscles = "Rotator Cuff (Supraspinatus, Infraspinatus, Teres Minor, Subscapularis), Deltoids",
                clinicalApplications = "Adhesive Capsulitis (Frozen Shoulder), Rotator Cuff impingement, Scapular Winging"
            ),
            BodyHotspot(
                id = 4,
                title = "Thoracic Spine",
                region = "Thoracic Spine",
                xPercent = 0.51f,
                yPercent = 0.28f,
                description = "T1-T12 thoracic vertebrae anchoring the cage mechanics and respiration movement systems.",
                keyBones = "T1-T12 vertebrae, Rib cage (1-12), Sternum (manubrium, xiphoid)",
                keyMuscles = "Diaphragm, Intercostales (externi/interni), Erector Spinae, Rhomboids",
                clinicalApplications = "Thoracic Outlet Syndrome (TOS), Kyphosis, Rib fractures breathing compromises"
            ),
            BodyHotspot(
                id = 5,
                title = "Lumbar Spine",
                region = "Lumber Spine",
                xPercent = 0.49f,
                yPercent = 0.38f,
                description = "Five massive vertebrae supporting trunk load transmission and cauda equina networks.",
                keyBones = "L1-L5 lumbar vertebrae segments, Sacral base",
                keyMuscles = "Psoas Major, Quadratus Lumborum, Multifidus, Abdominal Transversus",
                clinicalApplications = "Lumbar Disc Herniation, Sciatica radiculitis, Lower Crossed Syndrome, Stenosis"
            ),
            BodyHotspot(
                id = 6,
                title = "Elbow Complex",
                region = "Elbow",
                xPercent = 0.72f,
                yPercent = 0.35f,
                description = "Hinge and pivot connections managing forearm flexion/extension and rotational kinesis (pronation).",
                keyBones = "Distal humerus (trochlea, capitulum), Radius proximal head, Ulna olecranon",
                keyMuscles = "Biceps Brachii, Brachialis, Triceps Brachii, Pronator Teres, Brachioradialis",
                clinicalApplications = "Lateral Epicondylitis (Tennis Elbow), Medial Epicondylitis (Golfer's Elbow)"
            ),
            BodyHotspot(
                id = 7,
                title = "Pelvis & Girdle",
                region = "Pelvis",
                xPercent = 0.50f,
                yPercent = 0.46f,
                description = "Bony pelvic ring acting as the core force distribution block between upper trunk and lower limbs.",
                keyBones = "Ilium (iliac crest), Ischium, Pubis, Sacrum, Coccyx",
                keyMuscles = "Pelvic Floor (Levator Ani), Piriformis, Iliopsoas segment, Gluteals",
                clinicalApplications = "Sacroiliac (SI) Joint Dysfunction, Pelvic tilt deviations, Piriformis syndrome"
            ),
            BodyHotspot(
                id = 8,
                title = "Hip Joint Complex",
                region = "Hip",
                xPercent = 0.59f,
                yPercent = 0.50f,
                description = "Stable multi-axial ball-socket weight-bearing joint controlling lower limb sway and stride.",
                keyBones = "Pelvic Acetabulum, Femur head and anatomical/surgical necks",
                keyMuscles = "Gluteus Medius/Minimus/Maximus, Hamstring origin, Adductor sheets, Sartorius",
                clinicalApplications = "Trendelenburg positive sign, Hip Osteoarthritis, Femoroacetabular Impingement (FAI)"
            ),
            BodyHotspot(
                id = 9,
                title = "Wrist Joint",
                region = "Wrist",
                xPercent = 0.78f,
                yPercent = 0.54f,
                description = "Condyloid articulation governing precise wrist range of motion and grip power biomechanics.",
                keyBones = "Distal Radius, Scaphoid, Lunate, Triquetrum, Pisiform, Hamate, Capitate",
                keyMuscles = "Flexor Carpi Radialis/Ulnaris, Extensor Carpi Radialis Longus/Brevis, Palmaris",
                clinicalApplications = "Carpal Tunnel Syndrome, Colles' Fracture, Scaphoid avascular necrosis"
            ),
            BodyHotspot(
                id = 10,
                title = "Hand & Fingers",
                region = "Hand",
                xPercent = 0.82f,
                yPercent = 0.62f,
                description = "Intercalated intrinsic muscle networks controlling grip, opposition, and manipulation.",
                keyBones = "Metacarpals (1-5), Proximal/Middle/Distal phalanges",
                keyMuscles = "Thenar and Hypothenar bundles, Lumbricals, Intrinsic Interossei (PAD and DAB)",
                clinicalApplications = "Rheumatoid Arthritis deformities (Swan Neck, Boutonniere), Trigger Finger"
            ),
            BodyHotspot(
                id = 11,
                title = "Knee Joint Complex",
                region = "Knee",
                xPercent = 0.58f,
                yPercent = 0.72f,
                description = "Crucial tibiofemoral and patellofemoral joints managing loaded knee flexion and leg extensions.",
                keyBones = "Distal Femur, Sesamoid Patella, Proximal Tibia plateaus, Fibula head",
                keyMuscles = "Quadriceps (Rectus Femoris, Vastus Medialis/Lateralis), Hamstrings, Gastrocs",
                clinicalApplications = "ACL/PCL ligament tears, Meniscal lesions, Patellofemoral Pain (Runner's Knee)"
            ),
            BodyHotspot(
                id = 12,
                title = "Ankle Joint",
                region = "Ankle",
                xPercent = 0.56f,
                yPercent = 0.88f,
                description = "Talocrural mortise hinge stabilizing ground kinetic impacts and governing load drives.",
                keyBones = "Medial Malleolus of Tibia, Lateral Malleolus of Fibula, Talus",
                keyMuscles = "Tibialis Anterior, Gastrocnemius, Soleus, Peroneus Longus/Brevis",
                clinicalApplications = "Lateral Ankle Sprains (ATFL/CFL ligaments), Achilles tendonitis, Flat feet"
            ),
            BodyHotspot(
                id = 13,
                title = "Foot & Arches",
                region = "Foot",
                xPercent = 0.58f,
                yPercent = 0.94f,
                description = "Tri-planar support arches distributing dynamic loaded ground reaction forces during stride.",
                keyBones = "Calcaneus heel, Navicular bone, Cuneiform joints, Metatarsals (1-5)",
                keyMuscles = "Tibialis Posterior, Flexor Hallucis Longus, Abductor Hallucis, Plantar Aponeurosis",
                clinicalApplications = "Plantar Fasciitis chronic strain, Pes Planus (Flat Foot) over-pronation, Pes Cavus"
            )
        )
    }

    // Active selection trackers
    var selectedHotspotId by remember { mutableStateOf(1) }
    val activeHotspot = remember(selectedHotspotId) { hotspots.first { it.id == selectedHotspotId } }

    val themeColor = Color(0xFFD97706) // 2nd Year orange accent
    val hudGold = Color(0xFFFF9F1C)
    val hudPurple = Color(0xFF8A2BE2)

    // Glowing pulsation animation for active indicators
    val infiniteTransition = rememberInfiniteTransition(label = "AnatomyPulse")
    val animScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RadiusScaler"
    )
    val animAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "AlphaFader"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Anatomy Navigator",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Interactive Biomechanical Region Hub",
                            style = MaterialTheme.typography.labelMedium,
                            color = themeColor,
                            fontWeight = FontWeight.Bold
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
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(selectedHotspotId - 1)
                        }
                    }) {
                        Icon(Icons.Default.MyLocation, contentDescription = "Sync View", tint = themeColor)
                    }
                }
            )
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF0B0F19)) // Custom clinical deep space dark background
        ) {
            val isWideScreen = maxWidth > 600.dp

            if (isWideScreen) {
                // Wide Landscape Split Layout
                Row(modifier = Modifier.fillMaxSize()) {
                    // Left HUD Vector Visualizer Panel
                    Box(
                        modifier = Modifier
                            .weight(1.1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        HUDNetworkCanvas(
                            hotspots = hotspots,
                            selectedHotspotId = selectedHotspotId,
                            activeColor = hudPurple,
                            inactiveColor = Color(0xFF4B5563),
                            onSelect = { id ->
                                selectedHotspotId = id
                                coroutineScope.launch {
                                    scrollState.animateScrollToItem(id - 1)
                                }
                            },
                            animScale = animScale,
                            animAlpha = animAlpha
                        )
                    }

                    // Right Scrollable Detailed Analysis Sidebar
                    Column(
                        modifier = Modifier
                            .weight(0.9f)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.05f))
                            .border(1.dp, Color(0xFF1F2937), RoundedCornerShape(0.dp))
                    ) {
                        ActiveFocusPanel(
                            hotspot = activeHotspot,
                            navController = navController,
                            accentColor = themeColor,
                            hudColor = hudPurple
                        )

                        Divider(color = Color(0xFF1F2937), thickness = 1.dp)

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "ALL SYSTEMIC HIGHLIGHTS",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )

                        LazyColumn(
                            state = scrollState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 24.dp, start = 12.dp, end = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(hotspots) { spot ->
                                HorizontalRegionItem(
                                    hotspot = spot,
                                    isSelected = spot.id == selectedHotspotId,
                                    onSelect = {
                                        selectedHotspotId = spot.id
                                    },
                                    accentColor = themeColor,
                                    hudColor = hudPurple
                                )
                            }
                        }
                    }
                }
            } else {
                // Portrait Mobile Stack Layout
                Column(modifier = Modifier.fillMaxSize()) {
                    // Top Interactive Vector Canvas Area (fixed height ratio)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.95f)
                            .background(Color(0xFF030712))
                            .border(BorderStroke(1.dp, Color(0xFF1F2937))),
                        contentAlignment = Alignment.Center
                    ) {
                        HUDNetworkCanvas(
                            hotspots = hotspots,
                            selectedHotspotId = selectedHotspotId,
                            activeColor = hudPurple,
                            inactiveColor = Color(0xFF4B5563),
                            onSelect = { id ->
                                selectedHotspotId = id
                                coroutineScope.launch {
                                    scrollState.animateScrollToItem(id - 1)
                                }
                            },
                            animScale = animScale,
                            animAlpha = animAlpha
                        )

                        // Float Mini instruction HUD
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 12.dp)
                                .background(Color.Black.copy(alpha = 0.72f), RoundedCornerShape(12.dp))
                                .border(1.dp, hudPurple.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(hudPurple, CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "TAP PINK PINS TO ANALYZE BIOMECHANICS",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.5.sp
                                )
                            }
                        }
                    }

                    // Bottom Focus and Chapter Listing Panel
                    Column(
                        modifier = Modifier
                            .weight(1.05f)
                            .fillMaxWidth()
                            .background(Color(0xFF0B1120))
                    ) {
                        ActiveFocusPanel(
                            hotspot = activeHotspot,
                            navController = navController,
                            accentColor = themeColor,
                            hudColor = hudPurple
                        )

                        Divider(color = Color(0xFF1E293B), thickness = 1.dp)

                        LazyColumn(
                            state = scrollState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(hotspots) { spot ->
                                HorizontalRegionItem(
                                    hotspot = spot,
                                    isSelected = spot.id == selectedHotspotId,
                                    onSelect = {
                                        selectedHotspotId = spot.id
                                    },
                                    accentColor = themeColor,
                                    hudColor = hudPurple
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// --------------------------------------------------------------------------
// Core HUD Vector Human Drawing with Pins Layer
// --------------------------------------------------------------------------
@Composable
fun HUDNetworkCanvas(
    hotspots: List<BodyHotspot>,
    selectedHotspotId: Int,
    activeColor: Color,
    inactiveColor: Color,
    onSelect: (Int) -> Unit,
    animScale: Float,
    animAlpha: Float
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF020617)) // Deep clinical diagnostics environment
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        // 1. Futuristic Clinical HUD Grid & Hologram Elements
        Canvas(modifier = Modifier.fillMaxSize()) {
            val gridStepX = size.width / 12f
            val gridStepY = size.height / 14f

            // Thinnest medical backdrop grids
            for (i in 1..11) {
                drawLine(
                    color = Color(0xFF1E293B).copy(alpha = 0.35f),
                    start = Offset(gridStepX * i, 0f),
                    end = Offset(gridStepX * i, size.height),
                    strokeWidth = 1f
                )
            }
            for (j in 1..13) {
                drawLine(
                    color = Color(0xFF1E293B).copy(alpha = 0.35f),
                    start = Offset(0f, gridStepY * j),
                    end = Offset(size.width, gridStepY * j),
                    strokeWidth = 1f
                )
            }

            // Radar/Hologram surrounding circles
            drawCircle(
                color = Color(0xFF3B82F6).copy(alpha = 0.05f),
                radius = size.width.coerceAtMost(size.height) * 0.42f,
                center = Offset(size.width / 2f, size.height / 2f),
                style = Stroke(width = 1.5.dp.toPx())
            )
            drawCircle(
                color = Color(0xFF8A2BE2).copy(alpha = 0.03f),
                radius = size.width.coerceAtMost(size.height) * 0.28f,
                center = Offset(size.width / 2f, size.height / 2f),
                style = Stroke(width = 1.dp.toPx())
            )

            // Horizontal crosshair markers
            drawLine(
                color = Color(0xFF3B82F6).copy(alpha = 0.15f),
                start = Offset(gridStepX * 2, size.height / 2f),
                end = Offset(size.width - gridStepX * 2, size.height / 2f),
                strokeWidth = 2f
            )

            // 2. Deep Vector Art: Draw Stylized Clinical Human Skeleton
            val center = size.width / 2f

            // A. HEAD Skull outline
            val skullY = size.height * 0.08f
            val skullRadius = size.height * 0.045f
            drawOval(
                color = Color(0xFF94A3B8).copy(alpha = 0.5f),
                topLeft = Offset(center - skullRadius, skullY - skullRadius),
                size = Size(skullRadius * 2, skullRadius * 2.2f),
                style = Stroke(width = 2.dp.toPx())
            )
            // Jaw outline
            drawCircle(
                color = Color(0xFF64748B).copy(alpha = 0.4f),
                radius = skullRadius * 0.7f,
                center = Offset(center, skullY + skullRadius * 0.5f),
                style = Stroke(width = 1.5.dp.toPx())
            )

            // B. VERTEBRAL COLUMN SPINE line (Cervical to Lumbar)
            val spineStartY = skullY + skullRadius * 1.1f
            val spineEndY = size.height * 0.47f
            drawLine(
                color = Color(0xFFE2E8F0).copy(alpha = 0.6f),
                start = Offset(center, spineStartY),
                end = Offset(center, spineEndY),
                strokeWidth = 3.dp.toPx()
            )
            // Draw vertebral blocks (representing bone segments)
            var currentVertebraY = spineStartY
            while (currentVertebraY <= spineEndY) {
                drawRoundRect(
                    color = Color(0xFF38BDF8).copy(alpha = 0.4f),
                    topLeft = Offset(center - 5.dp.toPx(), currentVertebraY),
                    size = Size(10.dp.toPx(), 4.dp.toPx()),
                    cornerRadius = CornerRadius(1.dp.toPx(), 1.dp.toPx())
                )
                currentVertebraY += 8.dp.toPx()
            }

            // C. SHOULDER GIRDLE Clavicles
            val shoulderY = size.height * 0.20f
            val shoulderSpan = size.width * 0.17f
            drawLine(
                color = Color(0xFF94A3B8),
                start = Offset(center - shoulderSpan, shoulderY),
                end = Offset(center + shoulderSpan, shoulderY),
                strokeWidth = 2.5.dp.toPx(),
                cap = StrokeCap.Round
            )

            // D. RIB CAGE Thoracic basket
            val ribStartY = shoulderY + 8.dp.toPx()
            val ribEndY = size.height * 0.35f
            val ribSpreadX = size.width * 0.13f
            var ribY = ribStartY
            while (ribY <= ribEndY) {
                val ratio = (ribY - ribStartY) / (ribEndY - ribStartY)
                val currentWidth = ribSpreadX * (2f - ratio) * 0.65f
                // Left rib curves
                drawOval(
                    color = Color(0xFF64748B).copy(alpha = 0.35f),
                    topLeft = Offset(center - currentWidth, ribY),
                    size = Size(currentWidth, 6.dp.toPx()),
                    style = Stroke(width = 1.5.dp.toPx())
                )
                // Right rib curves
                drawOval(
                    color = Color(0xFF64748B).copy(alpha = 0.35f),
                    topLeft = Offset(center, ribY),
                    size = Size(currentWidth, 6.dp.toPx()),
                    style = Stroke(width = 1.5.dp.toPx())
                )
                ribY += 12.dp.toPx()
            }

            // E. PELVIS Bowl structure
            val pelvisY = size.height * 0.46f
            val pelvisWidth = size.width * 0.13f
            val pelvisHeight = size.height * 0.06f
            drawRoundRect(
                color = Color(0xFF94A3B8).copy(alpha = 0.5f),
                topLeft = Offset(center - pelvisWidth / 2f, pelvisY),
                size = Size(pelvisWidth, pelvisHeight),
                cornerRadius = CornerRadius(12.dp.toPx(), 12.dp.toPx()),
                style = Stroke(width = 2.dp.toPx())
            )
            // Obturator foramina (pelvic holes)
            drawCircle(
                color = Color(0xFF475569).copy(alpha = 0.3f),
                radius = pelvisWidth * 0.18f,
                center = Offset(center - pelvisWidth * 0.23f, pelvisY + pelvisHeight * 0.5f),
                style = Stroke(width = 1.5.dp.toPx())
            )
            drawCircle(
                color = Color(0xFF475569).copy(alpha = 0.3f),
                radius = pelvisWidth * 0.18f,
                center = Offset(center + pelvisWidth * 0.23f, pelvisY + pelvisHeight * 0.5f),
                style = Stroke(width = 1.5.dp.toPx())
            )

            // F. UPPER LIMBS Arm paths (Left normal, Right extended to lateral hotspot marker)
            val armElbowY = size.height * 0.34f
            val armWristY = size.height * 0.53f
            
            // Draw Left Arm (hanging at side)
            drawLine(
                color = Color(0xFF64748B).copy(alpha = 0.7f),
                start = Offset(center - shoulderSpan, shoulderY),
                end = Offset(center - shoulderSpan - 12.dp.toPx(), armElbowY),
                strokeWidth = 2.dp.toPx()
            )
            drawLine(
                color = Color(0xFF64748B).copy(alpha = 0.7f),
                start = Offset(center - shoulderSpan - 12.dp.toPx(), armElbowY),
                end = Offset(center - shoulderSpan - 4.dp.toPx(), armWristY),
                strokeWidth = 1.5.dp.toPx()
            )

            // Draw Right Arm (resting/partially active towards right wrist pin)
            val rElbowX = center + shoulderSpan + 14.dp.toPx()
            val rWristX = center + shoulderSpan + 32.dp.toPx()
            drawLine(
                color = Color(0xFFE2E8F0),
                start = Offset(center + shoulderSpan, shoulderY),
                end = Offset(rElbowX, armElbowY),
                strokeWidth = 2.dp.toPx()
            )
            drawLine(
                color = Color(0xFFE2E8F0),
                start = Offset(rElbowX, armElbowY),
                end = Offset(rWristX, armWristY),
                strokeWidth = 1.5.dp.toPx()
            )

            // G. LOWER LIMBS Leg paths (Hip to Knee to Ankle to Foot)
            val leftHipX = center - pelvisWidth * 0.28f
            val rightHipX = center + pelvisWidth * 0.28f
            val hipJointY = pelvisY + pelvisHeight * 0.7f

            val kneeY = size.height * 0.72f
            val lKneeX = leftHipX - 4.dp.toPx()
            val rKneeX = rightHipX + 16.dp.toPx() // Angled out slightly on right side

            val ankleY = size.height * 0.88f
            val lAnkleX = lKneeX
            val rAnkleX = rKneeX - 8.dp.toPx()

            // Draw femur bones
            drawLine(
                color = Color(0xFFE2E8F0),
                start = Offset(leftHipX, hipJointY),
                end = Offset(lKneeX, kneeY),
                strokeWidth = 2.5.dp.toPx()
            )
            drawLine(
                color = Color(0xFFE2E8F0),
                start = Offset(rightHipX, hipJointY),
                end = Offset(rKneeX, kneeY),
                strokeWidth = 2.5.dp.toPx()
            )

            // Draw tib-fib lower legs
            drawLine(
                color = Color(0xFF94A3B8),
                start = Offset(lKneeX, kneeY),
                end = Offset(lAnkleX, ankleY),
                strokeWidth = 2.dp.toPx()
            )
            drawLine(
                color = Color(0xFF94A3B8),
                start = Offset(rKneeX, kneeY),
                end = Offset(rAnkleX, ankleY),
                strokeWidth = 2.dp.toPx()
            )

            // Draw joint articulation marker ovals
            drawCircle(color = Color(0xFF38BDF8).copy(alpha = 0.5f), radius = 4.dp.toPx(), center = Offset(lKneeX, kneeY))
            drawCircle(color = Color(0xFF38BDF8).copy(alpha = 0.5f), radius = 4.dp.toPx(), center = Offset(rKneeX, kneeY))
            drawCircle(color = Color(0xFF38BDF8).copy(alpha = 0.5f), radius = 3.dp.toPx(), center = Offset(lAnkleX, ankleY))
            drawCircle(color = Color(0xFF38BDF8).copy(alpha = 0.5f), radius = 3.dp.toPx(), center = Offset(rAnkleX, ankleY))
        }

        // 3. Floating Overlay interactive hotspot buttons with numbers 1..13
        val density = LocalDensity.current
        hotspots.forEach { hotspot ->
            val isSelected = hotspot.id == selectedHotspotId

            // Relative positioning
            val xOffsetDp = with(density) { (width * hotspot.xPercent).toDp() }
            val yOffsetDp = with(density) { (height * hotspot.yPercent).toDp() }

            Box(
                modifier = Modifier
                    .offset(x = xOffsetDp - 24.dp, y = yOffsetDp - 24.dp)
                    .size(48.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onSelect(hotspot.id)
                    },
                contentAlignment = Alignment.Center
            ) {
                // Radial pulse for SELECTED node only
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(38.dp * animScale)
                            .background(
                                color = activeColor.copy(alpha = animAlpha),
                                shape = CircleShape
                            )
                    )
                }

                // Inner core button structure
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .shadow(
                            elevation = if (isSelected) 8.dp else 2.dp,
                            shape = CircleShape,
                            ambientColor = if (isSelected) activeColor else Color.Black
                        )
                        .background(
                            brush = if (isSelected) {
                                Brush.radialGradient(
                                    colors = listOf(activeColor, Color(0xFF4B10A3)),
                                    radius = 35f
                                )
                            } else {
                                Brush.radialGradient(
                                    colors = listOf(Color(0xFF2E334D), Color(0xFF131625)),
                                    radius = 35f
                                )
                            },
                            shape = CircleShape
                        )
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) Color.White else Color(0xFFFFCC33).copy(alpha = 0.7f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${hotspot.id}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

// --------------------------------------------------------------------------
// Selected Spot High-Yield Fact Sheet Panel
// --------------------------------------------------------------------------
@Composable
fun ActiveFocusPanel(
    hotspot: BodyHotspot,
    navController: NavHostController,
    accentColor: Color,
    hudColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .shadow(4.dp, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, hudColor.copy(alpha = 0.35f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Header: Number circle and Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(hudColor.copy(alpha = 0.15f), CircleShape)
                        .border(1.dp, hudColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${hotspot.id}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = hudColor
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = hotspot.title.uppercase(),
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleMedium,
                        letterSpacing = 0.5.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Physiotherapy Target Module: " + hotspot.region,
                        fontSize = 11.sp,
                        color = accentColor,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(
                    onClick = { navController.navigate("region_dashboard/${hotspot.region}") },
                    modifier = Modifier
                        .background(accentColor.copy(alpha = 0.12f), CircleShape)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = "Navigate to Study Hub",
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Body part short summary description
            Text(
                text = hotspot.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Clinical / Anatomical metadata chips
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoLabelRow(label = "Bones", value = hotspot.keyBones)
                InfoLabelRow(label = "Primary Muscles", value = hotspot.keyMuscles)
                InfoLabelRow(label = "Key Pathologies", value = hotspot.clinicalApplications)
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Main CTA: Redirect to study section
            Button(
                onClick = { navController.navigate("region_dashboard/${hotspot.region}") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = hudColor),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(vertical = 11.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CastForEducation,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ENTER ${hotspot.region.uppercase()} MODULE HUB",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            }
        }
    }
}

// Helper row displaying metadata bullet points
@Composable
fun InfoLabelRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "$label: ",
            fontSize = 11.sp,
            fontWeight = FontWeight.Black,
            color = Color.Gray,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// --------------------------------------------------------------------------
// Sidebar / Bottom list row representing individual body items
// --------------------------------------------------------------------------
@Composable
fun HorizontalRegionItem(
    hotspot: BodyHotspot,
    isSelected: Boolean,
    onSelect: () -> Unit,
    accentColor: Color,
    hudColor: Color
) {
    Surface(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        color = if (isSelected) {
            hudColor.copy(alpha = 0.12f)
        } else {
            MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
        },
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) hudColor else Color(0xFF1E293B)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Index counter
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (isSelected) hudColor else Color(0xFF1E293B),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${hotspot.id}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 9.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = hotspot.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = hotspot.clinicalApplications.take(50) + "...",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = hudColor,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
