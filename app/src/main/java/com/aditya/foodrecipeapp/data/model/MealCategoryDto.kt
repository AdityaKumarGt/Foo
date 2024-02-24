package com.aditya.foodrecipeapp.data.model

import com.aditya.foodrecipeapp.domain.model.MealCategory

data class MealCategoryDto(
    val idCategory: String?,
    val strCategory: String?,
    val strCategoryDescription: String?,
    val strCategoryThumb: String?
)

fun MealCategoryDto.toMealCategory(): MealCategory {
    return MealCategory(
        category = strCategory ?: "",
        description = strCategoryDescription ?: "",
        image = strCategoryThumb ?: ""
    )
}