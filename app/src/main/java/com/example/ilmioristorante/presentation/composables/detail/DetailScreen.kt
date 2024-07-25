package com.example.ilmioristorante.presentation.composables.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ilmioristorante.presentation.composables.common.ButtonComponent
import com.example.ilmioristorante.presentation.composables.common.NormalTextComponent
import com.example.ilmioristorante.presentation.composables.common.SmallTextComponent
import com.example.ilmioristorante.presentation.composables.common.RestaurantItem
import com.example.ilmioristorante.presentation.viewmodels.DetailVIewModel
import com.example.ilmioristorante.presentation.util.Screen

@Composable
fun DetailScreen(
    id: String,
    detailVIewModel: DetailVIewModel = hiltViewModel(),
) {
    val userReview = detailVIewModel.userReview
    val detailUiState = detailVIewModel.detailUiState

    LaunchedEffect(key1 = Unit) {
        detailVIewModel.setDefaultDetailUiState(id)
        detailVIewModel.getRestaurantItem(id)
        detailVIewModel.getReview(id)
    }

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            RestaurantItem(
                detailUiState.value.data,
                source = Screen.Detail,
                onThumbsDownClicked = {
                    detailVIewModel.onEvent(DetailUiEvent.DislikeChanged(!detailUiState.value.dislikeStatus))
                },
                dislikeStatus = detailUiState.value.dislikeStatus
            )
            Spacer(modifier = Modifier.height(20.dp))
            NormalTextComponent(value = "Review")
            SmallTextComponent(
                value = if (userReview.value.isNullOrEmpty()) {
                    "No Reviews available "
                } else {
                    userReview.value
                }
            )
            TextField(
                value = detailUiState.value.review,
                placeholder = {
                    Text(
                        text = "Add your review here.",
                        color = Color.Black
                    )
                },
                onValueChange = {
                    detailVIewModel.onEvent(DetailUiEvent.ReviewChanged(it))
                })

            Spacer(modifier = Modifier.height(40.dp))
            Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                ButtonComponent(
                    value = "Update Review",
                    onButtonClicked = {
                        detailVIewModel.onEvent(DetailUiEvent.LoginButtonClicked)
                    },
                    isEnabled = !detailUiState.value.reviewError
                )
            }
        }
    }

}