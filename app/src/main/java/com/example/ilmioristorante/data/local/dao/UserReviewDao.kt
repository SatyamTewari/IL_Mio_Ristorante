package com.example.ilmioristorante.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ilmioristorante.model.reviews.UserReviews

@Dao
interface UserReviewDao {

    @Query("SELECT * FROM USER_REVIEW_TABLE WHERE id=:id")
    suspend fun getUserReview(id: String): UserReviews

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserReview(review: UserReviews): Long
}