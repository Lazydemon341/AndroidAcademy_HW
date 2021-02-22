package com.avvlas.androidacademyhomework.data.remote.retrofit.response

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)