package com.wisnitech.data.remote.source.movie

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MovieNetworkDataSourceImpl @Inject constructor(
    private val api: MovieApi
) : MovieNetworkDataSource {

    override suspend fun getPopularMovies(page: Int) = api.getPopularMovies(page)

    override suspend fun getTopRatedMovies(page: Int) = api.getTopRatedMovies(page)

    override suspend fun getUpcomingMovies(page: Int) = api.getUpcomingMovies(page)

    override suspend fun getMovieDetails(movieId: Int) = api.getMovieDetails(movieId)
}