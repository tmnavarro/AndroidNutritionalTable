package com.example.nutritionaltable.data.datasource.local

import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.mockHealthyRecipes

class MockHealthyRecipeLocalDataSourceImpl: HealthyRecipeLocalDataSource {

    private val healthyRecipeFavoritesIdList = mutableListOf<String>()

    override suspend fun getAllHealthyRecipes(): List<HealthyRecipe> = mockHealthyRecipes
    override suspend fun getHealthyRecipeById(id: String) =
        mockHealthyRecipes.find { healthyRecipe ->
            healthyRecipe.id.toString() == id
    }

    override suspend fun checkIsFavorite(id: String): Boolean=
        healthyRecipeFavoritesIdList.contains(id)


    override suspend fun updateIsFavorite(id: String, isFavorite: Boolean) {
        if(isFavorite) {
            healthyRecipeFavoritesIdList.add(id)
        } else {
            healthyRecipeFavoritesIdList.remove(id)
        }
    }
}