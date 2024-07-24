package com.example.ilmioristorante.domain.usecase

import androidx.paging.PagingData
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import com.example.ilmioristorante.util.getMappedQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRestaurantUseCase @Inject constructor(
    private val repository: Repository
) {

    fun searchRestaurant(query: String): Flow<PagingData<RestaurantModel>>{
        return repository.searchRestaurant(getMappedQuery(query))
    }

}