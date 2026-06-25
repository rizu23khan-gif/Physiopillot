package com.example.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class ConceptCard(
    val title: String,
    val icon: ImageVector,
    val content: List<String>,
    val level: Int = 2
)

data class MemoryZone(
    val mnemonic: String,
    val vivaPoints: List<String>,
    val frequentlyConfused: Pair<String, String>,
    val examPearls: List<String>,
    val quickRevision: List<String>
)

object ClinicalEducationEngine {

    fun getThreeLevelExplanation(chapterId: String, chapterName: String, subject: String, definitionList: List<String>): Triple<String, String, String> {
        val baseDef = if (definitionList.isNotEmpty()) definitionList.joinToString(" ") else "Study of clinical fundamentals of $chapterName in $subject."
        
        val level1 = "Exam Definition: $baseDef"
        
        val level2 = when {
            chapterId.startsWith("anat") -> {
                "Analogy: Think of $chapterName as a critical structural landmark in the body's scaffolding. Just like a building needs pillars and wiring in exact places, the human body places $chapterName precisely to support movement, protect vital nerves/vessels, and transmit mechanical force during physical activity."
            }
            chapterId.startsWith("phys") -> {
                "Analogy: Think of $chapterName as a specialized physiological circuit or engine. Under the hood, your body runs countless homeostatic loops; $chapterName is the automatic regulator that coordinates ion flows, muscle contractions, or chemical signals to keep the system balanced during rest and exercise."
            }
            chapterId.startsWith("bio") -> {
                "Analogy: Think of $chapterName as the mechanical levers and pulleys of a crane. Your bones act as levers, joints as axes, and muscles as engines. Biomechanics shows how $chapterName optimizes force distribution to prevent structural wear-and-tear during movement."
            }
            chapterId.startsWith("path") -> {
                "Analogy: Think of $chapterName as a cellular security breach or plumbing breakdown. When healthy cells are overwhelmed by stress, the normal mechanism of $chapterName is disrupted, triggering a cascade of inflammatory alerts, structural changes, and tissue dysfunctions."
            }
            chapterId.startsWith("micro") -> {
                "Analogy: Think of $chapterName as invisible microscopic hitchhikers. Some of these microorganisms are harmless, but pathogens causing $chapterName bypass local immune barriers, multiply rapidly, and can turn a standard recovery ward into an infectious zone if precautions are neglected."
            }
            chapterId.startsWith("pharm") -> {
                "Analogy: Think of $chapterName as a molecular key entering a highly specific biological lock. Once administered, these chemical compounds bind to receptors to either boost or block bodily activities, helping manage pain, inflammation, or spasticity."
            }
            chapterId.startsWith("el") || chapterId.startsWith("et") -> {
                "Analogy: Think of $chapterName as a therapeutic electrical dialogue with tissue. By adjusting parameters like frequency, pulse width, and intensity, we can stimulate sensory nerves to block pain (like a gate blocking traffic) or stimulate motor nerves to rebuild weak muscles."
            }
            chapterId.startsWith("ex") -> {
                "Analogy: Think of $chapterName as graded physical medicine. By systematically applying mechanical load, passive stretch, or active resistance, we stimulate physiological tissue adaptation, re-educate motor pathways, and restore painless joint kinematics."
            }
            else -> {
                "Analogy: Think of $chapterName as a clinical clinical guide. Standardizing this concept allows physical therapists to formulate a diagnostic hypothesis, assess risk, and track progress using measurable checkpoints."
            }
        }

        val level3 = when {
            chapterId.startsWith("anat") -> {
                "Physiotherapy Clinical Reasoning: For therapists, knowing $chapterName is essential for manual palpation, joint mobilizations, and identifying peripheral nerve entrapments. When a patient presents with movement limitation, precise anatomical tracing allows you to target the exact restricted structure or affected nerve pathway."
            }
            chapterId.startsWith("phys") -> {
                "Physiotherapy Clinical Reasoning: Understanding this cellular/systemic physiology allows therapists to monitor vital signs safely, prevent over-fatigue, and customize aerobic training. By knowing the underlying metabolic or neuromuscular thresholds, you can safely scale therapeutic exercise."
            }
            chapterId.startsWith("bio") -> {
                "Physiotherapy Clinical Reasoning: Mechanical loading is the primary tool of physical therapy. By analyzing the lever arms, torque, and joint reaction forces of $chapterName, you can adjust patient positioning (such as moving a weight closer to the joint) to decrease stress on vulnerable tissues."
            }
            chapterId.startsWith("path") -> {
                "Physiotherapy Clinical Reasoning: Understanding the stages of cellular damage and tissue healing dictates your treatment timing. For instance, aggressive stretching is contraindicated during the acute inflammatory phase of $chapterName, but essential during chronic remodeling."
            }
            chapterId.startsWith("micro") -> {
                "Physiotherapy Clinical Reasoning: Physical therapy clinics feature high patient-equipment contact (treatment tables, therapeutic ultrasound heads, weights). Understanding transmission pathways of $chapterName ensures strict compliance with surface disinfection and hand hygiene to prevent healthcare-associated outbreaks."
            }
            chapterId.startsWith("pharm") -> {
                "Physiotherapy Clinical Reasoning: Pharmacological agents directly modify patient responsiveness. Therapists must coordinate therapy sessions around peak drug effects (e.g., initiating gait drills 30 minutes after analgesic intake) while screening for dangerous side effects like orthostatic hypotension."
            }
            chapterId.startsWith("el") || chapterId.startsWith("et") -> {
                "Physiotherapy Clinical Reasoning: Electrophysical modalities must be carefully dosed based on tissue biophysics. Selecting the correct modality and parameter (like choosing a pulse frequency of 80-120 Hz for acute pain) allows you to achieve targeted therapeutic effects without skin burns."
            }
            chapterId.startsWith("ex") -> {
                "Physiotherapy Clinical Reasoning: Prescription of therapeutic movement requires strict biomechanical and physiological dosage. Correcting alignment errors, adjusting resistance levels, and prescribing appropriate rest intervals ensures tissue healing and functional motor re-education."
            }
            else -> {
                "Physiotherapy Clinical Reasoning: Evidence-based assessment protocols ensure legal accountability and trackable patient outcomes. Incorporating standardized scales into your daily SOAP notes provides clear proof of functional progression."
            }
        }

        return Triple(level1, level2, level3)
    }

    fun getSubjectLayout(chapterId: String, subjectId: String, content: InteractiveChapterContent): List<ConceptCard> {
        val list = mutableListOf<ConceptCard>()
        val name = content.chapterName

        when {
            subjectId.equals("anatomy", ignoreCase = true) || chapterId.startsWith("anat") -> {
                list.add(ConceptCard("Anatomical Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Detailed spatial and structural definition of $name.") }))
                list.add(ConceptCard("Easy Explanation (ChatGPT style)", Icons.Default.Lightbulb, listOf("Think of $name as a main structural landmark. It is positioned to provide stability, accommodate movement, and shield critical neurovascular passages. If this structure is damaged or compressed, it directly impacts distal muscle function and sensation.")))
                
                if (content.principle.isNotEmpty()) {
                    list.add(ConceptCard("Visual Understanding & Spatial Orientation", Icons.Default.Visibility, content.principle))
                } else {
                    list.add(ConceptCard("Visual Understanding & Spatial Orientation", Icons.Default.Visibility, listOf("Palpate the bony landmarks surrounding the $name. Mentally trace its origin, insertion, and the neurovascular structures running superficial or deep to it to prevent clinical injury during manual therapy.")))
                }
                
                list.add(ConceptCard("Memory Trick & Mnemonics", Icons.Default.Psychology, listOf(getCustomMnemonic(chapterId, name))))
                
                if (content.clinicalPearls.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Correlation (BPT Application)", Icons.Default.Healing, content.clinicalPearls))
                } else {
                    list.add(ConceptCard("Clinical Correlation (BPT Application)", Icons.Default.Healing, listOf("Injury to this region often leads to predictable motor deficits and referral pain patterns. Exercises should target restoration of local tissue glide and biomechanical alignment.")))
                }
                
                if (content.vivaQuestions.isNotEmpty()) {
                    list.add(ConceptCard("High-Yield Viva Tips", Icons.Default.RecordVoiceOver, content.vivaQuestions.take(3)))
                } else {
                    list.add(ConceptCard("High-Yield Viva Tips", Icons.Default.RecordVoiceOver, listOf("Explain the clinical presentation of nerve injury at this level.", "Identify the key bony boundaries during oral examinations.")))
                }
            }
            
            subjectId.equals("physiology", ignoreCase = true) || chapterId.startsWith("phys") -> {
                list.add(ConceptCard("Physiological Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Study of normal physiological operations of $name.") }))
                list.add(ConceptCard("Explain Like ChatGPT", Icons.Default.Forum, listOf("In simple terms, $name is like a self-regulating circuit. When internal or external conditions shift (like walking into cold air or starting a sprint), this system alters cellular permeability, hormone secretion, or heart rate to maintain homeostatic balance.")))
                
                if (content.principle.isNotEmpty()) {
                    list.add(ConceptCard("Step-by-Step Mechanism", Icons.Default.FormatListNumbered, content.principle))
                }
                
                list.add(ConceptCard("Why It Matters", Icons.Default.HelpOutline, listOf("Without this physiological regulation, the human body could not generate coordinated muscle contractions, deliver oxygen to tissues, or clear metabolic waste during high-intensity physiotherapy sessions.")))
                list.add(ConceptCard("Memory Trick", Icons.Default.Psychology, listOf(getCustomMnemonic(chapterId, name))))
                list.add(ConceptCard("Common Confusion Clearer", Icons.Default.Warning, listOf("Students often mix up active and passive mechanisms here. Remember: if it requires ATP or active transport, it goes against the gradient. If it is passive diffusion, it happens spontaneously.")))
                
                if (content.clinicalPearls.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Correlation", Icons.Default.MedicalServices, content.clinicalPearls))
                }
            }
            
            subjectId.contains("biomechanics", ignoreCase = true) || chapterId.startsWith("bio") -> {
                list.add(ConceptCard("Mechanical Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Biomechanical and kinematic definition of $name.") }))
                list.add(ConceptCard("Simple Explanation", Icons.Default.Forum, listOf("Think of $name as how mechanical forces act upon the human skeleton. Every bone is a lever, every joint is an axis, and muscles pull on these levers to create motion. Biomechanics analyzes these forces to prevent injury.")))
                
                if (content.principle.isNotEmpty()) {
                    list.add(ConceptCard("Movement Analysis & Forces", Icons.Default.DirectionsRun, content.principle))
                } else {
                    list.add(ConceptCard("Movement Analysis & Forces", Icons.Default.DirectionsRun, listOf("Analyze the joint reaction forces, muscle moment arms, and lines of gravity to calculate mechanical efficiency during active motion.")))
                }
                
                list.add(ConceptCard("Clinical Example", Icons.Default.Accessibility, listOf("When a patient has a muscle weakness or joint restriction, their body compensates by using alternative levers. This increases mechanical stress elsewhere, leading to chronic overuse injuries.")))
                list.add(ConceptCard("Memory Trick", Icons.Default.Psychology, listOf(getCustomMnemonic(chapterId, name))))
            }
            
            subjectId.equals("pathology", ignoreCase = true) || chapterId.startsWith("path") -> {
                list.add(ConceptCard("Pathological Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Definition of $name disease state.") }))
                list.add(ConceptCard("Disease Mechanism", Icons.Default.Coronavirus, content.principle.ifEmpty { listOf("pathogenesis cascade involving cellular stress, inflammatory mediators, and tissue destruction.") }))
                list.add(ConceptCard("Easy Flowchart Pathway", Icons.Default.Schema, listOf("Cell Stress / Injury -> Failure of Ion Pumps -> Influx of Sodium & Water -> Cell Swelling -> Inflammatory Cascade Initiation -> Clinical Signs.")))
                
                if (content.indications.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Features", Icons.Default.ListAlt, content.indications))
                }
                
                val contra = content.contraindications.general + content.contraindications.local
                if (contra.isNotEmpty()) {
                    list.add(ConceptCard("Red Flags & Safety Limits", Icons.Default.NewReleases, contra))
                } else {
                    list.add(ConceptCard("Red Flags & Safety Limits", Icons.Default.NewReleases, listOf("Monitor patient vitals closely. Discontinue therapy immediately if acute exacerbation, severe pain, or neurological deficits occur.")))
                }
                
                list.add(ConceptCard("Mnemonics", Icons.Default.Psychology, listOf(getCustomMnemonic(chapterId, name))))
            }
            
            subjectId.equals("microbiology", ignoreCase = true) || chapterId.startsWith("micro") -> {
                list.add(ConceptCard("Microbiological Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Clinical microbiology definition of $name.") }))
                list.add(ConceptCard("Organism Summary", Icons.Default.BugReport, content.principle.ifEmpty { listOf("Classification, morphological features, and gram-stain status of the pathogen.") }))
                list.add(ConceptCard("Transmission & Infection Control", Icons.Default.SwapCalls, listOf("Transmission occurs via direct contact, airborne droplets, or contaminated clinical surfaces. Prevention relies strictly on standard aseptic protocols.")))
                list.add(ConceptCard("Memory Trick", Icons.Default.Psychology, listOf(getCustomMnemonic(chapterId, name))))
                list.add(ConceptCard("Clinical Importance in BPT Wards", Icons.Default.Shield, listOf("Physical therapy equipment (ultrasound gel, parallel bars, pillows) can harbor pathogens. Regular sanitization between patients is crucial to prevent cross-infection.")))
            }
            
            subjectId.equals("pharmacology", ignoreCase = true) || chapterId.startsWith("pharm") -> {
                list.add(ConceptCard("Pharmacological Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Classification and pharmacological profile of $name.") }))
                list.add(ConceptCard("Drug Classification", Icons.Default.Category, listOf("Divided based on chemical structures and receptor actions. Important subclasses include agonists, antagonists, and enzymatic inhibitors.")))
                list.add(ConceptCard("Mechanism of Action", Icons.Default.SettingsAccessibility, content.principle.ifEmpty { listOf("Binds to specific cell receptors to block pain receptors or reduce muscular spasticity.") }))
                list.add(ConceptCard("Memory Trick", Icons.Default.Psychology, listOf(getCustomMnemonic(chapterId, name))))
                
                val sideEffects = content.precautions.ifEmpty { listOf("Dry mouth, dizziness, postural hypotension, gastrointestinal distress.") }
                list.add(ConceptCard("Side Effects & Contraindications", Icons.Default.Warning, sideEffects))
                list.add(ConceptCard("Physiotherapy Considerations", Icons.Default.MedicalServices, listOf("Schedule active exercise sessions around peak drug concentration times for optimal cooperation and minimal pain, and monitor for sudden dizziness during transfers.")))
            }
            
            subjectId.contains("electrotherapy", ignoreCase = true) || chapterId.startsWith("el") || chapterId.startsWith("et") -> {
                list.add(ConceptCard("Modality Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Therapeutic electrophysical definition of $name.") }))
                list.add(ConceptCard("How It Works & Physics Behind It", Icons.Default.ElectricBolt, content.principle.ifEmpty { listOf("Passes electrical current through electrodes, generating ion migration and sensory/motor nerve depolarization.") }))
                
                if (content.clinicalPearls.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Reasoning & Decision Making", Icons.Default.Analytics, content.clinicalPearls))
                } else {
                    list.add(ConceptCard("Clinical Reasoning & Decision Making", Icons.Default.Analytics, listOf("Select parameter based on the phase of healing: high frequency for acute pain relief, low frequency with muscle contraction for chronic circulatory issues.")))
                }
                
                list.add(ConceptCard("Parameter Memory Tips", Icons.Default.Settings, listOf("Remember: Acute pain = High frequency (80-120 Hz) + Short pulse width. Chronic pain = Low frequency (2-5 Hz) + Long pulse width.")))
                
                val contra = content.contraindications.general + content.contraindications.local
                list.add(ConceptCard("Common Mistakes & Safety Warnings", Icons.Default.ReportProblem, contra.ifEmpty { listOf("Placing electrodes directly over metal implants, open wounds, or failing to check skin sensation prior to current application.") }))
            }
            
            subjectId.contains("exercise", ignoreCase = true) || chapterId.startsWith("ex") -> {
                list.add(ConceptCard("Exercise Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Prescription guidelines for $name.") }))
                list.add(ConceptCard("Why This Exercise Works", Icons.Default.FitnessCenter, listOf("Initiates adaptive muscular and neurological changes: increases recruitment of motor units, promotes local muscle hypertrophy, and enhances aerobic capacity of fibers.")))
                
                if (content.principle.isNotEmpty()) {
                    list.add(ConceptCard("Biomechanics & Joint Torques", Icons.Default.RotateRight, content.principle))
                } else {
                    list.add(ConceptCard("Biomechanics & Joint Torques", Icons.Default.RotateRight, listOf("Adjust patient positioning to change the length of the lever arm, thereby grading the resistive torque loaded onto the target muscle.")))
                }
                
                if (content.clinicalPearls.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Application", Icons.Default.AccessibilityNew, content.clinicalPearls))
                } else {
                    list.add(ConceptCard("Clinical Application", Icons.Default.AccessibilityNew, listOf("Prescribe systematically during stages of orthopedic rehabilitation to build muscle endurance, strength, and proprioceptive balance.")))
                }
                
                list.add(ConceptCard("Progression & Regression Guide", Icons.Default.TrendingUp, listOf("To Progress: Increase lever arm, add external resistance, or perform on an unstable surface. To Regress: Perform in gravity-eliminated planes, shorten lever arms, or provide manual assistance.")))
                list.add(ConceptCard("Common Errors & Patient Mistakes", Icons.Default.Cancel, listOf("Incorrect pelvic alignment, compensation using accessory muscles, performing movements too rapidly, or Valsalva maneuver during heavy resistance.")))
            }
            
            else -> {
                // Fallback for general clinical posting or assessment
                list.add(ConceptCard("Core Clinical Definition", Icons.Default.MenuBook, content.definition.ifEmpty { listOf("Clinical definition of $name.") }))
                if (content.principle.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Steps & Protocols", Icons.Default.FormatListNumbered, content.principle))
                }
                if (content.clinicalPearls.isNotEmpty()) {
                    list.add(ConceptCard("Clinical Application", Icons.Default.Analytics, content.clinicalPearls))
                }
            }
        }
        
        // Filter out empty cards automatically
        return list.filter { it.content.isNotEmpty() && it.content.any { line -> line.isNotBlank() } }
    }

    fun getMemoryZone(chapterId: String, chapterName: String): MemoryZone {
        return MemoryZone(
            mnemonic = getCustomMnemonic(chapterId, chapterName),
            vivaPoints = getVivaPoints(chapterId, chapterName),
            frequentlyConfused = getFrequentlyConfused(chapterId, chapterName),
            examPearls = getExamPearls(chapterId, chapterName),
            quickRevision = getQuickRevision(chapterId, chapterName)
        )
    }

    private fun getCustomMnemonic(chapterId: String, name: String): String {
        return when {
            chapterId.startsWith("anat_ch1") || chapterId.contains("carpal", ignoreCase = true) -> {
                "Mnemonic: 'Some Lovers Try Positions That They Can't Handle' -> Scaphoid, Lunate, Triquetrum, Pisiform, Trapezium, Trapezoid, Capitate, Hamate."
            }
            chapterId.startsWith("phys_ch1") || chapterId.contains("potential", ignoreCase = true) -> {
                "Mnemonic: 'P-I-N-O' -> Potassium In, Sodium Out (refers to active transport pump concentrations during resting state)."
            }
            chapterId.startsWith("bio_ch1") || chapterId.contains("lever", ignoreCase = true) -> {
                "Mnemonic: 'F-A-R 1-2-3' -> Force/Fulcrum in Middle (1st Class), Axis/Resistance in Middle (2nd Class), Resistance/Force in Middle (3rd Class)."
            }
            chapterId.startsWith("pharm") || chapterId.contains("drug", ignoreCase = true) -> {
                "Mnemonic: 'A-D-M-E' -> Key pharmacology processes: Absorption, Distribution, Metabolism, Excretion."
            }
            chapterId.startsWith("path") || chapterId.contains("inflammation", ignoreCase = true) -> {
                "Mnemonic: 'P-R-I-C-E' -> Acute tissue injury standard: Protect, Rest, Ice, Compress, Elevate."
            }
            chapterId.startsWith("el") || chapterId.contains("current", ignoreCase = true) -> {
                "Mnemonic: 'A-C-I-D' -> Anode attracts Acid, Cathode attracts Alkaline (rehab electrophysics polarity effects)."
            }
            chapterId.startsWith("ex") || chapterId.contains("exercise", ignoreCase = true) -> {
                "Mnemonic: 'F-I-T-T' -> Standard physical training prescription: Frequency, Intensity, Time, Type."
            }
            else -> {
                "Mnemonic: 'C-L-I-N-I-C' -> Complete assessment, Localize pathology, Identify red flags, Note baseline, Initiate therapy, Check response."
            }
        }
    }

    private fun getVivaPoints(chapterId: String, name: String): List<String> {
        return when {
            chapterId.startsWith("anat") -> listOf(
                "Identify the boundaries and clinical contents of this anatomical space.",
                "Name the nerve supplying this muscle and state its root values.",
                "Explain the exact functional loss resulting from a complete lesion of this nerve."
            )
            chapterId.startsWith("phys") -> listOf(
                "What is the exact value of resting membrane potential, and which ion maintains it?",
                "Define absolute and relative refractory periods in cardiac or skeletal muscle.",
                "Describe the metabolic shifts during high-intensity aerobic exercises."
            )
            chapterId.startsWith("bio") -> listOf(
                "Why is the third-class lever predominant in human limbs? (Range & speed of movement).",
                "Define mechanical advantage and explain when it is less than 1.",
                "Explain joint reaction force and how muscle contraction contributes to it."
            )
            else -> listOf(
                "State the primary clinical contraindications for physical therapy here.",
                "How does this condition impact active range of motion and joint stability?",
                "What safety parameters must be checked before beginning clinical applications?"
            )
        }
    }

    private fun getFrequentlyConfused(chapterId: String, name: String): Pair<String, String> {
        return when {
            chapterId.startsWith("anat") -> {
                "Trapezium vs. Trapezoid" to "Trapezi-UM is under the Th-UMB (connects to 1st metacarpal); Trapezi-OID is next to it (connects to 2nd metacarpal)."
            }
            chapterId.startsWith("phys") -> {
                "Depolarization vs. Repolarization" to "Depolarization is sodium influx (positive going membrane); Repolarization is potassium efflux (returning to negative resting state)."
            }
            chapterId.startsWith("bio") -> {
                "Kinematics vs. Kinetics" to "Kinematics maps movement visually (angles, range) without looking at forces; Kinetics measures the actual muscular torques and gravity loads."
            }
            chapterId.startsWith("path") -> {
                "Apoptosis vs. Necrosis" to "Apoptosis is programmed cell death (no inflammation, single cells); Necrosis is accidental cell death (triggers severe local inflammatory cascade)."
            }
            chapterId.startsWith("pharm") -> {
                "Pharmacokinetics vs. Pharmacodynamics" to "Pharmacokinetics is 'what the body does to the drug' (ADME); Pharmacodynamics is 'what the drug does to the body' (receptors)."
            }
            chapterId.startsWith("el") || chapterId.startsWith("et") -> {
                "Galvanic vs. Faradic Current" to "Galvanic is continuous direct current (long pulse width > 1 ms); Faradic is short-duration interrupted current (< 1 ms) stimulating healthy nerves."
            }
            chapterId.startsWith("ex") -> {
                "Passive Movement vs. Stretching" to "Passive movement stays strictly within current painless range; Stretching applies external force to stretch shortened tissue beyond the path limits."
            }
            else -> {
                "Hypo- vs. Hyper- Response" to "Hypo- response denotes pathologically reduced reflex/excitability; Hyper- response is pathologically heightened (e.g. spasticity)."
            }
        }
    }

    private fun getExamPearls(chapterId: String, name: String): List<String> {
        return listOf(
            "WBUHS Exam Favorite: Draw the structural outline and explain clinical staging.",
            "Always include clinical safety ranges and red flags for maximum marks.",
            "Write the answer with neat flowcharts dividing assessment, physics, and physiology."
        )
    }

    private fun getQuickRevision(chapterId: String, name: String): List<String> {
        return listOf(
            "Understand the anatomical pathways and mechanical advantages.",
            "Maintain aseptic techniques and check equipment safety boundaries.",
            "Tailor exercise progressions dynamically based on patient feedback."
        )
    }
}
