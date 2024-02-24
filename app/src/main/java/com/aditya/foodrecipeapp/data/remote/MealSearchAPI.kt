package com.aditya.foodrecipeapp.data.remote

import com.aditya.foodrecipeapp.data.model.AllCategoriesDto
import com.aditya.foodrecipeapp.data.model.CategorizedMealDto
import com.aditya.foodrecipeapp.data.model.CategorizedMealsDto
import com.aditya.foodrecipeapp.data.model.MealCategoryDto
import com.aditya.foodrecipeapp.data.model.MealsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchAPI {

    @GET("api/json/v1/1/search.php")
    suspend fun getSearchMealList(
        @Query("s") query: String
    ): MealsDto


    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetails(
        @Query("i") i: String
    ): MealsDto


    //new modifications
   @GET("api/json/v1/1/categories.php")
  // suspend fun getAllMealCategories(): List<MealCategoryDto>?
   suspend fun getAllMealCategories(): AllCategoriesDto


   @GET("api/json/v1/1/filter.php")
   suspend fun getCategorizedMeals(
       @Query("c") category: String
   ): CategorizedMealsDto
}