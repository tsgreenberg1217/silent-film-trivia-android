package com.example.silent_film_trivia.fragments

import android.os.Bundle
import android.os.Handler
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.api.SilentFilmTriviaApi
import com.example.silent_film_trivia.models.GiphyResponse
import com.example.silent_film_trivia.models.Question
import kotlinx.android.synthetic.main.fragment_choice_result.*
import kotlinx.android.synthetic.main.fragment_choice_result.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionResultFragment : BaseFragment() {

    val infoHandler: Handler = Handler()
    var infoRunnable: Runnable? = null

    val nextQuestionHander: Handler = Handler()
    var nextQuestionRunnable: Runnable? = null

    val delayOffset: Long = 4000


    private lateinit var mTxtResult: TextView
    private lateinit var mTxtInfp: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_choice_result, container, false)
        mTxtResult = view.Txt_Result_Container
        mTxtInfp = view.Txt_info_container
        arguments?.getParcelable<Question>(Constants.CURRENT_QUESTION)
            ?.let { initViewHandlers(it, view) }
        return view
    }

    private fun initViewHandlers(q: Question, view: View) {
        mTxtResult.text = if (q.isCorrect) "Correct!" else "Incorrect"
        mTxtInfp.text = q.info
        infoRunnable = Runnable {
            mTxtResult.visibility = View.GONE
            mTxtInfp.visibility = View.VISIBLE
            loadGif(q.giphyId, view)
            animateText(q.info)
        }
        infoHandler.postDelayed(infoRunnable, delayOffset)

        nextQuestionRunnable = Runnable { mListener?.onNextQuestion() }
        nextQuestionHander.postDelayed(nextQuestionRunnable, delayOffset * 2)


    }

    private fun animateText(info: String) {
        var cSet = ConstraintSet().apply {
            clone(Result_layout)
            connect(
                R.id.Txt_Result_Container,
                ConstraintSet.TOP,
                R.id.mid_guideline,
                ConstraintSet.BOTTOM,
                0
            )
        }

        var cSet2 = ConstraintSet().apply {
            clone(Result_layout)
            connect(
                R.id.Txt_info_container,
                ConstraintSet.TOP,
                R.id.mid_guideline,
                ConstraintSet.BOTTOM,
                0
            )
        }




        TransitionManager.beginDelayedTransition(Result_layout, AutoTransition().apply {
            duration = 2000
        })

        cSet.applyTo(Result_layout)
        cSet2.applyTo(Result_layout)

    }

    private fun loadGif(giphyId: String, view: View) = lifecycleScope.launch(Dispatchers.IO) {
        val response: GiphyResponse = SilentFilmTriviaApi.giphyService.getGiph(giphyId)
        launch(Dispatchers.Main) {
            val url: String = response.data.images.downsized.url
            Glide.with(view).load(url).centerCrop().into(Movie_gif_container)
        }
    }

    override fun onDestroy() {
        infoHandler.removeCallbacks(infoRunnable)
        nextQuestionHander.removeCallbacks(nextQuestionRunnable)
        super.onDestroy()
    }
}