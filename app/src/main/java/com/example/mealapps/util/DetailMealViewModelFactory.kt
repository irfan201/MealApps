package com.example.mealapps.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealapps.ui.DetailMealViewModel
import com.example.mealapps.local.MealDao

class DetailMealViewModelFactory(private val mealDao: MealDao, private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMealViewModel::class.java)) {
            return DetailMealViewModel(mealDao, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
