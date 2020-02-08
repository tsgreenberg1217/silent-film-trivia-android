package com.example.silent_film_trivia.activities

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.Utils.FragmentUtils
import com.example.silent_film_trivia.Utils.LogingUtils
import com.example.silent_film_trivia.fragments.QuestionResultFragment
import com.example.silent_film_trivia.fragments.QuestionFragment
import com.example.silent_film_trivia.interfaces.SessionFragmentListener
import com.example.silent_film_trivia.models.Question
import com.example.silent_film_trivia.models.SessionResult
import kotlinx.coroutines.*

class TriviaActivity : BaseActivity(), SessionFragmentListener {

    private val mQuestions: MutableList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)
        initTrivia(SilentFilmTriviaApplication.prefsManager.getSessionId())
    }

    private fun initTrivia(sessionId: Long) = lifecycleScope.launch(Dispatchers.IO) {
        SilentFilmTriviaApplication.database.sessionDao().getSession(sessionId).also { session ->
            launch(Dispatchers.Main) {
                session.questions.shuffle()
                mQuestions.addAll(session.questions)
                askNextQuestionOrEndGame()
            }
        }
    }

    private fun askNextQuestionOrEndGame() {
        for (question: Question in mQuestions) {
            if (!question.isAnswered) {
                askQuestion(question)
                return

            }
        }
        goToEnd()
    }


    private fun goToEnd() {
        val id = SilentFilmTriviaApplication.prefsManager.getSessionId()
        SilentFilmTriviaApplication.prefsManager.setSessionId(-1)
        val result = SessionResult(mQuestions)

        GlobalScope.async {
            SilentFilmTriviaApplication.database.sessionResultDao()
                .insertResultAndDeleteSession(id, result)
        }
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(homeIntent)
        finish()
    }

    private fun askQuestion(question: Question) {
        replaceSessionFragment(QuestionFragment(), Constants.CURRENT_QUESTION, question)
    }

    override fun onQuestionAnswred(question: Question) {
        replaceSessionFragment(QuestionResultFragment(), Constants.CURRENT_QUESTION, question, false)
        lifecycleScope.launch(Dispatchers.IO) {
            SilentFilmTriviaApplication.database.sessionDao().updateQuestions(
                SilentFilmTriviaApplication.prefsManager.getSessionId(),
                mQuestions
            )
        }
    }

    private fun replaceSessionFragment(fragment: Fragment, key: String, parcel: Parcelable, animate:Boolean = true) {
        fragment.arguments = Bundle().apply {
            putParcelable(key, parcel)
        }
        FragmentUtils.replaceFragment(
            supportFragmentManager,
            fragment,
            R.id.session_layout,
            animate
        )
    }

    override fun onNextQuestion() {
        askNextQuestionOrEndGame()
    }
}
