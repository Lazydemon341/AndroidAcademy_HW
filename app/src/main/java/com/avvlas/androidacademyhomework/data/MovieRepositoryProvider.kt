package com.avvlas.androidacademyhomework.data

import com.android.academy.fundamentals.homework.data.MovieRepository

internal interface MovieRepositoryProvider {
    fun provideMovieRepository() : MovieRepository
}