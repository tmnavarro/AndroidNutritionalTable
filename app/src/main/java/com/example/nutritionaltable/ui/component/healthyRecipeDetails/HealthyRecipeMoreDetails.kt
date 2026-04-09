package com.example.nutritionaltable.ui.component.healthyRecipeDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutritionaltable.data.model.NutrientUnit
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography

@Composable
fun HealthyRecipeMoreDetails(
    modifier: Modifier = Modifier,
    name: String,
    value: Float,
    unit: NutrientUnit,
    isSubNutrient: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f).padding(
                start = if(isSubNutrient) sizing.md else sizing.x0,
                bottom = sizing.sm),
            style = if(isSubNutrient) Typography.bodyMedium else Typography.headlineMedium,
            text = name
        )
        Text(
            text = "$value ${unit.symbol}",
            style = Typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeMoreDetailsPreview() {
    NutritionalTableTheme {
        Column(
            modifier = Modifier.padding(sizing.md),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HealthyRecipeMoreDetails(
                name = "Proteina",
                value = 10f,
                unit = NutrientUnit.KCAL,
                isSubNutrient = false
            )
            HealthyRecipeMoreDetails(
                name = "Fibras",
                value = 10f,
                unit = NutrientUnit.KCAL,
                isSubNutrient = true
            )
        }
    }
}