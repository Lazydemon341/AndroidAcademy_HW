package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.repository.MoviesRepository
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class MovieDetailsViewModelImpl(private val repository: MoviesRepository) :
    MovieDetailsViewModel() {

    override val state = MutableLiveData<ViewState<List<Actor>>>()

    @InternalCoroutinesApi
    override fun loadMovieActors(movieId: Int) {
        viewModelScope.launch {
            repository.loadActors(movieId).collect { state.postValue(it) }
        }
    }
}