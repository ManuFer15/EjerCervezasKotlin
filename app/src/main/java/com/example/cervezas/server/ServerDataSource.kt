package com.example.cervezas.server

import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item

/**
 * Implementación concreta de la interfaz [RemoteDataSource] que utiliza Retrofit para consumir Punk API v3.
 * Traduce las respuestas DTO de red ([BeerDto]) hacia modelos puros de dominio ([Item]).
 */
class ServerDataSource : RemoteDataSource {

    override suspend fun getItems(query: String?): List<Item> {
        val searchQuery = query?.trim()?.takeIf { it.isNotEmpty() }
        val dtos = RemoteApi.service.getBeers(page = 1, beerName = searchQuery)
        return dtos.map { it.toDomain() }
    }

    override suspend fun findItemById(id: Int): Item? {
        return try {
            RemoteApi.service.getBeerById(id).toDomain()
        } catch (_: Exception) {
            null
        }
    }
}
