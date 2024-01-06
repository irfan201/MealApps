package com.example.mealapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapps.R
import com.example.mealapps.model.Area
import com.example.mealapps.model.Category

class AreaAdapter:RecyclerView.Adapter<AreaAdapter.AreaViewHolder>() {
    private var itemClickListener: ((Area) -> Unit)? = null
    private var areas: List<Area> = emptyList()

    fun submitList(list: List<Area>) {
        areas = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Area) -> Unit) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaAdapter.AreaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_area, parent, false)
        return AreaAdapter.AreaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AreaAdapter.AreaViewHolder, position: Int) {
        val area = areas[position]
        holder.bind(area)
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(area)
        }
    }

    override fun getItemCount(): Int = areas.size

    class AreaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val areaName: TextView = itemView.findViewById(R.id.areaName)

        fun bind(area: Area) {
            areaName.text = area.strArea
        }
    }
}