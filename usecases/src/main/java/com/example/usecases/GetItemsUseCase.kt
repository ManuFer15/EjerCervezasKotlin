package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.domain.model.Item

/**
 * Caso de uso responsable de obtener la lista de cervezas del sistema,
 * permitiendo aplicar un filtro de búsqueda por nombre en tiempo real.
 *
 * @property repository Repositorio de datos de cervezas.
 */
class GetItemsUseCase(private val repository: ItemRepository) {
    /**
     * Ejecuta el caso de uso.
     *
     * @param query Término opcional introducido en el buscador.
     * @return Lista de modelos de dominio [Item].
     */
    suspend fun invoke(query: String? = null): List<Item> = repository.getItems(query)
}
