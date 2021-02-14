package com.avvlas.androidacademyhomework.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genre: String,
    val duration: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    @DrawableRes val imageRes: Int,
    @DrawableRes val detailsImageRes: Int,
    val storyLine: String,
    val actors: List<Actor>
) : Serializable
