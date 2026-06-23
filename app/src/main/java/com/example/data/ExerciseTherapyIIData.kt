package com.example.data

object ExerciseTherapyIIData {
    val questions: List<VivaMcq> = listOf(
        // === SUSPENSION THERAPY (1-7) ===
        VivaMcq(
            question = "What is the primary physical benefit of using axial suspension in suspension therapy compared to vertical suspension?",
            options = listOf(
                "The limb moves parallel to the ground, completely neutralizing gravity and allowing friction-free movement",
                "It raises the center of gravity and increases vertical loading on the joint capsular fibers",
                "It forces dynamic eccentric contraction of the antagonist muscles across vertical planes",
                "It stabilizes the distal bone extremity by placing a high resistance force vector opposite to the movement direction"
            ),
            correctAnswer = "The limb moves parallel to the ground, completely neutralizing gravity and allowing friction-free movement",
            whyCorrect = "In axial suspension, the point of suspension is directly above the center of the joint being moved. This ensures the limb remains parallel to the ground, neutralizing gravity so movement occurs with minimal resistance.",
            whyIncorrect = "Vertical suspension, not axial, supports the heavy limb against gravity but allows pendulum swing movements where the center of gravity rises and falls.",
            clinicalImportance = "Axial suspension is ideal for active-assisted exercises in patients with very low muscle grades (grade 1 or 2) to maintain joint mobility safely.",
            examinerTip = "Axial suspension concentrates force around the axial point of suspension, yielding pure rotational joint motion.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Axial suspension neutralizes gravitational resistance to allow pain-free rotatory range of motion.",
            relatedConcept = "Suspension Therapy Principles",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "In suspension therapy, if the suspension point is placed directly above the joint being moved (axial suspension), what type of movement is produced?",
            options = listOf(
                "Pure rotatory movement within a horizontal plane with gravity neutralized",
                "Linear translatoric joint traction and high longitudinal shear force",
                "A pendular or oscillatory swinging motion with variable mechanical advantage",
                "Dynamic vertical resistance that challenges slow concentric extensor muscle groups"
            ),
            correctAnswer = "Pure rotatory movement within a horizontal plane with gravity neutralized",
            whyCorrect = "With the suspension point directly over the axis of joint rotation, the limb is supported so that it maintains a constant, horizontal distance from the floor, preserving pure rotary motion.",
            whyIncorrect = "A pendular swing requires eccentric suspension. Linear traction is applied by distracting the limb away from the axis. Vertical resistance represents opposing gravity vectors which are negated in axial setups.",
            clinicalImportance = "Enables isolation of joint movement without recruiting synergist muscle groups to hold the limb up.",
            examinerTip = "Always point out that the axis of the joint is collinear with the suspension point in axial suspension.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Axial suspension alignment produces true angular rotation on a single horizontal plane.",
            relatedConcept = "Joint Axis Alignment",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "In double axial suspension, how are the suspension points positioned to optimize joint movement in a multi-link segment?",
            options = listOf(
                "Directly above the centers of each joint involved in the movement, perpendicular to the plane of motion",
                "At a single central point halfway between the distal and proximal segments",
                "Far lateral to the proximal joint to create a traction force vector",
                "Above the patient's head to maximize upper thoracic stabilization"
            ),
            correctAnswer = "Directly above the centers of each joint involved in the movement, perpendicular to the plane of motion",
            whyCorrect = "To facilitate independent and simultaneous movement of multiple hinges (e.g. hip and knee), the cables must be suspended vertically from points directly above each respective joint axis.",
            whyIncorrect = "A single suspension point across two segments causes joint jamming or locking due to conflicting arcs of motion. Distal or proximal offsets change the mechanical angles from vertical.",
            clinicalImportance = "Allows smooth, concurrent lower limb patterns (like flexion/extension) in neurological patients showing pattern weakness.",
            examinerTip = "Double axial setups are necessary when the therapeutic goal is to move more than one joint of a limb concurrently.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Each moving joint requires its own independent axis suspension point in multi-segment suspension.",
            relatedConcept = "Multi-Joint Suspension",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "How does eccentric suspension affect muscle work when performing hip abduction in a suspension layout?",
            options = listOf(
                "It moves the axis of rotation, creating an assisted movement in one direction and resisted in the opposite direction",
                "It leads to bilateral isometric hold of both limbs without dynamic range",
                "It decreases intra-articular pressure, eliminating the active trigger of sensory mechanoreceptors",
                "It limits range of motion completely by mechanically locking the outer pelvic rim"
            ),
            correctAnswer = "It moves the axis of rotation, creating an assisted movement in one direction and resisted in the opposite direction",
            whyCorrect = "In eccentric suspension, the anchor point is moved away from the axis of the joint. When moved toward the side of the motion (assistance), gravity assists the movement as the limb is pulled; moving away (resistance) forces the muscle to work against gravity as the limb is lifted.",
            whyIncorrect = "Eccentric setups never lock the joint; instead, they alter the gravitational vector to create resistance or assistance, requiring dynamic (not isometric) work.",
            clinicalImportance = "Provides a highly controllable graduation of resistive exercise for targeting specific weak patterns before advancing to free weights.",
            examinerTip = "Remember that shifting the suspension point laterally away from the moving limb forces the muscle to work harder against gravity as the limb ascends.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Eccentric suspension alters the gravitational vector to provide either assisted or resisted exercise options.",
            relatedConcept = "Eccentric Suspension Principles",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "When utilizing suspension therapy for shoulder joint mobilization, what is the mechanical role of the 'cleat' in the rope system?",
            options = listOf(
                "To adjust the length of the suspension cord and align the limb's height precisely",
                "To measure the compressive joint distraction force in kilograms",
                "To automatically release the rope if a painful muscle spasm is triggered",
                "To apply high-frequency thermal vibrations to the distal tendon pulley"
            ),
            correctAnswer = "To adjust the length of the suspension cord and align the limb's height precisely",
            whyCorrect = "The cleat is a simple mechanical friction lock that allows the physiotherapist to easily adjust the length of the rope, thereby tuning the height and alignment of the suspended body segment.",
            whyIncorrect = "Cleats are non-electronic manual adjustments. They do not register tensile forces, have no automated relief sensors, and do not vibrate.",
            clinicalImportance = "Ensures correct suspension angles are maintained throughout the clinical session to prevent postural malalignments.",
            examinerTip = "Examiners look for practical knowledge of suspension equipment, including slings, ropes, pulleys, and cleats.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "The rope cleat allows precise manual adjustment of the cable length in suspension frames.",
            relatedConcept = "Suspension Equipment Mechanics",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "What is a primary clinical contraindication for applying total suspension therapy to a patient?",
            options = listOf(
                "Acute localized joint inflammation or unstable fracture site",
                "Chronic muscular weakness secondary to peripheral nerve injury",
                "Spastic hemiparesis in the chronic stage of stroke recovery",
                "Mild lumbar disc herniation with conservative physical management"
            ),
            correctAnswer = "Acute localized joint inflammation or unstable fracture site",
            whyCorrect = "While suspension is gentle, an unstable fracture or acute articular irritation requires rigid stabilization. Uncontrolled angular motion within suspension can displace tissue edges or aggravate severe tissue inflammation.",
            whyIncorrect = "Weakness, chronic stroke hemiparesis, and conservative back pain are typical indications where suspension therapy is highly effective to facilitate early movement.",
            clinicalImportance = "Always evaluate structural and inflammatory status before suspending heavy segments to ensure patient safety.",
            examinerTip = "A broken or acutely inflamed bone segment must never be mobilized or hung on a swinging suspension frame.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Systemic or localized structural instability and acute active inflammation contraindicate suspension mobilization.",
            relatedConcept = "Contraindications of Suspension",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "Which of the following describes the difference in gravity interaction when the suspension point is shifted away from the axis of the joint (eccentric suspension)?",
            options = listOf(
                "It shifts the plane of movement, causing the limb to rise against gravity during abduction or adduction",
                "It maintains the limb perfectly parallel to the floor at all times",
                "It eliminates all mechanical benefits of the support slings",
                "It replaces active muscle work with high-speed reflex oscillations"
            ),
            correctAnswer = "It shifts the plane of movement, causing the limb to rise against gravity during abduction or adduction",
            whyCorrect = "By placing the suspension point eccentric to the joint axis, the arc of the swing is no longer flat. The limb climbs an inclined plane as it moves away from the suspension line, thus introducing gravity-resisted workload.",
            whyIncorrect = "Axial suspension keeps the limb parallel to the floor. Shifting the point does not eliminate sling support, nor does it cause automatic reflex oscillations.",
            clinicalImportance = "Acts as a bridge between active-free (gravity eliminated) and true active-resisted exercise.",
            examinerTip = "In eccentric suspension, moving the point towards the working side assists movement, while moving it away resists it.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Eccentric displacement forces the suspended limb to climb against gravity, acting as a natural resistance vector.",
            relatedConcept = "Gravity-Resisted Suspension",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),

        // === HYDROTHERAPY (8-14) ===
        VivaMcq(
            question = "Which physical property of water is responsible for reducing weight-bearing stress on a patient's joints during hydrotherapy?",
            options = listOf(
                "Buoyancy, which is governed by Archimedes' principle of upward pressure",
                "Hydrostatic pressure, which forces interstitial fluids into central compartments",
                "Viscosity, which creates friction-based resistance against active movement",
                "Specific heat, which allows rapid conduction of thermal energy to deep structures"
            ),
            correctAnswer = "Buoyancy, which is governed by Archimedes' principle of upward pressure",
            whyCorrect = "Archimedes' principle states that a body immersed in a fluid experiences an upward force equal to the weight of the fluid displaced. This upward force (buoyancy) unloads joint compressive forces.",
            whyIncorrect = "Hydrostatic pressure helps reduce edema and increase venous return. Viscosity is the drag force. Specific heat relates to temperature regulation.",
            clinicalImportance = "Enables early gait re-education and rehabilitation in patients with severe osteoarthritic knee pain or restricted weight-bearing status.",
            examinerTip = "Identify buoyancy as the key upward force that works directly opposite to gravity in water.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Buoyancy counteracts gravitational compression, protecting loaded skeletal structures.",
            relatedConcept = "Aquatic Physical Principles",
            reference = "Duffield's Exercise in Water, 3rd Ed"
        ),
        VivaMcq(
            question = "When a patient is immersed in water up to the level of the xiphoid process, approximately what percentage of their body weight is unloaded?",
            options = listOf(
                "Around 60% to 70% of total body weight is unloaded",
                "Only 10% to 20% of total body weight is unloaded",
                "Exactly 95% to 100% of total body weight is unloaded",
                "Weight is not unloaded; instead, joint loading is doubled by the surrounding fluid mass"
            ),
            correctAnswer = "Around 60% to 70% of total body weight is unloaded",
            whyCorrect = "Immersion to the xiphoid process unloads approximately 60-70% of a patient's body weight, reducing joint loading to about 30-40% of standard dry weight.",
            whyIncorrect = "Immersion to the symphysis pubis unloads about 40-50%. Immersion to the neck (C7) unloads about 90% of body weight.",
            clinicalImportance = "Allows specific partial weight-bearing orthopedic progressions based on immersion depth.",
            examinerTip = "Recall the approximate percentages: PSIS/hip ~50%, xiphoid ~70%, vertebrae C7/clavicle ~90%.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Unloading is directly proportional to joint immersion depth.",
            relatedConcept = "Hydrodynamic Weight Unloading",
            reference = "Duffield's Exercise in Water, 3rd Ed"
        ),
        VivaMcq(
            question = "How does hydrostatic pressure affect a patient with chronic venous insufficiency during deep pool hydrotherapy?",
            options = listOf(
                "It exerts inward pressure that assists venous return, reducing peripheral edema",
                "It decreases local arterial perfusion, inducing rapid muscle ischaemia",
                "It causes venous pooling in the distal calf and increases localized swelling",
                "It has no cardiovascular effect because fluid pressure only acts on bony tissues"
            ),
            correctAnswer = "It exerts inward pressure that assists venous return, reducing peripheral edema",
            whyCorrect = "Pascal's Law states that fluid pressure is exerted equally on all surfaces of an immersed body at a given depth. Dynamic pressure increases with depth, squeezing distal extremities to promote venous return and lymphatic drainage.",
            whyIncorrect = "Hydrostatic pressure prevents (rather than causes) venous pooling and helps resolve interstitial fluid accumulation in the ankles.",
            clinicalImportance = "Excellent for managing lower limb edema and assisting circulatory return in stable vascular patients.",
            examinerTip = "Hydrostatic pressure acts perpendicular to all body surfaces and increases linearly with depth.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Pascal's Law explains how water depth drives an external pressure gradient to reduce edema.",
            relatedConcept = "Pascal's Law in Rehabilitation",
            reference = "Duffield's Exercise in Water, 3rd Ed"
        ),
        VivaMcq(
            question = "What is the therapeutic significance of water viscosity, and how can a physical therapist use it to increase resistance during aquatic exercises?",
            options = listOf(
                "Viscosity creates resistance to movement; increasing the speed of movement or the surface area of the limb increases resistance",
                "Viscosity eliminates friction, ensuring the patient's target muscles perform zero active work",
                "Viscosity acts as a downward force vector that pushes the patient's upper body toward the pool floor",
                "Viscosity limits the range of motion by creating thick chemical bonds with cutaneous skin receptors"
            ),
            correctAnswer = "Viscosity creates resistance to movement; increasing the speed of movement or the surface area of the limb increases resistance",
            whyCorrect = "Viscosity is the molecular friction within a fluid that resists movement. Since drag is relative to velocity and frontal surface area, moving faster or using wider paddle paddles increases exercise resistance.",
            whyIncorrect = "Viscosity increases (not eliminates) friction. It provides resistance in all directions of movement, not just downwards.",
            clinicalImportance = "Allows accommodating resistance where patients control the workload intensity purely via their movement speed.",
            examinerTip = "Water resistance increases exponentially as the speed of movement increases.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Viscosity delivers customized, multi-directional resistance proportional to moving speed.",
            relatedConcept = "Fluid Resistance Dynamics",
            reference = "Duffield's Exercise in Water, 3rd Ed"
        ),
        VivaMcq(
            question = "Which hydrotherapy tank is specifically designed to allow localized treatment of the upper or lower extremities using agitating water?",
            options = listOf(
                "Whirlpool bath",
                "Hubbard tank",
                "Therapy pool with mechanical parallel bars",
                "Contrast sitz bath"
            ),
            correctAnswer = "Whirlpool bath",
            whyCorrect = "A whirlpool bath combines warm/cool water with mechanical agitation via a turbine ejector to treat localized limb segments, facilitating clean mechanical debridement and thermal conduction.",
            whyIncorrect = "Hubbard tanks are butterfly-shaped vessels for full-body immersion. Sitz baths target the pelvic and perineal areas. Therapy pools are for large-scale movement and gait retraining.",
            clinicalImportance = "Excellent for wound care, scar tissue softening, and safe non-weight-bearing mobilization of a single hand or foot.",
            examinerTip = "Remember that the turbine in a whirlpool aerates and agitates the water to create tactile stimulation.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Whirlpools combine thermal effects, mechanical agitation, and hydrostatic benefits for single extremities.",
            relatedConcept = "Localized Hydrotherapy",
            reference = "Duffield's Exercise in Water, 3rd Ed"
        ),
        VivaMcq(
            question = "A patient with multiple sclerosis is undergoing aquatic therapy. What is the optimal water temperature to prevent symptom exacerbation (Uhthoff's phenomenon)?",
            options = listOf(
                "Cool water, between 26°C and 28°C (79°F to 82°F)",
                "Warm water, between 36°C and 38°C (97°F to 100°F)",
                "Hot water, between 40°C and 42°C (104°F to 108°F)",
                "Freezing water, between 0°C and 5°C (32°F to 41°F)"
            ),
            correctAnswer = "Cool water, between 26°C and 28°C (79°F to 82°F)",
            whyCorrect = "Patients with MS have heat-sensitive demyelinating fibers. Elevated core body temperatures impair action potential propagation. Cool water prevents fatigue and temporary symptom exacerbation (Uhthoff's phenomenon).",
            whyIncorrect = "Warm or hot water promotes dramatic peripheral vasodilation and raises core temperature, causing extreme fatigue, sensory block, or transient paresis in MS patients.",
            clinicalImportance = "Underlines temperature guidelines for neuropathies where ambient thermal status directly dictates nervous transmission quality.",
            examinerTip = "Always emphasize that cool water (not warm) must be used for MS patients during exercise.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Heat sensitivity in multiple sclerosis requires strictly regulated cool-water therapeutics.",
            relatedConcept = "Uhthoff's Phenomenon Management",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the main cardiorespiratory precaution when immersing a patient with severe congestive heart failure in warm water up to the neck?",
            options = listOf(
                "The hydrostatic pressure increases venous return (preload), which may overload a failing heart",
                "Water temperature causes intense central vasoconstriction, inducing acute systemic hypotension",
                "Buoyancy creates a suction force that restricts normal chest wall expansion during inspiration",
                "It causes a rapid decrease in blood volume due to excessive pulmonary perspiration"
            ),
            correctAnswer = "The hydrostatic pressure increases venous return (preload), which may overload a failing heart",
            whyCorrect = "Immersing the body to the neck forces blood from peripheral veins into the thoracic cavity due to hydrostatic compression. This sudden jump in central blood volume (preload) can precipitate acute pulmonary edema in a failing myocardium.",
            whyIncorrect = "Neck-deep immersion increases thoracic volume demand. It causes shift in blood volume centrally, not dehydration from perspiration.",
            clinicalImportance = "Warns physical therapists about the risks of central volume overload in patients with impaired cardiac reserve during hydrotherapy.",
            examinerTip = "Deep water immersion shifts approximately 700 mL of blood from the lower extremities to the cardiothoracic vessels.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Hydrostatic displacement elevates central venous pressures, risking decompensation in compromised cardiac systems.",
            relatedConcept = "Cardiovascular Effects of Immersion",
            reference = "Duffield's Exercise in Water, 3rd Ed"
        ),

        // === AEROBIC EXERCISE (15-21) ===
        VivaMcq(
            question = "According to the FITT-VP principle, what is the minimal recommended weekly duration of moderate-intensity aerobic exercise for healthy adults?",
            options = listOf(
                "150 minutes per week",
                "30 minutes per week",
                "600 minutes per week",
                "There is no baseline recommendation; any duration below 5 hours is completely ineffective"
            ),
            correctAnswer = "150 minutes per week",
            whyCorrect = "The American College of Sports Medicine (ACSM) recommends at least 150 minutes of moderate-intensity (e.g. 30 minutes, 5 days a week) or 75 minutes of vigorous-intensity aerobic exercise per week for health promotion.",
            whyIncorrect = "30 minutes is insufficient for chronic disease prevention. 600 minutes represents extreme athletic demands rather than baseline wellness guidelines.",
            clinicalImportance = "Forms the foundational prescription target for metabolic, cardiovascular, and pulmonary prevention programs.",
            examinerTip = "FITT-VP stands for Frequency, Intensity, Time, Type, Volume, and Progression.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "150 weekly minutes of moderate aerobic load represents the universal threshold for maintaining health.",
            relatedConcept = "Aerobic Prescription Parameters",
            reference = "ACSM's Guidelines for Exercise Testing and Prescription, 11th Ed"
        ),
        VivaMcq(
            question = "Which of the following is the most reliable objective marker to establish that a patient has reached their maximal aerobic capacity (VO2 max) during a graded exercise test?",
            options = listOf(
                "A plateau in oxygen consumption (VO2) despite a further increase in workload intensity",
                "The patient states they feel tired and want to stop the test (voluntary exhaustion)",
                "The respiratory exchange ratio (RER) drops below 0.70 inside the collecting mask",
                "The heart rate reaches exactly 100 beats per minute"
            ),
            correctAnswer = "A plateau in oxygen consumption (VO2) despite a further increase in workload intensity",
            whyCorrect = "True VO2 max is defined by a plateau in oxygen uptake despite an increase in exercise intensity. Other supporting physiological indices include an RER > 1.10 and blood lactate exceeding 8 mmol/L.",
            whyIncorrect = "Voluntary exhaustion is VO2 peak, not VO2 max, as the patient may stop due to localized muscle pain or lack of motivation. An RER below 0.7 indicates fat metabolism at rest, not peak exertion where RER > 1.1.",
            clinicalImportance = "Enables precise benchmarking of aerobic fitness in cardiorespiratory training.",
            examinerTip = "A physiological plateau is the gold standard criterion distinguishing VO2 max from symptom-limited VO2 peak.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "A plateau in VO2 shows the absolute ceiling of the body's oxidative energy synthesis systems.",
            relatedConcept = "Maximal Oxygen Uptake Assessment",
            reference = "ACSM's Guidelines for Exercise Testing and Prescription, 11th Ed"
        ),
        VivaMcq(
            question = "How does regular aerobic endurance training affect resting stroke volume and resting heart rate over time?",
            options = listOf(
                "It increases resting stroke volume due to left ventricular volume enlargement and decreases resting heart rate due to increased vagal tone",
                "It decreases resting stroke volume and increases resting heart rate to compensate for systemic peripheral resistance",
                "It has no long-term effect on resting parameters; only active parameters are modified",
                "It raises resting heart rate to 120 beats per minute while maintaining high vascular pressure"
            ),
            correctAnswer = "It increases resting stroke volume due to left ventricular volume enlargement and decreases resting heart rate due to increased vagal tone",
            whyCorrect = "Aerobic adaptation leads to eccentric cardiac hypertrophy, expanding ventricular chamber size and compliance. This elevates stroke volume, allowing the heart to beat less frequently (bradycardia) at rest to maintain baseline cardiac output.",
            whyIncorrect = "Healthy aerobic adaptation decreases (not increases) resting heart rate and increases stroke volume, optimizing myocardial efficiency.",
            clinicalImportance = "Improves cardiac efficiency, lowering myocardial oxygen demand, which is highly protective in ischemic heart disease.",
            examinerTip = "An increased parasympathetic (vagal) tone is the main driver behind training-induced bradycardia.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Cardiovascular conditioning yields a larger stroke volume and a highly efficient slow resting pulse.",
            relatedConcept = "Cardiovascular Adaptations to Exercise",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "When utilizing the Karvonen formula to calculate target heart rate, which physiological parameter must be known in addition to age and estimated maximal heart rate?",
            options = listOf(
                "Resting heart rate to determine the Heart Rate Reserve (HRR)",
                "Vital capacity of the lungs in liters",
                "Systolic blood pressure under peak anaerobic stress",
                "Body mass index and total skeletal muscle mass"
            ),
            correctAnswer = "Resting heart rate to determine the Heart Rate Reserve (HRR)",
            whyCorrect = "The Karvonen formula is: Target HR = [ (Max HR - Resting HR) x % Intensity ] + Resting HR. The difference between Max HR and Resting HR defines the Heart Rate Reserve (HRR).",
            whyIncorrect = "Vital capacity, systolic blood pressure, and BMI are not mathematically utilized in the Karvonen formula calculation.",
            clinicalImportance = "Allows therapists to design highly specific, individual-adjusted training zones rather than relying on age-only estimations.",
            examinerTip = "Remember that Heart Rate Reserve (HRR) represents the functional range of heart rate acceleration available for physical exertion.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Karvonen calculation accounts for individual resting heart rate to set accurate cardiovascular intensity.",
            relatedConcept = "Exercise Intensity Prescription",
            reference = "ACSM's Guidelines for Exercise Testing and Prescription, 11th Ed"
        ),
        VivaMcq(
            question = "A patient with type 2 diabetes mellitus is beginning an aerobic exercise program. What is a critical blood glucose level below which exercise should NOT be initiated without a carbohydrate snack?",
            options = listOf(
                "100 mg/dL (5.6 mmol/L)",
                "250 mg/dL (13.9 mmol/L)",
                "350 mg/dL (19.4 mmol/L)",
                "80 mg/dL is safe under all conditions"
            ),
            correctAnswer = "100 mg/dL (5.6 mmol/L)",
            whyCorrect = "If blood glucose is below 100 mg/dL, the risk of developing exercise-induced hypoglycemia is high because muscle contraction stimulates GLUT-4 translocation, which uptakes blood glucose rapidly without insulin.",
            whyIncorrect = "A level of 250-300 mg/dL with ketones is a warning sign of ketosis where exercise is forbidden, but below 100 mg/dL requires immediate carbohydrate intake before exercising.",
            clinicalImportance = "Prevents systemic hypoglycemia, syncope, or seizure during active physical therapy sessions.",
            examinerTip = "Always check pre-exercise blood glucose in diabetic patients to avoid sudden hypoglycemic emergency events.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Skeletal muscle contractions act like insulin, meaning pre-exercise blood glucose must be above 100 mg/dL to prevent crisis.",
            relatedConcept = "Exercise and Diabetes Mellitus",
            reference = "ACSM's Guidelines for Exercise Testing and Prescription, 11th Ed"
        ),
        VivaMcq(
            question = "During moderate-intensity aerobic exercise, which metabolic pathway is the primary source of ATP resynthesis?",
            options = listOf(
                "Aerobic glycolysis and beta-oxidation of fatty acids in the mitochondria",
                "Anaerobic alactic phosphagen pathway (ATP-PCr system)",
                "Anaerobic lactic glycolysis converting glycogen to lactate in the cytoplasm",
                "Substrate-level phosphorylation of creatine phosphate alone"
            ),
            correctAnswer = "Aerobic glycolysis and beta-oxidation of fatty acids in the mitochondria",
            whyCorrect = "Moderate exercise (below the anaerobic threshold) relies on oxygen to synthesize ATP through the Krebs cycle and electron transport chain, utilizing blood glucose, glycogen, and free fatty acids.",
            whyIncorrect = "The ATP-PCr and anaerobic lactic pathways dominate during short-duration, high-intensity explosive bursts (e.g., sprinting or heavy weightlifting).",
            clinicalImportance = "Guides energy system targeting based on the patient's functional objectives (endurance vs power).",
            examinerTip = "Oxidative phosphorylation within mitochondria provides virtually infinite energy at low-to-moderate intensities.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Mitochondrial pathways support prolonged muscular activity by utilizing oxygen.",
            relatedConcept = "Exercise Bioenergetics",
            reference = "Fox's Physiological Basis For Exercise and Sport"
        ),
        VivaMcq(
            question = "What is the primary cardiovascular purpose of the 'cool-down' phase after a vigorous aerobic session?",
            options = listOf(
                "To prevent venous pooling in the lower extremities and maintain adequate cardiac output",
                "To rapidly build lactic acid deposits inside the joint capsule",
                "To stimulate sympathetic nerve output to keep blood pressure elevated",
                "To decrease the body weight by dehydrating active muscle fibers"
            ),
            correctAnswer = "To prevent venous pooling in the lower extremities and maintain adequate cardiac output",
            whyCorrect = "Abruptly stopping active leg movement removes the skeletal muscle pump, which assists venous return. This can cause blood to pool in dilated lower limb vessels, leading to hypotension, dizziness, or syncope.",
            whyIncorrect = "Cool-down clears (rather than deposits) lactic acid and dampens (rather than stimulates) sympathetic drive for a safe return to homeostatic baselines.",
            clinicalImportance = "Protects patients from post-exercise orthostatic hypotension and cardiac arrhythmias.",
            examinerTip = "The muscle pump is critical for returning venous blood up to the right side of the heart after dilation.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Sustained mild movement during cool-down prevents blood pooling via skeletal muscle compression.",
            relatedConcept = "Post-Exercise Recovery Dynamics",
            reference = "ACSM's Guidelines for Exercise Testing and Prescription, 11th Ed"
        ),

        // === BALANCE TRAINING (22-28) ===
        VivaMcq(
            question = "Which sensory systems must cooperate to maintain postural stability, and how does balance training challenge them?",
            options = listOf(
                "Visual, vestibular, and somatosensory systems; training challenges them by altering or removing specific cues",
                "Auditory, olfactory, and gustatory systems; training challenges them with sensory scent cues",
                "Autonomic vagal system and skeletal motor units; training challenges them via reflex breathing holds",
                "Cardiovascular and muscular systems; training challenges them with speed of blood flow"
            ),
            correctAnswer = "Visual, vestibular, and somatosensory systems; training challenges them by altering or removing specific cues",
            whyCorrect = "Balance is regulated by sensory inputs from eyes (visual), inner ear (vestibular), and peripheral joint/muscle receptors (somatosensory). Altering surfaces (foam) or removing vision (closing eyes) forces reliance on remaining systems.",
            whyIncorrect = "Scent, taste, and hearing are secondary or non-contributory to physical balance adjustments.",
            clinicalImportance = "Allows clinicians to target specific balance deficits (e.g. sensory reweighting in sensory neuropathies).",
            examinerTip = "Clinical tests like the CTSIB (mCTSIB) evaluate balance by manipulating these three primary systems.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Triad of balance relies on visual, vestibular, and somatosensory inputs working together.",
            relatedConcept = "Postural Control Systems",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "What is the difference between an 'ankle strategy' and a 'hip strategy' during postural perturbations?",
            options = listOf(
                "Ankle strategy corrects small, slow perturbations on firm surfaces; hip strategy corrects rapid, large perturbations or when standing on narrow surfaces",
                "Ankle strategy is purely voluntary; hip strategy is an involuntary cerebral cortex reflex",
                "Ankle strategy activates proximal muscles first; hip strategy activates distal muscles first",
                "Ankle strategy is only used in children; hip strategy is only used in adults"
            ),
            correctAnswer = "Ankle strategy corrects small, slow perturbations on firm surfaces; hip strategy corrects rapid, large perturbations or when standing on narrow surfaces",
            whyCorrect = "Ankle strategy corrects posture starting from distal to proximal muscles (gastrocnemius first) during minor swaying. Hip strategy initiates from proximal to distal (abdominal/quadriceps first) when the displacement is larger, faster, or the surface is smaller than the foot.",
            whyIncorrect = "Both are automatic postural responses. Ankle strategy recruits muscles distal-to-proximal, whereas hip recruits proximal-to-distal.",
            clinicalImportance = "Informing specific balance rehabilitation targets based on which strategy is impaired or absent.",
            examinerTip = "The stance surface width dictates strategy: a narrow balance beam forces a hip strategy because ankle leverage is ineffective.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Ankle and hip strategies are automated motor reactions graded to the magnitude of postural challenge.",
            relatedConcept = "Postural Correction Strategies",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),
        VivaMcq(
            question = "When training dynamic balance in a patient with a peripheral vestibular disorder, which progression represents the most logical increase in difficulty?",
            options = listOf(
                "Firm surface with eyes open -> foam surface with eyes open -> foam surface with eyes closed",
                "Foam surface with eyes closed -> foam surface with eyes open -> firm surface with eyes open",
                "Standing with wide base -> sitting on a treatment table -> lying down on a gym mat",
                "Weiping balance -> seated tilt board -> static lying posture"
            ),
            correctAnswer = "Firm surface with eyes open -> foam surface with eyes open -> foam surface with eyes closed",
            whyCorrect = "Progression should systematically withdraw reliable sensory inputs. Starting on a stable surface with vision intact, then destabilizing the surface (foam), and finally removing vision (closing eyes) forces maximal reliance on the vestibular system.",
            whyIncorrect = "Starting with foam and closed eyes is too difficult and unsafe. Lying down is a regression, not an advanced balance challenge.",
            clinicalImportance = "Safely graduates postural challenges to enhance compensation in patients with vestibular dysfunction.",
            examinerTip = "Remember that removing vision and standing on foam forces the patient to rely almost entirely on vestibular cues.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Postural challenge increases as sensory cues (visual and somatosensory) are progressively distorted or removed.",
            relatedConcept = "Balance Rehabilitation Progression",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "What does the 'limits of stability' (LOS) define in clinical balance and postural assessment?",
            options = listOf(
                "The maximum angle a person can lean in any direction without losing balance or changing their base of support",
                "The psychological boundary where a patient becomes afraid of falling",
                "The duration a patient can stand on one leg before muscle cramp occurs",
                "The mechanical strength limit of a joint capsule under load"
            ),
            correctAnswer = "The maximum angle a person can lean in any direction without losing balance or changing their base of support",
            whyCorrect = "Limits of stability (LOS) define the perimeter in which a person can intentionally shift their center of mass without stepping, falling, or lifting their feet. Typically, it is about 12 degrees anteroposteriorly and 16 degrees laterally.",
            whyIncorrect = "LOS is a physical balance boundary, not a psychological state, muscular fatigue duration, or connective tissue failure point.",
            clinicalImportance = "Allows therapists to design weight-shifting exercises to increase a patient's safe workspace boundaries.",
            examinerTip = "A patient with poor balance often shows a highly constricted limit of stability, moving conservatively to avoid falls.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "LOS determines the dynamic area inside which balance is maintained without altering the base support.",
            relatedConcept = "Postural Stability Boundaries",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),
        VivaMcq(
            question = "How does standing on a wobble board or BOSU ball challenge the somatosensory system during proprioceptive balance training?",
            options = listOf(
                "It introduces an unstable surface, reducing reliable joint mechanoreceptor feedback and forcing adaptation",
                "It eliminates vestibular fluid movement, isolating ocular stabilization reflexes",
                "It causes voluntary muscular inhibition, bypass-linking the spinal cord level",
                "It doubles the patient's reaction time by increasing peripheral blood pooling"
            ),
            correctAnswer = "It introduces an unstable surface, reducing reliable joint mechanoreceptor feedback and forcing adaptation",
            whyCorrect = "Unstable surfaces change the predictability of foot and ankle proprioception. This forces the central nervous system to re-weight sensory inputs, relying more on vestibular or visual information, and coordinating rapid motor adjustments.",
            whyIncorrect = "Unstable standing increases (not eliminates) vestibular demands and excites spinal reflex pathways to stabilize joints.",
            clinicalImportance = "Essential for restoring ankle stability after chronic ligaments tears or ankle ankle sprains.",
            examinerTip = "An unstable surface challenges and re-trains protective muscular reflexes to prevent rolling or twisting.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Compliant surfaces challenge neuromuscular adjustments by disrupting reliable ground feedback.",
            relatedConcept = "Proprioceptive Perturbation",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "Which clinical test is widely used to evaluate functional mobility, balance, and fall risk specifically in geriatric populations using a timed task?",
            options = listOf(
                "Timed Up and Go (TUG) test",
                "Manual Muscle Testing (MMT) scale",
                "Standard joint goniometric analysis",
                "Active passive goniometry of shoulder rotation"
            ),
            correctAnswer = "Timed Up and Go (TUG) test",
            whyCorrect = "The TUG test requires a patient to stand up from a chair, walk 3 meters, turn, walk back, and sit down. This integrates balance, speed, and functional dynamic mobility, and scoring over 12-13.5 seconds suggests high risk for falls.",
            whyIncorrect = "MMT measures isolated muscle strength. Goniometry measures absolute joint range of motion. Neither is a timed balance screen.",
            clinicalImportance = "A fast, inexpensive tool to identify older adults at risk for falling and track rehabilitation progress.",
            examinerTip = "The TUG is highly functional because it tests transfers, straight-line walking, and directional changes.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "The TUG test assesses multiple dynamic balance variables in a simple functional envelope.",
            relatedConcept = "Geriatric Fall Screenings",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "In balance rehabilitation, what is the 'stepping strategy' used for?",
            options = listOf(
                "To prevent a fall when a perturbation is so large that the center of gravity is displaced beyond the limits of stability",
                "To maintain rigid posture without any active movement of the ankle joints",
                "To elevate metabolic demand to stimulate type II muscle fiber hypertrophy",
                "To bypass the brainstem level and automate isolated spinal extension"
            ),
            correctAnswer = "To prevent a fall when a perturbation is so large that the center of gravity is displaced beyond the limits of stability",
            whyCorrect = "If a perturbation shifts the center of gravity past the base limits of support, ankle and hip strategies fail. The stepping strategy involves taking a step to establish a new, larger base of support to capture the falling center of gravity.",
            whyIncorrect = "The stepping strategy is dynamic, involving large adjustments rather than a rigid posture, and serves to protect equilibrium rather than target single muscle structures.",
            clinicalImportance = "Crucial for fall avoidance training in elderly patients with slowed motor planning.",
            examinerTip = "Stepping strategy represents the ultimate boundary of active postural safety before a complete fall occurs.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Stepping creates a new base of support to contain a displaced center of mass.",
            relatedConcept = "Postural Recovery Strategies",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),

        // === COORDINATION EXERCISE (29-35) ===
        VivaMcq(
            question = "What is the primary neurophysiological objective of performing Frenkel's Exercises in patients with cerebellar ataxia?",
            options = listOf(
                "To promote voluntary motor control and coordination through the repetitive use of visual and auditory feedback",
                "To maximize hypertrophy and strength of the core postural muscles",
                "To stimulate autonomic reflexes through localized articular heat",
                "To perform high-speed passive movements to lower motor neuron tone"
            ),
            correctAnswer = "To promote voluntary motor control and coordination through the repetitive use of visual and auditory feedback",
            whyCorrect = "Frenkel's Exercises are a sequence of slow, precise, repetitive movements designed for sensory ataxia. Because proprioceptive pathways are damaged, patients rely heavily on vision and therapist instructions to control movement accuracy.",
            whyIncorrect = "Frenkel's does not aim to build muscle strength or power, nor does it involve passive or autonomic reflex loops.",
            clinicalImportance = "Highly effective for restoring balance and coordinates during daily activities in patients with Tabes Dorsalis or cerebellar lesions.",
            examinerTip = "Remember that Frenkel's Exercises require close mental concentration and are guided by visual feedback.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Frenkel's exercises leverage visual guidance to compensate for proprioceptive deficits.",
            relatedConcept = "Frenkel's Coordination System",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "Which of the following describes the correct progression of positioning when administering Frenkel's Exercises?",
            options = listOf(
                "Lying down -> Sitting -> Standing -> Walking",
                "Walking -> Standing -> Sitting -> Lying down",
                "Standing -> Lying down -> Walking -> Sitting",
                "Frenkel's Exercises are only performed in a standing frame under body weight support"
            ),
            correctAnswer = "Lying down -> Sitting -> Standing -> Walking",
            whyCorrect = "The motor re-education progression should always go from a position of greatest support and lowest center of gravity to one of least support and high center of gravity: lying down, sitting, standing, and finally walking.",
            whyIncorrect = "Starting with walking is extremely unstable and unsafe for an ataxic patient. Progression should build stability systematically.",
            clinicalImportance = "Ensures safe, progressive neurorehabilitation that maximizes confidence and motor learning.",
            examinerTip = "Progression is based on increasing the physical difficulty of postural stabilization while executing coordinating movements.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Neurorehabilitation progression patterns systematically reduce external physical support.",
            relatedConcept = "Nervous Coordination Progression",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "Frenkel's Exercises require high mental concentration. What is the key clinical guideline to prevent cognitive and physical fatigue in these patients?",
            options = listOf(
                "Keep sessions short, avoid heavy physical resistance, and provide frequent rest periods between exercises",
                "Force the patient to perform exercises at high speeds to override cognitive pauses",
                "Use heavy ankle weights to stabilize the limbs against cerebellar intention tremors",
                "Perform all exercises in a completely dark room to block secondary distractions"
            ),
            correctAnswer = "Keep sessions short, avoid heavy physical resistance, and provide frequent rest periods between exercises",
            whyCorrect = "Frenkel's exercises depend on cortical concentration to override damaged pathways. This is mentally exhausting. Rest intervals block fatigue, which would otherwise degrade motor coordination quality.",
            whyIncorrect = "High speed increases tremors. Weighting may dampen tremors but defeats the fine coordinate learning goal. Dark rooms block the visual compensation vital for these exercises.",
            clinicalImportance = "Prevents neurological exhaustion and preserves high precision and quality of execution.",
            examinerTip = "Frenkel's exercises emphasize coordination and precision of movement, NOT strength or endurance.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Mental focus requires short, low-resistance, high-precision training intervals to avoid motor decay.",
            relatedConcept = "Ataxia Rehabilitation Guidelines",
            reference = "Gardiner's Principles of Exercise Therapy, 4th Ed"
        ),
        VivaMcq(
            question = "Which brain structure is primarily responsible for motor planning, timing, error correction, and motor coordination?",
            options = listOf(
                "Cerebellum",
                "Medulla oblongata",
                "Broca's area",
                "Spinal cord anterior horn cells"
            ),
            correctAnswer = "Cerebellum",
            whyCorrect = "The cerebellum acts as a comparator, comparing motor intent from the cerebral cortex with sensory feedback from spinal pathways. It adjusts motor output for timing, force, and coordination.",
            whyIncorrect = "Medulla controls autonomic cardiorespiratory functions. Broca's regulates speech production. Anterior horns house lower motor neurons.",
            clinicalImportance = "Enables localization of dysfunction (such as cerebellar vs sensory ataxia) to customize treatment strategies.",
            examinerTip = "Damage to the cerebellum typically manifests as dysmetria, intention tremor, and dysdiadochokinesia.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "The cerebellum functions as a sensory-motor feedback loop comparator to optimize movement accuracy.",
            relatedConcept = "Cerebellar Function",
            reference = "Guyton & Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "How do coordination exercises differ from traditional strength training exercises in terms of execution speed and clinical focus?",
            options = listOf(
                "Coordination exercises focus on precision, rhythm, and accuracy of movement at slow, controlled speeds, rather than high resistance",
                "Coordination exercises require explosive explosive actions to build power",
                "Coordination exercises rely on high mechanical load to stimulate myofibrillar splitting",
                "Coordination exercises are designed to increase heart rate above 85% maximum reserve"
            ),
            correctAnswer = "Coordination exercises focus on precision, rhythm, and accuracy of movement at slow, controlled speeds, rather than high resistance",
            whyCorrect = "Coordination training is about refining motor pathways, correcting errors, and pacing movement. It values motor accuracy and cognitive control over muscle hypertrophy or peak force output.",
            whyIncorrect = "Explosive actions are for power. High loads target strength. Cardiorespiratory loads are aerobic, not coordination parameters.",
            clinicalImportance = "Ensures correct exercise selection to achieve functional motor control rather than just physical bulk.",
            examinerTip = "Always point out that a coordinated movement is one that is smooth, precise, and executed with minimal effort.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Coordination is built on movement quality, sensory integration, and precise motor pacing.",
            relatedConcept = "Motor Coordination Principles",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the significance of the 'finger-to-nose' or 'heel-to-shin' test in coordination assessments, and how does it translate to exercise therapy?",
            options = listOf(
                "It identifies dysmetria and intention tremor; exercise therapies break down these complex multi-joint actions into simple, guided segments",
                "It evaluates pulmonary vital capacity under static respiratory load",
                "It tests skin dermatomal sensation in the lower lumbar and sacral segments",
                "It evaluates immediate spinal reflex speed at the L4 nerve root level"
            ),
            correctAnswer = "It identifies dysmetria and intention tremor; exercise therapies break down these complex multi-joint actions into simple, guided segments",
            whyCorrect = "The finger-to-nose and heel-to-shin tests are classic cerebellar signs. Intention tremors and dysmetria (over/undershooting targets) emerge at joint terminal ranges. Exercises break these into single-joint targets to reduce coordinate demands.",
            whyIncorrect = "These tests do not evaluate respiration, tactile skin segment mapping, or osteotendinous reflexes (like the patellar tap).",
            clinicalImportance = "Directly guides the design of targeted motor coordination drills to restore functional reaching.",
            examinerTip = "Dysmetria is an inability to control the distance, power, and speed of an active movement.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Clinical coordination tests show motor planning errors, which are then addressed with segmented motor path re-education.",
            relatedConcept = "Cerebellar Deficit Identification",
            reference = "Magee's Orthopedic Physical Assessment"
        ),
        VivaMcq(
            question = "In coordination training, what is the role of rhythmic stabilization (a PNF technique)?",
            options = listOf(
                "It promotes co-contraction of stabilizing muscles around a joint using alternating isometric contractions",
                "It physically stretches shortened musculotendinous structures to increase active range",
                "It involves high-velocity ballistic movements to trigger stretch reflexes",
                "It passively rotates the limbs within a suspension harness to lower resting tone"
            ),
            correctAnswer = "It promotes co-contraction of stabilizing muscles around a joint using alternating isometric contractions",
            whyCorrect = "Rhythmic stabilization utilizes alternating isometric contractions of the agonist and antagonist muscles against resistance, without movement, to build joint stability and postural control.",
            whyIncorrect = "It is an isometric stabilization technique, and does not involve mechanical stretching, ballistic speed components, or passive support mechanics.",
            clinicalImportance = "Excellent for building spinal segment control or shoulder stabilization in hypermobility.",
            examinerTip = "Rhythmic stabilization is a key Proprioceptive Neuromuscular Facilitation (PNF) progression for joint stability.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Rhythmic stabilization helps build joint safety by triggering cocontraction of protective muscular rings.",
            relatedConcept = "Proprioceptive Neuromuscular Facilitation",
            reference = "Adler's PNF in Practice, 4th Ed"
        ),

        // === FUNCTIONAL RE-EDUCATION (36-43) ===
        VivaMcq(
            question = "What is the defining principle of task-oriented functional re-education in physical therapy?",
            options = listOf(
                "Training real-life daily tasks in their natural context to promote neuroplasticity and functional recovery",
                "Isolating single muscles on continuous mechanical gym pulleys to maximize localized hypertrophy",
                "Using passive modalities like ultrasound to stimulate cortical reorganization",
                "Immobilizing limbs in rigid casts to force sensory rest and neural recovery"
            ),
            correctAnswer = "Training real-life daily tasks in their natural context to promote neuroplasticity and functional recovery",
            whyCorrect = "Task-oriented training is based on motor learning theories indicating that neuroplasticity is enhanced when exercises represent meaningful, real-world tasks (e.g., reaching for a cup, rising from a chair) rather than abstract contractions.",
            whyIncorrect = "Muscle isolation, passive modalities, and disuse immobilization do not stimulate the sensory-motor integration required for long-term functional motor re-education.",
            clinicalImportance = "Maximizes functional carrying over from the clinic to the patient's daily life after neurological injuries.",
            examinerTip = "Task-oriented training must be repetitive, structured, variable, and progressive to stimulate neuroplasticity.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Successful motor reeducation requires practice of contextual, functionally meaningful movements.",
            relatedConcept = "Neurorehabilitation Principles",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),
        VivaMcq(
            question = "How does 'Constraint-Induced Movement Therapy' (CIMT) promote functional re-education in stroke survivors with upper extremity paresis?",
            options = listOf(
                "By restraining the unaffected limb, forcing the patient to use the paretic limb, which overcomes learned non-use",
                "By using electrical stimulation to fully paralyze the affected limb, allowing the healthy limb to resting-heal",
                "By binding the patient's torso to a standing frame to eliminate hip sway",
                "By using virtual reality headsets to simulate unilateral limb removal"
            ),
            correctAnswer = "By restraining the unaffected limb, forcing the patient to use the paretic limb, which overcomes learned non-use",
            whyCorrect = "CIMT blocks the use of the strong, unaffected limb (using a mitt or sling). This forces the patient to use the weaker, hemiparetic limb for daily activities, driving neuroplastic re-mapping and reversing 'learned non-use'.",
            whyIncorrect = "Paralyzing the weak limb is counterproductive. Torso restraint does not define CIMT, nor does sensory visual unilateral limb removal.",
            clinicalImportance = "Highly evidenced paradigm for upper limb recovery in post-acute stroke survivors.",
            examinerTip = "The therapeutic mechanism of CIMT is reversing the behavioral construct of learned non-use.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "CIMT forces cortical restructuring by demanding functional execution from the paretic limb.",
            relatedConcept = "Cortical Plasticity in Rehabilitation",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "When re-educating the gait cycle in a hemiplegic patient, which phase of walk is typically most challenging and requires targeted correction?",
            options = listOf(
                "Stance phase on the affected limb, due to poor quadriceps control and hip instability",
                "Swing phase of the unaffected limb, due to fatigue of the upper extremity stabilizers",
                "Deceleration phase of the unaffected heel strike as it contacts the ground",
                "Initial contact phase of the strong foot when the body weight centers completely on it"
            ),
            correctAnswer = "Stance phase on the affected limb, due to poor quadriceps control and hip instability",
            whyCorrect = "Stance phase requires the weaker limb to bear the entire body weight. Weakness in hip abductors (gluteus medius) and quadriceps leads to hip drop (Trendelenburg sign) or knee hyperextension (genu recurvatum) during loading.",
            whyIncorrect = "Swing phase of the strong limb is easy because weight is supported by the paretic limb. Impairments dominate during weight-bearing on the weak side.",
            clinicalImportance = "Allows therapists to design specific closed-chain strengthening and feedback cues during early stance.",
            examinerTip = "Patients with weak quadriceps will often hyperextend (lock) the knee in stance to prevent buckling.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Affected limb weight-bearing (stance) represents the primary biomechanical challenge in gait re-education.",
            relatedConcept = "Pathological Gait Retraining",
            reference = "Perry's Gait Analysis: Normal and Pathological Function, 2nd Ed"
        ),
        VivaMcq(
            question = "In functional re-education, what is the clinical distinction between 'intrinsic feedback' and 'extrinsic feedback'?",
            options = listOf(
                "Intrinsic feedback is the patient's own sensory awareness (proprioceptive, visual); extrinsic feedback is provided by the therapist or biofeedback",
                "Intrinsic feedback is only generated during deep sleep; extrinsic is only generated during high-speed running",
                "Intrinsic feedback improves muscle bulk; extrinsic feedback improves joint capsule elasticity",
                "Intrinsic feedback is given verbally; extrinsic feedback is given via manual muscle testing scales"
            ),
            correctAnswer = "Intrinsic feedback is the patient's own sensory awareness (proprioceptive, visual); extrinsic feedback is provided by the therapist or biofeedback",
            whyCorrect = "Intrinsic feedback is naturally available to the patient via their own somatosensory, vestibular, and visual systems. Extrinsic (augmented) feedback is external information supplied by the therapist (verbal cues, tactile input, mirror) to support learning.",
            whyIncorrect = "Both types of feedback occur during active waking execution. They do not correlate to sleeping states or anatomical structures like physical capsules.",
            clinicalImportance = "Therapists must systematically withdraw extrinsic feedback over time to ensure the patient develops independent motor error correction.",
            examinerTip = "Extrinsic feedback is useful in early motor learning but can create dependency if not faded out as the patient progresses.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Motor learning success requires transitioning from augmented clinical feedback to independent intrinsic sensory loops.",
            relatedConcept = "Motor Learning Feedback",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),
        VivaMcq(
            question = "Which sequence of functional movements represents the most logical progression for building transfers and mobility in a non-ambulatory patient?",
            options = listOf(
                "Bed mobility and rolling -> supine-to-sit transitions -> sit-to-stand transitions",
                "Sit-to-stand transitions -> supine-to-sit transitions -> bed mobility and rolling",
                "Walking with a rolling walker -> rolling in bed -> sitting on the edge of the bed",
                "All functional sequences must start with bilateral stair climbing to build quick confidence"
            ),
            correctAnswer = "Bed mobility and rolling -> supine-to-sit transitions -> sit-to-stand transitions",
            whyCorrect = "Functional progression must follow developmental sequences, commencing with horizontal control (rolling/bridging), transitioning to seated terminal control (supine-to-sit), and ultimately vertical upright loading (sit-to-stand).",
            whyIncorrect = "A non-ambulatory patient cannot start with standing transitions, walking, or stairs before establishing basic horizontal and seated spinal alignment.",
            clinicalImportance = "Structures a safe, achievable rehabilitation pathway for patients with marked neurological deficits.",
            examinerTip = "Always secure trunk stabilization in a seated position before challenging standing balance.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Safe functional re-education scales from lying control, to seated control, to upright tasks.",
            relatedConcept = "Developmental Sequences in Therapy",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "What is the clinical purpose of utilizing 'environmental progression' in functional re-education (e.g., from quiet clinic gym to a busy street)?",
            options = listOf(
                "To test and build the patient's ability to maintain functional efficiency under cognitive overload and dual-task constraints",
                "To reduce the patient's heart rate by introducing visual distractions",
                "To assess whether the patient can execute movements without any active motor planning",
                "To artificially lower the mechanical friction of normal walking"
            ),
            correctAnswer = "To test and build the patient's ability to maintain functional efficiency under cognitive overload and dual-task constraints",
            whyCorrect = "A patient may perform perfectly in a quiet, closed clinic environment but fail in an open, unpredictable environment (crowds, changes in terrain). Exposure to dual tasks (talking while walking, avoiding steps) fosters real-world community independence.",
            whyIncorrect = "Unpredictable environments increase (not decrease) heart rate and demand high sensory-motor planning. Environmental factors do not alter leg physical friction.",
            clinicalImportance = "Crucial step in transitional rehabilitation before discharging patients to live alone in the community.",
            examinerTip = "Distinguish between a 'closed environment' (predictable, quiet) and an 'open environment' (unpredictable, busy).",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Dynamic environments force the brain to adapt motor skills to variable external challenges.",
            relatedConcept = "Environmental Adaptations in Motor Control",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),
        VivaMcq(
            question = "During sit-to-stand training, which muscle group works eccentrically when the patient is lowering themselves back down to the chair?",
            options = listOf(
                "Quadriceps and gluteus maximus",
                "Anterior tibialis and dorsiflexor stabilizers",
                "Rectus abdominis and obliques",
                "Biceps brachii and forearm pronators"
            ),
            correctAnswer = "Quadriceps and gluteus maximus",
            whyCorrect = "When lowering the body under gravity, the hip and knee joints flex. The knee extensors (quadriceps) and hip extensors (gluteus maximus) must lengthen while contracting (eccentric contraction) to control the descent.",
            whyIncorrect = "Dorsiflexors stabilize the ankle posture. Abdominals control spinal positioning. Upper limbs are typically non-weight-bearing during direct leg-driven sit-lowering.",
            clinicalImportance = "Prevents sit-to-stand plopping and builds the eccentric control required for down-ramp walking.",
            examinerTip = "Remember that eccentric contractions are essential for deceleration and safe downward control against gravity.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Lowering movements under gravity require eccentric control from the extensor muscles.",
            relatedConcept = "Functional Muscle Actions",
            reference = "Lippert's Clinical Kinesiology and Anatomy, 6th Ed"
        ),
        VivaMcq(
            question = "Which motor learning phase is characterized by the patient making many errors but rapidly understanding how to perform the movement?",
            options = listOf(
                "Cognitive phase",
                "Associative phase",
                "Autonomous phase",
                "Reflexive phase"
            ),
            correctAnswer = "Cognitive phase",
            whyCorrect = "In the cognitive phase, the patient is first learning the movement schema. They make frequent performance errors but show rapid gains as they understand what to do.",
            whyIncorrect = "The associative phase features fewer errors and focus on movement refinement. The autonomous phase is highly polished and requires minimal conscious attention. There is no 'reflexive phase' in Fitts and Posner's three-stage model.",
            clinicalImportance = "Allows clinicians to select appropriate coaching strategies (high feedback and quiet environment during cognitive stages).",
            examinerTip = "Fitts and Posner's three stages of motor learning are Cognitive, Associative, and Autonomous.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "The cognitive phase requires significant mental effort, with frequent errors as the patient learns the basic motor schema.",
            relatedConcept = "Three-Stage Motor Learning",
            reference = "Shumway-Cook & Woollacott's Motor Control, 5th Ed"
        ),

        // === REHABILITATION EXERCISE (44-50) ===
        VivaMcq(
            question = "What is the biological objective of applying the 'overload principle' in therapeutic exercise rehabilitation?",
            options = listOf(
                "To challenge the neuromuscular system by applying loads greater than normal to induce physiological adaptations",
                "To intentionally fatigue the patient's cardiorespiratory capacity to trigger acute hyperventilation",
                "To keep the physiological workload constant across several years of therapeutic exercise",
                "To deliberately stress a healing joint capsule beyond its absolute point of mechanical rupture"
            ),
            correctAnswer = "To challenge the neuromuscular system by applying loads greater than normal to induce physiological adaptations",
            whyCorrect = "The overload principle states that to improve physical performance or muscle strength, muscles must be challenged with loads exceeding baseline capabilities. This stimulates muscular hypertrophy and cellular remodeling.",
            whyIncorrect = "The objective is controlled adaptation, not hyperventilation crisis, constant flat workload (which leads to stagnation), or capsule rupture.",
            clinicalImportance = "Dictates progressive loading parameters to ensure continuous functional recovery.",
            examinerTip = "Without overload, muscle tissue remains in homeostasis and no further strength gains will occur.",
            subject = "Exercise Therapy II",
            difficulty = "Easy",
            learningPoint = "Tissue adaptation requires exceeding habitual loading thresholds in a controlled, progressive fashion.",
            relatedConcept = "Strength Adaptations",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "Which type of muscle contraction creates the highest tension per unit of muscle cross-sectional area and is often used cautiously in tendinopathy rehab?",
            options = listOf(
                "Eccentric contraction",
                "Concentric contraction",
                "Isometric contraction",
                "Isokinetic contraction"
            ),
            correctAnswer = "Eccentric contraction",
            whyCorrect = "Eccentric muscle contractions produce higher force outputs compared to concentric or isometric contractions. This is because muscles can resist higher external loads when stretching, although it also carries a higher risk of muscle soreness (DOMS).",
            whyIncorrect = "Concentric and isometric contractions generate lower peak tensions and metabolic stress per unit area compared to eccentric contractions.",
            clinicalImportance = "Key to tendon remodel protocols (e.g. Alfredson's heel-drops for Achilles tendinopathy) to promote collagen alignment.",
            examinerTip = "Eccentric contractions require lower oxygen consumption but generate higher high-load tension than concentric contractions.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Eccentric loading produces high force outputs and is highly effective for restructuring degenerated tender collagen fibers.",
            relatedConcept = "Mechanics of Muscle Contraction",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "When executing a rehabilitation program for an acute lateral ankle sprain, which phase of tissue healing allows the introduction of progressive resisted exercises (PRE)?",
            options = listOf(
                "Subacute (proliferation) phase, when collagen is being laid down but is still structurally immature",
                "Acute inflammatory phase, when local swelling and pain are at their worst",
                "Active remodeling phase, but only after complete fibrotic scarring has locked the joint",
                "No physical therapy active exercises are allowed inside the first six months of ankle treatment"
            ),
            correctAnswer = "Subacute (proliferation) phase, when collagen is being laid down but is still structurally immature",
            whyCorrect = "During the subacute proliferation phase (typically 4-21 days post-injury), noxious symptoms decline and collagen synthesis begins. Gentle, progressive resistive exercises can be safely started within pain-free ranges to guide early collagen fiber alignment.",
            whyIncorrect = "Active resistance is contraindicated in the acute stage (first 48-72 hours) due to risk of tissue tear and persistent bleeding. Remodeling is the final phase where high training loads are tolerated, rather than original PRE introduction.",
            clinicalImportance = "Prevents joint stiffness and muscle wasting while safeguarding healing ligament structures.",
            examinerTip = "Remember that early controlled mechanical loading (such as early weight-bearing) actually speeds up ligament repair.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Controlled mechanical stress during tissue proliferation directs strong, parallel collagen alignments.",
            relatedConcept = "Phases of Connective Tissue Healing",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "How does the Daily Adjustable Progressive Resistive Exercise (DAPRE) protocol differ from the classic DeLorme program in strength training?",
            options = listOf(
                "DAPRE adjusts the workload for subsequent sets based on the number of repetitions performed in the third and fourth sets",
                "DAPRE requires the patient to work on static traction tables for 24 continuous hours",
                "DAPRE operates entirely without calculating the user's 1-repetition maximum (1RM) baseline",
                "DAPRE is designed only for cardiovascular conditioning in patients with asthma"
            ),
            correctAnswer = "DAPRE adjusts the workload for subsequent sets based on the number of repetitions performed in the third and fourth sets",
            whyCorrect = "The DAPRE system uses a 4-set system. The weight for the 4th set is adjusted up or down based on the number of repetitions the patient managed to complete in the 3rd set. This matches training intensity to daily performance fluctuations.",
            whyIncorrect = "DeLorme uses a rigid progression scheme (50%, 75%, and 100% of 10RM). DAPRE does not involve traction tables, is force-focused, and uses repetitions to guide progression.",
            clinicalImportance = "Provides a highly objective, automated progress mechanism for clinical strength recovery.",
            examinerTip = "DAPRE adjusts to the patient's daily performance, preventing overtraining or undertraining.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "DAPRE dynamic adjustment tailors exercise intensity to match daily muscle recovery levels.",
            relatedConcept = "Progressive Resistive Exercise Models",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "In post-operative orthopedic rehabilitation, what does 'open kinetic chain' (OKC) exercise refer to, and when is it clinically preferred?",
            options = listOf(
                "Exercises where the distal segment is free to move; preferred when weight-bearing is restricted or isolated joint training is needed",
                "Exercises where the distal segment is fixed to a solid surface; preferred to maximize joint compression and cocontraction",
                "Exercises where the patient moves without any mental concentration; preferred during early cognitive phases",
                "Exercises that require active surgical wounds to remain open during muscle training"
            ),
            correctAnswer = "Exercises where the distal segment is free to move; preferred when weight-bearing is restricted or isolated joint training is needed",
            whyCorrect = "An open kinetic chain (OKC) features a moving distal branch (like knee extension on a machine). It is ideal for targeted single-muscle isolation and can be performed when closed-chain loading (weight-bearing) is contraindicated.",
            whyIncorrect = "Fixed distal segment defines closed kinetic chain (CKC) exercises. OKC requires active motor planning and does not relate to open surgical incisions.",
            clinicalImportance = "Ensures early quadriceps activation following knee injuries before full weight-bearing is permitted.",
            examinerTip = "OKC focus is on joint isolation, while closed kinetic chain (CKC) mimics functional multi-joint actions.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "OKC movements isolate muscle groups and bypass full skeletal weight-bearing thresholds.",
            relatedConcept = "Kinetic Chain Classifications",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "What is the clinical distinction of muscle 'power' compared to muscle 'strength' in orthopedic and athletic rehabilitation?",
            options = listOf(
                "Power is the rate of performing work (force x velocity); strength is the maximal force-generating capacity of a muscle",
                "Power is only measured when jumping; strength is only measured when standing completely still",
                "Power is controlled by type I aerobic fibers; strength is controlled by fast-twitch cardiac cells",
                "There is no difference; power and strength are synonyms in physical therapy terminology"
            ),
            correctAnswer = "Power is the rate of performing work (force x velocity); strength is the maximal force-generating capacity of a muscle",
            whyCorrect = "Power introduces a time domain: Power = (Work / Time) = Force x Velocity. Strength is simply peak torque output regardless of speed. Building power requires training both force and velocity.",
            whyIncorrect = "Power can be measured across any movement speed. Power relies heavily on type II fast-contracting glycolytic fibers, not type I or cardiac tissues.",
            clinicalImportance = "Essential for return-to-sport protocols where explosive acceleration and deceleration are required to prevent re-injury.",
            examinerTip = "A patient may have standard muscle strength but lack functional power because of slow motor unit transition speeds.",
            subject = "Exercise Therapy II",
            difficulty = "Moderate",
            learningPoint = "Muscle power represents force generation over time, whereas strength represents absolute peak force production.",
            relatedConcept = "Muscular Adaptation Parameters",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "During rehabilitation of a shoulder rotator cuff pathology, why is it critical to strengthen the scapular stabilizers alongside the rotator cuff muscles?",
            options = listOf(
                "To ensure proper scapulohumeral rhythm and prevent subacromial impingement during arm elevation",
                "To fully paralyze the upper trapezius and prevent any movement of the clavicle bone",
                "To prevent blood pooling in the cervical spine segments",
                "To decrease the range of motion of the glenohumeral joint to protect the labrum"
            ),
            correctAnswer = "To ensure proper scapulohumeral rhythm and prevent subacromial impingement during arm elevation",
            whyCorrect = "Elevation of the arm requires a 2:1 ratio of glenohumeral to scapulothoracic movement (scapulohumeral rhythm). Strengthening scapular stabilizers (e.g., serratus anterior, lower trapezius) ensures the scapula upwardly rotates, keeping the subacromial space open.",
            whyIncorrect = "Our goal is coordinated muscle function, not muscle paralysis. Stabilization preserves glenohumeral health, protecting the labrum without restricting normal movement.",
            clinicalImportance = "Prevents chronic subacromial impingement and speeds up overhead arm recovery.",
            examinerTip = "Scapular stabilizers form the base of support for all dynamic hand and arm functions.",
            subject = "Exercise Therapy II",
            difficulty = "High Yield",
            learningPoint = "Upward rotation of the scapula is required to preserve subacromial clearance during arm abduction.",
            relatedConcept = "Scapulohumeral Rhythm Mechanics",
            reference = "Magee's Orthopedic Physical Assessment"
        )
    )
}
