package com.example.nutritionaltable.ui.screen.home

import com.example.nutritionaltable.domain.model.HomeContent

data class HomeUIState(
    val isLoading: Boolean = false,
    val homeContent: HomeContent? = null,
    val userName: String? = null
)