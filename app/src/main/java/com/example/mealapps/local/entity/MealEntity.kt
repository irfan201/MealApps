package com.example.mealapps.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mealapps.local.Converters
import kotlinx.parcelize.Parcelize

@Entity(tableName = "meal_table")
@Parcelize
@TypeConverters(Converters::class)
data class MealEntity(

    @PrimaryKey
    val idMeal: Int,

    val mealName: String,
    val mealThumb: String,
    val mealInsturction: String,
    val mealIngridients: List<HashMap<String, String>>,
    val mealYoutube: String,
) : Parcelable