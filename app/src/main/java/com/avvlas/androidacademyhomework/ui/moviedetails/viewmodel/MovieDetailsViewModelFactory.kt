package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.academy.fundamentals.homework.data.MovieRepository

class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModelImpl(repository) as T
    }

}