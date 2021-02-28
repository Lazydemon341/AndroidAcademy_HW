package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState

internal abstract class MovieDetailsViewModel : ViewModel() {
    abstract val state: LiveData<ViewState<List<Actor>>>

    abstract fun loadMovieActors(movieId : Int)
}