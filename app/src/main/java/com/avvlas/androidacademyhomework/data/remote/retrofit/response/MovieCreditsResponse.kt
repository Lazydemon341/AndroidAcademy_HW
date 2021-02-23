package com.avvlas.androidacademyhomework.data.remote.retrofit.response

data class MovieCreditsResponse(
    val cast: List<CastResponse>,
    val id: Int
)