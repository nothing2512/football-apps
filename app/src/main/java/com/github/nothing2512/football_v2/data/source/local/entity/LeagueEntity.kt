package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["idLeague"])
data class LeagueEntity(
    val idLeague: Int,
    val strLeague: String,
    val intFormedYear: String?,
    val dateFirstEvent: String?,
    val strGender: String?,
    val strCountry: String?,
    val strWebsite: String?,
    val strFacebook: String?,
    val strTwitter: String?,
    val strYoutube: String?,
    val strDescriptionEN: String?,
    val strBadge: String?,
    var love: Int
) {

    fun getvalue() = ContentValues().apply {
        put("idLeague", idLeague)
        put("strLeague", strLeague)
        put("intFormedYear", intFormedYear)
        put("dateFirstEvent", dateFirstEvent)
        put("strGender", strGender)
        put("strCountry", strCountry)
        put("strWebsite", strWebsite)
        put("strFacebook", strFacebook)
        put("strTwitter", strTwitter)
        put("strYoutube", strYoutube)
        put("strDescriptionEN", strDescriptionEN)
        put("strBadge", strBadge)
        put("love", love)
    }
}