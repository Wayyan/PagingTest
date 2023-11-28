package com.example.pagingtest.data.paging.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pagingtest.data.cache.room.dao.BeerDao
import com.example.pagingtest.data.common.repository.beer.BeerPagingDataSource
import com.example.pagingtest.data.paging.mediator.BeerRemoteMediator
import com.example.pagingtest.domain.Constant
import com.example.pagingtest.domain.beer.model.BeerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BeerPagingDataSourceImpl constructor(
    private val beerDao: BeerDao,
    private val beerRemoteMediator: BeerRemoteMediator
) : BeerPagingDataSource {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getBeerPagingDataFlow(): Flow<PagingData<BeerModel>> {
        return Pager(
            PagingConfig(Constant.PAGING_MAX_ITEM_COUNT),
            remoteMediator = beerRemoteMediator
        ) {
            beerDao.pagingSource()
        }.flow.map {
            it.map { entity ->
                entity.mapToBeerModel()
            }
        }
    }
}