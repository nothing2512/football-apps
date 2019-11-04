package com.github.nothing2512.football_v2.data.source.local.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["idEvent"])
data class SearchEntity(
    @field:SerializedName("idEvent") val idEvent: Int,
    @field:SerializedName("keyword") val keyword: String
)