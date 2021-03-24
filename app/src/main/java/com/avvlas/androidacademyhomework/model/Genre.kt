package com.avvlas.androidacademyhomework.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "genres")
@Parcelize
data class Genre(
    @PrimaryKey
    val genreId: Int,
    val name: String
) : Parcelable