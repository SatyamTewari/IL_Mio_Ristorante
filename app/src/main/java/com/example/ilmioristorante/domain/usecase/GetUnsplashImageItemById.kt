package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import javax.inject.Inject

class GetUnsplashImageItemById @Inject constructor(
    private val repository: Repository
) {

    suspend fun getImageItemById(id: String): UnsplashImage {
        return repository.getUnsplashItemById(id)
    }
}