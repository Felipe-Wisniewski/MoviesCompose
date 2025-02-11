package com.wisnitech.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieGenres(
    val id: Int,
    val name: String
) : Parcelable
