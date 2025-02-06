package com.wisnitech.data.repositories.trending

import com.wisnitech.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    suspend fun loadAllTrending(): Flow<List<Movie>>
}