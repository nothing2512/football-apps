package com.github.nothing2512.football_v2.data.source.remote.response

import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity


data class LeagueResponse(
    val leagues: List<LeagueEntity>
)