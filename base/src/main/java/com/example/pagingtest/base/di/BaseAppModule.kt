package com.example.pagingtest.base.di

import com.example.pagingtest.base.DefaultDispatcherProvider
import com.example.pagingtest.domain.DispatcherProvider
import org.koin.dsl.module

val BaseAppModule = module {
    single<DispatcherProvider> {
        DefaultDispatcherProvider()
    }
}