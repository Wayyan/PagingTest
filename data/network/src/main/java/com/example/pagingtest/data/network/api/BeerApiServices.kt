package com.example.pagingtest.data.network.api

import com.example.pagingtest.data.network.api.response.BeerResponse
import com.example.pagingtest.domain.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApiServices {
    @GET(ApiRoute.BEERS)
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = Constant.PAGING_MAX_ITEM_COUNT
    ): Call<List<BeerResponse>>
}