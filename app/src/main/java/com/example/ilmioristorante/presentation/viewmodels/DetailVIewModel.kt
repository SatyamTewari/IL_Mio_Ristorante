package com.example.ilmioristorante.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilmioristorante.domain.usecase.AddUserReviewUseCase
import com.example.ilmioristorante.domain.usecase.GetReviewUseCase
import com.example.ilmioristorante.domain.usecase.GetUnsplashImageItemById
import com.example.ilmioristorante.model.reviews.UserReviews
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import com.example.ilmioristorante.presentation.screens.detail.DetailUiEvent
import com.example.ilmioristorante.presentation.screens.detail.DetailUiState
import com.example.ilmioristorante.presentation.screens.login.LoginUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVIewModel @Inject constructor(
    private val getReviewUseCase: GetReviewUseCase,
    private val getUnsplashImageItemById: GetUnsplashImageItemById,
    private val addUserReviewUseCase: AddUserReviewUseCase,
) : ViewModel() {

    private val _detailUiState = mutableStateOf(DetailUiState())
    val detailUiState : State<DetailUiState>
        get() = _detailUiState

    private val _userReview = mutableStateOf<String>("")
    val userReview: State<String>
        get() = _userReview

    fun getReview(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = getReviewUseCase.getReview(id)
            _detailUiState.value = detailUiState.value.copy(review = response)
            _userReview.value = response
        }
    }

    fun addUserReview(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = addUserReviewUseCase.addUserReview(_detailUiState.value.review, _detailUiState.value.id)
            if(response > 0){
                _userReview.value = _detailUiState.value.review
            }
        }
    }

    fun getUnsplashItem(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUnsplashImageItemById.getImageItemById(id)
            _detailUiState.value = detailUiState.value.copy(
                data = response
            )
        }
    }

    fun setDefaultDetailUiState(id: String){
        _detailUiState.value = detailUiState.value.copy(id = id)
    }

    fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.ReviewChanged -> {
                _detailUiState.value = detailUiState.value.copy(
                    review = event.review
                )
            }

            is DetailUiEvent.LoginButtonClicked -> {
                addUserReview()
            }
        }
        validateUserReview()
    }

    fun validateUserReview(){
        _detailUiState.value = detailUiState.value.copy(reviewError = detailUiState.value.review.isNullOrEmpty())
    }
}