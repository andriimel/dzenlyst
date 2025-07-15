package com.am.dzenlyst.data.task

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao
}