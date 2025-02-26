package com.wisnitech.source.local.source

import com.wisnitech.source.local.model.LocalWatchlist

interface WatchlistLocalDataSource {
    suspend fun saveToLocalWatchList(watchlist: List<LocalWatchlist>)
    suspend fun saveToLocalWatchList(itemWatchlist: LocalWatchlist)
    suspend fun loadWatchlist(): List<LocalWatchlist>
    suspend fun loadWatchlistById(id: Int): LocalWatchlist
    suspend fun deleteToLocalWatchlistById(id: Int)
}