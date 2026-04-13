package com.example.nutritionaltable.data.repository

import com.example.nutritionaltable.data.datasource.local.HealthyRecipeLocalDataSource
import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.domain.repository.HealthyRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HealthyRecipeRepositoryImpl(
    private val healthyRecipeLocalDataSource: HealthyRecipeLocalDataSource
) : HealthyRecipeRepository {
    override suspend fun getHealthyRecipeById(id: String): HealthyRecipe? = withContext(Dispatchers.Default) {
        healthyRecipeLocalDataSource.getHealthyRecipeById(id)
    }

    override suspend fun checkIsFavorite(id: String): Boolean = withContext(Dispatchers.Default) {
        healthyRecipeLocalDataSource.checkIsFavorite(id)
    }

    override suspend fun updateIsFavorite(id: String, isFavorite: Boolean) = withContext(Dispatchers.Default) {
        healthyRecipeLocalDataSource.updateIsFavorite(id, isFavorite)
    }
}