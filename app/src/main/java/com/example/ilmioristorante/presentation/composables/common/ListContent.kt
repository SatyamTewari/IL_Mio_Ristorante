package com.example.ilmioristorante.presentation.composables.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.annotation.ExperimentalCoilApi
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import com.example.ilmioristorante.util.Screen

@ExperimentalCoilApi
@Composable
fun ListContent(
    source: Screen,
    lazyPagingItems: LazyPagingItems<RestaurantModel>,
    onItemClicked: ((id: String) -> Unit)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id },
            contentType = lazyPagingItems.itemContentType { null }
        ) { index ->
            lazyPagingItems[index]?.let { RestaurantItem(restaurant = it, source = source, onItemClicked) }
        }
    }
}