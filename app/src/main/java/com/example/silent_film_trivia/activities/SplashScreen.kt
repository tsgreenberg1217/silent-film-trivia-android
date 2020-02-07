package com.example.silent_film_trivia.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silent_film_trivia.SilentFilmTriviaApplication

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToGameIfInProgress()
    }

    private fun goToGameIfInProgress() {
        val id = SilentFilmTriviaApplication.prefsManager.getSessionId()
        goToActivity(if (id > -1) TriviaActivity::class.java else HomeActivity::class.java)
        finish()
    }

    private fun <T> goToActivity(cls: Class<T>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
}