package com.tsgreenberg.silent_film_trivia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tsgreenberg.silent_film_trivia.R
import com.tsgreenberg.silent_film_trivia.Utils.Constants
import com.tsgreenberg.silent_film_trivia.models.Question
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_question.view.*

class QuestionFragment : BaseFragment() {


    val choicesBtnArray: ArrayList<MaterialButton> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_question, container, false)

        choicesBtnArray.add(view.Btn_choice_a)
        choicesBtnArray.add(view.Btn_choice_b)
        choicesBtnArray.add(view.Btn_choice_c)
        choicesBtnArray.add(view.Btn_choice_d)

        arguments?.getParcelable<Question>(Constants.CURRENT_QUESTION)?.let { q ->
            view.Txt_prompt.text = q.prompt
            q.choices.shuffle()
            q.choices.forEachIndexed { index, choice ->
                choicesBtnArray[index].apply {
                    append(choice)
                    setOnClickListener {
                        sendChoiceResult(q, q.answer == choice)
                    }
                }
            }
        }

        return view
    }

    fun sendChoiceResult(q: Question, isCorrect: Boolean) {
        q.isCorrect = isCorrect
        q.isAnswered = true
        mListener?.onQuestionAnswred(q)
    }
}

