package com.example.ilmioristorante.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.util.Constants.UNSPLASHED_REMOTE_KEYS_TABLE

@Entity(tableName = UNSPLASHED_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)