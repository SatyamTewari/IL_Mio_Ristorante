package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import javax.inject.Inject

class DeleteDislikedRestaurantUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun deleteDislikedRestaurant(id: String): Int {
        return repository.deleteDislikedRestaurant(id)
    }
}