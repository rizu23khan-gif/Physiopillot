package com.example.data

object BiomechanicsData {
    val questions: List<VivaMcq> = listOf(
        // === FORCE (1-6) ===
        VivaMcq(
            question = "According to Newton's First Law of Motion (Inertia), what biomechanical observation explains why a passive patient requires the most assistance during the initiation of transfer?",
            options = listOf(
                "A body at rest tends to remain at rest unless acted upon by an external net unbalanced force, meaning muscle forces or physical therapist assist must overcome static intertia",
                "The patient's kinetic energy increases exponentially with position, which naturally opposes any start of movement",
                "Atmospheric pressure pushes downwards more intensely when a patient is purely stationary than when they are sliding",
                "Static friction is mathematically identical to kinetic friction, meaning starting and sliding require the exact same effort"
            ),
            correctAnswer = "A body at rest tends to remain at rest unless acted upon by an external net unbalanced force, meaning muscle forces or physical therapist assist must overcome static intertia",
            whyCorrect = "Newton's First Law states that an object at rest remains at rest until a force changes its state. Overcoming the initial inertia of a stationary clinical patient requires a higher muscle/assistive force than keeping them moving.",
            whyIncorrect = "Kinetic energy is zero when stationary. Atmospheric pressure is uniform. Static friction coefficient is greater than kinetic friction, making starting harder than sliding.",
            clinicalImportance = "Informs therapists to use correct momentum strategies (e.g., rock back and forth) to easily overcome static inertia during sit-to-stand transfers.",
            examinerTip = "Examiners love when you relate Newton's First Law to real-world gait initiation or physical patient transfers.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Forces must first overcome static inertia to initiate movement of a stationary patient/object.",
            relatedConcept = "Newton's First Law and Transfers",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "When analyzing the shoulder joint, how does vector composition explain why the anterior and posterior deltoid muscle fibers produce pure abduction when acting simultaneously?",
            options = listOf(
                "Their transverse plane directional force vectors are equal and opposite, canceling each other out, while their frontal plane upward vectors combine to lift the arm",
                "Both sub-divisions of the deltoid undergo molecular adaptation that converts shear forces into rotational torque",
                "The anterior and posterior deltoid fibers are completely parallel, allowing their forces to add up without any trigonometric angles",
                "The mechanical pull of the deltoid causes a downward subluxation vector that is neutralized by atmospheric vacuum"
            ),
            correctAnswer = "Their transverse plane directional force vectors are equal and opposite, canceling each other out, while their frontal plane upward vectors combine to lift the arm",
            whyCorrect = "According to vector composition, when the anterior deltoid (pulls up and forward) and posterior deltoid (pulls up and backward) contract together, their forward/backward vectors cancel. Their common superior/abducting vectors summate, producing clean shoulder abduction.",
            whyIncorrect = "Their fibers are not parallel; they arise from different anatomical structures. No molecular transformation of shear forces occurs. The deltoids tend to pull the humeral head upward (not downward), which is neutralized by the rotators (rotator cuff force couple).",
            clinicalImportance = "Highlights the importance of balanced muscular activity; selective weakness in one deltoid division causes aberrant humeral pathomechanics and impingement.",
            examinerTip = "Be ready to resolve vectors into horizontal and vertical components to show how coplanar forces interact.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Vector components in opposite directions cancel out, leaving the net force along the common mechanical vector.",
            relatedConcept = "Vector Composition and Muscular Force Couples",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "In the biomechanics of joint wear, how does boundary lubrication contrast with fluid-film lubrication in preventing destructive friction forces in articular cartilage?",
            options = listOf(
                "Boundary lubrication relies on surface-bound glycoprotein molecules (e.g., lubricin) preventing direct solid-on-solid contact, whereas fluid-film lubrication creates a pressurised fluid layer that fully separates the surfaces",
                "Fluid-film lubrication relies on physical bone-to-bone contact to trigger local blood flow, while boundary lubrication prevents all joint motion entirely",
                "Boundary lubrication uses continuous acoustic shockwaves to push articular cartilage apart, while fluid-film lubrication melts fat layers",
                "There is no functional difference; both mechanisms melt synovial fluid to increase friction forces in heavy load situations"
            ),
            correctAnswer = "Boundary lubrication relies on surface-bound glycoprotein molecules (e.g., lubricin) preventing direct solid-on-solid contact, whereas fluid-film lubrication creates a pressurised fluid layer that fully separates the surfaces",
            whyCorrect = "Boundary lubrication occurs when a monolayer of lubricin molecules binds to joint surfaces, preventing contact under extremely high, static loads. Fluid-film (hydrodynamic or squeeze-film) lubrication occurs when joint movement of synovial fluid builds a pressurised wedge, lifting the surfaces apart completely.",
            whyIncorrect = "Fluid-film does not promote bone-to-bone contact. Boundary lubrication allows smooth motion. Synovial fluid decreases (does not increase) friction forces, and cannot be melted as it is water-based.",
            clinicalImportance = "In osteoarthritis, the breakdown of these lubrication mechanisms increases the coefficient of friction, accelerating cartilage degradation.",
            examinerTip = "Understand that joint lubrication changes based on load and velocity; high load, slow speed relies on boundary lubrication.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Joint health relies on both biochemical (boundary) and physical (fluid-film) lubrication to minimize frictional shear stresses.",
            relatedConcept = "Synovial Joint Lubrication Mechanisms",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "In human postural equilibrium, what is the biomechanical difference between internal forces and external forces acting on the skeletal system?",
            options = listOf(
                "Internal forces are generated by active muscular contraction and passive connective tissues, whereas external forces originate from gravity, ground reactions, or contact contacts",
                "Internal forces are completely exempt from Newton's laws, while external forces are purely rotational forces",
                "External forces exist only during dynamic gymnastics, while internal forces are the only forces active during deep sleep",
                "All internal forces are perpendicular shear forces, while all external forces are parallel vectors"
            ),
            correctAnswer = "Internal forces are generated by active muscular contraction and passive connective tissues, whereas external forces originate from gravity, ground reactions, or contact contacts",
            whyCorrect = "Internal forces are generated within the body (e.g., muscle tension, ligament stretch, joint friction). External forces are exerted on the body by the external environment (e.g., gravity, loads, wind, or ground reaction forces).",
            whyIncorrect = "Internal forces follow all laws of motion. External forces are present during static posture (e.g., gravity). Both types of forces can have shear and normal components.",
            clinicalImportance = "Therapists prescribe external loads (weights) to challenge and adapt the internal muscle and skeletal forces of their patients.",
            examinerTip = "Remember that motion occurs when external forces are greater than or less than internal resistive forces.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Postural balance is a continuous negotiation between internal muscular efforts and external gravitational forces.",
            relatedConcept = "Internal vs External Force Systems",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "During a heavy lifting task, why is the lumbar spine subjected to a much greater risk of injury when the trunk is bent forward compared to keeping it vertical?",
            options = listOf(
                "Bending forward converts the compressive load of gravity into a highly harmful perpendicular shear force vector on the intervertebral discs",
                "A vertical trunk creates a vacuum in the abdominal cavity that dissolves all external physical loads",
                "The lumbar multifidus muscle contracts with less electrical potential when the spine is flexed, preventing structural support",
                "Lifting vertically rotates the gravity vector by 90 degrees, directing it away from the lower extremity"
            ),
            correctAnswer = "Bending forward converts the compressive load of gravity into a highly harmful perpendicular shear force vector on the intervertebral discs",
            whyCorrect = "Flexing the trunk increases the angle of inclination of the lumbar spine, which of course resolves the gravitational vector into a larger parallel shear force component across the disc spaces (L5-S1) and relies heavily on muscular contraction to resist it.",
            whyIncorrect = "Vertical trunk does not create a load-dissolving vacuum. Multifidus actually displays high electromyographical activity during flexion until the flexion-relaxation phenomenon is reached. The gravity vector is always vertical.",
            clinicalImportance = "Informs safe ergonomics training—bending the knees (squat lift) keeps the trunk vertical, which reduces shear loading and protects the intervertebral discs from herniation.",
            examinerTip = "Examiners love drawing vector diagrams of spinal shear. Remember, spinal shear forces are parallel to the disc surfaces, and flexion increases them.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Forward trunk flexion resolves gravitational load into higher shear forces across the lumbar vertebral segments.",
            relatedConcept = "Lumbar Spine Load Resolving",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "During the heel strike (initial contact) phase of a normal walking gait, how does the Ground Reaction Force (GRF) vector affect the knee joint?",
            options = listOf(
                "The GRF vector passes anterior to the knee joint center, creating an external extension moment that stabilizes the knee",
                "The GRF vector passes posterior to the knee joint center, creating an external flexion moment that must be counteracted by eccentric quadriceps contraction",
                "The GRF vector points directly sideways, causing a severe varus subluxation in normal walking",
                "The GRF vector is exactly zero during heel strike because the foot is not yet flat on the ground"
            ),
            correctAnswer = "The GRF vector passes posterior to the knee joint center, creating an external flexion moment that must be counteracted by eccentric quadriceps contraction",
            whyCorrect = "At initial contact (heel strike), the Ground Reaction Force (GRF) rises superiorly from the heel, passing slightly posterior to the knee rotational axis. This creates an external flexion moment that would buckle the knee if the quadriceps (rectus femoris, vasti) did not contract eccentrically to control knee flexion.",
            whyIncorrect = "If the vector passed anterior, it would create an extension moment requiring hamstrings to resist. The vector is not lateral. The GRF begins immediately upon contact, and is not zero.",
            clinicalImportance = "Patients with quadriceps weakness (e.g., femoral nerve palsy or post-knee replacement) will display unstable knee hyperextension (groping/recurvatum) as a compensation to avoid knee buckling.",
            examinerTip = "Always trace where the GRF vector passes relative to joint axes (pelvis, knee, ankle) to deduce the external moments generated.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "At heel strike, the GRF vector passes behind the knee axis, generating an external flexion moment managed by the eccentric quadriceps.",
            relatedConcept = "Ground Reaction Forces and External Moments",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),

        // === TORQUE (7-12) ===
        VivaMcq(
            question = "Which of the following describes the thermodynamic or biomechanical definition of torque, and how is it mathematically calculated?",
            options = listOf(
                "Torque is the rotational equivalent of linear force; calculated by multiplying the magnitude of force by the perpendicular distance from the axis of rotation to the force's line of action (moment arm)",
                "Torque is the chemical potential of a muscle fiber; calculated by dividing total force by cross-sectional area",
                "Torque is mechanical pressure; calculated as force per unit area on synovial membranes",
                "Torque is the speed of joint flexion; calculated by multiplying acceleration by mass"
            ),
            correctAnswer = "Torque is the rotational equivalent of linear force; calculated by multiplying the magnitude of force by the perpendicular distance from the axis of rotation to the force's line of action (moment arm)",
            whyCorrect = "Torque (T), or moment (M), represents the rotational effect of a force. It is the product of the active force (F) and its perpendicular distance (d) from the joint axis: T = F * d.",
            whyIncorrect = "Chemical potential is not torque. Force per unit area is mechanical stress/pressure. Mass times acceleration is linear force (Newton's Second Law).",
            clinicalImportance = "Explains why weaker muscles can still lift loads if the load is positioned to minimize its external moment arm.",
            examinerTip = "Torque is measured in Newton-meters (Nm). Be prepared to calculate it in your exams.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Torque is the rotational effect of force and depends on the perpendicular distance to the joint's center of rotation.",
            relatedConcept = "Mechanical Torque and Joint Moments",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "How does the anatomic presence of the patella function as a pulley to influence the quadriceps torque capability during knee extension?",
            options = listOf(
                "It displaces the quadriceps tendon anteriorly away from the joint axis, increasing the perpendicular moment arm and thereby multiplying knee extension torque",
                "It absorbs thermal stress to cool the knee joint during dynamic athletic tasks",
                "It binds calcium molecules directly to the tendon, increasing absolute cross-sectional muscle mass temporarily",
                "It acts as a mechanical block that completely prevents all terminal hyperextension"
            ),
            correctAnswer = "It displaces the quadriceps tendon anteriorly away from the joint axis, increasing the perpendicular moment arm and thereby multiplying knee extension torque",
            whyCorrect = "The patella acts as an anatomical spacer. By holding the quadriceps tendon away from the knee's axis of rotation, it increases the internal moment arm of the extensor mechanism, allowing the quadriceps to generate up to 50% more extension torque with the same contractile force.",
            whyIncorrect = "The patella does not act as a heat-absorption unit or chemical calcium store. While it protects the knee joint, it is not a direct structural block to terminal extension.",
            clinicalImportance = "A patellectomy (removal of patella) decreases quadriceps extension torque dramatically, causing significant functional difficulty in stair climbing.",
            examinerTip = "Examiners frequently ask why a patellectomized knee requires much more quadriceps force to extend. It is because of the loss of the moment arm.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "The patella increases the internal moment arm of the quadriceps, significantly boosting terminal knee extension torque.",
            relatedConcept = "Pulley Systems and Anatomical Spacers",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "During a bent-over deadlift, why does holding a 20 kg barbell close to the shins place dramatically less stress on the lumbar erector spinae than holding it 30 cm away from the legs?",
            options = listOf(
                "Holding the load close minimizes the external moment arm of the barbell’s weight relative to the lumbar spine, which reduces the external flexion torque that muscle forces must counteract",
                "Holding the load close reduces the physical mass of the barbell itself by 50%",
                "Placing the load close to the legs converts the lumbar spine into a second-class mechanical lever",
                "Holding the load close to the legs allows internal friction forces of the skin to carry the weight"
            ),
            correctAnswer = "Holding the load close minimizes the external moment arm of the barbell’s weight relative to the lumbar spine, which reduces the external flexion torque that muscle forces must counteract",
            whyCorrect = "The external flexion torque of a lifted object equals the weight (force) times its horizontal distance (moment arm) from the lumbar spine rotational axis (L5-S1). Reducing this distance reduces the required internal muscular extension torque, relieving the erector spinae.",
            whyIncorrect = "The physical mass remains identical. The lever class does not change (it remains a third-class system). Skin friction cannot carry skeletal loads.",
            clinicalImportance = "This is the primary mechanical explanation for lifting with the back flat and keeping objects as close as possible to the body.",
            examinerTip = "Always focus on the distance (moment arm) when explaining dynamic spinal load differences.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Minimizing the external moment arm of a lifted load reduces the joint torque demands on adjacent spinal segments.",
            relatedConcept = "Leverage and Vertebral Intradiscal Pressure",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "A patient holds a 5 kg dumbbell in their hand with the elbow flexed to 90 degrees. If the horizontal distance from the elbow axis to the dumbbell is 30 cm, what is the external flexion torque generated by the dumbbell?",
            options = listOf(
                "Approximately 14.7 Newton-meters, assuming standard acceleration of gravity (9.8 m/sec²)",
                "Exactly 1.5 Newton-meters, calculated without accounting for vertical gravity",
                "Approximately 150 Newton-meters, due to joint fluid pressure magnification",
                "Zero, because the joint is undergoing static isometric contraction"
            ),
            correctAnswer = "Approximately 14.7 Newton-meters, assuming standard acceleration of gravity (9.8 m/sec²)",
            whyCorrect = "Force of dumbbell = mass * gravity = 5 kg * 9.8 m/sec² = 49 Newtons. Moment arm = 30 cm = 0.3 meters. Torque = Force * moment arm = 49 N * 0.3 m = 14.7 Nm.",
            whyIncorrect = "1.5 Nm does not include gravity. 150 Nm is mathematically incorrect. Even during static contractions, static load torque exists and is countered by muscle torque.",
            clinicalImportance = "Allows clinicians to calculate the exact workloads required during rehabilitation programs to avoid tissue failure.",
            examinerTip = "Always convert centimeters to meters and mass to Newtons (using gravity) before calculating mechanical torque.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Calculating external torque requires resolving mass into weight force and multiplying by the horizontal moment arm.",
            relatedConcept = "Static Torque Calculations",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "During active hip abduction, how does passive tension in the antagonist adductor muscles influence the net abductor torque produced by the gluteus medius?",
            options = listOf(
                "Passive tension in the adductors generates an antagonist torque that opposes abduction, meaning the gluteus medius must generate higher force to produce the same net movement",
                "Passive tension in the adductors directly feeds active cellular energy into the gluteus medius muscle fibers",
                "It causes the hip abductors to expand physically, converting the joint system into a second-class mechanical lever",
                "Passive tension remains identical in all hip positions, producing zero cumulative mechanical torque"
            ),
            correctAnswer = "Passive tension in the adductors generates an antagonist torque that opposes abduction, meaning the gluteus medius must generate higher force to produce the same net movement",
            whyCorrect = "Passive tension builds up in a muscle when it is stretched. During hip abduction, the adductor muscles are stretched. Their passive tension resists the motion, acting as an opposing hip adduction torque that the gluteus medius must overcome to abduct.",
            whyIncorrect = "Antagonist tension resists (does not feed) the agonist. The joint lever class remains unchanged. Passive tension increases non-linearly with joint range of motion.",
            clinicalImportance = "Explains why stretching tight, shortened antagonist muscles (e.g., adductors) can immediately improve active range of motion and reduce work of agonists (e.g., abductors).",
            examinerTip = "Differentiate clearly between passive tension (stretching of non-contractile units) and active tension (cross-bridge cycle contraction).",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Agonist muscle groups must overcome both external loads and passive tension of stretched antagonist muscles.",
            relatedConcept = "Agonist-Antagonist Torque Dynamics",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "How does the force couple of the rotator cuff (supraspinatus, infraspinatus, teres minor, subscapularis) and deltoid prevent superior subluxation of the humerus during shoulder abduction?",
            options = listOf(
                "The deltoid pulls the humerus superiorly while the lower cuff muscles (infraspinatus, subscapularis) pull the humeral head inferiorly, creating a pure upward rotational torque with zero net linear translation",
                "The rotator cuff muscles pull the glenoid cavity laterally to wrap around the humeral head during motion",
                "The force couple creates a high-voltage electrical field that repels the humerus down away from the acromion",
                "Both groups pull directly upward, compressing the subacromial bursa to establish joint vacuum"
            ),
            correctAnswer = "The deltoid pulls the humerus superiorly while the lower cuff muscles (infraspinatus, subscapularis) pull the humeral head inferiorly, creating a pure upward rotational torque with zero net linear translation",
            whyCorrect = "When two equal and opposite forces act on different sides of an axis, they form a force couple, generating pure rotational torque. The deltoid pulls upward; the infraspinatus, teres minor, and subscapularis pull inward and downward to neutralize superior drift, keeping the humerus centered in the glenoid during abduction.",
            whyIncorrect = "The glenoid cavity cannot move laterally independent of the scapula. Muscles do not generate high-voltage mechanical fields or compress structural bursas to create vacuums.",
            clinicalImportance = "In rotator cuff tears (especially supraspinatus/infraspinatus), this force couple is disrupted, allowing the deltoid to pull the humerus superiorly, causing subacromial impingement.",
            examinerTip = "This is a key shoulder biomechanics question. Remember that a force couple produces rotational torque without linear motion.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "The shoulder force couple balances linear forces to produce clean rotational motion while maintaining glenohumeral stability.",
            relatedConcept = "Force Couples and Glenohumeral Stability",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),

        // === LEVER (13-18) ===
        VivaMcq(
            question = "Which class of lever is most common in the human musculoskeletal system, and what is its primary mechanical trade-off?",
            options = listOf(
                "Third-class levers; they trade a mechanical disadvantage (requiring massive muscle forces) to gain high speed and greater range of motion at the distal limb",
                "First-class levers; they guarantee high speed, but require permanent mechanical locking of all adjacent joints",
                "Second-class levers; they allow joints to flex 360 degrees, but decrease the speed of limb movement to zero",
                "No single lever class exists in the human body, as muscles attach purely to soft tissue segments"
            ),
            correctAnswer = "Third-class levers; they trade a mechanical disadvantage (requiring massive muscle forces) to gain high speed and greater range of motion at the distal limb",
            whyCorrect = "In a third-class lever, the effort force (muscle) is applied between the axis of rotation (joint) and the load (resistance). Since the muscle moment arm is much shorter than the load moment arm, the mechanical advantage is less than 1. This means muscles must contract with force much greater than the load, but the distal segment moves with high speed and range.",
            whyIncorrect = "First-class levers are rare (e.g., atlanto-occipital joint). Second-class levers have mechanical advantage > 1 (rare, e.g., heel raise). Human bones and joints function as classic mechanical levers.",
            clinicalImportance = "Understanding the large internal muscle forces required in third-class setups explains why tendons are susceptible to overuse injuries and degeneration.",
            examinerTip = "Remember: Axis-Effort-Load (A-E-L) is Third-class. Most human joints use this layout.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Most skeletal joints are third-class levers designed to maximize displacement speed and range of motion at the expense of force.",
            relatedConcept = "Skeletal Lever Classes and Mechanical Trade-offs",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "Why does the human biological body utilize third-class levers predominantly, even though their Mechanical Advantage (MA) is always less than 1.0?",
            options = listOf(
                "Because a small contraction of the muscle tendon near the joint axis creates a large, fast movement of the distal hand or foot, which is highly advantageous for walking and tool use",
                "Because third-class levers reduce the gravitational force acting on the body's center of mass",
                "Because third-class configurations prevent lactic acid accumulation in distal joints during exercise",
                "Because they are the only lever systems that can operate with low calcium consumption"
            ),
            correctAnswer = "Because a small contraction of the muscle tendon near the joint axis creates a large, fast movement of the distal hand or foot, which is highly advantageous for walking and tool use",
            whyCorrect = "A mechanical advantage of <1 means the muscle must generate a high force (due to its short moment arm). However, since the effort arm is short, a very small shortening of the muscle produces a huge excursion and high linear velocity of the distal end (e.g., throwing a ball or swing phase in gait).",
            whyIncorrect = "Levers do not change the pull of gravity. They do not alter lactic acid biochemistry or calcium consumption.",
            clinicalImportance = "Helps explain why prosthetic limbs must also utilize this relationship to mimic natural movement speed and coordination.",
            examinerTip = "Be prepared to summarize this with the phrase: 'We sacrifice mechanical force advantage to gain distance and speed.'",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Third-class levers amplify displacement speed, making them biochemically and structurally ideal for locomotion.",
            relatedConcept = "Mechanical Advantage in Human Skeletons",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "How is Mechanical Advantage (MA) calculated in a biological lever system, and why does a second-class lever always maintain an MA greater than 1.0?",
            options = listOf(
                "MA is the ratio of the internal effort moment arm to the external resistance moment arm; in second-class levers, the internal effort arm is always longer than the resistance arm",
                "MA is calculated by dividing patient weight by muscle volume; second-class systems naturally have greater muscle volume",
                "MA represents the joint angle at terminal extension, which is always 180 degrees in second-class joints",
                "It is calculated by multiplying force by velocity; second-class systems operate at zero speed"
            ),
            correctAnswer = "MA is the ratio of the internal effort moment arm to the external resistance moment arm; in second-class levers, the internal effort arm is always longer than the resistance arm",
            whyCorrect = "Mechanical Advantage (MA) = Effort Arm (EA) / Resistance Arm (RA). In a second-class lever (e.g., heel raise), the load is positioned between the joint axis (metatarsophalangeal joint) and the muscle pull (gastrocnemius/soleus). Because the effort arm (from axis to tendo-achilles) is always longer than the resistance arm, the MA is always >1 (meaning less muscle force is needed to lift the load).",
            whyIncorrect = "MA has nothing to do with muscle volume, 180-degree angles, or multiplying force by velocity.",
            clinicalImportance = "Explains why calf muscles can easily lift the entire weight of the human body during a heel raise with relatively small muscle bulk.",
            examinerTip = "Be prepared to draw a calf raise diagram to explain its second-class lever layout.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Second-class levers put the resistance closer to the axis than the effort, prioritizing force amplification over speed.",
            relatedConcept = "Second Class Levers and Calf Mechanics",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "Which of the following clinical landmarks represents a classic biological first-class lever operating within the human body?",
            options = listOf(
                "The atlanto-occipital joint (head and neck flexion/extension setup), where the joint axis lies between the muscle pull (post-neck extensors) and the load (face/weight of head)",
                "The tibiofemoral joint during quadriceps contraction, where tendon attachment is always distal",
                "The glenohumeral joint during active shoulder abduction, which operates as a second-class system",
                "The distal interphalangeal joint of the index finger during light pinching"
            ),
            correctAnswer = "The atlanto-occipital joint (head and neck flexion/extension setup), where the joint axis lies between the muscle pull (post-neck extensors) and the load (face/weight of head)",
            whyCorrect = "A first-class lever has its axis (pivot) between the effort (force) and the resistance (load). The atlanto-occipital joint is a classic example: the joint is the axis, the posterior neck muscles provide the effort to hold up the head, and the center of mass of the skull anterior to the joint is the resisting load.",
            whyIncorrect = "Tibiofemoral quadriceps extension is a third-class lever. Glenohumeral abduction is also a third-class lever. DIP index finger pinch is a third-class lever.",
            clinicalImportance = "Fatigue in posterior neck muscles can lead to failure of this first-class system, causing the head to drop forward (often seen in postural fatigue or parkinsonian stance).",
            examinerTip = "Remember: Axis in the middle = First class. Effort in the middle = Third class. Load in the middle = Second class.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "First-class levers position the pivot point between opposing forces, allowing balance and direction adjustments.",
            relatedConcept = "First Class Biological Levers",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "If a surgeon alters the patellar tendon insertion site distally during an orthopaedic tibial tubercle osteotomy, how does this change the mechanical lever arm of the knee extensor mechanism?",
            options = listOf(
                "Moving the insertion distally increases the quadriceps internal moment arm, increasing extension torque but reducing the range of motion and velocity of extension",
                "Moving the insertion distally decreases the moment arm, forcing the quadriceps to contract with maximum speed and zero force",
                "It eliminates the knee axis completely, converting the joint into an unstable gliding plane",
                "It transforms the quadriceps from a third-class lever to a highly efficient first-class lever system"
            ),
            correctAnswer = "Moving the insertion distally increases the quadriceps internal moment arm, increasing extension torque but reducing the range of motion and velocity of extension",
            whyCorrect = "Moving the insertion distally increases the perpendicular distance (moment arm) from the knee axis to the tendon's line of pull. This increases the internal mechanical moment of the quadriceps, but the muscle must contract through a longer distance to produce the same joint range of motion, reducing velocity.",
            whyIncorrect = "It increases (does not decrease) the moment arm. The knee joint axis is not eliminated. It remains a third-class lever.",
            clinicalImportance = "Such osteotomies are performed to adjust patellofemoral tracking and contact stresses, but can alter gait biomechanics.",
            examinerTip = "Remember that changing tendon insertions alters the joint's movement range and force production in opposite ways.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Distal relocation of tendon insertions increases mechanical leverage but sacrifices movement velocity and range.",
            relatedConcept = "Surgical Alterations of Moment Arms",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "In muscular lever mechanics, how does the force-velocity relationship explain why maximum muscular torque can only be produced at slow contraction speeds?",
            options = listOf(
                "According to Hill's muscle equation, as contraction velocity increases, the number of active actin-myosin cross-bridge attachments decreases, reducing collective tension",
                "Fast contractions generate lower torque because synovial fluid viscosity increases to freeze muscle fiber movement",
                "Slow speeds allow the bone lever to lengthen physically to construct a bigger moment arm",
                "The nervous system completely coordinates muscle contraction to produce zero total force at fast velocities"
            ),
            correctAnswer = "According to Hill's muscle equation, as contraction velocity increases, the number of active actin-myosin cross-bridge attachments decreases, reducing collective tension",
            whyCorrect = "At high shortening velocities, actin and myosin filaments slide past each other so rapidly that fewer cross-bridges have time to form and generate tension. Consequently, maximum muscle force (and torque output) is produced during isometric or slow concentric contractions.",
            whyIncorrect = "Synovial fluid does not freeze movement. Bone length does not change with speed. The nervous system actually recruits more motor units during explosive movements, but individual sarcomere biochemistry limits the maximum force.",
            clinicalImportance = "In sports performance and rehabilitation, strength training for load hypertrophy is done at slower, controlled velocities to maximize high tension loading.",
            examinerTip = "Be ready to plot Hill's force-velocity curve: force decreases non-linearly as shortening velocity increases.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "SARCOMERE cross-bridge cycling dynamics limit force output at high shortening velocities.",
            relatedConcept = "Concentric Force Velocity Relationship",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),

        // === CENTER OF GRAVITY (19-24) ===
        VivaMcq(
            question = "Where is the anatomical Center of Gravity (COG) of a standard adult human located when standing in a neutral anatomical posture?",
            options = listOf(
                "Anterior to the second sacral vertebra (S2)",
                "Exactly at the xiphoid process of the sternum",
                "Anterior to the first cervical vertebra (C1), favoring the head weight",
                "Within the central medullary cavity of the left femur"
            ),
            correctAnswer = "Anterior to the second sacral vertebra (S2)",
            whyCorrect = "In a neutral adult, the center of gravity (or center of mass) is located in the midline of the pelvis, approximately anterior to the S2 vertebra (about 55% to 57% of total body height).",
            whyIncorrect = "The sternum is too superior. C1 is in the cervical spine, far too high. The left femur would mean a severe, uncoordinated asymmetry in neutral standing.",
            clinicalImportance = "A physical therapist must identify this location to safely position transfer belts or support patients with balance deficits.",
            examinerTip = "Remember the S2 landmark! It is a favorite trivia question for board exams.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "The human center of gravity lies anterior to the second sacral vertebra (S2) in a neutral standing posture.",
            relatedConcept = "Anatomical Center of Gravity",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "How does the anatomical Center of Gravity (COG) shift during late-stage pregnancy, and how does the body compensate for this biomechanically?",
            options = listOf(
                "The COG shifts anteriorly; to maintain balance, the mother hyperextends the lumbar spine (lumbar lordosis) to bring the LOG back within the BOS",
                "The COG shifts superiorly to the chest; compensated by slouching forward and flexing the thoracic spine",
                "There is no shift in COG during pregnancy because the fetus is weightless in amniotic fluid",
                "The COG shifts to the outer left knee; compensated by a permanent leg length discrepancy"
            ),
            correctAnswer = "The COG shifts anteriorly; to maintain balance, the mother hyperextends the lumbar spine (lumbar lordosis) to bring the LOG back within the BOS",
            whyCorrect = "The development of a fetus adds mass to the anterior pelvis, shifting the maternal COG anteriorly. To prevent falling forward, the base of support is widened, and the mother leans backwards (eccentrically loading lumbar extensors, generating hyperlordosis) to pull the Line of Gravity (LOG) back within the Base of Support (BOS).",
            whyIncorrect = "COG shifts forward and down/up based on weight, not purely superior. Amniotic fluid does not negate fetal mass. The center of mass does not shift unilaterally to the left knee.",
            clinicalImportance = "Explains why pregnant women commonly present with mechanical lower back pain and sacroiliac joint strain due to sustained erector spinae overwork.",
            examinerTip = "Link the anterior COG shift directly to the postural compensation of increased lumbar lordosis.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Anterior COG shifts are offset by backward trunk adjustments, increasing load on posterior spinal joints.",
            relatedConcept = "COG Shifts and Spinal Compensations",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Which of the following biomechanical strategies will most effectively increase physical stability in a patient performing dynamic balance exercises?",
            options = listOf(
                "Widening the base of support (BOS), lowering the Center of Gravity (COG), and keeping the Line of Gravity (LOG) centered within the BOS",
                "Narrowing the base of support, raising the COG, and positioning the LOG at the absolute edge of the BOS",
                "Standing on one leg on an unstable wobble board while elevating both hands above the head",
                "Compressing the joints to lock the cervical spine completely to prevent vestibular feedback"
            ),
            correctAnswer = "Widening the base of support (BOS), lowering the Center of Gravity (COG), and keeping the Line of Gravity (LOG) centered within the BOS",
            whyCorrect = "Stability is directly proportional to the area of the Base of Support (BOS) and inversely proportional to the height of the COG. Widening the BOS (e.g. feet apart) and lowering the COG (squatting slightly) keeps the Line of Gravity (LOG) well inside the base, maximizing balance.",
            whyIncorrect = "Narrowing the BOS and raising the COG reduces stability (making balance harder). wobble boards increase challenge, not stability. Vestibular feedback is critical for balance control.",
            clinicalImportance = "Informs how we train neurological patients (e.g. cerebellar ataxia)—teaching them to use wide-base gaits and assistive walking aids to increase safety.",
            examinerTip = "Know the three variables of stability: Size of BOS, Height of COG, and location of LOG relative to BOS.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Stability is enhanced by a larger base of support, a lower center of mass, and centralized alignment of the weight vector.",
            relatedConcept = "Factors Governing Postural Stability",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "What happens to a person’s postural equilibrium the instant their Line of Gravity (LOG) falls completely outside their Base of Support (BOS)?",
            options = listOf(
                "They immediately lose balance and must execute a stepping strategy or fall, as the gravity vector produces a net overturning moment",
                "They remain perfectly stationary due to active microcurrents in the metatarsophalangeal joints",
                "Their base of support automatically stretches via cellular expansion to find the gravity line",
                "They enter a zero-friction state, allowing them to glide effortlessly across the floor"
            ),
            correctAnswer = "They immediately lose balance and must execute a stepping strategy or fall, as the gravity vector produces a net overturning moment",
            whyCorrect = "When the Line of Gravity (weighted downward projection of COG) falls outside the margins of the Base of Support, equilibrium is lost. To avoid falling, the body must take a step to establish a new, wider BOS that encompasses the gravity line.",
            whyIncorrect = "Postural muscles cannot maintain a static position if the LOG is outside the BOS without dynamic adjustments. Cells do not expand, and friction is unaffected.",
            clinicalImportance = "Identifying the 'Limits of Stability' is essential for assessing fall risk and prescribing walking aids (canes/walkers) that extend the BOS.",
            examinerTip = "Explain the 'Stepping Strategy' as a functional biomechanical reaction to the gravity line crossing the boundaries of the base of support.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Perfect balance is impossible if the line of structural gravity lies outside the supportive margins of the base of support.",
            relatedConcept = "Limits of Stability and Falls",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "In the biomechanics of high jump, how does the Fosbury Flop technique allow athletes to clear a high bar even though their physical Center of Gravity (COG) passes underneath the bar?",
            options = listOf(
                "By arching the back dramatically, the athlete's body segments curve around the bar, projecting the mathematical COG outside and below the physical body",
                "The athlete's speed is so high that the gravitational constant decreases to zero momentarily",
                "The rubber jumping surface imparts a rotational magnetic charge that repels the hurdle bar",
                "Arching the spine activates the pelvic floor to create local upward thrust via air displacement"
            ),
            correctAnswer = "By arching the back dramatically, the athlete's body segments curve around the bar, projecting the mathematical COG outside and below the physical body",
            whyCorrect = "The center of gravity (COG) is a mathematical average point of mass. In a curved object (like a banana or an arched human body), the COG can lie outside the physical tissues. By arching backward over the bar, the athlete's mass curves around it. The bar passes through the hollow of the arch, while the COG actually passes beneath the bar, saving valuable kinetic energy.",
            whyIncorrect = "Gravity is always constant. No magnetic charges are generated, and air displacement from pelvic muscles is physically insignificant.",
            clinicalImportance = "Demonstrates how skeletal positioning can dissociate the center of mass from physical bone structures to optimize performance.",
            examinerTip = "Use this example to show that the center of gravity is a mathematical representation of cumulative mass distribution, not a physical bone node.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Segmental positioning can project the mathematical center of mass completely outside the physical boundaries of the body.",
            relatedConcept = "Segmental Center of Mass Distribution",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "What is the functional biomechanical relationship between the Center of Pressure (COP) and the Center of Gravity (COG) during quiet, upright postural standing?",
            options = listOf(
                "The COP is the point of application of the ground reaction forces, which must constantly swing back and forth around the COG to keep the body's weight vector contained",
                "The COP represents joint fluid pressure, and is completely unconnected to the gravity vector",
                "The COG and COP must remain perfectly locked on the exact same micron for survival",
                "COP is purely an acoustic variable measured only in auditory processing disorders"
            ),
            correctAnswer = "The COP is the point of application of the ground reaction forces, which must constantly swing back and forth around the COG to keep the body's weight vector contained",
            whyCorrect = "The Center of Pressure (COP) is the spatial average of all contact forces acting on the supportive surface. The COG is the body's mass center. During standing, the body sways forward and backward. The nervous system modulates muscles to shift the COP beyond the COG projection, creating corrective moments that drive the COG back to safety.",
            whyIncorrect = "COP is a mechanical force variable, not joint fluid pressure or acoustic. The COG and COP are never completely locked; they sway dynamically to maintain stability.",
            clinicalImportance = "Posturography systems track COP excursion (sway length) to diagnose vestibular, sensory, or neurological balance impairments.",
            examinerTip = "Remember: COG is the target; COP is the controller. The COP must sway wider than the COG to push the body back.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Systematic shifts in the Center of Pressure generate corrective torques that control the sway of the Center of Gravity.",
            relatedConcept = "Center of Pressure and Postural Sway",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),

        // === GAIT ANALYSIS (25-30) ===
        VivaMcq(
            question = "Which of the following correctly defines the spatial gait parameter of 'Step Length' and how does it contrast with 'Stride Length'?",
            options = listOf(
                "Step Length is the distance between consecutive points of heel contact of opposite feet, whereas Stride Length is the distance between two consecutive heel strikes of the same foot",
                "Step Length is measured in steps per minute, while Stride Length is measured in degrees of joint flexion",
                "Stride Length is the vertical height of leg swing, while Step Length is purely horizontal",
                "Both terms are identical in all biomechanical textbooks and can be used interchangeably"
            ),
            correctAnswer = "Step Length is the distance between consecutive points of heel contact of opposite feet, whereas Stride Length is the distance between two consecutive heel strikes of the same foot",
            whyCorrect = "Step length is measured from the heel strike of one foot to the heel strike of the opposite foot. Stride length represents a full gait cycle, measured from the heel strike of one foot to the next heel strike of that same foot (making a stride equal to two steps).",
            whyIncorrect = "Steps per minute represents cadence (temporal parameter). Joint flexion degrees represent kinematics. Step and stride lengths are both horizontal linear distances, but cover different cycle sizes.",
            clinicalImportance = "Asymmetry in step lengths (e.g. left step shorter than right step) is a classic indicator of unilateral pain, weakness, or joint restriction.",
            examinerTip = "Make sure you can draw a simple layout showing 'Heel-strike (Left) -> Heel-strike (Right) -> Heel-strike (Left)' to indicate these parameters.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "A stride comprises two steps, representing the complete kinematic sequence of both limbs during locomotion.",
            relatedConcept = "Spatial Parameters of Gait",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "During preprom promoted eccentric control of the foot descending to the floor after heel strike, which muscle group is active, and what gait anomaly occurs if this muscle group has zero strength?",
            options = listOf(
                "The ankle dorsiflexors (e.g., tibialis anterior) contract eccentrically; weakness causes 'foot slap' after initial contact",
                "The plantar flexors (soleus) contract eccentrically; weakness causes hyperextension of the knee",
                "The quadriceps contract concentrically; weakness causes the ankle to lock in full eversion",
                "The hip adductors contract isometrically; weakness causes the leg to swing out sideways"
            ),
            correctAnswer = "The ankle dorsiflexors (e.g., tibialis anterior) contract eccentrically; weakness causes 'foot slap' after initial contact",
            whyCorrect = "Immediately after heel strike, the ankle dorsiflexors (tibialis anterior, extensor digitorum longus) must contract eccentrically (lengthening while resisting) to control plantarflexion, allowing the foot to lower softly. If weak, gravity pulls the foot down abruptly, causing a loud 'foot slap'.",
            whyIncorrect = "Plantar flexors are active in late stance for push-off, not early loading heel descent. Hip adductors assist pelvis stability, they do not manage ankle descent. Quadriceps manage knee flexion, not ankle flexion.",
            clinicalImportance = "Allows clinicians to deduce tibialis anterior impairment (e.g., common peroneal nerve injury) simply by listening to the auditory footprint of a patient's walk.",
            examinerTip = "Remember that early loading response requires eccentric tibialis anterior activity to prevent foot slap.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Ankle dorsiflexors contract eccentrically during loading response to control the descent of the foot.",
            relatedConcept = "Eccentric Muscle Control in Loading Response",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "In a patient presenting with an uncompensated Trendelenburg gait due to left gluteus medius weakness, what kinematic deviation is observed during the stance phase of the left leg?",
            options = listOf(
                "The right side of the pelvis drops downward during the swing phase of the right leg",
                "The left hip suddenly dislocates anteriorly and rotates outward by 90 degrees",
                "The whole trunk leans heavily to the right side to vault the right leg over the floor",
                "The left knee locks in hyperextension while the right foot is held in full eversion"
            ),
            correctAnswer = "The right side of the pelvis drops downward during the swing phase of the right leg",
            whyCorrect = "When the left leg is in single-limb stance, the left hip abductors (gluteus medius/minimus) must contract to hold the right (swing-side) pelvis up. If the left gluteus medius is weak, it cannot generate enough abductor moment, causing the contralateral (right) pelvis to drop.",
            whyIncorrect = "The pelvic drop occurs on the contralateral (right) side, not ipsilateral. An anterior hip dislocation does not occur. Leaning the trunk to the left (same side) is a *compensated* Trendelenburg posture (not uncompensated).",
            clinicalImportance = "Identifying pelvic instability helps therapists target hip abductor strengthening, pelvic control, and core stabilization routines.",
            examinerTip = "Remember: Weak left abductor = Right pelvis drops (uncompensated) OR Left trunk lean (compensated).",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Hip abductors on the stance limb must contract to stabilize and hold up the opposite side of the pelvis.",
            relatedConcept = "Trendelenburg Pathomechanics",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "What is the relative distribution of the stance phase versus the swing phase during a normal human walking cycle at standard walking speeds?",
            options = listOf(
                "Stance phase occupies approximately 60% of the gait cycle, while Swing phase occupies approximately 40%",
                "Stance phase occupies 10% of the cycle, while Swing occupies 90% in slow walking",
                "Stance phase is exactly 50% and Swing is exactly 50% under all walking velocities",
                "Stance phase represents 100% of gait because swing phase is only present during running"
            ),
            correctAnswer = "Stance phase occupies approximately 60% of the gait cycle, while Swing phase occupies approximately 40%",
            whyCorrect = "In a single gait cycle of normal walking, stance phase (foot in contact with ground) takes up about 60% of the time, which includes two periods of double-limb support. The swing phase (foot in air) takes up the remaining 40%.",
            whyIncorrect = "A 10/90 distribution is biomechanically impossible unless flying. 50/50 occurs at very fast speeds but is not the standard distribution. Running features a float phase (zero stance), while walking always has stance.",
            clinicalImportance = "In neurological or painful conditions, patients often decrease their stance phase to minimize weight-bearing time, altering this 60/40 ratio.",
            examinerTip = "Be ready to break down stance into initial contact, loading response, mid-stance, terminal stance, and pre-swing.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Normal walking gait is distributed as 60% stance phase and 40% swing phase.",
            relatedConcept = "Phases of the Gait Cycle",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "How does an antalgic gait pattern present in a patient with painful osteoarthritis of the right knee, and what is its biomechanical purpose?",
            options = listOf(
                "Reduced stance phase duration on the right leg to decrease painful compressive joint loading, combined with a shortened left step length",
                "Locked knee extension at heel strike to increase loading rates on the painful joint",
                "Perfect symmetry of step lengths and pelvic height to trick the brain into ignoring pain",
                "Hopping on the left foot continuously while holding the right knee flexed in full external rotation"
            ),
            correctAnswer = "Reduced stance phase duration on the right leg to decrease painful compressive joint loading, combined with a shortened left step length",
            whyCorrect = "Antalgic gait is a pain-relieving walking pattern. The patient reduces the weight-bearing time (stance phase) on the painful (right) limb. To quickly transfer weight back to the safe side, they speed up swing of the healthy (left) leg, resulting in a short step length on the left.",
            whyIncorrect = "Locked extension increases joint compression forces and pain. perfect symmetry is lost in antalgic gaits. Continuous hopping is an extreme compensation, not a standard antalgic walking pattern.",
            clinicalImportance = "Directs the clinician to target pain relief at the affected joint while correcting gait asymmetries with assistive devices (e.g. a cane in the contralateral hand).",
            examinerTip = "Remember that ‘antalgic’ literally means ‘anti-pain’. The main feature is decreased stance duration on the painful side.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Antalgic gait minimizes stance time on the painful limb, shifting loads quickly to the unaffected side.",
            relatedConcept = "Antalgic Gait Characteristics",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "In a patient with complete foot drop due to common peroneal nerve paralysis, what kinematic compensation is typically observed during the swing phase to ensure the toes clear the ground?",
            options = listOf(
                "Excessive hip and knee flexion on the swing limb (Steppage gait) to lift the drop foot higher off the floor",
                "Sustained trunk extension with backward leaning to drag the heel along pelvic pathways",
                "Full eversion of the opposite stance ankle to tilt the entire pelvis down on the swing side",
                "Severe pelvic rotation to throw the foot forward in a straight line with zero knee flexion"
            ),
            correctAnswer = "Excessive hip and knee flexion on the swing limb (Steppage gait) to lift the drop foot higher off the floor",
            whyCorrect = "When the common peroneal nerve is damaged, calf dorsiflexors are paralyzed, and the foot hangs in plantarflexion (drop foot) during swing. To prevent the toes from catching on the floor, the patient compensates by flexing the hip and knee excessively on that side (Steppage gait) to lift the foot clear of the ground.",
            whyIncorrect = "Trunk extension does not lift the foot. Opposite ankle eversion does not assist swing-side clearance. Zero knee flexion would cause the toes to drag, which is the exact failure being avoided.",
            clinicalImportance = "Prescribing an Ankle-Foot Orthosis (AFO) holds the foot in neutral ankle dorsiflexion, eliminating the need for this fatiguing steppage compensation.",
            examinerTip = "Steppage gait is a classic board question. Connect peroneal nerve injury, drop foot, and excessive hip/knee flexion swing compensation.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Steppage gait uses high hip and knee flexion to clear a paralyzed, dropped foot during the swing phase.",
            relatedConcept = "Drop Foot Compensations",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),

        // === JOINT MECHANICS (31-36) ===
        VivaMcq(
            question = "Which type of joint lubrication occurs under high-load, low-velocity conditions when a single layer of boundary molecules prevents direct protein-to-protein friction?",
            options = listOf(
                "Boundary lubrication",
                "Hydrodynamic fluid lubrication",
                "Elastohydrodynamic lubrication",
                "Acoustic cavitation lubrication"
            ),
            correctAnswer = "Boundary lubrication",
            whyCorrect = "Boundary lubrication occurs when surface-active molecules (glycoproteins like lubricin or hyaluronic acid) bind directly to articular cartilage. When highly compressed, these chemical boundary layers prevent direct contact between opposing cartilage surfaces, reducing friction.",
            whyIncorrect = "Hydrodynamic fluid lubrication depends on relative joint velocity of fluids. Elastohydrodynamic lubrication includes cartilage elastic deformation to trap thin fluid films. Acoustic cavitation is an ultrasound phenomenon, not a joint lubrication style.",
            clinicalImportance = "Boundary lubrication is vital in static positions (standing still) or at the end of range of motion when sliding speeds approach zero.",
            examinerTip = "Remember that boundary lubrication does not depend on viscosity or velocity of synovial fluid.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Boundary lubrication relies on chemical boundary coatings to minimize joint friction during static high-load conditions.",
            relatedConcept = "Synovial Mechanics and Friction",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "When plotting the stress-strain curve of a ligament, what does the 'toe region' represent biomechanically?",
            options = listOf(
                "The initial phase of loading where crimped collagen fibers straighten out with minimal resistance and high strain",
                "The catastrophic region where the ligament ruptures completely",
                "The highly rigid segment where the tissue behaves like solid steel",
                "The point where fluid flow exits the cellular membrane entirely"
            ),
            correctAnswer = "The initial phase of loading where crimped collagen fibers straighten out with minimal resistance and high strain",
            whyCorrect = "At rest, collagen fibers in ligaments are slightly wavy or crimped. The toe region represents the first part of the stress-strain curve where very little stress (force) is needed to straighten out these waves, resulting in high strain (length change) with low load.",
            whyIncorrect = "Catastrophic rupture occurs in the failure region. High rigidity occurs in the linear/elastic region. Fluid flow exit is related to cartilage exudation, not ligament fiber crimp.",
            clinicalImportance = "Represents normal, everyday physiological range. Most basic movements occur within the toe region to prevent mechanical damage to joint ligaments.",
            examinerTip = "Examiners often ask you to define 'crimp' when discussing the toe region of connective tissues.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "The toe region reflects the straightening of wavy collagen fibers under low physiological loads.",
            relatedConcept = "Stress Strain Curve of Collagenous Tissues",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "How do the viscoelastic properties of articular cartilage respond to a sustained, constant compressive mechanical load over 3 hours?",
            options = listOf(
                "The tissue undergoes creep, showing a slow, progressive increase in deformation over time as water is squeezed out of the proteoglycan matrix",
                "The tissue expands instantly to double its thickness, blocking any joint movement",
                "The cartilage behaves as a purely elastic solid with zero time-dependent changes",
                "The compressive load is converted into a high-voltage piezoelectric wave that dissolves all water"
            ),
            correctAnswer = "The tissue undergoes creep, showing a slow, progressive increase in deformation over time as water is squeezed out of the proteoglycan matrix",
            whyCorrect = "Articular cartilage is viscoelastic, meaning its stress-strain behavior is time-dependent. Under sustained compression, fluid (water) is slowly exuded through the porous proteoglycan matrix. This time-dependent progressive deformation is called 'creep'. It stops when the internal swelling pressure of proteoglycans equals the external load.",
            whyIncorrect = "Cartilage does not expand under load. It is viscoelastic, not purely elastic (which would return to shape immediately without delay). No high-voltage wave dissolves water.",
            clinicalImportance = "Sustained static loading (like prolonged standing without moving) reduces fluid shielding, placing greater mechanical stress on the solid collagen matrix.",
            examinerTip = "Understand both creep (increasing strain under constant stress) and stress relaxation (decreasing stress under constant strain).",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Viscoelastic tissues display creep, presenting progressive deformation over time under a constant external load.",
            relatedConcept = "Viscoelasticity and Creep in Cartilage",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "What is the mechanical difference between the close-packed position and loose-packed (open) position of a synovial joint?",
            options = listOf(
                "The close-packed position features maximal joint congruency, tightest ligaments, and minimal joint play, whereas the loose-packed position has lax ligaments and maximum joint play",
                "The close-packed position allows the highest range of motion, while the loose-packed position is locked by bone spurs",
                "The close-packed position exists only during sleep, while the loose-packed position is only present during high-speed running",
                "The close-packed position features zero friction, while the loose-packed position has a high coefficient of static friction"
            ),
            correctAnswer = "The close-packed position features maximal joint congruency, tightest ligaments, and minimal joint play, whereas the loose-packed position has lax ligaments and maximum joint play",
            whyCorrect = "In a close-packed position (e.g. full knee extension), joint surfaces are highly congruent, and major ligaments/capsules are maximally spiraled and taut. Joint volume is minimized, providing high stability with minimal accessory motion (joint play). In loose-packed positions, the joint is lax, making it ideal for joint mobilization techniques.",
            whyIncorrect = "The close-packed position limits movement range due to tension. It is not limited by spurs in normal joints, nor is open-packed restricted to specific activities or sleep. Friction coefficients remain low in all normal articular states.",
            clinicalImportance = "Informs manual therapy: always perform joint mobilization assessments and glides in the loose-packed (resting) position where ligamentous tension is minimized.",
            examinerTip = "Be ready to list close- and loose-packed positions for the shoulder, hip, knee, and ankle.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "The close-packed position maximizes joint congruency and ligament tension, providing natural structural stability.",
            relatedConcept = "Joint Congruency and Mobilization Positions",
            reference = "Maitland's Peripheral Manipulation, 5th Ed"
        ),
        VivaMcq(
            question = "Why is the glenohumeral (shoulder) joint highly prone to mechanical subluxation and dislocation compared to the acetabulofemoral (hip) joint?",
            options = listOf(
                "The glenoid cavity is shallow, covering less than one-third of the humeral head, prioritizing range of motion over structural stability",
                "The glenohumeral joint is a hinge joint, limiting movement to only one directional axis",
                "The shoulder joint completely lacks a joint capsule or surrounding muscle support",
                "The humeral head is made of highly porous bone that bends under active compression"
            ),
            correctAnswer = "The glenoid cavity is shallow, covering less than one-third of the humeral head, prioritizing range of motion over structural stability",
            whyCorrect = "The hip joint (acetabulofemoral) has deep bony coverage with a high-congruency ball-and-socket socket, providing deep structural stability. The glenohumeral joint features a shallow glenoid socket, which provides less bony stability but allows a massive range of motion. It relies heavily on dynamic muscular stabilizers (rotator cuff) to prevent displacement.",
            whyIncorrect = "The shoulder is a ball-and-socket joint, not a hinge joint. It has a comprehensive capsule and strong surrounding musculature. Humeral heads are solid cortical/cancellous bone, they are not porous bending segments.",
            clinicalImportance = "Highlights why shoulder rehab focuses intensely on stabilizing muscle strengthening, while hip rehab often targets range of motion and joint load management.",
            examinerTip = "Contrast the shoulder (unstable, high mobility, dynamic muscle support) with the hip (very stable, lower relative mobility, deep bony socket).",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "The glenohumeral joint sacrifices osseous stability to maximize multi-axial spatial mobility.",
            relatedConcept = "Joint Congruency and Stability Trade-offs",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "At what knee flexion angles is the Patellofemoral Joint Reaction Force (PFJRF) greatest during a weighted closed-chain squat, and why?",
            options = listOf(
                "At deep knee flexion (90 degrees or more), because the sharper angulation of the quadriceps tendon around the femur squeezes the patella heavily into the trochlear groove",
                "At full knee extension (0 degrees), because there is zero contact area between cartilage surfaces",
                "At posterior flexion (150 degrees), because the hamstring muscles pull the patella backward into the tibia",
                "PFJRF remains constant across all knee angles because quadriceps force is static"
            ),
            correctAnswer = "At deep knee flexion (90 degrees or more), because the sharper angulation of the quadriceps tendon around the femur squeezes the patella heavily into the trochlear groove",
            whyCorrect = "The patellofemoral joint reaction force (PFJRF) is a vector sum of quadriceps tension and patellar tendon tension. As knee flexion angles increase, the angle between these two tendons becomes sharper. This vector mechanical angle directs a much larger compressive force pushing the patella into the anterior femoral groove.",
            whyIncorrect = "At 0 degrees, the patella sits superiorly and has minimal compression against the femur. Hamstrings do not attach to the patella. PFJRF varies significantly with knee angle and muscle loading.",
            clinicalImportance = "In patients with patellofemoral pain syndrome (PFPS), clinicians limit deep squat angles to reduce pain, using shallow squat ranges (0 to 45 degrees) instead.",
            examinerTip = "Draw the vector addition of quadriceps force and patellar tendon force to show why flexion increases joint compression.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Increasing knee flexion sharpens tendon vectors, multiplying patellofemoral compressive joint forces.",
            relatedConcept = "Patellofemoral Joint Loading",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),

        // === KINETICS (37-41) ===
        VivaMcq(
            question = "Using the concept of Impulse (Force x Time), how does a soft floor mat or wearing athletic shoes protect joints from high-impact landings?",
            options = listOf(
                "They increase the duration of the impact collision, which reduces the peak force magnitude required to bring the falling body to a stop",
                "They generate an active upward mechanical force that repels gravity entirely",
                "They convert the dynamic kinetic landing into an immediate vacuum status",
                "They shorten the collision time to absolute zero, preventing force from entering the foot"
            ),
            correctAnswer = "They increase the duration of the impact collision, which reduces the peak force magnitude required to bring the falling body to a stop",
            whyCorrect = "The impulse-momentum theorem states that Impulse (Force * Time) equals the change in momentum. To stop a landing body, the change in momentum is constant. By increasing the collision time (using compressible foam or shoes), the peak force of the landing is significantly reduced, sparing joint structures.",
            whyIncorrect = "Mats do not alter gravity or create vacuums. Shortening the collision time to zero would make the peak impact force infinitely high, destroying the joint.",
            clinicalImportance = "Highly relevant to playground safety surfaces and athletic shoes designed for high-impact activities (running, jumping) to prevent stress fractures.",
            examinerTip = "Impulse is Force times time (J = F * dt). Increasing the 'dt' is the standard way to protect structures in any landing.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Spreading a momentum change over a longer duration reduces the peak force transmitted to the skeleton.",
            relatedConcept = "Impulse Momentum Relationship in Impact",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "How is mechanical Power calculated during dynamic human movement, and what does it represent during explosive physical tasks?",
            options = listOf(
                "Power is the rate of performing work (Force * Velocity); reflecting both the force capacity and the speed of execution during explosive tasks",
                "Power is mass divided by acceleration; representing the weight of muscle tissue",
                "Power is joint angle multiplied by body surface area; measured purely in square decibels",
                "Power represents the total calories burned during a continuous 24-hour resting cycle"
            ),
            correctAnswer = "Power is the rate of performing work (Force * Velocity); reflecting both the force capacity and the speed of execution during explosive tasks",
            whyCorrect = "Mechanical Power (P) is calculated as Work divided by Time, or Force (F) multiplied by velocity (v). In physical tasks (like jumping or sprinting), it represents how quickly a muscle can generate high force output.",
            whyIncorrect = "Power is not mass/acceleration (which has no physical standard meaning). Joint angle * area is incorrect. Calories burned at rest represents basal metabolic rate.",
            clinicalImportance = "In athletic training, power output is a key performance index. Power declines rapidly with age, making power training essential to prevent falls in elderly populations.",
            examinerTip = "Remember that Power combines both force capacity (strength) and velocity (speed). You need both to be powerful.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Mechanical power is the rate of doing work, requiring both high force capability and contraction velocity.",
            relatedConcept = "Power Output in Human Movement",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "During a single-leg squat, how does the joint moment at the knee compare when squatting with the tibia vertical versus letting the knee slide forward beyond the toes?",
            options = listOf(
                "Allowing the knee to slide forward anterior to the toes increases the horizontal distance from the knee joint axis to the weight vector, dramatically increasing the knee joint extensor moment",
                "Letting the knee slide forward moves the knee joint axis outside the earth's gravity field, reducing torque to safe zero levels",
                "A vertical tibia multiplies cartilage shear stress at the hip joint by 100 times",
                "There is zero difference; joint moments are governed purely by skeletal mass and are not impacted by position coordinates"
            ),
            correctAnswer = "Allowing the knee to slide forward anterior to the toes increases the horizontal distance from the knee joint axis to the weight vector, dramatically increasing the knee joint extensor moment",
            whyCorrect = "As the knee slides forward, the horizontal distance (moment arm) between the knee rotational axis and the line of gravity (and external ground reaction force) increases. This larger moment arm increases the external flexion torque, demanding much higher quadriceps force to stabilize the knee.",
            whyIncorrect = "Tissues do not leave the earth's gravity field. A vertical tibia increases hip moment (by shifting the hip backward), but it does not multiply hypocardial shear by 100. Positions change moment arms, which dramatically alter joint torque.",
            clinicalImportance = "Explains why 'knees over toes' during squats increases knee loading, which is used for advanced tendon conditioning but avoided in acute cartilage injuries.",
            examinerTip = "Point out that keeping the tibia vertical shifts the load to the hip extensors (gluteals), while a forward knee shift targets the knee extensors (quadriceps).",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Altering segment alignment changes the relative moment arms, shifting stress between knee and hip joints.",
            relatedConcept = "Joint Moment Distribution during Squatting",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "What is the difference between linear kinetics and linear kinematics in human biomechanics?",
            options = listOf(
                "Kinetics studies the forces and torques that generate or modify motion, while kinematics describes the spatio-temporal aspects of motion (displacement, velocity) without regard to forces",
                "Kinematics focuses purely on cellular chemistry, while kinetics looks at macro-level global maps",
                "Kinetics is restricted to fluid motions, while kinematics is restricted to solid bone analysis",
                "There is no difference; they are different spellings of the same educational classification"
            ),
            correctAnswer = "Kinetics studies the forces and torques that generate or modify motion, while kinematics describes the spatio-temporal aspects of motion (displacement, velocity) without regard to forces",
            whyCorrect = "Kinematics describes movement parameters (how fast, how far, which direction, using metrics like velocity, curves, and angles). Kinetics investigates the causes of that movement (mass, forces, joint reactions, torques, gravity, and muscle efforts).",
            whyIncorrect = "They do not refer to cellular chemistry vs macro-level, nor are they limited to fluids vs solids. They represent two distinct branches of mechanics.",
            clinicalImportance = "A gait camera system measures kinematics (joint angles). A force plate measures kinetics (ground reaction forces). Clinicians combine both for comprehensive gait assessments.",
            examinerTip = "Remember the simple distinction: Kinematics = appearance of motion. Kinetics = causes of motion.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Kinetics investigates force causes, while kinematics quantitatively details movement paths and rates.",
            relatedConcept = "Kinematics vs Kinetics",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "In mechanical physics, how does an athlete adjust their Moment of Inertia (I) to perform a rapid high-dive rotation compared to slow rotational diving?",
            options = listOf(
                "They tuck their legs and arms tight to their chest to minimize their radius of gyrate, lowering their moment of inertia and increasing angular velocity",
                "They fully extend their trunk and arms, increasing the moment of inertia to speed up rotation",
                "They release compressed abdominal air to create a local vacuum thrust",
                "Moment of inertia is fixed by adult bone mass and cannot be adjusted by modifying body position"
            ),
            correctAnswer = "They tuck their legs and arms tight to their chest to minimize their radius of gyrate, lowering their moment of inertia and increasing angular velocity",
            whyCorrect = "According to the conservation of angular momentum (H = I * w), angular momentum is constant during a dive. Moment of inertia (I) is resistance to rotation, which depends on mass distribution (I = m * r²). Tucking pulls mass closer to the axis, reducing I, which causes angular velocity (w) to increase dramatically.",
            whyIncorrect = "Fully extending arms increases I, which slows down rotation. Air release from the abdomen has zero effect on rotation. The moment of inertia is highly dynamic and depends on body positioning.",
            clinicalImportance = "In sports rehabilitation and biomechanics, understanding mass distribution is vital for refining gymnastics, figure skating, or high-jump performances.",
            examinerTip = "Be ready to write down the conservation of angular momentum formula: I1 * w1 = I2 * w2.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Reducing mass distribution distance minimizes resistance to rotation, increasing angular motion speed.",
            relatedConcept = "Moment of Inertia and Angular Motion",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),

        // === KINEMATICS (42-46) ===
        VivaMcq(
            question = "How many rotational Degrees of Freedom (DOF) does a triaxial synovial joint like the glenohumeral or acetabulofemoral joint possess?",
            options = listOf(
                "Three degrees of freedom (flexion/extension, abduction/adduction, internal/external rotation)",
                "One degree of freedom (pure linear sliding along the longitudinal axis)",
                "Exactly six degrees of freedom, including cellular vibration and auditory popping",
                "Zero, because biological joint mechanics are too organic to have mathematical boundaries"
            ),
            correctAnswer = "Three degrees of freedom (flexion/extension, abduction/adduction, internal/external rotation)",
            whyCorrect = "A ball-and-socket joint allows rotation around three orthogonal/perpendicular axes, corresponding to three anatomical planes of movement: sagittal (flexion/extension), frontal (abduction/adduction), and transverse (internal/external rotation).",
            whyIncorrect = "One DOF is typical of hinge joints (e.g., humeroulnar). Six DOFs might represent both translation and rotation in free space, but ball-and-socket joints typically have 3 rotational DOFs. Biological joints follow clear mathematical laws.",
            clinicalImportance = "Allows clinicians to design multi-planar assessment and rehabilitation programs for complex joints.",
            examinerTip = "Be clear on the difference between rotational DOFs (flexion, abduction, rotation) and translational DOFs (sliding).",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Triaxial synovial joints permit selective rotation in three anatomical planes, yielding three degrees of freedom.",
            relatedConcept = "Degrees of Freedom in Synovial Joints",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "What does the Instantaneous Center of Rotation (ICR) represent during dynamic joint movement, and why is this center not fixed in human knee motion?",
            options = listOf(
                "The unique axis of rotation at any specific millisecond of joint movement; it shifts along a J-shaped curve in the knee because the joint condyles glide and roll simultaneously",
                "The temperature of synovial fluid at the center of the femoral groove during jumping",
                "The point where bony friction forces reach absolute zero during terminal extension",
                "A diagnostic point located purely within the acoustic apparatus of the middle ear"
            ),
            correctAnswer = "The unique axis of rotation at any specific millisecond of joint movement; it shifts along a J-shaped curve in the knee because the joint condyles glide and roll simultaneously",
            whyCorrect = "Human joints are rarely perfect pivots. In the knee, the large femoral condyle rolls and glides across the smaller tibia. Due to this asymmetry and ligament constraints, the axis of rotation changes as the knee flexes, tracing out a curved pathway (J-curve) of Instantaneous Centers of Rotation (ICR).",
            whyIncorrect = "The ICR has nothing to do with joint temperature, absolute zero friction, or the acoustic ear anatomy.",
            clinicalImportance = "Highly relevant to prosthetic knee joints and orthotic hinge designs, which must mimic this multi-center shift to prevent shearing and skin breakdown.",
            examinerTip = "Explain that a variable ICR is why simple single-axis hinges in braces can sometimes pinch or slip during deep squatting.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Osseous rolling and gliding cause joint axes to shift dynamically during normal physiological movement.",
            relatedConcept = "Instantaneous Center of Rotation",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "According to the Concave-Convex Rule of arthrokinematics, how do the roll and glide movements align during active shoulder abduction at the glenohumeral joint?",
            options = listOf(
                "The convex humeral head rolls superiorly and glides inferiorly on the concave glenoid fossa",
                "The convex femoral head rolls anteriorly and glides anteriorly on the acetabular rim",
                "Both surfaces undergo pure spin with zero roll or glide",
                "The concave glenoid moves laterally while the humerus remains completely stationary"
            ),
            correctAnswer = "The convex humeral head rolls superiorly and glides inferiorly on the concave glenoid fossa",
            whyCorrect = "According to the concave-convex rule: when a convex surface moves on a stable concave surface, the accessory glide occurs in the direction OPPOSITE to the bone's physiologic roll. During shoulder abduction, the humerus rolls superiorly (upwards), so it must glide inferiorly (downwards) to avoid impinging against the acromion.",
            whyIncorrect = "Femur/acetabulum refers to the hip, not shoulder. Glenohumeral abduction is not pure spin. The glenoid remains relatively stable compared to the humerus (though the scapula rotates later).",
            clinicalImportance = "In rehabilitation, therapists perform inferior joint glides (mobilization) to restore restricted shoulder abduction.",
            examinerTip = "This is a must-know rule! Convex on concave = roll and glide in opposite directions. Concave on convex = roll and glide in the same direction.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Convex-on-concave arthrokinematic motion requires opposite roll and glide directions to preserve joint centering.",
            relatedConcept = "Arthrokinematic Concave Convex Rules",
            reference = "Maitland's Peripheral Manipulation, 5th Ed"
        ),
        VivaMcq(
            question = "Which of the following coordinates represents the key physical parameters of linear kinematics?",
            options = listOf(
                "Linear displacement, velocity, and acceleration",
                "Skeletal muscle volume, density, and oxygenation levels",
                "Synovial fluid viscosity, lubrication coefficients, and temperature",
                "Joint compression moments, friction forces, and shear forces"
            ),
            correctAnswer = "Linear displacement, velocity, and acceleration",
            whyCorrect = "Linear kinematics is the branch of biomechanics that describes the movement of a body along a straight or curved path, using parameters like displacement (meters), velocity (m/s), and acceleration (m/s²).",
            whyIncorrect = "Muscle volume is anatomical/physiological, not kinematic. Fluid viscosity is kinetic/fluid mechanics. Compression, friction, and shear represent kinetic force variables.",
            clinicalImportance = "Used by therapists to evaluate hand speed, step velocity, or pelvic acceleration changes during functional training.",
            examinerTip = "Differentiate linear kinematics (straight path) from angular kinematics (rotation around joint axes).",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Kinematics maps movement spatial positions and rates without investigating the underlying forces.",
            relatedConcept = "Linear Kinematical Parameters",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "What is the biomechanical difference between open kinetic chain (OKC) exercises and closed kinetic chain (CKC) exercises during lower extremity rehabilitation?",
            options = listOf(
                "In OKC, the distal segment is free to move in space while the proximal segment is stable; in CKC, the distal segment is fixed to a surface and movement occurs at proximal segments",
                "OKC exercises are entirely restricted to underwater therapy, while CKC exercises use continuous high-tension springs",
                "OKC exercises produce zero muscle contraction, whereas CKC exercises generate destructive joint shearing",
                "There is no difference; both terminology classifications are completely obsolete in modern therapy"
            ),
            correctAnswer = "In OKC, the distal segment is free to move in space while the proximal segment is stable; in CKC, the distal segment is fixed to a surface and movement occurs at proximal segments",
            whyCorrect = "In an open kinetic chain (OKC) exercise (e.g., knee extension machine, biceps curl), the distal limb segment (foot/hand) moves freely in space. In a closed kinetic chain (CKC) exercise (e.g., squats, push-ups), the distal segment is fixed to a stable surface (floor/wall), causing movement at multiple proximal segments simultaneously.",
            whyIncorrect = "Chain definitions depend on distal fixation, not underwater or spring setups. Both generate strong, functional muscle contractions.",
            clinicalImportance = "OKC allows isolated muscle strengthening (e.g. quadriceps after ACL tear), while CKC provides functional joint compression and balance training.",
            examinerTip = "Be ready to classify everyday movements (jumping, writing, swimming) into kinetic chain types.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Closed kinetic chains involve fixed distal segments, distributing compressive forces across multiple joint levels.",
            relatedConcept = "Open vs Closed Kinetic Chains",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),

        // === POSTURE (47-50) ===
        VivaMcq(
            question = "From a biomechanical standpoint, why does performing a squat lift (knees bent, spine flat) load the lower lumbar spine much less than a stoop lift (knees straight, spine flexed)?",
            options = listOf(
                "The squat lift minimizes the horizontal moment arm of the load relative to the lumbar segments, reducing compression and shear loads on the L5-S1 disc",
                "The squat lift eliminates gravitational attraction entirely on the upper extremity trunk",
                "A bent knee posture turns the lower leg into a highly efficient first-class mechanical lever",
                "Bending the knees generates an upward vacuum force from the plantar arches"
            ),
            correctAnswer = "The squat lift minimizes the horizontal moment arm of the load relative to the lumbar segments, reducing compression and shear loads on the L5-S1 disc",
            whyCorrect = "Bending the knees enables the lifter to position the object close to their pelvis/hips. This minimizes the moment arm of the load relative to the lumbar spine, which reduces the required internal muscle force and the resulting compressive and shear loads on the intervertebral discs.",
            whyIncorrect = "Gravity remains constant. Squat lifts do not transition legs into first-class levers. Plantar arches cannot generate upward vacuum forces.",
            clinicalImportance = "This mechanical principle is the basis of back safety and ergonomic lifting instruction across industrial and clinical settings.",
            examinerTip = "Emphasize that muscle force generates the majority of spinal compression; reducing the load's moment arm decreases the required muscle force.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "Positioning loads closer to the body reduces moment arms, sparing spinal joints from high compression.",
            relatedConcept = "Spinal Ergonomics and Safe Lifting",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "During quiet standing posture, what biomechanical mechanism controls the subtle postural sway of the body's Center of Mass (COM)?",
            options = listOf(
                "Continuous low-level eccentric/concentric contractions of anti-gravity muscles (especially soleus and gastrocnemius) to correct the forward fall of the gravity line",
                "Continuous rapid micro-dislocations of the patella to offset balance shifts",
                "Atmospheric wind pressure pushing the front of the chest to balance gravity",
                "Spontaneous micro-vibrations of the spinal cord to generate local heat currents"
            ),
            correctAnswer = "Continuous low-level eccentric/concentric contractions of anti-gravity muscles (especially soleus and gastrocnemius) to correct the forward fall of the gravity line",
            whyCorrect = "In quiet standing, the Line of Gravity (LOG) falls slightly anterior to the ankle joint axis, creating a natural forward overturning moment. This forward sway is controlled by low-level, reflexive contractions of the ankle plantarflexors (soleus and gastrocnemius), which pull the body back.",
            whyIncorrect = "Patellar dislocations do not occur in normal balance, and wind pressure or spinal cord vibrations do not manage normal postural sways.",
            clinicalImportance = "Deficits in this ankle strategy (often seen in peripheral neuropathy or aging) lead to unstable balance, requiring hip/stepping strategies.",
            examinerTip = "Study the three postural strategies: Ankle Strategy, Hip Strategy, and Stepping Strategy, in order of magnitude.",
            subject = "Biomechanics",
            difficulty = "Moderate",
            learningPoint = "Ankle plantarflexors contract reflexively to counteract the biomechanical forward sway of upright standing.",
            relatedConcept = "Postural Sway Control Strategies",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "How does chronic Forward Head Posture (FHP) alter the muscular loading and mechanical stress across the cervical spine segments?",
            options = listOf(
                "It increases the external flexion moment arm of the head's weight, demanding massive, sustained contraction of suboccipital muscles and increasing facet joint compression",
                "It shifts the head's gravity line posterior to the sacrum, unloading the cervical core completely",
                "It converts the neck into an unstable second-class lever, increasing range of motion to 360 degrees",
                "FHP reduces cervical loading to zero by allowing neck sliding along lung segments"
            ),
            correctAnswer = "It increases the external flexion moment arm of the head's weight, demanding massive, sustained contraction of suboccipital muscles and increasing facet joint compression",
            whyCorrect = "FHP shifts the head's center of mass anteriorly away from the cervical rotation axis, increasing the external tilt/flexion moment arm. To keep the head level, the posterior cervical extensors (e.g. splenius capitis, suboccipitals) must contract continuously, causing muscular fatigue and high compressive loads on the lower cervical joints.",
            whyIncorrect = "Head position moves forward (not backward). FHP does not transition the neck into a second-class lever or reduce loading to zero.",
            clinicalImportance = "Explains why patients with FHP of the neck present commonly with tension headaches, suboccipital trigger points, and cervical disc degeneration.",
            examinerTip = "Remember: for every inch the head projects forward, its effective weight load on the posterior cervical musculature doubles.",
            subject = "Biomechanics",
            difficulty = "High Yield",
            learningPoint = "Forward neck drift increases the head's moment arm, overworking posterior neck muscles and compressing cervical joints.",
            relatedConcept = "Postural Alignment and Joint Overloading",
            reference = "Nordin & Frankel's Basic Biomechanics of the Musculoskeletal System"
        ),
        VivaMcq(
            question = "In a normal sagittal posture, through which of the following skeletal landmarks should a perfect plumb Line of Gravity (LOG) pass?",
            options = listOf(
                "Through the ear lobe, anterior to the shoulder joint, through the greater trochanter of the hip, slightly anterior to the knee joint, and slightly anterior to the lateral malleolus",
                "Through the pupil of the eye, posterior to the scapula, and posterior to the heel bone",
                "Through the midline of the nose, through the xiphoid process, and directly through the pubic symphysis",
                "Through the cervical spine, through the lumbar spine, and into the left foot exclusively"
            ),
            correctAnswer = "Through the ear lobe, anterior to the shoulder joint, through the greater trochanter of the hip, slightly anterior to the knee joint, and slightly anterior to the lateral malleolus",
            whyCorrect = "In normal standing, the plumb line (representing the LOG) should pass through the ear lobe (external auditory meatus), through the cervical vertebral bodies, anterior to the thoracic spine/shoulder joint axis, through the greater trochanter of the hip, anterior to the knee joint, and about 2 cm anterior to the lateral malleolus of the ankle.",
            whyIncorrect = "Plumb line alignment measurements are viewed from the side (sagittal plane), not front (coronal plane, which would include eye, nose, pubis). Spinal curvature prevents the line from passing straight through both cervical and lumbar zones.",
            clinicalImportance = "Therapists use a plumb line to perform postural screen assessments, helping identify alignment abnormalities (kyphosis, flatback, forward sway).",
            examinerTip = "Memorize the sagittal landmarks of the plumb line. They are essential clinical building blocks.",
            subject = "Biomechanics",
            difficulty = "Easy",
            learningPoint = "The plumb line maps optimal sagittal body segment alignment to minimize static muscle actions.",
            relatedConcept = "Sagittal Plumb Line Landmarks",
            reference = "Kendall's Muscles: Testing and Function, with Posture and Pain, 5th Ed"
        )
    )
}
