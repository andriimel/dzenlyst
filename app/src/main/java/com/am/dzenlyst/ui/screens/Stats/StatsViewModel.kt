package com.am.dzenlyst.ui.screens.Stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.dzenlyst.data.local.focus.FocusSessionDao
import com.am.dzenlyst.data.local.focus.FocusSessionEntity
import com.am.dzenlyst.data.local.focus.FocusSessionRepository
import com.am.dzenlyst.data.local.task.TaskDao
import com.am.dzenlyst.data.local.task.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val focusSessionDao: FocusSessionDao,
    private val taskRepository: TaskRepository
) : ViewModel() {
    val completedTaskCount = taskRepository.completedTaskCount.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        0
    )

    val last7Sessions: StateFlow<List<FocusSessionEntity>> =
        focusSessionDao.getLast7Sessions()
            .map { sessions ->
                // ensure exactly 7 days (even if some days missing)
                val today = LocalDate.now()
                (0..6).map { offset ->
                    val date = today.minusDays(offset.toLong()).toString()
                    sessions.find { it.date == date } ?: FocusSessionEntity(date = date, sessionCount = 0)
                }.reversed() // Monday → Sunday order
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}