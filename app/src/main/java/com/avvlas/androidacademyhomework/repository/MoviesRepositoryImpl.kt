package com.avvlas.androidacademyhomework.repository

import android.util.Log
import com.avvlas.androidacademyhomework.data.local.MoviesDatabase
import com.avvlas.androidacademyhomework.data.remote.RemoteDataSource
import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.model.Movie
import com.avvlas.androidacademyhomework.ui.viewstate.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepositoryImpl private constructor(
    private val database: MoviesDatabase,
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {

    override suspend fun loadMovies(): Flow<ViewState<List<Movie>>> = flow {
        emit(ViewState.Loading)

        var freshMovies : List<Movie>? = null
        try {
            freshMovies = remoteDataSource.loadMovies()
            database.moviesDao().clearAndCacheMovies(freshMovies)
        } catch (throwable: Throwable) {
            emit(ViewState.Error)
        }

        val cachedMovies = database.moviesDao().getMovies()
        if (cachedMovies != null) {
            emit(ViewState.Success(cachedMovies))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun loadActors(movieId: Int): Flow<ViewState<List<Actor>>> {
        // TODO ?
        return safeApiCall { remoteDataSource.loadMovieActors(movieId) }
    }

    private suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Flow<ViewState<T>> =
        flow {
            emit(ViewState.Loading)
            Log.d("view_state", "loading    ")
            try {
                emit(ViewState.Success(apiCall.invoke()))
                Log.d("view_state", "success")
            } catch (throwable: Throwable) {
                emit(ViewState.Error)
                Log.d("view_state", "error")
            }
        }.flowOn(dispatcher)

    companion object {
        private var instance: MoviesRepository? = null

        fun getInstance(
            database: MoviesDatabase,
            remoteDataSource: RemoteDataSource
        ): MoviesRepository {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MoviesRepositoryImpl(database, remoteDataSource)
                    }
                }
            }
            return instance!!
        }
    }
}