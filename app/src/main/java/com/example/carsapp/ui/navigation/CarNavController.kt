package com.example.carsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.carsapp.HomeScreen
import dev.chrisbanes.haze.HazeState

interface CarDestination {
    val route: String
}
object Home : CarDestination {
    override val route = "home"
}

object Details : CarDestination {
    override val route = "details"
}