package com.example.nutritionaltable.domain.usecase

import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.domain.repository.HealthyRecipeRepository

class GetHealthyRecipeByIdUseCase(
    private val healthyRecipeRepository: HealthyRecipeRepository
) {
    suspend operator fun invoke(id: String): HealthyRecipe? =
        healthyRecipeRepository.getHealthyRecipeById(id)

}