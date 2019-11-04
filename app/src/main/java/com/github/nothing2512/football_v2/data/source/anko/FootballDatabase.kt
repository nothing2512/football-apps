package com.github.nothing2512.football_v2.data.source.anko

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

@SuppressLint("StaticFieldLeak")
class FootballDatabase(context: Context)
    : ManagedSQLiteOpenHelper(context, "Football", null, 1) {

    init {
        instance = this
    }

    companion object {

        private var instance: FootballDatabase? = null

        @Synchronized
        fun getInstance(context: Context) = instance ?: FootballDatabase(context)
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable(
            "event", true,
            "idEvent" to INTEGER + PRIMARY_KEY + UNIQUE,
            "idLeague" to INTEGER,
            "strEvent" to TEXT,
            "strLeague" to TEXT,
            "strHomeTeam" to TEXT,
            "strAwayTeam" to TEXT,
            "intAwayScore" to INTEGER ,
            "intHomeScore" to INTEGER,
            "intRound" to INTEGER,
            "strHomeYellowCards" to TEXT,
            "strAwayYellowCards" to TEXT,
            "strHomeRedCards" to TEXT,
            "strAwayRedCards" to TEXT,
            "dateEvent" to TEXT,
            "strTime" to TEXT,
            "strThumb" to TEXT,
            "strSport" to TEXT,
            "state" to INTEGER,
            "love" to INTEGER
        )

        db?.createTable(
            "league", true,
            "idLeague" to INTEGER + PRIMARY_KEY + UNIQUE,
            "strLeague" to TEXT,
            "intFormedYear" to TEXT,
            "dateFirstEvent" to TEXT,
            "strGender" to TEXT,
            "strCountry" to TEXT,
            "strWebsite" to TEXT,
            "strFacebook" to TEXT,
            "strTwitter" to TEXT,
            "strYoutube" to TEXT,
            "strDescriptionEN" to TEXT,
            "strBadge" to TEXT,
            "love" to INTEGER
        )

        db?.createTable(
            "search", true,
            "idEvent" to INTEGER + PRIMARY_KEY,
            "keyword" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("event", true)
        db?.dropTable("league", true)
        db?.dropTable("search", true)
    }
}