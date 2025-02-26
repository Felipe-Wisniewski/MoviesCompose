package com.wisnitech.repository.model

import com.wisnitech.source.remote.model.NetworkCast
import com.wisnitech.source.remote.model.NetworkCrew
import com.wisnitech.repository.utils.IMAGE_URL

data class Person(
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val profileUrl: String?
)

fun NetworkCast.asExternalResumeModel() = Person(
    id = id,
    knownForDepartment = knownForDepartment ?: "-",
    name = name ?: "-",
    profileUrl = if (profilePath.isNullOrBlank()) null else IMAGE_URL + profilePath
)

fun NetworkCrew.asExternalResumeModel() = Person(
    id = id,
    knownForDepartment = knownForDepartment ?: "-",
    name = name ?: "-",
    profileUrl = if (profilePath.isNullOrBlank()) null else IMAGE_URL + profilePath
)