package com.example.nutritionaltable.domain.repository

import com.example.nutritionaltable.data.model.HealthyRecipe

interface HealthyRecipeRepository {
    suspend fun getHealthyRecipeById(id: String): HealthyRecipe?
    suspend fun checkIsFavorite(id: String): Boolean

    suspend fun updateIsFavorite(id: String, isFavorite: Boolean)
}