package com.tsgreenberg.silent_film_trivia.activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.tsgreenberg.silent_film_trivia.R
import com.tsgreenberg.silent_film_trivia.SilentFilmTriviaApplication
import com.tsgreenberg.silent_film_trivia.Utils.LocalQuestions
import com.tsgreenberg.silent_film_trivia.api.SilentFilmTriviaApi
import com.tsgreenberg.silent_film_trivia.models.Question
import com.tsgreenberg.silent_film_trivia.models.Session
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    val titleAnimationHandler: Handler = Handler()

    private lateinit var mTitleAimation: LottieAnimationView

    val titleAnimationRunnable: Runnable = Runnable {
        mTitleAimation.playAnimation()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mTitleAimation = createTitleAnimation()
        Btn_start.setOnClickListener { createAndStartTriviaSession() }
    }


    fun createAndStartTriviaSession() = lifecycleScope.launch(Dispatchers.IO) {
        //        val questions: ArrayList<Question> = SilentFilmTriviaApi.service.getQuestions()
        val questions = LocalQuestions.create()
        val session = Session(System.currentTimeMillis(), questions)
        val sessionId = SilentFilmTriviaApplication.database.sessionDao().insert(session)
        SilentFilmTriviaApplication.prefsManager.setSessionId(sessionId)
        launch(Dispatchers.Main) {
            Intent(this@HomeActivity, TriviaActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.also { startActivity(it) }
            finish()
        }
    }

    private fun createTitleAnimation(): LottieAnimationView = Title_Lottie.apply {
        playAnimation()
        addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                titleAnimationHandler.postDelayed(titleAnimationRunnable, 5000)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    override fun onDestroy() {
        titleAnimationHandler.removeCallbacks(titleAnimationRunnable)
        super.onDestroy()
    }
}
