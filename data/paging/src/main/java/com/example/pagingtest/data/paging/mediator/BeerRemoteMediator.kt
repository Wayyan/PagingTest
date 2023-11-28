package com.example.pagingtest.data.paging.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingtest.data.cache.room.entity.BeerEntity
import com.example.pagingtest.data.common.repository.beer.BeerCacheSource
import com.example.pagingtest.data.common.repository.beer.BeerNetworkSource
import com.example.pagingtest.domain.Constant
import kotlinx.coroutines.delay

private const val START_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator constructor(
    private val networkSource: BeerNetworkSource,
    private val cacheSource: BeerCacheSource
) :
    RemoteMediator<Int, BeerEntity>() {

    override suspend fun initialize(): InitializeAction {
        val cachedItemCount = cacheSource.getCountOfBeers()

        return if (cachedItemCount == 0) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        try {
            val pageIndex = when (loadType) {
                LoadType.REFRESH -> {
                    cacheSource.removeAllBeers()
                    START_PAGE_INDEX
                }

                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val previousIndex =
                        cacheSource.getCountOfBeers() / Constant.PAGING_MAX_ITEM_COUNT
                    previousIndex + 1
                }
            }

            val newBeers = networkSource.fetchBeers(pageIndex)

            cacheSource.saveNewBeers(newBeers)

            return MediatorResult.Success(newBeers.size < Constant.PAGING_MAX_ITEM_COUNT)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }
}