package com.am.dzenlyst.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.am.dzenlyst.data.local.focus.FocusSessionDao
import com.am.dzenlyst.data.local.focus.FocusSessionEntity
import com.am.dzenlyst.data.local.task.Converters
import com.am.dzenlyst.data.local.task.Subtasks.SubtaskDao
import com.am.dzenlyst.data.local.task.Subtasks.SubtaskEntity
import com.am.dzenlyst.data.local.task.TaskDao
import com.am.dzenlyst.data.local.task.TaskEntity

@Database(entities = [
    TaskEntity::class,
    SubtaskEntity :: class,
    FocusSessionEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun focusSessionDao(): FocusSessionDao
    abstract fun subtaskDao(): SubtaskDao

}