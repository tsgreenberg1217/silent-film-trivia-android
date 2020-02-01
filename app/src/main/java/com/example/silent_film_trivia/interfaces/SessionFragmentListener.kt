package com.example.silent_film_trivia.interfaces

import com.example.silent_film_trivia.models.Question

interface SessionFragmentListener {
    fun onQuestionAnswred(question: Question)
    fun onNextQuestion()
}