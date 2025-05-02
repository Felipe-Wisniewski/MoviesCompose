package com.wisnitech.source.di

import com.wisnitech.source.remote.retrofit.RetrofitClient
import com.wisnitech.source.remote.source.account.AccountApi
import com.wisnitech.source.remote.source.account.AccountNetworkDataSource
import com.wisnitech.source.remote.source.account.AccountNetworkDataSourceImpl
import com.wisnitech.source.remote.source.movie.MovieApi
import com.wisnitech.source.remote.source.movie.MovieNetworkDataSource
import com.wisnitech.source.remote.source.movie.MovieNetworkDataSourceImpl
import com.wisnitech.source.remote.source.search.SearchApi
import com.wisnitech.source.remote.source.search.SearchNetworkDataSource
import com.wisnitech.source.remote.source.search.SearchNetworkDataSourceImpl
import com.wisnitech.source.remote.source.trending.TrendingApi
import com.wisnitech.source.remote.source.trending.TrendingNetworkDataSource
import com.wisnitech.source.remote.source.trending.TrendingNetworkDataSourceImpl
import com.wisnitech.source.remote.source.tv.TvApi
import com.wisnitech.source.remote.source.tv.TvNetworkDataSource
import com.wisnitech.source.remote.source.tv.TvNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    internal fun provideMovieApi(): MovieApi {
        return RetrofitClient.generate<MovieApi>()
    }

    @Provides
    internal fun provideTvApi(): TvApi {
        return RetrofitClient.generate<TvApi>()
    }

    @Provides
    internal fun provideTrendingApi(): TrendingApi {
        return RetrofitClient.generate<TrendingApi>()
    }

    @Provides
    internal fun provideAccountApi(): AccountApi {
        return RetrofitClient.generate<AccountApi>()
    }

    @Provides
    internal fun provideSearchApi(): SearchApi {
        return RetrofitClient.generate<SearchApi>()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkDataSourceModule {
    @Binds
    internal abstract fun bindMovieNetworkDataSource(
        networkDataSource: MovieNetworkDataSourceImpl
    ): MovieNetworkDataSource

    @Binds
    internal abstract fun bindTvNetworkDataSource(
        networkDataSource: TvNetworkDataSourceImpl
    ): TvNetworkDataSource

    @Binds
    internal abstract fun bindTrendingNetworkDataSource(
        networkDataSource: TrendingNetworkDataSourceImpl
    ): TrendingNetworkDataSource

    @Binds
    internal abstract fun bindAccountNetworkDataSource(
        networkDataSource: AccountNetworkDataSourceImpl
    ): AccountNetworkDataSource

    @Binds
    internal abstract fun bindSearchNetworkDataSource(
        networkDataSource: SearchNetworkDataSourceImpl
    ): SearchNetworkDataSource
}