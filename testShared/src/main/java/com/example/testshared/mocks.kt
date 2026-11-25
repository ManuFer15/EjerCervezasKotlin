package com.example.testshared

import com.example.domain.model.Item

val mockedItem = Item(
    id = 1,
    name = "Punk IPA 2007 - 2010",
    tagline = "Post Modern Classic.",
    firstBrewed = "04/2007",
    description = "Our flagship beer that kick started the craft beer revolution.",
    imageUrl = "https://punkapi.dev/api/v3/images/001.png",
    abv = 6.0,
    ibu = 60.0,
    foodPairing = listOf("Spicy carne asada", "Cheesecake")
)

val mockedBeerList = listOf(
    mockedItem,
    Item(
        id = 2,
        name = "Punk IPA 2010 - Current",
        tagline = "Amplified.",
        firstBrewed = "10/2010",
        description = "Dry hopped beer.",
        imageUrl = "https://punkapi.dev/api/v3/images/002.png",
        abv = 5.6,
        ibu = 40.0,
        foodPairing = listOf("Chicken tacos")
    )
)
