package com.wisnitech.source.remote.model

import com.google.gson.annotations.SerializedName

data class NetworkCredits(
    val cast: List<NetworkCast>?,
    val crew: List<NetworkCrew>?
)

data class NetworkCast(
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    val character: String?,
    val order: Int?
)

data class NetworkCrew(
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?,
    val department: String?,
    val job: String?
)