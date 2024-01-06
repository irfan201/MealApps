package com.example.mealapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapps.R

class IngredientsAdapter(private val ingredients: List<HashMap<String, String>>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName: TextView = itemView.findViewById(R.id.tv_ingredient_name)
        val ingredientMeasure: TextView = itemView.findViewById(R.id.tv_ingredient_measure)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredients, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        val ingredientName = ingredient.keys.first()
        val ingredientMeasure = ingredient[ingredientName]
        holder.ingredientName.text = ingredientName
        holder.ingredientMeasure.text = ingredientMeasure?.toString()
    }

    override fun getItemCount(): Int = ingredients.size
}
