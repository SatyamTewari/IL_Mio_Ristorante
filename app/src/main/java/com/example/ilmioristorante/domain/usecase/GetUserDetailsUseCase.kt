package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.presentation.screens.SignUp.SignupUiState
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getUserDetail(email: String, password: String): User? {
        return repository.getUserDetails(email, password)
    }
}