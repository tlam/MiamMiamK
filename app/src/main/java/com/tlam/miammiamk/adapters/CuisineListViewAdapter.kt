package com.tlam.miammiamk.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.tlam.miammiamk.R
import com.tlam.miammiamk.models.Cuisine

import java.util.ArrayList



class CuisineListViewAdapter(
        private val activity: Activity,
        cuisineList: List<Cuisine>) : BaseAdapter() {

    private var cuisineList = ArrayList<Cuisine>()

    init {
        this.cuisineList = cuisineList as ArrayList<Cuisine>
    }

    override fun getCount(): Int {
        return cuisineList.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View? {
        val vi: View?

        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vi = inflater.inflate(R.layout.cuisine_list_row, null)
            val title = vi.findViewById<TextView>(R.id.title)
            val genre = vi.findViewById<TextView>(R.id.genre)

            title.text = cuisineList[i].title
            genre.text = cuisineList[i].genre

        } else {
            vi = convertView
        }
        return vi
    }
}
