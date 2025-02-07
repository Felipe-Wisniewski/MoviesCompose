package com.wisnitech.data.remote.utils

import retrofit2.HttpException
import retrofit2.Response

internal suspend fun <T : Any> handleApiCall(execute: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()

        if (response.isSuccessful && body != null)
            ApiResult.Success(body)
        else
            ApiResult.Error(response.code(), response.errorBody().toString())

    } catch (e: HttpException) {
        ApiResult.Error(e.code(), e.message())
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}