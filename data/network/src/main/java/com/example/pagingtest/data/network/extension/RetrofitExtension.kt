package com.example.pagingtest.data.network.extension

import com.example.pagingtest.domain.exception.BadRequestException
import com.example.pagingtest.domain.exception.NetworkException
import com.example.pagingtest.domain.exception.UnAuthorizedException
import retrofit2.Call
import retrofit2.Response

private const val SERVER_ERROR_CODE = 500
private const val UNAUTHORIZED_CODE = 401
private const val BAD_REQUEST_CODE = 400

internal fun <T> Call<T>.executeOrThrow(): T {
    val response = this.execute()
    return response.getBodyOrThrowNetworkException()
}

internal fun <T> Response<T>.getBodyOrThrowNetworkException(): T {

    if (this.isSuccessful.not()) {
        val errorString = this.errorBody()!!
            .byteStream()
            .bufferedReader()
            .use { it.readText() }

        if (this.code() == UNAUTHORIZED_CODE) {
            throw UnAuthorizedException()
        }

        if (this.code() == BAD_REQUEST_CODE) {
            throw BadRequestException()
        }

        throw NetworkException(
            errorString, this.code()
        )
    }

    val body = this.body() ?: throw NetworkException(errorCode = this.code())
    return body
}