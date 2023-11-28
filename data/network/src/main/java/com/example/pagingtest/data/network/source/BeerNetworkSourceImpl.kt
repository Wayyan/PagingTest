package com.example.pagingtest.data.network.source

import com.example.pagingtest.data.common.repository.beer.BeerNetworkSource
import com.example.pagingtest.data.network.api.BeerApiServices
import com.example.pagingtest.data.network.extension.executeOrThrow
import com.example.pagingtest.domain.exception.BadRequestException
import com.example.pagingtest.domain.beer.model.BeerModel
import timber.log.Timber

class BeerNetworkSourceImpl constructor(private val beerApiServices: BeerApiServices) :
    BeerNetworkSource {
    override suspend fun fetchBeers(page: Int): List<BeerModel> {
        Timber.d("page->$page")
        return try {
            val rawResponse = beerApiServices.getPopularMovies(page).executeOrThrow()

            rawResponse.map {
                it.mapToBeerModel()
            }
        } catch (e: BadRequestException) {
            emptyList()
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }
}