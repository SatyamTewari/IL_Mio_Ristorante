package com.example.ilmioristorante.model.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.data.util.Constants.RESTAURANTS_REMOTE_KEYS_TABLE

@Entity(tableName = RESTAURANTS_REMOTE_KEYS_TABLE)
data class RestaurantRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)