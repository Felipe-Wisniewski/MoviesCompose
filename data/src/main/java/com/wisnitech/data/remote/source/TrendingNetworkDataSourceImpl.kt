package com.wisnitech.data.remote.source

import com.wisnitech.data.remote.model.ResponseTrending
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingNetworkDataSourceImpl @Inject constructor(
    private val api: TrendingApi
) : TrendingNetworkDataSource {

    override suspend fun getAllTrending(): Response<ResponseTrending> = api.getAllTrending()

}