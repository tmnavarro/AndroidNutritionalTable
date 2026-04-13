package com.example.nutritionaltable.domain.repository

import com.example.nutritionaltable.domain.model.HomeContent

interface HomeContentRepository {
    suspend fun getHomeContent(): HomeContent

}