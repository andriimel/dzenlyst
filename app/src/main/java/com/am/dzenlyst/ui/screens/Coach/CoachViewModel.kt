package com.am.dzenlyst.ui.screens.Coach

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.dzenlyst.ai.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoachViewModel @Inject constructor( private val repo: GeminiRepository): ViewModel() {

    var prompt by mutableStateOf("")
    var result by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun onPromptChange(value: String) {
        prompt = value
    }

    fun askGeminiStream() {
        val currentPrompt = prompt.trim()
        if (currentPrompt.isBlank()) return


        viewModelScope.launch {
            isLoading = true
            result = ""
            try {
               repo.askStream(currentPrompt).collect { chunk ->
                   isLoading = false
                   for (char in chunk) {
                       result += char
                       delay(20)
                   }
               }
            } catch (e: Exception) {
                result = "Error: ${e.localizedMessage}"
            } finally {
                isLoading = false
                prompt = ""
            }
        }
    }

}