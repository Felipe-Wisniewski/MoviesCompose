package com.wisnitech.source.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseTrending(
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    val results: List<NetworkTrending>
)