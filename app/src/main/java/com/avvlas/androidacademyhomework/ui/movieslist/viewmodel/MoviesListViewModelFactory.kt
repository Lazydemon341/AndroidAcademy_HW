package com.avvlas.androidacademyhomework.ui.movieslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avvlas.androidacademyhomework.repository.MoviesRepository

class MoviesListViewModelFactory(private val repository : MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesListViewModel(repository) as T
    }

}