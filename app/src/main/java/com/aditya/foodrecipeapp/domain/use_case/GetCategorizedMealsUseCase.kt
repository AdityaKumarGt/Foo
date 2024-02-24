package com.aditya.foodrecipeapp.domain.use_case

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.CategorizedMeal
import com.aditya.foodrecipeapp.domain.model.MealCategory
import com.aditya.foodrecipeapp.domain.repository.CategorizedMealsRepository
import com.aditya.foodrecipeapp.domain.repository.MealCategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategorizedMealsUseCase @Inject constructor (private val repository: CategorizedMealsRepository) {

    operator fun invoke(category: String): Flow<Resource<List<CategorizedMeal>>> {
        return repository.getCategorizedMeals(category)
    }
}