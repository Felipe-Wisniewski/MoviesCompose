package com.wisnitech.data.di

import com.wisnitech.data.remote.retrofit.RetrofitClient
import com.wisnitech.data.remote.source.account.AccountApi
import com.wisnitech.data.remote.source.account.AccountNetworkDataSource
import com.wisnitech.data.remote.source.account.AccountNetworkDataSourceImpl
import com.wisnitech.data.remote.source.movie.MovieApi
import com.wisnitech.data.remote.source.movie.MovieNetworkDataSource
import com.wisnitech.data.remote.source.movie.MovieNetworkDataSourceImpl
import com.wisnitech.data.remote.source.search.SearchApi
import com.wisnitech.data.remote.source.search.SearchNetworkDataSource
import com.wisnitech.data.remote.source.search.SearchNetworkDataSourceImpl
import com.wisnitech.data.remote.source.trending.TrendingApi
import com.wisnitech.data.remote.source.trending.TrendingNetworkDataSource
import com.wisnitech.data.remote.source.trending.TrendingNetworkDataSourceImpl
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