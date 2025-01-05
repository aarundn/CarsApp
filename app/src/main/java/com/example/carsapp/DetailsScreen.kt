package com.example.carsapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carsapp.ui.Car
import com.example.carsapp.ui.Rater
import com.example.carsapp.ui.theme.CarsAppTheme
import com.example.carsapp.ui.theme.Primary
import com.example.carsapp.ui.viewmodel.SharedViewModel

@Composable
fun DetailsScreen(
    @DrawableRes image: Int,
    viewModel: SharedViewModel,
    navController: NavController
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
                text = "Details ${viewModel.selectedCar.collectAsState().value?.name}",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            DetailsScreenContent(
                onBackClick = { navController.popBackStack() },
                selectedCar = viewModel.selectedCar.collectAsState().value!!,
                image = image
            )
        }
    }

}

@Composable
fun DetailsScreenContent(
    onBackClick: () -> Unit,
    selectedCar: Car,
    @DrawableRes image: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(start = 16.dp, top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .size(40.dp)
                    .padding(4.dp)
                    .clickable {
                        onBackClick()
                    },

                )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    CarCard(selectedCar, Modifier, onBackClick, image)
                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                            .background(
                                selectedCar.bgColor
                            )
                            .padding(start = 24.dp)
                    )

                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(topEnd = 60.dp)
                        )
                        .align(Alignment.BottomCenter)
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                CartInfos(image = R.drawable.ic_car, text = "300Km")
                CartInfos(image = R.drawable.joystick, text = "550hs")
                CartInfos(image = R.drawable.gear, text = "Deep")
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(
                    Alignment.BottomCenter
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$${selectedCar.price}.00",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp),

                )
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 4.dp)
                    .weight(1f)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Book Car",
                    color = Primary,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Forward",
                    tint = Primary,
                    modifier = Modifier
                        .size(28.dp)
                        .padding(4.dp)

                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
                .align(Alignment.BottomEnd)
        )
    }


}


@Composable
private fun CarCard(
    selectedCar: Car,
    modifier: Modifier,
    onBackClick: () -> Unit,
    @DrawableRes image: Int
) {

    val animatedX = remember { Animatable(150f) }
    val animatedY = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedX.animateTo(0f, animationSpec = tween(durationMillis = 500))
        animatedY.animateTo(0f, animationSpec = tween(durationMillis = 500))
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = selectedCar.bgColor,
                shape = RoundedCornerShape(
                    topEnd = 50.dp,
                    topStart = 50.dp,
                    bottomStart = 50.dp
                )
            )
            .padding(bottom = 16.dp)
    ) {
        Text(
            text = "Car Details",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Black,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Image(
            painter = painterResource(image),
            contentDescription = selectedCar.name,
            modifier = Modifier.fillMaxWidth()
                .offset(x = animatedX.value.dp, y = animatedY.value.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(selectedCar.logo),
                contentDescription = selectedCar.name,
                modifier = modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(6.dp)
                    .size(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = selectedCar.name,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Black,
                modifier = modifier
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = selectedCar.description,
            fontWeight = FontWeight.Normal,
            modifier = modifier
                .padding(start = 16.dp, top = 4.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.background,
            fontSize = 12.sp,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Rater(
                    image = selectedCar.recommenders[0]
                )
                Rater(
                    modifier = Modifier.padding(start = 24.dp),
                    image = selectedCar.recommenders[1]
                )
                Rater(
                    modifier = Modifier.padding(start = 48.dp),
                    image = selectedCar.recommenders[2]
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = selectedCar.recommendationRate.toString(),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Reviews",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .size(24.dp)
                    .padding(4.dp)
                    .clickable {
                        onBackClick()
                    },

                )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun CartInfos(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(start = 25.dp, end = 25.dp, top = 16.dp, bottom = 16.dp),
    ) {

        Image(
            painter = painterResource(id = image),
            contentDescription = "Shopping Cart",
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailsScreenContentPreview() {
    CarsAppTheme {
        DetailsScreenContent(
            onBackClick = {}, selectedCar = Car(
                id = 1,
                name = "Ferrari 812 Superfast",
                description = "The Ferrari 812 Superfast is a front mid-engine, rear-wheel-drive grand tourer produced by Italian sports car manufacturer Ferrari that made its debut at the 2017 Geneva Motor Show. " +
                        "The 812 Superfast is the successor to the F12berlinetta.",
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
                bgColor = Color.Red
            ), image = R.drawable.ferrari_car
        )
    }
}
