package com.wisnitech.source.remote.source.movie

import com.wisnitech.source.remote.model.ResponseMovieDetails
import com.wisnitech.source.remote.model.ResponseMovies
import retrofit2.Response

interface MovieNetworkDataSource {
    suspend fun getNowPlayingMovies(page: Int): Response<ResponseMovies>
    suspend fun getTopRatedMovies(page: Int): Response<ResponseMovies>
    suspend fun getPopularMovies(page: Int): Response<ResponseMovies>
    suspend fun getUpcomingMovies(page: Int): Response<ResponseMovies>
    suspend fun getMovieDetails(movieId: Int): Response<ResponseMovieDetails>
}