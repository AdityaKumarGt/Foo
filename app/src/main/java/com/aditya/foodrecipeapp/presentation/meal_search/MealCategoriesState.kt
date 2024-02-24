package com.aditya.foodrecipeapp.presentation.meal_search

import com.aditya.foodrecipeapp.domain.model.Meal
import com.aditya.foodrecipeapp.domain.model.MealCategory

data class MealCategoriesState(
    val isLoading: Boolean = false,
    val data: List<MealCategory>? = null,
    val error: String = ""
)