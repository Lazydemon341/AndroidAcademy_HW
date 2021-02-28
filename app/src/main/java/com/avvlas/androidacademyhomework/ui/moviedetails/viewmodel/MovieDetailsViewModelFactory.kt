package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avvlas.androidacademyhomework.repository.MoviesRepository

class MovieDetailsViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModelImpl(repository) as T
    }

}