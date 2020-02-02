package com.example.silent_film_trivia.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.Utils.LogingUtils
import com.example.silent_film_trivia.api.SilentFilmTriviaApi
import com.example.silent_film_trivia.db.SilentFilmTriviaDatabase
import com.example.silent_film_trivia.models.GiphyResponse
import com.example.silent_film_trivia.models.Question
import com.example.silent_film_trivia.models.Session
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    lateinit var triviaIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        triviaIntent = Intent(this, TriviaActivity::class.java)
        Btn_start.setOnClickListener { createAndStartTriviaSession() }
        goToGameIfInProgress()
    }

    fun createAndStartTriviaSession() = lifecycleScope.launch(Dispatchers.IO) {
        val questions: ArrayList<Question> = SilentFilmTriviaApi.service.getQuestions()
        val session = Session(System.currentTimeMillis(), questions)
        val sessionId = SilentFilmTriviaApplication.database.sessionDao().insert(session)
        SilentFilmTriviaApplication.prefsManager.setSessionId(sessionId)
        launch(Dispatchers.Main) {
            triviaIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(triviaIntent)
            finish()
        }
    }
}
