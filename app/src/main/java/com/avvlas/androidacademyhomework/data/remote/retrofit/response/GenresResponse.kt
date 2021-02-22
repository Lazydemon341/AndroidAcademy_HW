package com.avvlas.androidacademyhomework.data.remote.retrofit.response

import com.squareup.moshi.Json

data class GenresResponse(
    @Json(name = "genres")
    val genres: List<GenreResponse>
)