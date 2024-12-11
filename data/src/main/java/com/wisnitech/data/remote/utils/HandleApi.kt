package com.wisnitech.data.remote.utils

import retrofit2.HttpException
import retrofit2.Response

internal suspend fun <T : Any> handleApiCall(execute: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()

        if (response.isSuccessful && body != null)
            NetworkResult.SuccessResult(body)
        else
            NetworkResult.ErrorResult(response.code(), response.errorBody().toString())

    } catch (e: HttpException) {
        NetworkResult.ErrorResult(e.code(), e.message())
    } catch (e: Throwable) {
        NetworkResult.ExceptionResult(e)
    }
}