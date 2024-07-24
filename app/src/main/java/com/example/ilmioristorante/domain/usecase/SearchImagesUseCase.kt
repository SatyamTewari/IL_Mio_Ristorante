package com.example.ilmioristorante.domain.usecase

import androidx.paging.PagingData
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val repository: Repository
) {

    fun searchImages(query: String): Flow<PagingData<UnsplashImage>>{
        return repository.searchImages(query)
    }

}