package com.tsgreenberg.silent_film_trivia.models

import com.google.gson.annotations.Expose

data class GiphyResponse(val data: GiphyData)

data class GiphyData(@Expose val images: GiphyImagesPacket)

data class GiphyImagesPacket(@Expose val downsized: GiphyImageDownsized)

data class GiphyImageDownsized(
    @Expose val height: String,
    @Expose val size: String,
    @Expose val url: String,
    @Expose val width: String
)