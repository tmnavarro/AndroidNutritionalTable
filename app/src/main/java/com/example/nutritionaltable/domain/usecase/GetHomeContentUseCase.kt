package com.example.nutritionaltable.domain.usecase

import com.example.nutritionaltable.domain.model.HomeContent
import com.example.nutritionaltable.domain.repository.HomeContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHomeContentUseCase(
    private val homeContentRepository: HomeContentRepository
) {
    operator fun invoke(): Flow<HomeContent> = flow {
        emit(homeContentRepository.getHomeContent())
    }
}