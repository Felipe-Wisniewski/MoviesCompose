package com.wisnitech.source.remote.source.tv

import com.wisnitech.source.remote.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvApi {

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("page") page: Int): Response<ResponseMovies>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(@Query("page") page: Int): Response<ResponseMovies>
}