package com.example.mealapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealapps.adapter.MealAdapter
import com.example.mealapps.databinding.ActivityMainBinding
import com.example.mealapps.model.Area
import com.example.mealapps.model.Category

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealAdapter = MealAdapter{ clickedMeal ->
            val intent = Intent(this, DetailsMeal::class.java)
            intent.putExtra("mealId", clickedMeal.idMeal)
            startActivity(intent)
        }

        binding.rvMeal.layoutManager = LinearLayoutManager(this)
        binding.rvMeal.adapter = mealAdapter

        binding.btnFilter.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        binding.fabBookmark.setOnClickListener {
            val intent = Intent(this, MealLocalActivity::class.java)
            startActivity(intent)
        }

        viewModel.mealList.observe(this, { meals ->
            mealAdapter.submitList(meals)
        })

        viewModel.getMealsByCategory("Seafood")

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.searchMeals(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    viewModel.getMealsByCategory("Seafood")
                }
                return true
            }
        })

        viewModel.searchedMeals.observe(this, { searchedMeals ->
            mealAdapter.submitSearchList(searchedMeals)
        })
    }
    fun onCategoryClicked(category: Category) {
        viewModel.getMealsByCategory(category.strCategory)
    }

    fun onAreaClicked(area: Area) {
        viewModel.getMealsByArea(area.strArea)
    }

}

