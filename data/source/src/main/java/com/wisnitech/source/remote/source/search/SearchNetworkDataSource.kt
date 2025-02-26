package com.wisnitech.source.remote.source.search

import com.wisnitech.source.remote.model.ResponseMovies
import retrofit2.Response

interface SearchNetworkDataSource {
    suspend fun searchMoviesAndTvShows(query: String, page: Int): Response<ResponseMovies>
}