package com.aditya.foodrecipeapp.domain.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealSearchRepository {

     fun getMealSearch(s:String): Flow<Resource<List<Meal>>>

}