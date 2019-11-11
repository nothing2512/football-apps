package com.github.nothing2512.football_v2.data.source.remote.response

import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity

data class TeamResponse(
    val teams: List<TeamEntity>
)