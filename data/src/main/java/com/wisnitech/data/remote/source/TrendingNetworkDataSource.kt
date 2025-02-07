package com.wisnitech.data.remote.source

import com.wisnitech.data.models.ResponseTrending
import retrofit2.Response

interface TrendingNetworkDataSource {
    suspend fun getAllTrending(): Response<ResponseTrending>
}