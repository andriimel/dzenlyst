package com.am.dzenlyst.ai

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.ai.type.content

class GeminiService {

    private val model  = Firebase.ai(backend = GenerativeBackend.vertexAI()).generativeModel("gemini-2.5-flash")

       // Generate content
//    suspend fun generate(prompt: String): String {
//        return try {
//            Log.d("GeminiService", "Sending prompt: $prompt")
//            val response = model.generateContent(prompt)
//
//            response.text ?: "No response"
//        } catch (e: Exception) {
//            Log.e("GeminiService", "Error from Gemini: ${e.message}", e)
//            "Error: ${e.message}"
//        }
//    }

    //Generate stream
    fun generateStream(prompt: String): Flow<String> = flow {
        val content = content { text(prompt) }
        model.generateContentStream(content).collect { response ->
            response.text?.let { emit(it) }
        }
    }
}