package com.example.pagingtest.data.network.di

import android.content.Context
import com.example.pagingtest.data.network.BuildConfig
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {
    private var retrofit: Retrofit? = null

    fun retrofit(context: Context): Retrofit {
        if (retrofit != null)
            return retrofit!!

        val moshi = Moshi.Builder()
            .build()

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(OkHttpProvider.okHttpClient(context))
            .build()
    }
}