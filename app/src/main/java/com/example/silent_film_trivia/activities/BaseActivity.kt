package com.example.silent_film_trivia.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.Utils.LogingUtils

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogingUtils.log("onCreate- ${this::class.java.canonicalName}")
    }

     fun goToGameIfInProgress() {
        val id = SilentFilmTriviaApplication.prefsManager.getSessionId()
        if (id > -1) {
            val intent = Intent(this, TriviaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        LogingUtils.log("onResume- ${this::class.java.canonicalName}")
    }

    override fun onStop() {
        LogingUtils.log("onStop- ${this::class.java.canonicalName}")
        super.onStop()
    }

    override fun onDestroy() {
        LogingUtils.log("onDestroy- ${this::class.java.canonicalName}")
        super.onDestroy()
    }
}