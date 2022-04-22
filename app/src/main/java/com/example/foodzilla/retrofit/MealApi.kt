package com.example.foodzilla.retrofit

import com.example.foodzilla.dataClasses.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<MealList>
}