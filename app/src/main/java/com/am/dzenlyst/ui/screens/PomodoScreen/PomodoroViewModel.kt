package com.am.dzenlyst.ui.screens.PomodoScreen

import com.am.dzenlyst.R
import android.app.Application
import android.media.MediaPlayer
import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@HiltViewModel
class PomodoroViewModel @Inject constructor(app : Application) : AndroidViewModel(app) {
    private val _timeLeft = MutableStateFlow("25:00")
    val timeLeft : StateFlow<String> = _timeLeft

    private val _progress = MutableStateFlow(1f)
    val progress: StateFlow<Float> = _progress

    private var initialSeconds = 25*60
    private var totalSeconds = initialSeconds
    private var isRunning = false



    private val context = app.applicationContext
    private val mediaPlayer by lazy {
        MediaPlayer.create(context, R.raw.alarm_beep)
    }

    private fun playSound() {
        Log.d("Pomodoro !!!!", "Sound is playing !!")
        mediaPlayer.start()
    }

    fun startTimer() {
        if (isRunning) return
        isRunning = true

        viewModelScope.launch {
            while (totalSeconds > 0 && isRunning ){
                delay(1000L)
                totalSeconds--
                _timeLeft.value = formatTime(totalSeconds)
                _progress.value = totalSeconds / initialSeconds.toFloat()
            }

            if(totalSeconds == 0) {

            isRunning = false
                playSound()
            }
        }
    }

    fun pauseTimer(){
        isRunning = false
    }

    fun resetTimer(){
        isRunning = false
        totalSeconds = 25 * 60
        _timeLeft.value = "25:00"
        _progress.value = 1f
    }

    private fun formatTime(seconds: Int): String{
        val m = seconds/60
        val s = seconds % 60
        return "%02d:%02d".format(m, s)
    }

}