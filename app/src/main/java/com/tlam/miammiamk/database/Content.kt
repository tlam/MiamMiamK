package com.tlam.miammiamk.database

import android.content.ContentValues
import android.location.Location
import android.net.Uri
import android.util.Log
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson

object Content {
    private val gson = Gson()
    private val tag = "Content"
/*
    val CUISINE = object : Crud<CuisineTable> {
        override fun insert(what: CuisineTable): Long {
            val inserted = insert(listOf(what))
            if (!inserted.isEmpty()) return inserted[0]
            return 0
        }
    }
    */
}