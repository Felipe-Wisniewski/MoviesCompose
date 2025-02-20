package com.wisnitech.data.remote.source.account

import com.wisnitech.data.remote.model.RequestWatchlist
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountNetworkDataSourceImpl @Inject constructor(
    private val api: AccountApi
) : AccountNetworkDataSource {

    override suspend fun postMovieToWatchlist(
        accountId: Int,
        requestWatchlist: RequestWatchlist
    ): Response<Unit> = api.postMovieToWatchlist(accountId, requestWatchlist)

    override suspend fun getMoviesFromWatchlist(accountId: Int) =
        api.getMoviesFromWatchlist(accountId)
}