package com.tlam.miammiamk.database

import android.content.ContentValues
import android.util.Log
import com.tlam.miammiamk.models.Cuisine
import com.tlam.miammiamk.models.Food

object Content {

    private val tag = "Db"
    private val version = 1
    private val name = "miammiamk"

    val CUISINE = object : Crud<Cuisine> {

        override fun insert(what: Cuisine): Long {
            val inserted = insert(listOf(what))
            if (!inserted.isEmpty()) return inserted[0]
            return 0
        }

        override fun insert(what: Collection<Cuisine>): List<Long> {
            val db = DbHelper(name, version).writableDatabase
            db.beginTransaction()
            var inserted = 0
            val items = mutableListOf<Long>()
            what.forEach { item ->
                val values = ContentValues()
                val table = DbHelper.TABLE_CUISINES
                values.put(DbHelper.CUISINE_NAME, item.name)
                values.put(DbHelper.CUISINE_GENRE, item.genre)
                val id = db.insert(table, null, values)
                if (id > 0) {
                    items.add(id)
                    Log.v(tag, "Entry ID assigned [ $id ]")
                    inserted++
                }
            }
            val success = inserted == what.size
            if (success) {
                db.setTransactionSuccessful()
            } else {
                items.clear()
            }
            db.endTransaction()
            db.close()
            return items
        }

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
                values.put(DbHelper.CUISINE_GENRE, item.genre)
                val id = db.replace(table, null, values)
                if (id > 0) {
                    items.add(id)
                    Log.v(tag, "Entry ID assigned [ $id ]")
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
            val foods = mutableListOf<Food>()
            val cursor = db.query(
                    true,
                    DbHelper.TABLE_CUISINES,
                    null, null, null, null, null, null, null
            )
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CUISINE_NAME))
                val genre = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CUISINE_GENRE))
                val cuisine = Cuisine(id, name, genre, foods)
                //cuisine.id = id
                result.add(cuisine)
            }
            cursor.close()
            return result
        }
    }
}