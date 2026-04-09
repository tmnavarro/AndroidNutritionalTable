package com.example.nutritionaltable.ui.screen.home

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.nutritionaltable.R
import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.data.model.WellnessNews
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.mockWellnessNews
import com.example.nutritionaltable.ui.component.home.HealthyRecipeCard
import com.example.nutritionaltable.ui.component.home.WelcomeHeader
import com.example.nutritionaltable.ui.component.home.WellnessNewsNewCard
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(sizing.md)
        ) {
            WelcomeHeader(
                userName = "Jonh Doe",
            )
            Spacer(
                modifier = Modifier.height(sizing.x2l)
            )
            Text(
                text = stringResource(R.string.saude_em_foco),
                style = Typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(sizing.lg)
            )
            WellnessNewsList(wellnessNewsList = mockWellnessNews, cardWidth = sizing.x5l)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF2F2F2))
                .padding(sizing.md)
        ) {
            Text(
                text = stringResource(R.string.tabela_nutricional),
                style = Typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(sizing.lg)
            )
            HealthyRecipeList(healthyRecipes = mockHealthyRecipes)
        }

    }
}

@Composable
fun WellnessNewsList(
    modifier: Modifier = Modifier,
    wellnessNewsList: List<WellnessNews>,
    cardWidth: Dp
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(sizing.md)
    ) {
        items(items = wellnessNewsList, key = { newsList -> newsList.id }) { wellnessNews ->
            WellnessNewsNewCard(
                modifier = Modifier.width(cardWidth),
                wellnessNews = wellnessNews
            )
        }
    }
}

@Composable
fun HealthyRecipeList(
    modifier: Modifier = Modifier,
    healthyRecipes: List<HealthyRecipe>
) {
    LazyColumn (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizing.md)
    ) {
        items(items = healthyRecipes, key = { healthyRecipe -> healthyRecipe.id }) { healthyRecipe ->
            HealthyRecipeCard(
                healthyRecipe = healthyRecipe
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    NutritionalTableTheme {
        HomeScreen()
    }
}