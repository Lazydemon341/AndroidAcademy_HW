package com.avvlas.androidacademyhomework.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.avvlas.androidacademyhomework.model.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel(private val repository : MovieRepository) : ViewModel() {
    private val mMoviesData = MutableLiveData<List<Movie>>()
    public val moviesData : LiveData<List<Movie>> = mMoviesData

    init{
        loadMovies()
    }

    private fun loadMovies(){
        viewModelScope.launch{
            mMoviesData.postValue(repository.loadMovies())
        }
    }

}