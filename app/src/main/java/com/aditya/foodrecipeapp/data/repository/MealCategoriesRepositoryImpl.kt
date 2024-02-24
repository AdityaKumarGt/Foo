package com.aditya.foodrecipeapp.data.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.data.model.toDomainMeal
import com.aditya.foodrecipeapp.data.model.toDomainMealDetails
import com.aditya.foodrecipeapp.data.model.toMealCategory
import com.aditya.foodrecipeapp.data.remote.MealSearchAPI
import com.aditya.foodrecipeapp.domain.model.MealCategory
import com.aditya.foodrecipeapp.domain.repository.MealCategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MealCategoriesRepositoryImpl(private val mealSearchAPI: MealSearchAPI) : MealCategoriesRepository {
    override fun getMealCategories(): Flow<Resource<List<MealCategory>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data =  mealSearchAPI.getAllMealCategories()
                val domainData =if (data.categories != null) data.categories.map { it.toMealCategory() } else emptyList()

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