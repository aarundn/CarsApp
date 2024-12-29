package com.example.carsapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.carsapp.R
import com.example.carsapp.ui.theme.Primary
import com.example.carsapp.ui.theme.Secondary


data class Car(
    val id : Int,
    val name: String,
    @DrawableRes val image: Int,
    val color: Color,
    @DrawableRes val logo: Int,
    val recommendation: Int,
    val recommendationRate: Float,
    val rentalDays: Int,
    val price: Int,
    val recommenders: List<Int>,
    val bgColor: Color
)


val luxuriousCars = listOf(
    Car(
        id = 1,
        name = "Ferrari SF90 Stradale",
        image = R.drawable.ferrari_car,
        color = Color.Red,
        logo = R.drawable.ferrari_logo,
        recommendation = 97,
        recommendationRate = 4.8f,
        rentalDays = 7,
        price = 759,
        recommenders = listOf(
            R.drawable.m_2, R.drawable.m_1, R.drawable.m_3
        ),
        bgColor = Primary
    ),
    Car(
        id = 2,
        name = "Rolls-Royce Phantom",
        image = R.drawable.rolls_royce_car,
        color = Color.Black,
        logo = R.drawable.rolls_royce_logo,
        recommendation = 98,
        recommendationRate = 4.7f,
        rentalDays = 10,
        price = 799,
        recommenders = listOf(
            R.drawable.m_1, R.drawable.m_1, R.drawable.m_3
        ),
        bgColor = Secondary
    ),
    Car(
        id = 3,
        name = "Porsche 911 Turbo S",
        image = R.drawable.porsche_car,
        color = Color.Yellow,
        logo = R.drawable.porsche_logo,
        recommendation = 99,
        recommendationRate = 4.8f,
        rentalDays = 6,
        price = 689,
        recommenders = listOf(
            R.drawable.m_3, R.drawable.m_2, R.drawable.m_1
        ),
        bgColor = Primary
    ),
    Car(
        id = 4,
        name = "Lamborghini Aventador",
        image = R.drawable.lamborghini_car,
        color = Color.White,
        logo = R.drawable.lamborghini_logo,
        recommendation = 97,
        recommendationRate = 4.9f,
        rentalDays = 5,
        price = 649,
        recommenders = listOf(
            R.drawable.m_1, R.drawable.m_2, R.drawable.m_2
        ),
        bgColor = Secondary
    )
)

fun getCar(
    carId: Int
): Car = luxuriousCars.find {
    it.id == carId
}!!