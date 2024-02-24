package com.aditya.foodrecipeapp.domain.use_case

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.Meal
import com.aditya.foodrecipeapp.domain.model.MealCategory
import com.aditya.foodrecipeapp.domain.repository.MealCategoriesRepository
import com.aditya.foodrecipeapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealCategoriesUseCase @Inject constructor(private val repository: MealCategoriesRepository) {

    operator fun invoke(): Flow<Resource<List<MealCategory>>> {
        return repository.getMealCategories()
    }
}