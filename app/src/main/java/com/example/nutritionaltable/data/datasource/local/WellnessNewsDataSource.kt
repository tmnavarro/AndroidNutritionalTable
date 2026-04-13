package com.example.nutritionaltable.data.datasource.local

import com.example.nutritionaltable.data.model.WellnessNews

interface WellnessNewsDataSource {
    suspend fun getAllWellnessNews(): List<WellnessNews>
}