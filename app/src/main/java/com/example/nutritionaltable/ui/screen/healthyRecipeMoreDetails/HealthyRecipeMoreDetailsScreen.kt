package com.example.nutritionaltable.ui.screen.healthyRecipeMoreDetails

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutritionaltable.R
import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.ui.component.healthyRecipeDetails.HealthyRecipeNutrientDetails
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.SurfaceElement
import com.example.nutritionaltable.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthyRecipeMoreDetailsScreen(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    healthyRecipe: HealthyRecipe,
    onDismiss: () -> Unit
    ) {
    val isPreview = LocalInspectionMode.current

    LaunchedEffect(key1 = Unit) {
        if(isPreview) sheetState.expand()
    }

    ModalBottomSheet(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier.padding(sizing.md)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxWidth(.2f).height(sizing.sm)
                ) {
                    drawRoundRect(
                        color = SurfaceElement,
                        cornerRadius = CornerRadius(x = 8.dp.toPx(),  y = 8.dp.toPx())
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = sizing.md).padding(bottom = sizing.lg),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(sizing.md)
        ) {
            Text(
                text = stringResource(R.string.mais_detalhes),
                style = Typography.headlineMedium
            )

            HealthyRecipeNutrientDetails(
                healthyRecipeNutrient = healthyRecipe.calories,
            )

            HealthyRecipeNutrientDetails(
                healthyRecipeNutrient = healthyRecipe.proteins,
            )

            HealthyRecipeNutrientDetails(
                healthyRecipeNutrient = healthyRecipe.fat,
                isSubNutrient = true
            )

            Text(
                text = "Alterar objetos unicos para lista",
                style = Typography.bodyLarge
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HealthyRecipeMoreDetailsScreenPreview() {
    NutritionalTableTheme {
        Column(
            modifier = Modifier.fillMaxSize().background(SurfaceElement)
        ) {
            HealthyRecipeMoreDetailsScreen(
                sheetState = rememberModalBottomSheetState(),
                onDismiss = {},
                healthyRecipe = mockHealthyRecipes.first())
        }

    }
}