import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'assessment_model.dart';

/// Screen displaying the list of all syllabus-approved assessment templates.
class AssessmentListScreen extends StatefulWidget {
  const AssessmentListScreen({super.key});

  @override
  State<AssessmentListScreen> createState() => _AssessmentListScreenState();
}

class _AssessmentListScreenState extends State<AssessmentListScreen> {
  List<AssessmentTemplate> _templates = [];
  List<AssessmentTemplate> _filteredTemplates = [];
  bool _isLoading = true;
  String _error = '';
  final TextEditingController _searchController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _loadTemplates();
    _searchController.addListener(_filterList);
  }

  @override
  void dispose() {
    _searchController.removeListener(_filterList);
    _searchController.dispose();
    super.dispose();
  }

  Future<void> _loadTemplates() async {
    setState(() {
      _isLoading = true;
      _error = '';
    });

    final hardcodedTemplates = [
      AssessmentTemplate(
        templateId: 'tpl_oa_knee',
        templateName: 'Osteoarthritis Knee',
        steps: [
          AssessmentStep(
            stepId: 'oa_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Gather patient timeline, localized knee pain symptoms, and impact on daily functioning.',
            expectedFindings: [
              'Gradual or insidious onset of deep, dull aching pain in the knee joint during weight bearing',
              'Morning stiffness lasting less than 30 minutes, resolving with activity',
              'Pain temporarily relieved by rest and aggravated by prolonged walking or stair climbing',
              'Difficulty with functional tasks like squatting, kneeling, rising from a low chair'
            ],
            relatedTests: [],
            relatedOutcomes: ['om_010'],
          ),
          AssessmentStep(
            stepId: 'oa_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Assess visual lower limb alignment, deformities, muscular asymmetry, and gait patterns.',
            expectedFindings: [
              'Genu varum (bow-legs) alignment from asymmetric medial cartilage loss',
              'Atrophy/wasting of the Quadriceps muscle, specifically the Vastus Medialis Obliquus (VMO)',
              'Mild-to-moderate localized joint swelling, bony enlargement, or joint capsule thickening',
              'Antalgic gait with shortened stance phase on the affected limb to avoid pain'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'oa_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Check active and passive motion curves of the knee to locate restriction limits.',
            expectedFindings: [
              'Painful active and passive knee flexion restricted to less than 110 degrees',
              'Inability to achieve complete knee extension (extension lag of 5-10 degrees)',
              'Passive knee flexion exhibiting a hard capsular or bony end-feel',
              'Asymmetrical side-to-side end-range pain patterns during extension'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'oa_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Assess muscle power of Knee Extensors (Quadriceps) and Knee Flexors (Hamstrings) (MRC Grade).',
            expectedFindings: [
              'Quadriceps strength reduced to Grade 3+/5 or 4/5; Hamstrings strength Grade 4/5.',
              'Presence of quadriceps extension lag.'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'oa_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Perform specific orthopedic tests to rule out ligamentous or meniscal involvement.',
            expectedFindings: [
              'Patellar Tap test positive (if effusion present); Clarke\'s sign positive (patellofemoral tracking); negative Lachman/McMurray.'
            ],
            relatedTests: ['Patellar Tap', 'Clarke\'s Sign'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'oa_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Administer clinical questionnaires to index pain and physical disability baseline.',
            expectedFindings: [
              'WOMAC Score (Western Ontario and McMaster Universities Index) high baseline score',
              'KOOS (Knee Injury and Osteoarthritis Outcome Score) altered'
            ],
            relatedTests: [],
            relatedOutcomes: ['WOMAC', 'KOOS'],
          ),
          AssessmentStep(
            stepId: 'oa_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Formulate primary impairment list based on subjective and objective clinical manifestations.',
            expectedFindings: [
              'Knee joint pain during weight bearing',
              'Reduced knee active flexion/extension ROM',
              'Quadriceps muscle weakness and extension lag',
              'Impaired stair-climbing and long walking capabilities'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'oa_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Define short-term and long-term physiotherapy rehabilitation goals (SMART criteria).',
            expectedFindings: [
              'Short-term: Reduce knee pain to visual analog scale 4/10 and activate quadriceps in 2 weeks',
              'Long-term: Negotiate 10 steps of stairs independently and walk 500m pain-free in 6 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Magee's Orthopedic Physical Assessment",
            author: "David J. Magee",
            edition: "6th Edition",
            chapterName: "Chapter 12: Knee",
            pageRange: "755-832",
          ),
          ReferenceItem(
            bookName: "Dutton's Orthopaedic Examination Evaluation and Intervention",
            author: "Mark Dutton",
            edition: "5th Edition",
            chapterName: "Chapter 20: The Knee Complex",
            pageRange: "980-1015",
          ),
          ReferenceItem(
            bookName: "Therapeutic Exercise: Foundations and Techniques",
            author: "Carolyn Kisner & Lynn Allen Colby",
            edition: "7th Edition",
            chapterName: "Chapter 21: The Knee",
            pageRange: "785-812",
          ),
          ReferenceItem(
            bookName: "OARSI Guidelines for the Non-surgical Management of Knee Osteoarthritis",
            author: "McAlindon TE et al.",
            edition: "2014 Standard",
            chapterName: "Non-Surgical Osteoarthritis Guidelines",
            pageRange: "363-388",
            type: "guideline",
          ),
          ReferenceItem(
            bookName: "Exercise therapy for osteoarthritis of the knee: a systematic review",
            author: "Fransen M et al.",
            edition: "2015 Cochrane Database",
            chapterName: "Exercise Efficacy Meta-Analysis",
            pageRange: "CD004376",
            type: "research",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "WOMAC (Western Ontario and McMaster Universities Index)",
            originalAuthor: "Bellamy N.",
            publicationYear: "1988",
            purpose: "Evaluate pain, stiffness, and physical functioning in knee and hip osteoarthritis populations.",
          ),
          AssessmentScale(
            scaleName: "KOOS (Knee Injury and Osteoarthritis Outcome Score)",
            originalAuthor: "Roos EM",
            publicationYear: "1998",
            purpose: "Multi-dimensional patient-reported outcome targeting active short and long term knee symptoms.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: true,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_stroke',
        templateName: 'Stroke (MCA Infarct)',
        steps: [
          AssessmentStep(
            stepId: 'stroke_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Review neurological history, onset timeline (acute), risk factors, and sensory-motor deficits.',
            expectedFindings: [
              'Sudden onset of contralateral weakness or numbness in face/arm/leg',
              'History of hypertension, hypercholesterolemia, or atrial fibrillation',
              'Language deficits (expressive or receptive aphasia if dominant hemisphere MCA infarct)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'stroke_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Examine posture of head, limbs, muscle tone asymmetry, facial symmetry, and unilateral neglect.',
            expectedFindings: [
              'Contralateral facial drooping (lower half of face)',
              'Classic hemiplegic posture (flexed upper limb, extended/internally rotated lower limb)',
              'Asymmetric weight distribution in sitting and standing, leaning towards sound side'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'stroke_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Check active and passive range of motion of the hemiplegic upper & lower extremities.',
            expectedFindings: [
              'Active ROM severely restricted with dominant synergic movements',
              'Passive ROM restricted in shoulder external rotation, abduction, wrist extension, and ankle dorsiflexion due to muscle stiffness'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'stroke_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Evaluate voluntary control and muscle activation patterns (Brunnstrom stages / MRC Scale).',
            expectedFindings: [
              'Brunnstrom Recovery Stage 1-3 (early flaccidity leading to progressive spasticity)',
              'MRC strength grade 1-3/5 in primary hemiparetic muscles'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'stroke_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Perform specific neurological tests for tone, reflexes, coordination, and cranial nerves.',
            expectedFindings: [
              'Modified Ashworth Scale (MAS) Grade 1+ or 2 in wrist flexors and ankle plantarflexors',
              'Deep tendon hyperreflexia (Biceps/Patellar hyperreflexia 3+)',
              'Positive Babinski response; impaired unilateral coordination (finger-to-nose)'
            ],
            relatedTests: ['Modified Ashworth Scale', 'Babinski Ref'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'stroke_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Establish baseline scores for motor recovery, balance, and independence in activities of daily living.',
            expectedFindings: [
              'Fugl-Meyer Assessment (FMA) for motor recovery deficit identification',
              'Berg Balance Scale (BBS) identifying high fall risk (<40/56)',
              'Barthel Index for Activities of Daily Living (ADL) dependence'
            ],
            relatedTests: [],
            relatedOutcomes: ['Fugl-Meyer', 'Berg Balance Scale', 'Barthel Index'],
          ),
          AssessmentStep(
            stepId: 'stroke_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Identify neuromuscular and functional limitations based on clinical examination.',
            expectedFindings: [
              'Right/Left sided hemiplegia with hypertonicity in antigravity muscles',
              'Impaired static and dynamic trunk control and balance',
              'Lack of selective voluntary motor control in distal limbs',
              'Loss of functional independence in transfers and ADLs'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'stroke_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Establish rehabilitation targets for motor control, balance recovery, and transfers.',
            expectedFindings: [
              'Short-term: Achieve independent bedside sitting balance and safe transfer to wheelchair in 2 weeks',
              'Long-term: Achieve independent ambulation for 20 meters using an ankle-foot orthosis and cane in 8 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Physical Rehabilitation",
            author: "Susan B. O'Sullivan & Thomas J. Schmitz",
            edition: "6th Edition",
            chapterName: "Chapter 18: Stroke",
            pageRange: "645-705",
          ),
          ReferenceItem(
            bookName: "Neurological Rehabilitation",
            author: "Darcy A. Umphred",
            edition: "6th Edition",
            chapterName: "Chapter 10: Cerebral Vascular Accidents",
            pageRange: "720-775",
          ),
          ReferenceItem(
            bookName: "AHA/ASA Guidelines for Adult Stroke Rehabilitation and Recovery",
            author: "Winstein CJ et al.",
            edition: "2016 Edition",
            chapterName: "Clinical Practice Guidelines for Post-Stroke Recovery",
            pageRange: "e98-e169",
            type: "guideline",
          ),
          ReferenceItem(
            bookName: "Repetitive task training for improving functional ability after stroke",
            author: "French B et al.",
            edition: "2016 Cochrane Systematic Review",
            chapterName: "Functional Task Training Rehabilitation Outcomes",
            pageRange: "CD006036",
            type: "research",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "Berg Balance Scale (BBS)",
            originalAuthor: "Katherine Berg",
            publicationYear: "1989",
            purpose: "To assess static and dynamic balance impairment and evaluate functional fall risk.",
          ),
          AssessmentScale(
            scaleName: "Modified Ashworth Scale (MAS)",
            originalAuthor: "Bohannon R",
            publicationYear: "1987",
            purpose: "To gauge the severity of muscle spasticity and excessive resistance to passive stretch.",
          ),
          AssessmentScale(
            scaleName: "Functional Independence Measure (FIM)",
            originalAuthor: "Granger CV",
            publicationYear: "1986",
            purpose: "Standard assessment of daily living functionality and motor-cognitive autonomy.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: true,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_parkinsons',
        templateName: 'Parkinson Disease',
        steps: [
          AssessmentStep(
            stepId: 'pd_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Obtain clinical history of motor symptoms, onset of tremors, history of falls, and off/on medication cycle.',
            expectedFindings: [
              'Insidious onset of resting tremor in fingers/hand first ("pill-rolling")',
              'Slow progress of movement (bradykinesia) and difficulty changing directions',
              'Frequent near-misses or falls due to loss of balance reflex'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'pd_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Examine resting tremors, facial expression (mask-like), standing posture, and gait pattern.',
            expectedFindings: [
              'Unilateral/Bilateral pill-rolling tremor at rest',
              'Mask-like facial expression (hypomimia) under spontaneous conversation',
              'Flexed posture (stooped head, thoracic kyphosis, hip/knee flexion)',
              'Festinating gait with short shuffling steps, reduced trunk rotation, and decreased arm swing'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'pd_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Evaluate active and passive range of motion of neck, trunk, and all major peripheral joints.',
            expectedFindings: [
              'Markedly reduced trunk extension and axial rotation',
              'Generalized mild restriction in passive shoulder, hip, and ankle joints in terminal ranges'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'pd_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Determine general muscle strength baseline throughout upper and lower extremities.',
            expectedFindings: [
              'Muscle strength often within normal functional limits but appears weak due to bradykinesia and rigidity'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'pd_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Conduct diagnostic neurological tests assessing rigidity, bradykinesia, tremor, and postural instability.',
            expectedFindings: [
              'Lead-pipe or Cogwheel rigidity felt prominently during passive elbow or wrist motion',
              'Positive Pull Test (retropulsion when shoulder pulled backward)',
              'Apparent motor freezing during doorway navigation'
            ],
            relatedTests: ['Pull Test', 'Rigidity Assessment'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'pd_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Measure disease severity, dynamic balance, and functional gait speed.',
            expectedFindings: [
              'Hoehn & Yahr Staging (often Stage II or III)',
              'Unified Parkinson\'s Disease Rating Scale (MDS-UPDRS)',
              'Timed Up and Go (TUG) elevated above 12 seconds'
            ],
            relatedTests: [],
            relatedOutcomes: ['MDS-UPDRS', 'M-Schober', 'TUG'],
          ),
          AssessmentStep(
            stepId: 'pd_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Synthesize the main physical therapy diagnosis and functional deficits.',
            expectedFindings: [
              'Severe bradykinesia and trunk/extremity rigidity',
              'Loss of postural protective reflexes causing frequent posterior falls',
              'Shuffling/Festinating gait with freezing',
              'Decreased chest expansion and vital capacity'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'pd_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Formulate progressive balance, gait, and axial mobility goals.',
            expectedFindings: [
              'Short-term: Increase chest expansion and trunk rotation by 2cm and 10 degrees in 3 weeks',
              'Long-term: Complete the TUG test in under 12 seconds with no freezing episodes in 6 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Physical Rehabilitation",
            author: "Susan B. O'Sullivan & Thomas J. Schmitz",
            edition: "6th Edition",
            chapterName: "Chapter 19: Parkinson's Disease",
            pageRange: "715-760",
          ),
          ReferenceItem(
            bookName: "Neurological Rehabilitation",
            author: "Darcy A. Umphred",
            edition: "6th Edition",
            chapterName: "Chapter 11: Basal Ganglia Disorders",
            pageRange: "780-815",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "MDS-UPDRS (Unified Parkinson's Disease Rating Scale)",
            originalAuthor: "Goetz CG et al.",
            publicationYear: "2008",
            purpose: "Gold standard clinical index evaluating motor and non-motor consequences of Parkinson's Disease.",
          ),
          AssessmentScale(
            scaleName: "Timed Up and Go (TUG)",
            originalAuthor: "Podsiadlo D",
            publicationYear: "1991",
            purpose: "Quick test to evaluate basic mobility, dynamic balance, and fall susceptibility.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: false,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_bells_palsy',
        templateName: 'Bell\'s Palsy',
        steps: [
          AssessmentStep(
            stepId: 'bp_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Document sudden onset of facial weakness, ear pain, taste changes, and eye irritation issues.',
            expectedFindings: [
              'Acute onset (<72 hours) of complete or partial weakness on one side of the face',
              'Retroauricular pain around massoid process preceding the weakness',
              'Dryness of the ipsilateral eye with pain/burning sensation'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'bp_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Inspect symmetry of the face at rest and during active facial expressions.',
            expectedFindings: [
              'Asymmetry of face at rest: drooping mouth corner, flattened nasolabial fold',
              'Inability to close eye fully, exposure of sclera',
              'Flattening of the skin creases on the affected side of forehead'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'bp_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Evaluate excursion of facial muscles during voluntary symmetrical movements.',
            expectedFindings: [
              'Impaired muscle excursion when asked to wrinkle forehead, close eyes, smile, show teeth, and whistle'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'bp_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Grading facial muscle strength using standard facial grading systems (House-Brackmann, May or Daniels & Worthingham).',
            expectedFindings: [
              'Severe weakness or paralysis (Grade Zero, Trace, or Poor) of Frontalis, Orbicularis Oculi, Buccinator, Orbicularis Oris, Platysma'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'bp_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Perform tests assessing neurological intactness of the Cranial Nerve VII (Facial).',
            expectedFindings: [
              'Positive Bell\'s Phenomenon (upward rot of eye on forced closure attempt)',
              'Taste test impairment (hypogeusia) in anterior 2/3 of tongue',
              'Absent/subdued Corneal Reflex'
            ],
            relatedTests: ['Bell\'s Phenomenon', 'Cranial Nerve VII test'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'bp_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Use standard facial nerve functional evaluation scales.',
            expectedFindings: [
              'House-Brackmann Scale score showing moderate to total paralysis (Grade IV-VI)',
              'Sunnybrook Facial Grading System'
            ],
            relatedTests: [],
            relatedOutcomes: ['House-Brackmann', 'Sunnybrook Scale'],
          ),
          AssessmentStep(
            stepId: 'bp_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Outline physical impairments and structural deficits of facial tissues.',
            expectedFindings: [
              'Paralysis/weakness of cranial nerve VII (facial mimic muscles)',
              'Inability to close eyelids, risk of dynamic corneal damage/irritation',
              'Facial asymmetry affecting speech, eating (food pooling), and drinking'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'bp_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Establish clinical milestones focusing on eye protection and motor re-education.',
            expectedFindings: [
              'Short-term: Educate patient on hourly lubricating eye drops and overnight eye patching in 1 day',
              'Long-term: Restore symmetrical facial expressions and complete close of eye in 8 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Daniels and Worthingham's Muscle Testing",
            author: "Helen Hislop",
            edition: "10th Edition",
            chapterName: "Chapter 6: Techniques of Facial Muscle Grading",
            pageRange: "310-348",
          ),
          ReferenceItem(
            bookName: "Physical Rehabilitation",
            author: "Susan B. O'Sullivan",
            edition: "6th Edition",
            chapterName: "Chapter 29: Cranial Nerve Assessment and Pathologies",
            pageRange: "1152-1170",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "House-Brackmann Facial Nerve Grading Scale",
            originalAuthor: "House JW, Brackmann DE",
            publicationYear: "1985",
            purpose: "Standardized system to evaluate the degree of facial nerve injury and analyze motor recovery progress.",
          ),
          AssessmentScale(
            scaleName: "Sunnybrook Facial Grading System",
            originalAuthor: "Ross BG et al.",
            publicationYear: "1996",
            purpose: "To provide a detailed clinical rating of facial symmetry at rest, voluntary movement excursion, and synkinesis.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: false,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_frozen_shoulder',
        templateName: 'Frozen Shoulder',
        steps: [
          AssessmentStep(
            stepId: 'fs_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Investigate patient history of progressive shoulder pain, night pain, and stage of stiffness pattern.',
            expectedFindings: [
              'History of gradual onset, painful phase (Freezing) changing to stiff phase (Frozen)',
              'Inability to lie on the affected shoulder sleeping due to deep gnawing joint pain',
              'Severe limitations in combing hair, tucking in shirt, reaching rear seats'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'fs_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Examine shoulder girdle posture, muscle wasting, and scapulothoracic compensation.',
            expectedFindings: [
              'Affected shoulder typically held in a protected position (extension, internal rotation)',
              'Early compensatory scapular elevation (shoulder shrugging) during minimal active abduction'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'fs_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Measure passive and active glenohumeral movements in cardinal planes.',
            expectedFindings: [
              'Distinct capsular pattern: External Rotation highly restricted, followed closely by Abduction and Internal Rotation',
              'Leathery, firm arthrogenic/capsular end-feel with pain at endpoints'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'fs_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Test isometric strength of rotator cuff muscles and primary shoulder movers.',
            expectedFindings: [
              'Strength of deltoids and rotators 4/5 or limited primarily by pain at target joint angles'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'fs_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Exclude primary rotator cuff tears or shoulder impingement syndromes.',
            expectedFindings: [
              'Negative Empty Can test for true muscle tear; Shoulder Shrug Sign positive',
              'Apley\'s Scratch test highly painful and restricted in both upper and lower patterns'
            ],
            relatedTests: ['Shoulder Shrug Sign', 'Apley Scratch Test'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'fs_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Measure pain severity, upper extremity function and disability indices.',
            expectedFindings: [
              'SPADI (Shoulder Pain and Disability Index) score reflecting major functional deficit',
              'DASH Questionnaire (Disabilities of the Arm, Shoulder, and Hand)'
            ],
            relatedTests: [],
            relatedOutcomes: ['SPADI Score', 'DASH Score'],
          ),
          AssessmentStep(
            stepId: 'fs_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Synthesize capsuloligamentous and muscular impairments.',
            expectedFindings: [
              'Severe night pain interfering with sleep quality',
              'Glenohumeral joint capsule contracture with restricted ROM',
              'Disordered scapulohumeral rhythm during elevation tasks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'fs_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Set practical milestones to resolve pain, restore capsular extensibility and restore biomechanics.',
            expectedFindings: [
              'Short-term: Reduce night pain to virtual score VAS 3/10 through gentle oscillations inside 2 weeks',
              'Long-term: Retrieve 140 degrees abduction and external rotation to 50 degrees for independent ADLs in 8 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Magee's Orthopedic Physical Assessment",
            author: "David J. Magee",
            edition: "6th Edition",
            chapterName: "Chapter 5: Shoulder",
            pageRange: "245-310",
          ),
          ReferenceItem(
            bookName: "Dutton's Orthopaedic Examination Evaluation and Intervention",
            author: "Mark Dutton",
            edition: "5th Edition",
            chapterName: "Chapter 15: The Shoulder Complex",
            pageRange: "420-465",
          ),
          ReferenceItem(
            bookName: "Therapeutic Exercise: Foundations and Techniques",
            author: "Carolyn Kisner & Lynn Allen Colby",
            edition: "7th Edition",
            chapterName: "Chapter 17: The Shoulder and Shoulder Girdle",
            pageRange: "512-540",
          ),
          ReferenceItem(
            bookName: "Shoulder Pain and Mobility Deficits: Adhesive Capsulitis Practice Guidelines",
            author: "Kelley MJ et al.",
            edition: "2013 Kelley CPG",
            chapterName: "Orthopaedic Section APTA Guidelines",
            pageRange: "A1-A31",
            type: "guideline",
          ),
          ReferenceItem(
            bookName: "Passive stretching vs active exercises for adhesive capsulitis_ a randomized trial",
            author: "Griggs SD et al.",
            edition: "2000 JBJS",
            chapterName: "Clinical Trial on Joint Mobilization Effectiveness",
            pageRange: "1351-1358",
            type: "research",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "SPADI (Shoulder Pain and Disability Index)",
            originalAuthor: "Roach KE",
            publicationYear: "1991",
            purpose: "Clinical metric evaluating pain and functional restriction associated with adhesive shoulder joint capsulitis.",
          ),
          AssessmentScale(
            scaleName: "DASH (Disabilities of the Arm, Shoulder and Hand)",
            originalAuthor: "Hudak PL",
            publicationYear: "1996",
            purpose: "General survey to assess upper extremity localized joint performance and physical limitations.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: true,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_cervical_spondylosis',
        templateName: 'Cervical Spondylosis',
        steps: [
          AssessmentStep(
            stepId: 'cs_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Gather clinical history of posterior neck pain, occipital headache, parasthesia in upper extremity.',
            expectedFindings: [
              'Neck discomfort or stiffness aggravated by prolonged static positioning (e.g. computer use)',
              'Radicular pain or pins-and-needles radiating to shoulder, scapular region, or upper arm'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'cs_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Assess head and neck posture, shoulder alignment, muscle spasm, and spinal curvatures.',
            expectedFindings: [
              'Pronounced forward head posture (FHP) with excessive upper-cervical extension',
              'Bilateral rounded shoulders (upper cross syndrome)',
              'Protective spasm in Upper Trapezius, Levator Scapulae, and Scalenes'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'cs_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Evaluate cervical spine range of motion (flexion, extension, lateral flexion, and rotation).',
            expectedFindings: [
              'Restricted and painful cervical extension and lateral flexion/rotation towards the affected segment side'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'cs_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Grade muscle strength of cervical flexors/extensors and crucial upper limb myotomes.',
            expectedFindings: [
              'Weakness of deep neck flexors (Longus colli/capitis)',
              'Myotomal weakness C5 (deltoid), C6 (biceps/wrist extensors) if nerve compression is present'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'cs_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Conduct specific tests for cervical nerve root compression or spinal cord involvement.',
            expectedFindings: [
              'Spurling\'s test (Foraminal Compression) reproduces radiating arm pain/paresthesia (positive)',
              'Cervical distraction test reduces radicular pain successfully'
            ],
            relatedTests: ['Spurling\'s Test', 'Distraction Test'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'cs_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Track subjective functional limitations and neck pain severity indices.',
            expectedFindings: [
              'Neck Disability Index (NDI) baseline score',
              'VAS for localized cervical and radiating arm pain'
            ],
            relatedTests: [],
            relatedOutcomes: ['NDI Score', 'VAS Pain Scale'],
          ),
          AssessmentStep(
            stepId: 'cs_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Outline anatomical, nerve-related, and muscular clinical findings.',
            expectedFindings: [
              'Mechanical neck pain with localized disc/facet joint degeneration',
              'Radiculopathic paresthesia along specific upper limb dermatomes',
              'Muscle length imbalances (tight upper fibers, weak deep neck stabilizers)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'cs_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Formulate physical therapy targets for decompression, posture, and muscular support.',
            expectedFindings: [
              'Short-term: Alleviate segment irritation, spasm, and lower VAS neck score to 3/10 in 2 weeks',
              'Long-term: Restore deep neck flexor strength to 40 seconds endurance and eliminate FHP pattern in 6 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Magee's Orthopedic Physical Assessment",
            author: "David J. Magee",
            edition: "6th Edition",
            chapterName: "Chapter 3: Cervical Spine",
            pageRange: "123-195",
          ),
          ReferenceItem(
            bookName: "Dutton's Orthopaedic Examination Evaluation and Intervention",
            author: "Mark Dutton",
            edition: "5th Edition",
            chapterName: "Chapter 27: The Cervical Spine",
            pageRange: "1180-1232",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "Neck Disability Index (NDI)",
            originalAuthor: "Vernon H",
            publicationYear: "1991",
            purpose: "To assess localized head-neck pain severity and measure disability during essential primary ADLs.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: false,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_lumbar_herniation',
        templateName: 'Lumbar Disc Herniation',
        steps: [
          AssessmentStep(
            stepId: 'ls_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Obtain clinical history of lower back pain radiating down the limb (radiculopathy), sciatic tingling, and aggravating postures.',
            expectedFindings: [
              'Sharp or burning radicular pain radiating below the knee (sciatica) along a specific dermatome (L4, L5, S1)',
              'Pain exacerbated by lumbar flexion, sitting, coughing, or sneezing, and relieved by extension or standing'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'ls_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Examine lumbar lordosis abnormalities, pelvic tilt asymmetry, sciatic trunk list (scoliosis), and gait impairments.',
            expectedFindings: [
              'Flattened lumbar spine curve or lateral spinal shift (gait avoidance list)',
              'Protective trunk muscle spasm and avoidance of weight bearing on the symptomatic side'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'ls_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Test active lumbar spine mobility (flexion, extension, lateral flexion) and note movement blocks.',
            expectedFindings: [
              'Severely restricted active lumbar flexion which reproduces distal radicular symptoms',
              'Extension may be partially limited but often reduces peripheralization of symptoms (centralization phenomenon)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'ls_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Examine lower limb dermatomal myotomes and core muscle stabilizing power.',
            expectedFindings: [
              'Myotomal weakness C/O L4 (tibialis anterior), L5 (extensor hallucis longus), or S1 (peroneus longus) if selective nerve root compression present'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'ls_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Perform dural stretch tests to evaluate sciatica and confirm radicular tract compression.',
            expectedFindings: [
              'Positive Straight Leg Raise (SLR / Lasegue\'s sign) of affected limb at 30-70 degrees with radicular reproduction',
              'Positive Crossed Straight Leg Raise test (highly specific for disc herniation); positive dural bias on neck flexion'
            ],
            relatedTests: ['SLR Test', 'Crossed SLR', 'Femoral Nerve Stretch'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'ls_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Evaluate low back pain disability level and back performance indexes.',
            expectedFindings: [
              'Oswestry Low Back Disability Index (ODI) score reflecting functional baseline',
              'Roland-Morris Disability Questionnaire (RMDQ)'
            ],
            relatedTests: [],
            relatedOutcomes: ['ODI Score', 'Roland-Morris'],
          ),
          AssessmentStep(
            stepId: 'ls_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Formulate primary impairments of the lumbosacral region.',
            expectedFindings: [
              'Subacute or chronic sciatic pain; localized lumbar flexion block',
              'Selective lower-limb muscle weakness; compromised core stabilizer strength',
              'Limitations with sitting, walking, or lifting household items'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'ls_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Formulate SMART milestones focusing on core stability, pain relief, and biomechanics.',
            expectedFindings: [
              'Short-term: Reduce dural signs and lower VAS leg score to 3/10 through McKenzie extension in 2 weeks',
              'Long-term: Independent lifting using safe lumbopelvic dissociation and return to light activity in 8 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Magee's Orthopedic Physical Assessment",
            author: "David J. Magee",
            edition: "6th Edition",
            chapterName: "Chapter 9: Lumbar Spine",
            pageRange: "490-580",
          ),
          ReferenceItem(
            bookName: "Dutton's Orthopaedic Examination Evaluation and Intervention",
            author: "Mark Dutton",
            edition: "5th Edition",
            chapterName: "Chapter 28: The Lumbar Spine",
            pageRange: "1250-1310",
          ),
          ReferenceItem(
            bookName: "Therapeutic Exercise: Foundations and Techniques",
            author: "Carolyn Kisner & Lynn Allen Colby",
            edition: "7th Edition",
            chapterName: "Chapter 16: The Spine: Exercise Interventions",
            pageRange: "440-485",
          ),
          ReferenceItem(
            bookName: "NASS Clinical Guidelines: Diagnosis and Treatment of Lumbar Disc Herniation with Radiculopathy",
            author: "Kreiner DS et al.",
            edition: "2014 Edition",
            chapterName: "Lumbar Disc Herniation and Sciatica Standards",
            pageRange: "1-56",
            type: "guideline",
          ),
          ReferenceItem(
            bookName: "Surgical vs Nonoperative Treatment for Lumbar Disc Herniation_ The SPORT Randomized Trial",
            author: "Weinstein JN et al.",
            edition: "2006 JAMA Trial",
            chapterName: "Multicenter Comparative Outcomes in Disc Herniations",
            pageRange: "2441-2450",
            type: "research",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "Oswestry Disability Index (ODI)",
            originalAuthor: "Fairbank JC",
            publicationYear: "1980",
            purpose: "The classic self-reported index to measure the impact of localized and radiating low back pain/sciatica during primary ADLs.",
          ),
          AssessmentScale(
            scaleName: "Roland-Morris Disability Questionnaire (RMDQ)",
            originalAuthor: "Roland M",
            publicationYear: "1983",
            purpose: "Subjective physical disability survey used for mild-to-moderate low back symptoms.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: true,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_acl_injury',
        templateName: 'ACL Injury',
        steps: [
          AssessmentStep(
            stepId: 'acl_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Document mechanism of injury (non-contact pivoting, deceleration), hearing a \'pop\', and immediate swelling timeline.',
            expectedFindings: [
              'Twisting or hyperextension force to knee with audible sound ("pop")',
              'Rapid, immediate joint swelling occurring within 1-2 hours (hemarthrosis)',
              'History of knee giving-way during pivoting or stair climbs'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'acl_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Look for joint effusion, patellar outline, muscle guarding, and knee flexion posture.',
            expectedFindings: [
              'Gross knee joint effusion (capsular swelling with obliterated suprapatellar pouch)',
              'Antalgic gait or walking with bent knee posture to escape pain'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'acl_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Test knee range of motion, noting guarding from pain and structural lock.',
            expectedFindings: [
              'Restricted flexion and painful extension; flexion blocked beyond 90 degrees due to fluid tension'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'acl_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Grade thigh muscle strength (quadriceps and hamstrings) under safe boundaries.',
            expectedFindings: [
              'Severe inhibition of Quadriceps (arthrogenic muscle inhibition) causing weak extension force',
              'Hamstring protective spasm protecting tibia extension'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'acl_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Check mechanical integrity of the Anterior Cruciate Ligament (crucial orthopedic testing).',
            expectedFindings: [
              'Positive Lachman\'s test (increased anterior tibial translation with mushy end-feel)',
              'Positive Anterior Drawer test at 90 degrees flexion',
              'Pivot Shift test showing rotatory subluxation (limited by acute guarding)'
            ],
            relatedTests: ['Lachman Test', 'Anterior Drawer', 'Pivot Shift'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'acl_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Track knee joint function, sports return capability, and subjective knee performance rating.',
            expectedFindings: [
              'Lysholm Knee Score demonstrating marked structural and activity disability',
              'IKDC (International Knee Documentation Committee) rating'
            ],
            relatedTests: [],
            relatedOutcomes: ['Lysholm Score', 'IKDC Form'],
          ),
          AssessmentStep(
            stepId: 'acl_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Determine diagnostic biomechanical and mechanical instability indicators.',
            expectedFindings: [
              'Instability of knee joint due to rupture of ACL tract',
              'Profound reflex quadriceps atrophy and activation lag',
              'Inability to pivot, jog, or perform athletic drills safely'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'acl_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Synthesize target milestones for pre-hab or post-injury physical therapy.',
            expectedFindings: [
              'Short-term: Eliminate knee effusion, restore full extension and achieve SLR without lag in 3 weeks',
              'Long-term: Achieve 90-100% limb symmetry on functional jump tests and return to pivots in 6 months'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Magee's Orthopedic Physical Assessment",
            author: "David J. Magee",
            edition: "6th Edition",
            chapterName: "Chapter 12: Knee Complex",
            pageRange: "790-820",
          ),
          ReferenceItem(
            bookName: "Dutton's Orthopaedic Examination Evaluation and Intervention",
            author: "Mark Dutton",
            edition: "5th Edition",
            chapterName: "Chapter 20: The Knee Ligaments",
            pageRange: "1005-1030",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "Lysholm Knee Score",
            originalAuthor: "Tegner Y, Lysholm J",
            publicationYear: "1985",
            purpose: "To grade functional knee stability, focusing on pain, swelling, and giving-way symptoms.",
          ),
          AssessmentScale(
            scaleName: "IKDC Individual Knee Documentation Committee Form",
            originalAuthor: "Hefti F et al.",
            publicationYear: "1993",
            purpose: "Validated subjective instrument used to track knee joint therapeutic outcomes and limitations.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: false,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_tkr',
        templateName: 'Total Knee Replacement',
        steps: [
          AssessmentStep(
            stepId: 'tkr_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Review surgical report details, post-op day status, pain levels (at rest/exercise), and thrombosis profile.',
            expectedFindings: [
              'Typical Post-Op Day (POD 1-7); sharp wound pain localized to anterior knee segment',
              'Medical history of severe end-stage knee OA in same joint'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'tkr_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Examine incision line integrity, staples/sutures, local edema, warmth, and limb positioning.',
            expectedFindings: [
              'Anterior surgical knee incision with staples/steri-strips intact; no leakage',
              'Significant local/distal lower limb pitting edema and warmth'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'tkr_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Assess active and passive knee range of motion to secure early joint mobility.',
            expectedFindings: [
              'Limited active knee range of motion (typically 0-60 degrees flexion passively in post-op day 3)',
              'Inability to reach complete straight extension (10-15 degrees lag)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'tkr_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'MMT Assessment',
            description: 'Determine voluntary concentric quad and calf squeeze capability.',
            expectedFindings: [
              'Poor active Quadriceps motor control (inability to lift leg or "squeeze")',
              'Ankle pump and toe flexor movements intact (MRC Grade 4-5/5)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'tkr_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Evaluate safe healing indicators and screen for deep vein thrombosis (DVT).',
            expectedFindings: [
              'Negative Homan\'s sign and calf tenderness check (no deep pain to screen for DVT)',
              'Good distal dorsalis pedis pulses'
            ],
            relatedTests: ['Homan\'s Sign Check', 'Distal Pulse Integrity'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'tkr_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Apply metrics tracking post-operative functional performance.',
            expectedFindings: [
              'Knee Society Score (KSS) or Oxford Knee Score',
              'Timed Up and Go (TUG) or 6-Minute Walk Test (6MWT)'
            ],
            relatedTests: [],
            relatedOutcomes: ['Knee Society Score', 'Oxford Knee Scale'],
          ),
          AssessmentStep(
            stepId: 'tkr_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'List surgical and biomechanical limitations following arthroplasty.',
            expectedFindings: [
              'Post-operative knee pain and localized swelling; restricted active/passive knee flexion and extension lag',
              'Weak/inhibited quadriceps muscle; impaired sit-to-stand and gait safety'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'tkr_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Establish aggressive phase-based goals for knee arthroplasty recovery.',
            expectedFindings: [
              'Short-term: Achieve knee flexion 0-90 degrees, straight leg raise with 0 degree lag on POD 10',
              'Long-term: Independent ambulation with cane or unassisted, stairs ascend/descend in 6-8 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Therapeutic Exercise: Foundations and Techniques",
            author: "Carolyn Kisner & Lynn Allen Colby",
            edition: "7th Edition",
            chapterName: "Chapter 21: Arthroplasty of the Knee Complex",
            pageRange: "800-835",
          ),
          ReferenceItem(
            bookName: "Dutton's Orthopaedic Examination Evaluation and Intervention",
            author: "Mark Dutton",
            edition: "5th Edition",
            chapterName: "Chapter 20: Arthroplasty Rehabilitation Protocols",
            pageRange: "1025-1045",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "Knee Society Score (KSS)",
            originalAuthor: "Insall JN et al.",
            publicationYear: "1989",
            purpose: "To grade outcomes of total knee arthroplasty, taking knee scores and functional capabilities separately.",
          ),
          AssessmentScale(
            scaleName: "Oxford Knee Score (OKS)",
            originalAuthor: "Dawson J et al.",
            publicationYear: "1998",
            purpose: "12-item questionnaire targeted for tracking knee arthroplasty progress and joint pain outcomes.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: false,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
      AssessmentTemplate(
        templateId: 'tpl_spinal_cord_injury',
        templateName: 'Spinal Cord Injury',
        steps: [
          AssessmentStep(
            stepId: 'sci_001',
            stepNumber: 1,
            category: 'subjective',
            title: 'Subjective Assessment',
            description: 'Document mechanism of injury, injury level (traumatic/non-traumatic), sensory-motor loss margins, bladder/bowel deficits, autonomic dysreflexia signs.',
            expectedFindings: [
              'Traumatic fracture line or spinal compression history with immediate sensory-motor loss margin',
              'Bilateral limb weakness or complete sensory deficit radiating below the segment level',
              'History of severe headaches or sweating fluctuations (lesions above T6: autonomic dysreflexia symptoms)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'sci_002',
            stepNumber: 2,
            category: 'observation',
            title: 'Observation',
            description: 'Examine respiratory pattern, chest expansion, decubitus pressure sores over bony prominences, and positional patterns of limbs.',
            expectedFindings: [
              'Impaired chest expansion or diaphragmatic/paradoxical breathing pattern',
              'High risk of redness or decubitus ulcers on sacrum, heels, or dynamic ischial tuberosities',
              'Paralyzed lower extremities presenting with progressive subluxation or external rotation posturing'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'sci_003',
            stepNumber: 3,
            category: 'rom',
            title: 'ROM Assessment',
            description: 'Evaluate active and passive range of motion of upper and lower limbs. Target key hamstring and shoulder stretch limits.',
            expectedFindings: [
              'Restricted hamstring lengh (passive SLR limit; needs stretching to 110 degrees for long sitting without trunk tilt)',
              'Ankle contractures (gastrocnemius tightness with dorsiflexion locked below 0 degrees)'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'sci_004',
            stepNumber: 4,
            category: 'mmt',
            title: 'Motor & Sensory (ASIA Scale)',
            description: 'Conduct key myotome strength and key sensory dermatome mapping to classify injury level.',
            expectedFindings: [
              'Key upper limb myotomes (C5-T1) or key lower limb myotomes (L2-S1) showing selective neurologic deficit',
              'Dermatomal map sensory block (light touch and pin-prick) establishing tactile boundaries'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'sci_005',
            stepNumber: 5,
            category: 'special_test',
            title: 'Special Tests',
            description: 'Evaluate muscle tone spasticity, ankle clonus, hyperreflexia, and autonomic dysreflexia indicators.',
            expectedFindings: [
              'High muscle tone (Modified Ashworth Scale Grade 2 or 3 in ankle extensors)',
              'Positive hyperactive deep tendon reflexes (3+ or 4+) and sustained ankle clonus',
              'Cardiovascular tracking: acute blood pressure peaks and bradycardia during bowel impactions (autonomic dysreflexia)'
            ],
            relatedTests: ['ASIA Scale Motor Test', 'Clonus Test', 'MAS Spasticity'],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'sci_006',
            stepNumber: 6,
            category: 'outcome_measures',
            title: 'Outcome Measures',
            description: 'Apply specialized clinical indices for functional independence and disability.',
            expectedFindings: [
              'SCIM Scale (Spinal Cord Independence Measure III) high functional deficit at baseline',
              'FIM Scale (Functional Independence Measure) and standard WISCI II walking index baseline'
            ],
            relatedTests: [],
            relatedOutcomes: ['SCIM III Scale', 'FIM Scale', 'WISCI II'],
          ),
          AssessmentStep(
            stepId: 'sci_007',
            stepNumber: 7,
            category: 'problem_list',
            title: 'Problem List',
            description: 'Structure spinal cord injury impairments and daily activity functional deficits.',
            expectedFindings: [
              'SCI paraplegia/tetraplegia; altered muscle tone (spasticity/flaccidity)',
              'Autonomic bowel/bladder dysfunction; loss of dynamic sitting balance',
              'High pressure ulcer susceptibility over sacral and ischial regions'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
          AssessmentStep(
            stepId: 'sci_008',
            stepNumber: 8,
            category: 'goal_setting',
            title: 'Goal Setting',
            description: 'Formulate SMART physiotherapy targets focusing on chest hygiene, transfer capacity, and balance.',
            expectedFindings: [
              'Short-term: Perform independent hamstring stretching and achieve dynamic bedside long-sitting balance in 3 weeks',
              'Long-term: Independent sliding board transfer from bed to wheelchair and wheel 100 meters on flat terrain in 8 weeks'
            ],
            relatedTests: [],
            relatedOutcomes: [],
          ),
        ],
        references: [
          ReferenceItem(
            bookName: "Physical Rehabilitation",
            author: "Susan B. O'Sullivan & Thomas J. Schmitz",
            edition: "6th Edition",
            chapterName: "Chapter 20: Spinal Cord Injury",
            pageRange: "885-940",
          ),
          ReferenceItem(
            bookName: "Neurological Rehabilitation",
            author: "Darcy A. Umphred",
            edition: "6th Edition",
            chapterName: "Chapter 12: Spinal Cord Trauma",
            pageRange: "430-492",
          ),
          ReferenceItem(
            bookName: "Preservation of Upper Limb Function Following Spinal Cord Injury Guidelines",
            author: "Consortium for Spinal Cord Medicine",
            edition: "2005 Guidelines",
            chapterName: "Clinical Practice Guidelines for Upper Extremity Care in SCI",
            pageRange: "1-45",
            type: "guideline",
          ),
          ReferenceItem(
            bookName: "Activity-dependent plastic reorganization of neurological circuits after spinal cord injury",
            author: "Dietz V et al.",
            edition: "2015 Edition",
            chapterName: "Neurological Locomotor Rehabilitation Studies",
            pageRange: "111-134",
            type: "research",
          ),
        ],
        scales: [
          AssessmentScale(
            scaleName: "ASIA Impairment Scale (AIS - ISNCSCI)",
            originalAuthor: "American Spinal Injury Association",
            publicationYear: "1982",
            purpose: "Standardized clinical procedure to determine neurologic status and classify sensory-motor level in spinal injuries.",
          ),
          AssessmentScale(
            scaleName: "Spinal Cord Independence Measure (SCIM III)",
            originalAuthor: "Catz A et al.",
            publicationYear: "1997",
            purpose: "Comprehensive disability instrument designed specifically to measure functional performance and ADL autonomy with spinal cord damage.",
          ),
        ],
        evidenceMetadata: EvidenceMetadata(
          textbookBased: true,
          clinicalGuidelineBased: true,
          researchSupported: true,
          wbuhsBptAligned: true,
        ),
      ),
    ];

    setState(() {
      _templates = hardcodedTemplates;
      _filteredTemplates = hardcodedTemplates;
      _isLoading = false;
    });
  }

  void _filterList() {
    final query = _searchController.text.toLowerCase();
    setState(() {
      _filteredTemplates = _templates.where((t) {
        final matchesName = t.templateName.toLowerCase().contains(query);
        final matchesSteps = t.steps.any((s) => s.title.toLowerCase().contains(query) || s.description.toLowerCase().contains(query));
        return matchesName || matchesSteps;
      }).toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Clinical Assessment Templates'),
        actions: [
          IconButton(
            icon: const Icon(Icons.sync_rounded),
            onPressed: _loadTemplates,
            tooltip: 'Reload database',
          ),
        ],
      ),
      body: Column(
        children: [
          _buildInfoBanner(theme),
          _buildSearchBox(theme),
          Expanded(child: _buildMainContent(theme)),
        ],
      ),
    );
  }

  Widget _buildInfoBanner(ThemeData theme) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(16),
      margin: const EdgeInsets.fromLTRB(16, 8, 16, 8),
      decoration: BoxDecoration(
        color: theme.colorScheme.primaryContainer.withOpacity(0.4),
        borderRadius: BorderRadius.circular(16),
        border: Border.all(color: theme.colorScheme.primary.withOpacity(0.15)),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(Icons.cast_for_education_rounded, color: theme.colorScheme.primary, size: 24),
              const SizedBox(width: 8),
              Text(
                'Clinical OSCE Guides',
                style: theme.textTheme.titleMedium?.copyWith(
                  fontWeight: FontWeight.bold,
                  color: theme.colorScheme.onPrimaryContainer,
                ),
              ),
            ],
          ),
          const SizedBox(height: 6),
          Text(
            'Checklists based on major physiotherapy curricula to learn the sequential examination of musculoskeletal and neurological cases.',
            style: theme.textTheme.bodyMedium?.copyWith(
              color: theme.colorScheme.onSurfaceVariant,
              height: 1.3,
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildSearchBox(ThemeData theme) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: TextField(
        controller: _searchController,
        decoration: InputDecoration(
          hintText: 'Search templates, cases, or symptoms...',
          prefixIcon: const Icon(Icons.search_rounded),
          suffixIcon: _searchController.text.isNotEmpty
              ? IconButton(
                  icon: const Icon(Icons.clear_rounded),
                  onPressed: () => _searchController.clear(),
                )
              : null,
          filled: true,
          fillColor: theme.colorScheme.surfaceVariant.withOpacity(0.3),
          contentPadding: const EdgeInsets.symmetric(vertical: 0, horizontal: 16),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(12),
            borderSide: BorderSide.none,
          ),
        ),
      ),
    );
  }

  Widget _buildMainContent(ThemeData theme) {
    if (_isLoading) {
      return const Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CircularProgressIndicator(),
            SizedBox(height: 16),
            Text('Parsing clinical assessment guides...'),
          ],
        ),
      );
    }

    if (_error.isNotEmpty) {
      return Center(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(Icons.warning_amber_rounded, size: 48, color: theme.colorScheme.error),
              const SizedBox(height: 16),
              Text(
                'Asset Sync Fail',
                style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 8),
              Text(
                _error,
                textAlign: TextAlign.center,
                style: TextStyle(color: theme.colorScheme.onErrorContainer, fontSize: 13),
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: _loadTemplates,
                child: const Text('Try Again'),
              ),
            ],
          ),
        ),
      );
    }

    if (_filteredTemplates.isEmpty) {
      return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.search_off_rounded, size: 48, color: theme.colorScheme.outline),
            const SizedBox(height: 16),
            Text('No templates matched your search.', style: TextStyle(color: theme.colorScheme.outline)),
          ],
        ),
      );
    }

    return ListView.builder(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      itemCount: _filteredTemplates.length,
      itemBuilder: (context, index) {
        final t = _filteredTemplates[index];
        final isOaKnee = t.templateName.contains('Osteoarthritis') || t.templateId == 'tpl_oa_knee';

        return Card(
          margin: const EdgeInsets.only(bottom: 12),
          elevation: isOaKnee ? 2 : 0,
          color: isOaKnee ? theme.colorScheme.surface : theme.colorScheme.surfaceVariant.withOpacity(0.15),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(16),
            side: BorderSide(
              color: isOaKnee ? theme.colorScheme.primary.withOpacity(0.4) : theme.colorScheme.outlineVariant.withOpacity(0.5),
              width: isOaKnee ? 1.5 : 1.0,
            ),
          ),
          child: ListTile(
            contentPadding: const EdgeInsets.all(16),
            title: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                if (isOaKnee)
                  Container(
                    padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 2),
                    margin: const EdgeInsets.only(bottom: 6),
                    decoration: BoxDecoration(
                      color: theme.colorScheme.primary,
                      borderRadius: BorderRadius.circular(4),
                    ),
                    child: const Text(
                      'CORE CURRICULUM',
                      style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold, fontSize: 8),
                    ),
                  ),
                Text(
                  t.templateName,
                  style: theme.textTheme.titleMedium?.copyWith(
                    fontWeight: FontWeight.bold,
                    color: isOaKnee ? theme.colorScheme.primary : theme.colorScheme.onSurface,
                  ),
                ),
              ],
            ),
            subtitle: Padding(
              padding: const EdgeInsets.only(top: 8.0),
              child: Row(
                children: [
                  Icon(Icons.playlist_add_check_rounded, size: 16, color: theme.colorScheme.secondary),
                  const SizedBox(width: 4),
                  Text(
                    '${t.steps.length} practical sequential steps',
                    style: theme.textTheme.bodyMedium?.copyWith(fontSize: 13, color: theme.colorScheme.onSurfaceVariant),
                  ),
                ],
              ),
            ),
            trailing: Icon(
              Icons.chevron_right_rounded,
              color: isOaKnee ? theme.colorScheme.primary : theme.colorScheme.outline,
            ),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => AssessmentDetailScreen(template: t),
                ),
              );
            },
          ),
        );
      },
    );
  }
}

/// Detail screen showing the checklist steps with progress bar, collapsible evidence, and ExpansionTiles.
class AssessmentDetailScreen extends StatefulWidget {
  final AssessmentTemplate template;

  const AssessmentDetailScreen({
    super.key,
    required this.template,
  });

  @override
  State<AssessmentDetailScreen> createState() => _AssessmentDetailScreenState();
}

class _AssessmentDetailScreenState extends State<AssessmentDetailScreen> {
  // Track status of steps using a local map of stepId -> wasChecked
  final Map<String, bool> _completedSteps = {};

  @override
  void initState() {
    super.initState();
    // Initialize checked states to false
    for (var step in widget.template.steps) {
      _completedSteps[step.stepId] = false;
    }
  }

  double get _progress {
    if (widget.template.steps.isEmpty) return 0.0;
    final int done = _completedSteps.values.where((test) => test).length;
    return done / widget.template.steps.length;
  }

  int get _doneCount => _completedSteps.values.where((test) => test).length;

  void _onStepChecked(String stepId, bool? val) {
    setState(() {
      _completedSteps[stepId] = val ?? false;
    });
  }

  void _clearChecks() {
    setState(() {
      _completedSteps.updateAll((key, val) => false);
    });
  }

  Color _getCategoryColor(String c, ThemeData theme) {
    switch (c.toLowerCase()) {
      case 'subjective':
        return Colors.teal;
      case 'observation':
        return Colors.indigo;
      case 'palpation':
        return Colors.deepOrange;
      case 'rom':
        return Colors.green;
      case 'mmt':
        return Colors.purple;
      case 'special_test':
        return Colors.blue;
      case 'outcome_measures':
        return Colors.pink;
      case 'problem_list':
        return Colors.deepOrange;
      case 'goal_setting':
        return Colors.cyan;
      default:
        return theme.colorScheme.secondary;
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final totalSteps = widget.template.steps.length;
    final progressValue = _progress;

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.template.templateName),
        actions: [
          IconButton(
            icon: const Icon(Icons.restart_alt_rounded),
            tooltip: 'Clear progress',
            onPressed: () {
              showDialog(
                context: context,
                builder: (context) => AlertDialog(
                  title: const Text('Reset checklist?'),
                  content: const Text('Do you want to reset all completed checkboxes for this template?'),
                  actions: [
                    TextButton(
                      onPressed: () => Navigator.pop(context),
                      child: const Text('Cancel'),
                    ),
                    TextButton(
                      onPressed: () {
                        _clearChecks();
                        Navigator.pop(context);
                      },
                      child: const Text('Reset'),
                    ),
                  ],
                ),
              );
            },
          ),
        ],
      ),
      body: Column(
        children: [
          // Linear progress bar at top of screen
          _buildProgressIndicator(theme, progressValue, _doneCount, totalSteps),
          Expanded(
            child: widget.template.steps.isEmpty
                ? const Center(child: Text('No steps found.'))
                : ListView.builder(
                    padding: const EdgeInsets.all(16),
                    itemCount: widget.template.steps.length + 1,
                    itemBuilder: (context, index) {
                      if (index == widget.template.steps.length) {
                        return Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const SizedBox(height: 16),
                            Padding(
                              padding: const EdgeInsets.only(bottom: 8.0, left: 4.0),
                              child: Text(
                                'Academic References & Evidence',
                                style: theme.textTheme.titleMedium?.copyWith(
                                  fontWeight: FontWeight.bold,
                                  color: theme.colorScheme.onSurface,
                                ),
                              ),
                            ),
                            EvidenceReferencesCard(template: widget.template),
                          ],
                        );
                      }
                      final step = widget.template.steps[index];
                      return _buildStepCard(step, theme);
                    },
                  ),
          ),
        ],
      ),
    );
  }

  Widget _buildProgressIndicator(ThemeData theme, double progress, int done, int total) {
    return Container(
      color: theme.colorScheme.surface,
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                'Assessment Progress',
                style: theme.textTheme.titleSmall?.copyWith(fontWeight: FontWeight.bold),
              ),
              Text(
                '$done / $total completed (${(progress * 100).toInt()}%)',
                style: TextStyle(
                  color: theme.colorScheme.primary,
                  fontWeight: FontWeight.bold,
                  fontSize: 13,
                ),
              ),
            ],
          ),
          const SizedBox(height: 8),
          ClipRRect(
            borderRadius: BorderRadius.circular(8),
            child: LinearProgressIndicator(
              value: progress,
              minHeight: 10,
              backgroundColor: theme.colorScheme.primaryContainer.withOpacity(0.3),
              valueColor: AlwaysStoppedAnimation<Color>(theme.colorScheme.primary),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildStepCard(AssessmentStep step, ThemeData theme) {
    final isDone = _completedSteps[step.stepId] ?? false;
    final categoryColor = _getCategoryColor(step.category, theme);

    return Container(
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        color: isDone ? theme.colorScheme.primaryContainer.withOpacity(0.05) : theme.colorScheme.surface,
        borderRadius: BorderRadius.circular(12),
        border: Border.all(
          color: isDone ? theme.colorScheme.primary.withOpacity(0.4) : theme.colorScheme.outlineVariant.withOpacity(0.5),
          width: isDone ? 1.5 : 1.0,
        ),
      ),
      clipBehavior: Clip.antiAlias,
      child: ExpansionTile(
        key: PageStorageKey<String>(step.stepId),
        leading: SizedBox(
          width: 24,
          height: 24,
          child: Checkbox(
            value: isDone,
            activeColor: theme.colorScheme.primary,
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(4)),
            onChanged: (val) => _onStepChecked(step.stepId, val),
          ),
        ),
        title: Row(
          children: [
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 2),
              decoration: BoxDecoration(
                color: categoryColor.withOpacity(0.1),
                borderRadius: BorderRadius.circular(4),
                border: Border.all(color: categoryColor.withOpacity(0.2)),
              ),
              child: Text(
                step.category.toUpperCase(),
                style: TextStyle(
                  color: categoryColor,
                  fontWeight: FontWeight.bold,
                  fontSize: 9,
                ),
              ),
            ),
            const SizedBox(width: 8),
            Expanded(
              child: Text(
                'Step ${step.stepNumber}: ${step.title}',
                style: theme.textTheme.titleSmall?.copyWith(
                  fontWeight: FontWeight.bold,
                  decoration: isDone ? TextDecoration.lineThrough : null,
                  color: isDone ? theme.colorScheme.onSurface.withOpacity(0.6) : theme.colorScheme.onSurface,
                ),
              ),
            ),
          ],
        ),
        subtitle: Padding(
          padding: const EdgeInsets.only(top: 4.0),
          child: Text(
            step.description,
            maxLines: 1,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(fontSize: 12, color: theme.colorScheme.onSurfaceVariant),
          ),
        ),
        childrenPadding: const EdgeInsets.all(16),
        expandedCrossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Divider(),
          const SizedBox(height: 8),
          Text(
            'Objective Instructions:',
            style: theme.textTheme.labelMedium?.copyWith(
              fontWeight: FontWeight.bold,
              color: theme.colorScheme.secondary,
            ),
          ),
          const SizedBox(height: 4),
          Text(
            step.description,
            style: theme.textTheme.bodyMedium?.copyWith(height: 1.35),
          ),
          const SizedBox(height: 16),
          Container(
            width: double.infinity,
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
              color: theme.colorScheme.surfaceVariant.withOpacity(0.3),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(Icons.fact_check_rounded, size: 16, color: theme.colorScheme.primary),
                    const SizedBox(width: 6),
                    Text(
                      'Expected Findings (BPT Standard):',
                      style: theme.textTheme.labelMedium?.copyWith(
                        fontWeight: FontWeight.bold,
                        color: theme.colorScheme.primary,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                if (step.expectedFindings.isEmpty)
                  const Text('No expected results specified.', style: TextStyle(fontSize: 12, fontStyle: FontStyle.italic))
                else
                  ...step.expectedFindings.map((f) => Padding(
                        padding: const EdgeInsets.only(bottom: 6),
                        child: Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const Text('• ', style: TextStyle(fontWeight: FontWeight.bold)),
                            Expanded(child: Text(f, style: const TextStyle(fontSize: 12, height: 1.3))),
                          ],
                        ),
                      )),
              ],
            ),
          ),
          if (step.relatedTests.isNotEmpty || step.relatedOutcomes.isNotEmpty) ...[
            const SizedBox(height: 12),
            Wrap(
              spacing: 8,
              runSpacing: 4,
              children: [
                ...step.relatedTests.map((test) => Chip(
                      label: Text('Test: $test'),
                      labelStyle: const TextStyle(fontSize: 10),
                      padding: EdgeInsets.zero,
                    )),
                ...step.relatedOutcomes.map((outcome) => Chip(
                      label: Text('Outcome: $outcome'),
                      labelStyle: const TextStyle(fontSize: 10),
                      padding: EdgeInsets.zero,
                      backgroundColor: theme.colorScheme.primaryContainer.withOpacity(0.5),
                    )),
              ],
            ),
          ]
        ],
      ),
    );
  }
}

/// Collapsible card displaying dynamic academic references, quality badges and scales.
class EvidenceReferencesCard extends StatefulWidget {
  final AssessmentTemplate template;

  const EvidenceReferencesCard({super.key, required this.template});

  @override
  State<EvidenceReferencesCard> createState() => _EvidenceReferencesCardState();
}

class _EvidenceReferencesCardState extends State<EvidenceReferencesCard> {
  String _searchQuery = '';
  String _selectedFilter = 'All'; // 'All', 'Textbooks', 'Outcome Measures', 'Guidelines', 'Research'

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final references = widget.template.references;
    final scales = widget.template.scales;
    final metadata = widget.template.evidenceMetadata;

    // Filter Logic
    final filteredReferences = references.where((ref) {
      final matchesSearch = ref.bookName.toLowerCase().contains(_searchQuery.toLowerCase()) ||
          ref.author.toLowerCase().contains(_searchQuery.toLowerCase()) ||
          ref.chapterName.toLowerCase().contains(_searchQuery.toLowerCase());
      if (!matchesSearch) return false;

      if (_selectedFilter == 'All') return true;
      if (_selectedFilter == 'Textbooks') return ref.type == 'textbook';
      if (_selectedFilter == 'Guidelines') return ref.type == 'guideline';
      if (_selectedFilter == 'Research') return ref.type == 'research';
      return false; // Handled separately
    }).toList();

    final filteredScales = scales.where((sc) {
      final matchesSearch = sc.scaleName.toLowerCase().contains(_searchQuery.toLowerCase()) ||
          sc.originalAuthor.toLowerCase().contains(_searchQuery.toLowerCase()) ||
          sc.purpose.toLowerCase().contains(_searchQuery.toLowerCase());
      if (!matchesSearch) return false;

      if (_selectedFilter == 'All') return true;
      if (_selectedFilter == 'Outcome Measures') return true;
      return false;
    }).toList();

    final totalSourcesCount = references.length + scales.length;

    return Card(
      margin: const EdgeInsets.only(bottom: 16),
      elevation: 0,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(16),
        side: BorderSide(color: theme.colorScheme.outlineVariant, width: 1),
      ),
      clipBehavior: Clip.antiAlias,
      child: Theme(
        data: theme.copyWith(dividerColor: Colors.transparent),
        child: ExpansionTile(
          initiallyExpanded: false,
          leading: Icon(
            Icons.menu_book_rounded,
            color: theme.colorScheme.primary,
          ),
          title: Row(
            children: [
              Text(
                'Evidence & References',
                style: theme.textTheme.titleMedium?.copyWith(
                  fontWeight: FontWeight.bold,
                  color: theme.colorScheme.primary,
                ),
              ),
              const SizedBox(width: 8),
              Container(
                padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 2),
                decoration: BoxDecoration(
                  color: theme.colorScheme.primaryContainer,
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Text(
                  '$totalSourcesCount Sources',
                  style: theme.textTheme.labelSmall?.copyWith(
                    color: theme.colorScheme.onPrimaryContainer,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ],
          ),
          subtitle: const Text(
            'Academic citations and standardized scales (BPT Curriculum)',
            style: TextStyle(fontSize: 12),
          ),
          children: [
            Padding(
              padding: const EdgeInsets.fromLTRB(16, 0, 16, 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Divider(height: 1),
                  const SizedBox(height: 12),
                  // Evidence Level Badges - Requirement 7
                  Wrap(
                    spacing: 8,
                    runSpacing: 8,
                    children: [
                      if (metadata.textbookBased)
                        _buildBadge('🟢 Textbook Based', theme),
                      if (metadata.clinicalGuidelineBased)
                        _buildBadge('🟢 Clinical Guideline Based', theme),
                      if (metadata.researchSupported)
                        _buildBadge('🟢 Research Supported', theme),
                      if (metadata.wbuhsBptAligned)
                        _buildBadge('🟢 WBUHS BPT Aligned', theme),
                    ],
                  ),
                  const SizedBox(height: 12),
                  // Search bar - Requirement 4
                  TextField(
                    onChanged: (val) {
                      setState(() {
                        _searchQuery = val;
                      });
                    },
                    decoration: InputDecoration(
                      hintText: 'Search citations, books, authors...',
                      prefixIcon: const Icon(Icons.search_rounded, size: 20),
                      isDense: true,
                      contentPadding: const EdgeInsets.all(10),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(12),
                        borderSide: BorderSide(color: theme.colorScheme.outlineVariant),
                      ),
                    ),
                  ),
                  const SizedBox(height: 12),
                  // Filter Chips - Requirement 5
                  SingleChildScrollView(
                    scrollDirection: Axis.horizontal,
                    child: Row(
                      children: [
                        _buildFilterChip('All', theme),
                        const SizedBox(width: 6),
                        _buildFilterChip('Textbooks', theme),
                        const SizedBox(width: 6),
                        _buildFilterChip('Outcome Measures', theme),
                        const SizedBox(width: 6),
                        _buildFilterChip('Guidelines', theme),
                        const SizedBox(width: 6),
                        _buildFilterChip('Research', theme),
                      ],
                    ),
                  ),
                  const SizedBox(height: 16),
                  // Rendered Lists
                  if (filteredReferences.isEmpty && filteredScales.isEmpty)
                    Center(
                      child: Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Text(
                          'No references matching filters or search.',
                          style: theme.textTheme.bodyMedium?.copyWith(
                            color: theme.colorScheme.onSurfaceVariant,
                            fontStyle: FontStyle.italic,
                          ),
                        ),
                      ),
                    )
                  else ...[
                    if (_selectedFilter == 'All' || _selectedFilter == 'Textbooks' || _selectedFilter == 'Guidelines' || _selectedFilter == 'Research') ...[
                      ...filteredReferences.map((ref) => _buildReferenceTile(ref, theme)),
                    ],
                    if (_selectedFilter == 'All' || _selectedFilter == 'Outcome Measures') ...[
                      ...filteredScales.map((scale) => _buildScaleTile(scale, theme)),
                    ],
                  ],
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildBadge(String label, ThemeData theme) {
    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 4),
      decoration: BoxDecoration(
        color: theme.colorScheme.surfaceVariant.withOpacity(0.5),
        borderRadius: BorderRadius.circular(20),
        border: Border.all(color: theme.colorScheme.outlineVariant),
      ),
      child: Text(
        label,
        style: theme.textTheme.labelMedium?.copyWith(
          fontWeight: FontWeight.bold,
          color: theme.colorScheme.onSurfaceVariant,
        ),
      ),
    );
  }

  Widget _buildFilterChip(String filter, ThemeData theme) {
    final isSelected = _selectedFilter == filter;
    return ChoiceChip(
      label: Text(filter),
      selected: isSelected,
      onSelected: (selected) {
        if (selected) {
          setState(() {
            _selectedFilter = filter;
          });
        }
      },
    );
  }

  Widget _buildReferenceTile(ReferenceItem ref, ThemeData theme) {
    final typeIcon = ref.type == 'guideline'
        ? Icons.gavel_rounded
        : ref.type == 'research'
            ? Icons.science_rounded
            : Icons.book_rounded;

    return Card(
      margin: const EdgeInsets.only(bottom: 10),
      color: theme.colorScheme.surfaceVariant.withOpacity(0.15),
      child: Theme(
        data: theme.copyWith(dividerColor: Colors.transparent),
        child: ExpansionTile(
          dense: true,
          leading: Icon(typeIcon, size: 20, color: theme.colorScheme.primary),
          title: Text(
            ref.bookName,
            style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 13),
          ),
          subtitle: Text(
            '${ref.author} (Ed. ${ref.edition})',
            style: const TextStyle(fontSize: 11),
          ),
          childrenPadding: const EdgeInsets.all(12),
          expandedCrossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('• Chapter: ${ref.chapterName}', style: const TextStyle(fontSize: 12)),
            if (ref.pageRange != null && ref.pageRange!.isNotEmpty)
              Text('• Pages: ${ref.pageRange}', style: const TextStyle(fontSize: 12)),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                OutlinedButton.icon(
                  onPressed: () {
                    final citation = '"${ref.chapterName}" in ${ref.bookName} (${ref.edition} Ed.) by ${ref.author}.' + (ref.pageRange != null ? ' pp. ${ref.pageRange}' : '');
                    Clipboard.setData(ClipboardData(text: citation));
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(content: Text('Citation copied: $citation')),
                    );
                  },
                  icon: const Icon(Icons.copy_rounded, size: 14),
                  label: const Text('Copy Citation', style: TextStyle(fontSize: 11)),
                  style: OutlinedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildScaleTile(AssessmentScale scale, ThemeData theme) {
    return Card(
      margin: const EdgeInsets.only(bottom: 10),
      color: theme.colorScheme.primaryContainer.withOpacity(0.08),
      child: Theme(
        data: theme.copyWith(dividerColor: Colors.transparent),
        child: ExpansionTile(
          dense: true,
          leading: Icon(Icons.assessment_rounded, size: 20, color: theme.colorScheme.secondary),
          title: Text(
            scale.scaleName,
            style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 13),
          ),
          subtitle: Text(
            'Author: ${scale.originalAuthor} (${scale.publicationYear})',
            style: const TextStyle(fontSize: 11),
          ),
          childrenPadding: const EdgeInsets.all(12),
          expandedCrossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Clinical Purpose:', style: TextStyle(fontWeight: FontWeight.bold, fontSize: 11)),
            const SizedBox(height: 4),
            Text(scale.purpose, style: const TextStyle(fontSize: 12)),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                OutlinedButton.icon(
                  onPressed: () {
                    final citation = '${scale.scaleName} (${scale.originalAuthor}, ${scale.publicationYear}). Purpose: ${scale.purpose}';
                    Clipboard.setData(ClipboardData(text: citation));
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(content: Text('Citation copied: $citation')),
                    );
                  },
                  icon: const Icon(Icons.copy_rounded, size: 14),
                  label: const Text('Copy Citation', style: TextStyle(fontSize: 11)),
                  style: OutlinedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
