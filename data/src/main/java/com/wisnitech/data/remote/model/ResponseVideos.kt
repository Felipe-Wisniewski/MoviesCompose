package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.Trailer

data class ResponseVideos(
    val results: List<NetworkVideo>?
)

data class NetworkVideo(
    val id: String,
    @SerializedName("key")
    val videoKey: String,
    val type: String,
    val official: Boolean
)

fun NetworkVideo.asExternalResumeModel() = Trailer(
    id = id,
    trailerKey = videoKey,
    type = type,
    official = official
)