package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues

data class SearchTeamEntity(
    val idTeam: Int,
    val keyword: String
) {

    fun getValue() = ContentValues().apply {
        put("idTeam", idTeam)
        put("keyword", keyword)
    }
}