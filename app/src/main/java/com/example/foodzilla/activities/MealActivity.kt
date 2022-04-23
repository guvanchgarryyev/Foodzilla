package com.example.foodzilla.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.foodzilla.R
import com.example.foodzilla.dataClasses.Meal
import com.example.foodzilla.databinding.ActivityMainBinding
import com.example.foodzilla.databinding.ActivityMealBinding
import com.example.foodzilla.fragments.HomeFragment
import com.example.foodzilla.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var binding:ActivityMealBinding
    private lateinit var mealViewModel:MealViewModel
    private lateinit var youtubeLink:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealViewModel = ViewModelProviders.of(this)[MealViewModel::class.java]

        getMealInfoFromIntent()
        setInfoInViews()

        loadingCase()
        mealViewModel.getMealDetail(mealId)
        observeMealDetailsLiveData()

        onYoutubeIconClicked()
    }

    private fun onYoutubeIconClicked() {
        binding.imageYoutube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observeMealDetailsLiveData() {
        mealViewModel.observerMealDetailsLiveData().observe(this, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t
                binding.categoryTextView.text = "Category : ${meal!!.strCategory}"
                binding.areaTextView.text = "Area : ${meal.strArea}"
                binding.instructionStepsTextView.text = meal.strInstructions
                youtubeLink = meal.strYoutube
            }

        })
    }

    private fun setInfoInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imageMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInfoFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.addToFavoritesButton.visibility = View.INVISIBLE
        binding.instructionTextview.visibility = View.INVISIBLE
        binding.categoryTextView.visibility = View.INVISIBLE
        binding.areaTextView.visibility = View.INVISIBLE
        binding.imageYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.addToFavoritesButton.visibility = View.VISIBLE
        binding.instructionTextview.visibility = View.VISIBLE
        binding.categoryTextView.visibility = View.VISIBLE
        binding.areaTextView.visibility = View.VISIBLE
        binding.imageYoutube.visibility = View.VISIBLE
    }
}