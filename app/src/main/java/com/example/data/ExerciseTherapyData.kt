package com.example.data

object ExerciseTherapyData {
    val questions: List<VivaMcq> = listOf(
        // === STARTING POSITION (1-5) ===
        VivaMcq(
            question = "Which of the following describes the correct posture and base of support in the fundamental 'Standing' starting position?",
            options = listOf(
                "Heels together, toes slightly apart in a V-shape, arms hanging naturally at the sides with palms facing medially",
                "Feet wide apart, knees hyperextended, arms abducted to 90 degrees",
                "Heels 12 inches apart, feet externally rotated 45 degrees, arms supinated resembling anatomical position",
                "Feet together, knees locked, trunk flexed 10 degrees forward to shift center of gravity"
            ),
            correctAnswer = "Heels together, toes slightly apart in a V-shape, arms hanging naturally at the sides with palms facing medially",
            whyCorrect = "In fundamental Standing, the feet are close together (heels touch, toes slightly apart), the spine is erect, and arms hang by the sides with palms facing medially, unlike the anatomical position where palms face forward.",
            whyIncorrect = "Anatomical position has palms facing forward. Wide feet or hyperextended knees represent derived or abnormal postures.",
            clinicalImportance = "Starting positions provide the base for stabilization and muscle action, dictating the difficulty and safety of exercises.",
            examinerTip = "Remember that Standing has a high center of gravity and a relatively small base of support, making it an unstable position.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Fundamental standing differs from anatomical position in the orientation of the palms (medial vs anterior).",
            relatedConcept = "Fundamental Starting Positions",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "When deriving a new starting position from standing by modifying the arm position to 'Yard Standing', which muscle group works statically to maintain the arms in 90 degrees of abduction?",
            options = listOf(
                "Scapular retractors and shoulder abductors (middle deltoid and supraspinatus)",
                "Shoulder adductors and internal rotators (pectoralis major)",
                "Elbow flexors and pronators",
                "Neck extensors and upper trapezius"
            ),
            correctAnswer = "Scapular retractors and shoulder abductors (middle deltoid and supraspinatus)",
            whyCorrect = "In 'Yard Standing', the arms are held horizontally at 90 degrees of abduction. This requires isometric (static) work of the middle deltoid, supraspinatus, and scapular stabilizers.",
            whyIncorrect = "Adductors and flexors would lower the arms or bend the elbows. Upper trapezius should remain quiet to prevent shoulder shrugging.",
            clinicalImportance = "Derived positions like Yard Standing increase the workload on the shoulder girdle and are useful for progressive endurance training.",
            examinerTip = "Yard starting position increases the lateral leverage and raises the center of gravity.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "'Yard' position refers to holding the arms horizontally abducted to 90 degrees.",
            relatedConcept = "Derived Starting Positions",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "In the derived starting position 'Stride Standing', how has the base of support (BOS) been altered compared to fundamental Standing, and what is its effect on equilibrium?",
            options = listOf(
                "BOS is enlarged anteroposteriorly or laterally, increasing stability in that direction",
                "BOS is decreased, raising the center of gravity and reducing stability",
                "BOS is lateralized only, raising the center of gravity",
                "BOS is unaffected, but the line of gravity shifts outside the limits of stability"
            ),
            correctAnswer = "BOS is enlarged anteroposteriorly or laterally, increasing stability in that direction",
            whyCorrect = "In Stride Standing, the wheels/feet are separated by one foot's distance in any direction. This expands the base of support and lowers the center of gravity slightly, significantly improving stability.",
            whyIncorrect = "Enlarging the base of support always increases rather than decreases stability, provided the line of gravity falls within it.",
            clinicalImportance = "Stride Standing is an excellent progression for neurological patients with balance deficits before transitioning back to fundamental standing.",
            examinerTip = "Stride increases stability in the direction of the stride (forward/backward or lateral).",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "A larger base of support lowers the muscular demand needed to maintain equilibrium.",
            relatedConcept = "Derived Starting Positions",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "Which fundamental starting position offers the lowest center of gravity and widest base of support, making it the most stable?",
            options = listOf(
                "Lying",
                "Sitting",
                "Kneeling",
                "Standing"
            ),
            correctAnswer = "Lying",
            whyCorrect = "Lying (supine) offers the maximum possible base of support as the entire posterior aspect of the body is supported, with the center of gravity at its lowest point, making it highly stable.",
            whyIncorrect = "Sitting, kneeling, and standing progressively raise the center of gravity and reduce the base of support, increasing instability.",
            clinicalImportance = "Lying is the safest position for early post-operative mobilization and severe cardiorespiratory or neurological rehabilitation.",
            examinerTip = "Lying has minimal muscle work required for postural maintenance compared to sitting or standing.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Lying requires the least muscle work to maintain posture due to the lower center of gravity and maximum support.",
            relatedConcept = "Fundamental Starting Positions",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "In the derived position 'Stoop Sitting', what is the state of the hips and spine, and why is this position clinically useful?",
            options = listOf(
                "Hips are flexed, trunk is bent forward from hip joints with spine straight; useful for back muscle strengthening",
                "Hips are extended, spine is flexed; useful for abdominal exercises",
                "Spine is hyperextended, knees are semi-flexed; useful for quadriceps training",
                "Trunk is rotated 45 degrees, hips are abducted; useful for scoliosis management"
            ),
            correctAnswer = "Hips are flexed, trunk is bent forward from hip joints with spine straight; useful for back muscle strengthening",
            whyCorrect = "In Stoop Sitting, the hips are flexed, and the trunk is inclined forward from the hips while the spine remains straight. This forces the back extensors to work against gravity to stabilize the neck and spine.",
            whyIncorrect = "The spine must not flex (slouch) in 'Stoop' sitting. Hips are flexed, not extended.",
            clinicalImportance = "Highly effective for training the thoracic and lumbar spinal extensors in patients with postural kyphosis.",
            examinerTip = "Stoop sitting places a high demand on the spinal extensors without Loading the lower limbs.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Stoop sitting relies on forward hip inclination with a straight spine.",
            relatedConcept = "Derived Starting Positions",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),

        // === FREE EXERCISE (6-10) ===
        VivaMcq(
            question = "What is the defining characteristic of a 'Free Active Exercise'?",
            options = listOf(
                "Performed by the patient using their own muscular effort without any external assistance or resistance",
                "Performed entirely by the therapist with the patient relaxed",
                "Requires external weights or pulley systems to assist movement",
                "Movement assisted by the force of gravity or buoyant water only"
            ),
            correctAnswer = "Performed by the patient using their own muscular effort without any external assistance or resistance",
            whyCorrect = "Free Active Exercises are those performed solely by the patient’s voluntary muscle efforts against the force of gravity only, without any added machine/therapist assistance or external resistance.",
            whyIncorrect = "Therapist-led movement is 'Passive'. Weighted exercises are 'Resisted'. Buoyancy-assisted exercises are 'Assisted'.",
            clinicalImportance = "Free exercises are great for building neuromuscular control, co-contraction, coordination, and patient-directed home programs.",
            examinerTip = "Remember that gravity acts as the only external resistance during free active exercises.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Free exercise tests and trains a patient's own voluntary strength and movement coordination.",
            relatedConcept = "Active Exercise Classification",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "How are active exercises classified based on the force or assistance applied during movement?",
            options = listOf(
                "Assisted, Free, and Resisted",
                "Passive, Static, and Ballistic",
                "Isotonic, Isometric, and Isokinetic",
                "Concentric, Eccentric, and Plyometric"
            ),
            correctAnswer = "Assisted, Free, and Resisted",
            whyCorrect = "Active exercises are classified into three core types under Gardiner's: Assisted (assisted by external force), Free (patient's effort against gravity), and Resisted (resisted by external load).",
            whyIncorrect = "Passive is not an active exercise. Isotonic/isometric relate to contraction types. Concentric/eccentric are muscle actions.",
            clinicalImportance = "Understanding this classification allows therapists to graduate rehabilitation progress logically based on muscle strength grade.",
            examinerTip = "Remember that active-assisted is typically used for muscle grades 1 to 2, free for grade 3, and resisted for grades 4 and 5.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Active exercises scale from assisted to free, and then to resisted as strength improves.",
            relatedConcept = "Active Exercise Principles",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "When progressing a patient recovering from a peripheral nerve injury of the upper limb, what is the logical sequence of active exercise prescription?",
            options = listOf(
                "Assisted-active -> Free-active -> Resisted-active",
                "Resisted-active -> Free-active -> Assisted-active",
                "Passive -> Resisted-active -> Free-active",
                "Isometric -> Resisted-active -> Assisted-active"
            ),
            correctAnswer = "Assisted-active -> Free-active -> Resisted-active",
            whyCorrect = "As myelin sheath regenerates and motor units are re-innervated, muscle strength increases sequentially. Progression goes from Assisted-active (to help weak muscles complete full ROM), to Free-active, and finally Resisted-active.",
            whyIncorrect = "Going from resisted to assisted reversely violates overload and progressive loading parameters.",
            clinicalImportance = "Ensures safe muscular recovery and prevents fatigue-induced soft tissue injuries or compensation strategies.",
            examinerTip = "Graduation of exercise load must always align with the patient's current manual muscle testing (MMT) scores.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Neurological rehabilitation of motor functions follows a systematic assisted-to-resisted progression.",
            relatedConcept = "Progression in Exercise Therapy",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What represents a primary therapeutic advantage of Free Exercise compared to highly resisted exercise?",
            options = listOf(
                "Promotes neuromuscular coordination, patient independence, and active motor learning",
                "Ensures isolated joint movement without any synergist recruitment",
                "Guarantees 100% hypertrophy of Type I muscle fibers",
                "Minimizes metabolic demand and oxygen consumption"
            ),
            correctAnswer = "Promotes neuromuscular coordination, patient independence, and active motor learning",
            whyCorrect = "Free Active Exercises force the patient to coordinate muscle activation, stabilize nearby segments voluntarily, work on posture, and understand movement patterns independently, driving motor learning.",
            whyIncorrect = "Free exercises recruit multiple muscle groups/synergists, do not guarantee isolated muscle hypertrophy, and increase local metabolic demand.",
            clinicalImportance = "Enables patients to practice functional, multi-joint movements that translate directly to daily living activities.",
            examinerTip = "Free exercise is vital for improving neuromuscular coordination and self-efficacy.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Free exercise emphasizes motor learning and coordinated multi-joint pathways.",
            relatedConcept = "Therapeutic Benefits of Active Exercise",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "When performing free shoulder abduction exercises in a standing position, where is the external resistive torque generated by gravity maximum?",
            options = listOf(
                "At 90 degrees of abduction, when the moment arm of gravity is longest",
                "At 0 degrees of abduction, when the arm hangs by the side",
                "At 180 degrees of abduction, when the hand is directly overhead",
                "At 45 degrees of abbreviation, during the initial acceleration phase"
            ),
            correctAnswer = "At 90 degrees of abduction, when the moment arm of gravity is longest",
            whyCorrect = "Gravity acts vertically downward. Dynamic resistance is equal to the product of weight of the limb and its perpendicular distance from the joint center (moment arm). At 90 degrees abduction, the arm is horizontal, making this moment arm maximum.",
            whyIncorrect = "At 0 and 180 degrees, the line of action of gravity passes close to or directly through the joint center, making the moment arm minimal.",
            clinicalImportance = "Enables physical therapists to modify tension and difficulty across ranges of motion by changing the patient's starting posture.",
            examinerTip = "Understand torque and moment arm concepts as they dictate joint loading in exercise therapy.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Gravitational resistance is maximum when the limb is horizontal to the ground.",
            relatedConcept = "Biomechanical Principles of Exercise",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),

        // === RELAXATION (11-16) ===
        VivaMcq(
            question = "What is the physiological difference between normal muscle tone (resting tension) and a pathological muscle spasm?",
            options = listOf(
                "Tone is a baseline involuntary contraction driven by reflex arcs; spasm is an acute, sustained, hyperactive contraction due to injury or pathology",
                "Tone is purely voluntary; spasm is purely involuntary",
                "Tone is managed by high-threshold motor units; spasm is managed by low-threshold motor units",
                "Tone occurs only in skeletal muscle; spasm occurs only in visceral smooth muscle"
            ),
            correctAnswer = "Tone is a baseline involuntary contraction driven by reflex arcs; spasm is an acute, sustained, hyperactive contraction due to injury or pathology",
            whyCorrect = "Normal muscle tone is healthy baseline resistance to passive stretch provided by motor unit activity. Spasm is an involuntary, state of abnormal, painful, hyperactive sustained contraction resulting from noxious stimuli or injury.",
            whyIncorrect = "Muscle tone is involuntary and managed by spinal monosynaptic reflexes. Spasms are skeletal and can occur alongside hyperactive spinal reflexes.",
            clinicalImportance = "Recognizing pathological spasm allows the therapist to selection relaxation techniques (e.g., heat, gentle movement) rather than forced stretching, which could worsen the spasm.",
            examinerTip = "Contrast muscular spasticity (upper motor neuron) with spasm (skeletal protective reflex).",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Spasm is a painful hyperactive contraction triggered often as a protective reflex following injury.",
            relatedConcept = "Muscle Tone Alterations",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the core working principle behind Jacobson's Progressive Muscle Relaxation (PMR) technique?",
            options = listOf(
                "Systematically tensing a muscle group before consciously releasing it to enhance awareness of deep muscle relaxation",
                "Using visual imagery of peaceful landscapes to reduce autonomic arousal",
                "Performing high-frequency rhythmic oscillations at joint endpoints",
                "Applying electrical stimulation to antagonist muscle groups"
            ),
            correctAnswer = "Systematically tensing a muscle group before consciously releasing it to enhance awareness of deep muscle relaxation",
            whyCorrect = "Jacobson's technique teaches patients to recognize the physical sensation of tension versus relaxation by systematically contracting specific muscle groups for 5-10 seconds, then releasing them suddenly.",
            whyIncorrect = "Mental imagery is a cognitive-stress management tool. Rhythmic oscillations mobilize joints. Electrical stimulation is a physical modality.",
            clinicalImportance = "Highly beneficial for chronic pain, fibromyalgia, and anxiety-related hypertonia to restore patient control over muscle tension.",
            examinerTip = "Jacobson's PMR relies on conscious somatic feedback to regulate somatic and autonomic hyperarousal.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "PMR leverages active contraction-relaxation cycles to build somatic awareness.",
            relatedConcept = "Relaxation Techniques",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "How does the neurological principle of reciprocal inhibition contribute to muscle relaxation during therapeutic exercise?",
            options = listOf(
                "Contraction of an agonist muscle automatically sends inhibitory signals via spinal interneurons to relax the antagonist muscle",
                "Simultaneous contraction of both agonist and antagonist stabilizes the joint segment",
                "Prolonged stretch of a muscle activates the Golgi tendon organ to inhibit that same muscle",
                "Passive lengthening of a muscle decreases the mechanical sensitivity of the Pacinian corpuscles"
            ),
            correctAnswer = "Contraction of an agonist muscle automatically sends inhibitory signals via spinal interneurons to relax the antagonist muscle",
            whyCorrect = "Reciprocal inhibition is a spinal reflex. When the agonist contracts, Ia afferents excite inhibitory interneurons in the spinal cord, which decreases the motor output to the antagonist muscle, causing it to relax.",
            whyIncorrect = "Agonist-antagonist simultaneous contraction is co-contraction. Self-inhibition via GTOs is autogenic inhibition.",
            clinicalImportance = "Allows physical therapists to relax hypertonic hamstrings by having the patient actively contract the quadriceps.",
            examinerTip = "Understand the neurological pathway: Ia spindle afferents synapsing on inhibitory spinal interneurons.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Active movement of one muscle groups helps relax the opposing muscle group.",
            relatedConcept = "Neurophysiological Mechanisms of Relaxation",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What physiological marker is primarily targeted to confirm the onset of systemic relaxation during clinical biofeedback training?",
            options = listOf(
                "Reduction in skeletal muscle electromyographical (EMG) activity and increased parasympathetic heart rate variability",
                "Increase in systolic blood pressure and breathing rate",
                "Increase in serum cortisol and sympathetic outflow",
                "Hyperactivity of deep tendon reflexes"
            ),
            correctAnswer = "Reduction in skeletal muscle electromyographical (EMG) activity and increased parasympathetic heart rate variability",
            whyCorrect = "During biofeedback relaxation, electromyography monitors muscle electrical activity. Systemic relaxation is characterized by reduced EMG amplitudes and an increase in parasympathetic dominant HRV.",
            whyIncorrect = "Increases in BP, respiratory rates, cortisol, or hyperreflexia are signs of sympathetic flight-or-fight feedback.",
            clinicalImportance = "Validates the patient's objective response to relaxation therapy and guides adjustment of clinical strategies.",
            examinerTip = "Know that EMG biofeedback translates micro-volt muscular signals into auditory/visual cues for patients.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Biofeedback converts physiological data into tangible feedback to help patients suppress hypertonicity.",
            relatedConcept = "Biofeedback in Relaxation",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "When treating a patient with severe localized spasticity of the flexor digitorum after stroke, which clinical approach best represents 'Local Relaxation'?",
            options = listOf(
                "Using comfortable positioning, warmth, and slow passive extension of the specific fingers",
                "Conducting a 30-minute diaphragmatic breathing session with dim lights",
                "Guiding the patient through full-body Progressive Muscle Relaxation (PMR)",
                "Administering systemic muscle relaxants orally"
            ),
            correctAnswer = "Using comfortable positioning, warmth, and slow passive extension of the specific fingers",
            whyCorrect = "Local relaxation addresses tension or spasticity in a specific joint or muscle group using positioning, thermal packs, and slow passive mobilization that does not trigger a stretch reflex.",
            whyIncorrect = "Diaphragmatic breathing and PMR are general/systemic relaxation techniques. Oral medications represent pharmacological systemic interventions.",
            clinicalImportance = "In Stroke rehab, focused local relaxation prepares hypertonic hand muscles for task-specific motor coordination drills.",
            examinerTip = "Be clear on the difference between systemic (general) relaxation and segment-focused (local) relaxation.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Local relaxation applies mechanical and physical inputs directly to a single hypertonic region.",
            relatedConcept = "Local vs General Relaxation",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "What biological mechanism explains the effectiveness of Post-Isometric Relaxation (PIR) in relieving local myofascial tightness?",
            options = listOf(
                "A brief, low-intensity isometric contraction activates Golgi Tendon Organs (GTOs), which inhibit alpha motor neurons of the target muscle",
                "High-intensity contractions rupture tight sarcomere bands, mechanically elongating the tissue",
                "An isometric contraction increases sympathetic nerve activity, causing vasodilation",
                "The contraction activates nociceptors, which block descending pain pathways through the spinal cord"
            ),
            correctAnswer = "A brief, low-intensity isometric contraction activates Golgi Tendon Organs (GTOs), which inhibit alpha motor neurons of the target muscle",
            whyCorrect = "Under PIR, an isometric contraction of the tight muscle against mild resistance builds tension. This stimulates GTOs (located in tendon junctions), triggering autogenic inhibition, which relaxes the muscle's motor units.",
            whyIncorrect = "Rupturing sarcomeres causes muscle tear/injury. Vasodilation and pain gating do not directly drive immediate muscular lengthening.",
            clinicalImportance = "Extremely useful in manual therapy of the neck and back to restore movement without aggressive, painful structural stretching.",
            examinerTip = "Remember PIR operates through autogenic inhibition, whereas Contract-Relax often utilizes reciprocal inhibition.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "PIR utilizes the post-isometric autogenic silent period of the muscle spindle/GTO loops.",
            relatedConcept = "Post-Isometric Relaxation (PIR)",
            reference = "Chaitow's Clinical Application of Neuromuscular Techniques"
        ),

        // === BREATHING EXERCISE (17-21) ===
        VivaMcq(
            question = "During quiet, normal inhalation, what is the primary movement of the diaphragm and its effect on intra-thoracic pressure?",
            options = listOf(
                "The diaphragm moves downward (flattens), creating a negative intra-thoracic pressure that pulls air in",
                "The diaphragm ascends into the thoracic dome, pushing carbon dioxide out",
                "The diaphragm contracts outward, expanding the lower ribs in a transverse plane only",
                "The diaphragm descends, increasing thoracic volume and increasing pleural pressure"
            ),
            correctAnswer = "The diaphragm moves downward (flattens), creating a negative intra-thoracic pressure that pulls air in",
            whyCorrect = "During inspiration, the diaphragm contracts and flattens (descends), increasing vertical thoracic volume. This drops intra-thoracic pressure below atmospheric pressure, causing air to rush into the lungs.",
            whyIncorrect = "Ascending into the thoracic dome occurs during exhalation. Pleural pressure becomes more negative during inspiration, not positive.",
            clinicalImportance = "Diaphragmatic breathing minimizes accessory muscle work, lowering energy expenditure in respiratory patients.",
            examinerTip = "Diaphragm descent is responsible for approximately 75% of inhalation volume in healthy individuals.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Contraction of the diaphragm flattens the muscle dome, driving negative pressure inspiration.",
            relatedConcept = "Mechanics of Respiration",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the primary physiological purpose of teaching pursed-lip breathing to a patient with Chronic Obstructive Pulmonary Disease (COPD)?",
            options = listOf(
                "To maintain positive airway pressure during exhalation, preventing early airway collapse and air trapping",
                "To hyperinflate the alveoli and increase the accessory muscle recruitment",
                "To increase the respiration rate to expel trapped nitrogen",
                "To bypass physical dead space in the upper airway"
            ),
            correctAnswer = "To maintain positive airway pressure during exhalation, preventing early airway collapse and air trapping",
            whyCorrect = "Pursed-lip exhalation creates a back pressure (PEP effect) that splints the floppy, diseased bronchioles open during expiration. This allows more air to be expelled and reduces hyperinflation.",
            whyIncorrect = "COPD rehabilitation aims to reduce (not increase) hyperinflation and accessory muscle use. Pursed-lip breathing slows the respiratory rate.",
            clinicalImportance = "Helps patients with emphysema control dyspnea during physical activities like stair climbing.",
            examinerTip = "Remember, pursed-lip breathing reduces exhalation collapse and improves carbon dioxide clearance.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Pursed lips generate positive expiratory airway pressure, preventing dynamic collapse of compliant airways.",
            relatedConcept = "Breathing Patterns",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "For a patient recovering from an acute abdominal surgery, which breathing exercise is most crucial to implement, and why?",
            options = listOf(
                "Diaphragmatic/basal expansions to prevent atelectasis and collapse of the lower lung lobes",
                "Apical breathing exercises to minimize abdominal incision movement and pain",
                "High-frequency shallow breathing to maintain cardiac output",
                "Forced hyperventilation to increase arterial oxygen saturation"
            ),
            correctAnswer = "Diaphragmatic/basal expansions to prevent atelectasis and collapse of the lower lung lobes",
            whyCorrect = "Abdominal surgery causes shallow, painful chest breathing. This causes poor ventilation of lung bases, leading to atelectasis. Diaphragmatic/basal expansions encourage alveolar recruitment in the dependent lobes.",
            whyIncorrect = "Apical breathing avoids lower chest movement but leaves lung bases collapsed. Shallow high-frequency breathing promotes atelectasis.",
            clinicalImportance = "Reduces post-operative pulmonary complications (PPCs) and shortens hospital stay.",
            examinerTip = "Observe the movement of the lower thoracic cage to confirm effective basal zone expansion.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Deep diaphragmatic expansions prevent atelectasis by mobilizing the lower lobes of the lungs.",
            relatedConcept = "Post-operative Respiratory Management",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the therapeutic rationale for instructing a respiratory patient to perform an 'inspiratory hold' at the end of deep inhalation?",
            options = listOf(
                "Promotes collateral ventilation through the pores of Kohn, aiding re-expansion of collapse alveoli",
                "Reduces cardiac preload and increases the efficiency of the left ventricle",
                "Fatigues accessory muscles of inspiration to prevent hyperventilation",
                "Decreases the diffusion of oxygen across the alveolar-capillary membrane"
            ),
            correctAnswer = "Promotes collateral ventilation through the pores of Kohn, aiding re-expansion of collapse alveoli",
            whyCorrect = "An inspiratory pause of 3-5 seconds allows air to bypass obstructed bronchioles through collateral pathways (pores of Kohn and canals of Lambert), ventilating and re-inflating draft-locked alveoli.",
            whyIncorrect = "Hold does not significantly alter cardiovascular preload in a beneficial way, nor does it target accessory muscle fatigue or reduce diffusion.",
            clinicalImportance = "Improves lung compliance and optimization of ventilation-perfusion (V/Q) matching in post-op care.",
            examinerTip = "The inspiratory hold is a key component of segment expansion breathing.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Collateral ventilation channels are recruited during sustained end-inspiratory holds.",
            relatedConcept = "Collateral Ventilation",
            reference = "Pryor and Prasad's Physiotherapy for Respiratory and Cardiac Problems"
        ),
        VivaMcq(
            question = "Which phase of the Active Cycle of Breathing Techniques (ACBT) focuses specifically on mobilizing secretions from the distal to proximal airways?",
            options = listOf(
                "Thoracic Expansion Exercises, which increase lung volume and collateral ventilation",
                "Breathing Control, which is a resting phase for energy conservation",
                "Forced Expiratory Technique (huffing) paired with abdominal contraction",
                "Deep coughing from low lung volumes using accessory neck muscles"
            ),
            correctAnswer = "Thoracic Expansion Exercises, which increase lung volume and collateral ventilation",
            whyCorrect = "Thoracic expansion exercises are deep inhalations, often with an inspiratory hold. This increases lung volume, lowering resistance and opening collateral pathways behind secretions, helping to mobilize them from small, distal airways.",
            whyIncorrect = "Breathing control is for recovery. Forced Expiration (huffing) is used to clear (expel) secretions already mobilized to the proximal airways.",
            clinicalImportance = "Effective airway clearance protocol for patients with bronchiectasis or cystic fibrosis.",
            examinerTip = "ACBT contains three phases: Breathing Control, Thoracic Expansion, and Forced Expiratory Technique (FET).",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Thoracic expansion uses deep breathing to slide behind and loosen distal bronchial secretions.",
            relatedConcept = "Airway Clearance Techniques",
            reference = "Pryor and Prasad's Physiotherapy for Respiratory and Cardiac Problems"
        ),

        // === ROM (22-27) ===
        VivaMcq(
            question = "Which of the following conditions is a primary indication for Passive Range of Motion (PROM) instead of Active contraction?",
            options = listOf(
                "In the acute stage of healing when active muscle contraction is contraindicated (e.g., immediately post-tendon repair)",
                "When the therapist aims to increase muscle strength and power from 0 to 1 on the MRC scale",
                "To prevent muscle atrophy in a patient with a fully immobilized limb",
                "To increase systemic cardiovascular endurance and metabolic demand in stable postoperative patients"
            ),
            correctAnswer = "In the acute stage of healing when active muscle contraction is contraindicated (e.g., immediately post-tendon repair)",
            whyCorrect = "In the early phase of tissue repair (e.g., tendon suturing, acute cartilage tears), muscle contraction could pull on and disrupt surgical repairs. PROM maintains joint mobility without loading repaired tissues.",
            whyIncorrect = "PROM cannot build muscle strength or prevent muscle atrophy because muscle fibers are not actively contracting.",
            clinicalImportance = "Safeguards structural repairs of soft tissue while mitigating the risk of articular contractures.",
            examinerTip = "A primary contraindication for active range is a newly repaired tendon, where active tension would rupture the repair.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "PROM is used when active muscular effort would damage healing tissues.",
            relatedConcept = "Passive Range of Motion Indications",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the defining feature of Active-Assisted Range of Motion (AAROM)?",
            options = listOf(
                "Prime mover muscles receive external assistance from an outside force to complete the desired joint trajectory",
                "Movement is performed entirely by an electromechanical Continuous Passive Motion (CPM) device",
                "The patient performs the movement against maximum physical resistance offered by the therapist",
                "The target joint is put through ranges of motion without any voluntary muscle contraction from the patient"
            ),
            correctAnswer = "Prime mover muscles receive external assistance from an outside force to complete the desired joint trajectory",
            whyCorrect = "AAROM is active movement where assistance is provided manually by a therapist, mechanically by a pulley/wand, or gravitationally, to help a weak muscle (typically MMT grade 2) complete the range.",
            whyIncorrect = "CPM does PROM. Resisted exercises involve resisting force. Zero voluntary contraction defines PROM.",
            clinicalImportance = "Allows weak muscles to be progressively trained in early phases of stroke recovery or orthopedic rehabilitation.",
            examinerTip = "AAROM is prescribed when muscle strength is insufficient to complete the full range against gravity.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "AAROM bridges the gap between passive movement and independent active free movement.",
            relatedConcept = "Active-Assisted Range of Motion",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "Which of the following physiological benefits can NOT be achieved by providing Passive Range of Motion (PROM) alone?",
            options = listOf(
                "Preventing muscle atrophy, increasing muscle strength, and improving muscle endurance",
                "Maintaining joint and connective tissue mobility",
                "Minimizing the formation of joint contractures",
                "Assisting lymphatic and blood synovial circulation"
            ),
            correctAnswer = "Preventing muscle atrophy, increasing muscle strength, and improving muscle endurance",
            whyCorrect = "Because PROM requires zero active muscular contraction, it cannot cause myofibrillar hypertrophy, recruitment modifications, or cardiovascular adaptations required to build strength, endurance, or prevent disuse atrophy.",
            whyIncorrect = "PROM does maintain fluid lubrication, capsule stretch, and venous return, thus mitigating contracture formation.",
            clinicalImportance = "Allows the therapist to explain to the patient why they must transition to active exercises as soon as tissue healing permits.",
            examinerTip = "Never expect PROM to increase active grade on the motor scales; active voluntary contraction is mandatory.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Muscle contraction is an absolute requirement to prevent muscle atrophy and build muscle force.",
            relatedConcept = "Limitations of PROM",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the primary therapeutic rationale for deploying a Continuous Passive Motion (CPM) machine in the early postoperative phase of a Total Knee Arthroplasty (TKA)?",
            options = listOf(
                "To prevent intra-articular adhesions, enhance cartilage healing, and decrease postoperative pain",
                "To increase quadriceps and hamstring strength to prepare for early ambulation",
                "To replace the need for active bed mobility exercises and stretching entirely",
                "To force joint range beyond structural limits of tissue healing"
            ),
            correctAnswer = "To prevent intra-articular adhesions, enhance cartilage healing, and decrease postoperative pain",
            whyCorrect = "CPM machines provide continuous slow passive mobilization. This movement prevents synovial stagnation, preserves joint play, blocks pain signals via mechanoreceptor stimulation, and prevents cross-linking of early collagen fibers.",
            whyIncorrect = "CPM cannot build active muscle strength, nor does it replace the functional need for active functional ambulation training.",
            clinicalImportance = "Helps patients gain early knee flexion angles, preventing arthrofibrosis and stiffness in the knee.",
            examinerTip = "Recall that CPM improves joint nutrition by circulating synovial fluid over the articular cartilage.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "CPM provides gentle, repetitive passive ROM to prevent adhesions and improve nourishment of healing joint structures.",
            relatedConcept = "Continuous Passive Motion",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "In a patient recovering from an acute joint injury with significant inflammation, why must passive ROM be kept within a pain-free range?",
            options = listOf(
                "Aggressive mobilization beyond the pain threshold can exacerbate inflammation, tissue microtrauma, and trigger protective muscle guarding",
                "It might cause immediate disuse atrophy of the surrounding joint stabilizers",
                "It results in excessive deposition of type I collagen in parallel sheets",
                "It lowers the patient's resting metabolic rate and compromises tissue healing"
            ),
            correctAnswer = "Aggressive mobilization beyond the pain threshold can exacerbate inflammation, tissue microtrauma, and trigger protective muscle guarding",
            whyCorrect = "In the acute inflammatory stage, tissues are structurally fragile. Mobilization beyond safe limits inflicts further microtrauma, releasing more inflammatory mediators and activating nociceptors, which triggers involuntary muscle guarding (spasm).",
            whyIncorrect = "PROM within or beyond pain limits doesn't cause immediate structural atrophy. Stretching does deposit collagen, but excessive trauma causes disorganized scar formation.",
            clinicalImportance = "Enforces safe physical therapy guidelines that prevent secondary inflammatory flares and joint damage.",
            examinerTip = "Acute joints require gentle, pain-free ROM. Never force movement during the active inflammatory phase.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Inflamed joints require gentle, pain-free ROM to support fluid circulation without disrupting delicate cellular healing.",
            relatedConcept = "Inflammation and Joint Mobilization",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "If a physical therapist encounters a firm, springy sensation at the end of external lateral rotation of the shoulder before reaching normal full range, how is this end-feel interpreted?",
            options = listOf(
                "Abnormal, indicating capsular or ligamentous shortening/tightness",
                "Normal, indicating a bone-to-bone block of the humeral head",
                "Abnormal, indicating acute intra-articular fluid effusion",
                "Normal, indicating typical muscle-to-muscle approximation"
            ),
            correctAnswer = "Abnormal, indicating capsular or ligamentous shortening/tightness",
            whyCorrect = "A capsular or firm end-feel that occurs early in the expected ROM indicates shortening of the joint capsule or surrounding ligaments. A springy block represents intra-articular derangement (like meniscus or cartilage teardowns).",
            whyIncorrect = "Hard (bone-to-bone) is normal only in elbow extension. Soft tissue approximation is normal in knee flexion. Effusion yields a boggy/empty end-feel.",
            clinicalImportance = "Guides the therapist to target the articular capsule with joint mobilizations rather than focusing solely on muscle stretching.",
            examinerTip = "Learn to distinguish normal (bony, capsular, soft tissue) from abnormal (spasm, empty, springy) end-feels.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "An early firm end-feel indicates capsular or ligamentous tightness limiting physiological range.",
            relatedConcept = "End-Feel Assessment",
            reference = "Magee's Orthopedic Physical Assessment"
        ),

        // === STRETCHING (28-33) ===
        VivaMcq(
            question = "What is the primary structural distinction between adaptive shortening (tightness) and a true contracture?",
            options = listOf(
                "Tightness is mild loss of range responsive to stretching; contracture is chronic shortening of connective tissue resistant to manual stretch",
                "Tightness occurs in joints; contracture occurs only in skeletal muscles",
                "Tightness involves hypermobility; contracture involves hypomobility",
                "Tightness is driven by cartilage degradation; contracture is driven by synovial dry-up"
            ),
            correctAnswer = "Tightness is mild loss of range responsive to stretching; contracture is chronic shortening of connective tissue resistant to manual stretch",
            whyCorrect = "Adaptive shortening or muscle tightness is transient loss of extinsibility. Contractures represent significant, chronic structural shortening of the joint capsule, myofascial, or skin tissue which is highly resistant to traditional brief stretching.",
            whyIncorrect = "Both affect mobility. Tightness does not involve hypermobility. Neither is purely cartilage-driven in early stages.",
            clinicalImportance = "Helps set realistic treatment durations: tightness resolves in weeks, whereas contractures may require prolonged splinting, casting, or surgical release.",
            examinerTip = "Contractures are named by the action of the shortened muscle (e.g., flexion contracture means the joint cannot extend).",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Contractures represent structurally fixed shortening of soft tissue, while tightness is easily reversible.",
            relatedConcept = "Soft Tissue Shortening",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "How does the biomechanical principle of 'creep' explain a tissue's response to a sustained, low-load stretch?",
            options = listOf(
                "The tissue gradually deforms/elongates over time when subjected to a constant, continuous load",
                "The force required to maintain a constant elongated state gradually decreases over time",
                "The plastic limit of collagen fibers is lowered immediately with hot packs",
                "The muscle spindle becomes desensitized to high velocity stretching maneuvers"
            ),
            correctAnswer = "The tissue gradually deforms/elongates over time when subjected to a constant, continuous load",
            whyCorrect = "Creep is a viscoelastic property. When a constant load is applied over an extended period, the tissue progressively deforms (lengthens) further due to the slow sliding of collagen fibrils and water displacement.",
            whyIncorrect = "The drop in force required to maintain a set length is termed 'stress relaxation'. Creep is length change over time under constant force.",
            clinicalImportance = "Explains why long-duration low-load splinting (like serial casting) is superior to short, high-force manual stretches for contracture resolution.",
            examinerTip = "Differentiate 'creep' (constant force -> length changes) from 'stress relaxation' (constant length -> force decreases).",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Creep details how viscoelastic tissues gradually elongate when held under a constant tension over time.",
            relatedConcept = "Viscoelastic Properties of Tissue",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "During a rapid ballistic stretch, which sensory receptor is excited, causing a protective contraction in the target muscle?",
            options = listOf(
                "The primary endings of the Muscle Spindle, which are highly sensitive to velocity and length changes",
                "The Golgi Tendon Organ, which is sensitive to mechanical tension changes",
                "The Ruffini endings, which detect high-frequency joint vibrations",
                "The Pacinian corpuscles, which respond to local compressive forces"
            ),
            correctAnswer = "The primary endings of the Muscle Spindle, which are highly sensitive to velocity and length changes",
            whyCorrect = "The muscle spindle contains dynamic Ia fibers (primary endings) that monitor speed of length changes. A rapid ballistic stretch triggers a strong monosynaptic reflex, causing the target muscle to contract, raising injury risk.",
            whyIncorrect = "GTO stimulation inhibits muscle contraction (autogenic inhibition). Ruffini and Pacinian are joint cutaneous/pressure capsular receptors.",
            clinicalImportance = "Highlights why static or progressive slow stretches are safer than ballistic stretches in tissue elongation programs.",
            examinerTip = "Recall that muscle spindles have dynamic sensory loops that monitor velocity, triggering the stretch reflex.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Sudden, rapid stretches stimulate the stretch reflex via muscle spindle fibers, raising tear risks.",
            relatedConcept = "Neurophysiological Mechanisms of Stretching",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "Which stretching technique is most appropriate to perform in a warm-up routine for an athlete to optimize power output, and why?",
            options = listOf(
                "Dynamic stretching, as it increases tissue temperature and prepares motor pathways without dampening muscle excitability",
                "Static stretching, as it maximizes muscle stiffness and elasticity",
                "Ballistic stretching, as it guarantees a safe resetting of the patellar reflex loop",
                "Passive static stretching held for 120 seconds to desubstantiate Golgi tendon outputs"
            ),
            correctAnswer = "Dynamic stretching, as it increases tissue temperature and prepares motor pathways without dampening muscle excitability",
            whyCorrect = "Dynamic warmups involve active, controlled joint mobility resembling sport movements. It elevates core temperature and neural drive without the neuromuscular depressive effects of long-duration static stretches.",
            whyIncorrect = "Static stretching (>45s) prior to explosive tasks can decrease peak force production (the stretch-induced strength deficit). Ballistic is hard to control and can provoke sprains.",
            clinicalImportance = "Enbalances injury reduction and athletic performance maximization in sports injury management.",
            examinerTip = "Dynamic stretching is preferred for pre-event warming, while static stretching is prioritized for post-event extensibility gains.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Dynamic stretching warms tissues and activates motor units without causing sensory-deficit force drops.",
            relatedConcept = "Warm-up Protocols",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "Which combination of stretching parameters is universally recommended to optimize tissue elongation safely in clinical rehabilitation?",
            options = listOf(
                "Low-intensity, long-duration static stretch",
                "High-intensity, ultra-short duration ballistic stretch",
                "High-intensity, high-frequency passive stretch beyond the pain threshold",
                "Low-intensity, intermittent high-velocity oscillatory stretches"
            ),
            correctAnswer = "Low-intensity, long-duration static stretch",
            whyCorrect = "Low-intensity (mild discomfort, not pain) combined with long duration (e.g., 30-60 seconds) achieves elastic-to-plastic transition safely. It minimizes muscle spindle activation and tissue tearing while facilitating creep.",
            whyIncorrect = "High intensity or high velocity maneuvers risk microscopic tissue tearing, joint capsule sprains, and spark protective muscle guarding.",
            clinicalImportance = "Formulates the standard prescription for tight hamstrings, calf, or pectorals in orthopedic clinics.",
            examinerTip = "For healthy adults, holding a static stretch for 30 seconds, repeated 2-4 times, is the clinical gold standard.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Low-intensity, long-duration stretching is the safest and most permanent way to elongate dense tissues.",
            relatedConcept = "Determinants of Stretching",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "To achieve permanent lengthening of tight connective tissue during therapeutic stretching, what state must the soft tissue reach on the stress-strain curve?",
            options = listOf(
                "The plastic region, where micro-failure of chemical bonds results in permanent reorganization",
                "The elastic region, where the tissue returns to its original length immediately after load removal",
                "The toe region, where the crimp in collagen fibers is initially straightened out",
                "The ultimate failure point, where macroscopic tearing of the main tendon belly occurs"
            ),
            correctAnswer = "The plastic region, where micro-failure of chemical bonds results in permanent reorganization",
            whyCorrect = "Plastic deformation occurs when the stress is sufficient to disrupt intermolecular cross-links of collagen. This results in permanent fiber elongation without macroscopic structural rupture.",
            whyIncorrect = "Elastic deformation is fully reversible. Toe region is normal, easy movement. Ultimate failure point represents ligament sprain or muscle tear.",
            clinicalImportance = "Informs clinicians that plastic change requires persistent slow tension rather than momentary elastic pulls.",
            examinerTip = "To get into the plastic region safely, you need prolonged low stress (thermal modality helps shift the curve).",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Connective tissues must reach the plastic region to undergo permanent, structural length adaptation.",
            relatedConcept = "Stress-Strain Relationship",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),

        // === PNF (34-39) ===
        VivaMcq(
            question = "What is the primary underlying neurophysiological mechanism targeted by Proprioceptive Neuromuscular Facilitation (PNF) techniques?",
            options = listOf(
                "The neuromuscular system, utilizing proprioceptive feedback to facilitate or inhibit motor unit activity",
                "The local vascular system, to increase muscle perfusion during concentric patterns",
                "The autonomic nervous system, to downregulate sympathetic firing via isometric holding",
                "The vestibular system, to coordinate multiaxial trunk translations"
            ),
            correctAnswer = "The neuromuscular system, utilizing proprioceptive feedback to facilitate or inhibit motor unit activity",
            whyCorrect = "PNF leverages cutaneous, joint, and muscle receptors (spindles and GTOs) to change alpha motor neuron excitation. It either enhances recruitment (faciliatory) or suppresses tone (inhibitory).",
            whyIncorrect = "While PNF alters blood flow or postural balance, its primary framework is neuromuscular reflex modulation.",
            clinicalImportance = "Provides immediate manual control of muscular tone and functional movement patterns in hemiplegic and orthopedic patients.",
            examinerTip = "Proprioceptive feedback in PNF is enhanced using manual contact, stretch reflexes, visual cues, and traction.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "PNF uses sensory-motor pathways to manipulate muscular recruitment and reflex activity.",
            relatedConcept = "Neurophysiological Treatment Concepts",
            reference = "Adler, Beckers, & Buck - PNF in Practice, 5th Ed"
        ),
        VivaMcq(
            question = "In PNF therapy, why are movements designed in spiral and diagonal patterns?",
            options = listOf(
                "Because natural functional human movements are multi-planar, rotational, and diagonal in nature",
                "Because single-plane movements create joint friction and increase cartilage wear",
                "To prevent activation of the mechanical Golgi tendon organs during passive therapy",
                "To replicate simple isolation pathways of individual skeletal muscles"
            ),
            correctAnswer = "Because natural functional human movements are multi-planar, rotational, and diagonal in nature",
            whyCorrect = "PNF diagonals duplicate real world motor functions (e.g., throwing, feeding, walking) which require muscles to contract in spiral (rotational) and diagonal trajectories across three planes.",
            whyIncorrect = "It is not about reducing cartilage friction, avoiding GTOs, or isolating a single muscle; PNF is a synergistic, multi-muscle holistic system.",
            clinicalImportance = "Helps stroke survivors re-learn normal motor coordination patterns for functional tasks like self-feeding or walking.",
            examinerTip = "The two major diagonal patterns are D1 and D2, designated for both upper and lower extremities.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "PNF diagonal patterns closely match our functional, multi-joint movement synergies.",
            relatedConcept = "PNF Diagonal Patterns",
            reference = "Adler, Beckers, & Buck - PNF in Practice, 5th Ed"
        ),
        VivaMcq(
            question = "What distinguishes the 'Contract-Relax' from the 'Hold-Relax' PNF stretching technique?",
            options = listOf(
                "Hold-Relax utilizes an isometric contraction of the tight muscle; Contract-Relax allows a concentric rotational component of the tight muscle",
                "Hold-Relax is purely a passive procedure; Contract-Relax is a maximal resisted active pattern",
                "Hold-Relax targets the antagonist muscle; Contract-Relax targets the agonist muscle group exclusively",
                "Hold-Relax increases tone; Contract-Relax reduces range of motion"
            ),
            correctAnswer = "Hold-Relax utilizes an isometric contraction of the tight muscle; Contract-Relax allows a concentric rotational component of the tight muscle",
            whyCorrect = "In 'Hold-Relax', the patient performs an isometric contraction against resistance (no movement allowed). In 'Contract-Relax', the patient concentrically contracts the tight rotator muscles through a limited rotational arc.",
            whyIncorrect = "Both techniques are active-assisted stretching patterns utilizing autogenic inhibition. Neither is completely passive.",
            clinicalImportance = "Hold-Relax is preferred if the patient has acute joint pain, as no movement is permitted during the contraction phase.",
            examinerTip = "Be clear that 'Hold' indicates isometric, while 'Contract' under PNF guidelines permits minor rotation.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Hold-Relax uses isometric contractions, whereas Contract-Relax allows slight concentric rotation.",
            relatedConcept = "PNF Stretching Techniques",
            reference = "Adler, Beckers, & Buck - PNF in Practice, 5th Ed"
        ),
        VivaMcq(
            question = "Which neurophysiological principle explains why a target muscle relaxes following a strong, voluntary isometric contraction during PNF?",
            options = listOf(
                "Autogenic inhibition, where Golgi tendon organs fire in response to tension, inhibiting the same muscle's alpha motor neurons",
                "Reciprocal inhibition, which requires contraction of the antagonist muscle to relax the agonist",
                "The stretch reflex, which increases muscle tone in response to quick elongation",
                "Spinal cord facilitation, which enhances nociceptive pathways to allow greater joint range"
            ),
            correctAnswer = "Autogenic inhibition, where Golgi tendon organs fire in response to tension, inhibiting the same muscle's alpha motor neurons",
            whyCorrect = "Autogenic inhibition occurs when GTOs sense a surge in muscle tension (from an isometric contraction). They send inhibitory impulses via Ib afferents to inhibit the alpha motor neurons of that same muscle, causing relaxation.",
            whyIncorrect = "Reciprocal inhibition requires contraction of the *opposing* muscle group. Stretch reflex is excitatory. Facilitation increases motor output.",
            clinicalImportance = "Allows physical therapists to immediately gain joint range by having the client contract the tight muscle before stretching it.",
            examinerTip = "Think: 'Autogenic' means same-muscle inhibition; 'Reciprocal' means opposite-muscle inhibition.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Isometric contraction triggers autogenic inhibition via Golgi Tendon Organs to relax the target muscle.",
            relatedConcept = "Neurophysiological Mechanisms",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "For a patient with Parkinson's disease experiencing bradykinesia and difficulty starting movements, which PNF technique is most appropriate?",
            options = listOf(
                "Rhythmic Initiation, which progresses from passive, to active-assisted, then to active-resisted movement",
                "Slow Reversals, which require rapid alternating agonist-antagonist contractions",
                "Hold-Relax Active Movement, focusing purely on end-range stretching",
                "Maximal isometric stabilization in shortened positions"
            ),
            correctAnswer = "Rhythmic Initiation, which progresses from passive, to active-assisted, then to active-resisted movement",
            whyCorrect = "Rhythmic Initiation starts with passive movement to relax and establish patterns, moves to active-assisted to encourage voluntary contraction, and progresses to active-resisted to build force. This helps overcome akinesia.",
            whyIncorrect = "Alternating reversals or isometric stabilization are too advanced for a rigid patient having trouble starting any movement.",
            clinicalImportance = "Helps patients with basal ganglia dysfunction overcome physical rigidity and initiate walking.",
            examinerTip = "Rhythmic Initiation is the prime PNF tool for teaching initiation of movement and motor control.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Rhythmic Initiation progresses from passive to active-resisted to bypass Parkinsonian motor blocks.",
            relatedConcept = "Motor Control Facilitation",
            reference = "Adler, Beckers, & Buck - PNF in Practice, 5th Ed"
        ),
        VivaMcq(
            question = "When treating a patient with hypermobile shoulder instability, which PNF technique is used to improve co-contraction and stabilizer strength?",
            options = listOf(
                "Rhythmic Stabilization, where a multi-directional rotary manual resistance is applied to isometric holding",
                "Repeated contractions immediately at tissue limits",
                "Hold-Relax with concentric antagonist contractions",
                "Agonistic reversals throughout the physiological mid-range"
            ),
            correctAnswer = "Rhythmic Stabilization, where a multi-directional rotary manual resistance is applied to isometric holding",
            whyCorrect = "Rhythmic Stabilization involves applying rotational, alternating resistance to agonists and antagonists about a joint. This forces static co-contraction of muscles, enhancing joint stability.",
            whyIncorrect = "Repeated contractions provoke fatigue-induced laxity. Hold-Relax is an extensibility drill. Agonistic reversals train dynamic concentric-eccentric control.",
            clinicalImportance = "Essential for treating multi-directional shoulder laxity or scapula instability.",
            examinerTip = "Remember Rhythmic Stabilization targets co-contraction and stability through isometric rotary resistance.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Rhythmic Stabilization challenges postural muscles to co-contract, securing joint positions.",
            relatedConcept = "Joint Stabilization",
            reference = "Adler, Beckers, & Buck - PNF in Practice, 5th Ed"
        ),

        // === MOBILITY EXERCISE (40-44) ===
        VivaMcq(
            question = "What is the main physiological distinction between joint mobility (arthrokinematics) and muscle flexibility?",
            options = listOf(
                "Mobility refers to joint arthrokinematics (slide, roll, spin); flexibility refers to the ability of a muscle to lengthen through ROM",
                "Mobility is a passive property; flexibility is purely active muscle contraction",
                "Mobility is measured in centimeters; flexibility is measured in degrees",
                "Mobility involves superficial skin tissue; flexibility involves deep joint capsules"
            ),
            correctAnswer = "Mobility refers to joint arthrokinematics (slide, roll, spin); flexibility refers to the ability of a muscle to lengthen through ROM",
            whyCorrect = "Mobility governs the internal mechanics of a joint (accessory glides, roll, spin). Flexibility represents the mechanical ability of muscle-tendon units to elongate through physiological range of motion.",
            whyIncorrect = "Flexibility is passive muscle extensibility, not active. Both are measured in degrees/cm. Mobility concerns the capsule/articular surfaces.",
            clinicalImportance = "Allows therapists to treat joint tightness with joint glides, whereas muscle tightness requires muscle stretching.",
            examinerTip = "Always test arthrokinematics first; if joint glides are restricted, stretching muscles will not restore full range.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Joint mobility regulates accessory glides, whereas flexibility regulates muscle-tendon length.",
            relatedConcept = "Arthrokinematics vs Osteokinematics",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "When a direct limitation in joint arthrokinematic slide is identified, which intervention is most effective to restore joint mobility?",
            options = listOf(
                "Joint mobilization/glide techniques targeting the joint capsule, rather than traditional muscle stretching",
                "Passive anatomical stretching of the antagonist muscle group for 3 sets of 30 seconds",
                "High-frequency plyometric drills to increase muscle contractility",
                "Applying a hot pack followed by static traction in the anatomical position"
            ),
            correctAnswer = "Joint mobilization/glide techniques targeting the joint capsule, rather than traditional muscle stretching",
            whyCorrect = "Capsular restrictions impair the internal slide. Passive stretching only puts tension on muscular fibers without resetting joint glide, which can pinch articular structures. Focused joint mobilization (gliding) elongates the capsular fibers.",
            whyIncorrect = "Stretching, plyometrics, or generic heat do not address the specific directional restriction of the joint capsule.",
            clinicalImportance = "Critical for recovery of shoulder abduction following adhesive capsulitis, where the inferior capsule is tight.",
            examinerTip = "Apply joint glides parallel to the treatment plane to restore accessory slide motion.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Joint glides restore the accessory slide mechanics required for pain-free physiological motion.",
            relatedConcept = "Joint Mobilization Concepts",
            reference = "Maitland's Peripheral Mobilisation, 5th Ed"
        ),
        VivaMcq(
            question = "Under Maitland's joint mobilization grading scale, what defines a Grade II mobilization?",
            options = listOf(
                "Large-amplitude oscillations within the mid-range of joint play, free from resistive tissue barriers",
                "Small-amplitude oscillations at the beginning of the available joint range",
                "Large-amplitude oscillations that push into the tissue resistance barrier",
                "Small-amplitude high-velocity thrust at the end of joint range"
            ),
            correctAnswer = "Large-amplitude oscillations within the mid-range of joint play, free from resistive tissue barriers",
            whyCorrect = "Maitland Grade II consists of large-amplitude, slow oscillatory movements that do not reach the limits of joint range or touch structural tissue resistance.",
            whyIncorrect = "Small-amplitude at start is Grade I. Large-amplitude pushing into barrier is Grade III. Small-amplitude high velocity thrust is Grade V (manipulation).",
            clinicalImportance = "Grade II mobilizations are highly effective for pain control and systemic fluid movement in painful, sensitive joints.",
            examinerTip = "Be ready to chart Maitland scales (I to IV). I and II are for pain; III and IV are for stiffness.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Grade II oscillations are large-amplitude movements kept short of any tissue resistance.",
            relatedConcept = "Maitland Mobilization Grades",
            reference = "Maitland's Peripheral Mobilisation, 5th Ed"
        ),
        VivaMcq(
            question = "For a patient presenting with an acute shoulder sprain and high pain levels, which Maitland mobilization grades are indicated?",
            options = listOf(
                "Grades I and II, as they stimulate mechanoreceptors to block pain pathways without stretching tissues",
                "Grades III and IV, to mechanically stretch the tight shoulder capsule",
                "Grade V, to physically rupture early adhesions",
                "No mobilization, because any joint input is contraindicated in acute pain"
            ),
            correctAnswer = "Grades I and II, as they stimulate mechanoreceptors to block pain pathways without stretching tissues",
            whyCorrect = "Grades I and II are low-force maneuvers that do not engage tissue limits. They stimulate type I and II joint mechanoreceptors, which neurologically inhibits nociceptive transmission (pain gating) and moves synovial fluid.",
            whyIncorrect = "Grades III, IV, and V involve physical stress or rapid force that will irritate a highly reactive, acute joint injury.",
            clinicalImportance = "Allows early pain control in the clinic without utilizing oral analgesics.",
            examinerTip = "Grade I and II are indicated primarily for pain and spasm; Grade III and IV for stretching mechanical tissue resistance.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Grade I & II mobilizations reduce pain through spinal mechanoreceptor pain-gating pathways.",
            relatedConcept = "Neurophysiological Effects of Mobilization",
            reference = "Maitland's Peripheral Mobilisation, 5th Ed"
        ),
        VivaMcq(
            question = "According to the convex-concave rule of arthrokinematics, if a convex joint surface is moving on a stable concave surface, how do the roll and slide occur?",
            options = listOf(
                "The roll and slide occur in opposite directions",
                "The roll and slide occur in the same direction",
                "The joint spins only, without roll or slide",
                "The slide occurs anteriorly regardless of the roll trajectory"
            ),
            correctAnswer = "The roll and slide occur in opposite directions",
            whyCorrect = "When a convex surface moves on flat concave bone, the roll of bone occurs in the direction of anatomical bone movement, but the glide (slide) of joint surface must occur in the opposite direction to prevent joint subluxation.",
            whyIncorrect = "Roll and slide occur in the same direction when a *concave* surface moves on a stable convex surface.",
            clinicalImportance = "Guides the direction of glenohumeral mobilization: to improve abduction (superior roll), the therapist must apply an inferior (opposite) glide.",
            examinerTip = "Memorize the Convex-Concave rule: Convex on Concave = Opposite directions; Concave on Convex = Same direction.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Convex-on-concave mobilization glides are executed opposite to the limited physiological movement.",
            relatedConcept = "Convex-Concave Rule",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),

        // === STRENGTHENING PRINCIPLES (45-50) ===
        VivaMcq(
            question = "What is the primary thesis of the 'Overload Principle' in therapeutic strengthening?",
            options = listOf(
                "To increase muscle strength, a load that exceeds the metabolic and physical capacity of the muscle must be applied",
                "A muscle should be exercised with light loads to fatigue to preserve structural tendon integrity",
                "The muscle must be trained through eccentric movements only to prevent joint inflammation",
                "The frequency of strengthening exercises must be repeated daily without rest periods"
            ),
            correctAnswer = "To increase muscle strength, a load that exceeds the metabolic and physical capacity of the muscle must be applied",
            whyCorrect = "The Overload Principle states that if muscle performance is to improve, a load that exceeds the metabolic capacity of the muscle must be applied. This stress forces the muscle to adapt biochemically and structurally.",
            whyIncorrect = "Light loads primarily build endurance. Skipping rest periods or using eccentric-only patterns are not core criteria of overload.",
            clinicalImportance = "Allows physical therapists to scientifically progress exercises by increasing resistance or volume as the patient adapts.",
            examinerTip = "Without overload, a muscle will maintain its current strength but will not increase its force production.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Muscular hypertrophy and neural drive warrant pushing tissue beyond its baseline operational limits.",
            relatedConcept = "Principles of Strength Training",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "How does the SAID principle (Specific Adaptation to Imposed Demands) guide the design of an exercise program for a patient wanting to return to stair climbing?",
            options = listOf(
                "The patient must perform closed-kinetic chain exercises like squats and leg presses to replicate the demands of climbing",
                "The patient should focus entirely on open-chain quadriceps extensions to isolate the knee extensors",
                "The patient must improve upper body pull strength to use the banister",
                "The patient should perform high-repetition isometric contractions at 90 degrees knee extension"
            ),
            correctAnswer = "The patient must perform closed-kinetic chain exercises like squats and leg presses to replicate the demands of climbing",
            whyCorrect = "The SAID principle implies that the body adapts specifically to the type of stress imposed. Stair climbing is a closed-chain, multi-joint activity. Therefore, closed-chain training is highly transferable and functional.",
            whyIncorrect = "Open-chain extensions isolate the muscle but do not train the coordinated biomechanics, balance, or weight-bearing demands of stair usage.",
            clinicalImportance = "Ensures rehabilitation tasks directly improve the specific physical goals of the patient.",
            examinerTip = "SAID highlights that training outcomes are highly specific to velocity, contraction mode, and context.",
            subject = "Exercise Therapy I",
            difficulty = "Easy",
            learningPoint = "Rehabilitation exercises must mirror the task-specific demands of a patient's functional goals.",
            relatedConcept = "SAID Principle",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "During the first 2-3 weeks of a progressive resistance training program, what is the primary source of early strength gains?",
            options = listOf(
                "Neural adaptations (increased motor unit recruitment, synchronization, and firing rate)",
                "Skeletal muscle fiber hypertrophy (increased cross-sectional area)",
                "Hyperplasia of slow-twitch Type I muscle fibers",
                "Increased density of intra-articular collagen bonds"
            ),
            correctAnswer = "Neural adaptations (increased motor unit recruitment, synchronization, and firing rate)",
            whyCorrect = "Skeletal muscle hypertrophy requires weeks to synthesize new contractile proteins. Initial rapid strength gains are neural: the brain learns to recruit more motor units, increase their synchronization, and raise firing frequencies.",
            whyIncorrect = "Hypertrophy becomes dominant only after 4-6 weeks. Hyperplasia (muscle splitting) is not a significant mechanism of human adaptions.",
            clinicalImportance = "Helps encourage patients who don't see immediate muscle size changes that their nervous system is actively strengthening.",
            examinerTip = "Explain that early strength gains are neural, while long-term gains are structural.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Early strength improvements are driven by neural efficiency before structural hypertrophy begins.",
            relatedConcept = "Neural vs Muscular Adaptations",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the defining feature of an 'Isokinetic' strengthening exercise?",
            options = listOf(
                "Movement is performed at a constant velocity, and resistance matches the force applied throughout the entire range",
                "The length of the muscle remains constant throughout the contraction at a set angle",
                "The muscle maintains a constant tension while the speed of movement varies",
                "The resistance is fixed, while the muscle shortens and lengthens dynamically"
            ),
            correctAnswer = "Movement is performed at a constant velocity, and resistance matches the force applied throughout the entire range",
            whyCorrect = "Isokinetic speed is controlled by a specialized electromechanical dynamometer. No matter how much force the patient exerts, the speed of movement remains constant, and the machine provides safe, accommodating resistance.",
            whyIncorrect = "Constant length is isometric. Constant tension under variable speed is classical isotonic (dynamic). Fixed resistance is traditional free weight exercise.",
            clinicalImportance = "Allows safe, maximal muscular training across the full articular range of motion under computer-directed safety limits.",
            examinerTip = "Note that isokinetic exercises allow maximal training because accommodating resistance matches physiological curves.",
            subject = "Exercise Therapy I",
            difficulty = "Moderate",
            learningPoint = "Isokinetic training controls movement velocity, adjusting mechanical resistance to patient output.",
            relatedConcept = "Types of Muscle Contractions",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "According to the Principle of Reversibility, when can detraining of cardiorespiratory and muscular adaptations be expected to begin after stopping regular exercise?",
            options = listOf(
                "Within 1-2 weeks of physical inactivity",
                "Only after 3 months of complete sedentary behavior",
                "Within 48 hours, due to immediate atrophy of Type II fibers",
                "Never, if the user maintains a high protein intake"
            ),
            correctAnswer = "Within 1-2 weeks of physical inactivity",
            whyCorrect = "The principle of reversibility (use it or lose it) shows that anatomical and physiological adaptations of training are transient. Muscular endurance, enzyme levels, and stroke volume begin to drop within 1-2 weeks of inactivity.",
            whyIncorrect = "Three months is too late; significant physiological loss occurs early. Forty-eight hours is too rapid for measurable clinical detraining.",
            clinicalImportance = "Stresses the necessity of home exercise programs (HEPs) and continuous maintenance routines for discharged patients.",
            examinerTip = "Understand that detraining effects are fast, making consistent compliance a core clinical goal.",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "Strength and cardiorespiratory adaptations are transient and degrade within weeks of physical inactivity.",
            relatedConcept = "Reversibility Principle",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the key clinical difference between the DeLorme and the Oxford progressive resistance exercise protocols?",
            options = listOf(
                "DeLorme progresses from low to high intensity (50%, 75%, 100% of 10-RM); Oxford regresses from high to low intensity as muscle fatigues",
                "DeLorme uses eccentric contractions only; Oxford uses isometric contractions only",
                "DeLorme is performed at high speeds; Oxford is designed for low speeds",
                "DeLorme requires expensive machines; Oxford is designed for free weights"
            ),
            correctAnswer = "DeLorme progresses from low to high intensity (50%, 75%, 100% of 10-RM); Oxford regresses from high to low intensity as muscle fatigues",
            whyCorrect = "The DeLorme method builds up intensity as a warm-up (Set 1: 50% 10-RM, Set 2: 75%, Set 3: 100%). The Oxford method starts at 100% and steps down intensity as the muscle fatigues, compensating for metabolic fatigue.",
            whyIncorrect = "Both utilize standard dynamic exercises against resistance, can be done with free weights, and incorporate concentric-eccentric patterns.",
            clinicalImportance = "Allows therapists to choose DeLorme for soft-tissue warming or Oxford to push fatigued muscle adaptations.",
            examinerTip = "Both use 3 sets of 10 repetitions, but they differ in the progression (DeLorme goes up; Oxford goes down).",
            subject = "Exercise Therapy I",
            difficulty = "High Yield",
            learningPoint = "DeLorme increases resistance across sets, while Oxford decreases resistance to match muscular fatigue.",
            relatedConcept = "Progressive Resistance Exercise (PRE)",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        )
    )
}
