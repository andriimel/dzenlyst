package com.am.dzenlyst.ai

import android.hardware.biometrics.BiometricPrompt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeminiRepository @Inject constructor(
    private val geminiService: GeminiService
) {
//    suspend fun ask(prompt: String): String{
//        return geminiService.generate(prompt)
//    }

    fun askStream(prompt: String): Flow<String> {
        return geminiService.generateStream(prompt)
    }


}