package com.example.nutritionaltable.data.model

import androidx.annotation.StringRes
import com.example.nutritionaltable.R

data class HealthyRecipeNutrient(
    val value: Float,
    val unit: NutrientUnit,
    @StringRes
    val name:  Int
)
