package com.example.silent_film_trivia

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.silent_film_trivia.Utils.SharedPreferencesManager
import com.example.silent_film_trivia.db.SilentFilmTriviaDatabase

class SilentFilmTriviaApplication {

    companion object : Application() {
        val database: SilentFilmTriviaDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                this,
                SilentFilmTriviaDatabase::class.java,
                "silent-film-trivia-db"
            ).build()
        }

        val prefsManager: SharedPreferencesManager by lazy {
            SharedPreferencesManager.getInstance(this)
        }
    }

}