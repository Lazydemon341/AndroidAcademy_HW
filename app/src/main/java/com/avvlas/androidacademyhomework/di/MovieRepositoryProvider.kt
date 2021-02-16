package com.avvlas.androidacademyhomework.di

import com.android.academy.fundamentals.homework.data.MovieRepository

internal interface MovieRepositoryProvider {
    fun provideMovieRepository() : MovieRepository
}