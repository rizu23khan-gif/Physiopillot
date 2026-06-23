package com.example.feature.topic

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.data.ContentRepo

data class PredefinedTopic(
    val id: String,
    val title: String,
    val subtitle: String,
    val overviewExplanation: String,
    val whyImportant: String,
    val keyFacts: List<String>,
    val examPearls: List<String>,
    val anatomyMuscle: String?, // name of muscle/nerve to preload for Anatomy Revision
    val assessmentCaseQuery: String?, // query to preload for Clinical Cases search
    val specialTestQuery: String?, // query to preload for Special Tests search
    val vivaTopicCategory: String?, // category to preload for Viva Prep
    val quickNotes: String,
    val mnemonics: List<Pair<String, String>>, // acronym to translation
    val highYieldPoints: List<String>,
    val relatedTopics: List<String> // matching list of IDs
)

val listOfPredefinedTopics = listOf(
    PredefinedTopic(
        id = "stroke",
        title = "Stroke Motor Recovery",
        subtitle = "Upper Motor Neuron Lesion Rehab",
        overviewExplanation = "A cerebrovascular accident (CVA) resulting in disruption of cortical blood supply (typically MCA or ACA), leading to contralateral hemiplegia, muscle tone alterations, and abnormal synergistic movement recovery patterns.",
        whyImportant = "A core pillar of Neurological Physiotherapy. Students are heavily clinical-tested on Brunnstrom Stages of recovery, spasticity management, gait re-education, and neural plasticity principles.",
        keyFacts = listOf(
            "UMN lesion results in initial flaccidity (spinal shock) followed by progressive spasticity.",
            "Motor recovery typically progresses through 6 distinct Brunnstrom stages, moving from joint synergies to isolated actions.",
            "Upper limb typically presents with flexion synergy (shoulder adducted/internally rotated, elbow flexed, wrist/fingers flexed).",
            "Lower limb typically shows extension synergy, presenting a gait challenge (gait with circumduction)."
        ),
        examPearls = listOf(
            "State the 6 recovery stages by sign/movement (e.g. Stage 3 is peak spasticity).",
            "Contrast spasticity (velocity-dependent clasp-knife) vs rigidity (lead-pipe non-velocity-dependent).",
            "Identify the role of the corticospinal tract and motor cortex in voluntary motor trigger functions."
        ),
        anatomyMuscle = "Biceps Brachii",
        assessmentCaseQuery = "Stroke",
        specialTestQuery = "Neurological",
        vivaTopicCategory = "Neurology",
        quickNotes = "Incorporate task-oriented, high-repetition practice early. Manage abnormal synergy patterns while actively stabilizing the shoulder joint to avoid painful subluxations. Use weight-bearing setups to facilitate sensory feedback and joint protection.",
        mnemonics = listOf(
            "F.A.S.T." to "Face drooping, Arm weakness, Speech difficulty, Time to call emergency",
            "C.I.M.T." to "Constraint-Induced Movement Therapy: constraining the unaffected limb to force usage of hemiplegic side",
            "S.O.A.P." to "Support, Orient, Activate, Progress (Hemiplegic limb sensory-motor sequence)"
        ),
        highYieldPoints = listOf(
            "Identify safety limits of blood pressure before initiating intensive functional upright gait training.",
            "Bobath (NDT) centers postural control & hand alignment, while PNF uses diagonal patterns for sensory-motor execution.",
            "Use standardized scales such as modified Ashworth Scale (MAS) and Fugl-Meyer assessment to document motor recovery."
        ),
        relatedTopics = listOf("parkinsons_disease", "trendelenburg_gait", "erbs_palsy")
    ),
    PredefinedTopic(
        id = "frozen_shoulder",
        title = "Frozen Shoulder",
        subtitle = "Adhesive Capsulitis of the Glenohumeral Joint",
        overviewExplanation = "A progressive inflammatory condition characterized by painful stiffness and loss of active and passive glenohumeral range of motion in a classic capsular pattern (External Rotation > Abduction > Internal Rotation).",
        whyImportant = "Highly prevalent in diabetic and post-immobilization patients. Students must master differential diagnosis against rotator cuff tears, Maitland mobilization grading, and multi-stage physiological management.",
        keyFacts = listOf(
            "Consists of 3 key dynamic phases: Freezing (painful stage), Frozen (stiff stage), and Thawing (resolution stage).",
            "Pathological changes involve chronic synovial inflammation, capsular thickening, and fibrotic contracture.",
            "Primary capsular pattern limitation involves greatest loss of external rotation, followed by abduction, and internal rotation.",
            "Often requires joint mobilization focusing on the anterior and posterior joint capsule components."
        ),
        examPearls = listOf(
            "Always state the precise capsular pattern ratio (ER > Abduct > IR) in viva testing.",
            "Identify Codman's Pendulum exercises as early active-assisted traction setups to control pain.",
            "Know that Maitland Grade III/IV mobilizations are indicated in the stiff phase but contraindicated in acute inflammatory phase."
        ),
        anatomyMuscle = "Deltoid",
        assessmentCaseQuery = "Adhesive Capsulitis",
        specialTestQuery = "Neer",
        vivaTopicCategory = "Orthopaedics",
        quickNotes = "Address acute pain with gentle passive techniques, joint distraction, and Codman's oscillations. Progress to progressive end-range stretching and low-load, prolonged static holds to remodel the tight capsular tissue.",
        mnemonics = listOf(
            "F.F.T." to "Freezing (1-9 mos), Frozen (4-12 mos), Thawing (5-26 mos)",
            "E.A.I." to "External rotation > Abduction > Internal rotation (Degrees of capsular pattern restriction)"
        ),
        highYieldPoints = listOf(
            "Always correct scapulothoracic rhythm compensatory mechanics during shoulder rehabilitation.",
            "Do not stretch beyond the point of acute muscle guarding during the early inflammatory freezing phase.",
            "Utilize posterior glide to enhance shoulder internal rotation, and inferior glide to enhance shoulder abduction."
        ),
        relatedTopics = listOf("acl_rehab", "carpal_tunnel", "erbs_palsy")
    ),
    PredefinedTopic(
        id = "acl_rehab",
        title = "ACL Reconstruction Rehab",
        subtitle = "Anterior Cruciate Ligament Post-Surgical Phase Care",
        overviewExplanation = "Rehabilitation program following surgical reconstruction of a ruptured anterior cruciate ligament. Emphasizes evidence-based phase progressions, graft remodeling limits, and tissue healing protective windows.",
        whyImportant = "A quintessential sports medicine competency. Tested heavily on graft ligamentization timelines, quadriceps shut-down, and functional return-to-play assessment metrics.",
        keyFacts = listOf(
            "Autografts are weakest between 6 to 12 weeks post-surgery due to revascularization and necrosis transition.",
            "Restoring full passive symmetrical knee extension immediately is non-negotiable to prevent cyclops lesions.",
            "Open kinetic chain (OKC) quadriceps extension between 0°-45° is restricted early due to anterior tibial shear stress.",
            "Closed kinetic chain exercises (squats/step-ups) distribute forces safely and co-contract hamstrings."
        ),
        examPearls = listOf(
            "State that the Lachman test is the gold-standard manual test with highest sensitivity for ACL laxity.",
            "Recognize the 'screw-home mechanism' of knee lock: external rotation of tibia in terminal extension.",
            "Explain graft choices: Bone-Patellar Tendon-Bone (BPTB) offers high initial rigidity but risks anterior knee pain."
        ),
        anatomyMuscle = "Quadriceps",
        assessmentCaseQuery = "ACL Reconstruction",
        specialTestQuery = "Lachman",
        vivaTopicCategory = "Sports Physiotherapy",
        quickNotes = "Regain pain-free terminal knee extension first. Focus early on VMO activation (isometric quad sets with patellar mobilization) list-wise, and protect the graft by avoiding early shear-heavy unresisted terminal knee extension arcs.",
        mnemonics = listOf(
            "L.A.P." to "Lachman, Anterior drawer, Pivot shift (The clinical diagnostic triad of ACL integrity)",
            "P.R.I.C.E." to "Protect, Rest, Ice, Compress, Elevate (Acute postoperative joint management)"
        ),
        highYieldPoints = listOf(
            "Graft ligamentization is an ongoing process of integration that takes up to 1 to 2 years.",
            "Ensure a minimum of 90% limb symmetry index (LSI) in hamstring/quadriceps torque before clearing return-to-sport activities.",
            "Integrate hip abductor (Gluteus Medius) conditioning to prevent dynamic knee valgus landing mechanics."
        ),
        relatedTopics = listOf("knee_oa", "trendelenburg_gait", "frozen_shoulder")
    ),
    PredefinedTopic(
        id = "carpal_tunnel",
        title = "Carpal Tunnel Syndrome",
        subtitle = "Median Nerve Entrapment Neuropathy",
        overviewExplanation = "Entrapment of the median nerve as it passes through the wrist beneath the flexor retinaculum, causing pain, paresthesia, and sensory deficits in the lateral three and a half digits, alongside possible thenar muscle atrophy.",
        whyImportant = "Critical hand rehabilitation topic. Practical viva examinations regularly request hand sensory maps, tests, and active gliding protocols.",
        keyFacts = listOf(
            "The carpal tunnel is an osteofibrous canal occupied by 1 median nerve and 9 flexor tendons.",
            "Nocturnal paresthesia (night pain) is a classic diagnostic symptom.",
            "Ape hand deformity develops due to chronic wasting of the Abductor Pollicis Brevis.",
            "Splinting the wrist in neutral (0°-5°) keeps carpal tunnel pressures at their physiological lowest."
        ),
        examPearls = listOf(
            "Distinguish Carpal Tunnel compression (spares palmar cutaneous branch) from high median nerve lesion (affects palate sensory).",
            "State Phalen's test holding time of exactly 60 seconds to elicit positive paresthesia.",
            "Understand Tinel's percussion sign as indicative of nerve regenerations or hypersensitivity."
        ),
        anatomyMuscle = "Median Nerve",
        assessmentCaseQuery = "Carpal Tunnel Syndrome",
        specialTestQuery = "Phalen",
        vivaTopicCategory = "Electrophysical Agents",
        quickNotes = "Provide protective wrist splints for overnight use. Introduce systematic nerve and tendon gliding exercises to reduce adhesion risk. Use sensory re-education and ergonomic workstation corrections to lower risk.",
        mnemonics = listOf(
            "P.T.O." to "Phalen's, Tinel's sign, Opponens pollicis wasting (The hallmark diagnostic indicators)",
            "T.E.N.S." to "Transcutaneous Electrical Nerve Stimulation: used for non-pharmacological sensory gating"
        ),
        highYieldPoints = listOf(
            "Rule out proximal double crush syndromic compressions at the cervical spine or pronator teres muscle.",
            "Assess first dorsal interosseous function (ulnar nerve) vs thenar function (median nerve) to differentiate distal lesions.",
            "Use therapeutic ultrasound in mild/moderate cases to promote anti-inflammatory restoration."
        ),
        relatedTopics = listOf("erbs_palsy", "frozen_shoulder", "stroke")
    ),
    PredefinedTopic(
        id = "erbs_palsy",
        title = "Erb's Palsy",
        subtitle = "Upper Brachial Plexus Traction Injury",
        overviewExplanation = "A traction lesion of the upper trunk (C5, C6 nerve roots) of the brachial plexus during difficult childbirth or shoulder trauma, paralyzing shoulder abductors, external rotators, and forearm supinators.",
        whyImportant = "The primary obstetric neurological condition featured in pediatric physiotherapy curriculum. Highly tested on postural presentation, muscle mapping, and splinting.",
        keyFacts = listOf(
            "Presents with the classic 'Waiter’s Tip' or 'Policeman’s Tip' posture.",
            "Affected arm is held in adduction, internal rotation, elbow extension, forearm pronation, and wrist flexion.",
            "Key paralyzed muscles are Deltoid, Biceps Brachii, Brachialis, Supraspinatus, Infraspinatus, and Supinator.",
            "Sensory loss is located along the lateral aspect of the arm and forearm (C5-C6 dermatomes)."
        ),
        examPearls = listOf(
            "Explain that Erb's Palsy involves upper trunk roots C5-C6, whereas Klumpke's Paralysis involves lower trunk C8-T1 (claw hand).",
            "Moro reflex is classically absent on the affected side in neonates.",
            "State that the Statue of Liberty or Aeroplane splint is used to hold the shoulder in abduction and external rotation."
        ),
        anatomyMuscle = "Deltoid",
        assessmentCaseQuery = "Erb's Palsy",
        specialTestQuery = "Neurological",
        vivaTopicCategory = "Neurology",
        quickNotes = "Maintain range of motion with passive, gentle movements of the baby's arm to prevent contracture. Guide parents on supportive positioning to prevent pulling the arm. Use sensory stimulation techniques to boost neural recovery.",
        mnemonics = listOf(
            "C.5.C.6" to "The root of Erb's: Shoulder abduction, external rotation, and elbow flexion shut down",
            "W.A.S." to "Waiter's posture: Wrist flexed, Arm rotated in, Shoulder adducted"
        ),
        highYieldPoints = listOf(
            "Assess scapular winging indicating serratus anterior impairment if C5 root damage extends proximally.",
            "Initiate active play therapy (reaching, bilateral toys) as soon as voluntary muscle contraction returns.",
            "Observe for muscle imbalances that can lead to posterior shoulder subluxation if left unmanaged."
        ),
        relatedTopics = listOf("carpal_tunnel", "stroke", "trendelenburg_gait")
    ),
    PredefinedTopic(
        id = "knee_oa",
        title = "Knee Osteoarthritis",
        subtitle = "Degenerative Joint Disease (Primary / Secondary)",
        overviewExplanation = "A progressive, chronic degenerative joint disorder characterized by articular cartilage loss, subchondral bone sclerosis, joint-margin osteophytes, and synovial tissue irritation, causing severe weight-bearing pain and disability.",
        whyImportant = "Highly prevalent in elderly populations. Essential clinical competencies include staging severity (Kellgren-Lawrence), prescribing muscle-sparing exercise, and implementing joint unloading strategies.",
        keyFacts = listOf(
            "Most frequently affects the medial tibiofemoral joint compartment, resulting in genu varum (bow-legs).",
            "Morning stiffness typically lasts less than 30 minutes, distinguishing it from rheumatoid arthritis.",
            "Quadriceps muscle weakness is a primary predictor of functional decline and joint space narrowing speed.",
            "Pathology is non-inflammatory systemic but localized wear-and-tear with secondary joint swelling."
        ),
        examPearls = listOf(
            "Memorize Kellgren-Lawrence grades: Grade 1 (doubtful narrowing) to Grade 4 (severe narrowing with large osteophytes).",
            "Understand that joint crepitus occurs due to rough, degenerate cartilage surfaces sliding under compression.",
            "Identify contralateral single-cane usage as a biomechanical tool reducing hip/knee contact forces by up to 30%."
        ),
        anatomyMuscle = "Quadriceps",
        assessmentCaseQuery = "Osteoarthritis",
        specialTestQuery = "Patellar",
        vivaTopicCategory = "Orthopaedics",
        quickNotes = "Implement a low-impact aerobic exercise plan (such as swimming or stationary cycling). Target strengthening of the Vastus Medialis Oblique (VMO) and hip abductors to optimize patellar tracking and minimize varus stress.",
        mnemonics = listOf(
            "K.L.G." to "Kellgren-Lawrence Grades: 1=Doubtful, 2=Minimal osteophytes, 3=Moderate narrowing, 4=Joint bone-on-bone",
            "M.E.T." to "Mobilization, Exercise, Taping (The multimodal evidence-based physical treatment approach)"
        ),
        highYieldPoints = listOf(
            "Assess secondary compensation at the hip and ankle joint alignment.",
            "Advise weight loss in obese patients as a crucial factor in reducing knee loading during daily activities.",
            "Integrate perturbation training on unstable surfaces to boost joint proprioceptive stability."
        ),
        relatedTopics = listOf("acl_rehab", "trendelenburg_gait", "parkinsons_disease")
    ),
    PredefinedTopic(
        id = "trendelenburg_gait",
        title = "Trendelenburg Gait",
        subtitle = "Abnormal Locomotion due to Hip Abductor Weakness",
        overviewExplanation = "Uncompensated pelvic drop on the contralateral (swing) side during single-leg stance of the weak limb, or compensated trunk lurch over the stance limb, arising from Gluteus Medius weakness or mechanical disadvantage.",
        whyImportant = "The ultimate kinesiological diagnostic benchmark. Checked in all biomechanics, anatomy, and neurological physical therapy viva guides.",
        keyFacts = listOf(
            "Occurs when the Gluteus Medius muscle fails to stabilize the pelvis in one-legged stance.",
            "The pelvic drop occurs on the opposite side of the weak Gluteus Medius muscle.",
            "The superior gluteal nerve (C5-S1) supplies the gluteus medius and minimus muscles.",
            "Commonly seen in poliomyelitis, high L5 nerve root compressions, congenital hip dislocations, and hip fractures."
        ),
        examPearls = listOf(
            "Differentiate the Trendelenburg Sign (clinical test in static stance) from Trendelenburg Gait (dynamic presentation during walking).",
            "Indicate that Gluteus Medius acts as a first-class Lever system with the hip joint acting as the fulcrum.",
            "Identify compensated gait by the lateral trunk lean *toward* the week side to keep the center of gravity centered."
        ),
        anatomyMuscle = "Gluteus Medius",
        assessmentCaseQuery = "Trendelenburg",
        specialTestQuery = "Trendelenburg",
        vivaTopicCategory = "Biomechanics",
        quickNotes = "Design targeted isolation work for the Gluteus Medius including lateral leg raises, clam exercises, and pelvic drop-and-raises. Advance to dynamic single-leg stance drills and lateral band walks to restore walking stability.",
        mnemonics = listOf(
            "D.S.W." to "Pelvis Drops on the Swing side due to Weakness of the stance-side muscle",
            "S.G.N." to "Superior Gluteal Nerve: the motor supply susceptible to surgical or mechanical injuries"
        ),
        highYieldPoints = listOf(
            "Perform gait analysis checking step length asymmetries, foot clearance, and vertical COG excursion.",
            "Instruct patients to hold a cane in the hand opposite the weak side to biomechanically unload the joint.",
            "Evaluate joint integrity of the femur and pelvic ring to rule out mechanical causes of Trendelenburg sign."
        ),
        relatedTopics = listOf("stroke", "knee_oa", "parkinsons_disease")
    ),
    PredefinedTopic(
        id = "parkinsons_disease",
        title = "Parkinson's Disease",
        subtitle = "Basal Ganglia Neurodegenerative Rehab",
        overviewExplanation = "A progressive, chronic neurological disorder marked by dopamine depletion in the substantia nigra of the basal ganglia, leading to motor control symptoms: resting tremor, rigidity, bradykinesia, and postural instability.",
        whyImportant = "Critical long-term neurological rehabilitation topic. Physical therapy focuses on gait cueing, posture correction, trunk rotation, and fall mitigation.",
        keyFacts = listOf(
            "Rigidity presents as Cogwheel (with tremor) or Lead-pipe (uniform stiff resistance) muscle tone.",
            "Classic dynamic gait presentation is 'Festinating': rapid, shuffling steps with diminished arm swing and bent posture.",
            "Underlying neurochemistry involves depletion of dopamine, disrupting inhibitory/excitatory basal ganglia pathways.",
            "Prone to freezing of gait (FOG), particularly when navigating narrow spaces or turning."
        ),
        examPearls = listOf(
            "Memorize the symptom acronym T.R.A.P. (Tremor, Rigidity, Akinesia, Postural instability).",
            "Explain Hoehn & Yahr staging: Grade 1 (unilateral symptoms) to Grade 5 (confined to wheelchair/bed).",
            "Know that external sensory cueing (visual laser lines or metronome beats) bypasses broken basal ganglia circuits through cortical pathways."
        ),
        anatomyMuscle = "Gluteus Medius", // fallback, uses basic muscles
        assessmentCaseQuery = "Parkinson",
        specialTestQuery = "Neurological",
        vivaTopicCategory = "Neurology",
        quickNotes = "Incorporate big-amplitude, high-effort movements (similar to LSVT BIG principles). Use external physical lines or auditory metronome beats to bypass basal ganglia dysfunction. Work on axial rotation exercises to loosen rigid trunk joints.",
        mnemonics = listOf(
            "T.R.A.P." to "Tremor (resting), Rigidity, Akinesia/Bradykinesia, Postural instability",
            "F.O.G." to "Freezing of Gait: sudden inability to step, high risk for falls"
        ),
        highYieldPoints = listOf(
            "Coordinate physical therapy sessions with the patient's 'On' medication timing (typically 45-60 mins post-dose).",
            "Standardize assessments using Berg Balance Scale and Timed Up and Go (TUG) to track fall risk.",
            "Include dual-task gait training (cognitive tasks while walking) to boost functional independence."
        ),
        relatedTopics = listOf("stroke", "trendelenburg_gait", "knee_oa")
    )
)

fun getTopicForQuery(topicId: String, query: String): PredefinedTopic {
    val idToMatch = if (topicId.isNotEmpty()) topicId.lowercase() else {
        val q = query.lowercase().trim()
        when {
            q.contains("stroke") || q.contains("hemiplegia") || q.contains("cva") || q.contains("bleed") -> "stroke"
            q.contains("frozen") || q.contains("capsulitis") || q.contains("adhesive") -> "frozen_shoulder"
            q.contains("acl") || q.contains("cruciate") || q.contains("ligament") || q.contains("reconstruct") || q.contains("lachman") -> "acl_rehab"
            q.contains("carpal") || q.contains("tunnel") || q.contains("median") || q.contains("phalen") -> "carpal_tunnel"
            q.contains("erb") || q.contains("palsy") || q.contains("brachial") || q.contains("waiter") -> "erbs_palsy"
            q.contains("osteoarthritis") || q.contains("oa") || q.contains("cartilage") || q.contains("capsular") -> "knee_oa"
            q.contains("trendelenburg") || q.contains("gait") || q.contains("gluteus") || q.contains("medius") || q.contains("lurch") -> "trendelenburg_gait"
            q.contains("parkinson") || q.contains("rigid") || q.contains("shuffl") || q.contains("tremor") -> "parkinsons_disease"
            else -> ""
        }
    }
    val matched = listOfPredefinedTopics.find { it.id == idToMatch }
    return matched ?: createDynamicTopic(query)
}

fun createDynamicTopic(query: String): PredefinedTopic {
    val q = if (query.isBlank()) "Physiotherapy Revision" else query.trim()
    
    // Attempt muscle deep link mapping
    val anatomyTarget = if (q.contains("deltoid", ignoreCase=true)) "Deltoid" 
                        else if (q.contains("biceps", ignoreCase=true) || q.contains("brachii", ignoreCase=true)) "Biceps Brachii"
                        else if (q.contains("quadriceps", ignoreCase=true) || q.contains("vastus", ignoreCase=true) || q.contains("thigh", ignoreCase=true)) "Quadriceps"
                        else if (q.contains("glute", ignoreCase=true)) "Gluteus Medius"
                        else "Deltoid"

    return PredefinedTopic(
        id = "dynamic_${q.hashCode()}",
        title = q,
        subtitle = "High-Yield Integrated Revision Card",
        overviewExplanation = "An essential topic in the physiotherapy syllabus. Dynamic clinical reviews and practical components compiled from active physical therapy and rehabilitation repositories.",
        whyImportant = "Frequently presented in university viva tests, clinical case studies, and during clinical hospital bedside posting assignments.",
        keyFacts = listOf(
            "Critical to evaluate joint range of motion, muscle length, and structural tissue integrity.",
            "Strongly correlates with biomechanical load distribution and nervous system tracking control.",
            "Requires careful assessment to rule out red flags such as swelling, sensory loss, or acute pain."
        ),
        examPearls = listOf(
            "State the exact baseline anatomy, origin, insertion, and nerve root supply during oral examinations.",
            "Identify the specific positive diagnostic criteria / positive signs during manual clinical tests."
        ),
        anatomyMuscle = anatomyTarget,
        assessmentCaseQuery = q,
        specialTestQuery = q,
        vivaTopicCategory = "Orthopaedics",
        quickNotes = "Prioritize pain-free mobilization, structured progressive load-bearing, and core muscle coordination. Combine evidence-based electrophysical therapeutic agents with focused active movement recovery techniques.",
        mnemonics = listOf(
            "R.I.C.E.S." to "Rest, Ice, Compression, Elevation, Support (The cornerstone of acute tissue healing)",
            "M.M.T." to "Manual Muscle Testing (Grades 0 to 5) used to chart muscle strength progress"
        ),
        highYieldPoints = listOf(
            "Document active vs passive ROM to analyze articular constraint versus musculotendinous limits.",
            "Utilize objective assessment tools (Goniometer, VAS, Dynamometer) to confirm evidence-based progress."
        ),
        relatedTopics = listOf("stroke", "frozen_shoulder", "acl_rehab")
    )
}

private val bookmarkedTopicsState = mutableStateMapOf<String, Boolean>()
private val recentlyViewedTopics = mutableStateListOf<String>()
private val studyFrequencyMap = mutableStateMapOf<String, Int>()

fun getTopicDifficulty(topicId: String): String {
    return when (topicId) {
        "stroke", "parkinsons_disease", "erbs_palsy" -> "Advanced"
        "frozen_shoulder", "acl_rehab", "copd_rehab" -> "Intermediate"
        "trendelenburg_gait" -> "Beginner"
        else -> {
            if (topicId.startsWith("dynamic")) "Beginner"
            else "Intermediate"
        }
    }
}

@Composable
fun DifficultyBadge(difficulty: String) {
    val (backgroundColor, textColor) = when (difficulty) {
        "Beginner" -> Color(0xFFE8F5E9) to Color(0xFF2E7D32) // Soft Green
        "Intermediate" -> Color(0xFFE3F2FD) to Color(0xFF1565C0) // Soft Blue
        "Advanced" -> Color(0xFFFFEBEE) to Color(0xFFC62828) // Soft Red
        else -> Color(0xFFF5F5F5) to Color(0xFF616161)
    }
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Text(
            text = difficulty.uppercase(),
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = textColor,
            fontSize = 9.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TopicHubScreen(
    navController: NavHostController,
    topicId: String,
    queryStr: String
) {
    val context = LocalContext.current
    val topic = remember(topicId, queryStr) { getTopicForQuery(topicId, queryStr) }
    var selectedMode by remember { mutableStateOf(TopicMode.LEARN) }
    var isNotesCardFlipped by remember { mutableStateOf(false) }
    var selectedDiscoveryCategory by remember { mutableStateOf("All") }

    val filteredTopics = remember(selectedDiscoveryCategory, topic.id) {
        if (selectedDiscoveryCategory == "All") {
            listOfPredefinedTopics.filter { it.id != topic.id }
        } else {
            listOfPredefinedTopics.filter { 
                it.id != topic.id && it.vivaTopicCategory?.equals(selectedDiscoveryCategory, ignoreCase = true) == true 
            }
        }
    }

    val recommendedNextTopic = remember(topic, recentlyViewedTopics) {
        val sameCategory = listOfPredefinedTopics.filter { 
            it.id != topic.id && it.vivaTopicCategory == topic.vivaTopicCategory 
        }
        val inRelated = topic.relatedTopics.mapNotNull { relId -> 
            listOfPredefinedTopics.find { it.id == relId }
        }
        val others = listOfPredefinedTopics.filter { it.id != topic.id }
        
        sameCategory.firstOrNull() ?: inRelated.firstOrNull() ?: others.firstOrNull()
    }

    val frequentlyStudied = remember(studyFrequencyMap, topic.id) {
        listOfPredefinedTopics
            .filter { it.id != topic.id }
            .map { t -> t to (studyFrequencyMap[t.id] ?: 0) }
            .map { (t, count) ->
                val baseline = when(t.id) {
                    "stroke" -> 3
                    "frozen_shoulder" -> 2
                    "knee_osteoarthritis" -> 1
                    else -> 0
                }
                t to (count + baseline)
            }
            .sortedByDescending { it.second }
            .filter { it.second > 0 }
            .take(4)
    }

    LaunchedEffect(topic.id) {
        recentlyViewedTopics.remove(topic.id)
        recentlyViewedTopics.add(0, topic.id)
        if (recentlyViewedTopics.size > 8) {
            recentlyViewedTopics.removeLast()
        }
        studyFrequencyMap[topic.id] = (studyFrequencyMap[topic.id] ?: 0) + 1
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Topic Hub",
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
    ) { paddingValues ->
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // 1. Topic Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = topic.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = (topic.vivaTopicCategory ?: "Physiotherapy Revision").uppercase(),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                            DifficultyBadge(getTopicDifficulty(topic.id))
                        }
                    }

                    val isBookmarked = bookmarkedTopicsState[topic.id] ?: false
                    IconButton(
                        onClick = {
                            bookmarkedTopicsState[topic.id] = !isBookmarked
                        },
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                RoundedCornerShape(12.dp)
                            )
                            .testTag("bookmark_button_${topic.id}")
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // 2. Topic Summary Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "30-SECOND OVERVIEW",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Text(
                            text = topic.overviewExplanation,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.MedicalServices,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "WHY CLINICALLY IMPORTANT",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Text(
                            text = topic.whyImportant,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // 3. Three Mode Switcher & 4. Sticky mode selector while scrolling
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(vertical = 8.dp)
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f)
                        ),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = androidx.compose.ui.graphics.SolidColor(
                                MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                            ),
                            width = 1.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            TopicMode.values().forEach { mode ->
                                val selected = selectedMode == mode
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (selected) MaterialTheme.colorScheme.primary else Color.Transparent)
                                        .clickable { selectedMode = mode }
                                        .padding(vertical = 10.dp)
                                        .testTag("mode_tab_${mode.name.lowercase()}"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = mode.icon,
                                            contentDescription = null,
                                            tint = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = mode.title,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Mode Content Areas
            item {
                AnimatedContent(
                    targetState = selectedMode,
                    transitionSpec = {
                        fadeIn() togetherWith fadeOut()
                    },
                    label = "workflowStateTransition"
                ) { currentMode ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        when (currentMode) {
                            TopicMode.LEARN -> {
                                // 1. Pathology & Core Facts
                                TopicHubSectionHeader(text = "Pathology & Core Facts", icon = Icons.Default.Dns)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = "KEY SYLLABUS FACTS",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        topic.keyFacts.forEach { fact ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 3.dp),
                                                verticalAlignment = Alignment.Top
                                            ) {
                                                Text(
                                                    text = "•",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.padding(end = 8.dp)
                                                )
                                                Text(
                                                    text = fact,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                        }
                                    }
                                }

                                // 2. Anatomy Alignment
                                TopicHubSectionHeader(text = "Anatomy Alignment", icon = Icons.Default.Accessibility)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                    border = CardDefaults.outlinedCardBorder().copy(
                                        brush = androidx.compose.ui.graphics.SolidColor(
                                            MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                                        ),
                                        width = 1.dp
                                    )
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = "Core Musculoskeletal Structures",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Access deep biological structures, origins, insertions, and nerve pathways associated with ${topic.title}.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                                                .clickable {
                                                    if (topic.anatomyMuscle != null) {
                                                        ContentRepo.lastViewedAnatomy = topic.anatomyMuscle
                                                    }
                                                    navController.navigate("anatomy")
                                                }
                                                .padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                Icons.Default.AccessibilityNew,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = "Explore ${topic.anatomyMuscle ?: "Associated Structures"}",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Text(
                                                    text = "Nerve roots, biomechanics & palpation",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                            Icon(
                                                Icons.AutoMirrored.Filled.ArrowForward,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            TopicMode.REVISE -> {
                                // 1. Quick Notes & Interactive Flashcard
                                TopicHubSectionHeader(text = "Interactive Notes Flipcard", icon = Icons.Default.FlashOn)
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { isNotesCardFlipped = !isNotesCardFlipped }
                                        .border(
                                            width = 1.5.dp,
                                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                                            shape = RoundedCornerShape(16.dp)
                                        ),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isNotesCardFlipped) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            if (!isNotesCardFlipped) {
                                                Icon(
                                                    imageVector = Icons.Default.Lightbulb,
                                                    contentDescription = "Tap to Flip",
                                                    tint = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.size(32.dp)
                                                )
                                                Text(
                                                    text = "TAP TO FLIP STUDY CARD",
                                                    style = MaterialTheme.typography.labelLarge,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                                Text(
                                                    text = "Tap to reveal the high-yield quick notes formulation for ${topic.title}",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                                )
                                            } else {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.fillMaxWidth()
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.CheckCircle,
                                                        contentDescription = "Active Notes",
                                                        tint = MaterialTheme.colorScheme.secondary,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Text(
                                                        text = "QUICK STUDY NOTES",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.secondary
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = topic.quickNotes,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    lineHeight = 20.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
                                                Spacer(modifier = Modifier.height(8.dp))
                                                Text(
                                                    text = "Tap again to flip back",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontStyle = FontStyle.Italic,
                                                    color = MaterialTheme.colorScheme.outline
                                                )
                                            }
                                        }
                                    }
                                }

                                // 2. Mnemonics Deck
                                TopicHubSectionHeader(text = "Aesthetic Study Mnemonics", icon = Icons.Default.Bookmarks)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        LazyRow(
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            items(topic.mnemonics) { mn ->
                                                Card(
                                                    modifier = Modifier
                                                        .width(240.dp)
                                                        .border(
                                                            width = 1.dp,
                                                            color = MaterialTheme.colorScheme.outlineVariant,
                                                            shape = RoundedCornerShape(12.dp)
                                                        ),
                                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)),
                                                    shape = RoundedCornerShape(12.dp)
                                                ) {
                                                    Column(modifier = Modifier.padding(12.dp)) {
                                                        Text(
                                                            text = mn.first,
                                                            style = MaterialTheme.typography.titleMedium,
                                                            fontWeight = FontWeight.ExtraBold,
                                                            color = MaterialTheme.colorScheme.primary
                                                        )
                                                        Spacer(modifier = Modifier.height(4.dp))
                                                        Text(
                                                            text = mn.second,
                                                            style = MaterialTheme.typography.bodySmall,
                                                            color = MaterialTheme.colorScheme.onSurface,
                                                            lineHeight = 16.sp
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                // 3. Examiner's High-Yield Pearls Card
                                TopicHubSectionHeader(text = "Examiner's High-Yield Pearls", icon = Icons.Default.EmojiEvents)
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(0xFFFEF3C7))
                                        .border(1.dp, Color(0xFFF59E0B), RoundedCornerShape(12.dp))
                                        .padding(16.dp)
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.EmojiEvents,
                                                contentDescription = "Exam Pearl",
                                                tint = Color(0xFFD97706),
                                                modifier = Modifier.size(20.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "EXAMINER'S HIGH-YIELD PEARLS",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = Color(0xFFB45309),
                                                letterSpacing = 0.5.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        topic.examPearls.forEach { pearl ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 3.dp),
                                                verticalAlignment = Alignment.Top
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color(0xFFB45309),
                                                    modifier = Modifier
                                                        .size(14.dp)
                                                        .padding(top = 3.dp, end = 4.dp)
                                                )
                                                Text(
                                                    text = pearl,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    color = Color(0xFF78350F)
                                                )
                                            }
                                        }
                                    }
                                }

                                // 4. High-Yield Facts Deck
                                TopicHubSectionHeader(text = "High-Yield Facts Deck", icon = Icons.Default.CheckCircleOutline)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        topic.highYieldPoints.forEach { point ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 4.dp),
                                                verticalAlignment = Alignment.Top
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.OfflineBolt,
                                                    contentDescription = null,
                                                    tint = Color(0xFFF59E0B),
                                                    modifier = Modifier
                                                        .size(18.dp)
                                                        .padding(top = 2.dp, end = 4.dp)
                                                )
                                                Text(
                                                    text = point,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                        }
                                    }
                                }

                                // 5. Viva Preparation Suite
                                TopicHubSectionHeader(text = "Viva Preparation Suite", icon = Icons.Default.MenuBook)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                    border = CardDefaults.outlinedCardBorder().copy(
                                        brush = androidx.compose.ui.graphics.SolidColor(
                                            MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                                        ),
                                        width = 1.dp
                                    )
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = "WBUHS Oral Cards & MCQs",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Review curated university guidelines, examiner tips, and high-yield question cards.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(Color(0xFFF3E8FF))
                                                .clickable {
                                                    if (topic.vivaTopicCategory != null) {
                                                        ContentRepo.lastRevisedViva = topic.vivaTopicCategory
                                                    }
                                                    navController.navigate("viva")
                                                }
                                                .padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                Icons.Default.School,
                                                contentDescription = null,
                                                tint = Color(0xFF9333EA)
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = "Train with ${topic.vivaTopicCategory ?: "Syllabus"} Viva Deck",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color(0xFF7E22CE)
                                                )
                                                Text(
                                                    text = "Structured MCQ diagnostics & answers",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                            Icon(
                                                Icons.AutoMirrored.Filled.ArrowForward,
                                                contentDescription = null,
                                                tint = Color(0xFF9333EA),
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            TopicMode.CLINICAL -> {
                                // 1. Clinical Assessment
                                TopicHubSectionHeader(text = "Clinical Assessment", icon = Icons.Default.MedicalServices)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                    border = CardDefaults.outlinedCardBorder().copy(
                                        brush = androidx.compose.ui.graphics.SolidColor(
                                            MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                                        ),
                                        width = 1.dp
                                    )
                                ) {
                                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                        Text(
                                            text = "Special Tests & Clinical Cases",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Review orthopedic diagnostics and university-style mock patient history files centered on ${topic.title}.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            // Special Tests link button
                                            Card(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable {
                                                        if (topic.specialTestQuery != null) {
                                                            ContentRepo.lastViewedSpecialTestQuery = topic.specialTestQuery
                                                        }
                                                        navController.navigate("special_tests")
                                                    },
                                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                                            ) {
                                                Column(modifier = Modifier.padding(12.dp)) {
                                                    Icon(Icons.Default.Healing, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                                                    Spacer(modifier = Modifier.height(6.dp))
                                                    Text("Special Tests", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                                    Text("Check positive indicators", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                                }
                                            }

                                            // Clinical Cases link button
                                            Card(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clickable {
                                                        if (topic.assessmentCaseQuery != null) {
                                                            ContentRepo.lastViewedClinicalCaseQuery = topic.assessmentCaseQuery
                                                        }
                                                        navController.navigate("clinical_cases")
                                                    },
                                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7).copy(alpha = 0.5f))
                                            ) {
                                                Column(modifier = Modifier.padding(12.dp)) {
                                                    Icon(Icons.Default.Assignment, contentDescription = null, tint = Color(0xFFD97706), modifier = Modifier.size(20.dp))
                                                    Spacer(modifier = Modifier.height(6.dp))
                                                    Text("Clinical Cases", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = Color(0xFFB45309))
                                                    Text("Analyze history files", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                                }
                                            }
                                        }
                                    }
                                }

                                // 2. Outcome Measures
                                TopicHubSectionHeader(text = "Outcome Measures", icon = Icons.Default.Timeline)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                        Text(
                                            text = "Clinical Scoring & Severity Indexes",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Objective diagnostic instruments to record clinical progression for ${topic.title}:",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        val measures = when {
                                            topic.title.contains("Stroke", ignoreCase = true) -> listOf(
                                                "Modified Ashworth Scale (MAS) - Tone",
                                                "Fugl-Meyer Assessment (FMA) - Motor recovery",
                                                "Brunnstrom Recovery Stages",
                                                "Berg Balance Scale (BBS) - Postural sway"
                                            )
                                            topic.title.contains("Frozen", ignoreCase = true) -> listOf(
                                                "Goniometer Joint Range of Motion (ROM)",
                                                "Shoulder Pain and Disability Index (SPADI)",
                                                "Visual Analogue Scale (VAS)",
                                                "Constant-Murley Score"
                                            )
                                            topic.title.contains("ACL", ignoreCase = true) -> listOf(
                                                "Limb Symmetry Index (LSI) - Quad torque",
                                                "Lysholm Knee Scoring Scale",
                                                "International Knee Documentation Committee (IKDC)",
                                                "Lachman test & Drawer metrics"
                                            )
                                            topic.title.contains("Carpal", ignoreCase = true) -> listOf(
                                                "Boston Carpal Tunnel Questionnaire (BCTQ)",
                                                "Semmes-Weinstein Monofilament testing",
                                                "Two-Point Discrimination Index",
                                                "Hand Grip Dynamometer torque"
                                            )
                                            topic.title.contains("Erb's", ignoreCase = true) -> listOf(
                                                "Active Movement Scale (AMS) for infants",
                                                "Mallet Classification score",
                                                "Moro Reflex dynamic assessment",
                                                "Goniometric active range testing"
                                            )
                                            topic.title.contains("Osteoarthritis", ignoreCase = true) || topic.title.contains("Knee OA", ignoreCase = true) -> listOf(
                                                "WOMAC Osteoarthritis Index",
                                                "Kellgren-Lawrence Severity Index",
                                                "Timed Up & Go (TUG) testing",
                                                "Visual Analogue Scale (VAS) for load"
                                            )
                                            topic.title.contains("Trendelenburg", ignoreCase = true) -> listOf(
                                                "Static Trendelenburg Pelvic Drop Sign",
                                                "Dynamic single-leg drop vertical swing",
                                                "Manual Muscle Testing (MMT) Gluteus Medius",
                                                "Center of Gravity (COG) sway displacement"
                                            )
                                            topic.title.contains("Parkinson", ignoreCase = true) -> listOf(
                                                "Unified Parkinson's Disease Rating Scale (UPDRS)",
                                                "Hoehn & Yahr Staging Score",
                                                "Timed Up and Go (TUG) for Freezing",
                                                "Berg Balance Scale (BBS) falling risk"
                                            )
                                            else -> listOf(
                                                "Visual Analogue Scale (VAS) for pain",
                                                "Manual Muscle Testing (MMT) metrics",
                                                "Goniometric Active/Passive ROM degrees",
                                                "Short Form-36 Health Questionnaire"
                                            )
                                        }

                                        measures.forEach { valStr ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 2.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.CheckCircle,
                                                    contentDescription = null,
                                                    tint = MaterialTheme.colorScheme.primary,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text(
                                                    text = valStr,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }
                                    }
                                }

                                // 3. Treatment Guidelines
                                TopicHubSectionHeader(text = "Treatment Guidelines", icon = Icons.Default.Healing)
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                        Text(
                                            text = "Therapeutic Protocols & Pathways",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Clinical rehabilitation progression standards to execute bed-side treatment:",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        Spacer(modifier = Modifier.height(4.dp))

                                        val instructions = when {
                                            topic.title.contains("Stroke", ignoreCase = true) -> listOf(
                                                "Early mobilization during flaccidity to optimize neural plasticity.",
                                                "Bobath NDT alignment focusing on trunk stability and symmetry.",
                                                "PNF diagonal patterns to re-trigger voluntary muscle control.",
                                                "Constraint-Induced Movement Therapy (CIMT) to bypass learned non-use."
                                            )
                                            topic.title.contains("Frozen", ignoreCase = true) -> listOf(
                                                "Control pain under early freezing stage (avoid aggressive overstretching).",
                                                "Introduce low-load Codman's Pendulum exercises for joint distraction.",
                                                "Progress Maitland Grade III & IV mobilizations under stiff thawed phase.",
                                                "Correct compensatory scapulothoracic rhythm movement patterns."
                                            )
                                            topic.title.contains("ACL", ignoreCase = true) -> listOf(
                                                "Restore pain-free knee terminal extension immediately to prevent cyclops lesion.",
                                                "Protect graft by avoiding Early unresisted OKC quad extension (0-45°).",
                                                "Employ CCK (squats/step-ups) for co-contraction stabilizer safety.",
                                                "Target lateral Gluteus Medius to suppress dynamic knee valgus patterns."
                                            )
                                            topic.title.contains("Carpal", ignoreCase = true) -> listOf(
                                                "Implement protective wrist splints keeping neutral (0-5°) lower pressures.",
                                                "Prescribe progressive nerve and tendon gliding exercises to lower friction.",
                                                "Apply non-pharmacological TENS for gating and sensory relief.",
                                                "Formulate ergonomic adjustment of keyboard positioning load factors."
                                            )
                                            topic.title.contains("Erb's", ignoreCase = true) -> listOf(
                                                "Introduce passive range exercises to restrain fast flexor contractures.",
                                                "Advise gentle, support-aligned parenting handling configurations.",
                                                "Configure Statue of Liberty/Aeroplane splint keeping abduct safety.",
                                                "Conduct play therapy to guide active muscle triggers of the shoulder."
                                            )
                                            topic.title.contains("Osteoarthritis", ignoreCase = true) || topic.title.contains("Knee OA", ignoreCase = true) -> listOf(
                                                "Introduce low-impact aerobics (swimming/cycling) to lubricate surfaces.",
                                                "Strengthen weak VMO structures to realign patellofemoral tracking.",
                                                "Advise single contralateral cane usage to diminish medial stress profiles.",
                                                "Integrate perturbation stability to boost secondary mechanoreceptors."
                                            )
                                            topic.title.contains("Trendelenburg", ignoreCase = true) -> listOf(
                                                "Configure targeted isolation muscle conditioning (lateral raises, clams).",
                                                "Progress single-leg stability exercises under horizontal control.",
                                                "Examine gait asymmetry, step length, and lateral lurch compensation.",
                                                "Coordinate hip load offsets utilizing opposite hand cane positioning."
                                            )
                                            topic.title.contains("Parkinson", ignoreCase = true) -> listOf(
                                                "Target motor amplitude using big movement sweeps (LSVT BIG concepts).",
                                                "Deploy rhythmic metronome and laser line cueing to bypass basal ganglia.",
                                                "Address rigid axis constraints utilizing progressive trunk rotations.",
                                                "Perform dynamic dual-task balance drills to diminish falling risks."
                                            )
                                            else -> listOf(
                                                "Address acute tissue swelling utilizing standard compression/RICE protocols.",
                                                "Initiate pain-free active-assisted range exercises to maintain length.",
                                                "Target surrounding core stability patterns to optimize load distribution.",
                                                "Educate patient in progressive pacing to guard against flare-ups."
                                            )
                                        }

                                        instructions.forEachIndexed { idx, value ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 3.dp),
                                                verticalAlignment = Alignment.Top
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(20.dp)
                                                        .background(
                                                            MaterialTheme.colorScheme.primaryContainer,
                                                            RoundedCornerShape(10.dp)
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = "${idx + 1}",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MaterialTheme.colorScheme.primary
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(
                                                    text = value,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    modifier = Modifier.weight(1f)
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

            // 5. Universal Topic Discovery & Category Chips (Requirement 5)
            item {
                TopicHubSectionHeader(text = "Topic Discovery", icon = Icons.Default.Explore)
                Text(
                    text = "Select a category to browse high-yield syllabus clusters, evaluate topics in self-study loops, and explore relevant modules.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val categories = listOf("All", "Neurology", "Orthopaedics", "Sports Physiotherapy", "Biomechanics", "Electrophysical Agents")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(categories) { cat ->
                        val isSelected = selectedDiscoveryCategory == cat
                        Surface(
                            modifier = Modifier
                                .clickable { selectedDiscoveryCategory = cat }
                                .testTag("category_chip_$cat"),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                width = 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                            )
                        ) {
                            Text(
                                text = cat,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }



            if (filteredTopics.isNotEmpty()) {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        contentPadding = PaddingValues(bottom = 8.dp)
                    ) {
                        items(filteredTopics) { otherTopic ->
                            Card(
                                modifier = Modifier
                                    .width(220.dp)
                                    .clickable {
                                        ContentRepo.lastViewedSpecialTestQuery = null
                                        ContentRepo.lastViewedClinicalCaseQuery = null
                                        navController.navigate("topic_hub?topicId=${otherTopic.id}")
                                    }
                                    .testTag("explore_topic_${otherTopic.id}"),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                shape = RoundedCornerShape(12.dp),
                                border = CardDefaults.outlinedCardBorder().copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(
                                        MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                                    ),
                                    width = 1.dp
                                )
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = (otherTopic.vivaTopicCategory ?: "REVISION").uppercase(),
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                        DifficultyBadge(getTopicDifficulty(otherTopic.id))
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = otherTopic.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = otherTopic.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Explore Module",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                    ) {
                        Box(modifier = Modifier.padding(16.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "No matching topics currently found for this category.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // 6. Recommended Next Topic (Requirement 2)

            recommendedNextTopic?.let { nextTopic ->
                item {
                    TopicHubSectionHeader(text = "Recommended Next Topic", icon = Icons.Default.Campaign)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                ContentRepo.lastViewedSpecialTestQuery = null
                                ContentRepo.lastViewedClinicalCaseQuery = null
                                navController.navigate("topic_hub?topicId=${nextTopic.id}")
                            }
                            .testTag("recommended_next_card"),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            width = 1.2.dp,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "HIGH ALIGNMENT",
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 8.sp
                                        )
                                    }
                                    DifficultyBadge(getTopicDifficulty(nextTopic.id))
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = nextTopic.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = nextTopic.subtitle,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Surface(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.size(40.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Go",
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 7. Related Topics (Requirement 1)
            item {
                TopicHubSectionHeader(text = "Related Topics Engine", icon = Icons.Default.Extension)
                val matchedRelated = remember(topic) {
                    topic.relatedTopics.mapNotNull { relId ->
                        listOfPredefinedTopics.find { it.id == relId }
                    }
                }
                
                if (matchedRelated.isNotEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        matchedRelated.forEach { relTop ->
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        ContentRepo.lastViewedSpecialTestQuery = null
                                        ContentRepo.lastViewedClinicalCaseQuery = null
                                        navController.navigate("topic_hub?topicId=${relTop.id}")
                                    }
                                    .testTag("related_topic_${relTop.id}"),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                shape = RoundedCornerShape(12.dp),
                                border = CardDefaults.outlinedCardBorder().copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(
                                        MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                                    ),
                                    width = 1.dp
                                )
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        DifficultyBadge(getTopicDifficulty(relTop.id))
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = relTop.title,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = relTop.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                    ) {
                        Box(modifier = Modifier.padding(16.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "No directly related topics found.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // 8. Recently Viewed Topics (Requirement 3)
            item {
                TopicHubSectionHeader(text = "Recently Studied Topics", icon = Icons.Default.History)
                val recentlyViewedOthers = remember(recentlyViewedTopics, topic.id) {
                    recentlyViewedTopics
                        .filter { it != topic.id }
                        .mapNotNull { id -> listOfPredefinedTopics.find { it.id == id } }
                }

                if (recentlyViewedOthers.isNotEmpty()) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(recentlyViewedOthers) { oldTopic ->
                            Card(
                                modifier = Modifier
                                    .width(200.dp)
                                    .clickable {
                                        ContentRepo.lastViewedSpecialTestQuery = null
                                        ContentRepo.lastViewedClinicalCaseQuery = null
                                        navController.navigate("topic_hub?topicId=${oldTopic.id}")
                                    }
                                    .testTag("recently_viewed_${oldTopic.id}"),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                shape = RoundedCornerShape(12.dp),
                                border = CardDefaults.outlinedCardBorder().copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(
                                        MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                                    ),
                                    width = 1.dp
                                )
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Recent",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.secondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                        DifficultyBadge(getTopicDifficulty(oldTopic.id))
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = oldTopic.title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = oldTopic.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                    ) {
                        Box(modifier = Modifier.padding(16.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Your session study history is currently empty.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // 9. Frequently Studied (Requirement 4)
            item {
                TopicHubSectionHeader(text = "Frequently Studied", icon = Icons.Default.Poll)

                if (frequentlyStudied.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        frequentlyStudied.forEach { (freqTopic, count) ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        ContentRepo.lastViewedSpecialTestQuery = null
                                        ContentRepo.lastViewedClinicalCaseQuery = null
                                        navController.navigate("topic_hub?topicId=${freqTopic.id}")
                                    }
                                    .testTag("frequent_topic_${freqTopic.id}"),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                shape = RoundedCornerShape(12.dp),
                                border = CardDefaults.outlinedCardBorder().copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(
                                        MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                                    ),
                                    width = 1.dp
                                )
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(
                                                text = (freqTopic.vivaTopicCategory ?: "REVISION").uppercase(),
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.secondary
                                            )
                                            DifficultyBadge(getTopicDifficulty(freqTopic.id))
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = freqTopic.title,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.TrendingUp,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = "$count Views",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                    ) {
                        Box(modifier = Modifier.padding(16.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Your frequent research telemetry will construct here.",
                                style = MaterialTheme.typography.bodySmall,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class TopicMode(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    LEARN("Learn", Icons.Default.MenuBook),
    REVISE("Revise", Icons.Default.Quiz),
    CLINICAL("Clinical", Icons.Default.Healing)
}

@Composable
fun TopicHubSectionHeader(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 1.sp
        )
    }
}
