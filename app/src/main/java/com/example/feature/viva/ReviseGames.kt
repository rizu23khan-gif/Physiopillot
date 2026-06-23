package com.example.feature.viva

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ContentRepo
import com.example.data.DetailedAnatomy
import com.example.data.VivaMcq
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

// --------------------------------------------------------------------------
// Score Tracking & Game Preferences Manager
// --------------------------------------------------------------------------
class ReviseGamesPrefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("physio_pilot_games_v1", Context.MODE_PRIVATE)

    fun getElectroHighScore(): Int = prefs.getInt("electro_high_score", 0)
    fun saveElectroHighScore(score: Int) {
        if (score > getElectroHighScore()) {
            prefs.edit().putInt("electro_high_score", score).apply()
        }
    }

    fun getClinicalXP(): Int = prefs.getInt("clinical_total_xp", 0)
    fun addClinicalXP(xp: Int) {
        val current = getClinicalXP()
        prefs.edit().putInt("clinical_total_xp", current + xp).apply()
    }

    fun getMemoryHighScore(): Int = prefs.getInt("memory_high_score", 0)
    fun saveMemoryHighScore(score: Int) {
        if (score > getMemoryHighScore()) {
            prefs.edit().putInt("memory_high_score", score).apply()
        }
    }

    fun getRapidFireHighScore(): Int = prefs.getInt("rapid_fire_high_score", 0)
    fun saveRapidFireHighScore(score: Int) {
        if (score > getRapidFireHighScore()) {
            prefs.edit().putInt("rapid_fire_high_score", score).apply()
        }
    }
}

enum class ReviseGameType {
    ELECTRO_ARENA,
    CLINICAL_CHALLENGE,
    MEMORY_MATCH,
    RAPID_FIRE
}

// --------------------------------------------------------------------------
// 1. Electrotherapy Parameter Arena Match Game
// --------------------------------------------------------------------------
data class ElectroModality(
    val name: String,
    val correctParameter: String,
    val incorrect1: String,
    val incorrect2: String,
    val incorrect3: String,
    val description: String
)

val electroModalities = listOf(
    ElectroModality(
        name = "TENS (Transcutaneous Electrical Nerve Stimulation)",
        correctParameter = "Gate Control: High Frequency 80-150 Hz, Pulse Width 50-100 μs | Endorphin: Low Frequency 2-5 Hz, Pulse Width 150-300 μs",
        incorrect1 = "Carrier Frequency 2500 Hz, burst frequency 50 Hz with 10% duty cycle strengthening bursts",
        incorrect2 = "Standard Frequency 27.12 MHz, deep heat with capacitive or inductive coupling plates",
        incorrect3 = "Double crossed medium-frequency AC circuits producing a 1-150 Hz beat frequency deep inside tissues",
        description = "Effective non-invasive therapy primarily indicated for acute and chronic musculoskeletal pain relief."
    ),
    ElectroModality(
        name = "Russian Current",
        correctParameter = "Medium Frequency carrier of 2500 Hz, interrupted with 50 Hz burst frequency to provoke high-torque muscle contractions",
        incorrect1 = "Wavelength of 12.24 cm, standard frequency of 2450 MHz designed for water-rich superficial muscle heating",
        incorrect2 = "Red wavelength of 630-700 nm with dosage around 2-10 J/cm² seeking cellular photobiomodulation",
        incorrect3 = "Spinal force traction adjusted specifically to 10% of patient body weight (approx 10-15 lbs) for intervertebral decompression",
        description = "High-amplitude current optimized to achieve muscle hypertrophy and structural strength progression."
    ),
    ElectroModality(
        name = "IFT (Interferential Therapy)",
        correctParameter = "Two crossed medium-frequency circuits (e.g. 4000 Hz and 4100 Hz), producing a 1-150 Hz beat frequency deep in tissues",
        incorrect1 = "Continuous or pulse modes with 1 MHz (deep) or 3 MHz (superficial) frequencies coupled with high acoustic gel transmission",
        incorrect2 = "Measurement of Motor Unit Action Potentials (MUAPs) under active contraction utilizing surface or needle electrodes",
        incorrect3 = "Needle sensory detection demonstrating delayed conduction velocity in suspected carpal tunnel median nerve path",
        description = "Utilizes crossed currents to bypass skin resistance, delivering pain-relieving beat frequencies to deep joints."
    ),
    ElectroModality(
        name = "SWD (Shortwave Diathermy)",
        correctParameter = "Standard Federal Frequency of 27.12 MHz (Wavelength 11.06 m) for high-frequency deep tissue heating using plate electrodes",
        incorrect1 = "High-frequency ultrasound wave reflection at tissue acoustic boundary lines prevented by coupling gel",
        incorrect2 = "Intermittent cervical strap force traction set at 10% body weight to avoid localized muscular spasm reactions",
        incorrect3 = "Low-frequency alternating current using Gate Control theory designed representing 80 Hz with 50 μs pulse duration",
        description = "A powerful electromagnetic heat treatment designed to cover large, deep joint areas like chronic osteoarthritis of the knee."
    ),
    ElectroModality(
        name = "MWD (Microwave Diathermy)",
        correctParameter = "Standard Frequency of 2450 MHz (Wavelength 12.24 cm) utilizing a parabolic emitter to heat muscular tissue selectively",
        incorrect1 = "Gate Control Theory activation using 100 Hz pulse rates coupled with skin safety electrodes",
        incorrect2 = "Crossed 4000 Hz alternating carrier waves designed to bypass superficial epidermal electrical impedance",
        incorrect3 = "Fibrillation potentials and positive sharp waves indicating nerve denervation at resting states",
        description = "Heats tissues with high water content (such as muscle) through focused radiant waves. Avoid in high fat layers."
    ),
    ElectroModality(
        name = "Therapeutic Ultrasound",
        correctParameter = "Dual options of 1 MHz (deep) or 3 MHz (superficial), utilizing standard transmission gel to prevent acoustic beam reflection",
        incorrect1 = "Intermittent active lumbar belt tension traction set strictly to 50% of overall body weight to unload discs",
        incorrect2 = "Continuous 27.12 MHz radio frequency applied through large capacitive plates placed parallel across knees",
        incorrect3 = "2500 Hz frequency delivered in 50 bursts per second with 10 seconds of active contraction followed by 50 seconds rest",
        description = "Acoustic micro-vibrations generating deep localized thermal or non-thermal microstreaming cellular benefits."
    ),
    ElectroModality(
        name = "LASER Therapy",
        correctParameter = "Red (600-700 nm) or Near-Infrared (700-1000 nm) wavelengths emitting photobiomodulation to activate cytochrome c oxidase",
        incorrect1 = "Acoustic mechanical waves of 1 MHz utilizing a mineral gel interface to conduct clinical thermal heat",
        incorrect2 = "Deep capacitive heating using high frequency 27.12 MHz plates configured with rubber insulation sheets",
        incorrect3 = "Nerve Conduction velocity calculated in meters per second seeking a typical upper limb speed exceeding 50 m/s",
        description = "Monochromatic light energy focused on trigger points and injuries to accelerate cellular repair and lower inflammation."
    ),
    ElectroModality(
        name = "EMG (Electromyography)",
        correctParameter = "Diagnostic study capturing Motor Unit Action Potentials (MUAPs) at rest and under contraction for denervation checks",
        incorrect1 = "High-frequency acoustic energy delivered in 20% pulsed duty cycle to foster soft tissue tissue cicatrization",
        incorrect2 = "Wavelength 12.24 cm radiating deep muscular tissues under careful exclusion of localized pacemakers and metal implants",
        incorrect3 = "Cervical traction straps configured with static pull force strictly totaling 50% of measured body weight",
        description = "Assesses neuromuscular health by recording active and resting electrical skeletal muscle membrane potentials."
    ),
    ElectroModality(
        name = "NCV (Nerve Conduction Velocity)",
        correctParameter = "Diagnostic assessment measuring Distal Latency, Amplitude, and Speed (m/s), targeting normal bounds >50 m/s in arms",
        incorrect1 = "Gate Control pain pathways stimulated via 100 Hz sensory thresholds coupled with portable battery devices",
        incorrect2 = "Medium-frequency AC producing a therapeutic beat frequency deep inside spinal facet joints",
        incorrect3 = "Standard 27.12 MHz high frequency fields generated under induction coils to treat muscular neck strains",
        description = "Measures the conduction velocity of electrical impulses along motor and sensory nerves to pinpoint entrapments."
    ),
    ElectroModality(
        name = "Spinal Traction",
        correctParameter = "Decompression mechanical pull utilizing 10% body weight for Cervical spine, and 50% body weight for Lumbar spine tension",
        incorrect1 = "Photobiomodulation dosage measuring 4 Joules per square centimeter targeting superficial muscle tendons",
        incorrect2 = "Fibrillation potentials detected via intramuscular fine-needle electrodes at completely rested states",
        incorrect3 = "Acoustic microstreaming wave matching 3 MHz applied strictly with a rapid 10cm/s clinical hand stroke speed",
        description = "Mechanically separates spinal joints, decompressing spinal discs and expanding neural foramens."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectrotherapyParameterArena(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { ReviseGamesPrefs(context) }
    
    var currentRound by remember { mutableStateOf(1) }
    val totalRounds = 5
    var score by remember { mutableStateOf(0) }
    var highScore by remember { mutableStateOf(prefs.getElectroHighScore()) }
    
    var isGameOver by remember { mutableStateOf(false) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    
    // Manage active modality and options
    var activeModality by remember { mutableStateOf(electroModalities[Random.nextInt(electroModalities.size)]) }
    var options by remember { mutableStateOf(emptyList<String>()) }
    
    val regenerateRound = {
        activeModality = electroModalities[Random.nextInt(electroModalities.size)]
        val rawOptions = listOf(
            activeModality.correctParameter,
            activeModality.incorrect1,
            activeModality.incorrect2,
            activeModality.incorrect3
        ).shuffled()
        options = rawOptions
        selectedAnswer = null
    }
    
    // Run initial generator
    LaunchedEffect(currentRound) {
        regenerateRound()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // High-fidelity top bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "ELECTRO PARAMETER ARENA",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = "High Score",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Best: $highScore",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
        )
        
        // Game Board
        if (!isGameOver) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Round indicators & Live Score
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Round $currentRound of $totalRounds",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Score: $score",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                
                // Progress Bar
                LinearProgressIndicator(
                    progress = { currentRound.toFloat() / totalRounds.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
                
                // Target Modality Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "TARGET CLINICAL MODALITY",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = activeModality.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = activeModality.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                Text(
                    text = "Select the accurate clinical parameters for this modality:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth()
                )
                
                // Parameter Option Buttons
                options.forEach { option ->
                    val isCorrect = option == activeModality.correctParameter
                    val isSelected = selectedAnswer == option
                    
                    val cardBg = when {
                        selectedAnswer != null && isCorrect -> Color(0xFFE6F4EA) // Green for correct answer always
                        selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFFCE8E6) // Red for selected incorrect
                        else -> MaterialTheme.colorScheme.surface
                    }
                    val cardBorderColor = when {
                        selectedAnswer != null && isCorrect -> Color(0xFF34A853)
                        selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    }
                    val cardBorderWidth = if (selectedAnswer != null && (isCorrect || isSelected)) 2.dp else 1.dp
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedAnswer == null) {
                                selectedAnswer = option
                                if (isCorrect) {
                                    score += 20
                                }
                            },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        border = BorderStroke(cardBorderWidth, cardBorderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        when {
                                            selectedAnswer != null && isCorrect -> Color(0xFF34A853)
                                            selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        },
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                             ) {
                                 if (selectedAnswer != null && isCorrect) {
                                     Icon(Icons.Default.Check, contentDescription = "Correct", tint = Color.White, modifier = Modifier.size(16.dp))
                                 } else if (selectedAnswer != null && isSelected && !isCorrect) {
                                     Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = Color.White, modifier = Modifier.size(16.dp))
                                 } else {
                                     Text(
                                         text = "",
                                         style = MaterialTheme.typography.labelSmall
                                     )
                                 }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (selectedAnswer != null && isCorrect) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedAnswer != null && isCorrect) Color(0xFF137333) else if (selectedAnswer != null && isSelected && !isCorrect) Color(0xFFC5221F) else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                
                // Next or Results Action Button
                if (selectedAnswer != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (currentRound < totalRounds) {
                                currentRound++
                            } else {
                                isGameOver = true
                                prefs.saveElectroHighScore(score)
                                highScore = prefs.getElectroHighScore()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (currentRound < totalRounds) "Next Modality" else "View Results",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        } else {
            // Results screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = "Trophy",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(64.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Arena Challenge Completed!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "You scored $score out of 100",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (score >= 80) "Excellent! Your electrotherapy parameter recall is fully clinical grade!"
                           else "Good effort! Retake the arena match to perfect your knowledge.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Exit Challenge")
                    }
                    Button(
                        onClick = {
                            currentRound = 1
                            score = 0
                            isGameOver = false
                            selectedAnswer = null
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Play Again")
                    }
                }
            }
        }
    }
}

// --------------------------------------------------------------------------
// 2. Clinical Case Challenge Composable (Board with XP Score)
// --------------------------------------------------------------------------
data class ClinicalCase(
    val category: String,
    val title: String,
    val description: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

val clinicalCases = listOf(
    ClinicalCase(
        category = "Orthopedic Physiotherapy",
        title = "Shoulder Arthrokinematics Limitation",
        description = "52-year-old female presenting with severe right shoulder pain restricting active functional mechanics. On evaluation, active and passive abduction is limited to 60°, external rotation is restricted to 15°, and flexion is limited to 90°. Passive trials feel distinctively leathery and stiff. A classic capsular limitation is noted.",
        question = "Which joint glides manipulation is clinically indicated to specifically restore external rotation according to the convex-concave rule?",
        options = listOf(
            "Anterior translation glide of humeral head relative to glenoid",
            "Posterior translation glide of humeral head relative to glenoid",
            "Superior traction of humeral neck outwards",
            "Medial glide of scapular body over thoracic fascia"
        ),
        correctAnswerIndex = 0,
        explanation = "According to the convex-concave rule, the humeral head is convex and moves opposite to translation. During external rotation, the humeral head rolls posteriorly and slips/glides anteriorly. An anterior glide is clinically required to expand the restricted anterior capsule."
    ),
    ClinicalCase(
        category = "Neurology",
        title = "Stroke Motor Syndrome Isolation",
        description = "65-year-old male who sustained a Right MCA ischemic stroke 4 weeks ago, manifesting hemiplegic motor loss. While walking, he shows extensor synergy in his right lower limb and typical flexor synergy in his right upper limb. Standing knee recovery is graded as Brunnstrom Stage 3.",
        question = "What functional task-oriented strategy is best suited to help the patient isolate active knee flexion out of the extensor synergy during stance phase?",
        options = listOf(
            "PNF diagonal flexion sequences in supine position without weight-bearing loads",
            "Task-oriented active steps-ups onto a 10cm block using the hemiparetic limb",
            "Sustained passive hamstring stretching coupled with sensory stroking",
            "High-load isometric quadriceps settings on a bolting bench"
        ),
        correctAnswerIndex = 1,
        explanation = "Active step-ups onto a block force the hemi-limb into combined hip and knee flexion under weight-bearing stresses. This challenges the system to coordinate movements outside the rigid extensor synergy, driving targeted sensory-motor neuroplastic changes."
    ),
    ClinicalCase(
        category = "Cardiopulmonary",
        title = "COPD Retained Secretion Clearing",
        description = "58-year-old heavy smoker diagnosed with severe Stage III COPD, presenting with persistent shortness of breath, a wet but weak cough, and bilateral lower lung field coarse crackles suggesting thick, retained pulmonary secretions.",
        question = "Which specific sequence of the Active Cycle of Breathing Techniques (ACBT) is most effective to safely mobilize and expectorate these peripheral secretions without inducing hyperreactivity or airway collapse?",
        options = listOf(
            "Maximal coughing attacks combined with heavy epigastric thrust",
            "Breathing Control -> Thoracic Expansion Exercises -> Forced Expiratory Technique (Huffing)",
            "Active postural drainage combined immediately with heavy mechanical percussion",
            "Repetitive incentive spirometry carried out to vital capacity limits"
        ),
        correctAnswerIndex = 1,
        explanation = "ACBT begins with Breathing Control (relaxing chest wall), moves to Thoracic Expansion (reinforces collateral alveolar ventilation to slip air behind secretions), and finishes with the Forced Expiratory Technique/Huffing, which uses high-velocity flow to clear secretions without causing airway collapse."
    ),
    ClinicalCase(
        category = "Pediatric Physiotherapy",
        title = "Progressive Proximal Myopathy",
        description = "6-year-old boy presenting with progressive fatigue, frequent clumsy falls, and pseudo-hypertrophy of calves. When attempting to rise to a standing position from the floor, he crawls up his shins, knees, and thighs with his hands.",
        question = "What sign does this rising technique represent, and which core muscle weakness is primarily responsible for this maneuver?",
        options = listOf(
            "Gower's Sign; indicating severe weakness of proximal hip extensors (Gluteus Maximus) and quadriceps",
            "Trendelenburg Sign; indicating severe weakness of hip abductors (Gluteus Medius)",
            "Kernig's Sign; indicating acute hamstring sheath spasticity and meningeal tension",
            "Romberg's Sign; indicating progressive somatosensory loss of lower extremities"
        ),
        correctAnswerIndex = 0,
        explanation = "Gower's sign is a classic compensation for severe weakness of proximal muscles (particularly Gluteus Maximus and Quadriceps). Because the patient cannot extend his hips or knees against gravity using these muscles alone, he uses his hands to walk up his legs, climbing his skeleton mechanically."
    ),
    ClinicalCase(
        category = "Sports Physiotherapy",
        title = "ACL Pivot Mechanics post-injury",
        description = "22-year-old university basketball player who pivoted suddenly on a planted leg during a cutting action, experiencing a painful 'pop' inside the knee joint followed by rapid effusion. Anterior drawer and Lachman's tests yield standard positive results with mushy, lax endpoints.",
        question = "Why must open kinetic chain (OKC) quadriceps extension exercises between 0° and 45° be strictly avoided during early rehabilitation?",
        options = listOf(
            "To prevent patellar ligament subluxation along the trochlea",
            "To avoid worsening meniscus strain by compressing the posterior horns",
            "To prevent excessive anterior drawer shear strain on the healing graft from quadriceps tendon vector tension",
            "To minimize hamstring fatigue which could compromise joint dynamic stability"
        ),
        correctAnswerIndex = 2,
        explanation = "Between 0° and 45° of open chain extension, the line of pull of the quadriceps tendon exerts a powerful anterior vector on the tibia. This pulls the tibia anteriorly relative to the femur, creating excessive tensile shear strain that can stretch or rupture a newly healing ACL graft."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicalCaseChallenge(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { ReviseGamesPrefs(context) }
    
    var clinicalXP by remember { mutableStateOf(prefs.getClinicalXP()) }
    
    var selectedCaseIndex by remember { mutableStateOf<Int?>(null) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var xpEarnedThisSession by remember { mutableStateOf(0) }
    
    val currentCase = selectedCaseIndex?.let { clinicalCases[it] }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // High-fidelity top bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    currentCase?.category?.uppercase() ?: "CLINICAL CASE CHALLENGE",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    if (selectedCaseIndex != null) {
                        selectedCaseIndex = null
                        selectedOptionIndex = null
                    } else {
                        onBack()
                    }
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .background(Color(0xFFFFF7E6), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Stars,
                            contentDescription = "XP Score",
                            tint = Color(0xFFD48806),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$clinicalXP XP",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFD48806)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
        )
        
        if (selectedCaseIndex == null) {
            // Case Selector Board
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "SELECT SPECIALTY CHALLENGE",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.2.sp
                )
                
                Text(
                    text = "Test your clinical reasoning, diagnostic skills, and therapy progressions on complex, real-world physiotherapy cases.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                if (xpEarnedThisSession > 0) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F4EA)),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFF34A853))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Celebration, contentDescription = null, tint = Color(0xFF137333))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Well done! You earned +$xpEarnedThisSession XP in this session!",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF137333)
                            )
                        }
                    }
                }
                
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(clinicalCases.size) { index ->
                        val item = clinicalCases[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCaseIndex = index
                                    selectedOptionIndex = null
                                },
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(44.dp)
                                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f), RoundedCornerShape(10.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        val icon = when (index) {
                                            0 -> Icons.Default.Handyman
                                            1 -> Icons.Default.Psychology
                                            2 -> Icons.Default.HeartBroken
                                            3 -> Icons.Default.ChildCare
                                            else -> Icons.Default.SportsBasketball
                                        }
                                        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column {
                                        Text(
                                            text = item.category.uppercase(),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(
                                            text = item.title,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        } else if (currentCase != null) {
            // Case Details and Questions
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Return board button
                TextButton(
                    onClick = { 
                        selectedCaseIndex = null
                        selectedOptionIndex = null
                    },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Select Another Case", style = MaterialTheme.typography.labelMedium)
                }

                // Clinical File Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ContactPage, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "CLINICAL PATIENT DATA CARD",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = currentCase.description,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // Question Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "DIAGNOSTIC QUESTION",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = currentCase.question,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                // Options
                currentCase.options.forEachIndexed { optIndex, option ->
                    val isCorrect = optIndex == currentCase.correctAnswerIndex
                    val isSelected = selectedOptionIndex == optIndex
                    
                    val cardBg = when {
                        selectedOptionIndex != null && isCorrect -> Color(0xFFE6F4EA)
                        selectedOptionIndex != null && isSelected && !isCorrect -> Color(0xFFFCE8E6)
                        else -> MaterialTheme.colorScheme.surface
                    }
                    val borderColor = when {
                        selectedOptionIndex != null && isCorrect -> Color(0xFF34A853)
                        selectedOptionIndex != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                        else -> MaterialTheme.colorScheme.outline
                    }
                    val borderWidth = if (selectedOptionIndex != null && (isCorrect || isSelected)) 2.dp else 1.dp
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedOptionIndex == null) {
                                selectedOptionIndex = optIndex
                                if (isCorrect) {
                                    prefs.addClinicalXP(50)
                                    clinicalXP = prefs.getClinicalXP()
                                    xpEarnedThisSession += 50
                                }
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        border = BorderStroke(borderWidth, borderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        when {
                                            selectedOptionIndex != null && isCorrect -> Color(0xFF34A853)
                                            selectedOptionIndex != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        },
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (selectedOptionIndex != null && isCorrect) {
                                    Icon(Icons.Default.Check, contentDescription = "Correct", tint = Color.White, modifier = Modifier.size(14.dp))
                                } else if (selectedOptionIndex != null && isSelected && !isCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = Color.White, modifier = Modifier.size(14.dp))
                                } else {
                                    Text(
                                        text = ('A' + optIndex).toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (selectedOptionIndex != null && isCorrect) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedOptionIndex != null && isCorrect) Color(0xFF137333) else if (selectedOptionIndex != null && isSelected && !isCorrect) Color(0xFFC5221F) else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // Interactive Explanation Reveal
                if (selectedOptionIndex != null) {
                    val isOptionCorrect = selectedOptionIndex == currentCase.correctAnswerIndex
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = if (isOptionCorrect) Color(0xFFE6F4EA).copy(alpha = 0.4f) else Color(0xFFFFF7E6).copy(alpha = 0.4f)),
                        border = BorderStroke(1.dp, if (isOptionCorrect) Color(0xFF34A853).copy(alpha = 0.5f) else Color(0xFFD48806).copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (isOptionCorrect) Icons.Default.CheckCircle else Icons.Default.Info,
                                    contentDescription = null,
                                    tint = if (isOptionCorrect) Color(0xFF34A853) else Color(0xFFD48806)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isOptionCorrect) "CRITICAL REASONING SUCCESS (+50 XP)" else "CLINICAL STUDY ADVICE",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (isOptionCorrect) Color(0xFF137333) else Color(0xFFB37D14)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = currentCase.explanation,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    
                    Button(
                        onClick = {
                            selectedCaseIndex = null
                            selectedOptionIndex = null
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Solve Another Specialty Case", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// --------------------------------------------------------------------------
// 3. Memory Match Game Composable (Origin/Nerve/Action Quick Quiz)
// --------------------------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryMatch(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { ReviseGamesPrefs(context) }
    
    val anatomies = remember { ContentRepo.anatomies }
    
    var score by remember { mutableStateOf(0) }
    var highScore by remember { mutableStateOf(prefs.getMemoryHighScore()) }
    var timeLeft by remember { mutableStateOf(45) }
    var isPlaying by remember { mutableStateOf(true) }
    
    // Game Question parameters
    var activeMuscle by remember { mutableStateOf<DetailedAnatomy?>(null) }
    var matchCategory by remember { mutableStateOf("Nerve Supply") } // Or Origin, Insertion, Action, Clinical Significance
    var correctProp by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(emptyList<String>()) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    
    val generateNextQuestion = {
        val picked = anatomies[Random.nextInt(anatomies.size)]
        activeMuscle = picked
        selectedAnswer = null
        
        val cat = listOf("Origin", "Insertion", "Nerve Supply", "Action", "Clinical Significance").random()
        matchCategory = cat
        
        val correctVal = when (cat) {
            "Origin" -> picked.origin
            "Insertion" -> picked.insertion
            "Nerve Supply" -> picked.nerveSupply
            "Action" -> picked.action
            else -> picked.clinicalImportance
        }
        correctProp = correctVal
        
        // Pick 3 random distractors from other muscles
        val distractors = mutableListOf<String>()
        while (distractors.size < 3) {
            val distMuscle = anatomies.random()
            if (distMuscle.id != picked.id) {
                val distVal = when (cat) {
                    "Origin" -> distMuscle.origin
                    "Insertion" -> distMuscle.insertion
                    "Nerve Supply" -> distMuscle.nerveSupply
                    "Action" -> distMuscle.action
                    else -> distMuscle.clinicalImportance
                }
                if (distVal.isNotEmpty() && distVal != correctVal && !distractors.contains(distVal)) {
                    distractors.add(distVal)
                }
            }
        }
        
        options = (distractors + correctVal).shuffled()
    }
    
    // Initial load
    LaunchedEffect(Unit) {
        generateNextQuestion()
    }
    
    // Timer flow
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            isPlaying = false
            prefs.saveMemoryHighScore(score)
            highScore = prefs.getMemoryHighScore()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // High fidelity top bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "ANATOMY MEMORY MATCH",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Memory High Score",
                            tint = Color(0xFFFA541C),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Best: $highScore",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
        )
        
        if (isPlaying && activeMuscle != null) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Time & Live Score
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "Timer",
                            tint = if (timeLeft < 10) Color.Red else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "$timeLeft s left",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (timeLeft < 10) Color.Red else MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Text(
                        text = "Matches: $score",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                
                // Active Muscle Target Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25f)),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "TARGET MUSCLE",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.secondary,
                            letterSpacing = 1.2.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = activeMuscle?.name ?: "",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(8.dp))
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "MATCH: ${matchCategory.uppercase()}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        }
                    }
                }
                
                // Matches/Options List
                options.forEach { option ->
                    val isCorrect = option == correctProp
                    val isSelected = selectedAnswer == option
                    
                    val cardBg = when {
                        selectedAnswer != null && isCorrect -> Color(0xFFE6F4EA)
                        selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFFCE8E6)
                        else -> MaterialTheme.colorScheme.surface
                    }
                    val cardBorderColor = when {
                        selectedAnswer != null && isCorrect -> Color(0xFF34A853)
                        selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    }
                    val cardBorderWidth = if (selectedAnswer != null && (isCorrect || isSelected)) 2.dp else 1.dp
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedAnswer == null) {
                                selectedAnswer = option
                                if (isCorrect) {
                                    score += 15
                                }
                            },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        border = BorderStroke(cardBorderWidth, cardBorderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        when {
                                            selectedAnswer != null && isCorrect -> Color(0xFF34A853)
                                            selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        },
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (selectedAnswer != null && isCorrect) {
                                    Icon(Icons.Default.Check, contentDescription = "Correct", tint = Color.White, modifier = Modifier.size(14.dp))
                                } else if (selectedAnswer != null && isSelected && !isCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = Color.White, modifier = Modifier.size(14.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (selectedAnswer != null && isCorrect) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedAnswer != null && isCorrect) Color(0xFF137333) else if (selectedAnswer != null && isSelected && !isCorrect) Color(0xFFC5221F) else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                
                if (selectedAnswer != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { generateNextQuestion() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Next Muscle", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        } else {
            // Memory game result card
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Clock",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(64.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Speed Memory Match Complete!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Total Score: $score points",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (score >= 100) "Astonishing speed! You are localizing origins, insertions, and nerve pathways correctly with effortless speed!"
                           else "Keep practicing to elevate your recall precision under timed clinical assessments.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Exit Game")
                    }
                    Button(
                        onClick = {
                            score = 0
                            timeLeft = 45
                            isPlaying = true
                            selectedAnswer = null
                            generateNextQuestion()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Play Again")
                    }
                }
            }
        }
    }
}

// --------------------------------------------------------------------------
// 4. Rapid Fire Revision Game Composable (60 seconds, -5s wrong +10pts right)
// --------------------------------------------------------------------------
data class RapidQuestion(
    val query: String,
    val options: List<String>,
    val correctAnswer: String,
    val hint: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RapidFireRevision(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { ReviseGamesPrefs(context) }
    
    val database = remember { ContentRepo.vivaQuestions }
    val anatomies = remember { ContentRepo.anatomies }
    
    var score by remember { mutableStateOf(0) }
    var highScore by remember { mutableStateOf(prefs.getRapidFireHighScore()) }
    var timeLeft by remember { mutableStateOf(60) }
    var isPlaying by remember { mutableStateOf(true) }
    
    var currentQuestion by remember { mutableStateOf<RapidQuestion?>(null) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    
    val loadNextQuestion = {
        selectedAnswer = null
        val pickerType = Random.nextInt(3)
        
        if (pickerType == 0 && database.isNotEmpty()) {
            // Grab MCQ from main database
            val raw = database[Random.nextInt(database.size)]
            currentQuestion = RapidQuestion(
                query = raw.question,
                options = raw.options,
                correctAnswer = raw.correctAnswer,
                hint = raw.learningPoint
            )
        } else if (pickerType == 1 && electroModalities.isNotEmpty()) {
            // Grab Parameter matching question
            val modal = electroModalities[Random.nextInt(electroModalities.size)]
            val opt = listOf(modal.correctParameter, modal.incorrect1, modal.incorrect2, modal.incorrect3).shuffled()
            currentQuestion = RapidQuestion(
                query = "Identify correct parameters of: \n${modal.name}?",
                options = opt,
                correctAnswer = modal.correctParameter,
                hint = modal.description
            )
        } else {
            // Grab Quick Nerve/Origin Question
            val muscle = anatomies[Random.nextInt(anatomies.size)]
            val correctNerve = muscle.nerveSupply
            
            // Build distractors
            val dList = mutableSetOf<String>()
            while (dList.size < 3) {
                val dm = anatomies[Random.nextInt(anatomies.size)]
                if (dm.nerveSupply != correctNerve && dm.nerveSupply.isNotEmpty()) {
                    dList.add(dm.nerveSupply)
                }
            }
            val finalOptions = (dList + correctNerve).toList().shuffled()
            
            currentQuestion = RapidQuestion(
                query = "What is the nerve supply of: \n${muscle.name}?",
                options = finalOptions,
                correctAnswer = correctNerve,
                hint = "Muscle Origin: ${muscle.origin}"
            )
        }
    }
    
    // Trigger initial question
    LaunchedEffect(Unit) {
        loadNextQuestion()
    }
    
    // Speed Game Timer loop
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            isPlaying = false
            prefs.saveRapidFireHighScore(score)
            highScore = prefs.getRapidFireHighScore()
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // High fidelity top bar
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "60s RAPID FIRE CHALLENGE",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .background(Color(0xFFFFECE5), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = "Daily High Score",
                            tint = Color(0xFFFA541C),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Best: $highScore",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFE0420F)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
        )
        
        if (isPlaying && currentQuestion != null) {
            val question = currentQuestion!!
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Progress Timer Bar & Score
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Alarm,
                            contentDescription = "Timer",
                            tint = if (timeLeft < 15) Color.Red else MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "$timeLeft seconds",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (timeLeft < 15) Color.Red else MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Text(
                        text = "Score: $score",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                
                // Question text
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "VIVA EXAMINER ASKED:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = question.query,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 26.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                
                // Options list
                question.options.forEach { option ->
                    val isCorrect = option == question.correctAnswer
                    val isSelected = selectedAnswer == option
                    
                    val cardBg = when {
                        selectedAnswer != null && isCorrect -> Color(0xFFE6F4EA)
                        selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFFCE8E6)
                        else -> MaterialTheme.colorScheme.surface
                    }
                    val cardBorderColor = when {
                        selectedAnswer != null && isCorrect -> Color(0xFF34A853)
                        selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    }
                    val cardBorderWidth = if (selectedAnswer != null && (isCorrect || isSelected)) 2.dp else 1.dp
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = selectedAnswer == null) {
                                selectedAnswer = option
                                if (isCorrect) {
                                    score += 10
                                    // Reward correct answer with +2 seconds!
                                    timeLeft += 2
                                } else {
                                    // Penalty of 5 seconds for wrong answer!
                                    timeLeft = maxOf(0, timeLeft - 5)
                                }
                            },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = cardBg),
                        border = BorderStroke(cardBorderWidth, cardBorderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        when {
                                            selectedAnswer != null && isCorrect -> Color(0xFF34A853)
                                            selectedAnswer != null && isSelected && !isCorrect -> Color(0xFFEA4335)
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        },
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (selectedAnswer != null && isCorrect) {
                                    Icon(Icons.Default.Check, contentDescription = "Correct", tint = Color.White, modifier = Modifier.size(14.dp))
                                } else if (selectedAnswer != null && isSelected && !isCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = Color.White, modifier = Modifier.size(14.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (selectedAnswer != null && isCorrect) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedAnswer != null && isCorrect) Color(0xFF137333) else if (selectedAnswer != null && isSelected && !isCorrect) Color(0xFFC5221F) else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
                
                if (selectedAnswer != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { loadNextQuestion() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Next Question", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
        } else {
            // Rapid Fire results sheet
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color(0xFFFFECE5), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = "Lightning",
                        tint = Color(0xFFFA541C),
                        modifier = Modifier.size(72.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "60s Rapid Challenge Fired!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Total Score: $score points",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (score >= 80) "Brilliant! Your clinical knowledge is fully dynamic, sharp, and instantaneous! Keep this rapid reflexes!"
                           else "Adrenaline was high! Take it once more to crush your high score and cement core facts.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Exit Game")
                    }
                    Button(
                        onClick = {
                            score = 0
                            timeLeft = 60
                            isPlaying = true
                            selectedAnswer = null
                            loadNextQuestion()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Re-Fire Challenge")
                    }
                }
            }
        }
    }
}
