package com.avvlas.androidacademyhomework.model

data class MovieDetails(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val duration: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val detailImageUrl: String,
    val storyLine: String,
    val actors: List<Actor>
)
