package com.tlam.miammiamk.database

import android.database.sqlite.SQLiteDatabase 
import android.database.sqlite.SQLiteOpenHelper 
import android.util.Log

import com.tlam.miammiamk.MainActivity

class DbHelper(val dbName : String, val version : Int) : SQLiteOpenHelper(MainActivity.ctx, dbName, null, version) {

    companion object {
        val ID: String = "id"
        val TABLE_CUISINES = "cuisines"
        val TABLE_FOODS = "foods"
        val CUISINE_NAME: String = "name"
        val CUISINE_GENRE: String = "genre"
        val FOOD_NAME: String = "name"
        val FOOD_DESCRIPTION: String = "description"
        val FOOD_SOURCE: String = "source"
        val FOOD_CUISINE: String = "cuisine_id"
    }

    private val tag = "DbHelper"
    private val createTableCuisines = """
        CREATE TABLE IF NOT EXISTS $TABLE_CUISINES
            (
                $ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $CUISINE_NAME TEXT,
                $CUISINE_GENRE TEXT
            )
        """

    private val dropTableCuisines = """
        DROP TABLE $TABLE_CUISINES
        """

    private val createTableFoods = """
        CREATE TABLE IF NOT EXISTS $TABLE_FOODS
            (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                $FOOD_NAME TEXT,
                $FOOD_DESCRIPTION TEXT,
                $FOOD_SOURCE TEXT,
                $FOOD_CUISINE INTEGER,
                FOREIGN KEY(cuisine_id) REFERENCES $TABLE_CUISINES(id)
            )
        """
    override fun onCreate(db: SQLiteDatabase) {
        Log.d(tag, "Database [ CREATING ]")
        db.execSQL(createTableCuisines)
        db.execSQL(createTableFoods)
        Log.d(tag, "Database [ CREATED ]")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Ignore for now.
    }

}
