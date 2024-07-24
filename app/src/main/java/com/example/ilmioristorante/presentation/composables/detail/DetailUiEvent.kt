package com.example.ilmioristorante.presentation.composables.detail

sealed class DetailUiEvent{

    data class ReviewChanged(val review:String): DetailUiEvent()
    data class DislikeChanged(val dislikeStatus: Boolean): DetailUiEvent()

    data object LoginButtonClicked : DetailUiEvent()
}