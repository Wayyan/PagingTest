package com.example.pagingtest.data.cache.di

import android.content.Context
import androidx.room.Room
import androidx.room.invalidationTrackerFlow
import com.example.pagingtest.data.cache.room.BeerAppDatabase
import com.example.pagingtest.data.cache.room.dao.BeerDao
import com.example.pagingtest.domain.Constant

object RoomProvider {
    fun provideBeerAppDatabase(context: Context): BeerAppDatabase {
        return Room.databaseBuilder(
            context,
            BeerAppDatabase::class.java,
            Constant.Database.NAME
        )
            .build()
    }

    fun provideBeerDao(database: BeerAppDatabase):BeerDao{
        return database.beerDao()
    }
}