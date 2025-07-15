package com.am.dzenlyst.data.local.task

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {

    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()
    val completedTaskCount: Flow<Int> = dao.getCompletedTask()
    val topTasks: Flow<List<TaskEntity>> = dao.getTop3UndoneTasks()

    suspend fun addTask(text: String, priority: TaskPriority) {
        val newTask = TaskEntity(text = text, isDone = false, priority = priority)
        dao.insertTask(newTask)
    }

    suspend fun updateTask(task: TaskEntity){
        dao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity){
        dao.deleteTask(task)
    }

    suspend fun toggleDone(task: TaskEntity) {
        val updated = task.copy(isDone = !task.isDone)
        dao.updateTask(updated)
    }
}