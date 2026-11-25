package com.example.data.repository

import com.example.data.source.RemoteDataSource
import com.example.domain.model.Item

/**
 * Repositorio central de datos para la entidad Cerveza ([Item]).
 * Actúa como punto único de acceso a los datos, desacoplando los casos de uso de la procedencia de la información.
 *
 * @property remoteDataSource Abstracción de la fuente de datos remota.
 */
class ItemRepository(private val remoteDataSource: RemoteDataSource) {

    /**
     * Obtiene el listado de cervezas (filtrado o completo).
     *
     * @param query Texto introducido por el usuario para filtrar.
     */
    suspend fun getItems(query: String? = null): List<Item> =
        remoteDataSource.getItems(query)

    /**
     * Obtiene los detalles de una cerveza a partir de su ID.
     *
     * @param id Identificador de la cerveza.
     */
    suspend fun findItemById(id: Int): Item? =
        remoteDataSource.findItemById(id)
}
