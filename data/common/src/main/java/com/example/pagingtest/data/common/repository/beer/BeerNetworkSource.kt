package com.example.pagingtest.data.common.repository.beer

import com.example.pagingtest.domain.beer.model.BeerModel

interface BeerNetworkSource {
   suspend fun fetchBeers(page: Int): List<BeerModel>
}