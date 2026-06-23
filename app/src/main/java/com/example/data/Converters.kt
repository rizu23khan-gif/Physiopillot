package com.example.data

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import com.example.ui.components.EvidenceLevel

object Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<String>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromHistoryItemList(value: List<HistoryItem>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toHistoryItemList(value: String?): List<HistoryItem> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<HistoryItem>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromObservationItemList(value: List<ObservationItem>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toObservationItemList(value: String?): List<ObservationItem> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<ObservationItem>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromPalpationItemList(value: List<PalpationItem>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toPalpationItemList(value: String?): List<PalpationItem> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<PalpationItem>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromRomAssessment(value: RomAssessment?): String {
        return if (value == null) "" else json.encodeToString(value)
    }

    @TypeConverter
    fun toRomAssessment(value: String?): RomAssessment {
        if (value.isNullOrEmpty()) return RomAssessment("", "", "", "", "")
        return try {
            json.decodeFromString<RomAssessment>(value)
        } catch (e: Exception) {
            RomAssessment("", "", "", "", "")
        }
    }

    @TypeConverter
    fun fromMmtAssessment(value: MmtAssessment?): String {
        return if (value == null) "" else json.encodeToString(value)
    }

    @TypeConverter
    fun toMmtAssessment(value: String?): MmtAssessment {
        if (value.isNullOrEmpty()) return MmtAssessment("", "", "")
        return try {
            json.decodeFromString<MmtAssessment>(value)
        } catch (e: Exception) {
            MmtAssessment("", "", "")
        }
    }

    @TypeConverter
    fun fromNeuroItemList(value: List<NeuroItem>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toNeuroItemList(value: String?): List<NeuroItem> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<NeuroItem>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromSpecialTestItemList(value: List<SpecialTestItem>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toSpecialTestItemList(value: String?): List<SpecialTestItem> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<SpecialTestItem>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromOutcomeMeasureItemList(value: List<OutcomeMeasureItem>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toOutcomeMeasureItemList(value: String?): List<OutcomeMeasureItem> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<OutcomeMeasureItem>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromGoalItem(value: GoalItem?): String {
        return if (value == null) "" else json.encodeToString(value)
    }

    @TypeConverter
    fun toGoalItem(value: String?): GoalItem {
        if (value.isNullOrEmpty()) return GoalItem(emptyList(), emptyList())
        return try {
            json.decodeFromString<GoalItem>(value)
        } catch (e: Exception) {
            GoalItem(emptyList(), emptyList())
        }
    }

    @TypeConverter
    fun fromSurvivalSoapNote(value: SurvivalSoapNote?): String {
        return if (value == null) "" else json.encodeToString(value)
    }

    @TypeConverter
    fun toSurvivalSoapNote(value: String?): SurvivalSoapNote {
        if (value.isNullOrEmpty()) return SurvivalSoapNote("", "", "", "")
        return try {
            json.decodeFromString<SurvivalSoapNote>(value)
        } catch (e: Exception) {
            SurvivalSoapNote("", "", "", "")
        }
    }

    @TypeConverter
    fun fromSurvivalVivaQuestionList(value: List<SurvivalVivaQuestion>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toSurvivalVivaQuestionList(value: String?): List<SurvivalVivaQuestion> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<SurvivalVivaQuestion>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromEvidenceLevel(value: EvidenceLevel?): String {
        return value?.name ?: EvidenceLevel.LEVEL_V.name
    }

    @TypeConverter
    fun toEvidenceLevel(value: String?): EvidenceLevel {
        if (value.isNullOrEmpty()) return EvidenceLevel.LEVEL_V
        return try {
            EvidenceLevel.valueOf(value)
        } catch (e: Exception) {
            EvidenceLevel.LEVEL_V
        }
    }

    @TypeConverter
    fun fromPostingStepList(value: List<PostingStep>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toPostingStepList(value: String?): List<PostingStep> {
        if (value.isNullOrEmpty()) return emptyList()
        return try {
            json.decodeFromString<List<PostingStep>>(value)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
