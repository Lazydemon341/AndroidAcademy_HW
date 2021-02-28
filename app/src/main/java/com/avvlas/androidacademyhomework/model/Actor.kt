package com.avvlas.androidacademyhomework.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
data class Actor(
    @PrimaryKey val actorId: Int,
    val name: String,
    val imageUrl: String
)
