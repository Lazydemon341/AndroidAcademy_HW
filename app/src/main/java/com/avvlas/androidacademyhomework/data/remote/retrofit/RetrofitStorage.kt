package com.avvlas.androidacademyhomework.data.remote.retrofit

import com.avvlas.androidacademyhomework.data.remote.RemoteDataSource
import com.avvlas.androidacademyhomework.di.NetworkModule
import com.avvlas.androidacademyhomework.model.Movie

class RetrofitStorage(api : MoviesApiService) : RemoteDataSource{
    override suspend fun loadMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun loadMovie(id: Int): Movie {
        TODO("Not yet implemented")
    }
}