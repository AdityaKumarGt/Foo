package com.aditya.foodrecipeapp.presentation.meal_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.foodrecipeapp.databinding.ItemMealCategoryBinding
import com.aditya.foodrecipeapp.databinding.ViewHolderSearchListBinding
import com.aditya.foodrecipeapp.domain.model.Meal
import com.aditya.foodrecipeapp.domain.model.MealCategory

class MealCategoriesAdapter : RecyclerView.Adapter<MealCategoriesAdapter.MyViewHolder>() {

    private var listener :((MealCategory)->Unit)?=null
    var list = mutableListOf<MealCategory>()

    fun setContentList(list: MutableList<MealCategory>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: ItemMealCategoryBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoriesAdapter.MyViewHolder {
        val binding = ItemMealCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(MealCategory)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: MealCategoriesAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.mealCategory = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }

}
