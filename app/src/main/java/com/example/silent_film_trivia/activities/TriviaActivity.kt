package com.example.silent_film_trivia.activities

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaActivity : BaseActivity(), SessionFragmentListener {

    private val mQuestions: ArrayList<Question> = ArrayList()

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

    fun askNextQuestionOrEndGame(){
        for(question:Question in mQuestions){
            if (!question.isAnswered) {
                askQuestion(question)
                return

            }
        }
        goToEnd()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun goToEnd() {
        SilentFilmTriviaApplication.prefsManager.setSessionId(-1)
        finish()
    }

    private fun askQuestion(question: Question) {
        replaceSessionFragment(QuestionFragment(), Constants.CURRENT_QUESTION, question)
    }

    override fun onQuestionAnswred(question: Question) {
        replaceSessionFragment(QuestionResultFragment(), Constants.CURRENT_QUESTION, question)
        lifecycleScope.launch(Dispatchers.IO) {
            SilentFilmTriviaApplication.database.sessionDao().updateQuestions(
                SilentFilmTriviaApplication.prefsManager.getSessionId(),
                mQuestions
            )
        }
    }

    private fun replaceSessionFragment(fragment: Fragment, key: String, parcel: Parcelable) {
        fragment.arguments = Bundle().apply {
            putParcelable(key, parcel)
        }
        FragmentUtils.replaceFragment(
            supportFragmentManager,
            fragment,
            R.id.session_layout
        )
    }

    override fun onNextQuestion() {
        askNextQuestionOrEndGame()
    }
}
