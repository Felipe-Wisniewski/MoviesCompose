package com.wisnitech.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalWatchlist(
    @PrimaryKey
    val id: Int,
    val type: String
)