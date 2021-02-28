package com.avvlas.androidacademyhomework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.model.MovieWithActors

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies() : List<Movie>?

    @Insert
    suspend fun cacheMovies(movies : List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun clearAllMovies()

    @Transaction
    suspend fun clearAndCacheMovies(movies : List<Movie>){
        clearAllMovies()
        cacheMovies(movies)
    }
}