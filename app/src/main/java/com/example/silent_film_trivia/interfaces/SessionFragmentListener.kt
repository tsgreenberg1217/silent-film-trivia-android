package com.example.silent_film_trivia.interfaces

import com.example.silent_film_trivia.models.QuestionResult

interface SessionFragmentListener {
    fun onQuestionAnswred(result:QuestionResult)
    fun onNextQuestion()
}