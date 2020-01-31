package com.example.silent_film_trivia.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.Utils.FragmentUtils
import com.example.silent_film_trivia.fragments.QuestionFragment
import com.example.silent_film_trivia.models.Question
import com.example.silent_film_trivia.viewmodels.SessionViewModel

class TriviaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
        val sessionViewmodel: SessionViewModel by viewModels()
        sessionViewmodel.sessionId = SilentFilmTriviaApplication.prefsManager.getSessionId()
        sessionViewmodel.questions.observe(this, Observer<ArrayList<Question>> { questions ->
            questions.forEach { question ->
                if (!question.isAnswered) {
                    askQuestion(question)
                    return@Observer
                }
            }
            goToEnd()
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun goToEnd() {

    }

    private fun askQuestion(question: Question) {
        val questionFragment = QuestionFragment()
        questionFragment.arguments = Bundle().apply {
            putParcelable(Constants.CURRENT_QUESTION, question)
        }
        FragmentUtils.addFragment(supportFragmentManager, questionFragment, R.id.session_layout)

    }
}
