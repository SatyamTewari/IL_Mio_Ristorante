package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import javax.inject.Inject

class GetRestaurantItemByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getRestaurantItemById(id: String): RestaurantModel {
        return repository.getRestaurantItemById(id)
    }
}