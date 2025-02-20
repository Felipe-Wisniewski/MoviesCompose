package com.wisnitech.data.remote.source.trending

import com.wisnitech.data.remote.model.ResponseTrending
import retrofit2.Response

interface TrendingNetworkDataSource {
    suspend fun getAllTrending(): Response<ResponseTrending>
}