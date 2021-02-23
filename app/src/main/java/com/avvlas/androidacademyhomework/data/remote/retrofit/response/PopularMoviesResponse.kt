package com.avvlas.androidacademyhomework.data.remote.retrofit.response

import com.squareup.moshi.Json

data class PopularMoviesResponse(
    val results: List<MovieResponse>
)