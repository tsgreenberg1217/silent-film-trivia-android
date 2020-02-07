package com.example.silent_film_trivia.activities

import android.app.Dialog
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
        if (id > -1) {
            val intent = Intent(this, TriviaActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}