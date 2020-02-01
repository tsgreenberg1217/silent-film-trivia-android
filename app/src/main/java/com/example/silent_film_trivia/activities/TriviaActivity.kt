package com.example.silent_film_trivia.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.Utils.FragmentUtils
import com.example.silent_film_trivia.fragments.QuestionFragment
import com.example.silent_film_trivia.models.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaActivity : AppCompatActivity() {

    val mQuestions: ArrayList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
        initTrivia(SilentFilmTriviaApplication.prefsManager.getSessionId())
    }

    private fun initTrivia(sessionId: Long) = lifecycleScope.launch(Dispatchers.IO) {
        SilentFilmTriviaApplication.database.sessionDao().getSession(sessionId).also { session ->
            launch(Dispatchers.Main) {
                mQuestions.addAll(session.questions)
                askNextQuestionOrEndGame()
            }
        }
    }

    fun askNextQuestionOrEndGame() = mQuestions.forEach { question ->
        if (!question.isAnswered) {
            askQuestion(question)
            return
        }
        goToEnd()
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
        FragmentUtils.replaceFragment(supportFragmentManager, questionFragment, R.id.session_layout)

    }
}
