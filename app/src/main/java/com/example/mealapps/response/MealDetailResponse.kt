package com.example.mealapps.response

import com.example.mealapps.model.MealDetail
import com.google.gson.annotations.SerializedName

data class MealDetailResponse (

    @SerializedName("meals")
    val meals: List<MealDetail>
)