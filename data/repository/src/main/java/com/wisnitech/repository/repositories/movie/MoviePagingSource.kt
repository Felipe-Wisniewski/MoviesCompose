package com.wisnitech.repository.repositories.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wisnitech.data.remote.model.NetworkMovie
import com.wisnitech.data.remote.source.movie.MovieNetworkDataSource
import com.wisnitech.repository.utils.ApiResult
import com.wisnitech.repository.utils.handleApiCall
import com.wisnitech.repository.model.Movie
import com.wisnitech.repository.model.asExternalModel
import java.io.IOException

internal class MoviePagingSource(
    private val call: MoviesCall,
    private val movieDataSource: MovieNetworkDataSource
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1

            val response = handleApiCall {
                when (call) {
                    MoviesCall.TOP_RATED -> movieDataSource.getTopRatedMovies(page)
                    MoviesCall.POPULAR -> movieDataSource.getPopularMovies(page)
                    MoviesCall.UPCOMING -> movieDataSource.getUpcomingMovies(page)
                }
            }

            when (response) {
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = response.data.results.map(NetworkMovie::asExternalModel),
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (page == response.data.totalPages) null else page + 1
                    )
                }

                else -> LoadResult.Error(Exception(""))
            }

        } catch (e: IOException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}

internal enum class MoviesCall {
    TOP_RATED,
    POPULAR,
    UPCOMING
}