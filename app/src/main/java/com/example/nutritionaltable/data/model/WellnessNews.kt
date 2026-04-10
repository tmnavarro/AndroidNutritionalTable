package com.example.nutritionaltable.data.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class WellnessNews(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    @param:DrawableRes
    val imageRes: Int,
    val readTimeInMinutes: Int,
    val tags: List<WellnessNewsTag>
)

