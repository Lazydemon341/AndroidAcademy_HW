package com.android.academy.fundamentals.homework.data

import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.model.MovieDetails
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface MovieRepository {
    suspend fun loadMovies(): Flow<ViewState<List<Movie>>>
    suspend fun loadMovie(movieId: Int): Flow<ViewState<MovieDetails>>
}
