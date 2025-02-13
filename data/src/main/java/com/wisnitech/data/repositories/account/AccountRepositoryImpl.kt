package com.wisnitech.data.repositories.account

import com.wisnitech.data.BuildConfig
import com.wisnitech.data.model.Movie
import com.wisnitech.data.remote.model.NetworkMovie
import com.wisnitech.data.remote.model.RequestWatchlist
import com.wisnitech.data.remote.model.asExternalModel
import com.wisnitech.data.remote.source.AccountNetworkDataSource
import com.wisnitech.data.remote.utils.ApiResult
import com.wisnitech.data.remote.utils.handleApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val accountId: String = BuildConfig.ACCOUNT_ID

class AccountRepositoryImpl @Inject constructor(
    private val accountNetworkDataSource: AccountNetworkDataSource
) : AccountRepository {

    override fun saveMovieToWatchlist(requestWatchlist: RequestWatchlist): Flow<Unit> {
        return flow {
            try {
                val result = handleApiCall {
                    accountNetworkDataSource.postMovieToWatchlist(
                        accountId.toInt(),
                        requestWatchlist
                    )
                }

                when (result) {
                    is ApiResult.Success -> emit(Unit)
                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    override fun loadMoviesFromWatchlist(): Flow<List<Movie>> {
        return flow {
            try {
                val result =
                    handleApiCall { accountNetworkDataSource.getMoviesFromWatchlist(accountId.toInt()) }

                when (result) {
                    is ApiResult.Success -> emit(result.data.results.map(NetworkMovie::asExternalModel))
                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}