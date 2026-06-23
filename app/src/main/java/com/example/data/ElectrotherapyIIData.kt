package com.example.data

object ElectrotherapyIIData {
    val questions: List<VivaMcq> = listOf(
        // === IFT (1-5) ===
        VivaMcq(
            question = "Which of the following physical principles explains why Interferential Therapy (IFT) is preferred over low-frequency currents for treating deep tissue pathologies?",
            options = listOf(
                "High-frequency carrier waves (e.g., 4000 Hz) encounter much lower capacitive skin impedance, allowing current to penetrate deeply with minimal sensory discomfort",
                "IFT utilizes thermal conduction to selectively melt adipose tissue barriers prior to motor nerve depolarization",
                "Medium-frequency currents have a greater chemical effect, inducing local ionization of deep nerve roots directly",
                "Unidirectional current patterns in IFT block the nerve's sodium channels permanently to act as a local anesthetic"
            ),
            correctAnswer = "High-frequency carrier waves (e.g., 4000 Hz) encounter much lower capacitive skin impedance, allowing current to penetrate deeply with minimal sensory discomfort",
            whyCorrect = "Skin impedance is inversely proportional to the frequency of the current (Xc = 1 / 2πfC). Medium-frequency carrier waves (typically 4000 Hz) bypass the high capacitive impedance of the dermis, and their interference inside deep tissue creates a low-frequency therapeutic 'beat' frequency.",
            whyIncorrect = "IFT is not a thermal modality, does not selectively melt adipose tissue, doesn't generate ionic/polar chemical accumulation (due to its high-frequency alternating nature), and does not permanently block sodium channels.",
            clinicalImportance = "Allows therapists to treat deep-seated joint capsules and muscle layers (e.g., hip osteoarthritis, lumbar spasm) without causing painful superficial electrical sensations.",
            examinerTip = "Remember the skin impedance equation! Higher frequencies mean lower impedance, which translates to a deeper, more comfortable treatment.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Skin impedance decreases as current frequency increases, making medium-frequency carrier currents deeper and more tolerable than low-frequency currents.",
            relatedConcept = "Skin Impedance and Carrier Frequency",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "In a 4-pole IFT configuration, how is the interference pattern distributed in the underlying tissues, and how does this contrast with a 2-pole configuration?",
            options = listOf(
                "4-pole configuration creates a cloverleaf-shaped interference pattern with maximum modulation in deep tissues at 45 degrees, whereas 2-pole creates premodulated currents within the machine",
                "4-pole configuration limits the interference to a tiny, straight clinical line between the diagonal pads, while 2-pole spreads the pattern across the whole abdomen",
                "2-pole configuration produces a pure direct current in the skin, while 4-pole produces continuous high-frequency oscillations that heat tissues",
                "There is no difference in depth of current penetration or current delivery between 2-pole and 4-pole setups"
            ),
            correctAnswer = "4-pole configuration creates a cloverleaf-shaped interference pattern with maximum modulation in deep tissues at 45 degrees, whereas 2-pole creates premodulated currents within the machine",
            whyCorrect = "In a 4-pole setup, two independent currents cross and interfere within the patient's tissues, producing a dynamic cloverleaf amplitude-modulated field. In a 2-pole setup, the interference/modulation is generated electronically inside the machine prior to delivery, acting as a premodulated medium-frequency current.",
            whyIncorrect = "4-pole coordinates cover a wide cloverleaf pattern rather than a single direct line. 2-pole does not produce direct current; the current remains an alternating medium-frequency output.",
            clinicalImportance = "4-pole setups are ideal for large, complex joint compartments (e.g., knee or shoulder joint capsular pain), whereas 2-pole configurations are preferred for linear structures or smaller areas (e.g., wrist tendons).",
            examinerTip = "Examiners often ask where the maximum current modulation occurs in a 4-pole crossing. It occurs diagonally at the 45-degree intersection of the two channels, not directly under the pads.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "True tissue interference is achieved through the spatial crossing of 4-pole electrodes, while 2-pole delivers a premodulated current.",
            relatedConcept = "Electrode Configurations in IFT",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "To induce pelvic floor muscle training and structural re-education under IFT, what choice of beat frequency parameters should a clinician configure?",
            options = listOf(
                "A low beat frequency of 1 to 10 Hz to selectively recruit motor units without causing sensory nerve accommodation",
                "A high beat frequency of 80 to 150 Hz to produce immediate sensory gating of myelinated nociceptors",
                "A continuous unchanging carrier frequency of 4000 Hz, maintaining a locked zero beat frequency",
                "An extremely high-frequency electromagnetic field operating at 2450 MHz"
            ),
            correctAnswer = "A low beat frequency of 1 to 10 Hz to selectively recruit motor units without causing sensory nerve accommodation",
            whyCorrect = "Low beat frequencies (1 to 10 Hz, or up to 25 Hz) are matched to recruit somatic motor fibers, generating strong individual or tetanic contractions suitable for physical pumping and myofibril muscle training.",
            whyIncorrect = "High beat frequencies (80-150 Hz) are for sensory analgesia via the Gate Control Theory, not motor training. Unchanging carrier waves without a beat frequency will not trigger motor contraction. 2450 MHz is the microwave frequency, not IFT.",
            clinicalImportance = "Allows re-education of skeletal muscle complexes, such as the pelvic floor or trunk stabilisers, especially in patients unable to initiate active contractions.",
            examinerTip = "Remember that different beat frequencies target different structures: 1-10 Hz for motor nerves, 80-150 Hz for sensory pain gating.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Low beat frequencies (1-10 Hz) stimulate myelinated motor efferents, promoting rhythmic muscle contraction and training.",
            relatedConcept = "IFT Beat Frequency Selection",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What is the primary physical function of the 'Vector Sweep' (or frequency sweep) feature in clinical IFT devices?",
            options = listOf(
                "To continuously alter the beat frequency within a programmed range to prevent tissue accommodation",
                "To rotate the electrical field by 360 degrees to scan the absolute entire body system continuously",
                "To convert the medium-frequency alternating current into a continuous direct current for iontophoresis",
                "To modulate the mechanical compression pressure of vacuum suction cups"
            ),
            correctAnswer = "To continuously alter the beat frequency within a programmed range to prevent tissue accommodation",
            whyCorrect = "Nerves accommodate to constant, repetitive electrical stimuli, raising their depolarization threshold and reducing the therapeutic effect. A vector sweep automatically and continuously cycles the beat frequency within a range (e.g., 90-130 Hz), preventing accommodation.",
            whyIncorrect = "Vectors focus current sweeps locally; they do not scan the entire systemic body. It does not convert AC to DC, and it does not govern the physical vacuum pressure of suction attachments.",
            clinicalImportance = "Allows long, effective treatment sessions without requiring the clinician to constantly return to the machine to increase the current intensity as the patient adapts.",
            examinerTip = "Familiarise yourself with sweep patterns like triangular (slow ramp), rectangular (abrupt step), and trapezoidal to explain how they challenge nerve adaptation.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Vector sweeping varies the beat frequency over time to prevent neurological accommodation and maintain analgesia.",
            relatedConcept = "Frequency Sweep Dynamics",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which of the following clinical scenarios presents a local contraindication for the application of IFT?",
            options = listOf(
                "Directly over the lower abdomen, pelvis, or lower back of a pregnant patient",
                "Over a chronically stiff knee joint following ACL repair (one year post-op)",
                "To the thoracic region of a patient with a history of mild tension headaches",
                "Over a chronic, non-infected plantar ulcer (distal and non-circulatory)"
            ),
            correctAnswer = "Directly over the lower abdomen, pelvis, or lower back of a pregnant patient",
            whyCorrect = "Applying electrical current over the pelvic, abdominal, or lumbar regions during pregnancy is contraindicated because of the unknown risk of altering uterine smooth muscle tone, placental detachment, or affecting fetal development.",
            whyIncorrect = "Stable post-operative metallic hardware (unless directly in the path of thermal diathermies, though IFT is non-thermal) or tension headaches can be managed safely. Distal ulcers do not preclude IFT on the hip or knee.",
            clinicalImportance = "Conducting a careful pregnancy screen is a fundamental legal and safety standard for physical therapists using any trunk-level electrotherapies.",
            examinerTip = "Safety first! Always emphasize contraindications, especially those concerning pregnancy and active cardiac pace devices.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Trunk-level electrotherapeutic currents are strictly contraindicated in pregnancy to avoid vascular or fetal adverse events.",
            relatedConcept = "IFT Safety and Contraindications",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === SWD (6-10) ===
        VivaMcq(
            question = "What is the standard radiofrequency and corresponding wavelength assigned for Shortwave Diathermy (SWD) in clinical practice?",
            options = listOf(
                "27.12 MHz with a wavelength of approximately 11.06 metres",
                "2450 MHz with a wavelength of approximately 12.24 centimetres",
                "1.0 MHz with a wavelength of approximately 1.5 millimetres (acoustic wave)",
                "4000 Hz with an infinite electromagnetic range"
            ),
            correctAnswer = "27.12 MHz with a wavelength of approximately 11.06 metres",
            whyCorrect = "The Federal Communications Commission (FCC) assigned specific medical and industrial bands to prevent radio interference. For SWD, the most common is 27.12 MHz, which corresponds to an electromagnetic wavelength of 11.06 meters.",
            whyIncorrect = "2450 MHz is the standard frequency for Microwave Diathermy (MWD). 1.0 MHz represents medical ultrasound (an acoustic pressure wave, not an electromagnetic wave). 4000 Hz represents the carrier frequency of IFT.",
            clinicalImportance = "Using the proper frequency ensures deep electromagnetic field propagation without interfering with municipal radio or telecommunication bands.",
            examinerTip = "Make sure you can state both the frequency (27.12 MHz) and wavelength (11.06 m) off by heart, as they are classic board examination questions.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "SWD operates within high-frequency radio bands, typically at 27.12 MHz and 11.06 meters, for deep tissue heating.",
            relatedConcept = "SWD Physical Parameters",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "How does tissue heating differ when using capacitive plates (condenser/electric field) versus inductive coils (drum/magnetic field) in SWD?",
            options = listOf(
                "Capacitive plates primarily heat tissues of high electrical resistance (e.g., superficial fat and skin), whereas inductive coils heat low-resistance, water-rich tissues (e.g., muscle and vascular structures) via eddy currents",
                "Capacitive plates exclusively heat deep cortical bone, while inductive coils only heat subcutaneous dry epidermal scales",
                "Inductive coils operate purely via acoustic vibrations and produce zero electromagnetic field lines",
                "Capacitive plates are completely non-thermal, whereas inductive coils are highly radioactive and dangerous"
            ),
            correctAnswer = "Capacitive plates primarily heat tissues of high electrical resistance (e.g., superficial fat and skin), whereas inductive coils heat low-resistance, water-rich tissues (e.g., muscle and vascular structures) via eddy currents",
            whyCorrect = "Capacitive field diathermy puts the patient's tissues in the electric circuit. Tissues with high resistance (like subcutaneous fat) resist the electric field, absorbing energy and heating up. Inductive method places the tissue in an alternating magnetic field, producing 'eddy currents' in low-resistance, highly conductive tissues (like muscle and blood).",
            whyIncorrect = "Capacitive plates do not bypass fat to heat bone. Inductive coils are electromagnetic, not acoustic, and are thermal (not radioactive).",
            clinicalImportance = "Use capacitive plates for heating superficial joints and tendons. Use inductive drums/coils for heating bulky muscular areas like the quadriceps, calves, or lumbar paraspinals.",
            examinerTip = "Remember the physics: Capacitor = Electric field (fat gets hot). Inductor = Magnetic field / Eddy currents (muscle and blood get hot).",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Capacitive SWD targets high-resistance superficial fat tissues, while inductive SWD targets highly conductive muscle and vascular structures.",
            relatedConcept = "Heating Mechanisms in SWD",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "When applying capacitive plates for SWD, why is it critical that the distance between the electrodes and the patient's skin is kept uniform and adequate?",
            options = listOf(
                "To prevent concentration of electric field lines on superficial tissues, which would cause an localized epidermal burn",
                "To block cosmic rays from entering the patient's muscular systems",
                "To guarantee that there is absolutely no air space for the patient to breathe",
                "To completely stop the electromagnetic field from dispersing into the clinical room atmosphere"
            ),
            correctAnswer = "To prevent concentration of electric field lines on superficial tissues, which would cause an localized epidermal burn",
            whyCorrect = "If a capacitive plate is placed too close to the skin, the electric field lines concentrate heavily in the superficial tissue layer (epidermis and subcutaneous fat), increasing the risk of homeostatic thermal necrosis and burning.",
            whyIncorrect = "It has nothing to do with blocking cosmic rays. Air space is necessary for safety, but must be controlled; it is not for patient breathing. The field will always disperse slightly, which is why onlookers must maintain distance.",
            clinicalImportance = "Maintaining proper spacing (using felt spacers or adjustable arms) ensures and uniform depth of heating and protects the patient's skin.",
            examinerTip = "Closer spacing increases superficial heat density; wider spacing allows deeper, more uniform electric field lines.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Adequate and uniform electrode-to-skin distance prevents superficial electric field concentration and potential tissue burns.",
            relatedConcept = "Electrode Spacing in Diathermy",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Why is it mandatory to place a layer of dry, clean Turkish terry towelling over the treatment area during Shortwave Diathermy application?",
            options = listOf(
                "To absorb skin perspiration, preventing the current from concentrating in water droplets and causing severe scald burns",
                "To mechanically restrain the patient from moving during treatment",
                "To act as a sterile barrier preventing the spread of deep fungal infections",
                "To directly block any electromagnetic fields from escaping the treatment pad"
            ),
            correctAnswer = "To absorb skin perspiration, preventing the current from concentrating in water droplets and causing severe scald burns",
            whyCorrect = "Water is highly conductive. If the patient perspires during SWD, the sweat will collect into droplets on the skin. The high-frequency current will concentrate in these highly conductive droplets, causing localized overheating and severe scald burns.",
            whyIncorrect = "Turkish towels do not restrain patients, do not act as sterile antifungal barriers, and cannot block electromagnetic radiofields.",
            clinicalImportance = "A basic safety rule is to dry the skin and place a clean towel (at least 1-2 cm thick) under the electrodes.",
            examinerTip = "This is a key safety question. If a student forgets the towel during a practical exam, it's an automatic fail.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Terry towelling absorbs sweat during SWD, preventing local current concentration and scald burns.",
            relatedConcept = "SWD Safety Precautions",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Which of the following conditions represents an absolute contraindication for Shortwave Diathermy (SWD), even in its pulsed mode (PSWD)?",
            options = listOf(
                "The presence of an active cardiac pacemaker, implanted neurostimulator, or metal hardware in the field",
                "Chronic joint capsule tightness secondary to a healed post-traumatic contracture",
                "Mild muscular pain from a stable, chronic myofascial back strain",
                "Subacute ankle sprain with residual edema (where localized tissue heating is avoided)"
            ),
            correctAnswer = "The presence of an active cardiac pacemaker, implanted neurostimulator, or metal hardware in the field",
            whyCorrect = "High-frequency electromagnetic fields can induce current in electrical pacemakers, leading to malfunction or complete cardiac arrest. Additionally, metal implants in the field absorb RF energy rapidly, heating up and burning adjacent bone and soft tissue.",
            whyIncorrect = "Healed contractures and chronic back strains are classic, safe indications for SWD. Subacute sprains can be treated with low-dose pulsed SWD (non-thermal parameters).",
            clinicalImportance = "Conducting a comprehensive safety screening for metallic implants and devices is a fundamental step before operating any SWD unit.",
            examinerTip = "Remember that both continuous and pulsed diathermies pose identical risks to active pacemakers and electronic implants.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Electromagnetic fields can disrupt pacemakers and cause severe tissue damage near metal implants.",
            relatedConcept = "Contraindications of RF Diathermy",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === MWD (11-15) ===
        VivaMcq(
            question = "What is the primary operating frequency and corresponding wavelength utilized by Microwave Diathermy (MWD) clinical machines?",
            options = listOf(
                "2450 MHz with a wavelength of 12.24 centimetres",
                "27.12 MHz with a wavelength of 11.06 metres",
                "1.0 MHz with an acoustic wavelength of 1.5 millimetres",
                "500 kHz with a wavelength of 600 metres"
            ),
            correctAnswer = "2450 MHz with a wavelength of 12.24 centimetres",
            whyCorrect = "Microwave Diathermy operates at much higher frequencies than SWD. The most common assigned medical frequency is 2450 MHz, which corresponds to a short wavelength of 12.24 cm.",
            whyIncorrect = "27.12 MHz is for SWD. 1.0 MHz is a typical ultrasound frequency. 500 kHz is too low, entering long-wave diathermies (no longer in standard clinical use).",
            clinicalImportance = "The high frequency and short wavelength allow MWD to be focused and directed using shaped reflectors/directors, unlike SWD fields.",
            examinerTip = "Be careful not to mix up the frequencies of SWD (27.12 MHz) and MWD (2450 MHz).",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "MWD is an electromagnetic therapy operating at a high frequency (2450 MHz) and short wavelength (12.24 cm) for focused superficial heating.",
            relatedConcept = "MWD Physical Characteristics",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "How does the depth of tissue penetration of MWD compare to that of SWD, and why does this difference exist?",
            options = listOf(
                "MWD has shallower penetration than SWD because higher frequency waves are more readily absorbed in superficial tissues, especially those with high water content",
                "MWD penetrates much deeper, easily reaching deep bone marrow, because its high frequency acts as x-ray radiation",
                "MWD does not penetrate skin at all, delivering its thermal energy purely through surface convection",
                "Both have identical depths of penetration because they use the same physical inductive coils"
            ),
            correctAnswer = "MWD has shallower penetration than SWD because higher frequency waves are more readily absorbed in superficial tissues, especially those with high water content",
            whyCorrect = "As electromagnetic frequency increases, the wave depth of penetration decreases. MWD (2450 MHz) is absorbed more rapidly in superficial subcutaneous fat and muscle layers, particularly those high in water, resulting in shallower heating than SWD (27.12 MHz).",
            whyIncorrect = "MWD does not act as ionizing x-ray radiation. It penetrates deeper than simple surface convection, reaching superficial muscles, but is not as deep as SWD. It uses directors, not inductive coils.",
            clinicalImportance = "MWD is ideal for heating localized superficial muscles and tendons (e.g., knee ligaments or biceps tendon) rather than deep pelvic or hip musculature.",
            examinerTip = "Remember that higher frequency = shallower penetration for electromagnetic diathermies.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Because of its high frequency, MWD is absorbed superficially, making it less effective for heating deep joint structures than SWD.",
            relatedConcept = "Penetration Depth of Diathermy Types",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "Why is applying Microwave Diathermy directly over or near the eyes absolutely contraindicated?",
            options = listOf(
                "Because the high frequency can rapidly cause irreversible cataract formation due to the avascular nature of the lens and its poor heat dissipation",
                "Because the optical lens will focus the microwave beam, burning the patient's occipital lobe",
                "Because microwaves alter the rhodopsin pigments, causing permanent fluorescent green vision",
                "Because the electronic director reflector will physically collapse onto the patient's face"
            ),
            correctAnswer = "Because the high frequency can rapidly cause irreversible cataract formation due to the avascular nature of the lens and its poor heat dissipation",
            whyCorrect = "The lens of the eye is avascular and cannot dissipate heat effectively through circulation. Exposing it to MWD causes rapid hyperthermia, which denatures lens crystallin proteins and leads to irreversible cataract formation.",
            whyIncorrect = "The lens does protect and focus light, but it does not direct diathermic RF to the occipital lobe in a way that causes cerebral ablation. It does not cause fluorescent green vision, and modern equipment is structurally stable.",
            clinicalImportance = "Always position the MWD director away from the face or use protective shielding to protect the patient's eyes.",
            examinerTip = "This is a classic examiner question on the specific risks of microwaves on fluid-filled avascular structures.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "The poor blood supply of the eye's lens prevents heat dissipation, making it highly susceptible to microwave-induced cataracts.",
            relatedConcept = "Thermal Risks of Microwaves on Avascular Structures",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "At which interface does a significant proportion of MWD energy get reflected, potentially creating 'standing waves' and localized subcutaneous burns?",
            options = listOf(
                "The fat-muscle interface, due to the high differences in conductivity and water content between these two tissue types",
                "The dermis-air interface, because air has higher salinity than normal skin layers",
                "The bone-tendon interface, because bone is purely metallic in its crystalline matrix",
                "There is zero wave reflection during MWD, as energy is absorbed completely"
            ),
            correctAnswer = "The fat-muscle interface, due to the high differences in conductivity and water content between these two tissue types",
            whyCorrect = "When microwaves traverse subcutaneous fat and strike highly conductive, water-rich muscle tissue, a large amount of the wave is reflected at the fat-muscle interface. The outgoing and reflected waves can interfere to form 'standing waves', concentrating energy and causing localized burns.",
            whyIncorrect = "The main reflection occurs at the fat-muscle boundary inside the tissue, not the dry skin-air boundary. Bone is not metallic. Energy is never fully absorbed without some reflection.",
            clinicalImportance = "Clinicians must closely monitor patient sensation and keep intensity levels within a safe, comfortable range, especially in patients with thicker subcutaneous fat layers.",
            examinerTip = "Understand that standing waves occur when a wave meets a tissue interface with a large difference in dielectric properties, reflecting some energy back.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "The large differences in water content between fat and muscle cause microwave reflections at their interface, risking standing wave burns.",
            relatedConcept = "Standing Waves at Tissue Interfaces",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),
        VivaMcq(
            question = "What safety distance must a physical therapist or bystander maintain from an active, unshielded clinical diathermy unit to verify zero electromagnetic exposure?",
            options = listOf(
                "At least 1.5 to 2.0 metres, as stray electromagnetic fields can irradiate tissues nearby",
                "At least 15 metres, requiring the clinician to exit the building entirely",
                "Only 1 centimetre, as the fields terminate immediately at the pad border",
                "There is no safety distance, as standard diathermies have zero emissions outside the body"
            ),
            correctAnswer = "At least 1.5 to 2.0 metres, as stray electromagnetic fields can irradiate tissues nearby",
            whyCorrect = "Unshielded clinical microwave and shortwave diathermies emit stray radiofrequency electromagnetic fields. Studies show these fields drop to safe levels at distances of 1.5 to 2 meters. Clinicians, especially those who are pregnant, should maintain this distance during treatment.",
            whyIncorrect = "15 meters is unnecessarily large, and exiting the building is impractical. 1 centimeter is far too close, exposing the clinician to significant stray RF. Diathermies do emit stray fields.",
            clinicalImportance = "Setting up a clear safety zone around active diathermy units is crucial to protect clinical staff and other patients in the area.",
            examinerTip = "Clinicians should avoid staying near active diathermy units to minimize cumulative occupational RF exposure.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "A safety zone of 1.5 to 2.0 meters protects clinicians from stray radiofrequency radiation emitted by active diathermies.",
            relatedConcept = "Occupational Safety and Stray RF Fields",
            reference = "Clayton's Electrotherapy, 9th Ed"
        ),

        // === ULTRASOUND (16-20) ===
        VivaMcq(
            question = "Which physical effect is utilized within a therapeutic ultrasound transducer head to convert electrical energy into mechanical acoustic waves?",
            options = listOf(
                "The reverse (or indirect) piezoelectric effect, where applying an alternating electrical current to a crystal causes it to expand and contract",
                "The direct photoelectric effect, where photons are captured to generate a direct current",
                "The thermo-ionic emission effect, where heating a vacuum filament releases free copper electrons",
                "The acoustic impedance effect, which rotates the transducer crystal mechanically"
            ),
            correctAnswer = "The reverse (or indirect) piezoelectric effect, where applying an alternating electrical current to a crystal causes it to expand and contract",
            whyCorrect = "Applying an alternating electrical current across a piezoelectric crystal (such as synthetic lead zirconate titanate) causes it to compress and expand rapidly. This mechanical vibration generates high-frequency acoustic pressure waves (therapeutic ultrasound).",
            whyIncorrect = "The direct photoelectric effect creates electricity from light, not sound. Thermo-ionic emission releases electrons in vacuum tubes. Acoustic impedance is a wave resistance property, not a transducer mechanism.",
            clinicalImportance = "Allows the generation of stable, high-frequency physical vibrations (1 MHz or 3 MHz) for deep tissue healing.",
            examinerTip = "Remember: Direct piezoelectric effect = mechanical stress creates electricity (like a BBQ igniter). Indirect/Reverse effect = electricity creates physical vibration (like therapeutic ultrasound).",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Therapeutic ultrasound relies on the reverse piezoelectric effect to convert high-frequency electrical signals into mechanical sound waves.",
            relatedConcept = "The Piezoelectric Effect",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "When should a physical therapist select a 1 MHz ultrasound frequency over a 3 MHz ultrasound frequency?",
            options = listOf(
                "When treating deep tissues (3 to 5 cm depth) such as the hip joint capsule, because 1 MHz has a lower rate of absorption and less attenuation than 3 MHz",
                "When targeting superficial structures (1 to 2 cm depth) such as hand tendons, because 1 MHz has high superficial absorption",
                "When treating patients who have active metallic joint implants, as 1 MHz is completely safe",
                "When the goal is purely non-thermal cell membrane stimulation with zero tissue heating"
            ),
            correctAnswer = "When treating deep tissues (3 to 5 cm depth) such as the hip joint capsule, because 1 MHz has a lower rate of absorption and less attenuation than 3 MHz",
            whyCorrect = "As ultrasound frequency increases, attenuation (absorption and scattering in tissues) increases. Therefore, 3 MHz is absorbed rapidly in superficial layers (1 to 2 cm), while 1 MHz penetrates deeper (3 to 5 cm) before its energy is fully absorbed.",
            whyIncorrect = "3 MHz is preferred for superficial structures. Metallic implants require caution regardless of frequency. Non-thermal parameters depend on the duty cycle (pulsed vs. continuous), not the frequency.",
            clinicalImportance = "Selecting the right frequency ensures the thermal energy actually reaches the target tissue (e.g., deeper piriformis or superficial patellar tendon).",
            examinerTip = "Always remember the inverse relationship: Higher frequency (3 MHz) = shallower penetration; Lower frequency (1 MHz) = deeper penetration.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Lower frequency ultrasound (1 MHz) is less absorbed superficially, allowing it to penetrate deeper to treat deep joint structures.",
            relatedConcept = "Ultrasound Penetration Depth",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "What do the Beam Non-uniformity Ratio (BNR) and Effective Radiating Area (ERA) represent on a therapeutic ultrasound transducer?",
            options = listOf(
                "BNR represents the ratio of peak intensity to average intensity within the beam (high BNR increases hot-spot risk); ERA is the actual area from which the acoustic energy is emitted",
                "BNR is the chemical ratio of water to coupling gel; ERA is the diameter of the physical hard plastic casing",
                "BNR measures the speed of acoustic rotation; ERA is the depth of tissue heating in centimeters",
                "BNR represents electrical line safety; ERA represents the acoustic decibel rating"
            ),
            correctAnswer = "BNR represents the ratio of peak intensity to average intensity within the beam (high BNR increases hot-spot risk); ERA is the actual area from which the acoustic energy is emitted",
            whyCorrect = "BNR measures the uniformity of the ultrasound output, representing the ratio of the peak intensity to the spatial average intensity. A lower BNR (closer to 1:1, clinically 2:1 to 5:1) indicates a more uniform beam, reducing the risk of 'hot spots' that can burn tissue. ERA is the area of the transducer crystal that physically emits ultrasound energy.",
            whyIncorrect = "BNR does not refer to coupling gel chemistry or electrical line safety. ERA is always smaller than the physical size of the metal treatment head, representing the active crystal area.",
            clinicalImportance = "Using a transducer with a high BNR (e.g., >6:1) requires the clinician to move the transducer head more rapidly to prevent local pain and potential tissue burns.",
            examinerTip = "Ideally, look for machines with a low BNR (between 2:1 and 5:1) for safer and more comfortable treatments.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "A low BNR ensures a uniform, safe beam with minimal risk of hot spots, and the ERA defines the actual size of the emitting acoustic beam.",
            relatedConcept = "BNR and ERA in Ultrasound",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "What is the difference between stable and unstable cavitation in therapeutic ultrasound, and how can the clinician prevent the latter?",
            options = listOf(
                "Stable cavitation involves controlled microbubble expansion and contraction that stimulates cell metabolism, while unstable cavitation causes violent bubble implosion and tissue damage; prevented by constantly moving the transducer head",
                "Stable cavitation refers to continuous joint alignment, while unstable cavitation destroys bone marrow; prevented by using high-salinity coupling gels",
                "Unstable cavitation is required for fracture healing, while stable cavitation is contraindicated; prevented by holding the transducer completely still",
                "There is no difference; all cavitation is highly dangerous and destroys living tissue immediately"
            ),
            correctAnswer = "Stable cavitation involves controlled microbubble expansion and contraction that stimulates cell metabolism, while unstable cavitation causes violent bubble implosion and tissue damage; prevented by constantly moving the transducer head",
            whyCorrect = "Stable cavitation is therapeutic, causing gentle oscillation of gas bubbles in tissues, which alters cell membrane permeability and promotes healing. Unstable cavitation occurs when bubbles expand and implode violently. This can damage cells and tissue, and is prevented by moving the transducer head constantly and using safe intensities.",
            whyIncorrect = "Cavitation is an acoustic bubble phenomenon, not a joint alignment or bone marrow process. Unstable cavitation should be avoided. Holding the transducer still actually increases the risk of unstable cavitation.",
            clinicalImportance = "Constantly moving the transducer head at 2-4 cm/second maintains safe, uniform heating and prevents unstable cavitation or bone burns.",
            examinerTip = "Examiners frequently watch your hand technique during practical tests. Holding the head still for too long can result in a fail.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Stable cavitation promotes cell recovery, whereas unstable cavitation is destructive and avoided by moving the transducer constantly.",
            relatedConcept = "Acoustic Cavitation Mechanics",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "What is 'Phonophoresis' and what is the primary physical mechanism behind it?",
            options = listOf(
                "The use of therapeutic ultrasound to drive intact medication molecules (e.g., NSAIDs or corticosteroids) transdermally into tissues by increasing skin permeability",
                "The transmission of sound frequencies through telephone networks to diagnose muscle spasms",
                "A diagnostic method that records joint popping sounds to measure articular wear",
                "Using high-frequency microcurrents to vaporize dry chemical compounds on the skin"
            ),
            correctAnswer = "The use of therapeutic ultrasound to drive intact medication molecules (e.g., NSAIDs or corticosteroids) transdermally into tissues by increasing skin permeability",
            whyCorrect = "Phonophoresis uses acoustic energy to drive topical medications (like hydrocortisone or ketoprofen) through the skin. The acoustic waves increase the permeability of the stratum corneum, allowing drug molecules to pass into the deeper tissues.",
            whyIncorrect = "It is not related to phone diagnostics, joint wear sounds, or vaporizing chemicals with microcurrents.",
            clinicalImportance = "Allows localized delivery of anti-inflammatories or analgesics directly to irritated tissues (e.g., Achilles tendonitis) without the systemic side effects or discomfort of injections.",
            examinerTip = "The drug molecules are driven through the skin as intact chemical units, not separated into ions (which is how iontophoresis works).",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Phonophoresis uses acoustic vibrations to physically open pathways in the stratum corneum for transdermal drug delivery.",
            relatedConcept = "Phonophoresis Mechanisms",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),

        // === LASER (21-25) ===
        VivaMcq(
            question = "Which three distinct physical properties distinguish Low-Level Laser Therapy (LLLT) light from standard ambient light?",
            options = listOf(
                "Monochromaticity (single wavelength), Coherence (synchronized light waves), and Collimation (parallel beam with minimal divergence)",
                "High thermogenesis (intense local heating), high radioactivity, and continuous high-voltage ionization",
                "Broad-spectrum white glow, non-focused scatter pattern, and random atomic oscillations",
                "Pure acoustic frequency, sound impedance, and physical tissue cavitation"
            ),
            correctAnswer = "Monochromaticity (single wavelength), Coherence (synchronized light waves), and Collimation (parallel beam with minimal divergence)",
            whyCorrect = "LLLT (often called cold laser) is characterized by: (1) Monochromaticity: light of a single precise wavelength. (2) Coherence: light waves are in-phase with each other in space and time. (3) Collimation: focus of parallel beams that do not scatter or diverge quickly.",
            whyIncorrect = "LLLT is non-thermal (does not rely on heating). Ambient white light has a broad, scattered spectrum. Sound impedance and cavitation are acoustic properties (ultrasound), not optical.",
            clinicalImportance = "These unique properties allow laser light to target specific chromophores in deep tissues to stimulate cellular healing.",
            examinerTip = "Prepare to define and explain all three terms (monochromaticity, coherence, and collimation) in any laser viva station.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "LLLT is defined by its monochromatic, coherent, and highly collimated light beam, which optimizes deep photobiomodulation.",
            relatedConcept = "Physical Properties of Laser Light",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "Under Low-Level Laser Therapy (LLLT), what is the primary cellular mechanism that drives 'Photobiomodulation' and accelerates tissue healing?",
            options = listOf(
                "Absorption of photons by Cytochrome C Oxidase in the mitochondrial respiratory chain, which increases ATP synthesis and cellular metabolism",
                "Immediate ablation of unhealthy cells by intense thermal energy",
                "Direct alteration of the DNA sequence in the nucleus of skin cells",
                "Physical vibration of intracellular water molecules to generate heat"
            ),
            correctAnswer = "Absorption of photons by Cytochrome C Oxidase in the mitochondrial respiratory chain, which increases ATP synthesis and cellular metabolism",
            whyCorrect = "The red or near-infrared photons emitted by therapeutic lasers are absorbed by Cytochrome C Oxidase, a key enzyme in the mitochondrial electron transport chain. This stimulates the production of Adenosine Triphosphate (ATP), nitric oxide, and reactive oxygen species, accelerating cell repair and proliferation.",
            whyIncorrect = "LLLT is a non-thermal cold laser; it does not destroy or ablate cells thermally, does not alter nuclear DNA sequences, and do not heat water molecules (which is what diathermies or microwaves do).",
            clinicalImportance = "Accelerates recovery in chronic ulcers, diabetic wounds, open incisions, and acute ligament sprains by boosting cellular energy.",
            examinerTip = "Cytochrome C Oxidase is the primary chromophore (light-receiving molecule) involved in LLLT. Remember this for your exams.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Photoreceptors in cell mitochondria absorb laser light, boosting ATP production and accelerating cellular repair.",
            relatedConcept = "Intracellular Photobiomodulation",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "How do Helium-Neon (He-Ne) lasers and Gallium-Arsenide (GaAs) lasers compare in terms of wavelength and depth of tissue penetration?",
            options = listOf(
                "He-Ne (632.8 nm) is red visible light with a shallow penetration of 0.5-1.0 cm, whereas GaAs (904 nm) is infrared invisible light with a deeper penetration of 2-5 cm",
                "GaAs is visible blue light with 10 cm penetration, while He-Ne is high-power ultraviolet light with 1 mm penetration",
                "Both have identical wavelengths of 4000 nm and penetrate deep into bone structures to warm joint replacements",
                "He-Ne has deeper penetration than GaAs because its red visible beam is highly reflective"
            ),
            correctAnswer = "He-Ne (632.8 nm) is red visible light with a shallow penetration of 0.5-1.0 cm, whereas GaAs (904 nm) is infrared invisible light with a deeper penetration of 2-5 cm",
            whyCorrect = "He-Ne lasers use visible red light (632.8 nm) which is absorbed quickly in the superficial tissues, providing therapy at a depth of about 0.5 to 1.0 cm (ideal for superficial wounds). GaAs lasers use near-infrared light (904 nm) which penetrates deeper (2 to 5 cm), making it suitable for treating deeper ligaments, joints, and trigger points.",
            whyIncorrect = "GaAs is near-infrared (invisible), not blue. Neither utilizes ultraviolet light or 4000 nm in therapies. Red light is absorbed rather than reflected down deeply.",
            clinicalImportance = "Use He-Ne lasers for cutaneous ulcers, shingles lesions, or surgical scars. Use GaAs or GaAlAs lasers for deeper conditions like tennis elbow, Achilles tendinopathy, or knee ligament strains.",
            examinerTip = "Remember: longer wavelengths (near-infrared, ~904 nm) penetrate deeper than shorter visible wavelengths (red, ~632.8 nm).",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Superficial skin wounds benefit from shorter wavelength visible red lasers (He-Ne), whereas deeper tendon and ligament issues require longer wavelength infrared lasers (GaAs).",
            relatedConcept = "Laser Wavelengths and Depth of Penetration",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "Which class of lasers represents typical Low-Level Laser Therapy (LLLT) devices used globally, and what are their power limitation parameters?",
            options = listOf(
                "Class 3B lasers; with a power output between 5 mW and 500 mW",
                "Class 1 lasers; with a power output below 1 microwatt",
                "Class 4 lasers; with a power output exceeding 50,000 Watts",
                "Class 2 lasers; with a power output strictly utilizing alternating high voltages"
            ),
            correctAnswer = "Class 3B lasers; with a power output between 5 mW and 500 mW",
            whyCorrect = "Class 3B lasers are the industry standard for LLLT / cold laser therapy, with a power output range of 5 mW to 500 mW. They are safe for skin contact (non-thermal) but present a direct eye hazard.",
            whyIncorrect = "Class 1 lasers are low-power devices like barcode scanners (extremely low wattage). Class 4 lasers exceed 500 mW and include high-power surgical cutting lasers which can burn tissue. Class 2 lasers are low-power visible lasers like laser pointers.",
            clinicalImportance = "Knowing the class helps therapists implement proper eye safety precautions. Class 3B lasers require protective eyewear for both patient and therapist.",
            examinerTip = "Class 3B lasers are therapeutic (cold). Class 4 lasers are warm/thermal and can cut tissue. Keep this distinction in mind.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Clinical LLLT uses Class 3B lasers with a power output limit of 5-500 mW to ensure non-thermal biomodulation.",
            relatedConcept = "Medical Laser Classification",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),
        VivaMcq(
            question = "What is the most critical safety precaution a clinician must enforce during any therapeutic laser session?",
            options = listOf(
                "Ensuring both the patient and clinician wear wavelength-specific protective safety goggles to prevent retinal damage",
                "Submerging the laser handpiece in cold sterile water to keep it from overheating",
                "Covering the patient's entire head in heavy lead-lined aprons to block gamma rays",
                "Applying a thick, 5-inch layer of ultrasound contact gel to avoid skin irritation"
            ),
            correctAnswer = "Ensuring both the patient and clinician wear wavelength-specific protective safety goggles to prevent retinal damage",
            whyCorrect = "The coherent, collimated light from Class 3B and Class 4 lasers can be focused by the eye's lens onto the retina, causing permanent blind spots. Both the clinician and patient must wear protective eyewear designed for the laser's specific wavelength.",
            whyIncorrect = "LLLT lasers are non-thermal and do not require cooling water. LLLT does not emit gamma radiation, and does not use ultrasound coupling gel.",
            clinicalImportance = "Failing to use protective laser eyewear is a serious safety violation that can result in immediate eye injury.",
            examinerTip = "Safety first! Always emphasize that protective goggles must match the exact wavelength of the laser being used.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Retinal hazards require the mandatory use of wavelength-appropriate safety goggles during all laser treatments.",
            relatedConcept = "Laser Eye Safety Protocols",
            reference = "Low & Reed's Electrotherapy Explained, 4th Ed"
        ),

        // === TRACTION (26-30) ===
        VivaMcq(
            question = "Which cervical traction angle of pull is optimal for targeting the lower cervical segments (C5 to C7) versus the upper cervical segments?",
            options = listOf(
                "Lower cervical: 20 to 25 degrees of flexion; Upper cervical: 0 degrees (neutral alignment)",
                "Lower cervical: 45 degrees of extension; Upper cervical: 90 degrees of flexion",
                "Lower cervical: 0 degrees of neutral alignment; Upper cervical: 45 degrees of flexion",
                "Both levels require identical traction angles of 90 degrees of lateral side-bending"
            ),
            correctAnswer = "Lower cervical: 20 to 25 degrees of flexion; Upper cervical: 0 degrees (neutral alignment)",
            whyCorrect = "To distract and open the intervertebral foramina in the lower cervical spine (C5-C7), the neck should be positioned in 20 to 25 degrees of flexion. Upper cervical structures are distracted best with the neck in a neutral (0 degrees) position.",
            whyIncorrect = "Hyperextension (45 degrees) narrows the posterior disc space and can compress joints. Extreme flexion (90 degrees) is biomechanically unsafe, and side-bending is not used for standard traction.",
            clinicalImportance = "Allows the therapist to target specific segments (e.g., C6-C7 nerve root compression) by adjusting the traction angle.",
            examinerTip = "Adjusting the angle of pull is key to targeting the right cervical spine segment during treatment.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Lower cervical segments are targeted best with 20-25 degrees of flexion, while upper cervical segments require a neutral position.",
            relatedConcept = "Cervical Traction Angles",
            reference = "Principles of Neuromuscular Traction"
        ),
        VivaMcq(
            question = "To achieve therapeutic mechanical separation of the lumbar vertebral bodies, what minimum tractive force is required in clinical practice?",
            options = listOf(
                "A force corresponding to at least 25% up to 50% of the patient's total body weight",
                "A constant force of exactly 5 pounds regardless of patient size",
                "A force equal to 120% of the patient's body weight, applied as a fast single pull",
                "Traction force is completely irrelevant, as long as the machine is turned on"
            ),
            correctAnswer = "A force corresponding to at least 25% up to 50% of the patient's total body weight",
            whyCorrect = "To cause actual physical separation of the lumbar vertebrae, the traction force must overcome friction and soft tissue resistance. This generally requires a force equal to 25% (to stretch soft tissue) to 50% (for true joint distraction) of the patient's body weight.",
            whyIncorrect = "5 lbs is far too low to affect the lumbar spine. 120% of body weight is excessive and can cause severe muscle tearing and injury. The level of force is critical for a safe, effective treatment.",
            clinicalImportance = "Starting at a lower force (e.g., 25% or less) during the first session allows the therapist to assess the patient's tolerance before progressing to joint distraction levels (~50%).",
            examinerTip = "Remember the 50% guideline for lumbar joint distraction, and always account for table friction.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Lumbar vertebral distraction requires a force of 25% to 50% of body weight to overcome friction and joint resistance.",
            relatedConcept = "Lumbar Traction Force Guidelines",
            reference = "Principles of Neuromuscular Traction"
        ),
        VivaMcq(
            question = "What is the recommended range of initial tractive force when applying cervical traction to avoid muscular shielding and spasm?",
            options = listOf(
                "10 to 15 pounds (approximately 7% to 10% of total body weight)",
                "50 to 80 pounds, to ensure immediate bone alignment",
                "Over 100 pounds, requiring mechanical wall winches",
                "0.1 pounds, to maintain a purely mental sensation"
            ),
            correctAnswer = "10 to 15 pounds (approximately 7% to 10% of total body weight)",
            whyCorrect = "The neck musculature is sensitive to sudden, high-force stretches. Starting with a gentle force of 10 to 15 lbs prevents protective muscle guarding and spasms, while still providing a therapeutic pull. This can be progressed up to 25-30 lbs as tolerated.",
            whyIncorrect = "50 to 80 lbs is excessive for the neck, risking joint instability or soft tissue tearing. 100 lbs can cause fatal spinal subluxations. 0.1 lbs has no clinical effect.",
            clinicalImportance = "Starting with low, safe forces in the cervical spine prevents reflexive muscle spasms, protecting the patient from increased pain.",
            examinerTip = "Always start low (10-15 lbs) on the first session to build patient trust and monitor their response.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "An initial cervical traction force of 10-15 lbs avoids muscle guarding and allows the neck tissues to adapt safely.",
            relatedConcept = "Cervical Traction Force Selection",
            reference = "Principles of Neuromuscular Traction"
        ),
        VivaMcq(
            question = "In which clinical scenario is static (continuous) traction preferred over intermittent mechanical traction?",
            options = listOf(
                "Acute lumbar disc herniation, where a steady, prolonged pull helps reduce intradiscal pressure and retract the nucleus pulposus",
                "Chronic facet joint hypomobility, to mobilize the stiff spinal segments",
                "Late-stage rehabilitation of mild muscular fatigue following exercise",
                "Joint contracture of the hands and ankles"
            ),
            correctAnswer = "Acute lumbar disc herniation, where a steady, prolonged pull helps reduce intradiscal pressure and retract the nucleus pulposus",
            whyCorrect = "Static traction is preferred for acute disc herniations because a steady pull stretches the surrounding ligaments and decreases disc pressure, creating a vacuum effect that helps draw the herniated nucleus pulposus back toward the center of the disc. Intermittent traction is better for mobilizing stiff joints.",
            whyIncorrect = "Facet hypomobility benefits more from the repetitive movement of intermittent traction. Traction does not treat generalized muscle fatigue or hand/ankle contractures.",
            clinicalImportance = "Static traction minimizes movement, reducing irritation of inflamed nerve roots in patients with acute disc herniations.",
            examinerTip = "Static traction is best for acute disc herniation. Intermittent traction is best for chronic joint stiffness and muscle spasms.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Static traction is used for acute disc herniations to help retract the disc, while intermittent traction is used for joint mobilization.",
            relatedConcept = "Traction Modes: Static vs Intermittent",
            reference = "Principles of Neuromuscular Traction"
        ),
        VivaMcq(
            question = "Why is the presence of Rheumatoid Arthritis (RA) or Down Syndrome considered an absolute contraindication for cervical traction therapy?",
            options = listOf(
                "Both conditions are associated with atlantoaxial ligament laxity and instability, which can lead to cervical subluxation and spinal cord injury under traction",
                "Both conditions increase localized skin impedance, causing electric burns from the traction harness",
                "Patients with these conditions possess high cognitive susceptibility to mechanical sounds",
                "There is no clinical risk, and traction is highly recommended for both conditions"
            ),
            correctAnswer = "Both conditions are associated with atlantoaxial ligament laxity and instability, which can lead to cervical subluxation and spinal cord injury under traction",
            whyCorrect = "Rheumatoid arthritis and Down syndrome can cause laxity or instability of the transverse ligament of the atlas (C1-C2). Applying traction to this area can cause atlantoaxial subluxation, compressing the spinal cord and risking severe neurological injury or death.",
            whyIncorrect = "Traction is non-electrical and does not pose burn risks. Retinal or cognitive susceptibility is not the primary reason for this caution. Recommending traction in these cases is extremely dangerous.",
            clinicalImportance = "A thorough medical history screening for systemic joint disorders is essential before administering cervical traction.",
            examinerTip = "This is a critical safety question. Always rule out ligament instability (as in RA or Down syndrome) before starting cervical traction.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "RA and Down syndrome cause atlantoaxial laxity, making cervical traction highly dangerous due to the risk of spinal cord compression.",
            relatedConcept = "Cervical Traction Contraindications",
            reference = "Principles of Neuromuscular Traction"
        ),

        // === EMG (31-35) ===
        VivaMcq(
            question = "What is the primary difference in clinical application and recording capabilities between Surface EMG (sEMG) and Needle EMG?",
            options = listOf(
                "sEMG records aggregate electrical activity from multiple motor units superficially, while needle EMG inserts into muscles to record individual Motor Unit Action Potentials (MUAPs)",
                "sEMG measures the physical velocity of blood flow, while needle EMG injects small chemical enzymes into the muscle tissue",
                "Needle EMG can only record activity from dead nerve tissues, whereas sEMG is used exclusively to stimulate nerve growth",
                "There is no recording difference; sEMG is simply more painful than needle EMG"
            ),
            correctAnswer = "sEMG records aggregate electrical activity from multiple motor units superficially, while needle EMG inserts into muscles to record individual Motor Unit Action Potentials (MUAPs)",
            whyCorrect = "Surface EMG uses electrodes on the skin to record the sum of all electrical activity from a group of muscles, making it useful for biofeedback. Needle EMG inserts a sterile needle electrode directly into the muscle tissue, allowing clinicians to record and analyze the individual Motor Unit Action Potentials (MUAPs) for diagnostic purposes.",
            whyIncorrect = "EMG does not measure blood flow or inject chemicals. Needle studies are performed on living, functional muscle tissue, and sEMG is non-painful compared to needle insertions.",
            clinicalImportance = "Use needle EMG to diagnose neuromuscular disorders (like myopathy or radiculopathy) and sEMG for muscle relaxation and re-education biofeedback.",
            examinerTip = "sEMG is for macro-movement and feedback; Needle EMG is for micro-diagnosis of specific motor units.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Needle EMG allows diagnostic assessment of individual motor units, while sEMG records broad muscle group activity for biofeedback.",
            relatedConcept = "sEMG vs Needle Electromyography",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "When performing a needle EMG, what does abnormally prolonged 'insertional activity' indicate upon insertion of the electrode into a muscle?",
            options = listOf(
                "An unstable, hyperirritable muscle membrane, which is commonly seen in denervation or active radiculopathies",
                "A perfectly healthy, relaxed, and fully functional neuromuscular junction",
                "Complete fibrosis of the muscle, where muscle fibers have been fully replaced by fat",
                "A highly advanced state of athletic training and muscle hypertrophy"
            ),
            correctAnswer = "An unstable, hyperirritable muscle membrane, which is commonly seen in denervation or active radiculopathies",
            whyCorrect = "Insertional activity is the burst of electrical noise that occurs when the needle electrode is inserted, breaking muscle membranes. Normal activity lasts less than 300 ms. Prolonged activity (>500 ms) indicates a hyperirritable, unstable membrane, often seen in denervation, neuropathies, or myopathies.",
            whyIncorrect = "A healthy, relaxed muscle shows normal insertional activity (brief) followed by electrical silence. Fibrosed muscles have reduced or absent insertional activity. Muscle hypertrophy does not cause prolonged insertional activity at rest.",
            clinicalImportance = "Helps clinicians identify sub-clinical nerve irritation or early denervation before muscle wasting becomes visible.",
            examinerTip = "Look for insertional activity duration on the EMG screen immediately upon moving or inserting the needle.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Prolonged insertional activity suggests membrane hyperirritability, an early sign of peripheral nerve damage.",
            relatedConcept = "EMG Insertional Activity",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "Which of the following spontaneous electrical waveforms, observed in a muscle at complete rest, are indicating active denervation?",
            options = listOf(
                "Fibrillation potentials and positive sharp waves",
                "A uniform, flat, and silent electrical baseline",
                "Symmetric sinusoidal waves operating at 4000 Hz",
                "A full, rich voluntary interference pattern"
            ),
            correctAnswer = "Fibrillation potentials and positive sharp waves",
            whyCorrect = "Normally, a resting muscle is electrically silent. If a muscle is denervated, its membranes become unstable, producing spontaneous, involuntary action potentials. These appear on an EMG as fibrillation potentials and positive sharp waves about 2 to 3 weeks after the injury.",
            whyIncorrect = "Normal muscle is silent at rest (flat line). 4000 Hz waves are external electrical interference, not muscle activity. An interference pattern only occurs during voluntary muscle contraction.",
            clinicalImportance = "Confirming the presence of fibrillations or positive sharp waves helps the clinician diagnose active, ongoing denervation.",
            examinerTip = "Finding fibrillations at rest is a key electrodiagnostic sign of active denervation.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Fibrillations and positive sharp waves at rest indicate active denervation and muscle membrane instability.",
            relatedConcept = "Spontaneous EMG Activity",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "How does the active voluntary EMG recruitment pattern of a neuropathic lesion differ from that of a myopathic lesion during maximal effort?",
            options = listOf(
                "Neuropathic lesions show reduced recruitment with fewer, rapidly firing motor units (incomplete interference), while myopathic lesions show early recruitment of small, low-voltage units (full interference pattern at low forces)",
                "Neuropathic lesions show immediate flat-line silence during voluntary effort, while myopathies show giant high-voltage bursts",
                "Both display completely normal recruitment patterns because EMG cannot analyze recruitment mechanics",
                "Neuropathic lesions produce constant high-frequency acoustic feedback with zero visual display"
            ),
            correctAnswer = "Neuropathic lesions show reduced recruitment with fewer, rapidly firing motor units (incomplete interference), while myopathic lesions show early recruitment of small, low-voltage units (full interference pattern at low forces)",
            whyCorrect = "In neuropathic lesions, axon loss reduces the number of available motor units. To compensate, the remaining units fire rapidly, resulting in an incomplete interference pattern. In myopathic lesions, the number of motor units is normal, but each unit has fewer functional muscle fibers, producing small, low-voltage, polyphasic units that must recruit quickly, showing a full pattern even at low efforts.",
            whyIncorrect = "Neuropathic lesions do not produce a flat line if some axons survive. Myopathic units are typically low-voltage and brief, not giant bursts. Recruitment is easily analyzed by EMG.",
            clinicalImportance = "Allows clinicians to distinguish between nerve injuries (like radiculopathy or neuropathy) and muscle diseases (like muscular dystrophy or myositis).",
            examinerTip = "Neuropathic = reduced recruitment, rapidly firing units. Myopathic = early, rapid recruitment of low-amplitude units.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Analyzing recruitment patterns helps clinicians distinguish neuropathic conditions (loss of motor units) from myopathic conditions (loss of muscle fibers).",
            relatedConcept = "EMG Interference Patterns",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "What morphological changes characterize a Motor Unit Action Potential (MUAP) associated with neuropathic reinnervation on diagnostic EMG?",
            options = listOf(
                "Giant MUAPs characterized by high amplitude, prolonged duration, and increased complexity (highly polyphasic)",
                "Extremely short, low-voltage, simple biphasic potentials",
                "Symmetric square waves with zero electrical deflection",
                "Fibrillations that disappear immediately upon warm water immersion"
            ),
            correctAnswer = "Giant MUAPs characterized by high amplitude, prolonged duration, and increased complexity (highly polyphasic)",
            whyCorrect = "During recovery from a neuropathic injury, surviving motor axons sprout collateral branches to re-innervate orphaned muscle fibers. This increases the number of muscle fibers per motor unit, producing 'giant MUAPs' on an EMG (high amplitude, long duration, and polyphasic).",
            whyIncorrect = "Myopathies produce short, low-voltage MUAPs, not neuropathic reinnervation. Square waves are artificial current stimulations. Fibrillations are not altered by simple water immersion.",
            clinicalImportance = "The appearance of giant, polyphasic MUAPs is an encouraging sign of motor nerve reinnervation and recovery.",
            examinerTip = "Remember that giant MUAPs indicate chronic neuropathic reinnervation, as surviving axons take over more muscle fibers.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Neuropathic reinnervation produces large, complex (giant, polyphasic) MUAPs due to collateral axonal sprouting.",
            relatedConcept = "MUAP Morphology in Recovery",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),

        // === NCV (36-40) ===
        VivaMcq(
            question = "Which of the following formulas is used to calculate the Motor Nerve Conduction Velocity (NCV) in clinical practice, and why are two stimulation sites required?",
            options = listOf(
                "Velocity = Distance / (Proximal Latency - Distal Latency); two sites are needed to subtract the neuromuscular junction synaptic delay",
                "Velocity = Distance * Time of stimulation; two sites are needed to double the electrical voltage safely",
                "Velocity = Distal Latency - Proximal Latency; two sites are needed to stimulate contralateral limbs simultaneously",
                "Conduction velocity is measured directly from the ground electrode without calculations"
            ),
            correctAnswer = "Velocity = Distance / (Proximal Latency - Distal Latency); two sites are needed to subtract the neuromuscular junction synaptic delay",
            whyCorrect = "To calculate motor NCV, we divide the distance between Proximal and Distal stimulation points by the difference in their latencies. Two stimulation sites are necessary because the distal latency includes both nerve conduction time and the synaptic delay at the neuromuscular junction. Subtracting the latencies cancels out this synaptic delay.",
            whyIncorrect = "Velocity cannot be calculated by multiplying distance by time. CONTRA lateral stimulations are not used for single nerve values, and latency subtraction, not addition or raw latency, is required.",
            clinicalImportance = "Allows accurate measurement of nerve conduction velocity (e.g., in m/s) to diagnose nerve compression or demyelination.",
            examinerTip = "This formula is a key concept in electrodiagnosis. Be sure you can explain why two stimulation points are used to eliminate synaptic delay.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Motor NCV is calculated using two stimulation points to eliminate the synaptic delay at the neuromuscular junction, isolating nerve conduction velocity.",
            relatedConcept = "Motor Nerve Conduction Velocity Formula",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "How does a Sensory Nerve Action Potential (SNAP) differ from a Compound Muscle Action Potential (CMAP) in clinical NCV testing?",
            options = listOf(
                "SNAPs are measured in microvolts (μV) and contain no synaptic delays, whereas CMAPs are measured in millivolts (mV) and represent muscle contraction summation",
                "SNAPs represent deep tendon reflexes, while CMAPs represent arterial pulse rates",
                "SNAPs can only be recorded from the brain cortex, while CMAPs are recorded from the skin surface",
                "SNAPs require continuous direct current stimulation, while CMAPs require ultra-high-frequency laser beams"
            ),
            correctAnswer = "SNAPs are measured in microvolts (μV) and contain no synaptic delays, whereas CMAPs are measured in millivolts (mV) and represent muscle contraction summation",
            whyCorrect = "A SNAP represents the depolarization of sensory axons and is small, measured in microvolts (μV), with no synaptic delays. A CMAP represents the sum of action potentials from the stimulated muscle fibers and is much larger, measured in millivolts (mV).",
            whyIncorrect = "Therapeutic reflex, arterial pulse, and cerebral cortex recordings are not part of standard NCV SNAP/CMAP testing.",
            clinicalImportance = "Distinguishing sensory from motor nerve injury helps localize lesions (e.g., pre-ganglionic vs. post-ganglionic brachial plexus lesional patterns).",
            examinerTip = "Be aware of the scale difference: SNAPs are recorded in microvolts (μV); CMAPs are recorded in millivolts (mV).",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "SNAPs are small sensory recordings (μV) without synaptic delays, while CMAPs are larger muscle sum recordings (mV).",
            relatedConcept = "SNAP vs CMAP Characteristics",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "What is the standard effect of low skin/tissue temperature on calculated nerve conduction velocity parameters?",
            options = listOf(
                "Low temperature slows conduction velocity, decreasing it by approximately 1.5 to 2.5 metres/second for every 1°C drop below normal skin temperature",
                "Low temperature increases conduction velocity dramatically, causing hyper-processing of nerve impulses",
                "Temperature has zero effect on nerve conduction biology or velocity metrics",
                "Low temperature converts the nerve action potential into a continuous alternating wave shape"
            ),
            correctAnswer = "Low temperature slows conduction velocity, decreasing it by approximately 1.5 to 2.5 metres/second for every 1°C drop below normal skin temperature",
            whyCorrect = "Cold skin and tissue temperature slows sodium channel opening and closing, which prolongs latency and slows nerve conduction velocity. For every 1°C drop below normal skin temperature (typically 32°C to 34°C), conduction velocity decreases by 1.5 to 2.5 m/s.",
            whyIncorrect = "Cold temperatures slow down (do not speed up) conduction velocity. The temperature effect is significant and must be accounted for clinically.",
            clinicalImportance = "Always check and warm the patient's limb prior to performing an NCV test to prevent a false diagnosis of neuropathy or entrapment.",
            examinerTip = "Warming cold limbs to at least 32°C is a standard safety and quality requirement before diagnostic NCV testing.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Cooler skin temperatures slow nerve conduction velocity, which can lead to a false diagnosis of neuropathy if not corrected.",
            relatedConcept = "Temperature Effects on NCV",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "In electrodiagnosis, what do the F-wave and H-reflex measurements represent, and where do they synapses?",
            options = listOf(
                "F-wave travels antidromically up motor axons to the spinal cord and back without a synapse; H-reflex is a monosynaptic loop involving A-alpha sensory afferents, a spinal synapse, and motor efferents",
                "F-wave represents facial nerve function, while H-reflex represents cardiac pacing reflex loops",
                "Both are purely spinal reflexes that synapse in the cerebellum to control balance",
                "The H-reflex is a non-synaptic wave, while the F-wave synapse in the brainstem exclusively"
            ),
            correctAnswer = "F-wave travels antidromically up motor axons to the spinal cord and back without a synapse; H-reflex is a monosynaptic loop involving A-alpha sensory afferents, a spinal synapse, and motor efferents",
            whyCorrect = "The F-wave is an antidromic response where an electrical impulse travels up a motor axon to the spinal cord and back down, without synapsing (useful for testing proximal nerve roots). The H-reflex is the electrical equivalent of the monosynaptic stretch reflex, traveling up sensory fibers, synapsing in the spinal cord, and returning via motor fibers.",
            whyIncorrect = "F-wave is not restricted to the facial nerve, and H-reflex is not related to cardiac pacing. Neither synapses in the cerebellum or brainstem.",
            clinicalImportance = "These late responses are useful for diagnosing proximal nerve issues (such as Radiculopathy or Guillain-Barré Syndrome) where standard distal NCV tests may be normal.",
            examinerTip = "F-wave uses motor axons only (no synapse); H-reflex is a true reflex arc with a sensory-to-motor synapse in the spinal cord.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "F-waves test proximal motor paths without synapses, while H-reflexes test the complete monosynaptic spinal reflex arc.",
            relatedConcept = "Late Responses: F-Wave and H-Reflex",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),
        VivaMcq(
            question = "According to the Seddon classification of nerve injury, what is 'Neuropraxia', and how does it affect NCV tests below the injury site?",
            options = listOf(
                "A localized conduction block without axonal degeneration; NCV distal to the lesion remains completely normal once the block is bypassed",
                "Complete severing of the axon and myelin sheath; resulting in zero nerve conduction distally within 24 hours",
                "Chronic bone calcification around the nerve that destroys the fiber completely within 1 hour",
                "A mild sensory headache that resolves without treatment"
            ),
            correctAnswer = "A localized conduction block without axonal degeneration; NCV distal to the lesion remains completely normal once the block is bypassed",
            whyCorrect = "Neuropraxia is a mild nerve injury characterized by localized myelin damage and a conduction block, with the axon remaining intact. Because there is no axonal degeneration, nerve conduction below/distal to the injury site remains normal.",
            whyIncorrect = "Axonotmesis or neurotmesis involves axonal degeneration where distal conduction drops to zero. Neuropraxia is a conduction block, not a bone calcification or sensory headache.",
            clinicalImportance = "Allows clinicians to distinguish neuropraxia (good prognosis, quick recovery) from more severe axonal injuries (which require regeneration).",
            examinerTip = "In neuropraxia, distal NCV is normal, but stimulating proximal to the injury site shows a blocked or delayed response.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Neuropraxia is a localized conduction block with normal distal NCV, indicating an intact axon and a favorable prognosis.",
            relatedConcept = "Nerve Injury Classifications and NCV",
            reference = "Clinical Electrophysiology: Electrotherapy and Electrophysiologic Testing, 3rd Ed"
        ),

        // === BIOFEEDBACK (41-45) ===
        VivaMcq(
            question = "Which of the following statements best defines the clinical mechanism and purpose of EMG biofeedback therapy?",
            options = listOf(
                "A training technique that uses surface electrodes to detect muscle electrical activity and provides real-time visual or auditory feedback to help patients regain conscious control",
                "An automated electrical stimulation system that forces passive muscle contractions",
                "A thermal imaging system that measures superficial skin temperature shifts",
                "A diagnostic system that injects radio isotopes to track pelvic blood flow change"
            ),
            correctAnswer = "A training technique that uses surface electrodes to detect muscle electrical activity and provides real-time visual or auditory feedback to help patients regain conscious control",
            whyCorrect = "EMG biofeedback is a training tool. It uses surface electrodes to detect micro-volt electrical activity from contracting muscles and translates this into visual or auditory cues, helping patients learn to contract (up-train) or relax (down-train) specific muscles.",
            whyIncorrect = "It does not apply electrical currents to force contractions (which is NMES). It does not measure skin temperature or inject radioactive isotopes.",
            clinicalImportance = "Useful for re-educating weak muscles (e.g., quadriceps after ACL surgery) or helping relax hyperactive muscles (e.g., upper trapezius in chronic neck pain).",
            examinerTip = "Remember that biofeedback is a learning tool; the patient performs the active work, and the machine only records and feeds back the signal.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "Biofeedback is a neuromuscular training tool that uses real-time biological feedback to help patients improve voluntary muscle control.",
            relatedConcept = "EMG Biofeedback Principles",
            reference = "Biofeedback: A Practitioner's Guide, 4th Ed"
        ),
        VivaMcq(
            question = "How does electrode spacing affect the signal recorded during EMG biofeedback therapy?",
            options = listOf(
                "Wide spacing records activity from a larger pool of muscles (general feedback), while narrow spacing records a more specific, localized signal from a target muscle",
                "Wide spacing blocks all nearby electrical signals, while narrow spacing increases radio interference",
                "Spacing has no effect on the recorded signal intensity or specificity",
                "Wide spacing delivers a painful thermal burn, while narrow spacing is safe"
            ),
            correctAnswer = "Wide spacing records activity from a larger pool of muscles (general feedback), while narrow spacing records a more specific, localized signal from a target muscle",
            whyCorrect = "Electrode spacing determines the volume of muscle tissue analyzed. Wide spacing detects electrical activity from a larger muscle pool (useful for general relaxation training). Narrow spacing isolates signals from a single target muscle (ideal for precise, single-muscle training).",
            whyIncorrect = "Spacing does not block external radio interference. Spacing has a significant effect on signal specificity, and surface biofeedback is non-electrical and cannot cause burns.",
            clinicalImportance = "Use wide spacing to help a patient relax a tense back, and narrow spacing to isolate vastus medialis obliquus (VMO) recruitment.",
            examinerTip = "Adjust electrode spacing based on your treatment goal: wide for relaxation, narrow for targeted muscle re-education.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Wide electrode spacing records broad muscle activity, while narrow spacing isolates signals from a specific target muscle.",
            relatedConcept = "Biofeedback Electrode Placement",
            reference = "Biofeedback: A Practitioner's Guide, 4th Ed"
        ),
        VivaMcq(
            question = "In EMG biofeedback, how are thresholds set and managed for muscle 'up-training' versus muscle 'down-training'?",
            options = listOf(
                "For up-training (muscle re-education), the threshold is set at or above the baseline and patients try to exceed it; for down-training (relaxation), the threshold is set just below baseline, and patients try to stay below it",
                "For up-training, patients are given electric shocks when they fail; for down-training, the machine is turned off completely",
                "Up-training setting utilizes continuous high-frequency laser tones, while down-training uses cold diathermies",
                "There are no threshold options; the machine runs on a single automated setting"
            ),
            correctAnswer = "For up-training (muscle re-education), the threshold is set at or above the baseline and patients try to exceed it; for down-training (relaxation), the threshold is set just below baseline, and patients try to stay below it",
            whyCorrect = "In muscle re-education (up-training), the goal is to increase muscle contraction, so the target threshold is set above baseline to challenge the patient. In muscle relaxation (down-training), the goal is to decrease hyperactivity, so the threshold is set below baseline, encouraging the patient to reduce muscle tension.",
            whyIncorrect = "Biofeedback is a recording therapy; it does not administer painful electric shocks, laser tones, or diathermies.",
            clinicalImportance = "Using clear visual and auditory thresholds helps motivate patients and provides an objective measure of their progress.",
            examinerTip = "Setting appropriate, achievable thresholds is key to keeping the patient motivated and engaged in their rehab.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Up-training sets the threshold above baseline to encourage contraction, while down-training sets it below baseline to promote relaxation.",
            relatedConcept = "Biofeedback Threshold Configuration",
            reference = "Biofeedback: A Practitioner's Guide, 4th Ed"
        ),
        VivaMcq(
            question = "Which of the following is a key clinical indication for using pressure or EMG biofeedback in pelvic floor physical therapy?",
            options = listOf(
                "Stress or urge urinary incontinence, to train and strengthen the levator ani and external urethral sphincter muscles",
                "Acute bacterial pelvic inflammatory disease (PID)",
                "To accelerate the healing of deep inguinal hernias",
                "To permanently replace mechanical catheter drainage in patients with complete spinal cord injuries"
            ),
            correctAnswer = "Stress or urge urinary incontinence, to train and strengthen the levator ani and external urethral sphincter muscles",
            whyCorrect = "Incontinence is often associated with weak pelvic floor muscles. Using vaginal or anal biofeedback probes helps patients identify and voluntarily strengthen the levator ani and sphincter muscles, improving bladder control.",
            whyIncorrect = "PID is an acute infection (contraindicated). Inguinal hernias require surgical repair. Biofeedback cannot replace reflex catheter functions in patients with complete spinal cord injuries.",
            clinicalImportance = "Provides an objective way for patients to learn to isolate and strengthen pelvic floor muscles, which are otherwise difficult to train.",
            examinerTip = "Pelvic floor biofeedback is a highly effective, evidence-based treatment for stress and urge urinary incontinence.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Pelvic floor biofeedback helps patients isolate and strengthen the muscles responsible for bladder control, reducing stress or urge incontinence.",
            relatedConcept = "Pelvic Floor Rehabilitation",
            reference = "Biofeedback: A Practitioner's Guide, 4th Ed"
        ),
        VivaMcq(
            question = "What raw EMG signal processing steps are performed by a biofeedback machine to convert alternating muscle currents into a smooth, readable waveform?",
            options = listOf(
                "Amplification, Rectification (converting negative phases to positive), and Integration/Smoothing",
                "Ionization, physical crystallization, and high-frequency wave scattering",
                "De-cellularization, heating, and magnetic field redirection",
                "Raw signals require zero processing as they are naturally smooth, direct current signals"
            ),
            correctAnswer = "Amplification, Rectification (converting negative phases to positive), and Integration/Smoothing",
            whyCorrect = "Raw muscle signals are small, high-frequency alternating currents. The biofeedback device must: (1) Amplify the tiny signal. (2) Rectify it (converting negative waves to positive to prevent them from canceling out). (3) Integrate/smooth the sign to create a clean, readable waveform for the clinical display.",
            whyIncorrect = "Biofeedback does not use chemical ionization, physical crystallization, de-cellularization, or deep thermal heating. Muscle potentials do not naturally appear as smooth direct currents.",
            clinicalImportance = "Proper signal processing ensures the patient sees a clear, responsive, and steady representation of their muscle effort.",
            examinerTip = "Be prepared to outline the steps of EMG signal processing: Amplification -> Rectification -> Smoothing.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "Biofeedback devices amplify, rectify, and smooth complex alternating muscle signals to display a clear, readable waveform.",
            relatedConcept = "EMG Signal Processing",
            reference = "Biofeedback: A Practitioner's Guide, 4th Ed"
        ),

        // === SHOCKWAVE THERAPY (46-50) ===
        VivaMcq(
            question = "Which of the following physical best describes the acoustic wave profile of Extracorporeal Shockwave Therapy (ESWT)?",
            options = listOf(
                "A single, extremely rapid positive pressure spike (peak within 1 microsecond) followed by a rapid tensile negative pressure phase",
                "A continuous, symmetric sinusoidal wave operating at 1.0 MHz with zero pressure fluctuations",
                "An alternating electrical current with constant voltage and zero kinetic energy emissions",
                "A low-intensity laser beam that scatters light photons across the dermis"
            ),
            correctAnswer = "A single, extremely rapid positive pressure spike (peak within 1 microsecond) followed by a rapid tensile negative pressure phase",
            whyCorrect = "Shockwaves are high-energy acoustic pulses. They are characterized by an extremely fast rise time (less than 1 microsecond) to high peak positive pressures (up to 100 MPa), followed by a brief tensile phase of negative pressure.",
            whyIncorrect = "Continuous sinusoidal waves describe therapeutic ultrasound. Electrical currents and lasers are not acoustic shockwaves.",
            clinicalImportance = "The rapid positive pressure spike and subsequent negative phase deliver unique mechanical forces that stimulate tissue healing.",
            examinerTip = "Remember that a shockwave is a single, sharp acoustic pressure wave, not a continuous oscillatory ultrasound wave.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "ESWT uses high-energy acoustic pulses with a rapid positive pressure spike followed by a brief negative phase to stimulate tissue repair.",
            relatedConcept = "Shockwave Physics",
            reference = "Evidence-Based Electrotherapy, 2nd Ed"
        ),
        VivaMcq(
            question = "What is the primary difference in propagation and energy delivery between Radial Shockwave Therapy and Focused Shockwave Therapy?",
            options = listOf(
                "Radial shockwaves diverge from the applicator tip, losing energy as they penetrate deeper; Focused shockwaves converge at a selected deep point, concentrating their energy in a targeted spot",
                "Radial shockwaves utilize radioactive lead nodes, while Focused shockwaves use ultraviolet light rays",
                "Focused shockwaves are entirely non-painful, whereas Radial shockwaves require general nerve anesthesia",
                "There is no difference; both are terms for therapeutic ultrasound"
            ),
            correctAnswer = "Radial shockwaves diverge from the applicator tip, losing energy as they penetrate deeper; Focused shockwaves converge at a selected deep point, concentrating their energy in a targeted spot",
            whyCorrect = "Radial shockwaves are generated pneumatically and diverge from the applicator tip, meaning their energy is greatest superficially and dissipates as they penetrate. Focused shockwaves are generated electromagnetically or piezoelectrically, focusing their energy at a precise depth to target deeper tissues.",
            whyIncorrect = "Neither uses radioactive lead or ultraviolet light. Neither requires general anesthesia, and they are distinct from traditional low-intensity ultrasound.",
            clinicalImportance = "Use radial shockwaves for superficial muscle/fascial issues (e.g., plantar fasciitis or myofascial trigger points). Use focused shockwaves for deep bone or tendon conditions (e.g., bone non-union or deep calcific tendonitis).",
            examinerTip = "Radial shockwaves are superficial and diverge. Focused shockwaves focus energy at a targeted deep tissue level.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "Radial shockwaves diverge and are best for superficial tissues, while focused shockwaves converge to treat deeper, targeted pathologies.",
            relatedConcept = "Radial vs Focused ESWT",
            reference = "Evidence-Based Electrotherapy, 2nd Ed"
        ),
        VivaMcq(
            question = "How does the negative pressure phase (tensile phase) of a shockwave contribute to therapeutic changes in chronic connective tissues?",
            options = listOf(
                "It induces acoustic cavitation, forming microbubbles that expand and collapse violently, breaking down calcified deposits and triggering tissue remodeling",
                "It cools down the tissue temperature immediately to prevent inflammation",
                "It temporarily pauses local arterial blood flow",
                "It directly dissolves bone minerals, converting them into liquid glycogen"
            ),
            correctAnswer = "It induces acoustic cavitation, forming microbubbles that expand and collapse violently, breaking down calcified deposits and triggering tissue remodeling",
            whyCorrect = "The negative tensile phase of a shockwave generates acoustic cavitation, forming tiny gas bubbles. As these bubbles expand and collapse violently, they produce micro-mechanical stresses that break down calcifications and stimulate cellular repair.",
            whyIncorrect = "ESWT does not cool tissue, does not pause arterial blood flow, and does not convert bone minerals into glycogen.",
            clinicalImportance = "Crucial for treating calcific tendonitis of the shoulder, helping break down calcium deposits and relieving pain.",
            examinerTip = "Remember that cavitation is a major mechanical effect behind the therapeutic benefits of shockwave therapy.",
            subject = "Electrotherapy II",
            difficulty = "High Yield",
            learningPoint = "The negative phase of the shockwave induces cavitation, causing microbubble collapses that break down calcifications and stimulate cellular healing.",
            relatedConcept = "Acoustic Cavitation in ESWT",
            reference = "Evidence-Based Electrotherapy, 2nd Ed"
        ),
        VivaMcq(
            question = "What primary cellular and physiological mechanism explains how ESWT promotes long-term tendon tissue remodeling and repair?",
            options = listOf(
                "Mechanotransduction triggers the release of local growth factors (e.g., VEGF and nitric oxide), stimulating angiogenesis and collagen synthesis",
                "Direct chemical destruction of the sensory nerve terminals, preventing pain sensation entirely",
                "The mechanical shockwaves realign the atomic nuclei of skeletal muscle fibers",
                "Applying pressure waves causes immediate cellular dehydration, eliminating local water content"
            ),
            correctAnswer = "Mechanotransduction triggers the release of local growth factors (e.g., VEGF and nitric oxide), stimulating angiogenesis and collagen synthesis",
            whyCorrect = "Shockwaves deliver mechanical stimuli to cells (mechanotransduction). This triggers the release of growth factors like Vascular Endothelial Growth Factor (VEGF) and nitric oxide, which promote angiogenesis (new blood vessel growth) and stimulate tenocytes to synthesize new collagen.",
            whyIncorrect = "ESWT does not destroy nerve terminals (though it can temporarily desensitize them), does not realign atomic nuclei, and does not cause cellular dehydration.",
            clinicalImportance = "Essential for treating chronic tendinopathies (e.g., plantar fasciitis or Achilles tendinopathy) with poor natural vascularity and slow healing.",
            examinerTip = "Mechanotransduction is the process by which physical forces are converted into biochemical signals to stimulate tissue healing.",
            subject = "Electrotherapy II",
            difficulty = "Moderate",
            learningPoint = "ESWT uses mechanotransduction to release growth factors, promoting angiogenesis and collagen synthesis to heal chronically damaged tendons.",
            relatedConcept = "Mechanotransduction and Tissue Healing",
            reference = "Evidence-Based Electrotherapy, 2nd Ed"
        ),
        VivaMcq(
            question = "Which of the following clinical conditions is a common, evidence-based indication for Extracorporeal Shockwave Therapy (ESWT) in physical therapy?",
            options = listOf(
                "Chronic calcific tendonitis of the supraspinatus, plantar fasciitis, and chronic insertional Achilles tendinopathy",
                "Acute compound fractures with open skin lesions",
                "Deep vein thrombosis (DVT) in the acute phase of calf swelling",
                "Directly over the uterus during pregnancy to treat pelvic floor spasticity"
            ),
            correctAnswer = "Chronic calcific tendonitis of the supraspinatus, plantar fasciitis, and chronic insertional Achilles tendinopathy",
            whyCorrect = "ESWT is an effective, evidence-based treatment for chronic, recalcitrant pathologies like calcific shoulder tendonitis, plantar fasciitis (with or without heel spurs), and chronic Achilles tendinopathies.",
            whyIncorrect = "Acute compound fractures, active deep vein thrombosis, and applications over a pregnant uterus are strict, absolute contraindications for shockwave therapy.",
            clinicalImportance = "Provides a highly effective non-invasive alternative for patients with chronic tendon issues who have not responded to other conservative treatments.",
            examinerTip = "Always rule out acute inflammation or vascular conditions before recommending shockwave therapy.",
            subject = "Electrotherapy II",
            difficulty = "Easy",
            learningPoint = "ESWT is highly effective for chronic tendon issues like plantar fasciitis and calcific tendonitis, but is contraindicated in acute or vascular conditions.",
            relatedConcept = "Clinical Applications of ESWT",
            reference = "Evidence-Based Electrotherapy, 2nd Ed"
        )
    )
}
