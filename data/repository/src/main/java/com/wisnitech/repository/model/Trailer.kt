package com.wisnitech.repository.model

import com.wisnitech.source.remote.model.NetworkVideo

data class Trailer(
    val id: String,
    val trailerKey: String,
    val type: String,
    val official: Boolean
)

fun NetworkVideo.asExternalResumeModel() = Trailer(
    id = id,
    trailerKey = videoKey,
    type = type,
    official = official
)