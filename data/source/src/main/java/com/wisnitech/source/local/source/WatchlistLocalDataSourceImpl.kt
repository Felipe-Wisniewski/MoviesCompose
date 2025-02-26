package com.wisnitech.source.local.source

import com.wisnitech.source.local.model.LocalWatchlist
import com.wisnitech.source.local.room.MovieDao
import javax.inject.Inject

class WatchlistLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : WatchlistLocalDataSource {

    override suspend fun saveToLocalWatchList(watchlist: List<LocalWatchlist>) =
        dao.saveToLocalWatchList(watchlist)

    override suspend fun saveToLocalWatchList(itemWatchlist: LocalWatchlist) =
        dao.saveToLocalWatchList(itemWatchlist)

    override suspend fun loadWatchlist(): List<LocalWatchlist> = dao.loadWatchlist()

    override suspend fun loadWatchlistById(id: Int) = dao.loadWatchlistById(id)

    override suspend fun deleteToLocalWatchlistById(id: Int) = dao.deleteToLocalWatchlistById(id)
}