package com.wisnitech.data.repositories.trending

import com.wisnitech.data.model.Trending
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun loadAllTrending(): Flow<List<Trending>>
}