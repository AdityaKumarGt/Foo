package com.aditya.foodrecipeapp.data.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.data.model.toDomainMeal
import com.aditya.foodrecipeapp.data.remote.MealSearchAPI
import com.aditya.foodrecipeapp.domain.model.Meal
import com.aditya.foodrecipeapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MealSearchRepistoryImpl(private val mealSearchAPI: MealSearchAPI) : MealSearchRepository {

    override fun getMealSearch(s: String): Flow<Resource<List<Meal>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data = mealSearchAPI.getSearchMealList(s)
                val domainData = if (data.meals != null) data.meals.map { it.toDomainMeal() } else emptyList()
                emit(Resource.Success(data = domainData))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
            } catch (e: Exception) {

            }
        }
    }
}