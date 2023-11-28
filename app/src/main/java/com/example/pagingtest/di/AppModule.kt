package com.example.pagingtest.di

import com.example.pagingtest.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel {
        MainViewModel(get())
    }
}