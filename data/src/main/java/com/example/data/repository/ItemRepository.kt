package com.example.data.repository

import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item

class ItemRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getItems(): List<Item> = remoteDataSource.getItems()

    suspend fun findItemsByName(name: String): List<Item> =
        remoteDataSource.findItemsByName(name)

    suspend fun findItemById(id: Int): List<Item> =
        remoteDataSource.findItemById(id)
}
