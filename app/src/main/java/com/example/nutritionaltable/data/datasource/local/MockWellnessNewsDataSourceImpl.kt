package com.example.nutritionaltable.data.datasource.local

import com.example.nutritionaltable.data.model.WellnessNews
import com.example.nutritionaltable.mockWellnessNews

class MockWellnessNewsDataSourceImpl: WellnessNewsDataSource {
    override suspend fun getAllWellnessNews(): List<WellnessNews> = mockWellnessNews
}