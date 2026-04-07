package com.example.nutritionaltable.ui.component.healthyRecipeDetails

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.nutritionaltable.R
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Primary
import com.example.nutritionaltable.ui.theme.SurfaceElement
import com.example.nutritionaltable.ui.theme.Typography
import kotlin.math.roundToInt

private const val DEFAULT_MAS_NUTRIENT_VALUE = 100f
private const val DEFAULT_TOTAL_BARS = 6

@Composable
fun HealthyRecipeNutrientBar(
    modifier: Modifier = Modifier,
    name: String,
    value: Float,
    maxValue: Float = DEFAULT_MAS_NUTRIENT_VALUE
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = name,
                style = Typography.headlineMedium
            )
            Text(
                text = stringResource(
                    R.string.valor_g,
                    value
                )
            )
        }
        Spacer(modifier = Modifier.height(sizing.sm))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {
            repeat(times = DEFAULT_TOTAL_BARS) { index ->
                Canvas(
                    modifier = Modifier.weight(1f).height(sizing.sm)
                ) {
                    val shouldFillNutrientBar =  shouldFillNutrientBar(index, value, maxValue)
                    drawRoundRect(
                        color = if(shouldFillNutrientBar) Primary else SurfaceElement,
                        cornerRadius = CornerRadius(x= 8.dp.toPx(), y= 8.dp.toPx())
                    )
                }
            }
        }
    }
}

private fun shouldFillNutrientBar(
    barPosition: Int,
    value: Float,
    maxValue: Float
): Boolean = barPosition <= (value * DEFAULT_TOTAL_BARS / maxValue).roundToInt() - 1

class NutrientValueProvider: PreviewParameterProvider<Float> {
    override val values: Sequence<Float>
        get() = sequenceOf(0f, 50f, 75f, 100f)

}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeNutrientBarPreview(
    @PreviewParameter(NutrientValueProvider::class) value : Float
) {
    NutritionalTableTheme {
        HealthyRecipeNutrientBar(
            modifier = Modifier.padding(sizing.md),
            name = "Proteínas",
            value = value
        )
    }
}