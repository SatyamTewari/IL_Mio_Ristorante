package com.example.ilmioristorante.presentation.screens.detail

import com.example.ilmioristorante.model.unsplash.UnsplashImage

data class DetailUiState(
    var id: String = "",
    var review: String = "",
    var data: UnsplashImage? = null,
    var dislikeStatus: Boolean = false,

    var reviewError: Boolean = false,
)
