package com.example.mealapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapps.adapter.MealLocalAdapter
import com.example.mealapps.databinding.ActivityMealLocalBinding
import com.example.mealapps.local.MealDatabase
import com.example.mealapps.util.MealLocalViewModelFactory

class MealLocalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealLocalBinding
    private lateinit var viewModel: MealLocalViewModel
    private lateinit var mealLocalAdapter: MealLocalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealLocalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDao = MealDatabase.getInstance(this)?.mealDao()
        viewModel = ViewModelProvider(this, MealLocalViewModelFactory(mealDao!!,this)).get(
            MealLocalViewModel::class.java)

        mealLocalAdapter = MealLocalAdapter { clickedMeal ->
            val intent = Intent(this, MealDetailLocal::class.java)
            intent.putExtra("mealId", clickedMeal.idMeal)
            startActivity(intent)
        }


        binding.rvMealLocal.layoutManager = LinearLayoutManager(this)
        binding.rvMealLocal.adapter = mealLocalAdapter


        viewModel.allMeals.observe(this) { meals ->
            mealLocalAdapter.submitList(meals)
        }
    }
}