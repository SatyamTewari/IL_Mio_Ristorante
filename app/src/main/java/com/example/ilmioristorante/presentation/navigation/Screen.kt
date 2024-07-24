package com.example.ilmioristorante.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object Login: Screen("login_screen")
    object Signup: Screen("signup_screen")
    object Detail: Screen("detail_screen")
}