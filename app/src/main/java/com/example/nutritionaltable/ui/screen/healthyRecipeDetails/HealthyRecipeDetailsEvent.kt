package com.example.nutritionaltable.ui.screen.healthyRecipeDetails

sealed interface HealthyRecipeDetailsEvent {
    data class FindHeathyRecipeById(val id: String) : HealthyRecipeDetailsEvent
    data class UpdateIsFavorite(val id: String, val isFavorite: Boolean) : HealthyRecipeDetailsEvent
    data object OnBackPressed: HealthyRecipeDetailsEvent
}