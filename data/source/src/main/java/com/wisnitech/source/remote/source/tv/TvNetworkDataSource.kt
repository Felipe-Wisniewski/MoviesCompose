package com.wisnitech.source.remote.source.tv

import com.wisnitech.source.remote.model.ResponseMovies
import retrofit2.Response

interface TvNetworkDataSource {
    suspend fun getPopularTvShows(page: Int): Response<ResponseMovies>
    suspend fun getTopRatedTvShows(page: Int): Response<ResponseMovies>
}