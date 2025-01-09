package com.wisnitech.data.remote.source

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MoviesNetworkDataSourceImpl @Inject constructor(
    private val api: MoviesApi
) : MoviesNetworkDataSource {

    override suspend fun loadPopularMovies(page: Int) = api.loadPopularMovies(page)

    override suspend fun loadTopRatedMovies(page: Int) = api.loadTopRatedMovies(page)
}