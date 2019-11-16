package com.github.nothing2512.football_v2.testing

import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.*
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse

object TestUtil {

    val LEAGUE_ITEM_BINDING = LeagueItemBindingData("name", 0, "desc") {}

    val EVENT_ENTITY = EventEntity(
        1,
        1,
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

    val EVENT_RESPONSE = EventResponse(
        listOf(
            EVENT_ENTITY,
            EVENT_ENTITY,
            EVENT_ENTITY
        )
    )

    val ARSENAL_RESPONSE = SearchResponse(
        listOf(
            EventEntity(
                1,
                1,
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                0,
                0,
                0,
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                0,
                0
            ),
            EventEntity(
                2,
                1,
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                0,
                0,
                0,
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                "arsenal",
                0,
                0
            )
        )
    )

    val BARCELONA_RESPONSE = SearchResponse(
        listOf(
            EventEntity(
                3,
                1,
                "barcelona",
                "barcelona",
                "barcelona",
                "barcelona",
                0,
                0,
                0,
                "barcelona",
                "barcelona",
                "barcelona",
                "barcelona",
                "barcelona",
                "barcelona",
                "barcelona",
                "barcelona",
                0,
                0
            )
        )
    )

    val SEARCH_RESPONSE = SearchResponse(
        listOf(
            EVENT_ENTITY,
            EVENT_ENTITY,
            EVENT_ENTITY
        )
    )

    val KLASEMEN_ENTITY = KlasemenEntity(
        1,
        1,
        "name",
        10,
        10,
        10
    )

    val PLAYER_ENTITY = PlayerEntity(
        1,
        1,
        "nationaity",
        "player",
        "team",
        "born",
        "10",
        "date",
        "wage",
        "kit",
        "birth loc",
        "desc",
        "side",
        "pos",
        "height",
        "thumb"
    )

    val TEAM_ENTITY = TeamEntity(
        1,
        1,
        1,
        "a",
        1,
        "1",
        "1",
        "1",
        "1",
        "1",
        1,
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        "1",
        0
    )

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
        1,
        1,
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
        1,
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
    const val BARCELONA_COUNT = 1
    const val ARSENAL_COUNT = 2
    const val LEAGUE_BINDING_COUNT = 1
    const val LOVED_COUNT = 1
}