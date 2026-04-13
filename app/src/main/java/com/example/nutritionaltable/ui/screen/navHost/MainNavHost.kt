package com.example.nutritionaltable.ui.screen.navHost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutritionaltable.core.navigation.UIArgument
import com.example.nutritionaltable.core.navigation.UIRoute
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.ui.screen.healthyRecipeDetails.HealthyRecipeDetailsScreen
import com.example.nutritionaltable.ui.screen.healthyRecipeDetails.HealthyRecipeDetailsViewModel
import com.example.nutritionaltable.ui.screen.home.HomeScreen
import com.example.nutritionaltable.ui.screen.home.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val homeViewModel = koinViewModel<HomeViewModel>()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    val healthyRecipeDetailsViewModel = koinViewModel<HealthyRecipeDetailsViewModel>()
    val healthyRecipeDetailsUIState by healthyRecipeDetailsViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(modifier = modifier, navController = navController, startDestination = UIRoute.Home) {
        composable<UIRoute.Home> {
            HomeScreen(
                uiState = homeUiState,
                onEvent = homeViewModel::onEvent,
                onNavigationToDetails = { healthyRecipeId ->
                    navController.navigate(UIRoute.HealthyRecipeDetails(healthyRecipeId = healthyRecipeId))
                })
        }

        composable<UIRoute.HealthyRecipeDetails> { navBackStackEntry ->
            val healthyRecipeId = navBackStackEntry.arguments?.getString(UIArgument.HEALTHY_RECIPE_ID.key)

            healthyRecipeId?.let {
                val healthyRecipe =
                    mockHealthyRecipes.find { healthyRecipe -> healthyRecipe.id.toString() == healthyRecipeId }
                if (healthyRecipe == null) return@composable
                HealthyRecipeDetailsScreen(
                    id = healthyRecipeId,
                    onNavigateBack = { navController.popBackStack() },
                    onEvent = healthyRecipeDetailsViewModel::onEvent,
                    uiState = healthyRecipeDetailsUIState
                    )
            }
        }
    }
}