package com.example.mealapps.response

import com.example.mealapps.model.MealData
import com.google.gson.annotations.SerializedName

data class MealResponse (
    @SerializedName("meals") val meals: List<MealData>
)