package com.wisnitech.data.remote.source

import com.wisnitech.data.models.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET

interface TrendingApi {

    @GET("trending/all/day")
    suspend fun getAllTrending(): Response<ResponseMovies>
}