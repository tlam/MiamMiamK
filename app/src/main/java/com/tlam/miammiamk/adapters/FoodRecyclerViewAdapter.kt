package com.tlam.miammiamk.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.tlam.miammiamk.R
import com.tlam.miammiamk.models.Food

class FoodRecyclerViewAdapter(private val foodList: List<Food>) : RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodHolder>() {

    inner class FoodHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private var food: Food? = null

        var title: TextView = view.findViewById<TextView>(R.id.title)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("FoodRecyclerView", "CLICK Food!")
        }

        fun bindFood(food: Food) {
            this.food = food
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
        holder.title.text = food.title
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}
