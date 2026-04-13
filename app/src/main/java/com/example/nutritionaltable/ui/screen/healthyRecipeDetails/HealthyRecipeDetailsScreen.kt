package com.example.nutritionaltable.ui.screen.healthyRecipeDetails

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.nutritionaltable.R
import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.healthyRecipeDetails.HealthyRecipeMainInfo
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.ui.component.BackButton
import com.example.nutritionaltable.ui.component.LoveButton
import com.example.nutritionaltable.ui.component.PrimaryButton
import com.example.nutritionaltable.ui.component.healthyRecipeDetails.HealthyRecipeNutrientBar
import com.example.nutritionaltable.ui.screen.healthyRecipeMoreDetails.HealthyRecipeMoreDetailsScreen
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Primary
import kotlinx.coroutines.launch


private const val MAX_NUTRIENT_BAR_VALUE = 35f
private const val HEALTHY_RECIPE_IMAGE_ANIMATION_DURATION = 1000
private const val HEALTHY_RECIPE_IMAGE_SCALE = 1.2f
private const val HEALTHY_RECIPE_IMAGE_ROTATE = 360f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthyRecipeDetailsScreen(
    modifier: Modifier = Modifier,
    id: String,
    uiState: HealthyRecipeDetailsUIState,
    onEvent: (HealthyRecipeDetailsEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {

    LaunchedEffect(key1 = Unit) {
        onEvent(HealthyRecipeDetailsEvent.FindHeathyRecipeById(id))
    }

    uiState.healthyRecipe?.let { healthyRecipe ->
        var isImageVisible by remember { mutableStateOf(false) }
        val scale = remember { Animatable(initialValue = 0f) }
        val rotation = remember { Animatable(initialValue = 0f) }
        var showMoreDetails by remember { mutableStateOf(false) }

        val coroutineScope = rememberCoroutineScope()
        val moreDetailsSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        LaunchedEffect(key1 = Unit) {
            isImageVisible = true

            scale.animateTo(
                targetValue = HEALTHY_RECIPE_IMAGE_SCALE,
                animationSpec = tween(
                    durationMillis = HEALTHY_RECIPE_IMAGE_ANIMATION_DURATION,
                    easing = FastOutSlowInEasing
                )
            )
        }

        LaunchedEffect(key1 = Unit) {
            rotation.animateTo(
                targetValue = HEALTHY_RECIPE_IMAGE_ROTATE,
                animationSpec = tween(
                    durationMillis = HEALTHY_RECIPE_IMAGE_ANIMATION_DURATION,
                    easing = LinearEasing
                )
            )
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        sizing.lg
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BackButton(
                        modifier = Modifier.shadow(
                            elevation = sizing.lg,
                            shape = CircleShape,
                            spotColor = Primary
                        ), onClick = {
                            onEvent(HealthyRecipeDetailsEvent.OnBackPressed)
                            onNavigateBack()
                        }
                    )
                    LoveButton(
                        modifier = Modifier.shadow(
                            elevation = sizing.lg,
                            shape = CircleShape,
                            spotColor = Primary
                        ),
                        isSelected = uiState.isFavorite,
                        onClick = { isSelected ->
                            onEvent(
                                HealthyRecipeDetailsEvent.UpdateIsFavorite(
                                    id = id,
                                    isFavorite = isSelected
                                )
                            )
                        }
                    )
                }

                if (isImageVisible) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer(
                                scaleX = scale.value,
                                scaleY = scale.value,
                                rotationZ = rotation.value
                            )
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(R.drawable.img_dish_with_shadow),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = stringResource(R.string.imagem_item_tabela_nutricional)
                    )
                }


                HealthyRecipeMainInfo(
                    modifier = Modifier.fillMaxWidth(),
                    recipeName = healthyRecipe.name,
                    calories = healthyRecipe.calories.value,
                    totalPortionInGrams = healthyRecipe.totalPortionInGrams
                )

                HealthyRecipeNutrientBarList(
                    modifier = Modifier.padding(sizing.md),
                    healthyRecipe = healthyRecipe
                )

                Spacer(
                    modifier = Modifier.height(sizing.sm)
                )

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(sizing.x3l)
                        .padding(horizontal = sizing.md),
                    text = stringResource(R.string.mais_detalhes),
                    onClick = {
                        showMoreDetails = true
                    }
                )

                if (showMoreDetails) {
                    LaunchedEffect(key1 = moreDetailsSheetState) {
                        moreDetailsSheetState.show()
                    }
                    HealthyRecipeMoreDetailsScreen(
                        sheetState = moreDetailsSheetState,
                        healthyRecipe = healthyRecipe,
                        onDismiss = {
                            coroutineScope.launch {
                                moreDetailsSheetState.hide()
                            }.invokeOnCompletion {
                                if (!moreDetailsSheetState.isVisible) {
                                    showMoreDetails = false
                                }
                            }
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .zIndex(-1f)
                    .align(Alignment.BottomStart)
                    .clip(RoundedCornerShape(topStart = sizing.xl, topEnd = sizing.xl))
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            )
        }
    }
}

@Composable
fun HealthyRecipeNutrientBarList(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizing.md)
    ) {
        HealthyRecipeNutrientBar(
            name = stringResource(healthyRecipe.proteins.name),
            value = healthyRecipe.proteins.value,
            maxValue = MAX_NUTRIENT_BAR_VALUE
        )
        HealthyRecipeNutrientBar(
            name = stringResource(healthyRecipe.carbohydrates.name),
            value = healthyRecipe.carbohydrates.value,
            maxValue = MAX_NUTRIENT_BAR_VALUE
        )
        HealthyRecipeNutrientBar(
            name = stringResource(healthyRecipe.sugar.name),
            value = healthyRecipe.sugar.value,
            maxValue = MAX_NUTRIENT_BAR_VALUE
        )
        HealthyRecipeNutrientBar(
            name = stringResource(healthyRecipe.fat.name),
            value = healthyRecipe.fat.value,
            maxValue = MAX_NUTRIENT_BAR_VALUE
        )
    }
}

@Preview
@Composable
private fun HealthyRecipeDetailsScreenPreview() {
    NutritionalTableTheme {
        HealthyRecipeDetailsScreen(
            onNavigateBack = {},
            uiState = HealthyRecipeDetailsUIState(
                isLoading = false,
                healthyRecipe = mockHealthyRecipes.first(),
                isFavorite = false
            ),
            onEvent = {},
            id = ""
        )
    }
}