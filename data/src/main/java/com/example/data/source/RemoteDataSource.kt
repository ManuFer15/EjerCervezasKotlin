package com.example.data.source

import com.example.domain.model.Item

/**
 * Contrato (interfaz) para la fuente de datos remota de cervezas.
 * Define la abstracción necesaria para que la capa de datos pueda solicitar información sin depender de Retrofit.
 */
interface RemoteDataSource {
    /**
     * Obtiene una lista de cervezas desde la fuente remota, opcionalmente filtradas por nombre.
     *
     * @param query Término de búsqueda opcional para filtrar cervezas por nombre.
     * @return Lista de modelos de dominio [Item].
     */
    suspend fun getItems(query: String? = null): List<Item>

    /**
     * Busca una cerveza específica mediante su ID único.
     *
     * @param id Identificador único de la cerveza.
     * @return Objeto [Item] si se encuentra, o null en caso contrario.
     */
    suspend fun findItemById(id: Int): Item?
}
