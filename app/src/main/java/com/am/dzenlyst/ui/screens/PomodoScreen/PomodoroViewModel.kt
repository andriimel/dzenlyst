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
import com.am.dzenlyst.data.datastore.PomodoroPreferencesManager
import com.am.dzenlyst.data.local.focus.FocusSessionEntity
import com.am.dzenlyst.data.local.focus.FocusSessionRepository
import com.am.dzenlyst.data.local.task.TaskDao
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.data.local.task.TaskRepository
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.PomodoroMode
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.PomodoroModes
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.getPomodoroModeByType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import kotlin.getValue

@HiltViewModel
class PomodoroViewModel @Inject constructor(app : Application,
                                            private val preferencesManager: PomodoroPreferencesManager,
    private val focusSessionRepository: FocusSessionRepository) : AndroidViewModel(app) {
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

    private val _selectMode = mutableStateOf(PomodoroModes.first())
    val selectMode : State<PomodoroMode> = _selectMode

    private var timerJob: Job? = null

    private val context = app.applicationContext
    private val mediaPlayer by lazy {
        MediaPlayer.create(context, R.raw.alarm_beep)
    }

    fun setMode(mode: PomodoroMode){
        _selectMode.value = mode
        resetTimer()
    }

    private fun playSound() {
        Log.d("Pomodoro !!!!", "Sound is playing !!")
        mediaPlayer.start()
    }

    init {
        viewModelScope.launch {
            preferencesManager.selectedModeFlow.collect { savedModeType ->
                if (savedModeType != null) {
                    val mode = getPomodoroModeByType(savedModeType)
                    _selectMode.value = mode
                    resetTimer()
                }
            }
        }
    }
    fun onModeSelected(mode: PomodoroMode) {
        _selectMode.value = mode
        resetTimer()
        viewModelScope.launch {
            preferencesManager.saveSelectedMode(mode.type)
        }
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

        totalSeconds = _selectMode.value.workDuration * 60
        _timeLeft.value = formatTime(totalSeconds)
        _progress.value = 1f
    }


    private fun formatTime(seconds: Int): String{
        val m = seconds/60
        val s = seconds % 60
        return "%02d:%02d".format(m, s)
    }

    private fun advancePhase() {
        if (_phase.value == PomodoroPhase.Work) {
            _completedWorkSession.value++
            saveSessionToDb()
        }

        _phase.value = when (_phase.value) {
            PomodoroPhase.Work -> {
                if (_completedWorkSession.value % 4 == 0)
                    PomodoroPhase.LongBreak
                else
                    PomodoroPhase.ShortBreak
            }
            PomodoroPhase.ShortBreak,
            PomodoroPhase.LongBreak -> PomodoroPhase.Work
        }

        totalSeconds = when (_phase.value) {
            PomodoroPhase.Work -> _selectMode.value.workDuration * 60
            PomodoroPhase.ShortBreak -> _selectMode.value.shortBreak * 60
            PomodoroPhase.LongBreak -> _selectMode.value.longBreak * 60
        }

        _timeLeft.value = formatTime(totalSeconds)
        _progress.value = 1f
    }
    private fun saveSessionToDb(){
        viewModelScope.launch {
            val today = LocalDate.now().toString()
            val exsiting =  focusSessionRepository.getByDate(today)

            if (exsiting != null) {

            val updated  = exsiting.copy(sessionCount = exsiting.sessionCount + 1)
            focusSessionRepository.update(updated)
            } else {

            focusSessionRepository.insert(FocusSessionEntity(date = today, sessionCount = 1))}
        }
    }


}