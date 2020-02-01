package com.example.silent_film_trivia.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.models.Question
import kotlinx.android.synthetic.main.fragment_choice_result.view.*

class QuestionResultFragment : BaseFragment() {

    val infoHandler: Handler = Handler()
    var infoRunnable: Runnable? = null

    val nextQuestionHander: Handler = Handler()
    var nextQuestionRunnable: Runnable? = null

    val delayOffset: Long = 4000


    private lateinit var mTxtContainer: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_choice_result, container, false)
        mTxtContainer = view.Txt_container
        arguments?.getParcelable<Question>(Constants.CURRENT_QUESTION)?.let { initViewHandlers(it) }
        return view
    }

    private fun initViewHandlers(q: Question) {
        mTxtContainer.text = if (q.isCorrect) "Correct!" else "Incorect"

        infoRunnable = Runnable { mTxtContainer.text = q.info }
        infoHandler.postDelayed(infoRunnable, delayOffset)

        nextQuestionRunnable = Runnable { mListener?.onNextQuestion() }
        nextQuestionHander.postDelayed(nextQuestionRunnable, delayOffset * 2)
    }

    override fun onDestroy() {
        infoHandler.removeCallbacks(infoRunnable)
        nextQuestionHander.removeCallbacks(nextQuestionRunnable)
        super.onDestroy()
    }
}