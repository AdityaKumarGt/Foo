package com.aditya.foodrecipeapp.domain.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.MealCategory
import kotlinx.coroutines.flow.Flow

interface MealCategoriesRepository {
    fun getMealCategories(): Flow<Resource<List<MealCategory>>>

}