package com.example.nutritionaltable.domain.usecase

import com.example.nutritionaltable.domain.repository.HealthyRecipeRepository

class UpdateHealthyRecipeIsFavoriteUseCase(
    private val healthyRecipeRepository: HealthyRecipeRepository
) {
    suspend operator fun invoke(id: String, isFavorite: Boolean) =
        healthyRecipeRepository.updateIsFavorite(id, isFavorite)
}