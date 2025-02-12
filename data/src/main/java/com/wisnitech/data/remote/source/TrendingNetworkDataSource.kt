package com.wisnitech.data.remote.source

import com.wisnitech.data.remote.model.ResponseTrending
import retrofit2.Response

interface TrendingNetworkDataSource {
    suspend fun getAllTrending(): Response<ResponseTrending>
}