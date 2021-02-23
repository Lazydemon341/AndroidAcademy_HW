package com.avvlas.androidacademyhomework.data.remote.retrofit.response

import com.squareup.moshi.Json

data class MovieResponse(
    val adult: Boolean,
    val genre_ids: List<Int>,
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)