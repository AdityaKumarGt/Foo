package com.aditya.foodrecipeapp.domain.use_case

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.Meal
import com.aditya.foodrecipeapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMealsUseCase @Inject constructor(private val repository: MealSearchRepository) {

    operator fun invoke(q: String): Flow<Resource<List<Meal>>> {
        return repository.getMealSearch(q)
    }

}