package com.wisnitech.repository.repositories.account

import com.wisnitech.data.remote.model.RequestWatchlist
import com.wisnitech.data.remote.source.account.AccountNetworkDataSource
import com.wisnitech.repository.utils.ApiResult
import com.wisnitech.repository.utils.handleApiCall
import com.wisnitech.data.repository.BuildConfig
import com.wisnitech.repository.model.MediaType
import com.wisnitech.repository.model.MovieResume
import com.wisnitech.repository.model.asExternalResumeModel
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

    override fun loadMoviesFromWatchlist(): Flow<List<MovieResume>> {
        return flow {
            try {
                val result =
                    handleApiCall { accountNetworkDataSource.getMoviesFromWatchlist(accountId.toInt()) }

                when (result) {
                    is ApiResult.Success -> {
                        emit(result.data.results.map { it.asExternalResumeModel(MediaType.MOVIE) })
                    }

                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}