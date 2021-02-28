package com.avvlas.androidacademyhomework.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.avvlas.androidacademyhomework.data.local.crossref.MovieActorCrossRef

data class MovieWithActors(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entity = Actor::class,
        entityColumn = "actorId",
        associateBy = Junction(
            MovieActorCrossRef::class,
            parentColumn = "mId", entityColumn = "aId"
        )
    )
    val actors: List<Actor>
)
