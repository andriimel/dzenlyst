package com.am.dzenlyst.data.local.task

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromPriority(priority: TaskPriority): String = priority.name
    @TypeConverter
    fun toPriority(value: String): TaskPriority = TaskPriority.valueOf(value)
}