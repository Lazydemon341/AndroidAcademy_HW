package com.avvlas.androidacademyhomework.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val imageUrl: String
)
