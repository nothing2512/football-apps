package com.github.nothing2512.football_v2.utils.data.source

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.github.nothing2512.football_v2.data.source.local.FootballDatabase

class DatabaseUtil(private val context: Context) : FootballDatabase(context, "testDb") {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun drop() {
        context.deleteDatabase("testDb")
    }
}