package com.example.foodzilla.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodzilla.dataClasses.MealsByCategory
import com.example.foodzilla.databinding.PopularItemsBinding
import java.util.ArrayList

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {
    private var mealsList = ArrayList<MealsByCategory>()
    lateinit var onItemClick:((MealsByCategory) -> Unit)

    fun setMeals(mealsByCategoryList:ArrayList<MealsByCategory>){
        this.mealsList = mealsByCategoryList
        notifyDataSetChanged()
    }

    class PopularMealViewHolder(val binding:PopularItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imagePopularItems)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}