package com.example.ilmioristorante.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = RestaurantColors.WhiteColor,
    primaryContainer = RestaurantColors.BlackColor,
    tertiary = RestaurantColors.LightGrayColor,
    onPrimary = RestaurantColors.WhiteColor,
    onSecondary = RestaurantColors.LightBlueGradient,
    onTertiary = RestaurantColors.DarkBlueGradient
)

private val LightColorScheme = lightColorScheme(
    primary = RestaurantColors.BlackColor,
    primaryContainer = RestaurantColors.WhiteColor,
    tertiary = RestaurantColors.DarkGrayColor,
    onPrimary = RestaurantColors.BlackColor,
    onSecondary = RestaurantColors.LightBlueGradient,
    onTertiary = RestaurantColors.DarkBlueGradient

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ILMioRistoranteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}