package com.example.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.feature.assessment.SpecialTestModel
import com.example.feature.curriculum.SubjectBlueprintItem
import com.example.feature.home.DashboardSubject

object CurriculumRepository {

    // 1st Year
    val ANATOMY = "Anatomy"
    val PHYSIOLOGY = "Physiology"
    val ELECTRO_I = "Electrotherapy I"
    val EXERCISE_I = "Exercise Therapy I"

    // 2nd Year
    val ELECTRO_II = "Electrotherapy II"
    val EXERCISE_II = "Exercise Therapy II"
    val BIOMECHANICS = "Biomechanics & Kinesiology"
    val PATHOLOGY = "Pathology"
    val MICROBIOLOGY = "Microbiology"
    val PHARMACOLOGY = "Pharmacology"

    // 3rd Year
    val GEN_MEDICINE = "General Medicine"
    val GEN_SURGERY = "General Surgery"
    val NEURO_SURG_3RD = "Neurology & Neurosurgery"
    val ASSESSMENT_3RD = "Physical/Functional Assessment & Management"
    val RESEARCH_STATS = "Research Methodology & Biostatistics"

    // 4th Year
    val PT_ORTHO_SPORTS = "PT in Orthopaedics & Sports"
    val PT_SURG_OBS = "PT in Surgery & Obstetrics"
    val PT_MED_CONDITIONS = "PT in Medical Conditions"
    val PT_NEURO_4TH = "PT in Neurology & Neurosurgery"
    val ETHICS_REHAB = "Ethics & Rehabilitation"

    // Internship
    val CLINICAL_POSTING = "Clinical Posting"
    val CASE_DISCUSSION = "Case Discussion"
    val DOCUMENTATION = "Documentation"
    val HOSPITAL_PRACTICE = "Hospital Practice"

    // Map each subject to its Metadata
    data class SubjectMetadata(
        val name: String,
        val description: String,
        val yearGroup: String, // "1st Year", "2nd Year", "3rd Year", "4th Year", "Internship"
        val icon: ImageVector,
        val accentColor: Color,
        val routeId: String
    )

    val allSubjects = listOf(
        // 1st Year
        SubjectMetadata(ANATOMY, "Study of bone structures, muscle attachments, regional nerves and cranial anatomy.", "1st Year", Icons.Default.Accessibility, Color(0xFF6366F1), "anatomy"),
        SubjectMetadata(PHYSIOLOGY, "Nervous systems, reflex arcs, muscle fibers, and cell metabolism.", "1st Year", Icons.Default.FavoriteBorder, Color(0xFFEC4899), "viva"),
        SubjectMetadata(ELECTRO_I, "Basic principles of electricity, low-frequency therapeutic currents & safety rules.", "1st Year", Icons.Default.FlashOn, Color(0xFFF59E0B), "viva"),
        SubjectMetadata(EXERCISE_I, "Foundational exercises, skeletal levers, passive ROM, and muscle massage.", "1st Year", Icons.Default.FitnessCenter, Color(0xFF10B981), "anatomy"),

        // 2nd Year
        SubjectMetadata(ELECTRO_II, "Medium & high-frequency currents, therapeutic lasers, TENS and SWD parameters.", "2nd Year", Icons.Default.FlashOn, Color(0xFFD97706), "viva"),
        SubjectMetadata(EXERCISE_II, "Passive stretching, suspension therapy, traction, and goniometry mechanics.", "2nd Year", Icons.Default.FitnessCenter, Color(0xFF059669), "anatomy"),
        SubjectMetadata(BIOMECHANICS, "Force vectors, joint levers, osteokinematics, gait kinetics and posture.", "2nd Year", Icons.Default.TrendingUp, Color(0xFF0D9488), "anatomy"),
        SubjectMetadata(PATHOLOGY, "Cell injury, Wallerian degeneration and inflammation stages.", "2nd Year", Icons.Default.Science, Color(0xFF9333EA), "viva"),
        SubjectMetadata(MICROBIOLOGY, "Wound pathology, sterile handling, and hospital-acquired infections.", "2nd Year", Icons.Default.Science, Color(0xFF9333EA), "viva"),
        SubjectMetadata(PHARMACOLOGY, "Drug action mechanisms, NSAIDs, skeletal muscle relaxants, and analgesics.", "2nd Year", Icons.Default.Science, Color(0xFF9333EA), "viva"),

        // 3rd Year
        SubjectMetadata(GEN_MEDICINE, "Systems pathophysiology, cardiovascular and respiratory medical syndromes.", "3rd Year", Icons.Default.MedicalServices, Color(0xFFEA580C), "viva"),
        SubjectMetadata(GEN_SURGERY, "Surgical access, tissue grafting, scars and postoperative recovery care.", "3rd Year", Icons.Default.Healing, Color(0xFFDC2626), "viva"),
        SubjectMetadata(NEURO_SURG_3RD, "Nerve suture repair, nerve regeneration monitoring, and brain injury care.", "3rd Year", Icons.Default.Psychology, Color(0xFF8B5CF6), "viva"),
        SubjectMetadata(ASSESSMENT_3RD, "Clinical physical assessments, pain intensity scales, posture and balance tests.", "3rd Year", Icons.Default.ContentPaste, Color(0xFF4F46E5), "assessment"),
        SubjectMetadata(RESEARCH_STATS, "Research designs, epidemiological sampling, and statistical significance.", "3rd Year", Icons.Default.Analytics, Color(0xFF2563EB), "viva"),

        // 4th Year
        SubjectMetadata(PT_ORTHO_SPORTS, "Tendonitis, meniscal tears, bone fractures, and ligament rehabilitation.", "4th Year", Icons.Default.SportsBasketball, Color(0xFF2563EB), "assessment"),
        SubjectMetadata(PT_SURG_OBS, "Post-surgical wound mobilization, thoracic drainage, and pelvic floor re-education.", "4th Year", Icons.Default.Face, Color(0xFFDB2777), "posting"),
        SubjectMetadata(PT_MED_CONDITIONS, "Cardiopulmonary, neuromuscular and systemic clinical therapy protocols.", "4th Year", Icons.Default.DirectionsRun, Color(0xFFE11D48), "assessment"),
        SubjectMetadata(PT_NEURO_4TH, "Neuro-plasticity recovery models, stroke rehabilitation, CP training.", "4th Year", Icons.Default.Psychology, Color(0xFF7C3AED), "assessment"),
        SubjectMetadata(ETHICS_REHAB, "Standard ethical practice, disability acts, and clinical legal bounds.", "4th Year", Icons.Default.Groups, Color(0xFF0F766E), "posting"),

        // Internship
        SubjectMetadata(CLINICAL_POSTING, "Clinical posting rotations and bedside reporting under supervision.", "Internship", Icons.Default.MedicalServices, Color(0xFF14B8A6), "posting"),
        SubjectMetadata(CASE_DISCUSSION, "Formal inpatient histories, clinical arguments, and clinical reasoning.", "Internship", Icons.Default.Forum, Color(0xFF0284C7), "posting"),
        SubjectMetadata(DOCUMENTATION, "Writing detailed clinical progress notes and legal discharge charts.", "Internship", Icons.Default.Description, Color(0xFF0891B2), "posting"),
        SubjectMetadata(HOSPITAL_PRACTICE, "Special ward ergonomics, equipment maintenance, and emergency duties.", "Internship", Icons.Default.Apartment, Color(0xFF4338CA), "posting")
    )

    fun getSubjectsForYear(year: String): List<SubjectMetadata> {
        return allSubjects.filter { it.yearGroup.equals(year, ignoreCase = true) }
    }

    // This dynamically calculates the counts for each of the canonical subjects
    fun calculateSubjectProgress(
        subjectName: String,
        currentAnatomies: List<DetailedAnatomy>,
        currentVivaTopics: List<VivaTopic>,
        currentAssessments: List<DiagnosisData>,
        currentPostings: List<SurvivalModule>,
        completedSet: Set<String>
    ): Pair<Int, Int> { // Pair(completed, total)
        return when (subjectName) {
            ANATOMY -> {
                val total = currentAnatomies.size + currentVivaTopics.count { it.category == "Anatomy" }
                val completed = currentAnatomies.count { completedSet.contains(it.id) } + currentVivaTopics.count { it.category == "Anatomy" && completedSet.contains(it.id) }
                Pair(completed, total)
            }
            PHYSIOLOGY -> {
                val baseTotal = 6
                // Map to some general first year tasks / physiology tagged items
                val completed = completedSet.count { it.startsWith("phys_") || it.contains("physiology") }
                Pair(completed, baseTotal)
            }
            ELECTRO_I -> {
                val total = currentVivaTopics.count { it.category == "Electrotherapy" }
                val completed = currentVivaTopics.count { it.category == "Electrotherapy" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            EXERCISE_I -> {
                val total = currentVivaTopics.count { it.category == "Exercise Therapy" }
                val completed = currentVivaTopics.count { it.category == "Exercise Therapy" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            ELECTRO_II -> {
                // High frequency / diathermy reference
                val baseTotal = 2
                val completed = if (completedSet.contains("electro_sd_curve")) 1 else 0
                Pair(completed, baseTotal)
            }
            EXERCISE_II -> {
                val baseTotal = 3
                val completed = completedSet.count { it.contains("ex_therapy") }
                Pair(completed, baseTotal)
            }
            BIOMECHANICS -> {
                val total = currentVivaTopics.count { it.category == "Biomechanics" }
                val completed = currentVivaTopics.count { it.category == "Biomechanics" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            PATHOLOGY -> {
                val total = currentVivaTopics.count { it.category == "Pathology" }
                val completed = currentVivaTopics.count { it.category == "Pathology" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            MICROBIOLOGY -> {
                val total = currentVivaTopics.count { it.category == "Microbiology" }
                val completed = currentVivaTopics.count { it.category == "Microbiology" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            PHARMACOLOGY -> {
                val total = currentVivaTopics.count { it.category == "Pharmacology" }
                val completed = currentVivaTopics.count { it.category == "Pharmacology" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            GEN_MEDICINE -> {
                val baseTotal = 3
                val completed = completedSet.count { it.contains("medicine") || it.contains("med_") }
                Pair(completed, baseTotal)
            }
            GEN_SURGERY -> {
                val baseTotal = 3
                val completed = completedSet.count { it.contains("surgery") || it.contains("surg_") }
                Pair(completed, baseTotal)
            }
            NEURO_SURG_3RD -> {
                val baseTotal = 2
                val completed = completedSet.count { it.startsWith("neurology") }
                Pair(completed, baseTotal)
            }
            ASSESSMENT_3RD -> {
                // Assessment methods and core special tests
                val baseTotal = 4
                val completed = completedSet.count { it.startsWith("ast_") || it.contains("assess_") }
                Pair(completed, baseTotal)
            }
            RESEARCH_STATS -> {
                val baseTotal = 2
                val completed = completedSet.count { it.contains("research_") }
                Pair(completed, baseTotal)
            }
            PT_ORTHO_SPORTS -> {
                // Orthopedic cases
                val orthoCases = currentAssessments.filter {
                    val n = it.diagnosisName.lowercase()
                    n.contains("acl") || n.contains("sprain") || n.contains("shoulder") || n.contains("fracture") || n.contains("osteo") || n.contains("arthritis") || n.contains("joint") || n.contains("ortho")
                }
                val total = orthoCases.size + currentVivaTopics.count { it.category == "Orthopedics" }
                val completed = orthoCases.count { completedSet.contains(it.id) } + currentVivaTopics.count { it.category == "Orthopedics" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            PT_SURG_OBS -> {
                val baseTotal = 3
                val completed = completedSet.count { it.contains("obs_") || it.contains("pregnancy") }
                Pair(completed, baseTotal)
            }
            PT_MED_CONDITIONS -> {
                val cardioCases = currentAssessments.filter {
                    val n = it.diagnosisName.lowercase()
                    !n.contains("acl") && !n.contains("sprain") && !n.contains("shoulder") && !n.contains("fracture") && !n.contains("osteo") && !n.contains("arthritis") && !n.contains("joint") && !n.contains("ortho") &&
                    !it.id.contains("neu", ignoreCase = true) && !n.contains("stroke") && !n.contains("palsy") && !n.contains("spinal") && !n.contains("bifida") && !n.contains("cerebral")
                }
                val total = cardioCases.size + currentVivaTopics.count { it.category == "Cardiopulmonary" }
                val completed = cardioCases.count { completedSet.contains(it.id) } + currentVivaTopics.count { it.category == "Cardiopulmonary" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            PT_NEURO_4TH -> {
                val neuroCases = currentAssessments.filter {
                    val n = it.diagnosisName.lowercase()
                    it.id.contains("neu", ignoreCase = true) || n.contains("stroke") || n.contains("palsy") || n.contains("spinal") || n.contains("bifida") || n.contains("cerebral")
                }
                val total = neuroCases.size + currentVivaTopics.count { it.category == "Neurology" }
                val completed = neuroCases.count { completedSet.contains(it.id) } + currentVivaTopics.count { it.category == "Neurology" && completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            ETHICS_REHAB -> {
                val baseTotal = 2
                val completed = completedSet.count { it.contains("ethics_") }
                Pair(completed, baseTotal)
            }
            CLINICAL_POSTING -> {
                val total = currentPostings.size
                val completed = currentPostings.count { completedSet.contains(it.id) }
                Pair(completed, maxOf(total, 1))
            }
            CASE_DISCUSSION -> {
                val baseTotal = 2
                val completed = completedSet.count { it.contains("case_") }
                Pair(completed, baseTotal)
            }
            DOCUMENTATION -> {
                val baseTotal = 2
                val completed = completedSet.count { it.contains("doc_") }
                Pair(completed, baseTotal)
            }
            HOSPITAL_PRACTICE -> {
                val baseTotal = 2
                val completed = completedSet.count { it.contains("hosp_") }
                Pair(completed, baseTotal)
            }
            else -> Pair(0, 0)
        }
    }
}
