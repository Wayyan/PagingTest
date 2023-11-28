package com.example.pagingtest.data.cache.di

import com.example.pagingtest.data.cache.source.BeerCacheSourceImpl
import com.example.pagingtest.data.common.repository.beer.BeerCacheSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val CacheModule = module {
    single {
        RoomProvider.provideBeerAppDatabase(androidContext())
    }

    single {
        RoomProvider.provideBeerDao(get())
    }

    single<BeerCacheSource> {
        BeerCacheSourceImpl(get())
    }
}