package com.example.ilmioristorante.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ilmioristorante.model.unsplash.DislikedRestaurants

@Dao
interface DislikedRestaurantsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDislikedRestaurant(dislikedRestaurants: DislikedRestaurants): Long

    @Query("DELETE FROM disliked_restaurant_table WHERE id=:id")
    suspend fun deleteDislikedRestaurant(id: String): Int
}