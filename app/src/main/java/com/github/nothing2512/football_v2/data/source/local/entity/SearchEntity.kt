package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues

data class SearchEntity(
    val idEvent: Int,
    val keyword: String
) {

    fun getValue() = ContentValues().apply {
        put("idEvent", idEvent)
        put("keyword", keyword)
    }
}