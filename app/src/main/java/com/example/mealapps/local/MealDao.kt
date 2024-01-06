package com.example.mealapps.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mealapps.local.entity.MealEntity

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealEntity: MealEntity)

    @Query("SELECT * FROM meal_table")
    fun getAllMeals(): LiveData<List<MealEntity>>

    @Query("DELETE FROM meal_table WHERE idMeal = :idMeal")
    suspend fun deleteById(idMeal: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM meal_table WHERE idMeal = :idMeal LIMIT 1)")
    fun isMealInFavorites(idMeal: Int): LiveData<Boolean>

    @Query("SELECT * FROM meal_table WHERE idMeal = :idMeal")
    fun getMealById(idMeal: Int): LiveData<MealEntity>

}