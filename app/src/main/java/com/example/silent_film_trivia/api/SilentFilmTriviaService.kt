package com.example.silent_film_trivia.api

import com.example.silent_film_trivia.BuildConfig
import com.example.silent_film_trivia.R
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.models.Question
import retrofit2.http.GET
import retrofit2.http.Path

interface SilentFilmTriviaService {
    @GET("${BuildConfig.BASE_URL}/questions")
    suspend fun getQuestions(): ArrayList<Question>


    @GET("${BuildConfig.GIPHY_URL}/gifs/{id}?api_key={key}")
    suspend fun getGiph(
        @Path("id") id: String, @Path("key") key: String = SilentFilmTriviaApplication.appResources.getString(
            R.string.giphy_api_key
        )
    )
}