package com.wisnitech.data.remote.source.trending

import com.wisnitech.data.remote.model.ResponseTrending
import retrofit2.Response
import retrofit2.http.GET

interface TrendingApi {

    @GET("trending/all/day")
    suspend fun getAllTrending(): Response<ResponseTrending>
}