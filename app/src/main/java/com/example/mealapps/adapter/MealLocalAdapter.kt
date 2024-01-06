package com.example.mealapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealapps.R
import com.example.mealapps.local.entity.MealEntity

class MealLocalAdapter(private val clickListener: (MealEntity) -> Unit) : RecyclerView.Adapter<MealLocalAdapter.MealViewHolder>() {

    private var meals: List<MealEntity> = emptyList()

    fun submitList(list: List<MealEntity>) {
        meals = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_local, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
        holder.itemView.setOnClickListener {
            clickListener(meal)
        }
    }

    override fun getItemCount(): Int = meals.size

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mealName: TextView = itemView.findViewById(R.id.tv_meal_name_local)
        private val mealImage: ImageView = itemView.findViewById(R.id.iv_meal_image_local)

        fun bind(meal: MealEntity) {
            mealName.text = meal.mealName
            Glide.with(itemView.context)
                .load(meal.mealThumb)
                .into(mealImage)
        }
    }
}