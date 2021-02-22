package com.avvlas.androidacademyhomework.di

import com.android.academy.fundamentals.homework.data.MovieRepository

interface MovieRepositoryProvider {
    fun provideMovieRepository() : MovieRepository
}