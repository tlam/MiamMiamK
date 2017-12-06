package com.tlam.miammiamk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.CardView
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.tlam.miammiamk.adapters.FoodRecyclerViewAdapter
import com.tlam.miammiamk.models.Food

class FoodActivity : AppCompatActivity() {
    private val FOOD_KEY = "FOOD"
    private var food: Food? = null
    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_item)

        imageView = findViewById<ImageView>(R.id.imageView)
        food = intent.getParcelableExtra<Food>(FOOD_KEY) as Food
        food?.let {
            Glide.with(this)
                    .load(it.source)
                    .into(imageView)
        }
    }
}