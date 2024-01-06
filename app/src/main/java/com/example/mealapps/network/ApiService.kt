package com.example.mealapps.network

import com.example.mealapps.model.Category
import com.example.mealapps.response.AreaResponse
import com.example.mealapps.response.CategoryResponse
import com.example.mealapps.response.MealDetailResponse
import com.example.mealapps.response.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") mealId: Int): MealDetailResponse

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse

    @GET("list.php?c=list")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByArea(@Query("a") category: String): MealResponse

    @GET("list.php?a=list")
    suspend fun getAreas(): AreaResponse

}