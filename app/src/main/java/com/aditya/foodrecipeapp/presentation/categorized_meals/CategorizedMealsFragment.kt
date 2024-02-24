package com.aditya.foodrecipeapp.presentation.categorized_meals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aditya.foodrecipeapp.R
import com.aditya.foodrecipeapp.databinding.FragmentCategorizedMealsBinding
import com.aditya.foodrecipeapp.databinding.FragmentMealSearchBinding
import com.aditya.foodrecipeapp.presentation.meal_details.MealDetailsFragmentArgs
import com.aditya.foodrecipeapp.presentation.meal_search.MealCategoriesAdapter
import com.aditya.foodrecipeapp.presentation.meal_search.MealSearchAdapter
import com.aditya.foodrecipeapp.presentation.meal_search.MealSearchFragmentDirections
import com.aditya.foodrecipeapp.presentation.meal_search.MealSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategorizedMealsFragment : Fragment() {

    private var _binding: FragmentCategorizedMealsBinding? = null
    val binding: FragmentCategorizedMealsBinding
        get() = _binding!!

    private val categorizedMealsAdapter = CategorizedMealsAdapter()
    private val viewModel: CategorizedMealsViewModel by viewModels()
    private val args: CategorizedMealsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategorizedMealsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.categorizedMealsRecycler.apply {
            adapter = categorizedMealsAdapter
        }

        args.category?.let {
            binding.categoryName.text = it
            viewModel.getCategorizedMeals(it)
        }




        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.categorizedMeals.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let { categorizedMeals ->
                    if (categorizedMeals.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    categorizedMealsAdapter.setContentList(categorizedMeals.toMutableList())
                }
            }
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }


        categorizedMealsAdapter.itemClickListener {
            findNavController().navigate(
                CategorizedMealsFragmentDirections.actionCategorizedMealsFragmentToMealDetailsFragment(
                    it.id
                )
            )
        }

    }
}