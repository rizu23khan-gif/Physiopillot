package com.example.data

data class ClinicalCaseDefinition(
    val id: String,
    val title: String,
    val scenario: String,
    val question: String,
    val diagnosis: String,
    val anatomicalBasis: String,
    val pathophysiology: String,
    val clinicalTests: String,
    val functionalConsequences: String,
    val physiotherapyRelevance: String,
    val examinerPearl: String
)

data class NerveDefinition(
    val nerveName: String,
    val rootValues: String,
    val motorLoss: String,
    val sensoryLoss: String,
    val deformity: String,
    val functionalDeficit: String,
    val clinicalSign: String
)

data class GaitDefinition(
    val gaitType: String,
    val observation: String,
    val weakStructure: String,
    val compensation: String,
    val phaseAffected: String,
    val clinicalSignificance: String
)

object ClinicalCaseRepo {
    val clinicalCases = listOf(
        // UPPER LIMB CRITICAL CASES
        ClinicalCaseDefinition(
            id = "case_ul_1",
            title = "Frozen Shoulder",
            scenario = "50yo F, progressive shoulder pain and stiffness of 6 months duration. Severe pain at night, especially when lying on the affected side. Passive external rotation, abduction, and internal rotation are severely limited.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Adhesive Capsulitis (Frozen Shoulder) in the Frozen stage.",
            anatomicalBasis = "Thickening and contracture of the glenohumeral joint capsule, particularly the anterior joint capsule and the axillary recess.",
            pathophysiology = "Chronic synovial inflammation yielding hypervascular synovitis, followed by progressive collagen deposition, capsule thickening, and severe downstream intra-articular fibrosis.",
            clinicalTests = "ROM assessment revealing a rigid capsular pattern pattern restriction: passive External Rotation loss is most severe, followed by Abduction and then Internal Rotation (ER > ABD > IR).",
            functionalConsequences = "Critical limitation in activities of daily living involving shoulder elevation or reach, such as grooming (combing hair), fasten bras behind back (IR), and retrieving wallet from back pocket.",
            physiotherapyRelevance = "During this stiff stage, treatment focuses on pain-free passive ROM expansion, joint mobilization (Maitland Grade I & II for pain-relief, and Grade III & IV as pain subsides), and gentle progressive stretching.",
            examinerPearl = "Pain at night combined with a matching loss in both active AND passive external rotation is highly diagnostic, confidently separating frozen shoulder from a purely functional rotator cuff tendon lesion."
        ),
        ClinicalCaseDefinition(
            id = "case_ul_2",
            title = "Rotator Cuff Tear",
            scenario = "45yo male construction contractor presents with persistent dull ache in the lateral shoulder and severe pain on reaching overhead. Active abduction is weak, and pain is sharpest in the 60-120 degree arc. Passive ROM is fully intact.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Rotator Cuff Tendinopathy / Partial-to-Complete Supraspinatus Tendon Tear.",
            anatomicalBasis = "Supraspinatus muscle tendon inserting into the greater tubercle of the humerus, frequently compressed under the acromion process.",
            pathophysiology = "Microvascular compromise, chronic mechanical impingement, and repetitive tensile shear stresses leading to tendon fraying, degeneration, and eventually an acute or chronic cellular structural tear.",
            clinicalTests = "Positive Empty Can (Jobe's) Test, Positive Hawkins-Kennedy Impingement Test, and positive Drop Arm Test for high-grade or full-thickness tears.",
            functionalConsequences = "Profound loss of active upper extremity elevation, overhead working ability, and sudden arm control, precluding overhead reach and heavy manual lifting tasks.",
            physiotherapyRelevance = "Conservative management focuses on subacromial decompression via scapular stabilizer strengthening (Seratus Anterior, Trapezius) and progressive rotator cuff eccentric strengthening to center the humeral head.",
            examinerPearl = "Unlike adhesive capsulitis, a patient with a rotator cuff tear typically retains normal passive ROM when the arm is moved gently by the clinician, despite severe active weakness."
        ),
        ClinicalCaseDefinition(
            id = "case_ul_3",
            title = "Tennis Elbow",
            scenario = "35yo professional tennis athlete presents with localized pain on the lateral aspect of the right elbow. The pain is triggered by shaking hands, gripping a racket, and is highly tender to palpation immediately distal to the epicondyle.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Lateral Epicondyalgia (Tennis Elbow).",
            anatomicalBasis = "The common extensor origin (CEO) on the lateral epicondyle of the humerus, specifically the origin fibers of the Extensor Carpi Radialis Brevis (ECRB) tendon.",
            pathophysiology = "Not acute inflammation (-itis), but rather angiofibroblastic hyperplasia (tendinosis) featuring disorganized type-III collagen, hypervascularity, and high-concentration inflammatory pain mediators in response to repetitive microtrauma.",
            clinicalTests = "Positive Cozen's Test (pain on resisted wrist extension with elbow flexed), Positive Mill's Test (pain on passive wrist flexion and elbow extension), and high tenderness over lateral humerus epicondyle.",
            functionalConsequences = "Profound difficulty with gripping objects, turning doorknobs, carrying bags, and typing on a keyboard, with gripping triggering intense local lateral arm pain.",
            physiotherapyRelevance = "Treatment utilizes progressive pain-free eccentric strengthening of ECRB, cross-friction massage, soft-tissue mobilization, and counterforce bracing (elbow strap) to minimize strain over CEO during manual load.",
            examinerPearl = "Remember that Tennis Elbow is a classic chronic degenerative tendinopathy (tendinosis) rather than acute inflammatory tendinitis. High-dose local anti-inflammatory meds have minimal efficacy compared to eccentrics."
        ),
        ClinicalCaseDefinition(
            id = "case_ul_4",
            title = "Carpal Tunnel Syndrome",
            scenario = "40yo typist presents with progressive burning numbness, tingling, and radiating paresthesia over the palmar aspects of the thumb, index, and middle fingers. Symptoms worsen significantly at night. Mild thenar muscle wasting is observed.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Carpal Tunnel Syndrome (CTS) secondary to median nerve compression.",
            anatomicalBasis = "Median nerve passing through the osteofibrous carpal tunnel beneath the rigid transverse carpal ligament (flexor retinaculum) of the wrist.",
            pathophysiology = "Elevated intra-tunnel pressure exceeding capillary perfusion threshold, leading to venous congestion, local ischemia, myelin sheath degradation, and chronic conduction deceleration.",
            clinicalTests = "Positive Phalen's Maneuver (sustained wrist flexion), Positive Tinel's Sign at the wrist (percussion reproducing tingling), and diminished sensation over the lateral three and a half digits.",
            functionalConsequences = "Loss of manual dexterity, persistent dropping of drinking glasses or coffee mugs, and difficulty with fine motor actions like typing, writing, or buttoning a shirt.",
            physiotherapyRelevance = "Includes neural mobilization flossing, active tendon gliding exercises, volar wrist splinting in neutral (especially at night to reduce compression), and ergonomic typing modification.",
            examinerPearl = "The palmar cutaneous branch of the median nerve branches off proximal to the carpal tunnel, which is why cutaneous sensation over the central palm is classically spared in CTS."
        ),
        ClinicalCaseDefinition(
            id = "case_ul_5",
            title = "Erb's Palsy",
            scenario = "Newborn delivery featuring severe shoulder dystocia. The infant presents with the right upper arm adducted and internally rotated, the forearm extended and pronated, and the wrist severely flexed.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Erb-Duchenne Palsy (Upper Brachial Plexus Injury).",
            anatomicalBasis = "The upper trunk of the brachial plexus, specifically the C5 and C6 nerve roots (Erb's Point).",
            pathophysiology = "Traction force or excessive lateral stretch separating the infant's neck and shoulder, causing nerve stretching (neuropraxia), tearing (rupture), or avulsion from the spinal cord.",
            clinicalTests = "Clinical observation revealing the classic 'Waiters Tip' or 'Porter's Tip' posture, combined with the complete absence of the Moro Reflex and biceps reflex on the affected side.",
            functionalConsequences = "Complete loss of active shoulder abduction, external rotation, forearm supination, and elbow flexion, preventing normal reach-to-mouth or bilateral grasping actions.",
            physiotherapyRelevance = "Early gentle passive stretching to prevent adduction/internal rotation contractures, positioning to prevent joint subluxation, sensory stimulation, and tactile retraining.",
            examinerPearl = "Erb's palsy involves C5-C6 roots, disabling the Deltoid, Biceps, Infraspinatus, and Supinator. Sensory loss is typically localized over the lateral dermis of the upper arm."
        ),
        ClinicalCaseDefinition(
            id = "case_ul_6",
            title = "Wrist Drop",
            scenario = "20yo male athlete presents to the emergency room following a mid-shaft humeral fracture. He is completely unable to actively extend his wrist or fingers, resulting in a persistent downward hanging hand posture.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Radial Nerve Injury (High Radial Nerve Palsy) resulting in Wrist Drop.",
            anatomicalBasis = "The radial nerve wrapping around the radial groove (spiral groove) of the mid-shaft humerus.",
            pathophysiology = "Mechanic shear force, compression, or direct nerve laceration by the fractured humerus bone fragments, arresting nerve action potential propagation.",
            clinicalTests = "Inability to perform active extension of the wrist, thumb, and metacarpophalangeal (MCP) joints. Intact sensation is tested over the dorsal web space.",
            functionalConsequences = "Inability to release objects or position the hand in extension, which severely compromises grip strength due to active insufficiency of the flexors.",
            physiotherapyRelevance = "Application of a dynamic wrist-extension splint to keep wrist in functional extension (optimizing flexor mechanics), aggressive passive stretching to prevent wrist flexion contracture, and electrical stimulation.",
            examinerPearl = "If the radial nerve is injured in the spiral groove, triceps function (elbow extension) is spared because its motor branches branch off proximal to this point in the axilla."
        ),

        // LOWER LIMB CRITICAL CASES
        ClinicalCaseDefinition(
            id = "case_ll_1",
            title = "ACL Tear",
            scenario = "25yo soccer player experiences a sudden, non-contact decelerating rotation of the tibia on his planted foot. He heard a loud 'pop', felt his knee buckle, and developed severe joint effusion within 2 hours.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Acute Anterior Cruciate Ligament (ACL) Tear.",
            anatomicalBasis = "The anterior cruciate ligament of the knee, which prevents anterior translation of the tibia on the femur.",
            pathophysiology = "Excessive torsional shear load exceeding the tensile point of the ACL, leading to complete structural fiber rupture and intra-articular hemarthrosis.",
            clinicalTests = "Positive Lachman's Test (most sensitive), Positive Anterior Drawer Test, and Positive Pivot-Shift Test.",
            functionalConsequences = "Profound dynamic knee instability, feeling of knee 'giving way' during pivot movements, and severe pain limiting weight-bearing ambulation.",
            physiotherapyRelevance = "Pre-operative rehab (Prehab) focusing on swelling reduction, expanding extension ROM, and quadriceps/hamstring strengthening. Post-operative rehab focuses on graft protection, progressive kinetic chain strengthening.",
            examinerPearl = "Rapid onset joint swelling (within 2 hours) is highly indicative of blood in the joint (hemarthrosis) from an ACL tear or osteochondral fracture, unlike delayed meniscus-associated swelling."
        ),
        ClinicalCaseDefinition(
            id = "case_ll_2",
            title = "Meniscus Injury",
            scenario = "30yo knee athlete presents with localized pain on the medial joint line of the knee. He describes episodes of the knee clicking, locking during weight-bearing flexion, and mild delayed swelling.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Medial Meniscal Tear (classic bucket-handle or degenerative tear).",
            anatomicalBasis = "The medial meniscus (C-shaped fibrocartilaginous disc) anchored to the tibia plateau.",
            pathophysiology = "Rotational weight-bearing torque grinding the femoral condyle across the compressed meniscus, causing a mechanical shear tear of the cartilage fibers.",
            clinicalTests = "Positive McMurray's Test (reproducing pain/click), Positive Apley's Compression Test, and highly localized tenderness to deep palpation over the medial joint line.",
            functionalConsequences = "Inability to squat, climb stairs, or twist without sharp pain or locked knee joint, causing a localized antalgic limp.",
            physiotherapyRelevance = "Pain and effusion control, quadriceps muscle setting, hip abductor strengthening, and closed-chain lower extremity alignment exercises to minimize load over knee compartments.",
            examinerPearl = "The outer red zone of the meniscus has a rich blood supply and can heal, whereas the inner white zone is completely avascular and must rely on synovial fluid diffusion, rarely healing without surgery."
        ),
        ClinicalCaseDefinition(
            id = "case_ll_3",
            title = "Trendelenburg Gait",
            scenario = "60yo female patient presents with a distinct pelvic drop on the right side when walking, which occurs during the left single-leg stance. She recently had a total hip arthroplasty via a lateral approach.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Trendelenburg Gait secondary to Left Gluteus Medius Weakness.",
            anatomicalBasis = "The gluteus medius muscle, innervated by the superior gluteal nerve (L4-S1).",
            pathophysiology = "Neuropraxia of the superior gluteal nerve or direct muscle split trauma during the lateral surgical orthopedic dissection of the hip.",
            clinicalTests = "Positive Trendelenburg Test (pelvis drops on the contralateral side when standing on the affected leg).",
            functionalConsequences = "Inability to stabilize the pelvis in the frontal plane during gait stance phase, leading to high metabolic cost of ambulation and compensatory trunk leaning.",
            physiotherapyRelevance = "Targeted strengthening of the weak gluteus medius under single-leg stance, progressive lateral band walks, and pelvic stabilization gait retraining.",
            examinerPearl = "Be clear: Trendelenburg Gait is named for the gait pattern, where weakness on the stance-side (e.g. left) causes the contralateral pelvis (right) to drop when lifted."
        ),
        ClinicalCaseDefinition(
            id = "case_ll_4",
            title = "Foot Drop",
            scenario = "50yo male presents with an inability to lift his left foot while walking, resulting in him frequently tripping over his toes. He reports a history of prolonged knee casting following a fibular neck fracture.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Common Peroneal (Fibular) Nerve Compression palsy.",
            anatomicalBasis = "Common peroneal nerve wrapping superficial to the neck of the fibula bone.",
            pathophysiology = "Direct mechanical compression of the nerve against the fibular neck, leading to local ischemia and high conduction failure (neuropraxia).",
            clinicalTests = "Zero or poor ankle dorsiflexion strength, absence of foot eversion, and sensory loss over the lateral leg and dorsum of the foot.",
            functionalConsequences = "Ankle drop causing toes to drag during swing phase, forcing compensatory hip and knee hyperflexion (steppage gait) to clear the ground.",
            physiotherapyRelevance = "Prescribing an Ankle-Foot Orthosis (AFO) to maintain neutral ankle dorsiflexion during swing phase, passive stretching to prevent gastrocnemius tightness, and neuromuscular re-education.",
            examinerPearl = "The common peroneal nerve divides into deep and superficial; deep peroneal nerve palsy isolates dorsiflexors, while superficial isolates the peroneal evertor muscles."
        ),
        ClinicalCaseDefinition(
            id = "case_ll_5",
            title = "Sciatica",
            scenario = "40yo male presents with an intense, shooting, electric-like pain radiating from his lower back, down the posterior thigh, lateral calf, into the dorsal aspect of the foot. The pain spikes on coughing.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Lumbar Radiculopathy / Sciatica (highly correlative to an L5/S1 disc herniation).",
            anatomicalBasis = "The sciatic nerve roots, specifically the L5 or S1 nerve roots forming the sciatic nerve plexus.",
            pathophysiology = "Mechanical herniation of the nucleus pulposus producing direct physical compression and inflammatory cytokine-mediated chemical irritation of the nerve root.",
            clinicalTests = "Positive Straight Leg Raise (SLR) Test (onset of radicular pain between 30-70 degrees), Positive Braggard's Test, and diminished Achilles reflex (S1).",
            functionalConsequences = "Severe sitting intolerance, severe pain on trunk flexion, and inability to perform basic transfers or walking without a significant antalgic posture.",
            physiotherapyRelevance = "Application of the McKenzie Method (Mechanical Diagnosis and Therapy - MDT) to find a directional preference (typical extension to centralize pain), lumbar stabilization core exercises, and nerve glides.",
            examinerPearl = "True sciatica is characterized by pain radiating below the knee in a dermatomal pattern, easily differentiating it from local somatic referred pain from lumbar facet joints."
        ),
        ClinicalCaseDefinition(
            id = "case_ll_6",
            title = "Plantar Fasciitis",
            scenario = "45yo female presents with sharp, stabbing pain under the heel of her foot. The pain is extremely severe when taking her very first steps in the morning, which gradually eases after walking for 10 minutes.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Plantar Fascial Tendinopathy (Plantar Fasciitis).",
            anatomicalBasis = "The plantar fascia (aponeurosis) at its proximal insertion into the medial tubercle of the calcaneus.",
            pathophysiology = "Repetitive tensile pull overstraining the fascia, leading to microtears, collagen matrix degeneration, calcaneus traction osteophyte formation, and local tissue thickening.",
            clinicalTests = "Positive Windlass Test (pain on passive dorsiflexion of the great toe) and extreme tenderness on deep palpation of the anteromedial calcaneal tubercle.",
            functionalConsequences = "Profound limitation in morning mobility and walking endurance, causing an abbreviated weight-bearing pattern during heel-strike of gait.",
            physiotherapyRelevance = "Treatment utilizes rigorous stretching of gastrocnemius/soleus and plantar fascia, prescribing silicone heel cups to reduce impact, calf muscle eccentric loading, and foot intrinsic muscle activation.",
            examinerPearl = "The characteristic peak of pain upon morning waking occurs because the foot is in plantarflexion overnight, causing the inflamed fascia to shorten and undergo high-tension stretch on first weight-bearing."
        ),

        // SPINE CASES
        ClinicalCaseDefinition(
            id = "case_sp_1",
            title = "Cervical Radiculopathy",
            scenario = "50yo male programmer presents with neck pain radiating down his right lateral arm, forearm, and into his index finger. He describes numbness in the thumb and index finger, and weak elbow flexion.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "C6 Cervical Radiculopathy.",
            anatomicalBasis = "The C6 cervical nerve root exiting the intervertebral foramen of the lower cervical spine.",
            pathophysiology = "Compressed nerve root from a herniated cervical disc or osteophytic osteoarthritic outgrowth (foraminal stenosis).",
            clinicalTests = "Positive Spurling's Test (cervical compression reproducing arm pain), Positive Cervical Distraction Test (symptom reduction), and diminished Brachioradialis reflex.",
            functionalConsequences = "Unable to sustain computed typing with forearm flexed, overhead reach pain, and frequent dropped objects due to sensory impairment of thumb and C6 weakness.",
            physiotherapyRelevance = "Cervical retraction exercises (directional preference), manual cervical traction, deep neck flexor strengthening (Craniocervical flexion), and mechanical posture retraining.",
            examinerPearl = "A positive Spurling's test must replicate the *radicular arm pain* down the mechanical dermatome to be diagnostic, rather than just neck pain."
        ),
        ClinicalCaseDefinition(
            id = "case_sp_2",
            title = "Lumbar Disc Prolapse",
            scenario = "30yo male warehouse worker manual lifted a 50kg crate with a bent back and felt a sudden snap. He now presents with severe lower back pain and acute central spasm, with trunk flexion being completely locked.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Acute Lumbar Herniated Nucleus Pulposus (Disc Prolapse).",
            anatomicalBasis = "The intervertebral disc, specifically the annulus fibrosus tear permitting posterior extrusion of the gel-like nucleus pulposus.",
            pathophysiology = "High mechanical flexion-rotation torque tearing the circular fibers of the annulus fibrosus, letting the soft nucleus push backward, compressing the dural sac or descending nerve roots.",
            clinicalTests = "Extremely positive Straight Leg Raise, severe pain on trunk flexion, and trunk deviation tilt (compensatory lateral shift away from herniation).",
            functionalConsequences = "Profound walking and sitting intolerance, complete loss of spinal mobility, and inability to perform any functional heavy lifting task.",
            physiotherapyRelevance = "Postural correction of lateral shift, progressive extension exercises (McKenzie centralization), core neuromuscular control training, and spinal stabilization.",
            examinerPearl = "A central herniation compressing the cauda equina is a medical emergency requiring immediate diagnostic referral. Look for bowel/bladder dysfunction, saddle anesthesia, and bilateral foot drop."
        ),
        ClinicalCaseDefinition(
            id = "case_sp_3",
            title = "Facet Joint Syndrome",
            scenario = "45yo female presents with localized lower back pain that flares up significantly on standing or arching her back backward (extension). The pain is dull and aching, referable to the flank, but never radiates below the knee.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Lumbar Facet Joint Syndrome.",
            anatomicalBasis = "The zygapophyseal joints (facet joints) of the lumbar vertebrae.",
            pathophysiology = "Repetitive spinal hyperextension leading to osteoarthritic cartilage degeneration, synovial inflammation, and joint space narrowing under load.",
            clinicalTests = "Positive Kemp's Test (reproducing local lower back pain on combined passive trunk extension, side-bending, and rotation), and deep paraspinal tenderness.",
            functionalConsequences = "Inability to stand for long periods of time, inability to reach overhead (requires extension), and constant back fatigue.",
            physiotherapyRelevance = "Strengthening of deep abdominal stabilizers to reduce anterior pelvic tilt, pelvic tilts, flexion-biased exercises, and posture retraining.",
            examinerPearl = "Facet pain is extension-sensitive and localized, whereas disc pain is flexion-sensitive and typically central or radiating."
        ),
        ClinicalCaseDefinition(
            id = "case_sp_4",
            title = "Spondylolisthesis",
            scenario = "15yo female competitive gymnast presents with persistent deep mid-lumbar pain. Pain is severely exacerbated by gymnastic back-bending. There is a palpable 'step-off' deformity in her lower back.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Spondylolisthesis (Isthmic type) secondary to spondylolysis.",
            anatomicalBasis = "The pars interarticularis of the L5 vertebra, letting L5 slip anteriorly over the S1 vertebral body.",
            pathophysiology = "Repeated lumbo-sacral hyperextension stress causing micro-fractures in the fragile pars interarticularis (spondylolysis), culminating in complete separation and forward slippage of the vertebral body.",
            clinicalTests = "Palpable Step-Off Deformity upon spinous process palpation, Positive Stork Test (one-legged hyperextension reproducing back pain).",
            functionalConsequences = "Inability to perform athletic back-bend actions without pain, severe muscle spasms of hamstrings, and local core bracing fatigue.",
            physiotherapyRelevance = "Strict avoidance of hyperextension, intensive core stabilization (William's flexion exercises, transversus abdominis bracing), and hamstring stretching.",
            examinerPearl = "Hamstring tightness is a classic reflex stabilizer sign secondary to spondylolisthesis. Always screen gymnasts with extension-based lower back pain for pars defects."
        ),

        // NEUROLOGY CASES
        ClinicalCaseDefinition(
            id = "case_nr_1",
            title = "Stroke",
            scenario = "65yo male presents with sudden-onset right-sided hemiplegia. When evaluated 4 weeks post-stroke, he displays marked spasticity (increased resistance to passive stretch) and typical flexor synergy patterns in his right upper limb.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Post-Stroke Spastic Hemiplegia (UMN lesion).",
            anatomicalBasis = "The internal capsule or motor cortex in the left cerebral hemisphere, fed by the middle cerebral artery (MCA).",
            pathophysiology = "Ischemic or hemorrhagic rupture of vessels leading to localized cortical necrosis, disabling descending corticospinal motor tracts, triggering hyperreflexia and disinhibited spinal reflexes.",
            clinicalTests = "Modified Ashworth Scale (MAS) to grade spasticity, Babinski sign (extensor plantar response), and Brunnstrom stages of motor recovery evaluation.",
            functionalConsequences = "Loss of independent gait, loss of fine and gross manual dexterity in the dominant hand, and dependency for basic ADLs (bathing, grooming).",
            physiotherapyRelevance = "Task-oriented motor retraining, neurotherapeutic approaches (Bobath/NDT, PNF), functional electrical stimulation (FES), and constraint-induced movement therapy (CIMT).",
            examinerPearl = "In UMN lesions like stroke, spasticity is velocity-dependent—passive movement resisting strongly only when moved fast."
        ),
        ClinicalCaseDefinition(
            id = "case_nr_2",
            title = "Parkinsonism",
            scenario = "70yo male presents with a slow, shuffling gait, difficulty initiating steps, a resting 'pill-rolling' tremor in his hands, and a blank, mask-like facial expression.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Parkinson's Disease (idiopathic parkinsonism).",
            anatomicalBasis = "The Substantia Nigra pars compacta within the basal ganglia of the midbrain.",
            pathophysiology = "Progressive degeneration of dopaminergic neurons, depleting dopamine levels in the striatum, which disinhibits the indirect motor loop and shuts down smooth motor facilitation.",
            clinicalTests = "Evaluation of rigidity (Lead-pipe or Cogwheel rigidity found on passive wrist/elbow rotation), assessment for bradykinesia, and pull test for postural instability.",
            functionalConsequences = "Frequent fall risks due to postural instability, episodes of motor freezing (especially near doorways/tight spots), and gradual loss of functional independence in locomotion.",
            physiotherapyRelevance = "Implementation of external sensory cueing (auditory metronome beats, laser lines on floor to bypass basal ganglia), balance and fall-prevention training, and high-amplitude trunk/limb exercises (LSVT BIG).",
            examinerPearl = "Parkinsonian tremors decrease during active, purposeful motor movement, contrasting sharply with cerebellar intention tremors."
        ),
        ClinicalCaseDefinition(
            id = "case_nr_3",
            title = "Bell's Palsy",
            scenario = "30yo female wakes up to find she cannot close her left eye or smile symmetrically. When she tries to smile, her mouth pulls aggressively to the right. She cannot wrinkle her left forehead skin.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Bell's Palsy (unilateral lower motor neuron facial nerve palsy).",
            anatomicalBasis = "The Facial Nerve (Cranial Nerve VII), as it passes through the stylomastoid foramen of the temporal bone.",
            pathophysiology = "Viral or idiopathic inflammation leading to swelling, ischemia, and subsequent myelin conduction block of CN VII inside its bony canal.",
            clinicalTests = "Observing facial symmetry during active movements: forehead wrinkling, forceful eye closure (observing Bell's Phenomenon), smiling, and puffing cheeks.",
            functionalConsequences = "Severe risk of corneal ulceration due to inability to close the eye (lagophthalmos), drooling during drinking, and dysarthria.",
            physiotherapyRelevance = "Urgent referral for facial taping/eye patching during sleep, prescribing artificial tears, progressive facial neuromuscular re-education exercises, and biofeedback.",
            examinerPearl = "UMN facial palsy (e.g. stroke) spares the forehead because of bilateral cortical innervation. LMN facial palsy (Bell's) affects the entire half-face, including the forehead."
        ),
        ClinicalCaseDefinition(
            id = "case_nr_4",
            title = "Spinal Cord Injury",
            scenario = "20yo male athlete presents following a motor vehicle accident that fractured his T10 vertebra. He has complete loss of motor function and sensation in both of his lower limbs, whereas his arms remain fully functional.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Complete T10 Paraplegia (Spinal Cord Injury, ASIA Grade A).",
            anatomicalBasis = "The spinal cord thoracic segment T10 level.",
            pathophysiology = "Severe mechanical transaction or ischemic necrosis of the spinal cord, permanently cutting off ascending sensory signals and descending motor signals distal to the lesion.",
            clinicalTests = "The International Standards for Neurological Classification of Spinal Cord Injury (ISNCSCI) exam (ASIA Scale) to determine motor and sensory levels.",
            functionalConsequences = "Permanent loss of walking ability, complete loss of voluntary bowel and bladder control, and high risk of pressure ulcers over sacrum.",
            physiotherapyRelevance = "Long-term transition training: wheelchair mobility training, bed mobility and transfer training, passive stretching to prevent contractures in hamstrings, pressure relief education.",
            examinerPearl = "Sensation at the level of the umbilicus corresponds precisely to the T10 dermatome, serving as a reliable clinical landmark."
        ),

        // CARDIORESPIRATORY CASES
        ClinicalCaseDefinition(
            id = "case_cd_1",
            title = "COPD",
            scenario = "70yo heavy smoker presents with progressive chronic dyspnea, a persistent productive morning cough for several years, a distinct barrel chest, and an audible expiratory wheeze.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Chronic Obstructive Pulmonary Disease (COPD) - Chronic Bronchitis and Emphysema.",
            anatomicalBasis = "The lower bronchial tree and alveolar walls.",
            pathophysiology = "Repeated inhalation of toxic cigarette particles triggering chronic airway inflammation, leading to excessive mucus production (bronchitis), destruction of alveolar elastic fibers (emphysema), and permanent airway entrapment.",
            clinicalTests = "Pulmonary Function Testing (Spirometry) showing gold-standard FEV1/FVC ratio < 0.70; visual check of hyper-inflated chest (barrel chest).",
            functionalConsequences = "Severely limited exercise tolerance, profound dyspnea during minimal exertion (showering, dressing), and generalized muscle deconditioning.",
            physiotherapyRelevance = "Pulmonary Rehabilitation program: teaching pursed-lip breathing (to keep airways splinted open), diaphragmatic breathing, posture drainage, and titrated cardio training.",
            examinerPearl = "Pursed-lip breathing builds a back-pressure in the airways, preventing collapse of unstable terminal bronchioles during forced expiration."
        ),
        ClinicalCaseDefinition(
            id = "case_cd_2",
            title = "Pleural Effusion",
            scenario = "50yo male presents with localized right-sided pleuritic chest pain that worsens on deep inspiration, and a dry cough. Chest percussion reveals flat, stony dullness over the right lung base.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Pleural Effusion (right lower lung field).",
            anatomicalBasis = "The pleural cavity (potential space) between the visceral and parietal pleural linings.",
            pathophysiology = "Inflammatory or hydrostatic imbalance shifting excessive fluid out of capillaries into the pleural space, compressing the lung tissue beneath.",
            clinicalTests = "Thoracic auscultation showing complete absence of vocal resonance and breath sounds over the right lung base, combined with a stony dull percussion response.",
            functionalConsequences = "Compromised tidal volume, severe dyspnea, and pleuritic pain leading to restrictive chest expansion.",
            physiotherapyRelevance = "Thoracic expansion exercises, deep breathing exercises (DBE) to promote re-expansion of collapsed lung once thoracentesis drainage is performed, and positive-pressure devices.",
            examinerPearl = "Stony dull percussion is the absolute physical diagnostic highlight for pleural effusion, contrasting sharply with hyper-resonance found in pneumothorax."
        ),
        ClinicalCaseDefinition(
            id = "case_cd_3",
            title = "Atelectasis",
            scenario = "35yo postoperative female is on bed rest 2 days after a major abdominal surgery. She displays rapid, shallow breathing, mild hypoxia (92% SpO2), and decreased breath sounds in the posterior-inferior lung fields.",
            question = "a) Most likely diagnosis\nb) Structure involved\nc) Clinical test\nd) Physiotherapy implication",
            diagnosis = "Postoperative Atelectasis (basal lung collapse).",
            anatomicalBasis = "The alveoli of the lower segments of the lungs.",
            pathophysiology = "Ineffective cough, dry mucosal plugs, and shallow breathing (due to incisional abdominal pain) preventing alveolar ventilation, leading to rapid collapse of alveolar air spaces.",
            clinicalTests = "Asymmetrical chest excursion, tactile fremitus, and low-amplitude breath sounds with crackles on auscultation over the lower lung lobes; chest X-ray proof of lung volume loss.",
            functionalConsequences = "Compromised gas exchange (hypoxia), dry restrictive ventilation pain, and significant risk of postsurgical pneumonia.",
            physiotherapyRelevance = "Instruction in Incentive Spirometry, deep breathing exercises with lateral costal expansion, active coughing with surgical wound splinting (holding pillow over abdomen), and early progressive mobilization.",
            examinerPearl = "Postoperative pain is the primary driver of shallow tidal volume. Splinted coughing is vital to help patients clear secretions without risking wound dehiscence."
        )
    )

    val nerveDefinitions = listOf(
        NerveDefinition(
            nerveName = "Radial Nerve",
            rootValues = "C5, C6, C7, C8, T1 (Posterior Cord of Brachial Plexus)",
            motorLoss = "Triceps brachii, Anconeus, Brachioradialis, Extensor Carpi Radialis Longus & Brevis, Supinator, and Finger Extensors (ED, EDM, ECU, EPL, EPB, APL, EIP).",
            sensoryLoss = "Posterior arm and forearm, and the lateral aspect of the dorsum of the hand (especially the dorsal first web space).",
            deformity = "Wrist Drop (MCP joint flexion and wrist hanging limply).",
            functionalDeficit = "Inability to release objects from hand because extensors cannot position the wrist to optimize flexion power.",
            clinicalSign = "Inability to actively extend wrist or fingers against gravity, and diminished Supinator/Triceps reflex."
        ),
        NerveDefinition(
            nerveName = "Median Nerve",
            rootValues = "C6, C7, C8, T1 (Lateral and Medial Cords of Brachial Plexus)",
            motorLoss = "Pronator Teres & Quadratus, Flexor Carpi Radialis, Palmaris Longus, FDS, FDP (Lateral Head), Flexor Pollicis Longus, Thenar Muscles (Abductor PB, Opponens, Flexor PB) and 1st/2nd Lumbricals.",
            sensoryLoss = "Palmar surface of the lateral three and a half digits (Thumb, Index, Middle, and lateral half of Ring finger) and thenar palm area.",
            deformity = "Ape Hand (Thenar wasting and thumb rotated flat into plane of hand) and 'Spinster's Hand' or 'Hand of Benediction' upon attempt to fist.",
            functionalDeficit = "Inability to perform key pinching, opposable grip, and fine precision actions.",
            clinicalSign = "Positive Phalen's wrist test, positive Tinel's sign at carpal tunnel, and flat thumb contour."
        ),
        NerveDefinition(
            nerveName = "Ulnar Nerve",
            rootValues = "C8, T1 (Medial Cord of Brachial Plexus)",
            motorLoss = "Flexor Carpi Ulnaris, Flexor Digitorum Profundus (Medial half), Hypothenar muscles, Interossei (Palmar & Dorsal), 3rd/4th Lumbricals, and Adductor Pollicis.",
            sensoryLoss = "Palmar and dorsal surface of the medial one and a half digits (Little finger and medial half of Ring finger).",
            deformity = "True Ulnar Claw Hand (Hyperextension of MCP and hyperflexion of IP joints of 4th/5th digits at rest).",
            functionalDeficit = "Severe loss of finger abduction/adduction grasp power, fine finger spacing, and stable power grip.",
            clinicalSign = "Positive Froment's Sign (thumb IP flexion during paper pull), and positive Tinel's at the cubital tunnel."
        ),
        NerveDefinition(
            nerveName = "Axillary Nerve",
            rootValues = "C5, C6 (Posterior Cord of Brachial Plexus)",
            motorLoss = "Deltoid and Teres Minor.",
            sensoryLoss = "A badge-like area over the lateral upper shoulder (Regimental Badge Area).",
            deformity = "Flattening of the lateral shoulder profile (loss of rounded Deltoid muscle mass contours).",
            functionalDeficit = "Profound weakness in active shoulder abduction, particularly past the first 15 degrees.",
            clinicalSign = "Severe muscle atrophy of the Deltoid and sensory deficit over the regimental patch."
        ),
        NerveDefinition(
            nerveName = "Musculocutaneous Nerve",
            rootValues = "C5, C6, C7 (Lateral Cord of Brachial Plexus)",
            motorLoss = "Coracobrachialis, Biceps Brachii, and Brachialis.",
            sensoryLoss = "Lateral aspect of the forearm (via the Lateral Cutaneous Nerve of the Forearm).",
            deformity = "Marked wasting of the anterior compartment of the arm (anterior arm flattening).",
            functionalDeficit = "Profound weakness in elbow flexion (specifically with forearm supinated) and weakness in forearm supination.",
            clinicalSign = "Complete loss of Biceps Tendon Reflex and sensory loss over the lateral forearm."
        ),
        NerveDefinition(
            nerveName = "Femoral Nerve",
            rootValues = "L2, L3, L4 (Lumbar Plexus posterior divisions)",
            motorLoss = "Iliopsoas (partial), Pectineus, Sartorius, and Quadriceps Femoris (Rectus Femoris, Vastus Lateralis/Medialis/Intermedius).",
            sensoryLoss = "Anterior and medial aspects of the thigh, and the medial leg down to the medial malleolus (Saphenous nerve).",
            deformity = "Quadriceps muscle wasting, instability of the knee on load.",
            functionalDeficit = "Inability to actively extend the knee against gravity, difficulty climbing stairs, and knee buckling during stance phase.",
            clinicalSign = "Complete loss of Patellar Reflex (Knee-jerk) and quadriceps flaccid paralysis."
        ),
        NerveDefinition(
            nerveName = "Obturator Nerve",
            rootValues = "L2, L3, L4 (Lumbar Plexus anterior divisions)",
            motorLoss = "Adductor Longus, Adductor Brevis, Adductor Magnus (anterior part), Gracilis, and Obturator Externus.",
            sensoryLoss = "A small localized patch on the medial aspects of the middle third of the thigh.",
            deformity = "Medial thigh wasting, leg postured in persistent abduction.",
            functionalDeficit = "Severe weakness in thigh adduction, making leg crossing extremely difficult or impossible.",
            clinicalSign = "Zero or extremely weak resisted hip adduction and local medial thigh sensory loss."
        ),
        NerveDefinition(
            nerveName = "Tibial Nerve",
            rootValues = "L4, L5, S1, S2, S3 (Sciatic Nerve division)",
            motorLoss = "Gastrocnemius, Soleus, Plantaris, Popliteus, Tibialis Posterior, Flexor Hallucis Longus, Flexor Digitorum Longus, and Intrinsic Foot Muscles.",
            sensoryLoss = "Posterolateral lower leg, lateral heel, and the entire plantar surface (sole) of the foot.",
            deformity = "Calcaneovalgus foot deformity (heel down, dorsiflexed, and everted).",
            functionalDeficit = "Inability to plantarflex the ankle/toes, making heel lift or tip-toe standing impossible during walk.",
            clinicalSign = "Loss of Achilles Reflex (Ankle-jerk) and sensory loss on the plantar foot."
        ),
        NerveDefinition(
            nerveName = "Common Peroneal Nerve",
            rootValues = "L4, L5, S1, S2 (Sciatic Nerve division)",
            motorLoss = "Tibialis Anterior, EHL, EDL, Peroneus Tertius, Peroneus Longus & Brevis, and EDB.",
            sensoryLoss = "Lateral aspect of the leg and the entire dorsum of the foot.",
            deformity = "Equinovarus foot deformity (Foot Drop: plantarflexed and inverted at rest).",
            functionalDeficit = "Inability to actively dorsiflex or evert the foot, causing constant dragging of toes during swing phase.",
            clinicalSign = "Foot drop, step-like high lifting of knee during walk (steppage gait), and sensory patch loss."
        ),
        NerveDefinition(
            nerveName = "Sciatic Nerve",
            rootValues = "L4, L5, S1, S2, S3 (Sacral Plexus)",
            motorLoss = "Hamstrings (Biceps Femoris, Semitendinosus, Semimembranosus), Adductor Magnus (posterior part), and all muscles of the leg and foot (via Tibial and Peroneal branches).",
            sensoryLoss = "Lateral and posterior leg, sole and dorsum of the foot, sparing only the medial leg strip.",
            deformity = "Total flaccid foot (Flail foot: complete foot drop with clawing of toes).",
            functionalDeficit = "Inability to flex the knee actively, and total paralysis of all foot and ankle joint actions.",
            clinicalSign = "Complete loss of ankle-jerk and distal leg reflexes, severe distal leg muscle wasting, and highly positive Straight Leg Raise (SLR)."
        )
    )

    val gaitDefinitions = listOf(
        GaitDefinition(
            gaitType = "Trendelenburg Gait",
            observation = "Contralateral (opposite side) drop of the pelvis when standing on the affected, weak leg.",
            weakStructure = "Gluteus Medius (Hip Abductors) on the stance side.",
            compensation = "Trunk tilt lean toward the affected/stance side (compensated Trendelenburg lurch).",
            phaseAffected = "Single-leg Stance Phase of the affected limb.",
            clinicalSignificance = "Indicates severe hip abductor muscle weakness, superior gluteal nerve lesion, or mechanical hip joint instability."
        ),
        GaitDefinition(
            gaitType = "Steppage Gait",
            observation = "Exaggerated lifting of the hip and knee high into the air during locomotion.",
            weakStructure = "Tibialis Anterior (Ankle Dorsiflexors).",
            compensation = "Excessive hip and knee flexion to ensure that the dropping toes clear the ground.",
            phaseAffected = "Swing Phase of the downward dropping foot.",
            clinicalSignificance = "Classic presentation of Foot Drop secondary to peroneal nerve palsy, L5 radiculopathy, or direct nerve trauma."
        ),
        GaitDefinition(
            gaitType = "Antalgic Gait",
            observation = "Markedly shortened stance phase on the painful leg, with a rapid step-through on the healthy leg.",
            weakStructure = "Any structural element causing acute pain in the lower extremity (e.g. bone, tendon, ligament).",
            compensation = "Reduced weight-bearing duration on the painful side, limp, and guarded trunk trunk posture.",
            phaseAffected = "Stance Phase of the painful limb.",
            clinicalSignificance = "Immediate diagnostic indicator of acute or localized painful structural weight-bearing pathology."
        ),
        GaitDefinition(
            gaitType = "Circumduction Gait",
            observation = "The leg is swung outward in a wide lateral circular arc (abduction swing) instead of crossing straight.",
            weakStructure = "Ankle Dorsiflexors or Hip Flexors, or structural difficulty in shortening the leg.",
            compensation = "Lateral pelvic elevation and trunk lean to sweep the stiff, structurally long leg outward.",
            phaseAffected = "Swing Phase.",
            clinicalSignificance = "Commonly observed in spastic hemiplegia where knee/ankle joints are locked in spastic extension."
        ),
        GaitDefinition(
            gaitType = "Hemiplegic Gait",
            observation = "The affected leg is held extended, adducting, internally rotated, with foot dropping down and circling.",
            weakStructure = "Upper Motor Neuron control pathways of the contralateral motor hemisphere.",
            compensation = "Trunk lean and abduction swing (circumduction) of the stiff, hypertonic leg.",
            phaseAffected = "Swing Phase and initial contact.",
            clinicalSignificance = "Pathognomonic gait pattern following a stroke, traumatic brain injury, or unilateral spinal cord injury."
        ),
        GaitDefinition(
            gaitType = "Parkinsonian Gait",
            observation = "Shuffling, small slow steps, forward flexed trunk posture, diminished arm swing, and festination (rushing steps).",
            weakStructure = "Basal Ganglia motor planning network circuitry (dopamine deplete).",
            compensation = "Accelerated forward steps (festination) to catch up with the advanced center of gravity.",
            phaseAffected = "Throughout the entire gait cycle (affects initiation, cadence, and deceleration stages).",
            clinicalSignificance = "Diagnostic pattern of advanced Parkinson's disease or drug-induced parkinsonism syndromic states."
        ),
        GaitDefinition(
            gaitType = "Ataxic Gait",
            observation = "Wide-based, unsteady, staggering, clumsy steps with highly erratic foot placement.",
            weakStructure = "Cerebellar sensory/motor coordination center pathways.",
            compensation = "Persistent widening of the stance base of support to prevent tipping sideways.",
            phaseAffected = "All gait phases.",
            clinicalSignificance = "Indicator of cerebellar lesion, Friedreich's ataxia, or sensory neuropathy affecting proprioceptive loops."
        ),
        GaitDefinition(
            gaitType = "Waddling Gait",
            observation = "Lateral trunk sway and exaggerated pelvic lurches alternating on both left and right sides (duck-like).",
            weakStructure = "Bilateral Hip Abductors (Bilateral Gluteus Medius).",
            compensation = "Exaggerated lateral trunk shifts alternating left/right to keep balance over single-stance feet.",
            phaseAffected = "Stance phases.",
            clinicalSignificance = "Classically observed in muscular dystrophies (e.g. Duchenne MD), advanced congenital hip dislocation, or pelvic muscle structural hypoplasia."
        )
    )
}
