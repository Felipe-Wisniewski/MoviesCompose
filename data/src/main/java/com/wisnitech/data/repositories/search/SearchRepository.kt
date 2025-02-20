package com.wisnitech.data.repositories.search

import androidx.paging.PagingData
import com.wisnitech.data.model.MovieResume
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMoviesAndTvShows(query: String): Flow<PagingData<MovieResume>>
}