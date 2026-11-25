package com.example.cervezas.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz de servicio de Retrofit para definir las llamadas HTTP a la API REST de Punk API v3.
 */
interface RemoteApiService {

    /**
     * Consulta el catálogo de cervezas con soporte para paginación y búsqueda por nombre.
     */
    @GET("api/v3/beers")
    suspend fun getBeers(
        @Query("page") page: Int = 1,
        @Query("beer_name") beerName: String? = null
    ): List<BeerDto>

    /**
     * Consulta los datos de una única cerveza por su ID.
     */
    @GET("api/v3/beers/{id}")
    suspend fun getBeerById(@Path("id") id: Int): BeerDto
}
