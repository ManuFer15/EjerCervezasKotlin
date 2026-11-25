package com.example.cervezas.server

import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item

class ServerDataSource : RemoteDataSource {
    override suspend fun getItems(): List<Item> =
        RemoteApi.service.getItems()

    override suspend fun findItemsByName(name: String): List<Item> =
        RemoteApi.service.findItemsByName(name)

    override suspend fun findItemById(id: Int): List<Item> =
        RemoteApi.service.findItemById(id.toString())
}
