package com.example.ilmioristorante.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ilmioristorante.model.restaurant.RestaurantModel

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants_table WHERE id NOT IN (SELECT id from disliked_restaurant_table)")
    fun getAllImages(): PagingSource<Int, RestaurantModel>

    @Query("SELECT * FROM restaurants_table WHERE `query`=:query AND id NOT IN (SELECT id from disliked_restaurant_table)")
    fun getSearchedRestaurant(query:String): PagingSource<Int, RestaurantModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<RestaurantModel>)

    @Query("SELECT * FROM restaurants_table WHERE id=:id")
    suspend fun getRestaurantItem(id: String): RestaurantModel

      /** not using for now but support added for future use-case */
//    @Query("DELETE FROM restaurants_table")
//    suspend fun deleteAllRestaurants()
}