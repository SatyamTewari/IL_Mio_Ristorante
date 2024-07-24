package com.example.ilmioristorante.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ilmioristorante.model.remoteKeys.RestaurantRemoteKeys

@Dao
interface RestaurantRemoteKeysDao {

    @Query("SELECT * FROM restaurants_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): RestaurantRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RestaurantRemoteKeys>)

    /** not using for now but support added for future use-case */
//    @Query("DELETE FROM restaurants_remote_keys_table")
//    suspend fun deleteAllRemoteKeys()

}