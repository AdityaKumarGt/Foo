package com.aditya.foodrecipeapp.data.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.data.model.toCategorizedMeal
import com.aditya.foodrecipeapp.data.model.toMealCategory
import com.aditya.foodrecipeapp.data.remote.MealSearchAPI
import com.aditya.foodrecipeapp.domain.model.CategorizedMeal
import com.aditya.foodrecipeapp.domain.model.MealCategory
import com.aditya.foodrecipeapp.domain.repository.CategorizedMealsRepository
import com.aditya.foodrecipeapp.domain.repository.MealCategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CategorizedMealsRepositoryImpl(private val mealSearchAPI: MealSearchAPI) :
    CategorizedMealsRepository {
    override fun getCategorizedMeals(category: String): Flow<Resource<List<CategorizedMeal>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data = mealSearchAPI.getCategorizedMeals(category)
                val domainData = if (data.meals != null) data.meals.map { it.toCategorizedMeal() } else emptyList()

                domainData?.let {
                    emit(Resource.Success(data = domainData))
                }

            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
            } catch (e: Exception) {

            }
        }
    }
}
