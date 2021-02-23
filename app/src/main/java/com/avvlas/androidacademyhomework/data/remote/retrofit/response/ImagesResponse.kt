package com.avvlas.androidacademyhomework.data.remote.retrofit.response

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

data class ImagesResponse(
    val backdrop_sizes: List<String>,
    val logo_sizes: List<String>,
    val poster_sizes: List<String>,
    val profile_sizes: List<String>,
    val secure_base_url: String
)