package com.example.ilmioristorante.presentation.screens.detail

sealed class DetailUiEvent{

    data class ReviewChanged(val review:String): DetailUiEvent()
    data class DislikeChanged(val dislikeStatus: Boolean): DetailUiEvent()

    data object LoginButtonClicked : DetailUiEvent()
}