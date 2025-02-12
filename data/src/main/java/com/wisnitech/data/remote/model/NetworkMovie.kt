package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.Movie

data class NetworkMovie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)

fun NetworkMovie.asExternalModel() = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    backdropPath = backdropPath
)