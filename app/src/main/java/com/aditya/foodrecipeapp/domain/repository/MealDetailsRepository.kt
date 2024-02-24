package com.aditya.foodrecipeapp.domain.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.model.MealDetails
import kotlinx.coroutines.flow.Flow

interface MealDetailsRepository {

     fun getMealDetails(id:String): Flow<Resource<MealDetails>>

}