package com.avvlas.androidacademyhomework.ui.movieslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.avvlas.androidacademyhomework.model.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository : MovieRepository) : ViewModel() {

    private val mutableMoviesData = MutableLiveData<List<Movie>>()
    val moviesData : LiveData<List<Movie>> = mutableMoviesData

    init{
        loadMovies()
    }

    private fun loadMovies(){
        viewModelScope.launch{
            mutableMoviesData.postValue(repository.loadMovies())
        }
    }

}