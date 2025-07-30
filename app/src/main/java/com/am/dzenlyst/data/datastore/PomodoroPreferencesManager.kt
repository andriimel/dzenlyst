package com.am.dzenlyst.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.PomodoroModeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey

val Context.dataStore by preferencesDataStore(name = "user_settings")

class PomodoroPreferencesManager(private val context: Context) {
    companion object{
        private val SELECTED_MODE_KEY  = stringPreferencesKey("selected_pomodoro_mode")
        private val COMPLETED_TASKS_COUNT_KEY = intPreferencesKey("completed_tasks_count")
    }
    val selectedModeFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[SELECTED_MODE_KEY]
    }

    val completedTasksCountFlow: Flow<Int> = context.dataStore.data.map{preferences ->
    preferences[COMPLETED_TASKS_COUNT_KEY] ?: 0}

    suspend fun saveSelectedMode(mode: PomodoroModeType){
        context.dataStore.edit { preferences ->
            preferences[SELECTED_MODE_KEY] = mode.name

        }
    }

    suspend fun incrementComplitedTasks(){
        context.dataStore.edit { preferences ->
            val currentCount = preferences[COMPLETED_TASKS_COUNT_KEY] ?: 0
            preferences[COMPLETED_TASKS_COUNT_KEY] = currentCount + 1
        }
    }
}