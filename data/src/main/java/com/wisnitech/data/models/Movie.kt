package com.wisnitech.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w1280"

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath:String?
) : Parcelable {

    fun getPosterUrl(): String? = if (posterPath.isNullOrBlank()) null else IMAGE_URL + posterPath

}
