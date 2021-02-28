package com.avvlas.androidacademyhomework.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avvlas.androidacademyhomework.data.local.crossref.MovieActorCrossRef
import com.avvlas.androidacademyhomework.data.local.dao.MoviesDao
import com.avvlas.androidacademyhomework.data.local.dao.MoviesWithActorsDao
import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.model.Movie

@Database(entities = [Movie::class, Actor::class, MovieActorCrossRef::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun moviesWithActorsDao(): MoviesWithActorsDao

    companion object {
        private var instance: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(
                        context,
                        MoviesDatabase::class.java,
                        "moviesDb"
                    )
                    .build()
            }
            return instance!!
        }
    }
}