package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues

data class KlasemenEntity(
    val teamid: Int,
    var idLeague: Int,
    val name: String,
    val win: Int,
    val draw: Int,
    val loss: Int
) {

    fun getValue() = ContentValues().apply {
        put("teamid", teamid)
        put("idLeague", idLeague)
        put("name", name)
        put("win", win)
        put("draw", draw)
        put("loss", loss)
    }
}