package com.example.mealapps.response

import com.example.mealapps.model.Area
import com.google.gson.annotations.SerializedName

data class AreaResponse(
    @field:SerializedName("meals")
    val areas: List<Area>
)
