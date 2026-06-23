package com.example.data

object KinesiologyData {
    val questions: List<VivaMcq> = listOf(
        // === MUSCLE ACTION (1-6) ===
        VivaMcq(
            question = "A clinician finds that a patient's knee flexion range of motion is significantly limited when the hip is held in full extension. What kinesiological phenomenon explain this?",
            options = listOf(
                "Passive insufficiency of the rectus femoris; it is stretched to its limits across both joints simultaneously",
                "Active insufficiency of the rectus femoris; it is excessively shortened",
                "Spasticity of the biceps femoris pulling the knee back",
                "Mechanical jamming of the patella in the trochlea"
            ),
            correctAnswer = "Passive insufficiency of the rectus femoris; it is stretched to its limits across both joints simultaneously",
            whyCorrect = "Passive insufficiency occurs when a multi-joint muscle cannot stretch enough to allow full range of motion at all crossed joints.",
            whyIncorrect = "Active insufficiency is contractile failure. Hamstring spasticity or patellar jamming do not describe length limits.",
            clinicalImportance = "Informs muscle length tests (e.g., Ely's test) to distinguish muscular tightness from capsular restrictions.",
            examinerTip = "Passive insufficiency = lack of passive stretch. Active insufficiency = lack of contractile force.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Multi-joint muscles cannot stretch enough to permit full range of motion at all crossed joints simultaneously.",
            relatedConcept = "Muscle Insufficiency",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Why is it extremely difficult to generate a powerful knee flexion contraction when the hip is placed in full hyperextension?",
            options = listOf(
                "Active insufficiency of the hamstrings; the muscle is excessively shortened, placing cross-bridges at an unfavorable position",
                "Passive insufficiency of the iliopsoas, which mechanically blocks joint flexion",
                "Reciprocal inhibition of the quadriceps shutting down motor unit recruitment",
                "Somatic nerve blockage of the sciatic nerve at the ischial tuberosity"
            ),
            correctAnswer = "Active insufficiency of the hamstrings; the muscle is excessively shortened, placing cross-bridges at an unfavorable position",
            whyCorrect = "Active insufficiency occurs when a multi-joint muscle is shortened across all its joints, reducing its force-generating capacity.",
            whyIncorrect = "Passive insufficiency of antagonist limits joint motion, not active force. Reciprocal inhibition does not fully silence agonists.",
            clinicalImportance = "Informs therapists to position patients with slight hip flexion during isolated hamstring strengthening.",
            examinerTip = "Look for extreme shortening of a muscle across both joints to identify active insufficiency.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Active insufficiency occurs when a muscle is overly shortened, drastically reducing its force output.",
            relatedConcept = "Muscle Insufficiency",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "When a person lowers their body weight slowly from tip-toe stance to flat-foot position, what type of contraction does the soleus undergo?",
            options = listOf(
                "Eccentric contraction; it is lengthening under tension to control the descent against gravity",
                "Concentric contraction; it is shortening to pull the ankle",
                "Isometric contraction; its length remains constant as joint angles change",
                "Isokinetic contraction; it contracts at a constant pre-set speed"
            ),
            correctAnswer = "Eccentric contraction; it is lengthening under tension to control the descent against gravity",
            whyCorrect = "Controlled lowering of the heel requires soleus to exert tension while slowly lengthening to act as a decelerator.",
            whyIncorrect = "Concentric contractions involve shortening. Isometric maintains constant length. Isokinetic requires dynamic speed governors.",
            clinicalImportance = "Eccentric control of soleus/gastrocnemius is vital during loading and terminal stance phases of gait.",
            examinerTip = "Controlled lowering movements against gravity are almost always eccentric contractions.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Eccentric contractions involve muscle lengthening under load to control gravity-driven movements.",
            relatedConcept = "Types of Muscle Contraction",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "When making a tight fist, the extensor carpi radialis brevis contracts strongly. In this functional activity, what is its primary role?",
            options = listOf(
                "Synergist; it stabilizes the wrist to prevent the finger flexors from falling into active insufficiency",
                "Agonist; it directly flexes the fingers to increase grip power",
                "Antagonist; it actively stretches to allow wrist flexion during grasp",
                "Fixator; it locks the pronator teres during forearm rotation"
            ),
            correctAnswer = "Synergist; it stabilizes the wrist to prevent the finger flexors from falling into active insufficiency",
            whyCorrect = "Wrist extensors act as synergists during gripping, holding the wrist in 20-30 degrees of extension to optimize finger flexor length-tension.",
            whyIncorrect = "Wrist extensors do not flex fingers. They do not act as antagonists during grip. They are not elbow fixators.",
            clinicalImportance = "Explains why radial nerve palsy (wrist drop) leads to a severely weak and ineffective grip.",
            examinerTip = "Synergistic wrist stabilization during grip is a favorite examiner example of coordinated muscle actions.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Synergists stabilize intermediate joints to preserve the optimal length of primary moving muscles.",
            relatedConcept = "Synergy and Grip Biomechanics",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "What is the fundamental kinesiological definition of a Closed Kinetic Chain (CKC) exercise?",
            options = listOf(
                "The distal segment of the limb is fixed to an immovable surface, causing joint movement proximal to the fixed segment",
                "The distal segment is free to move in space with no external resistance",
                "The exercise is performed without any voluntary muscle contraction",
                "The joint is surgically or mechanically fused to prevent motion"
            ),
            correctAnswer = "The distal segment of the limb is fixed to an immovable surface, causing joint movement proximal to the fixed segment",
            whyCorrect = "In a closed kinetic chain, the distal segment is fixed, so movement at one joint predictably affects other joints in the chain.",
            whyIncorrect = "Free distal segment defines an open kinetic chain (OKC). Voluntary contractions occur in both. Fusing joints is arthrodesis.",
            clinicalImportance = "CKC exercises stimulate joint proprioceptors, optimize joint compression, and mirror tasks like transfers.",
            examinerTip = "Confirm if hand or foot is anchored on an immovable surface to identify a closed kinetic chain.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Closed-chain activities anchor the distal limb, inducing coordinated, multi-joint movement patterns proximally.",
            relatedConcept = "Kinetic Chains",
            reference = "Brunstrom's Clinical Kinesiology"
        ),
        VivaMcq(
            question = "Based on sliding filament theory, why does a muscle generate maximum active tension at its mid-range resting length?",
            options = listOf(
                "At resting length, there is optimal overlap between actin and myosin, maximizing potential cross-bridge attachments",
                "At resting length, the muscle has completely dissolved non-contractile connective tissues",
                "Calcium ions undergo chemical transformation that increases cross-sectional mass",
                "Active tension increases exponentially the further a muscle is stretched"
            ),
            correctAnswer = "At resting length, there is optimal overlap between actin and myosin, maximizing potential cross-bridge attachments",
            whyCorrect = "Active tension is a direct function of cross-bridges; optimal filament overlap occurs at mid-range resting length.",
            whyIncorrect = "Connective tissue doesn't dissolve. Calcium ions trigger binding but don't change mass. Stretch increases passive tension.",
            clinicalImportance = "Allows clinicians to position joints in optimal strength bands during therapeutic exercises.",
            examinerTip = "Remember that extreme shortening or extreme passive stretching decreases active tension capacity.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Maximum active muscular contraction requires optimal sliding filament overlap at mid-range resting length.",
            relatedConcept = "Sarcomere Length-Tension",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),

        // === PLANES (7-11) ===
        VivaMcq(
            question = "A patient is instructed to perform active cervical rotation. In which anatomical plane does this movement primarily occur?",
            options = listOf(
                "Horizontal (transverse) plane about a vertical axis",
                "Sagittal plane about a coronal axis",
                "Frontal (coronal) plane about an anterior-posterior axis",
                "Oblique plane about an imaginary diagonal axis"
            ),
            correctAnswer = "Horizontal (transverse) plane about a vertical axis",
            whyCorrect = "Cervical rotation and all axial rotations occur in the horizontal plane around a vertical longitudinal axis of rotation.",
            whyIncorrect = "Sagittal plane is for flexion/extension. Frontal plane is for lateral flexion. Oblique is not a standard plane.",
            clinicalImportance = "Enables precise goniometer alignment relative to the vertical axis of the head.",
            examinerTip = "The axis of rotation is always perpendicular to the plane of movement (Horizontal = Vertical Axis).",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Transverse movements like axial rotations proceed parallel to the horizontal plane around a vertical axis.",
            relatedConcept = "Anatomical Planes and Axes",
            reference = "Brunstrom's Clinical Kinesiology"
        ),
        VivaMcq(
            question = "During side-lying hip abduction, in which anatomical plane does the femur move relative to the pelvis?",
            options = listOf(
                "Frontal (coronal) plane",
                "Sagittal plane",
                "Transverse plane",
                "Oblique sagittal plane"
            ),
            correctAnswer = "Frontal (coronal) plane",
            whyCorrect = "Abduction and adduction involve moving a limb toward or away from the midline parallel to the front of the body, which is the frontal plane.",
            whyIncorrect = "Sagittal is for flexion/extension. Transverse is for rotation. Oblique is diagonal.",
            clinicalImportance = "Ensures isolated gluteus medius targeting without substitution in anterior/posterior sagittal planes.",
            examinerTip = "Lateral movements of the limb or spine occur in the frontal plane around an anterior-posterior axis.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Lateral movements away or toward the midline occur parallel to the coronal/frontal plane.",
            relatedConcept = "Anatomical Planes",
            reference = "Brunstrom's Clinical Kinesiology"
        ),
        VivaMcq(
            question = "When observing a patient's walking gait from a side-profile view, which anatomical plane of joint movement are you examining?",
            options = listOf(
                "Sagittal plane",
                "Coronal plane",
                "Horizontal plane",
                "Oblique spinal plane"
            ),
            correctAnswer = "Sagittal plane",
            whyCorrect = "Side view inspection shows forward and backward movements, reflecting sagittal flexion and extension at joints.",
            whyIncorrect = "Frontal/coronal plane is best seen from front/back (lateral tilt). Transverse plane motions (rotation) are seen from top.",
            clinicalImportance = "Identifies sagittal anomalies like ankle foot drop, knee hyperextension, or trunk leaning.",
            examinerTip = "Looking from the side allows you to evaluate flexion and extension parameters which reside in the sagittal plane.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Forward and backward movements occur in the sagittal plane and are best observed from a side profile.",
            relatedConcept = "Gait Analysis Planes",
            reference = "Perry's Gait Analysis: Normal and Pathological Function"
        ),
        VivaMcq(
            question = "Ankle supination in an open kinetic chain consists of which combined triplanar movements?",
            options = listOf(
                "Inversion, plantarflexion, and adduction",
                "Eversion, dorsiflexion, and abduction",
                "Inversion, dorsiflexion, and abduction",
                "Eversion, plantarflexion, and adduction"
            ),
            correctAnswer = "Inversion, plantarflexion, and adduction",
            whyCorrect = "In open-chain kinematics, supination is a composite triplanar motion of inversion, plantarflexion, and adduction.",
            whyIncorrect = "Eversion, dorsiflexion, and abduction are components of pronation, not supination.",
            clinicalImportance = "Helps clinicians treat inversion ankle sprains, which commonly injure lateral ligaments.",
            examinerTip = "Remember the mnemonic S-I-P-A. Supination = Inversion, Plantarflexion, Adduction.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Ankle supination is a composite triplanar motion made of open-chain inversion, plantarflexion, and adduction.",
            relatedConcept = "Subtalar Triplanar Motion",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "During a chest fly exercise (bringing arms together at shoulder level), what are the plane and axis for the humerus?",
            options = listOf(
                "Horizontal plane about a vertical axis",
                "Sagittal plane about a coronal axis",
                "Frontal plane about an anterior-posterior axis",
                "Horizontal plane about a coronal axis"
            ),
            correctAnswer = "Horizontal plane about a vertical axis",
            whyCorrect = "Horizontally aligned limb adduction at 90 degrees of elevation is horizontal adduction, taking place in the transverse plane around a vertical axis.",
            whyIncorrect = "Sagittal plane is for standard elevation. Frontal plane is for vertical abduction. Coronal axis is not perpendicular to horizontal plane.",
            clinicalImportance = "Enables clinicians to target pectoralis major while controlling for rotator cuff stability in horizontal positions.",
            examinerTip = "Limb motions moving parallel to floor at shoulder height represent transverse/horizontal plane kinematics.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Horizontal abduction and adduction rotate limbs in the transverse plane around a vertical joint axis.",
            relatedConcept = "Shoulder Motion Profiles",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),

        // === AXES (12-16) ===
        VivaMcq(
            question = "A patient performs elbow flexion (sagittal plane). Around which axis of rotation does the forearm pivot on the humerus?",
            options = listOf(
                "Mediolateral (coronal) axis",
                "Anterior-posterior axis",
                "Vertical longitudinal axis",
                "Oblique sagittal axis"
            ),
            correctAnswer = "Mediolateral (coronal) axis",
            whyCorrect = "Sagittal plane movements (flexion/extension) always turn around a perpendicular mediolateral (coronal) axis of rotation.",
            whyIncorrect = "Anterior-posterior axis is for frontal plane. Longitudinal axis is for transverse rotation.",
            clinicalImportance = "Determines goniometric placement where the dial pivot sits over the lateral epicondyle axis.",
            examinerTip = "Axes of rotation are always perpendicular to the plane of movement (Sagittal Plane = Mediolateral Axis).",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Flexion and extension occur in the sagittal plane around a transverse mediolateral axis.",
            relatedConcept = "Joint Axes of Rotation",
            reference = "Brunstrom's Clinical Kinesiology"
        ),
        VivaMcq(
            question = "For lateral trunk flexion (bending the torso to the side), what is the correct axis of rotation?",
            options = listOf(
                "Anterior-posterior (sagittal-horizontal) axis",
                "Mediolateral (coronal) axis",
                "Vertical longitudinal axis",
                "Internal oblique diagonal axis"
            ),
            correctAnswer = "Anterior-posterior (sagittal-horizontal) axis",
            whyCorrect = "Lateral flexion takes place in the frontal (coronal) plane, revolving around a perpendicular anterior-posterior axis.",
            whyIncorrect = "Mediolateral axis is for flexion. Vertical longitudinal axis is for rotation.",
            clinicalImportance = "Critical in analyzing scoliosis kinematics where lateral flexion is coupled with vertebral rotation.",
            examinerTip = "Frontal plane motion (abduction, lateral bending) always occurs around an anterior-posterior axis.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Lateral spinal and limb movements in the frontal plane pivot around a sagitally oriented anterior-posterior axis.",
            relatedConcept = "Spinal Kinematic Axes",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "During clinical evaluation of forearm pronation and supination, around which axis does the radius rotate?",
            options = listOf(
                "Longitudinal axis running from radial head to ulnar styloid process",
                "Mediolateral axis across the elbow joint fold",
                "Anterior-posterior axis passing through the palm",
                "Transverse axis through the center of carpal bones"
            ),
            correctAnswer = "Longitudinal axis running from radial head to ulnar styloid process",
            whyCorrect = "Forearm pronation/supination is a transverse rotation around a longitudinal axis passing from proximal radial head to distal ulna.",
            whyIncorrect = "Mediolateral axis is for elbow flexion. AP axis is for wrist deviation. Transverse carpal is for wrist flexion.",
            clinicalImportance = "Aligns goniometric parameters relative to forearm bony shafts rather than the elbow fold axis.",
            examinerTip = "Forearm rotation is longitudinal, pivoting the radius around a relatively stationary ulna.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Forearm rotation is a longitudinal axis movement, pivoting the radius around the stationary ulna.",
            relatedConcept = "Radioulnar Joint Kinematics",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "What is the term for the lateral deviation of the forearm relative to the humerus in full extension and supination?",
            options = listOf(
                "Carrying angle (cubitus valgus)",
                "Q-angle",
                "Angle of inclination",
                "Torsion angle"
            ),
            correctAnswer = "Carrying angle (cubitus valgus)",
            whyCorrect = "The carrying angle is a normal lateral angulation of the extended elbow, caused by downward protrusion of the medial trochlea.",
            whyIncorrect = "Q-angle is in the knee. Inclination angle is in femoral neck. Torsion angle represents bone twist.",
            clinicalImportance = "Abnormal carrying angles (e.g. cubitus varus) must be monitored post-fracture to prevent ulnar nerve compression.",
            examinerTip = "Carrying angle defines normal valgus of the elbow joint.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "The carrying angle is a structural outward deviation of the forearm resulting from distal trochlear asymmetry.",
            relatedConcept = "Cubitus Valgus Carrying Angle",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Why is the axis of rotation in human synovial joints not a fixed center point, but rather a moving path (Instantaneous Axis of Rotation)?",
            options = listOf(
                "Articular surfaces are asymmetric and not perfect spheres, causing the pivot to shift as bones roll and slide",
                "Bones physically lengthen during muscular contraction",
                "Synovial fluid expands under heat, moving the core axis of rotation",
                "The central nervous system teleports joint axes to prevent continuous point fatigue"
            ),
            correctAnswer = "Articular surfaces are asymmetric and not perfect spheres, causing the pivot to shift as bones roll and slide",
            whyCorrect = "Biomaterial joint irregularities and asymmetric surface arcs cause the locus of rotation (IAR) to wander during excursion.",
            whyIncorrect = "Bones do not stretch during contraction. Fluid shifts do not reposition rotation center. Nervous system lacks teleportation capacity.",
            clinicalImportance = "Explains why braces and prostheses are designed with polycentric hinges rather than single-pin hinges.",
            examinerTip = "The concept of IAR highlights why joints are polycentric rather than simple fixed pivots.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Skeletal joint asymmetry creates a shifting polycentric Instantaneous Axis of Rotation (IAR) during movement.",
            relatedConcept = "Instantaneous Axis of Rotation",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),

        // === JOINT MOVEMENT (17-21) ===
        VivaMcq(
            question = "During active pull-ups, what is the action of the latissimus dorsi when the body is slowly lowered to a straight arm position?",
            options = listOf(
                "Eccentric contraction",
                "Concentric contraction",
                "Isometric contraction",
                "Isokinetic contraction"
            ),
            correctAnswer = "Eccentric contraction",
            whyCorrect = "Lowering under control means muscles generate holding force while slowly lengthening against gravity.",
            whyIncorrect = "Concentric refers to muscle shortening. Isometric represents zero length change. Isokinetic involves constant velocity.",
            clinicalImportance = "Eccentric deceleration work forms the core of tendon hypertrophy and structural remodeling.",
            examinerTip = "Controlled descent against gravity always requires eccentric contraction of the flexor or stabilizing muscles.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Eccentric muscle contractions decelerate external loads, protecting joints from rapid, uncontrolled impacts.",
            relatedConcept = "Eccentric Biomechanics",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Which joint of the foot-ankle complex is primarily responsible for isolated inversion and eversion?",
            options = listOf(
                "Subtalar joint",
                "Talocrural joint",
                "Metatarsophalangeal joint",
                "Distal tibiofibular syndesmosis"
            ),
            correctAnswer = "Subtalar joint",
            whyCorrect = "The subtalar joint (talocalcaneal) is clinically and mechanically responsible for hindfoot inversion and eversion.",
            whyIncorrect = "Talocrural is the hinge joint for flex/extension. MTP joints are for toes. Distal tibiofibular has minimal play.",
            clinicalImportance = "Subtalar movement adapts the foot to walking on uneven ground, reducing ankle sprain risks.",
            examinerTip = "Remember: Talocrural = Dorsi/Plantar. Subtalar = Inversion/Eversion.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "The subtalar joint provides transverse tilt (inversion/eversion) to adapt the foot to irregular ground planes.",
            relatedConcept = "Subtalar Joint Range of Motion",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "When a C6 tetraplegia patient extends their wrist, their fingers passively flex. What is this phenomenon called?",
            options = listOf(
                "Tenodesis action; passive tension in long flexors as they stretch over an extended wrist",
                "Stretch reflex; muscle contraction triggered by rapid muscle spindles",
                "Reciprocal routing; median nerve reflex from physical radial nerve extensions",
                "Spinal tethering; vertical traction of fibers"
            ),
            correctAnswer = "Tenodesis action; passive tension in long flexors as they stretch over an extended wrist",
            whyCorrect = "Wrist extension increases structural distance across finger flexors, pulling fingers into passive functional grip.",
            whyIncorrect = "It is entirely passive tension (no spindle-driven active contractions, neural cross-talk, or cord tethering).",
            clinicalImportance = "Essential to preserve long finger flexor tightness in quadriplegics to facilitate grip capacity.",
            examinerTip = "Tenodesis utilizes passive muscle insufficiency across the wrist to create a functional grasp.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Passive force transfer along multi-joint tendons can actuate distal joints automatically during proximal movement.",
            relatedConcept = "Tenodesis Grip Mechanics",
            reference = "Brunstrom's Clinical Kinesiology"
        ),
        VivaMcq(
            question = "To achieve full 180 degrees of shoulder abduction, what are the relative contributions of glenohumeral and scapulothoracic joints?",
            options = listOf(
                "120 degrees of glenohumeral and 60 degrees of scapulothoracic upward rotation (2:1 ratio)",
                "90 degrees of glenohumeral and 90 degrees of scapulothoracic",
                "160 degrees of glenohumeral and 20 degrees of scapulothoracic",
                "The scapula remains completely locked; 180 degrees occurs purely at the glenohumeral joint"
            ),
            correctAnswer = "120 degrees of glenohumeral and 60 degrees of scapulothoracic upward rotation (2:1 ratio)",
            whyCorrect = "According to scapulohumeral rhythm, there is a 2:1 ratio between GH abduction (120) and ST upward rotation (60).",
            whyIncorrect = "A 1:1 or 8:1 ratio is abnormal and represents a joint kinetic defect.",
            clinicalImportance = "Helps clinicians identify scapular dyskinesia in overhead athletes presenting with pain.",
            examinerTip = "Scapulohumeral rhythm ratio is 2:1. Know this baseline split.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Symmetric shoulder elevation relies on a coordinated 2:1 kinematic ratio between the glenohumeral joint and the scapulothoracic interface.",
            relatedConcept = "Scapulohumeral Rhythm",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "As the body advances over the fixed flat foot during stance phase, what talocrural joint kinematics are occurring?",
            options = listOf(
                "The concave tibia rolls and glides anteriorly on the convex talus",
                "The concave tibia rolls anteriorly and glides posteriorly on the convex talus",
                "The convex talus rolls posteriorly and glides anteriorly on the tibia",
                "The talus spins with zero rolling or gliding"
            ),
            correctAnswer = "The concave tibia rolls and glides anteriorly on the convex talus",
            whyCorrect = "In closed-chain dorsiflexion, a concave surface (tibia) moves on convex talus. Concave movement = same direction roll/glide.",
            whyIncorrect = "Opposite direction roll/glide is for convex moving surfaces. Talus is fixed in closed stance gait.",
            clinicalImportance = "Informs manual mobilizations (anterior tibial glides) to restore closed-chain dorsiflexion.",
            examinerTip = "For tibia-on-talus (concave-on-convex), roll and glide occur in the SAME direction.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "During concave-on-convex motion, joint surfaces roll and slide in a matching direction.",
            relatedConcept = "Concave-Convex Rule",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),

        // === ARTHROKINEMATICS (22-27) ===
        VivaMcq(
            question = "In arthrokinematics, how is a 'glide' (or slide) defined?",
            options = listOf(
                "A single point on one surface contacts multiple points on another surface",
                "Multiple points on one surface contact matching multiple points on another surface",
                "A single point rotates purely around another stationary point",
                "Immediate complete separation of joint surfaces"
            ),
            correctAnswer = "A single point on one surface contacts multiple points on another surface",
            whyCorrect = "A glide represents sliding of one spot over a series of points on the partner surface (similar to skidding tires).",
            whyIncorrect = "Multiple points to multiple points is a Roll. Spot-to-spot pivoting is a Spin. Separation is traction.",
            clinicalImportance = "Mobilization techniques rely on linear parallel glide forces to separate or free tight capsular recesses.",
            examinerTip = "Slide = single point touching multiple. Roll = multiple points touching multiple.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Joint sliding entails a single contact node translating across multiple points of the reciprocal surface.",
            relatedConcept = "Types of Arthrokinematic Motion",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "According to the convex-concave rule, what are the rolls and glides at the glenohumeral joint during active shoulder abduction?",
            options = listOf(
                "The convex humerus rolls superiorly and glides inferiorly on the concave glenoid",
                "The convex humerus rolls inferiorly and glides superiorly on the concave glenoid",
                "The concave glenoid rolls and glides superiorly on the humerus",
                "The humerus spins purely with zero rolling or gliding"
            ),
            correctAnswer = "The convex humerus rolls superiorly and glides inferiorly on the concave glenoid",
            whyCorrect = "Humerus is convex moving on concave glenoid. Convex moving = roll and glide occur in opposite directions (upward roll, downward glide).",
            whyIncorrect = "If it glided superiorly, the humerus would slide upward into osseous impingement on the acromial ceiling.",
            clinicalImportance = "Capsular tightness (especially inferior recess) blocks the downward slide, trapping the subacromial structures.",
            examinerTip = "In convex-on-concave joints, rolling and sliding MUST occur in opposite directions.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Convex boundaries roll in the direction of limb motion while sliding in the exact opposite direction to maintain joint centering.",
            relatedConcept = "Glenohumeral Arthrokinematics",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "During active open-chain knee extension, what are the rolls and glides occurring at the tibiofemoral joint?",
            options = listOf(
                "The concave tibia rolls anteriorly and glides anteriorly on the convex femur",
                "The concave tibia rolls anteriorly and glides posteriorly on the femur",
                "The convex femur rolls anteriorly and glides posteriorly on the tibia",
                "The tibia spins with zero sliding"
            ),
            correctAnswer = "The concave tibia rolls anteriorly and glides anteriorly on the convex femur",
            whyCorrect = "In open-chain knee extension, concave tibia moves on convex femur. Concave moving = roll and glide in the same direction (anterior).",
            whyIncorrect = "Opposite pathways occur in closed chain where the femur is moving. In open chain, tibia determines direction.",
            clinicalImportance = "Restoring extension deficits uses anterior tibial glides to mimic the normal extension slide.",
            examinerTip = "Open chain knee extension = Concave tibia moving = Roll and glide match (anteriorly).",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Concave tibiofemoral surfaces glide in the same direction as the bony lever rotation.",
            relatedConcept = "Tibiofemoral Arthrokinematics",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "During weight-bearing closed-chain knee extension, what terminal rotation occurs at the femur to lock the knee?",
            options = listOf(
                "Internal rotation of femur on the tibia",
                "External rotation of femur on the tibia",
                "Lateral glide of the talus",
                "Symmetric lateral translation of the patella"
            ),
            correctAnswer = "Internal rotation of femur on the tibia",
            whyCorrect = "The screw-home mechanism locks the knee; in closed chain stance, the femur rotates internally relative to the fixed tibia.",
            whyIncorrect = "External rotation of the femur unlocks the knee. Calcaneal or patellar movements are auxiliary.",
            clinicalImportance = "Allows stable weight-bearing with minimal muscular effort when the knee is locked in full extension.",
            examinerTip = "Confirm if joint is open or closed chain. Open chain = tibia rotates OUT. Closed chain = femur rotates IN.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Terminal locking in standing involves internal femoral rotation on the stabilized tibia.",
            relatedConcept = "Screw-Home Mechanism",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Which muscle is anatomically responsible for 'unlocking' the extended knee in weight-bearing, and what rotation does it produce?",
            options = listOf(
                "Popliteus; it rotates the femur externally on the fixed tibia",
                "Rectus femoris; it pulls the patella superiorly",
                "Biceps femoris; it rotates the tibia internally",
                "Gastrocnemius; it glides the tibia posteriorly"
            ),
            correctAnswer = "Popliteus; it rotates the femur externally on the fixed tibia",
            whyCorrect = "The popliteus muscle acts as the key to unlock the knee by initiating external rotation of the femur on the stabilized tibia.",
            whyIncorrect = "Rectus femoris extends knee. Biceps femoris rotates tibia externally. Gastrocnemius does not rotate the femur.",
            clinicalImportance = "Popliteus tendinitis causes pain when initiating flexion from a fully straight stance (e.g., downhill walking).",
            examinerTip = "Popliteus is the primary knee-unlocking muscle. Memorize its closed-chain external rotation of the femur.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "The popliteus muscle unlocks the weight-bearing knee by initiating external rotation of the femur.",
            relatedConcept = "Knee Joint Unlocking",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "How is the 'Close-Packed' position of a synovial joint characterized biomechanically?",
            options = listOf(
                "Surfaces are maximally congruent, capsules/ligaments are taut, and joint play is minimal",
                "Surfaces are separated, capsule is lax, and joint volume is maximized",
                "Mid-range alignment with elevated subluxation risk",
                "Complete numbness of the surrounding sensory nerve endings"
            ),
            correctAnswer = "Surfaces are maximally congruent, capsules/ligaments are taut, and joint play is minimal",
            whyCorrect = "Close-packed parameters represent the state of ultimate skeletal stability, with taut constraints and maximal surface lock.",
            whyIncorrect = "Maximally separated and lax features describe the Loose-Packed (open) position useful for mobilizations.",
            clinicalImportance = "Joint mobilizations must avoid close-packed positions as the lack of slack leads to cartilage trauma.",
            examinerTip = "Close-packed position = maximum congruency, maximum ligament tension, minimum joint play.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Close-packed alignments maximize surface contact area and ligamentous tension, yielding high structural stability.",
            relatedConcept = "Joint Positions",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),

        // === SCAPULOHUMERAL RHYTHM (28-33) ===
        VivaMcq(
            question = "Which muscle force couple is responsible for upwardly rotating the scapula during active shoulder elevation?",
            options = listOf(
                "Upper trapezius, lower trapezius, and serratus anterior",
                "Levator scapulae, rhomboids, and pectoralis minor",
                "Deltoid, supraspinatus, and infraspinatus",
                "Latissimus dorsi and teres major"
            ),
            correctAnswer = "Upper trapezius, lower trapezius, and serratus anterior",
            whyCorrect = "Upward rotation is driven by the upper trapezius pulling up, lower trapezius pulling down, and serratus anterior pulling laterally.",
            whyIncorrect = "Levator/rhomboids downwards rotate the scapula. Deltoid/cuff represent a glenohumeral couple.",
            clinicalImportance = "Serratus anterior weakness leads to scapular winging and restricts humeral elevation past 90 degrees.",
            examinerTip = "The upward rotation couple (trapezius/serratus) is high-yield and commonly tested in shoulder kinesiology.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Serrated and trapezial fibers act as a force couple to drive healthy scapulothoracic upward rotation.",
            relatedConcept = "Scapular Force Couples",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "A patient presents with medial border scapular winging during wall push-ups. This indicates damage to which nerve?",
            options = listOf(
                "Long thoracic nerve",
                "Spinal accessory nerve",
                "Dorsal scapular nerve",
                "Medial pectoral nerve"
            ),
            correctAnswer = "Long thoracic nerve",
            whyCorrect = "Long thoracic nerve supplies the serratus anterior, which holds the scapula flat against the thoracic cage.",
            whyIncorrect = "Spinal accessory nerve is for trapezius. Dorsal scapular is for rhomboids. Medial pectoral is for pectoralis major/minor.",
            clinicalImportance = "Serratus palsy blocks normal elevation because the scapula fails to rotate upwardly.",
            examinerTip = "Medial border winging = long thoracic nerve = serratus anterior muscle.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Serratus anterior holds the scapula flat against the rib cage, resisting winging when pushing.",
            relatedConcept = "Scapulothoracic Pathology",
            reference = "Magee's Orthopedic Physical Assessment"
        ),
        VivaMcq(
            question = "Which muscles act as downward rotators of the scapula when lowering the arm against resistance?",
            options = listOf(
                "Rhomboids, levator scapulae, and pectoralis minor",
                "Serratus anterior and lower trapezius",
                "Upper trapezius and supraspinatus",
                "Coracobrachialis and teres major"
            ),
            correctAnswer = "Rhomboids, levator scapulae, and pectoralis minor",
            whyCorrect = "Downward rotation, return to neutral, is executed by the rhomboids, levator scapulae and pectoralis minor.",
            whyIncorrect = "Serratus anterior upwardly rotates the scapula. Trapezius also upwardly rotates. Coracobrachialis affects humerus.",
            clinicalImportance = "Anterior tightness of pectoralis minor can dump the scapula into static anterior tilt and downward rotation.",
            examinerTip = "Rhomboids and levator scapulae pull the medial border superior-medially, causing downward rotation.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "The rhomboids, levator scapulae, and pectoralis minor act synergistically to downwardly rotate the scapula.",
            relatedConcept = "Scapular Control",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "How do the SC and AC joints contribute to the 60 degrees of scapulothoracic upward rotation during active elevation?",
            options = listOf(
                "Clavicular elevation at SC joint provides 30 degrees, and scapular rotation relative to clavicle at AC joint provides 30 degrees",
                "The SC joint elevates the clavicle by 60 degrees while AC joint remains locked",
                "SC and AC joints slide laterally by 10 centimeters without rotation",
                "The SC joint rotates forward while the AC joint executes pure distal distraction"
            ),
            correctAnswer = "Clavicular elevation at SC joint provides 30 degrees, and scapular rotation relative to clavicle at AC joint provides 30 degrees",
            whyCorrect = "SC elevation contributes approximately 30 degrees, and AC adjustment contributes the final 30 degrees of upward rotation.",
            whyIncorrect = "SC joint alone cannot fulfill the full range without AC adjustments.",
            clinicalImportance = "Informs rehab after clavicular fractures or AC separations where joint restriction blocks overhead range.",
            examinerTip = "Upward rotation is a combined pathway. SC and AC joint inputs must equal 60 degrees of rotation.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Scapulothoracic upward rotation is a composite of motion occurring at both the SC and AC articulation sites.",
            relatedConcept = "Shoulder Complex Pathomechanics",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "Why is backward axial rotation of the clavicle essential for full overhead shoulder elevation?",
            options = listOf(
                "Tension in coracoclavicular ligament pulls the clavicle into backward rotation, freeing AC joint of mechanical blocks",
                "Axial rotation transforms clavicle into a primary weight-bearing spur",
                "Clavicle backward spin directly shortens the humerus moment arm",
                "Backward spin tilts the scapula into pure lateral translation"
            ),
            correctAnswer = "Tension in coracoclavicular ligament pulls the clavicle into backward rotation, freeing AC joint of mechanical blocks",
            whyCorrect = "As scapula elevates, coracoid moves, tightening the coracoclavicular ligament. This tension forces the clavicle into posterior rotation.",
            whyIncorrect = "Clavicular rotation is movement-permitting, not weight-bearing, moment-arm shrinking or lateral translating.",
            clinicalImportance = "SC or AC joint stiffness can cause humeral impingement due to mechanical lock of clavicular movement.",
            examinerTip = "Expect questions on how ligamentous tension drives posterior clavicular rotation past 90 degrees.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Clavicular posterior rotation acts as a mechanical release valve for terminal scapulothoracic upward rotation.",
            relatedConcept = "Clavicular Kinematics",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Why must a patient externally rotate the humerus to achieve full active shoulder abduction?",
            options = listOf(
                "To rotate greater tubercle of humerus posteriorly, clearing acromion process to avoid bony impingement",
                "To shorten the gluteus medius during dynamic standing balance",
                "To compress subacromial bursa into a solid articular wedge",
                "To minimize deltoid line of action"
            ),
            correctAnswer = "To rotate greater tubercle of humerus posteriorly, clearing acromion process to avoid bony impingement",
            whyCorrect = "External rotation rotates the greater tubercle posterior to the acromion, clearing space for the humerus to extend fully.",
            whyIncorrect = "Gluteal medius, bursal wedges, and deltoid lines do not describe this bone clearance requirement.",
            clinicalImportance = "Patients with frozen shoulder or external rotation contractures pinch their humerus around 120 abduction.",
            examinerTip = "Try it: abducting with palms down (internal rot) is blocked; turning palms up (external rot) clears the acromion.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Humeral external rotation is mechanically mandatory to clear the greater tubercle under the acromial ceiling.",
            relatedConcept = "Subacromial Impingement Mechanics",
            reference = "Magee's Orthopedic Physical Assessment"
        ),

        // === PELVIC MOTION (34-39) ===
        VivaMcq(
            question = "In standing, which muscle presentation leads to excessive Anterior Pelvic Tilt (APT)?",
            options = listOf(
                "Tight hip flexors and lumbar extensors; lengthened hamstrings and rectus abdominis",
                "Tight hamstrings and rectus abdominis; lengthened hip flexors and lumbar extensors",
                "Tight quadriceps and gastrocnemius; lengthened hamstrings and gluteals",
                "Tight hip adductors; lengthened hip abductors"
            ),
            correctAnswer = "Tight hip flexors and lumbar extensors; lengthened hamstrings and rectus abdominis",
            whyCorrect = "APT is created by short hip flexors pulling front down, and lumbar extensors pulling back up, offsetting weak antagonists.",
            whyIncorrect = "Tight hamstrings/abdominals trigger posterior pelvic tilt (PPT), not anterior pelvic tilt.",
            clinicalImportance = "This postural presentation is known as Janda's Lower Crossed Syndrome, which causes mechanical back strains.",
            examinerTip = "APT forces are a balance of anterior flexors pulling down and spinal extensors pulling up.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Anterior pelvic tilt features a crossed imbalance of overactive hip flexors/lumbar extensors vs underactive abs/hamstrings.",
            relatedConcept = "Lower Crossed Syndrome",
            reference = "Magee's Orthopedic Physical Assessment"
        ),
        VivaMcq(
            question = "Which muscle groups contract concentrically to execute a Posterior Pelvic Tilt (PPT) in hook-lying?",
            options = listOf(
                "Abdominals (rectus abdominis, obliques) and hip extensors (gluteus maximus, hamstrings)",
                "Iliopsoas, rectus femoris, and lumbar erector spinae",
                "Quadratus lumborum, gluteus medius, and adductor longus",
                "Serratus anterior, latissimus dorsi, and cervical flexors"
            ),
            correctAnswer = "Abdominals (rectus abdominis, obliques) and hip extensors (gluteus maximus, hamstrings)",
            whyCorrect = "Abdominals pull the pubis superiorly while gluteals/hamstrings pull ischium inferiorly, rotating pelvis posteriorly.",
            whyIncorrect = "Iliopsoas/erector spinae perform anterior pelvic tilt. Quadratus lumborum hikes pelvis. Upper limb muscles do not apply.",
            clinicalImportance = "Commonly used in spinal rehabilitation to open narrowed intervertebral foramina in lumbar stenosis.",
            examinerTip = "PPT is generated by a pelvic force couple of anterior abdominal muscles and posterior hip extensors.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Posterior pelvic tilt is generated by synergistic co-contraction of abdominal pull-ups and gluteal/hamstring pull-downs.",
            relatedConcept = "Pelvic Control",
            reference = "Brunstrom's Clinical Kinesiology"
        ),
        VivaMcq(
            question = "During single-leg standing on right leg, the pelvis drops on the left side. What is this clinical observation?",
            options = listOf(
                "Positive Trendelenburg on right; weak right gluteus medius",
                "Positive Trendelenburg on left; weak left gluteus medius",
                "Reverse gait lurch; weak right quadriceps",
                "Thomas sign; tight left rectus femoris"
            ),
            correctAnswer = "Positive Trendelenburg on right; weak right gluteus medius",
            whyCorrect = "During single-leg stance, stance abductors (right medius) must contract closed-chain to prevent left hip drop.",
            whyIncorrect = "Pelvic drops occur on the swing (left) side, but indicate weakness of stance-side (right) gluteus medius.",
            clinicalImportance = "Common in superior gluteal nerve lesion, hip hemiarthroplasty post-op, or hip osteoarthritis.",
            examinerTip = "Stance leg is ALWAYS the weak side in a positive Trendelenburg sign (drops on swing side).",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "The stance-leg gluteus medius prevents pelvic drop on the contralateral swing side during walking.",
            relatedConcept = "Trendelenburg Mechanics",
            reference = "Magee's Orthopedic Physical Assessment"
        ),
        VivaMcq(
            question = "How does a patient with severe right gluteus medius weakness compensate to keep the pelvis balanced during stance phase?",
            options = listOf(
                "Lateral trunk lurch over right stance hip, which moves center of mass closer to hip axis to shrink external adductor moment",
                "Lateral trunk lean over left swing hip, away from stance leg",
                "Vaulting on left toes during swing phase",
                "Flexing neck forward to tuck chin"
            ),
            correctAnswer = "Lateral trunk lurch over right stance hip, which moves center of mass closer to hip axis to shrink external adductor moment",
            whyCorrect = "Leaning the torso over the weak stance hip brings the body's line of gravity closer to joint axis, decreasing the required stance abductor force.",
            whyIncorrect = "Leaning away extends the moment arm, increasing gravity's pull. Vaulting clears leg height. Chin tuck is cervical.",
            clinicalImportance = "Referred to as Trendelenburg lurch, it increases gait energy consumption and is checked post-hip surgery.",
            examinerTip = "Remember that a lateral lurch always occurs toward the weak limb stance side to bypass extensor limits.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Lateral trunk lurching shifts the gravity vector over the stance-side hip axis to offset abductor force deficits.",
            relatedConcept = "Gluteus Medius Lurch",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "How does horizontal pelvic rotation save metabolic energy during normal walking?",
            options = listOf(
                "It rotates forward on swing side, lengthening limbs to flatten arc of center of mass to minimize vertical bounce",
                "It raises center of mass by 10 inches to maximize gravitational pull",
                "It locks lumbar vertebrae to prevent any lateral flexion",
                "It creates horizontal shear forces that decrease systemic heart rate"
            ),
            correctAnswer = "It rotates forward on swing side, lengthening limbs to flatten arc of center of mass to minimize vertical bounce",
            whyCorrect = "Pelvic rotation forward lengthens legs at step transitions, flattening peaks and valleys of vertical body travel.",
            whyIncorrect = "Raising COG by 10 inches would consume massive energy. Pelvic rotation increases, rather than locks, lumbar mobility.",
            clinicalImportance = "Stroke hemiplegics lose pelvic rotation, substituting with lateral hip hikes or circumductions.",
            examinerTip = "Pelvic rotation acts as a primary gait determinant to conserve energy by smoothing vertical displacement.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Horizontal pelvic rotation flattens the vertical path of the body's center of mass, conserving metabolic energy.",
            relatedConcept = "Determinants of Gait",
            reference = "Perry's Gait Analysis: Normal and Pathological Function"
        ),
        VivaMcq(
            question = "A patient displays 'hip hiking' during swing phase to clear a splinted knee. Which muscle regulates this pelvis lift?",
            options = listOf(
                "Ipsilateral quadratus lumborum",
                "Contralateral gluteus medius",
                "Ipsilateral biceps femoris",
                "Bilateral rectus abdominis"
            ),
            correctAnswer = "Ipsilateral quadratus lumborum",
            whyCorrect = "Hip hiking lifts the swing pelvis in the frontal plane, driven entirely by concentric contractions of ipsilateral quadratus lumborum.",
            whyIncorrect = "Contralateral gluteus stabilization is stance-based. Biceps femoris and rectus abdominis do not lift the pelvic frame.",
            clinicalImportance = "This compensatory strategy leads to lumbar paraspinal fatigue and persistent lower back pain.",
            examinerTip = "Recall that hip hiking is driven by quadratus lumborum on the same side as the swinging limb.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Ipsilateral quadratus lumborum contraction hikes the pelvis to clear a functionally elongated limb during swing.",
            relatedConcept = "Frontal Plane Pelvic Compensations",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),

        // === GAIT (40-45) ===
        VivaMcq(
            question = "During normal walking, at which intervals does double limb support occur in the gait cycle?",
            options = listOf(
                "Initial contact/loading response (0-10%) and pre-swing (50-60%)",
                "Mid-stance (30-50% of the cycle)",
                "Mid-swing phase of walking",
                "No double support phases exist"
            ),
            correctAnswer = "Initial contact/loading response (0-10%) and pre-swing (50-60%)",
            whyCorrect = "Double support occurs twice per cycle during load transfers between stance hips (occupying roughly 20% of the cycle).",
            whyIncorrect = "Mid-stance and mid-swing are single support phases. Running has zero double support phases.",
            clinicalImportance = "Gait speed increases reduce double-support times. Balance-challenged patients extend double-stance times.",
            examinerTip = "A major gait classic: walking contains double support, while running is characterized by its absence.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Double limb support occupies the weight transfer phases at the margins of single-leg stance.",
            relatedConcept = "Gait Cycle Phasing",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "During early stance phase, the GRF vector passes medial to knee center. What moment does this generate at knee joint?",
            options = listOf(
                "External varus (adduction) moment",
                "External valgus (abduction) moment",
                "Internal locking projection translating joint laterally",
                "No external moments are generated"
            ),
            correctAnswer = "External varus (adduction) moment",
            whyCorrect = "An inward-running (medial) GRF vector creates an adduction tilt, compressing the medial knee joint.",
            whyIncorrect = "A lateral vector produces valgus momentos. Physical forces under weight-bearing always generate joint torque.",
            clinicalImportance = "Elevated knee adduction moments (KAM) compress the medial compartment, accelerating knee osteoarthritis.",
            examinerTip = "Expect varus moments when weight lines run medial to knee axes. Essential for joint cartilage health.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Medially aligned GRF vectors generate external varus moments, heavily loading the medial joint compartments.",
            relatedConcept = "Knee Adduction Moment",
            reference = "Perry's Gait Analysis: Normal and Pathological Function"
        ),
        VivaMcq(
            question = "What is the biological purpose of slight knee and ankle flexion during walking loading response?",
            options = listOf(
                "To act as shock absorbers dampening vertical landing impacts",
                "To elevate body center of mass as high as possible",
                "To lock joint capsules to prevent capsular flow",
                "To create electrostatic tension on the skin surfaces"
            ),
            correctAnswer = "To act as shock absorbers dampening vertical landing impacts",
            whyCorrect = "Knee flexion controlled by eccentric quadriceps, and ankle flexion controlled by pretibials cushion landings.",
            whyIncorrect = "Flexion lowers the center of mass to prevent impact spikes, rather than locking capsules or charging membranes.",
            clinicalImportance = "Straight knee walking (caused by weak quads or pain) causes repeated cartilage microtrauma.",
            examinerTip = "Loading shock absorption is led by eccentric quadriceps managing subtle knee bends.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Eccentric quadriceps and pretibial activity cushions load impacts through slight knee and ankle flexion.",
            relatedConcept = "Heel Strike Shock Absorption",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),
        VivaMcq(
            question = "How is Perry's 'Heel Rocker' (first rocker) characterized during stance progression?",
            options = listOf(
                "Heel pivots on ground, introducing ankle plantarflexion to draw tibia forward under eccentric pretibial controls",
                "Symmetric rotation of flat foot over ankle center",
                "Forefoot heel-off utilizing MTP joints to launch swing limbs",
                "Backward slides of calcaneus to decelerate landings"
            ),
            correctAnswer = "Heel pivots on ground, introducing ankle plantarflexion to draw tibia forward under eccentric pretibial controls",
            whyCorrect = "At initial contact, calcaneus serves as a pivot, lowering the foot to draw the tibia forward under control.",
            whyIncorrect = "Ankle rocker is tibia rolling over flat foot. Forefoot rocker is toe heel-off. Calcaneal sliding is unphysiological.",
            clinicalImportance = "Loss of heel rocker (flat foot stomp) destroys forward gait momentum, overloading knee extensors.",
            examinerTip = "Differentiate Perry's 3 rockers: first = heel pivot, second = ankle pivot, third = forefoot metatarsal pivot.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "The heel rocker utilizes calcaneal roll and eccentric pretibials to pull the tibia forward during landing.",
            relatedConcept = "Foot and Ankle Rockers",
            reference = "Perry's Gait Analysis: Normal and Pathological Function"
        ),
        VivaMcq(
            question = "How is an abnormal 'Antalgic' gait pattern characterized?",
            options = listOf(
                "Shortened stance phase on painful leg to lessen loading times, causing rapid step-through on healthy limb",
                "Severe trunk leaning backwards to accelerate gravity center speed",
                "Wide stance base to limit spinal shearing forces",
                "Foot drop landing causing a loud slapping noise"
            ),
            correctAnswer = "Shortened stance phase on painful leg to lessen loading times, causing rapid step-through on healthy limb",
            whyCorrect = "Antalgic gait minimizes painful load weight times by skipping forward onto the healthy leg.",
            whyIncorrect = "Leaning back is gluteus lurch. Wide base is cerebellar ataxia. Foot slap is neuropathic gait.",
            clinicalImportance = "Common in hip/knee arthritis or ankle sprains. Helps assess daily functional improvements.",
            examinerTip = "Whenever you note asymmetric rhythm with short stance times on one limb, identify it as antalgic.",
            subject = "Kinesiology",
            difficulty = "Easy",
            learningPoint = "Pain-induced antalgic gait truncates ipsilateral stance-phase duration to minimize tissue pressure.",
            relatedConcept = "Pathological Gait Patterns",
            reference = "Magee's Orthopedic Physical Assessment"
        ),
        VivaMcq(
            question = "A patient displays a backward trunk lean at heel contact of stance limb. What weakness does this compensate for?",
            options = listOf(
                "Gluteus maximus weakness; backward lean centers mass behind hip axis, creating external extension moments",
                "Iliopsoas weakness; backward lean pulls thigh forward passively",
                "Quadriceps weakness; backward lean locks knee via patellar friction",
                "Gastrocnemius tightness; backward lean prevents heel rise stretches"
            ),
            correctAnswer = "Gluteus maximus weakness; backward lean centers mass behind hip axis, creating external extension moments",
            whyCorrect = "Leaning backwards shifts the weight vector posterior to the hip axis, generating gravity-driven hip extension moments.",
            whyIncorrect = "Iliopsoas, quadriceps, and gastrocnemius deficits have lateral, hand-on-thigh, or vaulting compensations.",
            clinicalImportance = "Frequently checked in polio sequelae, muscular dystrophy, or inferior gluteal nerve injuries.",
            examinerTip = "Gluteus maximus lurch = BACKWARD trunk lean. Gluteus medius lurch = LATERAL trunk lean.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Backward trunk leaning shifts the body weight line behind the hip joint axis, bypassing gluteus maximus extension demands.",
            relatedConcept = "Gluteus Maximus Gait",
            reference = "Whittle's Gait Analysis: An Introduction, 5th Ed"
        ),

        // === FUNCTIONAL ANATOMY (46-50) ===
        VivaMcq(
            question = "If the femoral neck-shaft angle of inclination is abnormally low (under 120 degrees), what is this called?",
            options = listOf(
                "Coxa vara; increases abductor moment arm but rises femoral neck shear stress",
                "Coxa valga; reduces abductor moment arm and lowers skeletal load",
                "Femoral anteversion; causes in-toeing stance alignment",
                "Cubitus valgus; increases lateral deviations of hand axes"
            ),
            correctAnswer = "Coxa vara; increases abductor moment arm but rises femoral neck shear stress",
            whyCorrect = "Coxa vara is an angle < 120. It lengthens the abductor moment arm but elevates bending shear loads on the neck.",
            whyIncorrect = "Coxa valga is angle > 135. Anteversion represents neck twist. Cubitus valgus is located at the elbow.",
            clinicalImportance = "Can cause limb shortening, Trendelenburg gait, or slipped capital femoral epiphysis (SCFE) in adolescents.",
            examinerTip = "Vara reduces joint neck-shaft angles, extending the gluteus medius arm at the cost of bone fracture risk.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Coxa vara shortens structural height and changes muscle moments, placing biomechanical shear loads on the femur's neck.",
            relatedConcept = "Hip Angle of Inclination",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "What hip condition causes pigeon-toed standing (in-toeing) due to excessive anterior neck-to-condyle twist?",
            options = listOf(
                "Femoral anteversion; child internally rotates hip to center femoral head in acetabulum",
                "Femoral retroversion; child externally rotates hip to protect cartilage",
                "Genu varum; bowing coordinates tracking of collateral structures",
                "Coxa vara; horizontal necks reduce abductor capabilities"
            ),
            correctAnswer = "Femoral anteversion; child internally rotates hip to center femoral head in acetabulum",
            whyCorrect = "Anteversion > 15 degrees rotates the head anteriorly. Internal rotation aligns the femoral head squarely in the acetabulum.",
            whyIncorrect = "Retroversion is backwards twist leading to out-toeing. Genu varum is bowing. Coxa vara is neck vertical plane height.",
            clinicalImportance = "In-toeing must be monitored; persistent anteversion causes patellofemoral tracking strains.",
            examinerTip = "Excessive anteversion = in-toeing alignment. Excessive retroversion = out-toeing stance.",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "Excessive femoral anteversion requires hip internal rotation to optimize bony fit inside the hip socket.",
            relatedConcept = "Femoral Torsion",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        ),
        VivaMcq(
            question = "Why does an increased knee Q-angle elevate the risk of lateral patellar subluxation?",
            options = listOf(
                "It intensifies the lateral bowstringing vector of the quadriceps pulls on the patella",
                "It compresses the lateral popliteal nerve, causing peroneal palsy",
                "It shortens femoral length by 10 percent",
                "It limits reciprocal subtalar joint inversions"
            ),
            correctAnswer = "It intensifies the lateral bowstringing vector of the quadriceps pulls on the patella",
            whyCorrect = "High Q-angles (ASIS to patella, patella to tibia) create lateral bowstring vectors, pulling patella laterally out of groove.",
            whyIncorrect = "Does not crush popliteal nerves, shorten bone shapes or limit subtalar tilt ranges.",
            clinicalImportance = "Key in patellofemoral pain syndrome. Addressed by strengthening Vastus Medialis Obliquus (VMO).",
            examinerTip = "Higher Q-angle values multiply patellar lateral displacement vectors.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "An elevated Q-angle creates a lateral bowstring vector that pulls the patella outwards on the femur.",
            relatedConcept = "Patellofemoral Tracking Dynamics",
            reference = "Magee's Orthopedic Physical Assessment"
        ),
        VivaMcq(
            question = "When the pelvis undergoes posterior tilt, what relative motion occurs at the Sacroiliac (SI) joint?",
            options = listOf(
                "Counternutation; anterior base of sacrum tips backward relative to ilium",
                "Nutation; anterior base of sacrum tips forward relative to ilium",
                "Lateral separation of sacrum from pelvic rings by 5 centimeters",
                "Symmetric vertical gliding of sacral wings"
            ),
            correctAnswer = "Counternutation; anterior base of sacrum tips backward relative to ilium",
            whyCorrect = "Posterior pelvic tilt moves ilium posteriorly, presenting as counternutation where sacral base tilts backwards.",
            whyIncorrect = "Nutation is sacral anterior forward tilts, occurring during anterior pelvic tilt and locking the joints.",
            clinicalImportance = "Sacral nutation tightens robust pelvic ligaments, maximizing stability during heavy load lifts.",
            examinerTip = "Nutation = anterior pelvic tilt/stability. Counternutation = posterior pelvic tilt/laxity.",
            subject = "Kinesiology",
            difficulty = "Moderate",
            learningPoint = "Counternutation involves backward sacral base tilting relative to the pelvic wings, matching posterior pelvic tilts.",
            relatedConcept = "Sacroiliac Joint Kinematics",
            reference = "Joint Structure and Function by Norkin & Levangie"
        ),
        VivaMcq(
            question = "Which joint in the cervical column provides approximately 50% of total neck rotation?",
            options = listOf(
                "Atlanto-axial (C1-C2) pivot joint",
                "Atlanto-occipital (C0-C1) condylar joint",
                "C7-T1 cervicothoracic transition junction",
                "Uncovertebral C5 joint complex"
            ),
            correctAnswer = "Atlanto-axial (C1-C2) pivot joint",
            whyCorrect = "C1 (atlas) rotates around the dens of C2 (axis), acting as a pivot joint responsible for half of neck rotation.",
            whyIncorrect = "C0-C1 is condylar for nodding (Yes). C7-T1 is transitional. Uncovertebral are lateral guide tracks.",
            clinicalImportance = "C1-C2 instability (e.g. in RA or Down syndrome) can crush spinal cord during high manipulations.",
            examinerTip = "Atlas-Axis (C1-C2) handles horizontal rotation (No), while Atlas-Occiput (C0-C1) handles sagittal nodding (Yes).",
            subject = "Kinesiology",
            difficulty = "High Yield",
            learningPoint = "The C1-C2 pivot axis rotates atlas rings over dens cylinders, driving half of horizontal neck rotations.",
            relatedConcept = "Cervical Spine Kinematics",
            reference = "Neumann's Kinesiology of the Musculoskeletal System, 3rd Ed"
        )
    )
}
