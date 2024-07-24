package com.example.ilmioristorante.presentation.composables.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.ilmioristorante.util.Screen
import com.example.ilmioristorante.presentation.composables.common.ListContent
import com.example.ilmioristorante.presentation.viewmodels.HomeViewModel

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                ListContent(lazyPagingItems = getAllImages, source = Screen.Home, onItemClicked = {
                    navController.navigate("${Screen.Detail.route}/$it")
                })
            }
        }
    )
}