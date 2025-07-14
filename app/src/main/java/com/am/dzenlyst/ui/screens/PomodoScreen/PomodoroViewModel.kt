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
import androidx.compose.runtime.*
import kotlinx.coroutines.Job
import kotlin.getValue

@HiltViewModel
class PomodoroViewModel @Inject constructor(app : Application) : AndroidViewModel(app) {
    private val _timeLeft = MutableStateFlow("25:00")
    val timeLeft : StateFlow<String> = _timeLeft

    private val _progress = MutableStateFlow(1f)
    val progress: StateFlow<Float> = _progress

    private val _isRunningFlow = MutableStateFlow(false)
    val isRunningFlow: StateFlow<Boolean> = _isRunningFlow

    private var initialSeconds = 25*60
    private var totalSeconds = initialSeconds
    private var isRunning = false

    private val _phase = MutableStateFlow(PomodoroPhase.Work)
    val phase: StateFlow<PomodoroPhase> = _phase

    private val _completedWorkSession = MutableStateFlow(0)
    val completedWorkSession: StateFlow<Int> = _completedWorkSession

    private var timerJob: Job? = null

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
        _isRunningFlow.value = true

        timerJob = viewModelScope.launch {
            while (totalSeconds > 0 && isRunning ){
                delay(1000L)
                totalSeconds--
                _timeLeft.value = formatTime(totalSeconds)
                _progress.value = totalSeconds / initialSeconds.toFloat()
            }

            if(totalSeconds == 0) {

                isRunning = false
                _isRunningFlow.value = false
                playSound()
                advancePhase()
            }
        }
    }

    fun pauseTimer(){
        isRunning = false
        _isRunningFlow.value = false
    }

    fun resetTimer(){
        isRunning = false
        _isRunningFlow.value = false
        timerJob?.cancel()

        totalSeconds = 25 * 60
        _timeLeft.value = "25:00"
        _progress.value = 1f
    }

    private fun formatTime(seconds: Int): String{
        val m = seconds/60
        val s = seconds % 60
        return "%02d:%02d".format(m, s)
    }

    private fun advancePhase(){
        when(_phase.value) {
            PomodoroPhase.Work -> {
                _completedWorkSession.value = _completedWorkSession.value + 1

                _phase.value = if (_completedWorkSession.value % 4 == 0) {
                    PomodoroPhase.LongBreak
                } else {
                    PomodoroPhase.ShortBreak
                }
            }
            PomodoroPhase.ShortBreak,
            PomodoroPhase.LongBreak -> {
                _phase.value = PomodoroPhase.Work
            }
        }




        totalSeconds = when(_phase.value){
            PomodoroPhase.Work -> 25 * 60
            PomodoroPhase.LongBreak -> 15 * 60
            PomodoroPhase.ShortBreak -> 5 * 60

        }
        _timeLeft.value = formatTime(totalSeconds)
        _progress.value = 1f
}

}