package com.wisnitech.repository.model

import com.wisnitech.data.remote.model.NetworkTrending
import com.wisnitech.repository.utils.IMAGE_URL

data class Trending(
    val id: Int,
    val title: String,
    val mediaType: MediaType?,
    val backdropUrl: String?
)

fun NetworkTrending.asExternalModel() = Trending(
    id = id,
    title = when (MediaType.entries.firstOrNull { it.name == mediaType.uppercase() }) {
        MediaType.TV -> tvShowName ?: "-"
        MediaType.MOVIE -> movieTitle ?: "-"
        else -> "-"
    },
    mediaType = MediaType.entries.firstOrNull { it.name == mediaType.uppercase() },
    backdropUrl = if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath
)