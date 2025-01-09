package com.wisnitech.data.remote.source

import com.wisnitech.data.models.ResponseMovies
import retrofit2.Response

interface MoviesNetworkDataSource {
    suspend fun loadPopularMovies(page: Int): Response<ResponseMovies>

    suspend fun loadTopRatedMovies(page: Int): Response<ResponseMovies>
}