package com.example.ilmioristorante.domain.usecase

import androidx.paging.PagingData
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllImagesUseCase @Inject constructor(
    private val repository: Repository
) {

    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        return repository.getAllImages()
    }
}