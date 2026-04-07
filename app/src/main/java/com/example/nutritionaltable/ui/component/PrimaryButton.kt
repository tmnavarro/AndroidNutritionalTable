package com.example.nutritionaltable.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme.sizing
import com.example.nutritionaltable.ui.theme.Primary
import com.example.nutritionaltable.ui.theme.Typography


@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    ElevatedButton(
        modifier = modifier,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Primary
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = sizing.xs
        ),
        onClick = onClick,
        shape = RoundedCornerShape(size = sizing.md)
    ) {
        Text(
            text = text,
            style = Typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@PreviewLightDark
@Composable
private fun PrimaryButtonPreview() {
    NutritionalTableTheme {
        PrimaryButton(
            text = "Botão Primario"
        ) {}
    }
 }