package com.example.mealapps.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealapps.R
import com.example.mealapps.adapter.IngredientsAdapter
import com.example.mealapps.databinding.ActivityDetailsMealBinding
import com.example.mealapps.local.MealDatabase
import com.example.mealapps.local.entity.MealEntity
import com.example.mealapps.util.DetailMealViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailsMeal : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMealBinding
    private lateinit var viewModel: DetailMealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDao = MealDatabase.getInstance(applicationContext)?.mealDao()
        viewModel = ViewModelProvider(
            this,
            DetailMealViewModelFactory(mealDao!!, this@DetailsMeal)
        ).get(DetailMealViewModel::class.java)

        val mealId = intent.getIntExtra("mealId", 0)

        Log.d("DetailsMeal", "mealId: $mealId")

        viewModel.isMealInFavorites(mealId).observe(this) { isInFavorites ->
            updateFabFavoriteImage(isInFavorites)
        }

        viewModel.getMealDetails(mealId)

        viewModel.mealDetail.observe(this) { mealDetail ->
            binding.apply {

                var listIngridients: List<HashMap<String, String>> = emptyList()

                val ingridients = mealDetail.ingredients.toString()
                val ingridients2 = mealDetail.ingredients2.toString()
                val ingridients3 = mealDetail.ingredients3.toString()
                val ingridients4 = mealDetail.ingredients4.toString()
                val ingridients5 = mealDetail.ingredients5.toString()
                val ingridients6 = mealDetail.ingredients6.toString()
                val ingridients7 = mealDetail.ingredients7.toString()
                val ingridients8 = mealDetail.ingredients8.toString()
                val ingridients9 = mealDetail.ingredients9.toString()
                val ingridients10 = mealDetail.ingredients10.toString()
                val ingridients11 = mealDetail.ingredients11.toString()
                val ingridients12 = mealDetail.ingredients12.toString()
                val ingridients13 = mealDetail.ingredients13.toString()
                val ingridients14 = mealDetail.ingredients14.toString()
                val ingridients15 = mealDetail.ingredients15.toString()
                val ingridients16 = mealDetail.ingredients16.toString()
                val ingridients17 = mealDetail.ingredients17.toString()
                val ingridients18 = mealDetail.ingredients18.toString()
                val ingridients19 = mealDetail.ingredients19.toString()
                val ingridients20 = mealDetail.ingredients20.toString()
                val measure = mealDetail.measure.toString()
                val measure2 = mealDetail.measure2.toString()
                val measure3 = mealDetail.measure3.toString()
                val measure4 = mealDetail.measure4.toString()
                val measure5 = mealDetail.measure5.toString()
                val measure6 = mealDetail.measure6.toString()
                val measure7 = mealDetail.measure7.toString()
                val measure8 = mealDetail.measure8.toString()
                val measure9 = mealDetail.measure9.toString()
                val measure10 = mealDetail.measure10.toString()
                val measure11 = mealDetail.measure11.toString()
                val measure12 = mealDetail.measure12.toString()
                val measure13 = mealDetail.measure13.toString()
                val measure14 = mealDetail.measure14.toString()
                val measure15 = mealDetail.measure15.toString()
                val measure16 = mealDetail.measure16.toString()
                val measure17 = mealDetail.measure17.toString()
                val measure18 = mealDetail.measure18.toString()
                val measure19 = mealDetail.measure19.toString()
                val measure20 = mealDetail.measure20.toString()
                val hashMap = HashMap<String, String>()
                val hashMap2 = HashMap<String, String>()
                val hashMap3 = HashMap<String, String>()
                val hashMap4 = HashMap<String, String>()
                val hashMap5 = HashMap<String, String>()
                val hashMap6 = HashMap<String, String>()
                val hashMap7 = HashMap<String, String>()
                val hashMap8 = HashMap<String, String>()
                val hashMap9 = HashMap<String, String>()
                val hashMap10 = HashMap<String, String>()
                val hashMap11 = HashMap<String, String>()
                val hashMap12 = HashMap<String, String>()
                val hashMap13 = HashMap<String, String>()
                val hashMap14 = HashMap<String, String>()
                val hashMap15 = HashMap<String, String>()
                val hashMap16 = HashMap<String, String>()
                val hashMap17 = HashMap<String, String>()
                val hashMap18 = HashMap<String, String>()
                val hashMap19 = HashMap<String, String>()
                val hashMap20 = HashMap<String, String>()
                hashMap[ingridients] = measure
                hashMap2[ingridients2] = measure2
                hashMap3[ingridients3] = measure3
                hashMap4[ingridients4] = measure4
                hashMap5[ingridients5] = measure5
                hashMap6[ingridients6] = measure6
                hashMap7[ingridients7] = measure7
                hashMap8[ingridients8] = measure8
                hashMap9[ingridients9] = measure9
                hashMap10[ingridients10] = measure10
                hashMap11[ingridients11] = measure11
                hashMap12[ingridients12] = measure12
                hashMap13[ingridients13] = measure13
                hashMap14[ingridients14] = measure14
                hashMap15[ingridients15] = measure15
                hashMap16[ingridients16] = measure16
                hashMap17[ingridients17] = measure17
                hashMap18[ingridients18] = measure18
                hashMap19[ingridients19] = measure19
                hashMap20[ingridients20] = measure20
                listIngridients += hashMap
                listIngridients += hashMap2
                listIngridients += hashMap3
                listIngridients += hashMap4
                listIngridients += hashMap5
                listIngridients += hashMap6
                listIngridients += hashMap7
                listIngridients += hashMap8
                listIngridients += hashMap9
                listIngridients += hashMap10
                listIngridients += hashMap11
                listIngridients += hashMap12
                listIngridients += hashMap13
                listIngridients += hashMap14
                listIngridients += hashMap15
                listIngridients += hashMap16
                listIngridients += hashMap17
                listIngridients += hashMap18
                listIngridients += hashMap19
                listIngridients += hashMap20


                tvMealInstructions.text = mealDetail.strInstructions
                tvDetailMealTittle.text = mealDetail.strMeal
                btnPlayVideo.setOnClickListener {
                    val videoUrl = mealDetail.strYoutube
                    if (!videoUrl.isNullOrEmpty()) {
                        playVideo(videoUrl)
                    }
                }
                Glide.with(this@DetailsMeal)
                    .load(mealDetail.strMealThumb)
                    .into(ivDetailMealImage)
                rvIngredients.layoutManager = LinearLayoutManager(this@DetailsMeal)
                rvIngredients.adapter = IngredientsAdapter(listIngridients)
                rvIngredients.setHasFixedSize(true)

                Log.d("DetailsMeal", "apakah ada: ${mealDetail.strMeal}")


                viewModel.isMealInFavorites(mealId).observe(this@DetailsMeal) { isInFavorites ->
                    binding.fabFavorite.setOnClickListener {

                        if (isInFavorites) {
                            viewModel.removeMealFromFavorites(mealId)
                        } else {
                            val mealEntity = MealEntity(
                                idMeal = mealId,
                                mealName = mealDetail.strMeal,
                                mealThumb = mealDetail.strMealThumb,
                                mealIngridients = listIngridients,
                                mealInsturction = mealDetail.strInstructions ?: "",
                                mealYoutube = mealDetail.strYoutube ?: ""
                            )
                            viewModel.insertMeal(mealEntity)
                        }
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
        binding.fabFavorite.setImageResource(imageResource)
    }

    private fun playVideo(videoUrl: String) {
        val youTubePlayerView: YouTubePlayerView = binding.youtubePlayerView

        youTubePlayerView.visibility = View.VISIBLE
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                val videoId = extractVideoId(videoUrl)
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.play()
            }
        })

        // Sembunyikan ImageView dan TextView
        binding.ivDetailMealImage.visibility = View.GONE
        binding.tvDetailMealTittle.visibility = View.GONE
        binding.btnPlayVideo.visibility = View.GONE
    }

    private fun extractVideoId(videoUrl: String): String {
        return videoUrl.substringAfterLast("v=")
    }
}
