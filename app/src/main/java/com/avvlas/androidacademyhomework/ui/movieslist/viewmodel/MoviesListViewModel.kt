package com.avvlas.androidacademyhomework.ui.movieslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avvlas.androidacademyhomework.repository.MoviesRepository
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val mutableState = MutableLiveData<ViewState<List<Movie>>>()
    val state: LiveData<ViewState<List<Movie>>> = mutableState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            repository.loadMovies().collect { mutableState.postValue(it) }
        }
    }
}

