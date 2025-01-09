package com.wisnitech.data.repositories

import androidx.paging.PagingData
import com.wisnitech.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getTopRatedMovies(): Flow<PagingData<Movie>>
}