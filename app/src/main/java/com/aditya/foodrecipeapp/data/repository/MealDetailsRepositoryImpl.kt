package com.aditya.foodrecipeapp.data.repository

import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.data.model.toDomainMealDetails
import com.aditya.foodrecipeapp.data.remote.MealSearchAPI
import com.aditya.foodrecipeapp.domain.model.MealDetails
import com.aditya.foodrecipeapp.domain.repository.MealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MealDetailsRepositoryImpl(private val mealSearchAPI: MealSearchAPI) : MealDetailsRepository {

    override fun getMealDetails(id: String): Flow<Resource<MealDetails>> {
        return flow {
            try {
                emit(Resource.Loading())
                val data =  mealSearchAPI.getMealDetails(id)
                    val domainData = data.meals?.get(0)?.toDomainMealDetails()
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