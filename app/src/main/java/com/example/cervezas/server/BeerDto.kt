package com.example.cervezas.server

import com.example.domain.model.Item
import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) para mapear la respuesta JSON que retorna la API Punk API v3.
 */
data class BeerDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("first_brewed") val first_brewed: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("abv") val abv: Double?,
    @SerializedName("ibu") val ibu: Double?,
    @SerializedName("food_pairing") val food_pairing: List<String>?
)

/**
 * Convierte este [BeerDto] de red en un modelo de dominio [Item],
 * construyendo la URL completa de la imagen almacenada en la API v3.
 */
fun BeerDto.toDomain(): Item {
    val fullImageUrl = if (!image.isNullOrEmpty()) {
        if (image.startsWith("http")) image else "https://punkapi.dev/api/v3/images/$image"
    } else null

    return Item(
        id = id,
        name = name,
        tagline = tagline,
        firstBrewed = first_brewed,
        description = description,
        imageUrl = fullImageUrl,
        abv = abv,
        ibu = ibu,
        foodPairing = food_pairing
    )
}
