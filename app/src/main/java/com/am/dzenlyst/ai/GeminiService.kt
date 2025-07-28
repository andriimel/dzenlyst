package com.am.dzenlyst.ai

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend


class GeminiService {

    private val model  = Firebase.ai(backend = GenerativeBackend.vertexAI()).generativeModel("gemini-2.5-flash")

    suspend fun generate(prompt: String): String {
        return try {
            Log.d("GeminiService", "Sending prompt: $prompt")
            val response = model.generateContent(prompt)

            response.text ?: "No response"
        } catch (e: Exception) {
            Log.e("GeminiService", "Error from Gemini: ${e.message}", e)
            "Error: ${e.message}"
        }
    }
}