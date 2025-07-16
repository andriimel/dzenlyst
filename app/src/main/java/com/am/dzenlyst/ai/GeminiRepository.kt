package com.am.dzenlyst.ai

import android.hardware.biometrics.BiometricPrompt
import javax.inject.Inject

class GeminiRepository @Inject constructor(
    private val geminiService: GeminiService
) {
    suspend fun ask(prompt: String): String{
        return geminiService.generate(prompt)
    }


}