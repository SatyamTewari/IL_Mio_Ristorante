package com.example.ilmioristorante.domain

import androidx.paging.PagingData
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.model.reviews.UserReviews
import com.example.ilmioristorante.model.restaurant.DislikedRestaurants
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllImages(): Flow<PagingData<RestaurantModel>>
    fun searchRestaurant(query: String): Flow<PagingData<RestaurantModel>>
    suspend fun getUserDetails(email: String, password: String): User?
    suspend fun addUserDetails(user: User): Long
    suspend fun getRestaurantItemById(id: String): RestaurantModel
    suspend fun addUserReview(review: UserReviews): Long
    suspend fun getUserReview(id: String): UserReviews
    suspend fun addDislikedRestaurant(dislikedRestaurants: DislikedRestaurants): Long
    suspend fun deleteDislikedRestaurant(id: String): Int
}