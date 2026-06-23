package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "viva_prep_topics")
@Serializable
data class VivaTopic(
    @PrimaryKey val id: String,
    val title: String,
    val category: String, // "Anatomy", "Biomechanics", "Exercise Therapy", "Electrotherapy", "Pathology", "Pharmacology", "Neurology", "Orthopedics"
    val vivaQuestion: String,
    val oneLineAnswer: String,
    val detailedAnswer: String,
    val examinerFavorites: List<String>,
    val rapidRevision: List<String>,
    val commonMistakes: List<String>,
    val clinicalCorrelation: String,
    val memoryTricks: String
)

object VivaPrepRepo {
    val initialTopics = listOf(
        VivaTopic(
            id = "anatomy_brachial_plexus",
            title = "Brachial Plexus (C5-T1)",
            category = "Anatomy",
            vivaQuestion = "Describe the formation, trunks, cords, and clinical lesions of the Brachial Plexus with emphasis on Erb's and Klumpke's paralysis.",
            oneLineAnswer = "The brachial plexus is an somatic nerve network formed by anterior rami of C5-T1, supplying the upper limb and presenting clinically with Erb's (upper trunk) or Klumpke's (lower trunk) palsy.",
            detailedAnswer = "Formed by roots (C5-T1), trunks (Upper C5-C6, Middle C7, Lower C8-T1), divisions (anterior/posterior divisions of each trunk), cords (Lateral, Medial, Posterior relation to axillary artery), and branches. Erb's palsy occurs from traction at Erb's Point (C5-C6 junction) affecting deltoid, biceps, brachialis, and supinator, leading to a 'Waiter's tip' position. Klumpke's palsy (traction at C8-T1) affects intrinsic hand muscles, leading to a complete claw hand deformity.",
            examinerFavorites = listOf(
                "Where is Erb's Point located and which 6 nerves meet there?",
                "Explain the anatomical mechanism of the 'Waiter's tip' gesture.",
                "Which root supplies the nerve to Serratus Anterior (Long Thoracic Nerve of Bell)?"
            ),
            rapidRevision = listOf(
                "Erb's Point is the union of C5 & C6 roots, suprascapular nerve, nerve to subclavius, anterior & posterior divisions of upper trunk.",
                "Erb's deformity: Arm adducted, pronated, internally rotated.",
                "Klumpke's deformity: Complete Claw hand with wasting of lumbricals and interossei.",
                "Long thoracic nerve arises directly from roots C5, C6, C7."
            ),
            commonMistakes = listOf(
                "Assigning C5-C6 roots to Klumpke's palsy instead of Erb's.",
                "Confusing the lateral cord with the lateral division of trunks."
            ),
            clinicalCorrelation = "Obstetric traction during difficult vaginal delivery involving shoulder dystocia, or falling from height landing onto the shoulder neck junction.",
            memoryTricks = "Erb's affects the Elevated trunk (C5-C6); Klumpke's affects the Keep-down trunk (C8-T1)."
        ),
        VivaTopic(
            id = "biomechanics_gait_cycle",
            title = "Human Gait Cycle Phases",
            category = "Biomechanics",
            vivaQuestion = "Trace the phases of the Gait Cycle, detailing joint angles of the ankle and knee at Heel Strike, and discuss pathological gait compensations.",
            oneLineAnswer = "A single gait cycle consists of Stance (60%) and Swing (40%) phases, driven by synchronized foot strikes, knee flexion-extensions, and hip pelvic rotations.",
            detailedAnswer = "The cycle is divided into Stance Phase (Initial Contact, Loading Response, Mid-stance, Terminal Stance, Pre-swing) and Swing Phase (Initial, Mid, Terminal Swing). At Initial Contact (heel strike), the ankle is in neutral (0 degrees) and knee is near full extension (0-5 degrees). Muscle actions involve eccentric Tibialis Anterior to prevent rapid foot slap, and eccentric Quadriceps to absorb shock during loading response.",
            examinerFavorites = listOf(
                "What is the difference between step length and stride length?",
                "During which phase of stance is there maximum knee flexion?",
                "What causes Trendelenburg Gait and how does the body compensate?"
            ),
            rapidRevision = listOf(
                "Stance Phase = 60%, Swing Phase = 40% of normal cadence.",
                "Double Support: Occurs twice in a cycle (first 10% and last 10% of stance).",
                "Cadence: Average is 110-120 steps per minute.",
                "Trendelenburg Sign: Pelvise drops on the contralateral side due to Gluteus Medius weakness of the weight-bearing side."
            ),
            commonMistakes = listOf(
                "Interchanging the terms 'stance phase' and 'single limb support' as synonyms.",
                "Indicating that the hamstrings are active during mid-stance; they act eccentrically at terminal swing to decelerate the leg."
            ),
            clinicalCorrelation = "Gluteus Medius weakness leads to downward pelvis tilt on the opposite side (Trendelenburg sign) or a compensated trunk lateral lurch to the same side.",
            memoryTricks = "Stance has 5 sub-phases; Swing has 3 sub-phases. Stance starts with Strike (heel strike), Swing starts with Spring (toe off)."
        ),
        VivaTopic(
            id = "ex_therapy_insufficiency",
            title = "Active and Passive Insufficiency",
            category = "Exercise Therapy",
            vivaQuestion = "Define Active and Passive Insufficiency of multi-joint muscles, providing clinical examples with Gastrocnemius and Hamstrings.",
            oneLineAnswer = "Active insufficiency is the inability of a multi-joint muscle to contract fully to produce maximum force, while passive insufficiency is the inability of the opponent muscle to stretch enough to allow full joint movement.",
            detailedAnswer = "Active Insufficiency: Occurs when a multi-joint muscle is shortened across all its crossing joints simultaneously, leaving its sarcomeres overlapping so much that actin-myosin overlap is sub-optimal (e.g., hamstring knee flexion with hip in extension). Passive Insufficiency: Occurs when a multi-joint muscle cannot stretch further across all its joints simultaneously (e.g., straight leg raise is limited by hamstring stretch when hip is flexed and knee is extended).",
            examinerFavorites = listOf(
                "How do you isolate the Soleus muscle from the Gastrocnemius during manual muscle testing?",
                "Define the length-tension relationship in skeletal muscle.",
                "How do active and passive insufficiency restrict therapist joint measurements?"
            ),
            rapidRevision = listOf(
                "Active Insufficiency = Contractile limit (agonist cannot shorten more).",
                "Passive Insufficiency = Elastic/stretch limit (antagonist cannot lengthen more).",
                "Both restrict multi-joint movements when joints are positioned at extremes."
            ),
            commonMistakes = listOf(
                "Confusing which muscle undergoes active vs passive insufficiency (remember Agonist is ACTIVE, Antagonist is PASSIVE)."
            ),
            clinicalCorrelation = "To stretch the quadriceps/rectus femoris effectively, the hip must be extended while the knee is flexed, exploiting passive insufficiency of the anterior thigh.",
            memoryTricks = "Active is for Agonist (muscle doing the work); Passive is for Pulling back (antagonist stretching)."
        ),
        VivaTopic(
            id = "electro_sd_curve",
            title = "Strength-Duration (SD) Curve Mechanics",
            category = "Electrotherapy",
            vivaQuestion = "Explain the physiological principles of the Strength-Duration Curve, defining Rheobase, Chronaxie, and how they change during denervation and re-innervation.",
            oneLineAnswer = "The SD Curve graphic represents the relationship between electrical stimulus intensity and duration required to excite excitable tissues, shifting upwards and to the right in denervation.",
            detailedAnswer = "Uses pulse durations from 0.01 ms to 300 ms. Rheobase is the minimum intensity of constant electrical current of infinite duration (usually 100ms or 300ms) required to produce a minimal contraction. Chronaxie is the minimum duration of stimulus required to excite a tissue with a current of twice the rheobase intensity. Normal nerve chronaxie is < 1 ms, and denervated muscle is > 10 ms. Denervation shifts the curve up-right and introduces a 'kink' representing partial muscle excitation.",
            examinerFavorites = listOf(
                "What is the physiological value of Chronaxie for a healthy vs denervated muscle?",
                "Why is a square pulse preferred over a triangular pulse for SD plotting?",
                "What does a 'kink' in the SD curve signify?"
            ),
            rapidRevision = listOf(
                "Chronaxie of Innervated Nerve: 0.05 to 1 ms.",
                "Chronaxie of Denervated Muscle: 10 to 50 ms.",
                "Rheobase is measured in Milliamperes (mA) or Volts (V); Chronaxie is in Milliseconds (ms).",
                "A kink indicates partial denervation, formed due to different excitability of nerve fibers and muscle fibers."
            ),
            commonMistakes = listOf(
                "Defining Chronaxie as twice the intensity (it's the DURATION at twice the rheobase intensity).",
                "Using triangular current for assessing rheobase."
            ),
            clinicalCorrelation = "Used in clinical physical medicine to track nerve regeneration progress after peripheral nerve injuries like Bell's palsy or radial nerve wrist drop.",
            memoryTricks = "Rheobase is Rate (intensity/amplitude); Chronaxie is Clock (time/duration)."
        ),
        VivaTopic(
            id = "pathology_wallerian_degen",
            title = "Wallerian Degeneration & Nerve Injury",
            category = "Pathology",
            vivaQuestion = "Describe the stages of Wallerian Degeneration following axonotmesis, and detail the rate and mechanism of peripheral nerve regeneration.",
            oneLineAnswer = "Wallerian degeneration is the active enzymatic breakdown of the axon and myelin sheath distal to a nerve injury site, occurring within 24 to 36 hours.",
            detailedAnswer = "Stage 1: Breakdown of axonal cytoskeleton and membrane distal to injury. Stage 2: Myelin sheath unravels and fragments. Stage 3: Macrophages and Schwann cells clear cellular debris (takes ~1-2 weeks). Regeneration occurs from the proximal stump at a slow rate of approximately 1 mm per day (or 1 inch per month), guided by Schwann cell tubes (Bands of Büngner) towards target end-organs.",
            examinerFavorites = listOf(
                "Explain Tinel's sign and how it is used to monitor nerve regeneration rate.",
                "What are the Seddon and Sunderland classifications of nerve injury?",
                "What happens to the chromatolysis in the nerve cell body post-injury?"
            ),
            rapidRevision = listOf(
                "Seddon: Neuropraxia (conduction block), Axonotmesis (axon cut), Neurotmesis (entire nerve cut).",
                "Sunderland: 5 Degrees of severity (1st = Neuropraxia, 5th = Neurotmesis).",
                "Regeneration rate: 1 mm/day.",
                "Chromatolysis: Swelling of cell body and dispersal of Nissl granules in response to axonal shear."
            ),
            commonMistakes = listOf(
                "Stating that Wallerian degeneration occurs proximal to the injury site; it occurs DISTALLY.",
                "Assuming neurotmesis heals spontaneously without surgical suturing."
            ),
            clinicalCorrelation = "With a radial nerve injury (Saturday Night Palsy), recovery manifests as progressive distal sensory recovery and muscle re-activation monitored by tapping along the nerve pathway (Tinel's sign).",
            memoryTricks = "Wallerian goes Without the body (occurs distally away from cell body)."
        ),
        VivaTopic(
            id = "pharmacology_nsaids_steroids",
            title = "NSAIDs vs Corticosteroids in Rehab",
            category = "Pharmacology",
            vivaQuestion = "Differentiate between the mechanism of action of NSAIDs (COX-1 vs COX-2 inhibitors) and Corticosteroids, noting side-effects relevant to active physical therapy.",
            oneLineAnswer = "NSAIDs inhibit cyclooxygenase (COX) enzymes to prevent prostaglandin synthesis, whereas corticosteroids suppress phospholipase A2 to cause profound, multi-pathway anti-inflammatory action.",
            detailedAnswer = "NSAIDs block COX-1 (causing gastric irritation and renal perfusion drops) and COX-2 (reducing pain, fever, swelling). Corticosteroids enter cell nuclei to decrease transcription of inflammatory cytokines. Corticosteroids cause collagen breakdown, muscle wasting (myopathy), osteopenia, and delayed tendon healing. Local corticosteroid injections weaken tendon tensile strength, requiring careful load monitoring.",
            examinerFavorites = listOf(
                "Why should you avoid aggressive eccentric loading immediately following a corticosteroid injection?",
                "What are the systemic side-effects of prolonged oral prednisolone?",
                "Explain why selective COX-2 inhibitors (e.g., Celecoxib) were developed."
            ),
            rapidRevision = listOf(
                "COX-1: Constitutive (mediates gastroprotection, platelet aggregation).",
                "COX-2: Inducible (upregulated during inflammation, triggers hyperalgesia).",
                "Corticosteroids: Potent immunosuppressants; risk of tendon rupture.",
                "Side effects: Glucocorticoids cause osteoporosis, skin thinning, and muscle atrophy."
            ),
            commonMistakes = listOf(
                "Suggesting that NSAIDs cure tendon pathology; they only offer transient symptom relief.",
                "Forgetting that corticosteroids cause osteopenia, leading to increased rib or spinal fracture risk under heavy load."
            ),
            clinicalCorrelation = "A patient with De Quervain's tenosynovitis who received a steroid injection must avoid resistive exercises for 10-14 days to prevent tendon rupture.",
            memoryTricks = "NSAID targets the Needle of COX enzymes; Cortico targets the Collagen integrity (weakens tendons)."
        ),
        VivaTopic(
            id = "neurology_umn_lmn",
            title = "Upper vs Lower Motor Neuron Lesions",
            category = "Neurology",
            vivaQuestion = "Contrast Upper Motor Neuron (UMN) and Lower Motor Neuron (LMN) lesions in terms of tone, reflexes, muscle state, and pathological responses.",
            oneLineAnswer = "UMN lesions affect brain/spinal cord pathways leading to hypertonia and hyperreflexia, while LMN lesions affect anterior horn cells/nerves causing flaccidity, hypotonia, and hyporeflexia.",
            detailedAnswer = "UMN markers: Spasticity (velocity-dependent clasp-knife increased tone), exaggerated deep tendon reflexes, clonus (+), Babinski sign (+), and disuse atrophy (mild). LMN markers: Flaccidity (hypotonia), absent/decreased reflexes, presence of muscle fasciculations, rapid denervation muscle atrophy (severe), and Babinski sign (-).",
            examinerFavorites = listOf(
                "Define Spasticity and differentiate it from Rigidity.",
                "Explain the physiological mechanism behind the Babinski reflex.",
                "Name three common pediatric or adult diagnoses presenting with LMN signs."
            ),
            rapidRevision = listOf(
                "UMN: Stroke, Spinal Cord Injury, TBI, Multiple Sclerosis.",
                "LMN: Polio, Guillain-Barré Syndrome, Bell's Palsy, Cauda Equina.",
                "Spasticity: Velocity-dependent pyramidal sign. Rigidity: Extrapyramidal lead-pipe/cogwheel sign."
            ),
            commonMistakes = listOf(
                "Listing Spinal Shock period as hypertonic; immediately post-stroke or spinal injury, there is flaccidity (pseudo-LMN) before UMN spasticity emerges.",
                "Stating polio is a UMN lesion because it involves the spinal cord (it affects anterior horn cells, which are LMN)."
            ),
            clinicalCorrelation = "When handling a stroke patient with biceps spasticity, rapid stretching will trigger a contraction; slow stretch helps bypass spastic reflex arcs.",
            memoryTricks = "UMN features are always Upwards (high tone, high reflexes, Babinski upgoing). LMN features are always Low (low tone, low reflexes, flaccid)."
        ),
        VivaTopic(
            id = "orthopedics_adhesive_capsulitis",
            title = "Adhesive Capsulitis (Frozen Shoulder)",
            category = "Orthopedics",
            vivaQuestion = "Detail the clinical stages of Adhesive Capsulitis of the shoulder, its pathomechanics of capsule thickening, capsular patterns of restriction, and evidence-based physical therapy management.",
            oneLineAnswer = "Adhesive Capsulitis is a progressive, inflammatory fibroproliferative joint condition presenting with a classic capsular pattern: ER restriction > Abduction > IR.",
            detailedAnswer = "Pathomechanics: Chronic synovial inflammation led by collagen synthesis causes contracture of the axillary fold and rotator interval. restriction pattern: External Rotation (most restricted) > Abduction > Internal Rotation/Flexion. Stages: 1. Freezing (acute pain, night pain, 3-9 months), 2. Frozen (stiffness dominates, pain at end-range, 4-12 months), 3. Thawing (minimal restoration pain, gradual ROM recovery, 1-3 years).",
            examinerFavorites = listOf(
                "What is the specific capsular pattern of the glenohumeral joint?",
                "Explain the 'rotator interval' and which structures are thickened inside it.",
                "Compare mobilization techniques for Stage 1 vs Stage 2 Adhesive Capsulitis."
            ),
            rapidRevision = listOf(
                "Glenohumeral Capsular Pattern: External Rotation > Abduction > Internal Rotation.",
                "Epidemiology: Highly coupled with Diabetes Mellitus and Thyroid disorders.",
                "Rotator Interval: Contains coracohumeral ligament, superior glenohumeral ligament, and biceps long head tendon."
            ),
            commonMistakes = listOf(
                "Attempting aggressive passive stretching in the late freezing stage (it triggers severe inflammation and accelerates contracture).",
                "Diagnosing rotator cuff injury as adhesive capsulitis (rotator cuff has full passive ROM but limited active; capsulitis has BOTH limited)."
            ),
            clinicalCorrelation = "A patient with diabetes presenting with persistent shoulder stiffness and inability to brush their hair or reach their back pocket displays classic Adhesive Capsulitis pathomechanics.",
            memoryTricks = "Frozen shoulder restricts Facing out first (Foreign/External Rotation is most affected!)."
        ),
        VivaTopic(
            id = "pediatric_primitive_reflexes",
            title = "Primitive Reflexes & Integration",
            category = "Pediatrics",
            vivaQuestion = "List key primitive reflexes, their normal emergence and integration timelines, and the clinical significance of non-integration.",
            oneLineAnswer = "Primitive reflexes are brainstem-mediated involuntary motor responses essential for survival that must integrate as higher cortical pathways mature.",
            detailedAnswer = "Key reflexes: Moro (emerges in utero, integrates by 4-6 months), Rooting (integrates by 3-4 months), Palmar Grasp (integrates by 5-6 months), Asymmetrical Tonic Neck Reflex (ATNR - integrates by 4-6 months), and Symmetrical Tonic Neck Reflex (STNR - integrates by 8-12 months). Persistent primitive reflexes prevent normal developmental motor progress by trapping the child in stereotyped coordination patterns.",
            examinerFavorites = listOf(
                "Describe the 'Archer's Posture' seen in the Asymmetrical Tonic Neck Reflex.",
                "How does a persistent Symmetrical Tonic Neck Reflex (STNR) hinder reciprocal crawling?",
                "What is the difference between reflex integration and reflex obligatory persistence?"
            ),
            rapidRevision = listOf(
                "Moro: Head extension causes sudden abduction, extension, then adduction of upper limbs (integrates 4-6m).",
                "ATNR: Head turned to one side causes extension of limbs on face side and flexion of limbs on occiput side (integrates 4-6m).",
                "STNR: Head flexion causes upper limb flexion & lower limb extension; head extension causes upper limb extension & lower limb flexion (integrates 8-12m)."
            ),
            commonMistakes = listOf(
                "Stating that primitive reflexes are generated in the cerebral cortex when they are brainstem/subcortical reflexes.",
                "Assuming a persistent reflex is harmless; it prevents achievement of milestones like independent sitting and rolling."
            ),
            clinicalCorrelation = "Persistent ATNR in a child with Cerebral Palsy prevents midline hand-play, eye-hand coordination, and hand-to-mouth tasks.",
            memoryTricks = "ATNR: Face-side extends, Occiput-side flexes (think of an archer drawing a bow string)."
        ),
        VivaTopic(
            id = "pediatric_developmental_milestones",
            title = "Pediatric Developmental Milestones",
            category = "Pediatrics",
            vivaQuestion = "Outline the major gross and fine motor developmental milestones in the first 18 months of life, noting typical age windows.",
            oneLineAnswer = "Motor milestones progress in a cephalocaudal and proximodistal sequence from voluntary head control to independent upright walking.",
            detailedAnswer = "Gross Motor: Head control (3-4 months), rolling supine-to-prone (4-5 months), sitting without support (6-8 months), crawling/creeping (8-10 months), standing alone (10-12 months), and independent walking (12-15 months). Fine Motor: Midline hand play (3 months), palmar grasp (5 months), transfer objects hand-to-hand (6-7 months), pincer grasp (10-12 months), and stacking 2 blocks (15-18 months). Delay exceeding 2 standard deviations warrants early intervention screening.",
            examinerFavorites = listOf(
                "At what age should a child achieve independent, stable sitting?",
                "Differentiate between crawling (belly on floor) and creeping (quadruped on hands and knees).",
                "What fine motor grasp emerges at 10-12 months and is critical for feeding?"
            ),
            rapidRevision = listOf(
                "3 months: Head control and prone lifting.",
                "6 months: Sitting with support, transfer of objects.",
                "8-9 months: Independent sitting, creeping on hands and knees.",
                "12 months: Independent standing, neat pincer grasp.",
                "15 months: Independent walking, stacking 2 blocks."
            ),
            commonMistakes = listOf(
                "Using chronological age instead of corrected gestational age for premature infants when evaluating milestone timelines.",
                "Assuming all delays are neuromotor; sensory or social deprivation can also slow milestone achievement."
            ),
            clinicalCorrelation = "Failure to achieve head control by 4 months or independent sitting by 9 months is a major clinical red flag indicating developmental delay.",
            memoryTricks = "3-6-9-12 Rule: 3 months (Head), 6 months (Sitting), 9 months (Standing), 12 months (Walking)."
        ),
        VivaTopic(
            id = "pediatric_gmfcs_scale",
            title = "Gross Motor Function Classification (GMFCS)",
            category = "Pediatrics",
            vivaQuestion = "Explain the GMFCS levels for Cerebral Palsy, detailing the functional distinctions between Levels I through V.",
            oneLineAnswer = "GMFCS is a standardized 5-level system categorizing the gross motor function of children with Cerebral Palsy based on self-initiated movement.",
            detailedAnswer = "Level I: Walks without limitations; performs speed and coordination tasks. Level II: Walks with limitations; struggles with stairs and long distances. Level III: Walks using a hand-held mobility device (e.g. walker, crutches); uses wheeled mobility for long distances. Level IV: Self-mobility with limitations; transported or uses power wheelchair. Level V: Transported in a manual wheelchair; severe limitations in head and trunk control.",
            examinerFavorites = listOf(
                "How do GMFCS Level II and Level III walk-aid requirements differ?",
                "Is GMFCS used to evaluate motor progression or is it a classification of functional capability?",
                "At what age categories is the GMFCS scale validated?"
            ),
            rapidRevision = listOf(
                "Level I: Independent community walker, handles stairs without rails.",
                "Level II: Walks without aids indoors, holds handrails on stairs.",
                "Level III: Walks with hand-held devices (walkers/crutches) on flat surfaces.",
                "Level IV: Requires power chair or physical assistance for community travel.",
                "Level V: Entirely dependent; transported in manual wheelchair."
            ),
            commonMistakes = listOf(
                "Viewing GMFCS as an assessment of motor quality or movement style; it is strictly a classification of functional independence.",
                "Confusing GMFCS with GMFM; GMFCS is a classification scale (I-V), GMFM is a detailed assessment test (0-100%)."
            ),
            clinicalCorrelation = "A child at GMFCS Level III will benefit from a posterior wheeled walker (Pacer/crocodile) to walk independently in school.",
            memoryTricks = "1 (Solo), 2 (Struggles), 3 (Support/Walker), 4 (Sitting/Power chair), 5 (Severe/Dependent)."
        ),
        VivaTopic(
            id = "pediatric_outcome_measures",
            title = "Pediatric Outcome Measures (GMFM, PEDI, AIMS)",
            category = "Pediatrics",
            vivaQuestion = "Describe the clinical utility, populations, and structures of major pediatric outcome measures (GMFM, PEDI, AIMS, and PDMS-2).",
            oneLineAnswer = "Standardized pediatric outcome measures objectify motor delays, frame smart treatment goals, and quantify longitudinal therapeutic progress.",
            detailedAnswer = "1. GMFM-88/66: Measures changes in gross motor function over time in cerebral palsy (evaluates lying, rolling, sitting, crawling, standing, walking, jumping). 2. AIMS (Alberta Infant Motor Scale): Evaluates infant motor development (prone, supine, sitting, standing) from birth to 18 months. 3. PEDI (Pediatric Evaluation of Disability Inventory): Measures activity and participation in self-care, mobility, and social function. 4. PDMS-2 (Peabody Developmental Motor Scales-2): Assesses fine and gross motor skills from birth to 5 years.",
            examinerFavorites = listOf(
                "Which item tool is more sensitive for tracking cerebral palsy changes: GMFM-88 or GMFM-66?",
                "What age range is the Alberta Infant Motor Scale (AIMS) validated for?",
                "What is the key difference between an evaluative and a discriminative outcome measure?"
            ),
            rapidRevision = listOf(
                "GMFM: Evaluative test for Cerebral Palsy, Down Syndrome; spans 5 motor dimensions.",
                "AIMS: Observational, discriminative tool for infants at risk up to 18 months.",
                "PEDI: Interview or checklist covering self-care, mobility, and social play.",
                "Peabody (PDMS-2): Discriminative and evaluative test for children up to 5 years."
            ),
            commonMistakes = listOf(
                "Using the AIMS for a 3-year-old child (AIMS is only validated up to 18 months of age).",
                "Thinking GMFM measures *quality* of gait; it only checks if the child can complete the task details."
            ),
            clinicalCorrelation = "Documenting a GMFM score improvement from 45% to 58% over 3 months objectively demonstrates the clinical efficacy of NDT and gait training.",
            memoryTricks = "AIMS = Age under 18m; GMFM = Gross Motor for CP; PEDI = Participation and ADLs; Peabody = Up to 5 years."
        ),
        VivaTopic(
            id = "obgyn_pelvic_floor_muscles",
            title = "Pelvic Floor Muscles (PFM)",
            category = "Women's Health",
            vivaQuestion = "Detail the layers, anatomical components, innervation, and functions of the Pelvic Floor Muscles (levator ani).",
            oneLineAnswer = "The pelvic floor is a muscular sling primarily composed of the levator ani and coccygeus muscles, supporting pelvic viscera, maintaining continence, and innervated by S3-S4 and the pudendal nerve.",
            detailedAnswer = "Anatomically structured in three layers: 1. Superficial perineal pouch (ischiocavernosus, bulbospongiosus, superficial transverse perineal). 2. Urogenital diaphragm (deep transverse perineal, sphincter urethrae). 3. Pelvic diaphragm (levator ani: puborectalis, pubococcygeus, iliococcygeus; and coccygeus). Functions include supporting abdominal and pelvic organs, resistance to increased intra-abdominal pressure, and urethral/anal sphincter control. Innervation is by the perineal branch of the pudendal nerve (S2-S4) and direct branches from the sacral plexus (S3-S4).",
            examinerFavorites = listOf(
                "Detail the boundaries of the urogenital and anal triangles.",
                "Which muscle forms a sling around the anorectal junction to maintain fecal continence?",
                "State the muscles forming the pelvic diaphragm."
            ),
            rapidRevision = listOf(
                "Levator Ani parts: Puborectalis, Pubococcygeus, Iliococcygeus.",
                "Innervation of Levator Ani: Direct sacral plexus branches (S3-S4) and perineal nerve (S2-S4).",
                "Main active support against intra-abdominal load."
            ),
            commonMistakes = listOf(
                "Excluding the coccygeus muscle from the pelvic diaphragm.",
                "Confusing the superficial perineal muscles with the deep pelvic floor muscles."
            ),
            clinicalCorrelation = "Weakness of the levator ani leads to pelvic organ prolapse (cystocele, rectocele, uterine prolapse) and stress urinary incontinence (SUI).",
            memoryTricks = "Levator ani is a PPI: Puborectalis, Pubococcygeus, Iliococcygeus."
        ),
        VivaTopic(
            id = "obgyn_kegel_exercises",
            title = "Kegel Exercises & PFM Training",
            category = "Women's Health",
            vivaQuestion = "Outline the prescription, progression, assessment, and dynamic biofeedback mechanisms of Kegel Exercises.",
            oneLineAnswer = "Kegel exercises target the voluntary strengthening of levator ani muscles via repeated, selective contraction-relaxations, prescribed using the PERFECT scheme.",
            detailedAnswer = "Prescription follows the PERFECT framework: P (Power: max voluntary contraction), E (Endurance: hold time), R (Repetitions of endurance count), F (Fast contractions: quick 1-second flicks), E (Elevation: cranial lift), C (Co-contraction of transverse abdominis), T (Timing: cough lock). Prescribed as 3 sets of 10-12 slow holds (progressing from 3 to 10 seconds) matched with fast flicks, 3-4 times weekly. Direct digital palpation (Modified Oxford Scale 0-5) or perineometry assesses muscle strength before training.",
            examinerFavorites = listOf(
                "Explain the 'PERFECT' scheme for pelvic floor assessment.",
                "What is 'the Knack' or cough-lock maneuver and why is it useful?",
                "How does digital palpation grade pelvic floor strength?"
            ),
            rapidRevision = listOf(
                "Modified Oxford Scale: 0: No contraction, 3: Moderate contraction with lift, 5: Strong contraction with suction/lift.",
                "PERFECT scheme: Power, Endurance, Repetitions, Fast flicks, Every (or Elevation), Co-contraction, Timing.",
                "The Knack: Pre-contraction of PFM before coughing/sneezing to prevent leakage."
            ),
            commonMistakes = listOf(
                "Assuming co-contraction of gluteals or adductors is a correct Kegel; it is a common substitution error.",
                "Over-training the PFM without dedicated relaxation phases, leading to hypertonic pelvic pain."
            ),
            clinicalCorrelation = "First-line therapy for Stress Urinary Incontinence (SUI) and mild-to-moderate pelvic organ prolapse.",
            memoryTricks = "KEGEL: Keep pelvic muscles contracted, Exhale on contraction, Grade using PERFECT, Elevate inside, Lock before cough (Knack)."
        ),
        VivaTopic(
            id = "obgyn_antenatal_exercise",
            title = "Antenatal Exercise Prescription",
            category = "Women's Health",
            vivaQuestion = "Synthesize an evidence-based exercise program for uncomplicated pregnancies, including benefits, dosage, type, and hemodynamic changes.",
            oneLineAnswer = "Antenatal exercise involves accumulated low-to-moderate aerobic and pelvic stability training (150 mins/week) to mitigate obstetric lumbar strain and gestational diabetes.",
            detailedAnswer = "Exercise benefits include improved cardiovascular fitness, decreased incidence of gestational diabetes, reduced risk of preeclampsia, and less lower back/pelvic pain. Prescription: Moderate-intensity (RPE 12-14 on Borg scale, 'talk test' compliant), 30 minutes daily or 150 minutes/week. Modes include pelvic tilts, cat-camel, walking, swimming, and stationary cycling. Avoid supine exercises after 16-20 weeks gestation due to aortocaval compression (supine hypotensive syndrome).",
            examinerFavorites = listOf(
                "Explain Supine Hypotensive Syndrome and how to resolve it.",
                "What is the heart rate range or Borg RPE rating recommended for pregnant women?",
                "State three major musculoskeletal adaptations in pregnancy affecting posture."
            ),
            rapidRevision = listOf(
                "150 minutes of moderate-intensity aerobic physical activity per week is standard.",
                "Supine position shift: Place patient in left lateral decubitus to free the inferior vena cava.",
                "Adaptations: Increased lordosis, forward head posture, anterior pelvic tilt, ligamentous laxity (relaxin)."
            ),
            commonMistakes = listOf(
                "Prescribing contact sports or activities with high fall risk (e.g., horseback riding, downhill skiing).",
                "Forgetting to monitor maternal hydration and core temperature to prevent hyperthermia."
            ),
            clinicalCorrelation = "Reduces the risk of macrosomia, facilitates uncomplicated vaginal delivery, and hastens postpartum pelvic recovery.",
            memoryTricks = "PREG-FIT: Pace (RPE 12-14), Relaxin caution, Evade supine position, Gentle resistance, Fluids & Thermoregulation."
        ),
        VivaTopic(
            id = "obgyn_postnatal_exercise",
            title = "Postnatal Exercise & Recovery",
            category = "Women's Health",
            vivaQuestion = "Detail the progression of postpartum exercises from immediate post-delivery to 6-8 weeks postpartum.",
            oneLineAnswer = "Postnatal exercises progress from immediate respiratory, circulatory, and gentle PFM tilts to progressive core stabilization and low-impact aerobics after obstetric clearance.",
            detailedAnswer = "Immediate Phase (Day 1 to 2 weeks postpartum): Focus on deep diaphragmatic breathing, pelvic tilts, pelvic floor contractions (Kegels), and circulatory ankle pumps to prevent deep vein thrombosis. Intermediate Phase (2 to 6 weeks): Progress to transverse abdominis curls, gentle bridge, cat-camel, and graded walking. Late Phase (6-8 weeks onward): After pelvic and incision healing clearances, introduce moderate bodyweight exercises, low-impact aerobics, and graded rectus abdominis training (ensuring no diastasis recti dome-lag).",
            examinerFavorites = listOf(
                "When can a postpartum patient safely return to high-impact running or loading?",
                "What exercises would you prescribe to a patient on Day 1 post-Cesarean delivery?",
                "How do you check for Diastasis Recti before initiating rectus exercises?"
            ),
            rapidRevision = listOf(
                "Day 1: Leg pumps, deep breathing, pelvic tilt, huffing for incisional protection.",
                "No heavy lifting (> baby's weight) or high-impact training until 12 weeks postpartum.",
                "Delay loaded crunches if a diastasis recti gap is greater than 2 fingerbreadths."
            ),
            commonMistakes = listOf(
                "Prescribing rapid double-leg raises or heavy abdominal curls immediately postpartum, causing excessive perineal and rectus shear.",
                "Assuming C-section patients do not need pelvic floor rehabilitation (pregnancy itself stretches the pelvic floor)."
            ),
            clinicalCorrelation = "Early postnatal physical therapy significantly reduces the risk of postpartum low back pain, pelvic girdle pain, and persistent abdominal wall diastasis.",
            memoryTricks = "POST-NATAL: Pumps early, Organic tilt, Safe Kegels, TA activation, No early crunches, Aerobics later, Left-lateral rest."
        ),
        VivaTopic(
            id = "obgyn_exercise_contraindications",
            title = "Ob-Gyn Exercise Contraindications",
            category = "Women's Health",
            vivaQuestion = "Differentiate between Absolute and Relative Contraindications to exercise during pregnancy as outlined by ACOG.",
            oneLineAnswer = "ACOG lists specific maternal/obstetric conditions where exercise is strictly dangerous (Absolute) versus conditions requiring cautious medical clearance (Relative).",
            detailedAnswer = "Absolute Contraindications: Ruptured membranes, premature labor, incompetent cervix (cerclage), persistent 2nd/3rd trimester vaginal bleeding, placenta previa after 26 weeks, preeclampsia/gestational hypertension, restrictive lung disease, hemodynamically significant heart disease. Relative Contraindications: Chronic bronchitis, severe anemia, maternal cardiac arrhythmia, poorly controlled type 1 diabetes, extreme morbid obesity or underweight (BMI <12), intrauterine growth restriction (IUGR), poorly controlled hypertension/hyperthyroidism.",
            examinerFavorites = listOf(
                "Name 4 absolute contraindications to prenatal exercise.",
                "What signs would indicate that a pregnant woman must immediately stop exercising?",
                "Why is Placenta Previa an absolute contraindication to exercise?"
            ),
            rapidRevision = listOf(
                "Absolute Contraindications: Hemorrhage, membrane rupture, preeclampsia, cardiac disease.",
                "Relative Contraindications: Mild anemia, mild pulmonary pathology, chronic mild diabetes.",
                "Immediate stop physical signs: Vaginal fluid escape, dyspnea before exertion, chest pain, calf pain, uterine contractions."
            ),
            commonMistakes = listOf(
                "Assuming mild gestational diabetes is a contraindication (exercise is actually a primary management strategy for it!).",
                "Confusing gestational hypertension (absolute) with well-controlled pre-existing hypertension (relative with clearance)."
            ),
            clinicalCorrelation = "Adhering strictly to ACOG guidelines ensures safety and prevents adverse maternal-fetal outcomes such as placental abruption, fetal distress, or preterm birth.",
            memoryTricks = "ACOG-STOP: Amniotic leak, Cord/placenta previa, Obstetric bleeding, Gestational preeclampsia. (Absolute warnings)."
        )
    )

    var topics = initialTopics
}
