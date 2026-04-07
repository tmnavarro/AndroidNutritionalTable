package com.example.nutritionaltable.ui.component.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Typography

data class HealthyRecipe(
    val title: String,
    @DrawableRes
    val imageRes: Int,
    val calories: Float,
    val proteins: Float,
    val carbohydrates: Float
)

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
                .size(sizing.x2l)
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
                    text = healthyRecipe.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = Typography.headlineMedium
                )
                Text(
                    text = stringResource(R.string.valor_kcal, healthyRecipe.calories),
                    style = Typography.bodyLarge
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    R.string.proteinas_e_caiboidratos,
                    healthyRecipe.proteins,
                    healthyRecipe.carbohydrates,
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

    val healthyRecipe = HealthyRecipe(
        title = "Titulo da receita",
        imageRes = R.drawable.img_assorted_salad,
        calories = 1200f,
        proteins = 50f,
        carbohydrates = 250.5f,
    )

    NutritionalTableTheme {
        HealthyRecipeCard(
            healthyRecipe = healthyRecipe
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFf2f2f2)
@Composable
private fun HealthyRecipeCardListPreview() {

    val healthyRecipe = HealthyRecipe(
        title = "Titulo da receita",
        imageRes = R.drawable.img_assorted_salad,
        calories = 1200f,
        proteins = 50f,
        carbohydrates = 250.5f,
    )

    NutritionalTableTheme {
        LazyColumn (
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(count = 4) {
                HealthyRecipeCard(
                    modifier = Modifier.padding(sizing.md),
                    healthyRecipe = healthyRecipe
                )
            }

        }
    }
}