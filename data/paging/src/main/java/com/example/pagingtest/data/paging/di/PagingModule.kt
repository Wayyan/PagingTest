package com.example.pagingtest.data.paging.di

import com.example.pagingtest.data.common.repository.beer.BeerPagingDataSource
import com.example.pagingtest.data.paging.mediator.BeerRemoteMediator
import com.example.pagingtest.data.paging.source.BeerPagingDataSourceImpl
import org.koin.dsl.module

val PagingModule = module {
    single {
        BeerRemoteMediator(get(), get())
    }

    single<BeerPagingDataSource> {
        BeerPagingDataSourceImpl(get(), get())
    }
}