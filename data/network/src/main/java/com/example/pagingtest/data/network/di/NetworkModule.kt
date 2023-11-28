package com.example.pagingtest.data.network.di

import com.example.pagingtest.data.common.repository.beer.BeerNetworkSource
import com.example.pagingtest.data.network.api.BeerApiServices
import com.example.pagingtest.data.network.source.BeerNetworkSourceImpl
import org.koin.dsl.module

val NetworkModule = module {
    single {
        RetrofitProvider.retrofit(get()).create(BeerApiServices::class.java)
    }

    single<BeerNetworkSource> {
        BeerNetworkSourceImpl(get())
    }
}