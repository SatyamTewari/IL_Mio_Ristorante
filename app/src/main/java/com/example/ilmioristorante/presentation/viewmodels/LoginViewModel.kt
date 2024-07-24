package com.example.ilmioristorante.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.ilmioristorante.domain.Repository
import com.example.ilmioristorante.domain.usecase.GetUserDetailsUseCase
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.presentation.screens.login.LoginUIEvent
import com.example.ilmioristorante.presentation.screens.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ilmioristorante.util.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase
): ViewModel() {

    private val _loginUiState = mutableStateOf(LoginUiState())
    val loginUIState: State<LoginUiState>
        get() = _loginUiState

    private val _allValidationsPassed = mutableStateOf(false)
    val allValidationsPassed: State<Boolean>
        get() = _allValidationsPassed

    private val _isUserLoginSuccess = mutableStateOf<User?>(null)
    val isUserLoginSuccess : State<User?>
        get() = _isUserLoginSuccess

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                _loginUiState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                _loginUiState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }


    private fun login() {
        viewModelScope.launch(Dispatchers.IO)  {
            val response = getUserDetailsUseCase.getUserDetail(_loginUiState.value.email, _loginUiState.value.password)
            if(response != null){
                _isUserLoginSuccess.value = response
            }
        }
    }

    private fun validateLoginUIDataWithRules() {
        val isEmailError = Validator.validateEmail(
            email = loginUIState.value.email
        )


        val isPasswordError = Validator.validatePassword(
            password = loginUIState.value.password
        )

        _loginUiState.value = loginUIState.value.copy(
            emailError = isEmailError.status,
            passwordError = isPasswordError.status
        )

        _allValidationsPassed.value = !isEmailError.status && !isPasswordError.status

    }
}