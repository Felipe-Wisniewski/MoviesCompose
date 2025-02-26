package com.wisnitech.source.di

import android.content.Context
import androidx.room.Room
import com.wisnitech.source.local.room.MovieDao
import com.wisnitech.source.local.room.MovieDatabase
import com.wisnitech.source.local.source.WatchlistLocalDataSource
import com.wisnitech.source.local.source.WatchlistLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    @Provides
    internal fun provideMovieDao(@ApplicationContext appContext: Context): MovieDao {
        val db = Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            "movie-database"
        ).build()
        return db.getMovieDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {
    @Binds
    internal abstract fun bindWatchlistLocalDataSource(
        watchlistLocalDataSource: WatchlistLocalDataSourceImpl
    ): WatchlistLocalDataSource
}