package com.am.dzenlyst.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.am.dzenlyst.ai.GeminiRepository
import com.am.dzenlyst.ai.GeminiService
import com.am.dzenlyst.data.datastore.PomodoroPreferencesManager
import com.am.dzenlyst.data.local.focus.FocusSessionDao
import com.am.dzenlyst.data.local.focus.FocusSessionRepository
import com.am.dzenlyst.data.local.AppDatabase
import com.am.dzenlyst.data.local.task.TaskDao
import com.am.dzenlyst.data.local.task.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun  providesFocusSessionsDao(db : AppDatabase): FocusSessionDao = db.focusSessionDao()

    @Provides
    fun provideFocusSessionRepository(dao : FocusSessionDao): FocusSessionRepository =
        FocusSessionRepository(dao)

    @Provides
    @Singleton
    fun provideGeminiService(): GeminiService = GeminiService()

    @Provides
    @Singleton
    fun provideGeminiRepository(
        service: GeminiService
    ): GeminiRepository = GeminiRepository(service)

    @Provides
    @Singleton
    fun providePomodoroPreferencesManager(@ApplicationContext context: Context): PomodoroPreferencesManager {
        return PomodoroPreferencesManager(context)
    }

}