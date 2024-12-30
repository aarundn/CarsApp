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
import androidx.lifecycle.ViewModel
import com.example.carsapp.ui.viewmodel.SharedViewModel

@Composable
fun CarList(
    onGoToDetails: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel:SharedViewModel
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
                        viewModel.selectCar(car)
                        onGoToDetails()
                    },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}