package com.example.carsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.carsapp.ui.BottomBar
import com.example.carsapp.ui.CarList
import com.example.carsapp.ui.Pager
import com.example.carsapp.ui.TopBar
import com.example.carsapp.ui.theme.Blur
import com.example.carsapp.ui.theme.CarsAppTheme
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
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    state = rememberTopAppBarState()
                )
                Scaffold(
                    modifier = Modifier
                            .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .background(MaterialTheme.colorScheme.background),
                    containerColor = Color.Transparent,
                    topBar = {
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
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    paddingValues: PaddingValues
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
            paddingValues = paddingValues
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


