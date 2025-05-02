package com.wisnitech.source.remote.source.movie

import com.wisnitech.source.remote.model.ResponseMovieDetails
import com.wisnitech.source.remote.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Response<ResponseMovies>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("append_to_response") append: String = "videos,credits"
    ): Response<ResponseMovieDetails>
}