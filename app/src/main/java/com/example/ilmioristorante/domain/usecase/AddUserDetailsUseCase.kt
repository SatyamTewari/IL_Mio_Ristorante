package com.example.ilmioristorante.domain.usecase

import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.presentation.screens.SignUp.SignupUiState
import javax.inject.Inject

class AddUserDetailsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun addUserDetails(user: SignupUiState):Long{
        val data = User(
            email = user.email,
            password = user.password,
            name = user.firstName,
            lastName = user.lastName
        )
        return repository.addUserDetails(data)
    }
}