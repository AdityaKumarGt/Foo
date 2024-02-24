package com.aditya.foodrecipeapp.presentation.categorized_meals

import com.aditya.foodrecipeapp.domain.model.CategorizedMeal
import com.aditya.foodrecipeapp.domain.model.MealDetails

data class CategorizedMealsState(
    val isLoading: Boolean = false,
    val data: List<CategorizedMeal>? = null,
    val error: String = ""
)
