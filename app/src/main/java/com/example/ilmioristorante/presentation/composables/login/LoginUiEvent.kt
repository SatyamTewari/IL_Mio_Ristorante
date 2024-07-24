package com.example.ilmioristorante.presentation.composables.login

sealed class LoginUIEvent{

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButtonClicked : LoginUIEvent()
}