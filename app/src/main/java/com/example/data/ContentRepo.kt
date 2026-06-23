package com.example.data

import kotlinx.serialization.Serializable
import com.example.ui.components.TopicEvidenceReference
import com.example.ui.components.EvidenceLevel
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viva_mcqs")
@Serializable
data class VivaMcq(
    @PrimaryKey val id: String = java.util.UUID.randomUUID().toString(),
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val whyCorrect: String,
    val whyIncorrect: String,
    val clinicalImportance: String,
    val examinerTip: String,
    val subject: String,
    val difficulty: String,
    val learningPoint: String,
    val relatedConcept: String,
    val reference: String
)


@Serializable
data class DetailedDiagnosis(
    val id: String,
    val name: String,
    val relevantAnatomy: List<String>,
    val relevantPhysiology: List<String>,
    val clinicalCorrelation: List<String>,
    val pathology: List<String>,
    val pharmacology: List<String>,
    val microbiology: List<String>,
    val historyTaking: List<String>,
    val observation: List<String>,
    val palpation: List<String>,
    val rom: List<String>,
    val mmt: List<String>,
    val specialTests: List<String>,
    val outcomeMeasures: List<String>,
    val problemList: List<String>,
    val stg: List<String>,     // Short-Term Goals
    val ltg: List<String>,     // Long-Term Goals
    val ptManagement: List<String>,
    val clinicalPostingQuestions: List<String>,
    val vivaQuestions: List<String>,
    val references: List<String>,
    val neurologicalAssessment: List<String>,
    val functionalAssessment: List<String>,
    val clinicalReasoning: String
)

@Entity(tableName = "anatomy_revision")
@Serializable
data class DetailedAnatomy(
    @PrimaryKey val id: String,
    val name: String,
    val definition: String,
    val origin: String,
    val insertion: String,
    val nerveSupply: String,
    val bloodSupply: String,
    val action: String,
    val clinicalImportance: String,
    val commonConditions: List<String>,
    val vivaQuestions: List<String>,
    val references: List<String>,
    val searchTerms: List<String>,
    val anatomyType: String = "Muscle",
    val surfaceAnatomy: String = "",
    val biomechanicalImportance: String = "",
    val palpationLandmarks: String = "",
    val mnemonic: String = "",
    val quickRevision: String = ""
)

@Serializable
data class AdvancedVivaQuestion(
    val id: String,
    val topic: String,
    val question: String,
    val idealAnswer: String,
    val examinerExpectations: String,
    val references: List<String>
)

@Serializable
data class PostingStep(
    val title: String,
    val items: List<String>
)

@Entity(tableName = "clinical_posting_categories")
@Serializable
data class ClinicalPostingCategory(
    @PrimaryKey val id: String,
    val name: String,
    val steps: List<PostingStep>,
    val references: List<String>
)

object ContentRepo {
    @Volatile var lastViewedAnatomy: String? = null
    @Volatile var lastRevisedViva: String? = null
    @Volatile var lastViewedPosting: String? = null
    @Volatile var lastViewedSpecialTestQuery: String? = null
    @Volatile var lastViewedClinicalCaseQuery: String? = null
    
    val initialLibraryReferences = listOf(
        TopicEvidenceReference(
            topicId = "acl_reconstruction",
            title = "Anterior Cruciate Ligament (ACL) Reconstruction Rehab Protocol",
            category = "Sports Medicine & Orthopedics",
            referenceTextbooks = listOf(
                "Magee's Orthopedic Physical Assessment",
                "Kisner & Colby's Therapeutic Exercise",
                "West Bengal University of Health Sciences Syllabus Standards"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_I,
            sourceAttribution = "Journal of Orthopaedic & Sports Physical Therapy (JOSPT) 2024 CPG on Knee Ligament Sprains (Vol. 54, No. 3), Magee Chapter 12 (Knee: Special Tests), Kisner & Colby Chapter 21.",
            recommendedReading = "Kisner & Colby: Read 'Reconstructive Surgery of the Knee' describing early weight-bearing milestones and closed kinetic chain progress. Magee Checklist: Review Anterior Drawer, Lachman, and Pivot Shift sensitivity comparisons on Page 783.",
            clinicalNotes = "Avoid open kinetic chain extension exercises between 0°-45° during the first 6 weeks of rehab to protect the healing autograft from harmful shear stresses.",
            quickEvidenceSummary = "Level I systematic reviews confirm that early weight bearing and immediate progressive physical therapy do not compromise graft stability and significantly reduce the incidence of patellofemoral pain syndrome.",
            verifiedYear = "2024/2025 Edition"
        ),
        TopicEvidenceReference(
            topicId = "stroke_hemiplegia",
            title = "Stroke Motor Recovery & Neuro-Rehabilitation (MCA/ACA)",
            category = "Neurological Rehabilitation",
            referenceTextbooks = listOf(
                "O'Sullivan's Physical Rehabilitation",
                "Brunnstrom's Movement Therapy in Hemiplegia",
                "West Bengal University of Health Sciences Syllabus Standards"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_I,
            sourceAttribution = "O'Sullivan Chapter 18 (Stroke Rehabilitation), Brunnstrom Chapter 5 (Clinical Stages of Motor Recovery), Cochrane Database of Systematic Reviews: Task-Oriented Training in Stroke (2023).",
            recommendedReading = "O'Sullivan: Focus on 'Task-Oriented Training Interventions' outlining high-repetition functional practice. Brunnstrom: Review the 6 sequential stages of synergistic recovery in adult hemiplegia.",
            clinicalNotes = "Task-oriented, high-repetition resistive training must be triggered early. Do not reinforce abnormal synergy patterns during developmental therapeutic gait training.",
            quickEvidenceSummary = "Level I Cochrane reviews show extensive task-specific training and constraint-induced movement therapy (CIMT) yield superior cortical neuroplastic reorganization compared to passive Bobath facilitation.",
            verifiedYear = "2023/2024 Edition"
        ),
        TopicEvidenceReference(
            topicId = "knee_osteoarthritis",
            title = "Evidence-Based Management of Primary Knee Osteoarthritis",
            category = "Geriatric & Rheumatology",
            referenceTextbooks = listOf(
                "Kisner & Colby's Therapeutic Exercise",
                "Magee's Orthopedic Physical Assessment",
                "West Bengal University of Health Sciences Syllabus Standards"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_I,
            sourceAttribution = "OARSI (Osteoarthritis Research Society International) Guidelines 2024, Kisner & Colby Chapter 11 (Arthritis & Arthrosis), Magee Chapter 12.",
            recommendedReading = "Kisner & Colby: Study 'Conservative Management of Knee Joint Osteoarthrosis' with a direct emphasis on low-impact concentric quad reloading. WBUHS Syllabus: Focus on physical agents vs active therapeutic exercise.",
            clinicalNotes = "Combine loaded hip abductor and quadriceps strengthening to unload the medial tibiofemoral joint compartment, reducing joint degradation rates.",
            quickEvidenceSummary = "Exceptional Level I evidence demonstrates that supervised therapeutic exercise yields physical function benefits equivalent to typical oral NSAIDs with zero adverse gastrointestinal system side effects.",
            verifiedYear = "2024/2026 Edition"
        ),
        TopicEvidenceReference(
            topicId = "lumbar_disc_herniation",
            title = "Lumbar Disc Herniation with Radiculopathy (Sciatica)",
            category = "Spine Rehab & Neurology",
            referenceTextbooks = listOf(
                "Magee's Orthopedic Physical Assessment",
                "Hoppenfeld's Spine and Extremities",
                "Kisner & Colby's Therapeutic Exercise"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_II,
            sourceAttribution = "NASS (North American Spine Society) Evidence-Based Clinical Guidelines for Lumbar Disc Herniation (2022), Magee Chapter 9, Hoppenfeld Chapter 6.",
            recommendedReading = "Hoppenfeld: Review the complete sensory and motor neurological examination of L4, L5, and S1 nerve root segments on Page 242. Magee: Study Centralization and Peripheralization theories.",
            clinicalNotes = "Perform structural spinal traction and centralizing Extension maneuvers (McKenzie method) only if symptoms do not peripheralize into lower extremities on repetitive movement.",
            quickEvidenceSummary = "Level II RCTs validate that directional preference exercises (McKenzie centralization) provide rapid short-term improvements in pain index and functional scores compared to general pelvic alignment exercises.",
            verifiedYear = "2023/2024 Edition"
        ),
        TopicEvidenceReference(
            topicId = "joint_biomechanics",
            title = "Arthrokinematics, Osteokinematics & Joint Structure Analysis",
            category = "Biomechanical Kinesiology",
            referenceTextbooks = listOf(
                "Norkin & Levangie's Joint Structure and Function",
                "West Bengal University of Health Sciences Syllabus Standards"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_V,
            sourceAttribution = "Norkin & Levangie Chapter 1 (Joint Structure and Function), Chapter 2 (Kinesiology of Biomechanical Systems), WBUHS Core Anatomy Curriculum Requirements.",
            recommendedReading = "Norkin & Levangie: Memorize the 'Convex-Concave Rule' for joint mobilization accessory motions across major peripheral joint capsule structures.",
            clinicalNotes = "When evaluating or mobilizing a joint, roll and slide occur in opposite directions if the moving joint surface is convex (e.g., glenohumeral joint), and in the same direction if concave.",
            quickEvidenceSummary = "Class V expert analysis forms the foundation of clinical orthopaedic manual therapy. Proper application of arthrokinematic rules is mandatory to avoid micro-capsular structural trauma during manual joint gliding.",
            verifiedYear = "2025/2026 Edition"
        ),
        TopicEvidenceReference(
            topicId = "adhesive_capsulitis",
            title = "Adhesive Capsulitis (Frozen Shoulder) Stages and Interventions",
            category = "Orthopedics & Manual Therapy",
            referenceTextbooks = listOf(
                "Magee's Orthopedic Physical Assessment",
                "Hoppenfeld's Spine and Extremities",
                "Kisner & Colby's Therapeutic Exercise"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_I,
            sourceAttribution = "Cochrane Database of Systematic Reviews: Joint Mobilization Interventions for Frozen Shoulder (2021), Magee Chapter 5, Kisner Chapter 17.",
            recommendedReading = "Magee: Learn to distinguish early-stage localized subacromial impingement patterns from true capsular limitations (restricted ER > Abduction > IR).",
            clinicalNotes = "Intense painful manipulation under anesthesia is progressive trauma. Utilize high-grade pain-free accessory glides and patient-tolerated stretching in early freezing phases.",
            quickEvidenceSummary = "Current clinical evidence supports low-intensity joint mobilizations combined with home active range exercises over vigorous high-velocity thrust actions during active inflammatory freezing stages.",
            verifiedYear = "2021/2023 Edition"
        ),
        TopicEvidenceReference(
            topicId = "carpal_tunnel_syndrome",
            title = "Carpal Tunnel Syndrome (CTS) Assessment & Median Nerve Glides",
            category = "Upper Extremity & Hand Therapy",
            referenceTextbooks = listOf(
                "Magee's Orthopedic Physical Assessment",
                "Hoppenfeld's Spine and Extremities",
                "Kisner & Colby's Therapeutic Exercise"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_II,
            sourceAttribution = "Academy of Orthopaedic Physical Therapy (AOPT) CPG: Carpal Tunnel Syndrome (2019), Magee Chapter 7, Kisner & Colby Chapter 19.",
            recommendedReading = "Magee: Master Phalen's, Reverse Phalen's, and Tinel's sign specificity matrices on Page 415. Kisner: Review the 6-stage clinical tendon and median nerve gliding sequence.",
            clinicalNotes = "Do not prescribe high-load wrist flexion exercises; instruct the patient to wear a neutral wrist orthosis (wrist splint at 0°) at night to maximize carpal tunnel spacing.",
            quickEvidenceSummary = "Level II research suggests night splinting and neuromyofascial mobilization (nerve glides) reduce localized tunnel pressures, alleviating morning numbness index compared to zero active physical intervention.",
            verifiedYear = "2019/2022 Edition"
        ),
        TopicEvidenceReference(
            topicId = "copd_pulmonary_rehabilitation",
            title = "Pulmonary Rehabilitation & Airway Clearance in COPD",
            category = "Cardiopulmonary Physiotherapy",
            referenceTextbooks = listOf(
                "O'Sullivan's Physical Rehabilitation",
                "West Bengal University of Health Sciences Syllabus Standards"
            ),
            evidenceLevel = EvidenceLevel.LEVEL_I,
            sourceAttribution = "GOLD (Global Initiative for Chronic Obstructive Lung Disease) Guidelines 2024, O'Sullivan Chapter 24 (Cardiopulmonary Rehabilitation), WBUHS Syllabus Category 4.",
            recommendedReading = "O'Sullivan: Focus on 'Airway Clearance Techniques' including the Active Cycle of Breathing Techniques (ACBT) and autogenic drainage. WBUHS: Study physiology of chest percussion.",
            clinicalNotes = "Keep dynamic pulse oximetry active during exercise reconditioning. Ensure oxygen supply titration keeps patient saturations strictly above 88% at all times.",
            quickEvidenceSummary = "Strong Level I evidence demonstrates that comprehensive pulmonary rehabilitation reduces dyspnea, improves exercise capacity, and decreases hospital readmission frequencies following acute COPD exacerbations.",
            verifiedYear = "2024/2025 Edition"
        )
    )

    @Volatile var libraryReferences = initialLibraryReferences

    private val neuroReferences = listOf(
        "O'Sullivan Physical Rehabilitation",
        "Susan O'Sullivan, Thomas Schmitz",
        "Bland's Stroke Therapy"
    )

    private val orthoReferences = listOf(
        "Magee Orthopedic Physical Assessment",
        "Dutton's Orthopaedic Examination, Evaluation, and Intervention",
        "Hoppenfeld Physical Examination of the Spine and Extremities"
    )

    private val exerciseTherapyReferences = listOf(
        "Kisner & Colby Therapeutic Exercise",
        "ACSM's Guidelines for Exercise Testing and Prescription",
        "Tidy's Physiotherapy"
    )

    val diagnoses: List<DetailedDiagnosis>
        get() = com.example.data.DataLoader.rawDiagnoses.map { data ->
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
                references = when {
                    data.id.contains("neu", ignoreCase = true) || data.diagnosisName.contains("Stroke") || data.diagnosisName.contains("Parkinson") || data.diagnosisName.contains("Sclerosis") || data.diagnosisName.contains("Spinal Cord") || data.diagnosisName.contains("Guillain") || data.diagnosisName.contains("Brain Injury") || data.diagnosisName.contains("Ataxia") || data.diagnosisName.contains("Palsy") || data.diagnosisName.contains("Neuropathy") || data.diagnosisName.contains("Polio") -> neuroReferences
                    data.id.contains("orth", ignoreCase = true) || data.diagnosisName.contains("Capsulitis") || data.diagnosisName.contains("Tear") || data.diagnosisName.contains("Osteoarthritis") || data.diagnosisName.contains("Herniation") || data.diagnosisName.contains("Syndrome") || data.diagnosisName.contains("Epicondylitis") || data.diagnosisName.contains("Fasciitis") || data.diagnosisName.contains("Sprain") || data.diagnosisName.contains("Scoliosis") || data.diagnosisName.contains("Arthroplasty") || data.diagnosisName.contains("Fracture") || data.diagnosisName.contains("Kyphosis") -> orthoReferences
                    data.diagnosisName.contains("COPD") || data.diagnosisName.contains("Asthma") || data.diagnosisName.contains("Fibrosis") || data.diagnosisName.contains("Pneumonia") || data.diagnosisName.contains("Infarction") || data.diagnosisName.contains("CABG") || data.diagnosisName.contains("Effusion") || data.diagnosisName.contains("Bronchiectasis") || data.diagnosisName.contains("Failure") || data.diagnosisName.contains("Arterial") -> listOf(
                        "Cardiopulmonary Physical Therapy by Irwin and Tecklin",
                        "Essentials of Cardiopulmonary Physical Therapy by Ellen Hillegass",
                        "Cash's Textbook of Chest, Heart and Vascular Disorders for Physiotherapists"
                    )
                    data.diagnosisName.contains("Tecklin") || data.diagnosisName.contains("Spina") || data.diagnosisName.contains("Torticollis") || data.diagnosisName.contains("Arthritis") || data.diagnosisName.contains("Autism") || data.diagnosisName.contains("Coordination") || data.diagnosisName.contains("Duchenne") || data.diagnosisName.contains("Down") || data.diagnosisName.contains("Diplegia") -> listOf(
                        "Tecklin's Pediatric Physical Therapy",
                        "Campbell's Physical Therapy for Children",
                        "Meeting the Physical Therapy Needs of Children by Susan K. Effgen"
                    )
                    else -> exerciseTherapyReferences
                },
                neurologicalAssessment = data.neurologicalAssessment.map { 
                    "Tone: ${it.tone} - Reflexes: ${it.reflexes} - Sensation: ${it.sensation} - Coordination: ${it.coordination} - Cranial: ${it.cranial} - Balance: ${it.balance} - Findings: ${it.findings}" 
                },
                functionalAssessment = data.functionalAssessment,
                clinicalReasoning = data.clinicalReasoning
            )
        }

    private val anatomyReferences = listOf(
        "B.D. Chaurasia Human Anatomy",
        "Gray’s Anatomy for Students",
        "Moore Clinically Oriented Anatomy"
    )

    val initialAnatomies = listOf(
        DetailedAnatomy(
            id = "a_0",
            name = "Deltoid",
            definition = "A large triangular muscle covering the shoulder joint.",
            origin = "Lateral third of clavicle, acromion, spine of scapula",
            insertion = "Deltoid tuberosity of humerus",
            nerveSupply = "Axillary nerve (C5, C6)",
            bloodSupply = "Posterior circumflex humeral artery",
            action = "Abduction, flexion, extension of arm",
            clinicalImportance = "Frequent site of IM injections, vulnerable to axillary nerve injury",
            commonConditions = listOf("Shoulder dislocation", "Axillary neuropathy"),
            vivaQuestions = listOf("Which nerve supplies?", "Where does it insert?", "Action of anterior fibers?"),
            references = anatomyReferences,
            searchTerms = listOf("deltoid", "shoulder")
        ),
        DetailedAnatomy(
            id = "a_1",
            name = "Biceps Brachii",
            definition = "Two-headed muscle of the anterior arm.",
            origin = "Short head: coracoid. Long head: supraglenoid tubercle",
            insertion = "Radial tuberosity",
            nerveSupply = "Musculocutaneous nerve",
            bloodSupply = "Brachial artery",
            action = "Supinates forearm, flexes elbow",
            clinicalImportance = "Important for pulling and lifting",
            commonConditions = listOf("Biceps tendinitis", "Rupture of long head"),
            vivaQuestions = listOf("Nerve supply?", "Origins of heads?", "Reflex spinal segments?"),
            references = anatomyReferences,
            searchTerms = listOf("biceps brachii", "biceps")
        ),
        DetailedAnatomy(
            id = "a_2",
            name = "Triceps Brachii",
            definition = "Three-headed muscle of posterior arm.",
            origin = "Long: infraglenoid. Lateral/Medial: posterior humerus",
            insertion = "Olecranon of ulna",
            nerveSupply = "Radial nerve",
            bloodSupply = "Profunda brachii artery",
            action = "Extension of elbow",
            clinicalImportance = "Site of triceps reflex",
            commonConditions = listOf("Radial nerve palsy"),
            vivaQuestions = listOf("Nerve?", "Attachment?", "Action?"),
            references = anatomyReferences,
            searchTerms = listOf("triceps")
        ),
        DetailedAnatomy(
            id = "a_3",
            name = "Brachialis",
            definition = "A strong flexor of the elbow.",
            origin = "Distal anterior humerus",
            insertion = "Coronoid process of ulna",
            nerveSupply = "Musculocutaneous nerve",
            bloodSupply = "Brachial artery",
            action = "Elbow flexion",
            clinicalImportance = "Workhorse of elbow flexion",
            commonConditions = listOf("Brachialis strain"),
            vivaQuestions = listOf("Nerve?", "Origin?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("brachialis")
        ),
        DetailedAnatomy(
            id = "a_4",
            name = "Coracobrachialis",
            definition = "Small muscle of the anterior arm.",
            origin = "Coracoid process",
            insertion = "Middle medial humerus",
            nerveSupply = "Musculocutaneous nerve",
            bloodSupply = "Brachial artery",
            action = "Flexion and adduction of arm",
            clinicalImportance = "Landmark for axillary vessels",
            commonConditions = listOf("Musculocutaneous nerve injury"),
            vivaQuestions = listOf("Nerve?", "Origin?", "Action?"),
            references = anatomyReferences,
            searchTerms = listOf("coracobrachialis")
        ),
        DetailedAnatomy(
            id = "a_5",
            name = "Supraspinatus",
            definition = "Rotator cuff muscle above scapular spine.",
            origin = "Supraspinous fossa",
            insertion = "Greater tubercle of humerus",
            nerveSupply = "Suprascapular nerve",
            bloodSupply = "Suprascapular artery",
            action = "Initiates shoulder abduction (first 15 deg)",
            clinicalImportance = "Most frequently torn rotator cuff muscle",
            commonConditions = listOf("Impingement syndrome", "Rotator cuff tear"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("supraspinatus", "rotator cuff")
        ),
        DetailedAnatomy(
            id = "a_6",
            name = "Infraspinatus",
            definition = "Rotator cuff muscle below scapular spine.",
            origin = "Infraspinous fossa",
            insertion = "Greater tubercle of humerus",
            nerveSupply = "Suprascapular nerve",
            bloodSupply = "Suprascapular artery",
            action = "Lateral rotation of arm",
            clinicalImportance = "Important for shoulder stability",
            commonConditions = listOf("Rotator cuff tear"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("infraspinatus", "rotator cuff")
        ),
        DetailedAnatomy(
            id = "a_7",
            name = "Teres Minor",
            definition = "Small rotator cuff muscle.",
            origin = "Lateral border of scapula",
            insertion = "Greater tubercle of humerus",
            nerveSupply = "Axillary nerve",
            bloodSupply = "Posterior circumflex humeral artery",
            action = "Lateral rotation of arm",
            clinicalImportance = "Can be injured in posterior shoulder dislocation",
            commonConditions = listOf("Rotator cuff tear"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("teres minor", "rotator cuff")
        ),
        DetailedAnatomy(
            id = "a_8",
            name = "Teres Major",
            definition = "Medial rotator of arm.",
            origin = "Lower posterior scapula",
            insertion = "Lesser tubercle crest of humerus",
            nerveSupply = "Lower subscapular nerve",
            bloodSupply = "Circumflex scapular artery",
            action = "Adduction, medial rotation",
            clinicalImportance = "Forms lower border of axilla",
            commonConditions = listOf("Overuse injury"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("teres major")
        ),
        DetailedAnatomy(
            id = "a_9",
            name = "Subscapularis",
            definition = "Anterior rotator cuff muscle.",
            origin = "Subscapular fossa",
            insertion = "Lesser tubercle of humerus",
            nerveSupply = "Upper/Lower subscapular nerves",
            bloodSupply = "Subscapular artery",
            action = "Medial rotation of arm",
            clinicalImportance = "Prevents anterior dislocation",
            commonConditions = listOf("Rotator cuff tear"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("subscapularis", "rotator cuff")
        ),
        DetailedAnatomy(
            id = "a_10",
            name = "Rotator Cuff",
            definition = "Group of SITS muscles.",
            origin = "Various scapular fossae",
            insertion = "Humeral tubercles",
            nerveSupply = "Various",
            bloodSupply = "Various",
            action = "Stabilizes shoulder joint",
            clinicalImportance = "Often injured in overhead athletes",
            commonConditions = listOf("Shoulder impingement", "Tears"),
            vivaQuestions = listOf("Which muscles?", "Insertions?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("sits", "rotator cuff")
        ),
        DetailedAnatomy(
            id = "a_11",
            name = "Pectoralis Major",
            definition = "Large chest muscle.",
            origin = "Clavicle, sternum, ribs",
            insertion = "Greater tubercle crest",
            nerveSupply = "Medial/Lateral pectoral nerves",
            bloodSupply = "Pectoral branch of thoracoacromial",
            action = "Flexion, adduction, medial rotation",
            clinicalImportance = "Important for pushing",
            commonConditions = listOf("Pec tear"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertions?"),
            references = anatomyReferences,
            searchTerms = listOf("pectoralis major", "pec major", "pecs")
        ),
        DetailedAnatomy(
            id = "a_12",
            name = "Pectoralis Minor",
            definition = "Small chest muscle under Pec Major.",
            origin = "Ribs 3-5",
            insertion = "Coracoid process",
            nerveSupply = "Medial pectoral nerve",
            bloodSupply = "Pectoral branch of thoracoacromial",
            action = "Stabilizes scapula",
            clinicalImportance = "Can compress brachial plexus (Thoracic Outlet)",
            commonConditions = listOf("Thoracic outlet syndrome"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("pectoralis minor", "pec minor")
        ),
        DetailedAnatomy(
            id = "a_13",
            name = "Latissimus Dorsi",
            definition = "Broad muscle of the back.",
            origin = "T7-L5, sacrum, iliac crest",
            insertion = "Intertubercular groove of humerus",
            nerveSupply = "Thoracodorsal nerve",
            bloodSupply = "Thoracodorsal artery",
            action = "Extension, adduction, medial rotation",
            clinicalImportance = "Latissimus dorsi flap used in reconstructive surgery",
            commonConditions = listOf("Lat strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("latissimus dorsi", "lats")
        ),
        DetailedAnatomy(
            id = "a_14",
            name = "Serratus Anterior",
            definition = "Muscle on lateral thorax.",
            origin = "Ribs 1-8",
            insertion = "Medial border of scapula",
            nerveSupply = "Long thoracic nerve",
            bloodSupply = "Lateral thoracic artery",
            action = "Protracts and upwardly rotates scapula",
            clinicalImportance = "Weakness causes winged scapula",
            commonConditions = listOf("Winged Scapula"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("serratus anterior")
        ),
        DetailedAnatomy(
            id = "a_15",
            name = "Trapezius",
            definition = "Large superficial back muscle.",
            origin = "Occipital bone, ligamentum nuchae, T1-T12",
            insertion = "Clavicle, acromion, spine of scapula",
            nerveSupply = "Spinal accessory nerve (CN XI)",
            bloodSupply = "Transverse cervical artery",
            action = "Elevates, retracts, depresses scapula",
            clinicalImportance = "Often involved in tension headaches",
            commonConditions = listOf("Myofascial pain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertions?"),
            references = anatomyReferences,
            searchTerms = listOf("trapezius", "traps")
        ),
        DetailedAnatomy(
            id = "a_16",
            name = "Rhomboids",
            definition = "Muscles retracting the scapula.",
            origin = "C7-T5 spinous processes",
            insertion = "Medial border of scapula",
            nerveSupply = "Dorsal scapular nerve",
            bloodSupply = "Dorsal scapular artery",
            action = "Retracts scapula",
            clinicalImportance = "Poor posture causes strain",
            commonConditions = listOf("Postural strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("rhomboids", "rhomboid major", "rhomboid minor")
        ),
        DetailedAnatomy(
            id = "a_17",
            name = "Brachial Plexus",
            definition = "Network of nerves in the lower neck/axilla.",
            origin = "Ventral rami C5-T1",
            insertion = "Terminal branches in arm",
            nerveSupply = "Contributes to all upper limb supply",
            bloodSupply = "N/A",
            action = "Motor/Sensory to upper limb",
            clinicalImportance = "Injuries cause Erb's or Klumpke's palsy",
            commonConditions = listOf("Erb's Palsy", "Klumpke's Palsy"),
            vivaQuestions = listOf("Roots?", "Cords?", "Branches?"),
            references = anatomyReferences,
            searchTerms = listOf("brachial plexus")
        ),
        DetailedAnatomy(
            id = "a_18",
            name = "Median Nerve",
            definition = "Major nerve of the anterior forearm/hand.",
            origin = "Medial and lateral cords of BP",
            insertion = "N/A",
            nerveSupply = "Supplies anterior forearm and thenar eminence",
            bloodSupply = "N/A",
            action = "Flexion of wrist/fingers, pronation",
            clinicalImportance = "Compressed in carpal tunnel",
            commonConditions = listOf("Carpal Tunnel Syndrome", "Ape Hand"),
            vivaQuestions = listOf("Roots?", "Motor supply?", "Sensory supply?"),
            references = anatomyReferences,
            searchTerms = listOf("median nerve", "mn")
        ),
        DetailedAnatomy(
            id = "a_19",
            name = "Ulnar Nerve",
            definition = "Major nerve of the hand.",
            origin = "Medial cord of BP",
            insertion = "N/A",
            nerveSupply = "Supplies intrinsic hand muscles",
            bloodSupply = "N/A",
            action = "Fine motor movements of hand",
            clinicalImportance = "Vulnerable behind medial epicondyle",
            commonConditions = listOf("Cubital Tunnel Syndrome", "Claw Hand"),
            vivaQuestions = listOf("Roots?", "Motor supply?", "Sensory supply?"),
            references = anatomyReferences,
            searchTerms = listOf("ulnar nerve", "un")
        ),
        DetailedAnatomy(
            id = "a_20",
            name = "Radial Nerve",
            definition = "Major nerve of the posterior upper limb.",
            origin = "Posterior cord of BP",
            insertion = "N/A",
            nerveSupply = "Supplies triceps, brachioradialis, wrist extensors",
            bloodSupply = "N/A",
            action = "Extension of elbow, wrist, fingers",
            clinicalImportance = "Vulnerable in spiral groove of humerus",
            commonConditions = listOf("Wrist Drop", "Saturday Night Palsy"),
            vivaQuestions = listOf("Roots?", "Motor supply?", "Sensory supply?"),
            references = anatomyReferences,
            searchTerms = listOf("radial nerve", "rn")
        ),
        DetailedAnatomy(
            id = "a_21",
            name = "Musculocutaneous Nerve",
            definition = "Nerve of anterior arm.",
            origin = "Lateral cord of BP",
            insertion = "N/A",
            nerveSupply = "Supplies biceps, brachialis, coracobrachialis",
            bloodSupply = "N/A",
            action = "Elbow flexion, forearm supination",
            clinicalImportance = "Injury causes weak elbow flexion",
            commonConditions = listOf("Nerve injury"),
            vivaQuestions = listOf("Roots?", "Motor supply?", "Sensory supply?"),
            references = anatomyReferences,
            searchTerms = listOf("musculocutaneous nerve", "mcn")
        ),
        DetailedAnatomy(
            id = "a_22",
            name = "Gluteus Maximus",
            definition = "Largest muscle of buttocks.",
            origin = "Ilium, sacrum, coccyx",
            insertion = "Gluteal tuberosity, iliotibial tract",
            nerveSupply = "Inferior gluteal nerve",
            bloodSupply = "Inferior gluteal artery",
            action = "Extends and laterally rotates thigh",
            clinicalImportance = "Primary muscle for standing up and climbing stairs",
            commonConditions = listOf("Gluteal strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("gluteus maximus", "glutes")
        ),
        DetailedAnatomy(
            id = "a_23",
            name = "Gluteus Medius",
            definition = "Hip abductor muscle.",
            origin = "Outer surface of ilium",
            insertion = "Greater trochanter",
            nerveSupply = "Superior gluteal nerve",
            bloodSupply = "Superior gluteal artery",
            action = "Abducts and medially rotates thigh",
            clinicalImportance = "Weakness leads to Trendelenburg gait",
            commonConditions = listOf("Trendelenburg sign"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("gluteus medius", "glutes")
        ),
        DetailedAnatomy(
            id = "a_24",
            name = "Gluteus Minimus",
            definition = "Deep hip abductor muscle.",
            origin = "Outer surface of ilium",
            insertion = "Greater trochanter",
            nerveSupply = "Superior gluteal nerve",
            bloodSupply = "Superior gluteal artery",
            action = "Abducts and medially rotates thigh",
            clinicalImportance = "Synergist to gluteus medius",
            commonConditions = listOf("Trochanteric bursitis"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("gluteus minimus")
        ),
        DetailedAnatomy(
            id = "a_25",
            name = "Tensor Fascia Lata",
            definition = "Muscle of lateral thigh.",
            origin = "ASIS",
            insertion = "Iliotibial tract",
            nerveSupply = "Superior gluteal nerve",
            bloodSupply = "Superior gluteal artery",
            action = "Flexes, abducts, medially rotates thigh",
            clinicalImportance = "Tightness causes IT band syndrome",
            commonConditions = listOf("ITB Syndrome"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("tfl", "tensor fascia lata")
        ),
        DetailedAnatomy(
            id = "a_26",
            name = "Iliopsoas",
            definition = "Primary hip flexor.",
            origin = "Iliac fossa, T12-L5 vertebrae",
            insertion = "Lesser trochanter",
            nerveSupply = "Femoral nerve, L1-L3 ventral rami",
            bloodSupply = "Iliolumbar artery",
            action = "Flexes thigh",
            clinicalImportance = "Often tight in prolonged sitting",
            commonConditions = listOf("Psoas syndrome"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("iliopsoas", "psoas")
        ),
        DetailedAnatomy(
            id = "a_27",
            name = "Sartorius",
            definition = "Longest muscle in body.",
            origin = "ASIS",
            insertion = "Medial surface of tibia (Pes anserinus)",
            nerveSupply = "Femoral nerve",
            bloodSupply = "Femoral artery",
            action = "Flexes, abducts, laterally rotates thigh",
            clinicalImportance = "Forms lateral border of femoral triangle",
            commonConditions = listOf("Pes anserine bursitis"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("sartorius")
        ),
        DetailedAnatomy(
            id = "a_28",
            name = "Rectus Femoris",
            definition = "Two-joint muscle of quadriceps.",
            origin = "AIIS, superior acetabular rim",
            insertion = "Tibial tuberosity",
            nerveSupply = "Femoral nerve",
            bloodSupply = "Lateral circumflex femoral artery",
            action = "Extends knee, flexes hip",
            clinicalImportance = "Prone to strain in kicking sports",
            commonConditions = listOf("Quad strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("rectus femoris", "quads")
        ),
        DetailedAnatomy(
            id = "a_29",
            name = "Vastus Medialis",
            definition = "Medial head of quadriceps.",
            origin = "Intertrochanteric line, linea aspera",
            insertion = "Tibial tuberosity",
            nerveSupply = "Femoral nerve",
            bloodSupply = "Femoral artery",
            action = "Extends knee",
            clinicalImportance = "VMO crucial for patellar tracking",
            commonConditions = listOf("Patellofemoral pain syndrome"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("vastus medialis", "vmo", "quads")
        ),
        DetailedAnatomy(
            id = "a_30",
            name = "Vastus Lateralis",
            definition = "Lateral head of quadriceps.",
            origin = "Greater trochanter, linea aspera",
            insertion = "Tibial tuberosity",
            nerveSupply = "Femoral nerve",
            bloodSupply = "Lateral circumflex femoral artery",
            action = "Extends knee",
            clinicalImportance = "Largest quad muscle, common injection site",
            commonConditions = listOf("Quad strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("vastus lateralis", "quads")
        ),
        DetailedAnatomy(
            id = "a_31",
            name = "Vastus Intermedius",
            definition = "Deep head of quadriceps.",
            origin = "Anterior and lateral femur",
            insertion = "Tibial tuberosity",
            nerveSupply = "Femoral nerve",
            bloodSupply = "Lateral circumflex femoral artery",
            action = "Extends knee",
            clinicalImportance = "Deepest quad muscle",
            commonConditions = listOf("Quad strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("vastus intermedius", "quads")
        ),
        DetailedAnatomy(
            id = "a_32",
            name = "Gracilis",
            definition = "Medial thigh adductor.",
            origin = "Pubis",
            insertion = "Medial surface of tibia (Pes anserinus)",
            nerveSupply = "Obturator nerve",
            bloodSupply = "Obturator artery",
            action = "Adducts thigh, flexes knee",
            clinicalImportance = "Often used in flap surgery",
            commonConditions = listOf("Groin pull"),
            vivaQuestions = listOf("Action?", "Nerve?", "Insertion?"),
            references = anatomyReferences,
            searchTerms = listOf("gracilis", "adductors")
        ),
        DetailedAnatomy(
            id = "a_33",
            name = "Hamstrings",
            definition = "Posterior thigh muscles.",
            origin = "Ischial tuberosity (mostly)",
            insertion = "Tibia and fibula",
            nerveSupply = "Sciatic nerve",
            bloodSupply = "Profunda femoris artery",
            action = "Flexes knee, extends hip",
            clinicalImportance = "Frequently strained during sprinting",
            commonConditions = listOf("Hamstring strain"),
            vivaQuestions = listOf("Which muscles?", "Nerve?", "Action?"),
            references = anatomyReferences,
            searchTerms = listOf("hamstrings", "biceps femoris", "semitendinosus", "semimembranosus")
        ),
        DetailedAnatomy(
            id = "a_34",
            name = "Tibialis Anterior",
            definition = "Anterior leg muscle.",
            origin = "Lateral condyle and lateral tibia",
            insertion = "Medial cuneiform, base of 1st metatarsal",
            nerveSupply = "Deep peroneal nerve",
            bloodSupply = "Anterior tibial artery",
            action = "Dorsiflexes and inverts foot",
            clinicalImportance = "Weakness causes foot drop",
            commonConditions = listOf("Foot drop", "Shin splints"),
            vivaQuestions = listOf("Action?", "Nerve?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("tibialis anterior", "ta")
        ),
        DetailedAnatomy(
            id = "a_35",
            name = "Gastrocnemius",
            definition = "Superficial calf muscle.",
            origin = "Lateral & Medial femoral condyles",
            insertion = "Calcaneus via Achilles tendon",
            nerveSupply = "Tibial nerve",
            bloodSupply = "Sural arteries",
            action = "Plantarflexes foot, flexes knee",
            clinicalImportance = "Prone to tears (tennis leg)",
            commonConditions = listOf("Achilles tendinopathy", "Calf strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("gastrocnemius", "calf")
        ),
        DetailedAnatomy(
            id = "a_36",
            name = "Soleus",
            definition = "Deep calf muscle.",
            origin = "Posterior tibia and fibula",
            insertion = "Calcaneus via Achilles tendon",
            nerveSupply = "Tibial nerve",
            bloodSupply = "Posterior tibial artery",
            action = "Plantarflexes foot",
            clinicalImportance = "Primary postural muscle, calf pump",
            commonConditions = listOf("Achilles tendinopathy"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("soleus", "calf")
        ),
        DetailedAnatomy(
            id = "a_37",
            name = "Sciatic Nerve",
            definition = "Largest nerve in body.",
            origin = "L4-S3",
            insertion = "Splits into Tibial & Common Peroneal",
            nerveSupply = "Supplies posterior thigh/leg/foot",
            bloodSupply = "N/A",
            action = "Motor and sensory of lower limb",
            clinicalImportance = "Compressed in piriformis syndrome or disc prolapse",
            commonConditions = listOf("Sciatica"),
            vivaQuestions = listOf("Roots?", "Branches?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("sciatic nerve")
        ),
        DetailedAnatomy(
            id = "a_38",
            name = "Femoral Nerve",
            definition = "Major nerve of anterior thigh.",
            origin = "L2-L4",
            insertion = "N/A",
            nerveSupply = "Supplies anterior thigh muscles",
            bloodSupply = "N/A",
            action = "Knee extension, hip flexion",
            clinicalImportance = "Can be damaged in pelvic fractures",
            commonConditions = listOf("Femoral neuropathy"),
            vivaQuestions = listOf("Roots?", "Supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("femoral nerve")
        ),
        DetailedAnatomy(
            id = "a_39",
            name = "Obturator Nerve",
            definition = "Nerve of medial thigh.",
            origin = "L2-L4",
            insertion = "N/A",
            nerveSupply = "Supplies adductor compartment",
            bloodSupply = "N/A",
            action = "Hip adduction",
            clinicalImportance = "Can be compressed in pregnancy",
            commonConditions = listOf("Obturator neuropathy"),
            vivaQuestions = listOf("Roots?", "Supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("obturator nerve")
        ),
        DetailedAnatomy(
            id = "a_40",
            name = "Tibial Nerve",
            definition = "Branch of sciatic nerve.",
            origin = "L4-S3",
            insertion = "N/A",
            nerveSupply = "Supplies posterior leg and sole of foot",
            bloodSupply = "N/A",
            action = "Plantarflexion, toe flexion",
            clinicalImportance = "Compressed in Tarsal Tunnel",
            commonConditions = listOf("Tarsal Tunnel Syndrome"),
            vivaQuestions = listOf("Root?", "Supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("tibial nerve")
        ),
        DetailedAnatomy(
            id = "a_41",
            name = "Common Peroneal Nerve",
            definition = "Branch of sciatic nerve.",
            origin = "L4-S2",
            insertion = "N/A",
            nerveSupply = "Supplies anterior/lateral leg",
            bloodSupply = "N/A",
            action = "Dorsiflexion, eversion",
            clinicalImportance = "Vulnerable at fibular head; foot drop",
            commonConditions = listOf("Foot drop", "Peroneal neuropathy"),
            vivaQuestions = listOf("Root?", "Supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("common peroneal nerve", "fibular nerve", "cpn")
        ),
        DetailedAnatomy(
            id = "a_42",
            name = "Vertebral Column",
            definition = "Bony spine protecting spinal cord.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Spinal nerves",
            bloodSupply = "Segmental spinal arteries",
            action = "Support, movement, protection",
            clinicalImportance = "Site of disc herniations",
            commonConditions = listOf("Scoliosis", "Disc Herniation"),
            vivaQuestions = listOf("Sections?", "Curvatures?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("vertebral column", "spine", "backbone")
        ),
        DetailedAnatomy(
            id = "a_43",
            name = "Intervertebral Disc",
            definition = "Cartilaginous joint between vertebrae.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Sinuvertebral nerve (outer annulus)",
            bloodSupply = "Segmental arteries (outer portion)",
            action = "Shock absorption, mobility",
            clinicalImportance = "Herniations commonly impinge nerve roots",
            commonConditions = listOf("IVDD", "Disc Prolapse"),
            vivaQuestions = listOf("Structure?", "Function?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("intervertebral disc", "ivd", "spinal disc")
        ),
        DetailedAnatomy(
            id = "a_44",
            name = "Lumbar Plexus",
            definition = "Nerve network for lower limb.",
            origin = "L1-L4",
            insertion = "N/A",
            nerveSupply = "Supplies anterior/medial thigh",
            bloodSupply = "N/A",
            action = "Motor/Sensory lower limb",
            clinicalImportance = "Forms within psoas muscle",
            commonConditions = listOf("Lumbar radiculopathy"),
            vivaQuestions = listOf("Roots?", "Major branches?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("lumbar plexus")
        ),
        DetailedAnatomy(
            id = "a_45",
            name = "Sacral Plexus",
            definition = "Nerve network for pelvis and lower limb.",
            origin = "L4-S4",
            insertion = "N/A",
            nerveSupply = "Supplies posterior thigh, leg, foot",
            bloodSupply = "N/A",
            action = "Motor/Sensory lower limb",
            clinicalImportance = "Gives rise to sciatic nerve",
            commonConditions = listOf("Sacral plexopathy"),
            vivaQuestions = listOf("Roots?", "Major branches?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("sacral plexus")
        ),
        DetailedAnatomy(
            id = "a_46",
            name = "Erector Spinae",
            definition = "Group of deep back muscles.",
            origin = "Sacrum, iliac crest, spinous processes",
            insertion = "Ribs and vertebrae above",
            nerveSupply = "Dorsal rami of spinal nerves",
            bloodSupply = "Posterior intercostal arteries",
            action = "Extension of spine",
            clinicalImportance = "Primary extensor of back",
            commonConditions = listOf("Low back strain"),
            vivaQuestions = listOf("Muscles involved?", "Action?", "Nerve?"),
            references = anatomyReferences,
            searchTerms = listOf("erector spinae", "paravertebrals")
        ),
        DetailedAnatomy(
            id = "a_47",
            name = "Multifidus",
            definition = "Deepest back muscle.",
            origin = "Sacrum, transverse processes",
            insertion = "Spinous processes 2-4 levels above",
            nerveSupply = "Dorsal rami of spinal nerves",
            bloodSupply = "Segmental arteries",
            action = "Stabilizes spine",
            clinicalImportance = "Atrophies rapidly in low back pain",
            commonConditions = listOf("Low back pain"),
            vivaQuestions = listOf("Location?", "Action?", "Clinical importance?"),
            references = anatomyReferences,
            searchTerms = listOf("multifidus")
        ),
        DetailedAnatomy(
            id = "a_48",
            name = "Quadratus Lumborum",
            definition = "Deep abdominal/lumbar muscle.",
            origin = "Iliac crest, iliolumbar ligament",
            insertion = "12th rib, L1-L4 transverse processes",
            nerveSupply = "T12-L4 anterior rami",
            bloodSupply = "Lumbar arteries",
            action = "Lateral flexion of spine, pelvic hiking",
            clinicalImportance = "Common source of lower back pain",
            commonConditions = listOf("QL strain", "Lower back pain"),
            vivaQuestions = listOf("Origin?", "Action?", "Clinical significance?"),
            references = anatomyReferences,
            searchTerms = listOf("quadratus lumborum", "ql")
        ),
        DetailedAnatomy(
            id = "a_49",
            name = "Rectus Abdominis",
            definition = "Anterior abdominal muscle.",
            origin = "Pubic crest and symphysis",
            insertion = "Xiphoid process, costal cartilages 5-7",
            nerveSupply = "Thoracoabdominal nerves (T7-T11)",
            bloodSupply = "Superior and inferior epigastric arteries",
            action = "Flexion of trunk",
            clinicalImportance = "Divided by tendinous intersections (six-pack)",
            commonConditions = listOf("Diastasis recti"),
            vivaQuestions = listOf("Origin?", "Action?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("rectus abdominis", "abs")
        ),
        DetailedAnatomy(
            id = "a_50",
            name = "MCA",
            definition = "Middle Cerebral Artery.",
            origin = "Internal carotid artery",
            insertion = "N/A",
            nerveSupply = "Supplies lateral surface of cerebrum",
            bloodSupply = "N/A",
            action = "Motor/sensory to face and arm, language (dominant side)",
            clinicalImportance = "Most common site of ischemic stroke",
            commonConditions = listOf("Stroke", "Aphasia", "Hemiparesis"),
            vivaQuestions = listOf("Origin?", "Supply area?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("mca", "middle cerebral artery")
        ),
        DetailedAnatomy(
            id = "a_51",
            name = "ACA",
            definition = "Anterior Cerebral Artery.",
            origin = "Internal carotid artery",
            insertion = "N/A",
            nerveSupply = "Supplies medial aspect of frontal and parietal lobes",
            bloodSupply = "N/A",
            action = "Motor/Sensory to leg and foot",
            clinicalImportance = "Infarct causes contralateral leg weakness",
            commonConditions = listOf("Stroke"),
            vivaQuestions = listOf("Origin?", "Supply area?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("aca", "anterior cerebral artery")
        ),
        DetailedAnatomy(
            id = "a_52",
            name = "PCA",
            definition = "Posterior Cerebral Artery.",
            origin = "Basilar artery",
            insertion = "N/A",
            nerveSupply = "Supplies occipital lobe, inferior temporal lobe",
            bloodSupply = "N/A",
            action = "Vision, memory",
            clinicalImportance = "Infarct causes hemianopia",
            commonConditions = listOf("Stroke"),
            vivaQuestions = listOf("Origin?", "Supply area?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("pca", "posterior cerebral artery")
        ),
        DetailedAnatomy(
            id = "a_53",
            name = "Internal Capsule",
            definition = "White matter tract in brain.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Carries major ascending/descending tracts",
            bloodSupply = "Lenticulostriate arteries (mainly MCA)",
            action = "Carries major ascending/descending tracts",
            clinicalImportance = "Small infarcts here cause severe motor deficits",
            commonConditions = listOf("Lacunar Stroke"),
            vivaQuestions = listOf("What tracts pass?", "Blood supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("internal capsule")
        ),
        DetailedAnatomy(
            id = "a_54",
            name = "Basal Ganglia",
            definition = "Group of deep brain nuclei.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Motor control, learning, behavior",
            bloodSupply = "Lenticulostriate and other deep arteries",
            action = "Motor control, learning, behavior",
            clinicalImportance = "Dysfunction causes movement disorders",
            commonConditions = listOf("Parkinson's Disease", "Huntington's"),
            vivaQuestions = listOf("Nuclei involved?", "Function?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("basal ganglia", "corpus striatum")
        ),
        DetailedAnatomy(
            id = "a_55",
            name = "Cerebellum",
            definition = "Hindbrain structure.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Coordination, precision, balance",
            bloodSupply = "SCA, AICA, PICA",
            action = "Coordination, precision, balance",
            clinicalImportance = "Dysfunction causes ataxia",
            commonConditions = listOf("Ataxia", "Cerebellar hypoplasia"),
            vivaQuestions = listOf("Function?", "Vascular supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("cerebellum", "little brain")
        ),
        DetailedAnatomy(
            id = "a_56",
            name = "Brainstem",
            definition = "Connects brain to spinal cord.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Cranial nerves 3-12 originate here",
            bloodSupply = "Vertebrobasilar system",
            action = "Vital functions, arousal, reflexes",
            clinicalImportance = "Lesions can be life-threatening",
            commonConditions = listOf("Brainstem stroke", "Locked-in syndrome"),
            vivaQuestions = listOf("Parts?", "Function?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("brainstem", "pons", "medulla", "midbrain")
        ),
        DetailedAnatomy(
            id = "a_57",
            name = "Corticospinal Tract",
            definition = "Primary descending motor pathway.",
            origin = "Motor cortex",
            insertion = "Anterior horn of spinal cord",
            nerveSupply = "Voluntary motor control",
            bloodSupply = "Various (MCA, ACA, paramedian branches)",
            action = "Voluntary motor control",
            clinicalImportance = "Decussates at medulla (pyramids)",
            commonConditions = listOf("UMN Lesion", "Stroke", "SCI"),
            vivaQuestions = listOf("Origin?", "Decussation?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("corticospinal tract", "pyramidal tract")
        ),
        DetailedAnatomy(
            id = "a_58",
            name = "Thalamus",
            definition = "Relay station of the brain.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Sensory and motor relay to cortex",
            bloodSupply = "PCA branches",
            action = "Sensory and motor relay to cortex",
            clinicalImportance = "Thalamic pain syndrome (Dejerine-Roussy)",
            commonConditions = listOf("Thalamic Stroke", "Pain Syndrome"),
            vivaQuestions = listOf("Function?", "Pathology?", "Blood supply?"),
            references = anatomyReferences,
            searchTerms = listOf("thalamus")
        ),
        DetailedAnatomy(
            id = "a_59",
            name = "Corpus Callosum",
            definition = "Largest white matter commissure.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Connects left and right hemispheres",
            bloodSupply = "ACA (mostly)",
            action = "Connects left and right hemispheres",
            clinicalImportance = "Can be sectioned for severe epilepsy",
            commonConditions = listOf("Split-brain syndrome"),
            vivaQuestions = listOf("Function?", "Blood supply?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("corpus callosum", "commissure")
        ),
        DetailedAnatomy(
            id = "a_60",
            name = "Diaphragm",
            definition = "Primary muscle of respiration.",
            origin = "Xiphoid, lower 6 ribs, L1-L3",
            insertion = "Central tendon",
            nerveSupply = "Phrenic nerve (C3, C4, C5)",
            bloodSupply = "Musculophrenic, inferior phrenic arteries",
            action = "Increases thoracic volume during inspiration",
            clinicalImportance = "Key muscle for breathing, paralyzed in high cervical SCI",
            commonConditions = listOf("Diaphragmatic paralysis"),
            vivaQuestions = listOf("Action?", "Nerve?", "Origin?"),
            references = anatomyReferences,
            searchTerms = listOf("diaphragm")
        ),
        DetailedAnatomy(
            id = "a_61",
            name = "External Intercostals",
            definition = "Inspiratory muscles between ribs.",
            origin = "Inferior border of rib above",
            insertion = "Superior border of rib below",
            nerveSupply = "Intercostal nerves",
            bloodSupply = "Intercostal arteries",
            action = "Elevates ribs during inspiration",
            clinicalImportance = "Assist diaphragm in quiet and active inspiration",
            commonConditions = listOf("Intercostal strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("external intercostals")
        ),
        DetailedAnatomy(
            id = "a_62",
            name = "Internal Intercostals",
            definition = "Expiratory muscles between ribs.",
            origin = "Superior border of rib below",
            insertion = "Inferior border of rib above",
            nerveSupply = "Intercostal nerves",
            bloodSupply = "Intercostal arteries",
            action = "Depresses ribs during forced expiration",
            clinicalImportance = "Active during exercise or coughing",
            commonConditions = listOf("Intercostal strain"),
            vivaQuestions = listOf("Action?", "Nerve?", "Function?"),
            references = anatomyReferences,
            searchTerms = listOf("internal intercostals")
        ),
        DetailedAnatomy(
            id = "a_63",
            name = "Heart Anatomy",
            definition = "Muscular pump.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Autonomic nervous system (Vagus, Sympathetic)",
            bloodSupply = "Coronary arteries",
            action = "Pumps blood",
            clinicalImportance = "Four chambers, valves prevent backflow",
            commonConditions = listOf("Myocardial Infarction", "Heart Failure"),
            vivaQuestions = listOf("Chambers?", "Valves?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("heart", "cardiac")
        ),
        DetailedAnatomy(
            id = "a_64",
            name = "Coronary Arteries",
            definition = "Blood vessels of the heart.",
            origin = "Aortic root",
            insertion = "N/A",
            nerveSupply = "Supply heart muscle",
            bloodSupply = "N/A",
            action = "Deliver oxygenated blood to myocardium",
            clinicalImportance = "LAD is most commonly occluded",
            commonConditions = listOf("Coronary Artery Disease", "MI"),
            vivaQuestions = listOf("Origin?", "Major branches?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("coronary arteries", "lad", "rca", "lcx")
        ),
        DetailedAnatomy(
            id = "a_65",
            name = "Aorta",
            definition = "Largest artery in the body.",
            origin = "Left ventricle",
            insertion = "Bifurcates at L4",
            nerveSupply = "Supplies systemic circulation",
            bloodSupply = "N/A",
            action = "Distributes oxygenated blood",
            clinicalImportance = "Susceptible to aneurysms",
            commonConditions = listOf("Aortic Aneurysm", "Dissection"),
            vivaQuestions = listOf("Origin?", "Function?", "Pathology?"),
            references = anatomyReferences,
            searchTerms = listOf("aorta", "ascending aorta", "descending aorta")
        ),
        DetailedAnatomy(
            id = "a_66",
            name = "Pulmonary Circulation",
            definition = "Blood flow between heart and lungs.",
            origin = "Right ventricle",
            insertion = "Left atrium",
            nerveSupply = "Oxygenates blood",
            bloodSupply = "Pulmonary arteries/veins",
            action = "Oxygenates blood",
            clinicalImportance = "Only arteries carrying deoxygenated blood",
            commonConditions = listOf("Pulmonary Embolism", "Pulmonary Hypertension"),
            vivaQuestions = listOf("Function?", "Pathology?", "Vessels involved?"),
            references = anatomyReferences,
            searchTerms = listOf("pulmonary circulation", "pulmonary artery", "lungs")
        ),
        DetailedAnatomy(
            id = "a_p1",
            name = "Rectus Femoris",
            definition = "A dual-joint muscle running straight down the anterior aspect of the thigh, acting as both a hip flexor and knee extensor.",
            origin = "Anterior Inferior Iliac Spine (AIIS) and groove superior to acetabulum",
            insertion = "Base of patellar base and via patellar ligament to tibial tuberosity",
            nerveSupply = "Femoral nerve (L2, L3, L4)",
            bloodSupply = "Lateral circumflex femoral artery",
            action = "Knee extension and hip flexion",
            clinicalImportance = "Highly susceptible to groin/thigh strains and contusion ('charley horse') in soccer and sprinting athletes.",
            commonConditions = listOf("Rectus femoris strain", "Avulsion fracture of the AIIS", "Quadriceps tendinopathy"),
            vivaQuestions = listOf("Explain active insufficiency in Rectus Femoris.", "Give its origin and how it differs from other quadriceps.", "Which spinal reflex level does it test?"),
            references = listOf("B.D. Chaurasia Vol 2", "Gray's Anatomy", "Kisner & Colby Therapeutic Exercise"),
            searchTerms = listOf("rectus femoris", "quadriceps", "thigh", "knee"),
            anatomyType = "Muscle",
            surfaceAnatomy = "Prominent central muscle belly on the anterior thigh, visible during resisted knee extension.",
            biomechanicalImportance = "Crucial bipennate design for transmitting high-velocity forces during kicking and jumping. Active insufficiency occurs during simultaneous hip flexion and knee extension.",
            palpationLandmarks = "Palpated 2-3 inches distal to the AIIS on the anterior thigh while tensioning the hip in flexion.",
            mnemonic = "Rectus runs straight (Direct) from the AIIS to the patella!",
            quickRevision = "Dual-joint anterior thigh muscle • Hip Flexor / Knee Extensor • Femoral Nerve (L2-L4) • AIIS origin • Quadriceps reflex marker."
        ),
        DetailedAnatomy(
            id = "a_p2",
            name = "Femur",
            definition = "The longest, heaviest, and strongest bone in the human body, serving as the skeletal core of the thigh.",
            origin = "N/A (Skeletal Framework)",
            insertion = "N/A (Skeletal Framework)",
            nerveSupply = "Periosteum innervated by branches of femoral, sciatic, and obturator nerves.",
            bloodSupply = "Profunda femoris artery (nutrient artery), medial/lateral circumflex femoral arteries.",
            action = "Acts as a primary structural lever for locomotion, weight-bearing, and muscular attachments.",
            clinicalImportance = "Site of high-yield orthopedic fractures, especially neck of femur fractures in osteoporotic geriatric patients, risking avascular necrosis.",
            commonConditions = listOf("Neck of femur fracture", "Trochanteric bursitis", "Femoral shaft fracture", "Slipped capital femoral epiphysis (SCFE)"),
            vivaQuestions = listOf("Describe the clinical significance of the femoral angle of inclination.", "Why is a femoral neck fracture an orthopedic emergency?", "What structures attach to the greater trochanter?"),
            references = listOf("Hoppenfeld Spine & Extremities", "Gray's Anatomy for Students", "Moore Clinically Oriented Anatomy"),
            searchTerms = listOf("femur", "bone", "thigh", "greater trochanter", "femoral neck"),
            anatomyType = "Bone",
            surfaceAnatomy = "Greater trochanter is palpable laterally; medial and lateral condyles are palpable at the knee joint.",
            biomechanicalImportance = "Angle of inclination (normally 125 degrees) and anteversion angle determine mechanical alignment of the lower quadrant; abnormal angles lead to coxa vara/valga or toeing in/out.",
            palpationLandmarks = "Greater trochanter (width of hand distal to iliac crest laterally) and lateral femoral epicondyle.",
            mnemonic = "FEMUR: Flexion/Extension Muscle Utility Rod",
            quickRevision = "Longest bone • Angle of inclination (125 degrees) determines hip mechanics • At risk of avascular necrosis on neck fracture."
        ),
        DetailedAnatomy(
            id = "a_p3",
            name = "Glenohumeral Joint",
            definition = "A highly mobile, multi-axial ball-and-socket synovial joint articulating the humeral head with the glenoid fossa of the scapula.",
            origin = "Glenoid labrum of scapula (socket articulation)",
            insertion = "Anatomical neck of the humerus (ball articulation)",
            nerveSupply = "Suprascapular, axillary, and lateral pectoral nerves (Hilton's Law).",
            bloodSupply = "Anterior and posterior circumflex humeral arteries, suprascapular artery.",
            action = "Flexion/extension, abduction/adduction, internal/external rotation, and circumduction.",
            clinicalImportance = "Most frequently dislocated joint in the human body, commonly in the anterior-inferior direction. Highly prone to adhesive capsulitis.",
            commonConditions = listOf("Shoulder dislocation", "Adhesive capsulitis (frozen shoulder)", "Glenoid labrum tears (SLAP lesions)", "Shoulder impingement syndrome"),
            vivaQuestions = listOf("Explain the concept of Hilton's Law using the shoulder joint.", "Distinguish between static and dynamic stabilizers of the shoulder.", "What is a Bankart lesion?"),
            references = listOf("Magee Orthopedic Physical Assessment", "Dutton's Orthopaedic Examination", "Gray’s Anatomy"),
            searchTerms = listOf("shoulder", "glenohumeral", "joint", "rotator cuff", "dislocation"),
            anatomyType = "Joint",
            surfaceAnatomy = "Slight depression immediately distal to the lateral acromion represents the joint line.",
            biomechanicalImportance = "Sacrifices bony stability for extreme range of motion (mobility-stability trade-off). Static stabilizers: glenoid labrum, glenohumeral ligaments. Dynamic stabilizers: SITS rotator cuff muscles.",
            palpationLandmarks = "Glenohumeral joint cleft (1cm inferior and lateral to the coracoid process, palpable during passive humerus rotation).",
            mnemonic = "Rotator Cuff holds it tight: SITS (Supraspinatus, Infraspinatus, Teres Minor, Subscapularis)!",
            quickRevision = "Ball-and-socket synovial joint • Mobility > Stability trade-off • SITS rotator cuff dynamic stability • Axillary/Suprascapular nerve supply."
        ),
        DetailedAnatomy(
            id = "a_p4",
            name = "Sciatic Nerve",
            definition = "The largest and longest single nerve trunk in the human body, originating from the sacral plexus and supplying the posterior thigh and leg/foot.",
            origin = "Sacral Plexus anterior rami (L4, L5, S1, S2, S3)",
            insertion = "Divides into Tibial Nerve and Common Fibular (Peroneal) Nerve at the upper popliteal fossa.",
            nerveSupply = "N/A (Innervation transmitter)",
            bloodSupply = "Arteria comitans nervi ischiadici (branch of inferior gluteal artery).",
            action = "Motor: Posterior thigh muscles (hamstrings) and all leg/foot muscles. Sensory: Postero-lateral leg and foot (except medial strip).",
            clinicalImportance = "Subject to pelvic or piriformis muscle compression. Damage results in foot drop and paralysis of posterior compartment.",
            commonConditions = listOf("Sciatica", "Piriformis syndrome", "Lumbar radiculopathy", "Foot drop (Common peroneal compression)"),
            vivaQuestions = listOf("What are the root values of the sciatic nerve?", "Describe piriformis syndrome and how to differentiate it from lumbar radiculopathy.", "Which muscles are innervated by the tibial division?"),
            references = listOf("O'Sullivan Physical Rehabilitation", "Butler's Mobilisation of the Nervous System"),
            searchTerms = listOf("sciatic nerve", "sciatica", "nerve", "piriformis", "foot drop"),
            anatomyType = "Nerve",
            surfaceAnatomy = "Exits pelvis through greater sciatic foramen, passes midway between greater trochanter and ischial tuberosity.",
            biomechanicalImportance = "Must withstand high longitudinal strain and sliding excursion during leg mobilization (basis for nerve glide tensioning).",
            palpationLandmarks = "Midway between the greater trochanter and ischial tuberosity during passive hip flexion.",
            mnemonic = "L4 to S3 sets you free to bend your knee!",
            quickRevision = "Root values L4-S3 • Splits into Tibial & Common Fibular • Entrapped in Piriformis Syndrome • Stretch test: Slump/Straight Leg Raise."
        ),
        DetailedAnatomy(
            id = "a_p5",
            name = "Femoral Artery",
            definition = "The main arterial vascular trunk of the lower limb, continuing from the external iliac artery beneath the inguinal ligament.",
            origin = "Continuation of External Iliac Artery distal to inguinal ligament",
            insertion = "Passes through adductor hiatus to become the Popliteal Artery.",
            nerveSupply = "Innervated by femoral nerve branches and sympathetic perivascular plexus.",
            bloodSupply = "N/A (Main arterial supply)",
            action = "Vascular: Propels oxygenated blood to the anterior/medial compartments of the thigh and leg.",
            clinicalImportance = "Primary clinical access vessel for coronary catheterization, angiographies, and arterial blood gas (ABG) samples.",
            commonConditions = listOf("Peripheral Artery Disease (PAD)", "Femoral artery aneurysm", "Arterial thrombosis"),
            vivaQuestions = listOf("Outline the boundaries of the femoral triangle.", "List the major branches of the femoral artery.", "Where does the femoral artery change its name to popliteal?"),
            references = listOf("B.D. Chaurasia Vol 2", "Gray’s Anatomy for Students", "Moore Clinically Oriented Anatomy"),
            searchTerms = listOf("femoral artery", "pulse", "artery", "femoral triangle", "groin"),
            anatomyType = "Artery",
            surfaceAnatomy = "Pulse is felt in the femoral triangle, midway between the ASIS and the pubic symphysis.",
            biomechanicalImportance = "Subject to mechanical flexing, bending, and axial twisting within the adductor canal during gait cycle.",
            palpationLandmarks = "Groin mid-inguinal pulse point (halfway between ASIS and pubic symphysis).",
            mnemonic = "NAVEL (groin, lateral to medial): Nerve, Artery, Vein, Empty space, Lymphatics!",
            quickRevision = "Continuation of External Iliac • Mid-inguinal pulse point • Becomes Popliteal artery at adductor hiatus • Main lower limb blood supply."
        ),
        DetailedAnatomy(
            id = "a_p6",
            name = "Anterior Cruciate Ligament",
            definition = "A crucial intra-capsular, extra-synovial ligament inside the knee joint preventing anterior displacement of the tibia relative to the femur.",
            origin = "Anterior intercondylar area of tibia",
            insertion = "Postero-medial aspect of the lateral femoral condyle",
            nerveSupply = "Posterior articular branches of tibial nerve.",
            bloodSupply = "Middle genicular artery.",
            action = "Provides 85% of structural resistance to anterior tibial translation and controls knee rotational stability.",
            clinicalImportance = "Most commonly injured major knee ligament in sliding and pivoting sports; tear results in rotational instability and accelerated joint degeneration.",
            commonConditions = listOf("ACL tear (non-contact pivoting trauma)", "Unhappy triad (ACL, MCL, and medial meniscus tear)", "Rotational knee instability"),
            vivaQuestions = listOf("Describe the mechanical differences between the anteromedial and posterolateral bands of the ACL.", "Name three special tests to assess ACL integrity and compare sensitivity.", "Why does an ACL tear typically result in immediate joint effusion?"),
            references = listOf("Magee Orthopedic Physical Assessment", "Dutton's Orthopaedic Examination", "Kisner & Colby Therapeutic Exercise"),
            searchTerms = listOf("acl", "anterior cruciate ligament", "knee", "joint", "instability", "pivot"),
            anatomyType = "Ligament",
            surfaceAnatomy = "Deep ligament inside capsule, not directly visible or palpable at surface.",
            biomechanicalImportance = "Two principal bands (Anteromedial and Posterolateral) twist and tension dynamically throughout joint ROM to maintain multi-axial stability.",
            palpationLandmarks = "Epicondylar joint lines laterally/medially for swelling assessment; the ligament itself is internal.",
            mnemonic = "LAMP: Lateral femoral attaches Anterior cruciate, Medial femoral attaches Posterior cruciate!",
            quickRevision = "Intra-capsular ligament • Primary block to anterior tibial translation (85%) • Pivot-cut injury • Tests: Lachman, Drawer, Pivot-Shift."
        ),
        DetailedAnatomy(
            id = "a_t0",
            name = "Respiratory Diaphragm",
            definition = "A primary domain double-domed musculofibrous partition that separates the thoracic cavity from the abdominal cavity.",
            origin = "Sternal part (back of xiphoid process), costal part (inner surface of lower 6 ribs/cartilages), lumbar part (crura from vertebral bodies L1-L3).",
            insertion = "Central tendon of diaphragm.",
            nerveSupply = "Phrenic motor nerve (C3, C4, C5) and lower intercostal sensory nerves.",
            bloodSupply = "Superior phrenic, musculophrenic, inferior phrenic, and pericardiacophrenic arteries.",
            action = "Primary muscle of inspiration; flattens to increase thoracic vertical dimension.",
            clinicalImportance = "Involved in hiccup reflexes, diaphragmatic breathing exercises, and potential site for hiatal hernias.",
            commonConditions = listOf("Diaphragmatic hernia", "Phrenic nerve palsy", "Hiccups (singultus)"),
            vivaQuestions = listOf("Name the major apertures of the diaphragm and their vertebral levels.", "Explain the embryonic origin and nerve supply of the diaphragm.", "What nerve roots form the phrenic nerve?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Gray's Anatomy", "Moore's Clinically Oriented Anatomy"),
            searchTerms = listOf("diaphragm", "respiratory", "inspiration", "midline", "thorax"),
            anatomyType = "Muscle",
            surfaceAnatomy = "Dome rises as high as the 4th intercostal space on the right and 5th on the left during deep expiration.",
            biomechanicalImportance = "Generates negative intrathoracic pressure to draw air into the lungs and increases intra-abdominal pressure to assist in visceral emptying.",
            palpationLandmarks = "Not directly palpable, but xiphoid process and costal margins mark its attachments.",
            mnemonic = "I Eat 10 Eggs At 12: IVC (T8), Esophagus (T10), Aorta (T12)!",
            quickRevision = "Primary inspiratory muscle • Phrenic nerve (C3-C5) • Separates thorax/abdomen • Three major apertures at T8, T10, T12."
        ),
        DetailedAnatomy(
            id = "a_t1",
            name = "The Heart",
            definition = "A hollow, muscular, four-chambered organ situated in the middle mediastinum that pumps blood through the circulatory system.",
            origin = "N/A (Derived from embryonic heart tube)",
            insertion = "N/A",
            nerveSupply = "Autonomic nervous system via cardiac plexus (sympathetic from T1-T4, parasympathetic via vagus nerve).",
            bloodSupply = "Coronary arteries (left and right) arising from the ascending aorta.",
            action = "Pumps deoxygenated blood to the lungs and oxygenated blood to the systemic circulation.",
            clinicalImportance = "Apex beat is a key indicator of cardiac size and position; vulnerable to myocardial infarction and rhythm disorders.",
            commonConditions = listOf("Myocardial Infarction", "Coronary Artery Disease", "Cardiac Tamponade", "Arrhythmias"),
            vivaQuestions = listOf("Describe the boundary lines and chambers of the heart.", "Identify the blood vessels and branches of the left coronary artery.", "Where is the apex beat normally felt?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Gray's Anatomy", "Netter's Clinical Anatomy"),
            searchTerms = listOf("heart", "coronary", "cardiac", "ventricle", "atrium", "apex", "thorax"),
            anatomyType = "Organ",
            surfaceAnatomy = "Apex is located in the left 5th intercostal space, about 9 cm from the midsternal line.",
            biomechanicalImportance = "Ensures continuous multi-chamber dynamic pressure flow to maintain systematic perfusion under varying workloads.",
            palpationLandmarks = "Apex beat (5th intercostal space midclavicular line), sternal margins for cardiac boundaries.",
            mnemonic = "Try Before Buying: Tricuspid valve is on the right, Bicuspid (mitral) is on the left!",
            quickRevision = "Central pacemaker • Middle mediastinum • Coronary supply • Left ventricle is thickest • Apex at left 5th ICS."
        ),
        DetailedAnatomy(
            id = "a_t2",
            name = "Lungs",
            definition = "The paired principal organs of respiration, responsible for exchange of oxygen and carbon dioxide.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Pulmonary plexus (parasympathetic via vagus nerve, sympathetic from thoracic sympathetic trunk).",
            bloodSupply = "Bronchial arteries (nutrition) and pulmonary arteries (functional gas exchange).",
            action = "Facilitates passive and active external respiration (gas exchange).",
            clinicalImportance = "Aspiration prone due to right bronchus orientation; target of chest physiotherapy postural drainage techniques.",
            commonConditions = listOf("Aspiration pneumonia", "Atelectasis", "COPD", "Bronchitis", "Pleurisy"),
            vivaQuestions = listOf("Compare the lobes and fissures of the right and left lungs.", "What is a bronchopulmonary segment and why is it surgically significant?", "Why do foreign bodies lodge in the right main bronchus?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Gray's Anatomy", "Felson's Chest Roentgenology"),
            searchTerms = listOf("lungs", "pulmones", "respiratory", "bronchial", "alveoli", "thorax"),
            anatomyType = "Organ",
            surfaceAnatomy = "Apex extends about 2.5 cm above the medial third of the clavicle into the root of the neck.",
            biomechanicalImportance = "Inherent elastic tissue recoil facilitates passive exhalation while maintaining airway patency during expansion.",
            palpationLandmarks = "Supraclavicular fossa (apex), costal margins for inferior borders.",
            mnemonic = "Right has Three (lobes and letters in 'Tri'), Left has Two (lobes and letters in 'Bi')!",
            quickRevision = "Respiration organs • Right has 3 lobes / Left has 2 lobes • Clinically segmented • Right bronchus is wider/vertical."
        ),
        DetailedAnatomy(
            id = "a_t3",
            name = "Internal Thoracic Artery",
            definition = "A key branch of the subclavian artery that descends along the internal aspect of the anterior thoracic wall.",
            origin = "First part of the subclavian artery.",
            insertion = "Terminal branches: superior epigastric and musculophrenic arteries.",
            nerveSupply = "Autonomous periarterial sympathetic fibers.",
            bloodSupply = "Internal thoracic artery itself.",
            action = "Supplies the anterior intercostal spaces, sternum, breast, and pericardium.",
            clinicalImportance = "Commonly used as an arterial graft for Coronary Artery Bypass Grafting (CABG) surgery (LIMA/RIMA).",
            commonConditions = listOf("Cardiovascular bypass grafting", "Coarctation of the aorta (collateral pathway)"),
            vivaQuestions = listOf("Describe the origin, course, and termination of the internal thoracic artery.", "What are its terminal branches?", "Why is it favored for coronary bypass surgical grafts?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Gray's Anatomy for Students"),
            searchTerms = listOf("internal thoracic", "mammary", "artery", "cabg", "subclavian", "thorax"),
            anatomyType = "Artery",
            surfaceAnatomy = "Runs vertically downward, parallel to and about 1.25 cm lateral to the margin of the sternum.",
            biomechanicalImportance = "Maintains collateral anterior chest-to-abdominal vascular anastomosis (superior epigastric link).",
            palpationLandmarks = "Cannot be palpated directly, located deep to the costal cartilages.",
            mnemonic = "IMA: Internal Mammary Artery supplies the anterior chest wall!",
            quickRevision = "Branch of subclavian • Runs lateral to sternum • Terminates as musculophrenic and superior epigastric • Gold standard CABG graft."
        ),
        DetailedAnatomy(
            id = "a_t4",
            name = "Azygos Vein",
            definition = "A large venous channel that drains the posterior thoracic and abdominal walls, linking the IVC and SVC.",
            origin = "Ascending lumbar and subcostal veins (usually on the right side).",
            insertion = "Arch of azygos vein empties into the posterior aspect of the Superior Vena Cava (SVC) at T4.",
            nerveSupply = "N/A",
            bloodSupply = "Collected vein drainage.",
            action = "Returns blood from thoracic margins back to target central venous flow.",
            clinicalImportance = "Primary bypass collateral vector if either the SVC or IVC is obstructed.",
            commonConditions = listOf("SVC obstruction syndrome", "IVC thrombosis", "Portal hypertension collateral dilation"),
            vivaQuestions = listOf("Name the major tributaries of the azygos vein.", "How does the azygos system function in IVC obstruction?", "Where does the azygos vein terminate?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Gray's Anatomy"),
            searchTerms = listOf("azygos", "vein", "posterior mediastinum", "collateral", "svc", "thorax"),
            anatomyType = "Vein",
            surfaceAnatomy = "Deep posterior mediastinal structure, not palpable from the chest wall surface.",
            biomechanicalImportance = "Pressure-equalizing visceral bypass pathway linking the superior and inferior venous quadrants.",
            palpationLandmarks = "Deep posterior spinal levels T4-T12.",
            mnemonic = "Azygos: Unpaired (Greek) - located mainly on the right side, with hemiazygos on the left!",
            quickRevision = "Unpaired right-sided vein • Links IVC/SVC • Empties into SVC at T4 • Principal posterior mediastinal collateral."
        ),
        DetailedAnatomy(
            id = "a_t5",
            name = "Thoracic Duct",
            definition = "The main lymphatic collector trunk of the human body, draining three-fourths of systemic lymph.",
            origin = "Cisterna chyli in the abdomen at L1-L2 vertebral level.",
            insertion = "Junction of left internal jugular and left subclavian veins (left venous angle).",
            nerveSupply = "Autonomous peri-vascular sympathetic nerve network.",
            bloodSupply = "N/A",
            action = "Transports lipid-rich chyle and lymphatic immune white cell fluids back to systemic blood circulation.",
            clinicalImportance = "Vulnerable to surgical or traumatic laceration, causing rapid pleural chylothorax buildup.",
            commonConditions = listOf("Chylothorax", "Filariasis lymphatic obstruction", "Virchow's node metastasis enlargement"),
            vivaQuestions = listOf("Trace the course of the thoracic duct from origin to termination.", "Which parts of the body are drained by the thoracic duct?", "What is chylothorax and how is it managed?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Shorter's Lymphatic Vessels"),
            searchTerms = listOf("thoracic duct", "lymphatic", "chyle", "cisterna chyli", "left venous angle", "thorax"),
            anatomyType = "Lymphatic",
            surfaceAnatomy = "Deep thoracic pathway, terminates in the left supraclavicular fossa.",
            biomechanicalImportance = "Critical fluid volume regulator returning filtered interstitial fluid and visceral fats to venous blood.",
            palpationLandmarks = "Virchow's node (Troisier's sign) in the left supraclavicular fossa.",
            mnemonic = "Duct lies between two geese: Azy-GOOSE (Azygos) on the right, Esopha-GOOSE (Esophagus) on the left!",
            quickRevision = "Largest lymph duct • Starts at cisterna chyli (L1/L2) • Terminates at left venous angle • Drains 75% of body lymph."
        ),
        DetailedAnatomy(
            id = "a_t6",
            name = "Intercostal Nerves",
            definition = "The anterior rami of thoracic spinal nerves T1-T11 that travel in the intercostal spaces of the thoracic wall.",
            origin = "Anterior rami of T1-T11 thoracic spinal nerves.",
            insertion = "Sensory endings in skin of chest/abdomen, motor inserts in intercostal and abdominal muscles.",
            nerveSupply = "The nerves themselves.",
            bloodSupply = "Posterior and anterior intercostal arteries.",
            action = "Motor innervation to intercostal muscles; sensory cutaneous innervation to the thoracic wall skin.",
            clinicalImportance = "Subject to viral reactivation (herpes zoster/shingles), intercostal neuralgia, and target for regional intercostal blocks.",
            commonConditions = listOf("Intercostal neuralgia", "Herpes zoster (Shingles)", "Rib fracture pain relief (block target)"),
            vivaQuestions = listOf("Describe the pathway of an intercostal nerve in the costal groove.", "What is shingles and why does it present in a dermatomal band?", "Where is an intercostal nerve block injected?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Nolan's Regional Nerve Blocks"),
            searchTerms = listOf("intercostal", "nerves", "thoracic spinal", "neuralgia", "shingles", "thorax"),
            anatomyType = "Nerve",
            surfaceAnatomy = "Runs along the lower border of each rib in a horizontal band, corresponding to thoracic dermatomes.",
            biomechanicalImportance = "Coordinated dermatomal and myotomal signaling regulates rhythmic intercostal contraction and wall expansion.",
            palpationLandmarks = "Lower borders of ribs (costal grooves) for trigger zone assessment.",
            mnemonic = "VAN: Vein, Artery, Nerve from superior to inferior in the costal groove!",
            quickRevision = "Anterior rami T1-T11 • Run in costal grooves • Supply intercostal muscles and chest skin • Targeted in rib blocks."
        ),
        DetailedAnatomy(
            id = "a_t7",
            name = "Sternum",
            definition = "An elongated, flat bone located in the midline of the anterior thoracic wall, also called the breastbone.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Somatic sensory twigs from anterior cutaneous branches of intercostal nerves.",
            bloodSupply = "Branches of the internal thoracic artery.",
            action = "Forms the anterior protection of the mediastinal cavity and anchors true rib costal cartilages.",
            clinicalImportance = "Key landmark is the Sternal Angle of Louis; clinical access site for bone marrow aspiration or surgical median sternotomy.",
            commonConditions = listOf("Sternal fracture", "Pigeon chest (Pectus carinatum)", "Funnel chest (Pectus excavatum)"),
            vivaQuestions = listOf("Name the three divisions of the sternum.", "Describe the clinical structures located at the level of the sternal angle.", "How is median sternotomy performed?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Gray's Anatomy"),
            searchTerms = listOf("sternum", "breastbone", "manubrium", "xiphoid", "angle of louis", "thorax"),
            anatomyType = "Bone",
            surfaceAnatomy = "Entirely palpable under the skin in the anterior midline of the chest.",
            biomechanicalImportance = "Rigid anterior strut stabilizing the thoracic dome against respiratory expansion and chest pressure.",
            palpationLandmarks = "Suprasternal notch, Sternal Angle of Louis, Xiphoid process.",
            mnemonic = "Sternum sections from top to bottom: Man is xiphoid? No, Manubrium, Body, Xiphoid!",
            quickRevision = "Flat breastbone • Manubrium, Body, Xiphoid • Sternal local marker at T4-T5 • Accessed in CABG marrow taps."
        ),
        DetailedAnatomy(
            id = "a_t8",
            name = "Thoracic Ribs",
            definition = "The 12 pairs of curved, flat elastic bones forming the lateral protective walls of the chest cage.",
            origin = "N/A",
            insertion = "N/A",
            nerveSupply = "Adjacent intercostal nerves.",
            bloodSupply = "Posterior and anterior intercostal arteries.",
            action = "Dynamic levers that pivot anteriorly, posteriorly, and laterally to modify chest volume during breathing.",
            clinicalImportance = "Commonly fractured; floating ribs (11-12) do not attach to sternum and can lacerate underlying organs (kidney/spleen) in trauma.",
            commonConditions = listOf("Rib fractures", "Flail chest (multiple fractures)", "Cervical rib anomaly"),
            vivaQuestions = listOf("What is a typical vs an atypical rib?", "What is 'flail chest' and what is its physiological danger?", "Describe the costovertebral articulation."),
            references = listOf("B.D. Chaurasia Human Anatomy", "Magee Orthopedic Physical Assessment"),
            searchTerms = listOf("ribs", "costal", "thoracic cage", "floating ribs", "fracture", "thorax"),
            anatomyType = "Bone",
            surfaceAnatomy = "Easily palpable along the chest walls of the thorax.",
            biomechanicalImportance = "Acts as elastic spring-loaded levers enabling pump-handle and bucket-handle volumetric expansion.",
            palpationLandmarks = "Rib angles, intercostal spaces, costal margins.",
            mnemonic = "Ribs 1-7 are True, 8-10 are False, 11-12 are Floating!",
            quickRevision = "12 elastic bony pairs • Typical ribs are 3-9 • Lower costal groove protects VAN • Dynamic breathing levers."
        ),
        DetailedAnatomy(
            id = "a_t9",
            name = "Esophagus",
            definition = "A muscular, contractile alimentary tube carrying food from the pharynx down to the stomach.",
            origin = "C6 level (pharyngeal-esophageal junction).",
            insertion = "Stomach cardia at T11 level.",
            nerveSupply = "Esophageal plexus (vagus sensory/parasympathetic, sympathetic from thoracic trunk).",
            bloodSupply = "Esophageal branches of inferior thyroid, aortic thoracic branches, and left gastric artery.",
            action = "Peristaltic muscular contraction pushes food boluses downwards into the stomach.",
            clinicalImportance = "Prone to reflux damage (esophagitis, Barrett's esophagus) and esophageal varices in portal liver hypertension.",
            commonConditions = listOf("Gastroesophageal Reflux Disease (GERD)", "Esophageal varices", "Achalasia cardiae"),
            vivaQuestions = listOf("Describe the four sites of esophageal constriction and their clinical meaning.", "Describe the blood supply and venous drainage of the esophagus.", "What is Barrett's esophagus?"),
            references = listOf("B.D. Chaurasia Human Anatomy", "Sleisenger Gastroenterology"),
            searchTerms = listOf("esophagus", "gullet", "posterior mediastinum", "constrictions", "swallowing", "thorax"),
            anatomyType = "Organ",
            surfaceAnatomy = "Deep visceral midline structure, starts in neck and traverses posterior mediastinum.",
            biomechanicalImportance = "Coordinated striated and smooth muscle contractions propel swallowed items safely past the airway.",
            palpationLandmarks = "Deep neck level at C6, otherwise internal.",
            mnemonic = "Esophagus enters diaphragm at T10 (Ten letters in 'esophagus')!",
            quickRevision = "25cm muscular tube • Traverses posterior mediastinum • Four physiological constrictions • Passes diaphragm at T10."
        )
    )

    @Volatile var anatomies = initialAnatomies

    val initialVivaQuestions = listOf(
        VivaMcq(
            question = "Which nerve passes through the quadrangular space?",
            options = listOf("Axillary nerve", "Radial nerve", "Musculocutaneous nerve", "Median nerve"),
            correctAnswer = "Axillary nerve",
            whyCorrect = "The axillary nerve and posterior circumflex humeral vessels pass through the quadrangular space.",
            whyIncorrect = "The radial nerve passes through the triangular interval. The other nerves do not enter these posterior spaces.",
            clinicalImportance = "Surgical neck fractures or anterior shoulder dislocations can injure the axillary nerve, causing deltoid weakness.",
            examinerTip = "Remember the boundaries: teres minor (sup), teres major (inf), long head of triceps (med), humerus (lat).",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Axillary nerve supplies the deltoid and teres minor.",
            relatedConcept = "Posterior Scapular Spaces",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The musculocutaneous nerve continues into the forearm as the:",
            options = listOf("Lateral antebrachial cutaneous nerve", "Medial antebrachial cutaneous nerve", "Posterior antebrachial cutaneous nerve", "Superficial radial nerve"),
            correctAnswer = "Lateral antebrachial cutaneous nerve",
            whyCorrect = "After piercing the deep fascia lateral to the biceps tendon, it becomes the lateral antebrachial cutaneous nerve.",
            whyIncorrect = "The medial cutaneous nerve is a branch of the medial cord. The posterior is from the radial nerve.",
            clinicalImportance = "Lesions of the musculocutaneous nerve lead to sensory loss on the lateral aspect of the forearm.",
            examinerTip = "It pierces the coracobrachialis muscle then supplies biceps and brachialis.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Terminal sensory branch of musculocutaneous nerve.",
            relatedConcept = "Nerve continuations",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which muscle initiates abduction of the shoulder joint?",
            options = listOf("Supraspinatus", "Deltoid", "Trapezius", "Serratus anterior"),
            correctAnswer = "Supraspinatus",
            whyCorrect = "The supraspinatus initiates shoulder abduction for the first 15 degrees, followed by the deltoid.",
            whyIncorrect = "Deltoid handles 15-90 degrees. Trapezius and serratus anterior handle beyond 90 degrees.",
            clinicalImportance = "Supraspinatus tendinopathy or tear causes a painful arc and weakness in early abduction.",
            examinerTip = "Empty can test specifically targets the supraspinatus.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Initiation vs continuation of shoulder abduction.",
            relatedConcept = "Scapulohumeral Rhythm",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The carpal tunnel contains the median nerve and how many tendons?",
            options = listOf("7", "8", "9", "10"),
            correctAnswer = "9",
            whyCorrect = "It contains 4 flexor digitorum superficialis, 4 flexor digitorum profundus, and 1 flexor pollicis longus tendon.",
            whyIncorrect = "Flexor carpi radialis runs in its own compartment. Palmaris longus runs superficial to the retinaculum.",
            clinicalImportance = "Carpal tunnel syndrome involves compression of the median nerve by inflamed tendons or fluid.",
            examinerTip = "Remember FDS (4), FDP (4), FPL (1) = 9 tendons total.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Contents of the carpal tunnel are frequently tested clinically.",
            relatedConcept = "Wrist Anatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Which bone is the most commonly fractured carpal bone?",
            options = listOf("Lunate", "Scaphoid", "Capitate", "Pisiform"),
            correctAnswer = "Scaphoid",
            whyCorrect = "Scaphoid fractures typically occur from a fall on an outstretched hand (FOOSH).",
            whyIncorrect = "Lunate is the most commonly dislocated, but not most commonly fractured.",
            clinicalImportance = "Scaphoid fractures have a high risk of avascular necrosis due to retrograde blood supply.",
            examinerTip = "Tenderness in the anatomical snuffbox suggests a scaphoid fracture.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "FOOSH injuries often damage the scaphoid.",
            relatedConcept = "Carpal Bones",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which muscle is NOT part of the rotator cuff?",
            options = listOf("Supraspinatus", "Infraspinatus", "Teres major", "Subscapularis"),
            correctAnswer = "Teres major",
            whyCorrect = "The rotator cuff includes Supraspinatus, Infraspinatus, Teres minor, and Subscapularis (SITS).",
            whyIncorrect = "Teres major medially rotates and adducts but does not blend with the GH joint capsule.",
            clinicalImportance = "Teres minor stabilizes the joint, teres major does not.",
            examinerTip = "SITS acronym: Supraspinatus, Infraspinatus, Teres minor, Subscapularis.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Teres minor vs major role in shoulder stability.",
            relatedConcept = "Rotator Cuff",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Which nerve is injured in 'Saturday Night Palsy'?",
            options = listOf("Median nerve", "Ulnar nerve", "Radial nerve", "Axillary nerve"),
            correctAnswer = "Radial nerve",
            whyCorrect = "Prolonged compression of the radial nerve in the spiral groove causes paralysis of wrist extensors.",
            whyIncorrect = "Median nerve causes ape hand; ulnar nerve causes claw hand.",
            clinicalImportance = "Presents with wrist drop and sensory loss over the dorsum of the hand.",
            examinerTip = "Radial nerve supplies the extensor compartment of the arm and forearm.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Compression neuropathy of the radial nerve.",
            relatedConcept = "Nerve Injuries",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "What forms the floor of the anatomical snuffbox?",
            options = listOf("Scaphoid and Trapezium", "Lunate and Triquetrum", "Capitate and Hamate", "Styloid process of radius"),
            correctAnswer = "Scaphoid and Trapezium",
            whyCorrect = "The floor is formed by the scaphoid and trapezium bones, crossed by the radial artery.",
            whyIncorrect = "The boundaries are the tendons of APL, EPB, and EPL.",
            clinicalImportance = "Pulsations of the radial artery can be felt here.",
            examinerTip = "Snuffbox floor = Scaphoid + Trapezium. Contains radial artery.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Anatomical snuffbox contents and boundaries.",
            relatedConcept = "Surface Anatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which muscle supinates the forearm when the elbow is extended?",
            options = listOf("Biceps brachii", "Supinator", "Brachioradialis", "Pronator teres"),
            correctAnswer = "Supinator",
            whyCorrect = "The supinator is the primary supinator when the elbow is extended. Biceps is highly effective when the elbow is flexed.",
            whyIncorrect = "Biceps is the most powerful supinator but relies on elbow flexion.",
            clinicalImportance = "Testing supination in extension isolates the supinator muscle from the biceps.",
            examinerTip = "Extended elbow = Supinator. Flexed elbow = Biceps.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Muscle isolation based on joint position.",
            relatedConcept = "Forearm Biomechanics",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The superficial palmar arch is formed primarily by the:",
            options = listOf("Ulnar artery", "Radial artery", "Anterior interosseous artery", "Palmar carpal arch"),
            correctAnswer = "Ulnar artery",
            whyCorrect = "The superficial palmar arch is a direct continuation of the ulnar artery, completed by a superficial palmar branch of the radial artery.",
            whyIncorrect = "The deep palmar arch is formed primarily by the radial artery.",
            clinicalImportance = "Allen's test evaluates the anastomosis between radial and ulnar arteries.",
            examinerTip = "Superficial = Ulnar dominant; Deep = Radial dominant.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Arterial supply of the hand.",
            relatedConcept = "Vascular Anatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The femoral nerve is formed by which spinal roots?",
            options = listOf("L2, L3, L4 (Posterior divisions)", "L2, L3, L4 (Anterior divisions)", "L4, L5, S1, S2", "L1, L2"),
            correctAnswer = "L2, L3, L4 (Posterior divisions)",
            whyCorrect = "The femoral nerve arises from the posterior divisions of the ventral rami of L2, L3, L4.",
            whyIncorrect = "The obturator nerve arises from the anterior divisions of the same roots.",
            clinicalImportance = "Femoral nerve injury leads to weak knee extension (quadriceps paralysis) and loss of patellar reflex.",
            examinerTip = "Posterior = Femoral (extensors); Anterior = Obturator (adductors).",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Lumbar plexus roots.",
            relatedConcept = "Neuroanatomy LL",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which muscle unlocks the knee joint to initiate flexion?",
            options = listOf("Popliteus", "Plantaris", "Gastrocnemius", "Biceps femoris"),
            correctAnswer = "Popliteus",
            whyCorrect = "The popliteus laterally rotates the femur on the tibia (in closed chain) to unlock the knee.",
            whyIncorrect = "The other muscles flex the knee but do not primarily perform the rotational unlocking.",
            clinicalImportance = "Popliteus tendinopathy can cause posterolateral knee pain.",
            examinerTip = "Popliteus = Key that unlocks the knee.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Screw-home mechanism.",
            relatedConcept = "Knee Biomechanics",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The 'spring ligament' of the foot is anatomically known as the:",
            options = listOf("Plantar calcaneonavicular ligament", "Long plantar ligament", "Short plantar ligament", "Bifurcate ligament"),
            correctAnswer = "Plantar calcaneonavicular ligament",
            whyCorrect = "It connects the calcaneus to the navicular and supports the head of the talus, maintaining the medial longitudinal arch.",
            whyIncorrect = "The long plantar ligament is lateral to it and deeper.",
            clinicalImportance = "Laxity or tear of this ligament can lead to flat foot (pes planus).",
            examinerTip = "Supports the highest part of the medial longitudinal arch.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Arches of the foot support.",
            relatedConcept = "Foot Anatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which structure does NOT pass deep to the flexor retinaculum of the ankle?",
            options = listOf("Tibialis posterior", "Flexor hallucis longus", "Tibialis anterior", "Tibial nerve"),
            correctAnswer = "Tibialis anterior",
            whyCorrect = "Tibialis anterior passes anteriorly under the extensor retinaculum. The flexor retinaculum covers the tarsal tunnel structures.",
            whyIncorrect = "Tarsal tunnel contents: Tibialis posterior, Flexor digitorum longus, Posterior tibial artery/vein, Tibial nerve, Flexor hallucis longus (Tom, Dick, And Very Nervous Harry).",
            clinicalImportance = "Tarsal tunnel syndrome involves entrapment of the tibial nerve here.",
            examinerTip = "Mnemonic: Tom, Dick, And Very Nervous Harry.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Contents of Tarsal Tunnel.",
            relatedConcept = "Ankle Anatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The anterior cruciate ligament (ACL) prevents:",
            options = listOf("Anterior translation of tibia on femur", "Posterior translation of tibia on femur", "Valgus stress", "Varus stress"),
            correctAnswer = "Anterior translation of tibia on femur",
            whyCorrect = "The ACL restrains anterior movement of the tibia relative to the femur.",
            whyIncorrect = "PCL prevents posterior translation. MCL prevents valgus, LCL prevents varus.",
            clinicalImportance = "Anterior Drawer test and Lachman test evaluate ACL integrity.",
            examinerTip = "ACL = Anterior tibial movement.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Knee ligament stability.",
            relatedConcept = "Joint Biomechanics",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Which muscle is NOT a component of the pes anserinus?",
            options = listOf("Sartorius", "Gracilis", "Semitendinosus", "Semimembranosus"),
            correctAnswer = "Semimembranosus",
            whyCorrect = "Pes anserinus consists of the tendons of Sartorius, Gracilis, and Semitendinosus attaching to the medial tibia.",
            whyIncorrect = "Semimembranosus attaches posteriorly to the medial condyle of the tibia.",
            clinicalImportance = "Pes anserine bursitis causes medial knee pain just below the joint line.",
            examinerTip = "Mnemonic: SGT (Sartorius, Gracilis, Semitendinosus) = 'Sergeant' muscle group.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Pes anserinus attachments.",
            relatedConcept = "Knee Anatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The sciatic nerve typically divides into tibial and common fibular nerves at the:",
            options = listOf("Lower third of the thigh", "Greater sciatic foramen", "Gluteal fold", "Popliteal fossa"),
            correctAnswer = "Lower third of the thigh",
            whyCorrect = "The division usually occurs at the apex of the popliteal fossa (lower third of the thigh).",
            whyIncorrect = "High divisions can occur within the pelvis, piercing the piriformis.",
            clinicalImportance = "Piriformis syndrome can compress the sciatic nerve, often causing sciatica.",
            examinerTip = "The common fibular nerve wraps around the fibular head.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Course of the sciatic nerve.",
            relatedConcept = "Neuroanatomy LL",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Trendelenburg gait is caused by weakness of the:",
            options = listOf("Gluteus medius and minimus", "Gluteus maximus", "Quadriceps femoris", "Hamstrings"),
            correctAnswer = "Gluteus medius and minimus",
            whyCorrect = "These abductors stabilize the pelvis during the stance phase. Weakness causes the contralateral pelvis to drop.",
            whyIncorrect = "Gluteus maximus weakness causes a lurching gait (backward trunk lean).",
            clinicalImportance = "Superior gluteal nerve injury typically leads to a positive Trendelenburg sign.",
            examinerTip = "Sound side sags. The lesion is on the stance leg.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Hip abductor function.",
            relatedConcept = "Clinical Gait Analysis",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which artery provides the main blood supply to the head of the femur in adults?",
            options = listOf("Medial circumflex femoral artery", "Lateral circumflex femoral artery", "Obturator artery", "Inferior gluteal artery"),
            correctAnswer = "Medial circumflex femoral artery",
            whyCorrect = "The medial circumflex femoral artery provides the retinacular arteries, which are the primary supply to the femoral head and neck.",
            whyIncorrect = "The artery of the ligamentum teres (from obturator) provides limited supply in adults.",
            clinicalImportance = "Fractures of the femoral neck often disrupt this supply, leading to avascular necrosis.",
            examinerTip = "Medial > Lateral circumflex for femoral head supply.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Vascular supply to femoral head.",
            relatedConcept = "Applied Vascular Anatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Drop foot is most commonly a result of injury to the:",
            options = listOf("Common fibular (peroneal) nerve", "Tibial nerve", "Sciatic nerve", "Sural nerve"),
            correctAnswer = "Common fibular (peroneal) nerve",
            whyCorrect = "Injury to the common fibular nerve paralyzes the anterior compartment muscles (dorsiflexors), causing foot drop.",
            whyIncorrect = "Tibial nerve injury affects plantar flexion. Sural nerve is purely sensory.",
            clinicalImportance = "Often injured at the fibular neck due to superficial course.",
            examinerTip = "Foot drop = Fibular nerve. High stepping gait.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Nerve injuries of the leg.",
            relatedConcept = "Clinical Neuroanatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The sternal angle (Angle of Louis) marks the level of the:",
            options = listOf("2nd costal cartilage", "3rd costal cartilage", "Clavicle", "1st rib"),
            correctAnswer = "2nd costal cartilage",
            whyCorrect = "The sternal angle marks the articulation of the 2nd costal cartilage and is the starting point for counting ribs.",
            whyIncorrect = "The 1st rib articulates with the manubrium but is hidden by the clavicle.",
            clinicalImportance = "Crucial landmark for ECG lead placement and listening to heart valves.",
            examinerTip = "Also marks the level of T4/T5, tracheal bifurcation, and beginning/end of aortic arch.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Surface anatomy of the thorax.",
            relatedConcept = "Surface Anatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which heart valve is located between the left atrium and left ventricle?",
            options = listOf("Mitral (bicuspid) valve", "Tricuspid valve", "Aortic valve", "Pulmonary valve"),
            correctAnswer = "Mitral (bicuspid) valve",
            whyCorrect = "The mitral valve prevents backflow of oxygenated blood from the LV to the LA.",
            whyIncorrect = "Tricuspid is on the right side. Aortic and pulmonary are semilunar valves.",
            clinicalImportance = "Mitral stenosis or prolapse can lead to left-sided heart failure or pulmonary edema.",
            examinerTip = "Left side = 'Mi'tral; Right side = 'Tri'cuspid. (L-M, R-T)",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Cardiac anatomy basics.",
            relatedConcept = "Cardiovascular System",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The phrenic nerve arises primarily from which spinal cord level?",
            options = listOf("C4", "C2", "C6", "T1"),
            correctAnswer = "C4",
            whyCorrect = "The phrenic nerve arises from C3, C4, C5 fibers, with C4 being the primary contributor.",
            whyIncorrect = "Lesions above C3 cause complete paralysis of the diaphragm.",
            clinicalImportance = "C3, 4, 5 keeps the diaphragm alive.",
            examinerTip = "C3, 4, 5 keeps the diaphragm alive.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Innervation of the primary muscle of inspiration.",
            relatedConcept = "Respiratory Anatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The primary muscle of inspiration is the:",
            options = listOf("Diaphragm", "External intercostals", "Sternocleidomastoid", "Scalenes"),
            correctAnswer = "Diaphragm",
            whyCorrect = "The diaphragm accounts for about 75% of the inspiratory effort during quiet breathing.",
            whyIncorrect = "External intercostals elevate ribs. SCM and scalenes are accessory muscles.",
            clinicalImportance = "Diaphragmatic breathing is a key exercise in respiratory physiotherapy.",
            examinerTip = "It descends during inspiration, increasing the vertical dimension of the thoracic cavity.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Mechanics of breathing.",
            relatedConcept = "Respiratory Muscles",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which structure passes through the diaphragm at the level of T10?",
            options = listOf("Esophagus", "Inferior vena cava", "Aorta", "Thoracic duct"),
            correctAnswer = "Esophagus",
            whyCorrect = "The esophagus and vagus trunks pass through the esophageal hiatus at T10.",
            whyIncorrect = "IVC passes at T8. Aorta passes at T12.",
            clinicalImportance = "Hiatal hernias occur through the esophageal hiatus.",
            examinerTip = "I 8 10 Eggs At 12: IVC (8), Esophagus (10), Aorta (12).",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Diaphragmatic apertures.",
            relatedConcept = "Thoracoabdominal anatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The right lung normally has how many lobar bronchi?",
            options = listOf("3", "2", "4", "5"),
            correctAnswer = "3",
            whyCorrect = "The right lung has 3 lobes (superior, middle, inferior), each supplied by a secondary (lobar) bronchus.",
            whyIncorrect = "The left lung has 2 lobes to accommodate the heart.",
            clinicalImportance = "Aspirated foreign bodies more commonly enter the right main bronchus because it is wider, shorter, and more vertical.",
            examinerTip = "Right Lung = 3 Lobes, Left Lung = 2 Lobes + Lingula.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Bronchial tree anatomy.",
            relatedConcept = "Pulmonary Anatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The sinoatrial (SA) node is typically located in the:",
            options = listOf("Right atrium", "Left atrium", "Right ventricle", "Interventricular septum"),
            correctAnswer = "Right atrium",
            whyCorrect = "The SA node acts as the pacemaker of the heart, located in the superior wall of the right atrium near the opening of the SVC.",
            whyIncorrect = "The AV node is located in the interatrial septum.",
            clinicalImportance = "Dysfunction of the SA node leads to arrhythmias like sick sinus syndrome.",
            examinerTip = "SA node = Pacemaker, near SVC.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Cardiac conduction system.",
            relatedConcept = "Cardiovascular System",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which vessel carries oxygenated blood from the lungs to the heart?",
            options = listOf("Pulmonary vein", "Pulmonary artery", "Aorta", "Superior vena cava"),
            correctAnswer = "Pulmonary vein",
            whyCorrect = "The four pulmonary veins return oxygen-rich blood from the lungs to the left atrium.",
            whyIncorrect = "Pulmonary arteries carry deoxygenated blood to the lungs.",
            clinicalImportance = "Congestion in pulmonary veins leads to pulmonary edema in left heart failure.",
            examinerTip = "Veins go TO the heart, arteries go AWAY. Pulmonary veins are the exception for oxygenation.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Pulmonary circulation.",
            relatedConcept = "Cardiovascular System",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The thoracic duct empties lymph into the:",
            options = listOf("Junction of left internal jugular and left subclavian veins", "Right internal jugular vein", "Superior vena cava", "Azygos vein"),
            correctAnswer = "Junction of left internal jugular and left subclavian veins",
            whyCorrect = "It drains 3/4 of the body's lymph into the left venous angle.",
            whyIncorrect = "The right lymphatic duct drains the upper right quadrant into the right venous angle.",
            clinicalImportance = "Blockage or rupture can cause chylothorax.",
            examinerTip = "Thoracic duct = left side, largest lymphatic vessel.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Lymphatic drainage.",
            relatedConcept = "Lymphatic System",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which intercostal muscles are most active during forced expiration?",
            options = listOf("Internal intercostals", "External intercostals", "Innermost intercostals", "Transversus thoracis"),
            correctAnswer = "Internal intercostals",
            whyCorrect = "Internal intercostals pull the ribs downward, aiding forced expiration.",
            whyIncorrect = "External intercostals elevate the ribs during inspiration.",
            clinicalImportance = "In COPD patients, these accessory muscles are heavily recruited.",
            examinerTip = "External = Elevate (Inspiration). Internal = Depress (Expiration).",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Mechanics of respiration.",
            relatedConcept = "Respiratory Muscles",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The linea alba is formed by the interlacing aponeuroses of which muscles?",
            options = listOf("External oblique, internal oblique, transversus abdominis", "Rectus abdominis only", "Psoas major and iliacus", "Quadratus lumborum"),
            correctAnswer = "External oblique, internal oblique, transversus abdominis",
            whyCorrect = "The linea alba is a midline tendinous seam formed by the aponeuroses of the three flat abdominal muscles.",
            whyIncorrect = "The rectus abdominis runs vertically on either side of the linea alba.",
            clinicalImportance = "Diastasis recti is the separation of the rectus abdominis along the linea alba.",
            examinerTip = "Linea alba = 'White line' in the midline.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Abdominal wall structure.",
            relatedConcept = "Abdominal Wall",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which organ is retroperitoneal?",
            options = listOf("Kidneys", "Liver", "Stomach", "Spleen"),
            correctAnswer = "Kidneys",
            whyCorrect = "The kidneys lie behind the peritoneum in the retroperitoneal space.",
            whyIncorrect = "The liver, stomach, and spleen are primarily intraperitoneal.",
            clinicalImportance = "Retroperitoneal pain is often referred to the lower back.",
            examinerTip = "Mnemonic for retroperitoneal: SAD PUCKER.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Peritoneal relationships.",
            relatedConcept = "Abdominal Cavity",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The appendix is attached to the:",
            options = listOf("Cecum", "Ascending colon", "Ileum", "Sigmoid colon"),
            correctAnswer = "Cecum",
            whyCorrect = "The vermiform appendix is a narrow, blind tube attached to the posteromedial surface of the cecum.",
            whyIncorrect = "It is located at the junction of the ileum and the cecum.",
            clinicalImportance = "McBurney's point tenderness is a classic sign of appendicitis.",
            examinerTip = "Appendix = Cecal appendage.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Lower GI tract anatomy.",
            relatedConcept = "Gastrointestinal Tract",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The portal vein is formed by the union of the:",
            options = listOf("Superior mesenteric and splenic veins", "Inferior mesenteric and splenic veins", "Superior and inferior mesenteric veins", "Hepatic veins and IVC"),
            correctAnswer = "Superior mesenteric and splenic veins",
            whyCorrect = "The portal vein forms behind the neck of the pancreas by the junction of the SMV and splenic vein.",
            whyIncorrect = "The IMV usually drains into the splenic vein.",
            clinicalImportance = "Portal hypertension leads to varices (esophageal, caput medusae, hemorrhoids) due to portacaval anastomoses.",
            examinerTip = "Portal vein drains the GI tract to the liver for processing.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Hepatic portal circulation.",
            relatedConcept = "Vascular Anatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which muscle makes up the bulk of the pelvic floor?",
            options = listOf("Levator ani", "Piriformis", "Obturator internus", "Coccygeus"),
            correctAnswer = "Levator ani",
            whyCorrect = "The levator ani (puborectalis, pubococcygeus, iliococcygeus) forms the primary muscular sling of the pelvic floor.",
            whyIncorrect = "Piriformis forms the posterolateral wall. Coccygeus is smaller and posterior to levator ani.",
            clinicalImportance = "Weakness of levator ani leads to pelvic organ prolapse or urinary incontinence.",
            examinerTip = "Kegel exercises target the levator ani.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Pelvic floor anatomy.",
            relatedConcept = "Pelvic Diaphragm",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The ureters cross anterior to which artery in the pelvis?",
            options = listOf("Common or external iliac artery", "Internal iliac artery", "Gonadal artery", "Renal artery"),
            correctAnswer = "Common or external iliac artery",
            whyCorrect = "The ureters cross the pelvic brim anterior to the bifurcation of the common iliac artery or proximal external iliac artery.",
            whyIncorrect = "Water under the bridge: Ureters pass UNDER the uterine artery/vas deferens.",
            clinicalImportance = "Important landmark during pelvic surgeries (e.g., hysterectomy) to avoid ureteral injury.",
            examinerTip = "Water under the bridge.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Ureteral anatomical relationships.",
            relatedConcept = "Pelvic Anatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The inguinal canal in males contains the:",
            options = listOf("Spermatic cord", "Round ligament", "Pudendal nerve", "Femoral artery"),
            correctAnswer = "Spermatic cord",
            whyCorrect = "The inguinal canal transmits the spermatic cord in males (and the round ligament of the uterus in females).",
            whyIncorrect = "Femoral artery is in the femoral triangle.",
            clinicalImportance = "Site of direct and indirect inguinal hernias.",
            examinerTip = "Ilioinguinal nerve also passes through it.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Inguinal canal contents.",
            relatedConcept = "Abdominal Wall",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Which ligament limits extension of the hip joint?",
            options = listOf("Iliofemoral ligament", "Pubofemoral ligament", "Ischiofemoral ligament", "Ligamentum teres"),
            correctAnswer = "Iliofemoral ligament",
            whyCorrect = "The strong, Y-shaped iliofemoral ligament anteriorly restricts hyperextension of the hip.",
            whyIncorrect = "Pubofemoral limits abduction. Ischiofemoral limits internal rotation.",
            clinicalImportance = "Allows individuals with paraplegia to stand using the Y-ligament for passive support.",
            examinerTip = "Y-ligament of Bigelow is the strongest ligament in the body.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Hip joint stability.",
            relatedConcept = "Joint Biomechanics",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The common bile duct is formed by the union of the:",
            options = listOf("Common hepatic duct and cystic duct", "Right and left hepatic ducts", "Pancreatic duct and cystic duct", "Hepatic duct and pancreatic duct"),
            correctAnswer = "Common hepatic duct and cystic duct",
            whyCorrect = "The biliary tree converges as the cystic duct (from the gallbladder) joins the common hepatic duct.",
            whyIncorrect = "The common bile duct then joins the pancreatic duct at the hepatopancreatic ampulla (of Vater).",
            clinicalImportance = "Gallstones in the common bile duct (choledocholithiasis) cause jaundice.",
            examinerTip = "Cystic + Common Hepatic = Common Bile Duct.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Biliary system.",
            relatedConcept = "Hepatobiliary Anatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which nerve innervates the cremaster muscle?",
            options = listOf("Genitofemoral nerve", "Ilioinguinal nerve", "Pudendal nerve", "Obturator nerve"),
            correctAnswer = "Genitofemoral nerve",
            whyCorrect = "The genital branch of the genitofemoral nerve (L1, L2) provides motor supply to the cremaster muscle.",
            whyIncorrect = "The ilioinguinal nerve provides sensory supply to the anterior scrotum/labia.",
            clinicalImportance = "The cremasteric reflex tests the L1-L2 spinal cord level.",
            examinerTip = "Sensory = Ilioinguinal + Femoral br. of Genitofemoral. Motor = Genital branch.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Cremasteric reflex pathway.",
            relatedConcept = "Neuroanatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The facial nerve (CN VII) exits the skull through the:",
            options = listOf("Stylomastoid foramen", "Jugular foramen", "Foramen ovale", "Internal acoustic meatus"),
            correctAnswer = "Stylomastoid foramen",
            whyCorrect = "Following its course through the petrous part of the temporal bone, it exits via the stylomastoid foramen to supply facial expression muscles.",
            whyIncorrect = "It enters the skull through the internal acoustic meatus alongside CN VIII.",
            clinicalImportance = "Bell's palsy causes unilateral facial muscle paralysis due to CN VII inflammation at the stylomastoid foramen or facial canal.",
            examinerTip = "Motor branch exits stylomastoid foramen.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Facial nerve course.",
            relatedConcept = "Cranial Nerves",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which muscle elevates the mandible during mastication?",
            options = listOf("Masseter", "Lateral pterygoid", "Digastric", "Platysma"),
            correctAnswer = "Masseter",
            whyCorrect = "The masseter, temporalis, and medial pterygoid elevate the mandible. The lateral pterygoid depresses it.",
            whyIncorrect = "Lateral pterygoid opens the jaw. Digastric assists in depression.",
            clinicalImportance = "Spasm of the masseter causes trismus (lockjaw).",
            examinerTip = "Lateral pterygoid Lowers (depresses) the jaw.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Muscles of mastication.",
            relatedConcept = "Head Anatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The trigeminal nerve (CN V) has how many major divisions?",
            options = listOf("3", "2", "4", "5"),
            correctAnswer = "3",
            whyCorrect = "Ophthalmic (V1), Maxillary (V2), and Mandibular (V3) divisions.",
            whyIncorrect = "It is the primary sensory nerve of the face.",
            clinicalImportance = "Trigeminal neuralgia presents with severe stabbing pain along one of these divisions.",
            examinerTip = "V1, V2, V3.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "CN V anatomy.",
            relatedConcept = "Cranial Nerves",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The primary motor supply to the tongue is by the:",
            options = listOf("Hypoglossal nerve (CN XII)", "Glossopharyngeal nerve (CN IX)", "Vagus nerve (CN X)", "Facial nerve (CN VII)"),
            correctAnswer = "Hypoglossal nerve (CN XII)",
            whyCorrect = "CN XII supplies all intrinsic and extrinsic muscles of the tongue except the palatoglossus (CN X).",
            whyIncorrect = "CN IX and VII supply taste. V3 provides general sensation.",
            clinicalImportance = "A lesion to CN XII causes the protruded tongue to deviate TOWARDS the side of the lesion.",
            examinerTip = "Lick your wounds (deviates toward lesion).",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Tongue motor innervation.",
            relatedConcept = "Cranial Nerves",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which artery is a direct branch of the external carotid artery?",
            options = listOf("Facial artery", "Middle meningeal artery", "Vertebral artery", "Ophthalmic artery"),
            correctAnswer = "Facial artery",
            whyCorrect = "The facial artery is a major anterior branch of the ECA.",
            whyIncorrect = "Middle meningeal is from maxillary. Vertebral is from subclavian. Ophthalmic is from internal carotid.",
            clinicalImportance = "Facial pulse can be palpated on the inferior border of the mandible.",
            examinerTip = "ECA branches: SALFO PMS.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Carotid circulation.",
            relatedConcept = "Vascular Anatomy HN",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "What structure produces cerebrospinal fluid (CSF)?",
            options = listOf("Choroid plexus", "Arachnoid granulations", "Dura mater", "Pia mater"),
            correctAnswer = "Choroid plexus",
            whyCorrect = "The choroid plexus, located within the ventricles, produces CSF.",
            whyIncorrect = "Arachnoid granulations absorb CSF into the venous sinuses.",
            clinicalImportance = "Blockage of CSF flow leads to hydrocephalus.",
            examinerTip = "Produced in ventricles, absorbed in superior sagittal sinus.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "CSF circulation.",
            relatedConcept = "Neuroanatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The middle meningeal artery enters the skull through the:",
            options = listOf("Foramen spinosum", "Foramen ovale", "Foramen rotundum", "Jugular foramen"),
            correctAnswer = "Foramen spinosum",
            whyCorrect = "It runs through the foramen spinosum to supply the dura mater.",
            whyIncorrect = "Foramen ovale transmits the mandibular nerve (V3). Foramen rotundum transmits the maxillary nerve (V2).",
            clinicalImportance = "Rupture of the middle meningeal artery (often at the pterion) causes an epidural hematoma.",
            examinerTip = "Middle Meningeal = Spinosum.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Skull foramina.",
            relatedConcept = "Osteology Head",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The optic tract carries visual information primarily to the:",
            options = listOf("Lateral geniculate nucleus", "Medial geniculate nucleus", "Superior colliculus", "Visual cortex directly"),
            correctAnswer = "Lateral geniculate nucleus",
            whyCorrect = "Most fibers of the optic tract synapse in the LGN of the thalamus for visual processing.",
            whyIncorrect = "The medial geniculate nucleus is for auditory processing.",
            clinicalImportance = "Lesions behind the optic chiasm result in homonymous hemianopia.",
            examinerTip = "Lateral = Light (Vision). Medial = Music (Auditory).",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Visual pathway.",
            relatedConcept = "Neuroanatomy Visual",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "In the neck, the common carotid artery bifurcates at the level of the:",
            options = listOf("Upper border of thyroid cartilage", "Cricoid cartilage", "Hyoid bone", "Sternal angle"),
            correctAnswer = "Upper border of thyroid cartilage",
            whyCorrect = "The bifurcation occurs at C4 level, roughly the upper border of the thyroid cartilage.",
            whyIncorrect = "Cricoid cartilage is at C6 level.",
            clinicalImportance = "The carotid sinus (baroreceptor) and body (chemoreceptor) are located at this bifurcation.",
            examinerTip = "Bifurcation at C4. C4 = Cartilage (Thyroid).",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Surface anatomy of neck.",
            relatedConcept = "Surface Anatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Which muscle is supplied by the accessory nerve (CN XI)?",
            options = listOf("Sternocleidomastoid", "Digastric", "Omohyoid", "Mylohyoid"),
            correctAnswer = "Sternocleidomastoid",
            whyCorrect = "CN XI provides motor innervation to the sternocleidomastoid and trapezius muscles.",
            whyIncorrect = "Digastric and mylohyoid are supplied by V3 and CN VII.",
            clinicalImportance = "CN XI lesion causes weakness in shrugging shoulders and turning the head against resistance.",
            examinerTip = "Test by asking the patient to shrug against resistance.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Accessory nerve testing.",
            relatedConcept = "Cranial Nerves",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "The Circle of Willis surrounds which brain structure?",
            options = listOf("Optic chiasm and pituitary stalk", "Pons", "Medulla oblongata", "Cerebellum"),
            correctAnswer = "Optic chiasm and pituitary stalk",
            whyCorrect = "It forms an anastomotic ring at the base of the brain, encircling the optic chiasm and the infundibulum (pituitary stalk).",
            whyIncorrect = "It is located in the interpeduncular fossa at the base of the brain.",
            clinicalImportance = "Aneurysms often occur at the branch points within the Circle of Willis, causing subarachnoid hemorrhage.",
            examinerTip = "Connects anterior (carotid) and posterior (vertebrobasilar) circulations.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Cerebral blood supply.",
            relatedConcept = "Neurovascular Anatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "The main decussation of the corticospinal tract occurs in the:",
            options = listOf("Medulla oblongata", "Midbrain", "Pons", "Spinal cord"),
            correctAnswer = "Medulla oblongata",
            whyCorrect = "Roughly 85-90% of corticospinal fibers cross over to the contralateral side at the medullary pyramids.",
            whyIncorrect = "The remaining fibers form the anterior corticospinal tract and cross at the segmental spinal level.",
            clinicalImportance = "A lesion above the medulla causes contralateral weakness; below causes ipsilateral weakness.",
            examinerTip = "Pyramidal decussation = lower medulla.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Motor tracts.",
            relatedConcept = "Neuroanatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "Wernicke's area is primarily located in which lobe of the brain?",
            options = listOf("Temporal lobe", "Frontal lobe", "Parietal lobe", "Occipital lobe"),
            correctAnswer = "Temporal lobe",
            whyCorrect = "Located in the posterior part of the superior temporal gyrus of the dominant hemisphere, it is responsible for language comprehension.",
            whyIncorrect = "Broca's area (speech production) is in the frontal lobe.",
            clinicalImportance = "Wernicke's aphasia results in fluent but non-sensical speech (fluent aphasia).",
            examinerTip = "Wernicke = What? (Comprehension). Broca = Babble (Production).",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Cortical functional areas.",
            relatedConcept = "Functional Neuroanatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which cranial nerve carries parasympathetic preganglionic fibers to the heart?",
            options = listOf("Vagus nerve (CN X)", "Facial nerve (CN VII)", "Glossopharyngeal nerve (CN IX)", "Oculomotor nerve (CN III)"),
            correctAnswer = "Vagus nerve (CN X)",
            whyCorrect = "The vagus nerve provides widespread parasympathetic innervation to the thoracic and abdominal viscera, including the heart.",
            whyIncorrect = "CN III, VII, and IX supply parasympathetics to structures in the head.",
            clinicalImportance = "Vagal stimulation causes bradycardia.",
            examinerTip = "Vagus = Wanderer (extends into thorax and abdomen).",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Autonomic nervous system.",
            relatedConcept = "Cranial Nerves",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which layer of the meninges is closely applied to the surface of the brain?",
            options = listOf("Pia mater", "Arachnoid mater", "Dura mater", "Endosteum"),
            correctAnswer = "Pia mater",
            whyCorrect = "The pia mater intimately covers the brain tissue, dipping into every sulcus.",
            whyIncorrect = "The arachnoid is the middle layer, and dura is the tough outer layer.",
            clinicalImportance = "Meningitis involves inflammation of the pia and arachnoid mater (leptomeninges).",
            examinerTip = "PAD : Pia (inner), Arachnoid (middle), Dura (outer).",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Meningeal layers.",
            relatedConcept = "Neuroanatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The primary somatosensory cortex is situated in the:",
            options = listOf("Postcentral gyrus", "Precentral gyrus", "Superior temporal gyrus", "Cingulate gyrus"),
            correctAnswer = "Postcentral gyrus",
            whyCorrect = "The postcentral gyrus of the parietal lobe contains the primary somatosensory cortex (Brodmann areas 3, 1, 2).",
            whyIncorrect = "Precentral gyrus is the primary motor cortex.",
            clinicalImportance = "Lesions here cause contralateral loss of discriminative touch and proprioception.",
            examinerTip = "Post = Sensory. Pre = Motor.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Sensory cortex location.",
            relatedConcept = "Functional Neuroanatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "What is the function of the superior colliculi?",
            options = listOf("Visual reflexes", "Auditory reflexes", "Motor coordination", "Limbic integration"),
            correctAnswer = "Visual reflexes",
            whyCorrect = "The superior colliculi of the midbrain tectum are involved in preliminary visual processing and reflex movements of the head/eyes toward visual stimuli.",
            whyIncorrect = "Inferior colliculi handle auditory reflexes.",
            clinicalImportance = "Parinaud syndrome from a pineal tumor can compress the superior colliculi, causing upward gaze palsy.",
            examinerTip = "Eyes are superior to Ears. Superior = vision, Inferior = auditory.",
            subject = "Anatomy",
            difficulty = "Moderate",
            learningPoint = "Midbrain anatomy.",
            relatedConcept = "Neuroanatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        VivaMcq(
            question = "Which ascending tract is responsible for transmitting pain and temperature?",
            options = listOf("Lateral spinothalamic tract", "Dorsal column-medial lemniscus", "Anterior spinocerebellar tract", "Corticospinal tract"),
            correctAnswer = "Lateral spinothalamic tract",
            whyCorrect = "The lateral spinothalamic tract carries pain and temperature sensation and crosses at the spinal cord level.",
            whyIncorrect = "Dorsal columns carry fine touch, proprioception, and vibration.",
            clinicalImportance = "Syringomyelia disrupts the crossing fibers of the spinothalamic tract, causing bilateral loss of pain/temp in a cape-like distribution.",
            examinerTip = "Lateral = Pain/Temp. Anterior = Crude touch.",
            subject = "Anatomy",
            difficulty = "High Yield",
            learningPoint = "Sensory tracts.",
            relatedConcept = "Spinal Cord Anatomy",
            reference = "B.D. Chaurasia Human Anatomy"
        ),
        VivaMcq(
            question = "The basal ganglia structure most directly associated with Parkinson's disease is the:",
            options = listOf("Substantia nigra", "Caudate nucleus", "Subthalamic nucleus", "Globus pallidus"),
            correctAnswer = "Substantia nigra",
            whyCorrect = "Degeneration of dopaminergic neurons in the substantia nigra pars compacta leads to Parkinson's disease.",
            whyIncorrect = "Subthalamic nucleus lesions cause hemiballismus.",
            clinicalImportance = "Results in resting tremor, bradykinesia, rigidity, and postural instability.",
            examinerTip = "Substantia nigra = Dopamine production.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "Basal ganglia pathology.",
            relatedConcept = "Neuroanatomy",
            reference = "Moore Clinically Oriented Anatomy"
        ),
        VivaMcq(
            question = "Which structure connects the two cerebral hemispheres?",
            options = listOf("Corpus callosum", "Anterior commissure", "Internal capsule", "Fornix"),
            correctAnswer = "Corpus callosum",
            whyCorrect = "The corpus callosum is the largest white matter commissure connecting the left and right hemispheres.",
            whyIncorrect = "The internal capsule contains projection fibers (corticospinal, thalamocortical).",
            clinicalImportance = "Split-brain syndrome occurs if the corpus callosum is surgically severed.",
            examinerTip = "A vast network of commissural fibers.",
            subject = "Anatomy",
            difficulty = "Easy",
            learningPoint = "White matter tracts.",
            relatedConcept = "Neuroanatomy",
            reference = "Gray’s Anatomy for Students"
        ),
        
        
        
        VivaMcq(
            question = "Which of the following is the earliest morphological hallmark of reversible cellular injury?",
            options = listOf("Cellular swelling (hydropic change)", "Karyorrhexis", "Pyknosis", "Karyolysis"),
            correctAnswer = "Cellular swelling (hydropic change)",
            whyCorrect = "Cellular swelling is the first manifestation of almost all forms of injury to cells, resulting from the failure of energy-dependent membrane pumps.",
            whyIncorrect = "Pyknosis, karyorrhexis, and karyolysis are nuclear changes associated with irreversible cell injury (necrosis) and cell death.",
            clinicalImportance = "In conditions like acute myocardial ischemia, early cellular swelling is reversible if blood flow is restored promptly.",
            examinerTip = "WBUHS examiners frequently ask to differentiate reversible from irreversible cell injury. Cell swelling and fatty change are the key reversible changes.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Loss of ATP leads to failure of the Na+/K+ ATPase pump, causing sodium and water to accumulate inside the cell.",
            relatedConcept = "Cell Injury",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "In which of the following organs is coagulative necrosis most commonly seen as a result of ischemic injury?",
            options = listOf("Heart", "Brain", "Pancreas", "Lung"),
            correctAnswer = "Heart",
            whyCorrect = "Coagulative necrosis is characteristic of infarcts in all solid organs except the brain, and is classically seen in myocardial infarction.",
            whyIncorrect = "Ischemic injury in the brain leads to liquefactive necrosis. Pancreatic necrosis is often enzymatic fat necrosis. Lung necrosis can be coagulative but frequently liquefactive or caseous.",
            clinicalImportance = "Myocardial infarction triggers coagulative necrosis, requiring cardiac rehab to be carefully monitored during the healing and fibrotic stages.",
            examinerTip = "A favorite viva question is 'Name an organ where ischemia does NOT cause coagulative necrosis.' The answer is always the brain.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Coagulative necrosis preserves the basic structural outline of the dead tissue for several days.",
            relatedConcept = "Necrosis",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following protease enzymes serve as the key executioners in the apoptotic pathway?",
            options = listOf("Caspases", "Matrix metalloproteinases", "Cathepsins", "Amylases"),
            correctAnswer = "Caspases",
            whyCorrect = "Caspases (cysteine aspartate-specific proteases) are the core enzymatic mediators of apoptotic cell death, cleaving critical cellular proteins.",
            whyIncorrect = "Matrix metalloproteinases (MMPs) degrade extracellular matrix during healing. Cathepsins are lysosomal enzymes. Amylases digest carbohydrates.",
            clinicalImportance = "Dysregulation of apoptosis can lead to autoimmune diseases or neurodegenerative skeletal muscle atrophy.",
            examinerTip = "Remember the difference between initiator caspases (e.g., Caspase 8, 9) and executioner caspases (Caspase 3, 6, 7).",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "The intrinsic mitochondrial pathway releases Cytochrome c, which activates Caspase-9, leading to the Caspase-3 execution phase.",
            relatedConcept = "Apoptosis",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which type of necrosis is characterized by complete dissolution of the dead tissue, transforming it into a liquid viscous mass?",
            options = listOf("Liquefactive necrosis", "Coagulative necrosis", "Caseous necrosis", "Fibrinoid necrosis"),
            correctAnswer = "Liquefactive necrosis",
            whyCorrect = "Liquefactive necrosis occurs due to enzymatic digestion of dead cells, typically seen in bacterial/fungal infections or hypoxic death of brain tissue.",
            whyIncorrect = "Coagulative necrosis preserves tissue architecture. Caseous necrosis has a cheese-like structureless appearance. Fibrinoid necrosis occurs in blood vessel walls.",
            clinicalImportance = "Following an ischemic stroke, liquefactive necrosis leaves a fluid-filled cavity, dictating neurological physical therapy strategies.",
            examinerTip = "WBUHS papers emphasize the relationship between brain infarctions and liquefactive necrosis due to high myelin and lipid content with lack of supportive stroma.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Liquefaction occurs when hydrolytic enzymes dominate over protein denaturation.",
            relatedConcept = "Necrosis",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What type of necrosis is characteristically seen in a tuberculous granuloma?",
            options = listOf("Caseous necrosis", "Liquefactive necrosis", "Fat necrosis", "Coagulative necrosis"),
            correctAnswer = "Caseous necrosis",
            whyCorrect = "Caseous necrosis is a form of cell death that classically has a yellowish-white, crumbly, cheese-like macroscopic appearance, typical of tuberculosis.",
            whyIncorrect = "Liquefactive occurs in brain or suppurative infections. Fat necrosis is seen in acute pancreatitis or breast trauma. Coagulative necrosis is typical of infarcts.",
            clinicalImportance = "Patients with spine tuberculosis (Pott's disease) show caseous necrosis in vertebral bodies, risking spinal collapse and neurological deficits.",
            examinerTip = "Under the microscope, caseous necrosis appears as a structureless, eosinophilic, amorphous area surrounded by an epithelioid granulomatous border.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Caseous necrosis is essentially a combination of coagulative and liquefactive necrosis typical of mycobacterial infection.",
            relatedConcept = "Necrosis",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Irreversible cell injury is fundamentally distinguished from reversible injury by which of the following events?",
            options = listOf("Profound mitochondrial dysfunction and membrane damage", "Loss of ribosome attachments from ER", "Mild cell swelling", "Depletion of glycogen stores"),
            correctAnswer = "Profound mitochondrial dysfunction and membrane damage",
            whyCorrect = "The two consistent points of no return in cell injury are the inability to reverse mitochondrial dysfunction and profound disturbances in membrane function.",
            whyIncorrect = "Ribosomal detachment, cell swelling, and glycogen depletion are all reversible changes.",
            clinicalImportance = "In skeletal muscle compartment syndrome, prolonged ischemia causes membrane rupture, spilling creatine kinase and myoglobin into blood.",
            examinerTip = "Remember that mitochondrial membrane permeabilization releases Cytochrome c, driving the irreversible transition.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Severe membrane damage allows extracellular calcium to flood the cytosol, activating destructive enzymes.",
            relatedConcept = "Cell Injury",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which intracellular 'wear-and-tear' pigment accumulates in tissues as a result of free radical injury and lipid peroxidation with aging?",
            options = listOf("Lipofuscin", "Hemosiderin", "Melanin", "Bilirubin"),
            correctAnswer = "Lipofuscin",
            whyCorrect = "Lipofuscin is an insoluble brownish-yellow pigment that accumulates in heart, brain, and liver of aging patients or those with severe malnutrition.",
            whyIncorrect = "Hemosiderin is an iron-derived golden-yellow pigment. Melanin is the normal black skin pigment. Bilirubin is the bile pigment associated with jaundice.",
            clinicalImportance = "Accumulation of lipofuscin indicates cellular senescence and chronic oxidative stress, which correlate with age-related muscle sarcopenia.",
            examinerTip = "Often referred to as the 'brown atrophy' pigment. It is non-toxic to the cell but stands as a marker of past free-radical damage.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Lipofuscin consists of complexes of lipids and proteins derived from lipid peroxidation of polyunsaturated lipids of subcellular membranes.",
            relatedConcept = "Cell Injury",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which type of pathological calcification occurs in dying, necrotic tissues in the presence of normal serum calcium levels?",
            options = listOf("Dystrophic calcification", "Metastatic calcification", "Calcinosis cutis", "Osteoid calcification"),
            correctAnswer = "Dystrophic calcification",
            whyCorrect = "Dystrophic calcification occurs locally in non-viable, necrotic, or degenerated tissues, even though serum calcium levels are completely normal.",
            whyIncorrect = "Metastatic calcification occurs in normal, healthy tissue due to systemic hypercalcemia (high serum calcium).",
            clinicalImportance = "Calcification of atheromatous plaques in blood vessels or calcific aortic stenosis leads to arterial stiffness and reduced cardiopulmonary tolerance.",
            examinerTip = "A very popular distinction in WBUHS: Dystrophic occurs in dead tissue with normal calcium levels, while Metastatic occurs in living tissue with high calcium levels.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Calcium deposition is initiated by membrane damage and calcium binding to phospholipids.",
            relatedConcept = "Cell Injury",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What is the primary cause of the localized redness (rubor) and warmth (calor) seen in acute inflammation?",
            options = listOf("Vasodilation of arterioles leading to hyperaemia", "Increased vascular permeability", "Accumulation of leukocytic exudate", "Margination and pavementing"),
            correctAnswer = "Vasodilation of arterioles leading to hyperaemia",
            whyCorrect = "Vasodilation is the earliest vascular response in acute inflammation, resulting in increased local blood flow (hyperemia), causing redness and heat.",
            whyIncorrect = "Increased vascular permeability leads to edema (tumor). Leukocytic accumulation is a cellular event. Margination is the alignment of WBCs along the endothelium.",
            clinicalImportance = "When managing acute soft-tissue injuries (e.g., ankle sprain), vasodilation must be balanced using cryotherapy to prevent excessive exudate formation.",
            examinerTip = "Know the cardinal signs of inflammation described by Celsus: Rubor, Tumor, Calor, Dolor, and Functio Laesa (added by Virchow).",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Vasodilation is induced by chemical mediators like histamine and nitrogen oxide.",
            relatedConcept = "Inflammation",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which chemical mediator is primarily responsible for the immediate transient response of increased vascular permeability in acute inflammation?",
            options = listOf("Histamine", "Bradykinin", "Complement C5a", "Leukotriene B4"),
            correctAnswer = "Histamine",
            whyCorrect = "Histamine released from mast cells causes endothelial cell contraction, leading to intercellular gaps in postcapillary venules (the immediate transient response).",
            whyIncorrect = "Bradykinin causes pain and vasodilation. C5a is a chemotactic agent. LTB4 is primarily responsible for neutrophil chemotaxis.",
            clinicalImportance = "Histamine release underpins allergic reactions and early inflammatory tissue swelling, representing a target for pharmacological antihistamines.",
            examinerTip = "Examiners love asking about 'immediate transient reaction' vs 'delayed prolonged leakage'. Endothelial gaps are the main cause of the immediate reaction.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Histamine acts by binding to H1 receptors on endothelial cells, producing rapid venular contraction.",
            relatedConcept = "Inflammation",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following molecules mediates the firm adhesion of leukocytes to endothelial cells during acute inflammation?",
            options = listOf("Integrins", "Selectins", "Mucin-like glycoproteins", "Cadherins"),
            correctAnswer = "Integrins",
            whyCorrect = "Firm adhesion of white blood cells to the vascular endothelium is mediated by integrins expressed on WBC surfaces binding to ligands (like ICAM-1) on endothelial cells.",
            whyIncorrect = "Selectins (E, P, L-selectin) mediate the weak rolling phase of leukocytes along the endothelial wall. Cadherins are cell-cell adhesion molecules.",
            clinicalImportance = "Leukocyte Adhesion Deficiency (LAD) is a genetic defect in integrins, leading to recurrent bacterial infections and impaired wound healing.",
            examinerTip = "Rolling is mediated by Selectins. Firm Adhesion is mediated by Integrins. Transmigration (Diapedesis) is mediated by PECAM-1 (CD31).",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "The sequence of leukocyte migration is: Margination, Rolling, Adhesion, Diapedesis, and Chemotaxis.",
            relatedConcept = "Inflammation",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following is the most potent exogenous chemotactic agent for neutrophils?",
            options = listOf("Bacterial lipopolysaccharides and formylated peptides", "Interleukin-8 (IL-8)", "Leukotriene B4 (LTB4)", "Complement component C5a"),
            correctAnswer = "Bacterial lipopolysaccharides and formylated peptides",
            whyCorrect = "Bacterial products, such as peptides containing formyl-methionine (exogenous), are extremely potent chemotactic agents that attract neutrophils directly to infection sites.",
            whyIncorrect = "IL-8, LTB4, and C5a are highly potent but are strictly endogenous chemical mediators of chemotaxis, synthesized by host cells.",
            clinicalImportance = "In bacterial musculoskeletal infections (e.g., osteomyelitis), chemotactic factors recruit massive neutrophils, forming pus that can destroy bony structures.",
            examinerTip = "Pay close attention to whether the question asks for 'exogenous' (foreign like bacterial peptides) or 'endogenous' (host-made like chemokine, leukotriene, or complement factors).",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Chemotaxis is the directed migration of cells along a chemical concentration gradient.",
            relatedConcept = "Inflammation",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which arachidonic acid metabolite is synthesized via the cyclooxygenase (COX) pathway and acts as a potent vasodilator and inhibitor of platelet aggregation?",
            options = listOf("Prostacyclin (PGI2)", "Thromboxane A2 (TXA2)", "Leukotriene C4 (LTC4)", "Prostaglandin D2 (PGD2)"),
            correctAnswer = "Prostacyclin (PGI2)",
            whyCorrect = "Prostacyclin (PGI2) is produced by endothelial cells via the COX pathway, leading to vasodilation and platelet aggregation inhibition (counteracting Thromboxane).",
            whyIncorrect = "Thromboxane A2 promotes platelet aggregation and vasoconstriction. LTC4 causes bronchospasm and increased vascular permeability. PGD2 is a mediator of allergy.",
            clinicalImportance = "Nonsteroidal Anti-inflammatory Drugs (NSAIDs) block COX enzymes, reducing pain-causing prostaglandins, but must be used carefully due to gastrointestinal and renal side effects.",
            examinerTip = "Remember the opposing actions of PGI2 (vasodilator, anti-platelet) and TXA2 (vasoconstrictor, pro-platelet). WBUHS focuses highly on this dynamic.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "COX pathway yields prostaglandins and thromboxanes, whereas the 5-Lipoxygenase pathway yields leukotrienes.",
            relatedConcept = "Inflammation",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following cells are the hallmark cellular components of chronic inflammation?",
            options = listOf("Macrophages, Lymphocytes, and Plasma cells", "Neutrophils and Eosinophils", "Basophils and Mast cells", "Platelets and Megakaryocytes"),
            correctAnswer = "Macrophages, Lymphocytes, and Plasma cells",
            whyCorrect = "Chronic inflammation is characterized by infiltration with mononuclear cells, including macrophages, lymphocytes, and plasma cells, along with tissue destruction and fibrosis.",
            whyIncorrect = "Neutrophils are the hallmark of acute inflammation. Eosinophils are for parasitic infections. Basophils and mast cells mediate early allergy. Platelets are for clotting.",
            clinicalImportance = "Chronic inflammatory musculoskeletal disorders like tendonitis or rheumatoid arthritis involve persistent macrophage infiltration, requiring active physiotherapy to prevent joint contractures.",
            examinerTip = "Distinguish mononuclear cells (chronic) from polymorphonuclear neutrophils (acute). This basic distinction is heavily tested in BPT oral exams.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Macrophages are tissue-resident mononuclear phagocytes that orchestrate long-term inflammation and tissue remodeling.",
            relatedConcept = "Inflammation",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "What type of highly specialized macrophage characterized by abundant eosinophilic cytoplasm and resembling skin epithelial cells is the defining feature of a granuloma?",
            options = listOf("Epithelioid cell", "Langhans giant cell", "Foreign body giant cell", "Kupffer cell"),
            correctAnswer = "Epithelioid cell",
            whyCorrect = "Epithelioid cells are activated macrophages that have taken on an epithelial-like appearance. They have a secretory rather than major phagocytic role and aggregate to form a granuloma.",
            whyIncorrect = "Langhans or foreign-body giant cells are formed by the fusion of epithelioid cells. Kupffer cells are normal tissue macrophages in the liver.",
            clinicalImportance = "Tuberculosis, leprosy, and sarcoidosis are classic granulomatous diseases that present with prolonged physical weakness and skeletal comorbidities.",
            examinerTip = "Epithelioid cells are key. If you see epithelioid cells, think granulomatous chronic inflammation. In viva, they will ask you what an epithelioid cell is derived from (Answer: Monocyte/Macrophage).",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Granulomatous inflammation is a cellular mechanism to wall off insoluble or indigestible foreign irritants.",
            relatedConcept = "Granuloma",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which acute-phase reactant protein synthesized by the liver rises dramatically in response to IL-6 and is used clinically as a marker for systemic inflammation?",
            options = listOf("C-reactive protein (CRP)", "Albumin", "Transferrin", "Prealbumin"),
            correctAnswer = "C-reactive protein (CRP)",
            whyCorrect = "CRP is a classic acute-phase reactant whose blood levels spike dramatically (up to hundreds of fold) in acute infectious or inflammatory states.",
            whyIncorrect = "Albumin and transferrin are 'negative' acute phase reactants, meaning their levels drop during systemic inflammation.",
            clinicalImportance = "Monitoring CRP is crucial in systemic inflammatory arthropathies to assess disease activity and gauge safe physical therapy intervention levels.",
            examinerTip = "Be prepared to distinguish acute phase proteins (CRP, fibrinogen) from ESR (Erythrocyte Sedimentation Rate), which is a surrogate measurement.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "The rise of acute-phase proteins represents a systemic protective response designated as the acute-phase reaction.",
            relatedConcept = "Inflammation",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which of the following characteristics is unique to skin wound healing by secondary intention compared to primary intention?",
            options = listOf("Significant wound contraction mediated by myofibroblasts", "Minimal scar formation", "Fast epithelialization in 24 hours", "Absence of granulation tissue"),
            correctAnswer = "Significant wound contraction mediated by myofibroblasts",
            whyCorrect = "Wounds healing by secondary intention involve large tissue defects, requiring extensive granulation tissue, a larger scar, and significant wound contraction driven by myofibroblasts.",
            whyIncorrect = "Primary intention features fast healing, minimal scarring, rapid epithelialization, and little contraction.",
            clinicalImportance = "Extensive secondary intention wounds with high contraction can lead to joint contractures, which require early, gentle stretching to preserve range of motion.",
            examinerTip = "Myofibroblasts are modified fibroblasts containing alpha-smooth muscle actin, responsible for contractures. This is highly high-yield in WBUHS BPT exams!",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Secondary intention is required for gaping wounds, decubitus ulcers, or infected incisions.",
            relatedConcept = "Healing",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What are the core macro-histological constituents of healthy granulation tissue?",
            options = listOf("Proliferating capillaries (angiogenesis) and active fibroblasts", "Dense parallel collagen bundles", "Caseous necrosis and epithelioid cells", "Purulent exudate and calcification"),
            correctAnswer = "Proliferating capillaries (angiogenesis) and active fibroblasts",
            whyCorrect = "Granulation tissue is highly vascularized, young connective tissue containing newly formed capillaries (angiogenesis), active fibroblasts secreting ECM, and inflammatory cells.",
            whyIncorrect = "Parallel collagen bundles appear in mature scars. Caseous necrotic tissues are non-healing. Purulent exudate constitutes pus.",
            clinicalImportance = "In physical therapy for open wounds or ulcers, preserving delicate, granular red granulation tissue requires gentle dressings and avoiding shear forces.",
            examinerTip = "Never confuse 'granulation tissue' with 'granuloma'. Granulation tissue is a healing tissue; granuloma is a form of chronic inflammation.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Granulation tissue acts as a temporary scaffold for epithelial cell migration and tissue remodeling.",
            relatedConcept = "Healing",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "During the remodeling phase of wound healing, there is a structural shift from which temporary collagen type to which stronger mature collagen type?",
            options = listOf("Type III collagen replaced by Type I collagen", "Type I collagen replaced by Type III collagen", "Type II collagen replaced by Type IV collagen", "Type IV collagen replaced by Type II collagen"),
            correctAnswer = "Type III collagen replaced by Type I collagen",
            whyCorrect = "Early granulation tissue contains plentiful Type III collagen, which is later degraded by collagenases and replaced with the structurally stronger Type I collagen in mature scars.",
            whyIncorrect = "The opposing replacement is incorrect. Type II is found in hyaline cartilage. Type IV is found in basement membranes.",
            clinicalImportance = "Since remodeling and collagen crossover from Type III to Type I takes weeks to months, progressive loading in rehab should reflect this gaining tensile strength.",
            examinerTip = "Remember: 'Type 1' is 'Bone and strong skin'; 'Type 3' is 'Reticular fibers in soft granulation tissue'.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Matrix metalloproteinases (MMPs) degrade Type III collagen to allow tissue remodeling.",
            relatedConcept = "Healing",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What is the correct chronological sequence of stages in bone fracture healing?",
            options = listOf("Hematoma formation, Soft callus, Hard callus, remodeling", "Soft callus, Hematoma formation, Hard callus, remodeling", "Hematoma formation, remodeling, Soft callus, Hard callus", "Hard callus, Soft callus, Hematoma formation, remodeling"),
            correctAnswer = "Hematoma formation, Soft callus, Hard callus, remodeling",
            whyCorrect = "Fracture healing begins with bleeding (Hematoma), followed by cellular proliferation and soft callus (fibrocartilage), then osteoblast deposit of woven bone (Hard callus), and final remodeling to lamellar bone.",
            whyIncorrect = "Other sequences violate the physiological progression of skeletal repair.",
            clinicalImportance = "Understanding these stages is vital for physical therapists to determine timing for active range of motion, weight-bearing, and resistive exercise.",
            examinerTip = "WBUHS papers frequently ask for the timeline of fracture healing. Soft callus starts in 1-2 weeks; hard callus in 3-4 weeks.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Woven bone laid down in the hard callus phase satisfies basic mechanical load before converting to durable lamellar bone.",
            relatedConcept = "Healing",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "What pathology of cartilage healing explains why articular cartilage has an exceptionally limited capacity for self-repair after injury?",
            options = listOf("Articular cartilage is completely avascular and relies on slow synovial fluid diffusion", "Articular cartilage lacks cells and consists purely of calcium depots", "Articular cartilage has excessive nerve supply that prevents scar laying", "Articular cartilage is rapidly degraded by local lymphocytes"),
            correctAnswer = "Articular cartilage is completely avascular and relies on slow synovial fluid diffusion",
            whyCorrect = "Articular cartilage has no blood vessels, lymphatic drainage, or nerves. This avascular nature halts the typical inflammatory response required for rapid healing.",
            whyIncorrect = "Cartilage contains chondrocytes (not zero cells). It is not highly innervated. It is not systematically degraded by lymphocytes in benign traumatic injuries.",
            clinicalImportance = "In cartilage lesions or severe osteoarthritis, slow healing justifies joint mobilization exercises to facilitate nutrient diffusion through synovial fluid.",
            examinerTip = "In BPT vivas, they will ask: 'Why does bone heal with regeneration but cartilage heals with fibrous scar if at all?' Highlight the vascular differences.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Healing in cartilaginous lesions is restricted to chondrocyte proliferation without inflammation unless the subchondral bone is breached.",
            relatedConcept = "Healing",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which vitamin deficiency leads to impaired collagen synthesis and defective wound healing due to failure of proline hydroxylation?",
            options = listOf("Vitamin C", "Vitamin D", "Vitamin A", "Vitamin K"),
            correctAnswer = "Vitamin C",
            whyCorrect = "Vitamin C (ascorbic acid) is an essential cofactor for prolyl and lysyl hydroxylases, which stabilize the triple helix structure of collagen.",
            whyIncorrect = "Vitamin D is for calcium and bone homeostasis. Vitamin A regulates epithelial differentiation. Vitamin K is required for clotting factor synthesis.",
            clinicalImportance = "Scurvy results in poor healing, fragile capillaries, bleeding gums, and painful hematomas in muscles, which can mimic musculoskeletal strains.",
            examinerTip = "WBUHS MCQs often tie biochemistry to pathology. Remember that proline hydroxylation is key to collagen cross-linking.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Without Vitamin C, collagen fibers cannot be cross-linked, yielding fragile connective tissue.",
            relatedConcept = "Healing",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which of the following clinical features distinguishes a Keloid from a Hypertrophic scar?",
            options = listOf("Keloid grows beyond the boundaries of the original wound and rarely regresses", "Hypertrophic scar extends far beyond the original injury boundaries", "Keloid contains mostly thin parallel Type III collagen fibers", "Hypertrophic scars are rich in calcium salts, unlike Keloids"),
            correctAnswer = "Keloid grows beyond the boundaries of the original wound and rarely regresses",
            whyCorrect = "Keloids grow beyond the boundaries of the original wound and do not regress spontaneously, whereas hypertrophic scars stay within wound borders and can regress.",
            whyIncorrect = "Hypertrophic scars do not extend beyond original borders. Keloids contain thick, disorganized, hyalinized Type I and III collagen fibers. Calcium is not a primary differentiator.",
            clinicalImportance = "Keloids can cause functional joint restriction if crossing a joint line. They are difficult to treat and can recur after excision.",
            examinerTip = "This is a classical, high-yield theory question in BPT Pathology. Understand the collagen arrangement: Hypertrophic has parallel collagen; Keloid has thick, haphazardly arranged collagen bundles.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Keloids result from an overproduction of collagen and atypical fibroblast activity, often seen in individuals of African or Asian descent.",
            relatedConcept = "Healing",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following mechanisms is responsible for the formation of a transudative edema?",
            options = listOf("Decreased plasma oncotic pressure or increased hydrostatic pressure", "Direct bacterial infection of intercellular gaps", "Lymphatic obstruction by malignant cells", "Severe localized endothelial damage"),
            correctAnswer = "Decreased plasma oncotic pressure or increased hydrostatic pressure",
            whyCorrect = "Transudates are protein-poor fluids resulting from hydrostatic-osmotic pressure imbalances (e.g., congestive heart failure or nephrotic syndrome) with normal endothelial integrity.",
            whyIncorrect = "Intercellular gaps, infection, and severe endothelial damage produce protein-rich exudative edema. Lymphatic obstruction causes lymphedema, which is distinct.",
            clinicalImportance = "Pathological edema in renal or cardiac failure is systemic and transudative, requiring medical management rather than local manual lymphatic drainage.",
            examinerTip = "Remember the Starling equation. Transudate (protein-poor, specific gravity < 1.012); Exudate (protein-rich, inflammatory, specific gravity > 1.020).",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Reduction in plasma oncotic pressure is primarily caused by low albumin levels (hypoalbuminemia) due to liver or kidney disease.",
            relatedConcept = "Edema",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which of the following parameters is characteristic of an Exudate?",
            options = listOf("High protein content (> 3 g/dL) and high specific gravity (> 1.020)", "Low protein content (< 3 g/dL)", "Low specific gravity (< 1.012)", "Absence of inflammatory cells"),
            correctAnswer = "High protein content (> 3 g/dL) and high specific gravity (> 1.020)",
            whyCorrect = "An exudate is an inflammatory fluid characterized by high protein concentrations, cell debris, and specific gravity above 1.020, caused by increased vascular permeability.",
            whyIncorrect = "Low protein, low specific gravity, and lack of cells are typical of transudates.",
            clinicalImportance = "In pleural effusion, identifying exudative fluid suggests inflammatory or infectious pleural pathology (e.g., pneumonia, TB), requiring careful respiratory physiotherapy.",
            examinerTip = "A favorite diagnostic table in WBUHS oral exams. Be ready to explain why protein escapes in inflammation (due to widened endothelial junctions).",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Exudate indicates active localized inflammatory reactions, while transudate arises from systemic hemodynamic failures.",
            relatedConcept = "Edema",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What are the three components of Virchow's Triad that contribute to pathogenetic thrombus formation?",
            options = listOf("Endothelial injury, Stasis or turbulent blood flow, Blood hypercoagulability", "Hypoxia, Thrombocytopenia, Vasodilation", "Vasopression, Vascular spasm, Erythrocytosis", "Atherosclerosis, Heart rate, Cardiac output"),
            correctAnswer = "Endothelial injury, Stasis or turbulent blood flow, Blood hypercoagulability",
            whyCorrect = "Virchow's Triad represents the three primary abnormalities that lead to pathologic venous or arterial thrombus formation.",
            whyIncorrect = "Other options list signs or unrelated physiological variables.",
            clinicalImportance = "BPT students must understand that static bedridden patients have stasis, which is a major leg in Virchow's Triad leading to Deep Vein Thrombosis (DVT).",
            examinerTip = "Every pathology professor in West Bengal will ask you about Virchow's Triad. Memorize these three legs cleanly!",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Endothelial injury is the most dominant factor in arterial thrombus, while stasis is major in venous thrombus.",
            relatedConcept = "Thrombosis",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "What is the clinical significance of observing 'Lines of Zahn' during histopathological examination of a thrombus?",
            options = listOf("It indicates that the thrombus formed in flowing blood before death", "It proves the thrombus is post-mortem in origin", "It represents calcium deposition within the thrombus", "It shows the thrombus was caused by a bacterial embolus"),
            correctAnswer = "It indicates that the thrombus formed in flowing blood before death",
            whyCorrect = "Lines of Zahn are alternating pale bands of platelets/fibrin and dark bands of red blood cells. They only form in flowing blood, distinguishing antemortem clots from postmortem clots.",
            whyIncorrect = "Post-mortem clots lack Lines of Zahn because blood has stopped flowing. They represent platelet/fibrin layers, not calcium or bacteria.",
            clinicalImportance = "Identifying DVT vs postmortem clot at autopsy helps determine if a pulmonary embolism was the active cause of death.",
            examinerTip = "Lines of Zahn are a key microscopic differentiator. Postmortem clots are soft, unattached, and resemble 'chicken fat' or 'currant jelly' without Zahn lines.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Lines of Zahn signify active thrombosis occurring in a living system with active hemodynamic circulation.",
            relatedConcept = "Thrombosis",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Most clinically significant pulmonary emboli originate from which of the following vascular sources?",
            options = listOf("Deep veins of the lower extremity", "Superficial leg veins", "Internal jugular vein", "Portal vein"),
            correctAnswer = "Deep veins of the lower extremity",
            whyCorrect = "Over 95% of pulmonary thromboemboli originate from deep vein thrombosis (DVT) in the lower limbs, typically above the knee (e.g., popliteal, femoral, iliac veins).",
            whyIncorrect = "Superficial leg veins (varicose) rarely embolize because they do not have large surrounding muscle pumps. Jugular and portal veins do not routinely migrate to the lung.",
            clinicalImportance = "Deep vein clots are highly unstable. Vigorous active or passive leg exercises are strictly contraindicated if an acute thromboembolic DVT is suspected to avoid dislodging the clot.",
            examinerTip = "WBUHS physical therapy students need to know 'Homan's sign' (pain in calf on dorsiflexion) and the danger of embolization to the lungs.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Large emboli can lodge in the main pulmonary artery bifurcation, presenting as a fatal 'saddle embolus'.",
            relatedConcept = "Embolism",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "A patient presents with dyspnea, petechiae on the chest, and neurological symptoms 48 hours after sustaining fractures of the tibia and femur. What is the most likely pathological diagnosis?",
            options = listOf("Fat embolism syndrome", "Septic embolism", "Air embolism", "Caisson disease"),
            correctAnswer = "Fat embolism syndrome",
            whyCorrect = "Fat embolism syndrome occurs after fractures of long bones (which contain fatty marrow), characterized by pulmonary insufficiency, neurological symptoms, and a petechial rash.",
            whyIncorrect = "Septic embolism arises from infected heart valves. Air embolism requires entry of atmospheric air into vessels. Caisson disease is nitrogen gas bubble embolism.",
            clinicalImportance = "In fracture rehabilitation, early immobilization of fractures reduces bone fragment movement, preventing marrow fats from entering ruptured sinusoids.",
            examinerTip = "Look for the classic triad in clinical questions: long bone fracture + respiratory distress + petechial skin rash.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Microscopic fat globules clog pulmonary and cerebral microvasculature, accompanied by local fat breakdown releasing toxic free fatty acids.",
            relatedConcept = "Embolism",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What forms the primary pathophysiology of Shock at the cellular level?",
            options = listOf("Systemic hypoperfusion leading to cellular hypoxia and lactic acidosis", "Excessive oxygen delivery causing cellular burnout", "Widespread tissue calcification", "Sustained systemic hypertension"),
            correctAnswer = "Systemic hypoperfusion leading to cellular hypoxia and lactic acidosis",
            whyCorrect = "Shock is a state of systemic tissue hypoperfusion, resulting in inadequate oxygenation of cells, switching metabolism to anaerobic glycolysis, producing lactic acid.",
            whyIncorrect = "Oxygen delivery is depleted, not excessive. Calcification is not the acute driver. Hypotension is typical of shock, not hypertension.",
            clinicalImportance = "Early signs of shock (pallor, sweating, rapid weak pulse) in an ICU patient require immediate termination of physical therapy and urgent medical attention.",
            examinerTip = "Be clear on the transition: Hypoperfusion -> Anaerobic respiration -> Lactic acidosis -> Cell necrosis. This path is high-yield.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Cellular hypoperfusion is the common final pathway for hypovolemic, cardiogenic, and septic shock.",
            relatedConcept = "Shock",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which type of shock is characterized by systemic vasodilation and warm, flushed skin during its early stages due to release of bacterial endotoxins?",
            options = listOf("Septic shock", "Hypovolemic shock", "Cardiogenic shock", "Obstructive shock"),
            correctAnswer = "Septic shock",
            whyCorrect = "Septic shock (distributive) involves widespread peripheral vasodilation caused by inflammatory cytokines in response to lipopolysaccharides (endotoxins), leading to warm skin initially ('warm shock').",
            whyIncorrect = "Hypovolemic and cardiogenic shock trigger immediate systemic vasoconstriction (part of cold shock) to conserve brain and cardiac blood supply.",
            clinicalImportance = "Septic shock represents a major risk in postoperative orthopaedic patients who develop severe surgical site infections.",
            examinerTip = "WBUHS exams frequently ask about LPS (Lipopolysaccharide) binding. Gram-negative bacterial wall endotoxin is the major trigger of septic shock.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "In septic shock, high levels of TNF and IL-1 lead to widespread endothelial activation and systemic vasodilation.",
            relatedConcept = "Shock",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following biological behaviors represents the absolute differentiator of a malignant tumor from a benign tumor?",
            options = listOf("Metastasis", "Rapid rate of growth", "Nuclear pleomorphism", "Presence of mitotic figures"),
            correctAnswer = "Metastasis",
            whyCorrect = "Metastasis (spread of tumor to distant non-contiguous organs) and local invasiveness are the most reliable criteria that distinguish malignant from benign lesions.",
            whyIncorrect = "Rapid growth, nuclear pleomorphism, and mitoses can be seen in rapidly growing benign tissues or severe dysplasias, though more common in malignancy.",
            clinicalImportance = "Understanding metastasis routes (lymphatic vs hematogenous) helps physical therapists screen for secondary metastatic deposit risks (e.g., spine metastasis from breast cancer).",
            examinerTip = "Remember: Benign tumors NEVER metastasize. If asked for exceptions, there are none; by definition, if it metastasizes, it is malignant.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Metastasis represents the ultimate hallmark of malignancy, defining poor prognosis.",
            relatedConcept = "Neoplasia",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "A malignant tumor originating from mesenchymal tissues (such as bone, muscle, or cartilage) is designated as a:",
            options = listOf("Sarcoma", "Carcinoma", "Adenoma", "Papilloma"),
            correctAnswer = "Sarcoma",
            whyCorrect = "Sarcomas are malignant tumors arising from solid mesenchymal (connective) tissues like bones, nerves, cartilage, fat, and muscle.",
            whyIncorrect = "Carcinomas are malignant tumors of epithelial cell origin. Adenomas are benign tumors of glandular epithelium. Papillomas are benign epithelial tumors with finger-like projections.",
            clinicalImportance = "Since sarcomas (e.g., Osteosarcoma) commonly affect long bones in young adults, bone pain in youths must be screened carefully to avoid misdiagnosing it as benign muscle strain.",
            examinerTip = "WBUHS examiners frequently ask candidates to classify tumors. Epithelial is Carcinoma; Mesenchymal is Sarcoma.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Sarcomas generally spread via the hematogenous (blood) route, while carcinomas prefer lymphatic routes.",
            relatedConcept = "Neoplasia",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "What term describes an abnormal, disorganized pattern of cellular growth that represents a pre-cancerous lesion but has NOT breached the basement membrane?",
            options = listOf("Dysplasia", "Anaplasia", "Metaplasia", "Hyperplasia"),
            correctAnswer = "Dysplasia",
            whyCorrect = "Dysplasia refers to disordered growth of epithelial cells characterized by loss of cellular uniformity and architectural orientation, which can progress to cancer if untreated.",
            whyIncorrect = "Anaplasia is complete lack of differentiation (malignancy hallmark). Metaplasia is a reversible change of one adult cell type into another. Hyperplasia is an increase in cell number.",
            clinicalImportance = "Early medical detection of dysplastic cells (e.g., via cervical Pap smear) allows curative local intervention before invasion.",
            examinerTip = "Differentiate Dysplasia from Metaplasia. Metaplasia is adaptive and benign; Dysplasia is atypical and pre-neoplastic.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "When severe dysplasia involves the entire thickness of epithelium but does not penetrate the basement membrane, it is called Carcinoma in situ.",
            relatedConcept = "Neoplasia",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which malignant bone tumor typically arises in the metaphysis of long bones in young patients, showing a classic 'Codman triangle' or 'Sunburst appearance' on X-ray?",
            options = listOf("Osteosarcoma", "Ewing sarcoma", "Chondrosarcoma", "Giant cell tumor of bone"),
            correctAnswer = "Osteosarcoma",
            whyCorrect = "Osteosarcoma is the most common primary malignant bone tumor, presenting with bone pain, swelling, and classic periosteal elevation on radiographs (Codman triangle/sunburst pattern).",
            whyIncorrect = "Ewing sarcoma displays an 'onion-skin' periosteal reaction. Chondrosarcoma arises in older patients and produces cartilage. Giant cell tumor of bone features a 'soap-bubble' lesion.",
            clinicalImportance = "Following tumor resection and limb-salvage surgery for osteosarcoma, structured physical therapy is vital to restore maximum range of motion and weight-bearing safely.",
            examinerTip = "A favorite X-ray pathology question in BPT. Codman's triangle denotes elevation of periosteum by expanding tumor, indicative of aggressive growth.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Osteosarcoma cells produce neoplastic osteoid or bone matrix, which is diagnostic of this condition.",
            relatedConcept = "Tumors",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which bone tumor is characterized by 'soap bubble' lesions localized in the epiphysis of long bones, containing numerous multinucleated osteoclast-like giant cells?",
            options = listOf("Giant cell tumor (Osteoclastoma)", "Osteoma", "Ewing sarcoma", "Multiple myeloma"),
            correctAnswer = "Giant cell tumor (Osteoclastoma)",
            whyCorrect = "Giant cell tumor of bone is a benign but locally aggressive neoplasm occurring in young adults, localized in the epiphysis, presenting with soap-bubble lytic lesions.",
            whyIncorrect = "Osteomas are benign slow-growing skull bones. Ewing sarcoma is small blue round cell tumor in the diaphysis. Multiple myeloma features punched-out lesions in older adults.",
            clinicalImportance = "Because giant cell tumors expand in the epiphysis, they compromise joint stability, requiring careful postoperative weight-bearing regimes in physiotherapy.",
            examinerTip = "Epiphyseal location is key. Most bone tumors affect the metaphysis (osteosarcoma) or diaphysis (Ewing's), but Giant Cell Tumor is unique in affecting the epiphysis in adults.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Giant cell tumors contain a background of monocytic stromal cells (neoplastic) which recruit non-neoplastic multinucleated giant cells.",
            relatedConcept = "Tumors",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Cancer cachexia, characterized by progressive loss of body fat and lean skeletal muscle mass, is primarily mediated by which cytokine?",
            options = listOf("Tumor Necrosis Factor alpha (TNF-alpha)", "Interleukin-10 (IL-10)", "Transforming Growth Factor beta (TGF-beta)", "Interferon gamma"),
            correctAnswer = "Tumor Necrosis Factor alpha (TNF-alpha)",
            whyCorrect = "TNF-alpha (originally named cachectin) acts via hypothalamic appetite suppression, increasing lipolysis and skeletal muscle protein breakdown.",
            whyIncorrect = "IL-10 and TGF-beta are anti-inflammatory cytokines. Interferon gamma plays roles in macrophage activation but is not the primary driver of cachexia.",
            clinicalImportance = "Working with cancer cachexia patients requires gentle, progressive aerobic exercises to prevent muscle wasting while honoring structural fragility.",
            examinerTip = "WBUHS exams may ask: 'What cytokine was historically known as cachectin?' The answer is TNF-alpha.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Cachexia results from chronic systemic inflammation rather than simple nutritional starvation.",
            relatedConcept = "Neoplasia",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which primary malignant bone tumor is characterized histologically by sheets of small round blue cells and is associated with a reciprocal chromosomal translocation t(11;22)?",
            options = listOf("Ewing sarcoma", "Osteosarcoma", "Chondrosarcoma", "Synovial sarcoma"),
            correctAnswer = "Ewing sarcoma",
            whyCorrect = "Ewing sarcoma is composed of sheets of uniform, small, round, blue cells (neuroectodermal origin) associated with t(11;22) translocation and EWS-FLI1 fusion transcript.",
            whyIncorrect = "Osteosarcoma produces osteoid. Chondrosarcoma produces cartilaginous matrix. Synovial sarcoma is dual epithelial-spindle cell mesenchymal lesion.",
            clinicalImportance = "Ewing's primarily originates in the diaphysis of long bones. Early identification of childhood bone pain prevents delays in diagnostic oncological screening.",
            examinerTip = "In orthopaedic oncology, Ewing sarcoma's onion-skin radiographic pattern is caused by splitting of the periosteal bone layers.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Small round blue cell tumors include Ewing's, Neuroblastoma, Lymphoma, and Rhabdomyosarcoma.",
            relatedConcept = "Tumors",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What is the defining histopathological structure of the primary lung lesion in Tuberculosis, consisting of central caseous necrosis and a rim of epithelioid cells and lymphocytes?",
            options = listOf("Ghon focus (Tuberculous granuloma)", "Aschoff body", "Gandy-Gamna body", "Negri body"),
            correctAnswer = "Ghon focus (Tuberculous granuloma)",
            whyCorrect = "A Ghon focus is the primary parenchymal subpleural lesion of tuberculosis in the lung, which, when paired with infected draining lymph nodes, forms the Ghon complex.",
            whyIncorrect = "Aschoff bodies are seen in rheumatic carditis. Gandy-Gamna bodies are seen in splenomegaly. Negri bodies are seen in rabies brain cells.",
            clinicalImportance = "Prior pulmonary tuberculosis can leave fibrotic lung changes, requiring structured deep breathing and chest clearance physiotherapy.",
            examinerTip = "Ghon complex is a classic WBUHS question. Ghon complex = Ghon focus + Lymphangitis + Lymphadenitis.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Granulomatous reaction walls off Mycobacterium tuberculosis, mediated by Type IV hypersensitivity.",
            relatedConcept = "Tuberculosis",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which form of leprosy is characterized by a strong cell-mediated immune response, low bacterial load, and localized skin lesions with early nerve involvement?",
            options = listOf("Tuberculoid leprosy", "Lepromatous leprosy", "Borderline leprosy", "Histoid leprosy"),
            correctAnswer = "Tuberculoid leprosy",
            whyCorrect = "Tuberculoid leprosy is characterized by strong Th1 cell-mediated immunity, leading to localized anesthetic lesions with few bacilli (paucibacillary).",
            whyIncorrect = "Lepromatous leprosy features poor Th1 immunity, massive bacterial load (multibacillary), systemic lesions, and extensive bilateral nerve damage.",
            clinicalImportance = "Tuberculoid leprosy's localized neural thickening (e.g., ulnar nerve) causes muscle atrophy and sensory loss, necessitating hand splinting and safety education.",
            examinerTip = "Leprosy (Hansen's disease) is highly relevant in India and WBUHS exams. Know the Lepromin test: positive in Tuberculoid (strong CMI), negative in Lepromatous.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "The clinical presentation of Hansen's disease depends on the host's cell-mediated immune response to Mycobacterium leprae.",
            relatedConcept = "Infectious Diseases",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "In chronic hematogenous osteomyelitis, what term refers to the dead bone tissue that detaches and becomes surrounded by newly formed bone?",
            options = listOf("Sequestrum", "Involucrum", "Cloaca", "Osteoid"),
            correctAnswer = "Sequestrum",
            whyCorrect = "The sequestrum is the necrotic fragment of dead bone that detaches from its vascular source during osteomyelitis, harboring bacteria.",
            whyIncorrect = "The involucrum is the surrounding sheath of newly formed reactive bone. The cloaca is the drainage tract in the bone. Osteoid is uncalcified bone matrix.",
            clinicalImportance = "Because sequestrum lacks a blood supply, antibiotics cannot reach it easily, requiring surgical debridement before active physical rehab can succeed.",
            examinerTip = "This is a must-know. Sequestrum = Dead bone; Involucrum = New reactive bone. Very classic orthopaedic pathology!",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Chronic bone infection leads to vascular compromise, producing a sequestrum that prevents complete healing until removed.",
            relatedConcept = "Infectious Diseases",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "What is the earliest histological lesion observed in blood vessels in the pathogenesis of Atherosclerosis?",
            options = listOf("Fatty streak", "Atheromatous plaque", "Fibrous cap", "Calcified plaque"),
            correctAnswer = "Fatty streak",
            whyCorrect = "Fatty streaks are the earliest lesions of atherosclerosis, composed of lipid-laden foam cells beneath the endothelium, and can appear in children.",
            whyIncorrect = "Atheromatous plaques, fibrous caps, and calcified plaques are advanced stages of progressive vascular disease.",
            clinicalImportance = "Atherosclerosis underpins coronary artery disease and stroke, emphasizing early exercise and cardiovascular lifestyle changes.",
            examinerTip = "WBUHS papers often ask if fatty streaks are reversible. Yes, fatty streaks can be fully reversible if risk factors are managed.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Pathogenesis begins with endothelial injury followed by macrophage accumulation of LDL cholesterol, forming 'foam cells'.",
            relatedConcept = "Thrombosis",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which type of blood film morphology is characteristic of severe Iron Deficiency Anemia?",
            options = listOf("Microcytic and hypochromic red blood cells", "Macrocytic and normochromic red blood cells", "Normocytic and normochromic red blood cells", "Spherocytic red blood cells"),
            correctAnswer = "Microcytic and hypochromic red blood cells",
            whyCorrect = "Iron deficiency anemia is characterized by red blood cells that are smaller in size (microcytic, low MCV) and contain less hemoglobin (hypochromic, pale center).",
            whyIncorrect = "Macrocytic cells are seen in B12/folate deficiency. Normocytic cells occur in acute blood loss or chronic kidney disease. Spherocytes are found in hereditary spherocytosis.",
            clinicalImportance = "Anaemic patients exhibit fatigue, palpitations, and poor exercise tolerance, which physical therapists must consider when sizing exercise intensity.",
            examinerTip = "WBUHS exams prioritize basic hematology. Iron deficiency leads to microcytic, hypochromic cells, while folate/B12 deficiency leads to macrocytic cells.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Inadequate iron halts heme synthesis, preventing erythroblasts from maturing with adequate hemoglobin.",
            relatedConcept = "Anemia",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Megaloblastic anemia is characterized by macrocytic erythrocytes and hypersegmented neutrophils, caused by a deficiency in which substrates?",
            options = listOf("Vitamin B12 or Folic acid", "Iron and Vitamin C", "Vitamin K and Calcium", "Erythropoietin and Iron"),
            correctAnswer = "Vitamin B12 or Folic acid",
            whyCorrect = "Vitamin B12 and folate are essential cofactors for DNA synthesis. Deficiency impairs cellular division, giving larger cells with immature nuclei (megaloblasts).",
            whyIncorrect = "Iron deficiency causes microcytic anemia. Vitamin K regulates clotting factor synthesis, not cell marrow replication.",
            clinicalImportance = "Vitamin B12 deficiency also causes Subacute Combined Degeneration (SCD) of the spinal cord, presenting with severe sensory ataxia that BPTs must treat.",
            examinerTip = "Differentiate Megaloblastic anemia (nuclear-cytoplasmic asynchronous maturation due to DNA synthesis defect) from non-megaloblastic macrocytic anemia (liver disease, alcoholism).",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Defective DNA synthesis delays nuclear division while RNA and cytoplasmic growth continue, forming larger cells.",
            relatedConcept = "Anemia",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "The presence of the Philadelphia chromosome t(9;22), creating the BCR-ABL fusion oncogene, is the diagnostic hallmark of which leukemia?",
            options = listOf("Chronic Myeloid Leukemia (CML)", "Acute Lymphoblastic Leukemia (ALL)", "Acute Myeloid Leukemia (AML)", "Chronic Lymphocytic Leukemia (CLL)"),
            correctAnswer = "Chronic Myeloid Leukemia (CML)",
            whyCorrect = "The Philadelphia chromosome, representing t(9;22), is present in more than 95% of patients with CML, producing a constitutively active tyrosine kinase.",
            whyIncorrect = "ALL, AML, and CLL have different translocations or cytogenetic associations.",
            clinicalImportance = "Targeted therapy like Imatinib blocks BCR-ABL activity, transforming CML into a manageable chronic illness with minimal metabolic fatigue.",
            examinerTip = "BCR-ABL = Tyrosine kinase overactivity. This is a classic question in clinical haematology.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "BCR-ABL fusion gene causes uncontrolled myeloproliferation by activating downstream cell-survival pathways.",
            relatedConcept = "Leukemia",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which type of leukemia is the most common pediatric malignancy, characterized by bone pain, cytopenias, and a high proportion of PAS-positive lymphoblasts?",
            options = listOf("Acute Lymphoblastic Leukemia (ALL)", "Chronic Lymphocytic Leukemia (CLL)", "Acute Myeloid Leukemia (AML)", "Chronic Myeloid Leukemia (CML)"),
            correctAnswer = "Acute Lymphoblastic Leukemia (ALL)",
            whyCorrect = "ALL is the most common childhood leukemia. Lymphoblasts show positive periodic acid-Schiff (PAS) staining in a block-like pattern.",
            whyIncorrect = "CLL is an adult disease of mature B-lymphocytes. AML features Auer rods. CML features Philadelphia chromosome.",
            clinicalImportance = "Childhood leukemia survivors undergoing chemotherapy require physical therapy to combat joint pain, muscle weakness, and peripheral neuropathies.",
            examinerTip = "PAS-positive lymphoblasts (no granules) are ALL. MPO-positive blasts with Auer rods are AML.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "ALL represents rapid monoclonal expansion of immature lymphoid progenitor cells, crowding out normal marrow.",
            relatedConcept = "Leukemia",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Hemophilia A is an X-linked recessive bleeding disorder characterized by a deficiency in which coagulation factor?",
            options = listOf("Factor VIII", "Factor IX", "Factor VII", "Factor XI"),
            correctAnswer = "Factor VIII",
            whyCorrect = "Hemophilia A is caused by congenital deficiency of Factor VIII. Hemophilia B (Christmas disease) is caused by Factor IX deficiency.",
            whyIncorrect = "Factor VII is evaluated in PT/extrinsic pathway. Factor XI deficiency is Hemophilia C.",
            clinicalImportance = "Severe hemophiliacs present with recurrent hemarthroses (bleeding into joints, often the knee), causing chronic synovitis and joint contractures requiring specialized physical rehab.",
            examinerTip = "Remember the inheritance: X-linked recessive means it primarily affects males while females are asymptomatic carriers.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Factor VIII serves as an essential cofactor for Factor IXa in activating Factor X in the intrinsic pathway.",
            relatedConcept = "Infectious Diseases",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What amino acid substitution occurs in the beta-globin chain of hemoglobin to produce protective or pathologic sickle cell disease (HbS)?",
            options = listOf("Glutamic acid replaced by Valine at position 6", "Valine replaced by Glutamic acid at position 6", "Lysine replaced by Glutamic acid at position 6", "Glutamic acid replaced by Lysine at position 6"),
            correctAnswer = "Glutamic acid replaced by Valine at position 6",
            whyCorrect = "Sickle hemoglobin (HbS) is generated by a single point mutation where hydrophilic glutamic acid is substituted by hydrophobic valine at position 6 of the beta-globin chain.",
            whyIncorrect = "Other substitutions do not generate HbS. Glutamic acid to Lysine generates HbC.",
            clinicalImportance = "Deoxygenated HbS polymerizes, causing sickling of RBCs, leading to microvascular occlusions (vaso-occlusive pain crises) which present as sudden bone and joint pain.",
            examinerTip = "WBUHS papers ask about the exact mutation. Acid-to-Valine is the classic point mutation, resulting in sickle shape.",
            subject = "Pathology",
            difficulty = "Hard",
            learningPoint = "Polymerization of sickle hemoglobin is promoted by hypoxia, dehydration, and acidosis.",
            relatedConcept = "Anemia",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "The deposition of which crystals in joints is responsible for the acute inflammatory arthritis known as Gout?",
            options = listOf("Monosodium urate crystals", "Calcium pyrophosphate dihydrate (CPPD) crystals", "Calcium hydroxyapatite crystals", "Cholesterol crystals"),
            correctAnswer = "Monosodium urate crystals",
            whyCorrect = "Gout is caused by tissue accumulation of monosodium urate (MSU) crystals in hyperuricemic patients, presenting with needle-shaped negatively birefringent crystals.",
            whyIncorrect = "CPPD crystals cause pseudogout (rhomboid-shaped, weak positive birefringence). Calcium hydroxyapatite is seen in calcific tendinitis.",
            clinicalImportance = "Acute gout classically strikes the first metatarsophalangeal joint (podagra), presenting with extreme pain, redness, and swelling, which is a contraindication for local joint mobilization.",
            examinerTip = "Gout vs Pseudogout is a prized viva topic! Under polarized light: Gout has needle-shaped, negatively birefringent MSU; Pseudogout has rhomboid-shaped, positively birefringent CPPD.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "MSU crystals precipitate when serum uric acid levels exceed biochemical solubility limits (approx 6.8 mg/dL).",
            relatedConcept = "Rheumatoid & Joint Pathology",
            reference = "Harsh Mohan Textbook of Pathology, 8th Ed"
        ),
        VivaMcq(
            question = "Which morphological feature is the primary pathological event occurring in the synovium in Rheumatoid Arthritis?",
            options = listOf("Chronic proliferative synovitis with pannus formation", "Lytic devastation of subchondral bone by bacterial emboli", "Mechanical wear-and-tear narrowing of joint space without inflammation", "Massive cartilage swelling due to fluid retention"),
            correctAnswer = "Chronic proliferative synovitis with pannus formation",
            whyCorrect = "Rheumatoid Arthritis is an autoimmune disease characterized by chronic proliferative synovitis, producing a 'pannus' (granulation-like tissue rich in inflammatory cells) that erodes articular cartilage and bone.",
            whyIncorrect = "Osteoarthritis is characterized by mechanical wear-and-tear narrowing of joint space. Septic arthritis features pyogenic bacterial bone destruction. Fluid swelling alone does not define RA.",
            clinicalImportance = "Pannus leads to joint deformities (e.g., ulnar drift, swan-neck deformity), requiring continuous joint preservation physical therapy and gentle range of motion exercises.",
            examinerTip = "What is a pannus? The examiner will expect you to define it: a membrane of granulation tissue consisting of inflammatory cells, fibroblasts, and capillaries.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Autoantibodies (Rheumatoid Factor against IgG Fc portion, and Anti-CCP) trigger chronic T-cell mediated synovial inflammation.",
            relatedConcept = "Rheumatoid & Joint Pathology",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "Which of the following parameters represents the standard operating conditions for autoclaving (moist heat sterilization) to ensure destruction of highly resistant bacterial endospores?",
            options = listOf("121°C at 15 psi for 15 minutes", "100°C at 10 psi for 30 minutes", "134°C at 30 psi for 3 minutes", "115°C at 10 psi for 20 minutes"),
            correctAnswer = "121°C at 15 psi for 15 minutes",
            whyCorrect = "Autoclaving uses saturated steam under pressure. Standard conditions of 121°C at 15 lb/sq inch (psi) pressure for 15 to 20 minutes are universally validated to denature essential proteins and sterilize materials by killing all vegetative forms and endospores.",
            whyIncorrect = "100°C for 30 minutes is boiling and does not reliably destroy all spores. 134°C for 3 minutes is flash autoclaving, which requires specific pre-vacuum equipment and is not the standard protocol. 115°C is insufficient for absolute sterilization of spore-bearing items.",
            clinicalImportance = "In physical therapy, ensuring that hot pack covers, reusable tools, or dry needles are sterile is vital to prevent outbreak transmission. Dry needles must be single-use sterile.",
            examinerTip = "Examiners frequently ask for the biological indicator of autoclaving: it is Geobacillus stearothermophilus.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Sterilization is the absolute elimination of all microbial life forms, including spores, whereas disinfection only reduces pathogens to safe levels.",
            relatedConcept = "Moist Heat Sterilization",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "A physical therapy clinic is sterilizing reusable glass jars and metallic instruments that cannot tolerate moisture. Which of the following parameters is the standard cycle for sterilization in a Hot Air Oven?",
            options = listOf("160°C for 2 hours", "100°C for 1 hour", "121°C for 15 minutes", "180°C for 10 minutes"),
            correctAnswer = "160°C for 2 hours",
            whyCorrect = "Hot Air Oven utilizes dry heat, which sterilizes by oxidative damage, protein denaturation, and toxic effects of elevated electrolytes. The standard recommended holding temperature and time sequence is 160°C for 2 hours.",
            whyIncorrect = "100°C for 1 hour is insufficient to kill bacterial endospores under dry conditions. 121°C for 15 minutes is the parameter for moist heat, not dry heat. 180°C for 10 minutes is too short to ensure complete dry-heat sterilization.",
            clinicalImportance = "Glass cups used in vacuum cupping therapy and stainless steel physical therapy tools are best sterilized using dry heat to avoid corrosion and water spotting.",
            examinerTip = "Keep in mind that Bacillus atrophaeus is used as the biological indicator for dry heat sterilizers (Hot Air Ovens).",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Dry heat requires longer exposure times and higher temperatures than moist heat because proteins do not denature as easily in dry conditions.",
            relatedConcept = "Dry Heat Sterilization",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Disposable plastic syringes, catheters, and adhesive physical therapy tapes are commercially sterilized using which of the following non-thermal methods?",
            options = listOf("Gamma Radiation", "Autoclaving", "Hot Air Oven", "Glutaraldehyde (2%) immersion"),
            correctAnswer = "Gamma Radiation",
            whyCorrect = "Ionizing radiation, specifically gamma rays from Cobalt-60 sources, has extremely high penetrating power and damages microbial DNA. It is a form of 'cold sterilization' widely used for heat-sensitive commercial plastic supplies.",
            whyIncorrect = "Autoclaving and hot air ovens involve heat, which will melt disposable plastic items. Liquid glutaraldehyde immersion is used for high-level disinfection of endoscopes but is not practical for sterile commercial packaging.",
            clinicalImportance = "Physiotherapists utilize various pre-packaged sterile items (like wound dressing pads, surgical tape, and needles). Understanding that these can only be used once, and their sterility is maintained unless the package is breached, prevents cross-infection.",
            examinerTip = "Remember that the biological indicator utilized to monitor the efficacy of ionizing radiation is Bacillus pumilus.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Ionizing radiation (Gamma rays, X-rays) sterilizes, whereas non-ionizing radiation (UV light) is used only for air and surface disinfection due to its poor penetration.",
            relatedConcept = "Cold Sterilization & Radiation",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Which gaseous sterilizing agent is widely utilized in healthcare facilities for high-level sterilization of moisture-sensitive and heat-sensitive biomedical equipment, but requires post-exposure aeration due to its toxic and carcinogenic nature?",
            options = listOf("Ethylene Oxide (ETO)", "Formaldehyde gas", "Glutaraldehyde vapor", "Chlorine dioxide"),
            correctAnswer = "Ethylene Oxide (ETO)",
            whyCorrect = "Ethylene oxide (ETO) is an alkylating agent that chemically reacts with nucleic acids and proteins of microbes, rendering them inactive. It is ideal for heat-sensitive and moisture-sensitive components.",
            whyIncorrect = "Formaldehyde gas is used primarily for room fumigation and is less penetrating. Glutaraldehyde vapor is not commonly used in structured closed sterilizing cycles. Chlorine dioxide is used for water treatment and surface disinfection.",
            clinicalImportance = "Advanced cardiopulmonary physiotherapists working in ICUs must handle nebulizers, incentive spirometers, and ventilator circuits that are sterilized under specialized hospital protocols.",
            examinerTip = "The biological indicator for ethylene oxide sterilization is Bacillus atrophaeus.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "ETO alkylates amino, carboxyl, hydroxyl, and sulfhydryl groups in proteins, disrupting normal cellular metabolism.",
            relatedConcept = "Gaseous Sterilization",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "What chemical agent is commonly used in a 2% buffered solution ('Cidex') for the high-level disinfection of semi-critical medical instruments like endoscopes, respiratory therapy equipment, and spirometer mouthpieces, requiring a 20-minute immersion?",
            options = listOf("Glutaraldehyde", "Isopropyl alcohol", "Sodium hypochlorite", "Chlorhexidine gluconate"),
            correctAnswer = "Glutaraldehyde",
            whyCorrect = "2% Glutaraldehyde is an excellent high-level disinfectant (HLD). It is sporicidal with prolonged exposure (10 hours) and kills vegetative bacteria, fungi, and viruses in 20 minutes by alkylating sulfhydryl, hydroxyl, carboxyl, and amino groups.",
            whyIncorrect = "Isopropyl alcohol (70%) is a low-to-intermediate level disinfectant and is not sporicidal. Sodium hypochlorite is used mainly for surface blood spills. Chlorhexidine is an antiseptic, not an instrument disinfectant.",
            clinicalImportance = "Reusable spirometry tubes and respiratory valves used in chest physiotherapy must undergo proper HLD with 2% glutaraldehyde to protect immunocompromised patients.",
            examinerTip = "Note that glutaraldehyde is preferred over formaldehyde because it is less irritating to mucous membranes and has a much higher microbicidal potency.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Semi-critical items (those coming into contact with mucous membranes or non-intact skin) require high-level disinfection at a minimum.",
            relatedConcept = "High-Level Disinfectants",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Alcohols (specifically 70% ethyl alcohol or 70% isopropyl alcohol) are widely used in physical therapy clinics to clean skin before dry needling or to wipe treatment tables. Why is 70% alcohol more microbicidal than 100% absolute alcohol?",
            options = listOf("Water is required to facilitate diffusion and prevent rapid denaturation of outer membrane proteins before penetration", "100% absolute alcohol evaporates before it can contact microbial organisms", "Water increases the chemical stability of the alcohol molecule", "Absolute alcohol promotes rapid sporulation of bacteria"),
            correctAnswer = "Water is required to facilitate diffusion and prevent rapid denaturation of outer membrane proteins before penetration",
            whyCorrect = "Water acts as a catalyst and is essential for the denaturation of proteins. In 100% absolute alcohol, rapid protein denaturation at the outer cell wall forms a protective barrier that prevents further penetration of the alcohol. 70% water-alcohol mixture penetrates deep into the cytoplasm.",
            whyIncorrect = "Evaporation rate is a minor factor but not the biochemical reason. Water doesn't stabilize the molecule itself. Absolute alcohol does not induce sporulation (spore formation is a complex biological starvation response).",
            clinicalImportance = "Always use 60% to 80% alcohol rubs or wipes, never absolute alcohol, for disinfecting skin or physical therapy equipment like ultrasound transducers.",
            examinerTip = "Remember that alcohol is ineffective against bacterial spores and non-enveloped viruses.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "The optimal bacterial killing concentration of isopropyl and ethyl alcohol is 60%-80% by volume.",
            relatedConcept = "Mechanism of Alcohol Disinfection",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Which of the following substances represents an antiseptic rather than a disinfectant, meaning it is formulated to safely reduce microbial flora on living tissues like skin and wounds without causing tissue necrosis?",
            options = listOf("Chlorhexidine Gluconate", "Sodium Hypochlorite (0.5%)", "Glutaraldehyde (2%)", "Phenol (5%)"),
            correctAnswer = "Chlorhexidine Gluconate",
            whyCorrect = "Antiseptics are antimicrobial substances applied to living tissue/skin to reduce the possibility of infection. Chlorhexidine gluconate is a highly effective, persistent antiseptic used for surgical hand scrubs and skin preparation.",
            whyIncorrect = "Sodium hypochlorite (bleach), 2% glutaraldehyde, and 5% phenol are cytotoxic and highly irritating, making them suitable only for non-living surfaces and equipment (disinfectants).",
            clinicalImportance = "For wound care management (such as diabetic ulcers or venous stasis wounds treated in physical therapy), using physiological saline or gentle antiseptics like chlorhexidine is preferred over harsh disinfectants to avoid killing vital granulation tissue.",
            examinerTip = "Phenol coefficient test is used to compare the disinfecting efficacy of a chemical agent with that of pure phenol.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Antiseptics must be non-toxic to eukaryotic cells while remaining cytotoxic to prokaryotic cells.",
            relatedConcept = "Antisepsis & Chemical Disinfection",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "During a physical therapy session, an open surgical wound of a patient accidentally bleeds onto the gymnastics mat. According to standard hospital protocols, which disinfectant should be applied to manage the blood spill?",
            options = listOf("10% Sodium Hypochlorite (diluted 1:10 bleach)", "70% Isopropyl alcohol", "Benzalkonium chloride (quaternary ammonium compound)", "0.5% Chlorhexidine gluconate"),
            correctAnswer = "10% Sodium Hypochlorite (diluted 1:10 bleach)",
            whyCorrect = "Sodium hypochlorite diluted to a 1:10 concentration (approximately 5000 ppm chlorine) is the gold standard recommended by the CDC/WHO for cleaning blood spills and organic body fluids, as it kills bloodborne pathogens including HIV and Hepatitis B rapidly.",
            whyIncorrect = "Isopropyl alcohol can coagulate proteins and is less effective in the presence of massive organic loading (blood). Quaternary ammonium compounds are low-level disinfectants. Chlorhexidine is reserved for topical skin prep.",
            clinicalImportance = "Safe body fluid spill clean-up is a core competency for physical therapists working in acute rehab settings, preventing hepatitis and HIV transmission to other patients and staff.",
            examinerTip = "Chlorine-releasing agents like sodium hypochlorite act by releasing hypochlorous acid, which inactivates cell metabolism and denatures essential proteins.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Organic matter (such as blood or pus) acts as a physical barrier and chemically inactivates many disinfectants. Hypochlorite remains highly active even in such conditions.",
            relatedConcept = "Decontamination of Spills",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "In the Gram-staining technique, what structural component of the bacterial cell wall determines the retention of the primary crystal violet-iodine complex, preventing it from being decolorized by alcohol?",
            options = listOf("A thick layer of peptidoglycan in Gram-positive bacteria", "The presence of an outer lipopolysaccharide membrane in Gram-negative bacteria", "A high concentration of mycolic acid in the envelope", "The presence of calcium dipicolinate in the cytoplasm"),
            correctAnswer = "A thick layer of peptidoglycan in Gram-positive bacteria",
            whyCorrect = "Gram-positive bacteria possess a thick, highly cross-linked peptidoglycan layer (20-80 nm) which shrinks and dehydrates upon alcohol exposure. This closes the cell wall pores, trapping the large crystal violet-iodine complex.",
            whyIncorrect = "Gram-negative cells have an outer membrane that dissolved by alcohol, allowing the primary stain to wash away. Mycolic acid is for acid-fast bacteria. Calcium dipicolinate is located in endospores.",
            clinicalImportance = "Identifying whether a wound pathogen is Gram-positive or Gram-negative is priority #1 when selecting local antimicrobial therapies and modifying wound dressing regimes in physical therapy.",
            examinerTip = "Memorize the sequence of reagents in Gram stain: Crystal Violet (Primary), Iodine (Mordant), Alcohol/Acetone (Decolorizer), Safranin (Counterstain).",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "The Gram-reaction is fundamentally a reflection of cell wall architecture (specifically peptidoglycan layer thickness).",
            relatedConcept = "Gram Stain Chemistry",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Which of the following chemical components, found in high concentrations within the core of bacterial endospores, is primarily responsible for their extreme heat resistance, dehydration, and stability?",
            options = listOf("Calcium dipicolinate", "Teichoic acid", "Lipid A (endotoxin)", "Peptidoglycan monomer"),
            correctAnswer = "Calcium dipicolinate",
            whyCorrect = "The core of bacterial endospores contains a high concentration of dipicolinic acid complexed with calcium (calcium dipicolinate). This complex binds free water molecules, inducing extreme dehydration which preserves proteins and DNA.",
            whyIncorrect = "Teichoic acid is found exclusively in the cell walls of vegetative Gram-positive bacteria. Lipid A is the lipid component of LPS in Gram-negative cell walls. Peptidoglycan is a cell wall structural molecule, not a spore core protectant.",
            clinicalImportance = "Endospore-producing bacteria include Clostridium tetani (causative agent of tetanus) and Clostridium perfringens (gas gangrene). Patients with open traumatic wounds must have verified tetanus toxoid immunization status.",
            examinerTip = "Name clinical spore-forming genera: Bacillus (aerobic) and Clostridium (anaerobic). This is a top-tier viva question!",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Endospores are dormant, highly resistant structures formed by bacteria during environmental stress, not reproductive elements.",
            relatedConcept = "Spore Structure",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Sepsis and septic shock are severe complications of bacterial infections. What specific portion of the lipopolysaccharide (LPS) complex in Gram-negative bacterial cell walls acts as the toxic moiety (endotoxin) responsible for triggering systemic inflammatory response syndrome (SIRS)?",
            options = listOf("Lipid A", "O-antigen (somatic side chain)", "Core polysaccharide", "Teichoic acid"),
            correctAnswer = "Lipid A",
            whyCorrect = "Lipopolysaccharide (LPS) consists of three parts. Of these, Lipid A is the toxic component. It is released upon cell lysis and potently stimulates macrophages to release pro-inflammatory cytokines, inducing fever, vasodilation, and potentially fatal septic shock.",
            whyIncorrect = "O-antigen is the highly variable hydrophilic side chain used for serotyping. Core polysaccharide structural chain links O-antigen to Lipid A. Teichoic acid is present in Gram-positives, not Gram-negatives.",
            clinicalImportance = "Physically mobilizing patients recovering from Gram-negative sepsis requires constant hemodynamic monitoring. Quick rises in temperature or sudden drops in blood pressure may indicate endotoxin release.",
            examinerTip = "Remember that endotoxins are heat-stable and chromosome-encoded, whereas exotoxins are proteins (highly antigenic and heat-labile).",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "In Gram-negative septicemia, early administration of appropriate antibiotics can sometimes trigger a transient worsening of symptoms due to massive bacterial lysis and endotoxin release.",
            relatedConcept = "Endotoxin Pathogenesis",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Penicillins, cephalosporins, and other beta-lactam antibiotics exert their bactericidal activity by targeting which of the following processes in bacteria?",
            options = listOf("Cross-linking of peptidoglycan chains by transpeptidase enzymes", "Protein synthesis at the 30S ribosomal subunit", "DNA replication by DNA gyrase inhibition", "Synthesis of tetrahydrofolic acid via dihydropteroate synthase"),
            correctAnswer = "Cross-linking of peptidoglycan chains by transpeptidase enzymes",
            whyCorrect = "Beta-lactam antibiotics bind to and inhibit penicillin-binding proteins (PBPs), which are transpeptidase enzymes responsible for cross-linking the glycan strands in peptidoglycan synthesis. This weakens the cell wall, causing osmotic lysis.",
            whyIncorrect = "Aminoglycosides and Tetracyclines target the 30S ribosomal subunit. Fluoroquinolones inhibit DNA gyrase. Sulfonamides target folate synthesis.",
            clinicalImportance = "Physical therapists working with wound infections should be aware of antibiotic mechanisms, especially in cases of antibiotic resistance (such as MRSA), as treatment success relies on both pharmaceutical control and wound debridement.",
            examinerTip = "The beta-lactam ring is a four-membered ring that mimics the D-alanyl-D-alanine portion of the peptidoglycan precursor.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Cell-wall active agents (like beta-lactams and glycopeptides) are only active and bactericidal against actively growing and dividing bacterial cells.",
            relatedConcept = "Beta-lactam Antibiotics",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Unlike non-enveloped (naked) viruses, enveloped viruses acquire their lipid membrane outer layer from which cellular source during replication, and what physical vulnerability does this lipid envelope present?",
            options = listOf("Acquired from the host cell membrane during budding; makes them sensitive to lipid solvents and detergents", "Synthesized de novo by viral ribosomal enzymes; makes them highly resistant to environmental dry conditions", "Acquired from bacterial host cell walls; makes them sensitive to penicillin", "Acquired from host lysosomes; makes them vulnerable to acidic environments"),
            correctAnswer = "Acquired from the host cell membrane during budding; makes them sensitive to lipid solvents and detergents",
            whyCorrect = "Enveloped viruses obtain their envelope by 'budding' through host cell membranes. Because this envelope is composed of host lipids and embedded viral glycoproteins, it is easily disrupted by lipid solvents, alcohols, heat, and detergents, rendering the virus non-infectious.",
            whyIncorrect = "Viral membranes are host-derived, not synthesized de novo. Penicillin targets peptidoglycan and has zero effect on viral envelopes. They are generally much more environmentally sensitive than non-enveloped viruses.",
            clinicalImportance = "Enveloped viruses include SARS-CoV-2, HIV, and Influenza. Because they are sensitive to detergents and alcohols, standard clinical hand rubs (60%+ alcohol) and routine handwashing with soap are highly effective.",
            examinerTip = "Remember: naked capsid viruses (e.g., Poliovirus, Hepatitis A) are resistant to acid, detergents, and drying, which allows them to survive the gastrointestinal tract.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "The viral envelope contains receptor-binding proteins; damaging the lipid envelope destroys the virus's ability to infect host cells.",
            relatedConcept = "Enveloped vs. Non-Enveloped Viruses",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Retro-viruses like Human Immunodeficiency Virus (HIV) carry a unique, highly targeted enzyme in their capsid that converts their single-stranded RNA genome into double-stranded DNA inside the host cell cytoplasm. What is this enzyme?",
            options = listOf("Reverse transcriptase", "RNA-dependent RNA polymerase", "DNA-dependent DNA polymerase", "Integrase"),
            correctAnswer = "Reverse transcriptase",
            whyCorrect = "Reverse transcriptase is an RNA-dependent DNA polymerase that uses the viral single-stranded RNA genome to synthesize a complementary DNA strand, which is then replicated to form double-stranded viral DNA.",
            whyIncorrect = "RNA-dependent RNA polymerase is used by standard RNA viruses to replicate RNA from RNA. DNA-dependent DNA polymerase is standard host/viral DNA replication machinery. Integrase handles the insertion step of viral DNA.",
            clinicalImportance = "Nucleoside reverse transcriptase inhibitors (NRTIs) like Zidovudine (AZT) are essential pillars of antiretroviral therapy (ART) that limit HIV progression in patients receiving physical therapy.",
            examinerTip = "Examiners like to ask if RT is a host enzyme: No, it is a viral enzyme carried inside the virion, as human cells do not possess endogenous reverse transcription machinery.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Reverse transcription violates the traditional central dogma of molecular biology (DNA -> RNA -> Protein) by operating in the reverse direction (RNA -> DNA).",
            relatedConcept = "Retroviral Replication",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Seasonal outbreaks of influenza are driven by gradual point mutations in viral glycoprotein genes, whereas occasional catastrophic pandemics are caused by sudden genetic reassortment of viral segments when multiple strains infect a single host. What terms define these two mechanisms?",
            options = listOf("Antigenic drift (mutations) and Antigenic shift (reassortment)", "Antigenic shift (mutations) and Antigenic drift (reassortment)", "Phenotypic mixing and Phenotypic conversion", "Lysogeny and Lytic cycle"),
            correctAnswer = "Antigenic drift (mutations) and Antigenic shift (reassortment)",
            whyCorrect = "Antigenic drift refers to minor antigenic changes resulting from random point mutations in the hemagglutinin (HA) or neuraminidase (NA) genes during viral replication. Antigenic shift refers to major antigenic changes resulting from a sudden reassortment of RNA segments between different strains in a co-infected cell.",
            whyIncorrect = "The terms are switched in options. Phenotypic mixing is transient non-genetic co-packaging. Lysogeny is a bacteriophage state of chromosome integration.",
            clinicalImportance = "For physiotherapists working on chest health in hospitals, knowing how influenza evolves is critical. It reinforces why hospital staff must receive annual influenza vaccinations and wear appropriate PPE.",
            examinerTip = "The segmented genome of Influenza virus (8 segments in Influenza A and B) is the specific prerequisite structural feature that enables antigenic shift.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Only Influenza A undergoes antigenic shift because it infects humans and animals (birds, pigs); Influenza B is primarily human and only undergoes drift.",
            relatedConcept = "Influenza Variation",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Which of the following human viruses is definitively associated with the development of cervical carcinoma, as well as skin warts, and primarily acts by inactivating cellular tumor suppressor proteins p53 and Rb?",
            options = listOf("Human Papillomavirus (HPV) genotypes 16 and 18", "Epstein-Barr Virus (EBV)", "Hepatitis A Virus (HAV)", "Cytomegalovirus (CMV)"),
            correctAnswer = "Human Papillomavirus (HPV) genotypes 16 and 18",
            whyCorrect = "High-risk HPV genotypes express E6 and E7 oncoproteins. E6 binds and promotes degradation of the p53 tumor suppressor protein, while E7 binds and inactivates the retinoblastoma (Rb) tumor suppressor protein, leading to uncontrolled cell proliferation.",
            whyIncorrect = "EBV causes Burkitt lymphoma, not cervical cancer. Hepatitis A causes acute self-limiting hepatitis. CMV is a herpesvirus that does not cause cervical cancer.",
            clinicalImportance = "Public health awareness is within the scope of physical therapy education. Knowing that HPV vaccination prevents these oncogenic changes helps clinicians advocate for adolescent immunization programs.",
            examinerTip = "HPV types 6 and 11 are low-risk and cause benign condylomata acuminata (genital warts), not malignancy.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Viral oncogenesis involves the subversion of cellular checkpoints governing cell cycle progression and DNA damage repair.",
            relatedConcept = "Viral Oncogenesis & HPV",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Dimorphism is a unique virulence trait in medical mycology. How do dimorphic fungi modify their morphological structure depending on environmental temperature parameters?",
            options = listOf("Exist as multicellular molds/mycelia at ambient room temperature (25°C) and transition to unicellular yeasts at physiological body temperature (37°C)", "Exist as single-celled yeasts at 25°C and transition to multicellular hyphae at 37°C", "Exist as flagellated cells in water and dormant cysts in terrestrial soil tissue", "Synthesize peptidoglycan walls at 25°C and modify to lipopolysaccharide outer envelopes at 37°C"),
            correctAnswer = "Exist as multicellular molds/mycelia at ambient room temperature (25°C) and transition to unicellular yeasts at physiological body temperature (37°C)",
            whyCorrect = "Dimorphic fungi are thermal dimorphs. They grow as saprophytic molds with mycelia/hyphae in the environment and on culture media at 25°C ('mold in soil'), but transition to yeast phase inside host tissues incubated at 37°C ('yeast in beast').",
            whyIncorrect = "The temperatures are reversed in the second option. Fungi do not form flagellated water stages or possess bacterial wall components like peptidoglycan or lipopolysaccharide.",
            clinicalImportance = "Understanding dimorphism is crucial. For example, Sporothrix schenckii causes sporotrichosis ('rose gardener's disease') which manifests as chronic subcutaneous nodules along lymphatic lines in workers who may seek physical therapy for joint pain.",
            examinerTip = "A helpful mnemonic is: 'Mold in the Cold (25°C), Yeast in the Heat (37°C)'. It's a gold-standard viva response!",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Thermal dimorphism is an adaptation that allows fungi to adjust metabolic activities to survive host immune defenses during infection.",
            relatedConcept = "Fungal Dimorphism",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Dermatophytes cause superficial cutaneous infections commonly referred to as 'tinea' or 'ringworm'. What specific protein substrate do these fungal pathogens use as a nutrient source, explaining their restriction to dead outermost tissue planes?",
            options = listOf("Keratin", "Melanin", "Collagen", "Glycogen"),
            correctAnswer = "Keratin",
            whyCorrect = "Dermatophytes produce a specialized enzyme, keratinase, which hydrolyzes keratin. Consequently, they infect only keratinized structures, such as the stratum corneum of the epidermis, hair shafts, and nails. They cannot invade deeper living layers because they are inhibited by serum transferrin.",
            whyIncorrect = "Melanin is skin pigment. Collagen is in dermis and tendons. Glycogen is an intracellular carbohydrate storage molecule. None of these provide the specific metabolic niche of keratin.",
            clinicalImportance = "Physical therapists working in sports clinics often manage athletes who develop tinea pedis ('athlete's foot'). Sharing treatment spaces and moist changing rooms promotes transmission.",
            examinerTip = "Dermatophytes are diagnosed in clinic using a 10% KOH (Potassium Hydroxide) wet mount preparation of skin/nail scrapings to observe septate hyphae.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Dermatophytic lesions are highly pruritic (itchy) due to delayed hypersensitivity reactions against fungal metabolic active elements.",
            relatedConcept = "Superficial Mycoses",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "A patient undergoing prolonged intravenous antibiotic therapy in the ICU develops high fever and a thick, curd-like white pseudomembranous coating on the oral mucosa ('thrush') that bleeds when scraped. Which opportunist is the causative agent, and what morphology diagnostic test is characteristic?",
            options = listOf("Candida albicans; budding yeast cells with pseudohyphae forming germ tubes in serum", "Aspergillus fumigatus; dichotomously branching acute-angle septate hyphae", "Cryptococcus neoformans; heavily encapsulated globose yeasts showing narrow-necked budding", "Pneumocystis jirovecii; collapsed cup-shaped cysts highlighted on silver stain"),
            correctAnswer = "Candida albicans; budding yeast cells with pseudohyphae forming germ tubes in serum",
            whyCorrect = "Candida albicans is a normal commensal flora. Broad-spectrum antibiotics wipe out competing bacteria, leading to overgrowth. When inoculated at 37°C in serum for 2-3 hours, it extends a characteristic parallel-walled filament without constriction called a 'germ tube'.",
            whyIncorrect = "Aspergillus has broad branching hyphae, yielding pulmonary aspergillosis, not typical white oral thrush. Cryptococcus is encapsulated and identified with India Ink. Pneumocystis causes specific interstitial pneumonia in immunocompromised patients.",
            clinicalImportance = "Outpatients presenting with severe neck stiffness and general pain may be taking long-term corticosteroids. In such cases, checking the mouth for white plaques can help identify clinical systemic immunosuppression.",
            examinerTip = "Candidiasis is dimorphic but in a reverse sense: it forms yeasts in normal flora and tissue-invasive pseudohyphae when pathologically active.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Candida overgrowth occurs when immunocompromising conditions, diabetes, steroids, or broad-spectrum antibiotics disrupt normal microflora homeostasis.",
            relatedConcept = "Candida & Germ Tube Test",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Which neurotropic yeast is characterized by a thick, slimy mucopolysaccharide capsule that repels carbon suspension particles, allowing its rapid visualization in cerebrospinal fluid (CSF) via an India Ink preparation as an diagnostic marker for meningitis?",
            options = listOf("Cryptococcus neoformans", "Histoplasma capsulatum", "Candida tropicalis", "Sporothrix schenckii"),
            correctAnswer = "Cryptococcus neoformans",
            whyCorrect = "Cryptococcus neoformans is an encapsulated, opportunistic yeast. When CSF contains Cryptococcus, the large mucopolysaccharide capsules exclude the carbon particles in India ink, appearing as a clear halo around the budding yeast cells against a dark background.",
            whyIncorrect = "Histoplasma capsulatum has no capsule, despite the misleading species name. Candida and Sporothrix do not possess this highly diagnostic, massive mucopolysaccharide capsule.",
            clinicalImportance = "Cryptococcal meningitis is highly prevalent among patients with advanced HIV. Recognizing intracranial pressure signs (severe headache, photophobia, positive Kernig's/Brudzinski's signs) is critical for physical therapists.",
            examinerTip = "Mucicarmine stain stains the capsule of Cryptococcus a bright carmine red in histopathological tissue preparations.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Cryptococcal capsule inhibits phagocytosis. Cryptococcal antigen (CrAg) detection via latex agglutination or lateral flow assay of CSF/blood has more than 99% sensitivity.",
            relatedConcept = "Cryptococcal Diagnoses",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Which species of Plasmodium is responsible for the most severe, life-threatening form of malaria ('malignant tertian malaria'), and what microvascular feature makes its RBC parasitemia exceptionally dangerous to cerebral microcirculation?",
            options = listOf("Plasmodium falciparum; expression of PfEMP-1 on infected RBCs causing sequestration and microvascular obstruction", "Plasmodium vivax; persistent hypnozoites in hepatocytes causing late relapses", "Plasmodium malariae; slow replication causing quartan malaria and immune complexes", "Plasmodium ovale; selective invasion of young reticulocytes causing mild anemia"),
            correctAnswer = "Plasmodium falciparum; expression of PfEMP-1 on infected RBCs causing sequestration and microvascular obstruction",
            whyCorrect = "Plasmodium falciparum expresses Plasmodium falciparum erythrocyte membrane protein 1 (PfEMP-1) on the outer surface of infected RBCs. This protein causes the RBCs to adhere to endothelial cells in capillaries of vital organs (sequestration), blocking blood flow and leading to cerebral malaria.",
            whyIncorrect = "Plasmodium vivax and Plasmodium ovale cause benign tertian malaria with dormant hypnozoites in liver tissues. Plasmodium malariae is mild and causes quartan malaria (72-hour cycle) without vascular sequestration.",
            clinicalImportance = "Physiotherapists should immediately halt active physical therapies in patients with sudden, unexplained fever spikes who have traveled to malaria-endemic areas.",
            examinerTip = "Mention the characteristic ring-form trophozoites and crescent-shaped (banana-shaped) gametocytes of Plasmodium falciparum on a peripheral blood smear.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "P. falciparum is unique because it invades erythrocytes of all ages, leading to high parasitemia levels, unlike P. vivax which selectively targets reticulocytes.",
            relatedConcept = "Malaria Pathogenesis",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Which enteric protozoan tissue parasite is characterized by trophozoites showing active pseudopodial motility with ingested RBCs (erythrophagocytosis), and produces deep 'flask-shaped ulcers' in the large intestine mucosa?",
            options = listOf("Entamoeba histolytica", "Giardia lamblia", "Balantidium coli", "Cryptosporidium parvum"),
            correctAnswer = "Entamoeba histolytica",
            whyCorrect = "Entamoeba histolytica causes amebiasis and amebic dysentery. Following ingestion of mature quadrinucleate cysts, excystation yields invasive trophozoites which produce pore-forming proteins and cysteine proteases that create classic flask-shaped mucosal ulcers.",
            whyIncorrect = "Giardia causes non-invasive malabsorptive diarrhea and has a ventral sucking disk. Balantidium is a ciliated giant protozoan. Cryptosporidium causes secretory watery diarrhea.",
            clinicalImportance = "Patients recovering from amebic liver abscess surgery may undergo chest physiotherapy. High-risk patients should be closely monitored for referred right shoulder pain, which can indicate diaphragmatic irritation.",
            examinerTip = "In laboratory stool exams, ensure you differentiate the active vegetative trophozoite (invasive, has ingested red blood cells) from the dormant cyst stage.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "The presence of ingested red cells in the cytoplasm of E. histolytica trophozoites is the primary feature that distinguishes it from non-pathogenic Entamoeba coli.",
            relatedConcept = "Amebiasis Pathology",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "A hiker returns with persistent abdominal bloating, foul-smelling, greasy, floating fatty stools, and flatulence after drinking mountain stream water. Which pear-shaped, binucleated protozoan parasite is the culprit, and what is its non-invasive mechanism of diarrhea?",
            options = listOf("Giardia lamblia; mechanical barrier covering mucosal microvilli with a ventral sucking disk, causing fat malabsorption", "Entamoeba histolytica; toxin-mediated cell lysis of colonic epithelium", "Cryptosporidium parvum; intracellular replication disrupting enterocytes", "Trichomonas vaginalis; hydrogenosome enzyme-mediated tissue necrosis"),
            correctAnswer = "Giardia lamblia; mechanical barrier covering mucosal microvilli with a ventral sucking disk, causing fat malabsorption",
            whyCorrect = "Giardia lamblia trophozoites are pear-shaped flagellates with two nuclei and a flat ventral sucking disk. They attach to the duodenal and jejunal mucosa, acting as a mechanical barrier that prevents fat and fat-soluble vitamin absorption, leading to steatorrhea.",
            whyIncorrect = "Giardiasis is strictly non-invasive, meaning no blood or inflammatory cells appear in stool, unlike Entamoeba which invades tissue and causes bleeding. Cryptosporidium and Trichomonas affect different tissue systems.",
            clinicalImportance = "Nutritional deficit states secondary to untreated chronic giardiasis result in severe muscle wasting and generalized physical fatigue, reducing patient exercise tolerance.",
            examinerTip = "Remember that Giardia trophozoites have a characteristic falling-leaf motility in a fresh saline stool wet mount.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Giardia does not invade tissue; rather, it attaches to the brush border of enterocytes, causing microvillous atrophy and brush-border enzyme deficiency.",
            relatedConcept = "Giardiasis Mechanisms",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "What obligate intracellular protozoan, whose definitive hosts are domestic felines, is highly dangerous to pregnant mothers because of its ability to cross the placenta, causing developmental delays, chorioretinitis, and microcephaly/calcifications in the child?",
            options = listOf("Toxoplasma gondii", "Leishmania donovani", "Trypanosoma cruzi", "Babesia microti"),
            correctAnswer = "Toxoplasma gondii",
            whyCorrect = "Toxoplasma gondii is transmitted to humans by ingesting oocysts from cat litter or undercooked meat. It can cause severe congenital anomalies in pregnant women, including hydrocephalus, intracranial calcifications, and chorioretinitis.",
            whyIncorrect = "Leishmania causes visceral/cutaneous diseases, not transplacental syndrome. Trypanosoma causes Chagas disease. Babesia is tick-borne and resembles malaria.",
            clinicalImportance = "Pediatric physiotherapists working with developmentally delayed children should be familiar with the neurological manifestations of congenital toxoplasmosis, such as spasticity and balance impairments.",
            examinerTip = "Remember that crescent-shaped tachyzoites are seen during acute Toxoplasma infection, while tissue cysts containing bradyzoites characterize chronic latent infection in the brain and muscles.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Pregnant women should avoid cleaning cat litter boxes and eating undercooked meat to prevent acquiring primary congenital toxoplasmosis.",
            relatedConcept = "Congenital Toxoplasmosis",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "When comparing primary and secondary humoral immune responses to a protein antigen, how does the secondary response differ in terms of antibody isotype expression, speed, and binding affinity?",
            options = listOf("Rapid, high-level production of IgG with high antigen binding affinity due to somatic hypermutation and memory B-cell activation", "Slow, low-level production of IgM with fixed binding affinity", "Equal levels of IgM and IgG produced simultaneously in 24 hours", "Rapid production of mucosal secretory IgA with no change in IgG"),
            correctAnswer = "Rapid, high-level production of IgG with high antigen binding affinity due to somatic hypermutation and memory B-cell activation",
            whyCorrect = "The secondary immune response is triggered by memory B-cells. It has a shorter lag phase, produces vastly higher titers of antibodies, primarily IgG, and displays affinity maturation where somatic hypermutation during clonal expansion yields antibodies with significantly higher antigen-binding affinity.",
            whyIncorrect = "Slow, low-level IgM production describes the primary response (first antigen encounter). IgM and IgG are never produced with equal timeline profiles. IgA is for mucosal protection and does not dominate systemic secondary responses.",
            clinicalImportance = "This immunobiology principle explains why vaccinations and booster doses are highly effective in clinical practice.",
            examinerTip = "Highlight that IgM is the predominant antibody isotype in the primary response, while IgG is dominant in the secondary response.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Somatic hypermutation and follicular helper T-cell interactions are required to drive antibody isotype switching and affinity maturation in germinal centers.",
            relatedConcept = "Humoral Immunity Kinematics",
            reference = "Kuby Immunology, 8th Ed"
        ),
        VivaMcq(
            question = "A patient develops a severe contact dermatitis rash with tissue swelling 48 hours after plaster-of-Paris cast removal. What class of hypersensitivity reaction (Gell & Coombs) is this, and what immune players are responsible for the delayed tissue damage?",
            options = listOf("Type IV (Delayed-Type/Cell-Mediated); sensitized T-cells release cytokines that recruit and activate macrophages", "Type I (Anaphylactic); IgE antibodies trigger mast cell degranulation", "Type II (Cytotoxic); IgG/IgM antibodies activate complement against host membranes", "Type III (Immune-Complex); insoluble vascular antigen-antibody complexes induce neutrophils"),
            correctAnswer = "Type IV (Delayed-Type/Cell-Mediated); sensitized T-cells release cytokines that recruit and activate macrophages",
            whyCorrect = "Contact dermatitis is a classic example of Type IV delayed-type hypersensitivity. Unlike Types I-III, it is cell-mediated rather than antibody-mediated. Sensitized T-lymphocytes (CD4+ Th1 cells) are activated by the contact allergen and secrete cytokines that recruit and activate macrophages, causing tissue damage over 24-72 hours.",
            whyIncorrect = "Type I is rapid and mediated by IgE. Type II is antibody-mediated cytotoxic. Type III is immune-complex deposit mediated.",
            clinicalImportance = "Physical therapists must watch for skin allergy responses to ultrasound gel, adhesive therapeutic tapes (such as kinesiology tape), or orthopedic braces. These reactions represent CD4+ T-cell mediated delayed allergies.",
            examinerTip = "Remember: Type I, II, and III are antibody-mediated and occur rapidly (immediate hypersensitivity), whereas Type IV is cell-mediated and takes 24 to 72 hours.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Sensitization phase (antigen presentation to naive T-cells) must occur first; re-exposure activates the recruited memory T-cells, triggering the clinical manifestation.",
            relatedConcept = "Cell-Mediated Hypersensitivity",
            reference = "Kuby Immunology, 8th Ed"
        ),
        VivaMcq(
            question = "An infant acquires protective maternal antibodies across the placenta (IgG) and through breast milk (IgA). What type of immunity does this represent?",
            options = listOf("Natural Passive Immunity", "Natural Active Immunity", "Artificial Active Immunity", "Artificial Passive Immunity"),
            correctAnswer = "Natural Passive Immunity",
            whyCorrect = "Natural Passive Immunity is the transfer of pre-formed antibodies from one individual to another through a natural process. This includes maternal IgG crossing the placenta to protect the fetus and breast milk IgA protecting the neonate's gut. The infant's immune system is passive, meaning no immunological memory is established.",
            whyIncorrect = "Natural Active immunity is developed when an individual contracts an infection. Artificial Active immunity is immunization with vaccines. Artificial Passive immunity is the injection of pre-formed commercial immune globulins (e.g., anti-rabies serum).",
            clinicalImportance = "Knowing the maternal antibody clearance window helps pediatric physical therapists understand why early vaccination schedules play a key role in protecting infants.",
            examinerTip = "Be prepared to specify which antibody crosses the placenta: IgG is the only antibody isotype that can pass through the placental barrier due to its FcRn receptors.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Active immunity provides long-lasting protection with memory cell generation, while passive immunity provides immediate but temporary protection without memory.",
            relatedConcept = "Classifications of Immunity",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Many chronic musculoskeletal and autoimmune conditions managed in clinical physical therapy are strongly associated with specific Human Leukocyte Antigen (HLA) alleles. For example, HLA-B27 is linked to which inflammatory spinal arthropathy?",
            options = listOf("Ankylosing Spondylitis", "Rheumatoid Arthritis", "Systemic Lupus Erythematosus", "Osteoarthritis"),
            correctAnswer = "Ankylosing Spondylitis",
            whyCorrect = "HLA-B27 is a Class I MHC allele that has a very strong association with Ankylosing Spondylitis (AS)—over 90% of patients with AS carry this allele. AS is a chronic seronegative spondyloarthropathy characterized by pelvic and spinal joint inflammation, leading to spinal fusion.",
            whyIncorrect = "Rheumatoid Arthritis is associated with HLA-DR4. Systemic Lupus Erythematosus is associated with HLA-DR2 and HLA-DR3. Osteoarthritis is a mechanical joint disease and lacks specific HLA associations.",
            clinicalImportance = "Early recognition of ankylosing spondylitis is essential in musculoskeletal physical therapy. Patients presenting with chronic dull back pain and morning stiffness that improves with exercise should be referred for HLA-B27 screening.",
            examinerTip = "Remember that HLA Class I genes present intracellular antigens to CD8+ cytotoxic T-cells, while HLA Class II genes present extracellular antigens to CD4+ helper T-cells.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "AS patients have a high risk of developing cardiac issues, pulmonary fibrosis, and uveitis, showing that it is a systemic inflammatory disease.",
            relatedConcept = "MHC HLA Associations",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "What is a key biological detail that distinguishes Live Attenuated vaccines (e.g., MMR, BCG) from Killed/Inactivated vaccines (e.g., Salk Polio, Rabies) regarding cell-mediated immunity?",
            options = listOf("Live attenuated vaccines replicate within host cells, presenting antigens via MHC Class I to initiate a strong CD8+ cytotoxic T-cell response", "Killed vaccines undergo endosymbiotic mutation to induce permanent cellular immunity", "Live attenuated vaccines lack somatic antigens, so they only stimulate serum IgM without IgG", "Killed vaccines stimulate memory IgE production which avoids anaphylactoid complications"),
            correctAnswer = "Live attenuated vaccines replicate within host cells, presenting antigens via MHC Class I to initiate a strong CD8+ cytotoxic T-cell response",
            whyCorrect = "Live attenuated viruses replicate inside host cells, producing intracellular viral proteins that are processed and presented on MHC Class I molecules. This pathway stimulates a robust CD8+ Cytotoxic T-lymphocyte response. In contrast, killed vaccines are processed as extracellular proteins and presented on MHC Class II, triggering primarily CD4+ helper T-cells and humoral immunity.",
            whyIncorrect = "Killed vaccines do not replicate or mutate inside host cells. Live vaccines produce durable IgG and IgA isotypes. Killed vaccines do not selectively induce IgE.",
            clinicalImportance = "Immunocompromised patients (e.g., those on immunosuppressants or with advanced HIV) must never receive live attenuated vaccines due to the risk of uncontrolled vaccine strain replication.",
            examinerTip = "Identify live-attenuated vaccines: BCG, MMR, Oral Polio Vaccine (Sabin), Yellow Fever, and Varicella.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Live vaccines generally provide long-term protection with fewer doses, whereas killed vaccines require multiple doses and booster shots because they do not replicate.",
            relatedConcept = "Vaccine Immunobiology",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Tetanus and diphtheria vaccines utilize toxoids as the immunizing antigen. What is a toxoid, and how is it chemically processed during vaccine manufacturing?",
            options = listOf("An exotoxin treated with formalin to destroy its chemical toxicity while retaining its structural antigenicity", "An endotoxin derived from Gram-negative cell walls that is heat-treated", "An attenuated active bacterium that releases weak toxin variants", "A genetically engineered antibody that neutralizes muscle spasms"),
            correctAnswer = "An exotoxin treated with formalin to destroy its chemical toxicity while retaining its structural antigenicity",
            whyCorrect = "A toxoid is a bacterial exotoxin that has been treated with formalin (diluted formaldehyde). This process alters the toxin's active site, neutralizing its physical toxicity while preserving its epitopes, which allows the immune system to produce neutralizing antibodies.",
            whyIncorrect = "Toxoids are derived from exotoxins, not endotoxins (LPS). They contain no bacterial cells. They are immunizing antigens, not pre-formed therapeutic antibodies.",
            clinicalImportance = "Physical therapists working in orthopedic and wound care should confirm that patients with traumatic tissue wounds have received their tetanus booster within the last 5-10 years to protect against tetanospasmin-induced spastic paralysis.",
            examinerTip = "Toxoid vaccines induce humoral immunity (specifically IgG antibodies), which neutralize free toxin molecules before they can bind to neurological receptors.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Toxoids stimulate protective neutralizing antitoxin antibodies but do not target the bacterial cell wall directly.",
            relatedConcept = "Toxoid Immunology",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Herd immunity protects susceptible individuals within a population when a critical threshold of the community is immune. What epidemiological parameter determines this herd immunity threshold (HIT) for a given pathogen?",
            options = listOf("The basic reproduction number (R0) of the pathogen", "The clinical mortality rate of infection", "The dry environment stability of the vector", "The host cellular immune latency index"),
            correctAnswer = "The basic reproduction number (R0) of the pathogen",
            whyCorrect = "The basic reproduction number (R0) represents the average number of secondary cases generated by a single infectious individual in a fully susceptible population. Highly infectious diseases (like measles with a high R0 of 12-18) require a much higher vaccination coverage (92-95%) to achieve herd immunity.",
            whyIncorrect = "Mortality rate describes clinical severity, not transmissibility. Environmental stability and host cellular incubation times are biological traits, but do not directly calculate the herd immunity threshold mathematically.",
            clinicalImportance = "Physical therapy departments are high-risk sites for infection transmission due to close contact. Meeting herd immunity targets through staff immunizations protects vulnerable patients.",
            examinerTip = "Define R0: if R0 < 1, the infection will eventually die out in the community; if R0 > 1, the infection will continue to spread.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Herd immunity prevents epidemics by reducing the probability of an infectious case coming into contact with a susceptible individual.",
            relatedConcept = "Epidemiological Thresholds",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Why are capsular polysaccharide antigens (e.g., Streptococcus pneumoniae, Haemophilus influenzae type b) chemically conjugated (linked) to a carrier protein (such as diphtheria toxoid) in modern pediatric vaccines?",
            options = listOf("To recruit antigen-specific T-helper cells, converting a T-independent response into a T-dependent response with memory", "To prevent the live vaccine strain from reverting to virulent wild-type", "To neutralize gastrointestinal acid, allowing oral administration", "To bypass lysosomal processing, accelerating antibody synthesis"),
            correctAnswer = "To recruit antigen-specific T-helper cells, converting a T-independent response into a T-dependent response with memory",
            whyCorrect = "Polysaccharide capsules are T-independent antigens; they cannot be presented on MHC molecules to T-cells, triggering only low-affinity IgM from B-cells without generating memory cells. Conjugating the polysaccharide to a protein carrier allows the protein portion to be processed and presented on MHC Class II to helper T-cells, enabling memory cell formation.",
            whyIncorrect = "Reversion risk is a concern for live vaccines, but conjugate vaccines are subunit/inactivated. Protein conjugation does not protect against stomach acid or bypass standard dendritic antigen processing.",
            clinicalImportance = "Proper vaccination against Haemophilus influenzae type b (Hib) and pneumococcal strains has significantly reduced cases of bacterial meningitis, which can lead to permanent motor deficits and cerebral palsy.",
            examinerTip = "The most common protein carriers used in conjugate vaccines are non-toxic mutant diphtheria toxin, tetanus toxoid, or meningococcal outer membrane proteins.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "B-cells bind the polysaccharide surface, internalize the whole conjugate, and present peptide fragments to helper T-cells, which in turn activate the B-cell.",
            relatedConcept = "T-Dependent vs T-Independent Antigens",
            reference = "Kuby Immunology, 8th Ed"
        ),
        VivaMcq(
            question = "Mycobacterium tuberculosis cells resist decolorization by strong mineral acids during acid-fast staining (Ziehl-Neelsen stain). What unique macromolecule in their cell wall is responsible for this acid-fast characteristic and their resistance to environmental drying?",
            options = listOf("Mycolic acid", "Lipopolysaccharide", "Teichoic acid", "Peptidyl transferase"),
            correctAnswer = "Mycolic acid",
            whyCorrect = "Mycobacterium tuberculosis has a thick, waxy cell wall containing mycolic acids (extremely long, branched-chain fatty acids). This lipid barrier prevents the penetration of common dyes but retains heated carbolfuchsin stain, resisting decolorization by acid-alcohol.",
            whyIncorrect = "Lipopolysaccharides are found in Gram-negative bacteria. Teichoic acids are found in Gram-positive bacteria. Peptidyl transferase is a ribosomal enzyme, not a cell wall lipid.",
            clinicalImportance = "Physical therapists must follow strict airborne precautions when treating patients with active pulmonary tuberculosis. The high lipid content of M. tuberculosis allows droplets to remain infectious on dry clinic surfaces for long periods.",
            examinerTip = "Know the reagents in the Ziehl-Neelsen (ZN) stain: Carbolfuchsin (Primary stain), Sulfuric Acid (Decolorizer), and Methylene Blue (Counterstain).",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Acid-fastness is defined as the physical capacity of stained cells to resist decolorization by an acid-alcohol solution.",
            relatedConcept = "Acid-Fast Cell Wall",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "What is the host immune response that limits the spread of Mycobacterium tuberculosis, resulting in the formation of a 'granuloma' (tubercle) characterized by central caseous necrosis?",
            options = listOf("Type IV hypersensitivity; helper T-cells release IFN-gamma to activate macrophages, which aggregate into epithelioid cells and Langhans giant cells", "Type I hypersensitivity; IgE-mediated mast cell degranulation walling off the site with collagen", "Type II cytotoxicity; complement-mediated lysis of alveolar cells", "Type III hypersensitivity; antigen-antibody deposits in alveolar capillaries"),
            correctAnswer = "Type IV hypersensitivity; helper T-cells release IFN-gamma to activate macrophages, which aggregate into epithelioid cells and Langhans giant cells",
            whyCorrect = "Survival of M. tuberculosis inside macrophages leads to antigen presentation to T-helper cells, inducing Th1 differentiation. Activated Th1 cells secrete IFN-gamma, which powerfully activates macrophages, leading them to aggregate as epithelioid cells and fuse to form Langhans giant cells, forming a granuloma with central necrosis.",
            whyIncorrect = "Granulomatous inflammation is a type IV cell-mediated response, whereas allergen IgE, complement lysis, and immune complex deposits (Types I-III) are antibody-mediated and cannot form classic caseating granulomas.",
            clinicalImportance = "Reactivation of latent tuberculosis can occur if a patient undergoes prolonged immunosuppressive therapy (e.g., for rheumatoid arthritis). Physiotherapists should monitor patients for system-level red flags like unexplained night sweats, weight loss, and chronic cough.",
            examinerTip = "Be prepared to define the Ghon focus (initial localized parenchyma lesion) and the Ghon complex (Ghon focus plus infected lymph nodes).",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "The central caseous necrosis of a tuberculosis granuloma results from host immune cell activity and tissue ischemia, not direct bacterial toxins.",
            relatedConcept = "Granulomatous Pathology",
            reference = "Robbins & Cotran Pathologic Basis of Disease, 10th Ed"
        ),
        VivaMcq(
            question = "The Mantoux tuberculin skin test utilizes an intradermal injection of Purified Protein Derivative (PPD). What clinical response indicates a positive reading at 48-72 hours, and what does a positive test indicate?",
            options = listOf("A palpable induration exceeding 10-15 mm; indicating prior exposure and cell-mediated sensitization to M. tuberculosis, but not necessarily active disease", "A transient red wheal appearing within 15 minutes; indicating active, infectious pulmonary cavitation", "A fluid-filled vesicle over 5 mm; indicating active systemic osteoarticular tuberculosis", "Complete absence of skin redness; indicating absolute long-term protective antibody neutralizing titers"),
            correctAnswer = "A palpable induration exceeding 10-15 mm; indicating prior exposure and cell-mediated sensitization to M. tuberculosis, but not necessarily active disease",
            whyCorrect = "The PPD test detects cell-mediated immunity (delayed hypersensitivity). If a person has been exposed/sensitized to Mycobacterium tuberculosis, memory T-lymphocytes migrate to the injection site and cause a palpable, raised induration that peaks at 48-72 hours. A positive test confirms exposure, but cannot distinguish active disease from latent infection.",
            whyIncorrect = "A rapid wheal and flare (15 min) is a Type I IgE reaction. Blistering/vesicles do not define standard positive criteria. Complete absence of reaction (anergy) can occur in severe AIDS, but otherwise represents a negative test.",
            clinicalImportance = "Physiotherapists working in highly endemic areas must regularly undergo Mantoux screening. Patients with positive skin tests should receive follow-up chest X-rays to rule out active respiratory disease.",
            examinerTip = "Keep in mind that prior vaccination with the BCG (Bacillus Calmette-Guérin) vaccine can cause a false-positive Mantoux skin test.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "The Mantoux test measures induration (raised thickness), NOT skin redness (erythema).",
            relatedConcept = "Tuberculin Skin Testing",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Mycobacterium tuberculosis can spread hematogenously to the musculoskeletal system. What spinal condition is caused by this infection, and where does the destruction typically begin?",
            options = listOf("Pott's Disease; begins in the anterior vertebral body margin and spreads to destroy the intervertebral disc", "Marie-Strümpell disease; begins as sterile calcification of the supraspinous ligament", "Charcot spine; painless joint destruction due to spinal tabes dorsalis", "Schuermann's disease; aseptic necrosis of the ring apophysis"),
            correctAnswer = "Pott's Disease; begins in the anterior vertebral body margin and spreads to destroy the intervertebral disc",
            whyCorrect = "Spinal tuberculosis (Pott's disease) typically results from hematogenous spread of M. tuberculosis to the vertebral body. It begins at the anterior margin of the vertebral body adjacent to the intervertebral disc and spreads to destroy the disc and adjacent vertebrae. This leads to vertebral collapse and progressive kyphosis.",
            whyIncorrect = "Marie-Strümpell disease is another name for Ankylosing Spondylitis. Charcot spine is a neuropathic joint disease, often associated with syphilis. Scheuermann's disease is developmental juvenile osteochondrosis.",
            clinicalImportance = "Spinal tuberculosis is a critical condition in orthopedic physical therapy. Avoid spinal manipulation until Pott's disease has been ruled out, as structural collapse can cause paraplegia.",
            examinerTip = "In Pott's disease, the formation of a 'cold abscess' in the psoas muscle sheath (psoas abscess) is a classic clinical finding. It can present as a palpable groin lump.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Pott's disease causes progressive bony destruction that collapses the anterior spinal column, leading to a characteristic angular kyphosis called a gibbus deformity.",
            relatedConcept = "Skeletal Tuberculosis",
            reference = "Apley & Solomon's System of Orthopaedic and Trauma, 10th Ed"
        ),
        VivaMcq(
            question = "Which hepatitis virus is primarily transmitted through the fecal-oral route, typically causing acute, self-limiting hepatotoxic infections without progressing to chronic liver disease or carrier states?",
            options = listOf("Hepatitis A Virus (HAV)", "Hepatitis B Virus (HBV)", "Hepatitis C Virus (HCV)", "Hepatitis D Virus (HDV)"),
            correctAnswer = "Hepatitis A Virus (HAV)",
            whyCorrect = "Hepatitis A (HAV) and Hepatitis E (HEV) are enterically transmitted (fecal-oral route), often through contaminated water or food. HAV is a non-enveloped RNA virus that causes acute hepatitis but never causes chronic infection or a long-term carrier state.",
            whyIncorrect = "Hepatitis B, C, and D are enveloped viruses transmitted parenterally (via blood, needles, or sexual contact) and can lead to chronic carrier states, cirrhosis, and hepatocellular carcinoma.",
            clinicalImportance = "Public health awareness of sanitation prevents outbreaks of Hepatitis A. Physical therapists working in pediatric centers should emphasize hand hygiene after diaper changes.",
            examinerTip = "Remember the transmission mnemonic: 'Vowels affect the Bowels' (Hepatitis A and E are transmitted feces-to-mouth).",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Enteric hepatitis viruses (A and E) are non-enveloped, which makes them stable in the gastrointestinal environment, whereas parenteral hepatitis viruses (B, C, D) are enveloped and fragile.",
            relatedConcept = "Hepatitis Epidemiology",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "A physical therapist receives a needle-stick injury. Serological testing of the source patient reveals the presence of Hepatitis B surface Antigen (HBsAg) and Hepatitis B e-Antigen (HBeAg). What is the clinical significance of these serological markers?",
            options = listOf("The patient has active Hepatitis B infection with high viral replication and infectiousness", "The patient is immune due to prior successful vaccination", "The patient is in the late convalescent recovery window with no live virus present", "The patient has a self-limiting Hepatitis A co-infection with low transmission risk"),
            correctAnswer = "The patient has active Hepatitis B infection with high viral replication and infectiousness",
            whyCorrect = "The presence of HBsAg (surface antigen) indicates active Hepatitis B infection. The presence of HBeAg (e-antigen) indicates active viral replication, a high viral load, and high infectivity of blood/body fluids.",
            whyIncorrect = "Immunity from vaccination is indicated by anti-HBs in the absence of HBsAg. Late recovery is marked by HBsAg clearance and anti-HBc/anti-HBs positivity, with HBeAg being negative. These markers are specific to HBV, not HAV.",
            clinicalImportance = "Needle-stick injuries are a major occupational risk for physical therapists performing dry needling. Recognizing high-infectivity markers highlights the need for immediate prophylaxis (HBIG and vaccine boost).",
            examinerTip = "During the recovery phase, there is a 'Window Period' where HBsAg has disappeared but anti-HBs has not yet reached detectable levels, and IgM anti-HBc is the only marker of recent acute infection.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Anti-HBs is the only marker that indicates protective immunity, whether from natural recovery or vaccination.",
            relatedConcept = "HBV Serodiagnosis",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "Why does Hepatitis C Virus (HCV) lead to chronic carrier states in a majority of infected patients, and what are the long-term hepatic complications?",
            options = listOf("High mutation rate of the envelope gene (hypervariable region) allowing immune escape; leading to chronic hepatitis, cirrhosis, and hepatocellular carcinoma", "Integration of viral DNA into the host chromosome causing cell cycle arrest", "Formation of heat-stable endospores in hepatocytes resistant to lymphocytes", "Inhibition of bile salt synthesis causing systemic lipid metabolic failure"),
            correctAnswer = "High mutation rate of the envelope gene (hypervariable region) allowing immune escape; leading to chronic hepatitis, cirrhosis, and hepatocellular carcinoma",
            whyCorrect = "Hepatitis C is an RNA virus with a highly active, error-prone RNA-dependent RNA polymerase. This enzyme leads to rapid mutations, particularly in the hypervariable region of the envelope gene, escaping host neutralizing antibodies, resulting in chronic infection, cirrhosis, and cancer.",
            whyIncorrect = "HCV is an RNA virus that replicates in the cytoplasm and does not integrate into host DNA. Fungi and bacteria form spores, not human viruses. Lipid metabolic failure is a symptom, not the mechanism of persistence.",
            clinicalImportance = "Chronic Hepatitis C is the leading indication for liver transplant. Physical therapists working with these patients must carefully adjust exercise intensity based on fatigue and coagulation profiles.",
            examinerTip = "Unlike HBV, there is no vaccine available for HCV due to the high mutational variability of its envelope glycoproteins.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "The high genetic diversity of HCV allows the virus to stay ahead of the host's adaptive immune system, leading to chronic, persistent inflammation.",
            relatedConcept = "HCV Persistence Mechanisms",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Hepatitis D Virus (HDV) is a sub-viral satellite pathogen that is unique in its replication. What specific helper contribution does it require from Hepatitis B Virus (HBV) to infect human host cells?",
            options = listOf("HBsAg (surface antigen) to envelope the HDV RNA genome for attachment to hepatocytes", "Reverse transcriptase enzyme to replicate HDV RNA into host DNA", "HBcAg (core antigen) to form the icosahedral capsid of HDV", "HBeAg (e-antigen) to suppress the host's interferon response"),
            correctAnswer = "HBsAg (surface antigen) to envelope the HDV RNA genome for attachment to hepatocytes",
            whyCorrect = "Hepatitis D is a defective RNA virus that cannot replicate independently. It requires the presence of an active HBV infection, specifically using HBV surface antigen (HBsAg) as its outer envelope coat to bind to and enter hepatocytes.",
            whyIncorrect = "HDV does not use reverse transcriptase (it is an RNA virus, not a retrovirus). It does not use HBcAg or HBeAg for its structural envelope design.",
            clinicalImportance = "HDV superinfection in a chronic HBV carrier often leads to severe acute liver failure. Preventing HBV infection via vaccination also prevents HDV infection.",
            examinerTip = "Define superinfection: when an HDV infection occurs in an individual who is already a chronic HBV carrier. It has a much higher rate of complications than co-infection.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "HDV is a circular, single-stranded, negative-sense RNA satellite virus dependent on HBV for envelope synthesis.",
            relatedConcept = "Delta Hepatitis Pathogenesis",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "During the initial attachment phase of HIV entry into host cells, what specific viral envelope glycoprotein binds to the CD4 receptor on helper T-cells and macrophages?",
            options = listOf("gp120", "gp41", "gp24", "gp17"),
            correctAnswer = "gp120",
            whyCorrect = "The HIV envelope consists of two non-covalently linked glycoproteins: gp120 (outer membrane) and gp41 (transmembrane). Gp120 is responsible for binding to the CD4 receptor on host cells, along with host co-receptors.",
            whyIncorrect = "Gp41 initiates the fusion of the viral envelope with the host cell membrane once gp120 has bound. Gp24 is the major capsid protein. Gp17 is the matrix protein.",
            clinicalImportance = "Recognizing that HIV target CD4 cell binding is mediated by gp120 helps clinicians understand how early entry inhibitors (e.g., Maraviroc, which blocks CCR5) prevent cellular infection in exposure injuries.",
            examinerTip = "Remember that CCR5-tropic strains predominate in early infection, while CXCR4-tropic strains emerge in late stages, accelerating CD4+ T-cell depletion.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "The tropism of HIV is determined by the specificity of gp120 for host cell chemokine co-receptors (CCR5 on macrophages, CXCR4 on T-cells).",
            relatedConcept = "HIV Entry Mechanism",
            reference = "Jawetz, Melnick & Adelberg's Medical Microbiology, 28th Ed"
        ),
        VivaMcq(
            question = "What is the standard diagnostic algorithm for HIV testing in blood and serum, and what markers are detected in modern fourth-generation assays?",
            options = listOf("Fourth-generation ELISA (detects HIV-1/2 antibodies and p24 antigen) for screening; confirmed by HIV-1/2 differentiation immunoassay or Western Blot", "Western Blot for initial rapid screening; confirmed by direct skin culture", "Mantoux test for screening; confirmed by bacterial DNA polymerase chain reaction", "Gram stain of blood smear for screening; confirmed by viral envelope precipitation"),
            correctAnswer = "Fourth-generation ELISA (detects HIV-1/2 antibodies and p24 antigen) for screening; confirmed by HIV-1/2 differentiation immunoassay or Western Blot",
            whyCorrect = "Modern HIV diagnostic protocols recommend using a fourth-generation ELISA/EIA. This assay simultaneously detects both host anti-HIV-1/2 antibodies and the viral capsid p24 antigen. This combination reduces the window period to about 2-3 weeks. A positive screening test must be confirmed by a secondary assay.",
            whyIncorrect = "Western Blot is technically complex, making it unsuitable as an initial rapid screen. The Mantoux test is for tuberculosis. Gram staining cannot detect sub-microscopic viral particles.",
            clinicalImportance = "Recognizing the timeline of the early infection window period is critical for physical therapists when evaluating occupational blood exposures. Fourth-generation testing offers faster, more reliable confirmation.",
            examinerTip = "Keep in mind that the inclusion of the p24 antigen in fourth-generation ELISA tests is highly diagnostic because p24 levels rise before host antibodies are produced.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Early detection is essential. RNA viral load tests (RT-PCR) can detect the virus when serological tests are negative during the acute window period.",
            relatedConcept = "HIV Diagnostic Algorithm",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "A patient with HIV presents with a CD4+ T-cell count of 150 cells/µL. Based on this immunological status, what clinical diagnosis does this represent, and for which opportunistic pathogen is the patient at high risk?",
            options = listOf("Clinical AIDS; high risk for Pneumocystis jirovecii pneumonia", "Early stage HIV; high risk for uncomplicated Streptococcus pneumoniae infection", "Immune-competent carrier; high risk for subcutaneous dermatophytosis", "Latent HIV; selective risk for acute Staphylococcus aureus skin colonization"),
            correctAnswer = "Clinical AIDS; high risk for Pneumocystis jirovecii pneumonia",
            whyCorrect = "A CD4+ count below 200 cells/µL is a diagnostic criterion for clinical AIDS (stage 3 HIV). At CD4 levels below 200/µL, cellular immunity is severely impaired, placing the patient at high risk for Pneumocystis jirovecii pneumonia (PCP), an opportunistic fungal lung infection.",
            whyIncorrect = "Early-stage HIV typically has CD4 counts over 500 cells/µL. S. pneumoniae is a common community pathogen that can infect any stage, but isn't the primary marker of severe opportunistic risk. Skin infections can occur but PCP is the classic life-threatening opportunistic indicator.",
            clinicalImportance = "For chest physical therapists, knowing a patient's CD4 count is essential. When treating patients with PCP, respiratory therapy must be adapted to manage severe arterial hypoxia.",
            examinerTip = "Remember key CD4 diagnostic thresholds: <200 indicates risk for PCP; <100 indicates risk for Toxoplasma gondii; <50 indicates risk for CMV retinitis and Mycobacterium avium complex (MAC).",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "CD4 count measures the severity of immunosuppression, while viral load measures active replication and guides antiretroviral therapy (ART) efficacy.",
            relatedConcept = "Opportunistic Infections in AIDS",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Highly Active Antiretroviral Therapy (HAART) typically combines drugs from different pharmacological classes to suppress HIV replication. What is the primary biological reason for using a multidrug combination rather than monotherapy?",
            options = listOf("To prevent the emergence of drug-resistant mutants by targeting multiple stages of the viral replication cycle simultaneously", "To allow lower doses of each drug, minimizing liver toxicity in chronic carriers", "To neutralize circulating IgG antibody complexes before they can settle in joints", "To block both host cell DNA transcription and viral protein translation in parallel"),
            correctAnswer = "To prevent the emergence of drug-resistant mutants by targeting multiple stages of the viral replication cycle simultaneously",
            whyCorrect = "HIV has an extremely high mutation rate due to its error-prone reverse transcriptase. Combining drugs with different mechanisms (such as NRTIs, Integrase Inhibitors, and Protease Inhibitors) makes it highly unlikely that a single viral particle will generate mutations against all drugs simultaneously.",
            whyIncorrect = "Drug combinations are chosen to maximize efficacy, not specifically to reduce doses or toxicity. HAART does not target antibody complexes or block host cell transcription.",
            clinicalImportance = "Multidrug compliance is essential for patients with HIV. Physical therapists should educate patients about the importance of consistent medication adherence, as missed doses lead to rapid drug resistance.",
            examinerTip = "Name the key classes of antiretroviral drugs: Nucleoside Reverse Transcriptase Inhibitors (NRTIs), Non-Nucleoside Reverse Transcriptase Inhibitors (NNRTIs), Protease Inhibitors (PIs), Integrase Strand Transfer Inhibitors (INSTIs), and Entry/Fusion Inhibitors.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "HAART aims to reduce the viral load in blood to undetectable levels, which stops progression of immunodeficiency and prevents sexual transmission of the virus.",
            relatedConcept = "Antiretroviral Drug Combination Principles",
            reference = "Katzung's Basic & Clinical Pharmacology, 15th Ed"
        ),
        VivaMcq(
            question = "Which of the following parameters clinically defines a 'nosocomial' (hospital-acquired) infection?",
            options = listOf("An infection that manifests 48 hours or more after hospital admission, and was not present or incubating at the time of admission", "An infection that was actively incubating prior to arrival and manifests within 12 hours of admission", "Any viral infection contracted from a community vector within 5 days after discharge", "An infection caused exclusively by non-pathogenic normal flora in healthy physical therapy outpatients"),
            correctAnswer = "An infection that manifests 48 hours or more after hospital admission, and was not present or incubating at the time of admission",
            whyCorrect = "A nosocomial or healthcare-associated infection (HAI) is defined as an infection occurring in a patient in whom the infection was not present or incubating at the time of admission, with standard clinical thresholds standing at 48 hours or more after admission.",
            whyIncorrect = "Infections presenting within 12 hours were incubating in the community. Post-discharge infections are community-acquired unless linked to a procedure. HAI can be caused by opportunistic pathogens, but is not limited to normal flora in healthy outpatients.",
            clinicalImportance = "Physical therapists regularly treat multiple acute ICU and ward patients each day. Adhering to infection control protocols prevents the clinician from being a vector for hospital-acquired infections.",
            examinerTip = "Examiners will ask for the most common nosocomial infections: catheter-associated urinary tract infections (CAUTI), surgical site infections (SSI), ventilator-associated pneumonia (VAP), and bloodstream infections.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "HAIs are associated with high morbidity, prolonged hospital stays, and increased healthcare costs, making prevention a high priority.",
            relatedConcept = "Nosocomial Epidemiology",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "According to the World Health Organization (WHO) '5 Moments for Hand Hygiene,' which of the following represents the single most effective, evidence-based physical intervention to prevent hospital-acquired transmission of multi-drug resistant pathogens?",
            options = listOf("Performing hand hygiene immediately before touching a patient and immediately after touching a patient or their surroundings", "Fumigating the rehabilitation gym with formaldehyde once a week", "Wearing sterile gloves continuously throughout an 8-hour shift", "Administering prophylactic oral antibiotics to all physical therapy staff"),
            correctAnswer = "Performing hand hygiene immediately before touching a patient and immediately after touching a patient or their surroundings",
            whyCorrect = "Hand hygiene is the single most effective measure to prevent the spread of pathogens. The WHO 5 Moments capture critical healthcare touchpoints: (1) before touching a patient, (2) before aseptic procedures, (3) after body fluid exposure risk, (4) after touching a patient, and (5) after touching surroundings.",
            whyIncorrect = "Weekly fumigation is an auxiliary measure and doesn't prevent daily cross-contamination. Wearing the same gloves continuously promotes cross-contamination. Antibiotic prophylaxis is unnecessary and contributes to drug resistance.",
            clinicalImportance = "Physical therapists use hands-on techniques daily (such as joint mobilization). Performing hand hygiene before and after contact is critical to prevent carrying resistant bacteria (like MRSA) between vulnerable patients.",
            examinerTip = "Remember that alcohol hand rubs are preferred for routine decontamination unless hands are visibly soiled or Clostridioides difficile is suspected.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Hand hygiene compliance remains a key metric for hospital accreditation and quality improvement programs globally.",
            relatedConcept = "Standard Precautions & Hand Hygiene",
            reference = "CDC Guideline for Hand Hygiene in Health-Care Settings"
        ),
        VivaMcq(
            question = "Methicillin-Resistant Staphylococcus aureus (MRSA) is an important nosocomial pathogen. What structural change in the bacterium mediates resistance to beta-lactam antibiotics, and what type of isolation precaution is indicated?",
            options = listOf("Acquisition of the mecA gene encoding an altered Penicillin-Binding Protein (PBP2a) with low affinity for beta-lactams; requires Contact Precautions", "Production of a beta-lactamase enzyme that cleaves the antibiotic ring; requires Airborne Precautions", "Alteration of the ribosomal peptide exit tunnel; requires Droplet Precautions", "Synthesis of a thick capsule that prevents antibiotic entry; requires protective isolation"),
            correctAnswer = "Acquisition of the mecA gene encoding an altered Penicillin-Binding Protein (PBP2a) with low affinity for beta-lactams; requires Contact Precautions",
            whyCorrect = "MRSA resistance is mediated by the mecA gene, which encodes an altered transpeptidase called Penicillin-Binding Protein 2a (PBP2a) with low affinity for almost all beta-lactams. Because it is spread by touch and shared surfaces, Contact Precautions are required (gown, gloves, dedicated equipment).",
            whyIncorrect = "Simple beta-lactamase causes standard penicillin resistance, not MRSA. Airborne or droplet precautions are not indicated for standard MRSA skin infections. Ribosomal and capsular modifications are not the mechanism of MRSA.",
            clinicalImportance = "When treating a patient with MRSA in physical therapy, Contact isolation must be maintained. Gown up, wear gloves, and thoroughly disinfect goniometers and gym beds after use.",
            examinerTip = "Keep in mind that Vancomycin is the traditional drug of choice for treating severe MRSA infections, as it targets cell wall synthesis at a different stage.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "PBP2a continues to cross-link peptidoglycan even in the presence of inhibitory concentrations of beta-lactam antibiotics.",
            relatedConcept = "MRSA Resistance & Precautions",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "An elderly hospital patient develops severe, watery, foul-smelling pseudomembranous colitis after a 2-week course of clindamycin. The causative agent is Clostridioides difficile. Why are alcohol-based hand rubs ineffective for decontamination of hands after treating this patient, and what intervention is required?",
            options = listOf("C. difficile is a spore-forming bacterium, and spores are resistant to alcohol; hands must be washed with soap and water to mechanically wash away the spores", "C. difficile produces an endotoxin that inactivates alcohol; hands must be rinsed with hydrogen peroxide", "The vegetative cells replicate rapidly in alcohol solutions; hands must be treated with ultraviolet light", "The infection is airborne; staff must wear N95 respirators instead of performing hand hygiene"),
            correctAnswer = "C. difficile is a spore-forming bacterium, and spores are resistant to alcohol; hands must be washed with soap and water to mechanically wash away the spores",
            whyCorrect = "Clostridioides difficile is an anaerobic, spore-forming bacillus. Spores are resistant to alcohol because of their thick coat. Therefore, healthcare workers must wash their hands with soap and water after patient contact to mechanically friction-rub and rinse the spores from skin.",
            whyIncorrect = "C. difficile acts via exotoxins, not endotoxin. Alcohol does not promote the replication of C. difficile cells. The infection is fecal-oral (contact), not airborne, so contact precautions are required, not N95 masks.",
            clinicalImportance = "When managing patients with post-antibiotic diarrhea during inpatient physical rehab, switch from alcohol rub to washing hands with soap and water after every session to prevent spreading spores.",
            examinerTip = "C. difficile diarrhea is typically triggered by broad-spectrum antibiotics (such as clindamycin or cephalosporins) that disrupt normal colonic microflora, allowing C. difficile to overgrow.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Mechanical hand washing with soap and water does not kill C. difficile spores, but physically removes them from the skin.",
            relatedConcept = "C. difficile Infection Control",
            reference = "CDC Guidelines for Prevention of Clostridioides difficile Infections"
        ),
        VivaMcq(
            question = "Many nosocomial bacteremia infections are associated with indwelling central venous catheters. What microbial structure, secreted by opportunistic pathogens like Staphylococcus epidermidis, allows them to adhere to plastic catheters and resist both antibiotics and host immune cells?",
            options = listOf("Biofilm (slime/extracellular polymeric substance)", "Endotoxin", "Pili", "Endospore"),
            correctAnswer = "Biofilm (slime/extracellular polymeric substance)",
            whyCorrect = "Staphylococcus epidermidis is a major cause of catheter-associated infections. It adheres to plastic surfaces and secretes an extracellular polymeric substance, forming a biofilm. This matrix acts as a physical barrier that prevents antibiotic penetration and shields the bacteria from host immunology.",
            whyIncorrect = "Endotoxins are Gram-negative lipopolysaccharides and do not form protective matrices on plastic. Pili are for initial attachment, but do not provide long-term resistance. S. epidermidis is non-spore-forming.",
            clinicalImportance = "Physiotherapists working with patients who have central lines or urinary catheters should handle these devices with care during transfers to avoid micro-motion, which can introduce biofilm-producing bacteria into the blood.",
            examinerTip = "Staphylococcus epidermidis is novobiocin-sensitive, a key laboratory feature used to distinguish it from Staphylococcus saprophyticus (novobiocin-resistant).",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Biofilms can increase a bacterium's resistance to antibiotics by up to 1000-fold compared to its planktonic (free-floating) state.",
            relatedConcept = "Microbial Biofilms",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "MacConkey agar is widely used in diagnostic bacteriology. How is this medium classified, and what visual changes differentiate Escherichia coli from Salmonella species?",
            options = listOf("Selective and Differential; E. coli ferments lactose appearing pink/red, while Salmonella is a non-lactose fermenter appearing colorless", "Enriched and Selective; E. coli grows rapidly as flat yellow colonies, while Salmonella is inhibited by bile salts", "Simple and Differential; E. coli forms dry white colonies, while Salmonella ferments glucose appearing dark green", "Highly Selective anaerobic medium; E. coli produces hydrogen sulfide gas blackening the agar, while Salmonella is non-reactive"),
            correctAnswer = "Selective and Differential; E. coli ferments lactose appearing pink/red, while Salmonella is a non-lactose fermenter appearing colorless",
            whyCorrect = "MacConkey agar is selective because it contains bile salts and crystal violet, inhibiting Gram-positive bacteria. It is differential because it contains lactose and neutral red. Lactose-fermenting bacteria (like E. coli) produce acid, turning the colonies pink-red. Non-lactose fermenters (like Salmonella) appear as colorless or pale colonies.",
            whyIncorrect = "MacConkey is not an enriched medium. Visually, E. coli colonies on MacConkey are pink-red, not yellow. Salmonella is not inhibited by MacConkey, but grows as colorless colonies. It is incubated aerobically.",
            clinicalImportance = "In orthopedic physical therapy, pressure ulcers can become infected with Gram-negative rods. Knowing whether a pathogen is a lactose fermenter (pink on MacConkey) or helper non-lactose fermenter helps clinicians understand general wound profiles.",
            examinerTip = "Memorize key lactose fermenters: Escherichia, Klebsiella, and Enterobacter (pink on MacConkey). Key non-lactose fermenting pathogens are Salmonella, Shigella, Proteus, and Pseudomonas (colorless).",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Selective media contain additives that inhibit specific microbes, while differential media contain indicators that visually distinguish microbes based on their metabolic traits.",
            relatedConcept = "MacConkey Agar Reactions",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Blood agar is an enriched and differential medium. What are the three types of hemolysis observed on blood agar, and how do they appear visually?",
            options = listOf("Alpha (partial greening), Beta (complete clearing), and Gamma (no hemolysis / no zone)", "Alpha (complete clearing), Beta (partial greening), and Gamma (blackening)", "Alpha (blackening), Beta (no change), and Gamma (complete digestion)", "Alpha (gaseous bubbles), Beta (acidic precipitate), and Gamma (alkaline lysis)"),
            correctAnswer = "Alpha (partial greening), Beta (complete clearing), and Gamma (no hemolysis / no zone)",
            whyCorrect = "Blood agar contains 5% blood. It differentiates bacteria based on hemolytic activity: Alpha-hemolysis is partial hemolysis resulting in a greenish discoloration around colonies; Beta-hemolysis is complete lysis of RBCs resulting in a clear zone around colonies; Gamma-hemolysis indicates no hemolytic activity, leaving the medium red and unchanged.",
            whyIncorrect = "The descriptions are mismatched in other options. Alpha is partial greening and Beta is complete clearing, which is a key clinical distinction.",
            clinicalImportance = "Group A Beta-Hemolytic Streptococcus pyogenes causes pharyngitis and can lead to rheumatic fever and joint arthralgia. Identifying beta-hemolytic colonies helps confirm the diagnosis and prevent joint complications.",
            examinerTip = "Remember that Streptococcus pneumoniae is alpha-hemolytic, Streptococcus pyogenes is beta-hemolytic, and Enterococcus faecalis is typically gamma-hemolytic.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Hemolysis is mediated by bacterial exotoxins called hemolysins, which damage red blood cell membranes.",
            relatedConcept = "Haemolysis Patterns",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Which specialized culture medium, containing egg yolk, potato starch, glycerol, and malachite green, is used for the cultivation of Mycobacterium tuberculosis, and what is the role of malachite green?",
            options = listOf("Lowenstein-Jensen (LJ) medium; malachite green inhibits Gram-positive and Gram-negative contaminant bacteria", "Sabouraud Dextrose Agar; malachite green acts as a carbohydrate source", "Thiosulfate Citrate Bile Salts Sucrose (TCBS) agar; malachite green is a pH indicator", "Chocolate agar; malachite green provides essential iron and factor X"),
            correctAnswer = "Lowenstein-Jensen (LJ) medium; malachite green inhibits Gram-positive and Gram-negative contaminant bacteria",
            whyCorrect = "Lowenstein-Jensen (LJ) medium is the classic solid medium used to isolate Mycobacterium tuberculosis. Because tuberculosis specimens (sputum) are often contaminated with normal respiratory flora, malachite green is added to inhibit these faster-growing bacteria, allowing the slow-growing mycobacteria to emerge over 4-8 weeks.",
            whyIncorrect = "Sabouraud Dextrose Agar is for fungi. TCBS is for Vibrio cholerae. Chocolate agar is heated blood agar for Haemophilus.",
            clinicalImportance = "Liquid culture systems are faster, but LJ medium remains a reference. Physical therapists should know that tuberculosis takes weeks to grow, making molecular assays (like GeneXpert) essential for rapid diagnosis.",
            examinerTip = "Describe the appearance of M. tuberculosis on LJ medium: classic dry, rough, raised, cream-colored (buff-colored) colonies, often described as 'cauliflower-like'.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "M. tuberculosis has a very slow doubling time (15-20 hours), requiring selective media that can remain stable during incubation for up to 8 weeks.",
            relatedConcept = "Tuberculosis Culture",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "What culture medium is most widely used in diagnostic laboratories for the isolation and cultivation of fungi, and what environmental conditions make it selective?",
            options = listOf("Sabouraud Dextrose Agar (SDA); high dextrose concentration and low pH (5.6) inhibit bacterial growth while promoting fungal growth", "MacConkey agar; high bile salt concentration and neutral pH promote mold hyphae", "Blood agar; low temperature and high oxygen tension stimulate fungal sporulation", "Löwenstein-Jensen medium; high egg yolk lipid content supports dermatophytes"),
            correctAnswer = "Sabouraud Dextrose Agar (SDA); high dextrose concentration and low pH (5.6) inhibit bacterial growth while promoting fungal growth",
            whyCorrect = "Sabouraud Dextrose Agar (SDA) is the standard medium for cultivating fungi. It is selective due to its acidic pH (5.6) and high sugar concentration (dextrose), which inhibit most bacteria but support the growth of yeasts and molds.",
            whyIncorrect = "MacConkey agar is for Gram-negative bacteria. Blood agar supports both bacteria and yeasts but lacks selectivity for molds. LJ medium is specialized for mycobacteria.",
            clinicalImportance = "Fungal nail infections (onychomycosis) can be diagnosed by culturing nail clippings on SDA. Physical therapists should advise athletes to maintain clean, dry feet, as moisture encourages dermatophytic growth.",
            examinerTip = "Remember that SDA and DTM are incubated at 25°C to observe the mold phase of fungi, and at 37°C if isolating the yeast phase.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Fungi grow best in high-osmolality, acidic environments, making Sabouraud Dextrose Agar an ideal culture medium.",
            relatedConcept = "Fungal Cultivation",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "Why are specialized 'transport media' (such as Stuart's or Amies medium) used to collect wound swabs from patients, and what are their typical ingredients?",
            options = listOf("To preserve the viability of fastidious pathogens during transit without allowing them to multiply, containing charcoal to absorb toxic metabolites", "To selectively destroy non-pathogenic skin flora, containing high doses of penicillin", "To accelerate the replication of anaerobic bacteria, containing egg yolk", "To decontaminate wounds prior to lab submission, containing glutaraldehyde"),
            correctAnswer = "To preserve the viability of fastidious pathogens during transit without allowing them to multiply, containing charcoal to absorb toxic metabolites",
            whyCorrect = "Transport media are non-nutritional, semi-solid agar. They keep bacteria alive during transit without promoting replication, which would alter the ratio of pathogens in the specimen. Charcoal is added to Amies medium to adsorb toxic fatty acids and metabolic products.",
            whyIncorrect = "Transport media must not contain antibiotics like penicillin. They do not contain nutrients to encourage replication, and must never contain glutaraldehyde, which sterilizes and destroys the sample.",
            clinicalImportance = "When collecting wound swabs from a patient with an infected pressure ulcer, a physical therapist must use Amies or Stuart's transport media to ensure fastidious organisms remain viable for accurate antibiotic selection.",
            examinerTip = "Remember that transport media contain no nitrogen or carbon source, and often include a reducing agent to prevent oxidative stress in anaerobes.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Transport media maintain the viability of a clinical specimen without altering the relative recovery rates of the pathogens.",
            relatedConcept = "Clinical Specimen Transport",
            reference = "Ananthanarayan and Paniker's Textbook of Microbiology, 12th Ed"
        ),
        VivaMcq(
            question = "What is the primary function of the Na+/K+ pump?",
            options = listOf("Generates action potentials directly", "Maintains resting membrane potential", "Transports water", "Causes muscle contraction"),
            correctAnswer = "Maintains resting membrane potential",
            whyCorrect = "It moves 3 Na+ out and 2 K+ in, maintaining the concentration gradients necessary for the resting membrane potential.",
            whyIncorrect = "It does not generate APs directly; it restores gradients. It doesn't transport water or directly cause contraction.",
            clinicalImportance = "Digoxin inhibits this pump to increase cardiac contractility.",
            examinerTip = "3 Na+ OUT, 2 K+ IN.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Active transport mechanism.",
            relatedConcept = "Cell Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which organelle is the primary site of ATP production?",
            options = listOf("Golgi apparatus", "Mitochondria", "Rough ER", "Nucleus"),
            correctAnswer = "Mitochondria",
            whyCorrect = "Mitochondria are the powerhouses of the cell where oxidative phosphorylation occurs.",
            whyIncorrect = "Golgi packages proteins, Rough ER synthesizes proteins, Nucleus contains DNA.",
            clinicalImportance = "Mitochondrial myopathies present with severe muscle weakness and exercise intolerance.",
            examinerTip = "More active cells (like muscle) have more mitochondria.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Cellular energy production.",
            relatedConcept = "Cell Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "What constitutes the largest fluid compartment in the human body?",
            options = listOf("Plasma", "Interstitial fluid", "Intracellular fluid (ICF)", "Transcellular fluid"),
            correctAnswer = "Intracellular fluid (ICF)",
            whyCorrect = "ICF makes up about 2/3 of total body water (approx 40% of body weight).",
            whyIncorrect = "ECF (plasma + interstitial) makes up 1/3. Transcellular is minor.",
            clinicalImportance = "Dehydration affects ECF first, but severe dehydration depletes ICF.",
            examinerTip = "Body water = 60% of body weight. ICF = 40%, ECF = 20%.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Body fluid compartments.",
            relatedConcept = "General Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Osmosis refers to the passive movement of:",
            options = listOf("Solutes against their gradient", "Water down its concentration gradient", "Ions through a pump", "Proteins across a membrane"),
            correctAnswer = "Water down its concentration gradient",
            whyCorrect = "Osmosis is the net movement of water across a semipermeable membrane from an area of higher water concentration to lower water concentration.",
            whyIncorrect = "Movement of solutes is diffusion. Active transport uses pumps.",
            clinicalImportance = "IV fluids (isotonic, hypertonic, hypotonic) alter plasma osmolality, driving fluid shifts.",
            examinerTip = "Water follows salt.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Basic transport mechanisms.",
            relatedConcept = "Cell Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Which of the following is an example of positive feedback?",
            options = listOf("Blood glucose regulation", "Body temperature regulation", "Oxytocin release during childbirth", "Blood pressure regulation"),
            correctAnswer = "Oxytocin release during childbirth",
            whyCorrect = "Uterine contractions stimulate oxytocin release, which causes stronger contractions, leading to more oxytocin release until birth.",
            whyIncorrect = "Glucose, temperature, and BP are regulated by negative feedback.",
            clinicalImportance = "Positive feedback loops are rare and usually culminate in a specific event (childbirth, blood clotting, action potential generation).",
            examinerTip = "Negative feedback maintains homeostasis; positive feedback produces a rapid change.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Homeostatic control systems.",
            relatedConcept = "General Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which cells are primarily responsible for adaptive immunity?",
            options = listOf("Neutrophils", "Eosinophils", "Lymphocytes", "Monocytes"),
            correctAnswer = "Lymphocytes",
            whyCorrect = "B and T lymphocytes mediate adaptive (specific) immunity.",
            whyIncorrect = "Neutrophils and monocytes are phagocytic (innate). Eosinophils handle parasites.",
            clinicalImportance = "HIV attacks CD4+ T-lymphocytes, compromising adaptive immunity.",
            examinerTip = "T cells = cell-mediated, B cells = humoral.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Types of leukocytes.",
            relatedConcept = "Blood Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "What is the normal lifespan of a red blood cell?",
            options = listOf("30 days", "60 days", "120 days", "365 days"),
            correctAnswer = "120 days",
            whyCorrect = "RBCs circulate for about 120 days before being destroyed by macrophages in the spleen.",
            whyIncorrect = "Platelets live 8-10 days. WBCs vary from hours to years.",
            clinicalImportance = "HbA1c reflects average blood glucose over the 120-day lifespan of an RBC.",
            examinerTip = "120 days = roughly 3 months.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "RBC characteristics.",
            relatedConcept = "Blood Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which factor initiates the extrinsic pathway of blood coagulation?",
            options = listOf("Factor XII", "Tissue factor", "Thrombin", "Fibrinogen"),
            correctAnswer = "Tissue factor",
            whyCorrect = "Damage to tissues exposes Tissue Factor (Factor III), triggering the extrinsic pathway via Factor VII.",
            whyIncorrect = "Factor XII initiates the intrinsic pathway. Thrombin and Fibrinogen are common pathway.",
            clinicalImportance = "Prothrombin Time (PT) assesses the extrinsic pathway.",
            examinerTip = "Extrinsic = tissue trauma = Tissue Factor.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Coagulation cascade.",
            relatedConcept = "Blood Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The primary function of hemoglobin is to:",
            options = listOf("Fight infection", "Transport oxygen", "Clot blood", "Regulate pH alone"),
            correctAnswer = "Transport oxygen",
            whyCorrect = "Hemoglobin binds reversibly with oxygen in the lungs and releases it in the tissues.",
            whyIncorrect = "WBCs fight infection, platelets clot blood.",
            clinicalImportance = "Anemia is a reduction in Hb, leading to fatigue and reduced exercise tolerance.",
            examinerTip = "One Hb molecule binds 4 oxygen molecules.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "RBC functional anatomy.",
            relatedConcept = "Blood Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "What blood group is the universal donor for red blood cells?",
            options = listOf("O positive", "AB positive", "O negative", "A negative"),
            correctAnswer = "O negative",
            whyCorrect = "O- RBCs lack A, B, and Rh antigens, meaning they won't trigger an immune reaction in the recipient.",
            whyIncorrect = "AB+ is the universal recipient. O+ has the Rh antigen.",
            clinicalImportance = "O- blood is used in emergency transfusions when blood type is unknown.",
            examinerTip = "Donor = O-, Recipient = AB+.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Blood typing.",
            relatedConcept = "Blood Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The resting membrane potential of a typical neuron is approximately:",
            options = listOf("0 mV", "+30 mV", "-70 mV", "-90 mV"),
            correctAnswer = "-70 mV",
            whyCorrect = "A typical neuron RMP is around -70 mV, interior negative relative to exterior.",
            whyIncorrect = "Cardiac muscle RMP is -90 mV. +30 mV is the peak of an action potential.",
            clinicalImportance = "Hypokalemia hyperpolarizes the membrane, making it harder to fire an AP.",
            examinerTip = "Mainly determined by K+ leak channels.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Resting Membrane Potential.",
            relatedConcept = "Nerve Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "The upstroke of a neuronal action potential is caused by the influx of:",
            options = listOf("Potassium (K+)", "Calcium (Ca2+)", "Sodium (Na+)", "Chloride (Cl-)"),
            correctAnswer = "Sodium (Na+)",
            whyCorrect = "Depolarization is caused by the opening of voltage-gated Na+ channels and rapid Na+ influx.",
            whyIncorrect = "K+ efflux causes repolarization. Ca2+ causes neurotransmitter release at terminals.",
            clinicalImportance = "Local anesthetics block voltage-gated Na+ channels, preventing pain transmission.",
            examinerTip = "Na+ IN = Depolarization. K+ OUT = Repolarization.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Action potential phases.",
            relatedConcept = "Nerve Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Saltatory conduction occurs in:",
            options = listOf("Unmyelinated nerve fibers", "Myelinated nerve fibers", "Muscle fibers", "Dendrites"),
            correctAnswer = "Myelinated nerve fibers",
            whyCorrect = "Action potentials jump from one node of Ranvier to the next in myelinated fibers, speeding up conduction.",
            whyIncorrect = "Continuous conduction occurs in unmyelinated fibers and is much slower.",
            clinicalImportance = "Multiple sclerosis destroys myelin, impairing saltatory conduction.",
            examinerTip = "Saltare = to leap.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Nerve conduction velocity.",
            relatedConcept = "Nerve Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "The absolute refractory period of a nerve action potential is due to:",
            options = listOf("Slow closing of K+ channels", "Inactivation of Na+ channels", "Depletion of ATP", "Calcium influx"),
            correctAnswer = "Inactivation of Na+ channels",
            whyCorrect = "During the absolute refractory period, another AP cannot be generated because voltage-gated Na+ channels are inactivated.",
            whyIncorrect = "Relative refractory period is due to continued K+ efflux (hyperpolarization).",
            clinicalImportance = "Ensures one-way propagation of action potentials and limits maximum firing rate.",
            examinerTip = "Absolute = Na+ inactivated. Relative = K+ open.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Refractory periods.",
            relatedConcept = "Nerve Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "What neurotransmitter is released at the neuromuscular junction?",
            options = listOf("Dopamine", "Serotonin", "Norepinephrine", "Acetylcholine (ACh)"),
            correctAnswer = "Acetylcholine (ACh)",
            whyCorrect = "Motor neurons release ACh, which binds to nicotinic receptors on the motor end plate.",
            whyIncorrect = "Norepinephrine is sympathetic. Dopamine/Serotonin are central.",
            clinicalImportance = "Myasthenia gravis involves antibodies against ACh receptors, causing muscle weakness.",
            examinerTip = "NMJ = ALWAYS Acetylcholine.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Synaptic transmission.",
            relatedConcept = "Neuromuscular Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Calcium binds to which protein to initiate skeletal muscle contraction?",
            options = listOf("Tropomyosin", "Myosin", "Actin", "Troponin C"),
            correctAnswer = "Troponin C",
            whyCorrect = "Ca2+ binds to Troponin C, causing a conformational change that moves tropomyosin away from the myosin-binding sites on actin.",
            whyIncorrect = "Myosin binds actin. Tropomyosin blocks the binding site.",
            clinicalImportance = "Lack of ATP post-mortem prevents detachment of myosin from actin, causing rigor mortis.",
            examinerTip = "Troponin C = Calcium binding.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Excitation-Contraction Coupling",
            relatedConcept = "Muscle Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "A motor unit consists of:",
            options = listOf("One muscle fiber and its nerve", "One motor neuron and all the muscle fibers it innervates", "A fascicle of muscle fibers", "The entire muscle and its nerve supply"),
            correctAnswer = "One motor neuron and all the muscle fibers it innervates",
            whyCorrect = "A motor unit is the fundamental functional unit of muscle contraction.",
            whyIncorrect = "Fine control requires small motor units (1 neuron:few fibers).",
            clinicalImportance = "EMG testing evaluates motor unit action potentials to differentiate neuropathies and myopathies.",
            examinerTip = "All-or-none principle applies to the motor unit.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Motor Unit anatomy.",
            relatedConcept = "Neuromuscular Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Type I muscle fibers (slow-twitch) are characterized by:",
            options = listOf("Fast fatiguability", "High glycolytic enzyme content", "Low capillary density", "High number of mitochondria"),
            correctAnswer = "High number of mitochondria",
            whyCorrect = "Type I fibers are oxidative, endurance-oriented fibers (red muscle).",
            whyIncorrect = "Type IIb are fast-twitch, glycolytic, quickly fatiguing (white muscle).",
            clinicalImportance = "Postural muscles (e.g., soleus) have predominant Type I fibers. Endurance training enhances them.",
            examinerTip = "Type I = Slow Oxidative = Red = Endurance.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Muscle fiber types.",
            relatedConcept = "Exercise Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which type of muscle contraction involves constant tension while the muscle length changes?",
            options = listOf("Isometric", "Isotonic", "Isokinetic", "Eccentric only"),
            correctAnswer = "Isotonic",
            whyCorrect = "Isotonic (same tension) allows the muscle to shorten (concentric) or lengthen (eccentric) under a constant load.",
            whyIncorrect = "Isometric = length does not change. Isokinetic = velocity is constant.",
            clinicalImportance = "Most functional gym exercises (e.g., bicep curls) are isotonic.",
            examinerTip = "Iso = same, Tonic = tension. Iso = same, Metric = length.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Types of muscle contraction.",
            relatedConcept = "Exercise Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Smooth muscle contraction is initiated by calcium binding to:",
            options = listOf("Troponin C", "Tropomyosin", "Calmodulin", "Myosin light chain kinase"),
            correctAnswer = "Calmodulin",
            whyCorrect = "Smooth muscle lacks troponin. Ca2+ binds calmodulin, activating MLCK, which phosphorylates myosin heads.",
            whyIncorrect = "Troponin is in skeletal/cardiac. MLCK is an enzyme activated by Ca-Calmodulin.",
            clinicalImportance = "Calcium channel blockers relax vascular smooth muscle to treat hypertension.",
            examinerTip = "Skeletal/Cardiac = Troponin. Smooth = Calmodulin.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Smooth muscle physiology.",
            relatedConcept = "Muscle Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The delay in the transmission of the action potential at the AV node allows for:",
            options = listOf("Atrial repolarization to complete", "Complete ventricular filling before contraction", "The Purkinje fibers to rest", "Ventricular repolarization"),
            correctAnswer = "Complete ventricular filling before contraction",
            whyCorrect = "The AV node delay ensures the atria contract and empty their blood into the ventricles before ventricular systole begins.",
            whyIncorrect = "Without the delay, atria and ventricles would contract simultaneously.",
            clinicalImportance = "Heart block occurs when this delay becomes too long or transmission fails completely.",
            examinerTip = "AV node = The 'toll booth' slowing down the signal.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Cardiac conduction.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which phase of the cardiac cycle represents ventricular filling?",
            options = listOf("Systole", "Diastole", "Isovolumetric contraction", "Ejection"),
            correctAnswer = "Diastole",
            whyCorrect = "Diastole is the relaxation phase during which the ventricles fill with blood.",
            whyIncorrect = "Systole is contraction. Isovolumetric contraction occurs just before ejection.",
            clinicalImportance = "Tachycardia severely shortens diastole, reducing ventricular filling and cardiac output.",
            examinerTip = "Diastole = Dilates (Fills).",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Cardiac cycle phases.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Cardiac output is defined as:",
            options = listOf("Blood Pressure × Peripheral Resistance", "Heart Rate × Stroke Volume", "End Diastolic Volume - End Systolic Volume", "Venous Return"),
            correctAnswer = "Heart Rate × Stroke Volume",
            whyCorrect = "It is the volume of blood pumped by each ventricle per minute.",
            whyIncorrect = "EDV - ESV = Stroke Volume. BP = CO x TPR.",
            clinicalImportance = "Normal resting CO is roughly 5 L/min.",
            examinerTip = "CO = HR x SV.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Determinants of Cardiac Output.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The Frank-Starling law of the heart states that:",
            options = listOf("Increased EDV increases stroke volume", "Increased HR increases stroke volume", "Increased afterload increases stroke volume", "Contractility is independent of stretch"),
            correctAnswer = "Increased EDV increases stroke volume",
            whyCorrect = "Within physiological limits, a greater stretch of cardiac muscle (increased preload/EDV) results in a more forceful contraction.",
            whyIncorrect = "Increased afterload decreases SV. Increased HR does not directly increase SV.",
            clinicalImportance = "In heart failure, the Frank-Starling curve is depressed, meaning increased preload does not yield adequate SV.",
            examinerTip = "More stretch = harder contraction.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Frank-Starling mechanism.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which segment of the ECG represents ventricular depolarization?",
            options = listOf("P wave", "T wave", "QRS complex", "PR interval"),
            correctAnswer = "QRS complex",
            whyCorrect = "The QRS complex represents the rapid spread of electrical activity through the ventricles.",
            whyIncorrect = "P wave = atrial depolarization. T wave = ventricular repolarization.",
            clinicalImportance = "A widened QRS indicates a delay in ventricular conduction (e.g., bundle branch block).",
            examinerTip = "P = Atria, QRS = Ventricles (depolarize), T = Ventricles (repolarize).",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Electrocardiography.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The first heart sound (S1 or 'lub') is caused by the closure of:",
            options = listOf("Semilunar valves", "Atrioventricular (AV) valves", "Aortic valve only", "Pulmonary valve only"),
            correctAnswer = "Atrioventricular (AV) valves",
            whyCorrect = "S1 is caused by the closure of the mitral and tricuspid valves at the beginning of ventricular systole.",
            whyIncorrect = "S2 ('dub') is caused by the closure of the aortic and pulmonary valves at the end of systole.",
            clinicalImportance = "S1 is loudest at the apex of the heart.",
            examinerTip = "S1 = AV closure (systole begins). S2 = Semilunar closure (diastole begins).",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Heart sounds.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which factor primarily determines peripheral vascular resistance?",
            options = listOf("Blood volume", "Heart rate", "Arteriolar radius", "Venous return"),
            correctAnswer = "Arteriolar radius",
            whyCorrect = "According to Poiseuille's Law, resistance is inversely proportional to the radius to the 4th power. Arterioles are the primary resistance vessels.",
            whyIncorrect = "Viscosity and length also play roles but are generally constant. Radius is highly variable.",
            clinicalImportance = "Sympathetic stimulation constricts arterioles, sharply increasing blood pressure.",
            examinerTip = "Resistance is all about the arterioles.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Hemodynamics.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Baroreceptors responsible for short-term blood pressure regulation are located in the:",
            options = listOf("Vena cava", "Carotid sinus and aortic arch", "Renal arteries", "Medulla oblongata"),
            correctAnswer = "Carotid sinus and aortic arch",
            whyCorrect = "Stretch receptors in the carotid sinus and aortic arch detect changes in BP and send signals to the medulla.",
            whyIncorrect = "Renal mechanism controls long-term BP (RAAS). Medulla processes the signals.",
            clinicalImportance = "Carotid massage stimulates baroreceptors to slow heart rate in supraventricular tachycardia.",
            examinerTip = "Baroreceptors = Stretch = BP regulation.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Blood pressure regulation.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which ion is unique to the plateau phase (Phase 2) of the cardiac action potential?",
            options = listOf("Sodium (Na+)", "Potassium (K+)", "Calcium (Ca2+)", "Chloride (Cl-)"),
            correctAnswer = "Calcium (Ca2+)",
            whyCorrect = "The plateau is maintained by the slow influx of Ca2+ balancing the efflux of K+. This prolongs the action potential, allowing time for muscle contraction and preventing tetany.",
            whyIncorrect = "Phase 0 is Na+, Phase 3 is K+.",
            clinicalImportance = "Calcium channel blockers shorten the plateau and reduce contractility.",
            examinerTip = "Plateau = Calcium.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Cardiac muscle action potential.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Normal mean arterial pressure (MAP) is closest to:",
            options = listOf("120 mmHg", "80 mmHg", "90-100 mmHg", "60 mmHg"),
            correctAnswer = "90-100 mmHg",
            whyCorrect = "MAP = Diastolic BP + 1/3(Pulse Pressure). Thus, 80 + 1/3(40) = roughly 93 mmHg.",
            whyIncorrect = "120 is systolic, 80 diastole. 60 is minimum for organ perfusion.",
            clinicalImportance = "MAP is the driving pressure for organ perfusion homeostasis.",
            examinerTip = "Closer to diastolic because 2/3 of the cycle is diastole.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Hemodynamic calculations.",
            relatedConcept = "Cardiovascular Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Surfactant in the alveoli serves primarily to:",
            options = listOf("Increase gas exchange rate", "Decrease surface tension", "Kill bacteria", "Humidify air"),
            correctAnswer = "Decrease surface tension",
            whyCorrect = "Type II pneumocytes produce surfactant, which reduces surface tension of alveolar fluid, preventing alveolar collapse and reducing the work of breathing.",
            whyIncorrect = "Humidification happens in the upper airways. Macrophages kill bacteria.",
            clinicalImportance = "Premature infants lack surfactant, causing Respiratory Distress Syndrome (RDS).",
            examinerTip = "Surfactant = Surface active agent. Prevents collapse.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Pulmonary mechanics.",
            relatedConcept = "Respiratory Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which center in the brainstem generates the basic rhythm of respiration?",
            options = listOf("Pneumotaxic center in pons", "Apneustic center in pons", "Ventral respiratory group", "Dorsal respiratory group (DRG) in medulla"),
            correctAnswer = "Dorsal respiratory group (DRG) in medulla",
            whyCorrect = "The DRG contains inspiratory neurons that fire rhythmically to drive the diaphragm.",
            whyIncorrect = "Pneumotaxic limits inspiration. VRG is for forced breathing.",
            clinicalImportance = "Medullary depression by opioids or barbiturates causes fatal respiratory arrest.",
            examinerTip = "DRG = Diaphragm = Quiet breathing.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Control of breathing.",
            relatedConcept = "Respiratory Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The Bohr effect describes the effect of:",
            options = listOf("Increased temperature on increasing Hb affinity for O2", "Decreased pH and increased CO2 on releasing oxygen from Hb", "Decreased pO2 on binding CO2", "Increased pH on releasing oxygen"),
            correctAnswer = "Decreased pH and increased CO2 on releasing oxygen from Hb",
            whyCorrect = "High CO2 and H+ (low pH) in active tissues shift the oxygen-hemoglobin dissociation curve to the right, facilitating O2 offloading.",
            whyIncorrect = "The Haldane effect is the effect of O2 on CO2 binding.",
            clinicalImportance = "During exercise, the Bohr effect ensures working muscles get more oxygen.",
            examinerTip = "Bohr = Tissue. Haldane = Lungs.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Gas transport.",
            relatedConcept = "Respiratory Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Total lung capacity (TLC) is the sum of:",
            options = listOf("Tidal volume + Inspiratory reserve volume", "Inspiratory capacity + Functional residual capacity", "Vital capacity + Residual volume", "Both B and C"),
            correctAnswer = "Vital capacity + Residual volume",
            whyCorrect = "TLC = VC + RV = IC + FRC.",
            whyIncorrect = "IRV + TV = IC.",
            clinicalImportance = "In obstructive diseases (COPD), TLC and RV increase. In restrictive diseases (fibrosis), TLC decreases.",
            examinerTip = "TLC is everything. You cannot exhale RV.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Lung volumes and capacities.",
            relatedConcept = "Respiratory Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Central chemoreceptors are primarily stimulated by:",
            options = listOf("Decreased arterial pO2", "Decreased arterial pH directly", "Increased H+ concentration in the CSF derived from CO2", "Decreased blood volume"),
            correctAnswer = "Increased H+ concentration in the CSF derived from CO2",
            whyCorrect = "CO2 crosses the blood-brain barrier, forms H+ in the CSF, which directly stimulates the central chemoreceptors located in the medulla.",
            whyIncorrect = "H+ in blood cannot cross the BBB. Peripheral chemoreceptors sense decreased pO2.",
            clinicalImportance = "Chronic COPD patients rely on hypoxic drive (peripheral chemoreceptors) as central receptors adapt to chronic high CO2.",
            examinerTip = "Central = CO2 -> H+. Peripheral = Oxygen.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Chemoregulation of breathing.",
            relatedConcept = "Respiratory Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "According to Fick's Law, diffusion of a gas across the respiratory membrane is inversely proportional to:",
            options = listOf("Surface area", "Pressure gradient", "Membrane thickness", "Diffusion coefficient"),
            correctAnswer = "Membrane thickness",
            whyCorrect = "Thicker membrane means slower diffusion.",
            whyIncorrect = "Diffusion is directly proportional to surface area, pressure gradient, and diffusion coefficient.",
            clinicalImportance = "Pulmonary fibrosis increases membrane thickness, reducing diffusion capacity (DLCO).",
            examinerTip = "Thick membrane = bad diffusion.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Gas exchange.",
            relatedConcept = "Respiratory Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "What is the normal value for tidal volume (TV) in an average adult?",
            options = listOf("150 mL", "500 mL", "1200 mL", "3000 mL"),
            correctAnswer = "500 mL",
            whyCorrect = "Tidal volume is the amount of air inhaled or exhaled with each resting breath.",
            whyIncorrect = "150 mL is dead space. 1200 mL is residual volume.",
            clinicalImportance = "Monitoring TV is essential in ventilated and critically ill patients.",
            examinerTip = "TV = normal quiet breath.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Lung volumes.",
            relatedConcept = "Respiratory Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Glomerular Filtration Rate (GFR) is best estimated clinically using:",
            options = listOf("Urea clearance", "Para-aminohippuric acid (PAH) clearance", "Creatinine clearance", "Glucose clearance"),
            correctAnswer = "Creatinine clearance",
            whyCorrect = "Creatinine is freely filtered, not reabsorbed, and only slightly secreted, making its clearance a close approximation of GFR.",
            whyIncorrect = "PAH clearance measures renal plasma flow. Glucose is normally completely reabsorbed.",
            clinicalImportance = "Decreased GFR indicates acute or chronic kidney injury.",
            examinerTip = "GFR = Inulin (gold standard) or Creatinine (clinical).",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Renal clearance.",
            relatedConcept = "Renal Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "About 65% of filtered sodium and water is reabsorbed in the:",
            options = listOf("Proximal convoluted tubule (PCT)", "Loop of Henle", "Distal convoluted tubule (DCT)", "Collecting duct"),
            correctAnswer = "Proximal convoluted tubule (PCT)",
            whyCorrect = "The PCT performs bulk reabsorption of Na+, water, and 100% of glucose and amino acids.",
            whyIncorrect = "The loop of Henle handles concentration. The DCT and collecting duct handle fine-tuning.",
            clinicalImportance = "Carbonic anhydrase inhibitors work in the PCT. Osmotic diuretics also act here.",
            examinerTip = "PCT = the workhorse of the nephron.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Tubular function.",
            relatedConcept = "Renal Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which hormone increases water reabsorption in the collecting ducts?",
            options = listOf("Aldosterone", "Antidiuretic hormone (ADH)", "Atrial natriuretic peptide (ANP)", "Renin"),
            correctAnswer = "Antidiuretic hormone (ADH)",
            whyCorrect = "ADH inserts aquaporins into the collecting duct principal cells, increasing water permeability.",
            whyIncorrect = "Aldosterone increases Na+ reabsorption and K+ secretion. ANP promotes Na+ excretion.",
            clinicalImportance = "Diabetes insipidus lacks ADH, causing massive polyuria.",
            examinerTip = "ADH = Anti-diuresis = Keeps water.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Water balance.",
            relatedConcept = "Renal Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The primary function of bile salts in digestion is to:",
            options = listOf("Digest proteins", "Absorb vitamin B12", "Emulsify fats", "Neutralize stomach acid"),
            correctAnswer = "Emulsify fats",
            whyCorrect = "Bile salts act as detergents to break fat droplets into smaller micelles, increasing surface area for pancreatic lipase.",
            whyIncorrect = "Pepsin digests proteins. Secretin stimulates bicarbonate to neutralize acid.",
            clinicalImportance = "Gallbladder disease or biliary obstruction impairs fat digestion, leading to steatorrhea.",
            examinerTip = "Bile = Emulsification.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Lipid digestion.",
            relatedConcept = "Gastrointestinal Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which cells in the stomach secrete hydrochloric acid (HCl)?",
            options = listOf("Chief cells", "G cells", "Parietal cells", "Mucous neck cells"),
            correctAnswer = "Parietal cells",
            whyCorrect = "Parietal (oxyntic) cells secrete HCl and intrinsic factor.",
            whyIncorrect = "Chief cells secrete pepsinogen. G cells secrete gastrin.",
            clinicalImportance = "Proton pump inhibitors (PPIs) target parietal cells to treat GERD and peptic ulcers.",
            examinerTip = "Parietal = Protons (H+). Chief = Pepsinogen.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Gastric secretions.",
            relatedConcept = "Gastrointestinal Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The majority of nutrient absorption occurs in the:",
            options = listOf("Stomach", "Duodenum only", "Small intestine", "Large intestine"),
            correctAnswer = "Small intestine",
            whyCorrect = "The extensive surface area (villi and microvilli) of the jejunum and ileum handles almost all nutrient absorption.",
            whyIncorrect = "The stomach absorbs alcohol and aspirin. Large intestine absorbs water and electrolytes.",
            clinicalImportance = "Conditions like Celiac disease destroy villi, causing malabsorption.",
            examinerTip = "Small intestine = Absorption.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Digestion and Absorption.",
            relatedConcept = "Gastrointestinal Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which hormone lowers blood glucose levels?",
            options = listOf("Glucagon", "Cortisol", "Epinephrine", "Insulin"),
            correctAnswer = "Insulin",
            whyCorrect = "Insulin promotes cellular uptake and storage of glucose, lowering blood glucose.",
            whyIncorrect = "Glucagon, cortisol, and epinephrine are all counter-regulatory hormones that raise blood glucose.",
            clinicalImportance = "Type 1 diabetes requires exogenous insulin; Type 2 involves insulin resistance.",
            examinerTip = "Insulin puts glucose IN to cells.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Endocrine control of metabolism.",
            relatedConcept = "Endocrine Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Calcium homeostasis is primarily regulated by:",
            options = listOf("Thyroid hormone", "Parathyroid hormone (PTH)", "Aldosterone", "Calcitonin"),
            correctAnswer = "Parathyroid hormone (PTH)",
            whyCorrect = "PTH increases blood calcium by stimulating osteoclasts, increasing renal absorption, and activating Vitamin D.",
            whyIncorrect = "Calcitonin acts to lower calcium, but its role is minor in adult humans.",
            clinicalImportance = "Hyperparathyroidism causes hypercalcemia and osteoporosis.",
            examinerTip = "PTH = Puts Calcium High in blood.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Calcium metabolism.",
            relatedConcept = "Endocrine Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which anterior pituitary hormone stimulates the adrenal cortex?",
            options = listOf("Growth Hormone (GH)", "Thyroid Stimulating Hormone (TSH)", "Adrenocorticotropic hormone (ACTH)", "Prolactin"),
            correctAnswer = "Adrenocorticotropic hormone (ACTH)",
            whyCorrect = "ACTH stimulates the adrenal cortex to release cortisol.",
            whyIncorrect = "TSH stimulates thyroid. GH acts on liver/bone.",
            clinicalImportance = "Excess ACTH from a pituitary adenoma causes Cushing's disease.",
            examinerTip = "ACTH targets the adrenal glands.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Hypothalamic-pituitary axis.",
            relatedConcept = "Endocrine Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "The 'fight or flight' response is mediated by the release of epinephrine from the:",
            options = listOf("Adrenal cortex", "Adrenal medulla", "Anterior pituitary", "Posterior pituitary"),
            correctAnswer = "Adrenal medulla",
            whyCorrect = "Sympathetic stimulation acts directly on the adrenal medulla to release epinephrine and norepinephrine into the blood.",
            whyIncorrect = "Adrenal cortex releases cortisol, aldosterone, and androgens.",
            clinicalImportance = "Pheochromocytoma is a tumor of the medulla causing severe sympathetic overactivity.",
            examinerTip = "Medulla = Middle = Epinephrine.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Autonomic endocrinology.",
            relatedConcept = "Endocrine Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Ovulation is triggered by a surge in which hormone?",
            options = listOf("Follicle Stimulating Hormone (FSH)", "Estrogen", "Progesterone", "Luteinizing Hormone (LH)"),
            correctAnswer = "Luteinizing Hormone (LH)",
            whyCorrect = "A large surge of LH at mid-cycle causes the mature follicle to rupture, releasing the ovum.",
            whyIncorrect = "FSH stimulates follicle growth. Progesterone maintains the secretory endometrium.",
            clinicalImportance = "Ovulation predictor kits measure LH in urine.",
            examinerTip = "LH Surge = Ovulation.",
            subject = "Physiology",
            difficulty = "High Yield",
            learningPoint = "Menstrual cycle.",
            relatedConcept = "Reproductive Physiology",
            reference = "Ganong Review"
        ),
        VivaMcq(
            question = "Testosterone is produced in the testes by:",
            options = listOf("Sertoli cells", "Leydig cells", "Spermatogonia", "Epididymis"),
            correctAnswer = "Leydig cells",
            whyCorrect = "Leydig (interstitial) cells produce testosterone under the influence of LH.",
            whyIncorrect = "Sertoli cells nourish sperm under the influence of FSH.",
            clinicalImportance = "Testosterone promotes secondary sex characteristics and muscle hypertrophy.",
            examinerTip = "Leydig = LH = Testosterone. Sertoli = Sperm nourishment.",
            subject = "Physiology",
            difficulty = "Moderate",
            learningPoint = "Male reproduction.",
            relatedConcept = "Reproductive Physiology",
            reference = "Guyton & Hall"
        ),
        VivaMcq(
            question = "Which part of the brain is primarily responsible for coordinating movement and balance?",
            options = listOf("Cerebrum", "Cerebellum", "Hypothalamus", "Thalamus"),
            correctAnswer = "Cerebellum",
            whyCorrect = "The cerebellum compares intention with actual movement and makes corrective adjustments.",
            whyIncorrect = "Motor cortex initiates movement. Basal ganglia plan and initiate.",
            clinicalImportance = "Cerebellar lesions cause ataxia, intention tremor, and dysmetria.",
            examinerTip = "Cerebellum = Coordination.",
            subject = "Physiology",
            difficulty = "Easy",
            learningPoint = "Motor control systems.",
            relatedConcept = "Central Nervous System",
            reference = "Ganong Review"
        )
    ) + ExerciseTherapyData.questions + ExerciseTherapyIIData.questions + ElectrotherapyIData.questions + ElectrotherapyIIData.questions + BiomechanicsData.questions + KinesiologyData.questions

    @Volatile var vivaQuestions = initialVivaQuestions

    val initialClinicalPostingCategories = listOf(
        ClinicalPostingCategory(
            id = "cp_stroke",
            name = "Neuro: Stroke",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Stroke is an acute neurological deficit caused by interruption of cerebral blood flow (ischemic or hemorrhagic). It results in upper motor neuron signs, hemiparesis, abnormal tone, and sensory deficits."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Posture (asymmetry, neglect)",
                        "Gait (hemiplegic gait, circumduction)",
                        "Movement (synergy patterns, associated reactions)",
                        "Assistive devices (AFO, cane)",
                        "Balance (leaning towards unaffected side)",
                        "Facial asymmetry (droop)",
                        "Shoulder subluxation on affected side"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Presenting complaint: When did the weakness start?",
                        "History: Was the onset sudden or gradual?",
                        "Duration: How long have symptoms been present?",
                        "Aggravating/Relieving factors: What makes transitions or mobility easier or harder?",
                        "Medical history: HTN, Diabetes, Hyperlipidemia, previous TIAs?",
                        "Medication history: Blood thinners, anti-hypertensives?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Inspection: Muscle wasting, trophic changes, posture",
                        "Palpation: Tone (spasticity vs flaccidity), shoulder sulcus sign",
                        "ROM: Active vs Passive (note contractures)",
                        "MMT: Specific muscle groups, Brunnstrom staging",
                        "Special tests: Modified Ashworth Scale, Tardieu Scale",
                        "Functional tests: Sit-to-stand, reaching tasks",
                        "Outcome measures: BBS, STREAM, FMA"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Sudden worsening of neurological signs",
                        "Severe headache (suspect hemorrhagic expansion)",
                        "Uncontrolled hypertension (SBP > 180 mmHg)",
                        "Signs of DVT in paralyzed leg",
                        "Seizure activity",
                        "Severe dysphagia (choking risk during oral intake)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What is the difference between UMN and LMN lesion?",
                        "Describe Brunnstrom's stages of motor recovery.",
                        "How do you assess for shoulder subluxation?",
                        "What is the typical flexor synergy in the upper limb?",
                        "What is the difference between spasticity and rigidity?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Hemiparesis (contralateral to lesion)",
                        "Spasticity (clasp-knife presentation) in anti-gravity muscles",
                        "Hyperreflexia and positive Babinski sign",
                        "Impaired proprioception and tactile sensation",
                        "Aphasia (if dominant hemisphere is affected)",
                        "Dysphagia and dysarthria"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Patient reports inability to stand up from chair independently. Denies pain. States right leg feels 'heavy'.",
                        "O: HR: 78, BP: 130/80. Mod. Ashworth Scale: R Biceps (2), R Gastroc (3). Requires mod assist to stand.",
                        "A: Impaired motor control and increased tone in R limbs limiting safe independent transfers and gait.",
                        "P: WB activities on R leg. Sit-to-stand training (10 reps x 3 sets). Stretching R gastroc."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: R-sided hemiplegia, abnormal flexor tone in R UE, abnormal extensor tone in R LE, decreased balance.",
                        "Activity Limitations: Inability to perform independent sit-to-stand, difficulty with dressing, slow and unsafe gait.",
                        "Participation Restrictions: Unable to return to employment, inability to attend community events."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "Short-Term: Patient will perform sit-to-stand with min assist using standard chair within 1 week.",
                        "Long-Term: Patient will ambulate 50m independently with a quad cane on level surfaces within 4 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Berg Balance Scale (BBS): Assess fall risk. Max 56. <45 high fall risk.",
                        "Fugl-Meyer (FMA): Motor recovery evaluation.",
                        "Modified Ashworth Scale (MAS): Measure spasticity."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Always assess tone before strengthening.",
                        "Protect the flaccid shoulder during transfers; never pull on the arm.",
                        "Encourage weight-bearing on the affected side early."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_parkinsons",
            name = "Neuro: Parkinson's",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Parkinson's Disease is a progressive neurodegenerative movement disorder affecting the basal ganglia. It is characterized by bradykinesia, rigidity, resting tremor, and postural instability."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Posture: Stooped posture, forward head, kyphosis",
                        "Gait: Festinating gait, magnetic feet, absent arm swing",
                        "Movement: Bradykinesia, freezing of gait (FOG)",
                        "Tremor: Pill-rolling rest tremor",
                        "Facial expression: Hypomimia"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Presenting complaint: Tremors, slowness, or recurrent falls?",
                        "Onset: Gradual? Usually asymmetrical onset.",
                        "Medication history: On-Off cycles of Levodopa?",
                        "Aggravating factors: Does stress worsen tremor?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Inspection: Masked facies, resting tremor",
                        "Palpation: Cogwheel or lead-pipe rigidity",
                        "Functional tests: Timed Up and Go (TUG), Pull test (retropulsion)",
                        "Outcome measures: UPDRS, Hoehn & Yahr"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Sudden severe orthostatic hypotension",
                        "Severe dysphagia (aspiration pneumonia risk)",
                        "Frequent unprovoked backward falls (PSP differential)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What is the hallmark triad of Parkinson's disease?",
                        "Differentiate between cogwheel and lead-pipe rigidity.",
                        "What is the Hoehn & Yahr scale?",
                        "Explain the 'On-Off' phenomenon."
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Resting tremor (3-5 Hz)",
                        "Bradykinesia and hypokinesia",
                        "Rigidity (increased resistance to passive movement)",
                        "Postural instability"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Patient complains of 'freezing' when passing through doorways. Reports 2 near-falls.",
                        "O: TUG time: 24 seconds. Positive pull test. Rigid tone in bilateral Biceps and Hamstrings.",
                        "A: Postural instability and bradykinesia causing functional gait deficits.",
                        "P: LSVT BIG protocol. Rhythmic auditory stimulation (metronome) for gait training."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Axial rigidity, bradykinesia, impaired postural reflexes.",
                        "Activity Limitations: Freezing of gait, difficulty standing up from low chairs.",
                        "Participation Restrictions: Fear of falling prevents leaving the house."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "Short-Term: Patient will safely execute a doorway crossing using visual cues within 3 sessions.",
                        "Long-Term: Patient will improve TUG score to < 15 seconds within 4 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "UPDRS: Disease status progression.",
                        "Hoehn & Yahr: Clinical staging.",
                        "Mini-BESTest: Dynamic balance and fall risk."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Assess during ON medication phase.",
                        "Use external cues (lines, metronome) to bypass basal ganglia.",
                        "Always train large amplitude movements."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_sci",
            name = "Neuro: SCI",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Spinal Cord Injury (SCI) results in sensory-motor deficit and autonomic dysfunction below the level of the lesion."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Posture in wheelchair",
                        "Breathing pattern (diaphragmatic)",
                        "Muscle atrophy below injury level",
                        "Skin integrity (sacrum, heels)"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Level of injury?",
                        "Bowel and bladder routine?",
                        "Any recent episodes of autonomic dysreflexia?",
                        "Any pressure sores?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Neurological: ASIA Scale",
                        "Respiratory: Vital capacity",
                        "ROM: Assess need for tenodesis",
                        "Functional: Transfers, WC skills"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Autonomic Dysreflexia (Medical Emergency!)",
                        "Orthostatic Hypotension",
                        "DVT",
                        "Heterotopic Ossification"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What is spinal shock?",
                        "Define ASIA Impairment Scale.",
                        "What is Autonomic Dysreflexia?",
                        "Explain the tenodesis grasp."
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Loss of voluntary motor control below lesion",
                        "Hyperreflexia or spasticity (after shock)",
                        "Neurogenic bladder/bowel"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Patient states 'I get dizzy sitting up'.",
                        "O: BP lying: 110/70. BP sitting: 85/55. Patient pale.",
                        "A: Orthostatic hypotension limiting verticalization.",
                        "P: Progressive head-up tilt table. Use abdominal binder."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Paraplegia, sensory loss, impaired respiratory mechanics.",
                        "Activity Limitations: Dependent for transfers, inability to walk.",
                        "Participation Restrictions: Environmental barriers in community."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "Short-Term: Tolerate sitting on edge of bed for 15 mins within 1 week.",
                        "Long-Term: Independent sliding board transfer within 4 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "ASIA Impairment Scale: Neurological level.",
                        "SCIM: Functional capacity.",
                        "WISCI II: Gait assist level."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Never stretch the long finger flexors of a C6/C7 tetraplegic.",
                        "Check BP if patient gets pounding headache.",
                        "Pressure relief every 15-30 mins in WC."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_cp",
            name = "Neuro: Cerebral Palsy",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Cerebral Palsy (CP) is a non-progressive central motor deficit occurring in the developing brain, leading to abnormal tone and movement."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Posture: W-sitting, scissoring",
                        "Gait: Toe-walking, crouch gait",
                        "Assistive devices: AFOs"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Birth history (NICU, premature)?",
                        "Developmental milestones achieved?",
                        "History of seizures?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Tone: Tardieu Scale vs Ashworth",
                        "Gross Motor: GMFCS Level",
                        "Reflexes: ATNR, STNR holding"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Silent hip subluxation",
                        "Severe feeding difficulties",
                        "Uncontrolled seizures"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What are the types of CP?",
                        "Differentiate Spasticity from Contracture using Tardieu.",
                        "What is the GMFCS?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Spasticity in hip adductors, hamstrings, calf",
                        "Delayed milestones",
                        "Retention of primitive reflexes"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Parent reports child tripping frequently.",
                        "O: Spastic Diplegia (GMFCS II). MAS 2+ in bilateral calves. Dynamic toe walking.",
                        "A: Increased calf tone resulting in frequent tripping.",
                        "P: Stretching to gastroc. Gait training with auditory cues. Recommend AFOs."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Increased LE tone, decreased passive DF.",
                        "Activity Limitations: Delayed walking, frequent falls.",
                        "Participation Restrictions: Excluded from playground activities."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "Short-Term: Increase passive DF to neutral within 4 weeks.",
                        "Long-Term: Ambulate 20m independently without toe-walking within 8 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "GMFM: Gross motor tracking.",
                        "GMFCS: Functional classification.",
                        "Tardieu Scale: Exact spasticity measure."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Differentiate dynamic spasticity from fixed contracture.",
                        "Avoid W-sitting.",
                        "Disguise therapy as play."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_bells",
            name = "Neuro: Bell's Palsy",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Bell's Palsy is an acute idiopathic peripheral facial nerve (CN VII) palsy resulting in unilateral facial muscle weakness."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Facial symmetry at rest",
                        "Loss of forehead wrinkles",
                        "Inability to close eye",
                        "Drooling"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Onset sudden?",
                        "Altered taste sensation?",
                        "Hyperacusis?",
                        "Dry eye?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "CN VII testing",
                        "Observe for synkinesis",
                        "Outcome: House-Brackmann Scale"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Forehead sparing (implies Stroke, UMN!)",
                        "Bilateral facial palsy",
                        "Corneal ulceration"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "UMN vs LMN facial palsy?",
                        "Why hyperacusis?",
                        "What is Bell's phenomenon?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Unilateral flaccid facial paralysis affecting upper AND lower face",
                        "Positive Bell's phenomenon"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Woke up unable to move right side of face.",
                        "O: R hemifacial weakness. Unable to close R eye. House-Brackmann Grade IV.",
                        "A: Acute facial nerve palsy affecting eye protection.",
                        "P: Edu on eye care (artificial tears/tape). Facial PNF in front of mirror."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Flaccid paralysis of facial muscles, absent tearing.",
                        "Activity Limitations: Difficulty drinking, inability to close eye.",
                        "Participation Restrictions: Social embarrassment."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "Short-Term: Demonstrate safe eye taping protocol.",
                        "Long-Term: Achieve House-Brackmann Grade II within 12 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "House-Brackmann Scale: Facial nerve function grading."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Protect the cornea! Taping at night is mandatory.",
                        "Forehead sparing = Stroke.",
                        "Avoid early aggressive E-Stim (causes synkinesis)."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_oa_knee",
            name = "Ortho: OA Knee",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Osteoarthritis of the knee is a degenerative joint disease characterized by cartilage breakdown and osteophyte formation causing pain and stiffness."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Posture: Genu varum",
                        "Gait: Antalgic gait, lateral trunk lean",
                        "Joint appearance: Bony enlargement, quadriceps atrophy"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Pain worse with weight-bearing?",
                        "Morning stiffness < 30 mins?",
                        "Crepitus or grinding sound?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Palpation: Joint line tenderness",
                        "ROM: Loss of terminal extension",
                        "MMT: Quadriceps weakness",
                        "Functional: 30-sec Chair Stand"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Hot, acutely swollen joint (Septic/Gout)",
                        "True locking of the joint",
                        "Calf swelling (DVT)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Radiographic findings of OA? (LOSS)",
                        "Why varus deformity in medial OA?",
                        "Role of gluteus medius in knee OA?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Activity-related joint pain",
                        "Crepitus on active motion",
                        "Reduced ROM (capsular pattern)"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 65 y/o female reports bilateral knee pain worse on stairs. Stiffness lasts 15 mins.",
                        "O: Gait antalgic. AROM R knee 5-110 deg. MMT quad 4/5. Crepitus noted.",
                        "A: Decreased LE strength limiting stair negotiation.",
                        "P: Stationary bike for joint nutrition. SLR, SAQ, Clamshells."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Knee joint pain, reduced terminal extension, quad weakness.",
                        "Activity Limitations: Difficulty climbing stairs, sit-to-stand transitions.",
                        "Participation Restrictions: Unable to walk community distances."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Increase R knee flexion to 120 deg within 2 weeks.",
                        "LTG: Complete 5 flights of stairs pain-free within 6 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "WOMAC: Pain, stiffness, physical function.",
                        "TUG: Mobility screening.",
                        "VAS: Pain grading."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Check quadriceps wasting.",
                        "Treat the hip (Glute Med) to offload the knee.",
                        "Motion is lotion' - promote cyclic joint loading."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_thr",
            name = "Ortho: THR",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Total Hip Replacement involves surgical excision of the head/neck of the femur and acetabular surface, replacing them with prosthetics."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Incision site",
                        "Leg length discrepancy",
                        "Gait (Trendelenburg)"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Surgical approach used?",
                        "Current pain score?",
                        "Can you demonstrate hip precautions?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "ROM within safe limits",
                        "Isometric strength testing",
                        "Functional mobility (sit-to-stand techniques)"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Hip dislocation (severe acute pain, shortening/rotation)",
                        "DVT/PE",
                        "Infection"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Posterior vs Anterior hip precautions?",
                        "Indications for THR?",
                        "Role of abduction pillow?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Surgical incisional pain",
                        "Weakness in hip abductors/extensors",
                        "Dependence on walker initially"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: POD 2 THR (Posterior). Understands precautions.",
                        "O: Incision clean. MMT Quad isometric strong. Transferred bed to chair min assist holding precautions.",
                        "A: Good functional mobility progressing as expected.",
                        "P: Review precautions. Gait training with walker (100 ft). Glute sets."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Surgical tissue trauma, pain, decreased hip ROM/strength.",
                        "Activity Limitations: Inability to transition sit-to-stand without assist.",
                        "Participation Restrictions: Driving restrictions early on."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Ambulate 150 ft with walker supervise assist within 3 days.",
                        "LTG: Walk community distance independently without walker within 8 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Harris Hip Score: Clinical/functional outcome.",
                        "LEFS: Lower Extremity Functional Scale."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Posterior approach = avoid flexion > 90, adduction, internal rotation.",
                        "Watch for foot drop (sciatic nerve palsy)."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_tkr",
            name = "Ortho: TKR",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Total Knee Replacement involves replacing articular surfaces of the knee, usually for advanced osteoarthritis."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Incision site",
                        "Loss of terminal extension",
                        "Stiff knee gait"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Is pain controlled?",
                        "Are you doing home exercises?",
                        "Can you get your knee completely straight?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "ROM: Must measure extension (0 deg target)",
                        "Strength: Watch for quad lag during SLR",
                        "Patellar mobility"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "DVT (calf swelling)",
                        "Infection",
                        "Extensor mechanism rupture (cannot active extend)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What is a 'quad lag'?",
                        "Why is terminal extension critical?",
                        "PCL retaining vs substituting?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Significant post-op pain",
                        "Profound quadriceps inhibition",
                        "Restricted ROM due to swelling"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: POD 5 TKR. Reports stiffness.",
                        "O: AROM 10-85 deg (lacking 10 ext). SLR shows 10 deg quad lag. Mod effusion.",
                        "A: Extension deficit and quad lag limiting gait mechanics.",
                        "P: Passive knee extension with heel prop. TKEs with biofeedback."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Extension deficit of 10 degrees, quad inhibition.",
                        "Activity Limitations: Unable to walk without walker.",
                        "Participation Restrictions: Cannot drive."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Achieve 0 deg extension within 2 weeks.",
                        "LTG: Community ambulation independently without assistive device within 6 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Knee Society Score (KSS).",
                        "WOMAC Index.",
                        "TUG."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Get the knee straight! Focus on terminal extension (0 deg) BEFORE forced flexion.",
                        "Cryotherapy is essential post-exercise."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_frozen_shoulder",
            name = "Ortho: Frozen Shoulder",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Adhesive Capsulitis is a condition of insidious onset characterized by severe pain and global restriction of active and passive shoulder ROM."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Scapular hiking (shoulder shrugging)",
                        "Loss of arm swing during gait",
                        "Protective positioning of arm"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Diabetic?",
                        "Pain waking you at night?",
                        "Which phase (Freezing, Frozen, Thawing)?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "ROM: Capsular pattern (ER > Abd > IR)",
                        "Joint Play: Reduced inferior/anterior mobility"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Constant, unremitting night pain (rule out cancer)",
                        "History of trauma (rule out massive RCT)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Capsular pattern of the shoulder?",
                        "Three phases of adhesive capsulitis?",
                        "Why is diabetes a risk factor?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Equal limitation of active and passive ROM",
                        "Hard capsular end-feel",
                        "Pain radiating to deltoid insertion"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 55 y/o diabetic female reports severe L shoulder pain disrupting sleep.",
                        "O: PROM Flex 90, ER 20, IR 40. Hard end-feel. Escapular hiking > 70 deg flex.",
                        "A: Freezing phase adhesive capsulitis.",
                        "P: Gentle Grade I mobilizations for pain relief. Pendulum exercises. Edu on pacing."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Severe capsular restriction (ER primarily), night pain.",
                        "Activity Limitations: Unable to wash hair, hook bra.",
                        "Participation Restrictions: Reaching required for work."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Sleep 6 continuous hours pain-free within 2 weeks.",
                        "LTG: Improve functional elevation to 140 deg within 12 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "SPADI (Shoulder Pain and Disability Index).",
                        "DASH."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Do not aggressively stretch a 'Freezing' shoulder.",
                        "Assess ER at 0 degrees abduction to isolate capsule from muscle."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_acl",
            name = "Ortho: ACL Injury",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Anterior Cruciate Ligament tear often results from a non-contact deceleration and rotational force. Reconstruction requires progressive graft loading."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Quad atrophy",
                        "Knee effusion",
                        "Avoidance of full weight bearing"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Mechanism of injury ('pop' felt)?",
                        "Giving way episodes?",
                        "Graft type used (if post-op)?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Special Tests: Lachman, Anterior Drawer, Pivot Shift",
                        "ROM measurement",
                        "Quad lag check"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "DVT post-op",
                        "Graft failure (sudden return of instability)",
                        "Infection"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What does the ACL prevent?",
                        "Compare autograft vs allograft.",
                        "Why avoid open kinetic chain extension early post-op?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Positive Lachman test",
                        "Hemarthrosis acutely",
                        "Knee instability"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 4 weeks post-ACLR (Hamstring graft). Feel stable with brace.",
                        "O: AROM 0-110 deg. Minimal effusion. Walking WBAT.",
                        "A: Progressing well into intermediate phase of rehab.",
                        "P: Introduce closed kinetic chain exercises (mini-squats). Proprioception drills on balance board."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Decreased proprioception, quad weakness, surgical pain.",
                        "Activity Limitations: Unable to run or jump.",
                        "Participation Restrictions: Cannot participate in sports."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Full active knee extension (0 deg) within 1 week.",
                        "LTG: Return to sport with >90% LSI (Limb Symmetry Index) at 9 months."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "IKDC (International Knee Documentation Committee).",
                        "Lysholm Knee Score."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Protect the graft: it is weakest between 6-12 weeks due to revascularization remodeling.",
                        "Closed chain exercises are safer early on."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_fracture",
            name = "Ortho: Fracture Rehab",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Fracture rehabilitation focuses on restoring ROM, strength, and function after a period of immobilization or surgical fixation."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Casting / fixation devices",
                        "Muscle atrophy from immobilization",
                        "Skin condition (dry, scaly)"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Date of fracture and fixation type (ORIF)?",
                        "Weight-bearing status prescribed by surgeon?",
                        "Current pain levels?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "ROM of joints ABOVE and BELOW the fracture",
                        "Edema measurements (figure of eight)",
                        "Sensation and peripheral pulses"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Compartment Syndrome (severe out-of-proportion pain, pallor, pulselessness)",
                        "Complex Regional Pain Syndrome (shiny skin, extreme hypersensitivity)",
                        "Non-union / hardware failure"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Stages of bone healing?",
                        "Wolff's Law?",
                        "Signs of Compartment Syndrome (5 Ps)?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Joint stiffness (cast disease)",
                        "Disuse atrophy",
                        "Dependent edema"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 6 weeks post distal radius Colles' fracture, cast removed today.",
                        "O: Wrist ROM heavily restricted (Flx 20, Ext 15). Skin dry. Mod swelling. Grip strength 10 lbs.",
                        "A: Severe post-immobilization stiffness and weakness.",
                        "P: Warm hydrocollator pack. Joint mobilizations. Active tendon gliding exercises."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Loss of joint mobility, disuse muscle atrophy.",
                        "Activity Limitations: Inability to lift objects, grip tools.",
                        "Participation Restrictions: Cannot return to manual labor."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Restore functional wrist ROM within 4 weeks.",
                        "LTG: Normal grip strength recovery within 8 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Specific to region (e.g., DASH for UE, LEFS for LE)."
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Treat the joints above and below the cast from DAY ONE.",
                        "Respect the surgical healing timelines; radiologic union must precede aggressive loading."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_copd",
            name = "Cardio: COPD",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Chronic Obstructive Pulmonary Disease involves chronic airway inflammation and limitation. Presents with dyspnea, daily cough, and hyperinflation."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Pursed-lip breathing",
                        "Hypertrophied accessory muscles / Barrel chest",
                        "Cyanosis / Clubbing"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Smoking pack-years?",
                        "Sputum color and volume?",
                        "Distance walked before breathlessness?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Auscultation: Wheezes, decreased sounds",
                        "SpO2 at rest and exertion",
                        "6-Minute Walk Test"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Sudden drop in SpO2 < 88%",
                        "Acute respiratory distress",
                        "Cor pulmonale signs (pitting edema, JVD)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Pink puffer vs Blue bloater?",
                        "Physiology of pursed-lip breathing?",
                        "What is the mMRC scale?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Hyperinflation",
                        "Flattened diaphragm",
                        "FEV1/FVC < 0.70"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Patient complains of SOB walking to bathroom.",
                        "O: RR: 24, SpO2: 92% RA. Barrel chest. Expiratory wheeze. 6MWT = 250m.",
                        "A: Reduced exercise tolerance due to COPD.",
                        "P: Airway clearance (ACBT). Ergo cycle for 15 mins at RPE 3. Edu on pursed lip breathing."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Airflow obstruction, retained secretions, respiratory muscle fatigue.",
                        "Activity Limitations: Dyspnea on minimal exertion.",
                        "Participation Restrictions: Homebound due to O2 reliance."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Independent use of ACBT to clear morning secretions.",
                        "LTG: Increase 6MWT distance >300m in 6 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "mMRC Dyspnea Scale",
                        "COPD Assessment Test (CAT)",
                        "6-Minute Walk Test"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Monitor SpO2.",
                        "Pursed-lip breathing provides PEEP to stent airways open.",
                        "Coordinate with inhaler timings."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_pneumonia",
            name = "Cardio: Pneumonia",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Pneumonia is an infection causing alveolar consolidation. Main rehab goals are airway clearance and preventing atelectasis."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Tachypnea",
                        "Cough frequency / Sputum type",
                        "Accessory muscle use"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Fevers or chills?",
                        "Pleuritic chest pain?",
                        "Fatigue levels?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Auscultation: Crackles over consolidation",
                        "Percussion: Dullness",
                        "Vocal fremitus: Increased"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Sepsis (hypotension, altered mental status)",
                        "Massive hemoptysis",
                        "SpO2 < 88%"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Pneumonic auscultation findings?",
                        "Why does vocal fremitus increase?",
                        "Contraindications for postural drainage?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Crackles",
                        "Hypoxia (V/Q mismatch)",
                        "Productive cough"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: Admitted with RLL pneumonia. Sharp chest pain on deep breath.",
                        "O: T: 38.5C, HR: 105, SpO2: 91%. Coarse crackles in RLL.",
                        "A: Impaired gas exchange and retained secretions.",
                        "P: Incentive spirometry. Supported huffing. Early mobilization out of bed."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Retained alveolar secretions, pleuritic pain.",
                        "Activity Limitations: Bedbound due to fatigue.",
                        "Participation Restrictions: Medical isolation."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Effective huff cough yielding sputum today.",
                        "LTG: Clear lung fields and baseline ADLs in 2 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Borg RPE Scale",
                        "SpO2 Monitoring",
                        "Incentive Spirometry Volume"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Positioning is therapy ('Good lung down').",
                        "Hydration thins secretions."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_cabg",
            name = "Cardio: Post CABG",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Coronary Artery Bypass Grafting requires a median sternotomy. Phase 1 cardiac rehab focuses on early mobilization and clearing the lungs while protecting the sternum."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Sternal incision and graft harvest sites",
                        "Chest tubes and pacing wires",
                        "Breathing depth"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Chest pain? (anginal vs incisional)",
                        "Can you show me your sternal precautions?",
                        "Wearing compression stockngs?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Vitals & ECG monitoring",
                        "Auscultation (basal atelectasis is common)",
                        "Sternal stability check"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Sternal clicking/shifting",
                        "Typical anginal pain (graft issue)",
                        "New arrhythmias (Afib)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What are sternal precautions?",
                        "Common graft vessels?",
                        "Phases of cardiac rehab?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Sternal pain",
                        "Reduced inspiratory volumes",
                        "Deconditioning"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: POD 2 CABG. Sternal pain 4/10. Afraid to move.",
                        "O: Vitals stable. Sternum intact, no clicking. Transferred to chair min assist using pillow.",
                        "A: Early mobility good, requires confidence and education.",
                        "P: Review 'keep tube in the tube' precautions. Chest splinting for cough. Walked 50 ft."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Sternal instability risk, incisional pain, basal atelectasis.",
                        "Activity Limitations: Difficulty with bed mobility.",
                        "Participation Restrictions: Driving restrictions post-op."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Independent bed mobility with precautions in 3 days.",
                        "LTG: Graduate Phase 2 cardiac rehab in 3 months."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "6MWT",
                        "RPE (Borg Scale)",
                        "Duke Activity Status Index"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "'Hug a pillow' when coughing.",
                        "Watch for Valsalva during transfers.",
                        "Differentiate incisional vs anginal pain."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_covid",
            name = "Cardio: COVID Rehab",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Post-COVID rehabilitation handles survivors of severe acute respiratory distress syndrome, dealing with profound deconditioning, lung fibrosis, and PICS (Post Intensive Care Syndrome)."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Oxygen dependency / Titration needs",
                        "Profound muscle wasting (ICU acquired weakness)",
                        "Cognitive fatigue (brain fog)"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "How long were you intubated/in ICU?",
                        "Do you experience post-exertional malaise (PEM)?",
                        "Has your sense of taste/smell returned?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "SpO2 continuous monitoring (high risk of exertional desaturation)",
                        "MMT for ICU acquired weakness",
                        "Sit-to-stand endurance"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Post-Exertional Malaise (PEM) crash",
                        "Unexplained tachycardia / POTS symptoms",
                        "Myocarditis signs (arrhythmia, chest pain)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What is Post Intensive Care Syndrome?",
                        "What is the pathophysiology of COVID ARDS?",
                        "How do you manage pacing for long-COVID?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Severe deconditioning",
                        "Restrictive lung pattern if fibrotic",
                        "Tachycardia on minimal exertion"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 4 weeks post-ICU discharge. Reports 'crashing' for 2 days if he walks too much.",
                        "O: SpO2 resting 96%, drops to 89% after 1 flight of stairs. HR spikes to 130 bpm. Gross MMT 3+/5.",
                        "A: Post-COVID deconditioning with exertional desaturation and PEM.",
                        "P: Edu on energy conservation and Pacing (the 4 Ps). Interval training on recumbent stepper with strict HR caps."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Reduced pulmonary compliance, ICU acquired weakness.",
                        "Activity Limitations: Cannot complete heavy ADLs without oxygen.",
                        "Participation Restrictions: Unable to return to employment."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Complete self-dressing without SpO2 dropping below 90% within 1 week.",
                        "LTG: Wean completely off supplemental O2 during exercise in 6 weeks."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Post-COVID Functional Status Scale (PCFS)",
                        "6MWT",
                        "Borg RPE"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Do not push through the fatigue if they have PEM; it will cause a severe crash.",
                        "Monitor HR continuously, dysautonomia is common."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_sarcopenia",
            name = "Geriatrics: Sarcopenia",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Sarcopenia is a geriatric syndrome characterized by loss of skeletal muscle mass and strength, leading to physical disability and frailty."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Muscle wasting (calves, temples)",
                        "Gait: slow speed, shuffling",
                        "Dependence on functional mobility aids"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Unintentional weight loss?",
                        "History of falls?",
                        "Protein intake in diet?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Grip strength (JAMAR dynamometer)",
                        "Physical performance: Gait speed (10m walk)",
                        "Calf circumference"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Severe cachexia (rule out cancer)",
                        "Unexplained severe bone pain",
                        "Acute delirium"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "EWGSOP diagnostic criteria for Sarcopenia?",
                        "Difference between Sarcopenia and Cachexia?",
                        "Why is grip strength a vital sign?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Low muscle mass and strength",
                        "Slow gait speed (< 0.8 m/s)",
                        "Frailty"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 82 y/o male reports feeling 'weak all over'. Cannot open jars. Recent falls.",
                        "O: Grip strength 16kg (low). Gait speed 0.6 m/s. 5x Sit-to-Stand takes 18s.",
                        "A: Deficits in muscle power increasing fall risk.",
                        "P: Progressive Resistance Training (PRT) targeting LEs. Sit-to-stands, heel raises. Nutrition referral."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Reduced cross-sectional muscle area, decreased MVC.",
                        "Activity Limitations: Slow walking speed restricting community crossing.",
                        "Participation Restrictions: Loss of independence."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Decrease 5x sit-to-stand time to <15s within 4 weeks.",
                        "LTG: Gait speed >0.8 m/s within 6 weeks to reduce fall risk."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "SARC-F Screen",
                        "Grip Strength",
                        "SPPB (Short Physical Performance Battery)"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Resistance training is the ONLY effective intervention.",
                        "Do not under-load the elderly! Use 60-80% 1RM safely."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_senile_kyphosis",
            name = "Geriatrics: Senile Kyphosis",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Senile kyphosis is an excessive forward curvature of the thoracic spine in the elderly, often driven by osteoporosis and wedging of vertebrae."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Thoracic hyperkyphosis, forward head posture",
                        "Altered breathing mechanics (restrictive)",
                        "Balance deviations (anterior shift in COM)"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "History of vertebral compression fractures?",
                        "Loss of height over the years?",
                        "Any back pain with standing?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Occiput-to-wall distance",
                        "Spinal mobility and extensor strength",
                        "Pulmonary function (chest expansion)"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Acute severe localized back pain (new compression fracture)",
                        "Neurological signs (cord compression)",
                        "Severe osteoporosis history (avoid aggressive manipulation)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "Pathophysiology of osteoporotic wedge fractures?",
                        "How does kyphosis affect pulmonary function?",
                        "Occiput-to-wall clinical significance?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Structural vs functional kyphosis",
                        "Weak rhomboids and middle trapezius",
                        "Tight pectorals"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 75 y/o female reports 'stooped posture' and mild mid-back ache.",
                        "O: Occiput-to-wall distance: 7 cm. Severe thoracic kyphosis. Bilateral pec minor tightness. Weak middle traps.",
                        "A: Senile kyphosis resulting in postural pain and altered biomechanics.",
                        "P: Postural re-education. Pectoral doorway stretches. Prone trunk extensions (modified) targeting erector spinae."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Thoracic wedge deformity, extensor weakness.",
                        "Activity Limitations: Difficulty looking up to reach high shelves.",
                        "Participation Restrictions: Self-consciousness about appearance."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Reduce resting occiput-to-wall distance by 2cm within 4 weeks.",
                        "LTG: Increase endurance of spinal extensors to maintain upright posture for 10 mins."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Occiput-to-wall measure",
                        "Inclinometer thoracic angle",
                        "VAS for back pain"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Never do forceful flexion exercises (like sit-ups) in severe osteoporosis; they cause wedge fractures.",
                        "Strengthen the extensors, stretch the flexors."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        ),
        ClinicalPostingCategory(
            id = "cp_balance",
            name = "Geriatrics: Balance Disorders",
            steps = listOf(
                PostingStep(
                    title = "1. Introduction",
                    items = listOf(
                        "Falls in the elderly are multifactorial. Balance disorders can stem from vestibulopathy, sensory neuropathy, vascular issues, or musculoskeletal weakness."
                    )
                ),
                PostingStep(
                    title = "2. What Student Should Observe",
                    items = listOf(
                        "Base of support width during gait",
                        "Use of visual fixation to maintain balance",
                        "Turning block (taking multiple small steps to turn)"
                    )
                ),
                PostingStep(
                    title = "3. Questions To Ask Patient",
                    items = listOf(
                        "Have you fallen in the past 6 months? (If yes, how?)",
                        "Dizzy when rolling in bed vs standing up?",
                        "Vision changes or neuropathy in feet?"
                    )
                ),
                PostingStep(
                    title = "4. Assessment Checklist",
                    items = listOf(
                        "Sensory organization test (CTSIB - foam and dome)",
                        "Vestibular: Dix-Hallpike",
                        "Functional: Berg Balance Scale, TUG"
                    )
                ),
                PostingStep(
                    title = "5. Red Flags",
                    items = listOf(
                        "Syncope or drop attacks (cardiology referral)",
                        "Nystagmus resting or purely vertical (central neurological sign)",
                        "Unilateral hearing loss with dizziness (acoustic neuroma)"
                    )
                ),
                PostingStep(
                    title = "6. Common Viva Questions",
                    items = listOf(
                        "What are the 3 main systems maintaining balance?",
                        "Differentiate BPPV vs Orthostatic hypotension.",
                        "How do you score the Berg Balance Scale?"
                    )
                ),
                PostingStep(
                    title = "7. Expected Findings",
                    items = listOf(
                        "Positive Romberg on foam",
                        "Delayed stepping strategies",
                        "Fear of falling"
                    )
                ),
                PostingStep(
                    title = "8. SOAP Note Training",
                    items = listOf(
                        "S: 80 y/o reports a fall in the bathroom at night. 'I lose my balance in the dark'.",
                        "O: CTSIB: Fails condition 4 (eyes closed on foam). BBS: 41/56. Diminished light touch in feet.",
                        "A: Somatosensory deficit increasing reliance on vision; high fall risk in low light.",
                        "P: Balance training reducing visual inputs (eyes closed standing). Edu on home modifications (nightlights). Ankle strategy training."
                    )
                ),
                PostingStep(
                    title = "9. Problem List Training",
                    items = listOf(
                        "Impairments: Diminished proprioception, delayed postural reactions.",
                        "Activity Limitations: Unsafe walking in unlit environments.",
                        "Participation Restrictions: Fear of falling causing home confinement."
                    )
                ),
                PostingStep(
                    title = "10. Goal Setting Training",
                    items = listOf(
                        "STG: Stand on foam with eyes closed for 10s within 3 weeks.",
                        "LTG: Improve BBS to >45 to clear high fall risk category."
                    )
                ),
                PostingStep(
                    title = "11. Outcome Measures",
                    items = listOf(
                        "Berg Balance Scale",
                        "CTSIB",
                        "Dynamic Gait Index (DGI)"
                    )
                ),
                PostingStep(
                    title = "12. Clinical Posting Pearls",
                    items = listOf(
                        "Determine WHICH system is failing (visual, vestibular, somatosensory) and train the remaining systems to compensate.",
                        "'Fear of falling' is an independent risk factor for future falls."
                    )
                )
            ),
            references = listOf("O\'Sullivan Physical Rehabilitation", "Kisner & Colby Therapeutic Exercise", "Magee Orthopedic Physical Assessment", "Sullivan Neurological Rehabilitation")
        )
    )

    @Volatile var clinicalPostingCategories = initialClinicalPostingCategories
}
