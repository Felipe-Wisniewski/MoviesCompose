package com.wisnitech.data.di

import com.wisnitech.data.remote.retrofit.RetrofitClient
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MoviesNetworkModule {
    @Provides
    fun provideMoviesDataSource(): MoviesNetworkDataSource {
        return RetrofitClient.generate<MoviesNetworkDataSource>()
    }
}

