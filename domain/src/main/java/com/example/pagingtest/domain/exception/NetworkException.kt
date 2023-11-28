package com.example.pagingtest.domain.exception

class NetworkException(
    val errorBody: String? = null,
    var errorCode: Int = 0
) : Exception()