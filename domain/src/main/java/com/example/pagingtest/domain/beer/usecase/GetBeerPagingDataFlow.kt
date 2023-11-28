package com.example.pagingtest.domain.beer.usecase

import androidx.paging.PagingData
import com.example.pagingtest.domain.DispatcherProvider
import com.example.pagingtest.domain.FlowUseCase
import com.example.pagingtest.domain.beer.model.BeerModel
import com.example.pagingtest.domain.beer.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class GetBeerPagingDataFlow constructor(
    private val repository: BeerRepository,
    dispatcherProvider: DispatcherProvider
) : FlowUseCase<Unit, PagingData<BeerModel>>(dispatcherProvider) {
    override suspend fun provide(params: Unit): Flow<PagingData<BeerModel>> {
        return repository.getBeerPagingDataFlow()
    }
}