package com.example.nutritionaltable.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutritionaltable.R
import com.example.nutritionaltable.data.model.HealthyRecipe
import com.example.nutritionaltable.mockHealthyRecipes
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography


@Composable
fun HealthyRecipeCard(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .size(sizing.x3l)
                .clip(
                    shape = RoundedCornerShape(
                        size = sizing.md
                    )
                ),
            painter = painterResource(healthyRecipe.imageRes),
            contentDescription = stringResource(R.string.imagem_item_tabela_nutricional)
        )
        Spacer(
            modifier = Modifier.width(sizing.md)
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = healthyRecipe.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = Typography.headlineMedium
                )
                Text(
                    text = stringResource(R.string.valor_kcal, healthyRecipe.calories.value),
                    style = Typography.bodyLarge
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.g_proteinas_g_carboidratos_da_receita,
                    healthyRecipe.proteins.value,
                    healthyRecipe.carbohydrates.value,
                    ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = Typography.bodyLarge.copy(MaterialTheme.colorScheme.onSecondary)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFf2f2f2)
@Composable
private fun HealthyRecipeCardPreview() {
    NutritionalTableTheme {
        HealthyRecipeCard(
            healthyRecipe = mockHealthyRecipes[0]
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFf2f2f2)
@Composable
private fun HealthyRecipeCardListPreview() {
    NutritionalTableTheme {
        LazyColumn (
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(count = 4) {
                HealthyRecipeCard(
                    modifier = Modifier.padding(sizing.md),
                    healthyRecipe = mockHealthyRecipes[0]
                )
            }

        }
    }
}