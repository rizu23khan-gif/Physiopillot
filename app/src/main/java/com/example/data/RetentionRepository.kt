package com.example.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import com.example.data.ClinicalCaseRepo

class RetentionRepository(
    private val context: Context,
    private val retentionDao: RetentionDao,
    private val physioDao: PhysioDao
) {
    val allCards: Flow<List<ReviewCard>> = retentionDao.getAllCards()
    val dueCards: Flow<List<ReviewCard>> = retentionDao.getDueCards(System.currentTimeMillis())
    val allSessions: Flow<List<ReviewSession>> = retentionDao.getAllSessions()

    fun getDueCardsCountFlow(): Flow<Int> {
        return retentionDao.getDueCardsCount(System.currentTimeMillis())
    }

    suspend fun seedFlashcardsIfNeeded() = withContext(Dispatchers.IO) {
        val existing = retentionDao.getAllCards().first()
        val existingIds = existing.map { it.cardId }.toSet()

        val cards = mutableListOf<ReviewCard>()

        // 1. Anatomy Muscles
        try {
            for (muscle in ContentRepo.anatomies) {
                cards.add(
                    ReviewCard(
                        cardId = "fc_anat_muscle_${muscle.id}",
                        subject = "Anatomy",
                        category = "Muscle Specs",
                        frontText = "Recall the Origin, Insertion, Innervation, and Action of: ${muscle.name}",
                        backText = "Origin: ${muscle.origin}\n\nInsertion: ${muscle.insertion}\n\nNerve Supply: ${muscle.nerveSupply}\n\nAction: ${muscle.action}\n\nClinical Resonance: ${muscle.clinicalImportance}"
                    )
                )
                if (muscle.palpationLandmarks.isNotEmpty() || muscle.biomechanicalImportance.isNotEmpty()) {
                    cards.add(
                        ReviewCard(
                            cardId = "fc_anat_landmark_${muscle.id}",
                            subject = "Anatomy",
                            category = "Surface Landmarks",
                            frontText = "What are the surface palpation landmarks and biomechanical role of direct interest for: ${muscle.name}?",
                            backText = "Palpation Landmarks: ${muscle.palpationLandmarks}\n\nBiomechanical Role: ${muscle.biomechanicalImportance}"
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Pharmacology from Diagnoses
        try {
            for (diagnosis in ContentRepo.diagnoses) {
                if (diagnosis.pharmacology.isNotEmpty()) {
                    cards.add(
                        ReviewCard(
                            cardId = "fc_pharm_${diagnosis.id}",
                            subject = "Pharmacology",
                            category = "Drug Rationale",
                            frontText = "Recall pharmacological protocols and standard physiotherapy-specific drug rationales for: ${diagnosis.name}",
                            backText = diagnosis.pharmacology.joinToString("\n\n• ", prefix = "• ")
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 3. Curriculum Chapters (Physiology, Electrotherapy, Exercise Therapy, Biomechanics)
        try {
            val chapterKeys = listOf(
                "et2_ift", "el2_ch2", "el2_ch3", "el2_ch4", "el2_ch5", "el2_ch6", "el2_ch7", "el2_ch8", "el2_ch9", "el2_ch10", "el2_ch11",
                "anat_ch1", "anat_ch2", "anat_ch3", "anat_ch4", "anat_ch5", "anat_ch6", "anat_ch7", "anat_ch8", "anat_ch9", "anat_ch10",
                "anat_ch11", "anat_ch12", "anat_ch13", "anat_ch14", "anat_ch15", "anat_ch16", "anat_ch17", "anat_ch18", "anat_ch19", "anat_ch20",
                "anat_ch21", "anat_ch22", "anat_ch23", "anat_ch24", "anat_ch25", "anat_ch26", "anat_ch27", "anat_ch28", "anat_ch29", "anat_ch30", "anat_ch31",
                "phys_ch1", "phys_ch2", "phys_ch3", "phys_ch4",
                "ex1_ch1", "ex1_ch2", "ex1_ch3", "ex1_ch4", "ex1_ch5", "ex1_ch6",
                "el1_ch1", "el1_ch2", "el1_ch3", "el1_ch4",
                "bio_ch1", "bio_ch2", "bio_ch3", "bio_ch4",
                "ex2_ch1", "ex2_ch2", "ex2_ch3", "ex2_ch4", "ex2_ch5", "ex2_ch6", "ex2_ch7", "ex2_ch8", "ex2_ch9", "ex2_ch10", "ex2_ch11", "ex2_ch12"
            )

            for (chapterId in chapterKeys) {
                val content = ChapterContentRepository.loadChapterContent(context, chapterId) ?: continue
                val mappedSubject = when {
                    chapterId.startsWith("phys") -> "Physiology"
                    chapterId.startsWith("ex") -> "Exercise Therapy"
                    chapterId.startsWith("el") || chapterId.startsWith("et") -> "Electrotherapy"
                    chapterId.startsWith("bio") -> "Biomechanics"
                    chapterId.startsWith("anat") -> "Anatomy"
                    else -> "General"
                }

                if (content.definition.isNotEmpty()) {
                    cards.add(
                        ReviewCard(
                            cardId = "fc_def_${chapterId}",
                            subject = mappedSubject,
                            category = "Definition",
                            frontText = "[${content.chapterName}] What is the core medical definition or primary outline description?",
                            backText = content.definition.joinToString("\n\n")
                        )
                    )
                }
                
                if (content.principle.isNotEmpty()) {
                    cards.add(
                        ReviewCard(
                            cardId = "fc_princ_${chapterId}",
                            subject = mappedSubject,
                            category = "Core Principle",
                            frontText = "[${content.chapterName}] Outline the fundamental physics or therapy principle of action.",
                            backText = content.principle.joinToString("\n\n")
                        )
                    )
                }

                // Add up to 2 MCQs per chapter to keep list clean
                content.mcqs.take(2).forEachIndexed { idx, mcq ->
                    val optionsText = mcq.options.mapIndexed { oIdx, opt -> "${'A' + oIdx}. $opt" }.joinToString("\n")
                    cards.add(
                        ReviewCard(
                            cardId = "fc_mcq_${chapterId}_$idx",
                            subject = mappedSubject,
                            category = "MCQ Revision",
                            frontText = "[${content.chapterName}] Board Question:\n${mcq.question}\n\n$optionsText",
                            backText = "Correct Answer: ${mcq.answer}\n\nRationale:\n${mcq.explanation}"
                        )
                    )
                }

                // Add up to 2 Viva questions per chapter
                content.vivaQuestions.take(2).forEachIndexed { idx, q ->
                    val parsed = parseVivaQuestion(q)
                    val backBuilder = StringBuilder()
                    if (parsed.answer.isNotEmpty()) {
                        backBuilder.append("Answer:\n").append(parsed.answer)
                    }
                    if (parsed.keyPoints.isNotEmpty()) {
                        if (backBuilder.isNotEmpty()) backBuilder.append("\n\n")
                        backBuilder.append("Examiner Key Points:\n").append(parsed.keyPoints)
                    }
                    if (parsed.clinicalImportance.isNotEmpty()) {
                        if (backBuilder.isNotEmpty()) backBuilder.append("\n\n")
                        backBuilder.append("Clinical Importance:\n").append(parsed.clinicalImportance)
                    }
                    val formattedBack = if (backBuilder.isNotEmpty()) backBuilder.toString() else "Revise relevant clinical chapters."

                    cards.add(
                        ReviewCard(
                            cardId = "fc_viva_${chapterId}_$idx",
                            subject = mappedSubject,
                            category = "Oral Viva",
                            frontText = parsed.question,
                            backText = formattedBack
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 4. Clinical Case Engine V1
        try {
            // Cases
            for (case in ClinicalCaseRepo.clinicalCases) {
                cards.add(
                    ReviewCard(
                        cardId = "fc_case_${case.id}",
                        subject = "Clinical Cases",
                        category = "Clinical Cases",
                        frontText = "Clinical Scenario:\n${case.scenario}\n\nQuestion:\n${case.question}",
                        backText = "Diagnosis: ${case.diagnosis}\nAnatomical Basis: ${case.anatomicalBasis}\nPathophysiology: ${case.pathophysiology}\nClinical Tests: ${case.clinicalTests}\nFunctional Consequences: ${case.functionalConsequences}\nPhysiotherapy Relevance: ${case.physiotherapyRelevance}\nExaminer Pearl: ${case.examinerPearl}"
                    )
                )
            }
            // Nerves
            for (nerve in ClinicalCaseRepo.nerveDefinitions) {
                cards.add(
                    ReviewCard(
                        cardId = "fc_nerve_${nerve.nerveName.replace(" ", "_")}",
                        subject = "Nerve Lesions",
                        category = "Nerve Lesions",
                        frontText = "Recall the clinical features for lesion of: ${nerve.nerveName}",
                        backText = "Root Values: ${nerve.rootValues}\nMotor Loss: ${nerve.motorLoss}\nSensory Loss: ${nerve.sensoryLoss}\nDeformity: ${nerve.deformity}\nFunctional Deficit: ${nerve.functionalDeficit}\nClinical Sign: ${nerve.clinicalSign}"
                    )
                )
            }
            // Gait
            for (gait in ClinicalCaseRepo.gaitDefinitions) {
                cards.add(
                    ReviewCard(
                        cardId = "fc_gait_${gait.gaitType.replace(" ", "_")}",
                        subject = "Gait Analysis",
                        category = "Gait Analysis",
                        frontText = "Clinical presentation of: ${gait.gaitType}",
                        backText = "Observation: ${gait.observation}\nWeak Structure: ${gait.weakStructure}\nCompensation: ${gait.compensation}\nPhase Affected: ${gait.phaseAffected}\nClinical Significance: ${gait.clinicalSignificance}"
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val existingMap = existing.associateBy { it.cardId }
        val finalCardsToInsert = cards.map { card ->
            val existingCard = existingMap[card.cardId]
            if (existingCard != null) {
                card.copy(
                    state = existingCard.state,
                    lastReviewed = existingCard.lastReviewed,
                    nextReviewDue = existingCard.nextReviewDue,
                    intervalDays = existingCard.intervalDays,
                    easeFactor = existingCard.easeFactor,
                    timesReviewed = existingCard.timesReviewed
                )
            } else {
                card
            }
        }
        if (finalCardsToInsert.isNotEmpty()) {
            retentionDao.insertAllCards(finalCardsToInsert)
        }
    }

    private data class ParsedVivaData(
        val question: String,
        val answer: String,
        val keyPoints: String,
        val clinicalImportance: String
    )

    private fun parseVivaQuestion(vivaStr: String): ParsedVivaData {
        val question = extractField(vivaStr, "Question:")
        val answer = extractField(vivaStr, "Answer:")
        val keyPoints = extractField(vivaStr, "Examiner Key Points:")
        val clinicalImportance = extractField(vivaStr, "Clinical Importance:")
        
        val finalQuestion = if (question.isNotEmpty()) question else vivaStr
        return ParsedVivaData(finalQuestion, answer, keyPoints, clinicalImportance)
    }

    private fun extractField(vivaStr: String, prefix: String): String {
        val index = vivaStr.indexOf(prefix, ignoreCase = true)
        if (index == -1) return ""
        val rem = vivaStr.substring(index + prefix.length)
        val pipeIndex = rem.indexOf('|')
        return if (pipeIndex != -1) rem.substring(0, pipeIndex).trim() else rem.trim()
    }

    suspend fun recordReview(cardId: String, quality: String) = withContext(Dispatchers.IO) {
        val cards = retentionDao.getAllCards().first()
        val card = cards.find { it.cardId == cardId } ?: return@withContext

        val now = System.currentTimeMillis()
        val nextState: String
        val nextInterval: Int
        val nextEase: Float

        when (quality) {
            "HARD" -> {
                nextState = "LEARNING"
                nextInterval = 1
                nextEase = maxOf(1.3f, card.easeFactor - 0.2f)
            }
            "GOOD" -> {
                nextState = "REVIEWING"
                nextInterval = if (card.timesReviewed == 0) 1 else if (card.timesReviewed == 1) 3 else (card.intervalDays * card.easeFactor).toInt().coerceAtLeast(card.intervalDays + 1)
                nextEase = card.easeFactor
            }
            "EASY" -> {
                nextState = "MASTERED"
                nextInterval = if (card.timesReviewed == 0) 4 else (card.intervalDays * card.easeFactor * 1.5f).toInt().coerceAtLeast(card.intervalDays + 5)
                nextEase = minOf(3.0f, card.easeFactor + 0.15f)
            }
            else -> {
                nextState = card.state
                nextInterval = card.intervalDays
                nextEase = card.easeFactor
            }
        }

        val nextDueDate = now + (nextInterval * 24L * 60L * 60L * 1000L)

        retentionDao.updateCardProgress(
            cardId = cardId,
            state = nextState,
            lastReviewed = now,
            nextReviewDue = nextDueDate,
            intervalDays = nextInterval,
            easeFactor = nextEase,
            timesReviewed = card.timesReviewed + 1
        )

        // Increment physical therapy activity streak using the existing core ProgressManager
        ProgressManager.logActivityAndCheckStreak(
            context = context,
            topicId = "fc_$cardId",
            title = "Flashcard: ${card.category}",
            type = "VIVA",
            subject = card.subject,
            year = "1st Year"
        )
    }

    suspend fun saveSession(reviewedCount: Int, successfulCount: Int, durationMs: Long) = withContext(Dispatchers.IO) {
        val session = ReviewSession(
            sessionId = java.util.UUID.randomUUID().toString(),
            timestamp = System.currentTimeMillis(),
            cardsReviewed = reviewedCount,
            successfulReviews = successfulCount,
            durationMs = durationMs
        )
        retentionDao.insertSession(session)
    }

    suspend fun clearAndResetDeck() = withContext(Dispatchers.IO) {
        retentionDao.clearAllCards()
        seedFlashcardsIfNeeded()
    }
}
