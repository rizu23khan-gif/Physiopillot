package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "clinical_postings")
@Serializable
data class SurvivalModule(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val iconName: String, // e.g. "opd", "ward", "icu", "neuro", "ortho", "peds", "community"
    val whatToObserve: List<String>,
    val whatToAsk: List<String>,
    val whatToRecord: List<String>,
    val casePresentationFormat: List<String>,
    val soapNoteTemplate: SurvivalSoapNote,
    val assessmentSequence: List<String>,
    val professionalConduct: List<String>,
    val clinicalViva: List<SurvivalVivaQuestion>,
    val commonMistakes: List<String>
)

@Serializable
data class SurvivalSoapNote(
    val subjective: String,
    val objective: String,
    val assessment: String,
    val plan: String
)

@Serializable
data class SurvivalVivaQuestion(
    val question: String,
    val answer: String
)

object ClinicalPostingSurvivalRepo {
    val initialModules = listOf(
        SurvivalModule(
            id = "opd_posting",
            title = "OPD Posting (Outpatient Department)",
            description = "High-volume musculoskeletal and sports cases. Master rapid triage, postural analysis, and home exercise program prescribing.",
            iconName = "opd",
            whatToObserve = listOf(
                "Gait patterns as patient walks from the waiting area into the cubicle (look for antalgic, trendelenburg, or lurching gaits).",
                "Spontaneous posture while sitting or standing before formal assessment begins (e.g. rounded shoulders, forward head, guarded arm position).",
                "Patient interface and emotional status (anxiety, chronic pain guard, catastrophizing behavior).",
                "Dynamic transfers: How they sit down, stand up, take of their shoes, or handle bags/accessories."
            ),
            whatToAsk = listOf(
                "What is the exact behavior of pain? (Constant vs intermittent, morning stiffness duration, aggravating or relieving factors like load, movements, or rest).",
                "Do you experience any numbness, tingling, or radiation of pain down your arms or legs? (For ruling out radiculopathy/neuropathy).",
                "How does this condition impact your Work, Leisure, and Activities of Daily Living (ADLs)?",
                "What are your specific personal goals for outpatient physical rehabilitation?"
            ),
            whatToRecord = listOf(
                "Vitals: Blood pressure, resting heart rate (mandatory before prescribing any high-intensity therapeutic exercises).",
                "Pain Scores: Visual Analog Scale (VAS) or Numeric Pain Rating Scale (NPRS) at Best, Worst, and current state.",
                "Range of Motion (ROM): Active and Passive joint ranges measured via goniometer (record limitations, pain-free arches, and end-feel).",
                "Manual Muscle Testing (MMT) scores using the MRC grading checklist.",
                "Primary outcome measures (e.g., Oswestry Disability Index, SPADI, or DASH scores)."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: A [Age]-year-old [Occupation] presenting with chief complaints of [Location] pain of [Duration] duration, insidious/traumatic onset.",
                "Clinical Diagnosis Guidelines: Consistent with [e.g., Right Shoulder Subacromial Impingement, Stage II] with functional limitation in [e.g., reaching overhead].",
                "Objective Summary: Decreased active flexion and abduction, positive Neer's and Hawkins-Kennedy tests, strength 4-/5 for supraspinatus.",
                "Management Plan Proposal: Mobilization Grade I/II for pain relief, scapular stabilizers muscle strengthening, and instruction of home exercise programs (HEP)."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "Patient reports central low back pain (4/10 NPRS) following prolonged office sitting, slightly improved by walking. No radicular symptoms.",
                objective = "Inspection: Lumbar lordosis flattened. Range of Motion: Flexion restricted to 50% with pain at end-range; extension is full but produces mild discomfort. Palpation: Moderate paraspinal spasm L3-L5. Special tests: Straight Leg Raise (SLR) is negative bilaterally.",
                assessment = "Mechanical Low Back Pain with associated postural syndrome and core paraspinal muscle insufficiencies. Stage: Subacute; prognosis is strong.",
                plan = "Apply moist heat (10 min) for muscle spasm, manual lumbar central PA mobilization (McKenzie extension program), core activation exercises (abdominal bracing, cat-camel), and deliver pelvic-tilt posture education."
            ),
            assessmentSequence = listOf(
                "1. Demographic Details & Chief Complaint recording.",
                "2. History of Presenting Illness (HPI) & Medical/Surgical profiling.",
                "3. Systems Review (Rule out musculoskeletal flags).",
                "4. Observation (Symmetry, deformity, atrophy, gait, pelvic tilt).",
                "5. Palpation (Local temperature, tenderness grades I-IV, spasm, swelling).",
                "6. Range of Motion (Arom first, then Prom; record pain-free arches and end-feel).",
                "7. Muscle Performance (Resisted isometric checks and formal MMT).",
                "8. Special Tests (Provocative and joint stability maneuvers).",
                "9. Functional Status & Standard Outcome Measures."
            ),
            professionalConduct = listOf(
                "Introduce yourself clearly as a physiotherapy student and ask for informed consent before touching the patient.",
                "Ensure maximum patient modesty by utilizing appropriate draping sheets for target joints.",
                "Communicate instructions using clear, lay-friendly language; avoid using intimidating medical jargon with patients.",
                "Clean and sanitize all shared therapeutic equipment (goniometers, treatment tables, resistance bands) between patient visits."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "Why do we test Active Range of Motion (AROM) before Passive Range of Motion (PROM)?",
                    answer = "Testing AROM first indicates the patient's biological willingness to move, muscle coordination, and identifies pain-free zones, allowing the therapist to safely perform PROM without risking excess overstretch injury."
                ),
                SurvivalVivaQuestion(
                    question = "Explain Tenderness Grading (I-IV) used in clinical documentation.",
                    answer = "Grade I: Pain on palpation; Grade II: Pain with winced expression; Grade III: Pain with withdrawal of body parts; Grade IV: Refusal to allow palpation."
                )
            ),
            commonMistakes = listOf(
                "Forgetting to check the joint above and joint below the pathological site.",
                "Performing provocative orthopedic tests aggressively without establishing a pain-free baseline first."
            )
        ),
        SurvivalModule(
            id = "ward_posting",
            title = "Ward Posting (Post-Op & Medical)",
            description = "Manage inpatient recovery, acute surgical wounds, deep vein thrombosis prophylaxis, and early mobilization protocols.",
            iconName = "ward",
            whatToObserve = listOf(
                "Surgical line drains, IV lines, catheters, and monitoring devices; ensure safe placement before moving the patient.",
                "Patient chest excursion, respiratory effort, use of accessory muscles, and cough effectiveness.",
                "Surgical scar skin integrity (signs of active oozing, localized redness, or wound dehiscence).",
                "Leg swelling, asymmetry, and color of lower limbs (looking for unilateral pitting edema or warmth)."
            ),
            whatToAsk = listOf(
                "How long has it been since you received your last pain medication? (Coordinate therapy sessions to match peak analgesic activity).",
                "Do you feel dizzy, lightheaded, or nauseous upon sitting upright on the edge of the bed? (Checking for orthostatic hypotension).",
                "Are you experiencing any localized, throbbing calf pain or sudden breathing difficulties? (Screening for deep vein thrombosis and pulmonary embolism).",
                "Do you have a personal caretaker at home to assist with transfers and mobilization?"
            ),
            whatToRecord = listOf(
                "Pre- and post-mobilization vitals: SpO2, Heart Rate, Respiration Rate, and Blood Pressure.",
                "Bed mobility status: Independent, Minimal/Moderate Assist, or Dependent.",
                "Incentive spirometry volume achieved in cc/mL (track improvement daily).",
                "Drain volumes and color changes; notify nursing staff immediately if an abrupt increase in bleeding occurs during exercise."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: A [Age]-year-old patient on Post-Op Day [X] following [e.g., Total Knee Arthroplasty (TKA)].",
                "Clinical Progress: Managed with early ankle pumps, active-assisted quadriceps sets, and bed transfers.",
                "Objective Standing: Achieved [e.g., 60] degrees passive knee flexion, independent bed-edge sitting, and stands with minimal assist using a walker.",
                "Immediate Goals: Prevent chest complications, promote early knee ROM, and safely transition to independent walking."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "Patient on post-op day 2 after total hip replacement reports mild groin soreness (3/10 during resting, rising to 6/10 during sit-to-stand).",
                objective = "Vitals stable (SpO2 98%, HR 78, BP 120/80). Surgical incision clean. Active knee flexion achieved 80 deg, hip flexion limited to 90 deg (posterior approach precautions maintained). Patient performed transfers with minimal support.",
                assessment = "Normal subacute recovery pattern post-THA, characterized by transient strength deficits and protective pain.",
                plan = "Perform ankle pumps and static quad/glute isometric sets (3 sets of 10). Teach hip precautions (no flexion > 90°, no adduction, no internal rotation). Mobilize 15 meters using a front-wheeled walker with partial weight-bearing."
            ),
            assessmentSequence = listOf(
                "1. Medical Chart Review (Check surgical notes, strict weight-bearing status, post-op precautions, and lab values/hemoglobin).",
                "2. Pre-Treatment Vital signs assessment.",
                "3. In-bed Observation (Verify patency and length of all clinical tubes, lines, and bags).",
                "4. Chest Assessment (Auscultation, visual expansion, cough assessment).",
                "5. Circulatory Assessment (Calf tenderness, Homan's sign, capillary refill, trophic status).",
                "6. Active/Passive Range of Motion (within safe postoperative boundaries).",
                "7. Bed Mobility and Transfer capability assessment.",
                "8. Early Ambulation Trial with appropriate gait aids."
            ),
            professionalConduct = listOf(
                "Always check with the ward head nurse before mobilizing a patient for the first time post-operation.",
                "Lock all bed and wheelchair brakes securely before attempting transfers.",
                "Ensure the patient is wearing non-slip socks or footwear; never mobilize patients barefoot on slick hospital floors.",
                "Familiarize yourself with surgical protocol constraints (e.g. strict hip precautions vs knee extension immobilization rules)."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "What are the classic posterior hip replacement precautions, and why must they be strictly maintained?",
                    answer = "Precaution rules: No hip flexion past 90 degrees, no hip adduction across midline, and no hip internal rotation. Following these prevents posterior dislocation of the newly implanted femoral head through the healing muscle capsule."
                ),
                SurvivalVivaQuestion(
                    question = "Why do we emphasize ankle pumps post-operatively?",
                    answer = "Ankle pumps activate the calf muscle pump (the peripheral heart), promoting venous return in the lower limbs and dramatically minimizing the risk of Deep Vein Thrombosis (DVT) during periods of immobilization."
                )
            ),
            commonMistakes = listOf(
                "Attempting to mobilize a patient with a post-op Hemoglobin status below 8 g/dL without consulting the medical team.",
                "Accidentally tugging, pulling, or placing traction on urinary catheters, IV lines, or wound drains during bedside transfers."
            )
        ),
        SurvivalModule(
            id = "icu_posting",
            title = "ICU Posting (Intensive Care)",
            description = "High-criticality setting. Demystify mechanical ventilators, ICU-acquired weakness, airway clearance, and passive mobilization in critical care.",
            iconName = "icu",
            whatToObserve = listOf(
                "Mechanical ventilator screen settings (Mode: CMT, SIMV, PSV; PEEP, FiO2 values, respiratory frequency).",
                "ICU monitor displays: Electrocardiogram (ECG) rhythm, arterial blood pressure line waveform, SpO2 trend, and Intracranial Pressure (ICP).",
                "Secretions: Quantity, color (clear, yellow, green), and viscosity of endotracheal or tracheostomy tube aspirates.",
                "Patient alertness and response state (Richmond Agitation-Sedation Scale [RASS] or Glasgow Coma Scale [GCS])."
            ),
            whatToAsk = listOf(
                "Is the patient medically stable for physiotherapy? (Consult the intensive care doctor/nursing officer regarding dynamic inotropic support).",
                "Has the patient been suctioned recently, and what was the tolerance?",
                "Are there any active fractures, unstable spine concerns, or deep bony wounds limiting passive movements?",
                "What is the daily target for sedation vacation/weaning today?"
            ),
            whatToRecord = listOf(
                "Ventilator support limits and arterial blood gas (ABG) values (pH, PaO2, PaCO2, HCO3).",
                "Hemodynamic and oxygen parameters before, during, and directly after chest physiotherapy or passive limb mobilization.",
                "Sensation, reflexes, and level of consciousness score (using GCS/RASS).",
                "Muscle strength index to monitor progression of ICU-Acquired Weakness (ICUAW) or critical illness polyneuropathy."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: A [Age]-year-old patient admitted with [e.g., Acute Respiratory Distress Syndrome (ARDS) secondary to severe pneumonia], intubated and mechanically ventilated.",
                "Physiological Status: Logged in [e.g., SIMV] mode with FiO2 of [e.g., 40%], PEEP of [e.g., 5] cmH2O. Hemodynamically stable on minimal noradrenaline support.",
                "Physical Medicine Findings: Bilateral coarse crackles on auscultation, thick secretions, reduced basal chest expansion, and peripheral hypotonia.",
                "Intervention Proposal: Positioning (prone positioning if ARDS acute, or high semi-recumbent), airway suctioning, manual hyperinflation, chest vibrations, and passive ROM exercises."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "Patient intubated and sedated (RASS -3). Caretaker reports family history of COPD. Nursing reports high secretion load overnight.",
                objective = "Auscultation: Coarse crackles bilaterally in lower lobes. Ventilator: Simv, PEP 5, FiO2 35%. SpO2 95%, HR 85, MAP 75 mmHg. Chest wall compliance reduced.",
                assessment = "Impaired airway clearance and retained tracheobronchial secretions secondary to mechanical ventilation. High risk of ICU-Acquired Weakness.",
                plan = "Perform chest percussion, shaking, and manual vibrations in lateral decubitus positioning. Apply aseptic endotracheal suctioning (white, thick secretions cleared). Administer passive range of motion to all extremities (15 reps each)."
            ),
            assessmentSequence = listOf(
                "1. Medical Chart & ABG Analysis (Analyze lab work, chest X-rays, and current sedation levels).",
                "2. Monitor & Ventilator Review (Safely identify alarm parameters, FiO2, and baseline vitals).",
                "3. Systems Assessment (Observe chest wall movement, thoracic palpation, and auscultatory lung sounds).",
                "4. Neurological status check (Pupillary reflex, sedation state via GCS or RASS).",
                "5. Musculoskeletal assessment (Check limb joint tightness, peripheral muscle tone, and contracture threats).",
                "6. Mobilization readiness screen (using ICU mobility scale checklists)."
            ),
            professionalConduct = listOf(
                "Never silence or mute ventilator, ECG, or line alarms on your own authority; alert the ICU nurse immediately.",
                "Maintain complete aseptic techniques: wash hands, wear gloves, gown, and mask when handling endotracheal/tracheostomy suctioning tubes.",
                "Always monitor the ECG monitor during treatments; stop therapy immediately if malignant arrhythmias (PVCs, VTach) or dangerous BP drops occur.",
                "Coordinate your clinical treatments closely with the respiratory therapist and critical care nurse."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "What is PEEP and what is its physiological significance during chest physiotherapy?",
                    answer = "PEEP stands for Positive End-Expiratory Pressure. It keeps the alveoli open at the end of expiration, preventing atelectasis, optimizing arterial oxygenation, and improving lung compliance."
                ),
                SurvivalVivaQuestion(
                    question = "Define ICU-Acquired Weakness (ICUAW) and how it is diagnosed clinically.",
                    answer = "ICUAW is symmetrical, generalized limb weakness post-admission without an alternative neurologic cause. It is diagnosed using the Medical Research Council (MRC) sum score across 6 muscle groups bilaterally, with a sum score < 48 indicating ICUAW."
                )
            ),
            commonMistakes = listOf(
                "PT mobilization of patients with high inotropic support (e.g. escalating high-dosage norepinephrine or epinephrine pumps).",
                "Neglecting to oxygenate the patient (hyperoxygenation with 100% FiO2) for 30-60 seconds prior to and after performing tracheal suctioning."
            )
        ),
        SurvivalModule(
            id = "neuro_posting",
            title = "Neuro Posting (Neurology Rehabilitation)",
            description = "Neuroplasticity and functional recovery. Master motor control theories, spasticity management, stroke rehab, SCI, and ataxia.",
            iconName = "neuro",
            whatToObserve = listOf(
                "Patient synergy patterns during movement (flexor synergy of upper limb, extensor synergy of lower limb).",
                "Abnormal muscle tone: Spastic catch, clasp-knife tone, rigidity of parkinsonian type, or flaccidity.",
                "Balance and coordination strategies: Ankle, hip, or stepping strategies; intention tremors; dysmetria in reaching tasks.",
                "Functional abilities: Indpendence in rolling, coming to sit, sit-to-stand transitions, and gait cycle deviations."
            ),
            whatToAsk = listOf(
                "Do you experience sudden, involuntary muscle jerks or spasms? (Checking for spasticity, clonus, or flexor spasms).",
                "Do you have difficulty swallowing foods or liquids (Dysphagia) or speaking clearly (Dysarthria)?",
                "Are you experiencing any bladder or bowel dysfunction? (Crucial diagnostic criteria for spinal cord lesions and cauda equina).",
                "Do you feel sensory changes such as numbness, tingling, or crawling sensations (paresthesia)?"
            ),
            whatToRecord = listOf(
                "Mental State: Orientation to time, place, and person (using Mini-Mental State Examination, MMSE).",
                "Tone: Quantified using the Modified Ashworth Scale (MAS) for all major spastic muscle groups.",
                "Functional scores: Functional Independence Measure (FIM) or Barthel Index (BI) for tracking rehabilitation progress.",
                "Coordination: Heel-to-shin test, finger-to-nose test, and rapid alternating movements (Dysdiadochokinesia).",
                "Reflexes: Deep tendon reflexes (0 to 4+) and superficial reflexes (Babinski, abdominal)."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: A [Age]-year-old patient diagnosed with [e.g., Left-sided Hemiplegia secondary to Right MCA Ischemic Stroke] of [Duration] duration.",
                "Functional Assessment: Currently in Brunnstrom Recovery Stage [e.g., 3] for Upper Extremity, presenting with significant spasticity of elbow flexors.",
                "Motor Performance: Fails to extend fingers, displays hemiplegic gait with circumduction, and requires moderate assist for standing balance.",
                "Therapy Goals: Reduce elbow spasticity via dry needling/cryotherapy, promote out-of-synergy motor patterns, and retrain symmetrical weight-bearing."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "Patient with T10 complete spinal cord injury (AIS A, 6 months post-injury) reports muscle spasms in both legs and desire for transfer training.",
                objective = "Lower extremities display flaccid paralysis bilaterally. Muscle tone in lower limbs scored as Grade 0 MAS. Upper-body strength is 5/5 bilaterally. Sensation absent below T10. Independent with wheelchair propulsion on level surfaces.",
                assessment = "Paraplegia secondary to traumatic T10 SCI (AIS A). Fully preserved upper body strength. Core rehabilitation target is maximizing transfer independence and preventing pressure ulcers.",
                plan = "Perform long-sitting balance training and transfer training (slide board transfer to mat table). Instruction on regular 15-minute wheelchair weight relief maneuvers. Prescribe home stretches for hamstrings to keep ROM > 100 deg."
            ),
            assessmentSequence = listOf(
                "1. Higher Mental Functions (Cognition, memory, speech, orientation).",
                "2. Cranial Nerves Examination (I to XII, especially in brainstem/stroke cases).",
                "3. Sensory System Examination (Superficial, deep/proprioception, and cortical sensations).",
                "4. Motor System (Muscle bulk, resting tone [MAS], reflexes, and involuntary movements).",
                "5. Volitional Control (Brunnstrom Stages, isolated movement control).",
                "6. Coordination & Balance (Cerebellar tests, Romberg's test, Berg Balance Scale).",
                "7. Functional Assessment and Gait Analysis."
            ),
            professionalConduct = listOf(
                "Always guard the patient on their weaker (affected) side during transfer and gait training exercises.",
                "When treating patients with sensory deficits, always test the water/ice temperatures with your own hand prior to applying physical modalities.",
                "Be extraordinarily patient; stroke/TBI survivors may display expressive/receptive aphasia, emotional lability, or slow processing speeds.",
                "Never leave a neurological patient unattended on a transfer table or standing in parallel bars."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "Differentiate between Spasticity and Rigidity.",
                    answer = "Spasticity is velocity-dependent hypertonia due to pyramidal (corticospinal) tract damage, showing a clasp-knife response. Rigidity is velocity-independent hypertonia due to extrapyramidal (basal ganglia) tract damage, showing lead-pipe or cogwheel resistance."
                ),
                SurvivalVivaQuestion(
                    question = "What is Autonomic Dysreflexia and why is it a life-threatening medical emergency?",
                    answer = "Autonomic Dysreflexia occurs in patients with SCI at or above T6. It is an uncontrolled sympathetic response triggered by a noxious stimulus below the lesion (such as a blocked urinary catheter). Symptoms include severe hypertension, severe headache, and bradycardia. If untreated, it can cause hemorrhagic stroke or myocardial infarction."
                )
            ),
            commonMistakes = listOf(
                "Permitting a hemiplegic stroke patient to pull up using a walker/cane with their unaffected arm, reinforcing asymmetrical patterns.",
                "Forgetting to inspect the seat, sacral skin, and heels of immobilized neurological patients for skin integrity / pressure sore risks."
            )
        ),
        SurvivalModule(
            id = "orthopedic_posting",
            title = "Orthopedic Posting (Musculoskeletal Rehab)",
            description = "Bone and joint pathology. Master fracture healing stages, post-immobilization stiffness, joint replacements, and arthritis protocols.",
            iconName = "ortho",
            whatToObserve = listOf(
                "Deformity, joint malalignment, or bone asymmetry (such as genu varum, genu valgum, or scoliosis).",
                "Surgical scars, swelling, redness, and surrounding skin integrity (healed by primary intention vs delayed healing).",
                "Atrophy of muscles near the affected joint (e.g., Vastus Medialis Oblique [VMO] atrophy in knee osteoarthritis).",
                "Presence of any external fixation frames, casts, splints, or orthotic braces."
            ),
            whatToAsk = listOf(
                "Did you hear or feel a 'pop', 'crack', or 'snap' during the onset of injury? (To distinguish ligament tears, tendon ruptures, or fractures).",
                "Are you allowed to put full weight on your leg? (What is the surgeon's strict weight-bearing prescription: NWB, PWB, TTWB, or FWB?).",
                "Is your joint stiffness worse in the morning, and how long does it last? (Osteoarthritis shows morning stiffness < 30 mins, RA shows > 1 hour).",
                "Do you feel locking, clicking, or giving way sensations in your joint?"
            ),
            whatToRecord = listOf(
                "Swelling quantification using girth measurements at standardized anatomical landmarks.",
                "Goniometric measurements of active and passive ROM; classify limitations as capsular or non-capsular.",
                "Palpatory tenderness grading over joint lines, ligamentous insertional points, and bony edges.",
                "End-Feel classification of limited movements (e.g. bone-on-bone, springy block, empty, or muscle spasm end-feel).",
                "Special provocative testing profiles for ligaments (e.g., Lachman, anterior drawer, McMurray, or pivot shift)."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: A [Age]-year-old patient presenting with [e.g., Chronic mechanical right Knee Osteoarthritis, Kellgren-Lawrence Grade III].",
                "Subjective Symptoms: Pain (Grade 7/10 VAS during stair-climbing), morning stiffness lasting 15 minutes, reduced walking distance to 200m.",
                "Clinical Objective: Knee flexion ROM limited to 110 degrees, VMO atrophy, crepitus on extension, and positive patellar grind test.",
                "Rehabilitation Strategy: Isometric quadriceps strengthening, patellar mobilization, joint unloading strategies, and low-impact aerobic training."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "Patient with chronic left shoulder stiffness (adhesive capsulitis, freezing stage) reports severe nocturnal pain (8/10 VAS) disrupting sleep.",
                objective = "ROM assessment: Active abduction 70 deg (PROM 80 deg), ER restricted to 15 deg (PROM 20 deg), IR limited to L5 level. Pain triggered at end-range of all movements. Minimal warmth over joint.",
                assessment = "Adhesive Capsulitis of the glenohumeral joint in the acute inflammatory/freezing phase. High irritability profile.",
                plan = "Apply gentle Grade I/II joint oscillations to suppress pain pathways. Teach active-assisted pulley and Codman's pendulum exercises. Instruct patient on sleeping posture (supporting limb on pillows) to relieve nocturnal strain."
            ),
            assessmentSequence = listOf(
                "1. Medical History & Injury Mechanism review (including surgical op-notes, radiography, and MRI reports).",
                "2. Observation of standing alignment, joint symmetry, deformities, and gait.",
                "3. Palpation of local warmth, joint line tenderness, effusion, and bone contours.",
                "4. Range of Motion (Arom, Prom, and identification of capsular restrictions).",
                "5. Muscle Performance checking (MMT of regional joint movers).",
                "6. Specialized Joint Stability Tests (Lachman, Varus/Valgus stress, drawer tests).",
                "7. Functional Ability Score (e.g., timed up-and-go test, step-tests)."
            ),
            professionalConduct = listOf(
                "Never perform aggressive passive joint stretching on a healing fracture before radiological confirmation of union.",
                "Always check and double-check orthopedic precautions (e.g., avoiding knee hyperextension after a posterior capsule repair).",
                "Handle the patient's limb with firm, supportive manual contacts during mobilizations; never grasp the patient over painful or tender muscle bellies.",
                "Keep goniometers and other evaluation tools aligned to accurate bone landmarks during measurement sessions."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "What are the clinical differences between a capsular patten and a non-capsular pattern of joint restriction?",
                    answer = "A capsular pattern is a proportional restriction of movements unique to each joint, indicating inflammation or fibrosis of the entire joint capsule (e.g., Shoulder ER > Abduction > IR). A non-capsular pattern is a localized restriction not in typical proportions, indicating extra-articular pathology or localized internal derangement (e.g., foreign body or isolated ligament adhesion)."
                ),
                SurvivalVivaQuestion(
                    question = "Define the fracture healing stages and explain when exercise can safely commence.",
                    answer = "Stages: 1. Hematoma stage; 2. Fibrocartilaginous callus; 3. Bony callus; 4. Bone remodeling. Gentle isometric exercises of muscles crossing the fracture and active movement of distal joints can begin in Stage 2. Progressive weight-bearing and resisted exercises can start in Stage 3 (bony callus) once radiological union is confirmed."
                )
            ),
            commonMistakes = listOf(
                "Confusing joint effusion with extra-articular localized edema (test knee effusion using the Patellar Tap or Wipe test, not simple girth measurements).",
                "Aggressively stretching knee flexor contractures post-ACL surgery without recognizing graft remodeling limits, leading to graft laxity."
            )
        ),
        SurvivalModule(
            id = "pediatric_posting",
            title = "Pediatric Posting (Developmental Rehab)",
            description = "Milestones and neurodevelopmental therapy. Master normal developmental milestones, cerebral palsy grading, reflex testing, and playful therapy cues.",
            iconName = "peds",
            whatToObserve = listOf(
                "Spontaneous play and movements: How the child interacts with toys and moves across the room (crawling, rolling, cruising, walking).",
                "Developmental posture: Check pelvic tilt, spinal curves, foot arches (flat foot is physiological up to age 2), and hand preference (abnormal if before age 1).",
                "Primitive reflexes (asymmetrical tonic neck reflex - ATNR, Moro, palmar grasp) and check if they are integrated or persistent.",
                "Social interaction, eye contact, and emotional state (stranger anxiety, hyper-irritability)."
            ),
            whatToAsk = listOf(
                "Pregnancy & Birth History: Was the delivery pre-term or full-term? Was there any history of birth asphyxia or delayed crying? (To screen for hypoxic ischemic encephalopathy).",
                "How does the child feed? Does the child present with swallowing difficulties or frequent spitting/choking?",
                "Are they reaching their normal developmental milestones on time? (At what age did they achieve neck control, sit independently, crawl, or walk?).",
                "Does the child display any sensory spikes or meltdowns when exposed to light, touch, or specific noises?"
            ),
            whatToRecord = listOf(
                "Developmental Milestone Checklist: Identify the child's Gross Motor, Fine Motor, Language, and Social milestones.",
                "Muscle Tone (Spasticity): Scored using the pediatric-friendly Modified Tardieu Scale (measure R1 vs R2 angles).",
                "Gross Motor Function Classification System (GMFCS) level (for children with cerebral palsy, Level I to V).",
                "Primitive reflex profile: Log as present, absent, or integrated according to chronological age norms."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: A [Age]-old toddler diagnosed with [e.g., Spastic Diplegic Cerebral Palsy, GMFCS Level II] with history of premature birth.",
                "Milestone Profile: Gross motor development is delayed; currently sitting independently but unable to crawl or stand without support.",
                "Physical Medicine Findings: Bilateral adductor spasticity (Tardieu R1 30 deg, R2 60 deg), scissoring alignment of lower limbs, persistent ATNR.",
                "Rehabilitation Plan: Inhibit ATNR by active tracking exercises, manage hip adductor spasticity via slow prolonged stretching, promote core crawl transitions."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "Mother of a 14-month-old child reports delay in independent sitting. Positive birth history of neonatal ICU stay for 4 days post-birth due to oxygen deprivation.",
                objective = "Child shows poor trunk control, head lag present during pull-to-sit test. Sits only with hand support on a wide base (developmental age ~6 months). Tone is floppy/hypotonic in trunk but shows physiological resistance in limbs.",
                assessment = "Developmental delay in Gross Motor milestones, presenting with trunk hypotonia and delayed postural righting reactions. High risk of evolving Cerebral Palsy.",
                plan = "Perform trunk righting and equilibrium reaction training on a Swiss Gym ball (tilt exercises to activate paraspinal musculature). Practice supported sitting transitions and reach out exercises to promote protective extension."
            ),
            assessmentSequence = listOf(
                "1. Comprehensive Birth History Review (Prenatal, Natal, and Postnatal phases).",
                "2. Non-invasive observation during spontaneous play (identify GMFCS level).",
                "3. Developmental Milestones screening (Gross motor, Fine motor, Communication, Personal-social).",
                "4. Primitive and Postural Reflexes testing (Neck righting, Labyrinthine righting, saving responses).",
                "5. Muscle tone assessment (Distinguish spastic, floppy, athetoid, or ataxic syndromes).",
                "6. Functional movement analysis (Symmetry of rolling, crawling patterns, transitional transitions)."
            ),
            professionalConduct = listOf(
                "Always make therapy fun, playful, and engaging; structure treatment targets inside games, toys, first-best-match plays.",
                "Never leave a pediatric patient unattended on a raised examination couch or balancing roll.",
                "Explain all treatment targets and milestone steps to the parents; empower them with home-based therapy prescriptions.",
                "Adopt a calm, gentle tone of voice; avoid wearing intimidating white surgical coats if they trigger white-coat distress in the child."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "What is the Asymmetrical Tonic Neck Reflex (ATNR) and at what age should it typically integrate?",
                    answer = "ATNR (the 'fencer's posture') is triggered by turning the child's head to one side: the limbs on the face side extend, and the limbs on the occiput side flex. It should integrate by 4 to 6 months of age. Persistence of ATNR beyond 6 months prevents bilateral hand use, roll transitions, and indicates potential corticospinal tract lesion."
                ),
                SurvivalVivaQuestion(
                    question = "Define the 5 levels of the GMFCS for Cerebral Palsy.",
                    answer = "Level I: Walks without limitations; Level II: Walks with limitations; Level III: Walks using a hand-held mobility device; Level IV: Self-mobility with limitations, may use power mobility; Level V: Transported in a manual wheelchair with severe limitations."
                )
            ),
            commonMistakes = listOf(
                "Failing to screen for primitive reflex integration before attempting advanced standing balance training in kids with cerebral palsy.",
                "Applying intense or painful passive stretching techniques, which can trigger severe hypertonus and trust breakdown in parents and children."
            )
        ),
        SurvivalModule(
            id = "community_posting",
            title = "Community Posting (CBR & Geriatric Care)",
            description = "Community-Based Rehabilitation. Master accessibility barrier assessments, ergonomics, work-site evaluations, and home fall risk checklists for older adults.",
            iconName = "community",
            whatToObserve = listOf(
                "Physical environmental barriers in the patient's home (steps without handrails, loose carpets, poor lighting, low toilet seats).",
                "Ergonomics of the patient's primary workspace (chair support height, computer terminal leveling, repetitive motion hazards).",
                "Socio-cultural dynamics: family support network, financial limitations, and local community infrastructure.",
                "Geriatric movement strategies: slow transfers, cautious gait, narrow base of support, and high reliance on household surfaces for balance."
            ),
            whatToAsk = listOf(
                "How many falls have you experienced in the last 12 months? (Fall history is the strongest predictor of future fractures).",
                "What physical hurdles do you face when traveling to the local market, dispensary, or work place?",
                "Can you carry out your daily activities (cooking, bathing, dressing, financial transactions) independently?",
                "Do you have a support system or community volunteer network available in case of emergencies?"
            ),
            whatToRecord = listOf(
                "Home Environmental Fall Hazards Checklist index.",
                "Balance and mobility using the Berg Balance Scale (BBS) or Timed Up and Go (TUG) test (TUG > 12 seconds indicates high fall risk).",
                "Geriatric Depression Scale (GDS) or cognitive screen via Mini-Mental State Examination.",
                "Cardiovascular demand: resting heart rate, BP trends, and RPE rating during normal household walking tasks."
            ),
            casePresentationFormat = listOf(
                "Patient Profile: An [Age]-year-old community-dwelling older adult presenting with postural instability and fear of falling.",
                "Functional Standing: Fails to rise from a chair without hand support, TUG score of [e.g., 16] seconds, BBS score of [e.g., 38/56].",
                "Home Assessment: Lives alone in a home featuring high bathroom steps, loose floor mats, and dim corridor lighting.",
                "Intervention Proposal: Home modifications advice (grab bars, anti-slip mats), lower-limb strength exercises (Ototgo balance trial), and assistive cane fitting."
            ),
            soapNoteTemplate = SurvivalSoapNote(
                subjective = "A 78-year-old female retired teacher reports a fall 2 months ago in her kitchen (slipped on smooth floor). Expresses persistent fear of falling and avoids leaving her apartment.",
                objective = "Gait is cautious, slow speed. TUG test: 15.5 seconds (indicates moderate-to-high fall risk). BBS: 40/56. Hip abductor and quadriceps strength is 3+/5 bilaterally. Home access features 4 steps at entry without a handrail.",
                assessment = "High risk of falls secondary to age-related muscular deconditioning, impaired balance strategies, and home hazards.",
                plan = "Initiate Otago exercise protocol: standing leg raises, heel-toe walking, and progressive squats. Provide formal home safety checklist (recommend steps handrail and grabbing bars). Train in safe fall-recovery transfers."
            ),
            assessmentSequence = listOf(
                "1. Patient Profile, fallback medical records, and social history review.",
                "2. Comprehensive Fall Risk Assessment (History of falls, TUG, Berg Balance Scale).",
                "3. Instrumental Activities of Daily Living (IADL) and Barthel index check.",
                "4. Musculoskeletal & Neuromuscular deconditioning screening.",
                "5. Home Environment & Work-site hazard walk-through evaluation.",
                "6. Psychological fear-of-falling questionnaire (FES-I index)."
            ),
            professionalConduct = listOf(
                "Always treat elderly patients with immense respect and dignity, showing empathy towards slow movements or processing times.",
                "Consider the financial feasibility of any recommended home modifications (prioritize low-cost fixes like removing loose carpets or reorganizing furniture).",
                "Perform assessments safely; always use a gait belt during balance testing for high-risk elderly patients.",
                "Incorporate community-based peer support and group exercises to reduce depression and physical isolation in mature adults."
            ),
            clinicalViva = listOf(
                SurvivalVivaQuestion(
                    question = "What is the clinical cutoff value for the Timed Up and Go (TUG) test in predicting fall risk in older adults?",
                    answer = "A TUG score of 12 to 13.5 seconds or greater is the standard clinical cutoff. Clinically, score of >12 seconds indicates that the patient has high fall risk and requires progressive balance and lower limb conditioning."
                ),
                SurvivalVivaQuestion(
                    question = "Define Community-Based Rehabilitation (CBR) and its primary objective.",
                    answer = "CBR is a multi-sectoral strategy within general community development for the rehabilitation, equalization of opportunities, and social integration of all people with disabilities. Its primary objective is to maximize functional capability, empower the individual within their local community, and build barrier-free accessibility."
                )
            ),
            commonMistakes = listOf(
                "Prescribing complex, machine-dependent gym exercises for a rural or community-based patient who cannot access or afford a fitness center.",
                "Assuming cognitive or physical decline is 'normal aging' and failing to screen for remediable factors like vestibular imbalances or orthostatic blood pressure drops."
            )
        )
    )

    var modules = initialModules
}
