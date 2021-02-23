package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

internal abstract class MovieDetailsViewModel : ViewModel() {
    abstract val state: LiveData<MovieDetailsViewState>

    abstract fun loadMovieDetails(movieId : Int)
}