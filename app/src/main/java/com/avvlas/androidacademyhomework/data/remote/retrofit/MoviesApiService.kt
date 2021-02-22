package com.avvlas.androidacademyhomework.data.remote.retrofit

import com.avvlas.androidacademyhomework.data.remote.retrofit.response.Configuration
import retrofit2.http.GET

interface MoviesApiService{
    @GET("configuration")
    suspend fun getConfiguration() : Configuration
}