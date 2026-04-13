package com.example.nutritionaltable.core.di

import com.example.nutritionaltable.data.datasource.local.HealthyRecipeLocalDataSource
import com.example.nutritionaltable.data.datasource.local.MockHealthyRecipeLocalDataSourceImpl
import com.example.nutritionaltable.data.datasource.local.MockWellnessNewsDataSourceImpl
import com.example.nutritionaltable.data.datasource.local.WellnessNewsDataSource
import com.example.nutritionaltable.data.repository.HealthyRecipeRepositoryImpl
import com.example.nutritionaltable.data.repository.HomeContentRepositoryImpl
import com.example.nutritionaltable.domain.repository.HealthyRecipeRepository
import com.example.nutritionaltable.domain.repository.HomeContentRepository
import com.example.nutritionaltable.domain.usecase.GetHealthyRecipeByIdUseCase
import com.example.nutritionaltable.domain.usecase.GetHomeContentUseCase
import com.example.nutritionaltable.domain.usecase.IsHealthyRecipeFavoriteUseCase
import com.example.nutritionaltable.domain.usecase.UpdateHealthyRecipeIsFavoriteUseCase
import com.example.nutritionaltable.ui.screen.healthyRecipeDetails.HealthyRecipeDetailsViewModel
import com.example.nutritionaltable.ui.screen.home.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object AppModules {

    val uiMode = module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::HealthyRecipeDetailsViewModel)
    }

    val domainMode = module {
        factory { GetHomeContentUseCase(get()) }
        factory { GetHealthyRecipeByIdUseCase(get()) }
        factory { IsHealthyRecipeFavoriteUseCase(get()) }
        factory { UpdateHealthyRecipeIsFavoriteUseCase(get()) }
    }

    val dataMode = module {
        singleOf(::MockHealthyRecipeLocalDataSourceImpl) { bind<HealthyRecipeLocalDataSource>() }
        singleOf(::MockWellnessNewsDataSourceImpl) { bind<WellnessNewsDataSource>() }
        singleOf(::HomeContentRepositoryImpl) { bind<HomeContentRepository>() }
        singleOf(::HealthyRecipeRepositoryImpl) { bind<HealthyRecipeRepository>()}
    }



}