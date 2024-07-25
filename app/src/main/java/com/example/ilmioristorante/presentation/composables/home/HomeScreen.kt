package com.example.ilmioristorante.presentation.composables.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.ilmioristorante.presentation.util.Screen
import com.example.ilmioristorante.presentation.composables.common.ListContent
import com.example.ilmioristorante.presentation.util.PreferencesHelper
import com.example.ilmioristorante.presentation.viewmodels.HomeViewModel

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val preferencesHelper = PreferencesHelper(context)
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                },
                logout = {
                    preferencesHelper.saveLoginState(false)
                    navController.navigate(Screen.Login.route){
                        popUpTo(Screen.Home.route){ inclusive = true}
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                if (getAllImages.itemCount > 0) {
                    ListContent(
                        lazyPagingItems = getAllImages,
                        source = Screen.Home,
                        onItemClicked = {
                            navController.navigate("${Screen.Detail.route}/$it")
                        })
                } else {
                    HomeEmptyListComponent()
                }
            }
        }
    )
}