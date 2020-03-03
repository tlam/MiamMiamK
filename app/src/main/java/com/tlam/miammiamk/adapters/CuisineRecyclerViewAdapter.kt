package com.tlam.miammiamk.adapters

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.tlam.miammiamk.FoodListActivity
import com.tlam.miammiamk.R
import com.tlam.miammiamk.models.Cuisine


class CuisineRecyclerViewAdapter(private val cuisineList: List<Cuisine>) : RecyclerView.Adapter<CuisineRecyclerViewAdapter.CuisineHolder>() {

    inner class CuisineHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val CUISINE_KEY = "CUISINE"
        private var cuisine: Cuisine? = null

        var name: TextView = view.findViewById<TextView>(R.id.name)
        var origin: TextView = view.findViewById<TextView>(R.id.origin)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context

            val showFoodListIntent = Intent(context, FoodListActivity::class.java)
            showFoodListIntent.putExtra(CUISINE_KEY, this.cuisine)
            context.startActivity(showFoodListIntent)
        }

        fun bindCuisine(cuisine: Cuisine) {
            this.cuisine = cuisine
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.cuisine_list_row, parent, false)
        return CuisineHolder(itemView)
    }

    override fun onBindViewHolder(holder: CuisineHolder, position: Int) {
        val cuisine = cuisineList[position]
        holder.bindCuisine(cuisine)
        holder.name.text = cuisine.name
        holder.origin.text = cuisine.origin
    }

    override fun getItemCount(): Int {
        return cuisineList.size
    }
}
