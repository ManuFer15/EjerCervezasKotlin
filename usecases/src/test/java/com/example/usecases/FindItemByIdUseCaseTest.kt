package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item
import com.example.testshared.mockedItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FindItemByIdUseCaseTest {

    @Test
    fun `invoke returns correct beer by id`() = runBlocking {
        val fakeDataSource = object : RemoteDataSource {
            override suspend fun getItems(query: String?): List<Item> = emptyList()
            override suspend fun findItemById(id: Int): Item? = if (id == 1) mockedItem else null
        }
        val repository = ItemRepository(fakeDataSource)
        val useCase = FindItemByIdUseCase(repository)

        val result = useCase.invoke(1)

        assertEquals(mockedItem, result)
        assertEquals(1, result?.id)
    }
}
