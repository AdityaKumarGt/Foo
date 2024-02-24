package com.aditya.foodrecipeapp.presentation.meal_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.aditya.foodrecipeapp.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private var _binding: FragmentMealSearchBinding? = null
    val binding: FragmentMealSearchBinding
        get() = _binding!!

    private val searchAdapter = MealSearchAdapter()
    private val mealCategoriesAdapter = MealCategoriesAdapter()
    private val viewModel: MealSearchViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.mealSearchRecycler.apply {
            adapter = searchAdapter
        }

        binding.mealCategoriesRecycler.apply {
            adapter = mealCategoriesAdapter
        }

        viewModel.getMealCategories()

        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    viewModel.getSearchMeals(it)
                    viewModel.isSearchActive = true
                    binding.mealCategoriesRecycler.visibility = View.GONE
                    binding.mealSearchRecycler.visibility = View.VISIBLE

                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })


        if (viewModel.isSearchActive) {
            binding.mealCategoriesRecycler.visibility = View.GONE
            binding.mealSearchRecycler.visibility = View.VISIBLE
        } else {
            binding.mealCategoriesRecycler.visibility = View.VISIBLE
            binding.mealSearchRecycler.visibility = View.GONE
        }


        //to get the searched recipe
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {

                    if (it.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.toMutableList())
                }

            }
        }


        //for mealCategories
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealCategories.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressMealSearch.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let { mealCategories ->

                    if (mealCategories.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressMealSearch.visibility = View.GONE
                    mealCategoriesAdapter.setContentList(mealCategories.toMutableList())
                }

            }
        }


        searchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    it.id
                )
            )
        }

        mealCategoriesAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToCategorizedMealsFragment(
                    it.category
                )
            )
        }


        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.mealSearchRecycler.visibility == View.VISIBLE) {
                        binding.mealSearchRecycler.visibility = View.GONE
                        binding.mealCategoriesRecycler.visibility = View.VISIBLE
                    } else {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            })


    }


}