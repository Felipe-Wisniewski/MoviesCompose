package com.wisnitech.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wisnitech.source.local.model.LocalWatchlist
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToLocalWatchList(watchlist: List<LocalWatchlist>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToLocalWatchList(itemWatchlist: LocalWatchlist)

    @Query("SELECT * FROM localwatchlist")
    suspend fun loadLocalWatchlist(): List<LocalWatchlist>

    @Query("SELECT * FROM localwatchlist WHERE id = :id")
    fun loadLocalWatchlistById(id: Int): Flow<LocalWatchlist?>

    @Query("DELETE FROM localwatchlist WHERE id = :id")
    suspend fun deleteToLocalWatchlistById(id: Int)
}