package com.example.nutritionaltable.ui.component.healthyRecipeDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutritionaltable.R
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography


@Composable
fun HealthyRecipeMainInfo(
    modifier: Modifier = Modifier,
    recipeName: String,
    calories: Float,
    totalPortionInGrams: Int
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Typography.headlineLarge,
            text = recipeName,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.height(sizing.lg)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(sizing.sm),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = Typography.headlineSmall,
                    text = "Energia"
                )
                Text(
                    text = stringResource(
                        R.string.valor_kcal,
                        calories
                    ),
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(sizing.sm),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = Typography.headlineSmall,
                    text = "Porção total"
                )
                Text(
                    text = stringResource(
                        R.string.valor_g,
                        totalPortionInGrams
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeMainInfoPreview() {
    NutritionalTableTheme {
        HealthyRecipeMainInfo(
            modifier = Modifier.padding(vertical = sizing.md),
            recipeName = "Nome da receita",
            calories = 120.40f,
            totalPortionInGrams = 200
        )
    }
}