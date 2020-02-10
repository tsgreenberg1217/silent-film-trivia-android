package com.tsgreenberg.silent_film_trivia

import android.app.Application
import android.content.res.Resources
import androidx.room.Room
import com.tsgreenberg.silent_film_trivia.Utils.SharedPreferencesManager
import com.tsgreenberg.silent_film_trivia.db.SilentFilmTriviaDatabase

class SilentFilmTriviaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appResources = this.resources

        database = Room.databaseBuilder(
            this,
            SilentFilmTriviaDatabase::class.java,
            "silent-film-trivia-db"
        ).build()

        prefsManager = SharedPreferencesManager.getInstance(this)

    }

    companion object {
        lateinit var appResources: Resources
        lateinit var database: SilentFilmTriviaDatabase
        lateinit var prefsManager: SharedPreferencesManager
    }

}