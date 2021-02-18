package com.avvlas.androidacademyhomework.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.academy.fundamentals.homework.data.MovieRepository

class MoviesListViewModelFactory(private val repository : MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesListViewModel(repository) as T
    }
}