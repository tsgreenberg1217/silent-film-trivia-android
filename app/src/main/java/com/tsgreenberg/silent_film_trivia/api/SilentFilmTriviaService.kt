package com.tsgreenberg.silent_film_trivia.api

import com.tsgreenberg.silent_film_trivia.BuildConfig
import com.tsgreenberg.silent_film_trivia.models.Question
import retrofit2.http.GET

interface SilentFilmTriviaService {
    @GET("${BuildConfig.BASE_URL}questions")
    suspend fun getQuestions(): ArrayList<Question>
}