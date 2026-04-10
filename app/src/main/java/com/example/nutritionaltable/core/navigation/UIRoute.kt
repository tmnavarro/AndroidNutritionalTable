package com.example.nutritionaltable.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface UIRoute {

    @Serializable
    data object Home: UIRoute

    @Serializable
    data class HealthyRecipeDetails(val healthyRecipeId: String): UIRoute
}