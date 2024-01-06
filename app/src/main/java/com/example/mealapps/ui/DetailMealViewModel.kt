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
import com.example.mealapps.model.MealDetail
import com.example.mealapps.network.ApiConfig
import kotlinx.coroutines.launch

class DetailMealViewModel(private val mealDao: MealDao, private val context: Context) : ViewModel() {
    private val _mealDetail = MutableLiveData<MealDetail>()
    val mealDetail: LiveData<MealDetail> get() = _mealDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val apiService = ApiConfig.getApiService()

    fun getMealDetails(mealId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.getMealDetails(mealId)
                Log.d("DetailsMealViewModel", "Raw API response: $response")
                _mealDetail.value = response.meals.firstOrNull()
            } catch (e: Exception) {
                Log.e("DetailsMealViewModel", "Error fetching meal details: ${e.message}")
            } finally {
                _isLoading.value = false
            }
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
