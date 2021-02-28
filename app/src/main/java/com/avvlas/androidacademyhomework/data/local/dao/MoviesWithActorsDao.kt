package com.avvlas.androidacademyhomework.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.model.MovieWithActors

@Dao
interface MoviesWithActorsDao {
    @Query("SELECT * FROM movies WHERE movieId == :movieId")
    suspend fun getMovieWithActors(movieId : Int) : MovieWithActors

    @Insert
    suspend fun insertMovieWithActors(movie : Movie)
}