package com.am.dzenlyst.ui.screens.Tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.dzenlyst.data.datastore.PomodoroPreferencesManager
import com.am.dzenlyst.data.local.task.Subtasks.SubtaskEntity
import com.am.dzenlyst.data.local.task.TaskEntity
import com.am.dzenlyst.data.local.task.TaskPriority
import com.am.dzenlyst.data.local.task.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val preferencesManager: PomodoroPreferencesManager
) : ViewModel() {



    val tasks: StateFlow<List<TaskEntity>> = repository.allTasks.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList())
    val topTasks: StateFlow<List<TaskEntity>> = repository.topTasks.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val _subtasks = MutableStateFlow<List<SubtaskEntity>>(emptyList())
    val subtasks: StateFlow<List<SubtaskEntity>> = _subtasks

    fun  addTask( text: String, priority: TaskPriority = TaskPriority.Normal){
        viewModelScope.launch {
            repository.addTask(text, priority)
        }
    }

    fun  toggleDone( task: TaskEntity) {
        viewModelScope.launch {
            val currentTasks = tasks.value
            val updateTasks = currentTasks.map {
                when{
                    it.id == task.id -> it.copy(isDone = true)
                    it.isDone -> it.copy(isDone =  false)
                    else -> it
                }
            }
            updateTasks.forEach { repository.updateTask(it) }
        }
    }
    fun incrementConfirmedProjects(){

        viewModelScope.launch {
            preferencesManager.incrementComplitedTasks()
        }
    }
    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {

            repository.deleteTask(task)
        }
    }

    fun updateSubtask(task: TaskEntity) {
        viewModelScope.launch {

            repository.updateTask(task)
        }
    }

    // Subtasks

    fun getSubtasksForTask(taskId: Int){
        viewModelScope.launch {
            repository.getSubtasksForTask(taskId).collect { list ->
                _subtasks.value = list
            }
        }
    }


    fun addSubtask(taskId:Int, text: String){
        viewModelScope.launch {
            val subtask = SubtaskEntity(taskId = taskId, text = text)
            repository.insertSubtask(subtask)
        }
    }

    fun toggleSubtask(subtask : SubtaskEntity) {
        viewModelScope.launch {
            repository.updateSubtask(subtask.copy(isDone = !subtask.isDone))
        }
    }
    fun deleteSubtask(subtask: SubtaskEntity) {
        viewModelScope.launch {
            repository.deleteSubtask(subtask)
        }
    }
}