package com.example.pagingtest.data.common.repository.beer

import androidx.paging.PagingData
import com.example.pagingtest.domain.beer.model.BeerModel
import com.example.pagingtest.domain.beer.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class BeerRepositoryImpl constructor(
    private val beerPagingDataSource: BeerPagingDataSource
) :
    BeerRepository {
    override suspend fun getBeerPagingDataFlow(): Flow<PagingData<BeerModel>> {
        return beerPagingDataSource.getBeerPagingDataFlow()
    }

}