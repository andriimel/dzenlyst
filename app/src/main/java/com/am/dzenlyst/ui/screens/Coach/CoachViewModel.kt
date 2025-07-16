package com.am.dzenlyst.ui.screens.Coach

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.dzenlyst.ai.GeminiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.*
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

    fun askGemini() {
        viewModelScope.launch {
            isLoading = true
            prompt = ""
            try {
                result = repo.ask(prompt)
            } catch (e: Exception) {
                result = "Error: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }

}