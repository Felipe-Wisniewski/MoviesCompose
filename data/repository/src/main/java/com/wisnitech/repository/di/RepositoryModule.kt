package com.wisnitech.repository.di

import com.wisnitech.repository.repositories.account.AccountRepository
import com.wisnitech.repository.repositories.account.AccountRepositoryImpl
import com.wisnitech.repository.repositories.movie.MovieRepository
import com.wisnitech.repository.repositories.movie.MovieRepositoryImpl
import com.wisnitech.repository.repositories.search.SearchRepository
import com.wisnitech.repository.repositories.search.SearchRepositoryImpl
import com.wisnitech.repository.repositories.trending.TrendingRepository
import com.wisnitech.repository.repositories.trending.TrendingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(
        moviesRepository: MovieRepositoryImpl
    ): MovieRepository


    @Binds
    abstract fun bindTrendingRepository(
        trendingRepository: TrendingRepositoryImpl
    ): TrendingRepository

    @Binds
    abstract fun bindAccountRepository(
        accountRepository: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    abstract fun bindSearchRepository(
        searchRepository: SearchRepositoryImpl
    ): SearchRepository
}