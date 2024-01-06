package com.example.mealapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealapps.R
import com.example.mealapps.adapter.IngredientsAdapter
import com.example.mealapps.databinding.ActivityMealDetailLocalBinding
import com.example.mealapps.local.MealDatabase
import com.example.mealapps.local.entity.MealEntity
import com.example.mealapps.util.MealLocalViewModelFactory

class MealDetailLocal : AppCompatActivity() {
    private lateinit var binding: ActivityMealDetailLocalBinding
    private lateinit var viewModel: MealLocalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailLocalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealId = intent.getIntExtra("mealId", 0)
        val mealDao = MealDatabase.getInstance(this)?.mealDao()
        viewModel = ViewModelProvider(this, MealLocalViewModelFactory(mealDao!!,this)).get(
            MealLocalViewModel::class.java)

        viewModel.getMealsDetail(mealId)
        Log.d("MealDetailLocal", "mana id: $mealId")

        viewModel.isMealInFavorites(mealId).observe(this) { isInFavorites ->
            updateFabFavoriteImage(isInFavorites)
        }

        viewModel.mealDetail.observe(this) { mealEntity ->
            mealEntity?.let {
                binding.apply {
                    var listIngridients: List<HashMap<String, String>> = emptyList()
                    listIngridients = mealEntity.mealIngridients
                    Log.d("MealDetailLocal", "mana data: $listIngridients")
                    Glide.with(this@MealDetailLocal)
                        .load(it.mealThumb)
                        .into(ivDetailMealImageLocal)
                    tvDetailMealTittleLocal.text = it.mealName
                    tvMealInstructionsLocal.text = it.mealInsturction
                    rvIngredientsLocal.layoutManager = LinearLayoutManager(this@MealDetailLocal)
                    rvIngredientsLocal.adapter = IngredientsAdapter(listIngridients)
                    rvIngredientsLocal.setHasFixedSize(true)
                }
            } ?: run {
                Log.d("MealDetailLocal", "Meal details are null")
            }
        }

        viewModel.mealDetail.observe(this@MealDetailLocal){mealDetail ->
            viewModel.isMealInFavorites(mealId).observe(this@MealDetailLocal) { isInFavorites ->
                binding.fabFavoriteLocal.setOnClickListener {

                    if (isInFavorites) {
                        viewModel.removeMealFromFavorites(mealId)
                    } else {
                        val mealEntity = MealEntity(
                            idMeal = mealId,
                            mealName = mealDetail?.mealName ?: "",
                            mealThumb = mealDetail?.mealThumb ?: "",
                            mealIngridients = mealDetail?.mealIngridients ?: emptyList(),
                            mealInsturction = mealDetail?.mealInsturction ?: "",
                            mealYoutube = mealDetail?.mealYoutube ?: ""
                        )
                        viewModel.insertMeal(mealEntity)
                    }
                }
            }
        }

    }
    private fun updateFabFavoriteImage(isInFavorites: Boolean) {
        val imageResource = if (isInFavorites) {
            R.drawable.baseline_bookmark_24
        } else {
            R.drawable.baseline_bookmark_border_24
        }
        binding.fabFavoriteLocal.setImageResource(imageResource)
    }
}