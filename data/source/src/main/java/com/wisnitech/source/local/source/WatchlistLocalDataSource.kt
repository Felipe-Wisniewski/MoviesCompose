package com.wisnitech.source.local.source

import com.wisnitech.source.local.model.LocalWatchlist
import kotlinx.coroutines.flow.Flow

interface WatchlistLocalDataSource {
    suspend fun saveToLocalWatchList(watchlist: List<LocalWatchlist>)
    suspend fun saveToLocalWatchList(itemWatchlist: LocalWatchlist)
    suspend fun loadLocalWatchlist(): List<LocalWatchlist>
    fun loadLocalWatchlistById(id: Int): Flow<LocalWatchlist?>
    suspend fun deleteToLocalWatchlistById(id: Int)
}