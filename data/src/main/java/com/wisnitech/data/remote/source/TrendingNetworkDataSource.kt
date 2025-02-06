package com.wisnitech.data.remote.source

import com.wisnitech.data.models.ResponseMovies
import retrofit2.Response

interface TrendingNetworkDataSource {
    suspend fun getAllTrending():Response<ResponseMovies>
}