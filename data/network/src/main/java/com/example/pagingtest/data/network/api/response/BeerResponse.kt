package com.example.pagingtest.data.network.api.response

import com.example.pagingtest.domain.beer.model.BeerModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "description") val description: String,
    @Json(name = "image_url") val imageUrl: String?
) {
    fun mapToBeerModel(): BeerModel {
        return BeerModel(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl ?: "https://images.punkapi.com/v2/keg.png"
        )
    }
}
