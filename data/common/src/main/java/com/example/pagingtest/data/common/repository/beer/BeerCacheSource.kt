package com.example.pagingtest.data.common.repository.beer

import com.example.pagingtest.domain.beer.model.BeerModel

interface BeerCacheSource {
    suspend fun saveNewBeers(beers: List<BeerModel>)
    suspend fun removeAllBeers()
    suspend fun getCountOfBeers(): Int
}