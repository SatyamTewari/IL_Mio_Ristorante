package com.example.ilmioristorante.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResults(
    @SerialName("results")
    val images: List<UnsplashImage>
)
