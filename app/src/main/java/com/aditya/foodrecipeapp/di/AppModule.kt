package com.aditya.foodrecipeapp.di

import com.aditya.foodrecipeapp.common.Constants
import com.aditya.foodrecipeapp.data.remote.MealSearchAPI
import com.aditya.foodrecipeapp.data.repository.CategorizedMealsRepositoryImpl
import com.aditya.foodrecipeapp.data.repository.MealCategoriesRepositoryImpl
import com.aditya.foodrecipeapp.data.repository.MealDetailsRepositoryImpl
import com.aditya.foodrecipeapp.data.repository.MealSearchRepistoryImpl
import com.aditya.foodrecipeapp.domain.repository.CategorizedMealsRepository
import com.aditya.foodrecipeapp.domain.repository.MealCategoriesRepository
import com.aditya.foodrecipeapp.domain.repository.MealDetailsRepository
import com.aditya.foodrecipeapp.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMealSearchAPI(): MealSearchAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchAPI: MealSearchAPI): MealSearchRepository {
        return MealSearchRepistoryImpl(mealSearchAPI)
    }

    @Provides
    fun provideMealDetails(searchMealSearchAPI: MealSearchAPI): MealDetailsRepository {
        return MealDetailsRepositoryImpl(searchMealSearchAPI)
    }

    @Provides
    fun provideMealCategories(searchMealSearchAPI: MealSearchAPI): MealCategoriesRepository {
        return MealCategoriesRepositoryImpl(searchMealSearchAPI)
    }

    @Provides
    fun provideCategorizedMeals(searchMealSearchAPI: MealSearchAPI): CategorizedMealsRepository {
        return CategorizedMealsRepositoryImpl(searchMealSearchAPI)
    }


}