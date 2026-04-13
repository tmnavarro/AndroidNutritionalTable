package com.example.nutritionaltable.ui.screen.healthyRecipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionaltable.domain.usecase.GetHealthyRecipeByIdUseCase
import com.example.nutritionaltable.domain.usecase.IsHealthyRecipeFavoriteUseCase
import com.example.nutritionaltable.domain.usecase.UpdateHealthyRecipeIsFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HealthyRecipeDetailsViewModel(
    private val updateHealthyRecipeIsFavoriteUseCase: UpdateHealthyRecipeIsFavoriteUseCase,
    private val getHealthyRecipeByIdUseCase: GetHealthyRecipeByIdUseCase,
    private val isHealthyRecipeFavoriteUseCase: IsHealthyRecipeFavoriteUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HealthyRecipeDetailsUIState())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: HealthyRecipeDetailsEvent) {
        when(event) {
            is HealthyRecipeDetailsEvent.FindHeathyRecipeById -> findHealthyByID(id = event.id)

            is HealthyRecipeDetailsEvent.UpdateIsFavorite -> updateIsFavorite(id = event.id, isFavorite = event.isFavorite)

            is HealthyRecipeDetailsEvent.OnBackPressed -> resetState()
        }
    }

    private fun updateIsFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch {
            updateHealthyRecipeIsFavoriteUseCase(id, isFavorite)
        }
    }

    private fun resetState() {
        _uiState.update {
            HealthyRecipeDetailsUIState()
        }
    }

    private fun findHealthyByID(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val healthyRecipe = getHealthyRecipeByIdUseCase(id)
            val isFavorite = isHealthyRecipeFavoriteUseCase(id)

            _uiState.update {
                it.copy(
                    isFavorite = isFavorite,
                    healthyRecipe = healthyRecipe,
                    isLoading = false
                )
            }
        }
    }

}