package com.wisnitech.source.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkTrending(
    val id: Int,
    @SerializedName("name")
    val tvShowName: String?,
    @SerializedName("title")
    val movieTitle: String?,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)