package com.example.nutritionaltable.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutritionaltable.R
import com.example.nutritionaltable.ui.theme.Background
import com.example.nutritionaltable.ui.theme.NutritionalTableTheme
import com.example.nutritionaltable.ui.theme.Secondary
import kotlinx.coroutines.delay

@Composable
fun LoveButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (isSelected: Boolean) -> Unit) {
    var isSelected by remember { mutableStateOf(isSelected) }
    var isPressed by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (isSelected) Secondary else LocalContentColor.current,
        animationSpec = tween(durationMillis = 150)
    )

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            delay(300)
            isPressed = false
        }
    }

    IconButton(
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Background,
            contentColor = color
        ),
        onClick ={
            isPressed = true
            isSelected = !isSelected
            onClick(isSelected)
        }
    ) {
        Icon(
            modifier = Modifier.scale(scale),
            painter = painterResource(id = if(!isSelected) R.drawable.ic_heart else R.drawable.ic_heart_filled),
            contentDescription = stringResource(R.string.botao_coracao)
        )
    }
}

@Preview
@Composable
private fun LoveButtonPreview() {
    NutritionalTableTheme {
        LoveButton(onClick = {})
    }
}


