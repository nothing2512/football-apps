package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["idEvent"])
data class SearchEntity(val idEvent: Int, val keyword: String) {

    fun getValue() = ContentValues().apply {
        put("idEvent", idEvent)
        put("keyword", keyword)
    }
}