package com.aditya.foodrecipeapp.presentation.meal_search

import com.aditya.foodrecipeapp.domain.model.Meal

data class MealSearchState(
    val isLoading: Boolean = false,
    val data: List<Meal>? = null,
    val error: String = ""
)
