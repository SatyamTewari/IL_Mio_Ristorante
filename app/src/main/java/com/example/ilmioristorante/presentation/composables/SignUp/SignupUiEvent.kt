package com.example.ilmioristorante.presentation.composables.SignUp

sealed class SignupUIEvent{

    data class FirstNameChanged(val firstName:String) : SignupUIEvent()
    data class LastNameChanged(val lastName:String) : SignupUIEvent()
    data class EmailChanged(val email:String): SignupUIEvent()
    data class PasswordChanged(val password: String) : SignupUIEvent()

    object RegisterButtonClicked : SignupUIEvent()
}
