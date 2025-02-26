package com.wisnitech.source.remote.source.trending

import com.wisnitech.source.remote.model.ResponseTrending
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingNetworkDataSourceImpl @Inject constructor(
    private val api: TrendingApi
) : TrendingNetworkDataSource {

    override suspend fun getAllTrending(): Response<ResponseTrending> = api.getAllTrending()

}