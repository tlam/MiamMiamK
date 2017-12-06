package com.tlam.miammiamk.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.tlam.miammiamk.R
import com.tlam.miammiamk.FoodActivity
import com.tlam.miammiamk.models.Food

class FoodRecyclerViewAdapter(private val foodList: List<Food>) : RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodHolder>() {

    inner class FoodHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var food: Food? = null
        private val FOOD_KEY = "FOOD"

        var name: TextView = view.findViewById<TextView>(R.id.name)
        var description: TextView = view.findViewById<TextView>(R.id.description)
        var image: ImageView = view.findViewById<ImageView>(R.id.imageView)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("FoodRecyclerView", "CLICK Food!")
            val context = itemView.context

            val showFoodIntent = Intent(context, FoodActivity::class.java)
            showFoodIntent.putExtra(FOOD_KEY, this.food)
            context.startActivity(showFoodIntent)
        }

        fun bindFood(food: Food) {
            this.food = food
            Glide.with(this.itemView)
                    .load(food.source)
                    .into(this.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.food_list_row, parent, false)
        return FoodHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val food = foodList[position]
        holder.bindFood(food)
        holder.name.text = food.name
        holder.description.text = food.description
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}
