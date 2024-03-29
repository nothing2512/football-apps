package com.github.nothing2512.football_v2.utils

object Constants {

    const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    const val IDLING = "GLOBAL"

    const val THREAD_COUNT = 3

    const val STATE_NEXT = 0
    const val STATE_PREVIUS = 1

    const val DATE_COLUMN = 0
    const val TIME_COLUMN = 1

    const val EXTRA_ID = "eid"
    const val EXTRA_PARCELABLE = "EPe"

    const val LEAGUE_TITLE = "Leagues"
    const val SEARCH_TITLE = "Matches"

    const val TYPE_HOME = 0
    const val TYPE_AWAY = 1

    const val STATE_LEAGUE = 0
    const val STATE_EVENT = 1
    const val STATE_TEAM = 2
}