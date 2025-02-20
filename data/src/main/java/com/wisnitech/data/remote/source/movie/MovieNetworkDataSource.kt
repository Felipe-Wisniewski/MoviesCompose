package com.wisnitech.data.remote.source.movie

import com.wisnitech.data.remote.model.ResponseMovieDetails
import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response

interface MovieNetworkDataSource {
    suspend fun getPopularMovies(page: Int): Response<ResponseMovies>
    suspend fun getTopRatedMovies(page: Int): Response<ResponseMovies>
    suspend fun getUpcomingMovies(page: Int): Response<ResponseMovies>
    suspend fun getMovieDetails(movieId: Int): Response<ResponseMovieDetails>
}