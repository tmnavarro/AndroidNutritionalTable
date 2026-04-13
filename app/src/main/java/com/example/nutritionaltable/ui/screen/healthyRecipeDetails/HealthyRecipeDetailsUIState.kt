package com.example.nutritionaltable.ui.screen.healthyRecipeDetails

import com.example.nutritionaltable.data.model.HealthyRecipe

data class HealthyRecipeDetailsUIState(
    val isLoading: Boolean = false,
    val healthyRecipe: HealthyRecipe? = null,
    val isFavorite: Boolean = false
)
