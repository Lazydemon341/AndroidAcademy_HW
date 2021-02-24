package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.model.MovieDetails
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class MovieDetailsViewModelImpl(private val repository: MovieRepository) :
    MovieDetailsViewModel() {

    override val state = MutableLiveData<ViewState<MovieDetails>>()

    @InternalCoroutinesApi
    override fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            repository.loadMovie(movieId).collect { state.postValue(it) }
        }
    }
}