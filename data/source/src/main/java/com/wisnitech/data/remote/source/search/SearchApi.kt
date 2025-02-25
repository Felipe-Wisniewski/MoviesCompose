package com.wisnitech.data.remote.source.search

import com.wisnitech.data.remote.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/multi")
    suspend fun searchMoviesAndTvShows(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("include_adult") adult: Boolean = true
    ) : Response<ResponseMovies>
}