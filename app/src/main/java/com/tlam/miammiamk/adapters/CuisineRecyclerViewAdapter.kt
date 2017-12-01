package com.tlam.miammiamk.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.tlam.miammiamk.R
import com.tlam.miammiamk.models.Cuisine

class CuisineRecyclerViewAdapter(private val cuisineList: List<Cuisine>) : RecyclerView.Adapter<CuisineRecyclerViewAdapter.CuisineHolder>() {

    inner class CuisineHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var title: TextView = view.findViewById<TextView>(R.id.title)
        var genre: TextView = view.findViewById<TextView>(R.id.genre)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val context = itemView.context
            Toast.makeText(context, "HELLO", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.cuisine_list_row, parent, false)

        return CuisineHolder(itemView)
    }

    override fun onBindViewHolder(holder: CuisineHolder, position: Int) {
        val Cuisines = cuisineList[position]
        holder.title.text = Cuisines.title
        holder.genre.text = Cuisines.genre
    }

    override fun getItemCount(): Int {
        return cuisineList.size
    }
}
