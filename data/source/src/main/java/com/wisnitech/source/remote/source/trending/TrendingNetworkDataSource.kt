package com.wisnitech.source.remote.source.trending

import com.wisnitech.source.remote.model.ResponseTrending
import retrofit2.Response

interface TrendingNetworkDataSource {
    suspend fun getAllTrending(): Response<ResponseTrending>
}