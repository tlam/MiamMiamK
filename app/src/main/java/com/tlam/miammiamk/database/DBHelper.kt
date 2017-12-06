package com.tlam.miammiamk.database

import android.database.sqlite.SQLiteDatabase 
import android.database.sqlite.SQLiteOpenHelper 
import android.util.Log

import com.tlam.miammiamk.MainActivity

class DBHelper(val dbName : String, val version : Int) : SQLiteOpenHelper(
    MainActivity.context, dbName, null, version) {

    private val tag = "DbHelper"
    private val createTableCuisines = """
        CREATE TABLE IF NOT EXISTS cuisines
            (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                genre TEXT
            )
        """

    private val createTableFoods = """
        CREATE TABLE IF NOT EXISTS foods
            (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT,
                source TEXT,
                FOREIGN KEY(cuisine_id) REFERENCES cuisines(id)
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
