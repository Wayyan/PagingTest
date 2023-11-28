package com.example.pagingtest.domain.di

import com.example.pagingtest.domain.beer.usecase.GetBeerPagingDataFlow
import org.koin.dsl.module

val DomainModule = module {
    single {
        GetBeerPagingDataFlow(get(), get())
    }
}