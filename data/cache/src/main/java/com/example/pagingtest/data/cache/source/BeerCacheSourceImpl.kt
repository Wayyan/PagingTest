package com.example.pagingtest.data.cache.source

import com.example.pagingtest.data.cache.room.dao.BeerDao
import com.example.pagingtest.data.cache.room.entity.BeerEntity
import com.example.pagingtest.data.common.repository.beer.BeerCacheSource
import com.example.pagingtest.domain.beer.model.BeerModel
import timber.log.Timber

class BeerCacheSourceImpl constructor(
    private val beerDao: BeerDao
) : BeerCacheSource {
    override suspend fun saveNewBeers(beers: List<BeerModel>) {
        Timber.d("count -> ${beers.size}")
        beerDao.insertAll(beers.map {
            BeerEntity(
                id = it.id,
                name = it.name,
                tagline = it.tagline,
                description = it.description,
                imageUrl = it.imageUrl
            )
        })
    }

    override suspend fun removeAllBeers() {
        beerDao.deleteAll()
    }

    override suspend fun getCountOfBeers(): Int {
        return beerDao.beersCount()
    }
}