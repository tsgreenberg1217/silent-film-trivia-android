package com.example.silent_film_trivia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.Utils.Constants
import com.example.silent_film_trivia.models.Question
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    val choicesBtnArray: ArrayList<MaterialButton> = ArrayList()
    var mAnswer: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_question, container, false)

        choicesBtnArray.add(Btn_choice_a)
        choicesBtnArray.add(Btn_choice_b)
        choicesBtnArray.add(Btn_choice_c)
        choicesBtnArray.add(Btn_choice_d)

        return view
    }

    override fun onStart() {
        super.onStart()
        arguments?.getParcelable<Question>(Constants.CURRENT_QUESTION)?.let {
            Txt_prompt.setText(it.prompt)
            mAnswer = it.answer
            it.choices.forEach { choice ->
                choicesBtnArray.forEach { btn ->
                    btn.setOnClickListener { compareChoice(choice) }
                }
            }
        }
    }

    fun compareChoice(choice: String) {
        val isCorrect = mAnswer.equals(choice)
    }
}