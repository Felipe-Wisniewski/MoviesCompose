package com.wisnitech.source.remote.model

import com.google.gson.annotations.SerializedName

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