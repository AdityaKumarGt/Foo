package com.aditya.foodrecipeapp.domain.use_case

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.MealDetails
import com.aditya.foodrecipeapp.domain.repository.MealDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: MealDetailsRepository) {

    operator fun invoke(id: String): Flow<Resource<MealDetails>>{
        return repository.getMealDetails(id)
    }

}
