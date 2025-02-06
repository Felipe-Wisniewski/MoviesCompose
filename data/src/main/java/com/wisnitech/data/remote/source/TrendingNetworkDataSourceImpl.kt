package com.wisnitech.data.remote.source

import com.wisnitech.data.models.ResponseMovies
import retrofit2.Response

class TrendingNetworkDataSourceImpl(
    private val api: TrendingApi
) : TrendingNetworkDataSource {

    override suspend fun getAllTrending(): Response<ResponseMovies> = api.getAllTrending()

}