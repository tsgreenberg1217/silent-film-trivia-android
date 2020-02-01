package com.example.silent_film_trivia

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.silent_film_trivia.Utils.SharedPreferencesManager
import com.example.silent_film_trivia.db.SilentFilmTriviaDatabase

class SilentFilmTriviaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            SilentFilmTriviaDatabase::class.java,
            "silent-film-trivia-db"
        ).build()

        prefsManager = SharedPreferencesManager.getInstance(this)

    }

    companion object {
        lateinit var database: SilentFilmTriviaDatabase

        lateinit var prefsManager: SharedPreferencesManager
    }

}