package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.domain.model.Item

class FindItemByIdUseCase(private val repository: ItemRepository) {
    suspend fun invoke(id: Int): List<Item> = repository.findItemById(id)
}
