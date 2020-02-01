package com.example.silent_film_trivia.Utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("ApplySharedPref")
class SharedPreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("sft-prefs", Application.MODE_PRIVATE)

    fun setSessionId(sessionId: Long) =
        prefs.edit().putLong(Constants.CURRENT_SESSION_ID, sessionId).commit()

    fun getSessionId(): Long = prefs.getLong(Constants.CURRENT_SESSION_ID, -1)

    companion object {
        private var instance: SharedPreferencesManager? = null

        fun getInstance(context: Context): SharedPreferencesManager {
            if (instance == null) {
                instance = SharedPreferencesManager(context)
            }
            return instance!!
        }
    }
}