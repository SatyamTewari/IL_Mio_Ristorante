package com.example.ilmioristorante.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.ilmioristorante.domain.usecase.AddUserDetailsUseCase
import com.example.ilmioristorante.presentation.composables.SignUp.SignupUIEvent
import com.example.ilmioristorante.presentation.composables.SignUp.SignupUiState
import com.example.ilmioristorante.util.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val addUserDetailsUseCase: AddUserDetailsUseCase
) : ViewModel() {

    private val _registrationUIState = mutableStateOf(SignupUiState())
    val registrationUIState: State<SignupUiState>
        get() = _registrationUIState

    private val _allValidationsPassed = mutableStateOf(false)
    val allValidationsPassed: State<Boolean>
        get() = _allValidationsPassed

    private val _isUserSignupSuccess = mutableStateOf(0L)
    val isUserSignupSuccess : State<Long>
        get() = _isUserSignupSuccess

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                _registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is SignupUIEvent.LastNameChanged -> {
                _registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is SignupUIEvent.EmailChanged -> {
                _registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )

            }

            is SignupUIEvent.PasswordChanged -> {
                _registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signup()
            }

        }
        validateDataWithRules()
    }

    private fun signup() {
        viewModelScope.launch(Dispatchers.IO)  {
            val response = addUserDetailsUseCase.addUserDetails(_registrationUIState.value)
            if(response > 0){
                _isUserSignupSuccess.value = response
            }
        }
    }

    private fun validateDataWithRules() {
        val isFirstNameError = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val isLastNameError = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val isEmailError = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val isPasswordError = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        _registrationUIState.value = registrationUIState.value.copy(
            firstNameError = isFirstNameError.status,
            lastNameError = isLastNameError.status,
            emailError = isEmailError.status,
            passwordError = isPasswordError.status,
        )


        _allValidationsPassed.value = !isFirstNameError.status && !isLastNameError.status &&
                !isEmailError.status && !isPasswordError.status

    }
}