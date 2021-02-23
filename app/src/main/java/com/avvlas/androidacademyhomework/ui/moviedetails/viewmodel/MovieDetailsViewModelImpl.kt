package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.avvlas.androidacademyhomework.model.MovieDetails
import kotlinx.coroutines.launch

internal class MovieDetailsViewModelImpl(private val repository: MovieRepository) :
    MovieDetailsViewModel() {

    override val state = MutableLiveData<MovieDetailsViewState>()

    override fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.loadMovie(movieId)

            handleMovieLoadResult(movie)
        }
    }

    private fun handleMovieLoadResult(movie: MovieDetails?) {
        if (movie != null) {
            state.postValue(MovieDetailsViewState.MovieLoaded(movie))
        } else {
            state.postValue(MovieDetailsViewState.NoMovie)
        }
    }
}