package com.example.ilmioristorante.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.ilmioristorante.data.local.RestaurantDatabase
import com.example.ilmioristorante.data.remote.RestaurantApi
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import com.example.ilmioristorante.model.remoteKeys.RestaurantRemoteKeys
import com.example.ilmioristorante.data.util.Constants.ITEMS_PER_PAGE

@ExperimentalPagingApi
class RestaurantRemoteMediator(
    private val restaurantApi: RestaurantApi,
    private val restaurantDatabase: RestaurantDatabase,
    private val query: String,
) : RemoteMediator<Int, RestaurantModel>() {

    private val restaurantDao = restaurantDatabase.restaurantDao()
    private val restaurantRemoteKeysDao = restaurantDatabase.restaurantRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RestaurantModel>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = restaurantApi.searchImages(
                query = query,
                page = currentPage,
                perPage = ITEMS_PER_PAGE
            )
            val endOfPaginationReached = response.images.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            restaurantDatabase.withTransaction {

                val keys = response.images.map {  restaurant ->
                    RestaurantRemoteKeys(
                        id = restaurant.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                val images = response.images.map { restaurant ->
                    RestaurantModel(
                        id = restaurant.id,
                        urls = restaurant.urls,
                        user = restaurant.user,
                        query = query
                    )
                }

                restaurantRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                restaurantDao.addImages(images = images)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RestaurantModel>
    ): RestaurantRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                restaurantRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, RestaurantModel>
    ): RestaurantRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { restaurant ->
                restaurantRemoteKeysDao.getRemoteKeys(id = restaurant.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, RestaurantModel>
    ): RestaurantRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { restaurant ->
                restaurantRemoteKeysDao.getRemoteKeys(id = restaurant.id)
            }
    }
}