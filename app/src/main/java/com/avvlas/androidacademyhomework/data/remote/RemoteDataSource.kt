package com.avvlas.androidacademyhomework.data.remote

import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.model.Movie

interface RemoteDataSource {
    suspend fun loadMovies() : List<Movie>
    suspend fun loadMovieActors(movieId : Int) : List<Actor>
}