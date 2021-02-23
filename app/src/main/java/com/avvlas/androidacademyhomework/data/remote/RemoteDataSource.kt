package com.avvlas.androidacademyhomework.data.remote

import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.model.MovieDetails

interface RemoteDataSource {
    suspend fun loadMovies() : List<Movie>
    suspend fun loadMovie(id : Int) : MovieDetails
}