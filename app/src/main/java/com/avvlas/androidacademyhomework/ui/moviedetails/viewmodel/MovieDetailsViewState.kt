package com.avvlas.androidacademyhomework.ui.moviedetails.viewmodel

import com.avvlas.androidacademyhomework.model.MovieDetails

internal sealed class MovieDetailsViewState {
    data class MovieLoaded(val movie : MovieDetails) : MovieDetailsViewState()

    object NoMovie : MovieDetailsViewState()
}