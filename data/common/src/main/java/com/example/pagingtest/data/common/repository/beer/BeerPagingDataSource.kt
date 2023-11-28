package com.example.pagingtest.data.common.repository.beer

import androidx.paging.PagingData
import com.example.pagingtest.domain.beer.model.BeerModel
import kotlinx.coroutines.flow.Flow

interface BeerPagingDataSource {
    suspend fun getBeerPagingDataFlow(): Flow<PagingData<BeerModel>>
}