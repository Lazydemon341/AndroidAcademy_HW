package com.avvlas.androidacademyhomework.di

import com.avvlas.androidacademyhomework.repository.MoviesRepository

interface MovieRepositoryProvider {
    fun provideMovieRepository() : MoviesRepository
}