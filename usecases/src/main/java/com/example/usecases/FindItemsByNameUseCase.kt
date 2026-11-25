package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.domain.model.Item

class FindItemsByNameUseCase(private val repository: ItemRepository) {
    suspend fun invoke(name: String): List<Item> = repository.findItemsByName(name)
}
