package com.example.carsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsapp.R
import com.example.carsapp.ui.theme.Primary

@Composable
fun Pager(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
    )  {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Luxurious\nRental Cars",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            lineHeight = 40.sp,
            modifier = Modifier.padding(horizontal = 22.dp)
            )
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            var selectedCategory by remember { mutableStateOf(CarCategory.Luxurious) }
            val categories = listOf(CarCategory.Luxurious, CarCategory.VipCars)

            HomeCategoryTabs(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
private fun HomeCategoryTabs(
    categories: List<CarCategory>,
    selectedCategory: CarCategory,
    onCategorySelected: (CarCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (categories.isEmpty()) {
        return
    }

    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CarCategoryTabIndicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
        )
    }

    TabRow(
        selectedTabIndex = selectedIndex,
        containerColor = Color.Transparent,
        indicator = indicator,
        modifier = modifier,
        divider =
        { HorizontalDivider() }

    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onCategorySelected(category) },
                text = {
                    when (category) {
                        CarCategory.Luxurious -> Text(
                            text = stringResource(R.string.home_library),
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )

                        CarCategory.VipCars -> Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(R.string.home_discover),
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.size(5.dp))

                                Text(
                                    text = "New",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier
                                        .alpha(0.7f)
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(Primary)
                                        .padding(horizontal = 5.dp)
                                )
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun CarCategoryTabIndicator(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
) {
    Spacer(
        modifier
            .height(3.dp)
            .background(color)
    )
}

enum class CarCategory {
    Luxurious, VipCars
}














