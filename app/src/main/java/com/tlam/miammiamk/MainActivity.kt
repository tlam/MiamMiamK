package com.tlam.miammiamk

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.tlam.miammiamk.adapters.CuisineRecyclerViewAdapter
import com.tlam.miammiamk.database.Content
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.services.MainService

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var service: MainService? = null
    var recyclerView: RecyclerView? = null
    var cuisineList = ArrayList<Cuisine>()
    var adapter: CuisineRecyclerViewAdapter? = null

    companion object {
        val tag = "MainActivity"
        var ctx: Context? = null
        var toolbar: Toolbar? = null
    }
/*
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            service = null
            synchronize.enabled = false
        }

        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            if (binder is MainService.MainServiceBinder) {
                service = binder.getService()
                service?.let {
                    synchronize.enabled = true
                }
            }
        }
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ctx = applicationContext
        setContentView(R.layout.activity_main)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        adapter = CuisineRecyclerViewAdapter(cuisineList)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.adapter = adapter

        prepareCuisineData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                Log.v(tag, "Refreshing data")
                startService()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun startService() {
        val serviceIntent = Intent(this, MainService::class.java)
        startService(serviceIntent)
    }

    private fun stopService() {
        val serviceIntent = Intent(this, MainService::class.java)
        stopService(serviceIntent)
    }

    private fun prepareCuisineData() {
        /*
        var cuisine = Cuisine(
                1,
                "Japanese",
                "Asian",
                mutableListOf(
                        Food(
                                1,"Makizushi",
                                "Cylindrical piece",
                                "https://cdn.pixabay.com/photo/2016/03/05/22/23/asian-1239272__340.jpg",
                                1),
                        Food(
                                2,
                                "Nigirizushi",
                                "Topping on oval shaped ball of rice",
                                "https://cdn.pixabay.com/photo/2017/02/05/11/48/sushi-2039735__340.jpg",
                                1)))
        cuisineList.add(cuisine)

        cuisine = Cuisine(
                2,
                "Italian",
                "European",
                mutableListOf(
                        Food(
                                3,
                                "Farfalle",
                                "Butterfly looking",
                                "https://cdn.pixabay.com/photo/2014/10/20/22/04/farfalle-495747__340.jpg",
                                2),
                        Food(
                                4,
                                "Spaghetti",
                                "Long, thin and cylindrical",
                                "https://cdn.pixabay.com/photo/2017/11/08/22/18/spaghetti-2931846__340.jpg",
                                2)))
        cuisineList.add(cuisine)

        cuisine = Cuisine(
                3,
                "Mexican",
                "Central America",
                mutableListOf(
                        Food(
                                5,
                                "Enchilada",
                                "Corn tortilla rolled around a filling and covered with a chili pepper sauce",
                                "https://cdn.pixabay.com/photo/2014/01/14/22/13/mexican-245240__340.jpg",
                                3),
                        Food(
                                6,
                                "Fajita",
                                "Any grilled meat usually served as a taco",
                                "https://cdn.pixabay.com/photo/2014/11/07/17/14/tortillas-520808__340.jpg",
                                3)))
        cuisineList.add(cuisine)

        for (cuisine in cuisineList) {
            Content.CUISINE.replace(cuisine)
            Content.FOOD.replace(cuisine.foods)
        }
        */

        var dbFoods = Content.FOOD.selectAll()
        var dbCuisines = Content.CUISINE.selectAll()
        for (dbCuisine in dbCuisines) {
            Log.d("MainActivity:Cuisine", "${dbCuisine.id} ${dbCuisine.name}")
            for (food in dbFoods) {
                if (dbCuisine.id == food.cuisineId) {
                    dbCuisine.foods.add(food)
                    Log.d("MainActivity:Food", "${food.id} ${food.name}")
                }
            }
            cuisineList.add(dbCuisine)
        }

        adapter?.notifyDataSetChanged()
    }

}