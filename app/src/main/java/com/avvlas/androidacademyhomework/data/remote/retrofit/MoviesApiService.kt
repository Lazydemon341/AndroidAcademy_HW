package com.avvlas.androidacademyhomework.data.remote.retrofit

import com.avvlas.androidacademyhomework.data.remote.retrofit.responses.*
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {
    @GET("configuration")
    suspend fun loadConfiguration(): ConfigurationResponse

    @GET("genre/movie/list")
    suspend fun loadGenres(): GenresResponse

    @GET("movie/popular")
    suspend fun loadPopularMovies(): PopularMoviesResponse

    @GET("movie/{id}/credits")
    suspend fun loadMovieCredits(@Path("id") id : Int): MovieCreditsResponse
}