package com.example.domain.model

/**
 * Modelo de dominio que representa una cerveza dentro del sistema.
 * Es agnóstico a cualquier framework (Android, Retrofit, Room, etc.).
 *
 * @property id Identificador único numérico de la cerveza.
 * @property name Nombre principal de la cerveza.
 * @property tagline Lema o frase descriptiva corta.
 * @property firstBrewed Fecha en la que fue elaborada por primera vez (MM/YYYY o YYYY).
 * @property description Descripción detallada de las características y sabor.
 * @property imageUrl URL completa de la imagen de la botella o lata.
 * @property abv Porcentaje de alcohol por volumen (Alcohol By Volume).
 * @property ibu Unidades internacionales de amargor (International Bitterness Units).
 * @property foodPairing Lista de alimentos recomendados para maridar con la cerveza.
 */
data class Item(
    val id: Int,
    val name: String,
    val tagline: String? = null,
    val firstBrewed: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val abv: Double? = null,
    val ibu: Double? = null,
    val foodPairing: List<String>? = null
)
