package com.example.carsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsapp.ui.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _selectedCar = MutableStateFlow<Car?>(null)
    val selectedCar = _selectedCar.asStateFlow()

    fun selectCar(product: Car) {
        viewModelScope.launch {
            _selectedCar.value = product
        }
    }
}