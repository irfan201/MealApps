package com.example.mealapps.response

import com.example.mealapps.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("meals")
    val categories: List<Category>
)