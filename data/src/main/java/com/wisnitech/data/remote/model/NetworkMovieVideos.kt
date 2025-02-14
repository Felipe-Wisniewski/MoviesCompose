package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.VideoDetails

data class NetworkMovieVideos(
    val results: List<NetworkVideoDetails>?
)

data class NetworkVideoDetails(
    val id: String,
    @SerializedName("key")
    val videoKey: String,
    val type: String,
    val official: Boolean
)

fun NetworkVideoDetails.asExternalModel() = VideoDetails(
    id = id,
    videoKey = videoKey,
    type = type,
    official = official
)