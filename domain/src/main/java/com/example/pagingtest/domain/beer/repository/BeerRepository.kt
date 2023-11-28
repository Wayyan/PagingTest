package com.example.pagingtest.domain.beer.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.pagingtest.domain.beer.model.BeerModel
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    suspend fun getBeerPagingDataFlow(): Flow<PagingData<BeerModel>>
}