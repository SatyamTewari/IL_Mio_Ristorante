package com.example.ilmioristorante.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ilmioristorante.data.local.RestaurantDatabase
import com.example.ilmioristorante.data.paging.RestaurantRemoteMediator
import com.example.ilmioristorante.data.remote.RestaurantApi
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.model.reviews.UserReviews
import com.example.ilmioristorante.model.restaurant.DislikedRestaurants
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import com.example.ilmioristorante.data.util.Constants.INITIAL_LOAD_MULTIPLIER
import com.example.ilmioristorante.data.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class RepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val restaurantDatabase: RestaurantDatabase
) : Repository {

    override fun getAllImages(): Flow<PagingData<RestaurantModel>> {
        val pagingSourceFactory = { restaurantDatabase.restaurantDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, initialLoadSize = ITEMS_PER_PAGE * INITIAL_LOAD_MULTIPLIER),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchRestaurant(query: String): Flow<PagingData<RestaurantModel>> {
        val pagingSourceFactory = { restaurantDatabase.restaurantDao().getSearchedRestaurant(query) }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, initialLoadSize = ITEMS_PER_PAGE * INITIAL_LOAD_MULTIPLIER),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = RestaurantRemoteMediator(
                restaurantApi = restaurantApi,
                restaurantDatabase = restaurantDatabase,
                query = query
            ),
        ).flow
    }

    override suspend fun getUserDetails(email: String, password: String): User? {
        val response = restaurantDatabase.userDao().getUserDetails(email, password)
        return response
    }

    override suspend fun addUserDetails(user: User): Long {
        return restaurantDatabase.userDao().addUser(user)
    }

    override suspend fun getRestaurantItemById(id: String): RestaurantModel {
        return restaurantDatabase.restaurantDao().getRestaurantItem(id)
    }

    override suspend fun addUserReview(review: UserReviews): Long {
        return restaurantDatabase.userReviewDao().addUserReview(review)
    }

    override suspend fun getUserReview(id: String): UserReviews {
        return restaurantDatabase.userReviewDao().getUserReview(id)
    }

    override suspend fun addDislikedRestaurant(dislikedRestaurants: DislikedRestaurants): Long {
        return restaurantDatabase.dislikedRestaurantsDao().addDislikedRestaurant(dislikedRestaurants)
    }

    override suspend fun deleteDislikedRestaurant(id: String): Int {
        return restaurantDatabase.dislikedRestaurantsDao().deleteDislikedRestaurant(id)
    }
}