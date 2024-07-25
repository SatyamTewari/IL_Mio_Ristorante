package com.example.ilmioristorante.presentation.util

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(LOGIN_KEY, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGIN_KEY, false)
    }

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val LOGIN_KEY = "login_key"
    }
}
