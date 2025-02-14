package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.Trending
import com.wisnitech.data.remote.utils.IMAGE_URL

data class NetworkTrending(
    val id: Int,
    @SerializedName("name")
    val tvShowName: String?,
    @SerializedName("title")
    val movieTitle: String?,
    @SerializedName("media_type")
    val mediaType: NetworkMediaType,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)

fun NetworkTrending.asExternalModel() = Trending(
    id = id,
    title = when (mediaType) {
        NetworkMediaType.TV -> tvShowName ?: "-"
        NetworkMediaType.MOVIE -> movieTitle ?: "-"
    },
    mediaType = mediaType.name,
    backdropUrl = if (backdropPath.isNullOrBlank()) null else IMAGE_URL + backdropPath
)
