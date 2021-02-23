package com.android.academy.fundamentals.homework.data

import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieRepository {
    suspend fun loadMovies(): List<Movie>
    suspend fun loadMovie(movieId: Int): MovieDetails
}
