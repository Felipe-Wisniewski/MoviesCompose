package com.wisnitech.data.remote.source

import com.wisnitech.data.remote.model.ResponseMovieDetails
import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response

interface MoviesNetworkDataSource {
    suspend fun loadPopularMovies(page: Int): Response<ResponseMovies>
    suspend fun loadTopRatedMovies(page: Int): Response<ResponseMovies>
    suspend fun loadUpcomingMovies(page: Int): Response<ResponseMovies>
    suspend fun loadMovieDetails(movieId: Int): Response<ResponseMovieDetails>
}