package com.tlam.miammiamk.database

import android.content.ContentValues
import android.util.Log
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.models.Food

object Content {

    private val tag = "Db"
    private val version = 2
    private val name = "miammiamk"

    val CUISINE = object : Crud<Cuisine> {

        override fun replace(what: Cuisine): Long {
            val inserted = replace(listOf(what))
            if (!inserted.isEmpty()) return inserted[0]
            return 0
        }

        override fun replace(what: Collection<Cuisine>): List<Long> {
            val db = DbHelper(name, version).writableDatabase
            db.beginTransaction()
            var replaced = 0
            val items = mutableListOf<Long>()
            what.forEach { item ->
                val values = ContentValues()
                val table = DbHelper.TABLE_CUISINES
                values.put(DbHelper.ID, item.id)
                values.put(DbHelper.CUISINE_NAME, item.name)
                values.put(DbHelper.CUISINE_ORIGIN, item.origin)
                val id = db.replace(table, null, values)
                if (id > 0) {
                    items.add(id)
                    Log.v(tag, "Cuisine ID assigned [ $id ]")
                    replaced++
                }
            }
            val success = replaced == what.size
            if (success) {
                db.setTransactionSuccessful()
            } else {
                items.clear()
            }
            db.endTransaction()
            db.close()
            return items
        }

        override fun selectAll(): List<Cuisine> {
            val db = DbHelper(name, version).writableDatabase
            val result = mutableListOf<Cuisine>()
            val cursor = db.query(
                    true,
                    DbHelper.TABLE_CUISINES,
                    null, null, null, null, null, null, null
            )
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CUISINE_NAME))
                val origin = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CUISINE_ORIGIN))
                val foods = mutableListOf<Food>()
                val cuisine = Cuisine(id, name, origin, foods)
                result.add(cuisine)
            }
            cursor.close()
            return result
        }
    }

    val FOOD = object : Crud<Food> {

        override fun replace(what: Food): Long {
            val inserted = replace(listOf(what))
            if (!inserted.isEmpty()) return inserted[0]
            return 0
        }

        override fun replace(what: Collection<Food>): List<Long> {
            val db = DbHelper(name, version).writableDatabase
            db.beginTransaction()
            var replaced = 0
            val items = mutableListOf<Long>()
            what.forEach { item ->
                val values = ContentValues()
                val table = DbHelper.TABLE_FOODS
                values.put(DbHelper.ID, item.id)
                values.put(DbHelper.FOOD_NAME, item.name)
                values.put(DbHelper.FOOD_DESCRIPTION, item.description)
                values.put(DbHelper.FOOD_SOURCE, item.source)
                values.put(DbHelper.FOOD_CUISINE, item.cuisineId)
                val id = db.replace(table, null, values)
                if (id > 0) {
                    items.add(id)
                    Log.v(tag, "Food ID assigned [ $id ]")
                    replaced++
                }
            }
            val success = replaced == what.size
            if (success) {
                db.setTransactionSuccessful()
            } else {
                items.clear()
            }
            db.endTransaction()
            db.close()
            return items
        }

        override fun selectAll(): List<Food> {
            val db = DbHelper(name, version).writableDatabase
            val result = mutableListOf<Food>()
            val cursor = db.query(
                    true,
                    DbHelper.TABLE_FOODS,
                    null, null, null, null, null, DbHelper.FOOD_CUISINE, null
            )
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.FOOD_NAME))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.FOOD_DESCRIPTION))
                val source = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.FOOD_SOURCE))
                val cuisineId = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.FOOD_CUISINE))
                val food = Food(id, name, description, source, cuisineId)
                result.add(food)
            }
            cursor.close()
            return result
        }
    }
}