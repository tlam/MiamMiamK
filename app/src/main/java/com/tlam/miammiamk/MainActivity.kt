package com.tlam.miammiamk

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.tlam.miammiamk.adapters.CuisineRecyclerViewAdapter
import com.tlam.miammiamk.database.Content
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.services.MainService

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    var recyclerView: RecyclerView? = null
    var cuisineList = ArrayList<Cuisine>()
    var adapter: CuisineRecyclerViewAdapter? = null

    companion object {
        const val tag = "MainActivity"
        var ctx: Context? = null
        var toolbar: Toolbar? = null
    }

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
                cuisineList.clear()
                prepareCuisineData()
                Toast.makeText(this, "Cuisines refreshed!", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.action_sync -> {
                Log.v(tag, "Syncing data")
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
        var dbFoods = Content.FOOD.selectAll()
        var dbCuisines = Content.CUISINE.selectAll()
        for (dbCuisine in dbCuisines) {
            for (food in dbFoods) {
                if (dbCuisine.id == food.cuisineId) {
                    dbCuisine.foods.add(food)
                }
            }
            cuisineList.add(dbCuisine)
        }

        adapter?.notifyDataSetChanged()
    }

}