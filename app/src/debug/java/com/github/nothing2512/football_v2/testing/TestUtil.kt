package com.github.nothing2512.football_v2.testing

import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse

object TestUtil {

    val LEAGUE_ITEM_BINDING = LeagueItemBindingData("name", 0, "desc") {}

    val EVENT_ENTITY = EventEntity(
        1,
        0,
        "event",
        "league",
        "homeTeam",
        "awayTeam",
        0,
        0,
        0,
        "homeYellowCard",
        "awayYellowCard",
        "homeRedCard",
        "awayRedCard",
        "dateEvent",
        "time",
        "thumb",
        "soccer",
        0,
        0
    )

    val EVENT_RESPONSE = EventResponse(listOf(
        EVENT_ENTITY,
        EVENT_ENTITY,
        EVENT_ENTITY
    ))

    val SEARCH_RESPONSE = SearchResponse(listOf(
        EVENT_ENTITY,
        EVENT_ENTITY,
        EVENT_ENTITY
    ))

    val LEAGUE_ENTITY = LeagueEntity(
        1,
        "league",
        "formedYear",
        "dateFirstEvent",
        "gender",
        "country",
        "website",
        "facebook",
        "twitter",
        "youtube",
        "description",
        "badge",
        0
    )

    val NEXT_EVENT = EventEntity(
        0,
        0,
        "event",
        "league",
        "homeTeam",
        "awayTeam",
        0,
        0,
        0,
        "homeYellowCard",
        "awayYellowCard",
        "homeRedCard",
        "awayRedCard",
        "dateEvent",
        "time",
        "thumb",
        "soccer",
        0,
        0
    )

    val PREVIUS_EVENT = EventEntity(
        1,
        0,
        "event",
        "league",
        "homeTeam",
        "awayTeam",
        0,
        0,
        0,
        "homeYellowCard",
        "awayYellowCard",
        "homeRedCard",
        "awayRedCard",
        "dateEvent",
        "time",
        "thumb",
        "soccer",
        1,
        0
    )

    const val INT = 1
    const val STRING = "data"

    const val NEXT_EVENT_COUNT = 3
    const val PREVIUS_EVENT_COUNT = 3
    const val SEARCH_COUNT = 1
    const val LEAGUE_BINDING_COUNT = 1
    const val LOVED_COUNT = 1
}