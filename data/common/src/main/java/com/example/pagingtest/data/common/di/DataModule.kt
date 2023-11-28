package com.example.pagingtest.data.common.di

import com.example.pagingtest.data.common.repository.beer.BeerRepositoryImpl
import com.example.pagingtest.domain.beer.repository.BeerRepository
import org.koin.dsl.module

val DataModule = module {
    single<BeerRepository> {
        BeerRepositoryImpl(get())
    }
}