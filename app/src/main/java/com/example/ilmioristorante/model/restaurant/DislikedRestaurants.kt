package com.example.ilmioristorante.model.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.util.Constants.DISLIKED_RESTAURANTS_TABLE

@Entity(tableName = DISLIKED_RESTAURANTS_TABLE)
data class DislikedRestaurants(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val disliked: Boolean
)