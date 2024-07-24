package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.reviews.UserReviews
import javax.inject.Inject

class AddUserReviewUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun addUserReview(review: String, id: String): Long{
        return repository.addUserReview(UserReviews(id = id, review = review))
    }
}