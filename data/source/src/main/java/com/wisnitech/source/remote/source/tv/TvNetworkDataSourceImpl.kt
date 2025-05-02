package com.wisnitech.source.remote.source.tv

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TvNetworkDataSourceImpl @Inject constructor(
    private val api: TvApi
) : TvNetworkDataSource {

    override suspend fun getPopularTvShows(page: Int) = api.getPopularTvShows(page)

    override suspend fun getTopRatedTvShows(page: Int) = api.getTopRatedTvShows(page)
}