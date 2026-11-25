package com.example.data.source

import com.example.domain.model.Item

interface RemoteDataSource {
    suspend fun getItems(): List<Item>
    suspend fun findItemsByName(name: String): List<Item>
    suspend fun findItemById(id: Int): List<Item>
}
