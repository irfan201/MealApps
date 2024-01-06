package com.example.mealapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealapps.databinding.ItemMealsBinding
import com.example.mealapps.model.MealData

class MealAdapter(private val onItemClickListener: (MealData) -> Unit) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var mealList: List<MealData> = emptyList()

    fun submitList(list: List<MealData>) {
        mealList = list
        notifyDataSetChanged()
    }

    fun submitSearchList(list: List<MealData>) {
        mealList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMealsBinding.inflate(inflater, parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = mealList[position]
        holder.bind(meal)

        holder.itemView.setOnClickListener {
            onItemClickListener(meal)
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    inner class MealViewHolder(private val binding: ItemMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: MealData) {
            // Bind your data to the ViewHolder views here
            binding.tvMealName.text = meal.strMeal

            Glide.with(binding.root.context)
                .load(meal.strMealThumb)
                .into(binding.ivMealImage)
        }
    }
}
