package com.tsgreenberg.silent_film_trivia.api

import com.tsgreenberg.silent_film_trivia.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SilentFilmTriviaApi {
    companion object {
        val service: SilentFilmTriviaService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(SilentFilmTriviaService::class.java)
        }

        val giphyService: GiphyService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Retrofit.Builder()
                .baseUrl(BuildConfig.GIPHY_URL)
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(GiphyService::class.java)
        }

        val okhttpClient: OkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpClient().newBuilder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                })
            }.build()
        }
    }

}