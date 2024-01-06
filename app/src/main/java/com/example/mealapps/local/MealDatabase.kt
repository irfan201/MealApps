package com.example.mealapps.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mealapps.local.entity.MealEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Database(entities = [MealEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var instance: MealDatabase? = null

        fun getInstance(context:Context): MealDatabase? =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext, MealDatabase::class.java, "Meal.db"
                ).build()
            }
    }
}

class Converters {
    @TypeConverter
    fun fromIngredientsList(value: List<HashMap<String, String>>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIngredientsList(value: String): List<HashMap<String, String>> {
        val type = object : TypeToken<List<HashMap<String, String>>>() {}.type
        return Gson().fromJson(value, type)
    }
}