package com.am.dzenlyst.di

import android.app.Application
import androidx.room.Room
import com.am.dzenlyst.data.task.AppDatabase
import com.am.dzenlyst.data.task.TaskDao
import com.am.dzenlyst.data.task.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "dzenlyst_db",
        ).build()
    }

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()

    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepository(dao)
}