package com.example.pagingtest.data.network.di

import android.content.Context
import com.example.pagingtest.data.network.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

object OkHttpProvider {
    private var okHttpClient: OkHttpClient? = null

    fun okHttpClient(context: Context): OkHttpClient {
        if (okHttpClient != null)
            return okHttpClient!!

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build()
            chain.proceed(request)
        })

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)

        }

        return okHttpClientBuilder
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .build()
    }
}