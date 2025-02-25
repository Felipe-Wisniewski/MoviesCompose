package com.wisnitech.repository.repositories.trending

import com.wisnitech.data.remote.model.NetworkTrending
import com.wisnitech.data.remote.source.trending.TrendingNetworkDataSource
import com.wisnitech.repository.model.Trending
import com.wisnitech.repository.model.asExternalModel
import com.wisnitech.repository.utils.ApiResult
import com.wisnitech.repository.utils.handleApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val trendingNetworkDataSource: TrendingNetworkDataSource
) : TrendingRepository {

    override fun loadAllTrending(): Flow<List<Trending>> {
        return flow {
            try {
                val result = handleApiCall { trendingNetworkDataSource.getAllTrending() }

                when (result) {
                    is ApiResult.Success -> emit(result.data.results.map(NetworkTrending::asExternalModel))
                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}