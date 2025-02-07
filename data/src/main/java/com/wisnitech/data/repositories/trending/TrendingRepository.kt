package com.wisnitech.data.repositories.trending

import com.wisnitech.data.models.Trending
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun loadAllTrending(): Flow<List<Trending>>
}