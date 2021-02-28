package com.avvlas.androidacademyhomework.data.remote.retrofit

import android.util.Log
import com.avvlas.androidacademyhomework.data.remote.RemoteDataSource
import com.avvlas.androidacademyhomework.model.Actor
import com.avvlas.androidacademyhomework.model.Genre
import com.avvlas.androidacademyhomework.model.Movie
import kotlin.math.roundToInt

class RemoteDataSourceImpl(private val api: MoviesApiService) : RemoteDataSource {

    override suspend fun loadMovies(): List<Movie> {
        val images = api.loadConfiguration().images
        val baseImageUrl = images.secure_base_url
        Log.d("image_base_url", baseImageUrl)

        val posterSize = images.poster_sizes.find { it == "w500" }
        val backdropSize = images.backdrop_sizes.find { it == "w780" }

        val genres = api.loadGenres().genres

        val movies = api.loadPopularMovies().results
        movies.forEach { movie ->
            Log.d(
                "votes",
                movie.vote_average.toString().plus(" " + (movie.vote_average / 2).roundToInt())
            )
        }
        return movies.map {
            Movie(
                movieId = it.id,
                pgAge = if (it.adult) 16 else 13,
                title = it.title,
                genres = genres
                    .filter { genreResponse -> it.genre_ids.contains(genreResponse.id) }
                    .map { genreResponse -> Genre(genreResponse.id, genreResponse.name) },
                reviewCount = it.vote_count,
                isLiked = false,
                rating = (it.vote_average / 2).roundToInt(),
                imageUrl = formImageUrl(baseImageUrl, posterSize, it.poster_path),
                detailImageUrl = formImageUrl(baseImageUrl, backdropSize, it.backdrop_path),
                storyLine = it.overview
            )
        }
    }

    override suspend fun loadMovieActors(movieId: Int): List<Actor> {
        val images = api.loadConfiguration().images
        val baseImageUrl = images.secure_base_url
        val profileSize = images.profile_sizes.find { it == "w185" }

        return api.loadMovieCredits(movieId).cast.map {
            Actor(
                actorId = it.id,
                name = it.name,
                imageUrl =  formImageUrl(baseImageUrl, profileSize, it.profile_path)
            )
        }
    }

    private fun formImageUrl(baseUrl: String, size: String?, path: String): String {
        return baseUrl
            .plus(
                size.takeUnless { it.isNullOrEmpty() } ?: ORIGINAL_IMAGE_SIZE
            )
            .plus(path)
    }

    companion object {
        private const val ORIGINAL_IMAGE_SIZE = "original"
    }
}