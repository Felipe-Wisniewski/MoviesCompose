package com.wisnitech.data.repositories.account

interface AccountRepository {
    fun saveToWatchlist()
    fun loadWatchlist()
}