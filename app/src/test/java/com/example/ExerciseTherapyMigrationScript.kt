package com.example

import org.junit.Test
import java.io.File
import kotlinx.serialization.json.*
import kotlinx.serialization.encodeToString

class ExerciseTherapyMigrationScript {

    @Test
    fun migrateAllExerciseTherapy() {
        val dirs = listOf(
            File("src/main/assets/content/exercise_therapy_1"),
            File("src/main/assets/content/exercise_therapy_ii")
        )
        
        var migratedCount = 0
        
        dirs.forEach { dir ->
            if (!dir.exists()) {
                println("Directory does not exist: ${dir.absolutePath}")
                return@forEach
            }
            val files = dir.listFiles { file -> file.name.endsWith(".json") }
            
            files?.forEach { file ->
                val content = file.readText()
                val legacyJson = Json.parseToJsonElement(content).jsonObject
                
                val chapterId = legacyJson["chapterId"]?.jsonPrimitive?.content ?: ""
                val title = legacyJson["chapterName"]?.jsonPrimitive?.content ?: ""
                val subject = legacyJson["subject"]?.jsonPrimitive?.content ?: ""
                
                val (keywords, tags) = getTagsAndKeywords(chapterId)
                
                val definitions = legacyJson["definition"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val principles = legacyJson["principle"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val equipment = legacyJson["equipment"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                
                // Clean AI fillers
                fun cleanString(str: String): String {
                    return str.replace("It is important to note that ", "")
                              .replace("It is crucial to understand that ", "")
                              .replace("In summary, ", "")
                              .replace("Overall, ", "")
                              .replace("It is important to note that", "")
                              .replace("It is crucial to understand that", "")
                              .replace("In summary,", "")
                              .replace("Overall,", "")
                }
                
                val cleanDefinitions = definitions.map { cleanString(it) }
                val cleanPrinciples = principles.map { cleanString(it) }
                val cleanEquipment = equipment.map { cleanString(it) }
                
                val clinicalPearls = legacyJson["clinicalPearls"]?.jsonArray?.map { cleanString(it.jsonPrimitive.content) } ?: emptyList()
                val precautions = legacyJson["precautions"]?.jsonArray?.map { cleanString(it.jsonPrimitive.content) } ?: emptyList()
                val indications = legacyJson["indications"]?.jsonArray?.map { cleanString(it.jsonPrimitive.content) } ?: emptyList()
                
                val contraindicationsObj = legacyJson["contraindications"]?.jsonObject
                val genContra = contraindicationsObj?.get("general")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val locContra = contraindicationsObj?.get("local")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                
                val techniqueObj = legacyJson["technique"]?.jsonObject
                val patientPrep = techniqueObj?.get("patientPreparation")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val apparatusPrep = techniqueObj?.get("apparatusPreparation")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val skinPrep = techniqueObj?.get("skinPreparation")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val electrodePlace = techniqueObj?.get("electrodePlacement")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val treatmentProc = techniqueObj?.get("treatmentProcedure")?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                
                val protocols = legacyJson["clinicalProtocols"]?.jsonArray ?: buildJsonArray {}
                val physEffects = legacyJson["physiologicalEffects"]?.jsonArray ?: buildJsonArray {}
                
                val parameters = legacyJson["parameters"]?.jsonObject ?: buildJsonObject {}
                
                val mcqs = legacyJson["mcqs"]?.jsonArray ?: buildJsonArray {}
                
                val vivaLegacy = legacyJson["vivaQuestions"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                val vivaParsed = vivaLegacy.mapIndexed { index, str ->
                    val parts = str.split(" | ")
                    val q = parts.find { it.startsWith("Question: ") }?.substringAfter("Question: ") ?: ""
                    val a = parts.find { it.startsWith("Answer: ") }?.substringAfter("Answer: ") ?: ""
                    val k = parts.find { it.startsWith("Examiner Key Points: ") }?.substringAfter("Examiner Key Points: ") ?: ""
                    val c = parts.find { it.startsWith("Clinical Importance: ") }?.substringAfter("Clinical Importance: ") ?: ""
                    
                    buildJsonObject {
                        put("id", JsonPrimitive("viva_${chapterId}_${index+1}"))
                        put("question", JsonPrimitive(cleanString(q)))
                        put("answer", JsonPrimitive(cleanString(a)))
                        put("keyPoints", JsonPrimitive(cleanString(k)))
                        put("clinicalImportance", JsonPrimitive(cleanString(c)))
                    }
                }
                
                val references = legacyJson["reference"]?.jsonArray?.map { it.jsonPrimitive.content } ?: emptyList()
                
                val newJson = buildJsonObject {
                    put("metadata", buildJsonObject {
                        put("chapterId", JsonPrimitive(chapterId))
                        put("title", JsonPrimitive(title))
                        put("subject", JsonPrimitive(subject))
                        put("keywords", JsonArray(keywords.map { JsonPrimitive(it) }))
                        put("tags", JsonArray(tags.map { JsonPrimitive(it) }))
                    })
                    put("theory", buildJsonObject {
                        put("definitions", JsonArray(cleanDefinitions.map { JsonPrimitive(it) }))
                        put("coreConcepts", JsonArray(cleanPrinciples.map { JsonPrimitive(it) }))
                        put("equipment", JsonArray(cleanEquipment.map { JsonPrimitive(it) }))
                    })
                    put("clinicalCorrelations", buildJsonObject {
                        put("pearls", JsonArray(clinicalPearls.map { JsonPrimitive(it) }))
                        put("precautions", JsonArray(precautions.map { JsonPrimitive(it) }))
                        put("indications", JsonArray(indications.map { JsonPrimitive(it) }))
                        put("contraindications", buildJsonObject {
                            put("general", JsonArray(genContra.map { JsonPrimitive(it) }))
                            put("local", JsonArray(locContra.map { JsonPrimitive(it) }))
                        })
                        put("technique", buildJsonObject {
                            put("patientPreparation", JsonArray(patientPrep.map { JsonPrimitive(it) }))
                            put("apparatusPreparation", JsonArray(apparatusPrep.map { JsonPrimitive(it) }))
                            put("skinPreparation", JsonArray(skinPrep.map { JsonPrimitive(it) }))
                            put("electrodePlacement", JsonArray(electrodePlace.map { JsonPrimitive(it) }))
                            put("treatmentProcedure", JsonArray(treatmentProc.map { JsonPrimitive(it) }))
                        })
                        put("protocols", protocols)
                        put("physiologicalEffects", physEffects)
                    })
                    put("subjectExtensions", buildJsonObject {
                        put("anatomy", buildJsonObject {
                            put("parameters", parameters)
                        })
                    })
                    put("clinicalCases", buildJsonArray {})
                    put("mcqs", mcqs)
                    put("viva", JsonArray(vivaParsed))
                    put("flashcards", buildJsonArray {})
                    put("references", JsonArray(references.map { JsonPrimitive(it) }))
                }
                
                val format = Json { prettyPrint = true }
                val output = format.encodeToString(newJson)
                file.writeText(output)
                migratedCount++
            }
        }
        
        println("Successfully migrated $migratedCount Exercise Therapy files.")
    }

    private fun getTagsAndKeywords(chapterId: String): Pair<List<String>, List<String>> {
        val keywords = mutableListOf<String>()
        val tags = mutableListOf<String>()
        
        when (chapterId) {
            "ex1_ch1" -> {
                keywords.addAll(listOf("levers", "torque", "equilibrium", "gravity", "base of support", "force", "mechanical advantage"))
                tags.addAll(listOf("physics", "biomechanics", "levers"))
            }
            "ex1_ch2" -> {
                keywords.addAll(listOf("range of motion", "passive ROM", "active ROM", "active-assisted ROM", "end-feel", "capsular pattern"))
                tags.addAll(listOf("range_of_motion", "assessment", "stretching"))
            }
            "ex1_ch3" -> {
                keywords.addAll(listOf("starting positions", "standing", "sitting", "lying", "hanging", "kneeling", "muscle work", "stability"))
                tags.addAll(listOf("posture", "kinesiology", "starting_positions"))
            }
            "ex1_ch4" -> {
                keywords.addAll(listOf("massage", "effleurage", "petrissage", "tapotement", "friction", "physiological effects", "indications"))
                tags.addAll(listOf("manual_therapy", "soft_tissue", "massage"))
            }
            "ex1_ch5" -> {
                keywords.addAll(listOf("relaxation", "muscle tone", "spasticity", "mental imagery", "progressive relaxation", "jacobson"))
                tags.addAll(listOf("relaxation", "neurological", "therapeutic"))
            }
            "ex1_ch6" -> {
                keywords.addAll(listOf("joint mobility", "mobilization", "traction", "gliding", "kaltenborn", "maitland", "capsular restriction"))
                tags.addAll(listOf("manual_therapy", "mobilization", "joints"))
            }
            "ex2_ch1" -> {
                keywords.addAll(listOf("goniometry", "range of motion", "universal goniometer", "bony landmarks", "axis of rotation"))
                tags.addAll(listOf("assessment", "goniometry", "range_of_motion"))
            }
            "ex2_ch2" -> {
                keywords.addAll(listOf("stretch", "muscle tightness", "flexibility", "contracture", "pips", "autogenic inhibition", "reciprocal inhibition"))
                tags.addAll(listOf("stretching", "flexibility", "manual_therapy"))
            }
            "ex2_ch3" -> {
                keywords.addAll(listOf("resisted exercise", "manual resistance", "mechanical resistance", "progressive overload", "delorme", "oxford"))
                tags.addAll(listOf("strengthening", "resistance_training", "exercise"))
            }
            "ex2_ch4" -> {
                keywords.addAll(listOf("posture", "plumb line", "scoliosis", "kyphosis", "lordosis", "postural deviation", "balance"))
                tags.addAll(listOf("posture", "assessment", "biomechanics"))
            }
            "ex2_ch5" -> {
                keywords.addAll(listOf("muscle testing", "mrc scale", "manual muscle test", "gravity resisted", "gravity eliminated"))
                tags.addAll(listOf("assessment", "muscle_testing", "mrc"))
            }
            "ex2_ch6" -> {
                keywords.addAll(listOf("traction", "cervical traction", "lumbar traction", "mechanical traction", "joint distraction"))
                tags.addAll(listOf("traction", "manual_therapy", "spine"))
            }
            "ex2_ch7" -> {
                keywords.addAll(listOf("hydrotherapy", "buoyancy", "hydrostatic pressure", "viscosity", "thermodynamics", "pool therapy"))
                tags.addAll(listOf("hydrotherapy", "aquatic_therapy", "physics"))
            }
            "ex2_ch8" -> {
                keywords.addAll(listOf("gait", "gait cycle", "stance phase", "swing phase", "cadence", "gait deviations", "trendelenburg"))
                tags.addAll(listOf("gait", "biomechanics", "assessment"))
            }
            "ex2_ch9" -> {
                keywords.addAll(listOf("crutch walking", "ambulation aids", "walkers", "canes", "three point gait", "two point gait"))
                tags.addAll(listOf("mobility", "assistive_devices", "rehabilitation"))
            }
            "ex2_ch10" -> {
                keywords.addAll(listOf("suspension therapy", "axial suspension", "pendular suspension", "gallows frame", "springs", "slings"))
                tags.addAll(listOf("suspension", "rehabilitation", "biomechanics"))
            }
            "ex2_ch11" -> {
                keywords.addAll(listOf("balance", "proprioception", "vestibular system", "base of support", "equilibrium", "balance training"))
                tags.addAll(listOf("balance", "proprioception", "neurological"))
            }
            "ex2_ch12" -> {
                keywords.addAll(listOf("coordination", "frenkel exercises", "motor control", "cerebellum", "ataxia", "precision", "rhythm"))
                tags.addAll(listOf("coordination", "neurological", "exercise"))
            }
        }
        return Pair(keywords, tags)
    }
}
