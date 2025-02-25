package com.wisnitech.repository.repositories.account

import com.wisnitech.repository.model.MovieResume
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun saveOrRemoveToWatchlist(
        isWatchlist: Boolean,
        mediaType: String,
        mediaId: Int
    ): Flow<Boolean>

    fun loadMoviesFromWatchlist(): Flow<List<MovieResume>>
}