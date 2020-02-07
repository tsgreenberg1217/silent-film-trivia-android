package com.example.silent_film_trivia.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.api.SilentFilmTriviaApi
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
