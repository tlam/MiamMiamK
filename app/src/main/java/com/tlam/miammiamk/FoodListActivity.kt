package com.tlam.miammiamk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.tlam.miammiamk.adapters.FoodRecyclerViewAdapter
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.models.Food

class FoodListActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val CUISINE_KEY = "CUISINE"
    private var cuisine: Cuisine? = null
    var recyclerView: RecyclerView? = null
    var adapter: FoodRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_list)

        cuisine = intent.getParcelableExtra<Cuisine>(CUISINE_KEY) as Cuisine
        cuisine?.let {
            adapter = FoodRecyclerViewAdapter(it.foods)
            recyclerView = findViewById<RecyclerView>(R.id.foodRecyclerView)
            linearLayoutManager = LinearLayoutManager(this)
            recyclerView!!.layoutManager = linearLayoutManager
            recyclerView!!.adapter = adapter
        }
    }
}