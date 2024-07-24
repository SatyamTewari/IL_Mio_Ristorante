package com.example.ilmioristorante.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ilmioristorante.model.unsplash.UnsplashImage

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM unsplash_image_table WHERE id NOT IN (SELECT id from disliked_restaurant_table)")
    fun getAllImages(): PagingSource<Int, UnsplashImage>

    @Query("SELECT * FROM unsplash_image_table WHERE `query`=:query AND id NOT IN (SELECT id from disliked_restaurant_table)")
    fun getSearchedRestaurant(query:String): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<UnsplashImage>)

    @Query("SELECT * FROM unsplash_image_table WHERE id=:id")
    suspend fun getUnsplashImageItem(id: String): UnsplashImage

      /** not using for now but support added for future use-case */
//    @Query("DELETE FROM unsplash_image_table")
//    suspend fun deleteAllImages()
}