package com.wisnitech.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wisnitech.source.local.model.LocalWatchlist

@Database(entities = [LocalWatchlist::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}