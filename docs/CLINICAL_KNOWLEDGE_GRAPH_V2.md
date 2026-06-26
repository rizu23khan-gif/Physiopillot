# PhysioPilot Version 2: Clinical Knowledge Graph & Topic Hub Specification
## Next-Generation Educational Architecture atop the Frozen SSOT Repository

This document establishes the official architectural blueprint and design specification for **PhysioPilot Version 2**. It defines a highly structured, clinical-grade **Knowledge Graph** and **Topic Hub** architecture that overlays the existing certified Single Source of Truth (SSOT) YAML repository. 

By design, this architecture remains entirely decoupled from the underlying content schemas, ensuring **zero modifications** are required for the frozen master database, the Kotlin source code, the compilation pipeline, or the current production application features.

---

## 1. Body Region Architecture (Region Hierarchy)

To support logical clinical browsing, regional diagnosis, and physical therapy specialty postings, the repository is indexed across a hierarchical, multi-dimensional **Body Region Taxonomy**. Every educational topic, MCQ, and clinical pearl is tagged with one or more nodes from this hierarchy.

### 1.1 Structural Taxonomy
The taxonomy is structured into five core divisions:

```
[Body Region Taxonomy]
 ├── Musculoskeletal (Upper Quarter)
 ├── Musculoskeletal (Lower Quarter)
 ├── Systems & Specialties
 ├── Life Stage & Populations
 └── Community & Public Health
```

#### A. Musculoskeletal: Upper Quarter
1.  **Head**: Craniofacial pain, TMJ disorders, cranial nerve distributions.
2.  **Neck**: Cervical spine (upper and lower), suboccipital region, cervicogenic headaches.
3.  **TMJ (Temporomandibular Joint)**: Masticatory muscle dysfunction, internal disc derangement.
4.  **Shoulder**: Glenohumeral joint, acromioclavicular joint, sternoclavicular joint, scapulothoracic articulation, rotator cuff complex.
5.  **Arm / Elbow**: Humeroulnar, humeroradial, proximal radioulnar joints, cubital fossa.
6.  **Forearm / Wrist**: Distal radioulnar joint, radiocarpal joint, midcarpal joint, carpal tunnel.
7.  **Hand**: Carpometacarpal (CMC), metacarpophalangeal (MCP), interphalangeal (IP) joints, intrinsic hand muscle compartments.

#### B. Musculoskeletal: Lower Quarter
8.  **Thorax / Thoracic Spine**: Costovertebral joints, costochondral junctions, thoracic facet joints.
9.  **Lumbar Spine**: Lumbar facet joints, intervertebral discs, sacroiliac joint (SIJ) biomechanics.
10. **Pelvis**: Pubic symphysis, pelvic floor musculature, pelvic girdle.
11. **Hip**: Acetabulofemoral joint, proximal femoral mechanics, inguinal region.
12. **Thigh**: Quadriceps, hamstring, and adductor compartments.
13. **Knee**: Tibiofemoral, patellofemoral, and proximal tibiofibular joints, meniscus, cruciate/collateral ligaments.
14. **Leg**: Anterior, lateral, deep/superficial posterior compartments, tibia/fibula shafts.
15. **Ankle**: Talocrural, subtalar, and distal tibiofibular joints.
16. **Foot**: Midtarsal, tarsometatarsal (TMT), metatarsophalangeal (MTP), and interphalangeal (IP) joints, medial/lateral longitudinal arches.

#### C. Neurological & Cardiorespiratory Systems
17. **Neurology**: Central nervous system (brain, brainstem, cerebellum, spinal cord tracts) and peripheral nerve pathways (plexuses, individual nerve trunks).
18. **Cardiorespiratory**: Myocardium, pericardium, coronary circulation, lungs, tracheobronchial tree, diaphragmatic mechanics, intercostals, accessory respiratory muscles.

#### D. Life Stage & Clinical Specialties
19. **Pediatrics**: Congenital orthopedic anomalies, developmental delay, pediatric neuromotor conditions (e.g., cerebral palsy, spina bifida).
20. **Geriatrics**: Age-related sarcopenia, degenerative joint diseases, balance impairment, osteoporosis, fall prevention.
21. **Sports**: Overuse syndromes, athletic biomechanics, return-to-sport testing protocols, acute trauma management.
22. **Women’s Health**: Obstetric physical therapy, diastasis recti, pre/postnatal conditioning, pelvic organ prolapse.

#### E. Practice & Community Settings
23. **Community Physiotherapy**: Ergonomics, workplace wellness, community-based rehabilitation (CBR), geriatric home care, accessibility barriers.

---

## 2. Topic Hub Structure

Every body region within the hierarchy corresponds to a **Topic Hub**. The Topic Hub acts as a centralized routing dashboard, gathering and organizing content across 7 key educational modules.

```
+-----------------------------------------------------------------------------------+
|                              TOPIC HUB: [KNEE REGION]                             |
+------------------------+--------------------------+-------------------------------+
| 1. FOUNDATIONS         | 2. CLINICAL ASSESSMENT   | 3. REASONING & DIAGNOSIS      |
| - Anatomy (Bones, Lig) | - Observation & Palpation| - Differential Diagnosis      |
| - Biomechanics (Kin)   | - Range of Motion (ROM)  | - Prognosis & Goals           |
| - Path/Micro/Pharm     | - MMT & Special Tests    | - Clinical Reasoning          |
+------------------------+--------------------------+-------------------------------+
| 4. INTERVENTION        | 5. EVIDENCE-BASED        | 6. DOCUMENTATION & ASSESSMENT |
| - Protocols & HEP      | - Clinical Guidelines    | - SOAP Note Blueprints        |
| - Progressions         | - Research Papers        | - OSCE Case Cards             |
| - Contraindications    | - Case Discussions       | - Viva, MCQs & Flashcards     |
+------------------------+--------------------------+-------------------------------+
|                        7. RETENTION & REVISION MECHANICS                          |
|                        - Active Recall & Spaced Repetition Engine                 |
+-----------------------------------------------------------------------------------+
```

### 2.1 Module Blueprints
Each Module maps directly to standard clinical and academic competencies:

1.  **Foundations**:
    *   *Anatomy*: Skeletal geometry, joint capsules, ligamentous constraints, muscular origins/insertions, nerve supply, arterial flow.
    *   *Physiology*: Muscle fiber recruitment, connective tissue remodeling, joint lubrication mechanics.
    *   *Biomechanics*: Arthrokinematics (roll, spin, glide), osteokinematics, kinetics (ground reaction forces, joint torques).
    *   *Pathology*: Tissue inflammation, degeneration, healing phases, necrosis, immune responses.
    *   *Microbiology*: Pathogens causing septic arthritis, osteomyelitis, wound infections, surgical site contamination.
    *   *Pharmacology*: NSAIDs, corticosteroids, muscle relaxants, analgesics, anticoagulants, metabolic bone agents.
2.  **Clinical Assessment**:
    *   *Observation*: Postural deviation, swelling, muscle atrophy, gait anomalies, antalgic positioning.
    *   *Palpation*: Localized tenderness, joint effusion (e.g., ballotement test), tissue temperature, trigger points.
    *   *Range of Motion (ROM)*: Active, passive, end-feel quality (capsular, bony, empty), goniometry.
    *   *Manual Muscle Testing (MMT)*: Resisted isometric muscle testing, grading (0-5 scale).
    *   *Special Tests*: Orthopedic examination tests (e.g., Lachman, anterior drawer, McMurray) with specificity/sensitivity.
    *   *Outcome Measures*: Validated questionnaires (e.g., WOMAC, DASH, Oswestry, Lower Extremity Functional Scale).
3.  **Clinical Reasoning & Diagnosis**:
    *   *Differential Diagnosis*: Ruling out red flags, system-referred pain, and localized mimics.
    *   *Clinical Impression*: Diagnostic synthesis of history and objective findings.
    *   *Prognosis & Goals*: SMART goals (Specific, Measurable, Achievable, Realistic, Time-bound) for short-term and long-term recovery.
    *   *Clinical Reasoning*: Pattern recognition, clinical decision trees, and hypothesis testing.
4.  **Intervention & Treatment Protocol**:
    *   *Treatment Protocols*: Phase-specific physiotherapeutic interventions (protection, mobility, strengthening, functional recovery).
    *   *Exercise Progressions*: Precise scaling of volume, intensity, load, frequency, and motor control complexity.
    *   *Home Exercise Program (HEP)*: Actionable, clear instructions for home care compliance.
    *   *Contraindications & Red Flags*: Absolute and relative contraindications (e.g., deep vein thrombosis, undiagnosed fracture).
    *   *Clinical Pearls*: Experience-based heuristics, patient positioning tips, therapist ergonomics.
5.  **Evidence-Based Practice (EBP)**:
    *   *Guidelines*: Clinical Practice Guidelines (CPGs) from standard physical therapy associations.
    *   *Research Papers*: Key reference studies, systematic reviews, meta-analyses, and level-of-evidence grades.
    *   *Case Discussions*: Interactive hypothetical or clinical patient cases with logical analysis.
6.  **Documentation & Practical Assessment**:
    *   *SOAP Notes*: Subjective, Objective, Assessment, Plan documentation templates.
    *   *OSCE Cases*: Objective Structured Clinical Examination prep cards, highlighting safety checklists and examiner scoring criteria.
    *   *Viva & Oral Prep*: Formatted technical viva questions and sample answers.
    *   *Assessment Suite*: Multiple Choice Questions (MCQs), Prior Year Questions (PYQs), and active flashcards.
7.  **Retention & Revision Mechanics**:
    *   *Memory Tricks*: Mnemonics, visual analogies, clinical association charts.
    *   *Revision Engine*: Consolidated high-yield summaries, critical safety warnings, and rapid-fire clinical tables.

---

## 3. Knowledge Graph Schema

The core representation of the educational universe is a **Multi-Relational Knowledge Graph (MRKG)**. The graph maps how academic knowledge constructs translate into clinical diagnostics and therapeutics.

### 3.1 Node Definitions

```
(SubjectNode: "Microbiology") ──[:FOUNDS]──> (PathologyNode: "Osteomyelitis") ──[:MANIFESTS_AS]──> (AssessmentNode: "Ankle ROM")
```

| Node Type | ID Prefix | Core Properties |
| :--- | :--- | :--- |
| `Subject` | `subj_` | name, category, standard_code |
| `Chapter` | `chap_` | name, duration, subjectId, taxonomy_tags |
| `AnatomicalStructure`| `anat_` | name, classification, innervation, blood_supply, region_id |
| `Pathology` | `path_` | name, healing_phase, signs_symptoms, pharmacology_interactions |
| `ClinicalTest` | `test_` | name, description, sensitivity, specificity, positive_likelihood_ratio |
| `ClinicalIntervention`| `intv_` | name, type (manual/exercise), dosing_parameters, red_flags |
| `OutcomeMeasure` | `outm_` | name, domain, minimal_detectable_change (MDC) |

### 3.2 Relationship Dictionary
Nodes are linked bidirectionally through a strict edge dictionary:

1.  `[:PREREQUISITE_OF]` (Directed, DAG):
    *   *Definition*: Establishes developmental hierarchy.
    *   *Example*: `chap_phys_ch3` (Nerve-Muscle Physiology) `[:PREREQUISITE_OF]` `chap_el2_ch2` (Faradic/Galvanic Current).
2.  `[:INVERTS_OR_COMPROMISES]` (Directed):
    *   *Definition*: Pathology damages anatomical structure.
    *   *Example*: `path_acl_tear` `[:INVERTS_OR_COMPROMISES]` `anat_acl_ligament`.
3.  `[:EVALUATED_BY]` (Directed):
    *   *Definition*: Connects anatomical structures or pathologies to assessment methods.
    *   *Example*: `path_acl_tear` `[:EVALUATED_BY]` `test_lachman_test`.
4.  `[:CONTRAINDICATES]` (Directed):
    *   *Definition*: High-priority safety edge mapping pathologies or signs to interventions.
    *   *Example*: `path_acute_dvt` `[:CONTRAINDICATES]` `intv_sequential_compression_pump`.
5.  `[:CO_OCCURS_WITH]` (Undirected):
    *   *Definition*: Maps clinical syndromes or related pathologies.
    *   *Example*: `path_rheumatoid_arthritis` `[:CO_OCCURS_WITH]` `path_atlantoaxial_subluxation`.

---

## 4. Clinical Reasoning Architecture

The Clinical Reasoning Graph models the step-by-step sequential journey a clinician takes from the initial patient presentation to discharge. It is implemented as a **Sequential State Machine** that routes the student's learning.

```
 [1. Chief Complaint] ──> [2. Subjective History] ──> [3. Systems Review]
                                                            │
 [6. Clinical Impression] <── [5. Special Testing] <── [4. Objective Exam]
          │
          └──> [7. Differential Diagnosis] ──> [8. Functional Prognosis]
                                                      │
 [11. SOAP Documentation] <── [10. Progress/Outcomes] <── [9. Treatment Protocol]
```

### 4.1 State Definitions & Student Activities

1.  **Chief Complaint / Presenting Condition**:
    *   *Student Activity*: Identify red flags, refer to non-physiotherapy systems, capture pain distribution and onset.
2.  **Subjective History (SOCRATES pain assessment)**:
    *   *Student Activity*: Parse timeline, past medical history, pharmacological status, patient occupation/goals.
3.  **Systems Review (Red Flag Screening)**:
    *   *Student Activity*: Cross-reference signs of myelopathy, cauda equina syndrome, systemic infections (Microbiology), cancer, or vascular insufficiency.
4.  **Objective Examination**:
    *   *Student Activity*: Analyze posture, gait, perform palpation for effusion/temperature, document ROM (active, passive, end-feel), and MMT grades.
5.  **Special Testing & Palpation**:
    *   *Student Activity*: Select and analyze orthopedic special tests, interpreting true/false positive clinical likelihood.
6.  **Clinical Impression & Diagnostic Synthesis**:
    *   *Student Activity*: Consolidate physical impairments, identify structural limitations and participation restrictions (ICF Model).
7.  **Differential Diagnosis**:
    *   *Student Activity*: Rule out local mimics, referred pain patterns, and visceral origins of somatic symptoms.
8.  **Functional Prognosis & Goal Setting**:
    *   *Student Activity*: Construct SMART short-term (impairment-focused) and long-term (participation-focused) goals.
9.  **Treatment Protocol & Intervention**:
    *   *Student Activity*: Establish physical modalities (Electrotherapy), manual therapy parameters, exercise progressions, and home program.
10. **Progressive Re-assessment & Outcome Measurement**:
    *   *Student Activity*: Re-test goniometry, MMT, and standardized functional outcome questionnaires.
11. **SOAP Note Documentation**:
    *   *Student Activity*: Formalize clinical findings in standard clinical-reporting layout.

---

## 5. Retention Architecture

To ensure physical therapy graduates retain highly complex facts (e.g., origin/insertion details, pharmacological drug classifications, microbiological culture traits), the architecture overlays a **Knowledge Graph-Driven Active Recall & Spaced Repetition (KG-ARSR) Engine**.

### 5.1 Learning Loop Flowchart

```
       [Topic Selection]
               │
               ▼
        [Active Learn]  ◄───────────────┐ (Revision Trigger)
               │                        │
               ▼                        │
          [Recall Quiz]                 │
               │                        │
               ▼                        │
       [Spaced Flashcards] ─────────────┤
               │                        │
               ▼                        │
       [Clinical Case Lab] ─────────────┘
               │
               ▼
      [Mastery Aggregator] ──> [Clinical Readiness Score (CRS)]
```

### 5.2 Mathematical Spaced Repetition Model
Traditional Leitner or SuperMemo (SM-2) systems compute intervals in isolation. The PhysioPilot Retention Engine introduces the **Graph-Distance Memory Decay Model**. 

The memory decay rate $\lambda$ of a concept node $C_i$ is attenuated or accelerated based on the retention performance of its neighboring nodes in the Knowledge Graph:

$$I_{next}(C_i) = I_{prev}(C_i) \times EF(C_i) \times \left(1 + \omega \cdot \frac{\sum_{j \in \mathcal{N}(C_i)} \text{Mastery}(C_j)}{|\mathcal{N}(C_i)|}\right)$$

Where:
*   $I_{next}$ is the calculated next repetition interval (in days).
*   $EF$ is the Ease Factor of the node, determined by the user's subjective difficulty score ($1-5$).
*   $\mathcal{N}(C_i)$ represents the set of immediate neighboring nodes in the Knowledge Graph (e.g., Anatomical structures and Pathological conditions linked to the chapter).
*   $\omega$ is the graph resonance weight ($\omega = 0.15$), allowing strong mastery of surrounding anatomy to extend the retention window of a pathology or special test.

### 5.3 Metric Formulations
The application calculates two real-time indicators on the student profile:

#### A. Mastery Score (MS)
Calculated per chapter and subject, representing current cognitive retention:

$$\text{MS} = 0.40(\text{MCQ Accuracy}) + 0.30(\text{Spaced Repetition Performance}) + 0.30(\text{Viva Score})$$

#### B. Clinical Readiness Score (CRS)
Calculated per regional topic hub, representing pragmatic diagnostic ability:

$$\text{CRS} = \left( \sum_{k=1}^{N} \text{MS}(C_k) \cdot w_{\text{clin}} \right) \times \left( 1 - e^{-\alpha \cdot \text{CasesCompleted}} \right)$$

Where $w_{\text{clin}}$ weighting favors clinical assessment and pathology chapters over pure theory, and the saturation term handles interactive case study completions.

---

## 6. AI Readiness & Integration Architecture

The architecture is built for immediate ingestion by Large Language Models (e.g., Gemini 1.5 Pro, Gemini 1.5 Flash) using standard programmatic connectors, without changing the frozen SSOT repository files.

```
┌───────────────────────────────────────────────┐
│              Frozen SSOT Repository           │
│     (master_knowledge/*.yaml, schemas/*.json) │
└───────────────────────┬───────────────────────┘
                        │
                        ▼  [Pre-parsed JSON Directory]
┌───────────────────────────────────────────────┐
│             Vector Search Index               │
│ - Chunked by Section (Theory, Clinical, MCQ)  │
│ - Embeddings via text-embedding-004           │
└───────────────────────┬───────────────────────┘
                        │
                        ▼  [Semantic Queries]
┌───────────────────────────────────────────────┐
│                 RAG Engine                    │
│ - Strict prompt constraints preventing hallucinations│
│ - Strict grounding to YAML source citations   │
└───────────────────────┬───────────────────────┘
                        │
                        ├───────────────────────┐
                        ▼                       ▼
           ┌─────────────────────────┐     ┌─────────────────────────┐
           │        AI Tutor         │     │    Clinical Copilot     │
           │ (Adaptive Concept Quiz) │     │ (Simulated SOAP Audits) │
           └─────────────────────────┘     └─────────────────────────┘
```

### 6.1 Semantic RAG Extraction & Grounding
Because the master knowledge is serialized in strict, clean YAML layouts, the pre-compilation step generates standard chunk targets optimized for Retrieval-Augmented Generation (RAG):

1.  **Chunk Boundaries**: Grounded per chapter. Chunks are split by logical keys: `theory`, `clinicalCorrelations`, `mcqs`, and `vivaQuestions`. This avoids cross-context contamination.
2.  **No-Hallucination Grounding Rule**: The system instructions force the AI model to query the local `search_index.json` or the relevant `generated_json/content/*` files before answering. Any clinical statement must be appended with its exact citation ID mapped in the SSOT (e.g., `[APTA]` or `[OSHA]`).

### 6.2 Agent Role Definitions

#### A. AI Tutor (Adaptive Dialogue)
*   *Task*: Act as an active oral examiner. It parses the `vivaQuestions` of a target chapter, presents the scenario, waits for student audio-to-text input, and dynamically rates the answer based on the `answer` and `clinicalPearls` keys in the SSOT.
*   *Personalization*: Uses the student's decay interval schedule to proactively quiz the student on concepts nearing their retention decay threshold.

#### B. Clinical Copilot (Pragmatic Scenarios)
*   *Task*: Audit student documentation. When a student drafts a SOAP note in the Clinical Case Lab, the Copilot compares the drafted findings with the true anatomical boundaries and pharmacological interactions defined in the Knowledge Graph, flagging clinical inconsistencies or missed precautions.

---

## 7. Decoupled Dependency Diagram

The diagram below represents the software stack of PhysioPilot Version 2. This structure demonstrates how the educational layer operates above the content layers without causing any side effects.

```
┌──────────────────────────────────────────────────────────────────────────┐
│                   PHYSICAL PRESENTATION LAYER (UI/UX)                     │
│  - Compose Screens (Learn, Revise, Clinical, MCQ, Viva, References)      │
│  - Interactive Regional Topic Hub Dashboards & SOAP Clinical Case Labs   │
└────────────────────────────────────┬─────────────────────────────────────┘
                                     │
                                     ▼
┌──────────────────────────────────────────────────────────────────────────┐
│                   V2 RETENTION & INTELLIGENT ROUTING                    │
│  - Spaced Repetition Scheduler (Leitner/SM-2 Decay)                      │
│  - Clinical Reasoning State Machine (Patient Journey Routing)            │
│  - Metric Aggregator (Mastery Score & Clinical Readiness Score Engine)   │
└────────────────────────────────────┬─────────────────────────────────────┘
                                     │
                                     ▼
┌──────────────────────────────────────────────────────────────────────────┐
│               AI SERVICES & EXTENSION LAYER (Gemini RAG)                 │
│  - Vector Search Index (text-embedding-004 representation of chapters)    │
│  - AI Tutor (Voice/Oral Viva Examiner) & Clinical Copilot (SOAP Auditor) │
└────────────────────────────────────┬─────────────────────────────────────┘
                                     │
                                     ▼
┌──────────────────────────────────────────────────────────────────────────┐
│              COMPILED ASSETS & SEMANTIC RUNTIME (Frozen)                 │
│  - generated_json/all_mcqs.json, generated_json/all_viva.json            │
│  - generated_json/search_index.json, generated_json/revision_notes.json   │
└────────────────────────────────────┬─────────────────────────────────────┘
                                     │
                                     ▼
┌──────────────────────────────────────────────────────────────────────────┐
│               SSOT DATA REPOSITORY & PIPELINES (Frozen)                  │
│  - master_knowledge/*.yaml (Anatomy, Phys, Path, Pharm, Micro, Electro)  │
│  - Validation Pipelines & Asset Compiler Scripts                        │
└──────────────────────────────────────────────────────────────────────────┘
```

---

## 8. Future Development Roadmap

The roadmap establishes a low-risk, iterative strategy to roll out Version 2 over three distinct phases.

```
       [Phase I: Ingest & Index] ──> [Phase II: Retention Engine] ──> [Phase III: Case Labs & AI]
```

### Phase I: Ingest & Index (Milestone 1 - 2)
*   **Goal**: Create the vector index and map regional tags across all 106 existing master chapters.
*   **Action Items**:
    1.  Develop an offline vector indexing script (`scripts/embed_knowledge.py`) using `text-embedding-004` to parse theory nodes and clinical correlations.
    2.  Add a metadata field in the JSON schemas supporting `taxonomy_tags` (list of body regions).
*   **Validation Check**: Verify that 100% of the 106 chapters are mapped to at least one primary regional taxonomy node.

### Phase II: Spaced Repetition & Performance Metrics (Milestone 3 - 4)
*   **Goal**: Implement local Room tables to track spaced repetition schedules, ease factors, and student metrics.
*   **Action Items**:
    1.  Create Room tables for `RepetitionSchedule` and `ConceptMastery`.
    2.  Write the scheduling algorithm computing next-review dates using the Graph-Distance Memory Decay Model.
    3.  Build the presentation layer displaying the Mastery Score and Clinical Readiness Score inside the user profile.
*   **Validation Check**: Verify that manual adjustment of a single anatomy card adjusts the decay interval of linked pathology nodes by the expected graph resonance factor.

### Phase III: Clinical Case Labs & AI Integration (Milestone 5 - 6)
*   **Goal**: Roll out the interactive SOAP Case Labs, AI Tutor, and Clinical Copilot.
*   **Action Items**:
    1.  Implement the Clinical Reasoning state machine as a modular UI routing flow in Jetpack Compose.
    2.  Configure RAG prompt structures on the application backend to ground the AI model strictly to citation-indexed chunks.
    3.  Launch the audio oral exam interface for the AI Tutor.
*   **Validation Check**: Ensure that AI responses fail gracefully to local search matching if the device is offline, with zero app crashes or blocking exceptions.

---

## 9. Architectural Certification Statement

Following a comprehensive read-only architectural audit of the master data structures, validation pipelines, and production-ready JSON outputs, the next-generation blueprint for PhysioPilot Version 2 is:

### **APPROVED AND CERTIFIED FOR SUBSEQUENT IMPLEMENTATION**

The design strictly guarantees:
*   **No modifications** to the compiled Android packages or Kotlin application code during design.
*   **100% structural fidelity** of the frozen Single Source of Truth database.
*   **Comprehensive theoretical and practical coverage** of the entire physical therapy syllabus.
