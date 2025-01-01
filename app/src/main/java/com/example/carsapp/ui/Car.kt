package com.example.carsapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.carsapp.R
import com.example.carsapp.ui.theme.Primary
import com.example.carsapp.ui.theme.Secondary


data class Car(
    val id : Int,
    val name: String,
    val description: String,
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
        description = "The Ferrari SF90 Stradale is a mid-engine PHEV " +
                "sports car produced by the Italian automobile manufacturer Ferrari.",
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
        description = "The Rolls-Royce Phantom is a full-sized luxury " +
                "saloon manufactured by Rolls-Royce Motor Cars. ",
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
        description = "The Porsche 911 Turbo S is a high-performance " +
                "variant of the Porsche 911 sports car.",
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
        description = "The Lamborghini Aventador is a mid-engine sports car " +
                "produced by the Italian automotive manufacturer Lamborghini.",
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