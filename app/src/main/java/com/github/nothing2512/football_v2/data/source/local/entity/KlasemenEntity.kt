package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues

data class KlasemenEntity(
    val teamId: Int,
    val idLeague: Int,
    val name: String,
    val played: Int,
    val goalsfor: Int,
    val goalsagainst: Int,
    val goalsdifference: Int,
    val win: Int,
    val draw: Int,
    val loss: Int,
    val total: Int
) {

    fun getValue() = ContentValues().apply {
        put("teamId", teamId)
        put("idLeague", idLeague)
        put("name", name)
        put("goalsfor", goalsfor)
        put("goalsagainst", goalsagainst)
        put("goalsdifference", goalsdifference)
        put("win", win)
        put("draw", draw)
        put("loss", loss)
        put("total", total)
    }
}