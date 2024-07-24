package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.unsplash.DislikedRestaurants
import javax.inject.Inject

class AddDislikedRestaurantUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun addDislikedRestaurant(id: String): Long {
        return repository.addDislikedRestaurant(DislikedRestaurants(id = id, disliked = true))
    }
}