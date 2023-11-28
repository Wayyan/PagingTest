package com.example.pagingtest.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagingtest.data.cache.room.dao.BeerDao
import com.example.pagingtest.data.cache.room.entity.BeerEntity

@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
abstract class BeerAppDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}