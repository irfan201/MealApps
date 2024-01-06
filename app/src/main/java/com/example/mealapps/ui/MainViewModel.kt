package com.example.mealapps.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealapps.model.Area
import com.example.mealapps.model.Category
import com.example.mealapps.model.MealData
import com.example.mealapps.network.ApiConfig
import com.example.mealapps.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    private val _mealList = MutableLiveData<List<MealData>>()
    val mealList: LiveData<List<MealData>> get() = _mealList

    private val _searchedMeals = MutableLiveData<List<MealData>>()
    val searchedMeals: LiveData<List<MealData>> get() = _searchedMeals

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _areas = MutableLiveData<List<Area>>()
    val areas: LiveData<List<Area>> get() = _areas


    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categories.value = response.categories
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching categories apa: ${e.message}")
            }
        }
    }

    fun getAreas() {
        viewModelScope.launch {
            try {
                val response = apiService.getAreas()
                _areas.value = response.areas
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching areas: ${e.message}")
            }
        }
    }


    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getMealsByCategory(category)
                _mealList.value = response.meals
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMealsByArea(area: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getMealsByArea(area)
                _mealList.value = response.meals
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchMeals(query: String) {
        viewModelScope.launch {
            try {
                val response = apiService.searchMeals(query)
                _searchedMeals.value = response.meals
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error searching meals: ${e.message}")
            } finally {
            }
        }
    }
}
