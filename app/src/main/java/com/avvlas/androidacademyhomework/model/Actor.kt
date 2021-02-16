package com.avvlas.androidacademyhomework.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val imageUrl: String
) : Serializable
