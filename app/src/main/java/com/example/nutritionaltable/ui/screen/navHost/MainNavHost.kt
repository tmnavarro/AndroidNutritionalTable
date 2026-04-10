package com.example.nutritionaltable.ui.screen.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutritionaltable.core.navigation.UIRoute
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.ui.screen.healthyRecipeDetails.HealthyRecipeDetails
import com.example.nutritionaltable.ui.screen.home.HomeScreen

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(modifier = modifier, navController = navController, startDestination = UIRoute.Home) {
        composable<UIRoute.Home> {
            HomeScreen(
                onNavigationToDetails = { healthyRecipeId ->
                    navController.navigate(UIRoute.HealthyRecipeDetails(healthyRecipeId = healthyRecipeId))
                })
        }

        composable<UIRoute.HealthyRecipeDetails> { navBackStackEntry ->
            val healthyRecipeId = navBackStackEntry.arguments?.getString("healthyRecipeId")

            healthyRecipeId?.let {
                val healthyRecipe =
                    mockHealthyRecipes.find { healthyRecipe -> healthyRecipe.id.toString() == healthyRecipeId }
                if (healthyRecipe == null) return@composable
                HealthyRecipeDetails(
                    healthyRecipe = healthyRecipe,
                    onNavigateBack = { navController.popBackStack() },
                    onClickFavorite = {})
            }
        }
    }
}