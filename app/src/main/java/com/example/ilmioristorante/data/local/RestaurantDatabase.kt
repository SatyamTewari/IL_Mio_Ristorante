package com.example.ilmioristorante.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ilmioristorante.data.local.dao.DislikedRestaurantsDao
import com.example.ilmioristorante.data.local.dao.RestaurantDao
import com.example.ilmioristorante.data.local.dao.RestaurantRemoteKeysDao
import com.example.ilmioristorante.data.local.dao.UserDao
import com.example.ilmioristorante.data.local.dao.UserReviewDao
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.model.remoteKeys.RestaurantRemoteKeys
import com.example.ilmioristorante.model.reviews.UserReviews
import com.example.ilmioristorante.model.restaurant.DislikedRestaurants
import com.example.ilmioristorante.model.restaurant.RestaurantModel

@Database(entities = [RestaurantModel::class, RestaurantRemoteKeys::class, User::class, UserReviews::class, DislikedRestaurants::class], version = 1)
abstract class RestaurantDatabase: RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
    abstract fun restaurantRemoteKeysDao(): RestaurantRemoteKeysDao
    abstract fun userDao(): UserDao
    abstract fun userReviewDao(): UserReviewDao
    abstract fun dislikedRestaurantsDao(): DislikedRestaurantsDao
}