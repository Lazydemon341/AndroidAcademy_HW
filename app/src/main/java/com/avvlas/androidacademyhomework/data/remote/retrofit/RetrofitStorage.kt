package com.avvlas.androidacademyhomework.data.remote.retrofit

import com.avvlas.androidacademyhomework.data.remote.RemoteDataSource
import com.avvlas.androidacademyhomework.di.NetworkModule
import com.avvlas.androidacademyhomework.model.Movie

class RetrofitStorage(private val api : MoviesApiService) : RemoteDataSource{

    override suspend fun loadMovies(): List<Movie> {
        val images = api.loadConfiguration().images
        val imagesBaseUrl = images.base_url
        val posterSize = images.poster_sizes.find{it == "w500"}
        val backdropSize = images.backdrop_sizes.find { it == "w780" }
        val profileSize = images.profile_sizes.find{it == "w185"}
        val genres = api.loadGenres().genres
        // TODO
    }

    override suspend fun loadMovie(id: Int): Movie {
        TODO("Not yet implemented")
    }
}