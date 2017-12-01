package com.tlam.miammiamk.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.tlam.miammiamk.R
import com.tlam.miammiamk.models.Food

import java.util.ArrayList


class FoodListViewAdapter(
        private val activity: Activity,
        foodList: List<Food>) : BaseAdapter() {

    private var foodList = ArrayList<Food>()

    init {
        this.foodList = foodList as ArrayList<Food>
    }

    override fun getCount(): Int {
        return foodList.size
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
            vi = inflater.inflate(R.layout.food_list, null)
            // TODO: how to fill that list?

        } else {
            vi = convertView
        }
        return vi
    }
}
