package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class SubjectChapter(
    val chapterId: String,
    val title: String,
    val index: Int,
    val description: String = ""
)

@Serializable
data class SubjectModule(
    val moduleId: String,
    val title: String,
    val description: String = "",
    val chapters: List<SubjectChapter>
)

@Serializable
data class SubjectModel(
    val subjectId: String,
    val title: String,
    val year: String,
    val description: String,
    val primaryTextbook: String,
    val secondaryTextbook: String,
    val chapters: List<SubjectChapter> = emptyList(),
    val modules: List<SubjectModule> = emptyList()
)

object SubjectChapterRepository {

    val subjectsMap: Map<String, SubjectModel> = mapOf(
        // 1st Year
        "anatomy" to SubjectModel(
            subjectId = "anatomy",
            title = CurriculumRepository.ANATOMY,
            year = "1st Year",
            description = "Comprehensive study of bone structures, muscle attachments, regional nerves, and cranial anatomy.",
            primaryTextbook = "Textbook of Anatomy by B.D. Chaurasia (BDC)",
            secondaryTextbook = "Atlas of Human Anatomy by Frank H. Netter",
            modules = listOf(
                SubjectModule(
                    moduleId = "anat_gen",
                    title = "General Anatomy",
                    description = "Fundamental concepts and skeletal system overview.",
                    chapters = listOf(
                        SubjectChapter("anat_ch1", "Introduction to Anatomy & Osteology", 1, "Anatomical terminology, bone classification, and histology.")
                    )
                ),
                SubjectModule(
                    moduleId = "anat_upper_limb",
                    title = "Upper Limb",
                    description = "Detailed study of the upper limb skeleton, muscles, joints, and nerves.",
                    chapters = listOf(
                        SubjectChapter("anat_ch2", "Introduction to Upper Limb", 1, "Overview and functional anatomy of the upper limb."),
                        SubjectChapter("anat_ch3", "Bones of Upper Limb", 2, "Clavicle, Scapula, Humerus, Radius, Ulna, and Hand bones."),
                        SubjectChapter("anat_ch4", "Pectoral Region", 3, "Muscles of the pectoral region and mammary gland."),
                        SubjectChapter("anat_ch5", "Axilla", 4, "Boundaries and contents of the axilla, brachial plexus."),
                        SubjectChapter("anat_ch6", "Back & Scapular Region", 5, "Muscles connecting upper limb to the vertebral column."),
                        SubjectChapter("anat_ch7", "Shoulder Joint Complex", 6, "Articulations and movements of the shoulder complex."),
                        SubjectChapter("anat_ch8", "Cutaneous Innervation, Venous Drainage & Lymphatics", 7, "Superficial veins, lymphatics, and dermatomes."),
                        SubjectChapter("anat_ch9", "Arm", 8, "Anterior and posterior compartments of the arm."),
                        SubjectChapter("anat_ch10", "Forearm", 9, "Flexor and extensor compartments of the forearm."),
                        SubjectChapter("anat_ch11", "Elbow & Radio-ulnar Joints", 10, "Elbow joint articulation and mechanics."),
                        SubjectChapter("anat_ch12", "Hand", 11, "Intrinsic muscles, palmar arches, and fascial spaces."),
                        SubjectChapter("anat_ch13", "Joints & Movements of Hand", 12, "Wrist, CMC, MCP, and IP joints."),
                        SubjectChapter("anat_ch14", "Major Nerves of Upper Limb", 13, "Radial, median, ulnar, axillary, and musculocutaneous nerves.")
                    )
                ),
                SubjectModule(
                    moduleId = "anat_lower_limb",
                    title = "Lower Limb",
                    description = "Study of the lower limb skeleton, muscles, joints, and nerves.",
                    chapters = listOf(
                        SubjectChapter("anat_ch25", "Bones of Lower Limb", 24, "Hip bone, femur, patella, tibia, fibula, and foot bones."),
                        SubjectChapter("anat_ch26", "Anterior Compartment of Thigh & Femoral Triangle", 25, "Femoral triangle boundaries, femoral sheath, femoral vessels/nerve, and quadriceps."),
                        SubjectChapter("anat_ch27", "Gluteal Region & Medial Thigh", 26, "Gluteal muscles, sciatic nerve emergence, structures under cover of gluteus maximus, and adductors."),
                        SubjectChapter("anat_ch28", "Posterior Thigh & Popliteal Fossa", 27, "Hamstring muscle origins, insertions, nerve supply, and popliteal fossa contents."),
                        SubjectChapter("anat_ch29", "Knee Joint & Leg Compartments", 28, "Knee joint capsule, ligaments, menisci, locking-unlocking biomechanics, and leg muscle groups."),
                        SubjectChapter("anat_ch30", "Ankle, Foot & Arches of Foot", 29, "Ankle joints, foot bone articulations, and maintenance of the longitudinal and transverse arches."),
                        SubjectChapter("anat_ch31", "Nerves and Blood Vessels of Lower Limb", 30, "Lumbosacral plexus, femoral, sciatic, tibial, and peroneal nerves, plantar/dorsal arterial systems, and venous drainage.")
                    )
                ),
                SubjectModule(
                    moduleId = "anat_thorax",
                    title = "Thorax",
                    description = "Thoracic cage, respiratory diaphragm, and contents.",
                    chapters = listOf(
                        SubjectChapter("anat_ch15", "Introduction to Thorax and Thoracic Cage", 14, "Thoracic cage, formation, superior thoracic aperture."),
                        SubjectChapter("anat_ch16", "Bones and Joints of the Thorax", 15, "Ribs, costal cartilages, costovertebral articulations."),
                        SubjectChapter("anat_ch17", "Thoracic Wall and Mechanism of Respiration", 16, "Intercostal muscles, vessels, phrenic nerve and breathing biomechanics."),
                        SubjectChapter("anat_ch18", "Pleural Cavities", 17, "Visceral and parietal pleurae, recesses, nerve sensitivity."),
                        SubjectChapter("anat_ch19", "Lungs (Pulmones)", 18, "Lung lobes, oblique/horizontal fissures, bronchopulmonary segments, bronchial tree."),
                        SubjectChapter("anat_ch20", "Mediastinum", 19, "Subdivisions, boundaries, superior/posterior compartments content."),
                        SubjectChapter("anat_ch21", "Pericardium and Heart", 20, "Fibroserous pericardium, cardiac borders, valves, coronary vessels."),
                        SubjectChapter("anat_ch22", "Superior Vena Cava, Aorta, Pulmonary Trunk, and Thymus", 21, "Great mediastinal vessels and lymphatic thymus details."),
                        SubjectChapter("anat_ch23", "Trachea and Esophagus", 22, "Windpipe C-shaped rings, esophageal physiological constrictions."),
                        SubjectChapter("anat_ch24", "Thoracic Duct, Azygos and Hemiazygos Veins, and Thoracic Sympathetic Trunks", 23, "Cisterna chyli, lymphatic drainage, posterior chest wall collateral pathways.")
                    )
                ),
                SubjectModule(
                    moduleId = "anat_head_neck",
                    title = "Head & Neck",
                    description = "Cranial anatomy, neck regions, and neuroanatomy.",
                    chapters = listOf(
                        SubjectChapter("anat_ch32", "Skull and Cranial Cavity", 31, "Bones of the skull, cranial nerve pathways, and blood vessels."),
                        SubjectChapter("anat_ch33", "Cervical Spine & Vertebrae", 32, "Structure and mechanics of C1-C7 vertebrae, crucial nerves, and neck musculature."),
                        SubjectChapter("anat_ch34", "Scalp & Face Muscles", 33, "Muscles of facial expression, mastication, sensory supply, and blood flow.")
                    )
                )
            )
        ),
        "physiology" to SubjectModel(
            subjectId = "physiology",
            title = CurriculumRepository.PHYSIOLOGY,
            year = "1st Year",
            description = "Nervous systems, reflex arcs, muscle fibers, cardiovascular rhythms, and cell metabolism.",
            primaryTextbook = "Textbook of Medical Physiology by Guyton and Hall",
            secondaryTextbook = "Essentials of Medical Physiology by K. Sembulingam",
            chapters = listOf(
                SubjectChapter("phys_ch1", "General Cell Physiology", 1, "Membrane transport, active and passive conduction, action potentials."),
                SubjectChapter("phys_ch2", "Neuromuscular Physiology", 2, "Motor units, EMG basics, sliding filament theory, muscle fatigue."),
                SubjectChapter("phys_ch3", "Cardiovascular & Respiratory Systems", 3, "Cardiac cycle, oxygen dissociation curve, pulmonary compliance."),
                SubjectChapter("phys_ch4", "Nervous System & Reflex Arcs", 4, "Sensory receptors, synaptic transmission, stretch and deep tendon reflexes."),
                SubjectChapter("phys_ch5", "Hematology & Immunology", 5, "Composition and functions of blood, hemopoiesis, hemoglobin, blood grouping, hemostasis, and immune responses."),
                SubjectChapter("phys_ch6", "Gastrointestinal System & Metabolism", 6, "Functional anatomy, enteric nervous system, motility, secretions, digestion/absorption, energy balance, BMR, SDA, RQ, obesity, starvation, and exercise metabolism in physical rehabilitation."),
                SubjectChapter("phys_ch7", "Renal Physiology & Excretion", 7, "Glomerular filtration, countercurrent mechanism, micturition reflex, and renal regulation of acid-base balance."),
                SubjectChapter("phys_ch8", "Endocrine & Reproductive Physiology", 8, "Hormonal feedback loops, insulin pathways, calcium homeostasis, and reproductive cycles."),
                SubjectChapter("phys_ch9", "Applied Exercise Physiology", 9, "VO2 max, oxygen debt, energy pathways, muscle fatigue, thermoregulation, and cardiovascular response to exercise.")
            )
        ),
        "electrotherapy_i" to SubjectModel(
            subjectId = "electrotherapy_i",
            title = CurriculumRepository.ELECTRO_I,
            year = "1st Year",
            description = "Basic principles of electricity, low-frequency therapeutic currents, circuit safety, and instrumentation.",
            primaryTextbook = "Clayton's Electrotherapy by Angela Forster",
            secondaryTextbook = "Electrotherapy Explained: Principles and Practice by Low & Reed",
            chapters = listOf(
                SubjectChapter("el1_ch1", "Fundamentals of Biophysics & Currents", 1, "Ohm's law, capacitance, resistance, and safety rules."),
                SubjectChapter("el1_ch2", "Direct & Galvanic Currents", 2, "Chemical reactions, polar effects, and iontophoresis application."),
                SubjectChapter("el1_ch3", "Faradic & Pulsed Currents", 3, "Innervated muscle stimulation, pulse shapes, and motor points."),
                SubjectChapter("el1_ch4", "Introductory Electro-Diagnostics", 4, "Strength-Duration (S-D) curves, chronaxie, and rheobase concept.")
            )
        ),
        "exercise_therapy_i" to SubjectModel(
            subjectId = "exercise_therapy_i",
            title = CurriculumRepository.EXERCISE_I,
            year = "1st Year",
            description = "Foundational exercises, physical skeletal levers, passive range of motion, and therapeutic massage techniques.",
            primaryTextbook = "Therapeutic Exercise: Foundations and Techniques by Kisner & Colby",
            secondaryTextbook = "Principles Of Exercise Therapy by M. Dena Gardiner",
            chapters = listOf(
                SubjectChapter("ex1_ch1", "Mechanical Principles & Skeletal Levers", 1, "Force vectors, gravity, equilibrium, and skeletal joint levers."),
                SubjectChapter("ex1_ch2", "Range of Motion (ROM) Exercise", 2, "Passive, active-assisted, and active free ROM protocols."),
                SubjectChapter("ex1_ch3", "Joint Movements & Starting Positions", 3, "Fundamental positions, derived positions, and limb stability."),
                SubjectChapter("ex1_ch4", "Introductory Therapeutic Massage", 4, "Techniques of effleurage, petrissage, friction, and tapotement."),
                SubjectChapter("ex1_ch5", "Relaxation Techniques", 5, "Jacobson's progressive muscle relaxation, somatic downregulation, and somatic awareness."),
                SubjectChapter("ex1_ch6", "Group Muscle Actions & Muscle Work", 6, "Agonist, antagonist, synergist, and Sherrington's reciprocal innervation law.")
            )
        ),

        // 2nd Year
        "electrotherapy_ii" to SubjectModel(
            subjectId = "electrotherapy_ii",
            title = CurriculumRepository.ELECTRO_II,
            year = "2nd Year",
            description = "Medium & high frequency currents, therapeutic lasers, TENS, and deep heating diathermy parameters.",
            primaryTextbook = "Clayton's Electrotherapy by Angela Forster",
            secondaryTextbook = "Electrotherapy Explained: Principles and Practice by Low & Reed",
            chapters = listOf(
                SubjectChapter("et2_ift", "Interferential Therapy (IFT)", 1, "Medium frequency carrier currents, beat frequencies, and pain modulation."),
                SubjectChapter("el2_ch2", "Transcutaneous Electrical Nerve Stimulation (TENS)", 2, "Gate control theory, high/low frequency parameters, and electrode placement."),
                SubjectChapter("el2_ch3", "Russian Current", 3, "Medium frequency burst stimulation, muscle strengthening, and duty cycles."),
                SubjectChapter("el2_ch4", "Shortwave Diathermy (SWD)", 4, "Capacitive and inductive methods, thermal dosage, and contraindications."),
                SubjectChapter("el2_ch5", "Microwave Diathermy (MWD)", 5, "Electromagnetic radiation, localized tissue heating, and safety guidelines."),
                SubjectChapter("el2_ch6", "LASER Therapy", 6, "Monochromaticity, coherence, biostimulation properties, and safety."),
                SubjectChapter("el2_ch7", "Therapeutic Ultrasound (US)", 7, "Piezoelectric effect, thermal/non-thermal effects, cavitation, and phonophoresis."),
                SubjectChapter("el2_ch8", "Biofeedback", 8, "Electromyographic sensing, visual/auditory feedback, and motor re-education."),
                SubjectChapter("el2_ch9", "Electromyography (EMG)", 9, "Needle and surface recording, motor unit action potentials (MUAP)."),
                SubjectChapter("el2_ch10", "Nerve Conduction Velocity (NCV)", 10, "Motor and sensory conduction, latency, amplitude, and entrapment diagnostics."),
                SubjectChapter("el2_ch11", "Therapeutic Traction", 11, "Cervical and lumbar traction, mechanical forces, and joint decompression.")
            )
        ),
        "exercise_therapy_ii" to SubjectModel(
            subjectId = "exercise_therapy_ii",
            title = CurriculumRepository.EXERCISE_II,
            year = "2nd Year",
            description = "Passive stretching, suspension therapy, traction, goniometry mechanics, and progressive muscle hollowing.",
            primaryTextbook = "Therapeutic Exercise: Foundations and Techniques by Kisner & Colby",
            secondaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            chapters = listOf(
                SubjectChapter("ex2_ch1", "Goniometry Mechanics", 1, "Joint axis determination, fixed/movable arms, and active ROM measurement."),
                SubjectChapter("ex2_ch2", "Manual Muscle Testing", 2, "MRC strength grading, muscle group isolation, and resistance testing protocol."),
                SubjectChapter("ex2_ch3", "Passive Stretching & Flexibility", 3, "Biology of muscle elongation, plastic deformation, and autogenic inhibition."),
                SubjectChapter("ex2_ch4", "Suspension Therapy", 4, "Frictionless movement environment, axial and eccentric suspension set-ups."),
                SubjectChapter("ex2_ch5", "Progressive Resistance Exercise", 5, "DeLorme and Oxford loading, muscle overload, progressive hypertrophy, and specificity."),
                SubjectChapter("ex2_ch6", "Peripheral Joint Mobilization", 6, "Accessory movements, convex-concave glide direction rules, and Maitland Grade I-IV oscillations."),
                SubjectChapter("ex2_ch7", "Balance & Coordination Training", 7, "Postural center of mass control, sensory balance feedback integration, and Frenkel's coordination tracks."),
                SubjectChapter("ex2_ch8", "Gait Analysis & Training", 8, "Gait cycle stance vs swing division, osteokinematic trajectory tracks, and walking deviation corrections."),
                SubjectChapter("ex2_ch9", "Crutch Walking & Ambulation Aids", 9, "BOS expansion, 3-point vs 4-point cane/crutch sequences, and radial axillary safety fitting."),
                SubjectChapter("ex2_ch10", "Hydrotherapy", 10, "Archimedes' buoyancy joint unloading, hydrostatic pressure venous return support, and aquatic exercise benefits."),
                SubjectChapter("ex2_ch11", "Posture & Corrective Exercise", 11, "Vertical plumb line sagittal balance evaluation, and Upper Crossed Syndrome corrective stretches."),
                SubjectChapter("ex2_ch12", "Proprioceptive Neuromuscular Facilitation", 12, "Autogenic/reciprocal inhibition pathways, diagonal D1 and D2 upper/lower extremity sequences, and hold-relax techniques.")
            )
        ),
        "biomechanics_kinesiology" to SubjectModel(
            subjectId = "biomechanics_kinesiology",
            title = CurriculumRepository.BIOMECHANICS,
            year = "2nd Year",
            description = "Force vectors, joint levers, osteokinematics, gait kinetics, and posture analysis.",
            primaryTextbook = "Joint Structure and Function: A Comprehensive Analysis by Pamela K. Norkin",
            secondaryTextbook = "Kinesiology of the Musculoskeletal System by Donald A. Neumann",
            modules = listOf(
                SubjectModule(
                    moduleId = "bio_foundations",
                    title = "Foundations",
                    description = "Fundamental principles of biomechanics and human movement.",
                    chapters = listOf(
                        SubjectChapter("bio_ch1", "Kinematics", 1, "Scalar vs vector properties, resolution of joint forces, open/closed chains."),
                        SubjectChapter("bio_ch2", "Kinetics", 2, "Scapulohumeral rhythm, knee locking-unlocking mechanism, hip forces."),
                        SubjectChapter("bio_ch3", "Movement System", 3, "Overview of the movement system."),
                        SubjectChapter("bio_ch4", "Muscle Activity & Strength", 4, "Muscle mechanics, length-tension relationships, and strength analysis.")
                    )
                ),
                SubjectModule(
                    moduleId = "bio_upper_quarter",
                    title = "Upper Quarter",
                    description = "Biomechanics of the shoulder, elbow, wrist, and hand.",
                    chapters = listOf(
                        SubjectChapter("bio_ch5", "Shoulder Complex", 5, "Scapulothoracic, glenohumeral, acromioclavicular, and sternoclavicular joint mechanics."),
                        SubjectChapter("bio_ch6", "Elbow & Forearm", 6, "Humeroulnar, humeroradial, and radioulnar joint mechanics."),
                        SubjectChapter("bio_ch7", "Wrist & Hand", 7, "Carpal kinematics, grip mechanics, and finger joint forces.")
                    )
                ),
                SubjectModule(
                    moduleId = "bio_axial_skeleton",
                    title = "Axial Skeleton",
                    description = "Biomechanics of the spine and posture.",
                    chapters = listOf(
                        SubjectChapter("bio_ch8", "Head Neck Trunk & Posture", 8, "Cervical, thoracic, and lumbar spine mechanics, and postural analysis.")
                    )
                ),
                SubjectModule(
                    moduleId = "bio_lower_quarter",
                    title = "Lower Quarter",
                    description = "Biomechanics of the pelvis, hip, knee, ankle, and foot.",
                    chapters = listOf(
                        SubjectChapter("bio_ch9", "Pelvis & Hip", 9, "Pelvic ring mechanics, sacroiliac joint, and hip joint forces."),
                        SubjectChapter("bio_ch10", "Knee", 10, "Tibiofemoral and patellofemoral joint mechanics, and meniscal function."),
                        SubjectChapter("bio_ch11", "Ankle & Foot", 11, "Talocrural, subtalar, and midtarsal joint kinematics, and foot arches.")
                    )
                ),
                SubjectModule(
                    moduleId = "bio_functional",
                    title = "Functional Biomechanics",
                    description = "Analysis of complex functional movements.",
                    chapters = listOf(
                        SubjectChapter("bio_ch12", "Gait Analysis", 12, "Spatio-temporal parameters, stance/swing phases, and ground reaction forces."),
                        SubjectChapter("bio_ch13", "Ergonomics & Occupational Health", 13, "Workplace mechanics, lifting techniques, and injury prevention.")
                    )
                )
            )
        ),
        "pathology" to SubjectModel(
            subjectId = "pathology",
            title = CurriculumRepository.PATHOLOGY,
            year = "2nd Year",
            description = "General and systemic pathology, inflammation, and tissue repair.",
            primaryTextbook = "Textbook of Pathology by Harsh Mohan",
            secondaryTextbook = "Robbins Basic Pathology",
            modules = listOf(
                SubjectModule(
                    moduleId = "path_general",
                    title = "General Pathology",
                    description = "Fundamental mechanisms of disease, injury, and healing.",
                    chapters = listOf(
                        SubjectChapter("path_ch1", "Cell Injury & Adaptation", 1, "Causes, mechanisms of cell injury, necrosis, and gangrene."),
                        SubjectChapter("path_ch2", "Inflammation", 2, "Acute and chronic inflammation, chemical mediators."),
                        SubjectChapter("path_ch3", "Healing and Repair", 3, "Primary and secondary healing, factors affecting repair."),
                        SubjectChapter("path_ch4", "Hemodynamic Derangements", 4, "Oedema, hyperemia, hemorrhage, shock, embolism, thrombosis, infarction."),
                        SubjectChapter("path_ch5", "Immunity & Hypersensitivity", 5, "Immunological mechanisms, autoimmune and immunodeficiency diseases."),
                        SubjectChapter("path_ch6", "Neoplasia", 6, "Benign vs malignant tumors, grading, staging, carcinogenesis."),
                        SubjectChapter("path_ch7", "Nutritional Disorders", 7, "Protein and vitamin deficiencies.")
                    )
                ),
                SubjectModule(
                    moduleId = "path_systemic",
                    title = "Systemic Pathology",
                    description = "Pathological changes in specific organ systems.",
                    chapters = listOf(
                        SubjectChapter("path_ch8", "Blood Disorders", 8, "Disorders of RBC, WBC, and platelets."),
                        SubjectChapter("path_ch9", "Blood Vessels", 9, "Atherosclerosis, DVT, thromboangitis obliterans."),
                        SubjectChapter("path_ch10", "Disease of Heart", 10, "CHF, ischemic heart disease, rheumatic heart disease, infective heart disease."),
                        SubjectChapter("path_ch11", "Respiratory System", 11, "Pneumonias, asthma, COPD, tuberculosis."),
                        SubjectChapter("path_ch12", "Joint Disorders", 12, "Types of arthritis and their features."),
                        SubjectChapter("path_ch13", "Bone Disorders", 13, "Osteoporosis, Paget's disease, osteomyelitis, bone tumors."),
                        SubjectChapter("path_ch14", "Muscles", 14, "Muscular dystrophy, Myasthenia gravis."),
                        SubjectChapter("path_ch15", "Nervous System", 15, "Meningitis, encephalitis, poliomyelitis, nerve injuries.")
                    )
                )
            )
        ),
        "microbiology" to SubjectModel(
            subjectId = "microbiology",
            title = CurriculumRepository.MICROBIOLOGY,
            year = "2nd Year",
            description = "Study of infectious agents, immune defense mechanisms, sterile procedures, and wound pathology management.",
            primaryTextbook = "Textbook of Microbiology by Ananthanarayan",
            secondaryTextbook = "Essentials of Medical Microbiology",
            chapters = listOf(
                SubjectChapter("micro_ch1", "Introduction to Microbiology & Bacterial Morphology", 1, "Principles of microbiology, bacterial structure, classification, and microscopic morphology."),
                SubjectChapter("micro_ch2", "Sterilization and Disinfection", 2, "Physical and chemical methods of sterilization and disinfection in clinical environments."),
                SubjectChapter("micro_ch3", "Immunology & Hypersensitivity Basics", 3, "Innate and adaptive immunity, antigen-antibody reactions, and hypersensitivity states."),
                SubjectChapter("micro_ch4", "Systemic Bacteriology & Wound Infections", 4, "Pathogenic bacteria, pyogenic cocci, wound infections, and osteomyelitis pathology."),
                SubjectChapter("micro_ch5", "Virology & Viral Pathogens", 5, "Structure and replication of major DNA/RNA viruses, polio, HIV, and hepatitis."),
                SubjectChapter("micro_ch6", "Mycology & Fungal Infections", 6, "Superficial, cutaneous, and deep mycoses of clinical and rehabilitation relevance."),
                SubjectChapter("micro_ch7", "Parasitology & Parasitic Infections", 7, "Protozoan and helminthic parasites, malaria, and cysticercosis."),
                SubjectChapter("micro_ch8", "Nosocomial Infections & Biomedical Waste Management", 8, "Hospital-acquired infections, hand hygiene, and biomedical waste segregation protocols.")
            )
        ),
        "pharmacology" to SubjectModel(
            subjectId = "pharmacology",
            title = CurriculumRepository.PHARMACOLOGY,
            year = "2nd Year",
            description = "Detailed study of pharmacokinetics, pharmacodynamics, clinical indications, adverse reactions, and rehabilitation-relevant medications based on KD Tripathi.",
            primaryTextbook = "Essentials of Medical Pharmacology by K.D. Tripathi",
            secondaryTextbook = "Pharmacology for Physiotherapists",
            chapters = listOf(
                SubjectChapter("pharm_ch1", "General Pharmacology", 1, "Pharmacokinetics, pharmacodynamics, dosage forms, and routes of drug administration."),
                SubjectChapter("pharm_ch2", "Autonomic Nervous System", 2, "Adrenergic, cholinergic, and anticholinergic agents with therapeutic effects."),
                SubjectChapter("pharm_ch3", "Musculoskeletal System", 3, "Skeletal muscle relaxants, neuromuscular blockers, and muscle tone regulation drugs."),
                SubjectChapter("pharm_ch4", "Drugs Acting on Kidney", 4, "Diuretics and antidiuretics influencing fluid/electrolyte balance."),
                SubjectChapter("pharm_ch5", "Cardiovascular System & Blood", 5, "Antihypertensives, antianginal, anticoagulants, antiplatelets, and coagulants."),
                SubjectChapter("pharm_ch6", "Central Nervous System", 6, "Anesthetics, analgesics, sedatives, hypnotics, and anti-parkinsonism agents."),
                SubjectChapter("pharm_ch7", "Autacoids", 7, "Histamine, 5-HT, prostaglandins, and non-steroidal anti-inflammatory drugs (NSAIDs)."),
                SubjectChapter("pharm_ch8", "Respiratory System", 8, "Drugs for bronchial asthma, cough, and COPD management."),
                SubjectChapter("pharm_ch9", "Gastrointestinal Tract", 9, "Antiulcer agents, antiemetics, laxatives, and antidiarrheals."),
                SubjectChapter("pharm_ch10", "Hormones", 10, "Corticosteroids, thyroid, insulin, oral hypoglycemics, and calcium regulators."),
                SubjectChapter("pharm_ch11", "Chemotherapy", 11, "General principles of antimicrobial therapy, antibacterials, and cancer chemotherapy."),
                SubjectChapter("pharm_ch12", "Geriatric Pharmacology", 12, "Age-related changes in pharmacology, polypharmacy, and precautions in elderly patients.")
            )
        ),

        // 3rd Year
        "general_medicine" to SubjectModel(
            subjectId = "general_medicine",
            title = CurriculumRepository.GEN_MEDICINE,
            year = "3rd Year",
            description = "Systems pathophysiology, cardiovascular condition profiles, and respiratory medical syndromes.",
            primaryTextbook = "Davidson's Principles and Practice of Medicine",
            secondaryTextbook = "Harrison's Principles of Internal Medicine",
            chapters = listOf(
                SubjectChapter("med_ch1", "Cardiovascular Pathophysiology", 1, "Myocardial infarction, coronary artery disease, hypertension."),
                SubjectChapter("med_ch2", "Pulmonary Syndromes", 2, "COPD, bronchial asthma, bronchiectasis, restrictive lung disease."),
                SubjectChapter("med_ch3", "Neuromuscular Disorders", 3, "Myasthenia gravis, muscular dystrophies, motor neuron disease.")
            )
        ),
        "general_surgery" to SubjectModel(
            subjectId = "general_surgery",
            title = CurriculumRepository.GEN_SURGERY,
            year = "3rd Year",
            description = "Surgical access, tissue grafting, thoracotomy incisions, scars, and postoperative recovery care.",
            primaryTextbook = "SRB's Manual of Surgery by Sriram Bhat M",
            secondaryTextbook = "Bailey & Love's Short Practice of Surgery",
            chapters = listOf(
                SubjectChapter("surg_ch1", "Surgical Incisions & Tissue Integrity", 1, "Laparotomy, thoracotomy, skin incisions, and wound healing kinetics."),
                SubjectChapter("surg_ch2", "Post-Surgical Scars & Grafting", 2, "Hypertrophic scars, contracture prevention, skin grafts, tendon transfers."),
                SubjectChapter("surg_ch3", "Postoperative Complications & Drainage", 3, "Atelectasis, DVT prevention, chest drainage tubes management.")
            )
        ),
        "neurology_neurosurgery" to SubjectModel(
            subjectId = "neurology_neurosurgery",
            title = CurriculumRepository.NEURO_SURG_3RD,
            year = "3rd Year",
            description = "Nerve suture repair, nerve regeneration monitoring, herniations, and brain injury surgical care.",
            primaryTextbook = "Brain's Diseases of the Nervous System by John Walton",
            secondaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            chapters = listOf(
                SubjectChapter("neu3_ch1", "Peripheral Nerve Traumatic Injuries", 1, "Sunderland and Seddon classification, neurapraxia, axonotmesis."),
                SubjectChapter("neu3_ch2", "Nerve Repair & Diagnostics", 2, "Primary and secondary nerve sutures, graft indications, tinel sign monitoring."),
                SubjectChapter("neu3_ch3", "Neurosurgical Interventions", 3, "Craniotomy, laminectomy, shunt placements (V-P), microdiscectomy.")
            )
        ),
        "assessment_3rd" to SubjectModel(
            subjectId = "assessment_3rd",
            title = CurriculumRepository.ASSESSMENT_3RD,
            year = "3rd Year",
            description = "Clinical physical assessments, pain intensity scales, posture, and neurological balance testing.",
            primaryTextbook = "Orthopedic Physical Assessment by David J. Magee",
            secondaryTextbook = "Therapeutic Exercise: Foundations and Techniques by Kisner & Colby",
            chapters = listOf(
                SubjectChapter("ast3_ch1", "Pain Assessment & Subjective History", 1, "Visual Analog Scale, NPRS, McGill questionnaire, somatic pain markers."),
                SubjectChapter("ast3_ch2", "Posture & Balance Assessments", 2, "Berg Balance Scale, posture grids, plumb lines, center of foot pressure."),
                SubjectChapter("ast3_ch3", "Systemic Range & MMT Testing", 3, "Joint testing landmarks, manual muscle testing grading (MRC scale).")
            )
        ),
        "research_stats" to SubjectModel(
            subjectId = "research_stats",
            title = CurriculumRepository.RESEARCH_STATS,
            year = "3rd Year",
            description = "Research designs, epidemiological sampling, data visualization, and statistical significance.",
            primaryTextbook = "Methods in Biostatistics by B.K. Mahajan",
            secondaryTextbook = "Research Methodology: Methods and Techniques by C.R. Kothari",
            chapters = listOf(
                SubjectChapter("res_ch1", "Clinical Study Designs", 1, "Randomized controlled trials, cohort, case-control studies."),
                SubjectChapter("res_ch2", "Data Distribution & Sampling", 2, "Standard deviation, variance, normal curve, probability sampling."),
                SubjectChapter("res_ch3", "Statistical Significance", 3, "Null hypothesis, t-tests, ANOVA, chi-square, p-values interpretation.")
            )
        ),

        // 4th Year
        "pt_ortho_sports" to SubjectModel(
            subjectId = "pt_ortho_sports",
            title = CurriculumRepository.PT_ORTHO_SPORTS,
            year = "4th Year",
            description = "Tendonitis, meniscal tears, bone fractures, and elite sports ligament rehabilitation.",
            primaryTextbook = "Orthopedic Physical Assessment by David J. Magee",
            secondaryTextbook = "Therapeutic Exercise: Foundations and Techniques by Kisner & Colby",
            chapters = listOf(
                SubjectChapter("ort4_ch1", "Ligament & Meniscal Tears", 1, "ACL, PCL, collateral ligaments of knee, and meniscal injury rehab."),
                SubjectChapter("ort4_ch2", "Fracture Rehabilitation Guidelines", 2, "Consolidation phases, stiffness, casting implications, open reduction internal fixation (ORIF)."),
                SubjectChapter("ort4_ch3", "Overuse Tendinopathies", 3, "Rotator cuff syndrome, Achilles tendonitis, tennis/golfer's elbow.")
            )
        ),
        "pt_surg_obs" to SubjectModel(
            subjectId = "pt_surg_obs",
            title = CurriculumRepository.PT_SURG_OBS,
            year = "4th Year",
            description = "Post-surgical wound mobilization, thoracic drainage, and prenatal/postnatal pelvic floor re-education.",
            primaryTextbook = "Therapeutic Exercise: Foundations and Techniques by Kisner & Colby",
            secondaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            chapters = listOf(
                SubjectChapter("obs4_ch1", "Thoracotomy & Sternotomy Care", 1, "Postoperative breathing exercises, huffing, early mobilization, scars."),
                SubjectChapter("obs4_ch2", "Prenatal and Postnatal Rehabilitation", 2, "Pelvic girdle pain, pelvic floor muscle training, Diastasis Recti management."),
                SubjectChapter("obs4_ch3", "Gynecological & Pelvic Surgeries", 3, "Cesarean section recovery, hysterectomy, and pelvic floor re-education.")
            )
        ),
        "pt_med_conditions" to SubjectModel(
            subjectId = "pt_med_conditions",
            title = CurriculumRepository.PT_MED_CONDITIONS,
            year = "4th Year",
            description = "Cardiopulmonary, neuromuscular and systemic clinical therapy protocols.",
            primaryTextbook = "Essentials of Cardiopulmonary Physical Therapy by Ellen Hillegass",
            secondaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            chapters = listOf(
                SubjectChapter("pmd4_ch1", "Cardiopulmonary Rehabilitation Phase I-III", 1, "Inpatient cardiac rehab, met levels, aerobic scaling, home exercise."),
                SubjectChapter("pmd4_ch2", "Obstructive Lung Diseases Therapy", 2, "Airway clearance, postural drainage, manual chest wall vibrators."),
                SubjectChapter("pmd4_ch3", "Geriatric Frailty & Sarcopenia", 3, "Resistance loading, gait drills, fall risks prevention, osteopenia.")
            )
        ),
        "pt_neuro_4th" to SubjectModel(
            subjectId = "pt_neuro_4th",
            title = CurriculumRepository.PT_NEURO_4TH,
            year = "4th Year",
            description = "Neuro-plasticity recovery models, stroke rehabilitation, and cerebral palsy training.",
            primaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            secondaryTextbook = "Neurological Rehabilitation by Darcy A. Umphred",
            chapters = listOf(
                SubjectChapter("neu4_ch1", "Stroke & Hemiplegia Rehabilitation", 1, "Brunnstrom stages, Bobath NDT, constraint-induced movement therapy."),
                SubjectChapter("neu4_ch2", "Cerebral Palsy & Pediatrics", 2, "Developmental delay, spasticity management, GMFM scoring."),
                SubjectChapter("neu4_ch3", "Parkinson's & Cerebellar Ataxia", 3, "Balance progression, Frenkel's exercises, rhythmic stabilization.")
            )
        ),
        "ethics_rehab" to SubjectModel(
            subjectId = "ethics_rehab",
            title = CurriculumRepository.ETHICS_REHAB,
            year = "4th Year",
            description = "Standard ethical practice, disability acts, and clinical legal bounds of practice.",
            primaryTextbook = "Ethics in Physical Therapy by Elizabeth D. Sherman",
            secondaryTextbook = "Introduction to Physical Therapy by Michael A. Pagliarulo",
            chapters = listOf(
                SubjectChapter("eth_ch1", "Disability Acts & Legislation", 1, "Legal definitions of disability, accessibility standards, and welfare."),
                SubjectChapter("eth_ch2", "Codified Professional Ethics", 2, "Malpractice, informed consent, patient autonomy, confidentiality bounds.")
            )
        ),

        // Internship
        "clinical_posting" to SubjectModel(
            subjectId = "clinical_posting",
            title = CurriculumRepository.CLINICAL_POSTING,
            year = "Internship",
            description = "Clinical posting rotations and bedside reporting under supervision.",
            primaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            secondaryTextbook = "Orthopedic Physical Assessment by David J. Magee",
            chapters = listOf(
                SubjectChapter("pos_ch1", "Bedside Assessment & Charting", 1, "Review of medical charts, reading active vent settings, and vitals check."),
                SubjectChapter("pos_ch2", "ICU Mobilization Safety", 2, "Lines and tubes management, safety limits, early mobility protocols.")
            )
        ),
        "case_discussion" to SubjectModel(
            subjectId = "case_discussion",
            title = CurriculumRepository.CASE_DISCUSSION,
            year = "Internship",
            description = "Formal inpatient histories, clinical arguments, and clinical reasoning reviews.",
            primaryTextbook = "Clinical Reasoning in Physical Therapy by Joy Higgs",
            secondaryTextbook = "Physical Rehabilitation by Susan B. O'Sullivan",
            chapters = listOf(
                SubjectChapter("cas_ch1", "Subjective Case Integration", 1, "Formulating a patient chief complaint and past surgical history timeline."),
                SubjectChapter("cas_ch2", "Provisional Physiotherapeutic Diagnosis", 2, "Differential diagnostics, clinical reasoning strategies, final prognosis.")
            )
        ),
        "documentation" to SubjectModel(
            subjectId = "documentation",
            title = CurriculumRepository.DOCUMENTATION,
            year = "Internship",
            description = "Writing detailed clinical progress notes and legal discharge charts.",
            primaryTextbook = "Documentation for Physical Therapist Assistants by Wendy D. Bircher",
            secondaryTextbook = "Physical Therapy Documentation by Cheryl Hall",
            chapters = listOf(
                SubjectChapter("doc_ch1", "SOAP Note Writing Standard", 1, "Subjective, Objective, Assessment, Plan documentation format."),
                SubjectChapter("doc_ch2", "Goal Setting & Legal Reporting", 2, "SMART goals, incident reports, and billing/legal standards.")
            )
        ),
        "hospital_practice" to SubjectModel(
            subjectId = "hospital_practice",
            title = CurriculumRepository.HOSPITAL_PRACTICE,
            year = "Internship",
            description = "Special ward ergonomics, equipment maintenance, and emergency duties.",
            primaryTextbook = "Introduction to Physical Therapy by Michael A. Pagliarulo",
            secondaryTextbook = "Ethics in Physical Therapy by Elizabeth D. Sherman",
            chapters = listOf(
                SubjectChapter("hsp_ch1", "Ward Ergonomics & Transfers", 1, "Body mechanics for transferring bariatric or critical patients."),
                SubjectChapter("hsp_ch2", "Equipment Calibration & Safety", 2, "Checking leakage current, electrotherapy calibration, emergency steps.")
            )
        )
    )

    fun getSubjectById(subjectId: String): SubjectModel? {
        return subjectsMap[subjectId.trim().lowercase()]
    }

    // Slugifies constant subject names to match mapped subjectIds
    fun getSubjectIdFromTitle(title: String): String {
        return when (title) {
            CurriculumRepository.ANATOMY -> "anatomy"
            CurriculumRepository.PHYSIOLOGY -> "physiology"
            CurriculumRepository.ELECTRO_I -> "electrotherapy_i"
            CurriculumRepository.EXERCISE_I -> "exercise_therapy_i"
            CurriculumRepository.ELECTRO_II -> "electrotherapy_ii"
            CurriculumRepository.EXERCISE_II -> "exercise_therapy_ii"
            CurriculumRepository.BIOMECHANICS -> "biomechanics_kinesiology"
            CurriculumRepository.PATHOLOGY -> "pathology"
            CurriculumRepository.MICROBIOLOGY -> "microbiology"
            CurriculumRepository.PHARMACOLOGY -> "pharmacology"
            CurriculumRepository.GEN_MEDICINE -> "general_medicine"
            CurriculumRepository.GEN_SURGERY -> "general_surgery"
            CurriculumRepository.NEURO_SURG_3RD -> "neurology_neurosurgery"
            CurriculumRepository.ASSESSMENT_3RD -> "assessment_3rd"
            CurriculumRepository.RESEARCH_STATS -> "research_stats"
            CurriculumRepository.PT_ORTHO_SPORTS -> "pt_ortho_sports"
            CurriculumRepository.PT_SURG_OBS -> "pt_surg_obs"
            CurriculumRepository.PT_MED_CONDITIONS -> "pt_med_conditions"
            CurriculumRepository.PT_NEURO_4TH -> "pt_neuro_4th"
            CurriculumRepository.ETHICS_REHAB -> "ethics_rehab"
            CurriculumRepository.CLINICAL_POSTING -> "clinical_posting"
            CurriculumRepository.CASE_DISCUSSION -> "case_discussion"
            CurriculumRepository.DOCUMENTATION -> "documentation"
            CurriculumRepository.HOSPITAL_PRACTICE -> "hospital_practice"
            else -> title.lowercase().replace(" & ", "_").replace(" ", "_").replace("/", "_").replace(",", "")
        }
    }
}
