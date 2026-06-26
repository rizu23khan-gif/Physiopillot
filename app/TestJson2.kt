import java.io.File
import kotlinx.serialization.json.Json
import com.example.data.*

fun main() {
    val jsonParams = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    val file = File("app/src/main/assets/content/exercise_therapy_1/ex_ex1_ch2.json")
    val jsonString = file.readText()

    try {
        val pucs = jsonParams.decodeFromString<PucsChapter>(jsonString)
        val interactive = pucs.toInteractiveChapterContent()
        println("Success!")
        println(interactive.definition.size)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        e.printStackTrace()
    }
}
