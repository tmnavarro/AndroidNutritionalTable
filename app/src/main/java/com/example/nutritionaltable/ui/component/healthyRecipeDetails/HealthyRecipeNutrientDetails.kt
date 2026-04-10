package com.example.nutritionaltable.ui.component.healthyRecipeDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutritionaltable.data.model.HealthyRecipeNutrient
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography

@Composable
fun HealthyRecipeNutrientDetails(
    modifier: Modifier = Modifier,
    healthyRecipeNutrient: HealthyRecipeNutrient,
    isSubNutrient: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = if (isSubNutrient) sizing.md else sizing.x0,
                    bottom = sizing.sm
                ),
            style = if (isSubNutrient) Typography.bodyMedium else Typography.headlineMedium,
            text = stringResource(healthyRecipeNutrient.name)
        )
        Text(
            text = "${healthyRecipeNutrient.value} ${healthyRecipeNutrient.unit.symbol}",
            style = Typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeNutrientDetailsPreview() {
    NutritionalTableTheme {
        Column(
            modifier = Modifier.padding(sizing.md),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HealthyRecipeNutrientDetails(
                healthyRecipeNutrient = mockHealthyRecipes.first().calories,
                isSubNutrient = false
            )
            HealthyRecipeNutrientDetails(
                healthyRecipeNutrient = mockHealthyRecipes.first().proteins,
                isSubNutrient = true
            )
        }
    }
}