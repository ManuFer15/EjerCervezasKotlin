package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item
import com.example.testshared.mockedBeerList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetItemsUseCaseTest {

    @Test
    fun `invoke calls repository getItems and returns beer list`() = runBlocking {
        val fakeDataSource = object : RemoteDataSource {
            override suspend fun getItems(query: String?): List<Item> = mockedBeerList
            override suspend fun findItemById(id: Int): Item? = null
        }
        val repository = ItemRepository(fakeDataSource)
        val useCase = GetItemsUseCase(repository)

        val result = useCase.invoke()

        assertEquals(mockedBeerList, result)
        assertEquals(2, result.size)
    }
}
