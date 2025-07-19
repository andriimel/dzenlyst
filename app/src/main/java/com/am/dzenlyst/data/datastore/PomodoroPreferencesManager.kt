package com.am.dzenlyst.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.PomodoroMode
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroTypes.PomodoroModeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_settings")

class PomodoroPreferencesManager(private val context: Context) {
    companion object{
        private val SELECTED_MODE_KEY  = stringPreferencesKey("selected_pomodoro_mode")
    }
    val selectedModeFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[SELECTED_MODE_KEY]
    }

    suspend fun saveSelectedMode(mode: PomodoroModeType){
        context.dataStore.edit { preferences ->
            preferences[SELECTED_MODE_KEY] = mode.name

        }
    }
}