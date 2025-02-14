package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName

enum class NetworkMediaType(value: String) {
    @SerializedName("tv")
    TV("Tv"),

    @SerializedName("movie")
    MOVIE("Movie")
}