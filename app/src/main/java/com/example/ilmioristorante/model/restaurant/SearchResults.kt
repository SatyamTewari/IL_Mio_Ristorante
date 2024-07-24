package com.example.ilmioristorante.model.restaurant

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResults(
    @SerialName("results")
    val images: List<RestaurantModel>
)