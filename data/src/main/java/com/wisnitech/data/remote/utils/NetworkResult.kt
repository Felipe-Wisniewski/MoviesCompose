package com.wisnitech.data.remote.utils

sealed class NetworkResult<T : Any> {
    data class SuccessResult<T : Any>(val data: T) : NetworkResult<T>()
    data class ErrorResult<T : Any>(val code: Int, val errorMsg: String?) : NetworkResult<T>()
    data class ExceptionResult<T : Any>(val e: Throwable) : NetworkResult<T>()
}