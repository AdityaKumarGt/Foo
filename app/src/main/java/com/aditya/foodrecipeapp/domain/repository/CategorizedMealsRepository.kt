package com.aditya.foodrecipeapp.domain.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.CategorizedMeal
import com.aditya.foodrecipeapp.domain.model.MealCategory
import kotlinx.coroutines.flow.Flow

interface CategorizedMealsRepository {

    fun getCategorizedMeals(category: String): Flow<Resource<List<CategorizedMeal>>>

}