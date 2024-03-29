package com.example.foodzilla.retrofit

import com.example.foodzilla.dataClasses.CategoryList
import com.example.foodzilla.dataClasses.MealsByCategoryList
import com.example.foodzilla.dataClasses.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<MealList>

    @GET("lookup.php")
    fun getMealDetails(@Query("i") id:String) : Call<MealList>

    @GET("filter.php")
    fun getPopularItems(@Query("c") categoryName:String) : Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories() : Call<CategoryList>

    @GET("filter.php")
    fun getMealsbyCategory(@Query("c") categoryName: String) : Call<MealsByCategoryList>

    @GET("search.php")
    fun searchMeals(@Query("s") searchQuery:String) : Call<MealList>
}