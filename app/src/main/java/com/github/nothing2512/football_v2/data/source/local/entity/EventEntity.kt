package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["idEvent"])
data class EventEntity(
    val idEvent: Int,
    val idLeague: Int,
    val strEvent: String,
    val strLeague: String,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intAwayScore: Int?,
    val intHomeScore: Int?,
    val intRound: Int,
    val strHomeYellowCards: String?,
    val strAwayYellowCards: String?,
    val strHomeRedCards: String?,
    val strAwayRedCards: String?,
    val dateEvent: String,
    val strTime: String,
    val strThumb: String?,
    val strSport: String,
    var state: Int?,
    var love: Int
) {

    fun isFootball() = strSport == "Soccer"

    fun getValue() = ContentValues().apply {
        put("idEvent", idEvent)
        put("idLeague", idLeague)
        put("strEvent", strEvent)
        put("strLeague", strLeague)
        put("strHomeTeam", strHomeTeam)
        put("strAwayTeam", strAwayTeam)
        put("intAwayScore", intAwayScore)
        put("intHomeScore", intHomeScore)
        put("intRound", intRound)
        put("strHomeYellowCards", strHomeYellowCards)
        put("strAwayYellowCards", strAwayYellowCards)
        put("strHomeRedCards", strHomeRedCards)
        put("strAwayRedCards", strAwayRedCards)
        put("dateEvent", dateEvent)
        put("strTime", strTime)
        put("strThumb", strThumb)
        put("strSport", strSport)
        put("state", state)
        put("love", love)
    }
}