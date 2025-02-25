package com.wisnitech.repository.repositories.trending

import com.wisnitech.repository.model.Trending
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun loadAllTrending(): Flow<List<Trending>>
}