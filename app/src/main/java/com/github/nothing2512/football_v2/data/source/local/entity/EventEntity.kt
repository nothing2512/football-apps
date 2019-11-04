package com.github.nothing2512.football_v2.data.source.local.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["idEvent"])
data class EventEntity(
    @field:SerializedName("idEvent") val idEvent: Int,
    @field:SerializedName("idLeague") val idLeague: Int,
    @field:SerializedName("strEvent") val strEvent: String,
    @field:SerializedName("strLeague") val strLeague: String,
    @field:SerializedName("strHomeTeam") val strHomeTeam: String?,
    @field:SerializedName("strAwayTeam") val strAwayTeam: String?,
    @field:SerializedName("intAwayScore") val intAwayScore: Int?,
    @field:SerializedName("intHomeScore") val intHomeScore: Int?,
    @field:SerializedName("intRound") val intRound: Int,
    @field:SerializedName("strHomeYellowCards") val strHomeYellowCards: String?,
    @field:SerializedName("strAwayYellowCars") val strAwayYellowCards: String?,
    @field:SerializedName("strHomeRedCards") val strHomeRedCards: String?,
    @field:SerializedName("strAwayRedCards") val strAwayRedCards: String?,
    @field:SerializedName("dateEvent") val dateEvent: String,
    @field:SerializedName("strTime") val strTime: String,
    @field:SerializedName("strThumb") val strThumb: String?,
    @field:SerializedName("strSport") val strSport: String,
    @field:SerializedName("state") var state: Int?,
    @field:SerializedName("love") var love: Boolean = false
) {

    fun isFootball() = strSport == "Soccer"
}