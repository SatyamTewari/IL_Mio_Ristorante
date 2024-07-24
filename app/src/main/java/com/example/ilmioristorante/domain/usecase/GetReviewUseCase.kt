package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.reviews.UserReviews
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetReviewUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getReview(id: String): String {
        val response = repository.getUserReview(id)
        return response?.review ?: ""
    }
}