package com.wisnitech.data.remote.source

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesNetworkDataSourceImpl @Inject constructor(private val api: MoviesNetworkDataSource) {

    suspend fun loadPopularMovies(page: Int) = api.loadPopularMovies(page)

    suspend fun loadTopRatedMovies(page: Int) = api.loadTopRatedMovies(page)
}