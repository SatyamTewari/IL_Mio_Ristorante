package com.example.ilmioristorante.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.ilmioristorante.data.local.UnsplashDatabase
import com.example.ilmioristorante.data.paging.UnsplashRemoteMediator
import com.example.ilmioristorante.data.remote.UnsplashApi
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.model.reviews.UserReviews
import com.example.ilmioristorante.model.unsplash.DislikedRestaurants
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import com.example.ilmioristorante.util.Constants.INITIAL_LOAD_MULTIPLIER
import com.example.ilmioristorante.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class RepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : Repository {

    override fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, initialLoadSize = ITEMS_PER_PAGE * INITIAL_LOAD_MULTIPLIER),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchRestaurant(query: String): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getSearchedRestaurant(query) }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, initialLoadSize = ITEMS_PER_PAGE * INITIAL_LOAD_MULTIPLIER),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase,
                query = query
            ),
        ).flow
    }

    override suspend fun getUserDetails(email: String, password: String): User? {
        val response = unsplashDatabase.userDao().getUserDetails(email, password)
        return response
    }

    override suspend fun addUserDetails(user: User): Long {
        return unsplashDatabase.userDao().addUser(user)
    }

    override suspend fun getUnsplashItemById(id: String): UnsplashImage {
        return unsplashDatabase.unsplashImageDao().getUnsplashImageItem(id)
    }

    override suspend fun addUserReview(review: UserReviews): Long {
        return unsplashDatabase.userReviewDao().addUserReview(review)
    }

    override suspend fun getUserReview(id: String): UserReviews {
        return unsplashDatabase.userReviewDao().getUserReview(id)
    }

    override suspend fun addDislikedRestaurant(dislikedRestaurants: DislikedRestaurants): Long {
        return unsplashDatabase.dislikedRestaurantsDao().addDislikedRestaurant(dislikedRestaurants)
    }

    override suspend fun deleteDislikedRestaurant(id: String): Int {
        return unsplashDatabase.dislikedRestaurantsDao().deleteDislikedRestaurant(id)
    }
}