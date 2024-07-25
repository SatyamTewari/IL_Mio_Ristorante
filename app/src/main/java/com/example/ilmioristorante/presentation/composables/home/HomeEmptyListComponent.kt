package com.example.ilmioristorante.presentation.composables.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ilmioristorante.R
import com.example.ilmioristorante.ui.theme.ILMioRistoranteTheme

@Composable
fun HomeEmptyListComponent() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_list_holder),
            contentDescription = "Empty List"
        )
        Text(
            text = "No data found in storage. Please search for restaurants in your city.",
            color = MaterialTheme.colorScheme.primary,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeEmptyListComponent() {
    ILMioRistoranteTheme {
        HomeEmptyListComponent()
    }
}