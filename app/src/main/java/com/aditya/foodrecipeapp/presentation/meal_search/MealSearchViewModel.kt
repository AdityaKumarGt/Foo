package com.aditya.foodrecipeapp.presentation.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.foodrecipeapp.common.Resource
import com.aditya.foodrecipeapp.domain.use_case.MealCategoriesUseCase
import com.aditya.foodrecipeapp.domain.use_case.SearchMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(
    private val mealSearchMealsUseCase: SearchMealsUseCase,
    private val mealCategoriesUseCase: MealCategoriesUseCase
) : ViewModel() {

    var isSearchActive: Boolean = false

    private val _mealSearchList = MutableStateFlow(MealSearchState())
    val mealSearchList: StateFlow<MealSearchState> = _mealSearchList

    private val _mealCategories = MutableStateFlow(MealCategoriesState())
    val mealCategories: StateFlow<MealCategoriesState> = _mealCategories

    fun getSearchMeals(s: String) {
        mealSearchMealsUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealSearchList.value = MealSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _mealSearchList.value = MealSearchState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMealCategories(){
        mealCategoriesUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _mealCategories.value = MealCategoriesState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealCategories.value = MealCategoriesState(data = result.data)
                }
                is Resource.Error -> {
                    _mealCategories.value = MealCategoriesState(error = result.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }


}