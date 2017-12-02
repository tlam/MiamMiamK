package com.tlam.miammiamk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.tlam.miammiamk.models.Cuisine

class FoodListActivity : AppCompatActivity() {

    private val CUISINE_KEY = "CUISINE"
    private var cuisine: Cuisine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_list)

        cuisine = intent.getParcelableExtra<Cuisine>(CUISINE_KEY)
    }
}