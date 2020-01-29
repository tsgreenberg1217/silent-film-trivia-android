package com.example.silent_film_trivia.api

import com.example.silent_film_trivia.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SilentFilmTriviaApi {
    companion object {
        val service: SilentFilmTriviaService by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(SilentFilmTriviaService::class.java)
        }
    }
}