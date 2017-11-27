package com.tlam.miammiamk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.tlam.miammiamk.adapters.CuisineListViewAdapter
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.models.Food

class MainActivity : AppCompatActivity() {

    var listView: ListView? = null
    var cuisineList = ArrayList<Cuisine>()
    var adapter: CuisineListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.list)
        adapter = CuisineListViewAdapter(this, cuisineList)
        (listView as ListView).adapter = adapter

        prepareCuisineData()

        (listView as ListView).onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, i,
            l -> Toast.makeText(applicationContext, cuisineList?.get(i)?.title, Toast.LENGTH_SHORT).show()
        }
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