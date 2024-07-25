package com.example.ilmioristorante.presentation.navigation

import android.content.SharedPreferences
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.ilmioristorante.presentation.composables.SignUp.SignUpScreen
import com.example.ilmioristorante.presentation.composables.detail.DetailScreen
import com.example.ilmioristorante.presentation.composables.home.HomeScreen
import com.example.ilmioristorante.presentation.composables.login.LoginScreen
import com.example.ilmioristorante.presentation.composables.search.SearchScreen
import com.example.ilmioristorante.presentation.util.PreferencesHelper
import com.example.ilmioristorante.presentation.util.Screen

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController) {

    val context = LocalContext.current
    val isUserLoggedIn = PreferencesHelper(context).isLoggedIn()

    NavHost(
        navController = navController,
        startDestination = if(isUserLoggedIn) Screen.Home.route else Screen.Login.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Signup.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = "${Screen.Detail.route}/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            DetailScreen(id = id ?: "")
        }
    }
}