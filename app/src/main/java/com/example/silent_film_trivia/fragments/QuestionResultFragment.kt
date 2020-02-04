package com.example.silent_film_trivia.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.api.SilentFilmTriviaApi
import com.example.silent_film_trivia.models.GiphyResponse
import com.example.silent_film_trivia.models.Question
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_choice_result.*
import kotlinx.android.synthetic.main.fragment_choice_result.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionResultFragment : BaseFragment() {

    val infoHandler: Handler = Handler()
    var infoRunnable: Runnable? = null

    val delayOffset: Long = 4000


    private lateinit var mTxtResult: TextView
    private lateinit var mTxtInfp: TextView
    private lateinit var mBtnNxt: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_choice_result, container, false)
        mTxtResult = view.Txt_Result_Container
        mTxtInfp = view.Txt_info_container
        mBtnNxt = view.Btn_nxtQuestion

        mBtnNxt.apply {
            isEnabled = false
            setOnClickListener { mListener?.onNextQuestion() }
        }

        arguments?.getParcelable<Question>(Constants.CURRENT_QUESTION)
            ?.let { initViewHandlers(it, view) }
        return view
    }

    private fun initViewHandlers(q: Question, view: View) {
        mTxtResult.text = if (q.isCorrect) "Correct!" else "Incorrect"
        mTxtInfp.text = q.info
        infoRunnable = Runnable {
            loadGif(q.giphyId, view)
            animateText()
        }
        infoHandler.postDelayed(infoRunnable, delayOffset)

    }

    private fun animateText() {
        mTxtResult.animate().alpha(0.0f).setDuration(1000)
        mTxtInfp.animate().alpha(1.0f).setDuration(2000)
        
        mBtnNxt.apply {
            isEnabled = true
            animate().alpha(1.0f).setDuration(2000)
        }
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
        super.onDestroy()
    }
}