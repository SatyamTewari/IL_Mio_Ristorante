package com.example.ilmioristorante.presentation.composables.home

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ilmioristorante.R

@ExperimentalMaterial3Api
@Composable
fun HomeTopBar(
    onSearchClicked: () -> Unit,
    logout: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Home",
                color = Color.Black
            )
        },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            }
            IconButton(onClick = logout) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logout),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        colors = TopAppBarColors(Color.White, Color.White, Color.White, Color.White, Color.White)
    )
}

@ExperimentalMaterial3Api
@Composable
@Preview
fun HomeTopBarPreview() {
    HomeTopBar({}, {})
}