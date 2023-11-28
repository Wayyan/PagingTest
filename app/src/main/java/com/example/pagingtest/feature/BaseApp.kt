package com.example.pagingtest.feature

import android.app.Application
import com.example.pagingtest.base.di.BaseAppModule
import com.example.pagingtest.data.cache.di.CacheModule
import com.example.pagingtest.data.common.di.DataModule
import com.example.pagingtest.data.network.BuildConfig
import com.example.pagingtest.data.network.di.NetworkModule
import com.example.pagingtest.data.paging.di.PagingModule
import com.example.pagingtest.di.AppModule
import com.example.pagingtest.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@BaseApp)
            androidLogger()

            modules(
                listOf(
                    BaseAppModule,
                    AppModule,
                    DataModule,
                    NetworkModule,
                    CacheModule,
                    PagingModule,
                    DomainModule
                )
            )
        }
    }
}