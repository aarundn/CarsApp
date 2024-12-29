package com.example.carsapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carsapp.ui.navigation.Details

@Composable
fun CarList(
    onGoToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 16.dp,
            bottom = 90.dp,
        )
    ){
        itemsIndexed(luxuriousCars){
            index, car ->
            CarItem(car = car,
                modifier = Modifier.fillMaxWidth()
                    .height(230.dp)
                    .clickable {
                        onGoToDetails(car.id)
                    },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}