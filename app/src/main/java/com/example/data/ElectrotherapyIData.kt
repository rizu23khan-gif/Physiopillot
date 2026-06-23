package com.example.data

object ElectrotherapyIData {
    val questions: List<VivaMcq> = listOf(
        // === FARADIC CURRENT (1-6) ===
        VivaMcq(
            question = "Which of the following descriptions best defines the pulse characteristics of a classic faradic-type current used in clinical practice?",
            options = listOf(
                "Short-duration, asymmetric, alternating current with a pulse duration of 0.1 to 1 ms and a frequency of 50 to 100 Hz",
                "Long-duration, continuous, direct current with a duration exceeding 100 ms and a constant unidirectional flow",
                "Medium-frequency, symmetric, sinusoidal current operating at 2500 Hz modulated at 50 Hz",
                "Monophasic, high-voltage spike current with a pulse duration of 5 to 20 microseconds"
            ),
            correctAnswer = "Short-duration, asymmetric, alternating current with a pulse duration of 0.1 to 1 ms and a frequency of 50 to 100 Hz",
            whyCorrect = "Classic faradic current is a short-duration (0.1 to 1 ms) asymmetric alternating current. Its frequency typically ranges between 50 and 100 Hz, which is sufficient to produce a tetanic muscle contraction.",
            whyIncorrect = "Continuous direct current describes galvanic current. Medium-frequency sinusoid modulated at 50 Hz describes Russian current. Ultra-short spikes describe high-voltage pulsed current (HVPC).",
            clinicalImportance = "Understanding these parameters allows clinicians to select faradic currents to selectively stimulate innervated muscles without stimulating painful sensory fibers unduly.",
            examinerTip = "Remember that faradic current requires a functional, fully innervated motor nerve to produce a muscle contraction.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Faradic current is defined by its short pulsatile alternating nature (0.1-1 ms duration) designed for motor stimulation of innervated nerves.",
            relatedConcept = "Pulse Characteristics of Current",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Why is faradic current completely ineffective at stimulating contraction in a denervated muscle belly?",
            options = listOf(
                "Because its pulse duration (0.1-1 ms) is too short to depolarize the muscle sarcolemma, which has a much higher threshold and chronaxie than a healthy nerve",
                "Because faradic current induces an acidic reaction under the anode that chemically denatures skeletal proteins",
                "Because denervated muscles only contract in response to high-frequency therapeutic ultrasound waves",
                "Because the alternating polarity of the current causes rapid muscle fatigue and immediate sensory habituation"
            ),
            correctAnswer = "Because its pulse duration (0.1-1 ms) is too short to depolarize the muscle sarcolemma, which has a much higher threshold and chronaxie than a healthy nerve",
            whyCorrect = "Nerve fibers are highly excitable with low chronaxie (<1 ms), whereas denervated muscle sarcolemma is less excitable with a high chronaxie (>10 ms). The faradic pulse terminates before the denervated muscle membrane can depolarize threshold.",
            whyIncorrect = "Anodic acid reactions are associated with continuous galvanic current, not faradic current. Muscles do not contract in response to ultrasound waves. Polarity is not the primary mechanism of action here.",
            clinicalImportance = "This is a basic rule in diagnostics: if a muscle responds to galvanic current but not to faradic, it indicates denervation.",
            examinerTip = "Examiners frequently ask this to test your understanding of why we transition from faradic to galvanic current when a nerve is severed.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Denervated muscle tissue requires longer electrical pulses (>10 ms) to depolarize than faradic current can provide.",
            relatedConcept = "Excitation Thresholds",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What is the primary physiological purpose of applying a 'surged' faradic current rather than a continuous faradic stimulation?",
            options = listOf(
                "To prevent neuromuscular fatigue and simulate the gradual contraction/relaxation profile of normal voluntary movement",
                "To increase the skin impedance and block cutaneous pain fibers dynamically",
                "To generate a powerful chemical chemical burn that destroys infected skin pathogens",
                "To force the release of high concentrations of calcium into the extracellular matrix"
            ),
            correctAnswer = "To prevent neuromuscular fatigue and simulate the gradual contraction/relaxation profile of normal voluntary movement",
            whyCorrect = "Surging progressively increases and decreases the intensity of successive pulses. This cyclical pattern gives the stimulated muscle a crucial resting phase, preventing fatigue and mimicking natural functional contractions.",
            whyIncorrect = "Surging decreases (does not increase) skin impedance over time. It is not used to cause chemical burns, and it does not force extracellular calcium release.",
            clinicalImportance = "Crucial for muscle re-education after tendon transplants or immobilization, ensuring progressive strengthening and patient comfort.",
            examinerTip = "Always specify 'surging parameters' including ramp-up, hold (plateau), and ramp-down times in clinical documentations.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Surging faradic current prevents rapid motor unit exhaustion and matches normal physiological ramping.",
            relatedConcept = "Current Surging Protocols",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "When applying faradic current to rehabilitate a weak quadriceps muscle, where should the active electrode be placed for optimal recruitment?",
            options = listOf(
                "Directly over the muscle's anatomical motor point, where the motor nerve enters the muscle belly",
                "Onto the bony tendon insertion close to the knee joint capsule",
                "Over the dermatomal cutaneous nerve distribution on the lateral aspect of the hip",
                "Directly over the contralateral side of the pelvis"
            ),
            correctAnswer = "Directly over the muscle's anatomical motor point, where the motor nerve enters the muscle belly",
            whyCorrect = "A muscle's motor point is the site where the motor nerve branch enters the muscle. At this spot, the electrical threshold for eliciting a muscle contraction is lowest.",
            whyIncorrect = "Placing electrodes on tendons, contralateral pelvic surfaces, or pure sensory cutaneous distributions results in painful sensory stimulation without useful motor contraction.",
            clinicalImportance = "Proper placement minimizes the amplitude of current needed, making the treatment safer and significantly more comfortable for patients.",
            examinerTip = "You can map motor points clinically using a pen-type stimulating electrode under low-dose faradic stimulation.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Motor points present the lowest electrical resistance and threshold for generating localized muscle contractions.",
            relatedConcept = "Electrode Placement and Motor Points",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What is 'Faradism under pressure' primarily indicated for, and how is it clinically applied?",
            options = listOf("Applying surged faradic current combined with compression bandaging and active muscle work to reduce persistent post-traumatic edema",
                "Using high-frequency microcurrents inside a hyperbaric oxygen chamber for tissue regeneration",
                "Infiltrating local anesthetic under the electrode to allow noxious high-voltage therapy",
                "Submerging the entire limb in hot water under mechanical pressure to increase nerve velocity"
            ),
            correctAnswer = "Applying surged faradic current combined with compression bandaging and active muscle work to reduce persistent post-traumatic edema",
            whyCorrect = "Faradism under pressure involves wrapping the limb in a compression bandage, elevating it, and stimulating rhythmic muscle contractions. This mimics the natural skeletal muscle venous pump to clear exudate.",
            whyIncorrect = "This does not involve hyperbaric chambers, local anesthesia infiltration, or hot water submersion under pressure.",
            clinicalImportance = "Highly beneficial for chronic ankle swelling or hand edema where active movement alone is limited by pain or weakness.",
            examinerTip = "The contraction must be strong enough to pump, but positioned correctly with appropriate elastic compression bandaging.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Passive electrical pumping combined with elastic compression is a potent physical therapy treatment for chronic local edema.",
            relatedConcept = "Edema Management Techniques",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which of the following conditions represents an absolute localized contraindication for the application of faradic current stimulation?",
            options = listOf(
                "An active deep vein thrombosis (DVT) in the stimulation path",
                "Chronic disuse atrophy of the calf muscle following cast removal",
                "Mild neurological weakness secondary to a chronic, stable Bell's palsy",
                "Post-operative knee stiffness following ligament repair (after active tissue healing)"
            ),
            correctAnswer = "An active deep vein thrombosis (DVT) in the stimulation path",
            whyCorrect = "Applying electrical stimulation over an active DVT can dislodge the thrombus due to physical muscle pumping, posing an immediate risk of life-threatening pulmonary embolism.",
            whyIncorrect = "Disuse atrophy, Bell's palsy, and healed post-operative knee stiffness are common, safe indications for rehabilitation using electrical stimulation.",
            clinicalImportance = "Conducting a thorough screening for vascular disorders prior to utilizing motor electrical stimulation is a fundamental safety duty of every physical therapist.",
            examinerTip = "Contraindications are popular test topics; always prioritize patient safety and circulatory risks.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Active muscle pumping over venous thrombi runs a high risk of thromboembolism.",
            relatedConcept = "Electrotherapy Safety and Screening",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === GALVANIC CURRENT (7-12) ===
        VivaMcq(
            question = "What is the defining electrical characteristic of a continuous galvanic current in electrotherapy?",
            options = listOf(
                "A continuous, unidirectional, low-voltage direct current with constant intensity and polarity",
                "An alternating current with high-frequency sinusoidal oscillations that reverses polarity every 10 microseconds",
                "A pulsed current consisting of asymmetric biphasic shapes with a net charge of zero",
                "An intermittent monophasic current with short-duration high-voltage pulses"
            ),
            correctAnswer = "A continuous, unidirectional, low-voltage direct current with constant intensity and polarity",
            whyCorrect = "Continuous galvanic current is uninterrupted direct current (DC). It flows in only one direction and maintains constant voltage, intensity, and polarity unless manually adjusted.",
            whyIncorrect = "Alternating current reverses direction. Biphasic pulse current has zero net charge. High-voltage pulsed current has narrow monophasic intervals.",
            clinicalImportance = "Galvanic current is useful for iontophoresis and stimulating denervated muscles (when interrupted), but requires caution due to chemical accumulation under electrodes.",
            examinerTip = "Galvanic means 'direct current' (DC) in medical electrotherapy textbooks.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Galvanic current is characterized by uninterrupted, low-voltage, continuous direct current flows.",
            relatedConcept = "Direct Current Principles",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Under continuous galvanic current therapy, what chemical and physiological reaction occurs specifically under the active cathode (negative electrode)?",
            options = listOf(
                "Accumulation of alkaline sodium hydroxide, which softens the skin tissue and increases local irritability",
                "Accumulation of hydrochloric acid, which hardens the skin tissue and reduces nerve irritability",
                "An immediate crystallization of calcium carbonate that completely blocks regional blood flow",
                "Release of pure oxygen bubbles that dry out the skin surface"
            ),
            correctAnswer = "Accumulation of alkaline sodium hydroxide, which softens the skin tissue and increases local irritability",
            whyCorrect = "The negative electrode (cathode) attracts positive sodium ions (Na+). When Na+ reacts with water, it forms sodium hydroxide (NaOH), an alkaline substance that softens tissues and increases nerve excitability.",
            whyIncorrect = "Acidic reactions and tissue hardening occur under the positive anode clue to the accumulation of hydrochloric acid (HCl), which decreases local irritability.",
            clinicalImportance = "Because NaOH softens the skin, chemical burns are significantly more common and severe under the cathode than the anode during galvanic treatments.",
            examinerTip = "Remember the mnemonic: 'Cathode is Alkaline (softens), Anode is Acidic (hardens)'.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Continuous DC induces polar chemical gradients: the cathode generates alkaline softening, while the anode produces acidic hardening.",
            relatedConcept = "Polar Chemical Effects of Direct Current",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "To minimize the risk of severe electrochemical burns during a galvanic current treatment, what is the maximum recommended current density for the active electrode?",
            options = listOf(
                "0.5 mA per square centimeter of electrode area",
                "5.0 mA per square centimeter of electrode area",
                "15 mA per square centimeter of electrode area",
                "There is no safety limit, as direct current cannot cause superficial burns"
            ),
            correctAnswer = "0.5 mA per square centimeter of electrode area",
            whyCorrect = "To prevent galvanic burns, current density must be kept low. A safe clinical guideline is a maximum current density of 1.5 to 2.0 mA/inch² (or approximately 0.1 to 0.5 mA/cm²). The cathode should keep to even slightly lower densities than the anode.",
            whyIncorrect = "Densities of 5.0 or 15 mA/cm² are extremely high and will cause rapid electrochemical tissue coagulation, necrosis, and deep ulceration.",
            clinicalImportance = "Always measure your electrode dimensions and calculate safe peak current output rather than relying purely on patient sensation.",
            examinerTip = "Exceeding 0.5 mA/cm² on direct current creates high risk of chemical burns even if the patient claims to feel fine.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Current density limits during direct current application protect the epidermis from local alkaline chemical accumulation.",
            relatedConcept = "Safe Current Densities",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which type of clinical direct current is specifically indicated for stimulating a muscle that has suffered complete peripheral nerve severing (denervation)?",
            options = listOf(
                "Interrupted direct current (IDC) with pulse durations of 100 to 600 ms",
                "Continuous uninterrupted direct current flowing at 15 mA",
                "High-frequency pulsed waves with a pulse duration of 50 ms and zero interval",
                "Short-duration alternating sinusoids of 0.5 ms"
            ),
            correctAnswer = "Interrupted direct current (IDC) with pulse durations of 100 to 600 ms",
            whyCorrect = "A denervated muscle requires long periods of flow (long-pulse direct current) to excite myofibrils directly. Standard durations of 100 to 600 ms with long intervals allow muscle depolarization and subsequent contraction.",
            whyIncorrect = "Continuous DC does not cause rhythmic muscle contraction (only at make/break points). Short alternating currents (<1 ms) are too brief to stimulate denervated tissue.",
            clinicalImportance = "Using IDC allows selective physical maintenance of denervated muscles while avoiding fatigue and preventing surrounding healthy structures from taking over.",
            examinerTip = "Interrupted direct current is also called 'interrupted galvanic current' or 'long-pulse direct current'.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Denervated muscle excitation requires long-pulse interrupted direct current to accommodate the extremely high chronaxie of muscle fiber.",
            relatedConcept = "Interrupted Galvanic Current",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "During the clinical application of iontophoresis, what is the correct orientation if a therapist wants to deliver dexamethasone sodium phosphate (a negatively charged anti-inflammatory drug) into a patellar tendon?",
            options = listOf(
                "Place the drug under the cathode (negative electrode) so that the negative charge repels the drug ions into the tissue",
                "Place the drug under the anode (positive electrode) to utilize electrostatic attraction to pull the drug in",
                "Alternate the drug between the anode and cathode every 30 seconds to secure neutral diffusion",
                "Apply the drug to both electrodes and use a high-frequency alternating current"
            ),
            correctAnswer = "Place the drug under the cathode (negative electrode) so that the negative charge repels the drug ions into the tissue",
            whyCorrect = "The physical principle of iontophoresis is that like charges repel. A negatively charged pharmaceutical ion must be placed under the negative electrode (cathode) so it is pushed away from the metal and down into the dermis.",
            whyIncorrect = "Placing a negative drug under the anode (positive electrode) would bind the drug to the electrode, halting therapeutic delivery. Alternating polarity or AC currents would not drive unidirectional transdermal movement.",
            clinicalImportance = "Allows localized delivery of concentrated corticosteroids to tissues matching localized tendonitis without causing systemic side effects.",
            examinerTip = "Always check the chemical charge of your prescription medication prior to preparing the iontophoresis pads.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Iontophoresis utilizes electrostatic repulsion (like charges repel) to drive active ions across the protective skin barrier.",
            relatedConcept = "Iontophoresis Electrodynamics",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What is the primary physical layer of resistance that galvanic current must overcome to enter deep tissues, and how can a therapist decrease it?",
            options = listOf(
                "The stratum corneum of the skin; reduced by pre-cleansing, moistening, and pre-warming the skin with a damp pad",
                "Deep subcutaneous adipose tissue layers; reduced by ice packs applied immediately before current delivery",
                "Deep cortical bone marrow; bypassed by local anesthetic creams",
                "Viscous interstitial blood flow; reduced by high-frequency compression garments"
            ),
            correctAnswer = "The stratum corneum of the skin; reduced by pre-cleansing, moistening, and pre-warming the skin with a damp pad",
            whyCorrect = "The stratum corneum possesses high electrical impedance. Cleansing removes oils, and applying warm moist electrode pads hydrates the keratinized cells, lowering resistance and ensuring uniform current distribution.",
            whyIncorrect = "Adipose tissue resistance is not reduced by ice (which actually increases impedance). Bone marrow and blood flow are deep to the skin and are not the primary barrier to current entry across the epidermis.",
            clinicalImportance = "Proper skin preparation ensures a more comfortable treatment session and prevents localized 'hot spots' that cause chemical or thermal burns.",
            examinerTip = "Clinicians should always inspect the skin surface for cuts, as current will concentrate at breaks in the skin, risking burns.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Hydration and cleansing of the stratum corneum lowers epidermal resistivity for safe electrotherapeutic delivery.",
            relatedConcept = "Skin Impedance and Preparation",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === SD CURVE (13-18) ===
        VivaMcq(
            question = "What fundamental physiological relationship is represented in a Strength-Duration (SD) curve in clinical electrodiagnosis?",
            options = listOf(
                "The relationship between the intensity of an electrical stimulus and the minimum pulse duration required to elicit a threshold muscle contraction",
                "The relationship between the frequency of an alternating current and the rate of subcutaneous tissue heating",
                "The speed of joint angle movement relative to the resistance load generated by an external weight",
                "The rate of nerve regeneration following surgical anastomosis over a 12-month period"
            ),
            correctAnswer = "The relationship between the intensity of an electrical stimulus and the minimum pulse duration required to elicit a threshold muscle contraction",
            whyCorrect = "The SD curve plots threshold excitability of tissues by showing the current strength (intensity in mA or V) as a function of stimulus duration (in ms). It establishes the minimal energy required to trigger an action potential.",
            whyIncorrect = "It does not track thermal distribution, joint motion speed, or nerve physical growth rates directly.",
            clinicalImportance = "Allows objective clinical assessment of the level of denervation, partial denervation, or neural reinnervation of a skeletal muscle.",
            examinerTip = "The curve's coordinate points shift depending on whether the tissue is an innervated motor nerve or denervated sarcolemma.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "An SD curve demonstrates the inverse relationship between the intensity and duration of electrical current needed for nerve/muscle excitation.",
            relatedConcept = "SD Curve Principles",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "How is 'Rheobase' defined when analyzing a Strength-Duration (SD) curve?",
            options = listOf(
                "The minimum intensity of electrical current needed to produce a threshold contraction using a stimulus of infinite (or long) duration",
                "The minimum duration of a pulse required to produce a contraction at exactly double the voltage threshold",
                "The maximum frequency of alternating current that can be applied to muscle fibers without causing pain",
                "The rate at which a healthy nerve can accommodate slowly rising currents"
            ),
            correctAnswer = "The minimum intensity of electrical current needed to produce a threshold contraction using a stimulus of infinite (or long) duration",
            whyCorrect = "Rheobase is the lowest intensity of a long-duration pulse (usually 100 ms or more) that will elicit a threshold muscle contraction. It measures membrane sensitivity to current amplitude alone.",
            whyIncorrect = "Pulse duration at double the voltage defines chronaxie. Alternating current frequency thresholds and accommodation rates are distinct physiological parameters.",
            clinicalImportance = "Provides a baseline value from which other electrical diagnostic calculations (e.g. chronaxie and accommodation ratio) are derived.",
            examinerTip = "If a nerve is chronically inflamed or damaged, the rheobase may rise significantly due to membrane instability.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Rheobase is the threshold current amplitude required for excitation with a long, un-restricted pulse duration (e.g., 100-300 ms).",
            relatedConcept = "Rheobase",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which of the following values represents the typical 'Chronaxie' range for a healthy, normally innervated motor nerve compared to a denervated muscle belly?",
            options = listOf(
                "Innervated nerve: 0.01 to 0.1 ms; Denervated muscle: 10 to 50 ms",
                "Innervated nerve: 10 to 50 ms; Denervated muscle: 0.01 to 0.1 ms",
                "Both tissues display an identical chronaxie of exactly 1.0 ms",
                "Innervated nerve: 500 to 1000 ms; Denervated muscle: 2000 to 5000 ms"
            ),
            correctAnswer = "Innervated nerve: 0.01 to 0.1 ms; Denervated muscle: 10 to 50 ms",
            whyCorrect = "Myelinated motor nerves are highly excitable and have a very short chronaxie (usually 0.01 to 0.1 ms). In contrast, denervated muscle fibers are less excitable and have a chronaxie 100 to 1000 times longer (10 to 50 ms).",
            whyIncorrect = "An innervated nerve never has a chronaxie as high as 10-50 ms. Denervated muscle never yields short values like 0.1 ms. Hundreds or thousands of milliseconds represent unphysiological intervals.",
            clinicalImportance = "A lengthening chronaxie over sequential tests is a sensitive indicator of progressive peripheral motor nerve degeneration.",
            examinerTip = "By definition, chronaxie is the duration of an electrical pulse required to produce excitation at twice the rheobase intensity.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Chronaxie measures tissue excitability, with healthy innervated motor nerves displaying extremely short durations compared to denervated muscle.",
            relatedConcept = "Chronaxie Differences",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "A physical therapist plots an SD curve for a patient recovering from a fibular nerve injury. The resulting plot shows a prominent 'kink' or 'double peak' in the curve. What does this finding indicate?",
            options = listOf(
                "Partial denervation of the muscle, representing the presence of two distinct excitable populations (innervated and denervated fibers)",
                "Complete, irreversible necrosis of all muscle motor units in the lower leg",
                "A perfectly healthy nerve with a high rate of anatomical accommodation",
                "A calibration error in the high-voltage electrical stimulator unit"
            ),
            correctAnswer = "Partial denervation of the muscle, representing the presence of two distinct excitable populations (innervated and denervated fibers)",
            whyCorrect = "A 'kink' or 'double curve' appears when a muscle is partially denervated. The lower left part of the curve represents the highly excitable innervated nerve fibers, and the upper right represents the denervated muscle fibers.",
            whyIncorrect = "Complete denervation shows a smooth curve shifted fully up and to the right, without a kink. Perfect health shows a smooth, low curve to the left. It does not indicate equipment malfunction.",
            clinicalImportance = "Helps monitor recovery. As the nerve regenerates and reinnervates fibers, the kink shifts to the right and eventually disappears.",
            examinerTip = "The kink marks the threshold transition zone between axonal stimulation and direct muscular stimulation.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "The classic 'kink' in an SD curve is the clinical hallmark of partial nerve denervation.",
            relatedConcept = "Partial Denervation Curves",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which group of nerve fibers has the lowest threshold of excitation and will be stimulated first at short pulse durations on the SD curve?",
            options = listOf(
                "Large, myelinated A-alpha motor axons",
                "Small, unmyelinated C sensory pain fibers",
                "Thinly myelinated B preganglionic autonomic fibers",
                "Skeletal muscle sarcolemma fibers directly"
            ),
            correctAnswer = "Large, myelinated A-alpha motor axons",
            whyCorrect = "According to Ohm's and Cable laws, larger diameter myelinated axons (such as A-alpha motor fibers) have low internal resistance, higher conduction velocity, and the lowest threshold of electrical excitation.",
            whyIncorrect = "C fibers and muscle fibers have extremely high thresholds and require much larger intensities or pulse durations to depolarize.",
            clinicalImportance = "Explains why we can elicit a strong, painless muscle contraction using brief electrical impulses because motor fibers excite before pain fibers.",
            examinerTip = "Large diameter myelinated fibers are всегда recruited first during artificial electrical stimulation (reverse of Henneman's size principle).",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Axon diameter and myelination govern electrical thresholds, making large myelinated motor fibers the most excitable.",
            relatedConcept = "Sensory and Motor Nerve Recruitment",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "How does the Strength-Duration (SD) curve of a completely denervated muscle compare to its original innervated state?",
            options = listOf(
                "The entire curve is shifted upward and to the right, showing that excitation is only possible at long pulse durations and higher intensities",
                "The curve shifts downward and to the left, showing super-sensitisation and hyper-excitability",
                "The curve remains entirely unchanged, but the color of the plot changes due to tissue hypoxia",
                "The curve becomes a horizontal straight line across all pulse durations"
            ),
            correctAnswer = "The entire curve is shifted upward and to the right, showing that excitation is only possible at long pulse durations and higher intensities",
            whyCorrect = "Upon complete denervation, the highly excitable nerve pathway is lost, leaving only muscle fibers. Muscle sarcolemma has higher chronaxie and rheobase, shifting the entire curve upward and to the right.",
            whyIncorrect = "A shift to the left/downward would represent hyper-excitability (decreased thresholds), which does not happen in structural denervation. The curve never becomes a horizontal flat line.",
            clinicalImportance = "Confirms complete nerve interruption; guides the therapist to use long pulse galvanic currents instead of faradic current.",
            examinerTip = "Look for the complete absence of a 'kink' combined with the upward-right translation to diagnose complete denervation.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Complete denervation eliminates low-threshold motor nerve pathways, shifting the SD curve up and to the right.",
            relatedConcept = "Denervation Curve Characteristics",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === I/T CURVE (19-24) ===
        VivaMcq(
            question = "In clinical electrophysiology, what is the main difference in plotting an Intensity-Time (I/T) curve using rectangular pulses versus triangular (slow-rising) pulses?",
            options = listOf(
                "Healthy innervated nerves accommodate to progressive, slow-rising triangular pulses, while denervated muscles cannot accommodate and respond to both",
                "Rectangular pulses cause severe tissue healing, while triangular pulses destroy tissue structures",
                "Triangular pulses require 100 times less voltage than rectangular pulses to depolarize a nerve",
                "There is no functional difference; they yield identical curves under all healthy and pathological states"
            ),
            correctAnswer = "Healthy innervated nerves accommodate to progressive, slow-rising triangular pulses, while denervated muscles cannot accommodate and respond to both",
            whyCorrect = "A key diagnostic feature of a healthy nerve is its ability to 'accommodate' (undergo threshold adaptation) to a slowly rising stimulus (triangular/exponential pulse). Denervated muscle fibers lack this rapid accommodation mechanism.",
            whyIncorrect = "Neither pulse style is inherently destructive or healing. Triangular pulses actually require *higher* amplitudes in healthy tissue than rectangular ones due to the nerve's accommodation.",
            clinicalImportance = "Allows therapists to selectively stimulate denervated muscles using triangular pulses. The adjacent healthy muscles accommodate and remain quiet.",
            examinerTip = "Accommodation is driven by slow inactivation of voltage-gated sodium channels during a slow-rising stimulus.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Innervated nerves accommodate to slowly rising electrical pulses, raising their excitation threshold, whereas denervated fibers do not.",
            relatedConcept = "Accommodation and Pulse Style",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "The 'Accommodation Factor' (or Accommodation Ratio) is calculated during electrical testing of muscle tissue. What value is typical for healthy innervated tissue, and what happens to this ratio in denervation?",
            options = listOf(
                "Innervated: 3 to 5; Denervated: drops toward 1",
                "Innervated: 1; Denervated: rises to 15",
                "Both maintain a constant ratio of 0.5 under all conditions",
                "Innervated: 0.1; Denervated: drops to 0.001"
            ),
            correctAnswer = "Innervated: 3 to 5; Denervated: drops toward 1",
            whyCorrect = "The Accommodation Factor is the ratio of the threshold for a triangular pulse to that of a rectangular pulse of the same duration. In healthy nerves, accommodation is high (ratio of 3 to 5). In denervation, accommodation is lost, so the ratio falls toward 1.",
            whyIncorrect = "A ratio of 1 indicates no accommodation, characteristic of denervated tissue. Ratios below 1 are mathematically impossible under standard testing parameters.",
            clinicalImportance = "A quick, quantifiable metric to confirm denervation states without plotting a full multi-point curve.",
            examinerTip = "If the ratio is near 1, the tissue has lost its myelin sheath or axonal pathway, or is direct muscle.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "The accommodation ratio measures nerve-to-muscle adaptability; a low ratio (~1) indicates denervation.",
            relatedConcept = "Accommodation Ratio Calculations",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "When plotting an I/T curve, is it medically necessary to test pulse durations down to extremely brief intervals like 0.01 ms? Why?",
            options = listOf(
                "Yes, because healthy innervated motor nerves still respond to 0.01 ms pulses, whereas denervated muscles cease responding below 10 ms",
                "No, because brief pulses burn the skin immediately due to high capacitance",
                "No, because no clinical electrotherapy machine can generate pulses shorter than 10 ms",
                "Yes, because denervated muscles only show excitability at 0.01 ms"
            ),
            correctAnswer = "Yes, because healthy innervated motor nerves still respond to 0.01 ms pulses, whereas denervated muscles cease responding below 10 ms",
            whyCorrect = "Testing across a wide range (1000 ms down to 0.01 ms) helps differentiate tissue types. Innervated motor nerves have low chronaxie and still contract at brief durations, while denervated muscles cannot depolarize at these short durations.",
            whyIncorrect = "Brief pulses have lower skin impedance and are more comfortable, not burning. All modern clinical stimulators can easily generate microsecond pulses. Denervated muscle cannot respond to ultra-brief pulses.",
            clinicalImportance = "Ensures an accurate diagnostic plot to rule out sub-clinical neuropraxia or monitor axonotmesis recovery.",
            examinerTip = "Plotting from 1000 ms down to 0.1 ms or 0.01 ms is essential to capture the full shape of the threshold curve.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Plotting broad pulse duration ranges (1000 ms to 0.01 ms) is necessary to differentiate nerve versus muscle fiber thresholds.",
            relatedConcept = "I/T Curve Testing Parameters",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "In an I/T curve diagnostic plot, which morphological change indicates the earliest sign of neural regeneration before clinical movement is observed?",
            options = listOf(
                "A progressive leftward shift of the 'kink' and a lowering of the threshold at shorter pulse durations",
                "An increase in the overall slope of the curve shifting it further right",
                "A sudden flat line indicating complete muscle failure",
                "A large increase in the rheobase value matching high resistance"
            ),
            correctAnswer = "A progressive leftward shift of the 'kink' and a lowering of the threshold at shorter pulse durations",
            whyCorrect = "As regenerating axons reach the motor points, the muscle fibers are re-innervated. On the I/T plot, this shows as a new, highly excitable nerve response at shorter pulse durations, shifting the kink to the left and lowering the threshold.",
            whyIncorrect = "A shift to the right indicates worsening denervation. A flat line or rising rheobase does not suggest axonal recovery, but rather muscle atrophy or progressive fibrosis.",
            clinicalImportance = "Provides encouragement to both patient and therapist by demonstrating physiological recovery weeks before visible muscle contraction returns.",
            examinerTip = "Electrophysiological recovery on the I/T curve typically precedes visible voluntary movement.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Reinnervation displays on the I/T curve as a leftward shift of the threshold kink as shorter pulses become effective again.",
            relatedConcept = "Electrophysiological Signs of Regeneration",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which clinical factor can temporarily shift an I/T curve falsely to the right, mimicking a worse denervation state than reality?",
            options = listOf(
                "Severe tissue edema or cold skin temperature under the stimulating electrodes",
                "High systemic blood pressure during testing",
                "The patient consuming caffeine or stimulants prior to treatment",
                "Using a large, moist active electrode instead of a dry metal plate"
            ),
            correctAnswer = "Severe tissue edema or cold skin temperature under the stimulating electrodes",
            whyCorrect = "Edema increases the pathway distance and disperses the current, while cold temperature reduces nerve conduction velocity and increases membrane threshold. Both raise the apparent electrical threshold, shifting the curve to the right.",
            whyIncorrect = "Systemic blood pressure and caffeine do not cause major rightward physiological shifts in peripheral muscle/nerve chronaxie. Large, moist electrodes *improve* conduction and prevent false rightward shifts.",
            clinicalImportance = "Always warm the patient's limb and clear localized edema before diagnostic testing to avoid clinical misinterpretation.",
            examinerTip = "Ensure skin temperature is normalized (~34°C) to get valid, reproducible diagnostic curves.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Tissue edema and cold temperature elevate the electrical distance and threshold, distorting diagnostic curves.",
            relatedConcept = "Factors Distorting Electrophysiologic Testing",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Why is it clinical best practice to utilize a constant-current (CC) stimulator rather than a constant-voltage (CV) stimulator when plotting an I/T curve?",
            options = listOf(
                "Because CC stimulators automatically adjust the voltage to overcome variations in skin impedance during the test, ensuring a precise current delivery",
                "Because CV stimulators are prohibited because they generate radio-frequency waves that interfere with cardiac cycles",
                "Because CC units only work on denervated muscles and cannot stimulate healthy nerves",
                "Because constant current eliminates the need for any electrode gel or contact pads"
            ),
            correctAnswer = "Because CC stimulators automatically adjust the voltage to overcome variations in skin impedance during the test, ensuring a precise current delivery",
            whyCorrect = "During an I/T curve test, skin impedance can change. A constant-current (CC) stimulator adjusts the voltage dynamically to keep the current flow (intensity in mA) constant. This ensures that threshold measurements are precise and reproducible.",
            whyIncorrect = "CV stimulators are not prohibited and do not generate cardiac radio waves. CC units work on all excitable tissues. Contact pads are always required for conductivity.",
            clinicalImportance = "Using a CC stimulator ensures that changes in skin resistance do not cause false threshold changes during the test.",
            examinerTip = "Constant current is represented as intensity in milliamperes (mA), whereas constant voltage is measured in volts (V).",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Constant-current stimulation provides more accurate diagnostic data because it is unaffected by skin impedance changes during testing.",
            relatedConcept = "Electrophysiological Equipment Settings",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === DENERVATION (25-30) ===
        VivaMcq(
            question = "Which of the following cellular changes occurs in a skeletal muscle membrane after a peripheral motor nerve is cut (complete denervation)?",
            options = listOf(
                "Proliferation and spread of extrajunctional acetylcholine receptors (AChRs) across the entire sarcolemma surface, leading to hypersensitivity",
                "Immediate transformation of Type II fast muscle fibers into cardiac muscle cells",
                "Complete disappearance of all sarcoplasmic reticulum calcium channels within 24 hours",
                "A dramatic increase in the resting membrane potential from -90 mV to -200 mV"
            ),
            correctAnswer = "Proliferation and spread of extrajunctional acetylcholine receptors (AChRs) across the entire sarcolemma surface, leading to hypersensitivity",
            whyCorrect = "When the motor nerve is severed, the focal neuromuscular junction degenerates. Acetylcholine receptors (extrajunctional AChRs) proliferate and spread across the entire sarcolemma, making the muscle membrane hypersensitive.",
            whyIncorrect = "Skeletal fibers do not turn into cardiac cells. Sarcoplasmic calcium channels do not disappear. The resting membrane potential depolarizes slightly (grows closer to zero, e.g., -60 to -70 mV), rather than hyperpolarizing to -200 mV.",
            clinicalImportance = "This denervation hypersensitivity explains why fibrillations and fasciculations occur in denervated muscle tissue.",
            examinerTip = "Hypersensitivity to circulating acetylcholine is a classic feature of denervated skeletal muscle tissue.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Denervation leads to extrajunctional receptor proliferation, causing membrane hypersensitivity and spontaneous fibrillations.",
            relatedConcept = "Physiological Sequelae of Denervation",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "What is the primary therapeutic goal of applying electrical stimulation to a completely denervated muscle belly?",
            options = listOf(
                "To delay muscle atrophy, preserve contractile tissue properties, and retard fibrotic changes until reinnervation can occur",
                "To accelerate the physical rate of axonal sprout growth from the spinal cord",
                "To permanently replace the severed peripheral nerve with artificial electrical circuits",
                "To induce calcification of the joint capsular ligaments to prevent movement"
            ),
            correctAnswer = "To delay muscle atrophy, preserve contractile tissue properties, and retard fibrotic changes until reinnervation can occur",
            whyCorrect = "Electrical stimulation of denervated muscle maintains muscle fiber diameter, delays muscle atrophy, and maintains blood flow and mobility, keeping the contractile tissue viable until regenerating axons can reinnervate it.",
            whyIncorrect = "It does not speed up raw axonal regeneration rates (which occur at ~1 mm/day). Muscle tissue cannot permanently substitute for nerves, and joint calcification is an adverse pathology, not a goal.",
            clinicalImportance = "Essential for improving the post-reinnervation recovery outcome in hand or leg muscle injuries.",
            examinerTip = "Electrical stimulation does not speed up nerve regeneration; it keeps the target muscle viable while the nerve heals.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Stimulation retardates denervation atrophy and maintains mechanical muscle properties during the reinnervation window.",
            relatedConcept = "Goals of Muscle Stimulation",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which pulse waveform and duration are most appropriate for safely stimulating a denervated muscle while preventing contraction in adjacent healthy muscles?",
            options = listOf(
                "Slow-rising exponential (triangular) pulses with a duration of 100 to 300 ms",
                "Rapid-onset rectangular pulses with a duration of 0.1 ms",
                "High-frequency sinusoidal waves operating at 50,000 Hz",
                "Asymmetric biphasic waves with a pulse duration of 20 microseconds"
            ),
            correctAnswer = "Slow-rising exponential (triangular) pulses with a duration of 100 to 300 ms",
            whyCorrect = "A slow-rising exponential pulse takes advantage of accommodation. Healthy adjacent nerves accommodate to the slow rise and do not fire. The denervated muscle cannot accommodate, so it depolarizes and contracts.",
            whyIncorrect = "Short rectangular pulses (0.1 ms), high-frequency currents, and brief biphasic waves cannot depolarize denervated sarcolemma because their pulse durations are far below its chronaxie.",
            clinicalImportance = "This parameters allow selective stimulation of deep denervated fibers without causing pain or unwanted muscle contractions nearby.",
            examinerTip = "Exponential current stimulates denervated fibers while allowing adjacent innervated motor units to accommodate.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Slow-rising exponential pulses exploit nerve accommodation to stimulate denervated muscle selectively.",
            relatedConcept = "Selective Muscle Stimulation",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What is Wallerian Degeneration, and how does it affect electrodiagnostic findings in the first 14 days post nerve injury?",
            options = listOf(
                "The physiological breakdown of the axon and myelin sheath distal to a nerve lesion, after which faradic excitability is completely lost",
                "The rapid proliferation of Schwann cells that completely blocks all electrical current from entering tissue",
                "The calcification of muscle fibers distal to the nerve lesion",
                "An immediate, permanent shift of the muscle chronaxie from 30 ms to 0.01 ms"
            ),
            correctAnswer = "The physiological breakdown of the axon and myelin sheath distal to a nerve lesion, after which faradic excitability is completely lost",
            whyCorrect = "Following a nerve transaction, Wallerian degeneration of the distal segment occurs over 7 to 14 days. Once the nerve axon breaks down, the motor point loses nerve-mediated faradic excitability completely.",
            whyIncorrect = "Wallerian degeneration does not refer to Schwann cell blocking, myofibril calcification, or hyper-excitability shifts in muscle.",
            clinicalImportance = "This is why diagnostic testing for denervation is typically scheduled 14 to 21 days post-injury; testing too early can yield false-normal results because the distal axon has not yet completely degenerated.",
            examinerTip = "The loss of nerve-mediated responses occurs after the distal axon segment has fully degenerated.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Wallerian degeneration involves the breakdown of the isolated distal axon, leading to a loss of faradic nerve excitability within 2 weeks of injury.",
            relatedConcept = "Wallerian Degeneration and Electrodiagnosis",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "A patient presents with complete denervation of the Tibialis Anterior muscle 3 months after a deep laceration to the common fibular nerve. Which of the following electrotherapy regimens is indicated?",
            options = listOf(
                "Interrupted direct current (or exponential pulses), 100-300 ms duration, 10 to 20 contractions per session, 2-3 times daily",
                "High-frequency conventional TENS applied continuously for 24 hours at a sensory level",
                "Russian current applied at maximum intensity for 45 continuous minutes twice a week",
                "No electrical treatments, as denervated muscle should be left completely immobilized for 12 months"
            ),
            correctAnswer = "Interrupted direct current (or exponential pulses), 100-300 ms duration, 10 to 20 contractions per session, 2-3 times daily",
            whyCorrect = "Denervated muscle tissue is preserved best by moderate, daily stimulation. Long-duration direct or exponential pulses (100-300 ms) should be used, with limited repetitions (10-20 contractions) to prevent muscle damage, repeated throughout the day.",
            whyIncorrect = "Conventional TENS lacks the pulse width to stimulate denervated tissue. Russian current uses short burst durations aimed at innervated nerves. Complete long-term immobilization leads to muscle fibrosis and permanent loss of function.",
            clinicalImportance = "Allows clinicians to preserve muscle viability without overload or muscle fatigue.",
            examinerTip = "Fewer contractions per session help avoid fatigue and damage in denervated muscle, while multiple daily sessions keep fibers active.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Clinical management of denervation uses long-duration galvanic/exponential pulses, moderate repetitions, and multiple daily sessions.",
            relatedConcept = "Denervation Stimulation Protocol",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "How does the pathway of electrical current flow contrast when stimulating an innervated muscle versus a denervated muscle?",
            options = listOf(
                "Innervated muscle is stimulated via its motor nerve axon; denervated muscle must be stimulated by direct longitudinal current flow through the muscle fibers themselves",
                "Innervated muscle requires the current to flow through deep lymphatic vessels; denervated muscle holds current purely in subcutaneous cells",
                "There is no difference; both are stimulated by depolarizing the local dorsal root ganglia in the spinal cord",
                "Innervated muscle utilizes transdermal chemical ions; denervated muscle uses mechanical pressure vectors"
            ),
            correctAnswer = "Innervated muscle is stimulated via its motor nerve axon; denervated muscle must be stimulated by direct longitudinal current flow through the muscle fibers themselves",
            whyCorrect = "Normally, stimulating an innervated muscle depolarizes its motor nerve axon first, which then recruits all muscle fibers in that motor unit. In denervation, the axons are gone, so current must flow longitudinally through the muscle belly to depolarize individual muscle sarcolemma directly.",
            whyIncorrect = "Lymphatic channels, dorsal root ganglia, and physical pressure vectors are not the primary pathways of current flow for muscle depolarization.",
            clinicalImportance = "This difference requires larger electrodes and higher currents to stimulate denervated tissue directly, compared to stimulating an innervated motor point.",
            examinerTip = "Direct muscle stimulation (denervation) requires different electrode placement and sizing compared to motor-point nerve stimulation.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Innervated stimulation acts via nerve axons; denervated stimulation requires direct, longitudinal current flow through muscle sarcolemma.",
            relatedConcept = "Target Excitability Pathways",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === NMES (31-37) ===
        VivaMcq(
            question = "How does the order of motor unit recruitment differ during an electrically-induced contraction (NMES) compared to a normal voluntary contraction?",
            options = listOf(
                "Voluntary contraction recruits small, fatigue-resistant Type I motor units first; NMES recruits large, fast-fatiguable Type II units first",
                "Voluntary contraction recruits Type II fibers first; NMES recruits Type I fibers first",
                "There is no difference; both recruit motor units in a completely random order",
                "NMES only recruits cardiac muscle fibers, bypassing skeletal muscle fibers entirely"
            ),
            correctAnswer = "Voluntary contraction recruits small, fatigue-resistant Type I motor units first; NMES recruits large, fast-fatiguable Type II units first",
            whyCorrect = "According to Henneman's Size Principle, voluntary contraction recruits small, oxidative, fatigue-resistant Type I units first. NMES recruits motor units in reverse order: large-diameter Type II fast-glycolytic units are stimulated first because they present lower electrical resistance.",
            whyIncorrect = "NMES does not recruit Type I first, does not recruit randomly, and does not stimulate cardiac muscle fibers in skeletal rehabilitation.",
            clinicalImportance = "Because NMES preferentially recruits easily fatiguable Type II fibers, electrically induced contractions cause muscle fatigue much more rapidly than voluntary exercise.",
            examinerTip = "Highlight 'reverse recruitment order' as the primary physiological distinction between voluntary and electrically-induced muscle activity.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "NMES recruits large, fast-fatigating Type II motor units first (violating Henneman's size principle), causing rapid neuromuscular fatigue.",
            relatedConcept = "Motor Unit Recruitment",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "Which carrier frequency and burst frequency are classic characteristics of the 'Russian Current' used for muscle strengthening?",
            options = listOf(
                "Carrier frequency of 2500 Hz, delivered in bursts at 50 Hz",
                "Carrier frequency of 100 Hz, delivered in bursts at 2000 Hz",
                "Carrier frequency of 1 MHz, delivered continuously without bursts",
                "Carrier frequency of 5 Hz, delivered in bursts at 1 Hz"
            ),
            correctAnswer = "Carrier frequency of 2500 Hz, delivered in bursts at 50 Hz",
            whyCorrect = "Classic Russian current is a medium-frequency (2500 Hz) sinusoidal alternating current delivered in 50 Hz bursts. This carrier frequency lowers skin impedance, while the burst rate is optimized to cause strong muscle contraction.",
            whyIncorrect = "A 100 Hz carrier with 2000 Hz bursts would be ineffective. 1 MHz describes ultrasound, not dry motor stimulation. A 5 Hz carrier is too low and lacks the advantages of medium-frequency stimulation.",
            clinicalImportance = "Russian current is widely used to hypertrophy quadriceps muscle and restore torque output in elite athletes and rehabilitation.",
            examinerTip = "Kots (the developer) used a specific 10/50/10 regimen: 10 seconds of stimulation, 50 seconds of rest, for 10 repetitions.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Russian current utilizes a 2500 Hz carrier frequency modulated at a 50 Hz burst rate to achieve deep, strong muscle contractions.",
            relatedConcept = "Russian Current Parameters",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What minimum electrical pulse frequency is typically required to elicit a fully fused tetanic muscle contraction during NMES?",
            options = listOf(
                "35 to 50 pulses per second (pps)",
                "1 to 5 pulses per second (pps)",
                "500 to 1000 pulses per second (pps)",
                "Exactly 1,000,000 Hz"
            ),
            correctAnswer = "35 to 50 pulses per second (pps)",
            whyCorrect = "To achieve a smooth, tetanic contraction without visible muscle tremor, NMES frequency must typically be set to 35 to 50 pps (or Hz). This causes individual muscle twitches to fuse.",
            whyIncorrect = "1 to 5 pps results in separate, individual muscle twitches, used for pumping or acupuncture-TENS. Higher rates (500-1000 pps) are in the sensory range and can cause rapid fatigue if used for motor stimulation. 1,000,000 Hz is thermal frequency.",
            clinicalImportance = "Ensures a comfortable, functional muscle contraction during exercises like gait training or quad sets.",
            examinerTip = "Frequencies above 50-80 pps increase contraction force only minimally but cause rapid, premature muscle fatigue.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Muscle tetanization or fusion typically occurs at frequency thresholds of 35-50 pulses per second.",
            relatedConcept = "Frequency Settings in NMES",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "When prescribing NMES for rehabilitating disuse atrophy in a weak muscle, what is the best initial on:off (duty cycle) ratio to prevent muscle fatigue?",
            options = listOf(
                "1:5 (e.g., 10 seconds on, 50 seconds off)",
                "1:1 (e.g., 10 seconds on, 10 seconds off)",
                "Continuous stimulation for 30 minutes without a rest cycle",
                "10:1 (e.g., 100 seconds on, 10 seconds off)"
            ),
            correctAnswer = "1:5 (e.g., 10 seconds on, 50 seconds off)",
            whyCorrect = "Because NMES recruits fast-glycolytic motor units first, stimulated muscles fatigue quickly. An initial on:off ratio of 1:5 allows sufficient rest for ATP restoration and cellular clearance between contractions.",
            whyIncorrect = "Ratios like 1:1, continuous flow, or 10:1 do not allow enough rest, leading to rapid muscle fatigue and potential structural damage.",
            clinicalImportance = "Using a 1:5 duty cycle ensures patients can tolerate a full treatment session of 10 to 15 quality contractions.",
            examinerTip = "As muscle endurance improves, the duty cycle can be progressed to 1:3 or 1:2.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "A conservative 1:5 duty cycle prevents rapid fatigue of easily exhaustible Type II motor fibers recruited by NMES.",
            relatedConcept = "NMES Duty Cycle",
            reference = "Kisner & Colby's Therapeutic Exercise, 7th Ed"
        ),
        VivaMcq(
            question = "A physical therapist uses NMES to manage stress urinary incontinence in a patient with pelvic floor weakness. What is the physiological mechanism of this treatment?",
            options = listOf(
                "Direct electrical stimulation of the pudendal nerve branches and levator ani, inducing hypertrophy of external sphincter muscle fibers",
                "Chemical denervation of the detrusor muscle to prevent bladder compliance",
                "Applying thermal electrical energy to shrink the bladder wall structure",
                "Inhibiting all active sacral reflexes via thermal fields"
            ),
            correctAnswer = "Direct electrical stimulation of the pudendal nerve branches and levator ani, inducing hypertrophy of external sphincter muscle fibers",
            whyCorrect = "NMES to the pelvic floor stimulates the pudendal nerve branches to contract pelvic floor muscles (like the levator ani and pubococcygeus). This strengthens and hypertrophies the urethral closure muscles.",
            whyIncorrect = "Pelvic NMES does not cause chemical denervation, thermal tissue shrinkage, or broad inhibition of sacral reflexes.",
            clinicalImportance = "Provides an effective conservative option for patients who struggle to perform correct voluntary pelvic floor exercises (Kegels).",
            examinerTip = "Frequencies of 50 Hz are typically used for strengthening, while 10-20 Hz are used for sensory feedback or urge symptoms.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Pelvic NMES targets the pudendal nerve pathways to restore mechanical support and external sphincter tone.",
            relatedConcept = "Pelvic Floor Electrical Stimulation",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),
        VivaMcq(
            question = "What is the primary function of the 'ramp-up' and 'ramp-down' times in an NMES device parameter menu?",
            options = listOf(
                "To gradually transition current intensity to maximize patient comfort and prevent sudden, painful muscle jerking",
                "To change the chemical composition of the gel pads during the stimulation phase",
                "To alter the carrier frequency from 20 Hz to 20,000 Hz automatically",
                "To regulate the physical temperature of the skin under the active electrodes"
            ),
            correctAnswer = "To gradually transition current intensity to maximize patient comfort and prevent sudden, painful muscle jerking",
            whyCorrect = "Ramp-up progressively increases current intensity from zero to the target peak over several seconds. This gradual rise is much more comfortable for the patient and permits functional coordination.",
            whyIncorrect = "Ramping does not alter gel chemistry, carrier frequency ranges, or regulate skin temperature.",
            clinicalImportance = "Essential for orthotic applications, such as a foot drop stimulator, where a smooth ramp mimics natural dorsiflexion during swing phase.",
            examinerTip = "Ramp times are included in the overall 'on' time when calculating total stimulation duration.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Ramping parameters smooth the onset and offset of stimulation, improving comfort and functional movement integration.",
            relatedConcept = "Ramping Parameters",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which of the following clinical uses describes Functional Electrical Stimulation (FES)?",
            options = listOf(
                "Using electrical stimulation to contract dorsiflexor muscles during the swing phase of gait in a patient with hemiplegic foot drop",
                "Applying constant direct current to accelerate bone interface healing following fracture fixation",
                "Delivering high-voltage sensory currents to reduce pain in a patient with chronic osteoarthritis",
                "Using high-frequency currents to produce tissue heating prior to muscle stretching"
            ),
            correctAnswer = "Using electrical stimulation to contract dorsiflexor muscles during the swing phase of gait in a patient with hemiplegic foot drop",
            whyCorrect = "FES is the use of electrical stimulation to contract muscles during a functional activity (e.g. gait or grasping). Stimulating the common fibular nerve to dorsiflex the foot during swing phase prevents foot drop.",
            whyIncorrect = "Direct current for bone healing is galvanic bone growth stimulation. Pain reduction describes TENS. Tissue heating describes thermotherapy or shortwave diathermy.",
            clinicalImportance = "FES acts as an active orthosis, assisting gait and promoting neurological motor learning after stroke.",
            examinerTip = "FES must be precisely timed with functional tasks, often using foot-switch sensors or accelerometers.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "FES integrates neuromuscular electrical stimulation directly into functional movements like walking or reaching.",
            relatedConcept = "Functional Electrical Stimulation",
            reference = "O'Sullivan's Physical Rehabilitation, 7th Ed"
        ),

        // === TENS (38-44) ===
        VivaMcq(
            question = "Which of the following best describes the parameter settings and physiological mechanism of Action of Conventional (High-Frequency) TENS?",
            options = listOf(
                "Frequency: 80-120 Hz, Pulse duration: 50-100 microseconds; mechanism: Gate Control Theory of pain modulation",
                "Frequency: 1-5 Hz, Pulse duration: 200-300 microseconds; mechanism: release of beta-endorphins at the hypothalamic level",
                "Frequency: 5000 Hz, Pulse duration: 1 ms; mechanism: direct chemical neuromuscular blockade",
                "Frequency: 100 Hz, Pulse duration: 10 seconds; mechanism: localized thermal tissue coagulation under electrodes"
            ),
            correctAnswer = "Frequency: 80-120 Hz, Pulse duration: 50-100 microseconds; mechanism: Gate Control Theory of pain modulation",
            whyCorrect = "Conventional TENS uses high frequency (80-120 Hz) and short pulse duration (50-100 microseconds) at sensory intensity to activate A-beta fibers. These fibers inhibit nociceptive transmission in the spinal cord dorsal horn (Substantia Gelatinosa).",
            whyIncorrect = "Low frequency and long pulse duration define acupuncture-like TENS. High frequency (5000 Hz) does not describe TENS. Pulse durations of seconds are unphysiological.",
            clinicalImportance = "Provides immediate, comfortable pain relief during activities of daily living without eliciting painful muscle contractions.",
            examinerTip = "Conventional TENS works while the device is turned on, but pain relief often fades quickly after it is shut off.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Conventional TENS uses high-frequency sensory stimulation to block nociceptive signals via the Gate Control Theory.",
            relatedConcept = "Conventional TENS",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which mechanism of pain control is primary during Low-Frequency (Acupuncture-like) TENS, and what are its typical parameters?",
            options = listOf(
                "Acupuncture-like TENS (1-10 Hz, 150-300 microseconds) stimulates endogenous opioid release (endorphins and enkephalins) to achieve lasting analgesia",
                "It uses 120 Hz to paralyze the sensory receptors directly inside the patellar ligament",
                "It works by locally heating the cutaneous tissues to increase peripheral blood flow and clear inflammatory chemicals",
                "It blocks pain by destroying the pain-carrying C-fibers through chemical electrolysis"
            ),
            correctAnswer = "Acupuncture-like TENS (1-10 Hz, 150-300 microseconds) stimulates endogenous opioid release (endorphins and enkephalins) to achieve lasting analgesia",
            whyCorrect = "Low-frequency (1-10 Hz), high-width (150-300 microseconds) TENS is set to motor-level intensity to elicit muscle twitches. This stimulates endogenous opioid peptide release, providing longer-lasting pain relief.",
            whyIncorrect = "Acupuncture TENS does not use 120 Hz, does not rely on thermal effects, and does not damage pain fibers.",
            clinicalImportance = "Provides longer-lasting pain relief (hours after treatment) because systemic endorphins are cleared slowly from circulation.",
            examinerTip = "Because it releases internal opioids, the analgesic effect of low-frequency TENS can be blocked by naloxone (an opioid antagonist).",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Low-frequency TENS stimulates muscle twitches to trigger release of beta-endorphins and enkephalins for prolonged relief.",
            relatedConcept = "Low-Frequency TENS Mechanisms",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Under what clinical scenario is 'Brief Intense TENS' indicated, and how are its stimulation parameters set?",
            options = listOf(
                "To provide fast, temporary anesthesia during painful procedures like suture removal or wound debridement; using high frequency (100-150 Hz) and wide pulse duration (>150 microseconds) at the highest tolerated noxious intensity",
                "To stimulate abdominal muscle hypertrophy in obstetric patients",
                "To reduce swelling over a stable, acute ankle ligament tear using low-voltage thermal continuous frequencies",
                "To destroy fungal infections on the skin using high-voltage galvanic shocks"
            ),
            correctAnswer = "To provide fast, temporary anesthesia during painful procedures like suture removal or wound debridement; using high frequency (100-150 Hz) and wide pulse duration (>150 microseconds) at the highest tolerated noxious intensity",
            whyCorrect = "Brief Intense TENS uses high frequency (100-150 Hz), wide pulse duration (>150 microseconds), and high intensity (noxious level) for short durations (15 mins) to induce rapid conduction block or activate descending pathways.",
            whyIncorrect = "It is not used for abdominal hypertrophy, edema reduction, or direct electrical destruction of fungal infections.",
            clinicalImportance = "Extremely useful in clinical wound care for temporary pain reduction during debridement or joint manipulations.",
            examinerTip = "Brief Intense TENS is designed to induce a transient, rapid analgesic effect via noxious inhibitory pathways.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Brief Intense TENS provides rapid, temporary pain block through noxious sensory stimulation.",
            relatedConcept = "Brief Intense TENS Settings",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "How does the pharmacological mechanism of TENS analgesia differ between low-frequency and high-frequency settings regarding opioid receptor pathways?",
            options = listOf(
                "Low-frequency TENS acts through mu-opioid receptors; high-frequency TENS acts through delta-opioid receptors",
                "Low-frequency TENS binds directly to local adrenaline receptors; high-frequency TENS blocks cortisol pathways",
                "Both frequencies act exclusively on thyroid hormone pathways to alter general body metabolism",
                "There is no receptor involvement; pain relief is purely a mechanical distraction effect"
            ),
            correctAnswer = "Low-frequency TENS acts through mu-opioid receptors; high-frequency TENS acts through delta-opioid receptors",
            whyCorrect = "Research shows low-frequency TENS activates mu-opioid receptors in the central spinal pathway, whereas high-frequency TENS activates delta-opioid receptors. This has clinical implications for drug-tolerant patients.",
            whyIncorrect = "TENS does not target adrenaline receptors, thyroid hormones, or cortisol pathways directly.",
            clinicalImportance = "In patients who are tolerant to narcotics (which act on mu-opioid receptors), low-frequency TENS may be ineffective, while high-frequency TENS remains highly beneficial.",
            examinerTip = "Opioid receptor specificity explains why some chronic pain patients respond selectively to one TENS frequency over another.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Low-frequency and high-frequency TENS activate mu- and delta-opioid receptor pathways respectively.",
            relatedConcept = "Opioid Receptor Specificity in TENS",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "To prevent accommodation (sensory adaptation) during long TENS treatment sessions, what parameter modification should the physical therapist apply?",
            options = listOf(
                "Enable parameter modulation, which automatically sweeps frequency, pulse duration, or amplitude within set limits",
                "Increase the electrode gel concentration ten-fold every 5 minutes",
                "Switch the skin stimulation polarities from copper to silver electrodes",
                "Turn the device off and on manually every 10 seconds throughout treatment"
            ),
            correctAnswer = "Enable parameter modulation, which automatically sweeps frequency, pulse duration, or amplitude within set limits",
            whyCorrect = "Sensory adaptation occurs when nerves adapt to a steady, unchanging signal. Enabling modulation sweeps parameters dynamically, preventing the nerve membrane from accommodating and maintaining pain relief.",
            whyIncorrect = "Changing gel concentration, switching electrode metals, or manual power cycling are impractical and clinically ineffective.",
            clinicalImportance = "Modulation allows patients with chronic low back pain to use TENS for hours without needing constant intensity increases.",
            examinerTip = "Most clinical TENS units have a dedicated 'M' (Modulated) mode on their control panel.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Modulating the frequency or pulse width dynamically prevents sensory accommodation.",
            relatedConcept = "TENS Parameter Modulation",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which electrode configuration is considered 'quadripolar' and is typically used during Interferential Current (IFC) therapy?",
            options = listOf(
                "Using four electrodes placed so that two independent alternating currents cross each other to create an interference pattern within the target tissue",
                "Using two electrodes of unequal size to concentrate current denseness under the smaller pad",
                "Submerging four extremities simultaneously into a central copper-plated bath tub",
                "Placing four stacked electrodes directly on top of each other over a single motor point"
            ),
            correctAnswer = "Using four electrodes placed so that two independent alternating currents cross each other to create an interference pattern within the target tissue",
            whyCorrect = "A quadripolar IFC setup uses four electrodes from two separate circuits placed in a crossed pattern. The two medium-frequency currents intersect inside the tissue to create a low-frequency 'beat' frequency.",
            whyIncorrect = "Two electrodes of unequal size describe a bipolar active-dispersive layout. Submerging extremities is galvanic bath therapy. Stacked electrodes are unphysiological and compromise safety.",
            clinicalImportance = "IFC penetrates deeper with less skin discomfort, making it excellent for deep joint pain like chronic hip or knee osteoarthritis.",
            examinerTip = "Interferential beat frequency is the mathematical difference between the two carrier frequencies (e.g., 4050 Hz and 4000 Hz creates a 50 Hz beat).",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "IFC uses a quadripolar crossed pattern to generate deep, comfortable low-frequency therapeutic currents.",
            relatedConcept = "Interferential Current Principles",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What is a major localized contraindication for placing TENS electrodes on the neck?",
            options = listOf(
                "Placement over the anterior carotid sinus, which could stimulate baroreceptors and cause severe arrhythmias or a vasovagal attack",
                "Placement over the posterior cervical spine, which freezes cerebrospinal fluid flow",
                "Placement near the occiput, which causes rapid hair loss",
                "There is no neck restriction; TENS is completely safe to place across all regions of the windpipe"
            ),
            correctAnswer = "Placement over the anterior carotid sinus, which could stimulate baroreceptors and cause severe arrhythmias or a vasovagal attack",
            whyCorrect = "Placing TENS electrodes over the carotid sinus can stimulate baroreceptors, falsely signaling high blood pressure. This can trigger a reflex drop in heart rate and blood pressure, causing syncope or cardiac arrest.",
            whyIncorrect = "Occipital stimulation is not linked to hair loss, and cervical stimulation does not freeze CSF flow. Placing TENS across the trachea/thyroid is contraindicated as it may cause muscle spasm and compromise respiratory pathways.",
            clinicalImportance = "Avoid the anterior neck entirely during TENS electrode placement regardless of the source of the patient's pain.",
            examinerTip = "Always avoid the anterior neck and throat region to prevent vasovagal reflexes and airway spasms.",
            subject = "Electrotherapy I",
            difficulty = "Easy",
            learningPoint = "Carotid sinus stimulation is an absolute contraindication for TENS due to the risk of life-threatening cardiac reflexes.",
            relatedConcept = "TENS Contraindications",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === MUSCLE PHYSIOLOGY (45-50) ===
        VivaMcq(
            question = "What is the crucial role of calcium ions (Ca2+) during the excitation-contraction coupling cycle of skeletal muscle?",
            options = listOf(
                "Ca2+ is released from the sarcoplasmic reticulum and binds to troponin C, causing a conformational change that moves tropomyosin to expose actin-myosin binding sites",
                "Ca2+ blocks sodium channels to terminate muscle fiber action potentials",
                "Ca2+ acts as the primary external neurotransmitter crossing the synaptic cleft to depolarize the sarcolemma",
                "Ca2+ converts myosin light chains directly into structural collagen fibers"
            ),
            correctAnswer = "Ca2+ is released from the sarcoplasmic reticulum and binds to troponin C, causing a conformational change that moves tropomyosin to expose actin-myosin binding sites",
            whyCorrect = "Action potential propagation open ryanodine receptors, releasing Ca2+ from the sarcoplasmic reticulum. Calcium binds to troponin C, shifting tropomyosin off the active site so myosin cross-bridges can attach to actin.",
            whyIncorrect = "Sodium channel blocking is done by local anesthetics or tetrodotoxin. Acetylcholine is the primary motor neurotransmitter. Calcium does not synthesize collagen.",
            clinicalImportance = "Explains why conditions like severe hypocalcemia or sarcoplasmic dysfunction can cause clinical muscle cramps, weakness, or spasms.",
            examinerTip = "Calcium acts as the key regulator bridge matching mechanical cross-bridge cycles with electrical depolarization.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Sarcoplasmic calcium release and binding to troponin C expose actin-myosin binding sites to initiate contraction.",
            relatedConcept = "Excitation-Contraction Coupling",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "What is the typical resting membrane potential of a mammalian skeletal muscle fiber, and which pump is primarily responsible for maintaining the concentration gradients?",
            options = listOf(
                "-90 mV; maintained by the active sodium-potassium (Na+/K+) ATPase pump",
                "+40 mV; maintained by passive calcium diffusion",
                "0 mV; maintained by complete membrane impermeability",
                "-20 mV; maintained by the active chloride leak channels"
            ),
            correctAnswer = "-90 mV; maintained by the active sodium-potassium (Na+/K+) ATPase pump",
            whyCorrect = "The resting membrane potential (RMP) of a skeletal muscle fiber is approximately -90 mV. It is established by high intracellular potassium, potassium leak channels, and the Na+/K+ ATPase pump.",
            whyIncorrect = "Skeletal fibers never rest at depolarized positive charges (+40 mV) or zero charge. An RMP of -20 mV is typical of dying or severely damaged membranes.",
            clinicalImportance = "This polarization is essential for generating action potentials. Slight shifts (e.g. hyperkalemia) can impair muscle excitability.",
            examinerTip = "The Na+/K+ pump transports three sodium ions out of the cell for every two potassium ions in, maintaining cell electronegativity.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "The resting potential of -90 mV in skeletal muscle is sustained by key ion gradients and Na+/K+ ATPase pump activity.",
            relatedConcept = "Resting Membrane Potentials",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "Which of the following statements matches the physiological 'All-or-None' principle of neuromuscular contraction?",
            options = listOf(
                "A threshold electrical stimulus will elicit a maximal action potential and contraction in a single motor unit; however, whole skeletal muscle is graded because it comprises multiple motor units",
                "An entire skeletal muscle must contract at 100% capacity or not at all, preventing graded physical tasks",
                "Individual muscle fibers can execute half-strength action potentials if stimulated by weak currents",
                "Nerves only fire action potentials if they are stimulated by continuous galvanic direct current for 10 minutes"
            ),
            correctAnswer = "A threshold electrical stimulus will elicit a maximal action potential and contraction in a single motor unit; however, whole skeletal muscle is graded because it comprises multiple motor units",
            whyCorrect = "The all-or-none law applies strictly to individual muscle fibers or single motor units. A threshold stimulus depolarizes the whole motor unit maximally, but a whole muscle recruits multiple units progressively to grade force.",
            whyIncorrect = "Whole skeletal muscles can perform highly graded, subtle contractions. Muscle fibers do not fire partial action potentials. Nerves fire in milliseconds, not minutes.",
            clinicalImportance = "Explains how therapists can gradually increase current intensity to recruit more motor units, creating a smooth, graded electrically induced contraction.",
            examinerTip = "Differentiate between single-unit properties (All-or-None) and multi-unit grading (spatial/temporal recruitment).",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "The 'All-or-None' principle applies to individual motor units, whereas graded contraction refers to the spatial recruitment of multiple units.",
            relatedConcept = "The All-or-None Principle",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "According to the length-tension relationship of skeletal muscular tissue, what happens to contractility when a sarcomere is over-stretched beyond its optimal rest length?",
            options = listOf(
                "Active tension generation drops because thin actin filaments are pulled away from thick myosin filaments, reducing cross-bridge overlap",
                "Tension generation doubles because stretched elastic proteins release chemical ATP energy",
                "Contractility increases indefinitely as sarcomeres grow longer",
                "The sarcoplasmic reticulum immediately breaks down, releasing toxic amounts of calcium"
            ),
            correctAnswer = "Active tension generation drops because thin actin filaments are pulled away from thick myosin filaments, reducing cross-bridge overlap",
            whyCorrect = "Peak active tension is produced at optimal sarcomere length (2.0 to 2.2 micrometers) where actin-myosin overlap is maximal. Overstretching pulls filaments apart, reducing overlap and lowering tension.",
            whyIncorrect = "Overstretching decreases active tension, does not produce free ATP, and does not destroy the sarcoplasmic reticulum under physiological limits.",
            clinicalImportance = "Guides patient positioning: muscles must be placed at optimal middle length (rather than fully shortened or overly stretched) during NMES to maximize contraction force.",
            examinerTip = "Identify 'cross-bridge overlap' as the key variable in sliding filament model calculations.",
            subject = "Electrotherapy I",
            difficulty = "High Yield",
            learningPoint = "Sarcomere length dictates active tension capability: overstretching decreases cross-bridge overlap and reduces contractility.",
            relatedConcept = "Sarcomere Length-Tension Curves",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "How does an eccentric skeletal contraction differ from an isometric or concentric contraction regarding tension generation and ATP consumption under electrical load?",
            options = listOf(
                "Eccentric contraction produces the highest tension with the lowest active ATP oxygen demand per unit of force, but presents the highest risk of muscle microtrauma",
                "Concentric contraction produces the highest tension and burns zero ATP energy",
                "Isometric contraction produces zero force because muscle length remains identical",
                "Eccentric contraction is incapable of generating any physical force because the muscle is lengthening"
            ),
            correctAnswer = "Eccentric contraction produces the highest tension with the lowest active ATP oxygen demand per unit of force, but presents the highest risk of muscle microtrauma",
            whyCorrect = "Eccentric contractions generate high force because actin-myosin cross-bridges are pulled apart mechanically, which requires less ATP per unit of tension than concentric work, but increases microtrauma risk.",
            whyIncorrect = "Concentric contractions are mechanically inefficient and consume high ATP. Isometric contractions generate high force though joints do not translate. Eccentric work generates high passive-active force.",
            clinicalImportance = "Informing eccentric strengthening protocols for tendinopathies while allowing for appropriate rest intervals to minimize delayed onset muscle soreness (DOMS).",
            examinerTip = "Eccentric muscle actions generate higher force than concentric ones but can cause a greater degree of transient microtrauma.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Eccentric actions produce highly efficient mechanical tension with lower ATP demand but high microtrauma potential.",
            relatedConcept = "Types of Muscle Contractions",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        ),
        VivaMcq(
            question = "Which physiological changes occurring inside muscle fibers are primary chemical drivers of localized neuromuscular fatigue during high-intensity electrical stimulation?",
            options = listOf(
                "Accumulation of inorganic phosphate (Pi) and hydrogen ions (H+), causing local acidosis and interference with calcium release",
                "Complete dehydration of all extracellular zinc channels",
                "A rapid shift of cell structure into pure myelinated Schwann cells",
                "An extreme accumulation of myelin around proximal muscle tendons"
            ),
            correctAnswer = "Accumulation of inorganic phosphate (Pi) and hydrogen ions (H+), causing local acidosis and interference with calcium release",
            whyCorrect = "High-intensity exercise or stimulation drives rapid ATP breakdown, accumulating lactic acid and inorganic phosphate. This local acidosis reduces calcium sensitivity of troponin, leading to fatigue.",
            whyIncorrect = "Fatigue is not driven by zinc channel dehydration. Muscle fibers do not transform into Schwann cells or build up myelin sheath around tendons.",
            clinicalImportance = "Alerts physical therapists to monitor fatigue signs (e.g. tremors or drop-off in contraction amplitude) during clinical NMES.",
            examinerTip = "Accumulated inorganic phosphate interferes directly with calcium release from sarcoplasmic channels.",
            subject = "Electrotherapy I",
            difficulty = "Moderate",
            learningPoint = "Neuromuscular fatigue is driven by acidosis and inorganic phosphate accumulation, which impair calcium binding and cross-bridge movement.",
            relatedConcept = "Skeletal Muscle Fatigue Physiology",
            reference = "Guyton and Hall Textbook of Medical Physiology, 14th Ed"
        )
    )
}
