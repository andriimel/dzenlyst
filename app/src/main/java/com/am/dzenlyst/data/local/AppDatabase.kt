package com.am.dzenlyst.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.am.dzenlyst.data.local.focus.FocusSessionDao
import com.am.dzenlyst.data.local.focus.FocusSessionEntity
import com.am.dzenlyst.data.local.task.TaskDao
import com.am.dzenlyst.data.local.task.TaskEntity

@Database(entities = [TaskEntity::class,
    FocusSessionEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun focusSessionDao(): FocusSessionDao

}