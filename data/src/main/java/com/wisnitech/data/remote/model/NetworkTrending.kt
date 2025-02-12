package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.Trending
import com.wisnitech.data.model.Trending.MediaType

data class NetworkTrending(
    val id: Int,
    @SerializedName("name")
    val tvShowName: String?,
    @SerializedName("title")
    val movieTitle: String?,
    @SerializedName("media_type")
    val mediaType: MediaType,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)

fun NetworkTrending.asExternalModel() = Trending(
    id = id,
    tvShowName = tvShowName,
    movieTitle = movieTitle,
    mediaType = mediaType,
    backdropPath = backdropPath
)
