package com.example.ilmioristorante.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.ilmioristorante.domain.usecase.GetAllImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllImagesUseCase: GetAllImagesUseCase,
): ViewModel() {
    val getAllImages = getAllImagesUseCase.getAllImages()
}