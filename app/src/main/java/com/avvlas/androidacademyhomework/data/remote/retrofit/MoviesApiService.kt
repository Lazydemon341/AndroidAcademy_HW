package com.avvlas.androidacademyhomework.data.remote.retrofit

import com.avvlas.androidacademyhomework.data.remote.retrofit.response.ConfigurationResponse
import com.avvlas.androidacademyhomework.data.remote.retrofit.response.GenreResponse
import com.avvlas.androidacademyhomework.data.remote.retrofit.response.GenresResponse
import retrofit2.http.GET

interface MoviesApiService{
    @GET("configuration")
    suspend fun loadConfiguration() : ConfigurationResponse

    @GET("genre/movie/list")
    suspend fun loadGenres() : GenresResponse
}