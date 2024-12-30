package com.example.carsapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DetailsScreen(
    carId: Int?,
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding() // Handles the status bar padding
                .navigationBarsPadding(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Details $carId",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }

}


