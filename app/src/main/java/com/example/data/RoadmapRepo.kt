package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class GeneralAssessmentStep(
    val title: String,
    val purpose: String,
    val whatToAssess: String,
    val whyItIsImportant: String,
    val commonFindings: String
)

@Serializable
data class RoadmapSpecialTest(
    val name: String,
    val purpose: String,
    val positiveFinding: String,
    val interpretation: String
)

@Serializable
data class AssessmentRoadmap(
    val id: String,
    val diagnosisName: String,
    val steps: List<AssessmentStep>,
    val specialTests: List<RoadmapSpecialTest>,
    val outcomeMeasures: List<OutcomeMeasure>,
    val impairments: List<String>,
    val activityLimitations: List<String>,
    val participationRestrictions: List<String>,
    val shortTermGoals: List<String>,
    val longTermGoals: List<String>,
    val treatmentLink: String
)

@Serializable
data class AssessmentStep(
    val title: String,
    val whatToAssess: String,
    val whyToAssess: String,
    val howToAssess: String,
    val expectedFindings: String,
    val clinicalSignificance: String
)

@Serializable
data class OutcomeMeasure(
    val name: String,
    val purpose: String,
    val interpretation: String
)

object RoadmapRepo {
    val generalAssessment = listOf(
        GeneralAssessmentStep(
            title = "1. Patient Information",
            purpose = "To establish patient identity and demographics.",
            whatToAssess = "Name, age, gender, occupation, address.",
            whyItIsImportant = "Age and occupation inform risk factors and functional requirements. Vital for documentation.",
            commonFindings = "Provides baseline context for all further assessment."
        ),
        GeneralAssessmentStep(
            title = "2. Chief Complaint",
            purpose = "To understand the primary reason the patient is seeking physiotherapy.",
            whatToAssess = "The exact words the patient uses to describe their main problem.",
            whyItIsImportant = "Directs the focus of the assessment and ensures patient-centered goals.",
            commonFindings = "e.g., 'Pain in right knee for 3 days', 'Difficulty walking since the stroke'."
        ),
        GeneralAssessmentStep(
            title = "3. History Taking (HOPI)",
            purpose = "To determine the progression and behavior of symptoms.",
            whatToAssess = "Onset, duration, mode of injury, aggravating factors, relieving factors, 24-hour behavior.",
            whyItIsImportant = "Helps identify the tissue at fault and the stage of healing (acute vs chronic).",
            commonFindings = "Pain worse with activity and better with rest (mechanical); morning stiffness (inflammatory)."
        ),
        GeneralAssessmentStep(
            title = "4. Past Medical History",
            purpose = "To identify pre-existing conditions affecting recovery.",
            whatToAssess = "Previous surgeries, trauma, chronic illnesses (HTN, Diabetes).",
            whyItIsImportant = "Altered healing rates, contraindications for certain therapies.",
            commonFindings = "Diabetes (delayed wound healing, neuropathy), Hypertension (limits high-intensity exercise)."
        ),
        GeneralAssessmentStep(
            title = "5. Drug History",
            purpose = "To know current medications and potential side effects.",
            whatToAssess = "Analgesics, NSAIDs, anticoagulants, antihypertensives.",
            whyItIsImportant = "Medications can mask pain, alter heart rate during exercise, or increase bleeding risk.",
            commonFindings = "Patient takes Beta-blockers (blunted HR response to exercise)."
        ),
        GeneralAssessmentStep(
            title = "6. Social History",
            purpose = "To understand the patient's support system and home environment.",
            whatToAssess = "Living situation, stairs at home, family support.",
            whyItIsImportant = "Crucial for discharge planning and setting realistic functional goals.",
            commonFindings = "Lives alone on 2nd floor with no elevator (immediate goal: safe stair negotiation)."
        ),
        GeneralAssessmentStep(
            title = "7. ADL Assessment",
            purpose = "To evaluate independence in daily self-care tasks.",
            whatToAssess = "Ability to dress, bathe, eat, and transfer.",
            whyItIsImportant = "Highlights functional deficits that need immediate therapeutic intervention.",
            commonFindings = "Needs moderate assistance for sit-to-stand; unable to hook bra behind back."
        ),
        GeneralAssessmentStep(
            title = "8. Vital Signs",
            purpose = "To ensure physiological stability prior to assessment/treatment.",
            whatToAssess = "Heart rate, blood pressure, respiratory rate, SpO2, temperature.",
            whyItIsImportant = "Provides a safety baseline. Abnormal vitals are a red flag to stop therapy.",
            commonFindings = "Elevated HR > 100 bpm, SpO2 < 90%."
        ),
        GeneralAssessmentStep(
            title = "9. General Observation",
            purpose = "To gain an initial global impression of the patient.",
            whatToAssess = "Posture, body build, distress levels, facial expressions, mobility aids.",
            whyItIsImportant = "Often reveals compensatory strategies before a formal test is done.",
            commonFindings = "Patient leans to the left, frequently winces, uses a standard walker."
        ),
        GeneralAssessmentStep(
            title = "10. Local Observation",
            purpose = "To inspect the specific area of complaint.",
            whatToAssess = "Swelling, redness, muscle wasting, skin trophic changes, deformities.",
            whyItIsImportant = "Identifies localized inflammation, atrophy, or structural abnormalities.",
            commonFindings = "Visible quadriceps wasting, erythema over the joint."
        ),
        GeneralAssessmentStep(
            title = "11. Palpation",
            purpose = "To assess tissue state via touch.",
            whatToAssess = "Temperature, tenderness, swelling type (pitting vs non-pitting), muscle tone/spasm.",
            whyItIsImportant = "Confirms visual findings and pinpoints exact pain generators.",
            commonFindings = "Grade 3 tenderness over lateral epicondyle; warm to touch."
        ),
        GeneralAssessmentStep(
            title = "12. Sensory Examination",
            purpose = "To evaluate the integrity of the somatosensory system.",
            whatToAssess = "Dermatomal testing for light touch, pinprick, vibration, proprioception.",
            whyItIsImportant = "Distinguishes between peripheral nerve lesions and nerve root involvement.",
            commonFindings = "Loss of sensation over the C6 dermatome; impaired joint position sense."
        ),
        GeneralAssessmentStep(
            title = "13. Reflex Examination",
            purpose = "To test the spinal reflex arc and upper motor neuron pathways.",
            whatToAssess = "Deep Tendon Reflexes (DTRs) and pathological reflexes (Babinski, Clonus).",
            whyItIsImportant = "Helps classify UMN vs LMN lesions.",
            commonFindings = "Hyperreflexia (UMN lesion), hyporeflexia (LMN lesion)."
        ),
        GeneralAssessmentStep(
            title = "14. ROM Assessment",
            purpose = "To determine the quantity and quality of joint movement.",
            whatToAssess = "Active ROM, Passive ROM, and End-feel using a goniometer.",
            whyItIsImportant = "Identifies capsular vs non-capsular patterns and limits of mobility.",
            commonFindings = "Painful arc during shoulder abduction; hard capsular end-feel."
        ),
        GeneralAssessmentStep(
            title = "15. MMT Assessment",
            purpose = "To measure manual muscle strength.",
            whatToAssess = "Specific muscle testing using the Medical Research Council (MRC) 0-5 grading scale.",
            whyItIsImportant = "Guides strengthening protocols and detects neurological weakness patterns.",
            commonFindings = "Grade 3/5 quadriceps strength, unable to hold against resistance."
        ),
        GeneralAssessmentStep(
            title = "16. Balance Assessment",
            purpose = "To evaluate postural control and fall risk.",
            whatToAssess = "Static balance (Romberg) and dynamic balance in sitting/standing.",
            whyItIsImportant = "Crucial for predicting falls and determining the need for assistive devices.",
            commonFindings = "Positive Romberg test; requires hand support to maintain standing."
        ),
        GeneralAssessmentStep(
            title = "17. Gait Assessment",
            purpose = "To analyze walking mechanics safely.",
            whatToAssess = "Stance phase, swing phase, base of support, cadence, step length, deviations.",
            whyItIsImportant = "Identifies biomechanical faults leading to pain or inefficiency.",
            commonFindings = "Trendelenburg gait, foot drop, antalgic (painful) limp."
        ),
        GeneralAssessmentStep(
            title = "18. Functional Evaluation",
            purpose = "To test whole-body movements directly related to daily tasks.",
            whatToAssess = "Sit-to-stand, reaching, squatting, rolling in bed.",
            whyItIsImportant = "Connects impairments (e.g., weak quads) to disabilities (e.g., cannot stand up).",
            commonFindings = "Takes multi-attempts to rise from a low chair."
        ),
        GeneralAssessmentStep(
            title = "19. Investigations",
            purpose = "To review objective medical imaging and laboratory results.",
            whatToAssess = "X-rays, MRI, CT scans, blood tests (ESR, CRP).",
            whyItIsImportant = "Correlates clinical findings with structural damage and rules out red flags.",
            commonFindings = "X-ray shows complete loss of medial joint space; elevated ESR."
        ),
        GeneralAssessmentStep(
            title = "20. Special Tests",
            purpose = "To provoke or relieve symptoms to confirm a diagnosis.",
            whatToAssess = "Tests specific to joints/conditions (e.g., Lachman, Neer's, SLR).",
            whyItIsImportant = "High specificity/sensitivity tests seal the clinical hypothesis.",
            commonFindings = "Positive Lachman test indicating ACL laxity."
        ),
        GeneralAssessmentStep(
            title = "21. Differential Diagnosis",
            purpose = "To rule out conditions presenting with similar symptoms.",
            whatToAssess = "Comparisons of signs/symptoms with other possible pathologies.",
            whyItIsImportant = "Prevents misdiagnosis and improper treatment application.",
            commonFindings = "Ruling out cervical radiculopathy in a patient with shoulder pain."
        ),
        GeneralAssessmentStep(
            title = "22. Problem List",
            purpose = "To structure deficits in order of clinical priority.",
            whatToAssess = "List of impairments, activity limitations, and participation restrictions.",
            whyItIsImportant = "Provides a clear roadmap for forming goals and treatment plans.",
            commonFindings = "1. Decreased ROM. 2. Severe pain. 3. Unable to walk to work."
        ),
        GeneralAssessmentStep(
            title = "23. Goals",
            purpose = "To set measurable objectives for the therapy period.",
            whatToAssess = "Short-Term Goals (STG) for acute changes and Long-Term Goals (LTG) for functional outcomes.",
            whyItIsImportant = "Keeps therapy focused, motivates patients, and justifies treatment to insurance.",
            commonFindings = "STG: Reduce pain to 3/10 in 1 week. LTG: Walk 1 km pain-free in 4 weeks."
        ),
        GeneralAssessmentStep(
            title = "24. Treatment Planning",
            purpose = "To select interventions addressing the problem list.",
            whatToAssess = "Modalities, exercise therapy, manual therapy, and education.",
            whyItIsImportant = "The final culmination uniting assessment findings with patient recovery.",
            commonFindings = "Plan includes Ultrasound, Quadriceps strengthening, and gait training."
        )
    )

    val roadmaps: List<AssessmentRoadmap>
        get() = com.example.data.DataLoader.rawDiagnoses.map { data ->
            AssessmentRoadmap(
                id = "rm_" + data.id.substring(2),
                diagnosisName = data.diagnosisName,
                steps = listOf(
                    AssessmentStep(
                        title = "History Taking",
                        whatToAssess = data.historyTaking.joinToString { it.what },
                        whyToAssess = data.historyTaking.joinToString { it.why },
                        howToAssess = "Patient interview",
                        expectedFindings = data.historyTaking.joinToString { it.expected },
                        clinicalSignificance = data.historyTaking.joinToString { it.sig }
                    ),
                    AssessmentStep(
                        title = "Observation",
                        whatToAssess = data.observation.joinToString { it.what },
                        whyToAssess = data.observation.joinToString { it.why },
                        howToAssess = "Visual inspection",
                        expectedFindings = data.observation.joinToString { it.expected },
                        clinicalSignificance = data.observation.joinToString { it.sig }
                    ),
                    AssessmentStep(
                        title = "Palpation",
                        whatToAssess = data.palpation.joinToString { it.what },
                        whyToAssess = data.palpation.joinToString { it.why },
                        howToAssess = "Manual touch",
                        expectedFindings = data.palpation.joinToString { it.expected },
                        clinicalSignificance = data.palpation.joinToString { it.sig }
                    ),
                    AssessmentStep(
                        title = "ROM",
                        whatToAssess = data.romAssessment.joints,
                        whyToAssess = "Assess extensibility",
                        howToAssess = "Goniometry",
                        expectedFindings = "AROM: ${data.romAssessment.arom}, PROM: ${data.romAssessment.prom}",
                        clinicalSignificance = data.romAssessment.significance
                    ),
                    AssessmentStep(
                        title = "MMT",
                        whatToAssess = data.mmtAssessment.muscles,
                        whyToAssess = "Assess functional strength",
                        howToAssess = "Isotonic testing",
                        expectedFindings = data.mmtAssessment.pattern,
                        clinicalSignificance = data.mmtAssessment.interpretation
                    )
                ),
                specialTests = data.specialTests.map { test -> 
                    RoadmapSpecialTest(
                        name = test.name,
                        purpose = test.purpose,
                        positiveFinding = test.positiveFinding,
                        interpretation = test.interpretation
                    )
                },
                outcomeMeasures = data.outcomeMeasures.map { om -> 
                    OutcomeMeasure(
                        name = om.name,
                        purpose = om.purpose,
                        interpretation = om.interpretation
                    )
                },
                impairments = data.problemList,
                activityLimitations = data.functionalAssessment,
                participationRestrictions = listOf("Reduced community participation"),
                shortTermGoals = data.goals.shortTerm,
                longTermGoals = data.goals.longTerm,
                treatmentLink = data.clinicalReasoning
            )
        }
}
