package com.example.ilmioristorante.presentation.composables.search

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
import com.example.ilmioristorante.presentation.viewmodels.SearchViewModel

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery = searchViewModel.searchQuery
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery.value,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchRestaurants(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                ListContent(lazyPagingItems = searchedImages, source = Screen.Search, onItemClicked = {
                    navController.navigate("${Screen.Detail.route}/$it")
                })
            }
        }
    )
}