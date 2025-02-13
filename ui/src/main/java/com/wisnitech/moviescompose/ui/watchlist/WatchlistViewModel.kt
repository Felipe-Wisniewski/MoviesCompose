package com.wisnitech.moviescompose.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisnitech.data.repositories.account.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    accountRepository: AccountRepository
) : ViewModel() {

    val watchlistUiState: StateFlow<WatchlistUiState> = accountRepository.loadMoviesFromWatchlist()
        .map(WatchlistUiState::Watchlist)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WatchlistUiState.Loading
        )

}