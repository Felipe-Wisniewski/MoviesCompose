package com.wisnitech.repository.model

import com.wisnitech.source.remote.model.NetworkMovie
import com.wisnitech.repository.utils.IMAGE_URL

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val backdropUrl: String?
)

fun NetworkMovie.asExternalModel() = Movie(
    id = id,
    title = title ?: "-",
    posterUrl = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath,
    backdropUrl = if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath
)