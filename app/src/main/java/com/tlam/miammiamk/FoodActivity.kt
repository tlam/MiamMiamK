package com.tlam.miammiamk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.tlam.miammiamk.models.Food

class FoodActivity : AppCompatActivity() {
    private val FOOD_KEY = "FOOD"
    private var food: Food? = null
    var imageView: ImageView? = null
    var nameView: TextView? = null
    var descriptionView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_item)

        imageView = findViewById<ImageView>(R.id.image_view)
        nameView = findViewById<TextView>(R.id.name)
        descriptionView = findViewById<TextView>(R.id.description)
        food = intent.getParcelableExtra<Food>(FOOD_KEY) as Food
        food?.let {
            Glide.with(this)
                    .load(it.source)
                    .into(imageView)
            nameView!!.text = it.name
            descriptionView!!.text = it.description
        }
    }
}