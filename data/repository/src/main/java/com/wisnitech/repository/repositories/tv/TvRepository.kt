package com.wisnitech.repository.repositories.tv

import androidx.paging.PagingData
import com.wisnitech.repository.model.Movie
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun loadPopularTvShows(): Flow<PagingData<Movie>>
    fun loadTopRatedTvShows(): Flow<PagingData<Movie>>
}