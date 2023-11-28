package com.example.pagingtest.data.cache.room.entity

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingtest.domain.Constant
import com.example.pagingtest.domain.beer.model.BeerModel


@Entity(
    tableName = Constant.Database.BEER_TABLE_NAME
)
data class BeerEntity(
    @PrimaryKey
    @ColumnInfo(name = Constant.Database.BEER_FIELD_ID)
    val id: Int,
    @ColumnInfo(name = Constant.Database.BEER_FIELD_NAME)
    val name: String,
    @ColumnInfo(name = Constant.Database.BEER_FIELD_TAGLINE)
    val tagline: String,
    @ColumnInfo(name = Constant.Database.BEER_FIELD_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = Constant.Database.BEER_FIELD_IMAGE_URL)
    val imageUrl: String
) {
    fun mapToBeerModel() =
        BeerModel(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl
        )
}
