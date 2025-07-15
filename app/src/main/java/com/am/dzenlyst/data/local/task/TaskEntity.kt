package com.am.dzenlyst.data.local.task

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val text: String,
    val isDone: Boolean,
    val priority: TaskPriority = TaskPriority.Normal
)