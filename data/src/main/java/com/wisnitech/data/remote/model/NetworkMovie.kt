package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.Movie
import com.wisnitech.data.remote.utils.IMAGE_URL

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
    posterUrl = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath,
    backdropUrl = if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath
)