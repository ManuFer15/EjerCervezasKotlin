package com.example.usecases

import com.example.data.repository.ItemRepository
import com.example.domain.model.Item

/**
 * Caso de uso responsable de recuperar los detalles completos de una cerveza
 * utilizando su identificador único.
 *
 * @property repository Repositorio de datos de cervezas.
 */
class FindItemByIdUseCase(private val repository: ItemRepository) {
    /**
     * Ejecuta la consulta de la cerveza por ID.
     *
     * @param id Identificador único de la cerveza.
     * @return El modelo [Item] encontrado o null si no existe.
     */
    suspend fun invoke(id: Int): Item? = repository.findItemById(id)
}
