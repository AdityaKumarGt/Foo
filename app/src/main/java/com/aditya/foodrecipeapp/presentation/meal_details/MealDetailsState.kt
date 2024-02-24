package com.aditya.foodrecipeapp.presentation.meal_details

import com.aditya.foodrecipeapp.domain.model.MealDetails

data class MealDetailsState(
    val isLoading: Boolean = false,
    val data: MealDetails? = null,
    val error: String = ""
)