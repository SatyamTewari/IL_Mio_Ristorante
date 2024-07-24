package com.example.ilmioristorante.presentation.screens.detail

sealed class DetailUiEvent{

    data class ReviewChanged(val review:String): DetailUiEvent()

    object LoginButtonClicked : DetailUiEvent()
}