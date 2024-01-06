package com.example.mealapps.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapps.local.MealDao
import com.example.mealapps.local.entity.MealEntity
import kotlinx.coroutines.launch

class MealLocalViewModel(private var mealDao: MealDao,private val context: Context) : ViewModel() {


    val allMeals: LiveData<List<MealEntity>> = mealDao.getAllMeals()

    private val _mealDetail = MutableLiveData<MealEntity?>()
    val mealDetail: LiveData<MealEntity?>
        get() = _mealDetail

    fun getMealsDetail(mealId: Int) = viewModelScope.launch {
        try {
            mealDao.getMealById(mealId).observeForever { result ->
                _mealDetail.postValue(result)
            }
        } catch (e: Exception) {
            Log.e("MealLocalViewModel", "Error getting meal details: ${e.message}")
        }
    }

    fun insertMeal(mealEntity: MealEntity) {
        viewModelScope.launch {
            try {
                mealDao.insert(mealEntity)
                showToast("Meal saved successfully")
            } catch (e: Exception) {
                showToast("Error saving meal: ${e.message}")
            }
        }
    }
    fun isMealInFavorites(mealId: Int): LiveData<Boolean> {
        return mealDao.isMealInFavorites(mealId)
    }

    fun removeMealFromFavorites(mealId: Int) {
        viewModelScope.launch {
            try {
                mealDao.deleteById(mealId)
                showToast("Meal removed from favorites")
            } catch (e: Exception) {
                showToast("Error removing meal from favorites: ${e.message}")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}