package com.avvlas.androidacademyhomework.repository

import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun loadMovies(): Flow<ViewState<List<Movie>>>
    suspend fun loadActors(movieId: Int): Flow<ViewState<List<Actor>>>
}
