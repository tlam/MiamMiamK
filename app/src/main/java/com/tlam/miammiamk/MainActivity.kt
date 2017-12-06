package com.tlam.miammiamk

import android.content.Context
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

    companion object {
        val tag = "MainActivity"
        var ctx: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ctx = applicationContext
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
                        Food("Makizushi", "Cylindrical piece", "https://cdn.pixabay.com/photo/2016/03/05/22/23/asian-1239272__340.jpg"),
                        Food("Nigirizushi", "Topping on oval shaped ball of rice", "https://cdn.pixabay.com/photo/2017/02/05/11/48/sushi-2039735__340.jpg")))
        cuisineList.add(cuisine)

        cuisine = Cuisine(
                "Italian",
                "European",
                listOf(
                        Food("Farfalle", "Butterfly looking", "https://cdn.pixabay.com/photo/2014/10/20/22/04/farfalle-495747__340.jpg"),
                        Food("Spaghetti", "Long, thin and cylindrical", "https://cdn.pixabay.com/photo/2017/11/08/22/18/spaghetti-2931846__340.jpg")))
        cuisineList.add(cuisine)

        cuisine = Cuisine(
                "Mexican",
                "Central America",
                listOf(
                        Food("Enchilada", "Corn tortilla rolled around a filling and covered with a chili pepper sauce", "https://cdn.pixabay.com/photo/2014/01/14/22/13/mexican-245240__340.jpg"),
                        Food("Fajita", "Any grilled meat usually served as a taco", "https://cdn.pixabay.com/photo/2014/11/07/17/14/tortillas-520808__340.jpg")))
        cuisineList.add(cuisine)

        adapter?.notifyDataSetChanged()
    }

}