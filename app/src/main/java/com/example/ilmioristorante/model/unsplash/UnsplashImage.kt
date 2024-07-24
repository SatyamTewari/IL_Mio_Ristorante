package com.example.ilmioristorante.model.unsplash

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.util.Constants.UNSPLASHED_IMAGE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = UNSPLASHED_IMAGE_TABLE)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val urls: Urls,
    @Embedded
    val user: User,
    var query: String = ""
)