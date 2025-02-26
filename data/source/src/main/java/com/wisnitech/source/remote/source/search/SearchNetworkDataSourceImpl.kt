package com.wisnitech.source.remote.source.search

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNetworkDataSourceImpl @Inject constructor(
    private val api: SearchApi
) : SearchNetworkDataSource {

    override suspend fun searchMoviesAndTvShows(query: String, page: Int) =
        api.searchMoviesAndTvShows(query, page)
}