package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.avvlas.androidacademyhomework.model.MovieDetails
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState

internal abstract class MovieDetailsViewModel : ViewModel() {
    abstract val state: LiveData<ViewState<MovieDetails>>

    abstract fun loadMovieDetails(movieId : Int)
}