package com.wisnitech.data.remote.utils

sealed interface ApiResult<T : Any> {
    data class Success<T : Any>(val data: T) : ApiResult<T>
    data class Error<T : Any>(val code: Int, val errorMsg: String?) : ApiResult<T>
    data class Exception<T : Any>(val e: Throwable) : ApiResult<T>
}