package com.example.pagingtest.data.cache.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pagingtest.data.cache.room.entity.BeerEntity
import com.example.pagingtest.domain.Constant

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<BeerEntity>)

    @Query("DELETE FROM ${Constant.Database.BEER_TABLE_NAME}")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM ${Constant.Database.BEER_TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, BeerEntity>

    @Query("SELECT * FROM ${Constant.Database.BEER_TABLE_NAME} LIMIT :limit OFFSET :offset")
    suspend fun getByPage(offset: Int, limit: Int = Constant.PAGING_MAX_ITEM_COUNT): List<BeerEntity>

    @Query("SELECT COUNT(${Constant.Database.BEER_FIELD_NAME}) FROM ${Constant.Database.BEER_TABLE_NAME}")
    suspend fun beersCount(): Int
}