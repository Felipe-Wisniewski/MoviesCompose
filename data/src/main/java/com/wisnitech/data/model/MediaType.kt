package com.wisnitech.data.model

import com.google.gson.annotations.SerializedName

enum class MediaType(value: String) {
    @SerializedName("tv")
    TV("Tv"),

    @SerializedName("movie")
    MOVIE("Movie"),

    @SerializedName("person")
    PERSON("Person")
}