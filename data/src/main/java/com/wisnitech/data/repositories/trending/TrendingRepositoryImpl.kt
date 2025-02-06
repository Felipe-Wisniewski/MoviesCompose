package com.wisnitech.data.repositories.trending

import com.wisnitech.data.models.Movie
import com.wisnitech.data.remote.source.TrendingApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrendingRepositoryImpl(private val api: TrendingApi) : TrendingRepository {

    override suspend fun loadAllTrending(): Flow<List<Movie>> {
        return flow {
            val result = api.getAllTrending()
            result.body()?.results?.let {
                emit(it)
            }
        }
    }

}