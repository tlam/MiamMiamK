package com.tlam.miammiamk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tlam.miammiamk.adapters.CuisineRecyclerViewAdapter
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.models.Food

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    var recyclerView: RecyclerView? = null
    var cuisineList = ArrayList<Cuisine>()
    var adapter: CuisineRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CuisineRecyclerViewAdapter(cuisineList)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.adapter = adapter

        prepareCuisineData()
    }

    private fun prepareCuisineData() {
        var cuisine = Cuisine("Japanese", "Asian", listOf(Food("Maki"), Food("Sushi")))
        cuisineList.add(cuisine)

        cuisine = Cuisine("Italian", "European", listOf(Food("Farfalle"), Food("Spaghetti")))
        cuisineList.add(cuisine)

        cuisine = Cuisine("Mexican", "Central America", listOf(Food("Enchilada"), Food("Fajita")))
        cuisineList.add(cuisine)

        adapter?.notifyDataSetChanged()
    }

}