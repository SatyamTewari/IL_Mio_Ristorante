package com.example.ilmioristorante.model.reviews

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.data.util.Constants.USER_REVIEW_TABLE

@Entity(tableName = USER_REVIEW_TABLE)
data class UserReviews(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val review: String
)