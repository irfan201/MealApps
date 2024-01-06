package com.example.mealapps.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mealapps.ui.MealLocalViewModel
import com.example.mealapps.local.MealDao

class MealLocalViewModelFactory(private val mealDao: MealDao, private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealLocalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealLocalViewModel(mealDao,context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
