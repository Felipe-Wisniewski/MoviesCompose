package com.wisnitech.data.remote.model

import com.google.gson.annotations.SerializedName
import com.wisnitech.data.model.Person

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

fun NetworkCast.asExternalResumeModel() = Person(
    id = id,
    knownForDepartment = knownForDepartment ?: "-",
    name = name ?: "-",
    profileUrl = if (profilePath.isNullOrBlank()) null else com.wisnitech.data.remote.utils.IMAGE_URL + profilePath
)

fun NetworkCrew.asExternalResumeModel() = Person(
    id = id,
    knownForDepartment = knownForDepartment ?: "-",
    name = name ?: "-",
    profileUrl = if (profilePath.isNullOrBlank()) null else com.wisnitech.data.remote.utils.IMAGE_URL + profilePath
)