package com.example.cervezas.server

import com.example.domain.model.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {

    @GET("items")
    suspend fun getItems(): List<Item>

    @GET("items")
    suspend fun findItemsByName(@Query("name") name: String): List<Item>

    @GET("items")
    suspend fun findItemById(@Query("id") id: String): List<Item>
}
