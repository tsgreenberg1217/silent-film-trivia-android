package com.example.silent_film_trivia.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.api.SilentFilmTriviaApi
import com.example.silent_film_trivia.models.Question
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txt = Txt_msg

        GlobalScope.launch(Dispatchers.IO) {
            val questions: ArrayList<Question> = SilentFilmTriviaApi.service.getQuestions()
            val question: Question = questions.get(0)
            showQuestion(question.prompt)
        }


    }

    fun showQuestion(prompt: String) {
        GlobalScope.launch(Dispatchers.Main) {
            txt.setText(prompt)
        }
    }
}
