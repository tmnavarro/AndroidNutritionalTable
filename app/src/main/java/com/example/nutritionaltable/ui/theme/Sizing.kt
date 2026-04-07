package com.example.nutritionaltable.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalSizing = staticCompositionLocalOf { Sizing() }

data class Sizing(
    val x1: Dp = 1.dp,
    val x2: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val x2l: Dp = 64.dp,
    val x3l: Dp = 128.dp,
    val x4l: Dp = 256.dp
)
