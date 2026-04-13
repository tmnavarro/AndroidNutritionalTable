package com.example.nutritionaltable.domain.model

import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.data.model.WellnessNews

data class HomeContent(
    val healthyRecipes: List<HealthyRecipe>,
    val wellnessNews: List<WellnessNews>
)
