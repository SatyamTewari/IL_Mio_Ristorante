package com.example.ilmioristorante.domain.usecase

import androidx.paging.PagingData
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRestaurantUseCase @Inject constructor(
    private val repository: Repository
) {

    fun searchRestaurant(query: String): Flow<PagingData<RestaurantModel>>{
        return repository.searchRestaurant(getMappedQuery(query))
    }

    fun getMappedQuery(query: String): String {
        return "$RESTAURANT_API_QUERY_PREFIX $query"
    }

    private companion object {
        const val RESTAURANT_API_QUERY_PREFIX = "hotel"
    }

}