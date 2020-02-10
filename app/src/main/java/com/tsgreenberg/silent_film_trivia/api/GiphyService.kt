package com.tsgreenberg.silent_film_trivia.api

import com.tsgreenberg.silent_film_trivia.BuildConfig
import com.tsgreenberg.silent_film_trivia.R
import com.tsgreenberg.silent_film_trivia.SilentFilmTriviaApplication
import com.tsgreenberg.silent_film_trivia.models.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyService {
    @GET("${BuildConfig.GIPHY_URL}gifs/{id}")
    suspend fun getGiph(
        @Path("id") id: String, @Query("api_key") key: String = SilentFilmTriviaApplication.appResources.getString(
            R.string.giphy_api_key
        )
    ): GiphyResponse

}