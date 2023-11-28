package com.example.pagingtest.data.cache.room.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pagingtest.data.cache.room.BeerAppDatabase
import com.example.pagingtest.data.cache.room.entity.BeerEntity
import com.example.pagingtest.domain.Constant
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeerDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: BeerDao
    private lateinit var database: BeerAppDatabase

    @Before
    fun setUp() {

        database = Room.databaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BeerAppDatabase::class.java,
            Constant.Database.NAME
        )
            .build()

        dao = database.beerDao()

    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveBeersTest() {
        runBlocking {
            val beers = listOf(
                BeerEntity(
                    1,
                    "Test 1",
                    "Tagline 2",
                    "Description 1",
                    "http://localhost:8000/1.png"
                ),
                BeerEntity(
                    2,
                    "Test 2",
                    "Tagline 2",
                    "Description 2",
                    "http://localhost:8000/2.png"
                )
            )

            dao.insertAll(beers)

            val count = dao.beersCount()

            Truth.assertThat(count).isEqualTo(beers.size)
        }
    }

    @Test
    fun deleteAllBeersTest() {
        runBlocking {
            val beers = listOf(
                BeerEntity(
                    1,
                    "Test 1",
                    "Tagline 2",
                    "Description 1",
                    "http://localhost:8000/1.png"
                ),
                BeerEntity(
                    2,
                    "Test 2",
                    "Tagline 2",
                    "Description 2",
                    "http://localhost:8000/2.png"
                )
            )

            dao.insertAll(beers)

            dao.deleteAll()

            val count = dao.beersCount()

            Truth.assertThat(count).isEqualTo(0)
        }
    }
}