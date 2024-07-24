package com.example.ilmioristorante.presentation.composables.detail

import com.example.ilmioristorante.model.restaurant.RestaurantModel

data class DetailUiState(
    var id: String = "",
    var review: String = "",
    var data: RestaurantModel? = null,
    var dislikeStatus: Boolean = false,

    var reviewError: Boolean = false,
)
