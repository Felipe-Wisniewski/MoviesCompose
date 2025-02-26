package com.wisnitech.repository.repositories.account

import com.wisnitech.repository.model.MovieResume
import com.wisnitech.source.local.model.LocalWatchlist
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun saveOrRemoveToWatchlist(
        isWatchlist: Boolean,
        mediaType: String,
        mediaId: Int
    ): Flow<Boolean>
    fun loadMoviesFromWatchlist(): Flow<List<MovieResume>>
    suspend fun saveToLocalWatchList(watchlist: List<LocalWatchlist>)
    suspend fun saveToLocalWatchList(itemWatchlist: LocalWatchlist)
    suspend fun deleteToLocalWatchlistById(id: Int)
}