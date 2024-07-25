package com.example.ilmioristorante.model.restaurant

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.data.util.Constants.RESTAURANTS_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = RESTAURANTS_TABLE)
data class RestaurantModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val urls: Urls,
    @Embedded
    val user: User,
    var query: String = ""
)