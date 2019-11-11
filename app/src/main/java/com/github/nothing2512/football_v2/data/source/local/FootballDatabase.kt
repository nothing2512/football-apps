package com.github.nothing2512.football_v2.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.github.nothing2512.football_v2.testing.OpenForTesting
import org.jetbrains.anko.db.*

@Suppress("LeakingThis")
@OpenForTesting
@SuppressLint("StaticFieldLeak")
class FootballDatabase(context: Context, dbName: String = "football") :
    ManagedSQLiteOpenHelper(context, dbName, null, 1) {

    init {
        instance = this
    }

    companion object {

        private var instance: FootballDatabase? = null

        @Synchronized
        fun getInstance(context: Context) = instance
            ?: FootballDatabase(context)
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.apply {
            createTable(
                "event", true,
                "idEvent" to INTEGER + PRIMARY_KEY + UNIQUE,
                "idLeague" to INTEGER,
                "strEvent" to TEXT,
                "strLeague" to TEXT,
                "strHomeTeam" to TEXT,
                "strAwayTeam" to TEXT,
                "intAwayScore" to INTEGER,
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

            createTable(
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

            createTable(
                "klasemen", true,
                "teamId" to INTEGER + PRIMARY_KEY + UNIQUE,
                "idLeague" to INTEGER,
                "name" to TEXT,
                "played" to INTEGER,
                "goalsfor" to INTEGER,
                "goalsagainst" to INTEGER,
                "goalsdifference" to INTEGER,
                "win" to INTEGER,
                "draw" to INTEGER,
                "loss" to INTEGER,
                "total" to INTEGER
            )

            createTable(
                "search", true,
                "idEvent" to INTEGER + PRIMARY_KEY,
                "keyword" to TEXT
            )

            createTable(
                "player", true,
                "idPlayer" to INTEGER + PRIMARY_KEY + UNIQUE,
                "idTeam" to INTEGER,
                "strNationality" to TEXT,
                "strPlayer" to TEXT,
                "strTeam" to TEXT,
                "dateBorn" to TEXT,
                "strNumber" to TEXT,
                "dateSigned" to TEXT,
                "strWage" to TEXT,
                "strKit" to TEXT,
                "strBidthLocation" to TEXT,
                "strDescriptionEN" to TEXT,
                "strSide" to TEXT,
                "strPosition" to TEXT,
                "strHeight" to TEXT,
                "strThumb" to TEXT
            )

            createTable(
                "team", true,
                "idTeam" to INTEGER + PRIMARY_KEY + UNIQUE,
                "intLoved" to INTEGER,
                "strTeam" to TEXT,
                "intFormedYear" to INTEGER,
                "strLeague" to TEXT,
                "strStadium" to TEXT,
                "strStadiumThumb" to TEXT,
                "strStadiumDescription" to TEXT,
                "strStadiumLocation" to TEXT,
                "intStadiumCapacity" to INTEGER,
                "strWebsite" to TEXT,
                "strFacebook" to TEXT,
                "strTwitter" to TEXT,
                "strInstagram" to TEXT,
                "strYoutube" to TEXT,
                "strDescriptionEN" to TEXT,
                "strGender" to TEXT,
                "strCountry" to TEXT,
                "strBadge" to TEXT,
                "strTeamJersey" to TEXT,
                "strTeamLogo" to TEXT,
                "strTeamFanart1" to TEXT,
                "strTeamFanart2" to TEXT,
                "strTeamFanart3" to TEXT,
                "strTeamFanart4" to TEXT,
                "strTeamBanner" to TEXT
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.apply {
            dropTable("event", true)
            dropTable("league", true)
            dropTable("search", true)
            dropTable("klasemen", true)
            dropTable("player", true)
            dropTable("team", true)
        }

    }
}