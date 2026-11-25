package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.domain.model.Item

class GetItemsUseCase(private val repository: ItemRepository) {
    suspend fun invoke(): List<Item> = repository.getItems()
}
