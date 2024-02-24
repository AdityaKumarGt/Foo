package com.aditya.foodrecipeapp.presentation.categorized_meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.use_case.GetCategorizedMealsUseCase
import com.aditya.foodrecipeapp.domain.use_case.GetMealDetailsUseCase
import com.aditya.foodrecipeapp.presentation.meal_details.MealDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategorizedMealsViewModel @Inject constructor(
    private val categorizedMealsUseCase: GetCategorizedMealsUseCase
) : ViewModel() {


    private val _categorizedMeals = MutableStateFlow(CategorizedMealsState())
    val categorizedMeals: StateFlow<CategorizedMealsState> = _categorizedMeals


    fun getCategorizedMeals(category: String) {
        categorizedMealsUseCase(category).onEach {
            when (it) {
                is Resource.Loading -> {
                    _categorizedMeals.value = CategorizedMealsState(isLoading = true)
                }
                is Resource.Error -> {
                    _categorizedMeals.value = CategorizedMealsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _categorizedMeals.value = CategorizedMealsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}