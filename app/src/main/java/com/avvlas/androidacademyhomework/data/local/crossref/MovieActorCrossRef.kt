package com.avvlas.androidacademyhomework.data.local.crossref

import androidx.room.Entity

@Entity(primaryKeys = ["mId", "aId"])
data class MovieActorCrossRef(
    val mId : Int,
    val aId : Int,
)
