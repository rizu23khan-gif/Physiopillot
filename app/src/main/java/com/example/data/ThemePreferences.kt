package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "physiopilot_settings")

class ThemePreferences(private val context: Context) {
    companion object {
        val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
    }

    val themeModeFlow: Flow<AppTheme> = context.dataStore.data.map { preferences ->
        val themeString = preferences[THEME_MODE_KEY] ?: AppTheme.SYSTEM.name
        try {
            AppTheme.valueOf(themeString)
        } catch (e: IllegalArgumentException) {
            AppTheme.SYSTEM
        }
    }

    suspend fun saveThemeMode(theme: AppTheme) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = theme.name
        }
    }
}
