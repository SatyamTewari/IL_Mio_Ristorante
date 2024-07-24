package com.example.ilmioristorante.presentation.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import com.example.ilmioristorante.model.unsplash.Urls
import com.example.ilmioristorante.model.unsplash.User
import com.example.ilmioristorante.model.unsplash.UserLinks
import com.example.ilmioristorante.presentation.screens.common.ButtonComponent
import com.example.ilmioristorante.presentation.screens.common.NormalTextComponent
import com.example.ilmioristorante.presentation.screens.common.SmallTextComponent
import com.example.ilmioristorante.presentation.screens.common.UnsplashItem
import com.example.ilmioristorante.presentation.screens.login.LoginUIEvent
import com.example.ilmioristorante.presentation.viewmodels.DetailVIewModel

@Composable
fun DetailScreen(
    id: String,
    detailVIewModel: DetailVIewModel = hiltViewModel(),
) {
    val userReview = detailVIewModel.userReview
    val detailUiState = detailVIewModel.detailUiState

    LaunchedEffect(key1 = Unit) {
        detailVIewModel.setDefaultDetailUiState(id)
        detailVIewModel.getUnsplashItem(id)
        detailVIewModel.getReview(id)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnsplashItem(detailUiState.value.data, {})
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