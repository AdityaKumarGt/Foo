package com.aditya.foodrecipeapp.presentation.categorized_meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.foodrecipeapp.databinding.ItemCategorizedMealBinding
import com.aditya.foodrecipeapp.databinding.ItemMealCategoryBinding
import com.aditya.foodrecipeapp.domain.model.CategorizedMeal
import com.aditya.foodrecipeapp.domain.model.MealCategory

class CategorizedMealsAdapter : RecyclerView.Adapter<CategorizedMealsAdapter.MyViewHolder>() {

    private var listener :((CategorizedMeal)->Unit)?=null
    var list = mutableListOf<CategorizedMeal>()

    fun setContentList(list: MutableList<CategorizedMeal>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: ItemCategorizedMealBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorizedMealsAdapter.MyViewHolder {
        val binding = ItemCategorizedMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(CategorizedMeal)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: CategorizedMealsAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.categorizedMeal = this.list[position]

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
