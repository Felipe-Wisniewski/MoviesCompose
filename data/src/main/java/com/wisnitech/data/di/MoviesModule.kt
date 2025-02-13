package com.wisnitech.data.di

import com.wisnitech.data.remote.retrofit.RetrofitClient
import com.wisnitech.data.remote.source.AccountApi
import com.wisnitech.data.remote.source.AccountNetworkDataSource
import com.wisnitech.data.remote.source.AccountNetworkDataSourceImpl
import com.wisnitech.data.remote.source.MoviesApi
import com.wisnitech.data.remote.source.MoviesNetworkDataSource
import com.wisnitech.data.remote.source.MoviesNetworkDataSourceImpl
import com.wisnitech.data.remote.source.TrendingApi
import com.wisnitech.data.remote.source.TrendingNetworkDataSource
import com.wisnitech.data.remote.source.TrendingNetworkDataSourceImpl
import com.wisnitech.data.repositories.account.AccountRepository
import com.wisnitech.data.repositories.account.AccountRepositoryImpl
import com.wisnitech.data.repositories.movies.MoviesRepository
import com.wisnitech.data.repositories.movies.MoviesRepositoryImpl
import com.wisnitech.data.repositories.trending.TrendingRepository
import com.wisnitech.data.repositories.trending.TrendingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    internal fun provideMoviesApi(): MoviesApi {
        return RetrofitClient.generate<MoviesApi>()
    }

    @Provides
    internal fun provideTrendingApi(): TrendingApi {
        return RetrofitClient.generate<TrendingApi>()
    }

    @Provides
    internal fun provideAccountApi(): AccountApi {
        return RetrofitClient.generate<AccountApi>()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkDataSourceModule {

    @Binds
    internal abstract fun bindMoviesNetworkDataSource(
        networkDataSource: MoviesNetworkDataSourceImpl
    ): MoviesNetworkDataSource

    @Binds
    internal abstract fun bindTrendingNetworkDataSource(
        networkDataSource: TrendingNetworkDataSourceImpl
    ): TrendingNetworkDataSource

    @Binds
    internal abstract fun bindAccountNetworkDataSource(
        networkDataSource: AccountNetworkDataSourceImpl
    ): AccountNetworkDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoviesRepository(
        moviesRepository: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    abstract fun bindTrendingRepository(
        trendingRepository: TrendingRepositoryImpl
    ): TrendingRepository

    @Binds
    abstract fun bindAccountRepository(
        accountRepository: AccountRepositoryImpl
    ): AccountRepository
}

