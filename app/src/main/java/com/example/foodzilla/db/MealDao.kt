package com.example.foodzilla.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodzilla.dataClasses.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal:Meal)

    @Delete
    suspend fun delete(meal:Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeals():LiveData<List<Meal>>
}