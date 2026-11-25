package com.example.data

import com.example.data.repository.ItemRepository
import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item
import com.example.testshared.mockedBeerList
import com.example.testshared.mockedItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ItemRepositoryTest {

    @Test
    fun `getItems delegates call to remoteDataSource`() = runBlocking {
        val fakeDataSource = object : RemoteDataSource {
            override suspend fun getItems(query: String?): List<Item> = mockedBeerList
            override suspend fun findItemById(id: Int): Item? = mockedItem
        }
        val repository = ItemRepository(fakeDataSource)

        val items = repository.getItems("punk")
        val item = repository.findItemById(1)

        assertEquals(mockedBeerList, items)
        assertEquals(mockedItem, item)
    }
}
