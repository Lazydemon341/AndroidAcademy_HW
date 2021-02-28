package com.avvlas.androidacademyhomework.data.remote.retrofit.responses

data class MovieCreditsResponse(
    val cast: List<CastResponse>,
    val id: Int
)