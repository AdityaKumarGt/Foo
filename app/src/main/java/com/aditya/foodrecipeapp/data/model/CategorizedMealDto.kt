package com.aditya.foodrecipeapp.data.model

import com.aditya.foodrecipeapp.domain.model.CategorizedMeal

data class CategorizedMealDto(
    val idMeal: String?,
    val strMeal: String?,
    val strMealThumb: String?
)

fun CategorizedMealDto.toCategorizedMeal(): CategorizedMeal {
    return CategorizedMeal(
        id = idMeal ?: "",
        name = strMeal ?: "",
        image = strMealThumb ?: "",
    )
}