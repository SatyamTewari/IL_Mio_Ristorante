package com.example.ilmioristorante.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ilmioristorante.domain.usecase.AddDislikedRestaurantUseCase
import com.example.ilmioristorante.domain.usecase.AddUserReviewUseCase
import com.example.ilmioristorante.domain.usecase.DeleteDislikedRestaurantUseCase
import com.example.ilmioristorante.domain.usecase.GetReviewUseCase
import com.example.ilmioristorante.domain.usecase.GetRestaurantItemByIdUseCase
import com.example.ilmioristorante.presentation.composables.detail.DetailUiEvent
import com.example.ilmioristorante.presentation.composables.detail.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVIewModel @Inject constructor(
    private val getReviewUseCase: GetReviewUseCase,
    private val getRestaurantItemByIdUseCase: GetRestaurantItemByIdUseCase,
    private val addUserReviewUseCase: AddUserReviewUseCase,
    private val addDislikedRestaurantUseCase: AddDislikedRestaurantUseCase,
    private val deleteDislikedRestaurantUseCase: DeleteDislikedRestaurantUseCase
) : ViewModel() {

    private val _detailUiState = mutableStateOf(DetailUiState())
    val detailUiState: State<DetailUiState>
        get() = _detailUiState

    private val _userReview = mutableStateOf<String>("")
    val userReview: State<String>
        get() = _userReview

    fun getReview(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getReviewUseCase.getReview(id)
            _detailUiState.value = detailUiState.value.copy(review = response)
            _userReview.value = response
        }
    }

    fun addUserReview() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = addUserReviewUseCase.addUserReview(
                _detailUiState.value.review,
                _detailUiState.value.id
            )
            if (response > 0) {
                _userReview.value = _detailUiState.value.review
            }
        }
    }

    fun getRestaurantItem(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRestaurantItemByIdUseCase.getRestaurantItemById(id)
            _detailUiState.value = detailUiState.value.copy(
                data = response
            )
        }
    }

    fun setDefaultDetailUiState(id: String) {
        _detailUiState.value = detailUiState.value.copy(id = id)
    }

    fun addDislikedRestaurant(dislikeStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Long = if (dislikeStatus) {
                addDislikedRestaurantUseCase.addDislikedRestaurant(_detailUiState.value.id)
            } else {
                deleteDislikedRestaurantUseCase.deleteDislikedRestaurant(_detailUiState.value.id).toLong()
            }

            if (response > 0) {
                _detailUiState.value = detailUiState.value.copy(
                    dislikeStatus = dislikeStatus
                )
            }
        }
    }

    fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.ReviewChanged -> {
                _detailUiState.value = detailUiState.value.copy(
                    review = event.review
                )
                validateUserReview()
            }

            is DetailUiEvent.LoginButtonClicked -> {
                addUserReview()
            }

            is DetailUiEvent.DislikeChanged -> {
                addDislikedRestaurant(event.dislikeStatus)
            }
        }
    }

    fun validateUserReview() {
        _detailUiState.value =
            detailUiState.value.copy(reviewError = detailUiState.value.review.isNullOrEmpty())
    }
}