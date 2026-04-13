package com.example.nutritionaltable.data.repository

import com.example.nutritionaltable.data.datasource.local.HealthyRecipeLocalDataSource
import com.example.nutritionaltable.data.datasource.local.WellnessNewsDataSource
import com.example.nutritionaltable.domain.model.HomeContent
import com.example.nutritionaltable.domain.repository.HomeContentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeContentRepositoryImpl(
    private val healthyRecipeLocalDataSource: HealthyRecipeLocalDataSource,
    private val wellnessNewsDataSource: WellnessNewsDataSource
): HomeContentRepository {
    override suspend fun getHomeContent(): HomeContent  = withContext(Dispatchers.Default) {
        val healthyRecipeList = healthyRecipeLocalDataSource.getAllHealthyRecipes()
        val wellnessNewsList = wellnessNewsDataSource.getAllWellnessNews()

        delay(1000)

        return@withContext HomeContent(
            healthyRecipeList,
            wellnessNewsList
        )
    }
}