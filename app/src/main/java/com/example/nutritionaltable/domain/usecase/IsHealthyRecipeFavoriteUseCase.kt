package com.example.nutritionaltable.domain.usecase

import com.example.nutritionaltable.domain.repository.HealthyRecipeRepository

class IsHealthyRecipeFavoriteUseCase(
    private val healthyRecipeRepository: HealthyRecipeRepository
) {
    suspend operator fun invoke(id: String): Boolean =
        healthyRecipeRepository.checkIsFavorite(id)

}