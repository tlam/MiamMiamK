package com.tlam.miammiamk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.tlam.miammiamk.adapters.CuisinesListViewAdapter
import com.tlam.miammiamk.models.Cuisines

class MainActivity : AppCompatActivity() {

    var listView: ListView? = null
    var cuisineList = ArrayList<Cuisines>()
    var adapter: CuisinesListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.list)
        adapter = CuisinesListViewAdapter(this, cuisineList)
        (listView as ListView).adapter = adapter

        prepareCuisineData()

        (listView as ListView).onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, i,
            l -> Toast.makeText(applicationContext, cuisineList?.get(i)?.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun prepareCuisineData() {
        var cuisine = Cuisines("Japanese", "Asian")
        cuisineList.add(cuisine)

        cuisine = Cuisines("Italian", "European")
        cuisineList.add(cuisine)

        cuisine = Cuisines("Mexican", "Central America")
        cuisineList.add(cuisine)

        adapter?.notifyDataSetChanged()
    }

}