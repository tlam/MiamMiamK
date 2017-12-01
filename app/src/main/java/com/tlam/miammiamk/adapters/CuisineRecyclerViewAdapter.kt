package com.tlam.miammiamk.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
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

        var title: TextView = view.findViewById<TextView>(R.id.title)
        var genre: TextView = view.findViewById<TextView>(R.id.genre)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            //Toast.makeText(context, "HELLO", Toast.LENGTH_SHORT).show()

            val showFoodListIntent = Intent(context, FoodListActivity::class.java)
            showFoodListIntent.putExtra(CUISINE_KEY, this.cuisine.title)
            context.startActivity(showFoodListIntent)
        }

        fun bindCuisine(cuisine: Cuisine) {
            this.cuisine = cuisine
            /*
            Picasso.with(view.context).load(photo.url).into(view.itemImage)
            view.itemDate.text = photo.humanDate
            view.itemDescription.text = photo.explanation
            */
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.cuisine_list_row, parent, false)
/*
        mUserList.setOnUserClickListener(User user -> {
            final Intent intent = UserDetailActivity.newIntent(this, user);
            startActivity(intent);
        })
  */
        return CuisineHolder(itemView)
    }

    override fun onBindViewHolder(holder: CuisineHolder, position: Int) {
        val cuisine = cuisineList[position]
        holder.bindCuisine(cuisine)
        holder.title.text = cuisine.title
        holder.genre.text = cuisine.genre
    }

    override fun getItemCount(): Int {
        return cuisineList.size
    }
}
