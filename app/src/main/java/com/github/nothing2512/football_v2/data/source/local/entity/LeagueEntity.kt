package com.github.nothing2512.football_v2.data.source.local.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["idLeague"])
data class LeagueEntity(
    @field:SerializedName("idLeague") val idLeague: Int,
    @field:SerializedName("strLeague") val strLeague: String,
    @field:SerializedName("intFormedYear") val intFormedYear: String?,
    @field:SerializedName("dateFirstEvent") val dateFirstEvent: String?,
    @field:SerializedName("strGenre") val strGender: String?,
    @field:SerializedName("strCountry") val strCountry: String?,
    @field:SerializedName("strWebsite") val strWebsite: String?,
    @field:SerializedName("strFacebook") val strFacebook: String?,
    @field:SerializedName("strTwitter") val strTwitter: String?,
    @field:SerializedName("strYoutube") val strYoutube: String?,
    @field:SerializedName("strDescriptionEN") val strDescriptionEN: String?,
    @field:SerializedName("strBadge") val strBadge: String?,
    @field:SerializedName("love") val love: Boolean = false
)