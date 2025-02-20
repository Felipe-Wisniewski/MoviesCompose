package com.wisnitech.data.remote.source.search

import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response

interface SearchNetworkDataSource {
    suspend fun searchMoviesAndTvShows(query: String, page: Int): Response<ResponseMovies>
}