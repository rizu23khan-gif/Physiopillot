package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class VivaMcq(
    val id: String = java.util.UUID.randomUUID().toString(),
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

@Serializable
data class DetailedAnatomy(
    val id: String,
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
    val searchTerms: List<String>
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

@Serializable
data class ClinicalPostingCategory(
    val id: String,
    val name: String,
    val steps: List<PostingStep>,
    val references: List<String>
)

object ContentRepo {
    
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
                references = listOf("Standardized Physiotherapy Clinical Guidelines."),
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

    val anatomies = listOf(
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
        )
    )

    val vivaQuestions = listOf(
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
            question = "Which of the following is a key concept in Pathology (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Pathology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Pathology",
            difficulty = "Easy",
            learningPoint = "Always relate Pathology to clinical practice.",
            relatedConcept = "Advanced Pathology principles.",
            reference = "Standard Pathology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Pathology (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Pathology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Pathology",
            difficulty = "Moderate",
            learningPoint = "Always relate Pathology to clinical practice.",
            relatedConcept = "Advanced Pathology principles.",
            reference = "Standard Pathology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Pathology (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Pathology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Pathology",
            difficulty = "High Yield",
            learningPoint = "Always relate Pathology to clinical practice.",
            relatedConcept = "Advanced Pathology principles.",
            reference = "Standard Pathology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Microbiology (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Microbiology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Microbiology",
            difficulty = "Easy",
            learningPoint = "Always relate Microbiology to clinical practice.",
            relatedConcept = "Advanced Microbiology principles.",
            reference = "Standard Microbiology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Microbiology (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Microbiology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Microbiology",
            difficulty = "Moderate",
            learningPoint = "Always relate Microbiology to clinical practice.",
            relatedConcept = "Advanced Microbiology principles.",
            reference = "Standard Microbiology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Microbiology (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Microbiology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Microbiology",
            difficulty = "High Yield",
            learningPoint = "Always relate Microbiology to clinical practice.",
            relatedConcept = "Advanced Microbiology principles.",
            reference = "Standard Microbiology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Exercise Therapy I (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Exercise Therapy I.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Always relate Exercise Therapy I to clinical practice.",
            relatedConcept = "Advanced Exercise Therapy I principles.",
            reference = "Standard Exercise Therapy I Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Exercise Therapy I (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Exercise Therapy I.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Always relate Exercise Therapy I to clinical practice.",
            relatedConcept = "Advanced Exercise Therapy I principles.",
            reference = "Standard Exercise Therapy I Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Exercise Therapy I (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Exercise Therapy I.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Always relate Exercise Therapy I to clinical practice.",
            relatedConcept = "Advanced Exercise Therapy I principles.",
            reference = "Standard Exercise Therapy I Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Exercise Therapy II (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Exercise Therapy II.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Always relate Exercise Therapy II to clinical practice.",
            relatedConcept = "Advanced Exercise Therapy II principles.",
            reference = "Standard Exercise Therapy II Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Exercise Therapy II (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Exercise Therapy II.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Always relate Exercise Therapy II to clinical practice.",
            relatedConcept = "Advanced Exercise Therapy II principles.",
            reference = "Standard Exercise Therapy II Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Exercise Therapy II (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Exercise Therapy II.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Always relate Exercise Therapy II to clinical practice.",
            relatedConcept = "Advanced Exercise Therapy II principles.",
            reference = "Standard Exercise Therapy II Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Electrotherapy I (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Electrotherapy I.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Always relate Electrotherapy I to clinical practice.",
            relatedConcept = "Advanced Electrotherapy I principles.",
            reference = "Standard Electrotherapy I Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Electrotherapy I (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Electrotherapy I.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Always relate Electrotherapy I to clinical practice.",
            relatedConcept = "Advanced Electrotherapy I principles.",
            reference = "Standard Electrotherapy I Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Electrotherapy I (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Electrotherapy I.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Always relate Electrotherapy I to clinical practice.",
            relatedConcept = "Advanced Electrotherapy I principles.",
            reference = "Standard Electrotherapy I Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Electrotherapy II (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Electrotherapy II.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Always relate Electrotherapy II to clinical practice.",
            relatedConcept = "Advanced Electrotherapy II principles.",
            reference = "Standard Electrotherapy II Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Electrotherapy II (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Electrotherapy II.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Always relate Electrotherapy II to clinical practice.",
            relatedConcept = "Advanced Electrotherapy II principles.",
            reference = "Standard Electrotherapy II Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Electrotherapy II (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Electrotherapy II.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Always relate Electrotherapy II to clinical practice.",
            relatedConcept = "Advanced Electrotherapy II principles.",
            reference = "Standard Electrotherapy II Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Biomechanics (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Biomechanics.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Always relate Biomechanics to clinical practice.",
            relatedConcept = "Advanced Biomechanics principles.",
            reference = "Standard Biomechanics Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Biomechanics (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Biomechanics.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Always relate Biomechanics to clinical practice.",
            relatedConcept = "Advanced Biomechanics principles.",
            reference = "Standard Biomechanics Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Biomechanics (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Biomechanics.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Always relate Biomechanics to clinical practice.",
            relatedConcept = "Advanced Biomechanics principles.",
            reference = "Standard Biomechanics Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Kinesiology (Easy)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Kinesiology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Always relate Kinesiology to clinical practice.",
            relatedConcept = "Advanced Kinesiology principles.",
            reference = "Standard Kinesiology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Kinesiology (Moderate)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Kinesiology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Always relate Kinesiology to clinical practice.",
            relatedConcept = "Advanced Kinesiology principles.",
            reference = "Standard Kinesiology Textbook"
        ),
        VivaMcq(
            question = "Which of the following is a key concept in Kinesiology (High Yield)?",
            options = listOf("Concept A", "Concept B", "Concept C", "Concept D"),
            correctAnswer = "Concept A",
            whyCorrect = "Concept A is fundamental to understanding Kinesiology.",
            whyIncorrect = "Concepts B, C, and D belong to different domains.",
            clinicalImportance = "Understanding this helps in clinical reasoning.",
            examinerTip = "Examiners often ask this to test basic understanding.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Always relate Kinesiology to clinical practice.",
            relatedConcept = "Advanced Kinesiology principles.",
            reference = "Standard Kinesiology Textbook"
        )
,
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
    )

    val clinicalPostingCategories = listOf(
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
        ),
    )
}
