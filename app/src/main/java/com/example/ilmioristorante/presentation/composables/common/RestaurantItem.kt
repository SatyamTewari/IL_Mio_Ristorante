package com.example.ilmioristorante.presentation.composables.common

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ilmioristorante.R
import com.example.ilmioristorante.model.restaurant.RestaurantModel
import com.example.ilmioristorante.model.restaurant.Urls
import com.example.ilmioristorante.model.restaurant.User
import com.example.ilmioristorante.model.restaurant.UserLinks
import com.example.ilmioristorante.ui.theme.BlueColor
import com.example.ilmioristorante.ui.theme.WhiteColor
import com.example.ilmioristorante.util.Screen

@ExperimentalCoilApi
@Composable
fun RestaurantItem(
    restaurant: RestaurantModel?,
    source: Screen,
    onItemClicked: ((id: String) -> Unit) = {},
    onThumbsDownClicked: (() -> Unit) = {},
    dislikeStatus: Boolean = false
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                when (source) {
                    is Screen.Home, is Screen.Search -> {
                        onItemClicked.invoke(restaurant?.id ?: "")
                    }

                    else -> {
                        val browserIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://unsplash.com/@${restaurant?.user?.username}?utm_source=DemoApp&utm_medium=referral")
                        )
                        startActivity(context, browserIntent, null)
                    }
                }
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(restaurant?.urls?.regular)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = "Restaurant",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            color = Color.Black
        ) {}
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Photo by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(restaurant?.user?.username)
                    }
                },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Dislike Icon",
                tint = if(dislikeStatus) BlueColor else WhiteColor,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .rotate(180f)
                    .clickable {
                        when (source) {
                            is Screen.Home, is Screen.Search -> {
                                Toast
                                    .makeText(
                                        context,
                                        "Please Dislike this restaurant from details page",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }

                            else -> {
                                onThumbsDownClicked.invoke()
                            }
                        }
                    }
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun RestaurantItemPreview() {
    RestaurantItem(
        restaurant = RestaurantModel(
            id = "1",
            urls = Urls(regular = ""),
            user = User(username = "Satyam Tewari", userLinks = UserLinks(html = ""))
        ), source = Screen.Home, dislikeStatus = true
    )
}