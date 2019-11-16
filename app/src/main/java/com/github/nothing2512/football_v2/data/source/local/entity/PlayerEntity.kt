package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues

data class PlayerEntity(
    val idPlayer: Int,
    val idTeam: Int,
    val strNationality: String,
    val strPlayer: String,
    val strTeam: String,
    val dateBorn: String,
    val strNumber: String?,
    val dateSigned: String?,
    val strWage: String,
    val strKit: String?,
    val strBirthLocation: String,
    val strDescriptionEN: String,
    val strSide: String?,
    val strPosition: String,
    val strHeight: String,
    val strThumb: String
) {

    fun getValue() = ContentValues().apply {
        put("idPlayer", idPlayer)
        put("idTeam", idTeam)
        put("strNationality", strNationality)
        put("strPlayer", strPlayer)
        put("strTeam", strTeam)
        put("dateBorn", dateBorn)
        put("strNumber", strNumber)
        put("dateSigned", dateSigned)
        put("strWage", strWage)
        put("strKit", strKit)
        put("strBirthLocation", strBirthLocation)
        put("strDescriptionEN", strDescriptionEN)
        put("strSide", strSide)
        put("strPosition", strPosition)
        put("strHeight", strHeight)
        put("strThumb", strThumb)
    }
}