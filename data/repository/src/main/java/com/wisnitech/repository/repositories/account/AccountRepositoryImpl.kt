package com.wisnitech.repository.repositories.account

import androidx.annotation.WorkerThread
import com.wisnitech.data.repository.BuildConfig
import com.wisnitech.source.remote.model.RequestWatchlist
import com.wisnitech.source.remote.source.account.AccountNetworkDataSource
import com.wisnitech.repository.utils.ApiResult
import com.wisnitech.repository.utils.handleApiCall
import com.wisnitech.repository.model.MediaType
import com.wisnitech.repository.model.MovieResume
import com.wisnitech.repository.model.asExternalResumeModel
import com.wisnitech.repository.model.asLocalWatchlistModel
import com.wisnitech.source.local.model.LocalWatchlist
import com.wisnitech.source.local.source.WatchlistLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val accountId: String = BuildConfig.ACCOUNT_ID

class AccountRepositoryImpl @Inject constructor(
    private val accountNetworkDataSource: AccountNetworkDataSource,
    private val watchlistLocalDataSource: WatchlistLocalDataSource
) : AccountRepository {

    override fun saveOrRemoveToWatchlist(
        isWatchlist: Boolean,
        mediaType: String,
        mediaId: Int
    ): Flow<Boolean> {
        val requestWatchlist = RequestWatchlist(
            mediaType = mediaType,
            mediaId = mediaId,
            watchlist = !isWatchlist
        )

        return flow {
            try {
                val result = handleApiCall {
                    accountNetworkDataSource.postMovieToWatchlist(
                        accountId.toInt(),
                        requestWatchlist
                    )
                }

                when (result) {
                    is ApiResult.Success -> {
                        saveOrRemoveToLocalWatchlist(isWatchlist, mediaType, mediaId)
                        emit(!isWatchlist)
                    }

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
                        val watchlist = result.data.results
                        saveToLocalWatchList(watchlist.map { it.asLocalWatchlistModel(MediaType.MOVIE) })
                        emit(watchlist.map { it.asExternalResumeModel(MediaType.MOVIE) })
                    }

                    is ApiResult.Error -> throw Exception("code:${result.code},message:${result.errorMsg}")
                    else -> throw Exception("An error occurred in the fun loadAllTrending")
                }

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    @WorkerThread
    override suspend fun saveToLocalWatchList(watchlist: List<LocalWatchlist>) =
        withContext(Dispatchers.IO) {
            watchlistLocalDataSource.saveToLocalWatchList(watchlist)
        }


    private suspend fun saveOrRemoveToLocalWatchlist(
        isWatchlist: Boolean,
        mediaType: String,
        mediaId: Int
    ) {
        if (isWatchlist) {
            deleteToLocalWatchlistById(mediaId)
        } else {
            val itemWatchlist = LocalWatchlist(id = mediaId, type = mediaType)
            saveToLocalWatchList(itemWatchlist)
        }
    }

    @WorkerThread
    override suspend fun saveToLocalWatchList(itemWatchlist: LocalWatchlist) =
        withContext(Dispatchers.IO) {
            watchlistLocalDataSource.saveToLocalWatchList(itemWatchlist)
        }

    @WorkerThread
    override suspend fun deleteToLocalWatchlistById(id: Int) =
        withContext(Dispatchers.IO) {
            watchlistLocalDataSource.deleteToLocalWatchlistById(id)
        }
}