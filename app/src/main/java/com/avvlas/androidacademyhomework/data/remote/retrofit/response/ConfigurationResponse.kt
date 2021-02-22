package com.avvlas.androidacademyhomework.data.remote.retrofit.response

import com.squareup.moshi.Json

data class ConfigurationResponse(
    @Json(name="images")
    val images: Images
)