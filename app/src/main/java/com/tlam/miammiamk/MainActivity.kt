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
        var cuisine = Cuisine(
                "Japanese",
                "Asian",
                listOf(
                        Food("Makizushi", "Cylindrical piece"),
                        Food("Nigirizushi", "Topping on oval shaped ball of rice")))
        cuisineList.add(cuisine)

        cuisine = Cuisine(
                "Italian",
                "European",
                listOf(
                        Food("Farfalle", "Butterfly looking"),
                        Food("Spaghetti", "Long, thin and cylindrical")))
        cuisineList.add(cuisine)

        cuisine = Cuisine(
                "Mexican",
                "Central America",
                listOf(
                        Food("Enchilada", "Corn tortilla rolled around a filling and covered with a chili pepper sauce"),
                        Food("Fajita", "Any grilled meat usually served as a taco")))
        cuisineList.add(cuisine)

        adapter?.notifyDataSetChanged()
    }

}