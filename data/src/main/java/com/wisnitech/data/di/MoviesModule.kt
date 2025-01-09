package com.wisnitech.data.di

import com.wisnitech.data.remote.retrofit.RetrofitClient
import com.wisnitech.data.remote.source.MoviesApi
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import com.wisnitech.data.remote.source.MoviesNetworkDataSourceImpl
import com.wisnitech.data.repositories.MoviesRepository
import com.wisnitech.data.repositories.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object MoviesApiModule {

    @Provides
    internal fun provideMoviesApi(): MoviesApi {
        return RetrofitClient.generate<MoviesApi>()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MoviesDataSourceModule {

    @Binds
    internal abstract fun bindMoviesNetworkDataSource(
        networkDataSource: MoviesNetworkDataSourceImpl
    ): MoviesNetworkDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(
        moviesRepository: MoviesRepositoryImpl
    ): MoviesRepository
}

