package com.am.dzenlyst.data.local.task

import com.am.dzenlyst.data.local.task.Subtasks.SubtaskDao
import com.am.dzenlyst.data.local.task.Subtasks.SubtaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao,
    private val subtaskDao: SubtaskDao
) {

    val allTasks: Flow<List<TaskEntity>> = dao.getAllTasks()
    //val completedTaskCount: Flow<Int> = dao.getCompletedTask()
    val topTasks: Flow<List<TaskEntity>> = dao.getActiveTask()

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


    fun getSubtasksForTask(taskId: Int): Flow<List<SubtaskEntity>> =
        subtaskDao.getSubtasksForTask(taskId)
    suspend fun insertSubtask(subtask: SubtaskEntity) = subtaskDao.insertSubtask(subtask)
    suspend fun updateSubtask(subtask: SubtaskEntity) = subtaskDao.updateSubtask(subtask)
    suspend fun deleteSubtask(subtask: SubtaskEntity) = subtaskDao.deleteSubtask(subtask)

}