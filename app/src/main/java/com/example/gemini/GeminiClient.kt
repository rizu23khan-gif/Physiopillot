package com.example.gemini

import com.example.BuildConfig
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GeminiClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val service: GeminiApiService by lazy {
        val json = Json { ignoreUnknownKeys = true; encodeDefaults = true; explicitNulls = false }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        retrofit.create(GeminiApiService::class.java)
    }

    suspend fun generateText(
        prompt: String,
        modelName: String = "gemini-3.5-flash",
        systemInstruction: String? = null,
        tools: List<kotlinx.serialization.json.JsonObject>? = null,
        useHighThinking: Boolean = false,
        conversationHistory: List<Content> = emptyList()
    ): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY" || apiKey == "YOUR_API_KEY" || apiKey.startsWith("TODO")) {
            return@withContext "Hi! The Gemini API Key has not been configured yet. 💡 Please configure your private API Key by entering 'GEMINI_API_KEY' inside the Secrets panel of Google AI Studio to enable real-time expert physiotherapy tutoring!"
        }
        val contents = conversationHistory.toMutableList()
        contents.add(Content(parts = listOf(Part(text = prompt)), role = "user"))
        
        val generationConfig = if (useHighThinking) {
            GenerationConfig(thinkingConfig = ThinkingConfig(thinkingLevel = "high"))
        } else {
            null
        }

        val request = GenerateContentRequest(
            contents = contents,
            systemInstruction = systemInstruction?.let { Content(parts = listOf(Part(text = it))) },
            tools = tools,
            generationConfig = generationConfig
        )
        try {
            val response = service.generateContent(modelName, apiKey, request)
            response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "No response text"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
