package com.am.dzenlyst.ai

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart


class GeminiService {

    private val model  = GenerativeModel(
        modelName = "models/gemini-1.5-flash",
        apiKey = GeminiApiKey.KEY
    )

    suspend fun generate(prompt: String): String {
        return try {
            Log.d("GeminiService", "Sending prompt: $prompt")
            val response = model.generateContent(
                Content(parts = listOf(TextPart(prompt)))
            )
            response.text ?: "No response"
        } catch (e: Exception) {
            Log.e("GeminiService", "Error from Gemini: ${e.message}", e)
            "Error: ${e.message}"
        }
    }

}