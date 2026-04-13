package com.example.nutritionaltable.ui.screen.home

sealed interface HomeEvent {
    data object OnInit: HomeEvent
}