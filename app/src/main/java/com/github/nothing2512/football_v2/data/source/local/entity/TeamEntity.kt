package com.github.nothing2512.football_v2.data.source.local.entity

import android.content.ContentValues
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamEntity(
    val idTeam: Int,
    val idLeague: Int?,
    val intLoved: Int?,
    val strTeam: String?,
    val intFormedYear: Int?,
    val strLeague: String?,
    val strStadium: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: Int?,
    val strWebsite: String?,
    val strFacebook: String?,
    val strTwitter: String?,
    val strInstagram: String?,
    val strYoutube: String?,
    val strDescriptionEN: String?,
    val strGender: String?,
    val strCountry: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamBanner: String?,
    var love: Int
): Parcelable {

    fun getValue() = ContentValues().apply {
        put("idTeam", idTeam)
        put("idLeague", idLeague)
        put("intLoved", intLoved)
        put("strTeam", strTeam)
        put("intFormedYear", intFormedYear)
        put("strLeague", strLeague)
        put("strStadium", strStadium)
        put("strStadiumThumb", strStadiumThumb)
        put("strStadiumDescription", strStadiumDescription)
        put("strStadiumLocation", strStadiumLocation)
        put("intStadiumCapacity", intStadiumCapacity)
        put("strWebsite", strWebsite)
        put("strFacebook", strFacebook)
        put("strTwitter", strTwitter)
        put("strInstagram", strInstagram)
        put("strYoutube", strYoutube)
        put("strDescriptionEN", strDescriptionEN)
        put("strGender", strGender)
        put("strCountry", strCountry)
        put("strTeamBadge", strTeamBadge)
        put("strTeamJersey", strTeamJersey)
        put("strTeamFanart1", strTeamFanart1)
        put("strTeamFanart2", strTeamFanart2)
        put("strTeamFanart3", strTeamFanart3)
        put("strTeamFanart4", strTeamFanart4)
        put("strTeamBanner", strTeamBanner)
        put("love", love)
    }
}