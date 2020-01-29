package com.example.silent_film_trivia.api

import com.example.silent_film_trivia.models.Question
import retrofit2.http.GET

interface SilentFilmTriviaService{
    @GET("/questions")
    suspend fun getQuestions():ArrayList<Question>
}