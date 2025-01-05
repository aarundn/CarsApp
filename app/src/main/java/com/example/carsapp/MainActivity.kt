package com.example.carsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carsapp.ui.BottomBar
import com.example.carsapp.ui.CarList
import com.example.carsapp.ui.Pager
import com.example.carsapp.ui.TopBar
import com.example.carsapp.ui.navigation.Details
import com.example.carsapp.ui.navigation.Home
import com.example.carsapp.ui.theme.Blur
import com.example.carsapp.ui.theme.CarsAppTheme
import com.example.carsapp.ui.viewmodel.SharedViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarsAppTheme {
                val hazState = remember { HazeState() }
                val viewModel = SharedViewModel()
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Home.route,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None }
                ) {
                    composable(route = Home.route) {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                                .nestedScroll(scrollBehavior.nestedScrollConnection)
                                .background(MaterialTheme.colorScheme.background),
                            containerColor = Color.Transparent,
                            topBar = {
                                // Show only on HomeScreen
                                Column {
                                    TopBar(
                                        modifier = Modifier.hazeChild(state = hazState),
                                        scrollBehavior = scrollBehavior
                                    )
                                    Pager(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .hazeChild(state = hazState),
                                    )
                                }
                            }
                        ) { innerPadding ->
                            HomeScreen(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                hazeState = hazState,
                                paddingValues = innerPadding,
                                onGoToDetails = {
                                    navController.navigate("${Details.route}/${viewModel.selectedCar.value?.image}")
                                },
                                viewModel = viewModel
                            )
                        }
                    }
                    composable(
                        route = "${Details.route}/${"{photoUrl}"}",
                        arguments = listOf(navArgument("photoUrl") { type = NavType.IntType }),
                        enterTransition = {
                            fadeIn(
                                animationSpec = tween(
                                    300, easing = LinearEasing
                                )
                            ) + slideIntoContainer(
                                animationSpec = tween(300, easing = EaseIn),
                                towards = AnimatedContentTransitionScope.SlideDirection.Start
                            )
                        },
                        exitTransition = {
                            fadeOut(
                                animationSpec = tween(
                                    300, easing = LinearEasing
                                )
                            ) + slideOutOfContainer(
                                animationSpec = tween(300, easing = EaseOut),
                                towards = AnimatedContentTransitionScope.SlideDirection.End
                            )
                        }
                    ) { backStackEntry ->
                        val photoUrl = backStackEntry.arguments?.getInt("photoUrl")
                        DetailsScreen(
                           image = photoUrl!!,
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    paddingValues: PaddingValues,
    onGoToDetails: () -> Unit,
    viewModel: SharedViewModel
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        CarList(
            modifier = Modifier
                .fillMaxWidth()
                .haze(
                    state = hazeState,
                    style = HazeStyle(
                        blurRadius = 13.dp,
                        tint = Blur
                    )

                ),
            paddingValues = paddingValues,
            onGoToDetails = onGoToDetails,
            viewModel = viewModel
        )
        BottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .align(Alignment.BottomStart)
                .padding(bottom = 26.dp)
                .hazeChild(
                    state = hazeState,
                    shape = RoundedCornerShape(22.dp)
                )
        )
    }
}


